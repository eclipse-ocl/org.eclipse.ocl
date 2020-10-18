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
import org.eclipse.ocl.examples.xtext.build.analysis.ActionAssignmentAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

public class AssignedCurrentSerializationNode extends AbstractAssignedSerializationNode
{
	public AssignedCurrentSerializationNode(@NonNull ActionAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality) {
		super(assignmentAnalysis.getGrammarAnalysis(), assignmentAnalysis.getEClass(), assignmentAnalysis.getEStructuralFeature(), grammarCardinality, assignmentAnalysis.getTargetRuleAnalyses());
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		throw new IllegalStateException();		// clone occurs for flattened SerializationRules
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	@Override
	public @NonNull GrammarRuleVector getAssignedGrammarRuleVector() {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	@Override
	public int @NonNull [] getAssignedRuleIndexes() {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
//		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(SerializationUtils.getName(assignedEClass));
		s.append("::");
		s.append(SerializationUtils.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("«current»");
		appendCardinality(s, depth);
	}
}