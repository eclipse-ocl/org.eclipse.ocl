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

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.xtext.CompoundElement;

public interface SerializationNode extends SerializationElement
{
	/**
	 * If grammarCardinality is null return a deep copy of this node, else a deep copy
	 * with a changed grammarCardinality. This accommodates the need to change the
	 * multiplicity when flattening one-or-more alternatives and to avoid duplicates when flattening,
	 */
	@NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality);

	/**
	 * Return the (outer) configured multiplicative cardinality.
	 */
	@NonNull GrammarCardinality getGrammarCardinality();

	/**
	 * Gather the runtime represetation of the nodes in steps.
	 */
	void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms);

	/**
	 * Return true if this node has exactly one cardinality.
	 */
	boolean isOne();

	/**
	 * Return true if this node is unnecessary, e.g. an optional unassigned RuleCall.
	 */
	boolean isRedundant();

	@Override
	@NonNull SerializationNode setGrammarCardinality(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality);
}