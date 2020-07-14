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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
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
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.util.XtextSwitch;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class XtextParserRuleAnalysis extends XtextAbstractRuleAnalysis
{
	public static class ParserRuleSwitch extends XtextSwitch<@NonNull SerializationNode>
	{
		/**
		 * The analyzed rule
		 */
		protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;

		/**
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		private @Nullable NullSerializationNode nullSerializationNode = null;

		public ParserRuleSwitch(@NonNull XtextParserRuleAnalysis ruleAnalysis) {
			this.ruleAnalysis = ruleAnalysis;
			this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		}

		public @NonNull SerializationNode analyze() {
			AbstractElement rootElement = ruleAnalysis.getRule().getAlternatives();
			return doSwitch(rootElement);
		}

		@Override
		public @NonNull SerializationNode caseAction(Action action) {
			assert action != null;
			String feature = action.getFeature();
			if (feature != null) {
				XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(action);
				return new AssignedCurrentSerializationNode(assignmentAnalysis, MultiplicativeCardinality.toEnum(action));

			}
			return getNullSerializationNode();
		}

		@Override
		public @NonNull SerializationNode caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			AlternativeUnassignedKeywordsSerializationNode alternativeUnassignedKeywordsSerializationNode = doAlternativeUnassignedKeywords(alternatives);
			if (alternativeUnassignedKeywordsSerializationNode != null) {
				return alternativeUnassignedKeywordsSerializationNode;
			}
			Map<@NonNull EStructuralFeature, @NonNull List<@NonNull Keyword>> eFeature2keywords = doAlternativeAssignedKeywords(alternatives);
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(alternatives);
			List<@NonNull SerializationNode> serializationNodes = new ArrayList<>();
			for (@NonNull AbstractElement element : XtextGrammarUtil.getElements(alternatives)) {
				boolean doSwitchNeeded = true;
				if ((element instanceof Assignment) && (element.getCardinality() == null)) {
					AbstractElement terminal = ((Assignment)element).getTerminal();
					if ((terminal instanceof Keyword) && (terminal.getCardinality() == null))  {
						doSwitchNeeded = false;				// Already partially cached in eFeature2keywords
					}
				}
				if (doSwitchNeeded) {
					serializationNodes.add(doSwitch(element));
				}
			}
			if (eFeature2keywords != null) {
				for (Map.Entry<@NonNull EStructuralFeature, @NonNull List<@NonNull Keyword>> entry : eFeature2keywords.entrySet()) {
					EStructuralFeature eFeature = entry.getKey();
					List<@NonNull Keyword> keywords = entry.getValue();
					XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis((Assignment)keywords.get(0).eContainer());
					if (keywords.size() == 1) {
						Keyword keyword = keywords.get(0);
						serializationNodes.add(new AssignedKeywordSerializationNode(assignmentAnalysis, multiplicativeCardinality, keyword));
					}
					else {
						serializationNodes.add(new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, multiplicativeCardinality, keywords));
					}
				}
			}
			if (serializationNodes.size() == 1) {
				return serializationNodes.get(0);
			}
			else {
				return new AlternativesSerializationNode(ruleAnalysis, alternatives, multiplicativeCardinality, serializationNodes);
			}
		}

		@Override
		public @NonNull SerializationNode caseAssignment(Assignment assignment) {
			assert assignment != null;
			XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(assignment);
			AbstractElement terminal = XtextGrammarUtil.getTerminal(assignment);
			if (terminal instanceof RuleCall) {
				XtextAbstractRuleAnalysis ruleAnalysis2 = grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule((RuleCall)terminal));
				return new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, ruleAnalysis2);
			}
			else if (terminal instanceof Keyword) {
				return new AssignedKeywordSerializationNode(assignmentAnalysis, multiplicativeCardinality, (Keyword)terminal);
			}
			else if (terminal instanceof Alternatives) {
				Alternatives alternatives = (Alternatives)terminal;
			//	List<@NonNull XtextAbstractContent> contents = new ArrayList<>();
				SerializationNode content = null;
		/*		for (@NonNull AbstractElement alternative : getElements(alternatives)) {
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
				return getNullSerializationNode();
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
		public @NonNull SerializationNode caseGroup(Group group) {
			assert group != null;
			List<@NonNull SerializationNode> serializationNodes = new ArrayList<>();
			for (@NonNull AbstractElement element : XtextGrammarUtil.getElements(group)) {		// XXX optimize the no alternatives case
				SerializationNode serializationNode = doSwitch(element);
				if (!serializationNode.isNull()) {
					serializationNodes.add(serializationNode);
				}
			}
			return new SequenceSerializationNode(ruleAnalysis, group, MultiplicativeCardinality.toEnum(group), serializationNodes);
		}

		@Override
		public @NonNull SerializationNode caseKeyword(Keyword keyword) {
			assert keyword != null;
			return new UnassignedKeywordSerializationNode(ruleAnalysis, keyword, MultiplicativeCardinality.toEnum(keyword));
		}

		@Override
		public @NonNull SerializationNode caseRuleCall(RuleCall ruleCall) {
			assert ruleCall != null;
			assert !(ruleCall.eContainer() instanceof Assignment);
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(ruleCall);
			XtextAbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule(ruleCall));
			return new UnassignedRuleCallSerializationNode(ruleAnalysis, ruleCall, multiplicativeCardinality, calledRuleAnalysis);
		}

/*		@Override
		public @NonNull SerializationNode caseTypeRef(TypeRef object) {
			return nullSerializationNode;
		} */

		@Override
		public @NonNull SerializationNode defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in ParserRuleSwitch");
		}

		private @Nullable Map<@NonNull EStructuralFeature, @NonNull List<@NonNull Keyword>> doAlternativeAssignedKeywords(@NonNull Alternatives alternatives) {
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
						XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
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
			return eFeature2keywords;
		/*	assert uniqueEFeature != null;
			EClass eContainingClass = (EClass) XtextGrammarUtil.getEClassifierScope(elements.iterator().next());
			List<@NonNull AssignedKeywordSerializationNode> alternativeNodes = new ArrayList<>();
			for (@NonNull AbstractElement element : elements) {
				AbstractElement terminal = XtextGrammarUtil.getTerminal(((Assignment)element));
				MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(element);
				AssignedKeywordSerializationNode assignedKeywordSerializationNode = new AssignedKeywordSerializationNode(ruleAnalysis, eContainingClass, uniqueEFeature, multiplicativeCardinality, (Keyword)terminal);
				alternativeNodes.add(assignedKeywordSerializationNode);
			}
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(alternatives);
		//	EClass eContainingClass = (EClass) XtextGrammarUtil.getEClassifierScope(elements.iterator().next());
			AlternativeAssignedKeywordsSerializationNode alternativeAssignedKeywordsSerializationNode = new AlternativeAssignedKeywordsSerializationNode(ruleAnalysis, eContainingClass, uniqueEFeature, multiplicativeCardinality, alternativeNodes);
		/ *	for (@NonNull AbstractElement element : elements) {
				Assignment assignment = (Assignment)element;
				Keyword keyword = (Keyword)assignment.getTerminal();
				Assignment assignment = (Assignment)element;
				XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
				EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
				AbstractElement terminal = assignment.getTerminal();
				if (terminal instanceof Keyword) {
					Keyword keyword = (Keyword)terminal;
					if (eFeature2keywordsSerializationNode == null) {
						eFeature2keywordsSerializationNode = new HashMap<>();
					}
					AlternativeAssignedKeywordsSerializationNode serializationNode = eFeature2keywordsSerializationNode.get(eFeature);
					if (serializationNode == null) {
						EClass eContainingClass = (EClass) XtextGrammarUtil.getEClassifierScope(assignment);
						serializationNode = new AlternativeAssignedKeywordsSerializationNode(ruleAnalysis, eContainingClass, eFeature, multiplicativeCardinality);
						eFeature2keywordsSerializationNode.put(eFeature, serializationNode);
						serializationNodes.add(serializationNode);
					}
					serializationNode.addKeyword(keyword);
					doSwitchNeeded = false;
				}
			} * /
			return alternativeAssignedKeywordsSerializationNode; */
		}


		private @Nullable AlternativeUnassignedKeywordsSerializationNode doAlternativeUnassignedKeywords(@NonNull Alternatives alternatives) {
			Iterable<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements(alternatives);
			for (@NonNull AbstractElement element : elements) {
				if (!(element instanceof Keyword)) {
					return null;
				}
			}
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(alternatives);
			AlternativeUnassignedKeywordsSerializationNode alternativeUnassignedKeywordsSerializationNode = new AlternativeUnassignedKeywordsSerializationNode(ruleAnalysis, multiplicativeCardinality, null);
			for (@NonNull AbstractElement element : elements) {
				alternativeUnassignedKeywordsSerializationNode.addKeyword((Keyword)element);
			}
			return alternativeUnassignedKeywordsSerializationNode;
		}

		@Override
		public @NonNull SerializationNode doSwitch(EObject eObject) {
			int classifierID = eObject.eClass().getClassifierID();
			return ClassUtil.nonNullState(doSwitch(classifierID, eObject));
		}

		private @NonNull SerializationNode getNullSerializationNode() {
			NullSerializationNode nullSerializationNode2 = nullSerializationNode;
			if (nullSerializationNode2 == null) {
				this.nullSerializationNode = nullSerializationNode2 = new NullSerializationNode(ruleAnalysis);
			}
			return nullSerializationNode2;
		}
	}

	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();
	private @Nullable SerializationNode serializationNode = null;
	private @Nullable NullRequiredSlots nullRequiredSlots = null;
	private @Nullable SerializationRule nullConjunction = null;

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

	protected void addProducedTypeRef(@NonNull TypeRef type) {
		EClassifier eClassifier = XtextGrammarUtil.getClassifier(type);
		if (!this.eClassifiers.contains(eClassifier)) {
		//	if ("AttributeCS".equals(eClassifier.getName())) {
		//		getClass();
		//	}
			this.eClassifiers.add(eClassifier);
		}
	}

	/**
	 * Perform the analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void analyze() {
		if ("Base::MultiplicityCS".equals(getName())) {
			getClass(); // XXX debugging
		}
		addProducedTypeRef(XtextGrammarUtil.getType(abstractRule));
		for (EObject eObject : new TreeIterable(abstractRule, false)) {
			if (eObject instanceof Action) {
				Action action = (Action)eObject;
			//	if (isFirstResultType(action)) {
					addProducedTypeRef(XtextGrammarUtil.getType(action));
			//	}
			}
			else if (eObject instanceof RuleCall) {
				RuleCall ruleCall = (RuleCall)eObject;
				if (isFirstResultType(ruleCall)) {
					AbstractRule derivedRule = XtextGrammarUtil.getRule(ruleCall);
					XtextAbstractRuleAnalysis derivedRuleAnalysis = grammarAnalysis.getRuleAnalysis(derivedRule);
					derivedRuleAnalysis.addBaseRuleAnalysis(this);
				}
			}
		}
		this.serializationNode = new ParserRuleSwitch(this).analyze();
	}

	public @Nullable SerializationNode basicGetContents() {
		return serializationNode;
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> getEFeature2assignmentAnalyses() {
		return eFeature2assignmentAnalyses;
	}

	public @NonNull SerializationRule getNullConjunction() {
		if (nullConjunction == null) {
			nullConjunction = new SerializationRule(this);
		}
		assert nullConjunction != null;
		return nullConjunction;
	}

	public @NonNull NullRequiredSlots getNullRequiredSlots() {
		if (nullRequiredSlots == null) {
			nullRequiredSlots = new NullRequiredSlots(this);
		}
		assert nullRequiredSlots != null;
		return nullRequiredSlots;
	}


	public @NonNull ParserRule getParserRule() {
		return (ParserRule) abstractRule;
	}

	public @NonNull EClass getReturnedEClass() {
		return (EClass) XtextGrammarUtil.getClassifier(XtextGrammarUtil.getType(abstractRule));
	}

	public @NonNull SerializationNode getRootSerializationNode() {
		assert serializationNode != null;
		return serializationNode;
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
		SerializationNode serializationNode2 = serializationNode;
		assert serializationNode2 != null;
		RequiredSlots requiredSlots = serializationNode2.getRequiredSlots();
		for (@NonNull SerializationRule serializationRule : requiredSlots.getSerializationRules()) {
			serializationRule.getPreSerializer();		// XXX redundant/lazy
		}
	}

//	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
//		assert serializationNode != null;
//		serializationNode.serialize(serializationBuilder, element);
//	}
}