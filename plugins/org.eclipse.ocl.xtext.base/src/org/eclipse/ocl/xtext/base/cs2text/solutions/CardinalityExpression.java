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

	/**
	 * The cardinalities that determine the production of a particar feature/
	 *
	 * e.g.  { x+=X (',' x+=X)* }? is C0[?] + C0[?] * C1[*]
	 *
	 * NB an empty list for no variab;es is the consyamt 1 sice unit cardinalities are suppressed.
	 */
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

	//
	//	slots = aa + bb + cc * X + dd * X => X = (slots - (aa + bb)) / (cc + dd)
	//
	//	aa, bb are known, cc, dd may involve unknown terms
	//
	public boolean analyzeMayBeZeroCommonFactors(@NonNull StaticRuleMatch ruleMatch, boolean mayBeMany) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull List<@NonNull CardinalityVariable> product : sumOfProducts) {
			List<@NonNull CardinalityVariable> unknownVariables = getUnknownVariables(ruleMatch, product);
			if (unknownVariables == null) {
				return false;		// No variables to be common factors
			}
			else if (intersection == null) {
				intersection = new HashSet<>(unknownVariables);
			}
			else {
				intersection.retainAll(unknownVariables);
				if (intersection.isEmpty()) {
					return false;
				}
			}
		}
		if (intersection  != null) {
			for (@NonNull CardinalityVariable cardinalityVariable : intersection) {
				if (mayBeMany || !cardinalityVariable.mayBeMany()) {
					assert cardinalityVariable.mayBeNone();
					CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
					solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(0));
					ruleMatch.addSolution(cardinalityVariable, solution);
					return true;
				}
			}
		}
		return false;
	}

/*	public boolean analyzeConstants(@NonNull StaticRuleMatch ruleMatch) {
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
		CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
		if (sum != 0) {
			solution = new SubtractCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
		}
		if (factor != 1) {
			solution = new DivideCardinalitySolution(solution, new IntegerCardinalitySolution(factor));
		}
		ruleMatch.addSolution(sumVariable, solution);
		return true;
	} */

/*	public boolean analyzeConstants(@NonNull StaticRuleMatch ruleMatch) {
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
		CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
		if (sum != 0) {
			solution = new SubtractCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
		}
		if (factor != 1) {
			solution = new DivideCardinalitySolution(solution, new IntegerCardinalitySolution(factor));
		}
		ruleMatch.addSolution(sumVariable, solution);
		return true;
	} */

	/*	public boolean analyzeNoVariables(@NonNull StaticRuleMatch ruleMatch) {
			for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
				for (@NonNull CardinalityVariable variable : products) {
					CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
					if (!(solution instanceof IntegerCardinalitySolution)) {
						return false;
					}
				}
			}
			return true;
		} */

	/*	public boolean analyzePseudoBooleanFactors(@NonNull StaticRuleMatch ruleMatch) {
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
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
						if (sum != 0) {
							solution = new SubtractCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
						}
						ruleMatch.addSolution(variable, solution);
					}
					else {
						assert Iterables.contains(intersection, variable);
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
						solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
						ruleMatch.addSolution(variable, solution);
					}
				}
			}
			return true;
		} */

	public boolean analyzeRedundantProducts(@NonNull StaticRuleMatch ruleMatch) {
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
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
						ruleMatch.addSolution(variable, solution);
					}
					else {
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
						solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(0));
						ruleMatch.addSolution(variable, solution);
					}
				}
			}
		}
		return true;
	}

	/*	public boolean analyzeConstants(@NonNull StaticRuleMatch ruleMatch) {
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
			CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
			if (sum != 0) {
				solution = new SubtractCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
			}
			if (factor != 1) {
				solution = new DivideCardinalitySolution(solution, new IntegerCardinalitySolution(factor));
			}
			ruleMatch.addSolution(sumVariable, solution);
			return true;
		} */

	/*	public boolean analyzeNoVariables(@NonNull StaticRuleMatch ruleMatch) {
			for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
				for (@NonNull CardinalityVariable variable : products) {
					CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
					if (!(solution instanceof IntegerCardinalitySolution)) {
						return false;
					}
				}
			}
			return true;
		} */

	//
	//	slots = aa + bb + cc * X + dd * X => X = (slots - (aa + bb)) / (cc + dd)
	//
	//	aa, bb, cc, dd are known
	//
	//	if (cc + dd) or rather a v is optional the divide isredundant not y zero.
	//
	public boolean analyzeTrivial(@NonNull StaticRuleMatch ruleMatch) {
		CardinalityVariable trivialVariable = null;
		for (@NonNull List<@NonNull CardinalityVariable> product : sumOfProducts) {
			List<@NonNull CardinalityVariable> unknownVariables = getUnknownVariables(ruleMatch, product);
			if (unknownVariables == null) {
				// constant is easy
			}
			else if (unknownVariables.size() > 1) {
				return false;		// Two variables is not trivial
			}
			else {
				CardinalityVariable unknownVariable = unknownVariables.get(0);
				if (trivialVariable == null) {
					trivialVariable = unknownVariable;
				}
				else if (trivialVariable != unknownVariable) {
					return false;		// Two variables is not trivial
				}
			}
		}
		CardinalitySolution resultsSolution = createSolution(ruleMatch, trivialVariable);
		ruleMatch.addSolution(trivialVariable, resultsSolution);
		return true;
	}

