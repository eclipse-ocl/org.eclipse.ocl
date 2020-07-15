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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

import com.google.common.collect.Lists;

/**
 * A ListOfListOfSerializationNode is a frozen list of frozen list of SerializationNode awaiting distribution
 * to a disjunction of SerializationNode / SequenceSerializationNode.
 */
public class ListOfListOfSerializationNode extends AbstractSerializationElement
{
	private @NonNull List<@NonNull List<@NonNull SerializationNode>> listOfListOfNodes;
	private @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public ListOfListOfSerializationNode() {
		this.listOfListOfNodes = new ArrayList<>();
		this.multiplicativeCardinality = MultiplicativeCardinality.ONE;
	}

	public ListOfListOfSerializationNode(@NonNull List<@NonNull List<@NonNull SerializationNode>> listOfListOfNodes, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.listOfListOfNodes = listOfListOfNodes;
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	@Override
	public @NonNull SerializationElement add(@NonNull SerializationElement additionalSerializationElement) {
		if (additionalSerializationElement.isNull()) {
			multiplicativeCardinality = MultiplicativeCardinality.max(multiplicativeCardinality, MultiplicativeCardinality.ZERO_OR_ONE);
			return this;
		}
		else if (additionalSerializationElement.isNode()) {
			SerializationNode additionalSerializationNode = additionalSerializationElement.asNode();
			if (listOfListOfNodes.size() > 0) {
				for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
					listOfNodes.add(additionalSerializationNode);
				}
			}
			else {
				listOfListOfNodes.add(Lists.newArrayList(additionalSerializationNode));
			}
			return this;
		}
		else if (additionalSerializationElement.isList()) {
			throw new IllegalStateException();			// Additional list should not be a future SequenceSerializationNode
		}
		else if (additionalSerializationElement.isListOfList()) {
			List<@NonNull List<@NonNull SerializationNode>> newListOfList = new ArrayList<>();
			List<@NonNull List<@NonNull SerializationNode>> additionalListOfListOfNodes = additionalSerializationElement.asListOfList().getLists();
			if (listOfListOfNodes.size() > 0) {
				for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
					for (@NonNull List<@NonNull SerializationNode> additionalListOfNodes : additionalListOfListOfNodes) {
						List<@NonNull SerializationNode> newList = new ArrayList<>(listOfNodes);
						newList.addAll(additionalListOfNodes);
						newListOfList.add(newList);
					}
				}
			}
			else {
				listOfListOfNodes.addAll(additionalListOfListOfNodes);
			}
			return new ListOfListOfSerializationNode(newListOfList, MultiplicativeCardinality.ONE);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public @NonNull ListOfListOfSerializationNode asListOfList() {
		return this;
	}

	@Override
	public @NonNull SerializationNode freezeAlternatives(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull Alternatives alternatives) {
		List<@NonNull SerializationNode> newList = new ArrayList<>();
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			assert listOfNodes.size() == 1;
			newList.add(listOfNodes.get(0));
		}
		return new AlternativesSerializationNode(ruleAnalysis, alternatives, MultiplicativeCardinality.toEnum(alternatives), newList);
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull CompoundElement compoundElement) {
		List<@NonNull List<@NonNull SerializationNode>> newListOfList = new ArrayList<>();
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			SequenceSerializationNode sequenceSerializationNode = new SequenceSerializationNode(ruleAnalysis, compoundElement, MultiplicativeCardinality.toEnum(compoundElement), listOfNodes);
			newListOfList.add(Collections.singletonList(sequenceSerializationNode));
		}
		listOfListOfNodes = newListOfList;
		return this;
	}

	public @NonNull List<@NonNull List<@NonNull SerializationNode>> getLists() {
		return listOfListOfNodes;
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Override
	public boolean isListOfList() {
		return true;
	}

	@Override
	public @NonNull SerializationElement setMultiplicativeCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.multiplicativeCardinality = MultiplicativeCardinality.max(this.multiplicativeCardinality, multiplicativeCardinality);
		return this;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("| ");
			s.append("{");
			for (@NonNull SerializationNode serializationNode : listOfNodes) {
				s.append("\n");
				StringUtil.appendIndentation(s, depth+1, "\t");
				s.append("+ ");
				serializationNode.toString(s, depth+2);
			}
			s.append("\n");
			StringUtil.appendIndentation(s, depth+1, "\t");
			s.append("}");
		}
		s.append("\n");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
	//	appendCardinality(s, depth);
	}
}