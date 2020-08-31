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
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarRuleVector;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A StaticRuleMatch accumulates the variables and expressions that determine the cardinalities of the various SerializationRule terms.
 */
public class StaticRuleMatch extends RTStaticRuleMatch
{
	/**
	 * The rule for which this is the static analysis.
	 */
	protected final @NonNull SerializationRuleAnalysis serializationRuleAnalysis;

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

	public StaticRuleMatch(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		this.serializationRuleAnalysis = serializationRuleAnalysis;
	}

	/**
	 * Create/update the sum-of-products expression for the feature assigned by assignedSerializationNode to include
	 * a product for assignedSerializationNode's cardinality variable and for all surrounding sequences in outerVariables.
	 */
	protected void analyzeAssignment(@NonNull AssignedSerializationNode assignedSerializationNode, @NonNull Iterable<@NonNull CardinalityVariable> cardinalityVariables,
			@NonNull MultiplicativeCardinality netMultiplicativeCardinality) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		//
		//	Accumulate enumerated attributes
		//
		CardinalityExpression cardinalityExpression = eStructuralFeature2requiredSlotsExpression.get(eStructuralFeature);
		if (eStructuralFeature instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute)eStructuralFeature;
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			serializationRuleAnalysis.analyzeAssignment(eAttribute, enumerationValue, netMultiplicativeCardinality);
		/*	Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality>> eAttribute2enumerationValue2multiplicativeCardinality2 = eAttribute2enumerationValue2multiplicativeCardinality;
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
			enumerationValue2multiplicativeCardinality.put(enumerationValue, newMultiplicativeCardinality); */
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", eStructuralFeature2requiredSlotsExpression.size());
				assert name != null;;
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
				GrammarAnalysis grammarAnalysis = serializationRuleAnalysis.getRuleAnalysis().getGrammarAnalysis();
				cardinalityExpression = cardinalityExpression.getCardinalityExpression(grammarAnalysis, enumerationValue);
			}
		}
		//
		//	Accumulate discriminated references
		//
		else {
			EReference eReference = (EReference)eStructuralFeature;
			@NonNull Integer @Nullable [] ruleIndexes = assignedSerializationNode.getAssignedRuleIndexes();
			serializationRuleAnalysis.analyzeAssignment(eReference, ruleIndexes, netMultiplicativeCardinality);
		/*	Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull MultiplicativeCardinality>> eReference2ruleAnalysis2multiplicativeCardinality2 = eReference2ruleAnalysis2multiplicativeCardinality;
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
			} */
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			cardinalityExpression = eStructuralFeature2requiredSlotsExpression.get(eStructuralFeature);
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

	public void analyzeSerialization() {
		analyzeSerialization(serializationRuleAnalysis.getRootSerializationNode(), new Stack<@NonNull CardinalityVariable>(), MultiplicativeCardinality.ONE);
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
				@NonNull Integer @Nullable [] ruleIndexes = serializationNode instanceof AssignedSerializationNode ? ((AssignedSerializationNode)serializationNode).getAssignedRuleIndexes() : null;
				cardinalityVariable = new CardinalityVariable(index, name, ruleIndexes != null ? new GrammarRuleVector(ruleIndexes) : null, multiplicativeCardinality);
				CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
				assert old2 == null;
				SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
				assert old3 == null;
				SerializationNode old4 = variableIndex2node.put(cardinalityVariable.getIndex(), serializationNode);
				assert old4 == null;
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
		serializationRuleAnalysis.analyzeSolution(steps);
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
		Collections.sort(residualExpressions, NameUtil.NAMEABLE_COMPARATOR);
		List<@NonNull CardinalityVariable> variables = Lists.newArrayList(serializationRuleAnalysis.getVariables());
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
					CardinalityExpression residualExpression = unresolvedExpressions.iterator().next();
					/*if (residualExpression.analyzeMayBeZeroCommonFactors(this, true)) {
						// ok
					}
					else*/ if (residualExpression.analyzeRedundantProducts(this)) {
						// ok
					}
					else {
						RuntimeCardinalitySolution runtimeSolution = new RuntimeCardinalitySolution(unsolvedVariableGroup, unresolvedExpressions);
						for (@NonNull Integer unresolvedVariable : unsolvedVariableGroup) {
							addSolution(unresolvedVariable, runtimeSolution);
						}
					}
				}
				else {
					//
					//	assign run-time search solution to remaining expressions.
					//
					RuntimeCardinalitySolution runtimeSolution = new RuntimeCardinalitySolution(unsolvedVariableGroup, unresolvedExpressions);
					for (@NonNull Integer unresolvedVariable : unsolvedVariableGroup) {
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
					addSolution(variable.getIndex(), new IntegerCardinalitySolution(variable.mayBeNone() ? 0 : 1));
				}
				else {
					addSolution(variable.getIndex(), new UnsupportedCardinalitySolution());
				}
			}
		}
	}

	/**
 * Return all the expressions that use any of variableGroup.
 */
