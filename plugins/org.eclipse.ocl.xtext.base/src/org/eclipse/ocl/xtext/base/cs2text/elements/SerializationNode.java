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
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.xtext.CompoundElement;

public interface SerializationNode extends SerializationElement
{
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
	 * Gather the runtime represetation of the nodes in steps.
	 * @param subIdiomsList
	 * @param serializationNode2subIdioms
	 */
	void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList,
		@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList);

	/**
	 * Return true if this node has exactly one cardinality.
	 */
	boolean isOne();

	/**
	 * Return true if this node is unnecessary, e.g. an optional unassigned RuleCall.
	 */
	boolean isRedundant();

	/**
	 * Traverse the serialization node hoderarchy of a serializer's serializationRule to append appropriate string
	 * segments to the serializationBuilder to represent the serializer's user element.
	 */
//	void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);

	@Override
	@NonNull SerializationNode setMultiplicativeCardinality(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality);
}