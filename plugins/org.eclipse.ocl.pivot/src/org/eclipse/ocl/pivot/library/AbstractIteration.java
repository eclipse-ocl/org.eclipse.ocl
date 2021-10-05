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
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.library.LibraryOperation.LibraryOperationExtension2;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Iterables;

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
	 * @since 1.17
	 */
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull LoopExp loopExp) {
		TypeId returnTypeId = loopExp.getTypeId();
		SymbolicReason returnMayBeNullReason = SymbolicUtil.isRequiredReason(loopExp);
		OCLExpression source = PivotUtil.getOwnedSource(loopExp);
		SymbolicValue invalidSourceProblem = evaluationEnvironment.checkNotInvalid(source, returnTypeId, returnMayBeNullReason, loopExp);
		if (invalidSourceProblem != null) {
			return invalidSourceProblem;
		}
		if (!loopExp.isIsSafe()) {
			SymbolicValue nullSourceProblem = evaluationEnvironment.checkNotNull(source, returnTypeId, returnMayBeNullReason, loopExp);
			if (nullSourceProblem != null) {
				return nullSourceProblem;
			}
		}
		for (@NonNull VariableDeclaration iterator : PivotUtil.getOwnedIterators(loopExp)) {
			SymbolicValue invalidIteratorProblem = evaluationEnvironment.checkNotInvalid(iterator, returnTypeId, returnMayBeNullReason, loopExp);
			if (invalidIteratorProblem != null) {
				return invalidIteratorProblem;
			}
		}
		if (loopExp instanceof IterateExp) {
			VariableDeclaration ownedResult = PivotUtil.getOwnedResult((IterateExp)loopExp);
			SymbolicValue invalidResultProblem = evaluationEnvironment.checkNotInvalid(ownedResult, returnTypeId, returnMayBeNullReason, loopExp);
			if (invalidResultProblem != null) {
				return invalidResultProblem;
			}
		}
		OCLExpression bodyExpression = PivotUtil.getOwnedBody(loopExp);
		SymbolicValue invalidBodyProblem = evaluationEnvironment.checkNotInvalid(bodyExpression, returnTypeId, returnMayBeNullReason, loopExp);
		if (invalidBodyProblem != null) {
			return invalidBodyProblem;
		}
		if (bodyExpression.isIsRequired()) {
			SymbolicValue nullBodyProblem = evaluationEnvironment.checkNotNull(bodyExpression, returnTypeId, returnMayBeNullReason, loopExp);
			if (nullBodyProblem != null) {
				return nullBodyProblem;
			}
		}
		return null;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull SymbolicValue createResultValue(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull LoopExp loopExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		if (sourceSymbolicValue.mayBeInvalid()) {
			mayBeInvalid = true;
		}
		if (sourceSymbolicValue.mayBeNull()) {
			if (loopExp.isIsSafe()) {
				mayBeNull = true;
			}
			else {
				mayBeInvalid = true;
			}
		}
		for (@NonNull SymbolicValue argumentSymbolicValue : argumentSymbolicValues) {
			if (argumentSymbolicValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
		}
	/*	OCLExpression bodyExp = PivotUtil.getOwnedBody(loopExp);
		if (evaluationEnvironment.mayBeNull(bodyExp)) {
			Parameter bodyParameter = loopExp.getReferredIteration().getOwnedParameters().get(0);
			if (bodyParameter.isIsRequired()) {
				mayBeInvalid = true;
			}
			else {
				mayBeNull = true;
			}
		} */
		SymbolicAnalysis symbolicAnalysis = evaluationEnvironment.getSymbolicAnalysis();
		return symbolicAnalysis.createUnknownValue(loopExp.getTypeId(), SymbolicUtil.mayBeNullReason(mayBeNull), SymbolicUtil.mayBeInvalidReason(mayBeInvalid));
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
	 * @since 1.16
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull LoopExp loopExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, loopExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(loopExp));
		boolean isKnown = false; // Need to guard against huge symbolic constants -- sourceSymbolicValue.isKnown();
		Iterable<@NonNull Variable> ownedIterators = PivotUtil.getOwnedIterators(loopExp);
		int iteratorsSize = Iterables.size(ownedIterators);
		int resultsSize = loopExp instanceof IterateExp ? 1 : 0;
		int bodySize = 1;
		int argumentsSize = iteratorsSize + resultsSize + bodySize;
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
		for (@NonNull Variable iterator : ownedIterators) {
			SymbolicValue iteratorSymbolicValue = evaluationEnvironment.symbolicEvaluate(iterator);
			if (!iteratorSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(iteratorSymbolicValue);
		}
		if (loopExp instanceof IterateExp) {
			SymbolicValue resultSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedResult((IterateExp) loopExp));
			if (!resultSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(resultSymbolicValue);
		}
		OCLExpression bodyExp = PivotUtil.getOwnedBody(loopExp);
		SymbolicValue bodySymbolicValue = evaluationEnvironment.symbolicEvaluate(bodyExp);
		if (!bodySymbolicValue.isKnown()) {
			isKnown = false;
		}
		argumentSymbolicValues.add(bodySymbolicValue);
		if (isKnown) {
			@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+argumentsSize];
			sourceAndArgumentValues[0] = sourceSymbolicValue.getKnownValue();
			for (int i = 0; i < argumentsSize; i++) {
				sourceAndArgumentValues[i+1] = argumentSymbolicValues.get(i).getKnownValue();
			}
			SymbolicAnalysis symbolicAnalysis = evaluationEnvironment.getSymbolicAnalysis();
			Object result = ((LibraryOperationExtension2)this).evaluate(symbolicAnalysis.getExecutor(), loopExp, sourceAndArgumentValues);
			return evaluationEnvironment.getKnownValue(result);
		}
		else {
			return createResultValue(evaluationEnvironment, loopExp, sourceSymbolicValue, argumentSymbolicValues);
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
