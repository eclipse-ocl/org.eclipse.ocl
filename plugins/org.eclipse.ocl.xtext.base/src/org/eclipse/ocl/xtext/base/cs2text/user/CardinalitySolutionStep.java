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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis.UserSlotAnalysis;

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
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			return newIntegerSolution.equals(0);
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
		protected final @NonNull CardinalityVariable cardinalityVariable;
		protected final @NonNull CardinalitySolution cardinalitySolution;

		public Assign(@NonNull CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
			this.cardinalityVariable = cardinalityVariable;
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			assert cardinalityVariable != null;
			dynamicRuleMatch.putValue(cardinalityVariable, newIntegerSolution);
			return true;
		}

		@Override
		public boolean isAssignTo(@NonNull CardinalityVariable cardinalityVariable) {
			return this.cardinalityVariable == cardinalityVariable;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("assign ");
			s.append(cardinalityVariable);
			s.append(" = ");
			s.append(cardinalitySolution);
		}
	}

	/**
	 * A ValueCheck step re-computes the value of a variable on behalf of the invoking DynamicRuleMatch and requires it to be#
	 * consistent with the previous computation.
	 */
	public static class TypeCheck extends CardinalitySolutionStep
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;

		public TypeCheck(@NonNull EStructuralFeature eStructuralFeature) {
			this.eStructuralFeature = eStructuralFeature;
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserSlotAnalysis slotAnalysis = dynamicRuleMatch.getSlotsAnalysis().getSlotAnalysis(eStructuralFeature);
			return true;


		/*	Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			Integer integer = dynamicRuleMatch.getValue(cardinalityVariable);
			return newIntegerSolution.equals(integer); */
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("check ");
			s.append(eStructuralFeature.getEContainingClass().getEPackage().getName());
			s.append("::");
			s.append(eStructuralFeature.getEContainingClass().getName());
			s.append(".");
			s.append(eStructuralFeature.getName());
		}
	}

	/**
	 * A ValueCheck step re-computes the value of a variable on behalf of the invoking DynamicRuleMatch and requires it to be#
	 * consistent with the previous computation.
	 */
	public static class ValueCheck extends CardinalitySolutionStep
	{
		protected final @NonNull CardinalityVariable cardinalityVariable;
		protected final @NonNull CardinalitySolution cardinalitySolution;

		public ValueCheck(@NonNull CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
			this.cardinalityVariable = cardinalityVariable;
			this.cardinalitySolution = cardinalitySolution;
		}

		@Override
		public boolean execute(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			Integer newIntegerSolution = cardinalitySolution.basicGetIntegerSolution(dynamicRuleMatch);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			Integer integer = dynamicRuleMatch.getValue(cardinalityVariable);
			return newIntegerSolution.equals(integer);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("check ");
			s.append(cardinalityVariable);
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
	 * Return true if this is an assignment step to cardinalityVariable.
	 */
	public boolean isAssignTo(@NonNull CardinalityVariable cardinalityVariable) {
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