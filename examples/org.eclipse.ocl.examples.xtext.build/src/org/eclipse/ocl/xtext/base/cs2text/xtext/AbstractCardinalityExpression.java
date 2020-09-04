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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermDivide;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermGreaterThan;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermMultiply;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermSubtract;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermVariable;

/**
 * A CardinalityExpression equates the sum of CardinalityVariable products to the number of elements in an eStructuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public abstract class AbstractCardinalityExpression implements CardinalityExpression
{
	protected final @NonNull String name;

	/**
	 * The cardinalities that determine the production of a particular feature/
	 *
	 * e.g.  { x+=X (',' x+=X)* }? is C0[?] + C0[?] * C1[*]
	 *
	 * NB an empty list for no variables is the consyamt 1 sice unit cardinalities are suppressed.
	 */
	private final @NonNull List<@NonNull List<@NonNull CardinalityVariable>> sumOfProducts = new ArrayList<>();

	protected AbstractCardinalityExpression(@NonNull String name) {
		this.name = name;
	}

	@Override
	public void addMultiplicityProduct(@NonNull Iterable<@NonNull CardinalityVariable> variables) {
		List<@NonNull CardinalityVariable> prunedList = new ArrayList<>();
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
			prunedList.add(variable);
		}
		sumOfProducts.add(prunedList);
	}

	//
	//	slots = aa + bb + cc * X + dd * X => X = (slots - (aa + bb)) / (cc + dd)
	//
	//	aa, bb are known, cc, dd may involve unknown terms
	//
	@Override
	public boolean analyzeMayBeZeroCommonFactors(@NonNull StaticRuleMatch ruleMatch, boolean mayBeMany) {
		Set<@NonNull Integer> intersection = getUnknownCommonVariables(ruleMatch, sumOfProducts);
		if (intersection  != null) {
			for (@NonNull Integer cardinalityVariable : intersection) {
				if (mayBeMany /*|| !cardinalityVariable.mayBeMany()*/) {
				//	assert cardinalityVariable.mayBeNone();
					SerializationMatchTerm solution = createSizeCardinalitySolution();
					solution = new SerializationMatchTermGreaterThan(solution, new SerializationMatchTermInteger(0));
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

	@Override
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
						ruleMatch.addSolution(variable.getIndex(), new SerializationMatchTermInteger(0));
					}
					else if (variable == manyVariable) {
						SerializationMatchTerm solution = createSizeCardinalitySolution();
						ruleMatch.addSolution(variable.getIndex(), solution);
					}
					else {
						SerializationMatchTerm solution = createSizeCardinalitySolution();
						solution = new SerializationMatchTermGreaterThan(solution, new SerializationMatchTermInteger(0));
						ruleMatch.addSolution(variable.getIndex(), solution);
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
	@Override
	public boolean analyzeTrivial(@NonNull StaticRuleMatch ruleMatch, boolean mayBeMany) {
		Integer trivialVariable = null;
		for (@NonNull List<@NonNull CardinalityVariable> product : sumOfProducts) {
			List<@NonNull Integer> unknownVariables = getUnknownVariables(ruleMatch, product);
			if (unknownVariables == null) {
				// constant is easy
			}
			else if (unknownVariables.size() > 1) {
				return false;		// Two variables is not trivial
			}
			else {
				Integer unknownVariable = unknownVariables.get(0);
				if (trivialVariable == null) {
					trivialVariable = unknownVariable;
				}
				else if (trivialVariable != unknownVariable) {
					return false;		// Two variables is not trivial
				}
			}
		}
		SerializationMatchTerm resultsSolution = createSolution(ruleMatch, trivialVariable, mayBeMany);
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

	@Override
	public abstract boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch);

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

	protected abstract @NonNull SerializationMatchTerm createSizeCardinalitySolution();

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
	public @NonNull SerializationMatchTerm createSolution(@NonNull StaticRuleMatch ruleMatch, @Nullable Integer solvedVariable, boolean mayBeMany) {
		//
		// Determine slots = constantSumOfProducts + factorSumOfProducts * solvedVariable
		//
		List<@NonNull List<@NonNull CardinalityVariable>> constantSumOfProducts = new ArrayList<>();
		List<@NonNull List<@NonNull CardinalityVariable>> factorSumOfProducts = new ArrayList<>();
		for (@NonNull Iterable<@NonNull CardinalityVariable> product : sumOfProducts) {
			boolean isFactor = false;
			List<@NonNull CardinalityVariable> residualProducts = new ArrayList<>();
			for (@NonNull CardinalityVariable term : product) {
				if ((solvedVariable != null) && (term.getIndex() == solvedVariable)) {
					assert !isFactor;		// Quadratic doesn't happen
					isFactor = true;
				}
				else {
					residualProducts.add(term);
				}
			}
			if (isFactor) {
				factorSumOfProducts.add(residualProducts);
			}
			else {
				constantSumOfProducts.add(residualProducts);
			}
		}
		//
		//	Divide out common Boolean factors from non-empty constantSumOfProducts, factorSumOfProducts
		//	or from empty constantSumOfProducts, non-empty factorSumOfProducts
		//	to avoid gratuitous divisions whose result is not actually used.
		//
		Set<@NonNull CardinalityVariable> factorCommonVariables = getKnownCommonVariables(ruleMatch, factorSumOfProducts);
		if (factorCommonVariables != null) {
			Set<@NonNull CardinalityVariable> constantCommonVariables = getKnownCommonVariables(ruleMatch, constantSumOfProducts);
			for (@NonNull CardinalityVariable commonVariable : factorCommonVariables) {
				if ((mayBeMany || !commonVariable.mayBeMany()) && (constantSumOfProducts.isEmpty() || ((constantCommonVariables != null) && constantCommonVariables.contains(commonVariable)))) {
					for (@NonNull List<@NonNull CardinalityVariable> constantProduct : constantSumOfProducts) {
						constantProduct.remove(commonVariable);
					}
					for (@NonNull List<@NonNull CardinalityVariable> factorProduct : factorSumOfProducts) {
						factorProduct.remove(commonVariable);
					}
				}
			}
		}
		//
		//	Convert the residual constantSumOfProducts, factorSumOfProducts to
		//	solvedVariable = (slots - constantSumOfProducts) / factorSumOfProducts
		//
		SerializationMatchTerm resultSolution = createSizeCardinalitySolution();
		for (@NonNull Iterable<@NonNull CardinalityVariable> constantProduct : constantSumOfProducts) {
			SerializationMatchTerm sumSolution = null;
			for (@NonNull CardinalityVariable constantTerm : constantProduct) {
				SerializationMatchTerm termSolution = new SerializationMatchTermVariable(constantTerm.getIndex());
				sumSolution = sumSolution != null ? new SerializationMatchTermMultiply(sumSolution, termSolution) : termSolution;
			}
			resultSolution = new SerializationMatchTermSubtract(resultSolution, sumSolution != null ? sumSolution : new SerializationMatchTermInteger(1));
		}
		for (@NonNull Iterable<@NonNull CardinalityVariable> factorProduct : factorSumOfProducts) {
			SerializationMatchTerm sumSolution = null;
			for (@NonNull CardinalityVariable factorTerm : factorProduct) {
				SerializationMatchTerm termSolution = new SerializationMatchTermVariable(factorTerm.getIndex());
				sumSolution = sumSolution != null ? new SerializationMatchTermMultiply(sumSolution, termSolution) : termSolution;
			}
			if (sumSolution != null) {
				resultSolution = new SerializationMatchTermDivide(resultSolution, sumSolution);
			}
		}
		return resultSolution;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	protected @Nullable Set<@NonNull CardinalityVariable> getKnownCommonVariables(@NonNull StaticRuleMatch ruleMatch, @NonNull Iterable<? extends @NonNull Iterable<@NonNull CardinalityVariable>> sumOfProducts2) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull Iterable<@NonNull CardinalityVariable> product : sumOfProducts2) {
			List<@NonNull CardinalityVariable> knownVariables = getKnownVariables(ruleMatch, product);
			if (knownVariables == null) {
				return null;		// No variables to be common factors
			}
			else if (intersection == null) {
				intersection = new HashSet<>(knownVariables);
			}
			else {
				intersection.retainAll(knownVariables);
				if (intersection.isEmpty()) {
					return null;
				}
			}
		}
		return intersection;
	}

	protected @Nullable List<@NonNull CardinalityVariable> getKnownVariables(@NonNull StaticRuleMatch ruleMatch, @NonNull Iterable<@NonNull CardinalityVariable> product) {
		List<@NonNull CardinalityVariable> knownVariables = null;
		for (@NonNull CardinalityVariable variable : product) {
			SerializationMatchTerm solution = ruleMatch.basicGetSolution(variable);
			if (solution != null) {
				if (knownVariables == null) {
					knownVariables = new ArrayList<>();
				}
				if (knownVariables.contains(variable)) {
					return null;		// Quadratic cannot happen.
				}
				knownVariables.add(variable);
			}
		}
		return knownVariables;
	}

	protected @Nullable Set<@NonNull Integer> getUnknownCommonVariables(@NonNull StaticRuleMatch ruleMatch, @NonNull Iterable<? extends @NonNull Iterable<@NonNull CardinalityVariable>> sumOfProducts2) {
		Set<@NonNull Integer> intersection = null;
		for (@NonNull Iterable<@NonNull CardinalityVariable> product : sumOfProducts2) {
			List<@NonNull Integer> unknownVariables = getUnknownVariables(ruleMatch, product);
			if (unknownVariables == null) {
				return null;		// No variables to be common factors
			}
			else if (intersection == null) {
				intersection = new HashSet<>(unknownVariables);
			}
			else {
				intersection.retainAll(unknownVariables);
				if (intersection.isEmpty()) {
					return null;
				}
			}
		}
		return intersection;
	}

	/**
	 * Return a non-null list of variables that do not yet have a known solution.
	 *
	 * Returns null if no variable has no solution, or if a variable is used in an (impossible) non-linear fashion.
	 */
	@Override
	public @Nullable List<@NonNull Integer> getUnknownVariables(@NonNull StaticRuleMatch ruleMatch) {
		List<@NonNull Integer> unknownVariables = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				SerializationMatchTerm solution = ruleMatch.basicGetSolution(variable);
				if (solution == null) {
					if (unknownVariables == null) {
						unknownVariables = new ArrayList<>();
					}
					if (unknownVariables.contains(variable)) {
						return null;		// Quadratic cannot happen.
					}
					unknownVariables.add(variable.getIndex());
				}
			}
		}
		return unknownVariables;
	}

	@Override
	public @Nullable List<@NonNull Integer> getUnknownVariables(@NonNull StaticRuleMatch ruleMatch, @NonNull Iterable<@NonNull CardinalityVariable> product) {
		List<@NonNull Integer> unknownVariables = null;
		for (@NonNull CardinalityVariable variable : product) {
			SerializationMatchTerm solution = ruleMatch.basicGetSolution(variable);
			if (solution == null) {
				if (unknownVariables == null) {
					unknownVariables = new ArrayList<>();
				}
				if (unknownVariables.contains(variable)) {
					return null;		// Quadratic cannot happen.
				}
				unknownVariables.add(variable.getIndex());
			}
		}
		return unknownVariables;
	}

	@Override
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
			Integer integerSolution = ruleMatch.basicGetIntegerSolution(variable.getIndex());		// FIXME constants
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

	@Override
	public int solve(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		int sum = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			int product = 1;
			for (@NonNull CardinalityVariable variable : products) {
				Integer value = dynamicRuleMatch.basicGetIntegerSolution(variable.getIndex());
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
}