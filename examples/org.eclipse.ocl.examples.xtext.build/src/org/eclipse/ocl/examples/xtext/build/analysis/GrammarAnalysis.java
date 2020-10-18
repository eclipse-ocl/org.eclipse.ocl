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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.idioms.CustomSegment;
import org.eclipse.ocl.examples.xtext.idioms.HalfNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.examples.xtext.idioms.NewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.NoSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.PopSegment;
import org.eclipse.ocl.examples.xtext.idioms.PostCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PreCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PushSegment;
import org.eclipse.ocl.examples.xtext.idioms.ReferredSegment;
import org.eclipse.ocl.examples.xtext.idioms.Segment;
import org.eclipse.ocl.examples.xtext.idioms.SoftNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.SoftSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.idioms.ValueSegment;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.ocl.examples.xtext.serializer.TreeIterable;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.UntilToken;
import org.eclipse.xtext.service.GrammarProvider;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * An XtextGrammarAnalysis provides the extended analysis of an Xtext (multi-)grammar.
 */
public class GrammarAnalysis //extends AbstractGrammarAnalysis
{
	@Inject
	private @NonNull GrammarProvider grammarProvider;

	@Inject
	private @Nullable Provider<ResourceSet> resourceSetProvider;

	private @Nullable Grammar grammar = null;

	/**
	 * The rule analyses in index order.
	 */
	private final @NonNull List<@NonNull AbstractRuleAnalysis> ruleAnalyses = new ArrayList<>();

	/**
	 * The rule analysis for each rule.
	 */
	private final @NonNull Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis = new HashMap<>();

	/**
	 * The assignment analysis for each assignment or action.
	 */
	private final @NonNull Map<@NonNull AbstractElement, @NonNull AssignmentAnalysis> assignment2assignmentAnalysis = new HashMap<>();

	/**
	 * The possible assignment analyses for containment EReference.
	 */
	private final @NonNull Map<@NonNull EReference, @NonNull List<@NonNull AssignmentAnalysis>> containment2assignmentAnalyses = new HashMap<>();

	/**
	 * The possible producing rule analyses for each EClass. ??This analysis excludes overrides.
	 */
	private @Nullable Map<@NonNull EClass, @NonNull List<@NonNull ParserRuleAnalysis>> eClass2ruleAnalyses = null;

