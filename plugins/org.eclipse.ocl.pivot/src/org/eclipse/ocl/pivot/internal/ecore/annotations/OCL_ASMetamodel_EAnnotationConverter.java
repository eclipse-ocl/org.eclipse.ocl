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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * The OCL_ASMetamodel_EAnnotationConverter supports the addition EPackage isASMetamodel field.
 *
 * @since 1.23
 */
public class OCL_ASMetamodel_EAnnotationConverter extends AbstractEAnnotationConverter
{
	public static final @NonNull OCL_ASMetamodel_EAnnotationConverter INSTANCE = new OCL_ASMetamodel_EAnnotationConverter();

	public static @NonNull OCL_ASMetamodel_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_ASMetamodel_EAnnotationConverter() {
		super(PivotConstants.AS_METAMODEL_ANNOTATION_SOURCE);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		return false;		// Suppressed - just an extra EPackage field
	}
}