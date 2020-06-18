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
package org.eclipse.ocl.xtext.base.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.TypeRef;

/**
 * An XtextGrammarAnalysis provides the extended analysis of an Xtext (multi-)grammar.
 */
public class XtextGrammarAnalysis
{
	/**
	 * An XtextAssignmentAnalysis provides the extended analysis of an Xtext Assignment
	 */
	public static class XtextAssignmentAnalysis implements Nameable
	{
		/**
		 * The rule analysis that uses this assignment to assign a target rule result.
		 */
		protected final @NonNull XtextParserRuleAnalysis sourceRuleAnalysis;

		/**
		 * The analyzed assignment.
		 */
		protected final @NonNull Assignment assignment;

		/**
		 * The overall grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		/**
		 * The assigned feature.
		 */
		protected final @NonNull EStructuralFeature eFeature;

		/**
		 * The rules that may produce a result compatible with the target.
		 */
		private final @NonNull List<@NonNull XtextAbstractRuleAnalysis> targetRuleAnalyses = new ArrayList<>();

		public XtextAssignmentAnalysis(@NonNull XtextParserRuleAnalysis sourceRuleAnalysis, @NonNull Assignment assignment) {
			this.sourceRuleAnalysis = sourceRuleAnalysis;
			this.assignment = assignment;
			this.grammarAnalysis = sourceRuleAnalysis.getGrammarAnalysis();
			String featureName = getFeature(assignment);
			EClass eClass = (EClass)getEClassifierScope(assignment);
			this.eFeature = XtextGrammarAnalysis.getEStructuralFeature(eClass, featureName);
			addTerminal(getTerminal(assignment));
		}

		private void addTerminal(@NonNull AbstractElement terminal) {
			if (terminal instanceof RuleCall) {
				addTerminalRuleCall((RuleCall)terminal);
			}
			else if (terminal instanceof Alternatives) {
				for (@NonNull AbstractElement element : getElements((Alternatives)terminal)) {
					addTerminal(element);
				}
			}
			else if (terminal instanceof CrossReference) {}
			else if (terminal instanceof Keyword) {}
			else {
				throw new UnsupportedOperationException();
			}
		}

		private void addTerminalRuleCall(@NonNull RuleCall terminal) {
			AbstractRule terminalRule = getRule(terminal);
			XtextAbstractRuleAnalysis terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule);
			targetRuleAnalyses.add(terminalRuleAnalysis);
		}

		public @NonNull Assignment getAssignment() {
			return assignment;
		}

		public @NonNull EClass getEContainingClass() {
			return XtextGrammarAnalysis.getEContainingClass(eFeature);
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eFeature;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarAnalysis.getName(sourceRuleAnalysis.getRule()) + "-" + eFeature.getName();
		}

		public @NonNull XtextParserRuleAnalysis getSourceRuleAnalysis() {
			return sourceRuleAnalysis;
		}

		public @NonNull List<@NonNull XtextAbstractRuleAnalysis> getTargetRuleAnalyses() {
			return targetRuleAnalyses;
		}

		/**
		 * Return true if sourceActualRuleAnalysis produces an acceptable result for use as the source of this assignment.
		 */
		public boolean sourceIsAssignableFrom(@NonNull XtextAbstractRuleAnalysis sourceActualRuleAnalysis) {
			return sourceActualRuleAnalysis.getBaseRuleAnalysisClosure().contains(this.sourceRuleAnalysis);
		}

