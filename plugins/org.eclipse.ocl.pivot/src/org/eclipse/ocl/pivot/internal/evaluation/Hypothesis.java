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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue.SymbolicRefinedContentValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class Hypothesis implements Comparable<@NonNull Hypothesis>
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;

	/**
	 * The TypedElement whose for which a value is being hypothesis.
	 */
	protected final @NonNull TypedElement typedElement;

	/**
	 * The value of the typedElement prior to assignment by the hypothesis.
	 */
	protected final @NonNull SymbolicValue originalValue;

	/**
	 * The value of the typedElement after assignment by the hypothesis.
	 */
	protected final @NonNull SymbolicValue hypothesizedValue;

	/**
	 * The CSE of the typedElement.
	 */
	protected final @NonNull CSEElement cseElement;

	/**
	 * Whether the hypothesis is conformed(false), contradicted(true) or is yet to be decided (null).
	 */
	private @Nullable Boolean isContradiction = null;

	protected Hypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue originalValue, @NonNull SymbolicValue hypothesizedValue) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.typedElement = typedElement;
		this.originalValue = originalValue;
		this.hypothesizedValue = hypothesizedValue;
		this.cseElement = symbolicAnalysis.getCSEElement(typedElement);
	}

	public void check() {
		assert isContradiction == null : "isContradiction already determined for: " + this;
		boolean traceHypothesis = SymbolicAnalysis.HYPOTHESIS.isActive();
		if (traceHypothesis) {
			SymbolicAnalysis.HYPOTHESIS.println("  " + getKind() + " hypothesis for \"" + typedElement + "\" in \"" + typedElement.eContainer() + "\"");
			SymbolicAnalysis.HYPOTHESIS.println("    old: " + originalValue);
			SymbolicAnalysis.HYPOTHESIS.println("    hypothesized: " + hypothesizedValue);
		//	SymbolicAnalysis.HYPOTHESIS.println("    refined: " + getRefinedValue());		// XXX
		//	SymbolicAnalysis.HYPOTHESIS.println(this.toString());
		//	SymbolicAnalysis.HYPOTHESIS.println(this.toString());
		}
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = symbolicAnalysis.createHypothesizedSymbolicEvaluationEnvironment(this);
		symbolicAnalysis.pushEvaluationEnvironment(hypothesizedEvaluationEnvironment);
		isContradiction = hypothesizedEvaluationEnvironment.isContradiction();
		symbolicAnalysis.popEvaluationEnvironment();
		if (isContradiction()) {
			refine();
		//	SymbolicReEvaluationEnvironment refinedEvaluationEnvironment = symbolicAnalysis.createSymbolicReEvaluationEnvironment(this);
		//	refinedEvaluationEnvironment.putRefinedValue(expression, getRefinedValue());
		//	symbolicAnalysis.pushEvaluationEnvironment(refinedEvaluationEnvironment);
		//	refinedEvaluationEnvironment.install();
		//	symbolicAnalysis.popEvaluationEnvironment();
		}
		else if (traceHypothesis) {
			SymbolicAnalysis.HYPOTHESIS.println("No contradiction " + this);
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

	public @NonNull SymbolicValue getHypothesizedValue() {
		return hypothesizedValue;
	}

	public abstract @NonNull String getKind();

	public @NonNull SymbolicValue getOriginalValue() {
		return originalValue;
	}

	abstract protected @NonNull SymbolicValue getRefinedValue();

	public @NonNull TypedElement getTypedElement() {
		return typedElement;
	}

	public boolean isContradiction() {
		return ClassUtil.nonNullState(isContradiction).booleanValue();
	}

	public @Nullable Boolean mayBeEmpty() {
		return null;
	}

	public @Nullable Boolean mayBeInvalid() {
		return null;
	}

	public @Nullable Boolean mayBeNull() {
		return null;
	}

	public @Nullable Boolean mayBeSmallerThan(int minSize) {
		return null;
	}

	public @Nullable Boolean mayBeZero() {
		return null;
	}

	protected abstract void refine();

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	protected void toString(@NonNull StringBuilder s) {
		s.append(typedElement);
		s.append(" ");
		s.append(getKind());
		s.append(": ");
		s.append(originalValue);
		s.append(" ");
		s.append(isContradiction == null ? "undecided" : (isContradiction == Boolean.TRUE) ? "confirmed" :  "contradicted");
		s.append(": ");
		s.append(hypothesizedValue);
	}

	public static class MayBeEmptyHypothesis extends Hypothesis
	{
		protected static @NonNull SymbolicValue zeroValue(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypeId typeId, @NonNull SymbolicValue originalValue) {
		//	if (typeId instanceof CollectionTypeId) {
		//		return AbstractRefinedSymbolicValue.createZeroValue(originalValue);
		//	}
		//	else if (typeId instanceof MapTypeId) {
		//		return AbstractRefinedSymbolicValue.createZeroValue(originalValue);
		//	}
			return symbolicAnalysis.getSymbolicEvaluationEnvironment().getKnownValue(ValueUtil.ZERO_VALUE);
		}

		private static @NonNull SymbolicValue createHypothesizedValue(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull SymbolicValue originalValue) {
			SymbolicRefinedContentValue refinedValue = null;
			SymbolicContent content = originalValue.getContent();
			SymbolicValue sizeValue = content.getSize();
			if (!sizeValue.isZero()) {
				if (refinedValue == null) {
					refinedValue = new SymbolicRefinedContentValue(originalValue);
					sizeValue = symbolicAnalysis.getSymbolicEvaluationEnvironment().getKnownValue(ValueUtil.ZERO_VALUE);
					refinedValue.setSize(sizeValue);
//					refinedValue.toString();
				}
			}
			return refinedValue != null ? refinedValue : originalValue;
		}

		public MayBeEmptyHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue originalValue) {
			super(symbolicAnalysis, typedElement, originalValue, createHypothesizedValue(symbolicAnalysis, originalValue));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeEmpty";
		}

		@Override
		protected @NonNull SymbolicRefinedContentValue getRefinedValue() {
			SymbolicRefinedContentValue refinedValue = AbstractSymbolicRefinedValue.createRefinedContent(originalValue);
			SymbolicValue refinedSize = AbstractSymbolicRefinedValue.createExceptValue(refinedValue.getSize(), ValueUtil.ZERO_VALUE);
			refinedValue.setSize(refinedSize);
			return refinedValue;
		}

		@Override
		public @Nullable Boolean mayBeEmpty() {
			return isContradiction();
		}

		@Override
		protected void refine() {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicRefinedContentValue refinedValue = getRefinedValue();
			if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
				SymbolicAnalysis.HYPOTHESIS.println("  refined: " + refinedValue);
			}
		//	SymbolicValue refinedValue = getRefinedValue();
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}

	//	@Override
	//	protected void toString(@NonNull StringBuilder s) {
	//		super.toString(s);
	//		s.append(" => ");
	//		s.append(mayBeZeroValue);
	//	}
	}

	public static class MayBeInvalidHypothesis extends Hypothesis
	{
		public MayBeInvalidHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue originalValue) {
			super(symbolicAnalysis, typedElement, originalValue, symbolicAnalysis.getSymbolicEvaluationEnvironment().getKnownValue(ValueUtil.INVALID_VALUE));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeInvalid";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue() {
			return AbstractSymbolicRefinedValue.createExceptValue(originalValue, ValueUtil.INVALID_VALUE);
		}

		@Override
		public @Nullable Boolean mayBeInvalid() {
			return isContradiction();
		}

		@Override
		protected void refine() {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicValue refinedValue = getRefinedValue();
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}

	//	@Override
	//	protected void toString(@NonNull StringBuilder s) {
	//		super.toString(s);
	//		s.append(" => ");
	//		s.append(mayBeInvalidValue);
	//	}
	}

	public static class MayBeNullHypothesis extends Hypothesis
	{
		public MayBeNullHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue originalValue) {
			super(symbolicAnalysis, typedElement, originalValue, symbolicAnalysis.getSymbolicEvaluationEnvironment().getKnownValue(null));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeNull";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue() {
			return AbstractSymbolicRefinedValue.createExceptValue(originalValue, null);
		}

		@Override
		public @Nullable Boolean mayBeNull() {
			return isContradiction();
		}

		@Override
		protected void refine() {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicValue refinedValue = getRefinedValue();
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}

	//	@Override
	//	protected void toString(@NonNull StringBuilder s) {
	//		super.toString(s);
	//		s.append(" => ");
	//		s.append(mayBeNullValue);
	//	}
	}

