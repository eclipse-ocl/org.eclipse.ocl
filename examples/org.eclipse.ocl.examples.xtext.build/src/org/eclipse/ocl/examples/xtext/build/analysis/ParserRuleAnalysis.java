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
package org.eclipse.ocl.examples.xtext.build.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.LocatorHelper.LocatorSwitch;
import org.eclipse.ocl.examples.xtext.build.elements.AbstractAssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AbstractSerializationElement;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeAssignsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeUnassignedKeywordsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedCurrentSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.ListOfListOfSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.ListOfSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.NullSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SequenceSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationElement;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationRuleAnalysisComparator;
import org.eclipse.ocl.examples.xtext.build.elements.UnassignedGrammarRuleCallSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.UnassignedSerializationRuleCallSerializationNode;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.idioms.impl.LocatorImpl;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.Indexed;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.util.XtextSwitch;

import com.google.common.collect.Iterables;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class ParserRuleAnalysis extends AbstractRuleAnalysis implements Indexed
{
	/**
	 * The AbstractElement2SerializationElementSwitch supports the recursive transformation of a ParserRule AbstractElement to its
	 * disjoint normal form comprisising an outer disjunction of conjunctions of terms with cardinatlities.
	 * Some alternatives such as an an enumeration of keywords are aggregated as an inner alternative tio avoid
	 * the permutatioon of alternatives getting out of hand. Parser rule calls are flattened.
	 */
	protected static class ActionAndAssignmentAnalysisSwitch extends XtextSwitch<@NonNull ActionAndAssignmentAnalysisSwitch>
	{
		protected final @NonNull ParserRuleAnalysis parserRuleAnalysis;
		protected final @NonNull GrammarAnalysis grammarAnalysis;
		private @Nullable RuleCall firstUnassignedRuleCall;

		/**
		 * true if the current tree context can provide the produced EClass. i.e. it is a unit cardinality element gruped only
		 * by optional elements. A leaf isSimpleAlternative registers this rule as a super-rule of a called rule.
		 *
		 * isSimpleAlternative is true if the containment of abstractElement comprises pre alternatives of single element groups.
		 */
		private boolean isSimpleAlternative;

		/**
		 * The prevailing action/returns EClass.
		 */
		private @NonNull EClass producedEClass;

		public ActionAndAssignmentAnalysisSwitch(@NonNull ParserRuleAnalysis parserRuleAnalysis) {
			this.parserRuleAnalysis = parserRuleAnalysis;
			this.grammarAnalysis = parserRuleAnalysis.getGrammarAnalysis();
			this.firstUnassignedRuleCall = null;
			this.isSimpleAlternative = true;
			this.producedEClass = parserRuleAnalysis.getReturnedEClass();
		}

		/**
		 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
		 *	required behaviour of a current action.
		 */
		public @Nullable RuleCall analyze() {
			AbstractElement rootElement = SerializationUtils.getAlternatives(parserRuleAnalysis.getRule());
			analyze(rootElement);
			return firstUnassignedRuleCall;
		}

		private void analyze(@NonNull AbstractElement nestedElement) {
			int classifierID = nestedElement.eClass().getClassifierID();
			this.doSwitch(classifierID, nestedElement);
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch caseAction(Action action) {
			assert action != null;
			TypeRef type = SerializationUtils.getType(action);
			producedEClass = (EClass)SerializationUtils.getClassifier(type);
			String feature = action.getFeature();
			if (feature != null) {
				assert firstUnassignedRuleCall != null;
				AbstractRule currentRule = SerializationUtils.getRule(firstUnassignedRuleCall);
				ParserRuleAnalysis currentRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(currentRule);
				AssignmentAnalysis assignmentAnalysis = new ActionAssignmentAnalysis(parserRuleAnalysis, action, currentRuleAnalysis);
				parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
			}
			return this;
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			List<@NonNull AbstractElement> elements = SerializationUtils.getElements(alternatives);
			if (elements.size() == 1) {
				analyze(elements.get(0));
			}
			else {
				RuleCall savedFirstUnassignedRuleCall = this.firstUnassignedRuleCall;
				boolean savedIsSimpleAlternative = this.isSimpleAlternative;
				for (@NonNull AbstractElement nestedElement : SerializationUtils.getElements(alternatives)) {
					analyze(nestedElement);
					this.firstUnassignedRuleCall = savedFirstUnassignedRuleCall;
					this.isSimpleAlternative = savedIsSimpleAlternative;
				}
			}
			return this;
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch caseAssignment(Assignment assignment) {
			assert assignment != null;
			AssignmentAnalysis assignmentAnalysis = new DirectAssignmentAnalysis(parserRuleAnalysis, producedEClass, assignment);
			parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
			return this;
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch caseGroup(Group group) {
			assert group != null;
			List<@NonNull AbstractElement> elements = SerializationUtils.getElements(group);
			AbstractElement nonOptionalElement = null;
			for (@NonNull AbstractElement nestedElement : elements) {
				if (!(nestedElement instanceof RuleCall) && !(nestedElement instanceof Group)) {
					nonOptionalElement = null;
					break;
				}
				else {
					GrammarCardinality grammarCardinality = GrammarCardinality.toEnum(nestedElement);
					if (grammarCardinality.isOne()) {
						if (nonOptionalElement != null) {
							nonOptionalElement = null;
							break;
						}
						nonOptionalElement = nestedElement;
					}
					else if (!grammarCardinality.mayBeZero()) {
						nonOptionalElement = null;
						break;
					}
				}
			}
			boolean savedIsSimpleAlternative = this.isSimpleAlternative;
			for (@NonNull AbstractElement nestedElement : elements) {
				this.isSimpleAlternative = (nestedElement == nonOptionalElement) && savedIsSimpleAlternative;
				analyze(nestedElement);
			}
			this.isSimpleAlternative = savedIsSimpleAlternative;
			return this;
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch caseKeyword(Keyword keyword) {
			assert keyword != null;
			return this;
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch caseRuleCall(RuleCall ruleCall) {
			assert ruleCall != null;
			assert firstUnassignedRuleCall == null;
			AbstractRule subRule = SerializationUtils.getRule(ruleCall);
			if (SerializationUtils.getClassifier(SerializationUtils.getType(subRule)) instanceof EClass) {
				firstUnassignedRuleCall = ruleCall;
				if (isSimpleAlternative) {
					ParserRuleAnalysis subRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(subRule);
					subRuleAnalysis.addSuperRuleAnalysis(parserRuleAnalysis);
				}
			}
			return this;
		}

		@Override
		public @NonNull ActionAndAssignmentAnalysisSwitch defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in ActionAndAssignmentAnalysisSwitch");
		}

		@Override
		public @NonNull String toString() {
			return parserRuleAnalysis.toString();
		}
	}

	/**
	 * The SerializationElementSynthesisSwitch supports the recursive transformation of a ParserRule AbstractElement to its
	 * disjoint normal form comprisising an outer disjunction of conjunctions of terms with cardinatlities.
	 * Some alternatives such as an an enumeration of keywords are aggregated as an inner alternative tio avoid
	 * the permutatioon of alternatives getting out of hand. Parser rule calls are flattened.
	 */
	protected static class SerializationElementSynthesisSwitch extends XtextSwitch<@NonNull SerializationElement>
	{
		protected static final class AlterantiveSerializationElementComparator extends SerializationUtils.ToStringComparator<@NonNull SerializationElement>
		{
			private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2index = new HashMap<>();

			public AlterantiveSerializationElementComparator(@Nullable Iterable<@NonNull EStructuralFeature> eFeatures) {
				if (eFeatures != null) {
					int index = 0;
					for (@NonNull EStructuralFeature eFeature : eFeatures) {
						eFeature2index.put(eFeature, index++);
					}
				}
			}

			@Override
			public int compare(@NonNull SerializationElement o1, @NonNull SerializationElement o2) {
				@Nullable Integer x1 = o1 instanceof AbstractAssignedSerializationNode ? eFeature2index.get(((AbstractAssignedSerializationNode)o1).getEStructuralFeature()) : null;
				@Nullable Integer x2 = o2 instanceof AbstractAssignedSerializationNode ? eFeature2index.get(((AbstractAssignedSerializationNode)o2).getEStructuralFeature()) : null;
				int i1 = x1 != null ? x1.intValue() : -1;
				int i2 = x2 != null ? x2.intValue() : -1;
				if (i1 != i2) {
					return i1 - i2;
				}
				return super.compare(o1, o2);
			}
		}

		protected final @NonNull ParserRuleAnalysis parserRuleAnalysis;
		protected final @NonNull GrammarAnalysis grammarAnalysis;
		/**
		 * The prevailing action/returns EClass.
		 */
		private @NonNull EClass producedEClass;

		/**
		 * True if the current switch case is transitively a complete alternative for the parser rule.
		 * False if decorated by punctuation/assignments.
		 *
		 * This determines whether an UnassignedGrammarRuleCallSerializationNode can be converted to
		 * an UnassignedSerializationRuleCallSerializationNode to avoid creating a duplicate or must
		 * be folded into a flattened form.
		 */
		private boolean isRootAlternative = true;

		public SerializationElementSynthesisSwitch(@NonNull ParserRuleAnalysis parserRuleAnalysis) {
			this.parserRuleAnalysis = parserRuleAnalysis;
			this.grammarAnalysis = parserRuleAnalysis.getGrammarAnalysis();
			this.producedEClass = parserRuleAnalysis.getReturnedEClass();
		}

		public @NonNull SerializationElement analyze() {
			AbstractElement rootElement = SerializationUtils.getAlternatives(parserRuleAnalysis.getRule());
			SerializationElement serializationElement = analyze(rootElement);
			if (serializationElement instanceof UnassignedGrammarRuleCallSerializationNode) {
				UnassignedGrammarRuleCallSerializationNode unassignedGrammarRuleCallSerializationNode = (UnassignedGrammarRuleCallSerializationNode)serializationElement;
				GrammarCardinality grammarCardinality = unassignedGrammarRuleCallSerializationNode.getGrammarCardinality();
				serializationElement = AbstractSerializationElement.flattenUnassignedGrammarRuleCall(unassignedGrammarRuleCallSerializationNode, grammarCardinality, isRootAlternative);
				assert serializationElement != null;
			}
			assert serializationElement.noUnassignedParserRuleCall();					// FIXME -- need to flatten if not CompoundElement
			assert serializationElement.onlyRootUnassignedSerializationRuleCall(true);	// FIXME -- need to flatten if not CompoundElement
			return serializationElement;
		}

		private @NonNull SerializationElement analyze(@NonNull AbstractElement nestedElement) {
			int classifierID = nestedElement.eClass().getClassifierID();
			@SuppressWarnings("null") SerializationElement serializationElement = doSwitch(classifierID, nestedElement);
			return serializationElement;
		}

		@Override
		public @NonNull SerializationElement caseAction(Action action) {
			assert action != null;
			TypeRef type = SerializationUtils.getType(action);
			producedEClass = (EClass)SerializationUtils.getClassifier(type);
			String feature = action.getFeature();
			if (feature != null) {
				ActionAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(action);
				return new AssignedCurrentSerializationNode(assignmentAnalysis, GrammarCardinality.toEnum(action));

			}
			return NullSerializationNode.INSTANCE;
		}

		@Override
		public @NonNull SerializationElement caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			List<@NonNull Keyword> alternativeUnassignedKeywords = null;
			List<@NonNull EStructuralFeature> eFeatures = null;
			Map<@NonNull EStructuralFeature, @NonNull List<@NonNull Keyword>> eFeature2keywords = null;
			Map<@NonNull EStructuralFeature, @NonNull List<@NonNull RuleCall>> eFeature2ruleCalls = null;

			GrammarCardinality grammarCardinality = GrammarCardinality.toEnum(alternatives);
			List<@NonNull SerializationElement> alternativeSerializationElements = new ArrayList<>();
			for (@NonNull AbstractElement element : SerializationUtils.getElements(alternatives)) {
				boolean doSwitchNeeded = false;
				if ((element instanceof Keyword) && (element.getCardinality() == null)) {
					if (alternativeUnassignedKeywords == null) {
						alternativeUnassignedKeywords = new ArrayList<>();
					}
					alternativeUnassignedKeywords.add((Keyword)element);
				}
				else if ((element instanceof Assignment) && (element.getCardinality() == null)) {
					Assignment assignment = (Assignment)element;
					DirectAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
					EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
					AbstractElement terminal = assignment.getTerminal();
					if ((terminal instanceof Keyword) && (terminal.getCardinality() == null)) {
						if (eFeatures == null) {
							eFeatures = new ArrayList<>();
						}
						if (!eFeatures.contains(eFeature)) {
							eFeatures.add(eFeature);
						}
						if (eFeature2keywords == null) {
							eFeature2keywords = new HashMap<>();
						}
						List<@NonNull Keyword> keywords = SerializationUtils.maybeNull(eFeature2keywords.get(eFeature));
						if (keywords == null) {
							keywords = new ArrayList<>();
							eFeature2keywords.put(eFeature, keywords);
						}
						keywords.add((Keyword)terminal);
					}
					else if ((terminal instanceof RuleCall) && (terminal.getCardinality() == null)) {
						if (eFeatures == null) {
							eFeatures = new ArrayList<>();
						}
						if (!eFeatures.contains(eFeature)) {
							eFeatures.add(eFeature);
						}
						if (eFeature2ruleCalls == null) {
							eFeature2ruleCalls = new HashMap<>();
						}
						List<@NonNull RuleCall> ruleCalls = SerializationUtils.maybeNull(eFeature2ruleCalls.get(eFeature));
						if (ruleCalls == null) {
							ruleCalls = new ArrayList<>();
							eFeature2ruleCalls.put(eFeature, ruleCalls);
						}
						ruleCalls.add((RuleCall)terminal);
					}
					else {
						doSwitchNeeded = true;;
					}
				}
				else {
					doSwitchNeeded = true;;
				}
				if (doSwitchNeeded) {
					boolean savedIsRootAlternative = isRootAlternative;
					if (grammarCardinality.isOneOrMore()) {
						isRootAlternative = false;		// cannot delegate a permutation - must fold
					}
					alternativeSerializationElements.add(analyze(element));
					isRootAlternative = savedIsRootAlternative;
				}
			}

			if (alternativeUnassignedKeywords != null) {
				if (alternativeUnassignedKeywords.size() == 1) {
					alternativeSerializationElements.add(new UnassignedKeywordSerializationNode(alternativeUnassignedKeywords.get(0), producedEClass, GrammarCardinality.ONE));
				}
				else {
					AlternativeUnassignedKeywordsSerializationNode alternativeUnassignedKeywordsSerializationNode = new AlternativeUnassignedKeywordsSerializationNode(GrammarCardinality.ONE, null);
					for (@NonNull Keyword keyword : alternativeUnassignedKeywords) {
						alternativeUnassignedKeywordsSerializationNode.addKeyword(keyword);
					}
					alternativeSerializationElements.add(alternativeUnassignedKeywordsSerializationNode);
				}
			}
			if (eFeatures != null) {
				for (@NonNull EStructuralFeature eFeature : eFeatures) {
					List<@NonNull Keyword> keywords = eFeature2keywords != null ? SerializationUtils.maybeNull(eFeature2keywords.get(eFeature)) : null;
					List<@NonNull RuleCall> ruleCalls = eFeature2ruleCalls != null ? SerializationUtils.maybeNull(eFeature2ruleCalls.get(eFeature)) : null;
					if (ruleCalls == null) {
						assert keywords != null;
						Keyword firstKeyword = SerializationUtils.nonNullState(keywords.get(0));
						Assignment assignment = SerializationUtils.nonNullState((Assignment)firstKeyword.eContainer());
						DirectAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
						if (keywords.size() == 1) {
							alternativeSerializationElements.add(new AssignedKeywordSerializationNode(assignmentAnalysis, GrammarCardinality.ONE, firstKeyword));
						}
						else {
							alternativeSerializationElements.add(new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, GrammarCardinality.ONE, keywords));
						}
					}
					else if ((keywords == null) && (ruleCalls.size() == 1)) {
						RuleCall firstRuleCall = SerializationUtils.nonNullState(ruleCalls.get(0));
						Assignment assignment = SerializationUtils.nonNullState((Assignment)firstRuleCall.eContainer());
						DirectAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
						AbstractRuleAnalysis firstRuleAnalysis = grammarAnalysis.getRuleAnalysis(SerializationUtils.getRule(firstRuleCall));
						alternativeSerializationElements.add(new AssignedRuleCallSerializationNode(assignmentAnalysis, GrammarCardinality.ONE, firstRuleAnalysis.getIndex()));
					}
					else {
						assert ruleCalls != null;
						List<@NonNull DirectAssignmentAnalysis> assignmentAnalyses = new ArrayList<>();
						int [] ruleIndexes = new int [ruleCalls.size()];
						int i = 0;
						for (@NonNull RuleCall ruleCall : ruleCalls) {
							Assignment assignment = SerializationUtils.nonNullState((Assignment)ruleCall.eContainer());
							assignmentAnalyses.add(grammarAnalysis.getAssignmentAnalysis(assignment));
							AbstractRuleAnalysis ruleAnalysis = grammarAnalysis.getRuleAnalysis(SerializationUtils.getRule(ruleCall));
							ruleIndexes[i++] = ruleAnalysis.getIndex();
						}
						@SuppressWarnings("null")
						@NonNull DirectAssignmentAnalysis directAssignmentAnalysis = assignmentAnalyses.get(0);
						alternativeSerializationElements.add(new AlternativeAssignsSerializationNode(grammarAnalysis, directAssignmentAnalysis.getEClass(), eFeature, GrammarCardinality.ONE, keywords, ruleIndexes, getTargetRuleAnalyses(assignmentAnalyses)));
					}
				}
			}
			//  Would try to preserve declration order, but it may not always work.
			// There is no precedence so alphabetocal is ok/stable.
			Collections.sort(alternativeSerializationElements, new AlterantiveSerializationElementComparator(eFeatures));
			return doAlternatives(alternatives, alternativeSerializationElements, grammarCardinality);
		}

		@Override
		public @NonNull SerializationElement caseAssignment(Assignment assignment) {
			assert assignment != null;
			DirectAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
			GrammarCardinality grammarCardinality = GrammarCardinality.toEnum(assignment);
			AbstractElement terminal = SerializationUtils.getTerminal(assignment);
			if (terminal instanceof CrossReference) {
				return new AssignedCrossReferenceSerializationNode(assignmentAnalysis, grammarCardinality, (CrossReference)terminal);
			}
			else if (terminal instanceof Keyword) {
				return new AssignedKeywordSerializationNode(assignmentAnalysis, grammarCardinality, (Keyword)terminal);
			}
			else if (terminal instanceof RuleCall) {
				AbstractRuleAnalysis ruleAnalysis2 = grammarAnalysis.getRuleAnalysis(SerializationUtils.getRule((RuleCall)terminal));
				return new AssignedRuleCallSerializationNode(assignmentAnalysis, grammarCardinality, ruleAnalysis2.getIndex());
			}
			else if (terminal instanceof Alternatives) {
				Alternatives alternatives = (Alternatives)terminal;
				List<@NonNull Keyword> keywords = null;
				List<@NonNull AbstractRuleAnalysis> ruleAnalyses = null;
				Iterable<@NonNull AbstractElement> elements = SerializationUtils.getElements(alternatives);
				for (@NonNull AbstractElement alternative : elements) {
					assert alternative.getCardinality() == null;
					if (alternative instanceof Keyword) {
						if (keywords == null) {
							keywords = new ArrayList<>();
						}
						keywords.add((Keyword)alternative);
					}
					else if (alternative instanceof RuleCall) {
						if (ruleAnalyses == null) {
							ruleAnalyses = new ArrayList<>();
						}
						AbstractRuleAnalysis ruleAnalysis = grammarAnalysis.getRuleAnalysis(SerializationUtils.getRule((RuleCall)alternative));
						ruleAnalyses.add(ruleAnalysis);
					}
					else {
						throw new UnsupportedOperationException("Unsupported Assignment alternative terminal '" + alternative.eClass().getName() + "'");
					}
				}
				if (ruleAnalyses != null) {
					if ((ruleAnalyses.size() > 1) || (keywords != null)) {
						int [] ruleIndexes = new int [ruleAnalyses.size()];
						int i = 0;
						for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
							ruleIndexes[i++] = ruleAnalysis.getIndex();
						}
						return new AlternativeAssignsSerializationNode(assignmentAnalysis, grammarCardinality, keywords, ruleIndexes);
					}
					else {
						AbstractRuleAnalysis firstRuleAnalysis = SerializationUtils.nonNullState(ruleAnalyses.get(0));
						return new AssignedRuleCallSerializationNode(assignmentAnalysis, grammarCardinality, firstRuleAnalysis.getIndex());
					}
				}
				else if (keywords != null) {
					if (keywords.size() > 1) {
						return new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, grammarCardinality, keywords);
					}
					else {
						return new AssignedKeywordSerializationNode(assignmentAnalysis, grammarCardinality, keywords.get(0));
					}
				}
			}
			throw new UnsupportedOperationException("Unsupported Assignment terminal '" + terminal.eClass().getName() + "'");
		}

		@Override
		public @NonNull SerializationElement caseGroup(Group group) {
			assert group != null;
			GrammarCardinality grammarCardinality = GrammarCardinality.toEnum(group);
			boolean savedIsRootAlternative = isRootAlternative;
			if (grammarCardinality.isOneOrMore()) {
				isRootAlternative = false;		// cannot delegate a permutation - must fold
			}
			SerializationElement serializationResult = new ListOfSerializationNode();
			List<@NonNull AbstractElement> elements = SerializationUtils.getElements(group);
			if (elements.size() > 1) {
				isRootAlternative = false;		// cannot delegate a permutation - must fold
			}
			for (@NonNull AbstractElement element : elements) {
				SerializationElement serializationElement = analyze(element);
				serializationResult = serializationResult.addConcatenation(serializationElement);
			}
			isRootAlternative = savedIsRootAlternative;
			SerializationElement frozenSequence = serializationResult.freezeSequences(group, GrammarCardinality.toEnum(group), isRootAlternative);
			return frozenSequence;
		}

		@Override
		public @NonNull SerializationElement caseKeyword(Keyword keyword) {
			assert keyword != null;
			GrammarCardinality grammarCardinality = GrammarCardinality.toEnum(keyword);
			if (grammarCardinality.mayBeZero()) {
				return NullSerializationNode.INSTANCE;	// Skip gratuitous output
			}
			else {
				return new UnassignedKeywordSerializationNode(keyword, producedEClass, grammarCardinality);
			}
		}

		@Override
		public @NonNull SerializationElement caseRuleCall(RuleCall ruleCall) {
			assert ruleCall != null;
			assert !(ruleCall.eContainer() instanceof Assignment);
			AbstractRule abstractRule = SerializationUtils.getRule(ruleCall);
			AbstractRuleAnalysis calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(abstractRule);
			if (!(calledRuleAnalysis instanceof ParserRuleAnalysis)) {
				return NullSerializationNode.INSTANCE;
			}
			GrammarCardinality grammarCardinality = GrammarCardinality.toEnum(ruleCall);
			return new UnassignedGrammarRuleCallSerializationNode(producedEClass, grammarCardinality, calledRuleAnalysis);
		}

		/*		@Override
		public @NonNull SerializationNode caseTypeRef(TypeRef object) {
			return nullSerializationNode;
		} */

		@Override
		public @NonNull SerializationNode defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in AbstractElement2SerializationElement");
		}

		private @NonNull SerializationElement doAlternatives(@NonNull Alternatives alternatives, @NonNull List<@NonNull SerializationElement> alternativeSerializationElements, @NonNull GrammarCardinality grammarCardinality) {
			if (grammarCardinality.isZeroOrMore()) {	// (A|B)* => A* + B*
				SerializationElement conjunction = new ListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
					SerializationElement frozen = alternativeSerializationElement.setGrammarCardinality(alternatives, GrammarCardinality.ZERO_OR_MORE).freezeSequences(alternatives, GrammarCardinality.ONE, isRootAlternative);
					conjunction = conjunction.addConcatenation(frozen);
				}
				SerializationElement frozen = conjunction.freezeSequences(alternatives, GrammarCardinality.ONE, isRootAlternative);
				return frozen;
			}
			else if (grammarCardinality.isOneOrMore()) { 											// (A|B)+ => A+B* | A*B+
				ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement1 : alternativeSerializationElements) {
					SerializationElement conjunction = new ListOfSerializationNode();
					for (@NonNull SerializationElement alternativeSerializationElement2 : alternativeSerializationElements) {
						GrammarCardinality termCardinality = alternativeSerializationElement1 != alternativeSerializationElement2 ? GrammarCardinality.ZERO_OR_MORE : GrammarCardinality.ONE_OR_MORE;
						conjunction = conjunction.addConcatenation(alternativeSerializationElement2.setGrammarCardinality(alternatives, termCardinality));
					}
					SerializationElement frozen = conjunction.freezeSequences(alternatives, GrammarCardinality.ONE, false);
					disjunction = disjunction.addConjunction(frozen);
				}
				return disjunction;
			}
			else if (grammarCardinality.isZeroOrOne()) {	// (A|B)? => A|B|epsilon
				ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
					SerializationElement conjunction = new ListOfSerializationNode();
					conjunction = conjunction.addConcatenation(alternativeSerializationElement);
					SerializationElement frozen = conjunction.freezeSequences(alternatives, GrammarCardinality.ONE, isRootAlternative);
					disjunction = disjunction.addConjunction(frozen);
				}
				disjunction = disjunction.addConjunction(NullSerializationNode.INSTANCE);
				return disjunction;//.setGrammarCardinality(grammarAnalysis, alternatives, GrammarCardinality.ONE);
			//	return disjunction.setGrammarCardinality(grammarAnalysis, alternatives, GrammarCardinality.ZERO_OR_ONE);
			}
			else { // grammarCardinality.isOne()
				ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
				for (@NonNull SerializationElement alternativeSerializationElement : alternativeSerializationElements) {
					SerializationElement conjunction = new ListOfSerializationNode();
					conjunction = conjunction.addConcatenation(alternativeSerializationElement);
					SerializationElement frozen = conjunction.freezeSequences(alternatives, GrammarCardinality.ONE, isRootAlternative);
					disjunction = disjunction.addConjunction(frozen);
				}
				return disjunction;//.setGrammarCardinality(grammarAnalysis, alternatives, GrammarCardinality.ONE);
			}
		}

		private @NonNull Iterable<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses(@NonNull Iterable<@NonNull DirectAssignmentAnalysis> assignmentAnalyses) {
			@NonNull Set<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = new HashSet<>();
			for (@NonNull DirectAssignmentAnalysis assignmentAnalysis : assignmentAnalyses)  {
				targetRuleAnalyses.addAll(assignmentAnalysis.getTargetRuleAnalyses());
			}
			return targetRuleAnalyses;
		}

		@Override
		public @NonNull String toString() {
			return parserRuleAnalysis.toString();
		}
	}

	protected final @NonNull EClass eClass;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();
	private @Nullable List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = null;

	/**
	 * The super rules directly call this rule as an undecorated unassigned alterative.
	 * This rule may therefore substitute the super rule.
	 */
	private @Nullable Set<@NonNull ParserRuleAnalysis> superRuleAnalyses = null;

	/**
	 * The rules which transitively call this rule as undecorated unassigned alteratives.
	 * This rule may therefore substitute the super rule.
	 */
	private @Nullable List<@NonNull ParserRuleAnalysis> superRuleAnalysesClosure = null;

	/**
	 * The rules transitively called by this rule as undecorated unassigned alteratives.
	 * The sub-rules may therefore substitute this rule.
	 */
	private final @NonNull List<@NonNull ParserRuleAnalysis> subRuleAnalysesClosure = new UniqueList<>();

	/**
	 * The EReferences that need a run-time check is needed that the actual user element is compatible with any rules.
	 */
