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
import org.eclipse.xtext.CharacterRange;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.NegatedToken;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.UntilToken;
import org.eclipse.xtext.Wildcard;
import org.eclipse.xtext.util.XtextSwitch;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class XtextParserRuleAnalysis extends XtextAbstractRuleAnalysis
{
	public static class XtextTermsSwitch extends XtextSwitch<@NonNull SerializationNode>
	{
		protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;

		/**
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		protected final @NonNull NullSerializationNode nullSerializationNode;

//		private final @NonNull Map<@NonNull EObject, @NonNull AbstractElement> userElement2element;

		public XtextTermsSwitch(@NonNull XtextParserRuleAnalysis ruleAnalysis) {
			this.ruleAnalysis = ruleAnalysis;
			this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
			this.nullSerializationNode = new NullSerializationNode(ruleAnalysis);
		//	this.feature2assignmentAnalyses = new HashMap<>();
		//	this.userElement2element = new HashMap<>();
		}

	/*	private void addCorrelators(@NonNull List<@NonNull XtextTermsAnalysis> correlators, @NonNull Object correlatorOrCorrelators) {
			if (correlatorOrCorrelators instanceof XtextTermsAnalysis) {
				correlators.add((XtextTermsAnalysis)correlatorOrCorrelators);
			}
			else {
				correlators.addAll((Collection<@NonNull XtextTermsAnalysis>)correlatorOrCorrelators);
			}
		} */

/*		protected void accumulateContents(@NonNull List<@NonNull AbstractSerializationNode> contents, @NonNull Object nestedContentOrContents) {
			if (nestedContentOrContents instanceof AbstractSerializationNode) {
				contents.add((AbstractSerializationNode)nestedContentOrContents);
			}
			else {
				@SuppressWarnings("unchecked")
				List<@NonNull AbstractSerializationNode> nestedContents = (List<@NonNull AbstractSerializationNode>)nestedContentOrContents;
				contents.addAll(nestedContents);
			}
		} */

/*		private boolean addContent(@NonNull List<@NonNull AbstractSerializationNode> oldContents, @NonNull AbstractElement newContent) {
			for (@NonNull AbstractSerializationNode oldContent : oldContents) {
				if (oldContent.addAlternative(newContent)) {
					return true;
				}
			}
			return false;
		} */

		@Override
		public @NonNull SerializationNode caseAction(Action action) {
			assert action != null;
			String feature = action.getFeature();
			if (feature != null) {
				XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(action);
				EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
				EClass eFeatureScope = (EClass) XtextGrammarUtil.getEClassifierScope(action);
				return new AssignedCurrentSerializationNode(ruleAnalysis, eFeatureScope, eStructuralFeature, MultiplicativeCardinality.toEnum(action.getCardinality()));

			}
			return nullSerializationNode;
		}

		@Override
		public @NonNull SerializationNode caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(alternatives.getCardinality());
			List<@NonNull SerializationNode> serializationNodes = new ArrayList<>();
			AlternativeKeywordsSerializationNode alternativeKeywordsSerializationNode = null;
			Map<@NonNull EStructuralFeature, @NonNull AlternativeAssignedRuleCallsSerializationNode> eFeature2ruleCallSerializationNode = null;
			Map<@NonNull EStructuralFeature, @NonNull AlternativeAssignedKeywordsSerializationNode> eFeature2keywordsSerializationNode = null;
		//	boolean hasAlternativeAssignedRuleCalls = false;
			for (@NonNull AbstractElement element : XtextGrammarUtil.getElements(alternatives)) {
				boolean doSwitchNeeded = true;
				if (element instanceof Keyword) {
					if (alternativeKeywordsSerializationNode == null) {
						alternativeKeywordsSerializationNode = new AlternativeKeywordsSerializationNode(ruleAnalysis, multiplicativeCardinality);
						serializationNodes.add(alternativeKeywordsSerializationNode);
					}
					alternativeKeywordsSerializationNode.addKeyword((Keyword)element);
					doSwitchNeeded = false;
				}
				else if (element instanceof Assignment) {
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
					else if (terminal instanceof RuleCall) {
						RuleCall ruleCall = (RuleCall)terminal;
						if (eFeature2ruleCallSerializationNode == null) {
							eFeature2ruleCallSerializationNode = new HashMap<>();
						}
						AlternativeAssignedRuleCallsSerializationNode serializationNode = eFeature2ruleCallSerializationNode.get(eFeature);
						if (serializationNode == null) {
							EClass eContainingClass = (EClass) XtextGrammarUtil.getEClassifierScope(ruleCall);
							serializationNode = new AlternativeAssignedRuleCallsSerializationNode(ruleAnalysis, eContainingClass, eFeature, multiplicativeCardinality);
							eFeature2ruleCallSerializationNode.put(eFeature, serializationNode);
							serializationNodes.add(serializationNode);
						}
						serializationNode.addRuleAnalysis(grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule(ruleCall)));
						doSwitchNeeded = false;
					}
				}
				if (doSwitchNeeded) {
					SerializationNode serializationNode = doSwitch(element);
					if (!serializationNode.isNull()) {
						serializationNodes.add(serializationNode);
					}
				/*	boolean merged = false;
					if (nestedContentOrContents instanceof AbstractAssignedSerializationNode) {
						for (@NonNull SerializationNode content : contents) {
							if (content.addAlternative((AssignedSerializationNode)nestedContentOrContents)) {
								merged = true;
								break;
							}
						}
					}
					if (!merged) {
						if (nestedContentOrContents instanceof AbstractSerializationNode) {
							SerializationNode nestedContent = (SerializationNode)nestedContentOrContents;
							if ((contents.size() != 1) || !contents.get(0).addAlternative(nestedContent)) {
								contents.add(nestedContent);		// XXX alternatives case
							}
						}
						else {
							@SuppressWarnings("unchecked")
							List<@NonNull SerializationNode> nestedContents = (List<@NonNull SerializationNode>)nestedContentOrContents;
							if ((contents.size() != 1) || (nestedContents.size() != 1) || !contents.get(0).addAlternative(nestedContents.get(0))) {
								contents.addAll(nestedContents);		// XXX alternatives case
							}
						}
					} */
				}
			}
		/*	String cardinality = alternatives.getCardinality();
			if ((contents.size() > 1) && !"*".equals(cardinality)) {
				return new AlternativesSerializationNode(grammarAnalysis, cardinality, contents);
			}
			if (cardinality != null) {
				for (@NonNull SerializationNode content : contents) {
					content.setCardinality(cardinality);
				}
			} */
			if (serializationNodes.size() <= 0) {
				return nullSerializationNode;
			}
			if (serializationNodes.size() == 1) {
				if (alternativeKeywordsSerializationNode != null) {
					return alternativeKeywordsSerializationNode;
				}
				else if ((eFeature2keywordsSerializationNode != null) && (eFeature2keywordsSerializationNode.size() == 1)) {
					for (@NonNull AlternativeAssignedKeywordsSerializationNode serializationNode : eFeature2keywordsSerializationNode.values()) {		// All one value
						return serializationNode;
					}
				}
				else if ((eFeature2ruleCallSerializationNode != null) && (eFeature2ruleCallSerializationNode.size() == 1)) {
					for (@NonNull AlternativeAssignedRuleCallsSerializationNode serializationNode : eFeature2ruleCallSerializationNode.values()) {		// All one value
						return serializationNode;
					}
				}
			}
			return new AlternativesSerializationNode(ruleAnalysis, alternatives, serializationNodes);
		}

		@Override
		public @NonNull SerializationNode caseAssignment(Assignment assignment) {
			assert assignment != null;
			XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
			EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
			MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(assignment.getCardinality());
			EClass eFeatureScope = (EClass) XtextGrammarUtil.getEClassifierScope(assignment);
			AbstractElement terminal = XtextGrammarUtil.getTerminal(assignment);
			if (terminal instanceof RuleCall) {
				XtextAbstractRuleAnalysis ruleAnalysis2 = grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule((RuleCall)terminal));
				return new AssignedRuleCallSerializationNode(ruleAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality, ruleAnalysis2);
			}
			else if (terminal instanceof Keyword) {
				return new AssignedKeywordSerializationNode(ruleAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality, (Keyword)terminal);
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
				return nullSerializationNode;
			}
			else if (terminal instanceof CrossReference) {
			//	EClass eContainingClass = (EClass) XtextGrammarUtil.getEClassifierScope(assignment);
			//	RuleCall ruleCall = (RuleCall) XtextGrammarUtil.getTerminal((CrossReference)terminal);
			//	AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
			//	XtextAbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(calledRule);
				return new AssignedCrossReferenceSerializationNode(ruleAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality, (CrossReference)terminal);
			}
			else {
				throw new UnsupportedOperationException("Unsupported Assignment terminal '" + terminal.eClass().getName() + "'");
			}
		//	return new XtextAbstractContent(eStructuralFeature);
		}

		@Override
		public @NonNull SerializationNode caseCharacterRange(CharacterRange characterRange) {
			assert characterRange != null;
			return new CharacterRangeSerializationNode(ruleAnalysis, characterRange);
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
			return new SequenceSerializationNode(ruleAnalysis, group, serializationNodes);
		}

		@Override
		public @NonNull SerializationNode caseKeyword(Keyword keyword) {
			assert keyword != null;
			return new UnassignedKeywordSerializationNode(ruleAnalysis, keyword);
		}

		@Override
		public @NonNull SerializationNode caseNegatedToken(NegatedToken negatedToken) {
			assert negatedToken != null;
			return new NegatedTokenSerializationNode(ruleAnalysis, negatedToken, doSwitch(negatedToken.getTerminal()));
		}

		@Override
		public @NonNull SerializationNode caseRuleCall(RuleCall ruleCall) {
			assert ruleCall != null;
			assert !(ruleCall.eContainer() instanceof Assignment);
			return new UnassignedRuleCallSerializationNode(ruleAnalysis, ruleCall, grammarAnalysis.getRuleAnalysis(XtextGrammarUtil.getRule(ruleCall)));
		}

