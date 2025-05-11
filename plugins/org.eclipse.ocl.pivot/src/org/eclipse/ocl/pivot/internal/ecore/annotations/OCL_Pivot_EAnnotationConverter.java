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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ExpressionInOCLImpl;
import org.eclipse.ocl.pivot.internal.PivotFactoryImpl;
import org.eclipse.ocl.pivot.internal.delegate.SettingBehavior;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

/**
 * The OCL_Pivot_EAnnotationConverter maps the Constraint elements to the
 * http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot details of an EClass/EOperatioon/EStructuralFeature.
 *
 * @since 7.0
 */
public class OCL_Pivot_EAnnotationConverter extends AbstractEAnnotationConverter
{
	public static final @NonNull OCL_Pivot_EAnnotationConverter INSTANCE = new OCL_Pivot_EAnnotationConverter();

	public static @NonNull OCL_Pivot_EAnnotationConverter getInstance() {
		return INSTANCE;
	}

	private OCL_Pivot_EAnnotationConverter() {
		super(OCLConstants.OCL_DELEGATE_URI_PIVOT);
	}

	@Override
	public boolean convertEAnnotationDetails(@NonNull EAnnotation eAnnotation, @NonNull NamedElement asElement) throws XMIException {
		if (asElement instanceof org.eclipse.ocl.pivot.Class) {
			return false;			// Handled in caller
		}
		if (asElement instanceof Property) {
			Property asProperty = (Property)asElement;
			Map.Entry<String,String> bestEntry = null;
			for (Map.Entry<String,String> entry : eAnnotation.getDetails()) {
				String key = entry.getKey();
				if (key.equals(SettingBehavior.DERIVATION_CONSTRAINT_KEY)) {
					bestEntry = entry;
				}
				else if (key.equals(SettingBehavior.INITIAL_CONSTRAINT_KEY)) {
					if (bestEntry == null) {
						bestEntry = entry;
					}
				}
				else if (key.equals("get")) {
					if (bestEntry == null) {
						bestEntry = entry;
					}
				}
				else
				{
					throw new XMIException("Unsupported feature constraint " + key);
				}
			}
			if (bestEntry != null) {
				String value = bestEntry.getValue();
				ExpressionInOCLImpl specification = (ExpressionInOCLImpl)PivotFactory.eINSTANCE.createExpressionInOCL();
				specification.setESObject(eAnnotation);
				specification.setBody(value);
				asProperty.setOwnedExpression(specification);
			}
			return false;			// XXX Ignore more details
		}
		if (asElement instanceof Operation) {
			EOperation eOperation = (EOperation)eAnnotation.eContainer();
			assert eOperation != null;
			Operation asOperation = (Operation)asElement;
			for (Map.Entry<String,String> entry : eAnnotation.getDetails()) {
				String bodyName = null;
				String preName = null;
				String postName = null;
				String key = entry.getKey();
				String value = entry.getValue();
				if (key.equals("body")) {
					bodyName = "";
					if (value != null) {
						value = PivotUtilInternal.getBodyExpression(value);	// Workaround Bug 419324
					}
				}
				else if (key.startsWith("body_")) {
					bodyName = key.substring(5);
				}
				else if (key.equals("pre")) {
					preName = "";
				}
				else if (key.startsWith("pre_")) {
					preName = key.substring(4);
				}
				else if (key.equals("post")) {
					postName = "";
				}
				else if (key.startsWith("post_")) {
					postName = key.substring(5);
				}
				else
				{
					logger.error("Unsupported operation constraint " + key);
					continue;
				}
				ExpressionInOCLImpl specification = (ExpressionInOCLImpl)PivotFactory.eINSTANCE.createExpressionInOCL();
				specification.setESObject(eAnnotation);
				specification.setBody(value);
				//				constraint.setExprString(entry.getValue());
				//				constraint.setExprString(entry.getValue());
				if (bodyName != null) {
					asOperation.setBodyExpression(specification);
					asOperation.setImplementation(new EObjectOperation(asOperation, eOperation, specification));
				}
				else {
					Constraint constraint = PivotFactory.eINSTANCE.createConstraint();
					constraint.setOwnedSpecification(specification);
					if (preName != null) {
						asOperation.getOwnedPreconditions().add(constraint);
						constraint.setName(preName);
					}
					else {
						asOperation.getOwnedPostconditions().add(constraint);
						constraint.setName(postName);
					}
					String commentText = EcoreUtil.getAnnotation(eAnnotation, PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE, key);
					if (commentText != null) {
						Comment comment = PivotFactoryImpl.eINSTANCE.createComment();
						comment.setBody(commentText);
						constraint.getOwnedComments().add(comment);
					}
				//	copyAnnotationComment(constraint, eAnnotation, key);
				}
			}
			return false;			// XXX Ignore more details
		}
		if (!(asElement instanceof Constraint)/* && !(asElement instanceof Property)*/) {
			logger.warn("An '" + getSource() + "' EAnnotation should be associated with a Constraint or Property");
			return false;
		}
		boolean hasFurtherDetails = false;
		for (Map.Entry<String, String> detail : eAnnotation.getDetails()) {
			if ("body".equals(detail.getKey())) {
				((Constraint)asElement).getOwnedSpecification().setBody(detail.getValue());
			}
			else {
				hasFurtherDetails = true;
			}
		}
		return hasFurtherDetails;
	}
}