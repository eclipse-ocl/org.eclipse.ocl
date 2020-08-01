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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.DelegateSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationElement;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleComparator;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedRuleCallSerializationNode;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class ParserRuleAnalysis extends AbstractRuleAnalysis
{
	protected final @NonNull EClass eClass;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();
	private @Nullable List<@NonNull SerializationRule> serializationRules = null;
	private @Nullable Set<@NonNull ParserRuleAnalysis> callingRuleAnalyses = null;
	private @Nullable List<@NonNull ParserRuleAnalysis> callingRuleAnalysesClosure = null;	// XXX 2/3 of these closures should be redundant
	public final @NonNull List<@NonNull ParserRuleAnalysis> debugCalledRuleAnalysesClosure = new UniqueList<>();	// XXX 2/3 of these closures should be redundant
	private @Nullable List<@NonNull RuleCall> delegatingRuleCalls = null;
	private @Nullable List<@NonNull ParserRuleAnalysis> delegatedCalledRuleAnalysesClosure = null;	// XXX 2/3 of these closures should be redundant

	public ParserRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRule parserRule, @NonNull EClass eClass) {
		super(grammarAnalysis, parserRule);
		this.eClass = eClass;
	}

	protected void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		grammarAnalysis.addAssignmentAnalysis(assignmentAnalysis);
		EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eStructuralFeature);
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			eFeature2assignmentAnalyses.put(eStructuralFeature, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	protected void addCallingRuleAnalysis(@NonNull ParserRuleAnalysis callingRuleAnalysis) {
		Set<@NonNull ParserRuleAnalysis> callingRuleAnalyses2 = callingRuleAnalyses;
		if (callingRuleAnalyses2 == null) {
			callingRuleAnalyses = callingRuleAnalyses2 = new HashSet<>();
		}
		callingRuleAnalyses2.add(callingRuleAnalysis);
	}

	/**
	 * Perform the analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void analyze() {
		if ("EssentialOCL::SelfExpCS".equals(getName())) {
			getClass(); // XXX debugging
		}
	/*	for (EObject eObject : new TreeIterable(abstractRule, false)) {
			if (eObject instanceof RuleCall) {
				RuleCall ruleCall = (RuleCall)eObject;
				if (isFirstResultType(ruleCall)) {		// Re-use hierarchical switch flattenning
					AbstractRule derivedRule = XtextGrammarUtil.getRule(ruleCall);
					AbstractRuleAnalysis derivedRuleAnalysis = grammarAnalysis.getRuleAnalysis(derivedRule);
					derivedRuleAnalysis.addBaseRuleAnalysis(this);
				}
			}
		} */
		if ("OCLinEcore::TypedMultiplicityRefCS".equals(getName())) {
			getClass(); // XXX debugging
		}
		//
		//	Convert the parser element tree to a normalized/flattened disjunction of conjunctions of nodes.
		//
		ParserRuleSwitch parserRuleSwitch = new ParserRuleSwitch(this);
		SerializationElement serializationResult = parserRuleSwitch.analyze();
		//
		//	Convert the disjunction of conjunctions of nodes to one or more rules.
		//
		List<@NonNull SerializationRule> serializationRules = new ArrayList<>();
		if (serializationResult.isListOfList()) {
			for (@NonNull List<@NonNull SerializationNode> serializationNodes : serializationResult.asListOfList().getLists()) {
				SerializationNode serializationNode;
				if (serializationNodes.size() == 1) {
					serializationNode = serializationNodes.get(0);
				}
				else {
					CompoundElement alternatives = (CompoundElement)XtextGrammarUtil.getAlternatives(abstractRule);
					serializationNode = new SequenceSerializationNode(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE, serializationNodes);
				}
				createSerializationRules(serializationRules, serializationNode);
			}
		}
		else if (serializationResult.isList()) {
			List<@NonNull SerializationNode> serializationNodes = serializationResult.asList().getNodes();
			SerializationNode serializationNode;
			if (serializationNodes.size() == 1) {
				serializationNode = serializationNodes.get(0);
			}
			else {
				CompoundElement alternatives = (CompoundElement)XtextGrammarUtil.getAlternatives(abstractRule);
				serializationNode = new SequenceSerializationNode(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE, serializationNodes);
			}
			createSerializationRules(serializationRules, serializationNode);
		}
		else if (serializationResult.isNode()) {
			SerializationNode serializationNode = serializationResult.asNode();
			createSerializationRules(serializationRules, serializationNode);
		}
		else {		// isNull()
			throw new IllegalStateException();
		}
		if (serializationRules.size() > 1) {
			Collections.sort(serializationRules, new SerializationRuleComparator());
		}
		this.serializationRules = serializationRules;
	}

	/**
	 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
	 *	required behaviour of a current action.
	 */
	public @Nullable RuleCall analyzeActionsAndAssignments() {
		AbstractElement rootElement = XtextGrammarUtil.getAlternatives(getRule());
		this.delegatingRuleCalls  = analyzeDelegatingRuleCalls(rootElement);
		return analyzeActionsAndAssignments(rootElement, null);
	}

	/**
	 *	Return the RuleCalls that are invoked directly without any prefix/suffix terms..
	 */
	private @Nullable List<@NonNull RuleCall> analyzeDelegatingRuleCalls(@NonNull AbstractElement abstractElement) {
		if (abstractElement instanceof RuleCall) {
			RuleCall ruleCall = (RuleCall)abstractElement;
			return Collections.singletonList(ruleCall);
		}
		else if (abstractElement instanceof Alternatives) {
			List<@NonNull RuleCall> outerDelegatingRuleCalls = null;
			for (@NonNull AbstractElement nestedElement : XtextGrammarUtil.getElements((Alternatives)abstractElement)) {
				List<@NonNull RuleCall> innerDelegatingRuleCalls = analyzeDelegatingRuleCalls(nestedElement);
				if (innerDelegatingRuleCalls != null) {
					if (outerDelegatingRuleCalls == null) {
						outerDelegatingRuleCalls = new ArrayList<>();
					}
					outerDelegatingRuleCalls.addAll(innerDelegatingRuleCalls);
				}
			}
			return outerDelegatingRuleCalls;
		}
		else {		// The current rule calls are decorated and so cannot delegate
			return null;
		}
	}

	/**
	 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
	 *	required behaviour of a current action.
	 */
	private @Nullable RuleCall analyzeActionsAndAssignments(@NonNull AbstractElement abstractElement, @Nullable RuleCall firstUnassignedRuleCall) {
		if (abstractElement instanceof Assignment) {
			Assignment assignment = (Assignment)abstractElement;
			AssignmentAnalysis assignmentAnalysis = new AssignmentAnalysis(this, assignment);
			addAssignmentAnalysis(assignmentAnalysis);
		}
		else if (abstractElement instanceof Action) {
			Action action = (Action)abstractElement;
			String feature = action.getFeature();
			if (feature != null) {
				assert firstUnassignedRuleCall != null;
				AssignmentAnalysis assignmentAnalysis = new AssignmentAnalysis(this, action, firstUnassignedRuleCall);
				addAssignmentAnalysis(assignmentAnalysis);
			}
		}
		else if (abstractElement instanceof RuleCall) {
			assert firstUnassignedRuleCall == null;
			RuleCall ruleCall = (RuleCall)abstractElement;
			AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
			if (XtextGrammarUtil.getClassifier(XtextGrammarUtil.getType(calledRule)) instanceof EClass) {
				firstUnassignedRuleCall = ruleCall;
				ParserRuleAnalysis calledRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(calledRule);
				calledRuleAnalysis.addCallingRuleAnalysis(this);
			}
		}
		else if (abstractElement instanceof Alternatives) {
			for (@NonNull AbstractElement nestedElement : XtextGrammarUtil.getElements((Alternatives)abstractElement)) {
				analyzeActionsAndAssignments(nestedElement, firstUnassignedRuleCall);
			}
		}
		else if (abstractElement instanceof Group) {
			for (@NonNull AbstractElement nestedElement : XtextGrammarUtil.getElements((Group)abstractElement)) {
				firstUnassignedRuleCall = analyzeActionsAndAssignments(nestedElement, firstUnassignedRuleCall);
			}
		}
		return firstUnassignedRuleCall;
	}

	protected void createSerializationRules(@NonNull List<@NonNull SerializationRule> serializationRules, @NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
			ParserRuleAnalysis calledRuleAnalysis = (ParserRuleAnalysis) ((UnassignedRuleCallSerializationNode)serializationNode).getCalledRuleAnalysis();
			for (@NonNull SerializationRule calledSerializationRule : calledRuleAnalysis.getSerializationRules()) {
				BasicSerializationRule delegateSerializationRule = calledSerializationRule.getBasicSerializationRule();
				DelegateSerializationRule delegatingSerializationRule = new DelegateSerializationRule(this, delegateSerializationRule);
				serializationRules.add(delegatingSerializationRule);
			}
		}
		else {
			SerializationRule serializationRule = new BasicSerializationRule(this, serializationNode);
			serializationRules.add(serializationRule);
		}
	}

	public @NonNull Iterable<@NonNull ParserRuleAnalysis> getCallingRuleAnalysisClosure() {
		List<@NonNull ParserRuleAnalysis> callingRuleAnalysesClosureList = this.callingRuleAnalysesClosure;
		if (callingRuleAnalysesClosureList == null) {
			UniqueList<@NonNull ParserRuleAnalysis> callingRuleAnalysesClosureSet = new UniqueList<>();
			callingRuleAnalysesClosureSet.add(this);
			for (int i = 0; i < callingRuleAnalysesClosureSet.size(); i++) {
				ParserRuleAnalysis ruleAnalysis = callingRuleAnalysesClosureSet.get(i);
				Set<@NonNull ParserRuleAnalysis> callingRuleAnalyses = ruleAnalysis.callingRuleAnalyses;
				if (callingRuleAnalyses != null) {
					callingRuleAnalysesClosureSet.addAll(callingRuleAnalyses);
				}
			}
			callingRuleAnalysesClosureList = new ArrayList<>(callingRuleAnalysesClosureSet);
			Collections.sort(callingRuleAnalysesClosureList, NameUtil.NAMEABLE_COMPARATOR);
			this.callingRuleAnalysesClosure = callingRuleAnalysesClosureList;
			for (@NonNull ParserRuleAnalysis ruleAnalysis : callingRuleAnalysesClosureList) {
				ruleAnalysis.debugCalledRuleAnalysesClosure.add(this);
			}
		}
		return callingRuleAnalysesClosureList;
	}

	public @NonNull List<@NonNull ParserRuleAnalysis> getDelegatedCalledRuleAnalysesClosure() {
		List<@NonNull ParserRuleAnalysis> delegatedCalledRuleAnalysesClosure2 = delegatedCalledRuleAnalysesClosure;
		if (delegatedCalledRuleAnalysesClosure2 == null) {
			delegatedCalledRuleAnalysesClosure2 = new ArrayList<>();
			delegatedCalledRuleAnalysesClosure2.add(this);
			for (int i = 0; i < delegatedCalledRuleAnalysesClosure2.size(); i++) {
				ParserRuleAnalysis ruleAnalysis = delegatedCalledRuleAnalysesClosure2.get(i);
				List<@NonNull RuleCall> delegatingRuleCalls2 = ruleAnalysis.delegatingRuleCalls;
				if (delegatingRuleCalls2 != null) {
					for (@NonNull RuleCall ruleCall : delegatingRuleCalls2) {
						AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
						ParserRuleAnalysis calledRuleAnalysis = (ParserRuleAnalysis) grammarAnalysis.getRuleAnalysis(calledRule);
						for (@NonNull ParserRuleAnalysis calledRuleAnalysis2 : calledRuleAnalysis.getDelegatedCalledRuleAnalysesClosure()) {
							if (!delegatedCalledRuleAnalysesClosure2.contains(calledRuleAnalysis2)) {
								delegatedCalledRuleAnalysesClosure2.add(calledRuleAnalysis2);
							}
						}
					}
				}
			}
			Collections.sort(delegatedCalledRuleAnalysesClosure2, NameUtil.NAMEABLE_COMPARATOR);
			this.delegatedCalledRuleAnalysesClosure = delegatedCalledRuleAnalysesClosure2;
		}
		return delegatedCalledRuleAnalysesClosure2;
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> getEFeature2assignmentAnalyses() {
		return eFeature2assignmentAnalyses;
	}

	public @NonNull ParserRule getParserRule() {
		return (ParserRule) abstractRule;
	}

	public @NonNull EClass getReturnedEClass() {
		return eClass;
	}

	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
		if (serializationRules == null) {
			analyze();
		}
		assert serializationRules != null;
		return serializationRules;
	}

	/**
	 * Return true if the transitive descendants of element involve a RuleCall.
	 *
	private boolean hasResultType(@NonNull AbstractElement element) {
		if (element instanceof RuleCall) {
			return true;
		}
		if (element instanceof Group) {
			for (@NonNull AbstractElement childElement : XtextGrammarUtil.getElements((Group)element)) {
				return hasResultType(childElement);
			}
			return false;
		}
		if (element instanceof Alternatives) {
			for (@NonNull AbstractElement childElement : XtextGrammarUtil.getElements((Alternatives)element)) {
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
	} */


