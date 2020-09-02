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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ActionAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class AssignedCurrentSerializationNode extends AbstractAssignedSerializationNode
{
	public AssignedCurrentSerializationNode(@NonNull ActionAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality) {
		super(assignmentAnalysis, grammarCardinality);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		throw new IllegalStateException();		// clone occurs for flattened SerializationRules
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	@Override
	public @NonNull Integer @Nullable [] getAssignedRuleIndexes() {
		throw new UnsupportedOperationException();		// Should have been flattened away
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
//		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(XtextGrammarUtil.getName(assignmentAnalysis.getEClass()));
		s.append("::");
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("«current»");
		appendCardinality(s, depth);
	}
}