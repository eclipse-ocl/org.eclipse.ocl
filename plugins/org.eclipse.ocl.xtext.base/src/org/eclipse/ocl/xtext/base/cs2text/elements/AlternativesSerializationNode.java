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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
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
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) throw new IllegalStateException();		// deepClone occurs for flattened SerializationRules
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
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
			StringUtil.appendIndentation(s, depth);
			s.append("| ");
			alternativeSerializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		}
		StringUtil.appendIndentation(s, depth);
		s.append("}");
		appendCardinality(s, depth);
	}
}