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
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.values.AbstractRefinedSymbolicValue;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.15
 */
public abstract class Hypothesis implements Comparable<@NonNull Hypothesis>
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;
	protected final @NonNull TypedElement typedElement;
	protected final @NonNull SymbolicValue value;
	protected final @NonNull CSEElement cseElement;
	private @Nullable Boolean isContradiction = null;

	protected Hypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue value) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.typedElement = typedElement;
		this.value = value;
		this.cseElement = symbolicAnalysis.getCSEElement(typedElement);
	}

	public void check() {
		assert isContradiction == null : "isContradiction already determined for: " + this;
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = symbolicAnalysis.createHypothesizedSymbolicEvaluationEnvironment(this);
		symbolicAnalysis.pushEvaluationEnvironment(hypothesizedEvaluationEnvironment);
		isContradiction = hypothesizedEvaluationEnvironment.isContradiction();
		symbolicAnalysis.popEvaluationEnvironment();
		if (isContradiction()) {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, getRefinedValue());
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

	public @NonNull TypedElement getTypedElement() {
		return typedElement;
	}

	public abstract @NonNull SymbolicValue getRefinedValue();

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
		s.append(typedElement);
		return s.toString();
	}

	public static class MayBeInvalidHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue mayBeInvalidValue;

		public MayBeInvalidHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue mayBeInvalidValue) {
			super(symbolicAnalysis, typedElement, symbolicAnalysis.getEvaluationEnvironment().getKnownValue(ValueUtil.INVALID_VALUE));
			this.mayBeInvalidValue = mayBeInvalidValue;
		}

		@Override
		public @NonNull SymbolicValue getRefinedValue() {
			return AbstractRefinedSymbolicValue.createNotInvalidValue(mayBeInvalidValue);
		}

		@Override
		public @Nullable Boolean mayBeInvalid() {
			return isContradiction();
		}
	}

	public static class MayBeNullHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue mayBeNullValue;

		public MayBeNullHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue mayBeNullValue) {
			super(symbolicAnalysis, typedElement, symbolicAnalysis.getEvaluationEnvironment().getKnownValue(null));
			this.mayBeNullValue = mayBeNullValue;
		}

		@Override
		public @NonNull SymbolicValue getRefinedValue() {
			return AbstractRefinedSymbolicValue.createNotNullValue(mayBeNullValue);
		}

		@Override
		public @Nullable Boolean mayBeNull() {
			return isContradiction();
		}
	}

	public static class MayBeZeroHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue mayBeZeroValue;

		public MayBeZeroHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue mayBeZeroValue) {
			super(symbolicAnalysis, typedElement, symbolicAnalysis.getEvaluationEnvironment().getKnownValue(ValueUtil.ZERO_VALUE));
			this.mayBeZeroValue = mayBeZeroValue;
		}

		@Override
		public @NonNull SymbolicValue getRefinedValue() {
			return AbstractRefinedSymbolicValue.createNotZeroValue(mayBeZeroValue);
		}

		@Override
		public @Nullable Boolean mayBeZero() {
			return isContradiction();
		}
	}
}