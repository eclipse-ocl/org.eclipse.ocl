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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue.SymbolicRefinedContentValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class Hypothesis implements Comparable<@NonNull Hypothesis>
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;

	/**
	 * The value of the typedElement after assignment by the hypothesis.
	 */
	protected final @NonNull SymbolicValue hypothesizedValue;

	/**
	 * The TypedElements to which the hypothesis applies. Typically all of a CSEElement for the overall evaluation and just a single
	 * TypedElement for a hypothesis exploration.
	 */
	protected final @NonNull Iterable<@NonNull TypedElement> typedElements;

	/**
	 * The CSE of the typedElement.
	 */
	protected final @NonNull CSEElement cseElement;

	/**
	 * Whether the hypothesis is contradicted(true) or confirmed(false) or is yet to be decided (null).
	 */
	private @Nullable Map<@NonNull TypedElement, @Nullable String> typedElement2incompatibility = null;

	protected Hypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements, @NonNull SymbolicValue hypothesizedValue) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.hypothesizedValue = hypothesizedValue;
		this.typedElements = typedElements;
		this.cseElement = symbolicAnalysis.getCSEElement(typedElements.iterator().next());
		for (@NonNull TypedElement typedElement : typedElements) {
			assert cseElement == symbolicAnalysis.getCSEElement(typedElement);
		}
	}

	public void check() {
		Map<@NonNull TypedElement, @Nullable String> typedElement2incompatibility2 = typedElement2incompatibility;
		assert typedElement2incompatibility2 == null : "typedElement2incompatibility already determined for: " + this;
		typedElement2incompatibility = typedElement2incompatibility2 = new HashMap<>();
		for (@NonNull TypedElement typedElement : typedElements) {		// FIXME, can evaluate all in 'parallel'
			boolean traceHypothesis = SymbolicAnalysis.HYPOTHESIS.isActive();
			if (traceHypothesis) {
				SymbolicAnalysis.HYPOTHESIS.println("  " + getKind() + " hypothesis for: " + SymbolicUtil.printPath(typedElement));
				SymbolicAnalysis.HYPOTHESIS.println("    old: " + SymbolicUtil.printValue(getOriginalValue()));
				SymbolicAnalysis.HYPOTHESIS.println("    hypothesized: " + SymbolicUtil.printValue(hypothesizedValue));
			//	SymbolicAnalysis.HYPOTHESIS.println("    refined: " + getRefinedValue());		// XXX
			//	SymbolicAnalysis.HYPOTHESIS.println(this.toString());
			//	SymbolicAnalysis.HYPOTHESIS.println(this.toString());
			}
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = baseSymbolicEvaluationEnvironment.pushHypothesis(this, typedElement);
			String incompatibility = hypothesizedEvaluationEnvironment.isContradiction(typedElement);
			typedElement2incompatibility2.put(typedElement, incompatibility);
			baseSymbolicEvaluationEnvironment.popHypothesis();
			if (incompatibility != null) {
				SymbolicAnalysis.HYPOTHESIS.println("    => contradiction: " + incompatibility);
				refine(typedElement);
			}
			else if (traceHypothesis) {
				SymbolicAnalysis.HYPOTHESIS.println("    => no contradiction: " + SymbolicUtil.printPath(typedElement));
			}
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
		return System.identityHashCode(this) - System.identityHashCode(that);	// FIXME ?? breadth first ??
	}

	public @NonNull CSEElement getCSEElement() {
		return cseElement;
	}

	public @NonNull SymbolicValue getHypothesizedValue() {
		return hypothesizedValue;
	}

	public abstract @NonNull String getKind();

	protected @NonNull SymbolicValue getOriginalValue() {
		return symbolicAnalysis.getBaseSymbolicEvaluationEnvironment().getSymbolicValue(cseElement);
	}

	abstract protected @NonNull SymbolicValue getRefinedValue();

	protected abstract void refine(@NonNull TypedElement typedElement);

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	protected void toString(@NonNull StringBuilder s) {
		s.append(getKind());
		s.append(": ");
		s.append(getOriginalValue());
		s.append(": ");
		s.append(hypothesizedValue);
		for (@NonNull TypedElement typedElement : typedElements) {
			String incompatibility = typedElement2incompatibility != null ? typedElement2incompatibility.get(typedElement) : "indeterminate";
			s.append("\n\t");
			s.append(" ");
			s.append(typedElement);
			s.append(" ");
			s.append(incompatibility == null ? "undecided" : incompatibility);
		}
	}

	public static class MayBeEmptyHypothesis extends Hypothesis
	{
		private static @NonNull SymbolicValue createHypothesizedValue(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			CSEElement cseElement = symbolicAnalysis.getCSEElement(typedElements.iterator().next());
			SymbolicValue originalValue = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment().getSymbolicValue(cseElement);
			SymbolicRefinedContentValue refinedValue = null;
			SymbolicContent content = originalValue.getContent();
			SymbolicValue sizeValue = content.getSize();
			if (!sizeValue.isZero()) {
				refinedValue = new SymbolicRefinedContentValue(originalValue);
				sizeValue = symbolicAnalysis.getKnownValue(ValueUtil.ZERO_VALUE);
				refinedValue.setSize(sizeValue);
			}
			return refinedValue != null ? refinedValue : originalValue;
		}

		public MayBeEmptyHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, createHypothesizedValue(symbolicAnalysis, typedElements));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeEmpty";
		}

		@Override
		protected @NonNull SymbolicRefinedContentValue getRefinedValue() {
			SymbolicRefinedContentValue refinedValue = AbstractSymbolicRefinedValue.createRefinedContent(getOriginalValue());
			SymbolicValue refinedSize = AbstractSymbolicRefinedValue.createExceptValue(refinedValue.getSize(), ValueUtil.ZERO_VALUE);
			refinedValue.setSize(refinedSize);
			return refinedValue;
		}

		@Override
		protected void refine(@NonNull TypedElement typedElement) {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicRefinedContentValue refinedValue = getRefinedValue();
			if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
				SymbolicAnalysis.HYPOTHESIS.println("  refined: " + refinedValue);
			}
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}
	}

	public static class MayBeInvalidHypothesis extends Hypothesis
	{
		public MayBeInvalidHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, symbolicAnalysis.getKnownValue(ValueUtil.INVALID_VALUE));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeInvalid";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue() {
			return AbstractSymbolicRefinedValue.createExceptValue(getOriginalValue(), ValueUtil.INVALID_VALUE);
		}

		@Override
		protected void refine(@NonNull TypedElement typedElement) {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicValue refinedValue = getRefinedValue();
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}
	}

	public static class MayBeNullHypothesis extends Hypothesis
	{
		public MayBeNullHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, symbolicAnalysis.getKnownValue(null));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeNull";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue() {
			return AbstractSymbolicRefinedValue.createExceptValue(getOriginalValue(), null);
		}

		@Override
		protected void refine(@NonNull TypedElement typedElement) {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicValue refinedValue = getRefinedValue();
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}
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
	} */

	public static class MayBeZeroHypothesis extends Hypothesis
	{
		public MayBeZeroHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, symbolicAnalysis.getKnownValue(ValueUtil.ZERO_VALUE));
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeZero";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue() {
			return AbstractSymbolicRefinedValue.createExceptValue(getOriginalValue(), ValueUtil.ZERO_VALUE);
		}

		@Override
		protected void refine(@NonNull TypedElement typedElement) {
			BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
			SymbolicValue refinedValue = getRefinedValue();
			if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
				SymbolicAnalysis.HYPOTHESIS.println("  refined: " + refinedValue);
			}
			baseSymbolicEvaluationEnvironment.refineValue(typedElement, refinedValue);
		}
	}
}