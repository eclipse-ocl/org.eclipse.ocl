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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

/**
 * A StaticRuleMatch accumulates the variables and expressions that determine the cardinalities of the various SerializationRule terms.
 */
public class RTStaticRuleMatch implements RuleMatch
{
	/**
	 * The rule for which this is the static analysis.
	 */
//	protected final @NonNull RTSerializationRule serializationRule;

	/**
	 * The per-feature expression that (re-)computes the required number of assigned slots from the solved
	 * cardinality variables. This is checked gainst the actual number of slots in an actual user element.
	 */
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression = new HashMap<>();

	/**
	 * The per-variable solution expression that computes the variable's value from the actual number of slots of an actual user element.
	 *
	 * Lazily populated as solutions found.
	 */
	protected final @NonNull Map<@NonNull Integer, @NonNull CardinalitySolution> variableIndex2solution = new HashMap<>();

	/**
	 * The ordered sequence of assign/check instructions to evaluate at run-time to realize the computation of
	 * each solution for its variable.
	 */
	protected final @NonNull List<@NonNull CardinalitySolutionStep> steps = new ArrayList<>();

//	public RTStaticRuleMatch(@NonNull RTSerializationRule serializationRule) {
//		this.serializationRule = serializationRule;
//	}

	/**
	 * Accumulate an additional cardinalitySolution expression for a cardinalityVariable.
	 */
	public void addSolution(@Nullable Integer cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
		CardinalitySolutionStep newStep;
		if (cardinalityVariable != null) {
		//	assert !cardinalityVariable.isOne();
			boolean isAssigned = true;
			for (@NonNull CardinalitySolutionStep step : steps) {
				if (step.isAssignTo(cardinalityVariable)) {
					isAssigned = false;
					break;
				}
			}
			if (isAssigned) {
				newStep = new CardinalitySolutionStep.Assign(cardinalityVariable, cardinalitySolution);
			//	variable2solution.put(cardinalityVariable, cardinalitySolution);
				variableIndex2solution.put(cardinalityVariable, cardinalitySolution);
			}
			else {
				newStep = new CardinalitySolutionStep.ValueCheck(cardinalityVariable, cardinalitySolution);
			}
		}
		else {
			newStep = new CardinalitySolutionStep.Assert(cardinalitySolution);
		}
		steps.add(newStep);
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(int cardinalityVariableIndex) {
		CardinalitySolution solution = variableIndex2solution.get(cardinalityVariableIndex);
		return solution != null ? solution.basicGetIntegerSolution(this) : null;
	}

	@Override
	public @Nullable CardinalitySolution basicGetSolution(int cardinalityVariableIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return variableIndex2solution.get(cardinalityVariable.getIndex());
	}

	public @NonNull SerializationRuleAnalysis getSerializationRule() {
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public @Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature) {
		return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EReference eReference, @NonNull ParserRuleValue parserRuleValue) {
		return null;
	}

	public @NonNull List<@NonNull CardinalitySolutionStep> getSteps() {
		return steps;
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		DynamicRuleMatch dynamicRuleMatch = slotsAnalysis.basicGetDynamicRuleMatch(this); // new DynamicRuleMatch(this, slotsAnalysis);
		if (dynamicRuleMatch == null) {
			dynamicRuleMatch = slotsAnalysis.createDynamicRuleMatch(this);
			if (!dynamicRuleMatch.analyze()) {
				return null;
			}
			//
			//	Evaluate the expressions to determine the required size of each slot.
			//
			for (@NonNull EStructuralFeature eStructuralFeature : feature2expression.keySet()) {
				CardinalityExpression expression = feature2expression.get(eStructuralFeature);
				assert expression != null;
				if (!expression.checkSize(dynamicRuleMatch)) {
					return null;
				}
			}
			//
			//	Check that no 'unused' features are used.
			//
			for (@NonNull EStructuralFeature eStructuralFeature : slotsAnalysis.getEStructuralFeatures()) {
				if (!feature2expression.containsKey(eStructuralFeature)) {
					UserSlotAnalysis object = slotsAnalysis.getSlotAnalysis(eStructuralFeature);
					if (!object.isCounted() || (object.asCounted() != 0)) {
						return null;
					}
				}
			}
			dynamicRuleMatch.setChecked();
		}
		else {
			if (!dynamicRuleMatch.isChecked()) {
				return null;
			}
		}
		return dynamicRuleMatch;
	}
}