/*		@Override
		public @NonNull SerializationNode caseTypeRef(TypeRef object) {
			return nullSerializationNode;
		} */

		@Override
		public @NonNull SerializationNode caseUntilToken(UntilToken untilToken) {
			assert untilToken != null;
			return new UntilTokenSerializationNode(ruleAnalysis, untilToken);
		}

		@Override
		public @NonNull SerializationNode caseWildcard(Wildcard wildcard) {
			assert wildcard != null;
			return new WildcardSerializationNode(ruleAnalysis, wildcard);
		}

		public @NonNull SerializationNode correlate() {
			AbstractElement rootElement = ruleAnalysis.getRule().getAlternatives();
			return doSwitch(rootElement);
		}

		@Override
		public @NonNull SerializationNode defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in Correlator");
		//	return null;
		}

		@Override
		public @NonNull SerializationNode doSwitch(EObject eObject) {
			return ClassUtil.nonNullState(doSwitch(eObject.eClass().getClassifierID(), eObject));
		}

	/*	@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			List<@NonNull EStructuralFeature> features = new ArrayList<>(feature2assignmentAnalyses.keySet());
			Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		//	if (conten)
			boolean isFirst1 = true;
			for (@NonNull EStructuralFeature feature : features) {
				List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = feature2assignmentAnalyses.get(feature);
				assert assignmentAnalyses != null;
				if (!isFirst1) {
					s.append(",");
				}
				s.append(feature.getName());
				s.append("(");
				boolean isFirst2 = true;
				for (@NonNull XtextAssignmentAnalysis assignmentAnalysis : assignmentAnalyses) {
					assert assignmentAnalyses != null;
					if (!isFirst2) {
						s.append(",");
					}
					s.append(assignmentAnalysis.getCardinality());
					isFirst2 = false;
				}
				s.append(")");
				isFirst1 = false;
			}
			return s.toString();
		}*/
	}

	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();
	private @Nullable SerializationNode serializationNode = null;
	private @Nullable NullRequiredSlots nullRequiredSlots = null;
	private @Nullable RequiredSlotsConjunction nullConjunction = null;

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

	public @Nullable SerializationNode basicGetContents() {
		return serializationNode;
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> getEFeature2assignmentAnalyses() {
		return eFeature2assignmentAnalyses;
	}

	public @NonNull RequiredSlotsConjunction getNullConjunction() {
		if (nullConjunction == null) {
			nullConjunction = new RequiredSlotsConjunction(this);
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


	/**
	 * Perform the intra analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void intraAnalyze() {
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
		this.serializationNode = new XtextTermsSwitch(this).correlate();
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
		if ("EssentialOCL::CollectionPatternCS".equals(name)) {
			getClass();		// XXX
		}
		SerializationNode serializationNode2 = serializationNode;
		assert serializationNode2 != null;
		RequiredSlots requiredSlots = serializationNode2.getRequiredSlots();
		int conjunctionCount = requiredSlots.getConjunctionCount();
		for (int conjunctionIndex = 0; conjunctionIndex < conjunctionCount; conjunctionIndex++) {
			RequiredSlotsConjunction conjunction = requiredSlots.getConjunction(conjunctionIndex);
			conjunction.getPreSerializer();		// XXX redundant/lazy
		}
	}

//	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
//		assert serializationNode != null;
//		serializationNode.serialize(serializationBuilder, element);
//	}
}