/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.library.LibraryOperation.LibraryOperationExtension2;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * AbstractIteration realizes shared characteristics of library iterations by providing a
 * default iteration algorithm with a call-back at each iteration step.
 */
public abstract class AbstractIteration extends AbstractIterationOrOperation implements LibraryIteration.LibraryIterationExtension
{
	/**
	 * An out-of-band value that can be returned by {@link #updateAccumulator} to signal
	 * that the iteration should carry on rather than terminate using the returned value.
	 */
	protected static final @NonNull Object CARRY_ON = new Object() {
		@Override
		public String toString() {
			return "<<CARRY_ON>>";
		}
	};

	/**
	 * A MutableObject may be used as an iteration accumulatior containing a single changing value.
	 */
	public static class MutableObject
	{
		private @Nullable Object value;

		public MutableObject(@Nullable Object value) {
			this.value = value;
		}

		public @Nullable Object get() {
			return value;
		}

		public void set(@Nullable Object value) {
			this.value = value;
		}
	}
	/**
	 * @since 1.15
	 */
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull LoopExp loopExp) {
	//	EnvironmentFactory environmentFactory = symbolicEvaluationEnvironment.getEnvironmentFactory();
	//	CompleteModel completeModel = environmentFactory.getCompleteModel();
	//	StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
	//	Iteration referredIteration = PivotUtil.getReferredIteration(callExp);
		TypeId returnTypeId = loopExp.getTypeId();
		OCLExpression source = PivotUtil.getOwnedSource(loopExp);
	//	CompleteClass oclInvalidClass = completeModel.getCompleteClass(standardLibrary.getOclInvalidType());
	//	Operation oclInvalidOperation = oclInvalidClass.getOperation(referredOperation);
	//	assert oclInvalidOperation == null : "Missing OclInvalid overload for " + referredOperation;
		SymbolicValue invalidSourceProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (invalidSourceProblem != null) {
			return invalidSourceProblem;
		}
	//	CompleteClass oclVoidClass = completeModel.getCompleteClass(standardLibrary.getOclVoidType());
	//	Operation oclVoidOperation = oclVoidClass.getOperation(referredOperation);
	//	assert oclVoidOperation == null : "Missing OcVoid overload for " + referredOperation;
		SymbolicValue nullSourceProblem = symbolicEvaluationEnvironment.checkNotNull(source, returnTypeId);
		if (nullSourceProblem != null) {
			return nullSourceProblem;
		}
	/*	int i = 0;
		for (@NonNull OCLExpression argument : PivotUtil.getOwnedArguments(callExp)) {
			if (!referredOperation.isIsValidating()) {
				SymbolicValue invalidArgumentProblem = symbolicEvaluationEnvironment.checkNotInvalid(argument, returnTypeId);
				if (invalidArgumentProblem != null) {
					return invalidArgumentProblem;
				}
			}
			Parameter parameter = PivotUtil.getOwnedParameter(referredOperation, i);
			if (parameter.isIsRequired()) {
				SymbolicValue nullArgumentProblem = symbolicEvaluationEnvironment.checkNotNull(argument, returnTypeId);
				if (nullArgumentProblem != null) {
					return nullArgumentProblem;
				}
			}
			i++;
		} */
		return null;
	}

	/**
	 * @since 1.15
	 */
	protected @NonNull SymbolicValue createChildSymbolicValue(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull LoopExp loopExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		OCLExpression ownedSource = PivotUtil.getOwnedSource(loopExp);
		boolean mayBeInvalidOrNull = evaluationEnvironment.mayBeInvalidOrNull(ownedSource);
		for (@NonNull SymbolicValue argumentSymbolicValue : argumentSymbolicValues) {		// XXX correlate parameter/return nullity
			if (argumentSymbolicValue.mayBeInvalidOrNull()) {
				mayBeInvalidOrNull = true;
			}
		}
		return new SymbolicUnknownValueImpl(loopExp.getTypeId(), false, mayBeInvalidOrNull);
	}

	/**
	 * The default iteration algorithm steps through the iteration domain by invoking
	 * {@link IterationManager#hasCurrent()} and {@link IterationManager#advanceIterators()}.
	 * At each step {@link #updateAccumulator(IterationManager)} is invoked to update the
	 * accumulator for that step. A non-null return causes a premature exit and forms the
	 * return from the overall evaluation. If all steps complete {@link #resolveTerminalValue(IterationManager)}
	 * is invoked to provide the return value.
	 * <p>
	 * Derived classes may override this method to change the iteration algorithm or override
	 * the call-backs to customize the default iteration.
	 */
	@Override
	public @Nullable Object evaluateIteration(@NonNull IterationManager iterationManager) {
		try {
			while (true) {
				if (!iterationManager.hasCurrent()) {
					return resolveTerminalValue(iterationManager);
				}
				Object resultVal = updateAccumulator(iterationManager);
				if (resultVal != CARRY_ON) {
					return resultVal;
				}
				iterationManager.advanceIterators();
			}
		}
		finally {
			iterationManager.dispose();
		}
	}

	/**
	 * Return the final result at the end of an iteration over all the source elements. The
	 * default implementation just returns the accumulator. Derived iterations should
	 * override.
	 * <br>
	 * This method is bypassed if the iteration ends prematurely.
	 *
	 * @param iterationManager the iteration context
	 * @return the result
	 */
	protected @Nullable Object resolveTerminalValue(@NonNull IterationManager iterationManager) {
		return iterationManager.getAccumulatorValue();
	}

	/**
	 * @since 1.15
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull LoopExp loopExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, loopExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(loopExp));
		boolean isKnown = sourceSymbolicValue.isKnown();
		int argumentsSize = 0;
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
	/*	Iterable<@NonNull OCLExpression> ownedArguments = PivotUtil.getOwnedArguments(loopExp);
		int argumentsSize = Iterables.size(ownedArguments);
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
		for (@NonNull OCLExpression argument : ownedArguments) {
			SymbolicValue argumentSymbolicValue = evaluationEnvironment.symbolicEvaluate(argument);
			if (!argumentSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(argumentSymbolicValue);
		} */
		if (isKnown) {
			@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+argumentsSize];
			sourceAndArgumentValues[0] = ((SymbolicKnownValue)sourceSymbolicValue).getValue();
			for (int i = 0; i < argumentsSize; i++) {
				sourceAndArgumentValues[i+1] = ((SymbolicKnownValue)argumentSymbolicValues.get(i)).getValue();
			}
			Object result = ((LibraryOperationExtension2)this).evaluate(evaluationEnvironment.getExecutor(), loopExp, sourceAndArgumentValues);
			return evaluationEnvironment.getKnownValue(result);
		}
		else {
			return createChildSymbolicValue(evaluationEnvironment, loopExp, sourceSymbolicValue, argumentSymbolicValues);
		}
	}

	/**
	 * Update the accumulatorValue with the bodyValue resulting from the current iteration
	 * for which the iterators define the context in the environment.
	 *
	 * @param iterationManager the iteration context
	 * @return non-CARRY_ON premature result of iteration, or CARRY_ON if complete
	 */
	protected abstract @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager);
}
