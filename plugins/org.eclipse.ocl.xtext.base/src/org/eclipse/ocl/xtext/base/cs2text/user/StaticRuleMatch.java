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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.NullEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.AbstractCardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeCardinalityExpression0;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EReferenceCardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuntimeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.UnsupportedCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A StaticRuleMatch accumulates the varaibles and expressions that determine the cardinalities if the various SerializationRule terms.
 */
public class StaticRuleMatch implements RuleMatch
{
	protected final @NonNull BasicSerializationRule serializationRule;

	/**
	 * The expression that computes the number of assigned slots for each assigned fwature.
	 */
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull AbstractCardinalityExpression> feature2expression = new HashMap<>();

	/**
	 * The solution expression that computes the value of each cardinality variable.
	 */
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution = new HashMap<>();

	/**
	 * The ordered sequence of assign/check instictions to evaluate at run-time to realizze the computation of
	 * each solution for its variable.
	 */
	private final @NonNull List<@NonNull CardinalitySolutionResult> results = new ArrayList<>();

	public StaticRuleMatch(@NonNull BasicSerializationRule serializationRule) {
		this.serializationRule = serializationRule;
	}

	public @NonNull AbstractCardinalityExpression addAssignedNode(@NonNull AssignedSerializationNode assignedSerializationNode) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		AbstractCardinalityExpression cardinalityExpression = feature2expression.get(eStructuralFeature);
		if (cardinalityExpression == null) {
			String name = String.format("E%02d", feature2expression.size());
			assert name != null;;
			if (eStructuralFeature instanceof EAttribute) {
				cardinalityExpression = new EAttributeCardinalityExpression0(name, (EAttribute)eStructuralFeature, NullEnumerationValue.INSTANCE);
			}
			else {
				cardinalityExpression = new EReferenceCardinalityExpression(name, (EReference)eStructuralFeature, null);
			}
			feature2expression.put(eStructuralFeature, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	public void addSolution(@Nullable CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
		if (cardinalityVariable != null) {
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
		else {
			results.add(new CardinalitySolutionResult(null, cardinalitySolution, false));
		}
	}

	public void analyze() {
		//
		//	Prepare to restructure the variables/expressions as solutions.
		//
//		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
//		assert variable2solution2 == null;
//		variable2solution = variable2solution2 = new HashMap<>();
		List<@NonNull AbstractCardinalityExpression> residualExpressions = new ArrayList<>();
		for (@NonNull AbstractCardinalityExpression expression : feature2expression.values()) {
			Iterable<@NonNull AbstractCardinalityExpression> cardinalityExpressions = expression.getCardinalityExpressions();
			if (cardinalityExpressions != null) {
				for (@NonNull AbstractCardinalityExpression cardinalityExpression : cardinalityExpressions) {
					residualExpressions.add(cardinalityExpression);
				}
			}
			else {
				residualExpressions.add(expression);
			}
		}
		Collections.sort(residualExpressions, NameUtil.NAMEABLE_COMPARATOR);
		List<@NonNull CardinalityVariable> variables = Lists.newArrayList(serializationRule.getVariables());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		//
		//	Confirm that variables with a "1" solution were skipped.
		//
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
		}
		int oldSize;
		//
		//	Eliminate expressions that involve no unresolved variables or which provide a linear solution for a single variable.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				AbstractCardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeTrivial(this, false)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize);
		//
		//	Eliminate expressions that involve no unresolved variables.
		//	assign 0/1 solutions for all optional cardinalities that are common factors to all other products.
		//		e.g. |F| = C01 + C01 * C02 can be solved as C02 = !F| -1 if C01 is optional.
		//
		boolean gotOne;
		do {
			oldSize = residualExpressions.size();
			gotOne = false;
			for (int i = oldSize; --i >= 0; ) {
				AbstractCardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeMayBeZeroCommonFactors(this, false)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					AbstractCardinalityExpression residualExpression = residualExpressions.get(i);
					if (residualExpression.analyzeTrivial(this, false)) {
						residualExpressions.remove(i);
					}
				}
			}
		} while (gotOne || (residualExpressions.size() < oldSize));
		//
		//	Ditto, but consider mayBeMany for the case of gratuitous mayBeMany products.
		//
		do {
			oldSize = residualExpressions.size();
			gotOne = false;
			for (int i = oldSize; --i >= 0; ) {
				AbstractCardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					AbstractCardinalityExpression residualExpression = residualExpressions.get(i);
					if (residualExpression.analyzeTrivial(this, true)) {
						residualExpressions.remove(i);
					}
				}
			}
		} while (gotOne || (residualExpressions.size() < oldSize));
		/*
		//	Assign 0/1 solutions for all variables involved in a linear equation in the light of other solutions.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeTrivial(this)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize); */
		if (residualExpressions.size() > 0) {
			Map<@NonNull AbstractCardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables =
					computeExpression2unsolvedVariables(residualExpressions);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull AbstractCardinalityExpression>> unsolvedVariable2expressions =
					computeVariable2expressions(expression2unsolvedVariables);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unsolvedVariableGroups =
					computeUnsolvedVariableGroups(unsolvedVariable2expressions, expression2unsolvedVariables);

			for (@NonNull Set<@NonNull CardinalityVariable> unsolvedVariableGroup : new HashSet<>(unsolvedVariable2unsolvedVariableGroups.values())) {
				Iterable<@NonNull AbstractCardinalityExpression> unresolvedExpressions = computeExpressions(unsolvedVariableGroup, unsolvedVariable2expressions);
				int size = Iterables.size(unresolvedExpressions);
				if (size == 1) {
					AbstractCardinalityExpression residualExpression = unresolvedExpressions.iterator().next();
					/*if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
						// ok
					}
					else*/ if (residualExpression.analyzeRedundantProducts(this)) {
						// ok
					}
					else {
						RuntimeCardinalitySolution runtimeSolution = new RuntimeCardinalitySolution(unsolvedVariableGroup, unresolvedExpressions);
						for (@NonNull CardinalityVariable unresolvedVariable : unsolvedVariableGroup) {
							addSolution(unresolvedVariable, runtimeSolution);
						}
					}
				}
				else {
					//
					//	assign run-time search solution to remaining expressions.
					//
					RuntimeCardinalitySolution runtimeSolution = new RuntimeCardinalitySolution(unsolvedVariableGroup, unresolvedExpressions);
					for (@NonNull CardinalityVariable unresolvedVariable : unsolvedVariableGroup) {
						addSolution(unresolvedVariable, runtimeSolution);
					}
				}
			}
		}
		//
		// Assign solutions to gratuitous grammar terms.
		// XXX need to encode residue for run-time resolution
		//
		for (@NonNull CardinalityVariable variable : variables) {
			if (basicGetSolution(variable) == null) {
				if (residualExpressions.isEmpty()) {
					addSolution(variable, new IntegerCardinalitySolution(variable.mayBeNone() ? 0 : 1));
				}
				else {
					addSolution(variable, new UnsupportedCardinalitySolution());
				}
			}
		}
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		// throw new IllegalStateException();		// move to caller
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

	@Override
	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return variable2solution.get(cardinalityVariable);
	}

