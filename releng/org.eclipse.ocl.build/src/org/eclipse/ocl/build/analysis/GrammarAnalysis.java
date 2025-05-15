/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.analysis;

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
import org.eclipse.ocl.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.build.elements.SerializationNode;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.xtext.base.serializer.EClassValue;
import org.eclipse.ocl.xtext.base.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.xtext.base.serializer.GrammarRuleValue;
import org.eclipse.ocl.xtext.base.serializer.GrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.ParserRuleValue;
import org.eclipse.ocl.xtext.base.serializer.SerializationRule;
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment;
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.xtext.base.serializer.SerializationUtils;
import org.eclipse.ocl.xtext.idioms.CustomSegment;
import org.eclipse.ocl.xtext.idioms.FinalLocator;
import org.eclipse.ocl.xtext.idioms.HalfNewLineSegment;
import org.eclipse.ocl.xtext.idioms.Idiom;
import org.eclipse.ocl.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.xtext.idioms.KeywordLocator;
import org.eclipse.ocl.xtext.idioms.Locator;
import org.eclipse.ocl.xtext.idioms.NewLineSegment;
import org.eclipse.ocl.xtext.idioms.NoNewLineSegment;
import org.eclipse.ocl.xtext.idioms.NoSpaceSegment;
import org.eclipse.ocl.xtext.idioms.PopSegment;
import org.eclipse.ocl.xtext.idioms.PostCommentSegment;
import org.eclipse.ocl.xtext.idioms.PreCommentSegment;
import org.eclipse.ocl.xtext.idioms.PushSegment;
import org.eclipse.ocl.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.xtext.idioms.ReferredSegment;
import org.eclipse.ocl.xtext.idioms.RuleLocator;
import org.eclipse.ocl.xtext.idioms.Segment;
import org.eclipse.ocl.xtext.idioms.SoftNewLineSegment;
import org.eclipse.ocl.xtext.idioms.SoftSpaceSegment;
import org.eclipse.ocl.xtext.idioms.StringSegment;
import org.eclipse.ocl.xtext.idioms.SubIdiom;
import org.eclipse.ocl.xtext.idioms.ValueSegment;
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

	private @Nullable List<@NonNull Idiom> idioms = null;

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
	private @Nullable Map<@NonNull EClassifier, @NonNull List<@NonNull AbstractNonTerminalRuleAnalysis>> eClassifier2ruleAnalyses = null;

	/**
	 * The values of enumerated features
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = null;

	private  final @NonNull Map<@NonNull String, @NonNull EnumerationValueSingle> value2enumerationValue = new HashMap<>();
	private  final @NonNull Map<@NonNull List<@NonNull String>, @NonNull EnumerationValueMultiple> values2enumerationValue = new HashMap<>();

	private @Nullable List<@NonNull EClassValue> sortedProducedEClassValues = null;
	private @Nullable Map<@NonNull SerializationRule, @NonNull SerializationRuleAnalysis> serializationRule2serializationRuleAnalysis = null;

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
		AssignmentAnalysis old = assignment2assignmentAnalysis.put(assignmentAnalysis.getActionOrAssignment(), assignmentAnalysis);
		assert old == null;
	}

	public void addContainment(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull EReference eReference) {
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = containment2assignmentAnalyses.get(eReference);
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			containment2assignmentAnalyses.put(eReference, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	protected void addEClassValue(@NonNull EClassValue eClassValue) {
		EClassValue old = eClass2eClassValue.put(eClassValue.getEClass(), eClassValue);
		assert old == null;
	}

	public void addEnumeration(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues2 = eAttribute2enumerationValues;
		if (eAttribute2enumerationValues2 == null) {
			eAttribute2enumerationValues = eAttribute2enumerationValues2 = new HashMap<>();
		}
		Set<@NonNull EnumerationValue> enumerationValues = eAttribute2enumerationValues2.get(eAttribute);
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
		List<@NonNull Locator> dataTypeLocators = analyzeIdioms();
		Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls = new HashMap<>();
		Map<@NonNull String, @NonNull AbstractRule> ruleName2rule = new HashMap<>();
		Map<@NonNull AbstractRule, @NonNull AbstractRule> overriddenRule2overridingRule = new HashMap<>();
		analyzeRuleNames(rule2ruleCalls, ruleName2rule, overriddenRule2overridingRule);
		/*this.rule2ruleAnalysis =*/ createRuleAnalyses(ruleName2rule, overriddenRule2overridingRule, rule2ruleCalls);
		List<@NonNull DataTypeRuleAnalysis> dataTypeRuleAnalyses = new ArrayList<>();
		List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = new ArrayList<>();
		List<@NonNull AbstractNonTerminalRuleAnalysis> nonTerminalRuleAnalyses = new ArrayList<>();
		List<@NonNull TerminalRuleAnalysis> terminalRuleAnalyses = new ArrayList<>();
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			if (abstractRuleAnalysis instanceof DataTypeRuleAnalysis) {
				dataTypeRuleAnalyses.add((DataTypeRuleAnalysis)abstractRuleAnalysis);
				nonTerminalRuleAnalyses.add((DataTypeRuleAnalysis)abstractRuleAnalysis);
			}
			else if (abstractRuleAnalysis instanceof ParserRuleAnalysis) {
				parserRuleAnalyses.add((ParserRuleAnalysis)abstractRuleAnalysis);
				nonTerminalRuleAnalyses.add((ParserRuleAnalysis)abstractRuleAnalysis);
			}
			else if (abstractRuleAnalysis instanceof TerminalRuleAnalysis) {
				terminalRuleAnalyses.add((TerminalRuleAnalysis)abstractRuleAnalysis);
			}
		}
		Collections.sort(dataTypeRuleAnalyses, AbstractRuleAnalysis.QualifiedNameableComparator.INSTANCE);
		Collections.sort(nonTerminalRuleAnalyses, AbstractRuleAnalysis.QualifiedNameableComparator.INSTANCE);
		Collections.sort(parserRuleAnalyses, AbstractRuleAnalysis.QualifiedNameableComparator.INSTANCE);
		Collections.sort(terminalRuleAnalyses, AbstractRuleAnalysis.QualifiedNameableComparator.INSTANCE);
		//
		//	Create an assignment analysis for each assignment and current action.
		//	Populate GrammarAnalysis.assignment2assignmentAnalysis, ParserRuleAnalysis.callingRuleAnalysis
		//
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.analyzeActionsAndAssignments();
		}
		//
		//	Promote ParserRuleAnalysis.superRuleAnalysis to ParserRuleAnalysis.sub/superRuleAnalysisClosure
		//
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.getSuperRuleAnalysisClosure();
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
		for (@NonNull DataTypeRuleAnalysis dataTypeRuleAnalysis : dataTypeRuleAnalyses) {
			dataTypeRuleAnalysis.analyzeUnitLocators();
		}
		for (@NonNull DataTypeRuleAnalysis dataTypeRuleAnalysis : dataTypeRuleAnalyses) {
			dataTypeRuleAnalysis.analyzeSpacing(dataTypeLocators);
		}
	//	for (@NonNull DataTypeRuleAnalysis dataTypeRuleAnalysis : dataTypeRuleAnalyses) {
	//		dataTypeRuleAnalysis.getSubstringRuleAnalyses();		// Triggers lazy analyze();
	//	}
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.getSerializationRuleAnalyses();		// Triggers lazy analyze();
		}
		//
		//	Determine the variables and expressions and their solutions to determine the cardinality of each term.
		//
	//	for (@NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis : nonTerminalRuleAnalyses) {
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.analyzeMatches();
		}
		this.eClassifier2ruleAnalyses = analyzeProductions(parserRuleAnalyses);
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
									multipleLineCommentCharacterRanges2.put(ClassUtil.requireNonNull(((Keyword)firstElement).getValue()), ClassUtil.requireNonNull(((Keyword)terminal).getValue()));
								}
							}
						}
						else {
							List<@NonNull String> singleLineCommentKeywords2 = singleLineCommentKeywords;
							if (singleLineCommentKeywords2 == null) {
								singleLineCommentKeywords = singleLineCommentKeywords2 = new ArrayList<>();
							}
							singleLineCommentKeywords2.add(ClassUtil.requireNonNull(((Keyword)firstElement).getValue()));
						}
					}
				}
			}
		}
	}

	protected @NonNull List<@NonNull Locator> analyzeIdioms() {
		List<@NonNull Locator> dataTypeLocators = new ArrayList<>();
		for (@NonNull Idiom idiom : getIdioms()) {
			for (@NonNull SubIdiom subIdiom : IdiomsUtils.getOwnedSubIdioms(idiom)) {
				Locator locator = subIdiom.getOwnedLocator();
				while (locator instanceof ReferredLocator) {
					locator = ((ReferredLocator)locator).getLocatorDeclaration().getOwnedLocator();
				}
				if (locator instanceof FinalLocator) {
					dataTypeLocators.add(locator);
				}
				else if (locator instanceof KeywordLocator) {
					dataTypeLocators.add(locator);
				}
				else if (locator instanceof RuleLocator) {
					AbstractRule rule = ((RuleLocator)locator).getReferredRule();
					if (rule instanceof ParserRule) {
						EClassifier eClassifier = SerializationUtils.getClassifier(SerializationUtils.getType(rule));
						if (eClassifier instanceof EDataType) {
							dataTypeLocators.add(locator);
						}
					}
				}
			}
		}
		return dataTypeLocators;
	}

	/**
	 *	Identify the production rule(s) for each EClass.
	 */
	protected @NonNull Map<@NonNull EClassifier, @NonNull List<@NonNull AbstractNonTerminalRuleAnalysis>> analyzeProductions(
			@NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses) {
		Map<@NonNull EClassifier, @NonNull List<@NonNull AbstractNonTerminalRuleAnalysis>> eClassifier2nonTerminalRules = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : ruleAnalysis.getSerializationRuleAnalyses()) {
				EClass eClass = serializationRuleAnalysis.getProducedEClass();
				List<@NonNull AbstractNonTerminalRuleAnalysis> nonTerminalRuleAnalyses = eClassifier2nonTerminalRules.get(eClass);
				if (nonTerminalRuleAnalyses == null) {
					nonTerminalRuleAnalyses = new ArrayList<>();
					eClassifier2nonTerminalRules.put(eClass, nonTerminalRuleAnalyses);
				}
				nonTerminalRuleAnalyses.add(ruleAnalysis);
			}
		}
		return eClassifier2nonTerminalRules;
	}

	/**
	 *	Return the rules for each rule name and populate the rule2ruleCalls from each rule.
	 * @param overridden2overridingRule
	 * @param ruleName2rule
	 */
	protected void analyzeRuleNames(@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls,
			@NonNull Map<@NonNull String, @NonNull AbstractRule> ruleName2rule, @NonNull Map<@NonNull AbstractRule, @NonNull AbstractRule> overriddenRule2overridingRule) {
		List<@NonNull Grammar> grammars = new ArrayList<>();
		grammars.add(getGrammar());
		for (int i = 0; i < grammars.size(); i++) {
			@NonNull Grammar grammar = ClassUtil.requireNonNull(grammars.get(i));
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
					AbstractRule overridingRule = ruleName2rule.get(ruleName);
					if (overridingRule == null) {
						ruleName2rule.put(ruleName, abstractRule);
					}
					else {
						overriddenRule2overridingRule.put(abstractRule, overridingRule);
					}
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					AbstractRule rule = SerializationUtils.getRule(ruleCall);
					List<@NonNull RuleCall> ruleCalls = rule2ruleCalls.get(rule);
					if (ruleCalls == null) {
						ruleCalls = new ArrayList<>();
						rule2ruleCalls.put(rule, ruleCalls);
					}
					ruleCalls.add(ruleCall);
				}
			}
		}
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
		Map<@NonNull SerializationRule, @NonNull SerializationRuleAnalysis> serializationRule2serializationRuleAnalysis2 = serializationRule2serializationRuleAnalysis;
		assert serializationRule2serializationRuleAnalysis2 == null;
		serializationRule2serializationRuleAnalysis = serializationRule2serializationRuleAnalysis2 = new HashMap<>();
		Map<@NonNull EClassifier, @NonNull List<@NonNull SerializationRuleAnalysis>> eClassifier2serializationRuleList = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : ruleAnalysis.getSerializationRuleAnalyses()) {
				EClass eClass = serializationRuleAnalysis.getProducedEClass();
				List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = eClassifier2serializationRuleList.get(eClass);
				if (serializationRuleAnalyses == null) {
					serializationRuleAnalyses = new ArrayList<>();
					eClassifier2serializationRuleList.put(eClass, serializationRuleAnalyses);
				}
				if (!serializationRuleAnalyses.contains(serializationRuleAnalysis)) {
					serializationRuleAnalyses.add(serializationRuleAnalysis);
				}
			}
		}
		for (Map.Entry<@NonNull EClassifier, @NonNull List<@NonNull SerializationRuleAnalysis>> entry : eClassifier2serializationRuleList.entrySet()) {
			EClassifier eClassifier = ClassUtil.requireNonNull(entry.getKey());
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass)eClassifier;
				List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = ClassUtil.requireNonNull(entry.getValue());
				Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> eContainmentFeature2assignedTargetRuleValues = getEContainmentFeature2assignedTargetRuleValues(serializationRuleAnalyses);
				@NonNull EReference_TargetGrammarRuleVector[] eReferenceRuleIndexes = null;
				if (eContainmentFeature2assignedTargetRuleValues != null) {
					eReferenceRuleIndexes = new @NonNull EReference_TargetGrammarRuleVector[eContainmentFeature2assignedTargetRuleValues.size()];
					int i2 = 0;
					for (Map.Entry<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> entry2 : eContainmentFeature2assignedTargetRuleValues.entrySet()) {
						Set<@NonNull GrammarRuleValue> values = ClassUtil.requireNonNull(entry2.getValue());
						ParserRuleValue[] nonTerminalRuleValues = new ParserRuleValue[values.size()];
						int i3 = 0;
						for (GrammarRuleValue value : values) {
							nonTerminalRuleValues[i3++] = (ParserRuleValue)value;
						}
						eReferenceRuleIndexes[i2++] = new EReference_TargetGrammarRuleVector(entry2.getKey(), new GrammarRuleVector(nonTerminalRuleValues));
					}
				}
				@NonNull SerializationRule [] serializationRules = new @NonNull SerializationRule [serializationRuleAnalyses.size()];
				int i = 0;
				for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
					SerializationRule serializationRule = serializationRuleAnalysis.getSerializationRule();
					serializationRules[i++] = serializationRule;
					serializationRule2serializationRuleAnalysis2.put(serializationRule, serializationRuleAnalysis);
				}
				addEClassValue(new EClassValue(eClass, serializationRules, eReferenceRuleIndexes));
			}
		}
		for (@NonNull SerializationRule serializationRule : serializationRule2serializationRuleAnalysis2.keySet()) {
			SerializationRuleAnalysis serializationRuleAnalysis = serializationRule2serializationRuleAnalysis2.get(serializationRule);
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
		AbstractRuleAnalysis ruleAnalysis = ClassUtil.requireNonNull(ruleAnalyses.get(ruleValueIndex));
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
	 * @param overriddenRule2overridingRule
	 */
	protected void createRuleAnalyses(@NonNull Map<@NonNull String, @NonNull AbstractRule> ruleName2rule,
			@NonNull Map<@NonNull AbstractRule, @NonNull AbstractRule> overriddenRule2overridingRule, @NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		List<@NonNull String> ruleNames = new ArrayList<>(ruleName2rule.keySet());
		Collections.sort(ruleNames);
		for (@NonNull String ruleName : ruleNames) {
			AbstractRule activeRule = ruleName2rule.get(ruleName);
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
		//	System.out.println("createRuleAnalyses " + NameUtil.debugSimpleName(activeRule) + " : " + activeRule.getName() + " => " + NameUtil.debugSimpleName(ruleAnalysis));
			ruleAnalyses.add(ruleAnalysis);
		}
		for (Map.@NonNull Entry<@NonNull AbstractRule, @NonNull AbstractRule> entry : overriddenRule2overridingRule.entrySet()) {
			@NonNull AbstractRule overriddenRule = entry.getKey();
			@NonNull AbstractRule overridingRule = entry.getValue();
			AbstractRuleAnalysis ruleAnalysis = rule2ruleAnalysis.get(overridingRule);
			assert ruleAnalysis != null;
			AbstractRuleAnalysis old = rule2ruleAnalysis.put(overriddenRule, ruleAnalysis);
		//	System.out.println("createRuleAnalyses " + NameUtil.debugSimpleName(overriddenRule) + " : " + overriddenRule.getName() + " => " + NameUtil.debugSimpleName(ruleAnalysis));
			assert old == null;
		}
	}

	public @NonNull ActionAssignmentAnalysis getAssignmentAnalysis(@NonNull Action action) {
		assert assignment2assignmentAnalysis != null;
		return (ActionAssignmentAnalysis) ClassUtil.requireNonNull(assignment2assignmentAnalysis.get(action));
	}
	public @NonNull List<@NonNull AssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return ClassUtil.requireNonNull(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull DirectAssignmentAnalysis getAssignmentAnalysis(@NonNull Assignment assignment) {
		assert assignment2assignmentAnalysis != null;
		return (DirectAssignmentAnalysis) ClassUtil.requireNonNull(assignment2assignmentAnalysis.get(assignment));
	}

	public @NonNull EClassValue getEClassValue(@NonNull EClass eClass) {
		assert eClass2eClassValue.size() > 0;
		return ClassUtil.requireNonNull(eClass2eClassValue.get(eClass));
	}

	public @NonNull EReference_TargetGrammarRuleVector @NonNull [] getEReferenceRuleIndexes(@NonNull EClass eClass) {
		return ClassUtil.requireNonNull(basicGetEReferenceRuleIndexes(eClass));
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
		EnumerationValueSingle enumerationValue = value2enumerationValue.get(value);
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
		EnumerationValueMultiple enumerationValue = values2enumerationValue.get(values);
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
			grammar = grammar2 = ClassUtil.requireNonNull(grammarProvider.getGrammar(this));
		}
		return grammar2;
	}

	public @NonNull IdiomsModel getIdiomsModel(@NonNull ResourceSet resourceSet, @NonNull URI uri) {
		IdiomsPackage.eINSTANCE.getClass();
		Resource resource = resourceSet.getResource(uri, true);
		EcoreUtil.resolveAll(resourceSet);				// Avoid no-equality of proxies
		@NonNull IdiomsModel castIdiomModel = (IdiomsModel)resource.getContents().get(0);
		return castIdiomModel;
	}

	public @NonNull Iterable<@NonNull Idiom> getIdioms() {
		List<@NonNull Idiom> idioms2 = idioms;
		if (idioms2 == null) {
			idioms = idioms2 = new ArrayList<>();
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
			StringBuilder s = null;
			for (int i = 0; i < idiomsModels.size(); i++) {
				IdiomsModel idiomsModel = ClassUtil.requireNonNull(idiomsModels.get(i));
				List<Resource.Diagnostic> errors = ClassUtil.requireNonNull(idiomsModel.eResource().getErrors());
				if (errors.size() > 0) {
					if (s == null) {
						s = new StringBuilder();
					}
					s.append(PivotUtil.formatResourceDiagnostics(errors, "", "\n"));
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
		}
		return idioms2;
	}

	public @NonNull SerializationSegment @NonNull [] @NonNull [] getInnerFormattingSegments(@NonNull ParserRuleValue parserRuleValue) {
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
		List<@NonNull EClass> list = new ArrayList<>(ClassUtil.requireNonNull(eClass2serializationRules.keySet()));
		Collections.sort(list, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		return list;
	} */

	public @NonNull SerializationSegment @NonNull [] @NonNull [] getOuterFormattingSegments(@NonNull ParserRuleValue parserRuleValue) {
		return parserRuleValue.getOuterFormattingSegments();
	}

	public @NonNull List<@NonNull AbstractNonTerminalRuleAnalysis> getProducingRuleAnalyses(@NonNull EClassifier eClassifier) {
		assert eClassifier2ruleAnalyses != null;
		return ClassUtil.requireNonNull(eClassifier2ruleAnalyses.get(eClassifier));
	}

	public @NonNull Iterable<@NonNull AbstractRuleAnalysis> getRuleAnalyses() {
		assert eClassifier2ruleAnalyses != null;
		return ClassUtil.requireNonNull(rule2ruleAnalysis.values());
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		assert rule2ruleAnalysis != null;
		AbstractRuleAnalysis aT = rule2ruleAnalysis.get(abstractRule);
		if (aT == null) {
			System.out.println("getRuleAnalysis " + NameUtil.debugSimpleName(abstractRule) + " : " + abstractRule.getName() + " => null");
		}
		return ClassUtil.requireNonNull(aT);
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(int ruleValueIndex) {
		return ClassUtil.requireNonNull(ruleAnalyses.get(ruleValueIndex));
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
		assert serializationRule2serializationRuleAnalysis != null;
		return ClassUtil.requireNonNull(serializationRule2serializationRuleAnalysis.get(serializationRule));
	}

	public @NonNull SerializationRule @NonNull [] getSerializationRules(@NonNull ParserRuleValue parserRuleValue) {
		return parserRuleValue.getSerializationRules();
	}

	public @NonNull SerializationSegment getSerializationSegment(@NonNull Segment segment) {
		SerializationSegment serializationSegment = segment2serializationSegment.get(segment);
		if (serializationSegment == null) {
			if (segment instanceof HalfNewLineSegment) {
				serializationSegment = SerializationSegment.HALF_NEW_LINE;
			}
			else if (segment instanceof NewLineSegment) {
				serializationSegment = SerializationSegment.NEW_LINE;
			}
			else if (segment instanceof NoNewLineSegment) {
				serializationSegment = SerializationSegment.NO_NEW_LINE;
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
			else if (segment instanceof StringSegment) {
				String string = ((StringSegment)segment).getString();
				serializationSegment = SerializationSegment.StringSerializationSegment.getString(string);
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

	public @NonNull SerializationSegment @NonNull [] getSerializationSegments(@Nullable List<@NonNull Segment> segmentsList) {
		if (segmentsList != null) {
			@NonNull SerializationSegment @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [segmentsList.size()];
			int i = 0;
			for (Segment segment : segmentsList) {
				serializationSegments[i++] = getSerializationSegment(segment);
			}
			return serializationSegments;
		}
		else {
			return SerializationSegment.VALUE_SEGMENTS_ARRAY;
		}
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
				Segment segment = ClassUtil.requireNonNull(mergedSegments.get(j));
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
			sortedProducedEClassValues = sortedProducedEClassValues2 = new ArrayList<>(ClassUtil.requireNonNull(eClass2eClassValue.values()));
			Collections.sort(sortedProducedEClassValues, NameUtil.NAMEABLE_COMPARATOR);
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
				Collections.sort(eFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
				boolean isFirstFeature = true;
				for (@NonNull EStructuralFeature eFeature : eFeatures) {
					if (isFirstFeature) {
						s.append(" ");
					}
					else {
						s.append(",");
					}
					List<@NonNull AssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eFeature);
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
		@NonNull String castString = s.toString();
		return castString;
	}
}