/*	public boolean analyzeNoVariables(@NonNull StaticRuleMatch ruleMatch) {
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
				if (!(solution instanceof IntegerCardinalitySolution)) {
					return false;
				}
			}
		}
		return true;
	} */

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

	/**
	 * Create the solution for solvedVariable by inverting this expression, which is linear in in a non-null solvedVariable
	 *
	 * i.e.	slots = aa + bb + cc*X + dd*X => X = (slots - (aa + bb)) / (cc + dd)
	 *
	 *	aa, bb, cc, dd are will be known and constant at run-time.
	 *
	 *	if (cc + dd) or rather a v is optional the divide is redundant.
	 *
	 * If solved varable is null everything is known.
	 */
	public @NonNull CardinalitySolution createSolution(@NonNull StaticRuleMatch ruleMatch, @Nullable CardinalityVariable solvedVariable) {
		CardinalitySolution constantSolution = null;
		CardinalitySolution factorSolution = null;
		for (@NonNull Iterable<@NonNull CardinalityVariable> product : sumOfProducts) {
			boolean isFactor = false;
			CardinalitySolution residualSolution = null;
			for (@NonNull CardinalityVariable term : product) {
				if (term == solvedVariable) {
					assert !isFactor;		// Quadratic doesn't happen
					isFactor = true;
				}
				else {
					CardinalitySolution termSolution = new VariableCardinalitySolution(term);
					if (residualSolution == null){
						residualSolution = termSolution;
					}
					else {
						residualSolution = new MultiplyCardinalitySolution(residualSolution, termSolution);
					}
				}
			}
			if (residualSolution == null) {
				residualSolution = new IntegerCardinalitySolution(1);		// Empty / pruned list is a unit product
			}
			if (isFactor) {
				if (factorSolution == null) {
					factorSolution = residualSolution;
				}
				else {
					factorSolution = new AddCardinalitySolution(factorSolution, residualSolution);
				}
			}
			else {
				if (constantSolution == null) {
					constantSolution = residualSolution;
				}
				else {
					constantSolution = new AddCardinalitySolution(constantSolution, residualSolution);
				}
			}
		}
		// Below handles factorSolution is a Boolean scale factor but looks strange for a Boolean common factor. Dividing out in constantSolution would be clearer.
		CardinalitySolution resultSolution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
		if (constantSolution != null) {
			resultSolution = new SubtractCardinalitySolution(resultSolution, constantSolution);
		}
		if ((factorSolution != null) && !factorSolution.isOptional() && (!Integer.valueOf(1).equals(factorSolution.basicGetIntegerSolution(ruleMatch)))) {		// No need to divide by 0 or 1 when the 0 case was a multiplier.
			resultSolution = new DivideCardinalitySolution(resultSolution, factorSolution);
		}
		return resultSolution;
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

	/**
	 * Return a non-null list of variables that do not yet have a known solution.
	 *
	 * Returns null if no variable has no solution, or if a variable is used in an (impossible) non-linear fashion.
	 */
	public @Nullable List<@NonNull CardinalityVariable> getUnknownVariables(@NonNull StaticRuleMatch ruleMatch) {
		List<@NonNull CardinalityVariable> unknownVariables = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
				if (solution == null) {
					if (unknownVariables == null) {
						unknownVariables = new ArrayList<>();
					}
					if (unknownVariables.contains(variable)) {
						return null;		// Quadratic cannot happen.
					}
					unknownVariables.add(variable);
				}
			}
		}
		return unknownVariables;
	}
	protected @Nullable List<@NonNull CardinalityVariable> getUnknownVariables(@NonNull StaticRuleMatch ruleMatch, @NonNull List<@NonNull CardinalityVariable> product) {
		List<@NonNull CardinalityVariable> unknownVariables = null;
		for (@NonNull CardinalityVariable variable : product) {
			CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
			if (solution == null) {
				if (unknownVariables == null) {
					unknownVariables = new ArrayList<>();
				}
				if (unknownVariables.contains(variable)) {
					return null;		// Quadratic cannot happen.
				}
				unknownVariables.add(variable);
			}
		}
		return unknownVariables;
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
			Integer integerSolution = ruleMatch.basicGetIntegerSolution(variable);		// FIXME constants
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

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eStructuralFeature.getName());
		if (!enumerationValue.isNull()) {
			s.append(".\"");
			s.append(enumerationValue.getName());
			s.append("\"");
		}
		s.append("| = ");
		appendSumOfProducts(s);
		Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> enumerationValue2cardinalityExpression2 = enumerationValue2cardinalityExpression;
		if (enumerationValue2cardinalityExpression2 != null) {
			List<@NonNull CardinalityExpression> sortedExpressions = new ArrayList<>(enumerationValue2cardinalityExpression2.values());
			Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityExpression cardinalityExpression : sortedExpressions) {
				StringUtil.appendIndentation(s, depth, "  ");
				s.append("- ");
				cardinalityExpression.toString(s, depth);
			}
		}
	}
}