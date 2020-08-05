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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.RuleCall;

public class UnassignedRuleCallSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull RuleCall ruleCall;
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;

	public UnassignedRuleCallSerializationNode(@NonNull GrammarAnalysis grammarAnalysis, @NonNull RuleCall ruleCall, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(grammarAnalysis, multiplicativeCardinality);
		this.ruleCall = ruleCall;
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) throw new IllegalStateException();		// deepClone occurs for flattened SerializationRules
		return new UnassignedRuleCallSerializationNode(grammarAnalysis, ruleCall, multiplicativeCardinality, calledRuleAnalysis);
	}

	public @NonNull AbstractRuleAnalysis getCalledRuleAnalysis() {
		return calledRuleAnalysis;
	}

//	@Override
//	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
//		return MultiplicativeCardinality.ONE;			// ?? could more accurately be ZERO or ONE
//	}

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