	/**
	 * The values of enumerated features
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = null;

	private  final @NonNull Map<@NonNull String, @NonNull EnumerationValueSingle> value2enumerationValue = new HashMap<>();
	private  final @NonNull Map<@NonNull List<@NonNull String>, @NonNull EnumerationValueMultiple> values2enumerationValue = new HashMap<>();

	private @Nullable List<@NonNull EClassValue> sortedProducedEClassValues = null;
	private @Nullable Map<@NonNull SerializationRule, @NonNull SerializationRuleAnalysis> serializationRule2aserializationRuleAnalysis = null;

	private @NonNull Map<@NonNull Segment, @NonNull SerializationSegment> segment2serializationSegment = new HashMap<>();

	private final @NonNull Map<@NonNull EClass, @NonNull EClassValue> eClass2eClassValue = new HashMap<>();

//	private @NonNull Map<@NonNull Integer, @NonNull Object> semanticHash2serializationAnalysisOrAnalyses = new HashMap<>();

	private @Nullable Map<@NonNull String, @NonNull String> multipleLineCommentCharacterRanges = null;
	private @Nullable List<@NonNull String> singleLineCommentKeywords = null;

	private @Nullable GrammarRuleVector leafGrammarRulesVector = null;

	public GrammarAnalysis() {
		this.grammar = null;
	}

	public GrammarAnalysis(@NonNull Grammar grammar) {
		this.grammar = grammar;
	}

	public void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		AssignmentAnalysis old = SerializationUtils.maybeNull(assignment2assignmentAnalysis.put(assignmentAnalysis.getActionOrAssignment(), assignmentAnalysis));
		assert old == null;
	}

	public void addContainment(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull EReference eReference) {
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = SerializationUtils.maybeNull(containment2assignmentAnalyses.get(eReference));
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			containment2assignmentAnalyses.put(eReference, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	protected void addEClassValue(@NonNull EClassValue eClassValue) {
		assert eClassValue != null;
		EClassValue old = SerializationUtils.maybeNull(eClass2eClassValue.put(eClassValue.getEClass(), eClassValue));
		assert old == null;
	}

	public void addEnumeration(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues2 = eAttribute2enumerationValues;
		if (eAttribute2enumerationValues2 == null) {
			eAttribute2enumerationValues = eAttribute2enumerationValues2 = new HashMap<>();
		}
		Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues2.get(eAttribute));
		if (enumerationValues == null) {
			enumerationValues = new HashSet<>();
			eAttribute2enumerationValues2.put(eAttribute, enumerationValues);
		}
		enumerationValues.add(enumerationValue);
	}

	/**
	 * Perform the analysis to determine and populate the Assignment and Rule analyses.
	 * @throws IOException
	 */
	public void analyze() {
		Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls = new HashMap<>();
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = analyzeRuleNames(rule2ruleCalls);
		/*this.rule2ruleAnalysis =*/ createRuleAnalyses(ruleName2rules, rule2ruleCalls);
		List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = new ArrayList<>(ruleAnalyses.size());
		List<@NonNull TerminalRuleAnalysis> terminalRuleAnalyses = new ArrayList<>();
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			if (abstractRuleAnalysis instanceof ParserRuleAnalysis) {
				parserRuleAnalyses.add((ParserRuleAnalysis)abstractRuleAnalysis);
			}
			else if (abstractRuleAnalysis instanceof TerminalRuleAnalysis) {
				terminalRuleAnalyses.add((TerminalRuleAnalysis)abstractRuleAnalysis);
			}
		}
		Collections.sort(parserRuleAnalyses, AbstractRuleAnalysis.QualifiedNameableComparator.INSTANCE);
		//
		//	Create an assignment analysis for each assignment and current action.
		//	Populate GrammarAnalysis.assignment2assignmentAnalysis, ParserRuleAnalysis.callingRuleAnalysis
		//
		for (@NonNull ParserRuleAnalysis ruleAnalysis : parserRuleAnalyses) {
			ruleAnalysis.analyzeActionsAndAssignments();
		}
		//
		//	Promote ParserRuleAnalysis.superRuleAnalysis to ParserRuleAnalysis.sub/superRuleAnalysisClosure
		//
		for (@NonNull ParserRuleAnalysis ruleAnalysis : parserRuleAnalyses) {
			ruleAnalysis.getSuperRuleAnalysisClosure();
		}
		//
		//	Identify the assignment analyses that are containments.
		//
		for (@NonNull AssignmentAnalysis assignmentAnalysis : assignment2assignmentAnalysis.values()) {
			assignmentAnalysis.analyzeContainmentAndTargets();
		}
		//
		//	Create the disjunction of flattened SerializationRule comprising a conjunction of SerializationNode.
		//
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.getSerializationRuleAnalyses();		// Triggers lazy analyze();
		}
		//
		//	Determine the variables and expressions and their solutions to determine the cardinality of each term.
		//
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.analyzeMatches();
		}
		this.eClass2ruleAnalyses = analyzeProductions(parserRuleAnalyses);
		analyzeSerializations(parserRuleAnalyses);
	}

	protected void analyzeHiddenRules(@NonNull Grammar grammar) {
		for (AbstractRule hiddenRule : grammar.getHiddenTokens()) {
			assert hiddenRule != null;
			AbstractElement alternatives = hiddenRule.getAlternatives();
			if (alternatives instanceof Group) {
				List<AbstractElement> elements = ((Group)alternatives).getElements();
				int size = elements.size();
				if (size > 1) {
					AbstractElement firstElement = elements.get(0);
					if (firstElement instanceof Keyword) {
						if (size == 2) {
							AbstractElement lastElement = elements.get(1);
							if (lastElement instanceof UntilToken) {
								AbstractElement terminal = ((UntilToken)lastElement).getTerminal();
								if ((size == 2) && (terminal instanceof Keyword)) {
									Map<@NonNull String, @NonNull String> multipleLineCommentCharacterRanges2 = multipleLineCommentCharacterRanges;
									if (multipleLineCommentCharacterRanges2 == null) {
										multipleLineCommentCharacterRanges2 = multipleLineCommentCharacterRanges = new HashMap<>();
									}
									multipleLineCommentCharacterRanges2.put(SerializationUtils.nonNullState(((Keyword)firstElement).getValue()), SerializationUtils.nonNullState(((Keyword)terminal).getValue()));
								}
							}
						}
						else {
							List<@NonNull String> singleLineCommentKeywords2 = singleLineCommentKeywords;
							if (singleLineCommentKeywords2 == null) {
								singleLineCommentKeywords = singleLineCommentKeywords2 = new ArrayList<>();
							}
							singleLineCommentKeywords2.add(SerializationUtils.nonNullState(((Keyword)firstElement).getValue()));
						}
					}
				}
			}
		}
	}

	/**
	 *	Identify the production rule(s) for each EClass.
	 */
	protected @NonNull Map<@NonNull EClass, @NonNull List<@NonNull ParserRuleAnalysis>> analyzeProductions(
			@NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses) {
		Map<@NonNull EClass, @NonNull List<@NonNull ParserRuleAnalysis>> eClass2parserRules = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : ruleAnalysis.getSerializationRuleAnalyses()) {
				EClass eClass = serializationRuleAnalysis.getProducedEClass();
				List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = SerializationUtils.maybeNull(eClass2parserRules.get(eClass));
				if (parserRuleAnalyses == null) {
					parserRuleAnalyses = new ArrayList<>();
					eClass2parserRules.put(eClass, parserRuleAnalyses);
				}
				parserRuleAnalyses.add(ruleAnalysis);
			}
		}
		return eClass2parserRules;
	}

	/**
	 *	Return the rules for each rule name and populate the rule2ruleCalls from each rule.
	 */
	protected @NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> analyzeRuleNames(
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = new HashMap<>();
		List<@NonNull Grammar> grammars = new ArrayList<>();
		grammars.add(getGrammar());
		for (int i = 0; i < grammars.size(); i++) {
			@NonNull Grammar grammar = SerializationUtils.nonNullState(grammars.get(i));
			analyzeHiddenRules(grammar);
			for (@NonNull EObject eObject : new TreeIterable(grammar, true)) {
				if (eObject instanceof Grammar) {
					for (Grammar usedGrammar : ((Grammar)eObject).getUsedGrammars()) {
						if ((usedGrammar != null) && !grammars.contains(usedGrammar)) {
							grammars.add(usedGrammar);
						}
					}
				}
				else if (eObject instanceof AbstractRule) {
					AbstractRule abstractRule = (AbstractRule)eObject;
					String ruleName = SerializationUtils.getName(abstractRule);
					List<@NonNull AbstractRule> rules = SerializationUtils.maybeNull(ruleName2rules.get(ruleName));
					if (rules == null) {
						rules = new ArrayList<>();
						ruleName2rules.put(ruleName, rules);
					}
					rules.add(abstractRule);
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					AbstractRule rule = SerializationUtils.getRule(ruleCall);
					List<@NonNull RuleCall> ruleCalls = SerializationUtils.maybeNull(rule2ruleCalls.get(rule));
					if (ruleCalls == null) {
						ruleCalls = new ArrayList<>();
						rule2ruleCalls.put(rule, ruleCalls);
					}
					ruleCalls.add(ruleCall);
				}
			}
		}
		return ruleName2rules;
	}

	/**
	 * Return true if the containing feature is compatible with one of its containing assignments.
	 *
	 * If non-null each compatible assignment is assign to its corresponding production rulein ruleAnalysis2assignmentAnalyses.
	 *
	 * Compatbility requires
	 *
	 * The produced rule for this element is assignable to the assignment target's rule.
	 * The produced rule for the container of this element is assignable to the assignment source's rule.
	 * Recursively the container of this element has a similarly compatoble assignement.
	 */
	//protected abstract @Nullable List<@NonNull RequiredSlotsConjunction> isCompatible();
