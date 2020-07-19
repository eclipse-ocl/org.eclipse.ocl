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
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class AlternativeAssignedRuleCallsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull List<@NonNull AbstractRuleAnalysis> ruleAnalyses;

	public AlternativeAssignedRuleCallsSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull AbstractRuleAnalysis> ruleAnalyses) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.ruleAnalyses = ruleAnalyses; //eStructuralFeature.isUnique() ? new UniqueList<>() : new ArrayList<>();
	}

//	public void addRuleAnalysis(@NonNull AbstractRuleAnalysis ruleAnalysis) {
//		ruleAnalyses.add(ruleAnalysis);
//	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AlternativeAssignedRuleCallsSerializationNode(assignmentAnalysis, multiplicativeCardinality, ruleAnalyses);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		if (ruleAnalyses.size() > 1) {
			s.append("{");
		}
		boolean isFirst = true;
		for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(ruleAnalysis.getRuleName());
			isFirst = false;
		}
		if (ruleAnalyses.size() > 1) {
			s.append("}");
		}
		appendCardinality(s, depth);
	}
}