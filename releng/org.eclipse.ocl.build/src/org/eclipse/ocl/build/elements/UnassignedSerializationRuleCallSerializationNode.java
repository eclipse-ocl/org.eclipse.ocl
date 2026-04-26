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
import org.eclipse.ocl.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep;
import org.eclipse.ocl.xtext.idioms.SubIdiom;

/**
 * An UnassignedSerializationRuleCallSerializationNode delegates one of the functionalities of one
 * SerializationRule to another. It is used when flattening a UnassignedGrammarRuleCallSerializationNode.
 */
public class UnassignedSerializationRuleCallSerializationNode extends AbstractUnassignedSerializationNode
{
	protected final @NonNull SerializationRuleAnalysis calledRuleAnalysis;

	public UnassignedSerializationRuleCallSerializationNode(@NonNull EClassifier producedEClassifier, @NonNull GrammarCardinality grammarCardinality, @NonNull SerializationRuleAnalysis calledRuleAnalysis) {
		super(producedEClassifier, grammarCardinality);
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
	//	throw new UnsupportedOperationException();		// Should have been flattened already
	//	if (grammarCardinality == null) throw new IllegalStateException();		// deepClone occurs for flattened SerializationRules
		return new UnassignedSerializationRuleCallSerializationNode(producedEClassifier, this.grammarCardinality, calledRuleAnalysis);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
	}

	public @NonNull SerializationRuleAnalysis getCalledRuleAnalysis() {
		return calledRuleAnalysis;
	}

//	@Override
//	public boolean isRedundant() {
//		return grammarCardinality.mayBeZero();
//	}

	@Override
	public boolean onlyRootUnassignedSerializationRuleCall(boolean isRootAlternative) {
		return isRootAlternative;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
	//	s.append(calledRuleAnalysis);
		s.append(calledRuleAnalysis.getVariantName());
	//	s.append(":");
	//	s.append(calledRuleAnalysis.getProducedEClass().getName());
		appendCardinality(s, depth);
	//	calledRuleAnalysis.toString(s, depth);
	//	calledRuleAnalysis.getRootSerializationNode().toString(s, depth);
	}
}