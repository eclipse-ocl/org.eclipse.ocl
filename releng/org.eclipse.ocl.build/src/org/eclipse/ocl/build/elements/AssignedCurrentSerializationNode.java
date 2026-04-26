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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.build.analysis.ActionAssignmentAnalysis;
import org.eclipse.ocl.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;
import org.eclipse.ocl.xtext.base.serializer.GrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep;
import org.eclipse.ocl.xtext.base.serializer.SerializationUtils;
import org.eclipse.ocl.xtext.idioms.SubIdiom;

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