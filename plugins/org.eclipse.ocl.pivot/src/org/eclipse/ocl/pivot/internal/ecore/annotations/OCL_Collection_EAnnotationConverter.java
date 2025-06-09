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
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * The OCL_Collection_EAnnotationConverter supports configuration of the CollectionType isNullFree field
 * from the http://www.eclipse.org/OCL/Collection isNullFree detail of an EModelElement.
 * Other http://www.eclipse.org/OCL/Collection details are copied.
 *
 * @since 7.0
 */
public class OCL_Collection_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull OCL_Collection_EAnnotationConverter INSTANCE = new OCL_Collection_EAnnotationConverter();

	public static @NonNull OCL_Collection_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_Collection_EAnnotationConverter() {
		super(PivotConstants.COLLECTION_ANNOTATION_SOURCE);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		boolean hasFurtherDetails = false;
		for (Map.Entry<String, String> detail : eAnnotation.getDetails()) {
			if (PivotConstants.COLLECTION_IS_NULL_FREE.equals(detail.getKey())) {
				String isNullFree = detail.getValue();
			//	return Boolean.parseBoolean(isNullFree);		// XXX There is no 'set' here
			}
			else if (PivotConstants.COLLECTION_LOWER.equals(detail.getKey())) {
				String lowerText = detail.getValue();
			//	return Integer.parseInt(lowerText);		// XXX There is no 'set' here
			}
			else if (PivotConstants.COLLECTION_UPPER.equals(detail.getKey())) {
				String upperText = detail.getValue();
			//	return Integer.parseInt(upperText);		// XXX There is no 'set' here
			}
			else {
				hasFurtherDetails = true;
			}
		}
		return hasFurtherDetails;
	}
}