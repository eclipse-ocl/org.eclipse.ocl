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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;

/**
 * An ActionAssignmentAnalysis provides the extended analysis of an Xtext current assignment Action
 */
public class ActionAssignmentAnalysis extends AbstractAssignmentAnalysis
{
	/**
	 * The rule analysis that uses this assignment to assign a target rule result.
	 */
	protected final @NonNull ParserRuleAnalysis sourceRuleAnalysis;

	/**
	 * The analyzed action.
	 */
	protected final @NonNull Action action;

	/**
	 * .
	 */
	protected final @NonNull RuleCall firstUnassignedRuleCall;

//	private @NonNull AbstractElement terminal;
//	private @NonNull AbstractElement terminal;

	/**
	 * The rules declared to be useable as producers of the target.
	 */
	private final @NonNull List<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = new ArrayList<>();	// XXX obsolete

//	private AbstractRuleAnalysis terminalRuleAnalysis;

	public ActionAssignmentAnalysis(@NonNull ParserRuleAnalysis sourceRuleAnalysis, @NonNull Action action, @NonNull RuleCall firstUnassignedRuleCall) {
		super(sourceRuleAnalysis.getGrammarAnalysis(),(EClass)XtextGrammarUtil.getClassifier(XtextGrammarUtil.getType(action)), XtextGrammarUtil.getFeature(action));
		this.sourceRuleAnalysis = sourceRuleAnalysis;
		this.action = action;
	//	String featureName = XtextGrammarUtil.getFeature(action);
	//	EClass eClass = (EClass)XtextGrammarUtil.getClassifier(XtextGrammarUtil.getType(action));
		this.firstUnassignedRuleCall = firstUnassignedRuleCall;
	//	assert terminal instanceof RuleCall;
	/*	AbstractRule terminalRule = XtextGrammarUtil.getRule((RuleCall)terminal);
		this.terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule); */
	}

	@Override
	public void analyzeContainmentAndTargets() {
		if (eStructuralFeature instanceof EReference) {
			EReference eReference = (EReference)eStructuralFeature;
			if (eReference.isContainment()) {
				grammarAnalysis.addContainment(this, eReference);
			}
		}
		computeTargetRuleAnalyses(firstUnassignedRuleCall);
	}

	private void computeTargetRuleAnalyses(@NonNull AbstractElement terminal) {
		if ("ownedMultiplicity".equals(eStructuralFeature.getName())) {
			getClass();		// XXX debugging
		}
		if (terminal instanceof RuleCall) {
		//	AbstractRule terminalRule = XtextGrammarUtil.getRule((RuleCall)terminal);
			AbstractRule terminalRule = XtextGrammarUtil.getRule((RuleCall)terminal);
			AbstractRuleAnalysis terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule);
			if (terminalRuleAnalysis instanceof ParserRuleAnalysis) {
				for (@NonNull ParserRuleAnalysis ruleAnalysis : ((ParserRuleAnalysis)terminalRuleAnalysis).debugCalledRuleAnalysesClosure) { //getCallingRuleAnalysisClosure()) {
					targetRuleAnalyses.add(ruleAnalysis);
				}
			}
			else if (terminalRuleAnalysis != null) {
				targetRuleAnalyses.add(terminalRuleAnalysis);
			}
			else {
				throw new UnsupportedOperationException();
			}		// Keywords ???
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

	@Override
	public @NonNull Action getAssignment() {
		return action;
	}

	public @NonNull String getCardinality() {
		String cardinality = action.getCardinality();
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

	@Override
	public @NonNull EClass getEClass() {
		return (EClass) XtextGrammarUtil.getEClassifierScope(action);
	}

	public @NonNull EClass getEContainingClass() {
		return XtextGrammarUtil.getEContainingClass(eStructuralFeature);
	}

	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return MultiplicativeCardinality.toEnum(action);
	}

	@Override
	public @NonNull String getName() {
		return XtextGrammarUtil.getName(sourceRuleAnalysis.getRule()) + "-" + eStructuralFeature.getName();
	}

	public @NonNull ParserRuleAnalysis getSourceRuleAnalysis() {
		return sourceRuleAnalysis;
	}

	@Override
	public @NonNull List<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses() {
		return targetRuleAnalyses;
	}

//	public @Nullable AbstractRuleAnalysis getTerminalRuleAnalysis() {
//		return terminalRuleAnalysis;
//	}

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