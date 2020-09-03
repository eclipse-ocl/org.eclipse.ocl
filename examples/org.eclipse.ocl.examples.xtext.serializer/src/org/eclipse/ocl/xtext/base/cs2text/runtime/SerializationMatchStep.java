/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A CardinalitySolutionStep specifies a run-time action as part of the cardinality variable drtermination.
 * An expression may be assigned to or checked against some variable
 */
public abstract class SerializationMatchStep
{
	/**
	 * An Assert step requires a given expression to be zero-valued to allow the invoking DynamicRuleMatch to succeed.
	 */
	public static class MatchStep_Assert extends SerializationMatchStep
	{
		protected final @NonNull SerializationMatchTerm cardinalitySolution;

		public MatchStep_Assert(@NonNull SerializationMatchTerm cardinalitySolution) {
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * cardinalitySolution.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof MatchStep_Assert)) {
				return false;
			}
			MatchStep_Assert that = (MatchStep_Assert)obj;
			return this.cardinalitySolution.equals(that.cardinalitySolution);
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			return newIntegerSolution.equals(0);
		}

		public @NonNull SerializationMatchTerm getCardinalitySolution() {
			return cardinalitySolution;
		}

		@Override
		public @NonNull Set<@NonNull SerializationMatchTerm> getSolutionClosure() {
			return cardinalitySolution.getChildClosure();
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("assert ");
			s.append(cardinalitySolution);
			s.append(" == 0");
		}
	}

	/**
	 * An Assign step computes the value of a variable on behalf of the invoking DynamicRuleMatch.
	 */
	public static class MatchStep_Assign extends SerializationMatchStep
	{
		protected final int cardinalityVariableIndex;
		protected final @NonNull SerializationMatchTerm cardinalitySolution;

		public MatchStep_Assign(int cardinalityVariableIndex, @NonNull SerializationMatchTerm cardinalitySolution) {
			this.cardinalityVariableIndex = cardinalityVariableIndex;
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * cardinalitySolution.hashCode() + 7 * cardinalityVariableIndex;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof MatchStep_Assign)) {
				return false;
			}
			MatchStep_Assign that = (MatchStep_Assign)obj;
			return this.cardinalitySolution.equals(that.cardinalitySolution)
				&& (this.cardinalityVariableIndex == that.cardinalityVariableIndex);
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			assert cardinalityVariableIndex >= 0;
			dynamicRuleMatch.putValue(cardinalityVariableIndex, newIntegerSolution);
			return true;
		}

		public @NonNull SerializationMatchTerm getCardinalitySolution() {
			return cardinalitySolution;
		}

		@Override
		public @NonNull Set<@NonNull SerializationMatchTerm> getSolutionClosure() {
			return cardinalitySolution.getChildClosure();
		}

		public int getVariableIndex() {
			return cardinalityVariableIndex;
		}

		@Override
		public boolean isAssignTo(int cardinalityVariableIndex) {
			return this.cardinalityVariableIndex == cardinalityVariableIndex;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("assign V");
			s.append(cardinalityVariableIndex);
			s.append(" = ");
			s.append(cardinalitySolution);
		}
	}

	/**
	 * A RuleCheck step checks that a slot value conforms to a rule required by a rule assignment on behalf of the invoking DynamicRuleMatch=.
	 */
	public static class MatchStep_RuleCheck extends SerializationMatchStep
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull GrammarRuleVector ruleValueIndexes;

		public MatchStep_RuleCheck(/*@NonNull*/ EReference eReference, @NonNull GrammarRuleVector ruleValueIndexes) {
			assert eReference != null;
			this.eReference = eReference;
			this.ruleValueIndexes = ruleValueIndexes;
			assert eReference.isContainment();
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * eReference.hashCode() + 7 * ruleValueIndexes.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof MatchStep_RuleCheck)) {
				return false;
			}
			MatchStep_RuleCheck that = (MatchStep_RuleCheck)obj;
			return (this.eReference == that.eReference)
				&& this.ruleValueIndexes.equals(that.ruleValueIndexes);
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
			EObject eObject = slotsAnalysis.getEObject();
			if (!eReference.getEContainingClass().isInstance(eObject)) {
				return false;
			}
			Object slotContent = eObject.eGet(eReference);
			if (eReference.isMany()) {
				for (Object element : (List<?>)slotContent) {
					@SuppressWarnings("null")
					@NonNull EObject castElement = (@NonNull EObject)element;
					if (!isInstance(slotsAnalysis, castElement)) {
						return false;
					}
				}
			}
			else if (slotContent != null) {
				if (!isInstance(slotsAnalysis, (EObject)slotContent)) {
					return false;
				}
			}
			else {}				// Null is never actually serialized, */
			return true;
		}

		public @NonNull EReference getEReference() {
			return eReference;
		}

		public @NonNull GrammarRuleVector getRuleValueIndexes() {
			return ruleValueIndexes;
		}

		@Override
		public @NonNull Set<@NonNull SerializationMatchTerm> getSolutionClosure() {
			return Collections.emptySet();
		}

		protected boolean isInstance(@NonNull UserSlotsAnalysis slotsAnalysis, @NonNull EObject slotContent) {
			UserElementAnalysis elementAnalysis = slotsAnalysis.getModelAnalysis().getElementAnalysis(slotContent);
			for (@NonNull Integer ruleValueIndex : ruleValueIndexes) {
				DynamicRuleMatch dynamicRuleMatch = elementAnalysis.createDynamicRuleMatch(ruleValueIndex);
				if (dynamicRuleMatch != null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("check-rule ");
			s.append(eReference.getEContainingClass().getEPackage().getName());
			s.append("::");
			s.append(eReference.getEContainingClass().getName());
			s.append(".");
			s.append(eReference.getName());
			s.append(" : ");
			boolean isFirst = true;
			for (@NonNull Integer ruleValueIndex : ruleValueIndexes) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(ruleValueIndex);
//				s.append(ruleValue.getName());
				isFirst = false;
			}
		}
	}

	/**
	 * A TypeCheck step checks that a slot value conforms to a type required by a rule assignment on behalf of the invoking DynamicRuleMatch=.
	 *
	public static class MatchStep_TypeCheck extends CardinalitySolutionStep
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull Iterable<@NonNull EClass> eClasses;

		public MatchStep_TypeCheck(@NonNull EReference eReference, @NonNull Iterable<@NonNull EClass> eClasses) {
			this.eReference = eReference;
			this.eClasses = eClasses;
			assert Iterables.size(eClasses) >= 1;
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
			EObject eObject = slotsAnalysis.getEObject();
			if (!eReference.getEContainingClass().isInstance(eObject)) {
				return false;
			}
			Object slotContent = eObject.eGet(eReference);
			if (eReference.isMany()) {
				for (Object element : (List<?>)slotContent) {
					if (!isInstance(element)) {
						return false;
					}
				}
			}
			else if (slotContent != null) {
				if (!isInstance(slotContent)) {
					return false;
				}
			}
			else {}				// Null is never actually serialized,
			return true;
		}

		protected boolean isInstance(Object slotContent) {
			for (@NonNull EClass eClass : eClasses) {
				if (eClass.isInstance(slotContent)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("check-type ");
			s.append(eReference.getEContainingClass().getEPackage().getName());
			s.append("::");
			s.append(eReference.getEContainingClass().getName());
			s.append(".");
			s.append(eReference.getName());
			s.append(" : ");
			boolean isFirst = true;
			for (@NonNull EClass eClass : eClasses) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(eClass.getEPackage().getName());
				s.append("::");
				s.append(eClass.getName());
				isFirst = false;
			}
		}
	} */

	/**
	 * A ValueCheck step re-computes the value of a variable on behalf of the invoking DynamicRuleMatch and requires it to be
	 * consistent with the previous computation.
	 */
	public static class MatchStep_ValueCheck extends SerializationMatchStep
	{
		protected final int cardinalityVariableIndex;
		protected final @NonNull SerializationMatchTerm cardinalitySolution;

		public MatchStep_ValueCheck(int cardinalityVariableIndex, @NonNull SerializationMatchTerm cardinalitySolution) {
			this.cardinalityVariableIndex = cardinalityVariableIndex;
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * cardinalitySolution.hashCode() + 7 * cardinalityVariableIndex;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof MatchStep_ValueCheck)) {
				return false;
			}
			MatchStep_ValueCheck that = (MatchStep_ValueCheck)obj;
			return this.cardinalitySolution.equals(that.cardinalitySolution)
				&& (this.cardinalityVariableIndex == that.cardinalityVariableIndex);
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			Integer integer = dynamicRuleMatch.getValue(cardinalityVariableIndex);
			return newIntegerSolution.equals(integer);
		}

		public @NonNull SerializationMatchTerm getCardinalitySolution() {
			return cardinalitySolution;
		}

		@Override
		public @NonNull Set<@NonNull SerializationMatchTerm> getSolutionClosure() {
			return cardinalitySolution.getChildClosure();
		}

		public int getVariableIndex() {
			return cardinalityVariableIndex;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("check-value V");
			s.append(cardinalityVariableIndex);
			s.append(" = ");
			s.append(cardinalitySolution);
		}
	}

	private @Nullable Integer hashCode = null;

	protected int computeHashCode() {
		return getClass().hashCode();
	}

	/**
	 * Execute this step to contribute to the dermination of a successful dynamicRuleMatch.
	 *
	 * Returns true if the executi  is successful, false if the dynamicRuleMatch is to fail.
	 */
	public abstract boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch);

	/**
	 * Return all solutions to be evaluated.
	 */
	public abstract @NonNull Set<@NonNull SerializationMatchTerm> getSolutionClosure();

	@Override
	public final int hashCode() {
		Integer hashCode2 = hashCode;
		if (hashCode2 == null) {
			hashCode = hashCode2 = computeHashCode();
		}
		return hashCode2.intValue();
	}

	/**
	 * Return true if this is an assignment step to cardinalityVariable.
	 */
	public boolean isAssignTo(int variableIndex) {
		return false;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public abstract void toString(@NonNull StringBuilder s, int depth);
}