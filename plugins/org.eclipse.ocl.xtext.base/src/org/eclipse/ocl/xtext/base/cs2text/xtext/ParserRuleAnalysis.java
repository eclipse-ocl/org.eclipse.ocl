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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedRuleCallsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeUnassignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedCurrentSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.ListOfListOfSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.ListOfSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.NullSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
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
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.util.XtextSwitch;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class ParserRuleAnalysis extends AbstractRuleAnalysis
{
	/**
	 * The ParserRuleSwitch supports the recursive transfprmation of a ParserRule AbstractElement to its
	 * disjoint normal form comprisising an outer disjunction of conjunctions of terms with cardinatlities.
	 * Some alternatives such as an an enumeration of keywords are aggregated as an inner alternative tio avoid
	 * the permutatioon of alternatives getting out of hand. Parser rule calls are flattened.
	 */
	protected static class ParserRuleSwitch extends XtextSwitch<@NonNull SerializationElement>
	{
		/**
		 * The analyzed rule
		 */
		protected final @NonNull ParserRuleAnalysis ruleAnalysis;

		/**
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull GrammarAnalysis grammarAnalysis;

		public ParserRuleSwitch(@NonNull ParserRuleAnalysis ruleAnalysis) {
			this.ruleAnalysis = ruleAnalysis;
			this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
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
				AssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(action);
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
			if (multiplicativeCardinality.isZeroOrMore()) {	// (A|B)* => A* | B*
				SerializationElement conjunction = new ListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
					SerializationElement frozen = alternativeSerializationElement.setMultiplicativeCardinality(MultiplicativeCardinality.ZERO_OR_MORE).freezeSequences(grammarAnalysis, alternatives);
					conjunction = conjunction.addConcatenation(frozen);
				}
				return conjunction;
			}
			else if (multiplicativeCardinality.isOneOrMore()) { 											// (A|B)+ => A+B* | A*B+
				ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement1 : alternativeSerializationElements) {
					SerializationElement conjunction = new ListOfSerializationNode();
					conjunction.addConcatenation(alternativeSerializationElement1);
					for (@NonNull SerializationElement alternativeSerializationElement2 : alternativeSerializationElements) {
						if (alternativeSerializationElement1 != alternativeSerializationElement2) {
							conjunction = conjunction.addConcatenation(alternativeSerializationElement2.setMultiplicativeCardinality(MultiplicativeCardinality.ZERO_OR_MORE));
						}
					}
					SerializationElement frozen = conjunction.freezeSequences(grammarAnalysis, alternatives);
					disjunction = disjunction.addConjunction(frozen);
				}
				return disjunction;
			}
			else if (multiplicativeCardinality.isZeroOrOne()) {	// (A|B)? => A|B|epsilon
				ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
					SerializationElement conjunction = new ListOfSerializationNode();
					conjunction = conjunction.addConcatenation(alternativeSerializationElement);
					SerializationElement frozen = conjunction.freezeSequences(grammarAnalysis, alternatives);
					disjunction = disjunction.addConjunction(frozen);
				}
				return disjunction.setMultiplicativeCardinality(MultiplicativeCardinality.ZERO_OR_MORE);
			}
			else { // multiplicativeCardinality.isOne()
				ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
					SerializationElement conjunction = new ListOfSerializationNode();
					conjunction = conjunction.addConcatenation(alternativeSerializationElement);
					SerializationElement frozen = conjunction.freezeSequences(grammarAnalysis, alternatives);
					disjunction = disjunction.addConjunction(frozen);
				}
				return disjunction.setMultiplicativeCardinality(MultiplicativeCardinality.ONE);
			}
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
				SerializationNode assignedAlternativeRuleCalls = doAssignedAlternativeRuleCalls(assignment, alternatives, multiplicativeCardinality);
				if (assignedAlternativeRuleCalls != null) {
					return assignedAlternativeRuleCalls;
				}
			/*	for (@NonNull AbstractElement alternative : XtextGrammarUtil.getElements(alternatives)) {
					if (content == null) {
						if (alternative instanceof RuleCall) {
							content = new AssignedRuleCallSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, grammarAnalysis.getRuleAnalysis(getRule((RuleCall)alternative)));
						}
						else if (alternative instanceof Keyword) {
							content = new AssignedKeywordSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, (Keyword)alternative);
						}
						else {
							throw new UnsupportedOperationException("Unsupported Assignment alternative terminal '" + alternative.eClass().getName() + "'");
						}
					//	content = doSwitch(alternative);
					}
					else if (content instanceof AbstractSerializationNode) {
						if (!((AbstractSerializationNode)content).addAlternative(alternative)) {
							content = null;
							break;
						}
					}
				/ *	Object nestedContentOrContents = doSwitch(alternative);
					if (alternative instanceof XtextAbstractContent) {
						addContent(contents, alternative);
					}
					else if (alternative instanceof RuleCall) {
						addContent(contents, alternative);
					}
					else {
						throw new UnsupportedOperationException("Unsupported Assignment alternative terminal '" + alternative.eClass().getName() + "'");
					} * /
				}
				if (content != null) {
					return content;
				} */
			//	return contents;
				throw new UnsupportedOperationException();
			//	return NullSerializationNode.INSTANCE;
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
			return serializationResult.freezeSequences(grammarAnalysis, group);
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

		private @Nullable SerializationNode doAssignedAlternativeRuleCalls(@NonNull Assignment assignment, @NonNull Alternatives alternatives, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
			List<@NonNull AbstractRuleAnalysis> calledRuleAnalyses = null;
			Iterable<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements(alternatives);
			for (@NonNull AbstractElement element : elements) {
				if ((element instanceof RuleCall) && (element.getCardinality() == null)) {
					if (calledRuleAnalyses == null) {
						calledRuleAnalyses = new ArrayList<>();
					}
					AbstractRule calledRule = XtextGrammarUtil.getRule((RuleCall) element);
					AbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(calledRule);
					calledRuleAnalyses.add(calledRuleAnalysis);
				}
			}
			if (calledRuleAnalyses == null) {
				return null;
			}
			AssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
			if (calledRuleAnalyses.size() == 1) {
				return new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, calledRuleAnalyses.get(0));
			}
			else {
				return new AlternativeAssignedRuleCallsSerializationNode(assignmentAnalysis, multiplicativeCardinality, calledRuleAnalyses);
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

	/**
	 * SerializationRuleComparator provides a stable comparison that may be used in a sort to
	 * prioritize simpler rules first. This avoids gratuittous punctuation around optional
	 * sequences of elements.
	 */
	protected static final class SerializationRuleComparator implements Comparator<@NonNull SerializationRule>
	{
		private Map<@NonNull SerializationRule, @NonNull Integer> rule2size = new HashMap<>();

		@Override
		public int compare(@NonNull SerializationRule rule1, @NonNull SerializationRule rule2) {
			int size1 = getSize(rule1);
			int size2 = getSize(rule2);
			if (size1 != size2) {
				return size1 - size2;
			}
			String string1 = rule1.toString();
			String string2 = rule2.toString();
			return string1.compareTo(string2);
		}

		private int getSize(@NonNull SerializationRule serializationRule) {
			Integer size = rule2size.get(serializationRule);
			if (size == null) {
				size = getSize(serializationRule.getRootSerializationNode());
				rule2size.put(serializationRule, size);
			}
			return size;
		}

		private int getSize(@NonNull SerializationNode parentSerializationNode) {
			int size = 0;
			if (parentSerializationNode instanceof SequenceSerializationNode) {
				for (@NonNull SerializationNode childSerializationNode : ((SequenceSerializationNode)parentSerializationNode).getSerializationNodes()) {
					size += 2 * getSize(childSerializationNode);		// 2 penalizes nesting
				}
			}
			else {
				size++;
			}
			return size;
		}
	}

	protected final @NonNull EClass eClass;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();
	private @Nullable List<@NonNull SerializationRule> serializationRules = null;

	public ParserRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRule parserRule,@NonNull EClass eClass) {
		super(grammarAnalysis, parserRule);
		this.eClass = eClass;
	}

	public void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eStructuralFeature);
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			eFeature2assignmentAnalyses.put(eStructuralFeature, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	/**
	 * Perform the analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void analyze() {
		if ("Base::MultiplicityCS".equals(getName())) {
			getClass(); // XXX debugging
		}
		for (EObject eObject : new TreeIterable(abstractRule, false)) {
			if (eObject instanceof RuleCall) {
				RuleCall ruleCall = (RuleCall)eObject;
				if (isFirstResultType(ruleCall)) {		// Re-use hierarchical switch flattenning
					AbstractRule derivedRule = XtextGrammarUtil.getRule(ruleCall);
					AbstractRuleAnalysis derivedRuleAnalysis = grammarAnalysis.getRuleAnalysis(derivedRule);
					derivedRuleAnalysis.addBaseRuleAnalysis(this);
				}
			}
		}
		List<@NonNull SerializationRule> serializationRules = new ArrayList<>();
		SerializationElement serializationResult = new ParserRuleSwitch(this).analyze();
		if (serializationResult.isListOfList()) {
			for (@NonNull List<@NonNull SerializationNode> serializationNodes : serializationResult.asListOfList().getLists()) {
				assert serializationNodes.size() == 1;
				SerializationRule serializationRule = new SerializationRule(this, serializationNodes.get(0));
				serializationRules.add(serializationRule);
			}
		}
		else if (serializationResult.isList()) {
			List<@NonNull SerializationNode> serializationNodes = serializationResult.asList().getNodes();
			assert serializationNodes.size() == 1;
			SerializationRule serializationRule = new SerializationRule(this, serializationNodes.get(0));
			serializationRules.add(serializationRule);
		}
		else if (serializationResult.isNode()) {
			SerializationRule serializationRule = new SerializationRule(this, serializationResult.asNode());
			serializationRules.add(serializationRule);
		}
		else {		// isNull()
			throw new IllegalStateException();
		}
		if (serializationRules.size() > 1) {
			Collections.sort(serializationRules, new SerializationRuleComparator());
		}
		this.serializationRules = serializationRules;
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
	 */
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
	}


	private boolean isFirstResultType(@NonNull AbstractElement element) {
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
	}

	public void preSerialize() {
		if ("Base::MultiplicityCS".equals(name)) {
			getClass();		// XXX
		}
		assert serializationRules != null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			serializationRule.getPreSerializer();
		}
	}
}