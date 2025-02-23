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

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.util.DerivedConstants;

/**
 * The UML_EAnnotationConverter maps the NamedElement name field to the
 * http://www.eclipse.org/uml2/2.0.0/UML originalName detail of an ENamedElement.
 *
 * @since 1.23
 */
public class UML_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull UML_EAnnotationConverter INSTANCE = new UML_EAnnotationConverter();

	public static @NonNull UML_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private UML_EAnnotationConverter() {
		super(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		boolean hasFurtherDetails = false;
		for (Map.Entry<String, String> detail : eAnnotation.getDetails()) {
			if (DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME.equals(detail.getKey())) {
				String originalName = detail.getValue();
				asElement.setName(originalName);
			}
			else {
				hasFurtherDetails = true;
			}
		}
		return hasFurtherDetails;
	}
}