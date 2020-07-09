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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * A PreSerializer analyses a particular serialization rule to determine
 * - a multiplicity variable fro each term
 * - expressions relating the multiplicity variables to the number of elements of each feature
 * - the solutins for each variable as an equation involvinf the feature sizes.
 */
public class PreSerializer
{
	protected final @NonNull XtextParserRuleAnalysis parserRuleAnalysis;
	protected final @NonNull RequiredSlotsConjunction requiredSlotsConjunction;
	protected final @NonNull SerializationNode rootSerializationNode;
	protected final @Nullable SerializationNode parentSerializedNode;
	private final @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice;
	private final @NonNull Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent;
	private final @NonNull Map<@NonNull SerializationNode, /*@NonNull*/ CardinalityVariable> node2variable;		// XXX debugging @NonNull
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression;
	private final @NonNull List<@NonNull SerializationNode> serializationNodes;
	private @Nullable Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions = null;

	public PreSerializer(@NonNull XtextParserRuleAnalysis parserRuleAnalysis, @NonNull RequiredSlotsConjunction requiredSlotsConjunction, @NonNull SerializationNode rootSerializationNode) {
		this.parserRuleAnalysis = parserRuleAnalysis;
		this.requiredSlotsConjunction = requiredSlotsConjunction;
		this.rootSerializationNode = rootSerializationNode;
		this.parentSerializedNode = null;
		this.alternatives2choice = requiredSlotsConjunction.getAlternativesChoices();
		this.node2parent = new HashMap<>();
		this.node2variable = new HashMap<>();
		this.variable2node = new HashMap<>();
		this.feature2expression = new HashMap<>();
		this.serializationNodes = new ArrayList<>();
	}

	private PreSerializer(@NonNull PreSerializer preSerializer, @NonNull SequenceSerializationNode parentSerializedNode, @NonNull List<@NonNull SerializationNode> serializationNodes) {
		this.parserRuleAnalysis = preSerializer.parserRuleAnalysis;
		this.requiredSlotsConjunction = preSerializer.requiredSlotsConjunction;
		this.rootSerializationNode = preSerializer.rootSerializationNode;
		this.parentSerializedNode = parentSerializedNode;
		this.alternatives2choice = preSerializer.alternatives2choice;
		this.node2parent = preSerializer.node2parent;
		this.node2variable = preSerializer.node2variable;
		this.variable2node = preSerializer.variable2node;
		this.feature2expression = preSerializer.feature2expression;
		this.serializationNodes = serializationNodes;
	}