/*	private boolean isFirstResultType(@NonNull AbstractElement element) {
		EObject eContainer = element.eContainer();
		if (eContainer instanceof Group) {
			Group group = (Group)eContainer;
			List<@NonNull AbstractElement> siblings = XtextGrammarUtil.getElements(group);
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
	} */

	public void preSerialize() {
		if ("Base::MultiplicityCS".equals(name)) {
			getClass();		// XXX
		}
		assert serializationRules != null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
			basicSerializationRule.getStaticRuleMatch();
		}
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		List<@NonNull RuleCall> delegatingRuleCalls2 = delegatingRuleCalls;
		if (delegatingRuleCalls2 != null) {
			s.append(" <= ");
			boolean isFirst1 = true;
			for (@NonNull RuleCall ruleCall : delegatingRuleCalls2) {
				if (!isFirst1) {
					s.append(", ");
				}
				AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
				ParserRuleAnalysis callingRuleAnalysis = (ParserRuleAnalysis) grammarAnalysis.getRuleAnalysis(calledRule);
				s.append(callingRuleAnalysis.getName());
				isFirst1 = false;
			}
		}
		List<@NonNull ParserRuleAnalysis> callingRuleAnalysesClosure2 = delegatedCalledRuleAnalysesClosure != null ? delegatedCalledRuleAnalysesClosure : callingRuleAnalysesClosure;
		if (callingRuleAnalysesClosure2 != null) {
			s.append(" -> ");
			boolean isFirst1 = true;
			for (@NonNull ParserRuleAnalysis callingRuleAnalysis : callingRuleAnalysesClosure2) {
				if (!isFirst1) {
					s.append(", ");
				}
				s.append(callingRuleAnalysis.getName());
				isFirst1 = false;
			}
		}
/*		s.append(" <=> ");
		boolean isFirst2 = true;
		for (@NonNull EClassifier eClassifier : eClassifiers) {
			if (!isFirst2) {
				s.append(",");
			}
			s.append(eClassifier.getName());
			isFirst2 = false;
		} */
		return s.toString();
	}
}