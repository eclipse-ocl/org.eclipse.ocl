/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.AbstractConstraintEvaluator;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.validation.ValidationContext;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * An implementation of the dynamic validation delegate API, maintaining a cache
 * of compiled constraints and invariants.
 */
public class OCLValidationDelegate implements ValidationDelegate
{
	protected static class CheckingConstraintEvaluator extends AbstractConstraintEvaluator<Boolean>
	{
		protected final @NonNull EClassifier eClassifier;

		protected CheckingConstraintEvaluator(@NonNull EClassifier eClassifier, @NonNull ExpressionInOCL query) {
			super(query);
			this.eClassifier = eClassifier;
		}

		@Override
		public Boolean evaluate(@NonNull EvaluationVisitor evaluationVisitor) {
			if (!isBooleanConstraint()) {
				String objectLabel = LabelUtil.getLabel(query.getType());
				//				String constraintTypeName = getConstraintTypeName(query);
				String constraintTypeName = getConstraintTypeName();
				String constraintName = getConstraintName();
				String checkMessage = StringUtil.bind(PivotMessagesInternal.ValidationConstraintIsNotBooleanType_ERROR_,
					constraintTypeName, constraintName, objectLabel);
				EvaluationException cause = new EvaluationException(checkMessage);
				throw new OCLDelegateException(cause);
			}
			return super.evaluate(evaluationVisitor);
		}

		@Override
		protected String getObjectLabel() {
			return LabelUtil.getLabel(eClassifier, null, null);
		}

		@Override
		protected Boolean handleExceptionResult(@NonNull Throwable e) {
			String constraintTypeName = getConstraintTypeName();
			String constraintName = getConstraintName();
			String objectLabel = getObjectLabel();
			String message = e.toString();
			EvaluationException cause = new EvaluationException(e, PivotMessagesInternal.ValidationResultIsInvalid_ERROR_,
				constraintTypeName, constraintName, objectLabel, message);
			throw new OCLDelegateException(cause);
		}

		@Override
		protected Boolean handleFailureResult(@Nullable Object result) {
			if (result == null) {
				String message = getConstraintResultMessage(result);
				EvaluationException cause = new EvaluationException(message);
				throw new OCLDelegateException(cause);
			}
			else {
				return Boolean.FALSE;
			}
		}

		@Override
		protected Boolean handleInvalidExpression(@NonNull String message) {
			EvaluationException cause = new EvaluationException(message);
			throw new OCLDelegateException(cause);
		}

		@Override
		protected Boolean handleInvalidResult(@NonNull InvalidValueException e) {
			String constraintTypeName = getConstraintTypeName();
			String constraintName = getConstraintName();
			String objectLabel = getObjectLabel();
			String localizedMessage = e.getLocalizedMessage();
			EvaluationException cause = new EvaluationException(e, PivotMessagesInternal.ValidationResultIsInvalid_ERROR_,
				constraintTypeName, constraintName, objectLabel, localizedMessage);
			throw new OCLDelegateException(cause);
		}

		@Override
		protected Boolean handleSuccessResult() {
			return Boolean.TRUE;
		}
	}

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

	//	protected boolean check(@NonNull EvaluationVisitor evaluationVisitor, @NonNull String constraintName, @NonNull ExpressionInOCL query) {
	//		ConstraintEvaluator<Boolean> constraintEvaluator = new CheckingConstraintEvaluator(eClassifier, query);
	//		return constraintEvaluator.evaluate(evaluationVisitor);
	//	}

	/*	public @NonNull ExpressionInOCL getExpressionInOCL(@NonNull MetamodelManager metamodelManager, @NonNull Constraint constraint) {
		ExpressionInOCL query = null;
		ExpressionInOCL valueSpecification = constraint.getSpecification();
		if (valueSpecification instanceof ExpressionInOCL) {
			query = (ExpressionInOCL) valueSpecification;
		}
		else {
			Type contextType = (Type) constraint.getContext();
			if (contextType != null) {
				ClassContext classContext = new ClassContext(metamodelManager, null, contextType);
				query = ValidationBehavior.INSTANCE.getExpressionInOCL(classContext, constraint);
			}
		}
		if (query == null) {
			String message = ClassUtil.bind(OCLMessages.MissingBodyForInvocationDelegate_ERROR_, constraint.getContext());
			throw new OCLDelegateException(new SemanticException(message));
		}
		return query;
	} */