//	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2discriminatingRuleAnalyses = null;

	private @Nullable ParserRuleValue parserRuleValue = null;

	/**
	 * The subidioms to decorate each node during formatting.
	 */
	private @Nullable Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> grammarElement2subIdioms = null;

	public ParserRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull ParserRule parserRule, @NonNull EClass eClass) {
		super(grammarAnalysis, index, parserRule);
		this.eClass = eClass;
	}

	protected void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		grammarAnalysis.addAssignmentAnalysis(assignmentAnalysis);
		EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = SerializationUtils.maybeNull(eFeature2assignmentAnalyses.get(eStructuralFeature));
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			eFeature2assignmentAnalyses.put(eStructuralFeature, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	protected void addSuperRuleAnalysis(@NonNull ParserRuleAnalysis superRuleAnalysis) {
		Set<@NonNull ParserRuleAnalysis> superRuleAnalyses2 = superRuleAnalyses;
		if (superRuleAnalyses2 == null) {
			superRuleAnalyses = superRuleAnalyses2 = new HashSet<>();
		}
		superRuleAnalyses2.add(superRuleAnalysis);
	}

	/**
	 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
	 *	required behaviour of a current action.
	 */
	public @Nullable RuleCall analyzeActionsAndAssignments() {
		ActionAndAssignmentAnalysisSwitch actionAndAssignmentAnalysisSwitch = new ActionAndAssignmentAnalysisSwitch(this);
		return actionAndAssignmentAnalysisSwitch.analyze();
	}

	public void analyzeMatches() {
		assert serializationRuleAnalyses != null;
		for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
			serializationRuleAnalysis.analyzeMatches();
		}
	}

	/**
	 * Determine the ParserRuleAnalyses for each distinct EReference assignment.
	 */
	private void analyzeSerializations(@NonNull Iterable<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses) {
		Map<@NonNull EReference, @NonNull Object> eReference2ruleAnalysisOrAnalyses = new HashMap<>();
		for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
			SerializationNode rootSerializationNode = serializationRuleAnalysis.getRootSerializationNode();
			analyzeSerializations(rootSerializationNode, eReference2ruleAnalysisOrAnalyses);
		}
	}
	private void analyzeSerializations(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EReference, @NonNull Object> eReference2ruleAnalysisOrAnalyses) {
		if (serializationNode instanceof AssignedRuleCallSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				for (int ruleIndex : assignedSerializationNode.getAssignedRuleIndexes()) {
					AbstractRuleAnalysis newRuleAnalysis = grammarAnalysis.getRuleAnalysis(ruleIndex);
					if (newRuleAnalysis instanceof ParserRuleAnalysis) {
						Object oldRuleAnalysisOrAnalyses = SerializationUtils.maybeNull(eReference2ruleAnalysisOrAnalyses.get(eReference));
						if (oldRuleAnalysisOrAnalyses == null) {
							eReference2ruleAnalysisOrAnalyses.put(eReference, newRuleAnalysis);
						}
						else if (oldRuleAnalysisOrAnalyses instanceof ParserRuleAnalysis) {
							if (oldRuleAnalysisOrAnalyses != newRuleAnalysis) {
								List<@NonNull ParserRuleAnalysis> newRuleAnalysisOrAnalyses = new ArrayList<>();
								newRuleAnalysisOrAnalyses.add((ParserRuleAnalysis)oldRuleAnalysisOrAnalyses);
								newRuleAnalysisOrAnalyses.add((ParserRuleAnalysis)newRuleAnalysis);
								eReference2ruleAnalysisOrAnalyses.put(eReference, newRuleAnalysisOrAnalyses);
							}
						}
						else {
							@SuppressWarnings("unchecked")
							List<@NonNull ParserRuleAnalysis> oldRuleAnalyses = (List<@NonNull ParserRuleAnalysis>)oldRuleAnalysisOrAnalyses;
							if (!oldRuleAnalyses.contains(newRuleAnalysis)) {
								oldRuleAnalyses.add((ParserRuleAnalysis)newRuleAnalysis);
							}
						}
					}
				}
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				analyzeSerializations(nestedSerializationNode, eReference2ruleAnalysisOrAnalyses);
			}
		}
	}

	@Override
	public @Nullable ParserRuleValue basicGetRuleValue() {
		return parserRuleValue;
	}

	protected void createSerializationRuleAnalyses(@NonNull List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses, @NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof UnassignedGrammarRuleCallSerializationNode) {
			throw new IllegalStateException();	//Should have been flattened to UnassignedSerializationRuleCallSerializationNode
		}
		else if (serializationNode instanceof UnassignedSerializationRuleCallSerializationNode) {
			UnassignedSerializationRuleCallSerializationNode unassignedRuleCallSerializationNode = (UnassignedSerializationRuleCallSerializationNode)serializationNode;
			SerializationRuleAnalysis calledSerializationRule = unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
			if (!serializationRuleAnalyses.contains(calledSerializationRule)) {
				serializationRuleAnalyses.add(calledSerializationRule);
			}
		}
		else {
			SerializationRuleAnalysis serializationRuleAnalysis = new SerializationRuleAnalysis(this, serializationRuleAnalyses.size(), serializationNode);
			serializationRuleAnalyses.add(serializationRuleAnalysis);
		}
	}

	private void gatherFormattingIdioms(@NonNull AbstractElement abstractElement, @NonNull List<@Nullable List<@NonNull SubIdiom>> serializationIdiomsList) {
		serializationIdiomsList.add(getSubIdioms(abstractElement));
		if (abstractElement instanceof CompoundElement) {
			for (AbstractElement childElement : ((CompoundElement)abstractElement).getElements()) {
				assert childElement != null;
				gatherFormattingIdioms(childElement, serializationIdiomsList);
			}
		}
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> getEFeature2assignmentAnalyses() {
		return eFeature2assignmentAnalyses;
	}

	public @NonNull Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> getGrammarElement2subIdioms() {
		Map<@NonNull AbstractElement, @NonNull List<@NonNull SubIdiom>> grammarElement2subIdioms2 = grammarElement2subIdioms;
		if (grammarElement2subIdioms2 == null) {
			EClass producedEClass = (EClass) abstractRule.getType().getClassifier();
			assert producedEClass != null;
			List<@NonNull Idiom> idioms = new ArrayList<>();
			for (@NonNull Idiom idiom : grammarAnalysis.getIdioms()) {
				boolean isOk = true;
				EClass inEClass = idiom.getForEClass();
				if ((inEClass != null) && !AnalysisUtils.isSuperTypeOf(inEClass, producedEClass)) {
					isOk = false;
				}
				Pattern pattern = idiom.getRegexPattern();
				if (pattern != null) {
					Matcher matcher = pattern.matcher(getName());
					if (!matcher.matches()) {
						isOk = false;
					}
				}
				if (isOk) {
					idioms.add(idiom);
				}
			}
			//
			//	Locate the matches for each idiom.
			//
			@Nullable IdiomGrammarMatch @NonNull [] idiomMatches = new @Nullable IdiomGrammarMatch[Iterables.size(idioms)];
			AbstractElement alternatives = abstractRule.getAlternatives();
			assert alternatives != null;
			getIdiomMatches(alternatives, idioms, idiomMatches);
			//
			//	Install the subidioms for each first/mixin full idiom match.
			//
			grammarElement2subIdioms2 = new HashMap<>();
			for (@Nullable IdiomGrammarMatch idiomMatch : idiomMatches) {
				if (idiomMatch != null) {
					idiomMatch.installIn(grammarElement2subIdioms2);
				}
			}
			grammarElement2subIdioms = grammarElement2subIdioms2;
		}
		return grammarElement2subIdioms2;
	}

	private void getIdiomMatches(@NonNull AbstractElement abstractElement, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomGrammarMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomGrammarMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				SubIdiom firstSubIdiom = idiom.getOwnedSubIdioms().get(0);
				assert firstSubIdiom != null;
				boolean firstSubIdiomMatches = matches(firstSubIdiom, abstractElement);
				idiomMatches[idiomIndex] = firstSubIdiomMatches ? grammarAnalysis.createIdiomMatch(idiom, abstractElement) : null;
			}
			else {
				idiomMatch.nextMatch(abstractElement, this);
			}
			idiomIndex++;
		}
		if (abstractElement instanceof CompoundElement) {				// FIXME Alternatives need permutation or maybe just a constraint check
			for (AbstractElement nestedElement : ((CompoundElement)abstractElement).getElements()) {
				assert nestedElement != null;
				getIdiomMatches(nestedElement, idioms, idiomMatches);
			}
		}
	}

	@Override
	public int getIndex() {
		return index;
	}

	public @NonNull ParserRule getParserRule() {
		return (ParserRule) abstractRule;
	}

	public @NonNull EClass getReturnedEClass() {
		return eClass;
	}

	@Override
	public @NonNull ParserRuleValue getRuleValue() {
		ParserRuleValue parserRuleValue2 = parserRuleValue;
		if (parserRuleValue2 == null) {
			Collection<@NonNull ParserRuleValue> subParserRuleValueClosure = null;
			for (@NonNull ParserRuleAnalysis subParserRuleAnalysis : getSubRuleAnalysesClosure()) {
				if (subParserRuleAnalysis != this) {
					if (subParserRuleValueClosure == null) {
						subParserRuleValueClosure = new ArrayList<>();
					}
					subParserRuleValueClosure.add(subParserRuleAnalysis.getRuleValue());
				}
			}
			GrammarRuleVector subParserRuleValueIndexes = null;
			if (subParserRuleValueClosure != null) {
				subParserRuleValueIndexes = new GrammarRuleVector();
				subParserRuleValueIndexes.set(index);
				for (@NonNull ParserRuleValue parserRuleValue : subParserRuleValueClosure) {
					subParserRuleValueIndexes.set(parserRuleValue.getIndex());
				}
			}
			List<@Nullable List<@NonNull SubIdiom>> formattingSubIdiomsList = new ArrayList<>();
			AbstractElement alternatives = abstractRule.getAlternatives();
			assert alternatives != null;
			gatherFormattingIdioms(alternatives, formattingSubIdiomsList);
			Iterable<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = getSerializationRuleAnalyses();
			@NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule [Iterables.size(serializationRuleAnalyses)];
			@NonNull SerializationSegment @NonNull [] @NonNull [] innerFormattingSegmentsArray = new @NonNull SerializationSegment [formattingSubIdiomsList.size()] @NonNull [];
			@NonNull SerializationSegment @NonNull [] @NonNull [] outerFormattingSegmentsArray = new @NonNull SerializationSegment [formattingSubIdiomsList.size()] @NonNull [];
			for (int i = 0; i < formattingSubIdiomsList.size(); i++) {
				@NonNull SerializationSegment[] innerFormattingSegments = null;
				@NonNull SerializationSegment[] outerFormattingSegments = null;
				List<@NonNull SubIdiom> formattingSubIdioms = formattingSubIdiomsList.get(i);
				if (formattingSubIdioms != null) {
					innerFormattingSegments = grammarAnalysis.getSerializationSegments(formattingSubIdioms, false);
					outerFormattingSegments = grammarAnalysis.getSerializationSegments(formattingSubIdioms, true);
				}
				innerFormattingSegmentsArray[i] = innerFormattingSegments != null ? innerFormattingSegments : SerializationSegment.VALUE_SEGMENTS_ARRAY;
				outerFormattingSegmentsArray[i] = outerFormattingSegments != null ? outerFormattingSegments : SerializationSegment.VALUE_SEGMENTS_ARRAY;
			}
			parserRuleValue = parserRuleValue2 = new ParserRuleValue(index, getName(), serializationRules, outerFormattingSegmentsArray, innerFormattingSegmentsArray, subParserRuleValueIndexes);
			//
			// serializationRules content defined after construction to allow recursive references
			//
			int i = 0;
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
				serializationRules[i++] = serializationRuleAnalysis.getSerializationRule();
			}
		}
		return parserRuleValue2;
	}

	public @NonNull Iterable<@NonNull SerializationRuleAnalysis> getSerializationRuleAnalyses() {
		if (serializationRuleAnalyses == null) {
			synthesizeSerializationRuleAnalyses();
		}
		assert serializationRuleAnalyses != null;
		return serializationRuleAnalyses;
	}

	public @Nullable List<@NonNull SubIdiom> getSubIdioms(@NonNull AbstractElement grammarElement) {
		return getGrammarElement2subIdioms().get(grammarElement);
	}

	public @NonNull Collection<@NonNull ParserRuleAnalysis> getSubRuleAnalysesClosure() {
		assert superRuleAnalysesClosure != null;	// subRuleAnalysesClosure assigned as corrolary of superRuleAnalysesClosure
		return subRuleAnalysesClosure;
	}

	public @NonNull Iterable<@NonNull ParserRuleAnalysis> getSuperRuleAnalysisClosure() {
		List<@NonNull ParserRuleAnalysis> superRuleAnalysesClosureList = this.superRuleAnalysesClosure;
		if (superRuleAnalysesClosureList == null) {
			UniqueList<@NonNull ParserRuleAnalysis> superRuleAnalysesClosureSet = new UniqueList<>();
			superRuleAnalysesClosureSet.add(this);
			for (int i = 0; i < superRuleAnalysesClosureSet.size(); i++) {
				ParserRuleAnalysis ruleAnalysis = SerializationUtils.nonNullState(superRuleAnalysesClosureSet.get(i));
				Set<@NonNull ParserRuleAnalysis> superRuleAnalyses = ruleAnalysis.superRuleAnalyses;
				if (superRuleAnalyses != null) {
					superRuleAnalysesClosureSet.addAll(superRuleAnalyses);
				}
			}
			superRuleAnalysesClosureList = new ArrayList<>(superRuleAnalysesClosureSet);
			Collections.sort(superRuleAnalysesClosureList, AbstractRuleAnalysis.QualifiedNameableComparator.INSTANCE);
			this.superRuleAnalysesClosure = superRuleAnalysesClosureList;
			for (@NonNull ParserRuleAnalysis superRuleAnalysis : superRuleAnalysesClosureList) {
				superRuleAnalysis.subRuleAnalysesClosure.add(this);
			}
		}
		return superRuleAnalysesClosureList;
	}

	public boolean matches(@NonNull SubIdiom subIdiom, @NonNull AbstractElement grammarElement) {
		Locator locator = IdiomsUtils.getLocator(subIdiom);
		return matches(locator, grammarElement);
	}

	public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement) {
		assert !(locator instanceof ReferredLocator);
	//	if (locator instanceof ReferredLocator) {
	//		locator = IdiomsUtils.getOwnedLocator(IdiomsUtils.getLocatorDeclaration((ReferredLocator)locator));
	//	}
		LocatorImpl locatorImpl = (LocatorImpl)locator;
		LocatorHelper locatorHelper = (LocatorHelper)locatorImpl.basicGetHelper();
		if (locatorHelper == null) {
			LocatorSwitch subIdiomLocatorSwitch = grammarAnalysis.getLocatorSwitch();
			locatorHelper = subIdiomLocatorSwitch.doSwitch(locator);
			locatorImpl.setHelper(locatorHelper);
		}
		if (locatorHelper == null) {	// Only actually null after an UnsupportedOperationException
			return false;
		}
		return locatorHelper.matches(locator, grammarElement, this);
	}

	public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
		assert !(locator instanceof ReferredLocator);
	//	if (locator instanceof ReferredLocator) {
	//		locator = IdiomsUtils.getOwnedLocator(IdiomsUtils.getLocatorDeclaration((ReferredLocator)locator));
	//	}
		LocatorImpl locatorImpl = (LocatorImpl)locator;
		LocatorHelper locatorHelper = (LocatorHelper)locatorImpl.basicGetHelper();
		if (locatorHelper == null) {
			LocatorSwitch subIdiomLocatorSwitch = grammarAnalysis.getLocatorSwitch();
			locatorHelper = subIdiomLocatorSwitch.doSwitch(locator);
			locatorImpl.setHelper(locatorHelper);
		}
		if (locatorHelper == null) {	// Only actually null after an UnsupportedOperationException
			return false;
		}
		return locatorHelper.matches(locator, serializationNode);
	}

	/**
	 * Perform the analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void synthesizeSerializationRuleAnalyses() {
		assert serializationRuleAnalyses == null;
		//
		//	Convert the parser element tree to a normalized/flattened disjunction of conjunctions of nodes.
		//
		SerializationElementSynthesisSwitch serializationElementSynthesisSwitch = new SerializationElementSynthesisSwitch(this);
		SerializationElement serializationResult = serializationElementSynthesisSwitch.analyze();
		//
		//	Convert the disjunction of conjunctions of nodes to one or more rules.
		//
		List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = new ArrayList<>();
		synthesizeSerializationRuleAnalyses(serializationRuleAnalyses, serializationResult);
		if (serializationRuleAnalyses.size() > 1) {
			Collections.sort(serializationRuleAnalyses, new SerializationRuleAnalysisComparator());
		}
		this.serializationRuleAnalyses = serializationRuleAnalyses;
		analyzeSerializations(serializationRuleAnalyses);
	}

	protected void synthesizeSerializationRuleAnalyses(@NonNull List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses, @NonNull SerializationElement serializationElement) {
		if (serializationElement.isListOfList()) {
			for (@NonNull List<@NonNull SerializationNode> serializationNodes : serializationElement.asListOfList().getLists()) {
				SerializationNode serializationNode;
				if (serializationNodes.size() == 1) {
					serializationNode = SerializationUtils.nonNullState(serializationNodes.get(0));
					createSerializationRuleAnalyses(serializationRuleAnalyses, serializationNode);
				}
				else {
					CompoundElement alternatives = (CompoundElement)SerializationUtils.getAlternatives(abstractRule);
					SerializationElement nestedSerializationResult = AbstractSerializationElement.createFrozenSequence(alternatives, GrammarCardinality.ONE, serializationNodes, true);
					synthesizeSerializationRuleAnalyses(serializationRuleAnalyses, nestedSerializationResult);
				}
			}
		}
		else if (serializationElement.isList()) {
			List<@NonNull SerializationNode> serializationNodes = serializationElement.asList().getNodes();
			SerializationNode serializationNode;
			if (serializationNodes.size() == 1) {
				serializationNode = SerializationUtils.nonNullState(serializationNodes.get(0));
			}
			else {
				CompoundElement alternatives = (CompoundElement)SerializationUtils.getAlternatives(abstractRule);
				serializationNode = new SequenceSerializationNode(alternatives, GrammarCardinality.ONE, serializationNodes);
			}
			createSerializationRuleAnalyses(serializationRuleAnalyses, serializationNode);
		}
		else if (serializationElement.isNode()) {
			SerializationNode serializationNode = serializationElement.asNode();
			createSerializationRuleAnalyses(serializationRuleAnalyses, serializationNode);
		}
		else {		// isNull()
			throw new IllegalStateException();
		}
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(getQualifiedName());
	/*	List<@NonNull RuleCall> delegatingRuleCalls2 = delegatingRuleCalls;
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
		} */
		List<@NonNull ParserRuleAnalysis> superRuleAnalysesClosure2 = superRuleAnalysesClosure;
		if (superRuleAnalysesClosure2 != null) {
			s.append(" -> ");
			boolean isFirst1 = true;
			for (@NonNull ParserRuleAnalysis superRuleAnalysis : superRuleAnalysesClosure2) {
				if (!isFirst1) {
					s.append(", ");
				}
				s.append(superRuleAnalysis.getQualifiedName());
				isFirst1 = false;
			}
		}
		if (serializationRuleAnalyses != null) {
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
				SerializationUtils.appendIndentation(s, depth+1);
				s.append(serializationRuleAnalysis);
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
	}
}