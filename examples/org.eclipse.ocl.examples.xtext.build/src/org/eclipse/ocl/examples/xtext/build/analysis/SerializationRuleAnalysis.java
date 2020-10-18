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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.LocatorHelper.LocatorSwitch;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeAssignsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.CompositeSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SequenceSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.idioms.impl.LocatorImpl;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.RuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermUnsupported;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndex_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.RuleIndex_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRuleHelper;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString.ToDebugStringable;
import org.eclipse.ocl.examples.xtext.serializer.UserSlotsAnalysis;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A SerializationRuleAnalysis supervises the analysis leading to the MetaData for a SerializationRule.
 */
public class SerializationRuleAnalysis implements Nameable, ToDebugStringable, RuleMatch, SerializationRuleHelper
{
	private static final @NonNull Map<@NonNull SerializationRuleAnalysis, @NonNull SerializationRule> debugMap = new HashMap<>();

	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	private @Nullable EClass producedEClass = null;

	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugString = new ToDebugString(this){};

	/**
	 * The variables, expressions and solutions to determine if an actual user element matches.
	 */
	private boolean hasMatchAnalyses = false;

	/**
	 * The subidioms to decorate each node during serialization.
	 */
	private @Nullable Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms = null;

	private @Nullable Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = null;
	private @Nullable Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes = null;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality = null;

	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality = null;
	/**
	 * The per-feature expression that (re-)computes the required number of assigned slots from the solved
	 * cardinality variables. This is checked against the actual number of slots in an actual user element.
	 */
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> eStructuralFeature2requiredSlotsExpression = new HashMap<>();

	/**
	 * The per-variable solution expression that computes the variable's value from the actual number of slots of an actual user element.
	 *
	 * Lazily populated as solutions found.
	 */
	protected final @NonNull Map<@NonNull Integer, @NonNull SerializationMatchTerm> variableIndex2solution = new HashMap<>();

	/**
	 * The ordered sequence of assign/check instructions to evaluate at run-time to realize the computation of
	 * each solution for its variable.
	 */
	protected final @NonNull List<@NonNull SerializationMatchStep> steps = new ArrayList<>();

	/**
	 * The CardinalityVariable for each node, unless always exactly 1.
	 *
	 * Only used during serialization - replae by known indexes.
	 */
	private final @NonNull Map<@NonNull SerializationNode, @NonNull CardinalityVariable> node2variable = new HashMap<>();

	/**
	 * The Node which each cardinality variable defines the iteration for; the inverse of node2variable.
	 *
	 * ?? Only used as a debugging convenience ??
	 */
	private final @NonNull Map<@NonNull Integer, @NonNull SerializationNode> variableIndex2node = new HashMap<>();
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node = new HashMap<>();

	/**
	 * The Node which each cardinality variable defines the iteration for; the inverse of node2variable.
	 *
	 * ?? Only used as a debugging convenience ??
	 */
	private final @NonNull List<@NonNull AssignedSerializationNode> assignedSerializationNodes = new ArrayList<>();

	private @Nullable SerializationRule serializationRule = null;

	private @Nullable Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent = new HashMap<>();

	public SerializationRuleAnalysis(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		this.rootSerializationNode = rootSerializationNode;
	}

	/**
	 * Accumulate an additional matchTerm expression for a cardinalityVariable.
	 */
	public void addMatchTerm(@Nullable Integer cardinalityVariable, @NonNull SerializationMatchTerm matchTerm) {
		SerializationMatchStep newStep;
		if (cardinalityVariable != null) {
		//	assert !cardinalityVariable.isOne();
			boolean isAssigned = true;
			for (@NonNull SerializationMatchStep step : steps) {
				if (step.isAssignTo(cardinalityVariable)) {
					isAssigned = false;
					break;
				}
			}
			if (isAssigned) {
				newStep = new SerializationMatchStep.MatchStep_Assign(cardinalityVariable, matchTerm);
				variableIndex2solution.put(cardinalityVariable, matchTerm);
			}
			else {
				newStep = new SerializationMatchStep.MatchStep_ValueCheck(cardinalityVariable, matchTerm);
			}
		}
		else {
			newStep = new SerializationMatchStep.MatchStep_Assert(matchTerm);
		}
		steps.add(newStep);
	}

	private void analyzeAssignment(@NonNull EAttribute eAttribute, @Nullable EnumerationValue enumerationValue, @NonNull GrammarCardinality netGrammarCardinality) {
		Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
		if (eAttribute2enumerationValue2grammarCardinality2 == null) {
			eAttribute2enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality2 = new HashMap<>();
		}
		Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = SerializationUtils.maybeNull(eAttribute2enumerationValue2grammarCardinality2.get(eAttribute));
		if (enumerationValue2grammarCardinality == null) {
			enumerationValue2grammarCardinality = new HashMap<>();
			eAttribute2enumerationValue2grammarCardinality2.put(eAttribute, enumerationValue2grammarCardinality);
		}
		GrammarCardinality oldGrammarCardinality = SerializationUtils.maybeNull(enumerationValue2grammarCardinality.get(enumerationValue));
		GrammarCardinality newGrammarCardinality = refineGrammarCardinality(netGrammarCardinality, oldGrammarCardinality);
		enumerationValue2grammarCardinality.put(enumerationValue, newGrammarCardinality);
	}

