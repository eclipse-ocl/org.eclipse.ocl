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

import java.io.IOException;
import java.util.ArrayList;
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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.AbstractIdiomsProvider;
import org.eclipse.ocl.examples.xtext.idioms.CustomSegment;
import org.eclipse.ocl.examples.xtext.idioms.HalfNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsProvider;
import org.eclipse.ocl.examples.xtext.idioms.NoSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.PopSegment;
import org.eclipse.ocl.examples.xtext.idioms.PushSegment;
import org.eclipse.ocl.examples.xtext.idioms.Segment;
import org.eclipse.ocl.examples.xtext.idioms.SoftNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.SoftSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.ValueSegment;
import org.eclipse.ocl.examples.xtext.idioms.util.IdiomsResourceFactoryImpl;
import org.eclipse.ocl.examples.xtext.idioms.util.IdiomsResourceImpl;
import org.eclipse.ocl.examples.xtext.serializer.AbstractGrammarAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationGrammarAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.GrammarProvider;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * An XtextGrammarAnalysis provides the extended analysis of an Xtext (multi-)grammar.
 */
public class GrammarAnalysis extends AbstractGrammarAnalysis
{
	@Inject
	private @NonNull GrammarProvider grammarProvider;

	@Inject
	private @Nullable IdiomsProvider idiomsProvider;

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
	 * The possible producing rule analyses for each EClass. ??his analysis excludes overrides.
	 */
	private @Nullable Map<@NonNull EClass, List<@NonNull ParserRuleAnalysis>> eClass2ruleAnalyses = null;

