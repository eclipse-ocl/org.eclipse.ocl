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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A StaticRuleMatch accumulates the variables and expressions that determine the cardinalities of the various SerializationRule terms.
 */
public class StaticRuleMatch implements RuleMatch
{
	/**
	 * The rule for which this is the static analysis.
	 */
	protected final @NonNull BasicSerializationRule serializationRule;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality>> eAttribute2enumerationValue2multiplicativeCardinality = null;

	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality>> eReference2ruleAnalysis2multiplicativeCardinality = null;

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
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node = new HashMap<>();

	/**
	 * The Node which each cardinality variable defines the iteration for; the inverse of node2variable.
	 *
	 * ?? Only used as a debugging convenience ??
	 */
	private final @NonNull List<@NonNull AssignedSerializationNode> assignedSerializationNodes = new ArrayList<>();

	/**
	 * The per-feature expression that (re-)computes the required number of assigned slots from the solved
	 * cardinality variables. This is checked gainst the actual number of slots in an actual user element.
	 */
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression = new HashMap<>();

	/**
	 * The per-variable solution expression that computes the variable's value from the actual number of slots of an actual user element.
	 *
	 * Lazily populated as solutions found.
	 */
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution = new HashMap<>();

	/**
	 * The ordered sequence of assign/check instructions to evaluate at run-time to realize the computation of
	 * each solution for its variable.
	 */
	private final @NonNull List<@NonNull CardinalitySolutionStep> steps = new ArrayList<>();

	public StaticRuleMatch(@NonNull BasicSerializationRule serializationRule) {
		this.serializationRule = serializationRule;
	}

	/**
	 * Accumulate an additional cardinalitySolution expression for a cardinalityVariable.
	 */
	public void addSolution(@Nullable CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
		CardinalitySolutionStep newStep;
		if (cardinalityVariable != null) {
			assert !cardinalityVariable.isOne();
			boolean isAssigned = true;
			for (@NonNull CardinalitySolutionStep step : steps) {
				if (step.isAssignTo(cardinalityVariable)) {
					isAssigned = false;
					break;
				}
			}
			if (isAssigned) {
				newStep = new CardinalitySolutionStep.Assign(cardinalityVariable.getIndex(), cardinalitySolution);
				variable2solution.put(cardinalityVariable, cardinalitySolution);
			}
			else {
				newStep = new CardinalitySolutionStep.ValueCheck(cardinalityVariable.getIndex(), cardinalitySolution);
			}
		}
		else {
			newStep = new CardinalitySolutionStep.Assert(cardinalitySolution);
		}
		steps.add(newStep);
	}

