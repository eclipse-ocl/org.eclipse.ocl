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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.xtext.Group;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull Group group;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;
	private @Nullable RequiredSlots requiredSlots = null;

	public SequenceSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Group group, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(grammarAnalysis, group);
		this.group = group;
		this.serializationNodes = groupSerializationNodes;
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
					int innerSize = innerRequiredSlots.getConjunctionCount();
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
				requiredSlots = permute(nodesIndexes);
			}
			else {
				//
				//	Assign the permutations of all inner disjunctions.
				//
				List<@NonNull RequiredSlotsConjunction> outerDisjunctions = new ArrayList<>(outerSize);
				for (int outerIndex = 0; outerIndex < outerSize; outerIndex++) {
					outerDisjunctions.add(permute(nodesIndexes));
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

	private @NonNull RequiredSlotsConjunction permute(int[] nodesIndexes) {
		RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
		for (int nodesIndex = 0; nodesIndex < nodesIndexes.length; nodesIndex++) {
			SerializationNode serializationNode = serializationNodes.get(nodesIndex);
			RequiredSlots requiredSlots = serializationNode.getRequiredSlots();
			if (!requiredSlots.isNull()) {
				RequiredSlotsConjunction innerConjunction = requiredSlots.getConjunction(nodesIndexes[nodesIndex]);
			//	for (@NonNull SimpleRequiredSlot innerSlot : innerConjunction.getConjunction()) {
			//		outerConjunction.accumulate(innerSlot, cardinality);
			//	}
			//	outerConjunction.accumulate(innerConjunction.getAlternativesChoices());
				outerConjunction.accumulate(innerConjunction, cardinality);
			}
		}
		outerConjunction.getConjunction();		// XXX eager
		return outerConjunction;
	}

	@Override
	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
		//	serializationBuilder.appendSoftSpace();
			serializationNode.serialize(serializationBuilder, element);
		//	serializationBuilder.appendSoftSpace();
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\t");
		s.append("{");
	//	boolean isFirst = true;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
		//	if (!isFirst) {
				s.append("\n");
		//	}
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("+ ");
			serializationNode.toString(s, depth+1);
		//	isFirst = false;
		}
		s.append("\n");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s);
	}
}