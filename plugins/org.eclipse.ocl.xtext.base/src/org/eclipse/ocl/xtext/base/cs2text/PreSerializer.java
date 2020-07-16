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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.CardinalityExpression.ValueCardinalityExpression;

import com.google.common.collect.Iterables;

/**
 * A PreSerializer analyses a particular serialization rule to determine
 * - a multiplicity variable fro each term
 * - expressions relating the multiplicity variables to the number of elements of each feature
 * - the solutins for each variable as an equation involvinf the feature sizes.
 */
public class PreSerializer
{
	public static final @NonNull Integer ZERO = Integer.valueOf(0);

	protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;
	protected final @NonNull SerializationRule serializationRule;
	protected final @NonNull SerializationNode rootSerializationNode;
	protected final @Nullable SerializationNode parentSerializedNode;
	private final @NonNull Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent;
	private final @NonNull Map<@NonNull SerializationNode, /*@NonNull*/ CardinalityVariable> node2variable;		// XXX debugging @NonNull
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression;
	private final @NonNull List<@NonNull SerializationNode> serializationNodes;
	private @Nullable Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution = null;

	public PreSerializer(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull SerializationRule serializationRule, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.serializationRule = serializationRule;
		this.rootSerializationNode = rootSerializationNode;
		this.parentSerializedNode = null;
		this.node2parent = new HashMap<>();
		this.node2variable = new HashMap<>();
		this.variable2node = new HashMap<>();
		this.feature2expression = new HashMap<>();
		this.serializationNodes = new ArrayList<>();
	}

	private PreSerializer(@NonNull PreSerializer preSerializer, @NonNull SequenceSerializationNode parentSerializedNode, @NonNull List<@NonNull SerializationNode> serializationNodes) {
		this.ruleAnalysis = preSerializer.ruleAnalysis;
		this.serializationRule = preSerializer.serializationRule;
		this.rootSerializationNode = preSerializer.rootSerializationNode;
		this.parentSerializedNode = parentSerializedNode;
		this.node2parent = preSerializer.node2parent;
		this.node2variable = preSerializer.node2variable;
		this.variable2node = preSerializer.variable2node;
		this.feature2expression = preSerializer.feature2expression;
		this.serializationNodes = serializationNodes;
	}

