/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;

public class UnboxedCompositionProperty extends AbstractProperty
{
	/** @deprecated use propertyId */
	@Deprecated
	protected @NonNull String containmentFeatureName;
	private @Nullable PropertyId propertyId;		// null for deprecated compatobility
	private EStructuralFeature eFeature = null;

	/** @deprecated use PropertyId argument */
	@Deprecated
	public UnboxedCompositionProperty(@NonNull String containmentFeatureName) {
		this.containmentFeatureName = containmentFeatureName;
	}

	/**
	 * @since 1.3
	 */
	public UnboxedCompositionProperty(@NonNull PropertyId propertyId) {
		this.propertyId = propertyId;
		this.containmentFeatureName = propertyId.getName();
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		assert sourceValue != null;
		EObject eObject = (EObject)sourceValue;
		EObject eContainer = eObject.eContainer();
		if (eContainer == null) {
			return null;				// No container
		}
		EStructuralFeature eFeature2 = eFeature;
		if (eFeature2 == null) {
			PropertyId propertyId2 = propertyId;
			if (propertyId2 != null) {
				Element asProperty = executor.getIdResolver().visitPropertyId(propertyId2);
				eFeature = eFeature2 = (EStructuralFeature) asProperty.getESObject();
			}
		}
		EReference eContainmentFeature = eObject.eContainmentFeature();
		if (eFeature != null) {
			if (eContainmentFeature != eFeature) {
				return null;				// Contained but by some other property
			}
		}
		else {
			if (!containmentFeatureName.equals(eContainmentFeature.getName())) {
				return null;				// Contained but by some other property
			}
		}
		return eContainer;
	}
}