		/**
		 * Return true if targetActualRuleAnalysis produces an acceptable result for use as the target of this assignment.
		 */
		public boolean targetIsAssignableFrom(@NonNull XtextAbstractRuleAnalysis targetActualRuleAnalysis) {
			Set<@NonNull XtextAbstractRuleAnalysis> targetActualRuleAnalysisClosure = targetActualRuleAnalysis.getBaseRuleAnalysisClosure();
			for (@NonNull XtextAbstractRuleAnalysis targetRuleAnalysis : this.targetRuleAnalyses) {
				if (targetActualRuleAnalysisClosure.contains(targetRuleAnalysis)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" : ");
			boolean isFirst = true;
			for (@NonNull XtextAbstractRuleAnalysis targetRuleAnalysis : targetRuleAnalyses) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(targetRuleAnalysis.getName());
				isFirst = false;
			}
			return s.toString();
		}
	}

	/**
	 * An XtextAbstractRuleAnalysis provides the extended analysis of an Xtext AbstractRule
	 */
	public static class XtextAbstractRuleAnalysis implements Nameable
	{
		/**#
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		/**
		 * The analyzed Xtext rule.
		 */
		protected final @NonNull AbstractRule abstractRule;

		/**
		 * The semi-qualified name of this rule (final part of grammar name and rule name).
		 */
		protected final @NonNull String name;

		/**
		 * The EClassifiers that this rule may produce.
		 */
		protected final @NonNull List<@NonNull EClassifier> eClassifiers = new ArrayList<>();

		/**
		 * RuleAnalyses that this RuleAnalysis may be directly used as an alternative for.
		 */
		private @Nullable List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = null;

		/**
		 * Lazily computed closure of RuleAnalyses that this RuleAnalysis may be used as an alternative for.
		 */
		private @Nullable UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysesClosure = null;

		public XtextAbstractRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull AbstractRule abstractRule) {
			this.grammarAnalysis = grammarAnalysis;
			this.abstractRule = abstractRule;
			String grammarName = getEContainingGrammar(abstractRule).getName();
			int index = grammarName.lastIndexOf('.');
			if (index >= 0) {
				grammarName = grammarName.substring(index+1);
			}
			this.name = grammarName + "::" + XtextGrammarAnalysis.getName(abstractRule);
		}

		public void addBaseRuleAnalysis(@NonNull XtextAbstractRuleAnalysis baseRuleAnalysis) {
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
			if (baseRuleAnalyses2 == null) {
				baseRuleAnalyses = baseRuleAnalyses2 = new ArrayList<>();
			}
			if (!baseRuleAnalyses2.contains(baseRuleAnalysis)) {
				baseRuleAnalyses2.add(baseRuleAnalysis);
			}
		}

		protected void addProducedTypeRef(@NonNull TypeRef type) {
			EClassifier eClassifier = getClassifier(type);
			if (!this.eClassifiers.contains(eClassifier)) {
			//	if ("AttributeCS".equals(eClassifier.getName())) {
			//		getClass();
			//	}
				this.eClassifiers.add(eClassifier);
			}
		}

		public @Nullable List<@NonNull XtextAbstractRuleAnalysis> basicGetBaseRuleAnalyses() {
			return baseRuleAnalyses;
		}

		public @NonNull UniqueList<@NonNull XtextAbstractRuleAnalysis> getBaseRuleAnalysisClosure() {
			UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysesClosure = this.baseRuleAnalysesClosure;
			if (baseRuleAnalysesClosure == null) {
				baseRuleAnalysesClosure = new UniqueList<>();
				baseRuleAnalysesClosure.add(this);
				for (int i = 0; i < baseRuleAnalysesClosure.size(); i++) {
					XtextAbstractRuleAnalysis ruleAnalysis = baseRuleAnalysesClosure.get(i);
					List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = ruleAnalysis.basicGetBaseRuleAnalyses();
					if (baseRuleAnalyses != null) {
						baseRuleAnalysesClosure.addAll(baseRuleAnalyses);
					}
				}
			}
			Collections.sort(baseRuleAnalysesClosure, NameUtil.NAMEABLE_COMPARATOR);
			this.baseRuleAnalysesClosure = baseRuleAnalysesClosure;
			return baseRuleAnalysesClosure;
		}

		public @NonNull List<@NonNull EClassifier> getEClassifiers() {
			return eClassifiers;
		}

		public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
			return grammarAnalysis;
		}

		@Override
		public @NonNull String getName() {
			return name;
		}

		public @NonNull AbstractRule getRule() {
			return abstractRule;
		}

		public @NonNull String getRuleName() {
			return XtextGrammarAnalysis.getName(abstractRule);
		}

		/**
		 * Perform the inter analysis to determine the base rule closure.
		 *
		public void interAnalyze() {
			if ("TypedTypeRefCS".equals(abstractRule.getName())) {
				getClass(); //XXX
			}
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
			if (baseRuleAnalyses2 != null) {
				UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysisClosure = new UniqueList<>(baseRuleAnalyses2);
				for (int i = 0; i < baseRuleAnalysisClosure.size(); i++) {
					XtextAbstractRuleAnalysis baseRuleAnalysis = baseRuleAnalysisClosure.get(i);
					List<@NonNull XtextAbstractRuleAnalysis> nestedBaseRuleAnalyses = baseRuleAnalysis.getBaseRuleAnalyses();
					if (nestedBaseRuleAnalyses != null) {
						baseRuleAnalysisClosure.addAll(nestedBaseRuleAnalyses);
					}
				}
				this.baseRuleAnalyses = baseRuleAnalysisClosure;
			}
		}*/

		/**
		 * Perform the intra analysis to determine the locally produced EClassifiers and local base rules.
		 */
		protected void intraAnalyze() {
			addProducedTypeRef(getType(abstractRule));
			for (EObject eObject : new TreeIterable(abstractRule, false)) {
				if (eObject instanceof Action) {
					Action action = (Action)eObject;
				//	if (isFirstResultType(action)) {
						addProducedTypeRef(getType(action));
				//	}
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					if (isFirstResultType(ruleCall)) {
						AbstractRule derivedRule = XtextGrammarAnalysis.getRule(ruleCall);
						XtextAbstractRuleAnalysis derivedRuleAnalysis = grammarAnalysis.getRuleAnalysis(derivedRule);
						derivedRuleAnalysis.addBaseRuleAnalysis(this);
					}
				}
			}
		}

		/**
		 * Return true if the transitive descendants of element involve a RuleCall.
		 */
		private boolean hasResultType(@NonNull AbstractElement element) {
			if (element instanceof RuleCall) {
				return true;
			}
			if (element instanceof Group) {
				for (@NonNull AbstractElement childElement : getElements((Group)element)) {
					return hasResultType(childElement);
				}
				return false;
			}
			if (element instanceof Alternatives) {
				for (@NonNull AbstractElement childElement : getElements((Alternatives)element)) {
					return hasResultType(childElement);
				}
				return false;
			}
			if (element instanceof Action) {
				return true;
			}
			if (element instanceof Keyword) {
				return false;
			}
			if (element instanceof Assignment) {
				return true;
			}
			throw new UnsupportedOperationException();
		}

		private boolean isFirstResultType(@NonNull AbstractElement element) {
			EObject eContainer = element.eContainer();
			if (eContainer instanceof Group) {
				Group group = (Group)eContainer;
				List<@NonNull AbstractElement> siblings = getElements(group);
				int index = siblings.indexOf(element);
				for (int i = 0; i < index; i++) {
					AbstractElement predecessor = siblings.get(i);
					if (hasResultType(predecessor)) {
						return false;
					}
				}
			}
			else if (eContainer instanceof Alternatives) {}
			else if (eContainer instanceof Assignment) {
				return false;
			}
			else if (eContainer instanceof CrossReference) {
				return false;
			}
			else if (eContainer instanceof AbstractRule) {
				return true;
			}
			else {
				throw new UnsupportedOperationException();
			}
			return isFirstResultType((AbstractElement)eContainer);
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" -> ");
			boolean isFirst1 = true;
			for (@NonNull XtextAbstractRuleAnalysis baseRuleAnalyses : getBaseRuleAnalysisClosure()) {
				if (!isFirst1) {
					s.append(",");
				}
				s.append(baseRuleAnalyses.getName());
				isFirst1 = false;
			}
			s.append(" <=> ");
			boolean isFirst2 = true;
			for (@NonNull EClassifier eClassifier : eClassifiers) {
				if (!isFirst2) {
					s.append(",");
				}
				s.append(eClassifier.getName());
				isFirst2 = false;
			}
			return s.toString();
		}
	}

	/**
	 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
	 */
	public static class XtextParserRuleAnalysis extends XtextAbstractRuleAnalysis
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

	/**
	 * An XtextTerminalRuleAnalysis provides the extended analysis of an Xtext TerminalRule
	 */
	public static class XtextTerminalRuleAnalysis extends XtextAbstractRuleAnalysis
	{
		public XtextTerminalRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull TerminalRule terminalRule) {
			super(grammarAnalysis, terminalRule);
		}

		public @NonNull TerminalRule getTerminalRule() {
			return (TerminalRule)abstractRule;
		}
	}

	public static @NonNull EClassifier getClassifier(TypeRef type) {
		return ClassUtil.nonNullState(type.getClassifier());
	}

	public static @NonNull EClassifier getEClassifierScope(@NonNull AbstractElement abstractElement) {
		TypeRef type = null;
		for (EObject eObject = abstractElement, eChild = null; (type == null) && (eObject != null); eChild = eObject, eObject = eObject.eContainer()) {
			if (eObject instanceof ParserRule) {
				type = ((ParserRule)eObject).getType();
			}
			else if (eObject instanceof Group) {
				List<@NonNull AbstractElement> elements = getElements((Group)eObject);
				int index = elements.indexOf(eChild);
				assert index >= 0;
				for (int i = index; --i >= 0; ) {
					AbstractElement element = elements.get(i);
					if (element instanceof Action) {
						type = ((Action)element).getType();
						break;
					}
				}
			}
		}
		if (type != null) {
			return getClassifier(type);
		}
		throw new IllegalStateException();
	}

	public static @NonNull EClass getEContainingClass(@NonNull EStructuralFeature eFeature) {
		return ClassUtil.nonNullState(eFeature.getEContainingClass());
	}

	public static @NonNull Grammar getEContainingGrammar(@NonNull EObject eObject) {
		for (EObject eCursor = eObject; (eCursor != null); eCursor = eCursor.eContainer()) {
			if (eCursor instanceof Grammar) {
				return (Grammar)eCursor;
			}
		}
		throw new IllegalStateException();
	}

	public static @NonNull EStructuralFeature getEStructuralFeature(@NonNull EClass eClass, @NonNull String featureName) {
		return ClassUtil.nonNullState(eClass.getEStructuralFeature(featureName));
	}

	public static @NonNull List<@NonNull AbstractElement> getElements(@NonNull Alternatives alternatives) {
		return ClassUtil.nullFree(alternatives.getElements());
	}

	public static @NonNull List<@NonNull AbstractElement> getElements(@NonNull Group group) {
		return ClassUtil.nullFree(group.getElements());
	}

	public static @NonNull String getFeature(@NonNull Assignment assignment) {
		return ClassUtil.nonNullState(assignment.getFeature());
	}

	public static @NonNull String getName(@NonNull AbstractRule abstractRule) {
		return ClassUtil.nonNullState(abstractRule.getName());
	}

	public static @NonNull AbstractRule getRule(@NonNull RuleCall ruleCall) {
		return ClassUtil.nonNullState(ruleCall.getRule());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull Assignment assignment) {
		return ClassUtil.nonNullState(assignment.getTerminal());
	}

	public static @NonNull TypeRef getType(@NonNull AbstractRule abstractRule) {
		return ClassUtil.nonNullState(abstractRule.getType());
	}

	public static @NonNull TypeRef getType(@NonNull Action action) {
		return ClassUtil.nonNullState(action.getType());
	}

	/**
	 * The (multi-)grammar model.
	 */
	protected final @NonNull AbstractGrammarResource grammarResource;

	/**
	 * The rule analysis for each rule.
	 */
	private @Nullable Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = null;

	/**
	 * The possible assignment analyses for containment EReference.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> containment2assignmentAnalyses = null;

	/**
	 * The possible producing rule analyses for each EClassifier. This analysis excludes overrides.
	 */
	private @Nullable Map<@NonNull EClassifier, List<@NonNull XtextAbstractRuleAnalysis>> eClassifier2ruleAnalyses = null;

	public XtextGrammarAnalysis(@NonNull AbstractGrammarResource grammarResource) {
		this.grammarResource = grammarResource;
	}

	/**
	 * Perform the analysis to determine and populate thae Assignment and Rile analyses.
	 */
	public void analyze() {
		Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls = new HashMap<>();
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = analyzeRuleNames(rule2ruleCalls);
		Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = createRuleAnalyses(ruleName2rules, rule2ruleCalls);
		this.rule2ruleAnalysis = rule2ruleAnalysis;
		this.containment2assignmentAnalyses = analyzeAssignments(rule2ruleAnalysis);
		Iterable<@NonNull XtextAbstractRuleAnalysis> ruleAnalyses = rule2ruleAnalysis.values();
		//
		// Perform the intra rule analysis to determine the locally produced EClassifiers and local base rules.
		//
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			abstractRuleAnalysis.intraAnalyze();
		}
		this.eClassifier2ruleAnalyses = analyzeProductions(rule2ruleAnalysis);
		//
		// Perform the inter rule analysis to determine the base rule closure.
		/*
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			abstractRuleAnalysis.interAnalyze();
		} */
	}

	/**
	 *	Identify the rules for each rule name and the ruleCalls from each rule.
	 */
	protected @NonNull Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> analyzeAssignments(
			@NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis) {
		Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> containment2assignmentAnalyses = new HashMap<>();
		/**
		 * The assignment analysis for each assignment.
		 */
		Map<@NonNull Assignment, @NonNull XtextAssignmentAnalysis> assignment2assignmentAnalysis = new HashMap<>();
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			AbstractRule abstractRule = abstractRuleAnalysis.getRule();
			for (@NonNull EObject eObject : new TreeIterable(abstractRule, false)) {
				if (eObject instanceof Assignment) {
					Assignment assignment = (Assignment)eObject;
					XtextParserRuleAnalysis parserRuleAnalysis = (XtextParserRuleAnalysis)getRuleAnalysis(assignment);
					XtextAssignmentAnalysis assignmentAnalysis = new XtextAssignmentAnalysis(parserRuleAnalysis, assignment);
					assignment2assignmentAnalysis.put(assignment, assignmentAnalysis);
					parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
					EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
					if (eFeature instanceof EReference) {
						EReference eReference = (EReference)eFeature;
						if (eReference.isContainment()) {
							List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = containment2assignmentAnalyses.get(eReference);
							if (assignmentAnalyses == null) {
								assignmentAnalyses = new ArrayList<>();
								containment2assignmentAnalyses.put(eReference, assignmentAnalyses);
							}
							assignmentAnalyses.add(assignmentAnalysis);
						}
					}
				}
			}
		}
		return containment2assignmentAnalyses;
	}

	/**
	 *	Identify the production rule(s) for each EClassifier.
	 */
	protected @NonNull Map<@NonNull EClassifier, @NonNull List<@NonNull XtextAbstractRuleAnalysis>> analyzeProductions(
			@NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis) {
		Map<@NonNull EClassifier, @NonNull List<@NonNull XtextAbstractRuleAnalysis>> eClassifier2ruleAnalyses = new HashMap<>();
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			for (@NonNull EClassifier eClassifier : abstractRuleAnalysis.getEClassifiers()) {
				List<@NonNull XtextAbstractRuleAnalysis> ruleAnalyses = eClassifier2ruleAnalyses.get(eClassifier);
				if (ruleAnalyses == null) {
					ruleAnalyses = new ArrayList<>();
					eClassifier2ruleAnalyses.put(eClassifier, ruleAnalyses);
				}
				ruleAnalyses.add(abstractRuleAnalysis);
			}
		}
		return eClassifier2ruleAnalyses;
	}

	/**
	 *	Return the rules for each rule name and populate the rule2ruleCalls from each rule.
	 */
	protected @NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> analyzeRuleNames(
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = new HashMap<>();
		for (@NonNull EObject eObject : new TreeIterable(grammarResource)) {
			if (eObject instanceof AbstractRule) {
				AbstractRule abstractRule = (AbstractRule)eObject;
				String ruleName = getName(abstractRule);
				List<@NonNull AbstractRule> rules = ruleName2rules.get(ruleName);
				if (rules == null) {
					rules = new ArrayList<>();
					ruleName2rules.put(ruleName, rules);
				}
				rules.add(abstractRule);
			}
			else if (eObject instanceof RuleCall) {
				RuleCall ruleCall = (RuleCall)eObject;
				AbstractRule rule = getRule(ruleCall);
				List<@NonNull RuleCall> ruleCalls = rule2ruleCalls.get(rule);
				if (ruleCalls == null) {
					ruleCalls = new ArrayList<>();
					rule2ruleCalls.put(rule, ruleCalls);
				}
				ruleCalls.add(ruleCall);
			}
		}
		return ruleName2rules;
	}

	/**
	 *	Create a RuleAnalysis for each distinct name.
	 */
	protected @NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> createRuleAnalyses(
			@NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules,
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = new HashMap<>();
		List<@NonNull String> ruleNames = new ArrayList<>(ruleName2rules.keySet());
		Collections.sort(ruleNames);
		for (@NonNull String ruleName : ruleNames) {
			List<@NonNull AbstractRule> rules = ruleName2rules.get(ruleName);
			assert rules != null;
			AbstractRule activeRule = null;
			if (rules.size() == 1) {
				activeRule = rules.get(0);
			}
			else {
				for (@NonNull AbstractRule rule : rules) {
					List<@NonNull RuleCall> ruleCalls = rule2ruleCalls.get(rule);
					if (ruleCalls != null) {
						if (activeRule != null) {
							throw new IllegalStateException("Duplicate overridden rule '" + ruleName + "'");
						}
						activeRule = rule;
					}
				}
			}
			if (activeRule == null) {
				throw new IllegalStateException("No unique rule '" + ruleName + "'");
			}
			XtextAbstractRuleAnalysis ruleAnalysis;
			if (activeRule instanceof ParserRule) {
				ruleAnalysis = new XtextParserRuleAnalysis(this, (ParserRule)activeRule); //, activeRuleCalls);
			}
			else if (activeRule instanceof TerminalRule) {
				ruleAnalysis = new XtextTerminalRuleAnalysis(this, (TerminalRule)activeRule); //, activeRuleCalls);
			}
			else {
				throw new UnsupportedOperationException();
			}
			rule2ruleAnalysis.put(activeRule, ruleAnalysis);
		}
		return rule2ruleAnalysis;
	}

	public @NonNull List<@NonNull XtextAssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull List<@NonNull XtextAbstractRuleAnalysis> getProducingRuleAnalyses(@NonNull EClassifier eClassifier) {
		assert eClassifier2ruleAnalyses != null;
		return ClassUtil.nonNullState(eClassifier2ruleAnalyses.get(eClassifier));
	}

	public @NonNull XtextAbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractElement abstractElement) {
		for (EObject eObject = abstractElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof AbstractRule) {
				return getRuleAnalysis((AbstractRule)eObject);
			}
		}
		throw new IllegalStateException();
	}

	public @NonNull XtextAbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		assert rule2ruleAnalysis != null;
		return ClassUtil.nonNullState(rule2ruleAnalysis.get(abstractRule));
	}

	@Override
	public @NonNull String toString() {
		Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = this.rule2ruleAnalysis;
		if (rule2ruleAnalysis == null) {
			return "<<not-ready>>";
		}
		StringBuilder s = new StringBuilder();
		s.append("Xtext production rule -> Xtext base rules <=> User EClass - User EStructuralFeatures");
		List<@NonNull XtextAbstractRuleAnalysis> abstractRuleAnalyses = new ArrayList<>(rule2ruleAnalysis.values());
		Collections.sort(abstractRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : abstractRuleAnalyses) {
			s.append("\n\t");
			s.append(abstractRuleAnalysis);
			if (abstractRuleAnalysis instanceof XtextParserRuleAnalysis) {
				XtextParserRuleAnalysis parserRuleAnalysis = (XtextParserRuleAnalysis)abstractRuleAnalysis;
				s.append(" -");
				Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = parserRuleAnalysis.getEFeature2assignmentAnalyses();
				List<@NonNull EStructuralFeature> eFeatures = new ArrayList<>(eFeature2assignmentAnalyses.keySet());
				Collections.sort(eFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
				boolean isFirstFeature = true;
				for (@NonNull EStructuralFeature eFeature : eFeatures) {
					if (isFirstFeature) {
						s.append(" ");
					}
					else {
						s.append(",");
					}
					List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eFeature);
					assert assignmentAnalyses != null;
					int size = assignmentAnalyses.size();
					if (size != 1) {
						s.append(size);
						s.append("*");
					}
					s.append(eFeature.getName());
					isFirstFeature = false;
				}
			}
		}
		s.append("\n\nUser EClass <=> Active Xtext production rule(s)");
		Map<@NonNull EClassifier, List<@NonNull XtextAbstractRuleAnalysis>> eClassifier2ruleAnalyses2 = eClassifier2ruleAnalyses;
		assert eClassifier2ruleAnalyses2 != null;
		List<@NonNull EClassifier> eClassifiers2 = new ArrayList<>(eClassifier2ruleAnalyses2.keySet());
		Collections.sort(eClassifiers2, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EClassifier eClassifier : eClassifiers2) {
			List<@NonNull XtextAbstractRuleAnalysis> parserRuleAnalyses2 = new ArrayList<>(eClassifier2ruleAnalyses2.get(eClassifier));
		//	assert parserRuleAnalyses2 != null;
			Collections.sort(parserRuleAnalyses2, NameUtil.NAMEABLE_COMPARATOR);
			s.append("\n\t");;
			s.append(eClassifier.getName());
			s.append(" <=>");;
			for (@NonNull XtextAbstractRuleAnalysis parserRuleAnalysis : parserRuleAnalyses2) {
				s.append(" ");;
				s.append(parserRuleAnalysis.getName());;
			}
		}
		return s.toString();
	}
}
