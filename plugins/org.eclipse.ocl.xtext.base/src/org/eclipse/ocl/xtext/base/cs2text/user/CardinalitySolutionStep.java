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
package org.eclipse.ocl.xtext.base.cs2text.user;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

/**
 * A CardinalitySolutionStep specifies a run-time action as part of the cardinality variable drtermination.
 * An expression may be assigned to or checked against some variable
 */
public abstract class CardinalitySolutionStep
{
	/**
	 * An Assert step requires a given expression to be zero-valued to allow the invoking DynamicRuleMatch to succeed.
	 */
	public static class Assert extends CardinalitySolutionStep
	{
		protected final @NonNull CardinalitySolution cardinalitySolution;

		public Assert(@NonNull CardinalitySolution cardinalitySolution) {
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof Assert)) {
				return false;
			}
			Assert that = (Assert)obj;
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

		public @NonNull CardinalitySolution getCardinalitySolution() {
			return cardinalitySolution;
		}

		@Override
		public @NonNull Set<@NonNull CardinalitySolution> getSolutionClosure() {
			return cardinalitySolution.getChildClosure();
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + 5 * cardinalitySolution.hashCode();
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
	public static class Assign extends CardinalitySolutionStep
	{
		protected final int cardinalityVariableIndex;
		protected final @NonNull CardinalitySolution cardinalitySolution;

		public Assign(int cardinalityVariableIndex, @NonNull CardinalitySolution cardinalitySolution) {
			this.cardinalityVariableIndex = cardinalityVariableIndex;
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof Assign)) {
				return false;
			}
			Assign that = (Assign)obj;
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

		public @NonNull CardinalitySolution getCardinalitySolution() {
			return cardinalitySolution;
		}

		@Override
		public @NonNull Set<@NonNull CardinalitySolution> getSolutionClosure() {
			return cardinalitySolution.getChildClosure();
		}

		public int getVariableIndex() {
			return cardinalityVariableIndex;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + 5 * cardinalitySolution.hashCode() + 7 * cardinalityVariableIndex;
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
	public static class RuleCheck extends CardinalitySolutionStep
	{
		protected final @NonNull EReference eReference;
//		protected final @NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses;
		protected final @NonNull ParserRuleAnalysis @NonNull [] ruleAnalyses;

		public RuleCheck(@NonNull EReference eReference, @NonNull Collection<@NonNull ParserRuleAnalysis> ruleAnalyses) {
			this(eReference, ruleAnalyses.toArray(new @NonNull ParserRuleAnalysis @NonNull [ruleAnalyses.size()]));
		}

		public RuleCheck(@NonNull EReference eReference, @NonNull ParserRuleAnalysis @NonNull [] ruleAnalyses) {
			this.eReference = eReference;
			this.ruleAnalyses = ruleAnalyses;
			assert ruleAnalyses.length >= 1;
			assert eReference.isContainment();
		//	if ("ownedType".equals(eReference.getName())) {
		//		getClass();			// XXX debugging
		//	}
		}

		public RuleCheck(@NonNull EReference eReference, @NonNull String @NonNull [] ruleAnalyses) {
			this.eReference = eReference;
			this.ruleAnalyses = new ParserRuleAnalysis[] {};
		//	assert ruleAnalyses.length >= 1;
		//	assert eReference.isContainment();
		//	if ("ownedType".equals(eReference.getName())) {
		//		getClass();			// XXX debugging
		//	}
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof RuleCheck)) {
				return false;
			}
			RuleCheck that = (RuleCheck)obj;
			return (this.eReference == that.eReference)
				&& this.ruleAnalyses.equals(that.ruleAnalyses);
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
			EObject eObject = slotsAnalysis.getEObject();
			if ("ownedRight".equals(eReference.getName()) && "PrefixExpCS".equals(eObject.eClass().getName())) {
				getClass();		// XXX debugging
			}
			if (!eReference.getEContainingClass().isInstance(eObject)) {
				return false;
			}
			Object slotContent = eObject.eGet(eReference);
			if (eReference.isMany()) {
				for (Object element : (List<?>)slotContent) {
					if (!isInstance(slotsAnalysis, (@NonNull EObject)element)) {
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

		public @NonNull ParserRuleAnalysis  [] getRuleAnalyses() {
			return ruleAnalyses;
		}

		@Override
		public @NonNull Set<@NonNull CardinalitySolution> getSolutionClosure() {
			return Collections.emptySet();
		}

		@Override
		public int hashCode() {
			int hashCode = getClass().hashCode() + 5 * eReference.hashCode();
			for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
				hashCode += 7 * ruleAnalysis.hashCode();
			}
			return hashCode;
		}

		protected boolean isInstance(@NonNull UserSlotsAnalysis slotsAnalysis, @NonNull EObject slotContent) {
			UserElementAnalysis elementAnalysis = slotsAnalysis.getModelAnalysis().getElementAnalysis(slotContent);
			for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
				DynamicRuleMatch dynamicRuleMatch = elementAnalysis.createDynamicRuleMatch(ruleAnalysis.getRuleValue());
				if (dynamicRuleMatch != null) {
					return true;
				}
			}
			for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {		// XXX debugging
				DynamicRuleMatch dynamicRuleMatch = elementAnalysis.createDynamicRuleMatch(ruleAnalysis.getRuleValue());
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
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(ruleAnalysis.getName());
				isFirst = false;
			}
		}
	}

	/**
	 * A TypeCheck step checks that a slot value conforms to a type required by a rule assignment on behalf of the invoking DynamicRuleMatch=.
	 *
	public static class TypeCheck extends CardinalitySolutionStep
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull Iterable<@NonNull EClass> eClasses;

		public TypeCheck(@NonNull EReference eReference, @NonNull Iterable<@NonNull EClass> eClasses) {
			this.eReference = eReference;
			this.eClasses = eClasses;
			assert Iterables.size(eClasses) >= 1;
			if ("ownedType".equals(eReference.getName())) {
				getClass();			// XXX debugging
			}
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
	public static class ValueCheck extends CardinalitySolutionStep
	{
		protected final int cardinalityVariableIndex;
		protected final @NonNull CardinalitySolution cardinalitySolution;

		public ValueCheck(int cardinalityVariableIndex, @NonNull CardinalitySolution cardinalitySolution) {
			this.cardinalityVariableIndex = cardinalityVariableIndex;
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof ValueCheck)) {
				return false;
			}
			ValueCheck that = (ValueCheck)obj;
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

		public @NonNull CardinalitySolution getCardinalitySolution() {
			return cardinalitySolution;
		}

		@Override
		public @NonNull Set<@NonNull CardinalitySolution> getSolutionClosure() {
			return cardinalitySolution.getChildClosure();
		}

		public int getVariableIndex() {
			return cardinalityVariableIndex;
		}

		@Override
		public int hashCode() {
			return getClass().hashCode() + 5 * cardinalitySolution.hashCode() + 7 * cardinalityVariableIndex;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("check-value V");
			s.append(cardinalityVariableIndex);
			s.append(" = ");
			s.append(cardinalitySolution);
		}
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
	public abstract @NonNull Set<@NonNull CardinalitySolution> getSolutionClosure();

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