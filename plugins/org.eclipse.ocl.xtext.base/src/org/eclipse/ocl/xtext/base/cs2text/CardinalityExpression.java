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
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class CardinalityExpression implements Nameable
{
	private static class AdjustedFeatureSolution
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

	private static class BooleanFeatureSolution
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;
		protected final int subtrahend;

		public BooleanFeatureSolution(@NonNull EStructuralFeature eStructuralFeature, int subtrahend) {
			this.eStructuralFeature = eStructuralFeature;
			this.subtrahend = subtrahend;
			assert subtrahend >= 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof BooleanFeatureSolution)) {
				return false;
			}
			BooleanFeatureSolution that = (BooleanFeatureSolution) obj;
			return (this.eStructuralFeature ==  that.eStructuralFeature) && (this.subtrahend == that.subtrahend);
		}

		@Override
		public int hashCode() {
			return eStructuralFeature.hashCode() + 3 * subtrahend;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("||");
			s.append(eStructuralFeature.getName());
			s.append("||");
			if (subtrahend != 0) {
				s.append(" - ");
				s.append(subtrahend);
			}
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

	public boolean solveForBooleanFactors(@NonNull PreSerializer preSerializer) {
		CardinalityVariable sumVariable = null;
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
		if (intersection == null) {
			return false;
		}
		boolean gotOne = false;
		for (@NonNull CardinalityVariable cardinalityVariable : intersection) {
			if (!cardinalityVariable.mayBeMany()) {
				assert cardinalityVariable.mayBeNone();
				preSerializer.addSolution(cardinalityVariable, new BooleanFeatureSolution(eStructuralFeature, sum));
				gotOne = true;
			}
		}
		return gotOne;
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
				if (solution instanceof Integer) {
					product *= ((Integer)solution).intValue();
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