	private void analyzeAssignment(@NonNull EReference eReference, @NonNull Integer @Nullable [] ruleIndexes, @NonNull GrammarCardinality netGrammarCardinality) {
		Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 == null) {
			eReference2ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality2 = new HashMap<>();
		}
		Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality2.get(eReference));
		if (ruleAnalysis2grammarCardinality == null) {
			ruleAnalysis2grammarCardinality = new HashMap<>();
			eReference2ruleAnalysis2grammarCardinality2.put(eReference, ruleAnalysis2grammarCardinality);
		}
		if (ruleIndexes != null) {
			for (@NonNull Integer ruleIndex : ruleIndexes) {
				@NonNull AbstractRuleAnalysis ruleAnalysis2 = grammarAnalysis.getRuleAnalysis(ruleIndex);
				if (ruleAnalysis2 instanceof ParserRuleAnalysis) {
					GrammarCardinality oldGrammarCardinality = SerializationUtils.maybeNull(ruleAnalysis2grammarCardinality.get(ruleAnalysis2));
					GrammarCardinality newGrammarCardinality = refineGrammarCardinality(netGrammarCardinality, oldGrammarCardinality);
					ruleAnalysis2grammarCardinality.put((ParserRuleAnalysis) ruleAnalysis2, newGrammarCardinality);
				}
			}
		}
	}

	/**
	 * Create/update the sum-of-products expression for the feature assigned by assignedSerializationNode to include
	 * a product for assignedSerializationNode's cardinality variable and for all surrounding sequences in outerVariables.
	 */
	private void analyzeAssignment(@NonNull AssignedSerializationNode assignedSerializationNode, @NonNull Iterable<@NonNull CardinalityVariable> cardinalityVariables,
			@NonNull GrammarCardinality netGrammarCardinality) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		//
		//	Accumulate enumerated attributes
		//
		CardinalityExpression cardinalityExpression = SerializationUtils.maybeNull(eStructuralFeature2requiredSlotsExpression.get(eStructuralFeature));
		if (eStructuralFeature instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute)eStructuralFeature;
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			analyzeAssignment(eAttribute, enumerationValue, netGrammarCardinality);
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", eStructuralFeature2requiredSlotsExpression.size());
				assert name != null;
				if (enumerationValue == null) {
					cardinalityExpression = new EStructuralFeatureCardinalityExpression(name, eStructuralFeature);
				}
				else {
					cardinalityExpression = new EAttributeCardinalityExpression(name, (EAttribute)eStructuralFeature, enumerationValue);
				}
				eStructuralFeature2requiredSlotsExpression.put(eStructuralFeature, cardinalityExpression);
			}
			//
			//	Add cardinalityVariables as a further product term to the sum of products.
			//
			if (enumerationValue != null) {
				cardinalityExpression = cardinalityExpression.getCardinalityExpression(grammarAnalysis, enumerationValue);
			}
		}
		//
		//	Accumulate discriminated references
		//
		else {
			EReference eReference = (EReference)eStructuralFeature;
			@NonNull Integer @Nullable [] ruleIndexes = assignedSerializationNode.getAssignedRuleIndexes();
			analyzeAssignment(eReference, ruleIndexes, netGrammarCardinality);
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			cardinalityExpression = SerializationUtils.maybeNull(eStructuralFeature2requiredSlotsExpression.get(eStructuralFeature));
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", eStructuralFeature2requiredSlotsExpression.size());
				assert name != null;;
				cardinalityExpression = new EStructuralFeatureCardinalityExpression(name, eStructuralFeature);
				eStructuralFeature2requiredSlotsExpression.put(eStructuralFeature, cardinalityExpression);
			}
		}
		//
		//	Add cardinalityVariables as a further product term to the sum of products.
		//
		cardinalityExpression.addMultiplicityProduct(cardinalityVariables);
	}

	public void analyzeMatches() {
		if (!hasMatchAnalyses) {
			hasMatchAnalyses = true;
			//
			//	Traverse the chosen serialization tree path to determine the
			//	cardinality variables and expressions to be solved to characterize the serialization.
			//
			analyzeSerialization();
			//
			//	Analyze the cardinality expressions to determine the steps that compute/validate each cardinality variable.
			//
			analyzeMatchTerm();
		}
	}

	public void analyzeSerialization() {
		analyzeSerialization(rootSerializationNode, new Stack<@NonNull CardinalityVariable>(), GrammarCardinality.ONE);
	}
	protected void analyzeSerialization(@NonNull SerializationNode serializationNode, @NonNull Stack<@NonNull CardinalityVariable> cardinalityVariables, @NonNull GrammarCardinality outerGrammarCardinality) {
		//
		//	Allocate a CardinalityVariable for an indeterminate multiplicity.
		//
		CardinalityVariable cardinalityVariable = null;
		if (!serializationNode.isRedundant()) {
			GrammarCardinality grammarCardinality = serializationNode.getGrammarCardinality();
			if (!grammarCardinality.isOne()) {
				int index = variable2node.size();
				String name = String.format("C%02d", index);
				assert name != null;
				@NonNull Integer @Nullable [] ruleIndexes = serializationNode instanceof AssignedSerializationNode ? ((AssignedSerializationNode)serializationNode).getAssignedRuleIndexes() : null;
				cardinalityVariable = new CardinalityVariable(index, name, ruleIndexes != null ? new GrammarRuleVector(ruleIndexes) : null, grammarCardinality);
				CardinalityVariable old2 = SerializationUtils.maybeNull(node2variable.put(serializationNode, cardinalityVariable));
				assert old2 == null;
				SerializationNode old3 = SerializationUtils.maybeNull(variable2node.put(cardinalityVariable, serializationNode));
				assert old3 == null;
				SerializationNode old4 = SerializationUtils.maybeNull(variableIndex2node.put(cardinalityVariable.getIndex(), serializationNode));
				assert old4 == null;
			}
		}
		GrammarCardinality innerGrammarCardinality = serializationNode.getGrammarCardinality();
		GrammarCardinality netGrammarCardinality = GrammarCardinality.max(innerGrammarCardinality, outerGrammarCardinality);
		//
		//	Create the feature size expressions for an assignment
		//
		if (cardinalityVariable != null) {
			cardinalityVariables.push(cardinalityVariable);
		}
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			assignedSerializationNodes.add(assignedSerializationNode);
			analyzeAssignment(assignedSerializationNode, cardinalityVariables, netGrammarCardinality);
		}
		//
		//	Recurse for sequences
		//
		else if (serializationNode instanceof SequenceSerializationNode) {
			SequenceSerializationNode sequenceSerializationNode = (SequenceSerializationNode)serializationNode;
			for (@NonNull SerializationNode nestedSerializationNode : sequenceSerializationNode.getSerializationNodes()) {
				analyzeSerialization(nestedSerializationNode, cardinalityVariables, netGrammarCardinality);
			}
		}
		if (cardinalityVariable != null) {
			cardinalityVariables.pop();
		}
	}

	public void analyzeMatchTerm(@NonNull List<@NonNull SerializationMatchStep> steps) {
		Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 != null) {
			List<@NonNull EReference> eContainments = null;
			for (@NonNull EReference eReference : eReference2ruleAnalysis2grammarCardinality2.keySet()) {
				if (eReference.isContainment()) {
					if (eContainments == null) {
						eContainments = new ArrayList<>();
					}
					eContainments.add(eReference);
				}
			}
			if (eContainments != null) {
				Collections.sort(eContainments, SerializationUtils.ENAMED_ELEMENT_COMPARATOR);
				for (@NonNull EReference eReference : eContainments) {
					@SuppressWarnings("null")
					@NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> value = eReference2ruleAnalysis2grammarCardinality2.get(eReference);
					@SuppressWarnings("null")
					@NonNull Collection<@Nullable ParserRuleAnalysis> assignedRuleAnalyses = value.keySet();
					steps.add(new SerializationMatchStep.MatchStep_RuleCheck(eReference, new GrammarRuleVector(assignedRuleAnalyses)));
				}
			}
		}
	}

	public void analyzeMatchTerm() {
		analyzeMatchTerm(steps);
		List<@NonNull CardinalityExpression> residualExpressions = new ArrayList<>();
		for (@NonNull CardinalityExpression expression : eStructuralFeature2requiredSlotsExpression.values()) {
			Iterable<@NonNull CardinalityExpression> cardinalityExpressions = expression.getCardinalityExpressions();
			if (cardinalityExpressions != null) {
				for (@NonNull CardinalityExpression cardinalityExpression : cardinalityExpressions) {
					residualExpressions.add(cardinalityExpression);
				}
			}
			else {
				residualExpressions.add(expression);
			}
		}
		Collections.sort(residualExpressions, SerializationUtils.NAMEABLE_COMPARATOR);
		List<@NonNull CardinalityVariable> variables = Lists.newArrayList(getVariables());
		Collections.sort(variables, SerializationUtils.NAMEABLE_COMPARATOR);
		//
		//	Confirm that variables with a "1" solution were skipped.
		//
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
		}
		int oldSize;
		//
		//	Eliminate expressions that involve no unresolved variables or which provide a linear solution for a single variable.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
				if (residualExpression.analyzeTrivial(this, false)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize);
		//
		//	Eliminate expressions that involve no unresolved variables.
		//	assign 0/1 solutions for all optional cardinalities that are common factors to all other products.
		//		e.g. |F| = C01 + C01 * C02 can be solved as C02 = !F| -1 if C01 is optional.
		//
		boolean gotOne;
		do {
			oldSize = residualExpressions.size();
			gotOne = false;
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
				if (residualExpression.analyzeMayBeZeroCommonFactors(this, false)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
					if (residualExpression.analyzeTrivial(this, false)) {
						residualExpressions.remove(i);
					}
				}
			}
		} while (gotOne || (residualExpressions.size() < oldSize));
		//
		//	Ditto, but consider mayBeMany for the case of gratuitous mayBeMany products.
		//
		do {
			oldSize = residualExpressions.size();
			gotOne = false;
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
				if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
					if (residualExpression.analyzeTrivial(this, true)) {
						residualExpressions.remove(i);
					}
				}
			}
		} while (gotOne || (residualExpressions.size() < oldSize));
		/*
		//	Assign 0/1 solutions for all variables involved in a linear equation in the light of other solutions.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeTrivial(this)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize); */
		if (residualExpressions.size() > 0) {
			Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull Integer>> expression2unsolvedVariables =
					computeExpression2unsolvedVariables(residualExpressions);
			Map<@NonNull Integer, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions =
					computeVariable2expressions(expression2unsolvedVariables);
			Map<@NonNull Integer, @NonNull Set<@NonNull Integer>> unsolvedVariable2unsolvedVariableGroups =
					computeUnsolvedVariableGroups(unsolvedVariable2expressions, expression2unsolvedVariables);

			for (@NonNull Set<@NonNull Integer> unsolvedVariableGroup : new HashSet<>(unsolvedVariable2unsolvedVariableGroups.values())) {
				Iterable<@NonNull CardinalityExpression> unresolvedExpressions = computeExpressions(unsolvedVariableGroup, unsolvedVariable2expressions);
				int size = Iterables.size(unresolvedExpressions);
				if (size == 1) {
					CardinalityExpression residualExpression = SerializationUtils.nonNullState(unresolvedExpressions.iterator().next());
					/*if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
						// ok
					}
					else*/ if (residualExpression.analyzeRedundantProducts(this)) {
						// ok
					}
					else {
						SerializationMatchTermRuntime runtimeMatchTerm = new SerializationMatchTermRuntime(unsolvedVariableGroup, unresolvedExpressions);
						for (@NonNull Integer unresolvedVariable : unsolvedVariableGroup) {
							addMatchTerm(unresolvedVariable, runtimeMatchTerm);
						}
					}
				}
				else {
					//
					//	assign run-time search solution to remaining expressions.
					//
					SerializationMatchTermRuntime runtimeMatchTerm = new SerializationMatchTermRuntime(unsolvedVariableGroup, unresolvedExpressions);
					for (@NonNull Integer unresolvedVariable : unsolvedVariableGroup) {
						addMatchTerm(unresolvedVariable, runtimeMatchTerm);
					}
				}
			}
		}
		//
		// Assign solutions to gratuitous grammar terms.
		// XXX need to encode residue for run-time resolution
		//
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
			if (basicGetMatchTerm(variable) == null) {
				if (residualExpressions.isEmpty()) {
					addMatchTerm(variable.getIndex(), new SerializationMatchTermInteger(variable.mayBeNone() ? 0 : 1));
				}
				else {
					addMatchTerm(variable.getIndex(), new SerializationMatchTermUnsupported());
				}
			}
		}
	}

	public @NonNull EAttribute_EnumerationValues @Nullable [] basicGetEAttribute2EnumerationValues() {
		Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = getEAttribute2EnumerationValues();
		int size = eAttribute2enumerationValues.size();
		if (size <= 0) {
			return null;
		}
		@NonNull EAttribute_EnumerationValues[] eAttributeDatas = new @NonNull EAttribute_EnumerationValues[size];
		int i = 0;
		for (Map.Entry<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> entry : eAttribute2enumerationValues.entrySet()) {
			@SuppressWarnings("null")
			@NonNull Set<@NonNull EnumerationValue> enumerationValuesSet = entry.getValue();
			@SuppressWarnings("null")
			@NonNull EnumerationValue @NonNull [] enumerationValuesArray = enumerationValuesSet.toArray(new @NonNull EnumerationValue[enumerationValuesSet.size()]);
			Arrays.sort(enumerationValuesArray, SerializationUtils.NAMEABLE_COMPARATOR);
			eAttributeDatas[i++] = new EAttribute_EnumerationValues(entry.getKey(), enumerationValuesArray);
		}
		Arrays.sort(eAttributeDatas, SerializationUtils.NAMEABLE_COMPARATOR);
		return eAttributeDatas;
	}

	public @NonNull EAttribute_EnumerationValue_GrammarCardinality @Nullable [] basicGetEAttribute2enumerationValue2grammarCardinality() {
		Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
		if (eAttribute2enumerationValue2grammarCardinality2 == null) {
			return null;
		}
		@NonNull EAttribute_EnumerationValue_GrammarCardinality [] eAttribute2enumerationValue2grammarCardinality = new @NonNull EAttribute_EnumerationValue_GrammarCardinality[eAttribute2enumerationValue2grammarCardinality2.size()];
		int i1 = 0;
		for (Map.Entry<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> entry1 : eAttribute2enumerationValue2grammarCardinality2.entrySet()) {
			EAttribute eAttribute = SerializationUtils.maybeNull(entry1.getKey());
			@SuppressWarnings("null")
			@NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> value = entry1.getValue();
			@NonNull EnumerationValue_GrammarCardinality [] enumerationValue_GrammarCardinality = new @NonNull EnumerationValue_GrammarCardinality [value.size()];
			int i2 = 0;
			for (Map.Entry<@Nullable EnumerationValue, @NonNull GrammarCardinality> entry2 : value.entrySet()) {
				EnumerationValue enumerationValue = entry2.getKey();
				enumerationValue_GrammarCardinality[i2++] = new EnumerationValue_GrammarCardinality(enumerationValue, entry2.getValue());
			}
			Arrays.sort(enumerationValue_GrammarCardinality, SerializationUtils.NAMEABLE_COMPARATOR);
			eAttribute2enumerationValue2grammarCardinality[i1++] = new EAttribute_EnumerationValue_GrammarCardinality(eAttribute, enumerationValue_GrammarCardinality);
		}
		Arrays.sort(eAttribute2enumerationValue2grammarCardinality, SerializationUtils.NAMEABLE_COMPARATOR);
		return eAttribute2enumerationValue2grammarCardinality;
	}
