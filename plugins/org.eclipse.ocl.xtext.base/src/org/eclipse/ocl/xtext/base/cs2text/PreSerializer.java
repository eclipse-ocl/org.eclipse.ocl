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
import java.util.Map.Entry;

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
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression;
	private final @NonNull List<@NonNull SerializationNode> serializedNodes = new ArrayList<>();

	public PreSerializer(@Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice) {
		this.parentSerializedNode = null;
		this.alternatives2choice = alternatives2choice;
		this.node2parent = new HashMap<>();
		this.node2variable = new HashMap<>();
		this.feature2expression = new HashMap<>();
	}

	private PreSerializer(@NonNull PreSerializer preSerializer, @NonNull SequenceSerializationNode parentSerializedNode) {
		this.parentSerializedNode = parentSerializedNode;
		this.alternatives2choice = preSerializer.alternatives2choice;
		this.node2parent = preSerializer.node2parent;
		this.node2variable = preSerializer.node2variable;
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

	public void addSerializedNode2(@NonNull SerializationNode serializationNode) {
		assert serializationNode != parentSerializedNode;			// XXX
		assert !node2parent.containsKey(serializationNode);
		SerializationNode old1 = node2parent.put(serializationNode, parentSerializedNode);
//		assert old1 == null;
//		serializedNodes.add(serializationNode);
		MultiplicativeCardinality multiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
//		if (!multiplicativeCardinality.isOne()) {
			String name = "V" + node2variable.size();
			CardinalityVariable old2 = node2variable.put(serializationNode, new CardinalityVariable(name, multiplicativeCardinality));
			assert old2 == null;
//		}
//		else {			// XXXX debugging
//			CardinalityVariable old2 = node2variable.put(serializationNode, null);
//			assert old2 == null;
//		}
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
		for (@NonNull Entry<@NonNull SerializationNode, /*@NonNull*/ CardinalityVariable> entry : node2variable.entrySet()) {	// XXX
			if (entry.getValue() != null) {
				s.append("\n");
				StringUtil.appendIndentation(s, depth, "\t");
				s.append(entry.getValue());
				s.append(": ");
				s.append(entry.getKey().toString().replace("\n", "\\n"));
			}
		}
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression expression : expressions) {
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append(expression);
		}
	}
}
