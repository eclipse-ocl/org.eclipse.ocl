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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.UniqueList;

public class AlternativeAssignedRuleCallsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull List<@NonNull XtextAbstractRuleAnalysis> ruleAnalyses;

	public AlternativeAssignedRuleCallsSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature,
			@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(ruleAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality);
		this.ruleAnalyses = eStructuralFeature.isUnique() ? new UniqueList<>() : new ArrayList<>();
	}

	public void addRuleAnalysis(@NonNull XtextAbstractRuleAnalysis ruleAnalysis) {
		ruleAnalyses.add(ruleAnalysis);
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AlternativeAssignedRuleCallsSerializationNode(ruleAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		if (ruleAnalyses.size() > 1) {
			s.append("{");
		}
		boolean isFirst = true;
		for (@NonNull XtextAbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
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