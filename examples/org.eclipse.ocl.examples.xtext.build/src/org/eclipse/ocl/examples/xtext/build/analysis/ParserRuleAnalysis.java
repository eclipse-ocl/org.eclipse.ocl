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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.LocatorHelper.LocatorSwitch;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SequenceSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationElement;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationRuleAnalysisComparator;
import org.eclipse.ocl.examples.xtext.build.elements.UnassignedRuleCallSerializationNode;
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
	 * Perform the analysis to determine the locally produced EClassifiers and local base rules.
	 */
	protected void analyze() {
		assert serializationRuleAnalyses == null;
		//
		//	Convert the parser element tree to a normalized/flattened disjunction of conjunctions of nodes.
		//
		ParserRuleSwitch parserRuleSwitch = new ParserRuleSwitch(this);
		SerializationElement serializationResult = parserRuleSwitch.analyze();
		//
		//	Convert the disjunction of conjunctions of nodes to one or more rules.
		//
		List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = new ArrayList<>();
		if (serializationResult.isListOfList()) {
			for (@NonNull List<@NonNull SerializationNode> serializationNodes : serializationResult.asListOfList().getLists()) {
				SerializationNode serializationNode;
				if ((serializationNodes.size() == 1) && (getCompoundLocatorElements(serializationNodes) == null)) {
					serializationNode = serializationNodes.get(0);
				}
				else {
					CompoundElement alternatives = (CompoundElement)SerializationUtils.getAlternatives(abstractRule);
					serializationNode = new SequenceSerializationNode(alternatives, GrammarCardinality.ONE, serializationNodes);
				}
				createSerializationRules(serializationRuleAnalyses, serializationNode);
			}
		}
		else if (serializationResult.isList()) {
			List<@NonNull SerializationNode> serializationNodes = serializationResult.asList().getNodes();
			SerializationNode serializationNode;
			if ((serializationNodes.size() == 1) && (getCompoundLocatorElements(serializationNodes) == null)) {
				serializationNode = serializationNodes.get(0);
			}
			else {
				CompoundElement alternatives = (CompoundElement)SerializationUtils.getAlternatives(abstractRule);
				serializationNode = new SequenceSerializationNode(alternatives, GrammarCardinality.ONE, serializationNodes);
			}
			createSerializationRules(serializationRuleAnalyses, serializationNode);
		}
		else if (serializationResult.isNode()) {
			SerializationNode serializationNode = serializationResult.asNode();
			createSerializationRules(serializationRuleAnalyses, serializationNode);
		}
		else {		// isNull()
			throw new IllegalStateException();
		}
		if (serializationRuleAnalyses.size() > 1) {
			Collections.sort(serializationRuleAnalyses, new SerializationRuleAnalysisComparator());
		}
		this.serializationRuleAnalyses = serializationRuleAnalyses;
		analyzeSerializations(serializationRuleAnalyses);
	}

	/**
	 *	Create an assignment analysis for each assignment and current action returning an updated firstUnassignedRuleCall to track the
	 *	required behaviour of a current action.
	 */
	public @Nullable RuleCall analyzeActionsAndAssignments() {
		AbstractElement rootElement = SerializationUtils.getAlternatives(getRule());
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
				AbstractRule currentRule = SerializationUtils.getRule(firstUnassignedRuleCall);
				ParserRuleAnalysis currentRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(currentRule);
				AssignmentAnalysis assignmentAnalysis = new ActionAssignmentAnalysis(this, action, currentRuleAnalysis);
				addAssignmentAnalysis(assignmentAnalysis);
			}
		}
		else if (abstractElement instanceof RuleCall) {
			assert firstUnassignedRuleCall == null;
			RuleCall ruleCall = (RuleCall)abstractElement;
			AbstractRule subRule = SerializationUtils.getRule(ruleCall);
			if (SerializationUtils.getClassifier(SerializationUtils.getType(subRule)) instanceof EClass) {
				firstUnassignedRuleCall = ruleCall;
				if (isSimpleAlternative) {
					ParserRuleAnalysis subRuleAnalysis = (ParserRuleAnalysis)grammarAnalysis.getRuleAnalysis(subRule);
					subRuleAnalysis.addSuperRuleAnalysis(this);
				}
			}
		}
		else if (abstractElement instanceof Alternatives) {
			List<@NonNull AbstractElement> elements = SerializationUtils.getElements((Alternatives)abstractElement);
			if (elements.size() == 1) {
				firstUnassignedRuleCall = analyzeActionsAndAssignments(elements.get(0), firstUnassignedRuleCall, isSimpleAlternative);
			}
			else {
				for (@NonNull AbstractElement nestedElement : SerializationUtils.getElements((Alternatives)abstractElement)) {
					analyzeActionsAndAssignments(nestedElement, firstUnassignedRuleCall, isSimpleAlternative);
				}
			}
		}
		else if (abstractElement instanceof Group) {
			List<@NonNull AbstractElement> elements = SerializationUtils.getElements((Group)abstractElement);
			AbstractElement nonOptionalElement = null;
			for (@NonNull AbstractElement nestedElement : elements) {
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
			for (@NonNull AbstractElement nestedElement : elements) {
				firstUnassignedRuleCall = analyzeActionsAndAssignments(nestedElement, firstUnassignedRuleCall, (nestedElement == nonOptionalElement) && isSimpleAlternative);
			}
		}
		return firstUnassignedRuleCall;
	}

	public void analyzeMatches() {
		assert serializationRuleAnalyses != null;
		for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalyses) {
			serializationRuleAnalysis.analyzeMatches();
		}
	}

	private void analyzeSerializations(@NonNull Iterable<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses) {
		/**
		 * Determine the ParserRuleAnalyses for each distinct EReference assignment.
		 */
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
				@NonNull Integer @Nullable [] assignedRuleIndexes = assignedSerializationNode.getAssignedRuleIndexes();
				if (assignedRuleIndexes != null) {
					for (@NonNull Integer ruleIndex : assignedRuleIndexes) {
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

	protected void createSerializationRules(@NonNull List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses, @NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
			UnassignedRuleCallSerializationNode unassignedRuleCallSerializationNode = (UnassignedRuleCallSerializationNode)serializationNode;
			ParserRuleAnalysis calledRuleAnalysis = (ParserRuleAnalysis)unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
			for (@NonNull SerializationRuleAnalysis calledSerializationRule : calledRuleAnalysis.getSerializationRuleAnalyses()) {
				serializationRuleAnalyses.add(calledSerializationRule);
			}
		}
		else {
			SerializationRuleAnalysis serializationRuleAnalysis = new SerializationRuleAnalysis(this, serializationNode);
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

	private @Nullable Iterable<@NonNull Locator> getCompoundLocatorElements(@NonNull Iterable<@NonNull SerializationNode> serializationNodes) {
		Iterable<@NonNull Locator> compoundedLocators = grammarAnalysis.getCompoundedLocators();
		List<@NonNull Locator> compoundLocatorElements = null;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			for (@NonNull Locator locator : compoundedLocators) {
				if (matches(locator, serializationNode)) {
					if (compoundLocatorElements == null) {
						compoundLocatorElements = new ArrayList<>();
					}
					compoundLocatorElements.add(locator);
				}
			}
		}
		return compoundLocatorElements;
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
		if (abstractElement instanceof CompoundElement) {				// XXX Alternatives need permutation
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
				@NonNull SerializationSegment[] innerFormattingSegments;
				@NonNull SerializationSegment[] outerFormattingSegments;
				List<@NonNull SubIdiom> formattingSubIdioms = formattingSubIdiomsList.get(i);
				if (formattingSubIdioms != null) {
					innerFormattingSegments = grammarAnalysis.getSerializationSegments(formattingSubIdioms, false);
					outerFormattingSegments = grammarAnalysis.getSerializationSegments(formattingSubIdioms, true);
				}
				else {
					innerFormattingSegments = SerializationSegment.VALUE_SEGMENTS_ARRAY;
					outerFormattingSegments = SerializationSegment.VALUE_SEGMENTS_ARRAY;
				}
				innerFormattingSegmentsArray[i] = innerFormattingSegments;
				outerFormattingSegmentsArray[i] = outerFormattingSegments;
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
			analyze();
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
				ParserRuleAnalysis ruleAnalysis = superRuleAnalysesClosureSet.get(i);
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