	/**
	 * Create/update the sum-of-products expression for the feature assigned by assignedSerializationNode to include
	 * a product for assignedSerializationNode's cardinality variable and for all surrounding sequences in outerVariables.
	 */
	protected void analyzeAssignment(@NonNull AssignedSerializationNode assignedSerializationNode, @NonNull Iterable<@NonNull CardinalityVariable> cardinalityVariables,
			@NonNull MultiplicativeCardinality netMultiplicativeCardinality) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		if ("ownedProperties".equals(eStructuralFeature.getName())) {
			getClass();	// XXX
		}
		//
		//	Accumulate enumerated attributes
		//
		CardinalityExpression cardinalityExpression = feature2expression.get(eStructuralFeature);
		if (eStructuralFeature instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute)eStructuralFeature;
			Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality>> eAttribute2enumerationValue2multiplicativeCardinality2 = eAttribute2enumerationValue2multiplicativeCardinality;
			if (eAttribute2enumerationValue2multiplicativeCardinality2 == null) {
				eAttribute2enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality2 = new HashMap<>();
			}
			Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality2.get(eAttribute);
			if (enumerationValue2multiplicativeCardinality == null) {
				enumerationValue2multiplicativeCardinality = new HashMap<>();
				eAttribute2enumerationValue2multiplicativeCardinality2.put(eAttribute, enumerationValue2multiplicativeCardinality);
			}
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			MultiplicativeCardinality oldMultiplicativeCardinality = enumerationValue2multiplicativeCardinality.get(enumerationValue);
			MultiplicativeCardinality newMultiplicativeCardinality = refineMultiplicativeCardinality(netMultiplicativeCardinality, oldMultiplicativeCardinality);
			enumerationValue2multiplicativeCardinality.put(enumerationValue, newMultiplicativeCardinality);
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", feature2expression.size());
				assert name != null;;
				if (enumerationValue == null) {
					cardinalityExpression = new EStructuralFeatureCardinalityExpression(name, eStructuralFeature);
				}
				else {		// XXX RuleAnalysis
					cardinalityExpression = new EAttributeCardinalityExpression(name, (EAttribute)eStructuralFeature, enumerationValue);
				}
				feature2expression.put(eStructuralFeature, cardinalityExpression);
			}
			//
			//	Add cardinalityVariables as a further product term to the sum of products.
			//
			if (enumerationValue != null) {
				GrammarAnalysis grammarAnalysis = serializationRule.getRuleAnalysis().getGrammarAnalysis();
				cardinalityExpression = cardinalityExpression.getCardinalityExpression(grammarAnalysis, enumerationValue);
			}
		}
		//
		//	Accumulate discriminated references
		//
		else {
			EReference eReference = (EReference)eStructuralFeature;
			Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality>> eReference2ruleAnalysis2multiplicativeCardinality2 = eReference2ruleAnalysis2multiplicativeCardinality;
			if (eReference2ruleAnalysis2multiplicativeCardinality2 == null) {
				eReference2ruleAnalysis2multiplicativeCardinality = eReference2ruleAnalysis2multiplicativeCardinality2 = new HashMap<>();
			}
			Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality> ruleAnalysis2multiplicativeCardinality = eReference2ruleAnalysis2multiplicativeCardinality2.get(eReference);
			if (ruleAnalysis2multiplicativeCardinality == null) {
				ruleAnalysis2multiplicativeCardinality = new HashMap<>();
				eReference2ruleAnalysis2multiplicativeCardinality2.put(eReference, ruleAnalysis2multiplicativeCardinality);
			}
			Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses = assignedSerializationNode.getAssignedRuleAnalyses();
			if (ruleAnalyses != null) {
				for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
					if (ruleAnalysis instanceof ParserRuleAnalysis) {
						MultiplicativeCardinality oldMultiplicativeCardinality = ruleAnalysis2multiplicativeCardinality.get(ruleAnalysis);
						MultiplicativeCardinality newMultiplicativeCardinality = refineMultiplicativeCardinality(netMultiplicativeCardinality, oldMultiplicativeCardinality);
						ruleAnalysis2multiplicativeCardinality.put((ParserRuleAnalysis) ruleAnalysis, newMultiplicativeCardinality);
					}
				}
			}
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			cardinalityExpression = feature2expression.get(eStructuralFeature);
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", feature2expression.size());
				assert name != null;;
				cardinalityExpression = new EStructuralFeatureCardinalityExpression(name, eStructuralFeature);
				feature2expression.put(eStructuralFeature, cardinalityExpression);
			}
		}
		//
		//	Add cardinalityVariables as a further product term to the sum of products.
		//
		cardinalityExpression.addMultiplicityProduct(cardinalityVariables);
	}

	public void analyzeSerialization() {
		if ("EnumerationCS".equals(serializationRule.getRuleAnalysis().getRuleName())) {
			getClass();	// XXX
		}
		analyzeSerialization(serializationRule.getRootSerializationNode(), new Stack<@NonNull CardinalityVariable>(), MultiplicativeCardinality.ONE);
	}
	protected void analyzeSerialization(@NonNull SerializationNode serializationNode, @NonNull Stack<@NonNull CardinalityVariable> cardinalityVariables, @NonNull MultiplicativeCardinality outerMultiplicativeCardinality) {
		//
		//	Allocate a CardinalityVariable for an indeterminate multiplicity.
		//
		CardinalityVariable cardinalityVariable = null;
		if (!serializationNode.isRedundant()) {
			MultiplicativeCardinality multiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
			if (!multiplicativeCardinality.isOne()) {
				int index = variable2node.size();
				String name = String.format("C%02d", index);
				assert name != null;
				Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses = serializationNode instanceof AssignedSerializationNode ? ((AssignedSerializationNode)serializationNode).getAssignedRuleAnalyses() : null;
				cardinalityVariable = new CardinalityVariable(index, name, ruleAnalyses, multiplicativeCardinality);
				CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
				assert old2 == null;
				SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
				assert old3 == null;
			}
		}
		MultiplicativeCardinality innerMultiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		MultiplicativeCardinality netMultiplicativeCardinality = MultiplicativeCardinality.max(innerMultiplicativeCardinality, outerMultiplicativeCardinality);
		//
		//	Create the feature size expressions for an assignment
		//
		if (cardinalityVariable != null) {
			cardinalityVariables.push(cardinalityVariable);
		}
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			assignedSerializationNodes.add(assignedSerializationNode);
			analyzeAssignment(assignedSerializationNode, cardinalityVariables, netMultiplicativeCardinality);
		}
		//
		//	Recurse for sequences
		//
		else if (serializationNode instanceof SequenceSerializationNode) {
			SequenceSerializationNode sequenceSerializationNode = (SequenceSerializationNode)serializationNode;
			for (@NonNull SerializationNode nestedSerializationNode : sequenceSerializationNode.getSerializationNodes()) {
				analyzeSerialization(nestedSerializationNode, cardinalityVariables, netMultiplicativeCardinality);
			}
		}
		if (cardinalityVariable != null) {
			cardinalityVariables.pop();
		}
	}

	public void analyzeSolution() {
		if ("EssentialOCL::ExpCS".equals(serializationRule.getName())) {
			getClass();		// XXX debugging
		}
	/*	for (Map.Entry<@NonNull EStructuralFeature, @NonNull CardinalityExpression> entry : feature2expression.entrySet()) {
			EStructuralFeature eStructuralFeature = entry.getKey();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				CardinalityExpression cardinalityExpression = entry.getValue();
			//	cardinalityExpression.get
				EClass eReferenceType = eReference.getEReferenceType();
			//	steps.add(new CardinalitySolutionStep.TypeCheck(eReference, eReferenceType));
			}
		} */
		if (eReference2ruleAnalysis2multiplicativeCardinality != null) {
			for (Map.Entry<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality>> entry : eReference2ruleAnalysis2multiplicativeCardinality.entrySet()) {
				EReference eReference = entry.getKey();
				if (eReference.isContainment()) {
					Iterable<@Nullable ParserRuleAnalysis> assignedRuleAnalyses = entry.getValue().keySet();		// XXX exclude null
					steps.add(new CardinalitySolutionStep.RuleCheck(eReference, assignedRuleAnalyses));
				}
			}
		}
	/*	for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
			EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				EClass eReferenceType = eReference.getEReferenceType();
				Iterable<@NonNull AbstractRuleAnalysis> assignedRuleAnalyses = assignedSerializationNode.getAssignedRuleAnalyses();
				if (assignedRuleAnalyses != null) {
					if (eReference.isContainment()) {
						steps.add(new CardinalitySolutionStep.RuleCheck(eReference, assignedRuleAnalyses));
					}
					Set<@NonNull EClass> eClasses = null;
					for (@NonNull AbstractRuleAnalysis assignedRuleAnalysis : assignedRuleAnalyses) {
						if (assignedRuleAnalysis instanceof ParserRuleAnalysis) {
							EClass returnedEClass = ((ParserRuleAnalysis)assignedRuleAnalysis).getReturnedEClass();		// XXX sub rule closure
						//	if (returnedEClass != eReferenceType) {
								if (eClasses == null) {
									eClasses = new HashSet<>();
								}
								eClasses.add(returnedEClass);
						//	}
						}
					}
					if ((eClasses != null) && !eClasses.contains(eReferenceType)) {		// Prune derivations
						steps.add(new CardinalitySolutionStep.TypeCheck(eReference, eClasses));
					}
				}
			}
		} */
		//
		//	Prepare to restructure the variables/expressions as solutions.
		//
//		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
//		assert variable2solution2 == null;
//		variable2solution = variable2solution2 = new HashMap<>();
		List<@NonNull CardinalityExpression> residualExpressions = new ArrayList<>();
		for (@NonNull CardinalityExpression expression : feature2expression.values()) {
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
		Collections.sort(residualExpressions, NameUtil.NAMEABLE_COMPARATOR);
		List<@NonNull CardinalityVariable> variables = Lists.newArrayList(serializationRule.getVariables());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
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
				CardinalityExpression residualExpression = residualExpressions.get(i);
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
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeMayBeZeroCommonFactors(this, false)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					CardinalityExpression residualExpression = residualExpressions.get(i);
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
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					CardinalityExpression residualExpression = residualExpressions.get(i);
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
			Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables =
					computeExpression2unsolvedVariables(residualExpressions);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions =
					computeVariable2expressions(expression2unsolvedVariables);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unsolvedVariableGroups =
					computeUnsolvedVariableGroups(unsolvedVariable2expressions, expression2unsolvedVariables);

			for (@NonNull Set<@NonNull CardinalityVariable> unsolvedVariableGroup : new HashSet<>(unsolvedVariable2unsolvedVariableGroups.values())) {
				Iterable<@NonNull CardinalityExpression> unresolvedExpressions = computeExpressions(unsolvedVariableGroup, unsolvedVariable2expressions);
				int size = Iterables.size(unresolvedExpressions);
				if (size == 1) {
					CardinalityExpression residualExpression = unresolvedExpressions.iterator().next();
					/*if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
						// ok
					}
					else*/ if (residualExpression.analyzeRedundantProducts(this)) {
						// ok
					}
					else {
						RuntimeCardinalitySolution runtimeSolution = new RuntimeCardinalitySolution(unsolvedVariableGroup, unresolvedExpressions);
						for (@NonNull CardinalityVariable unresolvedVariable : unsolvedVariableGroup) {
							addSolution(unresolvedVariable, runtimeSolution);
						}
					}
				}
				else {
					//
					//	assign run-time search solution to remaining expressions.
					//
					RuntimeCardinalitySolution runtimeSolution = new RuntimeCardinalitySolution(unsolvedVariableGroup, unresolvedExpressions);
					for (@NonNull CardinalityVariable unresolvedVariable : unsolvedVariableGroup) {
						addSolution(unresolvedVariable, runtimeSolution);
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
			if (basicGetSolution(variable) == null) {
				if (residualExpressions.isEmpty()) {
					addSolution(variable, new IntegerCardinalitySolution(variable.mayBeNone() ? 0 : 1));
				}
				else {
					addSolution(variable, new UnsupportedCardinalitySolution());
				}
			}
		}
	}

	public@Nullable CardinalityVariable basicGetCardinalityVariable(@NonNull SerializationNode serializationNode) {
		return node2variable.get(serializationNode);
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		// throw new IllegalStateException();		// move to caller
		CardinalitySolution solution = variable2solution.get(cardinalityVariable);
		return solution != null ? solution.basicGetIntegerSolution(this) : null;
/*		if (solution instanceof IntegerCardinalitySolution) {
			return ((IntegerCardinalitySolution)solution).getValue();
		}
		if (solution instanceof BooleanCommonFactorCardinalitySolution) {
			return 1;
		}
		if (solution instanceof Iterable) {
			for (Object solutionElement : ((Iterable<?>)solution) ) {
				if (solutionElement instanceof Integer) {
					return ((Integer)solutionElement).intValue();
				}
				if (solutionElement instanceof BooleanCommonFactorCardinalitySolution) {
					return 1;
				}
			}
		}
		return null; */
	}

	@Override
	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return variable2solution.get(cardinalityVariable);
	}

	/**
 * Return all the expressions that use any of variableGroup.
 */
protected @NonNull Iterable<@NonNull CardinalityExpression> computeExpressions(@NonNull Set<@NonNull CardinalityVariable> variableGroup,
		@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions) {
	Set<@NonNull CardinalityExpression> unresolvedExpressions = new HashSet<>();
	for (@NonNull CardinalityVariable variable : variableGroup) {
		Set<@NonNull CardinalityExpression> expressions = variable2expressions.get(variable);
		assert expressions != null;
		unresolvedExpressions.addAll(expressions);
	}
	return unresolvedExpressions;
}

	/**
	 * Return the Map from each of expressions to its variables that lack a solution.
	 */
	protected @NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> computeExpression2unsolvedVariables(
			@NonNull Iterable<@NonNull CardinalityExpression> expressions) {
		Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expressions) {
			Iterable<@NonNull CardinalityVariable> unsolvedVariables = expression.getUnknownVariables(this);
			if (unsolvedVariables != null) {
				for (@NonNull CardinalityVariable variable : unsolvedVariables) {
					Set<@NonNull CardinalityVariable> variables = expression2unsolvedVariables.get(expression);
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
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> computeUnsolvedVariableGroups(
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions,
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unresolvedVariables = new HashMap<>();
		List<@NonNull CardinalityVariable> workVariables = new ArrayList<>(unsolvedVariable2expressions.keySet());
		for (@NonNull CardinalityVariable workVariable : workVariables) {
			Set<@NonNull CardinalityVariable> unsolvedVariables = unsolvedVariable2unresolvedVariables.get(workVariable);
			if (unsolvedVariables == null) {
				unsolvedVariables = new HashSet<>();
				unsolvedVariable2unresolvedVariables.put(workVariable, unsolvedVariables);
				unsolvedVariables.add(workVariable);
				Set<@NonNull CardinalityExpression> expressions = unsolvedVariable2expressions.get(workVariable);
				assert expressions != null;
				for (@NonNull CardinalityExpression expression : expressions) {
					Set<@NonNull CardinalityVariable> moreUnsolvedVariables = expression2unsolvedVariables.get(expression);
					assert moreUnsolvedVariables != null;
					moreUnsolvedVariables.removeAll(unsolvedVariables);
					for (@NonNull CardinalityVariable anotherUnsolvedVariable : moreUnsolvedVariables) {
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
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> computeVariable2expressions(
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2variables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expression2variables.keySet()) {
			Set<@NonNull CardinalityVariable> variables = expression2variables.get(expression);
			assert variables != null;
			for (@NonNull CardinalityVariable variable : variables) {
				Set<@NonNull CardinalityExpression> expressions = variable2expressions.get(variable);
				if (expressions == null) {
					expressions = new HashSet<>();
					variable2expressions.put(variable, expressions);
				}
				expressions.add(expression);
			}
		}
		return variable2expressions;
	}

	public @NonNull CardinalityVariable getCardinalityVariable(@NonNull SerializationNode serializationNode) {
		return ClassUtil.nonNullState(node2variable.get(serializationNode));
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getCardinalityVariables() {
		return variable2node.keySet();
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		if (eAttribute2enumerationValue2multiplicativeCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
			if (enumerationValue2multiplicativeCardinality != null) {
				return enumerationValue2multiplicativeCardinality.get(null);
			}
		}
		if (eReference2ruleAnalysis2multiplicativeCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality> ruleAnalysis2multiplicativeCardinality = eReference2ruleAnalysis2multiplicativeCardinality.get(eStructuralFeature);
			if (ruleAnalysis2multiplicativeCardinality != null) {
				return ruleAnalysis2multiplicativeCardinality.get(null);
			}
		}
		return null;
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		if (eAttribute2enumerationValue2multiplicativeCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality.get(eAttribute);
			if (enumerationValue2multiplicativeCardinality != null) {
				return enumerationValue2multiplicativeCardinality.get(enumerationValue);
			}
		}
		return null;
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		if (eReference2ruleAnalysis2multiplicativeCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality> ruleAnalysis2multiplicativeCardinality = eReference2ruleAnalysis2multiplicativeCardinality.get(eReference);
			if (ruleAnalysis2multiplicativeCardinality != null) {
				return ruleAnalysis2multiplicativeCardinality.get(ruleAnalysis);
			}
		}
		return null;
	}

	@Override
	public @NonNull BasicSerializationRule getSerializationRule() {
		return serializationRule;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature) {
		return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		return null;
	}

	public @NonNull List<@NonNull CardinalitySolutionStep> getSteps() {
		return steps;
	}

	public boolean needsDefault(@NonNull EStructuralFeature eStructuralFeature) {
		CardinalityExpression expression = feature2expression.get(eStructuralFeature);
		if (expression == null) {
			return false;
		}
		return expression.isOne();
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		DynamicRuleMatch dynamicRuleMatch = slotsAnalysis.basicGetDynamicRuleMatch(this); // new DynamicRuleMatch(this, slotsAnalysis);
		if (dynamicRuleMatch == null) {
			dynamicRuleMatch = slotsAnalysis.createDynamicRuleMatch(this);
			if (!dynamicRuleMatch.analyze()) {
				return null;
			}
			//
			//	Evaluate the expressions to determine the required size of each slot.
			//
			for (@NonNull EStructuralFeature eStructuralFeature : feature2expression.keySet()) {
				CardinalityExpression expression = feature2expression.get(eStructuralFeature);
				assert expression != null;
				if (!expression.checkSize(dynamicRuleMatch)) {
					return null;
				}
			}
			//
			//	Check that no 'unused' features are used.
			//
			for (@NonNull EStructuralFeature eStructuralFeature : slotsAnalysis.getEStructuralFeatures()) {
				if (!feature2expression.containsKey(eStructuralFeature)) {
					UserSlotAnalysis object = slotsAnalysis.getSlotAnalysis(eStructuralFeature);
					if (!object.isCounted() || (object.asCounted() != 0)) {
						return null;
					}
				}
			}
			dynamicRuleMatch.setChecked();
		}
		else {
			if (!dynamicRuleMatch.isChecked()) {
				return null;
			}
		}
		return dynamicRuleMatch;
	}

	private @NonNull MultiplicativeCardinality refineMultiplicativeCardinality(@NonNull MultiplicativeCardinality netMultiplicativeCardinality, @Nullable MultiplicativeCardinality oldMultiplicativeCardinality) {
		if (oldMultiplicativeCardinality == null) {
			return netMultiplicativeCardinality;
		}
		boolean newMayBeMany = netMultiplicativeCardinality.mayBeMany();
		boolean newMayBeZero = netMultiplicativeCardinality.mayBeZero();
		boolean oldMayBeMany = oldMultiplicativeCardinality.mayBeMany();
		boolean oldMayBeZero = oldMultiplicativeCardinality.mayBeZero();
		if (!oldMayBeZero) {
			newMayBeZero = false;
		}
		if (oldMayBeMany) {
			newMayBeMany = true;
		}
		return newMayBeMany
			? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
			: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
	}

	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {
			SerializationNode serializationNode = variable2node.get(variable);
			assert serializationNode != null;
			StringUtil.appendIndentation(s, depth);
			s.append(variable);
			s.append(": ");
			serializationNode.toString(s, -1);
		}
		toString(s, depth);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
	//	s.append(serializationRule.getName());
	//	s.append(" : ");
	//	serializationRule.toRuleString(s);
		toString(s, 1);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(serializationRule.getName());
		s.append(" : ");
		serializationRule.toRuleString(s);
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression expression : expressions) {
			StringUtil.appendIndentation(s, depth);
			expression.toString(s, depth);
		}
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2solution.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityVariable variable : variables) {
			SerializationNode serializationNode = variable2node.get(variable);
			assert serializationNode != null;
			StringUtil.appendIndentation(s, depth);
			s.append(variable);
			s.append(": ");
			serializationNode.toString(s, -1);
		}
		for (CardinalitySolutionStep step : steps) {
			StringUtil.appendIndentation(s, depth);
			step.toString(s, depth+1);
			s.append(";");
		}
	}
}