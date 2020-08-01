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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

/**
 * A DynamicRuleMatch accumulates the results of augmenting the static match of a particular SerializationRule
 * with the actual analysis of the slots of a user model element.
 */
public class DynamicRuleMatch implements RuleMatch
{
	protected final @NonNull StaticRuleMatch staticRuleMatch;
	protected final @NonNull UserSlotsAnalysis slotsAnalysis;
	protected final @NonNull Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = new HashMap<>();

	public DynamicRuleMatch(@NonNull StaticRuleMatch staticRuleMatch, @NonNull UserSlotsAnalysis slotsAnalysis) {
		this.staticRuleMatch = staticRuleMatch;
		this.slotsAnalysis = slotsAnalysis;
	}

	/**
	 * Analyze the actual slots to compute the value of each cardinality variable.
	 */
	public boolean analyze() {
		for (@NonNull CardinalitySolutionResult result : staticRuleMatch.getResults()) {
			CardinalityVariable cardinalityVariable = result.getCardinalityVariable();
			CardinalitySolution solution = result.getCardinalitySolution();
			Integer newIntegerSolution = solution.basicGetIntegerSolution(this);
			if (newIntegerSolution == null) {
				// throw new UnsupportedOperationException();
				return false;
			}
			if (result.isAssigned()) {
				assert cardinalityVariable != null;
				variable2value.put(cardinalityVariable, newIntegerSolution);
			}
			else if (cardinalityVariable != null) {
				Integer integer = variable2value.get(cardinalityVariable);
				if (!newIntegerSolution.equals(integer)) {
					return false;
				}
			}
			else {
				if (!newIntegerSolution.equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return variable2value.get(cardinalityVariable);
	}

	@Override
	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable) {
		throw new IllegalStateException();		// run-time shoild use known values
	}

	public @NonNull Integer getIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return ClassUtil.nonNullState(variable2value.get(cardinalityVariable));
	}

	@Override
	public @NonNull BasicSerializationRule getSerializationRule() {
		return staticRuleMatch.getSerializationRule();
	}

	@Override
	public @NonNull Integer getSize(@NonNull EStructuralFeature eStructuralFeature) {
		return slotsAnalysis.getSize(eStructuralFeature);
	}

	@Override
	public @NonNull Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		return slotsAnalysis.getSize(eAttribute, enumerationValue);
	}

	@Override
	public @NonNull Integer getSize(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		return slotsAnalysis.getSize(eReference, ruleAnalysis);
	}

	public @NonNull UserSlotsAnalysis getSlotsAnalysis() {
		return slotsAnalysis;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		slotsAnalysis.toString(s, depth);
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2value.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {
			Integer value = variable2value.get(variable);
			StringUtil.appendIndentation(s, depth, "  ");
			s.append("- ");
			s.append(variable.getName());
			s.append(" = ");
			s.append(value);
		}
	}
}