/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.values.AbstractRefinedSymbolicValue;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.RefinedSymbolicValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.15
 */
public abstract class Hypothesis implements Comparable<@NonNull Hypothesis>
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;
	protected final @NonNull OCLExpression expression;
	protected final @NonNull SymbolicValue value;
	protected final @NonNull CSEElement cseElement;
	private @Nullable Boolean isContradiction = null;

	protected Hypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull OCLExpression expression, @NonNull SymbolicValue value) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.expression = expression;
		this.value = value;
		this.cseElement = symbolicAnalysis.getCSEElement(expression);
	}

	public void check() {
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = symbolicAnalysis.createHypothesizedSymbolicEvaluationEnvironment(this);
		hypothesizedEvaluationEnvironment.putHypothesizedValue(expression, value);
		symbolicAnalysis.pushEvaluationEnvironment(hypothesizedEvaluationEnvironment);
		isContradiction = hypothesizedEvaluationEnvironment.isContradiction();
		symbolicAnalysis.popEvaluationEnvironment();
		if (isContradiction()) {
		//	SymbolicReEvaluationEnvironment refinedEvaluationEnvironment = symbolicAnalysis.createSymbolicReEvaluationEnvironment(this);
		//	refinedEvaluationEnvironment.putRefinedValue(expression, getRefinedValue());
		//	symbolicAnalysis.pushEvaluationEnvironment(refinedEvaluationEnvironment);
		//	refinedEvaluationEnvironment.install();
		//	symbolicAnalysis.popEvaluationEnvironment();
		}
	}

	@Override
	public int compareTo(@NonNull Hypothesis that) {
		int h1 = this.cseElement.getHeight();
		int h2 = that.cseElement.getHeight();
		int diff = h1 - h2;
		if (diff != 0) {
			return diff;
		}
		return System.identityHashCode(this) - System.identityHashCode(that);
	}

	public @NonNull OCLExpression getExpression() {
		return expression;
	}

	public abstract @NonNull RefinedSymbolicValue getRefinedValue();

	public @NonNull SymbolicValue getValue() {
		return value;
	}

	public boolean isContradiction() {
		return ClassUtil.nonNullState(isContradiction).booleanValue();
	}

	public @Nullable Boolean mayBeInvalid() {
		return null;
	}

	public @Nullable Boolean mayBeNull() {
		return null;
	}

	public @Nullable Boolean mayBeZero() {
		return null;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(value);
		s.append(isContradiction == null ? " ?= " : (isContradiction == Boolean.TRUE) ? " == " :  " != ");
		s.append(expression);
		return s.toString();
	}

	public static class MayBeInvalidHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue mayBeInvalidValue;

		public MayBeInvalidHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull OCLExpression expression, @NonNull SymbolicValue mayBeInvalidValue) {
			super(symbolicAnalysis, expression, symbolicAnalysis.getEvaluationEnvironment().getKnownValue(ValueUtil.INVALID_VALUE));
			this.mayBeInvalidValue = mayBeInvalidValue;
		}

		@Override
		public @NonNull RefinedSymbolicValue getRefinedValue() {
			return new NotInvalidSymbolicValue(this, mayBeInvalidValue);
		}

		@Override
		public @Nullable Boolean mayBeInvalid() {
			return isContradiction();
		}
	}

	public static class MayBeNullHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue mayBeNullValue;

		public MayBeNullHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull OCLExpression expression, @NonNull SymbolicValue mayBeNullValue) {
			super(symbolicAnalysis, expression, symbolicAnalysis.getEvaluationEnvironment().getKnownValue(ValueUtil.NULL_VALUE));
			this.mayBeNullValue = mayBeNullValue;
		}

		@Override
		public @NonNull RefinedSymbolicValue getRefinedValue() {
			return new NotNullSymbolicValue(this, mayBeNullValue);
		}

		@Override
		public @Nullable Boolean mayBeNull() {
			return isContradiction();
		}
	}

	public static class MayBeZeroHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue mayBeZeroValue;

		public MayBeZeroHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull OCLExpression expression, @NonNull SymbolicValue mayBeZeroValue) {
			super(symbolicAnalysis, expression, symbolicAnalysis.getEvaluationEnvironment().getKnownValue(ValueUtil.ZERO_VALUE));
			this.mayBeZeroValue = mayBeZeroValue;
		}

		@Override
		public @NonNull RefinedSymbolicValue getRefinedValue() {
			return new NotZeroSymbolicValue(this, mayBeZeroValue);
		}

		@Override
		public @Nullable Boolean mayBeZero() {
			return isContradiction();
		}
	}

	public static class NotInvalidSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotInvalidSymbolicValue(@NonNull Hypothesis hypothesis, @NonNull SymbolicValue value) {
			super(hypothesis, value);
		}

		@Override
		public boolean mayBeInvalid() {
			return false;
		}
	}

	public static class NotNullSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotNullSymbolicValue(@NonNull Hypothesis hypothesis, @NonNull SymbolicValue value) {
			super(hypothesis, value);
		}

		@Override
		public boolean mayBeNull() {
			return false;
		}
	}

	public static class NotZeroSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotZeroSymbolicValue(@NonNull Hypothesis hypothesis, @NonNull SymbolicValue value) {
			super(hypothesis, value);
		}

		@Override
		public boolean mayBeZero() {
			return false;
		}
	}
}