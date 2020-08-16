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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.cs2text.AbstractIdiomsProvider;
import org.eclipse.ocl.xtext.base.cs2text.IdiomsProvider;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;
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

import com.google.inject.Inject;

/**
 * An XtextGrammarAnalysis provides the extended analysis of an Xtext (multi-)grammar.
 */
public class GrammarAnalysis extends RTGrammarAnalysis
{
	@Inject
	private @NonNull GrammarProvider grammarProvider;

	@Inject
	private @Nullable IdiomsProvider idiomsProvider;

	private @Nullable Grammar grammar = null;

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

	private  final @NonNull Map<@NonNull String, @NonNull SingleEnumerationValue> value2enumerationValue = new HashMap<>();
	private  final @NonNull Map<@NonNull List<@NonNull String>, @NonNull MultipleEnumerationValue> values2enumerationValue = new HashMap<>();

//	private @Nullable RTGrammarAnalysis runtime = null;

	public GrammarAnalysis() {}

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
	 * Perform the analysis to determine and populate thae Assignment and Rule analyses.
	 */
	public void analyze() {
		Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls = new HashMap<>();
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = analyzeRuleNames(rule2ruleCalls);
		/*this.rule2ruleAnalysis =*/ createRuleAnalyses(ruleName2rules, rule2ruleCalls);
		List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = new ArrayList<>(rule2ruleAnalysis.size());
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
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
			parserRuleAnalysis.analyze();
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
//			@NonNull Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis) {
/*		Map<@NonNull EClassifier, @NonNull List<@NonNull AbstractRuleAnalysis>> eClassifier2ruleAnalyses = new HashMap<>();
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			for (@NonNull EClassifier eClassifier : abstractRuleAnalysis.getEClassifiers()) {
				List<@NonNull AbstractRuleAnalysis> ruleAnalyses = eClassifier2ruleAnalyses.get(eClassifier);
				if (ruleAnalyses == null) {
					ruleAnalyses = new ArrayList<>();
					eClassifier2ruleAnalyses.put(eClassifier, ruleAnalyses);
				}
				ruleAnalyses.add(abstractRuleAnalysis);
			}
		}
		return eClassifier2ruleAnalyses; */
		Map<@NonNull EClass, @NonNull List<@NonNull ParserRuleAnalysis>> eClass2parserRules = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			if ("Base::MultiplicityBoundsCS".equals(ruleAnalysis.getName())) {		// XXX debugging
				getClass();
			}
			for (@NonNull SerializationRule serializationRule : ruleAnalysis.getSerializationRules()) {
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
					String ruleName = XtextGrammarUtil.getName(abstractRule);
					List<@NonNull AbstractRule> rules = ruleName2rules.get(ruleName);
					if (rules == null) {
						rules = new ArrayList<>();
						ruleName2rules.put(ruleName, rules);
					}
					rules.add(abstractRule);
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					AbstractRule rule = XtextGrammarUtil.getRule(ruleCall);
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
		Map<@NonNull EClass, @NonNull List<@NonNull SerializationRule>> eClass2serializationRuleList = new HashMap<>();
		for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
			if ("EssentialOCL::SelfExpCS".equals(ruleAnalysis.getName())) {
				getClass(); // XXX debugging
			}
			for (@NonNull SerializationRule serializationRule : ruleAnalysis.getSerializationRules()) {
				EClass eClass = serializationRule.getProducedEClass();
				List<@NonNull SerializationRule> serializationRules = eClass2serializationRuleList.get(eClass);
				if (serializationRules == null) {
					serializationRules = new ArrayList<>();
					eClass2serializationRuleList.put(eClass, serializationRules);
				}
				serializationRules.add(serializationRule);
			}
		}
		Map<@NonNull EClass, @NonNull SerializationRules> eClass2serializationRules = new HashMap<>();
		for (Map.Entry<@NonNull EClass, @NonNull List<@NonNull SerializationRule>> entry : eClass2serializationRuleList.entrySet()) {
			addSerializationRules(new SerializationRules(entry.getKey(), entry.getValue()));
		}
	}

