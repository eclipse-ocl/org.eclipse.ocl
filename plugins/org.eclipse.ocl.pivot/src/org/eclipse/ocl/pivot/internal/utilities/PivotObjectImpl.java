/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotObject;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;

public abstract class PivotObjectImpl extends EObjectImpl implements PivotObject
{
	private @Nullable EObject esObject;		// always null for Model.

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		if (newContainer != null) {
			EObject oldContainer = eInternalContainer();
			assert (oldContainer == null) || oldContainer.eIsProxy() || (newContainer == oldContainer) || (oldContainer.eResource() == null);
		}
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
	}

	@Override
	public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
		for (EObject eObject : eContents()) {
			if (eObject instanceof Nameable) {
				String name = ((Nameable)eObject).getName();
				if ((name != null) && name.equals(uriFragmentSegment)) {
					return eObject;
				}
			}
		}
		return super.eObjectForURIFragmentSegment(uriFragmentSegment);
	}

	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		StringBuilder s = null;
		if (ASResourceImpl.PROXIES.isActive()) {
			s = new StringBuilder();
			s.append("eResolveProxy " + NameUtil.debugSimpleName(this) + " " + NameUtil.debugSimpleName(proxy) + " " + proxy.eProxyURI());
		}
		EObject resolvedProxy = super.eResolveProxy(proxy);
	/*	if (resolvedProxy instanceof Pivotable) {
			Resource resource = resolvedProxy.eResource();
			if (resource instanceof CSResource) {
				((CSResource)resource).getASResource();
			}
			resolvedProxy = ((Pivotable)resolvedProxy).getPivot();
		}
		else */ if (resolvedProxy instanceof EModelElement) {
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				try {
					resolvedProxy = ((EnvironmentFactoryInternalExtension)environmentFactory).getASOf(Element.class, resolvedProxy);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (s != null) {
			s.append(" => " + NameUtil.debugSimpleName(resolvedProxy));
			ASResourceImpl.PROXIES.println(s.toString());
		}
		return resolvedProxy;
	}

	@Override
	public void eSetProxyURI(URI uri) {
		StringBuilder s = null;
		ASResourceImpl.PROXIES.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " " + uri);
		super.eSetProxyURI(uri);
	}

	public @Nullable EObject getESObject() {
		assert !(this instanceof Model) : "no ESObject for Model";
		return esObject;
	}

	@Deprecated // Use getESObject()
	public @Nullable EObject getETarget() {
		return esObject;
	}

	@Override
	public Object getImage() {
		return null;
	}

	@Deprecated // Use getESObject()
	public @Nullable EObject getTarget() {
		return esObject;
	}

	@Override
	public String getText() {
		return toString();
	}

	/**
	 * preUnload() is invoked to support the depth-first traversal of an ASResource contents from ASResourceImpl.doUnload().
	 * The traversal assigns proxies from the esObject that is then set to null. Other pivot artefacts are also reset.
	 *
	 * @since 1.22
	 */
	public void preUnload() {
	    assert eResource() != null;
		for (EObject eObject : eContents()) {
			if (eObject instanceof PivotObjectImpl) {		// Propagate resetESObject through hierarchy (except for internal ExpressionInOCL)
				((PivotObjectImpl)eObject).preUnload();		// proxify the esObject before the eContainer() vanishes
			}
		}
		resetESObject();
	}

	/**
	 * resetESObject is called at the end of preUnload() to assign the URI of esObject as the proxy
	 * and optionally to diagnose non-proxies.
	 *
	 * @since 1.22
	 */
	protected void resetESObject() {
	    InternalEObject eInternalContainer = eInternalContainer();
	    assert eInternalContainer != null;
		Notifier esProxyTarget = null;
		EObject esObject = getESObject();
		if (esObject != null) {						// If there is a known ES
			esProxyTarget = esObject;				//  use es to create proxy
		}
		else {										// else need a CS
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory == null) {
				ASResourceImpl.PROXIES.println("No EnvironmentFactory when proxifying " + NameUtil.debugSimpleName(this));
				return;
			}
			// Look for a specific CS
			ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
			if (csi2asMapping == null) {
				ASResourceImpl.PROXIES.println("No CSI2ASMappings when proxifying  " + NameUtil.debugSimpleName(this));
				return;
			}
			EObject csElement = csi2asMapping.getCSElement(this);
			if (csElement == null) {		// If a CS Element references that AS Element
				ASResourceImpl.PROXIES.println("No CSI2ASMapping when proxifying " + NameUtil.debugSimpleName(this));
			}
			esProxyTarget = csElement;
			if ((esProxyTarget == null) && !environmentFactory.isDisposing()) {
				// Else any old ES
				esProxyTarget = resolveESNotifier(environmentFactory.getCompleteModel());
			}
		}
		if (esProxyTarget instanceof EObject) {
			URI uri = EcoreUtil.getURI((EObject)esProxyTarget);
			eSetProxyURI(uri);
		}
		else if (esProxyTarget instanceof Resource) {
			URI uri = ((Resource)esProxyTarget).getURI();
			eSetProxyURI(uri);
		}
		else {
			ASResourceImpl.PROXIES.println("No ES or CS Object when proxifying " + NameUtil.debugSimpleName(this));
		}
		this.esObject = null;
	}

	/**
	 * @since 1.22
	 */
	public void resetStaleESObject() {
		if ((esObject != null) && eIsProxy()) {
			esObject = null;
		}
	}

	/**
	 * resolveESNotifier is called from resetESObject() to locate the ES Object that provides the Proxy URI.
	 * Derived classes may navigate the complete element to find an ESObject, or access the AS2CS mapping or
	 * bypass bloated AS such as Import.
	 *
	 * @since 1.22
	 */
	protected @Nullable Notifier resolveESNotifier(@NonNull CompleteModel completeModel) {
		return null;
	}

	public void setESObject(@NonNull EObject newTarget) {
		assert !(this instanceof Model) : "no ESObject for Model";
		esObject = newTarget;
	}

	@Deprecated // Use setESObject()
	public void setTarget(@Nullable EObject newTarget) {
		esObject = newTarget;
	}

	@Deprecated /* @deprecated no longer used, moved to preUnload() */
	public void unloaded(@NonNull ASResource asResource) {
		assert esObject == null;
		esObject = null;
	}
}
