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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class CardinalityExpression implements Nameable
{
	public static interface Solution
	{
		@NonNull Integer solve(@NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size);
	}

	private static class AdjustedFeatureSolution implements Solution
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;
		protected final int subtrahend;
		protected final int divisor;

		public AdjustedFeatureSolution(@NonNull EStructuralFeature eStructuralFeature, int subtrahend, int divisor) {
			this.eStructuralFeature = eStructuralFeature;
			this.subtrahend = subtrahend;
			this.divisor = divisor;
			assert subtrahend >= 0;
			assert divisor >= 1;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof AdjustedFeatureSolution)) {
				return false;
			}
			AdjustedFeatureSolution that = (AdjustedFeatureSolution) obj;
			return (this.eStructuralFeature ==  that.eStructuralFeature) && (this.subtrahend == that.subtrahend) && (this.divisor == that.divisor);
		}

		@Override
		public int hashCode() {
			return eStructuralFeature.hashCode() + 3 * subtrahend + 7 * (divisor-1);
		}

		@Override
		public @NonNull Integer solve(@NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size) {
			Integer size = eFeature2size.get(eStructuralFeature);
			int intSize = size != null ? size.intValue() : 0;
			return (intSize - subtrahend) / divisor;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			if (((subtrahend != 0)) && (divisor != 1)) {
				s.append("(");
			}
			s.append("|");
			s.append(eStructuralFeature.getName());
			s.append("|");
			if (subtrahend != 0) {
				s.append(" - ");
				s.append(subtrahend);
			}
			if (((subtrahend != 0)) && (divisor != 1)) {
				s.append(")");
			}
			if (divisor != 1) {
				s.append(" / ");
				s.append(divisor);
			}
			return String.valueOf(s);
		}
	}

	private static class BooleanCommonFactorSolution implements Solution
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;
		protected final int subtrahend;

		public BooleanCommonFactorSolution(@NonNull EStructuralFeature eStructuralFeature, int subtrahend) {
			this.eStructuralFeature = eStructuralFeature;
			this.subtrahend = subtrahend;
			assert subtrahend >= 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof BooleanCommonFactorSolution)) {
				return false;
			}
			BooleanCommonFactorSolution that = (BooleanCommonFactorSolution) obj;
			return (this.eStructuralFeature ==  that.eStructuralFeature) && (this.subtrahend == that.subtrahend);
		}

		@Override
		public int hashCode() {
			return eStructuralFeature.hashCode() + 3 * subtrahend;
		}

		@Override
		public @NonNull Integer solve(@NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size) {
			Integer size = eFeature2size.get(eStructuralFeature);
			int intSize = size != null ? size.intValue() : 0;
			return (intSize - subtrahend) > 0 ? 1 : 0;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("|");
			s.append(eStructuralFeature.getName());
			s.append("|>");
			s.append(subtrahend);
			return String.valueOf(s);
		}
	}

	protected final @NonNull String name;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	private final List<@NonNull List<@NonNull CardinalityVariable>> sumOfProducts = new ArrayList<>();

	public CardinalityExpression(@NonNull String name, @NonNull EStructuralFeature eStructuralFeature) {
		this.name = name;
		this.eStructuralFeature = eStructuralFeature;
	}

	public void addMultiplicityProduct(@NonNull List<@NonNull CardinalityVariable> variables) {
		sumOfProducts.add(variables);
	}

	private @Nullable Integer getIntegerSolution(@Nullable Object solution) {
		if (solution instanceof Integer) {
			return ((Integer)solution).intValue();
		}
		if (solution instanceof BooleanCommonFactorSolution) {
			return 1;
		}
		if (solution instanceof Iterable) {
			for (Object solutionElement : ((Iterable<?>)solution) ) {
				if (solutionElement instanceof Integer) {
					return ((Integer)solutionElement).intValue();
				}
				if (solutionElement instanceof BooleanCommonFactorSolution) {
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

	public void solveForBooleanCommonFactors(@NonNull PreSerializer preSerializer) {
		int sum = 0;
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull List<@NonNull CardinalityVariable> products : sumOfProducts) {
			Object resolution = resolveProduct(preSerializer, products);
			if (resolution instanceof Integer) {
				sum += ((Integer)resolution).intValue();
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
		if (intersection != null) {
			for (@NonNull CardinalityVariable cardinalityVariable : intersection) {
				if (!cardinalityVariable.mayBeMany()) {
					assert cardinalityVariable.mayBeNone();
					preSerializer.addSolution(cardinalityVariable, new BooleanCommonFactorSolution(eStructuralFeature, sum));
				}
			}
		}
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
		return preSerializer.addSolution(sumVariable, new AdjustedFeatureSolution(eStructuralFeature, sum, factor));
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

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst1 = true;
		s.append(name);
		s.append(": |");
		s.append(eStructuralFeature.getName());
		s.append("| = ");
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
}