	@Override
	public String toString() {
		return "<" + delegateDomain.getURI() + ":validate> " + eClassifier.getEPackage().getName() + "::" + eClassifier.getName(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject,
			Map<Object, Object> context, EOperation invariant, String expression) {
		if (eClass == null) {
			throw new NullPointerException("Null EClass");
		}
		if (eObject == null) {
			throw new NullPointerException("Null EObject");
		}
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(eObject);			// XXX context lookup first
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		NamedElement namedElement = delegateDomain.getPivot(NamedElement.class, ClassUtil.nonNullEMF(invariant));
		if (namedElement instanceof Operation) {
			Operation operation = (Operation)namedElement;
			ExpressionInOCL query = InvocationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, operation);
			InvocationBehavior.INSTANCE.validate(operation);
			return validateExpressionInOCL(environmentFactory, eClass, eObject, null, context, null, 0, query);
		}
		else if (namedElement instanceof Constraint) {
			Constraint constraint = (Constraint)namedElement;
			ExpressionInOCL query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, constraint);
			ValidationBehavior.INSTANCE.validate(constraint);
			return validateExpressionInOCL(environmentFactory, eClass, eObject, null, context, null, 0, query);
		}
		else if (namedElement != null) {
			throw new ClassCastException(namedElement.getClass().getName() + " does not provide a Constraint");
		}
		else {
			throw new ClassCastException(invariant.eClass().getName() + " does not provide a Constraint");
		}
	}

	@Override
	public boolean validate(@NonNull EClass eClass, @NonNull EObject eObject, @Nullable DiagnosticChain diagnostics,
			Map<Object, Object> context, @NonNull EOperation invariant, String expression, int severity, String source, int code) {
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(eObject);
	//	MetamodelManager metamodelManager = delegateDomain.getMetamodelManager();
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		NamedElement namedElement = delegateDomain.getPivot(NamedElement.class, ClassUtil.nonNullEMF(invariant));
		if (namedElement instanceof Operation) {
			Operation operation = (Operation)namedElement;
			ExpressionInOCL query = InvocationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, operation);
			InvocationBehavior.INSTANCE.validate(operation);
			return validateExpressionInOCL(environmentFactory, eClass, eObject, null, context, null, 0, query);
		}
		else if (namedElement instanceof Constraint) {
			Constraint constraint = (Constraint)namedElement;
			ExpressionInOCL query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, constraint);
			ValidationBehavior.INSTANCE.validate(constraint);
			return validateExpressionInOCL(environmentFactory, eClass, eObject, diagnostics, context, source, code, query);
		}
		else if (namedElement != null) {
			throw new ClassCastException(namedElement.getClass().getName() + " does not provide a Constraint");
		}
		else {
			throw new IllegalStateException(invariant.eClass().getName() + " does not provide a Constraint");
		}
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject,
			Map<Object, Object> context, String constraintName, String expression) {
		assert context != null;
	//	if (Boolean.TRUE.equals(context.get(DelegateInstaller.SUPPRESS_DYNAMIC_OCL_DELEGATES))) {
	//		assert false : "XXX experimenting with dynamic OCL detection";
	//		return true;			// XXX This suppresses non-dynamic too
	//	}
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

	@Override
	public boolean validate(@NonNull EClass eClass, @NonNull EObject eObject, @Nullable DiagnosticChain diagnostics, Map<Object, Object> context,
			@NonNull String constraintName, String expression, int severity, String source, int code) {
		return validatePivot(eClass, eObject, diagnostics, context, constraintName, source, code);
	}

	@Override
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

	@Override
	public boolean validate(@NonNull EDataType eDataType, @NonNull Object value, @Nullable DiagnosticChain diagnostics, Map<Object, Object> context,
			@NonNull String constraintName, String expression, int severity, String source, int code) {
		return validatePivot(eDataType, value, diagnostics, context, constraintName, source, code);
	}

	@Deprecated /* @deprecated pass known EnvironmentFactory */
	protected boolean validateExpressionInOCL(@NonNull EClassifier eClassifier, @NonNull Object value, @Nullable DiagnosticChain diagnostics,
			Map<Object, Object> context, String constraintName, String source, int code, @NonNull ExpressionInOCL query) {
		EnvironmentFactory environmentFactory = PivotUtilInternal.getEnvironmentFactory(value);
		return validateExpressionInOCL(environmentFactory, eClassifier, value, diagnostics, context, source, code, query);
	}

	/**
	 * @since 1.23
	 */
	protected boolean validateExpressionInOCL(final @NonNull EnvironmentFactory environmentFactory, final @NonNull EClassifier eClassifier, final @NonNull Object value, final @Nullable DiagnosticChain diagnostics,
			final Map<Object, Object> context, final String source, final int code, @NonNull ExpressionInOCL query) {
		AbstractConstraintEvaluator<Boolean> constraintEvaluator = new CheckingConstraintEvaluator(eClassifier, query)
		{
			@Override
			protected String getObjectLabel() {
				return NameUtil.qualifiedNameFor(value);
				//				return ClassUtil.getLabel(eClassifier, value, context);
			}

			@Override
			protected Boolean handleFailureResult(@Nullable Object result) {
			//	if (result == null) {
			//		String message = getConstraintResultMessage(result);
			//		EvaluationException cause = new EvaluationException(message);
			//		throw new OCLDelegateException(cause);
			//	}
				if (diagnostics != null) {
					String message = getConstraintResultMessage(result);
					int severity = getConstraintResultSeverity(result);
					diagnostics.add(new BasicDiagnostic(severity, source, code, message, new Object [] { value }));
				}
				return Boolean.FALSE;
			}
		};
		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		ModelManager modelManager = executor != null ? executor.getModelManager() : null;
		EvaluationVisitor evaluationVisitor = environmentFactory.createEvaluationVisitor(value, query, modelManager);
		return constraintEvaluator.evaluate(evaluationVisitor);
	}

	protected boolean validatePivot(@NonNull EClassifier eClassifier, @NonNull Object value, @Nullable DiagnosticChain diagnostics,
			Map<Object, Object> context, @NonNull String constraintName, String source, int code) {
		EnvironmentFactoryInternal environmentFactory = ValidationContext.getEnvironmentFactory(context, value);// instanceof Notifier ? (Notifier)value : null);
		// XXX ??? must confirm that value is in the externalResourceSet
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Type type = delegateDomain.getPivot(Type.class, eClassifier);
		Constraint constraint = ValidationBehavior.INSTANCE.getConstraint(metamodelManager, eClassifier, constraintName);
		if (constraint == null) {
			SemanticException cause = new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, type, PivotConstantsInternal.CONSTRAINT_ROLE);
			throw new OCLDelegateException(cause);
		}
		ExpressionInOCL query = null;
		if (type != null) {
			query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, constraint);
		}
		if (query == null) {
			SemanticException cause = new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, type, PivotConstantsInternal.CONSTRAINT_ROLE);
			throw new OCLDelegateException(cause);
		}
		return validateExpressionInOCL(environmentFactory, eClassifier, value, diagnostics, context, source, code, query);
	}
}
