/**
 * <copyright>
 *
 * Copyright (c) 2010, 2013 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: OCLValidationDelegate.java,v 1.7 2011/05/13 18:43:42 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot.delegate;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.internal.delegate.OCLDelegateException;
import org.eclipse.ocl.examples.domain.evaluation.DomainModelManager;
import org.eclipse.ocl.examples.domain.messages.EvaluatorMessages;
import org.eclipse.ocl.examples.domain.utilities.DomainUtil;
import org.eclipse.ocl.examples.domain.values.impl.InvalidValueException;
import org.eclipse.ocl.examples.domain.values.util.ValuesUtil;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.Environment;
import org.eclipse.ocl.examples.pivot.EnvironmentFactory;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.OpaqueExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.context.ClassContext;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.messages.OCLMessages;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

/**
 * An implementation of the dynamic validation delegate API, maintaining a cache
 * of compiled constraints and invariants.
 */
public class OCLValidationDelegate implements ValidationDelegate
{	
	protected final @NonNull OCLDelegateDomain delegateDomain;
	protected final @NonNull EClassifier eClassifier;
	  
	/**
	 * Initializes me with the classifier whose DelegateEClassifierAdapter delegates to me.
	 * 
	 * @param classifier
	 *            my classifier
	 */
	public OCLValidationDelegate(@NonNull OCLDelegateDomain delegateDomain, @NonNull EClassifier classifier) {
		this.delegateDomain = delegateDomain;
		this.eClassifier = classifier;
	}


	public EvaluationVisitor<?> createEvaluationVisitor(Object object, ExpressionInOCL query) {
		EnvironmentFactory environmentFactory = delegateDomain.getOCL().getEnvironmentFactory();
		Environment rootEnvironment = environmentFactory.createEnvironment();
		// can determine a more appropriate context from the context
		// variable of the expression, to account for stereotype constraints
//			context = HelperUtil.getConstraintContext(rootEnvironment, context, expression);
		EvaluationEnvironment evaluationEnvironment = createEvaluationEnvironment(object, query, environmentFactory);
//			if ((value != null) && !value.isUndefined()) {
//				expression.getContextVariable().setValue(value);
//			}
		DomainModelManager extents = evaluationEnvironment.createModelManager(object);

		EvaluationVisitor<?> evaluationVisitor = environmentFactory.createEvaluationVisitor(rootEnvironment, evaluationEnvironment, extents);
		return evaluationVisitor;
	}

	public EvaluationEnvironment createEvaluationEnvironment(Object object, ExpressionInOCL query,
			EnvironmentFactory environmentFactory) {
		EvaluationEnvironment evaluationEnvironment = environmentFactory.createEvaluationEnvironment();
		Object value = evaluationEnvironment.getMetaModelManager().getIdResolver().boxedValueOf(object);
		Variable contextVariable = DomainUtil.nonNullState(query.getContextVariable());
		evaluationEnvironment.add(contextVariable, value);
		return evaluationEnvironment;
	}

