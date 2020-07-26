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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.NullEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolutionResult;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuntimeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.UnsupportedCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Iterables;

public class BasicSerializationRule extends AbstractSerializationRule
{
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull Map<@NonNull EnumerationValue, org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality>> eFeature2enumerationValue2multiplicativeCardinality = new HashMap<>();
	private @Nullable Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = null;
	private final @NonNull Map<@NonNull SerializationNode, /*@NonNull*/ CardinalityVariable> node2variable;		// XXX debugging @NonNull
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> feature2expression;
	private @Nullable Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution = null;
	private boolean preSerialized = false;
	private @NonNull List<@NonNull CardinalitySolutionResult> results = new ArrayList<>();

	public BasicSerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		super(ruleAnalysis, rootSerializationNode);
		this.node2variable = new HashMap<>();
		this.variable2node = new HashMap<>();
		this.feature2expression = new HashMap<>();
		if ("EnumerationCS".equals(ruleAnalysis.getRuleName())) {
			getClass();	// XXX
		}
		accumulate(rootSerializationNode, MultiplicativeCardinality.ONE);
	}

	private void accumulate(@NonNull SerializationNode serializationNode, @NonNull MultiplicativeCardinality outerMultiplicativeCardinality) {
		MultiplicativeCardinality innerMultiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		MultiplicativeCardinality netMultiplicativeCardinality = MultiplicativeCardinality.max(innerMultiplicativeCardinality, outerMultiplicativeCardinality);
		if (serializationNode instanceof AssignedSerializationNode) {		// XXX bad cast
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			AssignmentAnalysis assignmentAnalysis = assignedSerializationNode.getAssignmentAnalysis();
			EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
			if ("ownedProperties".equals(eStructuralFeature.getName())) {
				getClass();	// XXX
			}
			MultiplicativeCardinality newMultiplicativeCardinality = netMultiplicativeCardinality;
			Map<@NonNull EnumerationValue, org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eFeature2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
			if (enumerationValue2multiplicativeCardinality == null) {
				enumerationValue2multiplicativeCardinality = new HashMap<>();
				eFeature2enumerationValue2multiplicativeCardinality.put(eStructuralFeature, enumerationValue2multiplicativeCardinality);
			}
			MultiplicativeCardinality oldMultiplicativeCardinality = enumerationValue2multiplicativeCardinality.get(enumerationValue);
			if (oldMultiplicativeCardinality != null) {
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
				newMultiplicativeCardinality = newMayBeMany
					? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
					: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
			}
			enumerationValue2multiplicativeCardinality.put(enumerationValue, newMultiplicativeCardinality);
//			assignedSerializationNodes.add(assignedSerializationNode);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			SequenceSerializationNode sequenceSerializationNode = (SequenceSerializationNode)serializationNode;
			for (@NonNull SerializationNode nestedSerializationNode : sequenceSerializationNode.getSerializationNodes()) {
				accumulate(nestedSerializationNode, netMultiplicativeCardinality);
			}
		}
	}

	public void addAssignedNode(@NonNull AssignedSerializationNode assignedSerializationNode, @NonNull EnumerationValue enumerationValue, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		CardinalityExpression cardinalityExpression = feature2expression.get(eStructuralFeature);
		if (cardinalityExpression == null) {
			String name = String.format("E%02d", feature2expression.size());
			assert name != null;;
			cardinalityExpression = new CardinalityExpression(name, eStructuralFeature, NullEnumerationValue.INSTANCE);
			feature2expression.put(eStructuralFeature, cardinalityExpression);
		}
		List<@NonNull CardinalityVariable> variables = new ArrayList<>();
		for (SerializationNode serializationNode : parentStack) {
			CardinalityVariable cardinalityVariable = node2variable.get(serializationNode);
			if (cardinalityVariable != null) {
				variables.add(cardinalityVariable);
			}
		}
		CardinalityVariable cardinalityVariable = node2variable.get(assignedSerializationNode);
		if (cardinalityVariable != null) {
			variables.add(cardinalityVariable);
		}
		if (!enumerationValue.isNull()) {
			CardinalityExpression cardinalityExpression2 = cardinalityExpression.getCardinalityExpression(ruleAnalysis.getGrammarAnalysis(), enumerationValue);
			cardinalityExpression2.addMultiplicityProduct(variables);
		}
		else {
			cardinalityExpression.addMultiplicityProduct(variables);
		}
	}

	public void addSerializedNode(@NonNull SerializationNode serializationNode, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		MultiplicativeCardinality multiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		String name = String.format("C%02d", variable2node.size());
		assert name != null;
		if (!multiplicativeCardinality.isConstant()) {
			CardinalityVariable cardinalityVariable = new CardinalityVariable(name, multiplicativeCardinality);
			CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
			assert old2 == null;
			SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
			assert old3 == null;
		}
	/*	MultiplicativeCardinality subMultiplicativeCardinality = multiplicativeCardinality.mayBeMany() ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ZERO_OR_ONE;
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			for (@NonNull String value : ((AlternativeAssignedKeywordsSerializationNode)serializationNode).getValueOrValues()) {
				String name2 = String.format("C%02d", variable2node.size());
				assert name2 != null;
				CardinalityVariable cardinalitySubVariable = new CardinalityVariable(name2, subMultiplicativeCardinality);
				SerializationNode old4 = variable2node.put(cardinalitySubVariable, serializationNode);
				assert old4 == null;
			}
		} */
	}

	public void addSolution(@NonNull CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution) {
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		assert variable2solution2 != null;
		boolean isAssigned = true;
		for (@NonNull CardinalitySolutionResult result : results) {
			if (result.getCardinalityVariable() == cardinalityVariable) {
				isAssigned = false;
				break;
			}
		}
		results.add(new CardinalitySolutionResult(cardinalityVariable, cardinalitySolution, isAssigned));
		if (isAssigned) {
			variable2solution2.put(cardinalityVariable, cardinalitySolution);
		}
	}

	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable variable) {
		assert variable2solution != null;
		return variable2solution.get(variable);
	}

	public @Nullable Map<@NonNull CardinalityVariable, @NonNull Integer> computeActualCardinalities(@NonNull UserSlotsAnalysis slotsAnalysis) {
		getPreSerializer();
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		assert variable2solution2 != null;
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = new HashMap<>();
		for (@NonNull CardinalitySolutionResult result : results) {
			CardinalityVariable cardinalityVariable = result.getCardinalityVariable();
			CardinalitySolution solution = result.getCardinalitySolution();
			Integer newIntegerSolution = solution.basicGetIntegerSolution(slotsAnalysis, variable2value);
			if (newIntegerSolution == null) {
				throw new UnsupportedOperationException();
			}
			if (result.isAssigned()) {
				variable2value.put(cardinalityVariable, newIntegerSolution);
			}
			else {
				Integer oldIntegerSolution = variable2value.get(cardinalityVariable);
				if (oldIntegerSolution == null) {
					throw new IllegalStateException();
				}
				if (oldIntegerSolution != newIntegerSolution) {
					return null;
				}
			}
		}
		//
		//	Evaluate the expressions to determine the required size of each slot.
		//
		for (@NonNull EStructuralFeature eStructuralFeature : feature2expression.keySet()) {
			CardinalityExpression expression = feature2expression.get(eStructuralFeature);
			assert expression != null;
			Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> value2valueCardinalityExpression = expression.getEnumerationValue2cardinalityExpression();
			if (value2valueCardinalityExpression != null) {
				for (Entry<@NonNull EnumerationValue, @NonNull CardinalityExpression> entry : value2valueCardinalityExpression.entrySet()) {
					EnumerationValue value = entry.getKey();
					CardinalityExpression nestedExpression = entry.getValue();
					int requiredCount = nestedExpression.solve(variable2value);
					int actualCount = CardinalityExpression.getSize(slotsAnalysis, eStructuralFeature, value);
					if (requiredCount != actualCount) {
						return null;
					}
				}
			}
			else {
				assert expression.getEnumerationValue().isNull();
				int requiredCount = expression.solve(variable2value);
				int actualCount = CardinalityExpression.getSize(slotsAnalysis, eStructuralFeature, NullEnumerationValue.INSTANCE);
				if (requiredCount != actualCount) {
					return null;
				}
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
		return variable2value;
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
			Iterable<@NonNull CardinalityVariable> unsolvedVariables = expression.getUnsolvedVariables(this);
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

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return this;
	}

	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return eFeature2enumerationValue2multiplicativeCardinality.keySet();
	}

	private void getIdiomMatches(@NonNull SerializationNode outerSerializationNode, @NonNull Idiom @NonNull [] idioms,
			@Nullable IdiomMatch @NonNull [] idiomMatches) {
		for (int idiomIndex = 0; idiomIndex < idioms.length; idiomIndex++) {
			IdiomMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				Idiom idiom = idioms[idiomIndex];
				idiomMatches[idiomIndex] = idiom.firstMatch(outerSerializationNode, this);
			}
			else {
				idiomMatch.nextMatch(outerSerializationNode, this);
			}
		}
		if (outerSerializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode innerSerializationNode : ((SequenceSerializationNode)outerSerializationNode).getSerializationNodes()) {
				getIdiomMatches(innerSerializationNode, idioms, idiomMatches);
			}
		}
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EnumerationValue, org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eFeature2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
		if (enumerationValue2multiplicativeCardinality == null) {
			return null;
		}
		return enumerationValue2multiplicativeCardinality.get(enumerationValue);
	}

	private void getPreSerializer() {
		assert preSerialized;
//		if (!preSerialized) {
//			preSerialize2();
//			preSerialized = true;
//		}
	}

	public @NonNull SubIdiom getSubIdiom(@NonNull SerializationNode serializationNode) {
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom2 = serializationNode2subIdiom;
		if (serializationNode2subIdiom2 == null) {
			serializationNode2subIdiom = serializationNode2subIdiom2 = getSerializationNode2subIdioms(Idiom.IDIOMS);
		}
		SubIdiom subIdiom = serializationNode2subIdiom2.get(serializationNode);
		return subIdiom != null ? subIdiom : SubIdiom.VALUE;
	}

	private @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> getSerializationNode2subIdioms(@NonNull Idiom @NonNull [] idioms) {
		//
		//	Locate the matches for each idiom.
		//
		@Nullable IdiomMatch @NonNull [] idiomMatches = new @Nullable IdiomMatch[idioms.length];
		getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
		//
		//	Install the subdioms for each first full idom match.
		//
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = new HashMap<>();
		for (@Nullable IdiomMatch idiomMatch : idiomMatches) {
			if (idiomMatch != null) {
				idiomMatch.installIn(serializationNode2subIdiom);
			}
		}
		return serializationNode2subIdiom;
	}

	public @NonNull CardinalityVariable getVariable(@NonNull SerializationNode serializationNode) {
		getPreSerializer();
		return ClassUtil.nonNullState(node2variable.get(serializationNode));
	}

	public boolean needsDefault(@NonNull EStructuralFeature eStructuralFeature) {
		getPreSerializer();
		CardinalityExpression expression = feature2expression.get(eStructuralFeature);
		if (expression == null) {
			return false;
		}
		return expression.isOne();
	}

	public void preSerialize() {
		if (preSerialized) {
			return;
		}
		preSerialized = true;
		if ("OCLinEcore::InvariantConstraintCS".equals(ruleAnalysis.getName())) {
			getClass();	// XXX debugging
		}
		//
		//	Traverse the chosen serialization tree path to trigger addAssignedNode/addSerializedNode call-backs to determine the
		//	cardinality variables and expressions to be solved to characterize the serialization.
		//
		rootSerializationNode.preSerialize(this, new Stack<@NonNull SerializationNode>());
		//
		//	Prepare to restructure the variables/expressions as solutions.
		//
		Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
		assert variable2solution2 == null;
		variable2solution = variable2solution2 = new HashMap<>();
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
		List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
		Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
		//
		//	Confirm that variables with a "1" solution were skipped.
		//
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
		}
		int oldSize;
		//
		//	Eliminate expressions that involve no unresolved variables or which provide alinear solution for a single variable.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.solveTrivial(this)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize);
		//
		//	Eliminate expressions that involve no unresolved variables.
		//	assign 0/1 solutions for all optional cardinalities that are common factors to all other products.
		//		e.g. |F| = C01 + C01 * C02 can be solved as C02 = !F| -1 if C01 is optional.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
			//	if (residualExpression.solveForNoVariables(this)) {
			//		residualExpressions.remove(i);
			//	}
			//	else {
					residualExpression.solveForBooleanCommonFactors(this);
			//	}
			}
		} while (residualExpressions.size() < oldSize);
		//
		//	Assign 0/1 solutions for all variables involved in a linear equation in the light of other solutions.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.solveForConstants(this)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize);
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
					if (residualExpression.solveForPseudoBooleanFactors(this)) {
						// ok
					}
					else if (residualExpression.solveForRedundantProducts(this)) {
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
			if (!variable2solution2.containsKey(variable)) {
				if (residualExpressions.isEmpty()) {
					addSolution(variable, new IntegerCardinalitySolution(variable.mayBeNone() ? 0 : 1));
				}
				else {
					addSolution(variable, new UnsupportedCardinalitySolution());
				}
			}
		}
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		if (preSerialized) {
			List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
			Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityVariable variable : variables) {
				SerializationNode serializationNode = variable2node.get(variable);
				assert serializationNode != null;
				StringUtil.appendIndentation(s, depth, "  ");
				s.append("- ");
				s.append(variable);
				s.append(": ");
				serializationNode.toString(s, -1);
			}
			List<@NonNull CardinalityExpression> expressions = new ArrayList<>(feature2expression.values());
			Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityExpression expression : expressions) {
				StringUtil.appendIndentation(s, depth, "  ");
				s.append("- ");
				expression.toString(s, depth);
			}
			Map<@NonNull CardinalityVariable, @NonNull CardinalitySolution> variable2solution2 = variable2solution;
			if (variable2solution2 != null) {
				for (@NonNull CardinalityVariable variable : variables) {	// XXX
					CardinalitySolution solution = variable2solution2.get(variable);
					StringUtil.appendIndentation(s, depth, "  ");
					s.append("- ");
					s.append(variable);
					s.append(" = ");
					s.append(solution);
				}
			}
			for (@NonNull CardinalitySolutionResult result : results) {
				StringUtil.appendIndentation(s, depth, "  ");
				result.toString(s, 1);
			}
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
	//	rootSerializationNode.toString(s, depth);
	/*	boolean isFirst = true;
		for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
			if (!isFirst) {
				s.append(" & ");
			}
			assignedSerializationNode.toString(s, depth);
			isFirst = false;
		} */
		s.append(ruleAnalysis.getName());
		s.append(": ");
		EClass producedEClass = getProducedEClass();
		s.append(producedEClass.getEPackage().getName());
		s.append("::");
		s.append(producedEClass.getName());
		s.append(" ");
	//	if (preSerializer != null) {
	//		preSerializer.toString(s, depth);
	//	}
	//	else {
			rootSerializationNode.toString(s, depth);
	//	}
	}
}