//	public @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> basicGetEAttribute2enumerationValue2grammarCardinality() {
//		return eAttribute2enumerationValue2grammarCardinality;
//	}

//	public @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable Integer, @NonNull GrammarCardinality>> basicGetEReference2ruleValueIndex2grammarCardinality() {
	public @NonNull EReference_RuleIndex_GrammarCardinality @Nullable [] basicGetEReference2ruleValueIndex2grammarCardinality() {
		Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 == null) {
			return null;
		}
		@NonNull EReference_RuleIndex_GrammarCardinality [] eReference2ruleValueIndex2grammarCardinality = new @NonNull EReference_RuleIndex_GrammarCardinality[eReference2ruleAnalysis2grammarCardinality2.size()];
		int i1 = 0;
		for (Map.Entry<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> entry1 : eReference2ruleAnalysis2grammarCardinality2.entrySet()) {
			@SuppressWarnings("null")
			@NonNull EReference eReference = entry1.getKey();
			@SuppressWarnings("null")
			@NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> value = entry1.getValue();
			@NonNull RuleIndex_GrammarCardinality [] ruleValueIndex2grammarCardinality = new @NonNull RuleIndex_GrammarCardinality [value.size()];
			int i2 = 0;
			for (Map.Entry<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> entry2 : value.entrySet()) {
				ParserRuleAnalysis ruleAnalysis = entry2.getKey();
				Integer ruleValueIndex = ruleAnalysis != null ? ruleAnalysis.getIndex() : null;
				ruleValueIndex2grammarCardinality[i2++] = new RuleIndex_GrammarCardinality(ruleValueIndex, entry2.getValue());
			}
			Arrays.sort(ruleValueIndex2grammarCardinality);
			eReference2ruleValueIndex2grammarCardinality[i1++] = new EReference_RuleIndex_GrammarCardinality(eReference, ruleValueIndex2grammarCardinality);
		}
		Arrays.sort(eReference2ruleValueIndex2grammarCardinality, SerializationUtils.NAMEABLE_COMPARATOR);
		return eReference2ruleValueIndex2grammarCardinality;
	}

	public @NonNull EReference_RuleIndexes @Nullable [] basicGetEReference2AssignedRuleValueIndexes() {
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes = getEReference2AssignedRuleValueIndexes();
		int size = eReference2assignedRuleIndexes.size();
		if (size <= 0) {
			return null;
		}
		@NonNull EReference_RuleIndexes[] eReferenceDatas = new @NonNull EReference_RuleIndexes[size];
		int i = 0;
		for (Map.Entry<@NonNull EReference, @NonNull GrammarRuleVector> entry : eReference2assignedRuleIndexes.entrySet()) {
			eReferenceDatas[i++] = new EReference_RuleIndexes(entry.getKey(), entry.getValue());
		}
		Arrays.sort(eReferenceDatas, SerializationUtils.NAMEABLE_COMPARATOR);
		return eReferenceDatas;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(int cardinalityVariableIndex) {
		SerializationMatchTerm solution = SerializationUtils.maybeNull(variableIndex2solution.get(cardinalityVariableIndex));
		return solution != null ? solution.basicGetIntegerSolution(this) : null;
	}

	public @NonNull EAttribute @Nullable [] basicGetNeedsDefaultEAttributes() {
		assert hasMatchAnalyses;
		int needsDefaultCount = 0;
		for (Map.Entry<@NonNull EStructuralFeature, @NonNull CardinalityExpression> entry : eStructuralFeature2requiredSlotsExpression.entrySet()) {
			if (SerializationUtils.nonNullState(entry.getValue()).isOne() && (entry.getKey() instanceof EAttribute)) {
				needsDefaultCount++;
			}
		}
		if (needsDefaultCount <= 0) {
			return null;
		}
		@NonNull EAttribute [] needsDefaultEAttributes = new @NonNull EAttribute[needsDefaultCount];
		int needsDefaultIndex = 0;
		for (Map.Entry<@NonNull EStructuralFeature, @NonNull CardinalityExpression> entry : eStructuralFeature2requiredSlotsExpression.entrySet()) {
			EStructuralFeature eStructuralFeature = SerializationUtils.nonNullState(entry.getKey());
			if (SerializationUtils.nonNullState(entry.getValue()).isOne() && (eStructuralFeature instanceof EAttribute)) {
				needsDefaultEAttributes[needsDefaultIndex++] = (EAttribute)eStructuralFeature;
			}
		}
		Arrays.sort(needsDefaultEAttributes, SerializationUtils.ENAMED_ELEMENT_COMPARATOR);
		return needsDefaultEAttributes;
	}

	public @Nullable SerializationMatchTerm basicGetMatchTerm(@NonNull CardinalityVariable cardinalityVariable) {
		return variableIndex2solution.get(cardinalityVariable.getIndex());
	}

	/**
	 * Return all the expressions that use any of variableGroup.
	 */
	protected @NonNull Iterable<@NonNull CardinalityExpression> computeExpressions(@NonNull Set<@NonNull Integer> variableGroup,
			@NonNull Map<@NonNull Integer, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions) {
		Set<@NonNull CardinalityExpression> unresolvedExpressions = new HashSet<>();
		for (@NonNull Integer variable : variableGroup) {
			Set<@NonNull CardinalityExpression> expressions = SerializationUtils.maybeNull(variable2expressions.get(variable));
			assert expressions != null;
			unresolvedExpressions.addAll(expressions);
		}
		return unresolvedExpressions;
	}

	/**
	 * Return the Map from each of expressions to its variables that lack a solution.
	 */
	protected @NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull Integer>> computeExpression2unsolvedVariables(
			@NonNull Iterable<@NonNull CardinalityExpression> expressions) {
		Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull Integer>> expression2unsolvedVariables = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expressions) {
			Iterable<@NonNull Integer> unsolvedVariables = expression.getUnknownVariables(this);
			if (unsolvedVariables != null) {
				for (@NonNull Integer variable : unsolvedVariables) {
					Set<@NonNull Integer> variables = SerializationUtils.maybeNull(expression2unsolvedVariables.get(expression));
					if (variables == null) {
						variables = new HashSet<>();
						expression2unsolvedVariables.put(expression, variables);
					}
					variables.add(variable);
				}
			}
		}
		return expression2unsolvedVariables;
	}

	/**
	 * Return a Map from each variable to the set of variables that participate in non-independent expressions.
	 */
	protected @NonNull Map<@NonNull Integer, @NonNull Set<@NonNull Integer>> computeUnsolvedVariableGroups(
			@NonNull Map<@NonNull Integer, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions,
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull Integer>> expression2unsolvedVariables) {
		Map<@NonNull Integer, @NonNull Set<@NonNull Integer>> unsolvedVariable2unresolvedVariables = new HashMap<>();
		List<@NonNull Integer> workVariables = new ArrayList<>(unsolvedVariable2expressions.keySet());
		for (@NonNull Integer workVariable : workVariables) {
			Set<@NonNull Integer> unsolvedVariables = SerializationUtils.maybeNull(unsolvedVariable2unresolvedVariables.get(workVariable));
			if (unsolvedVariables == null) {
				unsolvedVariables = new HashSet<>();
				unsolvedVariable2unresolvedVariables.put(workVariable, unsolvedVariables);
				unsolvedVariables.add(workVariable);
				Set<@NonNull CardinalityExpression> expressions = SerializationUtils.maybeNull(unsolvedVariable2expressions.get(workVariable));
				assert expressions != null;
				for (@NonNull CardinalityExpression expression : expressions) {
					Set<@NonNull Integer> moreUnsolvedVariables = SerializationUtils.maybeNull(expression2unsolvedVariables.get(expression));
					assert moreUnsolvedVariables != null;
					moreUnsolvedVariables.removeAll(unsolvedVariables);
					for (@NonNull Integer anotherUnsolvedVariable : moreUnsolvedVariables) {
						unsolvedVariables.add(anotherUnsolvedVariable);
						unsolvedVariable2unresolvedVariables.put(anotherUnsolvedVariable, unsolvedVariables);
					}
				}
			}
		}
		return unsolvedVariable2unresolvedVariables;
	}

	/**
	 * Return the inverse of the Map from expressions to variables.
	 */
	protected @NonNull Map<@NonNull Integer, @NonNull Set<@NonNull CardinalityExpression>> computeVariable2expressions(
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull Integer>> expression2variables) {
		Map<@NonNull Integer, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expression2variables.keySet()) {
			Set<@NonNull Integer> variables = SerializationUtils.maybeNull(expression2variables.get(expression));
			assert variables != null;
			for (@NonNull Integer variable : variables) {
				Set<@NonNull CardinalityExpression> expressions = SerializationUtils.maybeNull(variable2expressions.get(variable));
				if (expressions == null) {
					expressions = new HashSet<>();
					variable2expressions.put(variable, expressions);
				}
				expressions.add(expression);
			}
		}
		return variable2expressions;
	}

	protected @NonNull DynamicRuleMatch createDynamicRuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis) {
		assert slotsAnalysis.basicGetDynamicRuleMatch(this) == null;
		@NonNull SerializationMatchStep [] matchStepsArray = SerializationUtils.nonNullState(steps.toArray(new @NonNull SerializationMatchStep[steps.size()]));
		SerializationRule serializationRule = getSerializationRule();
		DynamicRuleMatch dynamicRuleMatch = new DynamicRuleMatch(slotsAnalysis, serializationRule, matchStepsArray, this);
		slotsAnalysis.addDynamicRuleMatch(dynamicRuleMatch);
		return dynamicRuleMatch;
	}

	private @Nullable List<@NonNull AssignedSerializationNode> gatherAssignedSerializationNodes(@NonNull EReference eReference, @NonNull SerializationNode serializationNode, @Nullable List<@NonNull AssignedSerializationNode> assignedSerializationNodes) {
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			if (assignedSerializationNode.getEStructuralFeature() == eReference) {
				if (assignedSerializationNodes == null) {
					assignedSerializationNodes = new ArrayList<>();
				}
				assignedSerializationNodes.add(assignedSerializationNode);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				assignedSerializationNodes = gatherAssignedSerializationNodes(eReference, nestedSerializationNode, assignedSerializationNodes);
			}
		}
		return assignedSerializationNodes;
	}

	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference) {
		return gatherAssignedSerializationNodes(eReference, rootSerializationNode, null);
	}

	public int getCardinalityVariableIndex(@NonNull SerializationNode serializationNode) {
		CardinalityVariable cardinalityVariable = SerializationUtils.maybeNull(node2variable.get(serializationNode));
		return cardinalityVariable != null ? cardinalityVariable.getIndex() : -1;
	}

	public @NonNull Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> getEAttribute2EnumerationValues() {
		Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues2 = eAttribute2enumerationValues;
		if (eAttribute2enumerationValues2 == null) {
			eAttribute2enumerationValues = eAttribute2enumerationValues2 = new HashMap<>();
			getEAttribute2EnumerationValues(rootSerializationNode, eAttribute2enumerationValues2);
		}
		if (eAttribute2enumerationValues2.size() > 0) {
			for (Map.Entry<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> entry : eAttribute2enumerationValues2.entrySet()) {
				@SuppressWarnings("null")
				@NonNull Set<@NonNull EnumerationValue> assignedRuleIndexes = entry.getValue();
				eAttribute2enumerationValues2.put(entry.getKey(), assignedRuleIndexes);
			}
		}
		return eAttribute2enumerationValues2;
	}

	private void getEAttribute2EnumerationValues(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			EAttribute eAttribute = (EAttribute)assignedKeywordsSerializationNode.getEStructuralFeature();
			Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues.get(eAttribute));
			if (enumerationValues == null) {
				enumerationValues = new HashSet<>();
				eAttribute2enumerationValues.put(eAttribute, enumerationValues);
			}
			EnumerationValue enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
			enumerationValues.add(enumerationValue);
		}
		else if (serializationNode instanceof AlternativeAssignsSerializationNode) {
			AlternativeAssignsSerializationNode assignsSerializationNode = (AlternativeAssignsSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignsSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute)eStructuralFeature;
				EnumerationValue enumerationValue = assignsSerializationNode.getEnumerationValue();
				if (enumerationValue != null) {
					Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues.get(eAttribute));
					if (enumerationValues == null) {
						enumerationValues = new HashSet<>();
						eAttribute2enumerationValues.put(eAttribute, enumerationValues);
					}
					enumerationValues.add(enumerationValue);
				}
			}
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			AssignedKeywordSerializationNode assignedKeywordSerializationNode = (AssignedKeywordSerializationNode)serializationNode;
			EAttribute eAttribute = (EAttribute)assignedKeywordSerializationNode.getEStructuralFeature();
			Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues.get(eAttribute));
			if (enumerationValues == null) {
				enumerationValues = new HashSet<>();
				eAttribute2enumerationValues.put(eAttribute, enumerationValues);
			}
			EnumerationValue enumerationValue = assignedKeywordSerializationNode.getEnumerationValue();
			enumerationValues.add(enumerationValue);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEAttribute2EnumerationValues(nestedSerializationNode, eAttribute2enumerationValues);
			}
		}
	}

	public @NonNull Map<@NonNull EReference, @NonNull GrammarRuleVector> getEReference2AssignedRuleValueIndexes() {
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes2 = eReference2assignedRuleIndexes;
		if (eReference2assignedRuleIndexes2 == null) {
			eReference2assignedRuleIndexes = eReference2assignedRuleIndexes2 = new HashMap<>();
			getEReference2AssignedRuleIndexes(rootSerializationNode, eReference2assignedRuleIndexes2);
		}
		return eReference2assignedRuleIndexes2;
	}

	private void getEReference2AssignedRuleIndexes(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes) {
		if ((serializationNode instanceof AssignedRuleCallSerializationNode) || (serializationNode instanceof AlternativeAssignsSerializationNode)) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				GrammarRuleVector assignedRuleIndexes = SerializationUtils.maybeNull(eReference2assignedRuleIndexes.get(eReference));
				if (assignedRuleIndexes == null) {
					assignedRuleIndexes = new GrammarRuleVector();
					eReference2assignedRuleIndexes.put(eReference, assignedRuleIndexes);
				}
				@NonNull Integer @Nullable [] ruleIndexes = assignedSerializationNode.getAssignedRuleIndexes();
				if (ruleIndexes != null) {
					for (@NonNull Integer ruleIndex : ruleIndexes) {
						assignedRuleIndexes.set(ruleIndex);
					}
				}
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEReference2AssignedRuleIndexes(nestedSerializationNode, eReference2assignedRuleIndexes);
			}
		}
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> getEStructuralFeature2requiredSlotsExpression() {
		return eStructuralFeature2requiredSlotsExpression;
	}

	public @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		return getEAttribute2EnumerationValues().get(eAttribute);
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		assert hasMatchAnalyses;
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = SerializationUtils.maybeNull(eAttribute2enumerationValue2grammarCardinality.get(eStructuralFeature));
			if (enumerationValue2grammarCardinality != null) {
				return enumerationValue2grammarCardinality.get(null);
			}
		}
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality.get(eStructuralFeature));
			if (ruleAnalysis2grammarCardinality != null) {
				return ruleAnalysis2grammarCardinality.get(null);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		assert hasMatchAnalyses;
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = SerializationUtils.maybeNull(eAttribute2enumerationValue2grammarCardinality.get(eAttribute));
			if (enumerationValue2grammarCardinality != null) {
				return enumerationValue2grammarCardinality.get(enumerationValue);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		assert hasMatchAnalyses;
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality.get(eReference));
			if (ruleAnalysis2grammarCardinality != null) {
				return ruleAnalysis2grammarCardinality.get(ruleAnalysis);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		assert hasMatchAnalyses;
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality.get(eReference));
			if (ruleAnalysis2grammarCardinality != null) {
				for (@Nullable ParserRuleAnalysis parserRuleAnalysis : ruleAnalysis2grammarCardinality.keySet()) {
					if ((parserRuleAnalysis != null) && (parserRuleAnalysis.getRuleValue() == ruleValue)) {
						return ruleAnalysis2grammarCardinality.get(parserRuleAnalysis);
					}
				}
			}
		}
		return null;
	}

	private void getIdiomMatches(@NonNull SerializationNode serializationNode, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomSerializationMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomSerializationMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				SubIdiom firstSubIdiom = idiom.getOwnedSubIdioms().get(0);
				assert firstSubIdiom != null;
				boolean firstSubIdiomMatches = matches(firstSubIdiom, serializationNode);
				idiomMatches[idiomIndex] = firstSubIdiomMatches ? grammarAnalysis.createIdiomMatch(idiom, serializationNode) : null;
			}
			else {
				idiomMatch.nextMatch(serializationNode, this);
			}
			idiomIndex++;
		}
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getIdiomMatches(nestedSerializationNode, idioms, idiomMatches);
			}
		}
	}

	@Override
	public @NonNull String getName() {
		return ruleAnalysis.getName();
	}

	public @Nullable SerializationNode getParent(@NonNull SerializationNode serializationNode) {
		Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent2 = node2parent;
		if (node2parent2 == null) {
			node2parent2 = node2parent = new HashMap<>();
			node2parent2.put(rootSerializationNode, null);
			getParentInternal(node2parent2, rootSerializationNode);
		}
		return node2parent2.get(serializationNode);
	}
	private void getParentInternal(@NonNull Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent2, @NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode childNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				node2parent2.put(childNode, serializationNode);
				getParentInternal(node2parent2, childNode);
			}
		}
		else {
			assert !(serializationNode instanceof CompositeSerializationNode);
		}
	}

	public @NonNull EClass getProducedEClass() {
		EClass producedEClass2 = producedEClass;
		if (producedEClass2 == null) {
			EClass returnedEClass = ruleAnalysis.getReturnedEClass();
			producedEClass = producedEClass2 = refineProducedEClass(rootSerializationNode, returnedEClass);
		}
		return producedEClass2;
	}

	public @NonNull SerializationNode getRootSerializationNode() {
		return rootSerializationNode;
	}

	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	public int getRuleValueIndex() {
		return ruleAnalysis.getIndex();
	}

	public @NonNull Iterable<@NonNull SerializationMatchStep> getSerializationMatchSteps() {
		assert hasMatchAnalyses;
		return steps;
	}

	public @NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> getSerializationNode2subIdioms() {
		Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms2 = serializationNode2subIdioms;
		if (serializationNode2subIdioms2 == null) {
			assert hasMatchAnalyses;
			EClass producedEClass = getProducedEClass();
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
			@Nullable IdiomSerializationMatch @NonNull [] idiomMatches = new @Nullable IdiomSerializationMatch[Iterables.size(idioms)];
			getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
			//
			//	Install the subidioms for each first/mixin full idiom match.
			//
			serializationNode2subIdioms2 = new HashMap<>();
			for (@Nullable IdiomSerializationMatch idiomMatch : idiomMatches) {
				if (idiomMatch != null) {
					idiomMatch.installIn(serializationNode2subIdioms2);
				}
			}
			serializationNode2subIdioms = serializationNode2subIdioms2;
		}
		return serializationNode2subIdioms2;
	}

	public @NonNull SerializationRule getSerializationRule() {
		SerializationRule serializationRule2 = serializationRule;
		if (serializationRule2 == null) {
			analyzeMatches();
			serializationRule2 = serializationRule;
			if (serializationRule2 == null) {
				@NonNull SerializationMatchStep @NonNull [] matchSteps = SerializationUtils.nonNullState(steps.toArray(new @NonNull SerializationMatchStep[steps.size()]));
				List<@NonNull SerializationStep> stepsList = new ArrayList<>();
				Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms = getSerializationNode2subIdioms();
				rootSerializationNode.gatherStepsAndSubIdioms(this, stepsList, serializationNode2subIdioms);
				int size = stepsList.size();
				@NonNull SerializationStep @NonNull [] serializationSteps = SerializationUtils.nonNullState(stepsList.toArray(new @NonNull SerializationStep[size]));
				EAttribute [] castNeedsDefaultEAttributes = basicGetNeedsDefaultEAttributes();
				serializationRule = serializationRule2 = new SerializationRule(ruleAnalysis.getName(), ruleAnalysis.getIndex(), matchSteps, serializationSteps,
					basicGetEAttribute2EnumerationValues(), basicGetEReference2AssignedRuleValueIndexes(),
					castNeedsDefaultEAttributes,
					basicGetEAttribute2enumerationValue2grammarCardinality(),
					basicGetEReference2ruleValueIndex2grammarCardinality());
				serializationRule.setHelper(this);
				SerializationRule old = SerializationUtils.maybeNull(debugMap.put(this, serializationRule2));		// XXX debugging
				assert old == null;
			}
		}
		return serializationRule2;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature) {
		throw new UnsupportedOperationException();// return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		throw new UnsupportedOperationException();// return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EReference eReference, @NonNull ParserRuleValue parserRuleValue) {
		throw new UnsupportedOperationException();// return null;
	}

	public @Nullable List<@NonNull SubIdiom> getSubIdioms(@NonNull SerializationNode serializationNode) {
		return getSerializationNode2subIdioms().get(serializationNode);
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getVariables() {
		assert hasMatchAnalyses;
		return SerializationUtils.nonNullState(variable2node.keySet());
	}

	public boolean matches(@NonNull SubIdiom subIdiom, @NonNull SerializationNode serializationNode) {
		Locator locator = subIdiom.getOwnedLocator();
		if (locator == null) {
			return false;
		}
		return matches(locator, serializationNode);
	}

	protected boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
		if (locator instanceof ReferredLocator) {
			locator = IdiomsUtils.getOwnedLocator(IdiomsUtils.getLocatorDeclaration((ReferredLocator)locator));
		}
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
		return locatorHelper.matches(locator, serializationNode, this);
	}

	private @NonNull GrammarCardinality refineGrammarCardinality(@NonNull GrammarCardinality netGrammarCardinality, @Nullable GrammarCardinality oldGrammarCardinality) {
		if (oldGrammarCardinality == null) {
			return netGrammarCardinality;
		}
		boolean newMayBeMany = netGrammarCardinality.mayBeMany();
		boolean newMayBeZero = netGrammarCardinality.mayBeZero();
		boolean oldMayBeMany = oldGrammarCardinality.mayBeMany();
		boolean oldMayBeZero = oldGrammarCardinality.mayBeZero();
		if (!oldMayBeZero) {
			newMayBeZero = false;
		}
		if (oldMayBeMany) {
			newMayBeMany = true;
		}
		return newMayBeMany
			? newMayBeZero ? GrammarCardinality.ZERO_OR_MORE : GrammarCardinality.ONE_OR_MORE
			: newMayBeZero ? GrammarCardinality.ZERO_OR_ONE : GrammarCardinality.ONE;
	}

	protected @NonNull EClass refineProducedEClass(@NonNull SerializationNode serializationNode, @NonNull EClass producedEClass) {
		if (serializationNode instanceof AssignedSerializationNode) {
			EClass assignedEClass = ((AssignedSerializationNode)serializationNode).getAssignedEClass();
			if (producedEClass.isSuperTypeOf(assignedEClass)) {
				producedEClass = assignedEClass;
			}
			else {
				assert assignedEClass.isSuperTypeOf(producedEClass);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				producedEClass = refineProducedEClass(nestedSerializationNode, producedEClass);
			}
		}
		return producedEClass;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		rootSerializationNode.toString(s, -1);
	}

	@Override
	public void toMatchTermString(@NonNull StringBuilder s, int depth) {
		if (hasMatchAnalyses) {
			List<@NonNull Integer> variables = new ArrayList<>(variableIndex2node.keySet());
			Collections.sort(variables);
			for (@NonNull Integer variable : variables) {
				SerializationNode serializationNode = SerializationUtils.maybeNull(variableIndex2node.get(variable));
				assert serializationNode != null;
				SerializationUtils.appendIndentation(s, depth);
				s.append(variable);
				s.append(": ");
				serializationNode.toString(s, -1);
			}
			toString(s, depth);
		}
	}

	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		rootSerializationNode.toString(s, -1);
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, -1);
		@SuppressWarnings("null")
		@NonNull String castString = String.valueOf(s);
		return castString;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(ruleAnalysis.getQualifiedName());
		s.append("(");
		EClass producedEClass = getProducedEClass();
		s.append(producedEClass.getEPackage().getName());
		s.append("::");
		s.append(producedEClass.getName());
		s.append("): ");
		rootSerializationNode.toString(s, depth);
	}

/*	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(serializationRuleAnalysis.getName());
		s.append(" : ");
		serializationRuleAnalysis.toRuleString(s);
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(eStructuralFeature2requiredSlotsExpression.values());
		Collections.sort(expressions, SerializationUtils.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression expression : expressions) {
			StringUtil.appendIndentation(s, depth);
			expression.toString(s, depth);
		}
		List<@NonNull Integer> variables = new ArrayList<>(variableIndex2solution.keySet());
		Collections.sort(variables);
		for (@NonNull Integer variable : variables) {
			SerializationNode serializationNode = variableIndex2node.get(variable);
			assert serializationNode != null;
			StringUtil.appendIndentation(s, depth);
			s.append(variable);
			s.append(": ");
			serializationNode.toString(s, -1);
		}
		for (SerializationMatchStep step : steps) {
			StringUtil.appendIndentation(s, depth);
			step.toString(s, depth+1);
			s.append(";");
		}
	} */
}