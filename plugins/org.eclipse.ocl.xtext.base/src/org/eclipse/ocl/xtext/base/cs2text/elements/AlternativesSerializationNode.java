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

import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.PreSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.Alternatives;

public class AlternativesSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull Alternatives alternatives;
	protected final @NonNull List<@NonNull SerializationNode> alternativeSerializationNodes;

	public AlternativesSerializationNode(@NonNull GrammarAnalysis grammarAnalysis, @NonNull Alternatives alternatives, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> alternativeSerializationNodes) {
		super(grammarAnalysis, multiplicativeCardinality);
		this.alternatives = alternatives;
		this.alternativeSerializationNodes = alternativeSerializationNodes;
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AlternativesSerializationNode(grammarAnalysis, alternatives, multiplicativeCardinality, alternativeSerializationNodes);
	}

	/**
	 * Return the alternative for alternativeIndex or null for an invalid index, whicj may be appropriate for
	 * the optional alternative.
	 */
	public @Nullable SerializationNode getAlternativeSerializationNode(int alternativeIndex) {
		return alternativeIndex < alternativeSerializationNodes.size() ? alternativeSerializationNodes.get(alternativeIndex) : null;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		throw new IllegalStateException();
/*		SerializationNode chosenNode = preSerializer.getChosenNode(this);
		if (chosenNode == this) {
			List<@NonNull SerializationNode> multiAlternativeSerializationNodes = new ArrayList<>(alternativeSerializationNodes.size());
			for (@NonNull SerializationNode alternativeNode : alternativeSerializationNodes) {
				if (alternativeNode.getMultiplicativeCardinality() == MultiplicativeCardinality.ZERO_OR_MORE) {
					multiAlternativeSerializationNodes.add(alternativeNode);
				}
				else {
					multiAlternativeSerializationNodes.add(alternativeNode.clone(MultiplicativeCardinality.ZERO_OR_MORE));
				}
			}
			for (@NonNull SerializationNode alternativeNode : multiAlternativeSerializationNodes) {
				alternativeNode.preSerialize(preSerializer);
			}
		}
		else if (chosenNode != null) {
			chosenNode.preSerialize(preSerializer);
		} */
	}

//	@Override
//	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
//		SerializationNode serializationNode = serializationBuilder.getAlternative(this);
	//	serializationNode.serialize(serializationBuilder, element);
//	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
	//	boolean isFirst = true;
		for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("| ");
			alternativeSerializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		//	isFirst = false;
		}
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s, depth);
	}
}