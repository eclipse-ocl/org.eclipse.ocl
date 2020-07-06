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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

public class PreSerializer
{
	protected final @Nullable SerializationNode parentSerializedNode;
	private final @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice;
	private final @NonNull Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent;
	private final @NonNull Map<@NonNull SerializationNode, /*@NonNull*/ CardinalityVariable> node2variable;		// XXX debugging @NonNull
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression;
	private final @NonNull List<@NonNull SerializationNode> serializedNodes = new ArrayList<>();
	private @Nullable Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions = null;

	public PreSerializer(@Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice) {
		this.parentSerializedNode = null;
		this.alternatives2choice = alternatives2choice;
		this.node2parent = new HashMap<>();
		this.node2variable = new HashMap<>();
		this.variable2node = new HashMap<>();
		this.feature2expression = new HashMap<>();
	}

	private PreSerializer(@NonNull PreSerializer preSerializer, @NonNull SequenceSerializationNode parentSerializedNode) {
		this.parentSerializedNode = parentSerializedNode;
		this.alternatives2choice = preSerializer.alternatives2choice;
		this.node2parent = preSerializer.node2parent;
		this.node2variable = preSerializer.node2variable;
		this.variable2node = preSerializer.variable2node;
		this.feature2expression = preSerializer.feature2expression;
	}

	public void addAssignedNode(@NonNull AssignedSerializationNode assignedSerializationNode) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		CardinalityExpression cardinalityExpression = feature2expression.get(eStructuralFeature);
		if (cardinalityExpression == null) {
			String string = "E" + feature2expression.size();
			cardinalityExpression = new CardinalityExpression(string, eStructuralFeature);
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

	public void addSerializedNode(@NonNull SerializationNode serializationNode) {
		serializedNodes.add(serializationNode);
		addSerializedNode2(serializationNode);
	}

	public void addSerializedNode1(@NonNull SerializationNode serializationNode) {
		assert serializationNode != parentSerializedNode;			// XXX
		assert !node2parent.containsKey(serializationNode);
		SerializationNode old1 = node2parent.put(serializationNode, parentSerializedNode);
	}

	public void addSerializedNode2(@NonNull SerializationNode serializationNode) {
		addSerializedNode1(serializationNode);
//		assert old1 == null;
//		serializedNodes.add(serializationNode);
		MultiplicativeCardinality multiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
//		if (!multiplicativeCardinality.isOne()) {
			String name = "V" + variable2node.size();
			CardinalityVariable cardinalityVariable = new CardinalityVariable(name, multiplicativeCardinality);
			CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
			assert old2 == null;
			SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
			assert old3 == null;
//		}
//		else {			// XXXX debugging
//			CardinalityVariable old2 = node2variable.put(serializationNode, null);
//			assert old2 == null;
//		}
	}

	public boolean addSolution(@NonNull CardinalityVariable variable, @NonNull Object solution) {
		Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions2 = variable2solutions;
		assert variable2solutions2 != null;
		Object oldSolutions = variable2solutions2.get(variable);
		if (oldSolutions instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<Object> castOldSolutions = (List<Object>)oldSolutions;
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

	public @NonNull PreSerializer createNestedPreSerializer(@NonNull SequenceSerializationNode sequenceSerializationNode) {
		return new PreSerializer(this, sequenceSerializationNode);
	}

	public @Nullable SerializationNode getChosenNode(@NonNull AlternativesSerializationNode alternativesSerializationNode) {
		assert alternatives2choice != null;
		return alternatives2choice.get(alternativesSerializationNode);
	}

	public @NonNull List<@NonNull SerializationNode> getSerializedNodes() {
		return serializedNodes;
	}

	public @Nullable Object getSolution(@NonNull CardinalityVariable variable) {
		assert variable2solutions != null;
		return variable2solutions.get(variable);
	}

	public void solve() {
		Map<@NonNull CardinalityVariable, @NonNull Object> variable2solutions2 = variable2solutions;
		assert variable2solutions2 == null;
		variable2solutions = variable2solutions2 = new HashMap<>();
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);

		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {
			if (variable.isOne()) {
				variable2solutions2.put(variable, Integer.valueOf(1));
			}
		}

		boolean gotOne;
//		do {
			gotOne = false;
			for (@NonNull CardinalityExpression expression : expressions) {
				if (expression.solveForConstants(this)) {
					gotOne = true;
				}
			}
//		} while (gotOne);


//			toString();
		for (@NonNull CardinalityVariable variable : variables) {
			if (!variable2solutions2.containsKey(variable)) {
//				toString();
				variable2solutions2.put(variable, "?");
			}
		}
//		toString();
	}

	@Override
	public @NonNull String toString() {
		@NonNull StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\n");
		StringUtil.appendIndentation(s, depth+1, "\t");
		for (@NonNull SerializationNode serializedNode : serializedNodes) {
			serializedNode.toString(s, depth+2);
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
				s.append(serializationNode.toString().replace("\n", "\\n"));
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
						if (isFirst) {
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
