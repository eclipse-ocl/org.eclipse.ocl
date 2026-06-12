/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.annotations;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.internal.ecore.es2as.AbstractExternal2AS;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * The OCL_CoEClass_EAnnotationConverter suppresses the nested http://www.eclipse.org/OCL/CoEClass
 * contents which are resolvred directly by Ecore2ASDeclarationSwitch.caseEAnnotation.
 *
 * @since 7.0
 */
public class OCL_CoEClass_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull OCL_CoEClass_EAnnotationConverter INSTANCE = new OCL_CoEClass_EAnnotationConverter();

	public static @NonNull OCL_CoEClass_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_CoEClass_EAnnotationConverter() {
		super(PivotConstants.CO_ECLASS_ANNOTATION_SOURCE);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) throws XMIException {
		return false;
	}

	@Override
	public @Nullable Annotation createAnnotation(@NonNull AbstractExternal2AS converter, @NonNull EAnnotation eAnnotation) {
		return null;
	}
}