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
 * The OCL_EntryClass_EAnnotationConverter suppresses the redundant mapping of http://www.eclipse.org/OCL/EntryClass
 * contents since, if needed, they are captured by MapType references.
 *
 * @since 7.0
 */
public class OCL_EntryClass_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull OCL_EntryClass_EAnnotationConverter INSTANCE = new OCL_EntryClass_EAnnotationConverter();

	public static @NonNull OCL_EntryClass_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) throws XMIException {
		return false;
	}

	private OCL_EntryClass_EAnnotationConverter() {
		super(PivotConstants.ENTRY_CLASS_ANNOTATION_SOURCE);
	}

	@Override
	public @Nullable Annotation createAnnotation(@NonNull AbstractExternal2AS converter, @NonNull EAnnotation eAnnotation) {
		return null;
	}
}