/*	@Override
	protected @Nullable List<@NonNull RequiredSlotsConjunction> isCompatible() {
		List<@NonNull RequiredSlotsConjunction> ruleAnalysis2assignmentAnalyses = new ArrayList<>();
		Iterable<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalysisCandidates = grammarAnalysis.getAssignmentAnalyses(eContainingFeature);
		for (@NonNull XtextAssignmentAnalysis containingAssignmentAnalysisCandidate : containingAssignmentAnalysisCandidates) {
			List<@NonNull XtextParserRuleAnalysis> compatibleTargetRuleAnalysisCandidates = null;
			EClass targetEClass = UserModelAnalysis.eClass(element);
			Iterable<@NonNull RequiredSlotsConjunction> targetRuleAnalysisCandidates = grammarAnalysis.getProducingRuleAnalyses(targetEClass);
			for (@NonNull RequiredSlotsConjunction targetRuleAnalysisCandidate : targetRuleAnalysisCandidates) {
			//	if (targetRuleAnalysisCandidate instanceof XtextParserRuleAnalysis) {
					if (containingAssignmentAnalysisCandidate.targetIsAssignableFrom(targetRuleAnalysisCandidate)) {					// If target rule compatible
						boolean isOkSource = false;
						Iterable<@NonNull XtextParserRuleAnalysis> containerProductionRules = containingElementAnalysis.getSerializationRules();
						for (@NonNull XtextAbstractRuleAnalysis sourceRuleAnalysisCandidate : containerProductionRules) {
							if (containingAssignmentAnalysisCandidate.sourceIsAssignableFrom(sourceRuleAnalysisCandidate)) {			// If source rule compatible
								if (containingElementAnalysis.isCompatible(null)) {													// If transitively compatible
									isOkSource = true;
									break;
								}
							}
						}
						if (isOkSource) {
							if (compatibleTargetRuleAnalysisCandidates == null) {
								compatibleTargetRuleAnalysisCandidates = new ArrayList<>(4);
							}
							compatibleTargetRuleAnalysisCandidates.add((XtextParserRuleAnalysis)targetRuleAnalysisCandidate);
						}
					}
			//	}
			}
			if (compatibleTargetRuleAnalysisCandidates != null) {
				for (@NonNull XtextParserRuleAnalysis compatibleTargetRuleAnalysisCandidate : compatibleTargetRuleAnalysisCandidates) {
					if (ruleAnalysis2assignmentAnalyses == null) {
						return true;
					}
					List<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalyses = ruleAnalysis2assignmentAnalyses.get(compatibleTargetRuleAnalysisCandidate);
					if (containingAssignmentAnalyses == null) {
						containingAssignmentAnalyses = new ArrayList<>();
						ruleAnalysis2assignmentAnalyses.put(compatibleTargetRuleAnalysisCandidate, containingAssignmentAnalyses);
					}
					containingAssignmentAnalyses.add(containingAssignmentAnalysisCandidate);
				}
			}
		}
		return false;
	} */

	protected void analyzeSerializations(@NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses) {
		Map<@NonNull SerializationRule, @NonNull SerializationRuleAnalysis> serializationRule2aserializationRuleAnalysis2 = serializationRule2aserializationRuleAnalysis;
		assert serializationRule2aserializationRuleAnalysis2 == null;
		serializationRule2aserializationRuleAnalysis = serializationRule2aserializationRuleAnalysis2 = new HashMap<>();
		Map<@NonNull EClass, @NonNull List<@NonNull SerializationRuleAnalysis>> eClass2serializationRuleList = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : ruleAnalysis.getSerializationRuleAnalyses()) {
				EClass eClass = serializationRuleAnalysis.getProducedEClass();
				List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = SerializationUtils.maybeNull(eClass2serializationRuleList.get(eClass));
				if (serializationRuleAnalyses == null) {
					serializationRuleAnalyses = new ArrayList<>();
					eClass2serializationRuleList.put(eClass, serializationRuleAnalyses);
				}
				if (!serializationRuleAnalyses.contains(serializationRuleAnalysis)) {
					serializationRuleAnalyses.add(serializationRuleAnalysis);
				}
			}
		}
		for (Map.Entry<@NonNull EClass, @NonNull List<@NonNull SerializationRuleAnalysis>> entry : eClass2serializationRuleList.entrySet()) {
			EClass eClass = SerializationUtils.nonNullState(entry.getKey());
			List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = SerializationUtils.nonNullState(entry.getValue());
			Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> eContainmentFeature2assignedTargetRuleValues = getEContainmentFeature2assignedTargetRuleValues(serializationRuleAnalyses);
			@NonNull EReference_TargetGrammarRuleVector[] eReferenceRuleIndexes = null;
			if (eContainmentFeature2assignedTargetRuleValues != null) {
				eReferenceRuleIndexes = new @NonNull EReference_TargetGrammarRuleVector[eContainmentFeature2assignedTargetRuleValues.size()];
				int i2 = 0;
				for (Map.Entry<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> entry2 : eContainmentFeature2assignedTargetRuleValues.entrySet()) {
					Set<@NonNull GrammarRuleValue> values = SerializationUtils.nonNullState(entry2.getValue());
					ParserRuleValue[] parserRuleValues = new ParserRuleValue[values.size()];
					int i3 = 0;
					for (GrammarRuleValue value : values) {
						parserRuleValues[i3++] = (ParserRuleValue) value;
					}
					eReferenceRuleIndexes[i2++] = new EReference_TargetGrammarRuleVector(entry2.getKey(), new GrammarRuleVector(parserRuleValues));
				}
			}
			@NonNull SerializationRule [] serializationRules = new @NonNull SerializationRule [serializationRuleAnalyses.size()];
			int i = 0;
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
				SerializationRule serializationRule = serializationRuleAnalysis.getSerializationRule();
				serializationRules[i++] = serializationRule;
				serializationRule2aserializationRuleAnalysis2.put(serializationRule, serializationRuleAnalysis);
			}
			addEClassValue(new EClassValue(eClass, serializationRules, eReferenceRuleIndexes));
		}
		for (@NonNull SerializationRule serializationRule : serializationRule2aserializationRuleAnalysis2.keySet()) {
			SerializationRuleAnalysis serializationRuleAnalysis = SerializationUtils.maybeNull(serializationRule2aserializationRuleAnalysis2.get(serializationRule));
			assert serializationRuleAnalysis != null;
			serializationRule = serializationRuleAnalysis.getSerializationRule();
		}
	}

	//	public @Nullable Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> basicGetEContainmentFeature2assignedTargetRuleValues(@NonNull EClass eClass) {
	//		EClassValue eClassValue = getEClassValue(eClass);
	//		return eClassValue.basicGetEContainmentFeature2assignedTargetRuleValues();
	//	}

	public @NonNull EReference_TargetGrammarRuleVector @Nullable [] basicGetEReferenceRuleIndexes(@NonNull EClass eClass) {
		EClassValue eClassValue = getEClassValue(eClass);
		return eClassValue.basicGetEReferenceRuleIndexes();
	}

	public @Nullable AbstractRuleAnalysis basicGetRuleAnalysis(int ruleValueIndex) {
			return ruleAnalyses.get(ruleValueIndex);
		}

	public @Nullable GrammarRuleValue basicGetRuleValue(int ruleValueIndex) {
		AbstractRuleAnalysis ruleAnalysis = SerializationUtils.nonNullState(ruleAnalyses.get(ruleValueIndex));
		return ruleAnalysis.basicGetRuleValue();
	}

	public @NonNull IdiomGrammarMatch createIdiomMatch(@NonNull Idiom idiom, @NonNull AbstractElement grammarElement) {
		return new IdiomGrammarMatch(idiom, grammarElement);
	}

	public @NonNull IdiomSerializationMatch createIdiomMatch(@NonNull Idiom idiom, @NonNull SerializationNode serializationNode) {
		return new IdiomSerializationMatch(idiom, serializationNode);
	}

	/**
	 *	Create a RuleAnalysis for each distinct name.
	 */
	protected void createRuleAnalyses(
			@NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules,
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		List<@NonNull String> ruleNames = new ArrayList<>(ruleName2rules.keySet());
		Collections.sort(ruleNames);
		for (@NonNull String ruleName : ruleNames) {
			List<@NonNull AbstractRule> rules = SerializationUtils.maybeNull(ruleName2rules.get(ruleName));
			assert rules != null;
			AbstractRule activeRule = null;
			if (rules.size() == 1) {
				activeRule = SerializationUtils.maybeNull(rules.get(0));
			}
			else {
				for (@NonNull AbstractRule rule : rules) {
					List<@NonNull RuleCall> ruleCalls = SerializationUtils.maybeNull(rule2ruleCalls.get(rule));
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
			int ruleIndex = ruleAnalyses.size();
			AbstractRuleAnalysis ruleAnalysis;
			if (activeRule instanceof ParserRule) {
				EClassifier eClassifier = SerializationUtils.getClassifier(activeRule.getType());
				if (eClassifier instanceof EClass) {
					ruleAnalysis = new ParserRuleAnalysis(this, ruleIndex, (ParserRule)activeRule, (EClass)eClassifier);
				}
				else {
					ruleAnalysis = new DataTypeRuleAnalysis(this, ruleIndex, (ParserRule)activeRule, (EDataType)eClassifier);
				}
			}
			else if (activeRule instanceof TerminalRule) {
				ruleAnalysis = new TerminalRuleAnalysis(this, ruleIndex, (TerminalRule)activeRule);
			}
			else {
				throw new UnsupportedOperationException();
			}
			rule2ruleAnalysis.put(activeRule, ruleAnalysis);
			ruleAnalyses.add(ruleAnalysis);
		}
	}

	public @NonNull ActionAssignmentAnalysis getAssignmentAnalysis(@NonNull Action action) {
		assert assignment2assignmentAnalysis != null;
		return (ActionAssignmentAnalysis) SerializationUtils.nonNullState(assignment2assignmentAnalysis.get(action));
	}
	public @NonNull List<@NonNull AssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return SerializationUtils.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull DirectAssignmentAnalysis getAssignmentAnalysis(@NonNull Assignment assignment) {
		assert assignment2assignmentAnalysis != null;
		return (DirectAssignmentAnalysis) SerializationUtils.nonNullState(assignment2assignmentAnalysis.get(assignment));
	}

	public @NonNull EClassValue getEClassValue(@NonNull EClass eClass) {
		assert eClass2eClassValue.size() > 0;
		return SerializationUtils.nonNullState(eClass2eClassValue.get(eClass));
	}

	public @NonNull EReference_TargetGrammarRuleVector @NonNull [] getEReferenceRuleIndexes(@NonNull EClass eClass) {
		return SerializationUtils.nonNullState(basicGetEReferenceRuleIndexes(eClass));
	}

	public @Nullable Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> getEContainmentFeature2assignedTargetRuleValues(
			@NonNull Iterable<@NonNull SerializationRuleAnalysis> serializationRules) {
		Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> eContainmentFeature2assignedTargetRuleValues = null;
		for (EReference eContainmentFeature : containment2assignmentAnalyses.keySet()) {	// FIXME this is needlessly broad
			Set<@NonNull GrammarRuleValue> targetRuleValues = null;
			for (@NonNull SerializationRuleAnalysis serializationRule : serializationRules) {
				Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = serializationRule.getAssignedSerializationNodes(eContainmentFeature);
				if (assignedSerializationNodes != null) {
					for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
						for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getTargetRuleAnalyses()) {
							if (targetRuleValues == null) {
								targetRuleValues = new HashSet<>();
							}
							targetRuleValues.add(targetRuleAnalysis.getRuleValue());
						}
					}
				}
			}
			if (targetRuleValues != null) {
				if (eContainmentFeature2assignedTargetRuleValues == null) {
					eContainmentFeature2assignedTargetRuleValues = new HashMap<>();
				}
				eContainmentFeature2assignedTargetRuleValues.put(eContainmentFeature, targetRuleValues);
			}
		}
		return eContainmentFeature2assignedTargetRuleValues;
	}

	public @NonNull EnumerationValue getEnumerationValue(@NonNull Keyword keyword) {
		String value = SerializationUtils.getValue(keyword);
		EnumerationValueSingle enumerationValue = SerializationUtils.maybeNull(value2enumerationValue.get(value));
		if (enumerationValue == null) {
			enumerationValue = new EnumerationValueSingle(value);
			value2enumerationValue.put(value, enumerationValue);
		}
		return enumerationValue;
	}

	public @NonNull EnumerationValue getEnumerationValue(@NonNull Iterable<@NonNull Keyword> keywords) {
		List<@NonNull String> values = new ArrayList<>();
		for (@NonNull Keyword keyword : keywords) {
			values.add(SerializationUtils.getValue(keyword));
		}
		Collections.sort(values);
		EnumerationValueMultiple enumerationValue = SerializationUtils.maybeNull(values2enumerationValue.get(values));
		if (enumerationValue == null) {
			enumerationValue = new EnumerationValueMultiple(values);
			values2enumerationValue.put(values, enumerationValue);
		}
		return enumerationValue;
	}

	public @NonNull Iterable<@NonNull EnumerationValue> getEnumerationValues() {
		return new HashSet<>(values2enumerationValue.values());
	}

	public @NonNull Grammar getGrammar() {
		Grammar grammar2 = grammar;
		if (grammar2 == null) {
			grammar = grammar2 = SerializationUtils.nonNullState(grammarProvider.getGrammar(this));
		}
		return grammar2;
	}

	public @NonNull IdiomsModel getIdiomsModel(@NonNull ResourceSet resourceSet, @NonNull URI uri) {
		IdiomsPackage.eINSTANCE.getClass();
		Resource resource = resourceSet.getResource(uri, true);
		EcoreUtil.resolveAll(resourceSet);				// Avoid no-equality of proxies
		@SuppressWarnings("null")
		@NonNull IdiomsModel castIdiomModel = (IdiomsModel)resource.getContents().get(0);
		return castIdiomModel;
	}

	public @NonNull Iterable<@NonNull Idiom> getIdioms() {
		Grammar grammar = getGrammar();
		ResourceSet resourceSet = grammar.eResource().getResourceSet();
		if (resourceSet == null) {
			if (resourceSetProvider != null) {
				resourceSet = resourceSetProvider.get();
				assert resourceSet != null;
			}
			else {
				resourceSet = new ResourceSetImpl();
			}
		}
	//	String idiomsPath =  "/" + grammar.getName().replace('.', '/') + ".idioms";
		URI xtextURI = grammar.eResource().getURI();
		URI idiomsURI = xtextURI.trimFileExtension().appendFileExtension("idioms");
		assert idiomsURI != null;
		List<@NonNull IdiomsModel> idiomsModels = Lists.newArrayList(getIdiomsModel(resourceSet, idiomsURI));
		List<@NonNull Idiom> idioms = new ArrayList<>();
		StringBuilder s = null;
		for (int i = 0; i < idiomsModels.size(); i++) {
			IdiomsModel idiomsModel = SerializationUtils.nonNullState(idiomsModels.get(i));
			List<Resource.Diagnostic> errors = SerializationUtils.nonNullState(idiomsModel.eResource().getErrors());
			if (errors.size() > 0) {
				if (s == null) {
					s = new StringBuilder();
				}
				s.append(SerializationUtils.formatResourceDiagnostics(errors, "", "\n"));
			}
			Iterables.addAll(idioms, IdiomsUtils.getOwnedIdioms(idiomsModel));
			for (@NonNull IdiomsImport idiomsImport : IdiomsUtils.getOwnedWiths(idiomsModel)) {
				IdiomsModel importedIdiomsModel = IdiomsUtils.getIdiomsModel(idiomsImport);
				if (!idiomsModels.contains(importedIdiomsModel)) {
					idiomsModels.add(importedIdiomsModel);
				}
			}
		}
		if (s != null) {
			throw new IllegalStateException("Failed to load idioms" + s.toString());
		}
		return idioms;
	}

	public @NonNull SerializationSegment @NonNull [] @NonNull []  getInnerFormattingSegments(@NonNull ParserRuleValue parserRuleValue) {
		return parserRuleValue.getInnerFormattingSegments();
	}

	public @NonNull GrammarRuleVector getLeafGrammarRulesVector() {
		GrammarRuleVector leafGrammarRulesVector2 = leafGrammarRulesVector ;
		if (leafGrammarRulesVector2 == null) {
			leafGrammarRulesVector2 = new GrammarRuleVector();
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : rule2ruleAnalysis.values()) {
				if (ruleAnalysis instanceof ParserRuleAnalysis) {
					Collection<@NonNull ParserRuleAnalysis> subRuleAnalysesClosure = ((ParserRuleAnalysis)ruleAnalysis).getSubRuleAnalysesClosure();
					if (subRuleAnalysesClosure.size() <= 1) {
						leafGrammarRulesVector2.set(ruleAnalysis.getIndex());
					}
				}
			}
		}
		return leafGrammarRulesVector2;
	}

	public LocatorHelper.@NonNull LocatorSwitch getLocatorSwitch() {
		return LocatorHelper.LocatorSwitch.INSTANCE;
	}

	public @Nullable Map<@NonNull String, @NonNull String> getMultipleLineCommentCharacterRanges() {
		return multipleLineCommentCharacterRanges;
	}

