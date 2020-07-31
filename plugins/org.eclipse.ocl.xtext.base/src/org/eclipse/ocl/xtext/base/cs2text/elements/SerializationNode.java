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

import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.CompoundElement;

public interface SerializationNode extends SerializationElement
{
	/**
	 * Traverse the serialization node hoderarchy of a serializationRule to populate its StaticRuleMatch.
	 *
	 * parentStack identifoes the ancestry of this node.
	 */
	void analyze(@NonNull BasicSerializationRule serializationRule, @NonNull Stack<@NonNull SerializationNode> parentStack);

	/**
	 * If multiplicativeCardinality is null return a deep copy of this node, else a deep copy
	 * with a changed multiplicativeCardinality. This accommodates the need to change the
	 * multiplicity when flattening one-or-more alternatives and to avoid duplicates when flattening,
	 */
	@NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality);

	/**
	 * Return the (outer) configured multiplicative cardinality.
	 */
	@NonNull MultiplicativeCardinality getMultiplicativeCardinality();

	/**
	 * Return the RuleAnalysis that types matches to this SerializationNode.
	 */
	@Nullable AbstractRuleAnalysis getRuleAnalysis();

	/**
	 * Return true if this node has exactly one cardinality.
	 */
	boolean isOne();

	/**
	 * Traverse the serialization node hoderarchy of a serializer's serializationRule to append appropriate string
	 * segments to the serializationBuilder to represent the serializer's user element.
	 */
	void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder);

	@Override
	@NonNull SerializationNode setMultiplicativeCardinality(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality);
}