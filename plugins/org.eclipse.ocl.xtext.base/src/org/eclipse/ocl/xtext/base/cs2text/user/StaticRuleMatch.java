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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

/**
 * A DynamicRuleMatch accumulates the solutions to the expressions that constrain the cardinalitoes of the terms of
 * a SerializationRule..
 */
public class StaticRuleMatch implements RuleMatch
{
	protected final @NonNull BasicSerializationRule serializationRule;
	private @NonNull Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution = new HashMap<>();
	private @NonNull List<@NonNull CardinalitySolutionResult> results = new ArrayList<>();

	public StaticRuleMatch(@NonNull BasicSerializationRule serializationRule) {
		this.serializationRule = serializationRule;
	}

	public void addSolution(@NonNull CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
		boolean isAssigned = true;
		for (@NonNull CardinalitySolutionResult result : results) {
			if (result.getCardinalityVariable() == cardinalityVariable) {
				isAssigned = false;
				break;
			}
		}
		results.add(new CardinalitySolutionResult(cardinalityVariable, cardinalitySolution, isAssigned));
		if (isAssigned) {
			variable2solution.put(cardinalityVariable, cardinalitySolution);
		}
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		CardinalitySolution solution = variable2solution.get(cardinalityVariable);
		return solution != null ? solution.basicGetIntegerSolution(this) : null;
/*		if (solution instanceof IntegerCardinalitySolution) {
			return ((IntegerCardinalitySolution)solution).getValue();
		}
		if (solution instanceof BooleanCommonFactorCardinalitySolution) {
			return 1;
		}
		if (solution instanceof Iterable) {
			for (Object solutionElement : ((Iterable<?>)solution) ) {
				if (solutionElement instanceof Integer) {
					return ((Integer)solutionElement).intValue();
				}
				if (solutionElement instanceof BooleanCommonFactorCardinalitySolution) {
					return 1;
				}
			}
		}
		return null; */
	}

	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return variable2solution.get(cardinalityVariable);
	}

	public @NonNull Iterable<@NonNull CardinalitySolutionResult> getResults() {
		return results;
	}

	public @NonNull BasicSerializationRule getSerializationRule() {
		return serializationRule;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature,@NonNull EnumerationValue enumerationValue) {
		return null;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(serializationRule.getName());
		toString(s, 1);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2solution.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {	// XXX
			CardinalitySolution solution = variable2solution.get(variable);
			StringUtil.appendIndentation(s, depth, "  ");
			s.append("- ");
			s.append(variable);
			s.append(" = ");
			s.append(solution);
		}
		for (@NonNull CardinalitySolutionResult result : results) {
			StringUtil.appendIndentation(s, depth, "  ");
			result.toString(s, 1);
		}
	}
}