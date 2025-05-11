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

/**
 * The MOF_XML_EAnnotationConverter supports configuration of the Property opposites field
 * from the http://schema.omg.org/spec/MOF/2.0/emof.xml detail of an EStructuralFeature.
 *
 * @since 7.0
 */
public class MOF_XML_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull MOF_XML_EAnnotationConverter INSTANCE = new MOF_XML_EAnnotationConverter();

	public static @NonNull MOF_XML_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private MOF_XML_EAnnotationConverter() {
		super("http://schema.omg.org/spec/MOF/2.0/emof.xml");
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		return false;  // Functionality in OppositePropertyDetails.createFromEReference(eReference)
	}
}