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
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.xtext.RuleCall;

public class UnassignedRuleCallSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull RuleCall ruleCall;
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;

	public UnassignedRuleCallSerializationNode(@NonNull RuleCall ruleCall, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(multiplicativeCardinality);
		this.ruleCall = ruleCall;
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) throw new IllegalStateException();		// deepClone occurs for flattened SerializationRules
		return new UnassignedRuleCallSerializationNode(ruleCall, multiplicativeCardinality, calledRuleAnalysis);
	}

	@Override
	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList) {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	public @NonNull AbstractRuleAnalysis getCalledRuleAnalysis() {
		return calledRuleAnalysis;
	}

	@Override
	public boolean isRedundant() {
		return multiplicativeCardinality.mayBeZero();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}