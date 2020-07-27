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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.common.collect.Iterables;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class CardinalityExpression implements Nameable
{
	protected final @NonNull String name;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull EnumerationValue enumerationValue;
	private final @NonNull List<@NonNull List<@NonNull CardinalityVariable>> sumOfProducts = new ArrayList<>();
	private @Nullable Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> enumerationValue2cardinalityExpression = null;

	public CardinalityExpression(@NonNull String name, @NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		this.name = name;
		this.eStructuralFeature = eStructuralFeature;
		this.enumerationValue = enumerationValue;
	}

	public void addMultiplicityProduct(@NonNull List<@NonNull CardinalityVariable> variables) {
		sumOfProducts.add(variables);
	}

	protected void appendSumOfProducts(@NonNull StringBuilder s) {
		boolean isFirst1 = true;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			if (!isFirst1) {
				s.append(" + ");
			}
			boolean gotOne = false;
			boolean isFirst2 = true;
			for (@NonNull CardinalityVariable variable : products) {
				if (!variable.isOne()) {
					if (!isFirst2) {
						s.append(" * ");
					}
					assert !variable.isOne();
					s.append(variable);
					gotOne = true;
					isFirst2 = false;
				}
			}
			if (!gotOne) {
				s.append("1");
			}
			isFirst1 = false;
		}
	}

