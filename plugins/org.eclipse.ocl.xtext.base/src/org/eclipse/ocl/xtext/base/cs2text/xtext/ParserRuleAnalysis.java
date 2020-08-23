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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationElement;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleComparator;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;

import com.google.common.collect.Iterables;

/**
 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
 */
public class ParserRuleAnalysis extends AbstractRuleAnalysis implements Indexed
{
	protected final @NonNull EClass eClass;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();
	private @Nullable List<@NonNull BasicSerializationRule> serializationRules = null;

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
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatingRuleAnalyses = null;

	private @Nullable ParserRuleValue parserRuleValue = null;

	public ParserRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull ParserRule parserRule, @NonNull EClass eClass) {
		super(grammarAnalysis, index, parserRule);
		this.eClass = eClass;
	}

	protected void addAssignmentAnalysis(@NonNull AssignmentAnalysis assignmentAnalysis) {
		grammarAnalysis.addAssignmentAnalysis(assignmentAnalysis);
		EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		List<@NonNull AssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eStructuralFeature);
		if (assignmentAnalyses == null) {
			assignmentAnalyses = new ArrayList<>();
			eFeature2assignmentAnalyses.put(eStructuralFeature, assignmentAnalyses);
		}
		assignmentAnalyses.add(assignmentAnalysis);
	}

	protected void addSuperRuleAnalysis(@NonNull ParserRuleAnalysis superRuleAnalysis) {
		if ("TypedMultiplicityRefCS".equals(superRuleAnalysis.getRuleName())) {
			getClass();
		}
		Set<@NonNull ParserRuleAnalysis> superRuleAnalyses2 = superRuleAnalyses;
		if (superRuleAnalyses2 == null) {
			superRuleAnalyses = superRuleAnalyses2 = new HashSet<>();
		}
		superRuleAnalyses2.add(superRuleAnalysis);
	}

	/**
	 * Perform the analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void analyze() {
		if ("EssentialOCL::RoundBracketedClauseCS".equals(getName())) {
			getClass(); // XXX debugging
		}
		if ("OCLinEcore::TypedMultiplicityRefCS".equals(getName())) {
			getClass(); // XXX debugging
		}
		//
		//	Convert the parser element tree to a normalized/flattened disjunction of conjunctions of nodes.
		//
		ParserRuleSwitch parserRuleSwitch = new ParserRuleSwitch(this);
		SerializationElement serializationResult = parserRuleSwitch.analyze();
		//
		//	Convert the disjunction of conjunctions of nodes to one or more rules.
		//
		List<@NonNull BasicSerializationRule> serializationRules = new ArrayList<>();
		if (serializationResult.isListOfList()) {
			for (@NonNull List<@NonNull SerializationNode> serializationNodes : serializationResult.asListOfList().getLists()) {
				SerializationNode serializationNode;
				if (serializationNodes.size() == 1) {
					serializationNode = serializationNodes.get(0);
				}
				else {
					CompoundElement alternatives = (CompoundElement)XtextGrammarUtil.getAlternatives(abstractRule);
					serializationNode = new SequenceSerializationNode(alternatives, MultiplicativeCardinality.ONE, serializationNodes);
				}
				createSerializationRules(serializationRules, serializationNode);
			}
		}
		else if (serializationResult.isList()) {
			List<@NonNull SerializationNode> serializationNodes = serializationResult.asList().getNodes();
			SerializationNode serializationNode;
			if (serializationNodes.size() == 1) {
				serializationNode = serializationNodes.get(0);
			}
			else {
				CompoundElement alternatives = (CompoundElement)XtextGrammarUtil.getAlternatives(abstractRule);
				serializationNode = new SequenceSerializationNode(alternatives, MultiplicativeCardinality.ONE, serializationNodes);
			}
			createSerializationRules(serializationRules, serializationNode);
		}
		else if (serializationResult.isNode()) {
			SerializationNode serializationNode = serializationResult.asNode();
			createSerializationRules(serializationRules, serializationNode);
		}
		else {		// isNull()
			throw new IllegalStateException();
		}
		if (serializationRules.size() > 1) {
			Collections.sort(serializationRules, new SerializationRuleComparator());
		}
		this.serializationRules = serializationRules;
		analyzeSerializations(serializationRules);
	}

	/**
	 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
	 *	required behaviour of a current action.
	 */
	public @Nullable RuleCall analyzeActionsAndAssignments() {
		AbstractElement rootElement = XtextGrammarUtil.getAlternatives(getRule());
		return analyzeActionsAndAssignments(rootElement, null, true);
	}

	/**
	 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
	 *	required behaviour of a current action.
	 *
	 * isSimpleAlternative is true if the containment of abstractElement comprises pre alternatives of single element groups.
	 */
	private @Nullable RuleCall analyzeActionsAndAssignments(@NonNull AbstractElement abstractElement, @Nullable RuleCall firstUnassignedRuleCall, boolean isSimpleAlternative) {
		if (abstractElement instanceof Assignment) {
			Assignment assignment = (Assignment)abstractElement;
			AssignmentAnalysis assignmentAnalysis = new DirectAssignmentAnalysis(this, assignment);
			addAssignmentAnalysis(assignmentAnalysis);
		}
		else if (abstractElement instanceof Action) {
			Action action = (Action)abstractElement;
			String feature = action.getFeature();
			if (feature != null) {
				assert firstUnassignedRuleCall != null;
				AbstractRule currentRule = XtextGrammarUtil.getRule(firstUnassignedRuleCall);
				ParserRuleAnalysis currentRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(currentRule);
				AssignmentAnalysis assignmentAnalysis = new ActionAssignmentAnalysis(this, action, currentRuleAnalysis);
				addAssignmentAnalysis(assignmentAnalysis);
			}
		}
		else if (abstractElement instanceof RuleCall) {
			assert firstUnassignedRuleCall == null;
			RuleCall ruleCall = (RuleCall)abstractElement;
			AbstractRule subRule = XtextGrammarUtil.getRule(ruleCall);
			if (XtextGrammarUtil.getClassifier(XtextGrammarUtil.getType(subRule)) instanceof EClass) {
				firstUnassignedRuleCall = ruleCall;
				if (isSimpleAlternative) {
					ParserRuleAnalysis subRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(subRule);
					subRuleAnalysis.addSuperRuleAnalysis(this);
				}
			}
		}
		else if (abstractElement instanceof Alternatives) {
			List<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements((Alternatives)abstractElement);
			if (elements.size() == 1) {
				firstUnassignedRuleCall = analyzeActionsAndAssignments(elements.get(0), firstUnassignedRuleCall, isSimpleAlternative);
			}
			else {
				for (@NonNull AbstractElement nestedElement : XtextGrammarUtil.getElements((Alternatives)abstractElement)) {
					analyzeActionsAndAssignments(nestedElement, firstUnassignedRuleCall, isSimpleAlternative);
				}
			}
		}
		else if (abstractElement instanceof Group) {
			List<@NonNull AbstractElement> elements = XtextGrammarUtil.getElements((Group)abstractElement);
			AbstractElement nonOptionalElement = null;
			for (@NonNull AbstractElement nestedElement : elements) {
				MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(nestedElement);
				if (multiplicativeCardinality.isOne()) {
					if (nonOptionalElement != null) {
						nonOptionalElement = null;
						break;
					}
					nonOptionalElement = nestedElement;
				}
				else if (!multiplicativeCardinality.mayBeZero()) {
					nonOptionalElement = null;
					break;
				}
			}
			for (@NonNull AbstractElement nestedElement : elements) {
				firstUnassignedRuleCall = analyzeActionsAndAssignments(nestedElement, firstUnassignedRuleCall, (nestedElement == nonOptionalElement) && isSimpleAlternative);
			}
		}
		return firstUnassignedRuleCall;
	}

	private void analyzeSerializations(@NonNull Iterable<@NonNull BasicSerializationRule> serializationRules) {
		/**
		 * Determine the ParserRuleAnalyses for each distinct EReference assignment.
		 */
		Map<@NonNull EReference, @NonNull Object> eReference2ruleAnalysisOrAnalyses = new HashMap<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			SerializationNode rootSerializationNode = serializationRule.getBasicSerializationRule().getRootSerializationNode();
			analyzeSerializations(rootSerializationNode, eReference2ruleAnalysisOrAnalyses);
		}
		/**
		 * Father the EReferences for which any ParserRuleAnalyses conflict between distinct assignments.
		 */
		for (Map.Entry<@NonNull EReference, @NonNull Object> entry : eReference2ruleAnalysisOrAnalyses.entrySet()) {
			EReference eReference = entry.getKey();
			Object ruleAnalysisOrAnalyses = eReference2ruleAnalysisOrAnalyses.get(eReference);
			ParserRuleAnalysis discriminatingRuleAnalysis = null;
			List<@NonNull ParserRuleAnalysis> discriminatingRuleAnalyses = null;
			if (ruleAnalysisOrAnalyses instanceof ParserRuleAnalysis) {
				// ?? check that it is not a derived rule
				ParserRuleAnalysis ruleAnalysis = (ParserRuleAnalysis)ruleAnalysisOrAnalyses;
				EClass returnedEClass = ruleAnalysis.getReturnedEClass();
				if (returnedEClass != eReference.getEReferenceType()) {
					discriminatingRuleAnalysis  = ruleAnalysis;		// XXX can probably be much stricter
				}
			}
			else {
				@SuppressWarnings("unchecked")
				List<@NonNull ParserRuleAnalysis> ruleAnalyses = (List<@NonNull ParserRuleAnalysis>)ruleAnalysisOrAnalyses;
				assert ruleAnalyses != null;
				assert ruleAnalyses.size() >= 2;
				discriminatingRuleAnalyses = ruleAnalyses;		// XXX can probably be much stricter
			}
			if ((discriminatingRuleAnalysis != null) || (discriminatingRuleAnalyses != null)) {
				Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatingRuleAnalyses2 = eReference2disciminatingRuleAnalyses;
				if (eReference2disciminatingRuleAnalyses2 == null) {
					eReference2disciminatingRuleAnalyses = eReference2disciminatingRuleAnalyses2 = new HashMap<>();
				}
				List<@NonNull ParserRuleAnalysis> list = eReference2disciminatingRuleAnalyses2.get(eReference);
				if (list == null) {
					list = new ArrayList<>();
					eReference2disciminatingRuleAnalyses2.put(eReference, list);
				}
				if ((discriminatingRuleAnalysis != null) && !list.contains(discriminatingRuleAnalysis)) {
					list.add(discriminatingRuleAnalysis);
				}
				if (discriminatingRuleAnalyses != null) {
					for (@NonNull ParserRuleAnalysis discriminatingRuleAnalysis2 : discriminatingRuleAnalyses) {
						if (!list.contains(discriminatingRuleAnalysis2)) {
							list.add(discriminatingRuleAnalysis2);
						}
					}
				}
			}
		}
	}
	private void analyzeSerializations(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EReference, @NonNull Object> eReference2ruleAnalysisOrAnalyses) {
		if (serializationNode instanceof AssignedRuleCallSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				Iterable<@NonNull AbstractRuleAnalysis> assignedRuleAnalyses = assignedSerializationNode.getAssignedRuleAnalyses();
				if (assignedRuleAnalyses != null) {
					for (@NonNull AbstractRuleAnalysis newRuleAnalysis : assignedRuleAnalyses) {
						if (newRuleAnalysis instanceof ParserRuleAnalysis) {
							Object oldRuleAnalysisOrAnalyses = eReference2ruleAnalysisOrAnalyses.get(eReference);
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
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				analyzeSerializations(nestedSerializationNode, eReference2ruleAnalysisOrAnalyses);
			}
		}
	}

	protected void createSerializationRules(@NonNull List<@NonNull BasicSerializationRule> serializationRules, @NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
			UnassignedRuleCallSerializationNode unassignedRuleCallSerializationNode = (UnassignedRuleCallSerializationNode)serializationNode;
			ParserRuleAnalysis calledRuleAnalysis = (ParserRuleAnalysis)unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
			for (@NonNull SerializationRule calledSerializationRule : calledRuleAnalysis.getSerializationRules()) {
				BasicSerializationRule delegateSerializationRule = calledSerializationRule.getBasicSerializationRule();
				assert delegateSerializationRule == calledSerializationRule;
				serializationRules.add(delegateSerializationRule);
			}
		}
		else {
			BasicSerializationRule serializationRule = new BasicSerializationRule(this, serializationNode);
			serializationRules.add(serializationRule);
		}
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull AssignmentAnalysis>> getEFeature2assignmentAnalyses() {
		return eFeature2assignmentAnalyses;
	}

	public @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> getEReference2DiscriminatingRuleAnalyses() {
		return eReference2disciminatingRuleAnalyses;
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
			IndexVector subParserRuleValueIndexes = null;
			if (subParserRuleValueClosure != null) {
				subParserRuleValueIndexes = new IndexVector();
				subParserRuleValueIndexes.set(index);
				for (@NonNull ParserRuleValue parserRuleValue : subParserRuleValueClosure) {
					subParserRuleValueIndexes.set(parserRuleValue.getIndex());
				}
			}
			Iterable<@NonNull BasicSerializationRule> serializationRules = getSerializationRules();
			@NonNull RTSerializationRule @NonNull [] rtSerializationRules = new @NonNull RTSerializationRule [Iterables.size(serializationRules)];
			parserRuleValue = parserRuleValue2 = new ParserRuleValue(index, getRuleName(), rtSerializationRules, subParserRuleValueIndexes);
			//
			// rtSerializationRules content defined after construction to allow recursive references
			//
			int i = 0;
			for (@NonNull SerializationRule serializationRule : serializationRules) {
				rtSerializationRules[i++] = serializationRule.getBasicSerializationRule().getRuntime();
			}
		}
		return parserRuleValue2;
	}

	public @NonNull Iterable<@NonNull BasicSerializationRule> getSerializationRules() {
		if (serializationRules == null) {
			analyze();
		}
		assert serializationRules != null;
		return serializationRules;
	}

	public void getStaticRuleMatch() {
		if ("Base::MultiplicityCS".equals(name)) {
			getClass();		// XXX
		}
		assert serializationRules != null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
			basicSerializationRule.getStaticRuleMatch();
		}
	}

	public @NonNull Collection<@NonNull ParserRuleAnalysis> getSubRuleAnalysesClosure() {
		assert superRuleAnalysesClosure != null;	// subRuleAnalysesClosure assigned as corrolary of superRuleAnalysesClosure
		return subRuleAnalysesClosure;
	}

	public @NonNull Iterable<@NonNull ParserRuleAnalysis> getSuperRuleAnalysisClosure() {
		if ("SelfExpCS".equals(eClass.getName())) {
			getClass();				// XXX
		}
		List<@NonNull ParserRuleAnalysis> superRuleAnalysesClosureList = this.superRuleAnalysesClosure;
		if (superRuleAnalysesClosureList == null) {
			UniqueList<@NonNull ParserRuleAnalysis> superRuleAnalysesClosureSet = new UniqueList<>();
			superRuleAnalysesClosureSet.add(this);
			for (int i = 0; i < superRuleAnalysesClosureSet.size(); i++) {
				ParserRuleAnalysis ruleAnalysis = superRuleAnalysesClosureSet.get(i);
				Set<@NonNull ParserRuleAnalysis> superRuleAnalyses = ruleAnalysis.superRuleAnalyses;
				if (superRuleAnalyses != null) {
					superRuleAnalysesClosureSet.addAll(superRuleAnalyses);
				}
			}
			superRuleAnalysesClosureList = new ArrayList<>(superRuleAnalysesClosureSet);
			Collections.sort(superRuleAnalysesClosureList, NameUtil.NAMEABLE_COMPARATOR);
			this.superRuleAnalysesClosure = superRuleAnalysesClosureList;
			for (@NonNull ParserRuleAnalysis superRuleAnalysis : superRuleAnalysesClosureList) {
				superRuleAnalysis.subRuleAnalysesClosure.add(this);
			}
		}
		return superRuleAnalysesClosureList;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(getName());
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
				s.append(superRuleAnalysis.getName());
				isFirst1 = false;
			}
		}
		if (serializationRules != null) {
			for (@NonNull SerializationRule serializationRule : serializationRules) {
				StringUtil.appendIndentation(s, depth+1);
				s.append(serializationRule);
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