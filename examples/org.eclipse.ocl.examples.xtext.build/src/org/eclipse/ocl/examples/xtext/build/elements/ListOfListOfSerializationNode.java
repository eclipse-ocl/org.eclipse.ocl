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
package org.eclipse.ocl.examples.xtext.build.elements;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

/**
 * A ListOfListOfSerializationNode is a frozen list of frozen list of SerializationNode awaiting distribution
 * to a disjunction of SerializationNode / SequenceSerializationNode.
 */
public class ListOfListOfSerializationNode extends AbstractSerializationElement
{
	private @NonNull List<@NonNull List<@NonNull SerializationNode>> listOfListOfNodes;

	public ListOfListOfSerializationNode() {
		this.listOfListOfNodes = new ArrayList<>();
	}

	public ListOfListOfSerializationNode(@NonNull List<@NonNull List<@NonNull SerializationNode>> listOfListOfNodes) {
		this.listOfListOfNodes = listOfListOfNodes;
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
			for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
				for (@NonNull SerializationNode additionalNode : additionalNodes) {
					appendNodeToList(listOfNodes, additionalNode);
				}
			}
			return this;
//				SerializationNode additionalNode = new SequenceSerializationNode(additionalSerializationElement.getGrammarAnalysis(), additionalNodes)
//				return this;
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
			return new ListOfListOfSerializationNode(newListOfList);
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
		//	grammarCardinality = GrammarCardinality.max(grammarCardinality, GrammarCardinality.ZERO_OR_ONE);
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
	public @NonNull SerializationNode freezeAlternatives(@NonNull Alternatives alternatives) {
		List<@NonNull SerializationNode> newList = new ArrayList<>();
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			assert listOfNodes.size() == 1;
			newList.add(listOfNodes.get(0));
		}
		return new AlternativesSerializationNode(alternatives, GrammarCardinality.toEnum(alternatives), newList);
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, boolean isRootAlternative) {
		List<@NonNull List<@NonNull SerializationNode>> newListOfList = new ArrayList<>();
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			SerializationElement frozenSequence = createFrozenSequence(compoundElement, grammarCardinality, listOfNodes, isRootAlternative);
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
	public boolean isListOfList() {
		return true;
	}

	@Override
	public boolean noUnassignedParserRuleCall() {
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			for (@NonNull SerializationNode serializationNode : listOfNodes) {
				if (!serializationNode.noUnassignedParserRuleCall()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean onlyRootUnassignedSerializationRuleCall(boolean isRootAlternative) {
		for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
			for (@NonNull SerializationNode serializationNode : listOfNodes) {
				if (!serializationNode.onlyRootUnassignedSerializationRuleCall(isRootAlternative)) {
					return false;
				}
			}
		}
		return true;
	}

//	@Override
//	public @NonNull SerializationElement setGrammarCardinality(@NonNull GrammarCardinality grammarCardinality) {
//		this.grammarCardinality = GrammarCardinality.max(this.grammarCardinality, grammarCardinality);
//		return this;
//	}

	@Override
	public @NonNull SerializationElement setGrammarCardinality(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality) {
		if (grammarCardinality.isOne()) {
			return this;
		}
		else {
			List<@NonNull List<@NonNull SerializationNode>> newListOfList = new ArrayList<>();
			if (listOfListOfNodes.size() > 0) {
				for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
					List<@NonNull SerializationNode> newList = new ArrayList<>(listOfNodes.size());
					for (@NonNull SerializationNode node : listOfNodes) {
						newList.add(node.setGrammarCardinality(compoundElement, grammarCardinality));
						newListOfList.add(newList);
					}
				}
			}
			return new ListOfListOfSerializationNode(newListOfList);
		}
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.append("{");
		if (listOfListOfNodes.size() > 0) {
			for (@NonNull List<@NonNull SerializationNode> listOfNodes : listOfListOfNodes) {
				s.appendIndentation(depth);
				s.append("|\t{");
				if (listOfNodes.size() > 0) {
					for (@NonNull SerializationNode serializationNode : listOfNodes) {
						s.appendIndentation(depth+1);
						s.append("+\t");
						serializationNode.toString(s, depth+2);
					}
					s.appendIndentation(depth+1);
				}
				s.append("}");
			}
			s.appendIndentation(depth);
		}
		s.append("}");
	}
}