	/**
 * Return all the expressions that use any of variableGroup.
 */
protected @NonNull Iterable<@NonNull AbstractCardinalityExpression> computeExpressions(@NonNull Set<@NonNull CardinalityVariable> variableGroup,
		@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull AbstractCardinalityExpression>> variable2expressions) {
	Set<@NonNull AbstractCardinalityExpression> unresolvedExpressions = new HashSet<>();
	for (@NonNull CardinalityVariable variable : variableGroup) {
		Set<@NonNull AbstractCardinalityExpression> expressions = variable2expressions.get(variable);
		assert expressions != null;
		unresolvedExpressions.addAll(expressions);
	}
	return unresolvedExpressions;
}

	/**
	 * Return the Map from each of expressions to its variables that lack a solution.
	 */
	protected @NonNull Map<@NonNull AbstractCardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> computeExpression2unsolvedVariables(
			@NonNull Iterable<@NonNull AbstractCardinalityExpression> expressions) {
		Map<@NonNull AbstractCardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables = new HashMap<>();
		for (@NonNull AbstractCardinalityExpression expression : expressions) {
			Iterable<@NonNull CardinalityVariable> unsolvedVariables = expression.getUnknownVariables(this);
			if (unsolvedVariables != null) {
				for (@NonNull CardinalityVariable variable : unsolvedVariables) {
					Set<@NonNull CardinalityVariable> variables = expression2unsolvedVariables.get(expression);
					if (variables == null) {
						variables = new HashSet<>();
						expression2unsolvedVariables.put(expression, variables);
					}
					variables.add(variable);
				}
			}
		}
		return expression2unsolvedVariables;
	}

