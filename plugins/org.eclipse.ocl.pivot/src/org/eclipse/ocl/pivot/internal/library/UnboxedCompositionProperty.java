/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;

public class UnboxedCompositionProperty extends AbstractProperty
{
	private final @NonNull PropertyId propertyId;
	private EStructuralFeature eFeature = null;

	/**
	 * @since 1.3
	 */
	public UnboxedCompositionProperty(@NonNull PropertyId propertyId) {
		this.propertyId = propertyId;
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		assert sourceValue != null;
		EObject eObject = (EObject)sourceValue;
		EObject eContainer = eObject.eContainer();
		if (eContainer == null) {
			return null;						// No container
		}
		EReference eContainmentFeature = eObject.eContainmentFeature();
		EStructuralFeature eFeature2 = eFeature;
		if (eFeature2 != null) {
			if (eContainmentFeature != eFeature2) {
				return null;					// Contained but by some other property
			}
		}
		else {
			NestedTypeId typeId = (NestedTypeId) propertyId.getParent();
			EClass eClass = eContainmentFeature.getEContainingClass();
			if (!typeId.getName().equals(eClass.getName())) {
				return null;				// Contained but by some other property
			}
			PackageId packageId = typeId.getParent();
			EPackage ePackage = eClass.getEPackage();
			while (packageId instanceof NestedPackageId) {
				if (!((NestedPackageId)packageId).getName().equals(ePackage.getName())) {
					return null;				// Contained but by some other property
				}
				packageId = ((NestedPackageId)packageId).getParent();
				ePackage = ePackage.getESuperPackage();
				if (ePackage == null) {
					return null;				// Contained but by some other property
				}
			}
			if (packageId instanceof NsURIPackageId) {
				if (!((NsURIPackageId)packageId).getNsURI().equals(ePackage.getNsURI())) {
					return null;				// Contained but by some other property
				}
			}
			else if (packageId instanceof RootPackageId) {
				if (!((RootPackageId)packageId).getName().equals(ePackage.getName())) {
					return null;				// Contained but by some other property
				}
			}
			else {
				throw new UnsupportedOperationException();
			}
			eFeature = eContainmentFeature;
		}
		return eContainer;
	}
}
