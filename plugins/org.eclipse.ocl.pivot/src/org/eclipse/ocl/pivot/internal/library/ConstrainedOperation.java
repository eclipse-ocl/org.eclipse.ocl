/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (CEA LIST) - Bug 425799 - validity view
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.Executor.ExecutorExtension;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Iterables;

/**
 * An instance of ConstrainedOperation supports evaluation of
 * an operation defined by constraints.
 */
public class ConstrainedOperation extends AbstractOperation
{
	protected final @NonNull ExpressionInOCL expressionInOCL;

	public ConstrainedOperation(@NonNull ExpressionInOCL expressionInOCL) {
		this.expressionInOCL = expressionInOCL;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @Nullable Object basicEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] boxedSourceAndArgumentValues) {
		PivotUtil.checkExpression(expressionInOCL);
		EvaluationEnvironment nestedEvaluationEnvironment = ((ExecutorExtension)executor).pushEvaluationEnvironment(expressionInOCL, caller);
		nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(expressionInOCL.getOwnedContext()), boxedSourceAndArgumentValues[0]);
		List<Variable> parameters = expressionInOCL.getOwnedParameters();
		if (!parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); i++) {
				Object value = boxedSourceAndArgumentValues[i+1];
				nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(parameters.get(i)), value);
			}
		}
		try {
			OCLExpression bodyExpression = expressionInOCL.getOwnedBody();
			assert bodyExpression != null;
			return executor.evaluate(bodyExpression);
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}

	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		return checkPreconditions(evaluationEnvironment, callExp, 0); 		// XXX is there anything to check ?? yes the preconditions
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		assert !PivotUtil.getReferredOperation(callExp).isIsValidating();
		List<? extends OCLExpression> arguments = callExp.getOwnedArguments();
		@Nullable Object[] boxedSourceAndArgumentValues = new @Nullable Object[1+arguments.size()];
		boxedSourceAndArgumentValues[0]= sourceValue;
		for (int i = 0; i < arguments.size(); i++) {
			OCLExpression argument = arguments.get(i);
			assert argument != null;
			boxedSourceAndArgumentValues[1+i] = executor.evaluate(argument);
		}
		return evaluate(executor, callExp, boxedSourceAndArgumentValues);
	}

	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(callExp));
		boolean isKnown = sourceSymbolicValue.isKnown();
		List<@NonNull OCLExpression> ownedArguments = PivotUtilInternal.getOwnedArgumentsList(callExp);
		int argumentsSize = Iterables.size(ownedArguments);
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
		for (@NonNull OCLExpression argument : ownedArguments) {
			SymbolicValue argumentSymbolicValue = evaluationEnvironment.symbolicEvaluate(argument);
			if (!argumentSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(argumentSymbolicValue);
		}
		if (isKnown) {



			SymbolicAnalysis symbolicAnalysis = evaluationEnvironment.getSymbolicAnalysis();
			@Nullable Object[] boxedSourceAndArgumentValues = new @Nullable Object[1+argumentsSize];
			boxedSourceAndArgumentValues[0] = sourceSymbolicValue.getKnownValue();
			for (int i = 0; i < argumentsSize; i++) {
				OCLExpression argument = ownedArguments.get(i);
				assert argument != null;
				boxedSourceAndArgumentValues[1+i] = argumentSymbolicValues.get(i).getKnownValue();
			}
			final Object resultObject = evaluate(symbolicAnalysis.getExecutor(), callExp, boxedSourceAndArgumentValues);
			return symbolicAnalysis.getKnownValue(resultObject);




		/*	// FIXME nested
			@Nullable Object[] argumentValues = new @Nullable Object[1+argumentsSize];
			Object selfObject = sourceSymbolicValue.getKnownValue();
		//	SymbolicValue resultSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(callExp));
			Object resultObject = null;//resultSymbolicValue.getKnownValue();
			Variable resultVariable = expressionInOCL.getOwnedResult();
			if (resultVariable != null) {
			//	Object contextValue = idResolver.boxedValueOf(contextElement);
			//	evaluationEnvironment.add(contextVariable, contextValue);
				CSEElement cseElement = symbolicAnalysis.getCSEElement(resultVariable);
			// XXX	evaluationEnvironment.traceValue(cseElement, resultObject);
			}
			for (int i = 0; i < argumentsSize; i++) {
				argumentValues[i] = argumentSymbolicValues.get(i).getKnownValue();
			}
		//	Object result = ((LibraryOperationExtension2)this).evaluate(evaluationEnvironment.getExecutor(), callExp, sourceAndArgumentValues);
		//	return evaluationEnvironment.getKnownValue(result);
		//	symbolicAnalysis.initializeEvaluationEnvironment(expressionInOCL, selfObject, resultObject, argumentValues);			// XXX all related constraints

			symbolicAnalysis.symbolicEvaluate(expressionInOCL);
			return sourceSymbolicValue;// XXXX result symbolicAnalysis; */
		}
		else {
			return createResultValue(evaluationEnvironment, callExp, sourceSymbolicValue, argumentSymbolicValues);
		}


	//	ModelManager modelManager = environmentFactory.createModelManager(selfObject);
	//	SymbolicAnalysis symbolicAnalysis = createSymbolicAnalysis(expressionInOCL, modelManager);



	//	return super.symbolicEvaluate(evaluationEnvironment, callExp);
	}

	@Override
	public @NonNull String toString() {
		return String.valueOf(expressionInOCL.eContainer());
	}
}