protected @NonNull Iterable<@NonNull CardinalityExpression> computeExpressions(@NonNull Set<@NonNull Integer> variableGroup,
		@NonNull Map<@NonNull Integer, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions) {
	Set<@NonNull CardinalityExpression> unresolvedExpressions = new HashSet<>();
	for (@NonNull Integer variable : variableGroup) {
		Set<@NonNull CardinalityExpression> expressions = variable2expressions.get(variable);
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
					Set<@NonNull Integer> variables = expression2unsolvedVariables.get(expression);
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
			Set<@NonNull Integer> unsolvedVariables = unsolvedVariable2unresolvedVariables.get(workVariable);
			if (unsolvedVariables == null) {
				unsolvedVariables = new HashSet<>();
				unsolvedVariable2unresolvedVariables.put(workVariable, unsolvedVariables);
				unsolvedVariables.add(workVariable);
				Set<@NonNull CardinalityExpression> expressions = unsolvedVariable2expressions.get(workVariable);
				assert expressions != null;
				for (@NonNull CardinalityExpression expression : expressions) {
					Set<@NonNull Integer> moreUnsolvedVariables = expression2unsolvedVariables.get(expression);
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
			Set<@NonNull Integer> variables = expression2variables.get(expression);
			assert variables != null;
			for (@NonNull Integer variable : variables) {
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

	@Override
	protected @NonNull DynamicRuleMatch createDynamicRuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis) {
		assert slotsAnalysis.basicGetDynamicRuleMatch(this) == null;
		List<@NonNull CardinalitySolutionStep> matchStepsList = getSteps();
		@NonNull CardinalitySolutionStep [] matchStepsArray = matchStepsList.toArray(new @NonNull CardinalitySolutionStep[matchStepsList.size()]);
		SerializationRule serializationRule = getSerializationRuleAnalysis().getRuntime();
		DynamicRuleMatch dynamicRuleMatch = new DynamicRuleMatch(slotsAnalysis, serializationRule, matchStepsArray, serializationRule.getStaticSegments(), this);
		slotsAnalysis.addDynamicRuleMatch(dynamicRuleMatch);
		return dynamicRuleMatch;
	}

	public int getCardinalityVariableIndex(@NonNull SerializationNode serializationNode) {
		CardinalityVariable cardinalityVariable = node2variable.get(serializationNode);
		return cardinalityVariable != null ? cardinalityVariable.getIndex() : -1;
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getCardinalityVariables() {
		return variable2node.keySet();
	}

	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis() {
		return serializationRuleAnalysis;
	}

	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		List<@NonNull Integer> variables = new ArrayList<>(variableIndex2node.keySet());
		Collections.sort(variables);
		for (@NonNull Integer variable : variables) {
			SerializationNode serializationNode = variableIndex2node.get(variable);
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
		s.append(serializationRuleAnalysis.getName());
		s.append(" : ");
		serializationRuleAnalysis.toRuleString(s);
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(eStructuralFeature2requiredSlotsExpression.values());
		Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
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
		for (CardinalitySolutionStep step : steps) {
			StringUtil.appendIndentation(s, depth);
			step.toString(s, depth+1);
			s.append(";");
		}
	}
}