	/**
	 * The values of enumerated features
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = null;

	private  final @NonNull Map<@NonNull String, @NonNull EnumerationValueSingle> value2enumerationValue = new HashMap<>();
	private  final @NonNull Map<@NonNull List<@NonNull String>, @NonNull EnumerationValueMultiple> values2enumerationValue = new HashMap<>();

	private @Nullable SerializationGrammarAnalysis runtime = null;
	private @Nullable Iterable<@NonNull EClassValue> sortedProducedEClassValues = null;
	private @Nullable Map<@NonNull SerializationRule, @NonNull SerializationRuleAnalysis> serializationRule2aserializationRuleAnalysis = null;

	private @NonNull Map<@NonNull Segment, @NonNull SerializationSegment> segment2serializationSegment = new HashMap<>();

	public GrammarAnalysis() {
		this.grammar = null;
	}

	public GrammarAnalysis(@NonNull Grammar grammar) {
		this.grammar = grammar;
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
		Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls = new HashMap<>();
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = analyzeRuleNames(rule2ruleCalls);
		/*this.rule2ruleAnalysis =*/ createRuleAnalyses(ruleName2rules, rule2ruleCalls);
		List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = new ArrayList<>(ruleAnalyses.size());
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			if (abstractRuleAnalysis instanceof ParserRuleAnalysis) {
				parserRuleAnalyses.add((ParserRuleAnalysis)abstractRuleAnalysis);
			}
		}
		Collections.sort(parserRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
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
			if ("EssentialOCL::URIFirstPathElementCS".equals(parserRuleAnalysis.getName())) {
				getClass();
			}
			parserRuleAnalysis.getStaticRuleMatch();
		}
		this.eClass2ruleAnalyses = analyzeProductions(parserRuleAnalyses);
		analyzeSerializations(parserRuleAnalyses);
	}

	public void addContainment(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull EReference eReference) {
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = containment2assignmentAnalyses.get(eReference);
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			containment2assignmentAnalyses.put(eReference, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	/**
	 *	Identify the production rule(s) for each EClass.
	 */
	protected @NonNull Map<@NonNull EClass, @NonNull List<@NonNull ParserRuleAnalysis>> analyzeProductions(
			@NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses) {
		Map<@NonNull EClass, @NonNull List<@NonNull ParserRuleAnalysis>> eClass2parserRules = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			for (@NonNull SerializationRuleAnalysis serializationRule : ruleAnalysis.getSerializationRuleAnalyses()) {
				EClass eClass = serializationRule.getProducedEClass();
				List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = eClass2parserRules.get(eClass);
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
			@NonNull Grammar grammar = grammars.get(i);
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
					String ruleName = GrammarUtils.getName(abstractRule);
					List<@NonNull AbstractRule> rules = ruleName2rules.get(ruleName);
					if (rules == null) {
						rules = new ArrayList<>();
						ruleName2rules.put(ruleName, rules);
					}
					rules.add(abstractRule);
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					AbstractRule rule = GrammarUtils.getRule(ruleCall);
					List<@NonNull RuleCall> ruleCalls = rule2ruleCalls.get(rule);
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
			for (@NonNull SerializationRuleAnalysis serializationRule : ruleAnalysis.getSerializationRuleAnalyses()) {
				EClass eClass = serializationRule.getProducedEClass();
				List<@NonNull SerializationRuleAnalysis> serializationRules = eClass2serializationRuleList.get(eClass);
				if (serializationRules == null) {
					serializationRules = new ArrayList<>();
					eClass2serializationRuleList.put(eClass, serializationRules);
				}
				if (!serializationRules.contains(serializationRule)) {
					serializationRules.add(serializationRule);
				}
			}
		}
		for (Map.Entry<@NonNull EClass, @NonNull List<@NonNull SerializationRuleAnalysis>> entry : eClass2serializationRuleList.entrySet()) {
			EClass eClass = entry.getKey();
			List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = entry.getValue();
			Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> eContainmentFeature2assignedTargetRuleValues = getEContainmentFeature2assignedTargetRuleValues(serializationRuleAnalyses);
			@NonNull EReference_RuleIndexes[] eReferenceRuleIndexes = null;
			if (eContainmentFeature2assignedTargetRuleValues != null) {
				eReferenceRuleIndexes = new @NonNull EReference_RuleIndexes[eContainmentFeature2assignedTargetRuleValues.size()];
				int i2 = 0;
				for (Map.Entry<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> entry2 : eContainmentFeature2assignedTargetRuleValues.entrySet()) {
					Set<@NonNull GrammarRuleValue> values = entry2.getValue();
					ParserRuleValue[] parserRuleValues = new ParserRuleValue[values.size()];
					int i3 = 0;
					for (GrammarRuleValue value : values) {
						parserRuleValues[i3++] = (ParserRuleValue) value;
					}
					eReferenceRuleIndexes[i2++] = new EReference_RuleIndexes(entry2.getKey(), new GrammarRuleVector(parserRuleValues));
				}
			}
			@NonNull SerializationRule_SegmentsList [] serializationRuleSegmentsLists = new @NonNull SerializationRule_SegmentsList [serializationRuleAnalyses.size()];
			int i = 0;
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
				SerializationRule serializationRule = serializationRuleAnalysis.getRuntime();
				serializationRuleSegmentsLists[i++] = new SerializationRule_SegmentsList(serializationRule, serializationRule.getStaticSegments());
				serializationRule2aserializationRuleAnalysis2.put(serializationRule, serializationRuleAnalysis);
			}
			addEClassValue(new EClassValue(eClass, serializationRuleSegmentsLists, eReferenceRuleIndexes));
		}
		for (@NonNull SerializationRule serializationRule : serializationRule2aserializationRuleAnalysis2.keySet()) {
			SerializationRuleAnalysis serializationRuleAnalysis = serializationRule2aserializationRuleAnalysis2.get(serializationRule);
			assert serializationRuleAnalysis != null;
			serializationRule = serializationRuleAnalysis.getRuntime();
		}
	}

	public void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		AssignmentAnalysis old = assignment2assignmentAnalysis.put(assignmentAnalysis.getActionOrAssignment(), assignmentAnalysis);
		assert old == null;
	}

	public @Nullable GrammarRuleValue basicGetRuleValue(int ruleValueIndex) {
		AbstractRuleAnalysis ruleAnalysis = ruleAnalyses.get(ruleValueIndex);
		return ruleAnalysis.basicGetRuleValue();
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
			int ruleIndex = ruleAnalyses.size();
			AbstractRuleAnalysis ruleAnalysis;
			if (activeRule instanceof ParserRule) {
				EClassifier eClassifier = GrammarUtils.getClassifier(activeRule.getType());
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
		return (ActionAssignmentAnalysis) ClassUtil.nonNullState(assignment2assignmentAnalysis.get(action));
	}
	public @NonNull List<@NonNull AssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

//	public @Nullable Map<@NonNull EReference, @NonNull Set<@NonNull GrammarRuleValue>> basicGetEContainmentFeature2assignedTargetRuleValues(@NonNull EClass eClass) {
//		EClassValue eClassValue = getEClassValue(eClass);
//		return eClassValue.basicGetEContainmentFeature2assignedTargetRuleValues();
//	}

	public @NonNull EReference_RuleIndexes @Nullable [] basicGetEReferenceRuleIndexes(@NonNull EClass eClass) {
		EClassValue eClassValue = getEClassValue(eClass);
		return eClassValue.basicGetEReferenceRuleIndexes();
	}

	public @NonNull IdiomMatch createIdiomMatch(@NonNull Idiom idiom, @NonNull SerializationNode serializationNode) {
		return new IdiomMatch(idiom, serializationNode);
	}

	public @NonNull DirectAssignmentAnalysis getAssignmentAnalysis(@NonNull Assignment assignment) {
		assert assignment2assignmentAnalysis != null;
		return (DirectAssignmentAnalysis) ClassUtil.nonNullState(assignment2assignmentAnalysis.get(assignment));
	}

	public @NonNull EReference_RuleIndexes @NonNull [] getEReferenceRuleIndexes(@NonNull EClass eClass) {
		return ClassUtil.nonNullState(basicGetEReferenceRuleIndexes(eClass));
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
						for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
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
		String value = GrammarUtils.getValue(keyword);
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
			values.add(GrammarUtils.getValue(keyword));
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
			grammar = grammar2 = ClassUtil.nonNullState(grammarProvider.getGrammar(this));
		}
		return grammar2;
	}

	public @NonNull Iterable<@NonNull Idiom> getIdioms() {
		ResourceSet resourceSet = getGrammar().eResource().getResourceSet();
		if (resourceSet == null) {
			if (resourceSetProvider != null) {
				resourceSet = resourceSetProvider.get();
			}
			else {
				resourceSet = new ResourceSetImpl();
			}
		}
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("idioms", new IdiomsResourceFactoryImpl());
		IdiomsProvider idiomsProvider2 = idiomsProvider;
		if (idiomsProvider2 == null) {

			idiomsProvider = idiomsProvider2 = new AbstractIdiomsProvider()
			{
				private @Nullable Iterable<@NonNull Idiom> idioms = null;

				@Override
				public @NonNull Iterable<@NonNull Idiom> getIdioms(@NonNull ResourceSet resourceSet) {
					Iterable<@NonNull Idiom> idioms2 = idioms;
					if (idioms2 == null) {
						URI xtextURI = getGrammar().eResource().getURI();
						URI idiomsURI = xtextURI.trimFileExtension().appendFileExtension("idioms");
						IdiomModel idiomModel = getIdiomModel(resourceSet, idiomsURI);
						idioms = idioms2 = getIdioms(idiomModel);
					}
					return idioms2;
				}
			};
		}
		Iterable<@NonNull Idiom> idioms = idiomsProvider2.getIdioms(resourceSet);


		StringBuilder s = null;
		for (Resource resource : resourceSet.getResources()) {
			if (resource instanceof IdiomsResourceImpl) {
				List<Resource.Diagnostic> errors = ClassUtil.nonNullState(resource.getErrors());
				if (errors.size() > 0) {
					if (s == null) {
						s = new StringBuilder();
					}
					s.append(PivotUtil.formatResourceDiagnostics(errors, "", "\n"));
				}
			}
		}
		if (s != null) {
			throw new IllegalStateException("Failed to load idioms" + s.toString());
		}


		return idioms != null ? idioms : Collections.emptyList();
	}

	public SerializationRuleAnalysis.@NonNull LocatorSwitch getLocatorSwitch() {
		return SerializationRuleAnalysis.LocatorSwitch.INSTANCE;
	}

