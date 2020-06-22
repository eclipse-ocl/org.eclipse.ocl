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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.ParserRule;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class XtextParserRuleAnalysis extends XtextAbstractRuleAnalysis
{
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();

	public XtextParserRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull ParserRule parserRule) {
		super(grammarAnalysis, parserRule);
	}

	public void addAssignmentAnalysis(@NonNull XtextAssignmentAnalysis assignmentAnalysis) {
		EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eStructuralFeature);
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			eFeature2assignmentAnalyses.put(eStructuralFeature, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> getEFeature2assignmentAnalyses() {
		return eFeature2assignmentAnalyses;
	}

	public @NonNull ParserRule getParserRule() {
		return (ParserRule) abstractRule;
	}
}