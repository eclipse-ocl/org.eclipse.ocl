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
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;

/**
 * The OCL_Pivot_Operation_EAnnotationConverter maps the Operation isTransient field to the
 * http://www.eclipse.org/OCL/Pivot/Operation isTransient detail of an EOperation.
 *
 * @since 7.0
 */
public class OCL_Pivot_Operation_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final Logger logger = Logger.getLogger(OCL_Pivot_Operation_EAnnotationConverter.class);

	private static final @NonNull OCL_Pivot_Operation_EAnnotationConverter INSTANCE = new OCL_Pivot_Operation_EAnnotationConverter();

	public static @NonNull OCL_Pivot_Operation_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_Pivot_Operation_EAnnotationConverter() {
		super(PivotConstantsInternal.OPERATION_ANNOTATION_SOURCE);
	}

	public void convertAnnotations(@NonNull Operation asOperation, @NonNull EOperation eOperation) {
		if (asOperation.isIsTransient()) {
			EAnnotation eAnnotation = getEAnnotation(eOperation);
			EMap<String, String> details = eAnnotation.getDetails();
			details.put(PivotConstantsInternal.OPERATION_IS_TRANSIENT, "true");
		}
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		if (!(asElement instanceof Operation)) {
			logger.warn("An '" + getSource() + "' EAnnotation should be associated with an Operation");
			return false;
		}
		boolean hasFurtherDetails = false;
		for (Map.Entry<String, String> detail : eAnnotation.getDetails()) {
			if (PivotConstantsInternal.OPERATION_IS_TRANSIENT.equals(detail.getKey())) {
				boolean isTransient = Boolean.parseBoolean(detail.getValue());
				((Operation)asElement).setIsTransient(isTransient);
			}
			else {
				hasFurtherDetails = true;
			}
		}
		return hasFurtherDetails;
	}

	@Override
	public @Nullable Detail convertEDetail(@NonNull Entry<String, String> eDetail) {
		String key = eDetail.getKey();
		if (PivotConstantsInternal.OPERATION_IS_TRANSIENT.equals(key)) {
			return null;
		}
		return super.convertEDetail(eDetail);
	}
}