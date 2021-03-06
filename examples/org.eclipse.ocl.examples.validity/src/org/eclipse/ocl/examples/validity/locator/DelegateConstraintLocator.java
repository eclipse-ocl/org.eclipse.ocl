/*******************************************************************************
 * Copyright (c) 2014, 2021 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.locator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityModel;
import org.eclipse.ocl.examples.validity.plugin.OCLValidityPlugin;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.evaluation.AbstractConstraintEvaluator;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class DelegateConstraintLocator extends AbstractPivotConstraintLocator
{
	public static @NonNull DelegateConstraintLocator INSTANCE = new DelegateConstraintLocator();

	protected @Nullable Constraint getConstraint(@NonNull PivotMetamodelManager metamodelManager, @NonNull ResultConstrainingNode resultConstrainingNode) throws ParserException {
		Object constrainingObject = resultConstrainingNode.getParent().getConstrainingObject();
		if (constrainingObject instanceof EAnnotation) {
			EObject eObject = ((EAnnotation) constrainingObject).eContainer();
			if (eObject instanceof EOperation) {
				return ((EnvironmentFactoryInternalExtension)metamodelManager.getEnvironmentFactory()).getASOf(Constraint.class, eObject);
			}
		}
		else if (constrainingObject instanceof EStringToStringMapEntryImpl) {
			EStringToStringMapEntryImpl eEntry = (EStringToStringMapEntryImpl)constrainingObject;
			EObject eAnnotation = eEntry.eContainer();
			if (eAnnotation instanceof EAnnotation) {
				EObject eClassifier = ((EAnnotation)eAnnotation).eContainer();
				if (eClassifier instanceof EClassifier) {
					org.eclipse.ocl.pivot.Class asType = ((EnvironmentFactoryInternalExtension)metamodelManager.getEnvironmentFactory()).getASOf(org.eclipse.ocl.pivot.Class.class, eClassifier);
					if (asType != null) {
						return NameUtil.getNameable(asType.getOwnedInvariants(), eEntry.getKey());
					}
				}
			}
		}
		return null;
	}

	protected EObject getConstrainedObject(@NonNull ResultConstrainingNode resultConstrainingNode) {
		ValidatableNode validatableNode = resultConstrainingNode.getResultValidatableNode().getParent();
		assert validatableNode != null;
		return validatableNode.getConstrainedObject();
	}

	@Override
	public @Nullable Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel,
			@NonNull EPackage ePackage, @NonNull Set<@NonNull Resource> resources, @NonNull Monitor monitor) {
		if (ePackage.eContainer() instanceof EAnnotation) {			// Applied UML Profiles are UML not Ecore constraints
			return null;
		}
		Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> map = null;
		for (@SuppressWarnings("null")@NonNull EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (monitor.isCanceled()) {
				return null;
			}
			EAnnotation oclAnnotation = OCLCommon.getDelegateAnnotation(eClassifier);
			if (oclAnnotation != null) {
				EMap<String, String> details = oclAnnotation.getDetails();
				for (@SuppressWarnings("null")Map.@NonNull Entry<String, String> constraint : details.entrySet()) {
					String constraintName = constraint.getKey();
					if (constraintName != null) {
						map = createLeafConstrainingNode(map, validityModel, eClassifier, constraint, constraintName);
					}
				}
			}
			if (eClassifier instanceof EClass) {
				for (@SuppressWarnings("null")@NonNull EOperation eOperation : ((EClass)eClassifier).getEOperations()) {
					if (EcoreUtil.isInvariant(eOperation)) {
						oclAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
						if (oclAnnotation != null) {
							String constraintBody = oclAnnotation.getDetails().get("body");
							if (constraintBody != null) {
								String constraintName = eOperation.getName();
								if (constraintName != null) {
									map = createLeafConstrainingNode(map, validityModel, eClassifier, oclAnnotation, constraintName);
								}
							}
						}
					}
				}
			}
		}
		return map;
	}

	@Override
	public Object getImage() {
		return OCLValidityPlugin.INSTANCE.getImage("OCLModelFile.gif");		// FIXME something better
	}

	@Override
	public @NonNull ConstraintLocator getInstance() {
		return INSTANCE;
	}

	@Override
	public @NonNull String getName() {
		return "Delegated OCL constraints";
	}

	@Override
	public @Nullable String getSourceExpression(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (constrainingObject instanceof EAnnotation) {
			return ((EAnnotation)constrainingObject).getDetails().get("body");
		}
		else if (constrainingObject instanceof EStringToStringMapEntryImpl) {
			return ((EStringToStringMapEntryImpl)constrainingObject).getValue();
		}
		else {
			return null;
		}
	}

	protected @NonNull String print(@NonNull Map<EClassifier, @NonNull List<LeafConstrainingNode>> map) {
		StringBuilder s = new StringBuilder();
		ArrayList<EClassifier> sortedList = new ArrayList<EClassifier>(map.keySet());
		Collections.sort(sortedList, new Comparator<EClassifier>()
		{
			@Override
			public int compare(EClassifier o1, EClassifier o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (EClassifier eClassifier : sortedList) {
			s.append("\t" + eClassifier.getName() + ":");
			List<LeafConstrainingNode> constraints = map.get(eClassifier);
			assert constraints != null;
			for (LeafConstrainingNode constraint : constraints) {
				s.append(" \'" + constraint.getLabel() + "'");
			}
			s.append("\n");
		}
		return s.toString();
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager, @Nullable Monitor monitor) {
		EObject constrainedObject = result.getValidatableNode().getConstrainedObject();
		ResultConstrainingNode resultConstrainingNode = result.getResultConstrainingNode();
		if (resultConstrainingNode == null) {
			return;
		}
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(constrainedObject);
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Constraint asConstraint = null;
		try {
			asConstraint = getConstraint(metamodelManager, resultConstrainingNode);
		} catch (ParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Severity severity = Severity.UNKNOWN;
		try {
			if (asConstraint == null) {
				throw new ParserException("Failed to create pivot Constraint");
			}
			final Constraint finalConstraint = asConstraint;
			ExpressionInOCL query = getQuery(metamodelManager, asConstraint);
			EvaluationVisitor evaluationVisitor = createEvaluationVisitor(environmentFactory, query, constrainedObject, monitor);
			AbstractConstraintEvaluator<Diagnostic> constraintEvaluator = new AbstractConstraintLocator(metamodelManager, query, constrainedObject)
			{
				@Override
				protected String getObjectLabel() {
					org.eclipse.ocl.pivot.Type type = PivotUtil.getContainingType(finalConstraint);
					org.eclipse.ocl.pivot.Type primaryType = type != null ? metamodelManager.getPrimaryType(type) : null;
					EClassifier classifier = primaryType != null ?  (EClassifier)primaryType.getESObject() : null;
					return classifier != null ? classifier.getName() : "??";
					//								return ClassUtil.getLabel(classifier, object, context);
				}

			};
			Diagnostic diagnostic = constraintEvaluator.evaluate(evaluationVisitor);
			result.setDiagnostic(diagnostic);
			severity = diagnostic != null ? getSeverity(diagnostic) : Severity.OK;
		} catch (Throwable e) {
			result.setException(e);
			severity = Severity.FATAL;
		} finally {
			result.setSeverity(severity);
		}
	}
}