/*	public void gatherRuntimeProblems(@NonNull PreSerializer preSerializer,
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unresolvedVariable2expressions,
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unresolvedVariables) {
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				Object solution = preSerializer.getSolution(variable);
				if ((solution == null) || ((solution instanceof Solution) && ((Solution)solution).isRuntime())) {
					Set<@NonNull CardinalityExpression> expressions = unresolvedVariable2expressions.get(variable);
					if (expressions == null) {
						expressions = new HashSet<>();
						unresolvedVariable2expressions.put(variable, expressions);
					}
					expressions.add(this);
					Set<@NonNull CardinalityVariable> unresolvedVariables = expression2unresolvedVariables.get(this);
					if (unresolvedVariables == null) {
						unresolvedVariables = new HashSet<>();
						expression2unresolvedVariables.put(this, unresolvedVariables);
					}
					unresolvedVariables.add(variable);
				}
			}
		}
	} */

	/**
	 * Return the variables commn to all products that lack a solution, or null if none.
	 */
	private @Nullable Set<@NonNull CardinalityVariable> computeUnsolvedCommonFactors(@NonNull StaticRuleMatch ruleMatch) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			Object resolution = resolveProduct(ruleMatch, products);
			if (resolution instanceof Integer) {
				//
			}
			else if (resolution instanceof Set){
				@SuppressWarnings("unchecked")
				Set<@NonNull CardinalityVariable> resolutions = (Set<@NonNull CardinalityVariable>)resolution;
				if (intersection == null) {
					intersection = new HashSet<>(resolutions);
				}
				else {
					intersection.retainAll(resolutions);
				}
			}
		}
		return (intersection != null) && !intersection.isEmpty() ? intersection : null;
	}

	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> enumerationValue2cardinalityExpression2 = enumerationValue2cardinalityExpression;
		if (enumerationValue2cardinalityExpression2 == null) {
			enumerationValue2cardinalityExpression = enumerationValue2cardinalityExpression2 = new HashMap<>();
		}
		CardinalityExpression cardinalityExpression = enumerationValue2cardinalityExpression2.get(enumerationValue);
		if (cardinalityExpression == null) {
			grammarAnalysis.addEnumeration((EAttribute)eStructuralFeature, enumerationValue);
			String subName = name + "." + enumerationValue2cardinalityExpression2.size();
			cardinalityExpression = new CardinalityExpression(subName, eStructuralFeature, enumerationValue);
			enumerationValue2cardinalityExpression2.put(enumerationValue, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
		return enumerationValue2cardinalityExpression != null ? enumerationValue2cardinalityExpression.values() : null;
	}

	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	public @Nullable Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> getEnumerationValue2cardinalityExpression() {
		return enumerationValue2cardinalityExpression;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @Nullable Iterable<@NonNull CardinalityVariable> getUnsolvedVariables(@NonNull StaticRuleMatch ruleMatch) {
		List<@NonNull CardinalityVariable> unsolvedVariables = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
				if ((solution == null) || solution.isRuntime()) {
					if (unsolvedVariables == null) {
						unsolvedVariables = new ArrayList<>();
					}
					unsolvedVariables.add(variable);
				}
			}
		}
		return unsolvedVariables;
	}

	public boolean isOne() {
		return (sumOfProducts.size() == 1) && (sumOfProducts.get(0).size() == 0);
	}

	/**
	 * Simplify a product term returning
	 * - Integer for a constant
	 * - CardinalityVariable for a scaled variable
	 * - Set(CardinalityVariable) for a scaled product of non-quadratic variable
	 * - null for a quadratic variable
	 */
	public Object resolveProduct(@NonNull StaticRuleMatch ruleMatch, @NonNull List<@NonNull CardinalityVariable> product) {
		Set<@NonNull CardinalityVariable> productVariables = null;
		int constantProduct = 1;
		for (@NonNull CardinalityVariable variable : product) {
			Integer integerSolution = ruleMatch.basicGetIntegerSolution(variable);
		//	Integer integer = zzgetIntegerSolution(solution);
			if (integerSolution != null) {
				constantProduct *= integerSolution;
			}
			else {
				if (productVariables == null) {
					productVariables = new HashSet<>();
				}
				productVariables.add(variable);
			}
		}
		if (productVariables != null) {
			return productVariables;
		}
		else {
			return constantProduct;
		}
	}

	public int solve(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		int sum = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			int product = 1;
			for (@NonNull CardinalityVariable variable : products) {
				Integer value = dynamicRuleMatch.basicGetIntegerSolution(variable);
				int intValue = value != null ? value.intValue() : 1;
				product *= intValue;
			}
			sum += product;
		}
		return sum;
	}

	public void solveAtRuntime(@NonNull SerializationRule serializationRule) {
	//	Solution runtimeSolution = new RuntimeSolution(this, unresolvedVariables);
	//	for (@NonNull CardinalityVariable variable : unresolvedVariables) {
	//		preSerializer.addSolution(variable, runtimeSolution);
	//	}
	}

	public boolean solveForBooleanCommonFactors(@NonNull StaticRuleMatch ruleMatch) {
		Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(ruleMatch);
		if (intersection == null) {
			return false;
		}
		int sum = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			Object resolution = resolveProduct(ruleMatch, products);
			if (resolution instanceof Integer) {
				sum += ((Integer)resolution).intValue();
			}
		}
		for (@NonNull CardinalityVariable cardinalityVariable : intersection) {
			if (!cardinalityVariable.mayBeMany()) {
				assert cardinalityVariable.mayBeNone();
				CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
				solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
				ruleMatch.addSolution(cardinalityVariable, solution);
			}
		}
		return true;
	}

	public boolean solveForConstants(@NonNull StaticRuleMatch ruleMatch) {
		CardinalityVariable sumVariable = null;
		int sum = 0;
		int factor = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			CardinalityVariable productVariable = null;
			int product = 1;
			for (@NonNull CardinalityVariable variable : products) {
				Integer integerSolution = ruleMatch.basicGetIntegerSolution(variable);
				if (integerSolution != null) {
					product *= integerSolution.intValue();
				}
				else {
					if ((sumVariable != null) && (sumVariable != variable)) {
						return false;		// Cannot solve 2 variables here
					}
					sumVariable = variable;
					if (productVariable != null) {
						return false;		// Cannot solve quadratic variables here
					}
					productVariable = variable;
				}
			}
			if (productVariable == null) {
				sum += product;
			}
			else {
				factor += product;
			}
		}
		if (sumVariable == null) {
			return true;
		}
		CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
		if (sum != 0) {
			solution = new MinusCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
		}
		if (factor != 1) {
			solution = new DivideCardinalitySolution(solution, new IntegerCardinalitySolution(factor));
		}
		ruleMatch.addSolution(sumVariable, solution);
		return true;
	}

	public boolean solveForNoVariables(@NonNull StaticRuleMatch ruleMatch) {
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
				if (!(solution instanceof IntegerCardinalitySolution)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean solveForPseudoBooleanFactors(@NonNull StaticRuleMatch ruleMatch) {
		Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(ruleMatch);
		if (intersection == null) {
			return false;
		}
		int sum = 0;
		CardinalityVariable productVariable = null;
		for (@NonNull List<@NonNull CardinalityVariable> product : sumOfProducts) {
			int constantProduct = 1;
			for (@NonNull CardinalityVariable variable : product) {
				if (!Iterables.contains(intersection, variable)) {
					Integer integerSolution = ruleMatch.basicGetIntegerSolution(variable);
					if (integerSolution != null) {
						constantProduct *= integerSolution;
					}
					else if (productVariable != null) {
						return false;
					}
					else {
						productVariable = variable;
					}
				}
			}
			if ((productVariable == null) || !product.contains(productVariable)) {
				sum += constantProduct;
			}
		}
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				if (variable == productVariable) {
					CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
					if (sum != 0) {
						solution = new MinusCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
					}
					ruleMatch.addSolution(variable, solution);
				}
				else {
					assert Iterables.contains(intersection, variable);
					CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
					solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
					ruleMatch.addSolution(variable, solution);
				}
			}
		}
		return true;
	}

	public boolean solveForRedundantProducts(@NonNull StaticRuleMatch ruleMatch) {
		Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(ruleMatch);
		if (intersection != null) {
			return false;
		}
		List<@NonNull CardinalityVariable> manyProducts = null;
		CardinalityVariable manyVariable = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			boolean mayBeNone = false;
			for (@NonNull CardinalityVariable variable : products) {
				if (variable.mayBeNone()) {
					mayBeNone = true;
				}
				if ((manyVariable == null) && variable.mayBeMany()) {
					manyVariable = variable;
					manyProducts = products;
				}
			}
			if (!mayBeNone) {
				return false;
			}
		}
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				if (ruleMatch.basicGetSolution(variable) == null) {
					if (products != manyProducts) {
						ruleMatch.addSolution(variable, new IntegerCardinalitySolution(0));
					}
					else if (variable == manyVariable) {
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
						ruleMatch.addSolution(variable, solution);
					}
					else {
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
						solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(0));
						ruleMatch.addSolution(variable, solution);
					}
				}
			}
		}
		return true;
	}

	public boolean solveTrivial(@NonNull StaticRuleMatch ruleMatch) {
		CardinalityVariable trivialVariable = null;
		int scaleFactor = 1;
		int bias = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			int product = 1;
			boolean hasTrivialVariable = false;
			for (@NonNull CardinalityVariable variable : products) {
				CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
				if (solution instanceof IntegerCardinalitySolution) {
					product *= ((IntegerCardinalitySolution)solution).getValue();
				}			// FIXME a solved variable can be a trivial divisor
				else if (trivialVariable == null) {
					trivialVariable = variable;
					hasTrivialVariable = true;
				}
				else if (variable != trivialVariable) {
					return false;			// A second variable is not trivial
				}
				else if (hasTrivialVariable) {
					return false;			// A quadratic variable is not trivial
				}
				else {
					hasTrivialVariable = true;
				}
			}
			if (hasTrivialVariable) {
				scaleFactor *= product;
			}
			else {
				bias += product;
			}
		}
		if (trivialVariable != null) {
			CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, getEnumerationValue());
			if (bias != 0) {
				solution = new MinusCardinalitySolution(solution, new IntegerCardinalitySolution(bias));
			}
			if (scaleFactor != 1) {
				solution = new DivideCardinalitySolution(solution, new IntegerCardinalitySolution(scaleFactor));
			}
			ruleMatch.addSolution(trivialVariable, solution);
		}
		return true;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		EnumerationValue enumerationValue1 = getEnumerationValue();
		s.append(name);
		s.append(": |");
		s.append(eStructuralFeature.getName());
		if (!enumerationValue1.isNull()) {
			s.append(".\"");
			s.append(enumerationValue1.getName());
			s.append("\"");
		}
		s.append("| = ");
		appendSumOfProducts(s);
		Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> enumerationValue2cardinalityExpression2 = enumerationValue2cardinalityExpression;
		if (enumerationValue2cardinalityExpression2 != null) {
			List<@NonNull CardinalityExpression> sortedExpressions = new ArrayList<>(enumerationValue2cardinalityExpression2.values());
			Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityExpression cardinalityExpression : sortedExpressions) {
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("- ");
				cardinalityExpression.toString(s, depth);
			}
		}
	}
}