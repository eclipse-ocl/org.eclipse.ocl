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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;
	private @Nullable RequiredSlots requiredSlots = null;

	public SequenceSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(ruleAnalysis, multiplicativeCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new SequenceSerializationNode(ruleAnalysis, compoundElement, multiplicativeCardinality, serializationNodes);
	}

	/**
	 * Return the simplified disjunction of conjunction of required slots.
	 */
	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		RequiredSlots requiredSlots = this.requiredSlots;
		if (requiredSlots == null) {
			// Initialize the conjunction size and counter for each inner compounded node.
			int nodesSize = serializationNodes.size();
			int[] nodesSizes = new int[nodesSize];
			int[] nodesIndexes = new int[nodesSize];
			//
			//	Compute the number of outer disjunctions in the permutation of all inner disjunctions.
			//
			int outerSize = 1;
			for (int nodesIndex = 0; nodesIndex < nodesSize; nodesIndex++) {
				SerializationNode serializationNode = serializationNodes.get(nodesIndex);
				RequiredSlots innerRequiredSlots = serializationNode.getRequiredSlots();
				if (!innerRequiredSlots.isNull()) {
					int innerSize = innerRequiredSlots.getSerializationRules().size();
					assert innerSize != 0;
					outerSize *= innerSize;
					nodesSizes[nodesIndex] = innerSize;
					nodesIndexes[nodesIndex] = 0;
				}
			}
			//
			//	No alternatives => a Conjunction
			//
			if (outerSize == 1) {
				requiredSlots = permute(nodesIndexes, nodesSizes);
			}
			else {
				//
				//	Assign the permutations of all inner disjunctions.
				//
				List<@NonNull SerializationRule> outerDisjunctions = new ArrayList<>(outerSize);
				for (int outerIndex = 0; outerIndex < outerSize; outerIndex++) {
					outerDisjunctions.add(permute(nodesIndexes, nodesSizes));
					for (int nodesIndex = 0; nodesIndex < nodesSize; nodesIndex++) {
						nodesIndexes[nodesIndex]++;
						if (nodesIndexes[nodesIndex] < nodesSizes[nodesIndex]) {
							break;
						}
						nodesIndexes[nodesIndex] = 0;
					}
				}
				requiredSlots = createRequiredSlots(outerDisjunctions);
			}
			this.requiredSlots = requiredSlots;
		}
		return requiredSlots;
	}

	public @NonNull List<@NonNull SerializationNode> getSerializationNodes() {
		return serializationNodes;
	}

	/**
	 * Return a conjunction with alternatives choices for the nodeIndexes permutation of all possible nodesSizes permutations.
	 */
	private @NonNull SerializationRule permute(int @NonNull [] nodesIndexes, int @NonNull [] nodesSizes) {
		Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> outerAlternatives2choice = null;
		SerializationRule outerConjunction = new SerializationRule(ruleAnalysis);
		for (int nodesIndex = 0; nodesIndex < nodesIndexes.length; nodesIndex++) {
			SerializationNode serializationNode = serializationNodes.get(nodesIndex);
		//	if (serializationNode instanceof AlternativesSerializationNode) {
		//		hasAlternatives = true;
		//	}
			RequiredSlots requiredSlots = serializationNode.getRequiredSlots();
			if (!requiredSlots.isNull()) {
			//	int subSize = nodesSizes[nodesIndex];
				int subIndex = nodesIndexes[nodesIndex];
				SerializationRule innerConjunction = requiredSlots.getSerializationRules().get(subIndex);
			//	if (subSize > 0) {
					Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> innerAlternatives2choice = innerConjunction.getAlternativesChoices();
					if (innerAlternatives2choice != null) {
						if (outerAlternatives2choice == null) {
							outerAlternatives2choice = new HashMap<>();
						}
						outerAlternatives2choice.putAll(innerAlternatives2choice);
					}
			//	}
			//	for (@NonNull SimpleRequiredSlot innerSlot : innerConjunction.getConjunction()) {
			//		outerConjunction.accumulate(innerSlot, cardinality);
			//	}
			//	outerConjunction.accumulate(innerConjunction.getAlternativesChoices());
				outerConjunction.accumulate(innerConjunction, multiplicativeCardinality);
			}
		}
		outerConjunction.getConjunction();		// XXX eager
		if (outerAlternatives2choice != null) {
			outerConjunction.setAlternatives(outerAlternatives2choice);
		}
		return outerConjunction;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
//		super.preSerialize(preSerializer);
		PreSerializer nestedPreSerializer = preSerializer.createNestedPreSerializer(this);
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.preSerialize(nestedPreSerializer);
		}
	/*	List<@NonNull SerializationNode> nestedSerializedNodes = nestedPreSerializer.getSerializedNodes();
		SequenceSerializationNode nestedSequenceSerializationNode = new SequenceSerializationNode(grammarAnalysis, group, nestedSerializedNodes)
		{
			@Override
			public void toString(@NonNull StringBuilder s, int depth) {
			//	StringUtil.appendIndentation(s, depth, "\t");
				s.append("{");
			//	boolean isFirst = true;
				for (@NonNull SerializationNode serializationNode : serializationNodes) {
				//	if (!isFirst) {
						s.append(" ");
				//	}
					serializationNode.toString(s, depth+1);
				//	isFirst = false;
				}
				s.append(" }");
				appendCardinality(s);
			}
		};
		preSerializer.addSerializedNode(nestedSequenceSerializationNode);			// XXX parent counted list */
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializer.serializeNodes(serializationBuilder, serializationNodes);
	//	for (@NonNull SerializationNode serializationNode : serializationNodes) {
	//		serializationNode.serialize(serializationBuilder);
	//	}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("+ ");
			serializationNode.toString(s, depth+1);
		}
		s.append("\n");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s, depth);
	}
}