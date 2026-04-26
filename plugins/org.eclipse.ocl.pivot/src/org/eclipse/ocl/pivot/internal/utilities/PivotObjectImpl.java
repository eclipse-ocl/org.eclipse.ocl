/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
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
		assert proxy != null;
		URI proxyURI = proxy.eProxyURI();
		StringBuilder s = null;
		if (ASResourceImpl.RESOLVE_PROXY.isActive()) {
			s = new StringBuilder();
			s.append(NameUtil.debugSimpleName(this) + " " + NameUtil.debugSimpleName(proxy) + " " + proxyURI);
		}
		EObject resolvedProxy = null;
		if (!PivotUtilInternal.isASURI(proxyURI)) {
			//
			//	A non-AS (ES) proxy must try to resolve in the externalResourceSet.
			//
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				ResourceSet externalResourceSet = environmentFactory.getResourceSet();
				resolvedProxy = EcoreUtil.resolve(proxy, externalResourceSet);
				Resource resolvedResource = resolvedProxy.eResource();
				if (resolvedResource != null) {
					ResourceSet resolvedResourceSet = resolvedResource.getResourceSet();
					assert resolvedResourceSet == externalResourceSet;
					try {
						resolvedProxy = ((EnvironmentFactoryInternalExtension)environmentFactory).getASOf(Element.class, resolvedProxy);
					} catch (ParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (resolvedProxy == null) {
			//
			//	An AS (or fallback ES) proxy resolves to the containing ResourceSet.
			//
			ResourceSet resourceSet = null;
			Resource eResource = eResource();
			if (eResource != null) {
				resourceSet = eResource.getResourceSet();
			}
			if (resourceSet == null) {				// CompleteClass has no eResource()
				EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
				if (environmentFactory != null) {
					resourceSet = environmentFactory.getResourceSet();
				}
			}
			resolvedProxy = EcoreUtil.resolve(proxy, resourceSet);
		}
		if (s != null) {
			s.append(" => " + NameUtil.debugSimpleName(resolvedProxy));
			ASResourceImpl.RESOLVE_PROXY.println(s.toString());
		}
		return resolvedProxy;
	}

	@Override
	public void eSetProxyURI(URI uri) {
		ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " " + NameUtil.debugSimpleName(this) + " " + uri);
		assert (uri == null) || (eContainer == null) || !uri.toString().contains(PivotConstants.DOT_OCL_AS_FILE_EXTENSION) : "Bad AS proxy " + uri;		// eContainer null during SAX parsing
		if ((uri != null) && uri.toString().contains(PivotConstants.DOT_OCL_AS_FILE_EXTENSION)) {
			getClass();		// XXX happens for testStandaloneExecution_execute_model_self_closure
		}
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
	 * @since 7.0
	 */
	public void resetESObject() {
		assert !(this instanceof Model) : "no ESObject for Model";
		assert esObject != null;
		esObject = null;
	}

	public void setESObject(@NonNull EObject newTarget) {
		assert !(this instanceof Model) : "no ESObject for Model";
		esObject = newTarget;
	}

	@Deprecated // Use setESObject()
	public void setTarget(@Nullable EObject newTarget) {
		esObject = newTarget;
	}

	/**
	 * Eliminate the esObject to facilitate leaking testing after a JUnit tearDown()
	 *
	 * @since 7.0
	 */
	public void tearDownESObject() {
		if ((esObject != null) && eIsProxy()) {
			esObject = null;
		}
	}

	@Deprecated /* @deprecated no longer used, moved to preUnload() */
	public void unloaded(@NonNull ASResource asResource) {
		assert esObject == null;
		esObject = null;
	}
}
