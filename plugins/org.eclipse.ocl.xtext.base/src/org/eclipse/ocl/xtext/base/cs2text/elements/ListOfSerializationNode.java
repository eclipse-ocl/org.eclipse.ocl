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
 * A ListOfSerializationNode is an extensible list of SerializationNode awaiting aggregation as a frozen SequenceSerializationNode.
 */
public class ListOfSerializationNode extends AbstractSerializationElement
{
	private final @NonNull List<@NonNull SerializationNode> listOfNodes;
	private @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public ListOfSerializationNode() {
		this.listOfNodes = new ArrayList<>();
		this.multiplicativeCardinality = MultiplicativeCardinality.ONE;
	}

	public ListOfSerializationNode(@NonNull List<@NonNull SerializationNode> listOfNodes, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.listOfNodes = listOfNodes;
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	@Override
	public @NonNull SerializationElement addConcatenation(@NonNull SerializationElement additionalSerializationElement) {
		if (additionalSerializationElement.isNull()) {
			return this;
		}
		else if (additionalSerializationElement.isNode()) {
			appendNodeToList(listOfNodes, additionalSerializationElement.asNode());
			return this;
		}
		else if (additionalSerializationElement.isList()) {
			listOfNodes.addAll(additionalSerializationElement.asList().getNodes());
			return this;
		}
		else if (additionalSerializationElement.isListOfList()) {
			List<@NonNull List<@NonNull SerializationNode>> newListOfList = new ArrayList<>();
			for (@NonNull List<@NonNull SerializationNode> additionalList : additionalSerializationElement.asListOfList().getLists()) {
				List<@NonNull SerializationNode> newList = new ArrayList<>(listOfNodes);
				newList.addAll(additionalList);
				newListOfList.add(newList);
			}
			return new ListOfListOfSerializationNode(newListOfList, MultiplicativeCardinality.ONE);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public @NonNull ListOfSerializationNode asList() {
		return this;
	}

	@Override
	public @NonNull SerializationNode freezeAlternatives(@NonNull GrammarAnalysis grammarAnalysis, @NonNull Alternatives alternatives) {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement) {
		if (listOfNodes.isEmpty()) {
			return NullSerializationNode.INSTANCE;
		}
		else {
			return createFrozenSequence(grammarAnalysis, compoundElement, MultiplicativeCardinality.toEnum(compoundElement), listOfNodes);
		}
	}

	public @NonNull List<@NonNull SerializationNode> getNodes() {
		return listOfNodes;
	}

	@Override
	public boolean isList() {
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
		if (listOfNodes.size() > 0) {
			for (@NonNull SerializationNode serializationNode : listOfNodes) {
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("+\t");
				serializationNode.toString(s, depth+1);
			}
			StringUtil.appendIndentation(s, depth, "\t");
		}
		s.append("}");
		s.append(multiplicativeCardinality);
	//	appendCardinality(s, depth);
	}
}