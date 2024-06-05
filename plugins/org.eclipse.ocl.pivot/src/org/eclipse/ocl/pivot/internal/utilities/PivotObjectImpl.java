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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
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
			if (eObject instanceof PivotObjectImpl) {
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
	    InternalEObject result = eInternalContainer();
	    assert result != null;
		EObject esObject = getESObject();
		if (esObject == null) {
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory == null) {
				ASResourceImpl.PROXIES.println("No EnvironmentFactory when proxifying " + NameUtil.debugSimpleName(this));
			}
			else {
				// Look for a specific CS
				ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
				if (csi2asMapping == null) {
					ASResourceImpl.PROXIES.println("No CSI2ASMapping when proxifying  " + NameUtil.debugSimpleName(this));
				}
				else {
					EObject csElement = csi2asMapping.getCSElement(this);
					if (csElement != null) {		// XXX never happens CS is never externally referenced
						esObject = csElement;
					//	ASResourceImpl.PROXIES.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " fixup-cs " + uri);
					}
				}
				if (esObject == null) {
					// Else any old ES
					esObject = resolveESObject(environmentFactory.getCompleteModel());
				}
			}
		}
		if (esObject != null) {
			URI uri = EcoreUtil.getURI(esObject);
			eSetProxyURI(uri);
		}
		else {
			ASResourceImpl.PROXIES.println("No esObject when proxifying " + NameUtil.debugSimpleName(this));
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
	 * resolveESObject is called from unloaded(ASResource) to locate the ES Object that provides the Proxy URI.
	 * Derived ckasses may naigayte the complete element to find an ESObject, or access the AS2CS mapping or
	 * bypass blopated AS such as Import.
	 *
	 * @since 1.22
	 */
	protected @Nullable EObject resolveESObject(@NonNull CompleteModel completeModel) {
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