	public void addAssignedNode(@NonNull AssignedSerializationNode assignedSerializationNode) {
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
			assert node2variable.containsKey(serializationNode);		// XXX debugging
			CardinalityVariable cardinalityVariable = node2variable.get(serializationNode);
			if (cardinalityVariable != null) {
				variables.add(cardinalityVariable);
			}
		}
		cardinalityExpression.addMultiplicityProduct(variables);
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
		CardinalityVariable cardinalityVariable = new CardinalityVariable(name, multiplicativeCardinality);
		CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
		assert old2 == null;
		SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
		assert old3 == null;
	}

	public boolean addSolution(@NonNull CardinalityVariable variable, @NonNull Object solution) {
		Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions2 = variable2solutions;
		assert variable2solutions2 != null;
		Object oldSolutions = variable2solutions2.get(variable);
		if (oldSolutions instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<Object> castOldSolutions = (List<Object>)oldSolutions;
			if (castOldSolutions.contains(solution)) {
				return false;
			}
			castOldSolutions.add(solution);
			return true;
		}
		else if (oldSolutions == null) {
			variable2solutions2.put(variable, solution);
			return true;
		}
		else if (!oldSolutions.equals(solution)) {
			List<Object> newSolutions = new ArrayList<>();
			newSolutions.add(oldSolutions);
			newSolutions.add(solution);
			variable2solutions2.put(variable, newSolutions);
			return true;
		}
		else {
			return false;
		}
	}

	public @Nullable Map<@NonNull CardinalityVariable, @NonNull Integer> computeActualCardinalities(@NonNull EObject element, @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size) {
		Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions2 = variable2solutions;
		assert variable2solutions2 != null;
		Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = new HashMap<>();
		for (@NonNull CardinalityVariable cardinalityVariable : variable2solutions2.keySet()) {
			Object solution = variable2solutions2.get(cardinalityVariable);
			Integer integerSolution;
			if (solution instanceof Integer) {
				integerSolution = ((Integer)solution).intValue();
			}
			else if (solution instanceof CardinalityExpression.Solution) {
				integerSolution = ((CardinalityExpression.Solution)solution).solve(eFeature2size);
			}
			else {
				throw new UnsupportedOperationException();
			}
			variable2value.put(cardinalityVariable, integerSolution);
		}
		for (@NonNull EStructuralFeature eStructuralFeature : feature2expression.keySet()) {
			CardinalityExpression expression = feature2expression.get(eStructuralFeature);
			assert expression != null;
			int requiredCount = expression.solve(variable2value);
			Integer value = eFeature2size.get(eStructuralFeature);
			int actualCount = value != null ? value.intValue() : 0;
			if (requiredCount != actualCount) {
				return null;
			}
		}
		for (@NonNull EStructuralFeature eStructuralFeature : eFeature2size.keySet()) {
			if (!feature2expression.containsKey(eStructuralFeature)) {
				return null;
			}
		}
		return variable2value;
	}

	public @NonNull PreSerializer createNestedPreSerializer(@NonNull SequenceSerializationNode sequenceSerializationNode) {
		addChildNode(sequenceSerializationNode);
		List<@NonNull SerializationNode> nestedSerializedNodes = new ArrayList<>(); //nestedPreSerializer.getSerializedNodes();
		SequenceSerializationNode nestedSequenceSerializationNode = new SequenceSerializationNode(sequenceSerializationNode.grammarAnalysis, sequenceSerializationNode.group, nestedSerializedNodes);
		PreSerializer nestedPreSerializer = new PreSerializer(this, nestedSequenceSerializationNode, nestedSerializedNodes);
		addSerializedNode(nestedSequenceSerializationNode);			// XXX parent counted list
		return nestedPreSerializer;
	}

	public @Nullable SerializationNode getChosenNode(@NonNull AlternativesSerializationNode alternativesSerializationNode) {
		assert alternatives2choice != null;
		return alternatives2choice.get(alternativesSerializationNode);
	}

	public @NonNull List<@NonNull SerializationNode> getSerializedNodes() {
		return serializationNodes;
	}

	public @Nullable Object getSolution(@NonNull CardinalityVariable variable) {
		assert variable2solutions != null;
		return variable2solutions.get(variable);
	}

	public @NonNull CardinalityVariable getVariable(@NonNull SerializationNode serializationNode) {
		return ClassUtil.nonNullState(node2variable.get(serializationNode));
	}

	public void preSerialize() {
		//
		//	Traverse the chosen serialization tree path to trigger addAssignedNode/addSerializedNode call-backs to determine the
		//	cardinality variables and expressions to be solved to characterize the serialization.
		//
		rootSerializationNode.preSerialize(this);
		//
		//	Prepare to restructure the variables/expressions as solutions.
		//
		Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions2 = variable2solutions;
		assert variable2solutions2 == null;
		variable2solutions = variable2solutions2 = new HashMap<>();
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		//
		//	Assign the "1" solution to all one-only cardinality variables.
		//
		for (@NonNull CardinalityVariable variable : variables) {
			if (variable.isOne()) {
				variable2solutions2.put(variable, Integer.valueOf(1));
			}
		}
		int oldSize;
		//
		//	Eliminate expressions that involve no unresolved variables.
		//	assign 0/1 solutions for all optional cardinalitoes that are common factors to all other products.
		//		e.g. |F| = C01 + C01 * C02 can be solved as C02 = !F| -1 if C01 is optional.
		//
		do {
			oldSize = expressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression expression = expressions.get(i);
				if (expression.solveForNoVariables(this)) {
					expressions.remove(i);
				}
				else {
					expression.solveForBooleanCommonFactors(this);
				}
			}
		} while (expressions.size() < oldSize);
		//
		//	Assign 0/1 solutions for all variables involved in a linear equation in the light of other solutions.
		//
		do {
			oldSize = expressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression expression = expressions.get(i);
				if (expression.solveForConstants(this)) {
					expressions.remove(i);
				}
			}
		} while (expressions.size() < oldSize);
		//
		// Assign solutions to gratuitous grammar terms.
		// XXX need to encode residue for run-time resolution
		//
		for (@NonNull CardinalityVariable variable : variables) {
			if (!variable2solutions2.containsKey(variable)) {
				if (expressions.isEmpty()) {
					variable2solutions2.put(variable, variable.mayBeNone() ? 0 : 1);
				}
				else {
					variable2solutions2.put(variable, "?");
				}
			}
		}
	}

	@Override
	public @NonNull String toString() {
		@NonNull StringBuilder s = new StringBuilder();
		s.append(parserRuleAnalysis.getRuleName());
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\n");
		StringUtil.appendIndentation(s, depth+1, "\t");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.toString(s, depth+2);
		}
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {
			SerializationNode serializationNode = variable2node.get(variable);
			assert serializationNode != null;
		//	if (entry.getValue() != null) {
				s.append("\n");
				StringUtil.appendIndentation(s, depth, "\t");
				s.append(variable);
				s.append(": ");
				serializationNode.toString(s, -1);
		//	}
		}
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression expression : expressions) {
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			expression.toString(s, depth);
		}
		Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions2 = variable2solutions;
		if (variable2solutions2 != null) {
			for (@NonNull CardinalityVariable variable : variables) {	// XXX
				Object solutions = variable2solutions2.get(variable);
				s.append("\n");
				StringUtil.appendIndentation(s, depth, "\t");
				s.append(variable);
				s.append(" = ");
				if (solutions instanceof List) {
					boolean isFirst = true;
					for (Object solution : (List<?>)solutions) {
						if (!isFirst) {
							s.append(", ");
						}
						s.append(solution);
						isFirst = false;
					}
				}
				else {
					s.append(solutions);
				}
			}
		}
	}
}
