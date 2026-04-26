/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.elements;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.build.analysis.AbstractRuleAnalysis;
import org.eclipse.ocl.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep;
import org.eclipse.ocl.xtext.idioms.SubIdiom;

/**
 * An UnassignedGrammarRuleCallSerializationNode uses another GrammarRule to provide the produced EClass.
 * Additional nodes may provide punctuation decoration or additional assignments.
 *
 * An UnassignedGrammarRuleCallSerializationNode is created wheb parsing a GrammarRule and flattened to its content or to
 * UnassignedSerializationRuleCallSerializationNode delegations.
 */
public class UnassignedGrammarRuleCallSerializationNode extends AbstractUnassignedSerializationNode
{
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;

	public UnassignedGrammarRuleCallSerializationNode(@NonNull EClassifier producedEClassifier, @NonNull GrammarCardinality grammarCardinality, @NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(producedEClassifier, grammarCardinality);
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		throw new UnsupportedOperationException();		// Should be delegating via UnassignedSerializationRuleCallSerializationNode
	//	if (grammarCardinality == null) throw new IllegalStateException();		// deepClone occurs for flattened SerializationRules
	//	return new UnassignedGrammarRuleCallSerializationNode(producedEClass, grammarCardinality, calledRuleAnalysis);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	public @NonNull AbstractRuleAnalysis getCalledRuleAnalysis() {
		return calledRuleAnalysis;
	}

	@Override
	public boolean isRedundant() {
		return grammarCardinality.mayBeZero();
	}

	@Override
	public boolean noUnassignedParserRuleCall() {
		return false;
	}

	@Override
	public boolean onlyRootUnassignedSerializationRuleCall(boolean isRootAlternative) {
		throw new UnsupportedOperationException();		// Should have been flattened
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.append(calledRuleAnalysis.getName());
		appendCardinality(s, depth);
	}
}