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
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;

public interface SerializationNode extends SerializationElement
{
	/**
	 * Traverse the serialization node hoderarchy of a serializationRule to populate its StaticRuleMatch.
	 *
	 * parentStack identifoes the ancestry of this node.
	 */
	void analyze(@NonNull BasicSerializationRule serializationRule, @NonNull Stack<@NonNull SerializationNode> parentStack);

	/**
	 * Ceate a shallow copy of this node with a changed multiplicativeCardinality. This accommodates the nedd to change
	 * the multiplicity when flattening one-or-more alternatives.
	 */
	@NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality);

	/**
	 * Return true if this node has exactly one cardinality.
	 */
	boolean isOne();

	/**
	 * Traverse the serialization node hoderarchy of a serializer's serializationRule to append appropriate string
	 * segments to the serializationBuilder to represent the serializer's user element.
	 */
	void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder);

	/**
	 * Return an equivalent SerializationElement to this that supports a multiplicativeCardinality or greater.
	 * Returns this if existing cardinality is adequate, lor a clone with adjusted cardinality oterwise.
	 */
	@Override
	@NonNull SerializationElement setMultiplicativeCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality);
}