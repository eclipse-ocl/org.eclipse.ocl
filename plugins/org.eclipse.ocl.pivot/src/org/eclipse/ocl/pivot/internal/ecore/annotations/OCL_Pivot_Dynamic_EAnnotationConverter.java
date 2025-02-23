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
 * The OCL_Pivot_Dynamic_EAnnotationConverter suppresses the unwanted mapping of dynamically
 * created OCL constraints.
 *
 * @since 1.23
 */
public class OCL_Pivot_Dynamic_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull OCL_Pivot_Dynamic_EAnnotationConverter INSTANCE = new OCL_Pivot_Dynamic_EAnnotationConverter();

	public static @NonNull OCL_Pivot_Dynamic_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_Pivot_Dynamic_EAnnotationConverter() {
		super(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		return false;			// suppressed Dynamic OCL is created dynamically from the AS not vice-versa
	}
}