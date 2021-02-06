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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;

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

	protected @NonNull EStructuralFeature resolveRedefinition(@NonNull EStructuralFeature eStructuralFeature) {
		EStructuralFeature eFeature = eStructuralFeature;
		for (EAnnotation eAnnotation; (eAnnotation = eFeature.getEAnnotation(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE)) != null; ) {
			for (EObject reference : eAnnotation.getReferences()) {
				if (reference instanceof EStructuralFeature) {
					eFeature = (EStructuralFeature) reference;
					break;
				}
			}
		}
		return eFeature;
	}
}
