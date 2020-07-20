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
import org.eclipse.ocl.xtext.base.cs2text.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue;
import org.eclipse.ocl.xtext.base.utilities.AbstractGrammarResource;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

/**
 * An XtextGrammarAnalysis provides the extended analysis of an Xtext (multi-)grammar.
 */
public class GrammarAnalysis
{
	/**
	 * The (multi-)grammar model.
	 */
	protected final @NonNull AbstractGrammarResource grammarResource;

	/**
	 * The rule analysis for each rule.
	 */
	private @Nullable Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis = null;

	/**
	 * The assignment analysis for each assignment or action.
	 */
	private @Nullable Map<@NonNull AbstractElement, @NonNull AssignmentAnalysis> assignment2assignmentAnalysis = null;

	/**
	 * The possible assignment analyses for containment EReference.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull AssignmentAnalysis>> containment2assignmentAnalyses = null;

	/**
	 * The prioritized serialization rules for each EClass.
	 */
	private @Nullable Map<@NonNull EClass, @NonNull List<@NonNull SerializationRule>> eClass2serializationRules = null;

	/**
	 * The values of enumerated features
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = null;

	private  final @NonNull Map<@NonNull String, @NonNull SingleEnumerationValue> value2enumerationValue = new HashMap<>();
	private  final @NonNull Map<@NonNull List<@NonNull String>, @NonNull MultipleEnumerationValue> values2enumerationValue = new HashMap<>();

	protected final @NonNull ICrossReferenceSerializer crossReferenceSerializer;
	protected final @NonNull IValueConverterService valueConverterService;
	protected final @NonNull LinkingHelper linkingHelper;

	public GrammarAnalysis(@NonNull AbstractGrammarResource grammarResource, @NonNull ICrossReferenceSerializer crossReferenceSerializer, IValueConverterService valueConverterService, LinkingHelper linkingHelper) {
		this.grammarResource = grammarResource;
		assert crossReferenceSerializer != null;
		this.crossReferenceSerializer = crossReferenceSerializer;
		assert valueConverterService != null;
		this.valueConverterService = valueConverterService;
		assert linkingHelper != null;
		this.linkingHelper = linkingHelper;
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
		Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis = createRuleAnalyses(ruleName2rules, rule2ruleCalls);
		this.rule2ruleAnalysis = rule2ruleAnalysis;
		this.assignment2assignmentAnalysis = analyzeAssignments(rule2ruleAnalysis);
		this.containment2assignmentAnalyses = analyzeContainments(assignment2assignmentAnalysis);
		Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses = rule2ruleAnalysis.values();
		List<@NonNull ParserRuleAnalysis> parserRuleAnalyses = new ArrayList<>(rule2ruleAnalysis.size());
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			if (abstractRuleAnalysis instanceof ParserRuleAnalysis) {
				parserRuleAnalyses.add((ParserRuleAnalysis)abstractRuleAnalysis);
			}
		}
		Collections.sort(parserRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		//
		// Perform the intra rule analysis to determine the locally produced EClassifiers and local base rules.
		//
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			parserRuleAnalysis.analyze();
		}
		//
		// Perform the inter rule analysis to determine the base rule closure.
		for (@NonNull ParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses) {
			if ("EssentialOCL::URIFirstPathElementCS".equals(parserRuleAnalysis.getName())) {
				getClass();
			}
			parserRuleAnalysis.preSerialize();
		}
		this.eClass2serializationRules = analyzeSerializations(parserRuleAnalyses);
	}

	/**
	 *	Create an assignment analysis for each assignment..
	 */
	protected @NonNull Map<@NonNull AbstractElement, @NonNull AssignmentAnalysis> analyzeAssignments(
			@NonNull Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis) {
		/**
		 * The assignment analysis for each assignment.
		 */
		Map<@NonNull AbstractElement, @NonNull AssignmentAnalysis> assignment2assignmentAnalysis = new HashMap<>();
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			AbstractRule abstractRule = abstractRuleAnalysis.getRule();
			for (@NonNull EObject eObject : new TreeIterable(abstractRule, false)) {
				if (eObject instanceof Assignment) {
					Assignment assignment = (Assignment)eObject;
					ParserRuleAnalysis parserRuleAnalysis = (ParserRuleAnalysis)getRuleAnalysis(assignment);
					AssignmentAnalysis assignmentAnalysis = new AssignmentAnalysis(parserRuleAnalysis, assignment);
					assignment2assignmentAnalysis.put(assignment, assignmentAnalysis);
					parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
				}
				else if (eObject instanceof Action) {
					Action action = (Action)eObject;
					String feature = action.getFeature();
					if (feature != null) {
						ParserRuleAnalysis parserRuleAnalysis = (ParserRuleAnalysis)getRuleAnalysis(action);
						AssignmentAnalysis assignmentAnalysis = new AssignmentAnalysis(parserRuleAnalysis, action);
						assignment2assignmentAnalysis.put(action, assignmentAnalysis);
						parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
					}
				}
			}
		}
		return assignment2assignmentAnalysis;
	}

	/**
	 *	Identify the assignment analyses that are containments.
	 */
	protected @NonNull Map<@NonNull EReference, @NonNull List<@NonNull AssignmentAnalysis>> analyzeContainments(
			@NonNull Map<@NonNull AbstractElement, @NonNull AssignmentAnalysis> assignment2assignmentAnalysis) {
		Map<@NonNull EReference, @NonNull List<@NonNull AssignmentAnalysis>> containment2assignmentAnalyses = new HashMap<>();
		for (@NonNull AssignmentAnalysis assignmentAnalysis : assignment2assignmentAnalysis.values()) {
			EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
			if (eFeature instanceof EReference) {
				EReference eReference = (EReference)eFeature;
				if (eReference.isContainment()) {
					List<@NonNull AssignmentAnalysis> assignmentAnalyses = containment2assignmentAnalyses.get(eReference);
					if (assignmentAnalyses == null) {
						assignmentAnalyses = new ArrayList<>();
						containment2assignmentAnalyses.put(eReference, assignmentAnalyses);
					}
					assignmentAnalyses.add(assignmentAnalysis);
				}
			}
		}
		return containment2assignmentAnalyses;
	}

	/**
	 *	Return the rules for each rule name and populate the rule2ruleCalls from each rule.
	 */
	protected @NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> analyzeRuleNames(
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = new HashMap<>();
		for (@NonNull EObject eObject : new TreeIterable(grammarResource)) {
			if (eObject instanceof AbstractRule) {
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

	protected @NonNull Map<@NonNull EClass, @NonNull List<@NonNull SerializationRule>> analyzeSerializations(
			@NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses) {
		Map<@NonNull EClass, @NonNull List<@NonNull SerializationRule>> eClass2serializationRules = new HashMap<>();
		for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
			if (ruleAnalysis instanceof ParserRuleAnalysis) {
				for (@NonNull SerializationRule serializationRule : ((ParserRuleAnalysis)ruleAnalysis).getSerializationRules()) {
					EClass eClass = serializationRule.getProducedEClass();
					List<@NonNull SerializationRule> serializationRules = eClass2serializationRules.get(eClass);
					if (serializationRules == null) {
						serializationRules = new ArrayList<>();
						eClass2serializationRules.put(eClass, serializationRules);
					}
					serializationRules.add(serializationRule);
				}
			}
		}
		return eClass2serializationRules;
	}

	/**
	 *	Create a RuleAnalysis for each distinct name.
	 */
	protected @NonNull Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> createRuleAnalyses(
			@NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules,
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis = new HashMap<>();
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
			AbstractRuleAnalysis ruleAnalysis;
			if (activeRule instanceof ParserRule) {
				EClassifier eClassifier = XtextGrammarUtil.getClassifier(activeRule.getType());
				if (eClassifier instanceof EClass) {
					ruleAnalysis = new ParserRuleAnalysis(this, (ParserRule)activeRule, (EClass)eClassifier);
				}
				else {
					ruleAnalysis = new DataTypeRuleAnalysis(this, (ParserRule)activeRule, (EDataType)eClassifier);
				}
			}
			else if (activeRule instanceof TerminalRule) {
				ruleAnalysis = new TerminalRuleAnalysis(this, (TerminalRule)activeRule);
			}
			else {
				throw new UnsupportedOperationException();
			}
			rule2ruleAnalysis.put(activeRule, ruleAnalysis);
		}
		return rule2ruleAnalysis;
	}

	public @NonNull AssignmentAnalysis getAssignmentAnalysis(@NonNull Action action) {
		assert assignment2assignmentAnalysis != null;
		return ClassUtil.nonNullState(assignment2assignmentAnalysis.get(action));
	}
	public @NonNull AssignmentAnalysis getAssignmentAnalysis(@NonNull Assignment assignment) {
		assert assignment2assignmentAnalysis != null;
		return ClassUtil.nonNullState(assignment2assignmentAnalysis.get(assignment));
	}

	public @NonNull List<@NonNull AssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull ICrossReferenceSerializer getCrossReferenceSerializer() {
		return crossReferenceSerializer;
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

//	public @Nullable Iterable<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
//		return (eAttribute2enumerationValues != null) ? eAttribute2enumerationValues.get(eAttribute) : null;
//	}

	public @NonNull LinkingHelper getLinkingHelper() {
		return linkingHelper;
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractElement abstractElement) {
		for (EObject eObject = abstractElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof AbstractRule) {
				return getRuleAnalysis((AbstractRule)eObject);
			}
		}
		throw new IllegalStateException();
	}

	public @NonNull AbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		assert rule2ruleAnalysis != null;
		return ClassUtil.nonNullState(rule2ruleAnalysis.get(abstractRule));
	}

	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules(@NonNull EClass eClass) {
		if ("PathElementWithURICS".equals(eClass.getName())) {
			getClass(); // XXX
		}
		assert eClass2serializationRules != null;
		return ClassUtil.nonNullState(eClass2serializationRules.get(eClass));
	}

	public @NonNull IValueConverterService getValueConverterService() {
		return valueConverterService;
	}

	@Override
	public @NonNull String toString() {
		Map<@NonNull AbstractRule, @NonNull AbstractRuleAnalysis> rule2ruleAnalysis = this.rule2ruleAnalysis;
		if (rule2ruleAnalysis == null) {
			return "«not-ready»";
		}
		StringBuilder s = new StringBuilder();
		s.append("Xtext production rule -> Xtext base rules <=> User EClass - User EStructuralFeatures");
		List<@NonNull AbstractRuleAnalysis> abstractRuleAnalyses = new ArrayList<>(rule2ruleAnalysis.values());
		Collections.sort(abstractRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull AbstractRuleAnalysis abstractRuleAnalysis : abstractRuleAnalyses) {
			s.append("\n\t");
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
							StringUtil.appendIndentation(s, 1, "\t");
							s.append("|& ");
							s.append(serializationRule.getProducedEClass().getName());
							s.append(" ");
							serializationRule.toString(s, 2);
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
		Map<@NonNull EClass, @NonNull List<@NonNull SerializationRule>> eClass2serializationRules2 = eClass2serializationRules;
		assert eClass2serializationRules2 != null;
		List<@NonNull EClass> eClasses = new ArrayList<>(eClass2serializationRules2.keySet());
		Collections.sort(eClasses, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EClass eClass : eClasses) {
			Iterable<@NonNull SerializationRule> serializationRules = eClass2serializationRules2.get(eClass);
			assert serializationRules != null;
			s.append("\n  ");;
			s.append(eClass.getName());
			if ("PackageCS".equals(eClass.getName())) {
				getClass(); // XXX debugging
			}
			s.append(" <=>");;
			for (@NonNull SerializationRule serializationRule : serializationRules) {
				s.append(" ");;
			//	serializationRule.preSerialize(parserRuleAnalysis, rootSerializationNode);
				StringUtil.appendIndentation(s, 1, "\t");
				s.append("|&\t");
				serializationRule.toString(s, 2);
			}
		}
		return s.toString();
	}
}
