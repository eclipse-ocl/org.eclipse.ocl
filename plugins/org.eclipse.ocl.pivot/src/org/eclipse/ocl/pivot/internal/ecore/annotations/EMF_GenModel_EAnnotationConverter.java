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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * The EMF_GenModel_EAnnotationConverter maps the Element ownedComments field to the
 * http://www.eclipse.org/emf/2002/GenModel documentation detail of an EModelElement.
 * Other //www.eclipse.org/emf/2002/GenModel details are copied.
 *
 * @since 7.0
 */
public class EMF_GenModel_EAnnotationConverter extends AbstractEAnnotationConverter
{
	private static final @NonNull EMF_GenModel_EAnnotationConverter INSTANCE = new EMF_GenModel_EAnnotationConverter();

	public static @NonNull EMF_GenModel_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private EMF_GenModel_EAnnotationConverter() {
		super(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
	}

	public void convertAnnotations(@NonNull Element asElement, @NonNull EModelElement eModelElement) {
		List<String> newComments = null;
		for (Comment comment : asElement.getOwnedComments()) {
			if (newComments == null) {
				newComments = new ArrayList<>();
			}
			newComments.add(comment.getBody());			// NB may be null.
		}
		for (Element element : asElement.getOwnedAnnotations()) {
			if (element instanceof Annotation) {
				Annotation pivotAnnotation = (Annotation)element;
				if (PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(pivotAnnotation.getName())) {
					Detail detail = NameUtil.getNameable(pivotAnnotation.getOwnedDetails(), PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY);
					if (detail != null) {
						List<String> values = detail.getValues();
						if (newComments == null) {
							newComments = new ArrayList<>();
						}
						newComments.addAll(values);
					}
				}
			}
		}
		EAnnotation eAnnotation = eModelElement.getEAnnotation(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
		if (newComments != null) {
			if (eAnnotation == null) {
				eAnnotation = getEAnnotation(eModelElement);
			}
			String value = StringUtil.splice(newComments, "");
			eAnnotation.getDetails().put(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY, value);
		}
		else {
			if (eAnnotation != null) {
				eAnnotation.getDetails().removeKey(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY);
			}
		}
	}

	@Override
	public void convertDetail(@NonNull Detail asDetail, @NonNull EAnnotation eAnnotation) {
		if (!PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(asDetail.getName())	) {	// Documentation and comments are merged by comment handling
			super.convertDetail(asDetail, eAnnotation);
		}
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) {
		boolean hasFurtherDetails = false;
		for (Map.Entry<String, String> detail : eAnnotation.getDetails()) {
			if (PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(detail.getKey())) {
				String body = detail.getValue();
				Comment pivotComment = PivotFactory.eINSTANCE.createComment();
				pivotComment.setBody(body);
				asElement.getOwnedComments().add(pivotComment);
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
		if (PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(key)) {
			return null;
		}
		return super.convertEDetail(eDetail);
	}
}