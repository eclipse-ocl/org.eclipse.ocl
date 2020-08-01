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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;

/**
 * An XtextAssignmentAnalysis provides the extended analysis of an Xtext Assignment
 */
public class AssignmentAnalysis implements Nameable
{
	/**
	 * The rule analysis that uses this assignment to assign a target rule result.
	 */
	protected final @NonNull ParserRuleAnalysis sourceRuleAnalysis;

	/**
	 * The analyzed assignment.
	 */
	protected final @NonNull AbstractElement assignment;			// Assignment or Action

	/**
	 * The overall grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The assigned feature.
	 */
	protected final @NonNull EStructuralFeature eStructuralFeature;

	private @NonNull AbstractElement terminal;

	/**
	 * The rules declared to be useable as producers of the target.
	 */
	private final @NonNull List<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = new ArrayList<>();	// XXX obsolete

	private AbstractRuleAnalysis terminalRuleAnalysis;

	public AssignmentAnalysis(@NonNull ParserRuleAnalysis sourceRuleAnalysis, @NonNull Assignment assignment) {
		this.sourceRuleAnalysis = sourceRuleAnalysis;
		this.assignment = assignment;
		this.grammarAnalysis = sourceRuleAnalysis.getGrammarAnalysis();
		String featureName = XtextGrammarUtil.getFeature(assignment);
		EClass eClass = (EClass)XtextGrammarUtil.getEClassifierScope(assignment);
		this.eStructuralFeature = XtextGrammarUtil.getEStructuralFeature(eClass, featureName);
		this.terminal = XtextGrammarUtil.getTerminal(assignment);
	//	assert terminal instanceof RuleCall;
		AbstractRule terminalRule;
		if (terminal instanceof CrossReference) {
			RuleCall ruleCall = (RuleCall)XtextGrammarUtil.getTerminal((CrossReference)terminal);
			terminalRule = XtextGrammarUtil.getRule(ruleCall);
			this.terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule);
		}
		else if (terminal instanceof RuleCall)  {
			terminalRule = XtextGrammarUtil.getRule((RuleCall)terminal);
			this.terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule);
		}
		else {
			this.terminalRuleAnalysis = null;
		}
	}

	public AssignmentAnalysis(@NonNull ParserRuleAnalysis sourceRuleAnalysis, @NonNull Action action, @NonNull RuleCall firstUnassignedRuleCall) {
		this.sourceRuleAnalysis = sourceRuleAnalysis;
		this.assignment = action;
		this.grammarAnalysis = sourceRuleAnalysis.getGrammarAnalysis();
		String featureName = XtextGrammarUtil.getFeature(action);
		EClass eClass = (EClass)XtextGrammarUtil.getClassifier(XtextGrammarUtil.getType(action));
		this.eStructuralFeature = XtextGrammarUtil.getEStructuralFeature(eClass, featureName);
		this.terminal = firstUnassignedRuleCall;
		assert terminal instanceof RuleCall;
		AbstractRule terminalRule = XtextGrammarUtil.getRule((RuleCall)terminal);
		this.terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule);
	}

	public void analyzeContainmentAndTargets() {
		if (eStructuralFeature instanceof EReference) {
			EReference eReference = (EReference)eStructuralFeature;
			if (eReference.isContainment()) {
				grammarAnalysis.addContainment(this, eReference);
			}
		}
		computeTargetRuleAnalyses(terminal);
	}

	private void computeTargetRuleAnalyses(@NonNull AbstractElement terminal) {
		if ("ownedMultiplicity".equals(eStructuralFeature.getName())) {
			getClass();		// XXX debugging
		}
		if (terminal instanceof RuleCall) {
		//	AbstractRule terminalRule = XtextGrammarUtil.getRule((RuleCall)terminal);
			if (terminalRuleAnalysis instanceof ParserRuleAnalysis) {
				for (@NonNull ParserRuleAnalysis ruleAnalysis : ((ParserRuleAnalysis)terminalRuleAnalysis).debugCalledRuleAnalysesClosure) { //getCallingRuleAnalysisClosure()) {
					targetRuleAnalyses.add(ruleAnalysis);
				}
			}
			else if (terminalRuleAnalysis != null) {
				targetRuleAnalyses.add(terminalRuleAnalysis);
			}
			else {}		// Keywords ???
		}
		else if (terminal instanceof Alternatives) {
			for (@NonNull AbstractElement element : XtextGrammarUtil.getElements((Alternatives)terminal)) {
				computeTargetRuleAnalyses(element);
			}
		}
		else if (terminal instanceof CrossReference) {}
		else if (terminal instanceof Keyword) {}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public @NonNull AbstractElement getAssignment() {
		return assignment;
	}

	public @NonNull String getCardinality() {
		String cardinality = assignment.getCardinality();
		return cardinality != null ?  cardinality : "@";
	/*	int lowerBound = eStructuralFeature.getLowerBound();
		int upperBound = eStructuralFeature.getUpperBound();
		if (upperBound < 0) {
			return lowerBound != 0 ? "+" : "*";
		}
		else if (upperBound == 1) {
			return lowerBound != 0 ? "1" : "?";
		}
		else if (upperBound == lowerBound) {
			return Integer.toString(lowerBound);
		}
		else {
			return lowerBound + ".." + upperBound;
		} */
	}

	public @NonNull EClass getEClass() {
		return (EClass) XtextGrammarUtil.getEClassifierScope(assignment);
	}

	public @NonNull EClass getEContainingClass() {
		return XtextGrammarUtil.getEContainingClass(eStructuralFeature);
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return sourceRuleAnalysis.getGrammarAnalysis();
	}

	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return MultiplicativeCardinality.toEnum(assignment);
	}

	@Override
	public @NonNull String getName() {
		return XtextGrammarUtil.getName(sourceRuleAnalysis.getRule()) + "-" + eStructuralFeature.getName();
	}

	public @NonNull ParserRuleAnalysis getSourceRuleAnalysis() {
		return sourceRuleAnalysis;
	}

	public @NonNull List<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses() {
		return targetRuleAnalyses;
	}

	public @Nullable AbstractRuleAnalysis getTerminalRuleAnalysis() {
		return terminalRuleAnalysis;
	}

	/**
	 * Return true if sourceActualRuleAnalysis produces an acceptable result for use as the source of this assignment.
	 *
	public boolean sourceIsAssignableFrom(@NonNull AbstractRuleAnalysis sourceActualRuleAnalysis) {
		return sourceActualRuleAnalysis.getBaseRuleAnalysisClosure().contains(this.sourceRuleAnalysis);
	} */

	/**
	 * Return true if targetActualRuleAnalysis produces an acceptable result for use as the target of this assignment.
	 *
	public boolean targetIsAssignableFrom(@NonNull AbstractRuleAnalysis targetActualRuleAnalysis) {
		Set<@NonNull AbstractRuleAnalysis> targetActualRuleAnalysisClosure = targetActualRuleAnalysis.getBaseRuleAnalysisClosure();
		for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : this.targetRuleAnalyses) {
			if (targetActualRuleAnalysisClosure.contains(targetRuleAnalysis)) {
				return true;
			}
		}
		return false;
	} */

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" : ");
		if (ClassUtil.maybeNull(targetRuleAnalyses) != null) {
			boolean isFirst = true;
			for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : targetRuleAnalyses) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(targetRuleAnalysis.getName());
				isFirst = false;
			}
		}
		return s.toString();
	}
}