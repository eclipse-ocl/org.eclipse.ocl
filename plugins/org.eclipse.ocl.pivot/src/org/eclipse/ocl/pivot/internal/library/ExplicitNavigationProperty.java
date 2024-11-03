/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.PartialProperties;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * An instance of ExplicitNavigationProperty supports evaluation of
 * a property call that navigates a relationship.
 */
public class ExplicitNavigationProperty extends AbstractProperty
{
	protected @NonNull Property property;
	protected @NonNull PropertyId propertyId;
	private EStructuralFeature eFeature;

	public ExplicitNavigationProperty(@NonNull Property property) {
		this.property = property;
		this.propertyId = property.getPropertyId();
		EObject esObject = property.getESObject();
		this.eFeature = esObject instanceof EStructuralFeature ? (EStructuralFeature) esObject : null;
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		EObject eObject = asNavigableObject(sourceValue, propertyId, executor);
		EStructuralFeature eFeature2 = eFeature;
		if (eFeature2 == null) {			// This fall-back is only used for two UMLValidateTests where we need the Ecore rather than the UML ES object
			EnvironmentFactory environmentFactory = executor.getEnvironmentFactory();
			CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass(PivotUtil.getOwningClass(property));
			// For UML, the OMG and UML2 models complement and the UML2 one has an EStructuralFeature
			Iterable<@NonNull Property> properties = completeClass.getProperties(property);
			if (properties instanceof PartialProperties) {
				Iterable<@NonNull Property> partials = ((PartialProperties)properties).getPartials();
				if (partials != null) {
					for (Property partialProperty : partials) {
						EObject esObject = partialProperty.getESObject();
						if (esObject instanceof EStructuralFeature) {
							eFeature2 = (EStructuralFeature) esObject;
							break;
						}
					}
				}
			}
			if (eFeature2 == null) {
				return null;
			}
			eFeature = eFeature2;
		}
		// A specialized property such as CollectionType.elementType is returned from the specialized type
		// An unspecialized property such as CollectionType.ownedOperation is returned from the unspecialized type
		if ((eObject instanceof TemplateableElement) && !eObject.eIsSet(eFeature2)) {
			TemplateableElement rawType = ((TemplateableElement)eObject).getUnspecializedElement();
			if (rawType != null) {
				eObject = rawType;
			}
		}
		try {
			Object eValue = eObject.eGet(eFeature2, true);				// IAE for metamodel schizophrenia
			if (eValue != null) {
				return executor.getIdResolver().boxedValueOf(eValue, eFeature2, returnTypeId);
			}
		}
		catch (IllegalArgumentException e) {
			EnvironmentFactory environmentFactory = executor.getEnvironmentFactory();
			ResourceSet resourceSet0 = environmentFactory.getResourceSet();
			EClass eClass1 = eObject.eClass();
			Resource eResource1 = eClass1.eResource();
			Resource eResource2 = eFeature2.eResource();
			ResourceSet resourceSet1 = eResource1.getResourceSet();
			ResourceSet resourceSet2 = eResource2.getResourceSet();
			throw new InvalidValueException(e, e.getMessage());
		}
		return null;
	}
}