/*	public static class MayBeSmallerThanHypothesis extends Hypothesis
	{
		protected final @NonNull SymbolicValue minSizeValue;

		public MayBeSmallerThanHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue originalValue, @NonNull SymbolicValue minSizeValue) {
			super(symbolicAnalysis, typedElement, originalValue, AbstractRefinedSymbolicValue.createSmallerThanValue(originalValue, minSizeValue));
			this.minSizeValue = minSizeValue;
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeSmallerThan(" + minSizeValue + ")";
		}

		@Override
		public @NonNull SymbolicValue getRefinedValue() {
			return AbstractRefinedSymbolicValue.createNotSmallerThanValue(originalValue, minSizeValue);
		}

		@Override
		public @Nullable Boolean mayBeSmallerThan(int minSize) {
			return isContradiction();
		}

	//	@Override
	//	protected void toString(@NonNull StringBuilder s) {
	//		super.toString(s);
	//		s.append(" => ");
	//		s.append(mayBeSmallerThanValue);
	//	}
	} */

	public static class MayBeZeroHypothesis extends Hypothesis
	{
		protected static @NonNull SymbolicValue zeroValue(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypeId typeId, @NonNull SymbolicValue originalValue) {
		//	if (typeId instanceof CollectionTypeId) {
		//		return AbstractRefinedSymbolicValue.createZeroValue(originalValue);
		//	}
		//	else if (typeId instanceof MapTypeId) {
		//		return AbstractRefinedSymbolicValue.createZeroValue(originalValue);
		//	}
			return symbolicAnalysis.getSymbolicEvaluationEnvironment().getKnownValue(ValueUtil.ZERO_VALUE);
		}

		public MayBeZeroHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement typedElement, @NonNull SymbolicValue originalValue) {
			super(symbolicAnalysis, typedElement, originalValue, zeroValue(symbolicAnalysis, typedElement.getTypeId(), originalValue));
		}
		@Override
		public @NonNull String getKind() {
			return "mayBeZero";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue() {
			return AbstractSymbolicRefinedValue.createExceptValue(originalValue, ValueUtil.ZERO_VALUE);
		}

		@Override
		public @Nullable Boolean mayBeZero() {
			return isContradiction();
		}

		@Override
		protected void refine() {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicValue refinedValue = getRefinedValue();
			if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
				SymbolicAnalysis.HYPOTHESIS.println("  refined: " + refinedValue);
			}
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}

	//	@Override
	//	protected void toString(@NonNull StringBuilder s) {
	//		super.toString(s);
	//		s.append(" => ");
	//		s.append(mayBeZeroValue);
	//	}
	}
}