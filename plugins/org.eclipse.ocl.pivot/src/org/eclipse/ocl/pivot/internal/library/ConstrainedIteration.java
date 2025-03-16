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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractIteration;
import org.eclipse.ocl.pivot.library.EvaluatorConstrainedIterationManager;

/**
 * An instance of ConstrainedIteration supports evaluation of
 * an operation defined by a body expression.
 *
 * @since 1.23
 */
public class ConstrainedIteration extends AbstractIteration
{
	protected final @NonNull ExpressionInOCL expressionInOCL;

	public ConstrainedIteration(@NonNull ExpressionInOCL expressionInOCL) {
		this.expressionInOCL = expressionInOCL;
	}

/*	@Override
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
	} */

/*	@Override
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
	} */

	@Override
	public @NonNull String toString() {
		return String.valueOf(expressionInOCL.eContainer());
	}

	@Override
	public @NonNull Object createAccumulatorValue(@NonNull Executor executor, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
//		return this;		// Not used	// XXX chnage to null after signature change and regen
		throw new IllegalStateException();			// Caller should bypass
	}

	@Override
	public @Nullable Object evaluateIteration(@NonNull IterationManager iterationManager) {
		return ((EvaluatorConstrainedIterationManager)iterationManager).evaluate();
		// TODO Auto-generated method stub
		// return super.evaluateIteration(iterationManager);
	/*	iterationManager.
		assert !PivotUtil.getReferredOperation(callExp).isIsValidating();
		List<? extends OCLExpression> arguments = callExp.getOwnedArguments();
		@Nullable Object[] boxedSourceAndArgumentValues = new @Nullable Object[1+arguments.size()];
		boxedSourceAndArgumentValues[0]= sourceValue;
		for (int i = 0; i < arguments.size(); i++) {
			OCLExpression argument = arguments.get(i);
			assert argument != null;
			boxedSourceAndArgumentValues[1+i] = executor.evaluate(argument);
		}
		return evaluate(executor, callExp, boxedSourceAndArgumentValues); */
	}

	@Override
	protected @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager) {
		return null;		// Not used
	}
}