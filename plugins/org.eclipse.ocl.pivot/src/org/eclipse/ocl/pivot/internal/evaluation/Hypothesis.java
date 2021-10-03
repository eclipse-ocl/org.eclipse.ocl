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
 * A Hypothesis identifies a hypothesizedValue to be independently assessed for each of the typed elements sharing a cse element,
 * and, if that assessment leads to a contradiction, provides a refined value for the typed element.
 *
 * Derived Hypothesis support alternative hypothesized and subsequent refined values.
 *
 * @since 1.17
 */
public abstract class Hypothesis
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;
	protected final @NonNull BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment;

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
		this.baseSymbolicEvaluationEnvironment = symbolicAnalysis.getBaseSymbolicEvaluationEnvironment();
		this.hypothesizedValue = hypothesizedValue;
		this.typedElements = typedElements;
		this.cseElement = symbolicAnalysis.getCSEElement(typedElements.iterator().next());
		for (@NonNull TypedElement typedElement : typedElements) {
			assert cseElement == symbolicAnalysis.getCSEElement(typedElement);
		}
	}

	public void analyze() {
		Map<@NonNull TypedElement, @Nullable String> typedElement2incompatibility2 = typedElement2incompatibility;
		assert typedElement2incompatibility2 == null : "typedElement2incompatibility already determined for: " + this;
		typedElement2incompatibility = typedElement2incompatibility2 = new HashMap<>();
		boolean traceHypothesis = SymbolicAnalysis.HYPOTHESIS.isActive();
		//
		// It is tempting to perform the sub-hypotheses together since each TypedElement has the same hypothesizedvalue, but
		// each has a distinct ancestral contol path and so may have distinct contradictions.
		//
		for (@NonNull TypedElement typedElement : typedElements) {
			SymbolicValue oldSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(typedElement);
			if (traceHypothesis) {
				SymbolicAnalysis.HYPOTHESIS.println("  " + getKind() + " hypothesis for: " + SymbolicUtil.printPath(typedElement, false));
				SymbolicAnalysis.HYPOTHESIS.println("    old: " + SymbolicUtil.printPath(typedElement, false) + " was: " + SymbolicUtil.printValue(oldSymbolicValue));	// Show TypedElement value
			}
			if (cannotBeSatisfiedBy(oldSymbolicValue)) {
				if (traceHypothesis) {
					SymbolicAnalysis.HYPOTHESIS.println("    => already contradicted");
				}
			}
			else {
				String incompatibility = baseSymbolicEvaluationEnvironment.hypothesize(this, typedElement);
				if (incompatibility != null) {
					if (traceHypothesis) {
						SymbolicAnalysis.HYPOTHESIS.println("    => contradiction: " + incompatibility);
					}
					SymbolicValue unrefinedValue = oldSymbolicValue;
					SymbolicValue refinedValue = getRefinedValue(unrefinedValue);
					baseSymbolicEvaluationEnvironment.refineSymbolicValue(typedElement, refinedValue);
					assert cannotBeSatisfiedBy(refinedValue);
				}
				else if (traceHypothesis) {
					SymbolicAnalysis.HYPOTHESIS.println("    => no contradiction: " + SymbolicUtil.printPath(typedElement, false));
				}
				typedElement2incompatibility2.put(typedElement, incompatibility);
			}
		}
	}

	/**
	 * Return true if the hypothesis is incompatible with a safe navigation.
	 */
	public boolean cannotBeSafe() {
		return false;
	}

	/**
	 * Return true if symbolicValue cannot be refined to satisfy the hypothesis.
	 */
	protected abstract boolean cannotBeSatisfiedBy(@NonNull SymbolicValue symbolicValue);

	public @NonNull CSEElement getCSEElement() {
		return cseElement;
	}

	public @NonNull SymbolicValue getHypothesizedValue() {
		return hypothesizedValue;
	}

	public abstract @NonNull String getKind();

	abstract protected @NonNull SymbolicValue getRefinedValue(@NonNull SymbolicValue unrefinedValue);

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	protected void toString(@NonNull StringBuilder s) {
		s.append(getKind());
		s.append(": explore ");
	//	s.append(getOriginalValue());
	//	s.append(": ");
		s.append(hypothesizedValue);
		for (@NonNull TypedElement typedElement : typedElements) {
			String incompatibility = typedElement2incompatibility != null ? typedElement2incompatibility.get(typedElement) : "indeterminate";
			s.append("\n\t");
			s.append(" ");
			s.append(typedElement);
			s.append(": ");
			s.append(baseSymbolicEvaluationEnvironment.getSymbolicValue(typedElement));
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
		protected boolean cannotBeSatisfiedBy(@NonNull SymbolicValue symbolicValue) {
			return !symbolicValue.getContent().mayBeEmpty();
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeEmpty";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue(@NonNull SymbolicValue unrefinedValue) {
			SymbolicRefinedContentValue refinedValue = AbstractSymbolicRefinedValue.createRefinedContent(unrefinedValue);
			SymbolicValue refinedSize = AbstractSymbolicRefinedValue.createExceptValue(refinedValue.getSize(), ValueUtil.ZERO_VALUE);
			refinedValue.setSize(refinedSize);
			return refinedValue;
		}
	}

	public static class MayBeInvalidHypothesis extends Hypothesis
	{
		public MayBeInvalidHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, symbolicAnalysis.getKnownValue(ValueUtil.INVALID_VALUE));
		}

		@Override
		public boolean cannotBeSafe() {
			return true;
		}

		@Override
		protected boolean cannotBeSatisfiedBy(@NonNull SymbolicValue symbolicValue) {
			return !symbolicValue.mayBeInvalid();
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeInvalid";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue(@NonNull SymbolicValue unrefinedValue) {
			return AbstractSymbolicRefinedValue.createExceptValue(unrefinedValue, ValueUtil.INVALID_VALUE);
		}
	}

	public static class MayBeNullHypothesis extends Hypothesis
	{
		public MayBeNullHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, symbolicAnalysis.getKnownValue(null));
		}

		@Override
		public boolean cannotBeSafe() {
			return true;
		}

		@Override
		protected boolean cannotBeSatisfiedBy(@NonNull SymbolicValue symbolicValue) {
			return !symbolicValue.mayBeNull();
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeNull";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue(@NonNull SymbolicValue unrefinedValue) {
			return AbstractSymbolicRefinedValue.createExceptValue(unrefinedValue, null);
		}
	}

	public static class MayBeZeroHypothesis extends Hypothesis
	{
		public MayBeZeroHypothesis(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull Iterable<@NonNull TypedElement> typedElements) {
			super(symbolicAnalysis, typedElements, symbolicAnalysis.getKnownValue(ValueUtil.ZERO_VALUE));
		}

		@Override
		protected boolean cannotBeSatisfiedBy(@NonNull SymbolicValue symbolicValue) {
			return !symbolicValue.mayBeZero();
		}

		@Override
		public @NonNull String getKind() {
			return "mayBeZero";
		}

		@Override
		protected @NonNull SymbolicValue getRefinedValue(@NonNull SymbolicValue unrefinedValue) {
			return AbstractSymbolicRefinedValue.createExceptValue(unrefinedValue, ValueUtil.ZERO_VALUE);
		}
	}
}