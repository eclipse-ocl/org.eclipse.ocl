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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeUnassignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedCurrentSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.ListOfListOfSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.ListOfSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.NullSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationElement;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedRuleCallSerializationNode;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.util.XtextSwitch;

/**
 * The ParserRuleSwitch supports the recursive transfprmation of a ParserRule AbstractElement to its
 * disjoint normal form comprisising an outer disjunction of conjunctions of terms with cardinatlities.
 * Some alternatives such as an an enumeration of keywords are aggregated as an inner alternative tio avoid
 * the permutatioon of alternatives getting out of hand. Parser rule calls are flattened.
 */
public class ParserRuleSwitch extends XtextSwitch<@NonNull SerializationElement>
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The analyzed rule
	 */
	protected final @NonNull ParserRuleAnalysis ruleAnalysis;

	public ParserRuleSwitch(@NonNull ParserRuleAnalysis ruleAnalysis) {
		this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		this.ruleAnalysis = ruleAnalysis;
	}

	public @NonNull SerializationElement analyze() {
		AbstractElement rootElement = ruleAnalysis.getRule().getAlternatives();
		int classifierID = rootElement.eClass().getClassifierID();
		@SuppressWarnings("null") SerializationElement serializationNode = doSwitch(classifierID, rootElement);
		return serializationNode;
	}

	@Override
	public @NonNull SerializationElement caseAction(Action action) {
		assert action != null;
		String feature = action.getFeature();
		if (feature != null) {
			ActionAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(action);
			return new AssignedCurrentSerializationNode(assignmentAnalysis, MultiplicativeCardinality.toEnum(action));

		}
		return NullSerializationNode.INSTANCE;
	}

	@Override
	public @NonNull SerializationElement caseAlternatives(Alternatives alternatives) {
		assert alternatives != null;
		SerializationElement alternativeUnassignedKeywordsSerializationNode = doAlternativeUnassignedKeywords(alternatives);
		if (alternativeUnassignedKeywordsSerializationNode != null) {
			return alternativeUnassignedKeywordsSerializationNode;
		}
		//	SerializationElement alternativeUnassignedRuleCallsSerializationNode = doAlternativeUnassignedRuleCalls(alternatives);
		//	if (alternativeUnassignedRuleCallsSerializationNode != null) {
		//		return alternativeUnassignedRuleCallsSerializationNode;
		//	}
		MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(alternatives);
		List<@NonNull SerializationElement> alternativeSerializationElements = doAlternativeAssignedKeywords(alternatives, multiplicativeCardinality);
		for (@NonNull AbstractElement element : XtextGrammarUtil.getElements(alternatives)) {
			boolean doSwitchNeeded = true;
			if ((element instanceof Assignment) && (element.getCardinality() == null)) {
				AbstractElement terminal = ((Assignment)element).getTerminal();
				if ((terminal instanceof Keyword) && (terminal.getCardinality() == null))  {
					doSwitchNeeded = false;				// Already partially cached in eFeature2keywords
				}
			}
			if (doSwitchNeeded) {
				int classifierID = element.eClass().getClassifierID();
				alternativeSerializationElements.add(doSwitch(classifierID, element));
			}
		}
		return doAlternatives(alternatives, alternativeSerializationElements, multiplicativeCardinality);
	}

	@Override
	public @NonNull SerializationElement caseAssignment(Assignment assignment) {
		assert assignment != null;
		AssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
		MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(assignment);
		AbstractElement terminal = XtextGrammarUtil.getTerminal(assignment);
		if (terminal instanceof RuleCall) {
			AbstractRuleAnalysis ruleAnalysis2 = grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule((RuleCall)terminal));
			return new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, ruleAnalysis2);
		}
		else if (terminal instanceof Keyword) {
			return new AssignedKeywordSerializationNode(assignmentAnalysis, multiplicativeCardinality, (Keyword)terminal);
		}
		else if (terminal instanceof Alternatives) {
			Alternatives alternatives = (Alternatives)terminal;
			SerializationNode assignedAlternativeKeywords = doAssignedAlternativeKeywords(assignment, alternatives, multiplicativeCardinality);
			if (assignedAlternativeKeywords != null) {
				return assignedAlternativeKeywords;
			}
			List<@NonNull SerializationElement> alternativeSerializationElements = new ArrayList<>();
			for (@NonNull AbstractElement alternative : XtextGrammarUtil.getElements(alternatives)) {
				MultiplicativeCardinality cardinality = MultiplicativeCardinality.toEnum(alternative);
				assert cardinality.isOne();
				if (alternative instanceof RuleCall) {
					alternativeSerializationElements.add(new AssignedRuleCallSerializationNode(assignmentAnalysis, cardinality, grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule((RuleCall)alternative))));
				}
				else if (alternative instanceof Keyword) {
					alternativeSerializationElements.add(new AssignedKeywordSerializationNode(assignmentAnalysis, cardinality, (Keyword)alternative));
				}
				else {
					throw new UnsupportedOperationException("Unsupported Assignment alternative terminal '" + alternative.eClass().getName() + "'");
				}
			}
			return doAlternatives(alternatives, alternativeSerializationElements, multiplicativeCardinality);
		}
		else if (terminal instanceof CrossReference) {
			return new AssignedCrossReferenceSerializationNode(assignmentAnalysis, multiplicativeCardinality, (CrossReference)terminal);
		}
		else {
			throw new UnsupportedOperationException("Unsupported Assignment terminal '" + terminal.eClass().getName() + "'");
		}
	}

	/*	@Override
	public @NonNull SerializationNode caseCrossReference(CrossReference object) {
		return nullSerializationNode;
	} */

	@Override
	public @NonNull SerializationElement caseGroup(Group group) {
		assert group != null;
		SerializationElement serializationResult = new ListOfSerializationNode();
		for (@NonNull AbstractElement element : XtextGrammarUtil.getElements(group)) {		// XXX optimize the no alternatives case
			int classifierID = element.eClass().getClassifierID();
			@SuppressWarnings("null") SerializationElement serializationElement = doSwitch(classifierID, element);
			serializationResult = serializationResult.addConcatenation(serializationElement);
		}
		return serializationResult.freezeSequences(grammarAnalysis, group, MultiplicativeCardinality.toEnum(group));
	}

	@Override
	public @NonNull SerializationElement caseKeyword(Keyword keyword) {
		assert keyword != null;
		MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(keyword);
		if (multiplicativeCardinality.mayBeZero()) {
			return NullSerializationNode.INSTANCE;	// Skip gratuitous output
		}
		else {
			return new UnassignedKeywordSerializationNode(grammarAnalysis, keyword, multiplicativeCardinality);
		}
	}

	@Override
	public @NonNull SerializationElement caseRuleCall(RuleCall ruleCall) {
		assert ruleCall != null;
		assert !(ruleCall.eContainer() instanceof Assignment);
		AbstractRule abstractRule = XtextGrammarUtil.getRule(ruleCall);
		AbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(abstractRule);
		if (!(calledRuleAnalysis instanceof ParserRuleAnalysis)) {
			return NullSerializationNode.INSTANCE;
		}
		/*	ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
		for (@NonNull SerializationRule serializationRule : ((ParserRuleAnalysis)calledRuleAnalysis).getSerializationRules()) {
			disjunction.addConjunction(serializationRule.getRootSerializationNode());
			// XXX multipllicity
		}
		return disjunction; */
		MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(ruleCall);
		return new UnassignedRuleCallSerializationNode(grammarAnalysis, ruleCall, multiplicativeCardinality, calledRuleAnalysis);
	}

	/*		@Override
	public @NonNull SerializationNode caseTypeRef(TypeRef object) {
		return nullSerializationNode;
	} */

	@Override
	public @NonNull SerializationNode defaultCase(EObject object) {
		throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in ParserRuleSwitch");
	}

	private @NonNull List<@NonNull SerializationElement> doAlternativeAssignedKeywords(@NonNull Alternatives alternatives, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		Map<@NonNull EStructuralFeature, @NonNull List<@NonNull Keyword>> eFeature2keywords = null;
		Iterable<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements(alternatives);
		for (@NonNull AbstractElement element : elements) {
			if ((element instanceof Assignment) && (element.getCardinality() == null)) {
				Assignment assignment = (Assignment)element;
				AbstractElement terminal = assignment.getTerminal();
				if ((terminal instanceof Keyword) && (terminal.getCardinality() == null)) {
					if (eFeature2keywords == null) {
						eFeature2keywords = new HashMap<>();
					}
					AssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
					EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
					List<@NonNull Keyword> keywords = eFeature2keywords.get(eFeature);
					if (keywords == null) {
						keywords = new ArrayList<>();
						eFeature2keywords.put(eFeature, keywords);
					}
					keywords.add((Keyword)terminal);
				}
			}
		}
		List<@NonNull SerializationElement> alternativeSerializationElements = new ArrayList<>();
		if (eFeature2keywords != null) {
			for (@NonNull List<@NonNull Keyword> keywords : eFeature2keywords.values()) {
				Assignment assignment = ClassUtil.nonNullState((Assignment)keywords.get(0).eContainer());
				AssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
				if (keywords.size() == 1) {
					Keyword keyword = keywords.get(0);
					alternativeSerializationElements.add(new AssignedKeywordSerializationNode(assignmentAnalysis, multiplicativeCardinality, keyword));
				}
				else {
					alternativeSerializationElements.add(new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, multiplicativeCardinality, keywords));
				}
			}
		}
		return alternativeSerializationElements;
	}

	private @Nullable AlternativeUnassignedKeywordsSerializationNode doAlternativeUnassignedKeywords(@NonNull Alternatives alternatives) {
		Iterable<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements(alternatives);
		for (@NonNull AbstractElement element : elements) {
			if (!(element instanceof Keyword)) {
				return null;
			}
		}
		MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(alternatives);
		AlternativeUnassignedKeywordsSerializationNode alternativeUnassignedKeywordsSerializationNode = new AlternativeUnassignedKeywordsSerializationNode(grammarAnalysis, multiplicativeCardinality, null);
		for (@NonNull AbstractElement element : elements) {
			alternativeUnassignedKeywordsSerializationNode.addKeyword((Keyword)element);
		}
		return alternativeUnassignedKeywordsSerializationNode;
	}

	private @NonNull SerializationElement doAlternatives(@NonNull Alternatives alternatives, @NonNull List<@NonNull SerializationElement> alternativeSerializationElements, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality.isZeroOrMore()) {	// (A|B)* => A* | B*
			SerializationElement conjunction = new ListOfSerializationNode();
			for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
				SerializationElement frozen = alternativeSerializationElement.setMultiplicativeCardinality(grammarAnalysis, alternatives, MultiplicativeCardinality.ZERO_OR_MORE).freezeSequences(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE);
				conjunction = conjunction.addConcatenation(frozen);
			}
			return conjunction;
		}
		else if (multiplicativeCardinality.isOneOrMore()) { 											// (A|B)+ => A+B* | A*B+
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			for (@NonNull SerializationElement alternativeSerializationElement1 : alternativeSerializationElements) {
				SerializationElement conjunction = new ListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement2 : alternativeSerializationElements) {
					MultiplicativeCardinality termCardinality = alternativeSerializationElement1 != alternativeSerializationElement2 ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE;
					conjunction = conjunction.addConcatenation(alternativeSerializationElement2.setMultiplicativeCardinality(grammarAnalysis, alternatives, termCardinality));
				}
				SerializationElement frozen = conjunction.freezeSequences(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE);
				disjunction = disjunction.addConjunction(frozen);
			}
			return disjunction;
		}
		else if (multiplicativeCardinality.isZeroOrOne()) {	// (A|B)? => A|B|epsilon
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
				SerializationElement conjunction = new ListOfSerializationNode();
				conjunction = conjunction.addConcatenation(alternativeSerializationElement);
				SerializationElement frozen = conjunction.freezeSequences(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE);
				disjunction = disjunction.addConjunction(frozen);
			}
			disjunction = disjunction.addConjunction(NullSerializationNode.INSTANCE);
			return disjunction;//.setMultiplicativeCardinality(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE);
		//	return disjunction.setMultiplicativeCardinality(grammarAnalysis, alternatives, MultiplicativeCardinality.ZERO_OR_ONE);
		}
		else { // multiplicativeCardinality.isOne()
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
				SerializationElement conjunction = new ListOfSerializationNode();
				conjunction = conjunction.addConcatenation(alternativeSerializationElement);
				SerializationElement frozen = conjunction.freezeSequences(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE);
				disjunction = disjunction.addConjunction(frozen);
			}
			return disjunction;//.setMultiplicativeCardinality(grammarAnalysis, alternatives, MultiplicativeCardinality.ONE);
		}
	}

	/*	private @Nullable SerializationElement doAlternativeUnassignedRuleCalls(@NonNull Alternatives alternatives) {
		Iterable<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements(alternatives);
		List<@NonNull ParserRuleAnalysis> calledRuleAnalyses = new ArrayList<>();
		for (@NonNull AbstractElement element : elements) {
			if (!(element instanceof RuleCall)) {
				return null;
			}
			RuleCall ruleCall = (RuleCall)element;
			AbstractRule abstractRule = XtextGrammarUtil.getRule(ruleCall);
			AbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(abstractRule);
			if (!(calledRuleAnalysis instanceof ParserRuleAnalysis)) {
				return null;
			}
			calledRuleAnalyses.add((ParserRuleAnalysis) calledRuleAnalysis);
		}
		ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
		for (@NonNull ParserRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
			for (@NonNull SerializationRule serializationRule : calledRuleAnalysis.getSerializationRules()) {
				disjunction.addConjunction(serializationRule.getRootSerializationNode());
				// XXX multipllicity
			}
		}
		return disjunction;
	} */

	private @Nullable SerializationNode doAssignedAlternativeKeywords(@NonNull Assignment assignment, @NonNull Alternatives alternatives, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		List<@NonNull Keyword> keywords = null;
		Iterable<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements(alternatives);
		for (@NonNull AbstractElement element : elements) {
			if ((element instanceof Keyword) && (element.getCardinality() == null)) {
				if (keywords == null) {
					keywords = new ArrayList<>();
				}
				keywords.add((Keyword)element);
			}
		}
		if (keywords == null) {
			return null;
		}
		AssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
		if (keywords.size() == 1) {
			return new AssignedKeywordSerializationNode(assignmentAnalysis, multiplicativeCardinality, keywords.get(0));
		}
		else {
			return new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, multiplicativeCardinality, keywords);
		}
	}

	//	@Override
	//	public @NonNull SerializationElement doSwitch(EObject eObject) {
	//		int classifierID = eObject.eClass().getClassifierID();
	//		return ClassUtil.nonNullState(doSwitch(classifierID, eObject));
	//	}

	@Override
	public @NonNull String toString() {
		return ruleAnalysis.toString();
	}
}