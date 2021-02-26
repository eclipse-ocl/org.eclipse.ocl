/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;

public class UMLGenModelHelper extends AbstractGenModelHelper
{
	public UMLGenModelHelper(@NonNull PivotMetamodelManager metamodelManager) {
		super(metamodelManager);
	}

	/**
	 * Return the EClass-contained EStructuralFeature lements in the transitive clozure of the
	 * 'redefines' EAnnotation of eStructuralFeature.
	 */
	protected @Nullable List<@NonNull EStructuralFeature> gatherDefinitions(@Nullable List<@NonNull EStructuralFeature> definingFeatures, @NonNull EStructuralFeature eStructuralFeature) {
		if (eStructuralFeature.eContainer() instanceof EClass) {
			if (definingFeatures == null) {
				definingFeatures = new ArrayList<>();
			}
			if (!definingFeatures.contains(eStructuralFeature)) {
				definingFeatures.add(eStructuralFeature);
			}
		}
		else {
			EAnnotation eAnnotation = eStructuralFeature.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE);
			if (eAnnotation != null) {
				for (EObject reference : eAnnotation.getReferences()) {
					if (reference instanceof EStructuralFeature) {
						definingFeatures = gatherDefinitions(definingFeatures, (EStructuralFeature)reference);
					}
				}
			}
		}
		return definingFeatures;
	}

	@Override
	public @NonNull GenFeature getGenFeature(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException {
		EStructuralFeature eFeature = eStructuralFeature;
		if (!(eStructuralFeature.eContainer() instanceof EClass)) {
			List<@NonNull EStructuralFeature> definingFeatures = gatherDefinitions(null, eStructuralFeature);
			assert (definingFeatures != null) && (definingFeatures.size() > 0);
			eFeature = definingFeatures.get(0);			// Any of the features should hit the same virtual resolution.
		}
		return super.getGenFeature(eFeature);
	}

	@Override
	public @NonNull String getName(@Nullable ENamedElement eNamedElement) {
		if (eNamedElement == null) {
			return "";
		}
		String name = EcoreUtil.getAnnotation(eNamedElement, DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI, DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME);
		if (name == null) {
			name = eNamedElement.getName();
		}
		if (name == null) {
			name = "";
		}
		return name;
	}
}
