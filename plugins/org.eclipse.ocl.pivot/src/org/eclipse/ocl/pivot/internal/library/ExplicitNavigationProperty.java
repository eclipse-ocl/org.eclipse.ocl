/*******************************************************************************
 * Copyright (c) 2011, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;

/**
 * An instance of ExplicitNavigationProperty supports evaluation of
 * a property call that navigates a relationship.
 */
public class ExplicitNavigationProperty extends AbstractProperty
{
	protected @NonNull Property property;
	protected @NonNull PropertyId propertyId;
//	protected @NonNull DomainProperty property;
	private EStructuralFeature eFeature = null;
	
	public ExplicitNavigationProperty(@NonNull Property property) {
		this.property = property;
		this.propertyId = property.getPropertyId();
	}
	
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		EObject eObject = asNavigableObject(sourceValue, propertyId, executor); 
		EStructuralFeature eFeature2 = eFeature;
		if (eFeature2 == null) {
			EClass eClass = eObject.eClass();
			eFeature = eFeature2 = eClass.getEStructuralFeature(propertyId.getName());
		}
		// A specialized property such as CollectionType.elementType is returned from the specialized type
		// An unspecialized property such as CollectionType.ownedOperation is returned from the unspecialized type
		if ((eObject instanceof TemplateableElement) && !eObject.eIsSet(eFeature2)) {
			TemplateableElement rawType = ((TemplateableElement)eObject).getUnspecializedElement();
			if (rawType != null) {
				eObject = rawType;
			}
		}
		if (eFeature2 != null) {
			Object eValue = eObject.eGet(eFeature2, true);
			if (eValue != null) {
				return executor.getIdResolver().boxedValueOf(eValue, eFeature2, returnTypeId);
			}
			
		}
		return null;
	}
}