	public void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		AssignmentAnalysis old = assignment2assignmentAnalysis.put(assignmentAnalysis.getActionOrAssignment(), assignmentAnalysis);
		assert old == null;
	}

	/**
	 *	Create a RuleAnalysis for each distinct name.
	 */
	protected void createRuleAnalyses(
			@NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules,
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		List<@NonNull String> ruleNames = new ArrayList<>(ruleName2rules.keySet());
		Collections.sort(ruleNames);
		int ruleIndex = 0;
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
			AbstractRuleAnalysis ruleAnalysis;
			if (activeRule instanceof ParserRule) {
				EClassifier eClassifier = XtextGrammarUtil.getClassifier(activeRule.getType());
				if (eClassifier instanceof EClass) {
					ruleAnalysis = new ParserRuleAnalysis(this, ruleIndex++, (ParserRule)activeRule, (EClass)eClassifier);
				}
				else {
					ruleAnalysis = new DataTypeRuleAnalysis(this, ruleIndex++, (ParserRule)activeRule, (EDataType)eClassifier);
				}
			}
			else if (activeRule instanceof TerminalRule) {
				ruleAnalysis = new TerminalRuleAnalysis(this, ruleIndex++, (TerminalRule)activeRule);
			}
			else {
				throw new UnsupportedOperationException();
			}
			rule2ruleAnalysis.put(activeRule, ruleAnalysis);
		}
	}

	public @NonNull ActionAssignmentAnalysis getAssignmentAnalysis(@NonNull Action action) {
		assert assignment2assignmentAnalysis != null;
		return (ActionAssignmentAnalysis) ClassUtil.nonNullState(assignment2assignmentAnalysis.get(action));
	}
	public @NonNull DirectAssignmentAnalysis getAssignmentAnalysis(@NonNull Assignment assignment) {
		assert assignment2assignmentAnalysis != null;
		return (DirectAssignmentAnalysis) ClassUtil.nonNullState(assignment2assignmentAnalysis.get(assignment));
	}

	public @NonNull List<@NonNull AssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull EnumerationValue getEnumerationValue(@NonNull Keyword keyword) {
		String value = XtextGrammarUtil.getValue(keyword);
		SingleEnumerationValue enumerationValue = value2enumerationValue.get(value);
		if (enumerationValue == null) {
			enumerationValue = new SingleEnumerationValue(value);
			value2enumerationValue.put(value, enumerationValue);
		}
		return enumerationValue;
	}

	public @NonNull EnumerationValue getEnumerationValue(@NonNull Iterable<@NonNull Keyword> keywords) {
		List<@NonNull String> values = new ArrayList<>();
		for (@NonNull Keyword keyword : keywords) {
			values.add(XtextGrammarUtil.getValue(keyword));
		}
		Collections.sort(values);
		MultipleEnumerationValue enumerationValue = values2enumerationValue.get(values);
		if (enumerationValue == null) {
			enumerationValue = new MultipleEnumerationValue(values);
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
		IdiomsProvider idiomsProvider2 = idiomsProvider;
		if (idiomsProvider2 == null) {

			idiomsProvider = idiomsProvider2 = new AbstractIdiomsProvider()
			{
				private Iterable<Idiom> idioms = null;

				@Override
				public Iterable<Idiom> getIdioms() {
					if (idioms == null) {
						URI xtextURI = getGrammar().eResource().getURI();
						URI idiomsURI = xtextURI.trimFileExtension().appendFileExtension("idioms");
						IdiomModel idiomModel = getIdiomModel(idiomsURI);
						idioms = getIdioms(idiomModel);
					}
					return idioms;
				}
			};
		}
		return idiomsProvider2.getIdioms() != null ? idiomsProvider2.getIdioms() : Collections.emptyList();
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

	@Deprecated
	public @NonNull RTGrammarAnalysis getRuntime() {
//		RTGrammarAnalysis runtime2 = runtime;
//		if (runtime2 == null)  {
//			runtime = runtime2 = new RTGrammarAnalysis(this);
//		}
//		return runtime2;
		return this;
	}

	@Override
	public @NonNull SerializationRules getSerializationRules(@NonNull EClass eClass) {
		if ("PathElementWithURICS".equals(eClass.getName())) {
			getClass(); // XXX
		}
	//	assert eClass2serializationRules != null;
		return super.getSerializationRules(eClass);
	}

	public @NonNull RTSerializationRule @NonNull [] getSerializationRules(@NonNull ParserRuleValue ruleValue) {
		return ruleValue.getSerializationRules();
	}

	@Override
	public @NonNull String toString() {
		Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis = this.rule2ruleAnalysis;
	//	if (rule2ruleAnalysis == null) {
	//		return "«not-ready»";
	//	}
		StringBuilder s = new StringBuilder();
		s.append("Xtext production rule -> Xtext base rules <=> User EClass - User EStructuralFeatures");
		List<@NonNull AbstractRuleAnalysis> abstractRuleAnalyses = new ArrayList<>(rule2ruleAnalysis.values());
		Collections.sort(abstractRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : abstractRuleAnalyses) {
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
				if ("Base::TypeRefCS".equals(parserRuleAnalysis.getName())) {
					getClass();		// XXX
				}
				for (@NonNull SerializationRule serializationRule : parserRuleAnalysis.getSerializationRules()) {
		//		SerializationNode rootSerializationNode = parserRuleAnalysis.getR();
		//		if (rootSerializationNode != null) {
				//	s.append("\n");
				//	StringUtil.appendIndentation(s, rootSerializationNode instanceof CompositeSerializationNode ? 1 : 2, "\t");
				//	rootSerializationNode.toString(s, 2);
				//	serializationRule.getPreSerializer();		// XXX redundant/lazy
							StringUtil.appendIndentation(s, 2);
							s.append("|& ");
							serializationRule.toString(s, -1);
						}
					/*	s.append("\n");
						StringUtil.appendIndentation(s, 2, "\t");
						s.append("|& ");
						requiredSlots.toString(s, 2); * /
					} */
		//		}
			}
		}
/*		s.append("\n\nUser EClass <=> Active Xtext production rule(s)");
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
		} */
		s.append("\n\nUser EClass <=> Prioritized serialization rule(s)");
		s.append(super.toString());
		return s.toString();
	}
}