/*	public @NonNull Iterable<@NonNull EClass> getSortedProducedEClasses() {
		assert eClass2serializationRules != null;
		List<@NonNull EClass> list = new ArrayList<>(SerializationUtils.nonNullState(eClass2serializationRules.keySet()));
		Collections.sort(list, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		return list;
	} */

	public @NonNull SerializationSegment @NonNull [] @NonNull []  getOuterFormattingSegments(@NonNull ParserRuleValue parserRuleValue) {
		return parserRuleValue.getOuterFormattingSegments();
	}

	public @NonNull List<@NonNull ParserRuleAnalysis> getProducingRuleAnalyses(@NonNull EClass eClass) {
		assert eClass2ruleAnalyses != null;
		return SerializationUtils.nonNullState(eClass2ruleAnalyses.get(eClass));
	}

	public @NonNull Iterable<@NonNull AbstractRuleAnalysis> getRuleAnalyses() {
		assert eClass2ruleAnalyses != null;
		return SerializationUtils.nonNullState(rule2ruleAnalysis.values());
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		assert rule2ruleAnalysis != null;
		return SerializationUtils.nonNullState(rule2ruleAnalysis.get(abstractRule));
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(int ruleValueIndex) {
		return SerializationUtils.nonNullState(ruleAnalyses.get(ruleValueIndex));
	}

//	@Override
	public @NonNull String getRuleName(int ruleValueIndex) {
		return getRuleAnalysis(ruleValueIndex).getName();
	}

//	@Override
	public @NonNull GrammarRuleValue getRuleValue(int ruleValueIndex) {
		return getRuleAnalysis(ruleValueIndex).getRuleValue();
	}

//	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis(@NonNull ParserRuleAnalysis parserRuleAnalysis, @NonNull SerializationNode thatSerializationNode) {
//		SerializationRuleAnalysis serializationRuleAnalysis = new SerializationRuleAnalysis(parserRuleAnalysis, thatSerializationNode);
//		return serializationRuleAnalysis;
//	}

	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis(@NonNull SerializationRule serializationRule) {
		assert serializationRule2aserializationRuleAnalysis != null;
		return SerializationUtils.nonNullState(serializationRule2aserializationRuleAnalysis.get(serializationRule));
	}

	public @NonNull SerializationRule @NonNull [] getSerializationRules(@NonNull ParserRuleValue ruleValue) {
		return ruleValue.getSerializationRules();
	}

	public @NonNull SerializationSegment getSerializationSegment(@NonNull Segment segment) {
		SerializationSegment serializationSegment = SerializationUtils.maybeNull(segment2serializationSegment.get(segment));
		if (serializationSegment == null) {
			if (segment instanceof HalfNewLineSegment) {
				serializationSegment = SerializationSegment.HALF_NEW_LINE;
			}
			else if (segment instanceof NewLineSegment) {
				serializationSegment = SerializationSegment.NEW_LINE;
			}
			else if (segment instanceof NoSpaceSegment) {
				serializationSegment = SerializationSegment.NO_SPACE;
			}
			else if (segment instanceof PopSegment) {
				serializationSegment = SerializationSegment.POP;
			}
			else if (segment instanceof PostCommentSegment) {
				serializationSegment = SerializationSegment.POST_COMMENT;
			}
			else if (segment instanceof PreCommentSegment) {
				serializationSegment = SerializationSegment.PRE_COMMENT;
			}
			else if (segment instanceof PushSegment) {
				serializationSegment = SerializationSegment.PUSH;
			}
			else if (segment instanceof SoftNewLineSegment) {
				serializationSegment = SerializationSegment.SOFT_NEW_LINE;
			}
			else if (segment instanceof SoftSpaceSegment) {
				serializationSegment = SerializationSegment.SOFT_SPACE;
			}
			else if (segment instanceof ValueSegment) {
				serializationSegment = SerializationSegment.VALUE;
			}
			else if (segment instanceof CustomSegment) {
				String supportClassName = ((CustomSegment) segment).getSupportClassName();
				assert supportClassName != null;
				serializationSegment = new CustomSerializationSegment(supportClassName);
			}
			else {
				throw new UnsupportedOperationException();
			}
			segment2serializationSegment.put(segment, serializationSegment);
		}
		return serializationSegment;
	}

	public @NonNull SerializationSegment @Nullable [] getSerializationSegments(@NonNull List<@NonNull SubIdiom> subIdioms, @Nullable Boolean isAll) {
		@NonNull SerializationSegment @Nullable [] serializationSegments = null;
		List<@NonNull Segment> mergedSegments = null;
		for (@NonNull SubIdiom subIdiom : subIdioms) {
			if ((isAll == null) || (subIdiom.isAll() == isAll.booleanValue())) {
				List<Segment> segments = subIdiom.getOwnedSegments();
				if (segments != null) {
					if (mergedSegments == null) {
						mergedSegments = new ArrayList<>(segments);
					}
					else {
						List<Segment> newMergedSegments = new ArrayList<>(segments.size() + mergedSegments.size() - 1);
						for (@NonNull Segment segment : mergedSegments) {
							if (segment instanceof ValueSegment) {
								newMergedSegments.addAll(segments);
							}
							else {
								newMergedSegments.add(segment);
							}
						}
						mergedSegments = newMergedSegments;
					}
				}
			}
		}
		if (mergedSegments != null) {
			serializationSegments = new @NonNull SerializationSegment[mergedSegments.size()];
			for (int j = 0; j < serializationSegments.length; j++) {
				Segment segment = SerializationUtils.nonNullState(mergedSegments.get(j));
				if (segment instanceof ReferredSegment) {
					segment = IdiomsUtils.getOwnedSegment(IdiomsUtils.getSegmentDeclaration((ReferredSegment)segment));
				}
				serializationSegments[j] = getSerializationSegment(segment);
			}
		}
		return serializationSegments;
	}

	public @Nullable List<@NonNull String> getSingleLineCommentKeywords() {
		return singleLineCommentKeywords;
	}

	public @NonNull Iterable<@NonNull EClassValue> getSortedProducedEClassValues() {
		List<@NonNull EClassValue> sortedProducedEClassValues2 = sortedProducedEClassValues;
		if (sortedProducedEClassValues2 == null) {
			assert eClass2eClassValue.size() > 0;
			sortedProducedEClassValues = sortedProducedEClassValues2 = new ArrayList<>(SerializationUtils.nonNullState(eClass2eClassValue.values()));
			Collections.sort(sortedProducedEClassValues, SerializationUtils.NAMEABLE_COMPARATOR);
		}
		return sortedProducedEClassValues2;
	}

	@Override
	public @NonNull String toString() {
		DiagnosticStringBuilder s = new DiagnosticStringBuilder();
		s.append("Xtext production rule -> Xtext base rules <=> User EClass - User EStructuralFeatures");
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			s.append("\n  ");
			s.append(abstractRuleAnalysis.getName());
			if (abstractRuleAnalysis instanceof ParserRuleAnalysis) {
				ParserRuleAnalysis parserRuleAnalysis = (ParserRuleAnalysis)abstractRuleAnalysis;
				s.append(" -");
				Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> eFeature2assignmentAnalyses = parserRuleAnalysis.getEFeature2assignmentAnalyses();
				List<@NonNull EStructuralFeature> eFeatures = new ArrayList<>(eFeature2assignmentAnalyses.keySet());
				Collections.sort(eFeatures, SerializationUtils.ENAMED_ELEMENT_COMPARATOR);
				boolean isFirstFeature = true;
				for (@NonNull EStructuralFeature eFeature : eFeatures) {
					if (isFirstFeature) {
						s.append(" ");
					}
					else {
						s.append(",");
					}
					List<@NonNull AssignmentAnalysis> assignmentAnalyses = SerializationUtils.maybeNull(eFeature2assignmentAnalyses.get(eFeature));
					assert assignmentAnalyses != null;
					int size = assignmentAnalyses.size();
					if (size != 1) {
						s.appendObject(size);
						s.append("*");
					}
					s.append(eFeature.getName());
					isFirstFeature = false;
				}
				//
				for (@NonNull SerializationRuleAnalysis serializationRule : parserRuleAnalysis.getSerializationRuleAnalyses()) {
					s.appendIndentation(2);
					s.append("|& ");
					serializationRule.toRuleString(s, -1);
				}
			}
		}
		s.append("\n\nUser EClass <=> Prioritized serialization rule(s)");
		s.append(super.toString());
		@SuppressWarnings("null")
		@NonNull String castString = s.toString();
		return castString;
	}
}
