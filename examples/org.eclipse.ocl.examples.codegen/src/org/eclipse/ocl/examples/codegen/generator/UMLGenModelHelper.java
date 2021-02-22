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

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EAnnotation;
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

	@Override
	public @NonNull GenFeature getGenFeature(@NonNull EStructuralFeature eStructuralFeature) throws GenModelException {
		EStructuralFeature eFeature = resolveRedefinition(eStructuralFeature);
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

	/**
	 * If eStructuralFeature is a same-Ecore-named redefinition of a super-feature return the redefined feature for
	 * which a GenFeature will  be available.
	 */
	protected @NonNull EStructuralFeature resolveRedefinition(@NonNull EStructuralFeature eStructuralFeature) {
		String name = eStructuralFeature.getName();
		assert name != null;
		EStructuralFeature eFeature = eStructuralFeature;	// See Bug 570891/571407 - no need to trace changed name redefinitions
		for (EAnnotation eAnnotation; (eAnnotation = eFeature.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE)) != null; ) {
			boolean gotOne = false;
			for (EObject reference : eAnnotation.getReferences()) {
				if ((reference instanceof EStructuralFeature) && name.equals(((EStructuralFeature)reference).getName())) {
					eFeature = (EStructuralFeature) reference;
					gotOne = true;
					break;
				}
			}
			if (!gotOne) {
				break;
			}
		}
		return eFeature;
	}
}
