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
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * The EMF_Ecore_EAnnotationConverter supports configuration of the Constraint elements
 * from the http://www.eclipse.org/emf/2002/Ecore constrainats detail of an EModelElement.
 *
 * @since 7.0
 */
public class EMF_Ecore_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull EMF_Ecore_EAnnotationConverter INSTANCE = new EMF_Ecore_EAnnotationConverter();

	public static @NonNull EMF_Ecore_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private EMF_Ecore_EAnnotationConverter() {
		super(EcorePackage.eNS_URI);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		return false;				// "constraints" is a contents list, no individual constraints details to copy
	}
}