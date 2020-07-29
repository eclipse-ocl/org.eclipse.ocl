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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

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
		assert listOfListOfNodes.size() < 20;
	}

	/**
	 * Permute additionalSerializationElement as concatenations of each prevailing conjunction.
	 */
	@Override
	public @NonNull ListOfListOfSerializationNode addConcatenation(@NonNull SerializationElement additionalSerializationElement) {
		if (additionalSerializationElement.isNull()) {
			return this;
		}
		else if (additionalSerializationElement.isNode()) {
			SerializationNode additionalSerializationNode = additionalSerializationElement.asNode();
			for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
				appendNodeToList(listOfNodes, additionalSerializationNode);
			}
			return this;
		}
		else if (additionalSerializationElement.isList()) {
//			throw new IllegalStateException();			// Additional list should not be a future SequenceSerializationNode
			ListOfSerializationNode additionalListOfNodes = additionalSerializationElement.asList();
			List<@NonNull SerializationNode> additionalNodes = additionalListOfNodes.getNodes();
			if (additionalListOfNodes.isOne()) {
				for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
					for (@NonNull SerializationNode additionalNode : additionalNodes) {
						appendNodeToList(listOfNodes, additionalNode);
					}
				}
				return this;
			}
			else {
				throw new UnsupportedOperationException();
//				SerializationNode additionalNode = new SequenceSerializationNode(additionalSerializationElement.getGrammarAnalysis(), additionalNodes)
//				return this;
			}
/*			for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
				for (@NonNull List<@NonNull SerializationNode> additionalListOfNodes : additionalListOfListOfNodes) {
					List<@NonNull SerializationNode> newList = new ArrayList<>(listOfNodes);
					newList.addAll(additionalListOfNodes);
					newListOfList.add(newList);
				}
			} */
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
				newListOfList.addAll(additionalListOfListOfNodes);
			}
			return new ListOfListOfSerializationNode(newListOfList, MultiplicativeCardinality.ONE);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Add additionalSerializationElement as one or more additional conjunctions leaving the prevailing conjunctions unchanged.
	 */
	public @NonNull ListOfListOfSerializationNode addConjunction(@NonNull SerializationElement additionalSerializationElement) {
		if (additionalSerializationElement.isNull()) {
		//	multiplicativeCardinality = MultiplicativeCardinality.max(multiplicativeCardinality, MultiplicativeCardinality.ZERO_OR_ONE);
			listOfListOfNodes.add(new ArrayList<>());
			return this;
		}
		else if (additionalSerializationElement.isNode()) {
			appendNodeToListOfList(listOfListOfNodes, additionalSerializationElement.asNode());
			return this;
		}
		else if (additionalSerializationElement.isList()) {
			throw new IllegalStateException();			// Additional list should not be a future SequenceSerializationNode
		}
		else if (additionalSerializationElement.isListOfList()) {
			List<@NonNull List<@NonNull SerializationNode>> additionalListOfListOfNodes = additionalSerializationElement.asListOfList().getLists();
			listOfListOfNodes.addAll(additionalListOfListOfNodes);
			return this;
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
	public @NonNull SerializationNode freezeAlternatives(@NonNull GrammarAnalysis grammarAnalysis, @NonNull Alternatives alternatives) {
		List<@NonNull SerializationNode> newList = new ArrayList<>();
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			assert listOfNodes.size() == 1;
			newList.add(listOfNodes.get(0));
		}
		return new AlternativesSerializationNode(grammarAnalysis, alternatives, MultiplicativeCardinality.toEnum(alternatives), newList);
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement) {
		List<@NonNull List<@NonNull SerializationNode>> newListOfList = new ArrayList<>();
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			SerializationElement frozenSequence = createFrozenSequence(grammarAnalysis, compoundElement, MultiplicativeCardinality.toEnum(compoundElement), listOfNodes);
			if (frozenSequence.isListOfList()) {
				newListOfList.addAll(frozenSequence.asListOfList().getLists());
			}
			else if (frozenSequence.isList()) {
				newListOfList.add(frozenSequence.asList().getNodes());
			}
			else if (frozenSequence.isNode()) {
				appendNodeToListOfList(newListOfList, frozenSequence.asNode());
			}
			else {
				throw new UnsupportedOperationException();
			}
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
	public boolean isOne() {
		return multiplicativeCardinality.isOne();
	}

	@Override
	public @NonNull SerializationElement setMultiplicativeCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.multiplicativeCardinality = MultiplicativeCardinality.max(this.multiplicativeCardinality, multiplicativeCardinality);
		return this;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		if (listOfListOfNodes.size() > 0) {
			for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("|\t{");
				if (listOfNodes.size() > 0) {
					for (@NonNull SerializationNode serializationNode : listOfNodes) {
						StringUtil.appendIndentation(s, depth+1, "\t");
						s.append("+\t");
						serializationNode.toString(s, depth+2);
					}
					StringUtil.appendIndentation(s, depth+1, "\t");
				}
				s.append("}1");
			}
			StringUtil.appendIndentation(s, depth, "\t");
		}
		s.append("}");
		s.append(multiplicativeCardinality);
	}
}