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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotObject;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;

public abstract class PivotObjectImpl extends EObjectImpl implements PivotObject
{
	private @Nullable Notifier esObject;		// Model may map to Resource

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
	public void eSetProxyURI(URI uri) {		// XXX Move override to unloaded
	//	System.out.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " " + uri);
		if (PivotUtilInternal.isASURI(uri)) {
			if (esObject instanceof EObject) {
				uri = EcoreUtil.getURI((EObject)esObject);
				System.out.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " fixup-es " + uri);
			}
			else if (esObject instanceof Resource) {
				uri = ((Resource)esObject).getURI();			// XXX assert == Model.externalURI
				System.out.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " fixup-es " + uri);
			}
			else {
				EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
				if (environmentFactory == null) {
					System.err.println("No EnvironmentFactory when proxifying '" + uri + "' for " + NameUtil.debugSimpleName(this));
				}
				else {
					ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
					if (csi2asMapping == null) {
						System.err.println("No CSI2ASMapping when proxifying '" + uri + "' for " + NameUtil.debugSimpleName(this));
					}
					else {
						EObject csElement = csi2asMapping.getCSElement(this);		// XXX alternate logic for Model -> Resource
						if (csElement == null) {
							System.err.println("No CSElement when proxifying '" + uri + "' for " + NameUtil.debugSimpleName(this));
							csElement = csi2asMapping.getCSElement(this);		// XXX alternate logic for Model -> Resource
							getClass();		// XXX
						}
						else {
							uri = EcoreUtil.getURI(csElement);
							System.out.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " fixup-cs " + uri);
						}
					}
				}
			}
		}
		super.eSetProxyURI(uri);
	}

	public @Nullable EObject getESObject() {
		return (EObject)esObject;
	}

	@Deprecated // Use getESObject()
	public @Nullable EObject getETarget() {
		return (EObject)esObject;
	}

	@Override
	public Object getImage() {
		return null;
	}

	/**
	 * @since 1.21
	 */
	public @Nullable Notifier getNotifier() {
		return esObject;
	}

	@Deprecated // Use getESObject()
	public @Nullable EObject getTarget() {
		return (EObject)esObject;
	}

	@Override
	public String getText() {
		return toString();
	}

	public void setESObject(@Nullable EObject newTarget) {
		System.out.println("setESObject " + NameUtil.debugSimpleName(this) + " => " + NameUtil.debugSimpleName(newTarget));
		if (newTarget == null) {
			getClass();		// XXX
		}
		esObject = newTarget;
	}

	/**
	 * @since 1.21
	 */
	public void setNotifier(@Nullable Notifier newTarget) {
		esObject = newTarget;
	}

	@Deprecated // Use setESObject()
	public void setTarget(@Nullable EObject newTarget) {
		System.out.println("setTarget " + NameUtil.debugSimpleName(this) + " => " + NameUtil.debugSimpleName(newTarget));
		esObject = newTarget;
	}

	public void unloaded(@NonNull ASResource asResource) {
		System.out.println("unloaded " + NameUtil.debugSimpleName(this) + " => " + NameUtil.debugSimpleName(null));
		esObject = null;
	}
}