	public void addAssignedNode(@NonNull AssignedSerializationNode assignedSerializationNode, @Nullable Object valueOrValues) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		CardinalityExpression cardinalityExpression = feature2expression.get(eStructuralFeature);
		if (cardinalityExpression == null) {
			String name = String.format("E%02d", feature2expression.size());
			assert name != null;;
			cardinalityExpression = new CardinalityExpression(name, eStructuralFeature);
			feature2expression.put(eStructuralFeature, cardinalityExpression);
		}
		List<@NonNull CardinalityVariable> variables = new ArrayList<>();
		for (SerializationNode serializationNode = assignedSerializationNode; serializationNode != null; serializationNode = node2parent.get(serializationNode)) {
			assert node2parent.containsKey(serializationNode);		// XXX debugging
		//	assert node2variable.containsKey(serializationNode);		// XXX debugging
			CardinalityVariable cardinalityVariable = node2variable.get(serializationNode);
			if (cardinalityVariable != null) {
				variables.add(0, cardinalityVariable);
			}
		}
		if (valueOrValues != null) {
			ValueCardinalityExpression valueCardinalityExpression = cardinalityExpression.getValueCardinalityExpression(ruleAnalysis.getGrammarAnalysis(), valueOrValues);
			valueCardinalityExpression.addMultiplicityProduct(variables);
		}
		else {
			cardinalityExpression.addMultiplicityProduct(variables);
		}
	}

	private void addChildNode(@NonNull SerializationNode serializationNode) {
		assert serializationNode != parentSerializedNode;
		assert !node2parent.containsKey(serializationNode);
		SerializationNode old = node2parent.put(serializationNode, parentSerializedNode);
		assert old == null;
	}

	public void addSerializedNode(@NonNull SerializationNode serializationNode) {
		serializationNodes.add(serializationNode);
		addChildNode(serializationNode);
		MultiplicativeCardinality multiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		String name = String.format("C%02d", variable2node.size());
		assert name != null;
		if (!multiplicativeCardinality.isOne()) {
			CardinalityVariable cardinalityVariable = new CardinalityVariable(name, multiplicativeCardinality);
			CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
			assert old2 == null;
			SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
			assert old3 == null;
		}
	/*	MultiplicativeCardinality subMultiplicativeCardinality = multiplicativeCardinality.mayBeMany() ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ZERO_OR_ONE;
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			for (@NonNull String value : ((AlternativeAssignedKeywordsSerializationNode)serializationNode).getValueOrValues()) {
				String name2 = String.format("C%02d", variable2node.size());
				assert name2 != null;
				CardinalityVariable cardinalitySubVariable = new CardinalityVariable(name2, subMultiplicativeCardinality);
				SerializationNode old4 = variable2node.put(cardinalitySubVariable, serializationNode);
				assert old4 == null;
			}
		} */
	}

	public void addSolution(@NonNull CardinalityVariable variable, @NonNull CardinalitySolution solution) {
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		assert variable2solution2 != null;
		CardinalitySolution oldSolution = variable2solution2.get(variable);
		CardinalitySolution netSolution;
		if (oldSolution == null) {
			netSolution = solution;
		}
		else {
			netSolution = oldSolution.addSolution(solution);
		}
		variable2solution2.put(variable, netSolution);
	/*	if (oldSolutions instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<Object> castOldSolutions = (List<Object>)oldSolutions;
			if (castOldSolutions.contains(solution)) {
				return false;
			}
			castOldSolutions.add(solution);
			return true;
		}
		else if (oldSolutions == null) {
			variable2solution2.put(variable, solution);
			return true;
		}
		else if (!oldSolutions.equals(solution)) {
			List<Object> newSolutions = new ArrayList<>();
			newSolutions.add(oldSolutions);
			newSolutions.add(solution);
			variable2solution2.put(variable, newSolutions);
			return true;
		}
		else {
			return false;
		} */
	}

	public @Nullable Map<@NonNull CardinalityVariable, @NonNull Integer> computeActualCardinalities(@NonNull EObject element, @NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		assert variable2solution2 != null;
		Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = new HashMap<>();
		for (@NonNull CardinalityVariable cardinalityVariable : variable2solution2.keySet()) {
			CardinalitySolution solution = variable2solution2.get(cardinalityVariable);
			assert solution != null;
			Integer integerSolution = solution.getIntegerSolution(eFeature2contentAnalysis);
			if (integerSolution == null) {
				throw new UnsupportedOperationException();
			}
			variable2value.put(cardinalityVariable, integerSolution);
		}
		for (@NonNull EStructuralFeature eStructuralFeature : feature2expression.keySet()) {
			CardinalityExpression expression = feature2expression.get(eStructuralFeature);
			assert expression != null;
			int requiredCount = expression.solve(variable2value);
			int actualCount = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, expression.getValue());
			if (requiredCount != actualCount) {
				return null;
			}
		}
		for (@NonNull EStructuralFeature eStructuralFeature : eFeature2contentAnalysis.keySet()) {
			if (!feature2expression.containsKey(eStructuralFeature)) {
				Object object = eFeature2contentAnalysis.get(eStructuralFeature);
				assert object != null;
				if (!object.equals(ZERO)) {
					return null;
				}
			}
		}
		return variable2value;
	}

	/**
	 * Return the Map from each of expressions to its variables that lack a solution.
	 */
	protected @NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> computeExpression2unsolvedVariables(
			@NonNull Iterable<@NonNull CardinalityExpression> expressions) {
		Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expressions) {
			Iterable<@NonNull CardinalityVariable> unsolvedVariables = expression.getUnsolvedVariables(this);
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
	 * Return all the expressions that use any of variableGroup.
	 */
	protected @NonNull Iterable<@NonNull CardinalityExpression> computeExpressions(@NonNull Set<@NonNull CardinalityVariable> variableGroup,
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions) {
		Set<@NonNull CardinalityExpression> unresolvedExpressions = new HashSet<>();
		for (@NonNull CardinalityVariable variable : variableGroup) {
			Set<@NonNull CardinalityExpression> expressions = variable2expressions.get(variable);
			assert expressions != null;
			unresolvedExpressions.addAll(expressions);
		}
		return unresolvedExpressions;
	}

	/**
	 * Return a Map from each variable to the set of variables that participate in non-independent expressions.
	 */
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> computeUnsolvedVariableGroups(
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions,
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unresolvedVariables = new HashMap<>();
		List<@NonNull CardinalityVariable> workVariables = new ArrayList<>(unsolvedVariable2expressions.keySet());
		for (@NonNull CardinalityVariable workVariable : workVariables) {
			Set<@NonNull CardinalityVariable> unsolvedVariables = unsolvedVariable2unresolvedVariables.get(workVariable);
			if (unsolvedVariables == null) {
				unsolvedVariables = new HashSet<>();
				unsolvedVariable2unresolvedVariables.put(workVariable, unsolvedVariables);
				unsolvedVariables.add(workVariable);
				Set<@NonNull CardinalityExpression> expressions = unsolvedVariable2expressions.get(workVariable);
				assert expressions != null;
				for (@NonNull CardinalityExpression expression : expressions) {
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
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> computeVariable2expressions(
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2variables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expression2variables.keySet()) {
			Set<@NonNull CardinalityVariable> variables = expression2variables.get(expression);
			assert variables != null;
			for (@NonNull CardinalityVariable variable : variables) {
				Set<@NonNull CardinalityExpression> expressions = variable2expressions.get(variable);
				if (expressions == null) {
					expressions = new HashSet<>();
					variable2expressions.put(variable, expressions);
				}
				expressions.add(expression);
			}
		}
		return variable2expressions;
	}

	public @NonNull PreSerializer createNestedPreSerializer(@NonNull SequenceSerializationNode sequenceSerializationNode) {
		addChildNode(sequenceSerializationNode);
		List<@NonNull SerializationNode> nestedSerializedNodes = new ArrayList<>(); //nestedPreSerializer.getSerializedNodes();
		SequenceSerializationNode nestedSequenceSerializationNode = new SequenceSerializationNode(sequenceSerializationNode.getRuleAnalysis(), sequenceSerializationNode.compoundElement, sequenceSerializationNode.getMultiplicativeCardinality(), nestedSerializedNodes);
		PreSerializer nestedPreSerializer = new PreSerializer(this, nestedSequenceSerializationNode, nestedSerializedNodes);
		addSerializedNode(nestedSequenceSerializationNode);			// XXX parent counted list
		return nestedPreSerializer;
	}

	public @NonNull List<@NonNull SerializationNode> getSerializedNodes() {
		return serializationNodes;
	}

	public @Nullable CardinalitySolution getSolution(@NonNull CardinalityVariable variable) {
		assert variable2solution != null;
		return variable2solution.get(variable);
	}

	public @NonNull CardinalityVariable getVariable(@NonNull SerializationNode serializationNode) {
		return ClassUtil.nonNullState(node2variable.get(serializationNode));
	}

	public void preSerialize() {
		if ("OCLinEcore::ReferenceCS".equals(ruleAnalysis.getName())) {
			getClass();	// XXX debugging
		}
		//
		//	Traverse the chosen serialization tree path to trigger addAssignedNode/addSerializedNode call-backs to determine the
		//	cardinality variables and expressions to be solved to characterize the serialization.
		//
		rootSerializationNode.preSerialize(this);
		//
		//	Prepare to restructure the variables/expressions as solutions.
		//
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		assert variable2solution2 == null;
		variable2solution = variable2solution2 = new HashMap<>();
		List<@NonNull CardinalityExpression> residualExpressions = new ArrayList<>();
		for (@NonNull CardinalityExpression expression : feature2expression.values()) {
			Iterable<@NonNull ValueCardinalityExpression> valueCardinalityExpressions = expression.getValueCardinalityExpressions();
			if (valueCardinalityExpressions != null) {
				for (@NonNull CardinalityExpression valueCardinalityExpression : valueCardinalityExpressions) {
					residualExpressions.add(valueCardinalityExpression);
				}
			}
			else {
				residualExpressions.add(expression);
			}
		}
		Collections.sort(residualExpressions, NameUtil.NAMEABLE_COMPARATOR);
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		//
		//	Assign the "1" solution to all one-only cardinality variables.
		//
		for (@NonNull CardinalityVariable variable : variables) {
			if (variable.isOne()) {
				variable2solution2.put(variable, new IntegerCardinalitySolution(1));
			}
		}
		int oldSize;
		//
		//	Eliminate expressions that involve no unresolved variables.
		//	assign 0/1 solutions for all optional cardinalitoes that are common factors to all other products.
		//		e.g. |F| = C01 + C01 * C02 can be solved as C02 = !F| -1 if C01 is optional.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.solveForNoVariables(this)) {
					residualExpressions.remove(i);
				}
				else {
					residualExpression.solveForBooleanCommonFactors(this);
				}
			}
		} while (residualExpressions.size() < oldSize);
		//
		//	Assign 0/1 solutions for all variables involved in a linear equation in the light of other solutions.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.solveForConstants(this)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize);
		if (residualExpressions.size() > 0) {
			Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables =
					computeExpression2unsolvedVariables(residualExpressions);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions =
					computeVariable2expressions(expression2unsolvedVariables);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unsolvedVariableGroups =
					computeUnsolvedVariableGroups(unsolvedVariable2expressions, expression2unsolvedVariables);

			for (@NonNull Set<@NonNull CardinalityVariable> unsolvedVariableGroup : new HashSet<>(unsolvedVariable2unsolvedVariableGroups.values())) {
				Iterable<@NonNull CardinalityExpression> unresolvedExpressions = computeExpressions(unsolvedVariableGroup, unsolvedVariable2expressions);
				int size = Iterables.size(unresolvedExpressions);
				if (size == 1) {
					CardinalityExpression residualExpression = unresolvedExpressions.iterator().next();
					if (residualExpression.solveForPseudoBooleanFactors(this)) {
						// ok
					}
					else if (residualExpression.solveForRedundantProducts(this)) {
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
			if (!variable2solution2.containsKey(variable)) {
				if (residualExpressions.isEmpty()) {
					variable2solution2.put(variable, new IntegerCardinalitySolution(variable.mayBeNone() ? 0 : 1));
				}
				else {
					variable2solution2.put(variable, new UnsupportedCardinalitySolution());
				}
			}
		}
	}

	public void toRuleString(@NonNull StringBuilder s) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.toString(s, -1);
		}
	}

	@Override
	public @NonNull String toString() {
		@NonNull StringBuilder s = new StringBuilder();
		s.append(ruleAnalysis.getRuleName());
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.toString(s, depth);
		}
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {
			SerializationNode serializationNode = variable2node.get(variable);
			assert serializationNode != null;
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("- ");
			s.append(variable);
			s.append(": ");
			serializationNode.toString(s, -1);
		}
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression expression : expressions) {
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("- ");
			expression.toString(s, depth);
		}
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		if (variable2solution2 != null) {
			for (@NonNull CardinalityVariable variable : variables) {	// XXX
				CardinalitySolution solution = variable2solution2.get(variable);
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("- ");
				s.append(variable);
				s.append(" = ");
				s.append(solution);
			}
		}
	}
}