/*	public @NonNull Iterable<@NonNull EClass> getSortedProducedEClasses() {
		assert eClass2serializationRules != null;
		List<@NonNull EClass> list = new ArrayList<>(ClassUtil.nonNullState(eClass2serializationRules.keySet()));
		Collections.sort(list, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		return list;
	} */

	public @NonNull Iterable<@NonNull AbstractRuleAnalysis> getRuleAnalyses() {
		assert eClass2ruleAnalyses != null;
		return ClassUtil.nonNullState(rule2ruleAnalysis.values());
	}

	public @NonNull List<@NonNull ParserRuleAnalysis> getProducingRuleAnalyses(@NonNull EClass eClass) {
		assert eClass2ruleAnalyses != null;
		return ClassUtil.nonNullState(eClass2ruleAnalyses.get(eClass));
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		assert rule2ruleAnalysis != null;
		return ClassUtil.nonNullState(rule2ruleAnalysis.get(abstractRule));
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(int ruleValueIndex) {
		return ruleAnalyses.get(ruleValueIndex);
	}

	@Override
	public @NonNull String getRuleName(int ruleValueIndex) {
		return getRuleAnalysis(ruleValueIndex).getRuleName();
	}

	@Override
	public @NonNull GrammarRuleValue getRuleValue(int ruleValueIndex) {
		return getRuleAnalysis(ruleValueIndex).getRuleValue();
	}

	@Deprecated
	public @NonNull SerializationGrammarAnalysis getRuntime() {
		SerializationGrammarAnalysis runtime2 = runtime;
		if (runtime2 == null)  {
			Iterable<? extends @NonNull EClassValue> sortedProducedEClassValues = getSortedProducedEClassValues();
			@NonNull EClassValue @NonNull [] eClassValues = Iterables.toArray(sortedProducedEClassValues, EClassValue.class);
			@NonNull GrammarRuleValue @NonNull [] ruleValues = new @NonNull GrammarRuleValue [ruleAnalyses.size()];
			for (int i = 0; i < ruleAnalyses.size(); i++) {
				ruleValues[i] = ruleAnalyses.get(i).getRuleValue();
			}
			runtime = runtime2 = new SerializationGrammarAnalysis(eClassValues, ruleValues);
		}
		return runtime2;
	}

	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis(@NonNull ParserRuleAnalysis parserRuleAnalysis, @NonNull SerializationNode thatSerializationNode) {
	/*	int hashCode = thatSerializationNode.semanticHashCode();			// Do we really need this ??
		Object analysisOrAnalyses = semanticHash2serializationAnalysisOrAnalyses.get(hashCode);
		SerializationRuleAnalysis oldSerializationRuleAnalysis = null;
		if (analysisOrAnalyses instanceof SerializationRuleAnalysis) {
			if (((SerializationRuleAnalysis)analysisOrAnalyses).getRootSerializationNode().semanticEquals(thatSerializationNode)) {
				throw new UnsupportedOperationException();
			//	oldSerializationRuleAnalysis = (SerializationRuleAnalysis)analysisOrAnalyses;
			}
		}
		else if (analysisOrAnalyses != null) {
			@SuppressWarnings("unchecked")
			List<@NonNull SerializationRuleAnalysis> ruleAnalyses = (List<@NonNull SerializationRuleAnalysis>)analysisOrAnalyses;
			for (@NonNull SerializationRuleAnalysis ruleAnalysis : ruleAnalyses) {
				if (ruleAnalysis.getRootSerializationNode().semanticEquals(thatSerializationNode)) {
					throw new UnsupportedOperationException();
				//	oldSerializationRuleAnalysis = ruleAnalysis;
				}
			}
		}
		if (oldSerializationRuleAnalysis != null) {
			return oldSerializationRuleAnalysis;
		} */
		SerializationRuleAnalysis serializationRuleAnalysis = new SerializationRuleAnalysis(parserRuleAnalysis, thatSerializationNode);
	/*	if (analysisOrAnalyses == null) {
			semanticHash2serializationAnalysisOrAnalyses.put(hashCode, serializationRuleAnalysis);
		}
		else if (analysisOrAnalyses instanceof SerializationNode) {
			List<@NonNull SerializationRuleAnalysis> ruleAnalyses = new ArrayList<>();
			ruleAnalyses.add((SerializationRuleAnalysis)analysisOrAnalyses);
			ruleAnalyses.add(serializationRuleAnalysis);
			semanticHash2serializationAnalysisOrAnalyses.put(hashCode, ruleAnalyses);
		}
		else {
			@SuppressWarnings("unchecked")
			List<@NonNull SerializationRuleAnalysis> ruleAnalyses = (List<@NonNull SerializationRuleAnalysis>)analysisOrAnalyses;
			ruleAnalyses.add(serializationRuleAnalysis);
		} */
		return serializationRuleAnalysis;
	}

	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis(@NonNull SerializationRule serializationRule) {
		assert serializationRule2aserializationRuleAnalysis != null;
		return ClassUtil.nonNullState(serializationRule2aserializationRuleAnalysis.get(serializationRule));
	}

	public @NonNull SerializationRule @NonNull [] getSerializationRules(@NonNull ParserRuleValue ruleValue) {
		return ruleValue.getSerializationRules();
	}

	public @NonNull SerializationSegment getSerializationSegment(@NonNull Segment segment) {
		SerializationSegment serializationSegment = segment2serializationSegment.get(segment);
		if (serializationSegment == null) {
			if (segment instanceof HalfNewLineSegment) {
				serializationSegment = SerializationSegment.HALF_NEW_LINE;
			}
			else if (segment instanceof NoSpaceSegment) {
				serializationSegment = SerializationSegment.NO_SPACE;
			}
			else if (segment instanceof PopSegment) {
				serializationSegment = SerializationSegment.POP;
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
				serializationSegment = new CustomSerializationSegment(((CustomSegment) segment).getSupportClassName());
			}
			else {
				throw new UnsupportedOperationException();
			}
			segment2serializationSegment.put(segment, serializationSegment);
		}
		return serializationSegment;
	}

	@Override
	public @NonNull Iterable<@NonNull EClassValue> getSortedProducedEClassValues() {
		Iterable<@NonNull EClassValue> sortedProducedEClassValues2 = sortedProducedEClassValues;
		if (sortedProducedEClassValues2 == null) {
			sortedProducedEClassValues = sortedProducedEClassValues2 = super.getSortedProducedEClassValues();
		}
		return sortedProducedEClassValues2;
	}

	private @NonNull Map<@NonNull Integer, @NonNull Object> semanticHash2serializationAnalysisOrAnalyses = new HashMap<>();

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Xtext production rule -> Xtext base rules <=> User EClass - User EStructuralFeatures");
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			s.append("\n  ");
			s.append(abstractRuleAnalysis);
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
						s.append(size);
						s.append("*");
					}
					s.append(eFeature.getName());
					isFirstFeature = false;
				}
				//
				for (@NonNull SerializationRuleAnalysis serializationRule : parserRuleAnalysis.getSerializationRuleAnalyses()) {
					StringUtil.appendIndentation(s, 2);
					s.append("|& ");
					serializationRule.toString(s, -1);
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
