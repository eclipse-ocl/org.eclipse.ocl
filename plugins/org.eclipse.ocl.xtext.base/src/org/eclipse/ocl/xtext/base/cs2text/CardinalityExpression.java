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
package org.eclipse.ocl.xtext.base.cs2text;

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
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class CardinalityExpression implements Nameable
{
	public static class ValueCardinalityExpression extends CardinalityExpression
	{
		protected final @NonNull String value;

		public ValueCardinalityExpression(@NonNull CardinalityExpression cardinalityExpression, @NonNull String value) {
			super(cardinalityExpression.getName() + ".\"" + value + "\"", cardinalityExpression.eStructuralFeature);
			this.value = value;
		}

		@Override
		protected @NonNull String getValue() {
			return value;
		}
	}

	public static int getSize(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis, @NonNull EStructuralFeature eStructuralFeature, @Nullable String value) {
		Object object = eFeature2contentAnalysis.get(eStructuralFeature);
		if (object == null) {
			return 0;
		}
		if (object instanceof Integer) {
			return ((Integer)object).intValue();
		}
		assert value != null;
		Map<@NonNull String, @NonNull Integer> contentAnalysis = (Map<@NonNull String, @NonNull Integer>)object;
		Integer size = contentAnalysis.get(value);
		int intSize = size != null ? size.intValue() : 0;
		return intSize;
	}

	protected final @NonNull String name;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	private final @NonNull List<@NonNull List<@NonNull CardinalityVariable>> sumOfProducts = new ArrayList<>();
	private @Nullable Map<@NonNull String, @NonNull ValueCardinalityExpression> value2valueCardinalityExpression = null;

	public CardinalityExpression(@NonNull String name, @NonNull EStructuralFeature eStructuralFeature) {
		this.name = name;
		this.eStructuralFeature = eStructuralFeature;
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
					if (variable.isOne()) {
						s.append("1");
					}
					else {
						s.append(variable);
						gotOne = true;
					}
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
	private @Nullable Iterable<@NonNull CardinalityVariable> computeUnsolvedCommonFactors(@NonNull PreSerializer preSerializer) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			Object resolution = resolveProduct(preSerializer, products);
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

	private @Nullable Integer getIntegerSolution(@Nullable Object solution) {
		if (solution instanceof Integer) {
			return ((Integer)solution).intValue();
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
		return null;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @Nullable Iterable<@NonNull CardinalityVariable> getUnsolvedVariables(@NonNull PreSerializer preSerializer) {
		List<@NonNull CardinalityVariable> unsolvedVariables = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				Object solution = preSerializer.getSolution(variable);
				if ((solution == null) || ((solution instanceof CardinalitySolution) && ((CardinalitySolution)solution).isRuntime())) {
					if (unsolvedVariables == null) {
						unsolvedVariables = new ArrayList<>();
					}
					unsolvedVariables.add(variable);
				}
			}
		}
		return unsolvedVariables;
	}

	protected @Nullable String getValue() {
		return null;
	}

	public @NonNull ValueCardinalityExpression getValueCardinalityExpression(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Object valueOrValues) {
		String value = String.valueOf(valueOrValues);		// XXX wip fudge
		Map<@NonNull String, @NonNull ValueCardinalityExpression> value2valueCardinalityExpression2 = value2valueCardinalityExpression;
		if (value2valueCardinalityExpression2 == null) {
			value2valueCardinalityExpression = value2valueCardinalityExpression2 = new HashMap<>();
		}
		ValueCardinalityExpression valueCardinalityExpression = value2valueCardinalityExpression2.get(value);
		if (valueCardinalityExpression == null) {
			grammarAnalysis.addEnumeration((EAttribute)eStructuralFeature, value);
			valueCardinalityExpression = new ValueCardinalityExpression(this, value);
			value2valueCardinalityExpression2.put(value, valueCardinalityExpression);
		}
		return valueCardinalityExpression;
	}

	public @Nullable Iterable<@NonNull ValueCardinalityExpression> getValueCardinalityExpressions() {
		return value2valueCardinalityExpression != null ? value2valueCardinalityExpression.values() : null;
	}

	/**
	 * Simplify a product term returning
	 * - Integer for a constant
	 * - CardinalityVariable for a scaled variable
	 * - Set(CardinalityVariable) for a scaled product of non-quadratic variable
	 * - null for a quadratic variable
	 */
	public Object resolveProduct(@NonNull PreSerializer preSerializer, @NonNull List<@NonNull CardinalityVariable> product) {
		Set<@NonNull CardinalityVariable> productVariables = null;
		int constantProduct = 1;
		for (@NonNull CardinalityVariable variable : product) {
			Object solution = preSerializer.getSolution(variable);
			if (solution instanceof Integer) {
				constantProduct *= ((Integer)solution).intValue();
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

	public int solve(@NonNull Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value) {
		int sum = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			int product = 1;
			for (@NonNull CardinalityVariable variable : products) {
				Integer value = variable2value.get(variable);
				int intValue = value != null ? value.intValue() : 1;
				product *= intValue;
			}
			sum += product;
		}
		return sum;
	}

	public void solveAtRuntime(@NonNull PreSerializer preSerializer) {
	//	Solution runtimeSolution = new RuntimeSolution(this, unresolvedVariables);
	//	for (@NonNull CardinalityVariable variable : unresolvedVariables) {
	//		preSerializer.addSolution(variable, runtimeSolution);
	//	}
	}

	public boolean solveForBooleanCommonFactors(@NonNull PreSerializer preSerializer) {
		Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(preSerializer);
		if (intersection == null) {
			return false;
		}
		int sum = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			Object resolution = resolveProduct(preSerializer, products);
			if (resolution instanceof Integer) {
				sum += ((Integer)resolution).intValue();
			}
		}
		for (@NonNull CardinalityVariable cardinalityVariable : intersection) {
			if (!cardinalityVariable.mayBeMany()) {
				assert cardinalityVariable.mayBeNone();
				preSerializer.addSolution(cardinalityVariable, new BooleanCommonFactorCardinalitySolution(eStructuralFeature, getValue(), sum));
			}
		}
		return true;
	}

	public boolean solveForConstants(@NonNull PreSerializer preSerializer) {
		CardinalityVariable sumVariable = null;
		int sum = 0;
		int factor = 0;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			CardinalityVariable productVariable = null;
			int product = 1;
			for (@NonNull CardinalityVariable variable : products) {
				Object solution = preSerializer.getSolution(variable);
				Integer integerSolution = getIntegerSolution(solution);
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
		preSerializer.addSolution(sumVariable, new AdjustedFeatureCardinalitySolution(eStructuralFeature, getValue(), sum, factor));
		return true;
	}

	public boolean solveForNoVariables(PreSerializer preSerializer) {
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				Object solution = preSerializer.getSolution(variable);
				if (!(solution instanceof Integer)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean solveForPseudoBooleanFactors(@NonNull PreSerializer preSerializer) {
		Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(preSerializer);
		if (intersection == null) {
			return false;
		}
		int sum = 0;
		CardinalityVariable manyVariable = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			Object resolution = resolveProduct(preSerializer, products);
			if (resolution instanceof Integer) {
				sum += ((Integer)resolution).intValue();
			}
			for (@NonNull CardinalityVariable variable : products) {
				if ((manyVariable == null) && variable.mayBeMany()) {
					manyVariable = variable;
				}
			}
		}
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				if (preSerializer.getSolution(variable) == null) {
					if (variable == manyVariable) {
						preSerializer.addSolution(variable, new AdjustedFeatureCardinalitySolution(eStructuralFeature, null, sum, 1));
					}
					else {
						preSerializer.addSolution(variable, new BooleanCommonFactorCardinalitySolution(eStructuralFeature, null, 0));
					}
				}
			}
		}
		return true;
	}

	public boolean solveForRedundantProducts(@NonNull PreSerializer preSerializer) {
		Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(preSerializer);
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
				if (preSerializer.getSolution(variable) == null) {
					if (products != manyProducts) {
						preSerializer.addSolution(variable, new IntegerCardinalitySolution(0));
					}
					else if (variable == manyVariable) {
						preSerializer.addSolution(variable, new AdjustedFeatureCardinalitySolution(eStructuralFeature, null, 0, 1));
					}
					else {
						preSerializer.addSolution(variable, new BooleanCommonFactorCardinalitySolution(eStructuralFeature, null, 0));
					}
				}
			}
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
		String value = getValue();
		s.append(name);
		s.append(": |");
		s.append(eStructuralFeature.getName());
		if (value != null) {
			s.append(".\"");
			s.append(value);
			s.append("\"");
		}
		s.append("| = ");
		appendSumOfProducts(s);
		Map<@NonNull String, @NonNull ValueCardinalityExpression> value2valueCardinalityExpression2 = value2valueCardinalityExpression;
		if (value2valueCardinalityExpression2 != null) {
			List<@NonNull String> sortedValues = new ArrayList<>(value2valueCardinalityExpression2.keySet());
			Collections.sort(sortedValues);
			for (@NonNull String sortedValue : sortedValues) {
				StringUtil.appendIndentation(s, depth+1, "\t");
				ValueCardinalityExpression valueCardinalityExpression = value2valueCardinalityExpression2.get(sortedValue);
				assert valueCardinalityExpression != null;
				valueCardinalityExpression.toString(s, depth+1);
			}
		}
	}
}