	protected String evaluateMessage(@NonNull EvaluationVisitor<?> evaluationVisitor, String constraintName, ExpressionInOCL query) {
		OCLExpression messageExpression = query.getMessageExpression();
		if (messageExpression == null) {
			return null;
		}
		try {
			Object result = messageExpression.accept(evaluationVisitor);
			if (result != null) {
				return ValuesUtil.asString(result);
			}
			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
			String message = DomainUtil.bind(OCLMessages.ValidationMessageIsNotString_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			throw new OCLDelegateException(message);
		} catch (InvalidValueException e) {
			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
			String message = DomainUtil.bind(OCLMessages.ValidationMessageIsNotString_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			throw new OCLDelegateException(message, e);
		}
	}

	@Override
	public String toString() {
		return "<" + delegateDomain.getURI() + ":validate> " + eClassifier.getEPackage().getName() + "::" + eClassifier.getName(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public boolean validate(EClass eClass, EObject eObject,
			Map<Object, Object> context, EOperation invariant, String expression) {
		if (eClass == null) {
			throw new NullPointerException("Null EClass");
		}
		if (eObject == null) {
			throw new NullPointerException("Null EObject");
		}
		MetaModelManager metaModelManager = delegateDomain.getMetaModelManager();
		NamedElement namedElement = delegateDomain.getPivot(NamedElement.class, DomainUtil.nonNullEMF(invariant));
		if (namedElement instanceof Operation) {
			Operation operation = (Operation)namedElement;
			ExpressionInOCL query = InvocationBehavior.INSTANCE.getExpressionInOCL(metaModelManager, operation);
			InvocationBehavior.INSTANCE.validate(operation);
			return validateExpressionInOCL(eClass, eObject, null, context, invariant.getName(), null, 0, query);
		}
		else if (namedElement instanceof Constraint) {
			Constraint constraint = (Constraint)namedElement;
			ExpressionInOCL query = getExpressionInOCL(metaModelManager, constraint);
			ValidationBehavior.INSTANCE.validate(constraint);
			return validateExpressionInOCL(eClass, eObject, null, context,
				invariant.getName(), null, 0, query);
		}
		else if (namedElement != null) {
			throw new ClassCastException(namedElement.getClass().getName() + " does not provide a Constraint");
		}
		else {
			throw new ClassCastException(invariant.eClass().getName() + " does not provide a Constraint");
		}
	}


	public @NonNull ExpressionInOCL getExpressionInOCL(@NonNull MetaModelManager metaModelManager, @NonNull Constraint constraint) {
		ExpressionInOCL query = null;
		OpaqueExpression valueSpecification = constraint.getSpecification();
		if (valueSpecification instanceof ExpressionInOCL) {
			query = (ExpressionInOCL) valueSpecification;
		}
		else {
			Type contextType = (Type) constraint.getContext();
			if (contextType != null) {
				ClassContext classContext = new ClassContext(metaModelManager, null, contextType);
				query = ValidationBehavior.INSTANCE.getExpressionInOCL(classContext, constraint);
			}
		}
		if (query == null) {
			String message = DomainUtil.bind(OCLMessages.MissingBodyForInvocationDelegate_ERROR_, constraint.getContext());
			throw new OCLDelegateException(message);
		}
		return query;
	}

	public boolean validate(EClass eClass, EObject eObject,
			Map<Object, Object> context, String constraintName, String expression) {
		if (eClass == null) {
			throw new NullPointerException("Null EClass");
		}
		if (eObject == null) {
			throw new NullPointerException("Null EObject");
		}
		if (constraintName == null) {
			throw new NullPointerException("Null constraint name");
		}
		return validatePivot(eClass, eObject, null, context, constraintName, null, 0);
	}

	public boolean validate(EDataType eDataType, Object value,
			Map<Object, Object> context, String constraintName, String expression) {
		if (eDataType == null) {
			throw new NullPointerException("Null EClass");
		}
		if (value == null) {
			throw new NullPointerException("Null EObject");
		}
		if (constraintName == null) {
			throw new NullPointerException("Null constraint name");
		}
		return validatePivot(eDataType, value, null, context, constraintName, null, 0);
	}

	public boolean validate(@NonNull EClass eClass, @NonNull EObject eObject, @Nullable DiagnosticChain diagnostics, Map<Object, Object> context,
			@NonNull String constraintName, String expression, int severity, String source, int code) {
		return validatePivot(eClass, eObject, diagnostics, context, constraintName, source, code);
	}

	public boolean validate(@NonNull EDataType eDataType, @NonNull Object value, @Nullable DiagnosticChain diagnostics, Map<Object, Object> context,
			@NonNull String constraintName, String expression, int severity, String source, int code) {
		return validatePivot(eDataType, value, diagnostics, context, constraintName, source, code);
	}

	protected boolean validatePivot(@NonNull EClassifier eClassifier, @NonNull Object value, @Nullable DiagnosticChain diagnostics,
			Map<Object, Object> context, @NonNull String constraintName, String source, int code) {
		MetaModelManager metaModelManager = delegateDomain.getMetaModelManager();
		Type type = delegateDomain.getPivot(Type.class, eClassifier);
		Constraint constraint = ValidationBehavior.INSTANCE.getConstraint(metaModelManager, eClassifier, constraintName);
		if (constraint == null) {
			String message = DomainUtil.bind(OCLMessages.MissingBodyForInvocationDelegate_ERROR_, type);
			throw new OCLDelegateException(message);
		}
		ExpressionInOCL query = null;
		OpaqueExpression valueSpecification = constraint.getSpecification();
		if (valueSpecification instanceof ExpressionInOCL) {
			query = (ExpressionInOCL) valueSpecification;
		}
		else if (type != null) {
			ClassContext classContext = new ClassContext(metaModelManager, null, type);
			query = ValidationBehavior.INSTANCE.getExpressionInOCL(classContext, constraint);
		}
		if (query == null) {
			String message = DomainUtil.bind(OCLMessages.MissingBodyForInvocationDelegate_ERROR_, type);
			throw new OCLDelegateException(message);
		}
		return validateExpressionInOCL(eClassifier, value, diagnostics, context,
			constraintName, source, code, query);
	}
	protected boolean check(EvaluationVisitor<?> evaluationVisitor, String constraintName, ExpressionInOCL query) {
		if (query.getType() != evaluationVisitor.getMetaModelManager().getBooleanType()) {
			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
			String message = DomainUtil.bind(OCLMessages.ValidationConstraintIsNotBoolean_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			throw new OCLDelegateException(message);
		}
		try {
			Object result = query.accept(evaluationVisitor);
			if (result == null) {
				String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
				String message = DomainUtil.bind(OCLMessages.ValidationResultIsNull_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
				throw new OCLDelegateException(message);
			}
			return ValuesUtil.asBoolean(result);
		} catch (InvalidValueException e) {
			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
			String message = DomainUtil.bind(OCLMessages.ValidationResultIsNotBoolean_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			throw new OCLDelegateException(message, e);
//		} catch (InvalidEvaluationException e) {
//			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
//			String message = DomainUtil.bind(OCLMessages.ValidationResultIsInvalid_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
//			throw new OCLDelegateException(message, e);
		}
	}

	protected boolean validateExpressionInOCL(@NonNull EClassifier eClassifier, @NonNull Object value, @Nullable DiagnosticChain diagnostics,
			Map<Object, Object> context, String constraintName, String source, int code, @NonNull ExpressionInOCL query) {
		EvaluationVisitor<?> evaluationVisitor = createEvaluationVisitor(value, query);
		if (query.getType() != evaluationVisitor.getMetaModelManager().getBooleanType()) {
			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
			String message = DomainUtil.bind(OCLMessages.ValidationConstraintIsNotBoolean_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			throw new OCLDelegateException(message);
		}
		Object result;
		try {
			result = query.accept(evaluationVisitor);
			if (result == null) {
				if (diagnostics == null) {
					String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
					String message = DomainUtil.bind(OCLMessages.ValidationResultIsNull_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
					throw new OCLDelegateException(message);
				}
			}
			else if (ValuesUtil.asBoolean(result)) {
				return true;
			}
		} catch (InvalidValueException e) {
//			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
//			String message = DomainUtil.bind(OCLMessages.ValidationResultIsNotBoolean_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
//			throw new OCLDelegateException(message, e);
//		} catch (InvalidEvaluationException e) {
			String objectLabel = DomainUtil.getLabel(query.getContextVariable().getType());
			String message = DomainUtil.bind(OCLMessages.ValidationResultIsInvalid_ERROR_, PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			throw new OCLDelegateException(message, e);
		}
		if (diagnostics != null) {
			String message = evaluateMessage(evaluationVisitor, constraintName, query);
			if (message == null) {
				Object objectLabel = DomainUtil.getLabel(eClassifier, value, context);
				message = DomainUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_,
					PivotUtil.getConstraintTypeName(query), constraintName, objectLabel);
			}
			int severity = result == null ? Diagnostic.ERROR : Diagnostic.WARNING;
		    diagnostics.add(new BasicDiagnostic(severity, source, code, message, new Object [] { value }));
		}
		return false;
	}
}
