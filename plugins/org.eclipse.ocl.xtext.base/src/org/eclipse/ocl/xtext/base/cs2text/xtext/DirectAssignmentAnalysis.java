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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;

/**
 * A DirectAssignmentAnalysis provides the extended analysis of an Xtext Assignment
 */
public class DirectAssignmentAnalysis extends AbstractAssignmentAnalysis<@NonNull Assignment>
{
	public DirectAssignmentAnalysis(@NonNull ParserRuleAnalysis sourceRuleAnalysis, @NonNull Assignment assignment) {
		super(sourceRuleAnalysis, (EClass)XtextGrammarUtil.getEClassifierScope(assignment), XtextGrammarUtil.getFeature(assignment), assignment);
	}

	@Override
	public void analyzeContainmentAndTargets() {
		analyzeContainment();
		AbstractElement terminal = XtextGrammarUtil.getTerminal(actionOrAssignment);
		computeTargetRuleAnalyses(terminal);
	}

	private void computeTargetRuleAnalyses(@NonNull AbstractElement terminal) {
		if (terminal instanceof RuleCall) {							// containment EReference / value EAttribute
			AbstractRule calledRule = XtextGrammarUtil.getRule((RuleCall)terminal);
			AbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(calledRule);
			addTargetRuleAnalysis(calledRuleAnalysis);
		}
		else if (terminal instanceof CrossReference) {}				// non-containment EReference
		else if (terminal instanceof Keyword) {}					// enumerated attribute
		else if (terminal instanceof Alternatives) {
			for (@NonNull AbstractElement element : XtextGrammarUtil.getElements((Alternatives)terminal)) {
				computeTargetRuleAnalyses(element);
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
	}
}