	/**
	 * Return a Map from each variable to the set of variables that participate in non-independent expressions.
	 */
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> computeUnsolvedVariableGroups(
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull AbstractCardinalityExpression>> unsolvedVariable2expressions,
			@NonNull Map<@NonNull AbstractCardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unresolvedVariables = new HashMap<>();
		List<@NonNull CardinalityVariable> workVariables = new ArrayList<>(unsolvedVariable2expressions.keySet());
		for (@NonNull CardinalityVariable workVariable : workVariables) {
			Set<@NonNull CardinalityVariable> unsolvedVariables = unsolvedVariable2unresolvedVariables.get(workVariable);
			if (unsolvedVariables == null) {
				unsolvedVariables = new HashSet<>();
				unsolvedVariable2unresolvedVariables.put(workVariable, unsolvedVariables);
				unsolvedVariables.add(workVariable);
				Set<@NonNull AbstractCardinalityExpression> expressions = unsolvedVariable2expressions.get(workVariable);
				assert expressions != null;
				for (@NonNull AbstractCardinalityExpression expression : expressions) {
					Set<@NonNull CardinalityVariable> moreUnsolvedVariables = expression2unsolvedVariables.get(expression);
					assert moreUnsolvedVariables != null;
					moreUnsolvedVariables.removeAll(unsolvedVariables);
					for (@NonNull CardinalityVariable anotherUnsolvedVariable : moreUnsolvedVariables) {
						unsolvedVariables.add(anotherUnsolvedVariable);
						unsolvedVariable2unresolvedVariables.put(anotherUnsolvedVariable, unsolvedVariables);
					}
				}
			}
		}
		return unsolvedVariable2unresolvedVariables;
	}

	/**
	 * Return the inverse of the Map from expressions to variables.
	 */
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull AbstractCardinalityExpression>> computeVariable2expressions(
			@NonNull Map<@NonNull AbstractCardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2variables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull AbstractCardinalityExpression>> variable2expressions = new HashMap<>();
		for (@NonNull AbstractCardinalityExpression expression : expression2variables.keySet()) {
			Set<@NonNull CardinalityVariable> variables = expression2variables.get(expression);
			assert variables != null;
			for (@NonNull CardinalityVariable variable : variables) {
				Set<@NonNull AbstractCardinalityExpression> expressions = variable2expressions.get(variable);
				if (expressions == null) {
					expressions = new HashSet<>();
					variable2expressions.put(variable, expressions);
				}
				expressions.add(expression);
			}
		}
		return variable2expressions;
	}

	public @NonNull Iterable<@NonNull CardinalitySolutionResult> getResults() {
		return results;
	}

	@Override
	public @NonNull BasicSerializationRule getSerializationRule() {
		return serializationRule;
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
	public @Nullable Integer getSize(@NonNull EReference eReference, @Nullable ParserRuleAnalysis ruleAnalysis) {
		return null;
	}

	public boolean needsDefault(@NonNull EStructuralFeature eStructuralFeature) {
		AbstractCardinalityExpression expression = feature2expression.get(eStructuralFeature);
		if (expression == null) {
			return false;
		}
		return expression.isOne();
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		DynamicRuleMatch dynamicRuleMatch = new DynamicRuleMatch(this, slotsAnalysis);
		if (!dynamicRuleMatch.analyze()) {
			return null;
		}
		//
		//	Evaluate the expressions to determine the required size of each slot.
		//
		for (@NonNull EStructuralFeature eStructuralFeature : feature2expression.keySet()) {
			AbstractCardinalityExpression expression = feature2expression.get(eStructuralFeature);
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
		return dynamicRuleMatch;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(serializationRule.getName());
		s.append(" : ");
		serializationRule.toRuleString(s);
		toString(s, 1);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		List<@NonNull AbstractCardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull AbstractCardinalityExpression expression : expressions) {
			StringUtil.appendIndentation(s, depth, "  ");
			s.append("- ");
			expression.toString(s, depth);
		}
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2solution.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (CardinalitySolutionResult result : results) {
			StringUtil.appendIndentation(s, depth, "  ");
			s.append("- ");
			result.toString(s, depth);
		}
	//	for (@NonNull CardinalitySolutionResult result : results) {
	//		StringUtil.appendIndentation(s, depth, "  ");
	//		result.toString(s, 1);
	//s	}
	}
}