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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermDivide;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEAttributeSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEReferenceSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermGreaterThan;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermMultiply;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermSubtract;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermVariable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

/**
 * A CardinalityExpression equates the sum of CardinalityVariable products to the number of elements in an eStructuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution must be found to
 * select a potential serialization option.
 */
public abstract class CardinalityExpression implements Nameable
{
	/**
	 * An EAttributeCardinalityExpression equates the sum of CardinalityVariable products to the number of elements in an EAttribute slot.
	 * If a non-null enumerationValue is in force, the slot values are restricted to those identified by the enumerationValue.
	 */
	public static class EAttributeCardinalityExpression extends CardinalityExpression
	{
		protected final @NonNull EAttribute eAttribute;
		protected final @Nullable EnumerationValue enumerationValue;
		private final @NonNull Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> enumerationValue2cardinalityExpression = new HashMap<>();

		public EAttributeCardinalityExpression(@NonNull String name, @NonNull EAttribute eAttribute) {
			super(name);
			assert eAttribute != null;
			this.eAttribute = eAttribute;
			this.enumerationValue = null;
		}

		private EAttributeCardinalityExpression(@NonNull EAttributeCardinalityExpression parentCardinalityExpression, @NonNull EnumerationValue enumerationValue) {
			super(parentCardinalityExpression.name + "." + parentCardinalityExpression.enumerationValue2cardinalityExpression.size());
			this.eAttribute = parentCardinalityExpression.eAttribute;
			this.enumerationValue = enumerationValue;
		}

	/*	@Override
		public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserElementAnalysis elementAnalysis = dynamicRuleMatch.getElementAnalysis();
			for (Entry<@NonNull EnumerationValue, @NonNull CardinalityExpression> entry : enumerationValue2cardinalityExpression.entrySet()) {
				EnumerationValue value = SerializationUtils.nonNullState(entry.getKey());
				CardinalityExpression nestedExpression = SerializationUtils.nonNullState(entry.getValue());
				int requiredCount = nestedExpression.solve(dynamicRuleMatch);
				int actualCount = elementAnalysis.getSize(eAttribute, value);
				if (requiredCount != actualCount) {
					return false;
				}
			}
			return true;
		} */

		@Override
		protected @NonNull SerializationMatchTermEAttributeSize createSizeCardinalityMatchTerm() {
			assert enumerationValue != null;
			return new SerializationMatchTermEAttributeSize(eAttribute, enumerationValue);
		}

		@Override
		public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
			assert this.enumerationValue == null;
			CardinalityExpression cardinalityExpression = SerializationUtils.maybeNull(enumerationValue2cardinalityExpression.get(enumerationValue));
			if (cardinalityExpression == null) {
				grammarAnalysis.addEnumeration(eAttribute, enumerationValue);
				cardinalityExpression = new EAttributeCardinalityExpression(this, enumerationValue);
				enumerationValue2cardinalityExpression.put(enumerationValue, cardinalityExpression);
			}
			return cardinalityExpression;
		}

		@Override
		public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
			return enumerationValue2cardinalityExpression.values();
		}

		public @NonNull EAttribute getEAttribute() {
			return eAttribute;
		}

		@Override
		public @NonNull EAttribute getEStructuralFeature() {
			return eAttribute;
		}

		public @NonNull EnumerationValue getEnumerationValue() {
			assert enumerationValue != null;
			return enumerationValue;
		}

		public @Nullable Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> getEnumerationValue2cardinalityExpression() {
			return enumerationValue2cardinalityExpression;
		}

		@Override
		public void toString(@NonNull DiagnosticStringBuilder s) {
			s.append(name);
			s.append(": |");
			s.append(SerializationUtils.getName(eAttribute));
			EnumerationValue enumerationValue2 = enumerationValue;
			if (enumerationValue2 != null) {
				s.append(".'");
				s.append(enumerationValue2.getName());
				s.append("'");
			}
			s.append("| = ");
			appendSumOfProducts(s);
			List<@NonNull CardinalityExpression> sortedExpressions = new ArrayList<>(enumerationValue2cardinalityExpression.values());
			Collections.sort(sortedExpressions, SerializationUtils.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityExpression cardinalityExpression : sortedExpressions) {
				cardinalityExpression.toString(s);
			}
		}
	}

	/**
	 * An EReferenceCardinalityExpression equates the sum of CardinalityVariable products to the number of elements in an EReference slot.
	 * If a non-null grammarRuleVector is in force, the slot values are restricted to those identified by the grammarRuleVector.
	 */
	public static class EReferenceCardinalityExpression extends CardinalityExpression
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull GrammarRuleVector grammarRuleVector;
		private @NonNull Map<@NonNull GrammarRuleVector, @NonNull CardinalityExpression> grammarRuleVector2cardinalityExpression = new HashMap<>();

		private EReferenceCardinalityExpression(@NonNull String name, @NonNull EReference eReference, @NonNull GrammarRuleVector grammarRuleVector) {
			super(name);
			this.eReference = eReference;
			this.grammarRuleVector = grammarRuleVector;
		}

	/*	@Override
		public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserElementAnalysis elementAnalysis = dynamicRuleMatch.getElementAnalysis();
			for (Entry<@NonNull GrammarRuleVector, @NonNull CardinalityExpression> entry : grammarRuleVector2cardinalityExpression.entrySet()) {
				assert entry != null;
				@NonNull GrammarRuleVector grammarRuleVector = SerializationUtils.nonNullState(entry.getKey());
				@NonNull CardinalityExpression nestedExpression = SerializationUtils.nonNullState(entry.getValue());
				int requiredCount = nestedExpression.solve(dynamicRuleMatch);
				int actualCount = elementAnalysis.getSize(eReference, grammarRuleVector);
				if (requiredCount != actualCount) {
					return false;
				}
			}
			return true;
		} */

		@Override
		protected @NonNull SerializationMatchTermEReferenceSize createSizeCardinalityMatchTerm() {
			return new SerializationMatchTermEReferenceSize(eReference, grammarRuleVector);
		}

		@Override
		public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull GrammarRuleVector grammarRuleVector) {
			CardinalityExpression cardinalityExpression = SerializationUtils.maybeNull(grammarRuleVector2cardinalityExpression.get(grammarRuleVector));
			if (cardinalityExpression == null) {
				String subName = name + "." + grammarRuleVector2cardinalityExpression.size();
				cardinalityExpression = new EReferenceCardinalityExpression(subName, eReference, grammarRuleVector);
				grammarRuleVector2cardinalityExpression.put(grammarRuleVector, cardinalityExpression);
			}
			return cardinalityExpression;
		}

		@Override
		public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
			return grammarRuleVector2cardinalityExpression.values();
		}

		public @NonNull EReference getEReference() {
			return eReference;
		}

		@Override
		public @NonNull EReference getEStructuralFeature() {
			return eReference;
		}

		public @NonNull GrammarRuleVector getGrammarRuleVector() {
			assert grammarRuleVector != null;
			return grammarRuleVector;
		}

		public @Nullable Map<@NonNull GrammarRuleVector, @NonNull CardinalityExpression> getGrammarRuleVector2cardinalityExpression() {
			return grammarRuleVector2cardinalityExpression;
		}

		@Override
		public void toString(@NonNull DiagnosticStringBuilder s) {
			s.append(name);
			s.append(": |");
			s.append(SerializationUtils.getName(eReference));
			s.append(":{");
			grammarRuleVector.toString(s);
			s.append("}| = ");
			appendSumOfProducts(s);
			List<@NonNull CardinalityExpression> sortedExpressions = new ArrayList<>(grammarRuleVector2cardinalityExpression.values());
			Collections.sort(sortedExpressions, SerializationUtils.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityExpression cardinalityExpression : sortedExpressions) {
				cardinalityExpression.toString(s);
			}
		}
	}

	/**
	 * An EStructuralFeatureCardinalityExpression equates the sum of CardinalityVariable products to the number of elements in an eStructuralFeature slot.
	 */
	public static class EStructuralFeatureCardinalityExpression extends CardinalityExpression
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;

		public EStructuralFeatureCardinalityExpression(@NonNull String name, @NonNull EStructuralFeature eStructuralFeature) {
			super(name);
			this.eStructuralFeature = eStructuralFeature;
		}

	/*	@Override
		public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			UserElementAnalysis elementAnalysis = dynamicRuleMatch.getElementAnalysis();
			int requiredCount = solve(dynamicRuleMatch);
			int actualCount = elementAnalysis.getSize(eStructuralFeature);
			if (requiredCount != actualCount) {
				return false;
			}
			return true;
		} */

		@Override
		protected @NonNull SerializationMatchTermEStructuralFeatureSize createSizeCardinalityMatchTerm() {
			return new SerializationMatchTermEStructuralFeatureSize(eStructuralFeature);
		}

		@Override
		public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
			return null;
		}

		@Override
		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eStructuralFeature;
		}

		@Override
		public void toString(@NonNull DiagnosticStringBuilder s) {
			s.append(name);
			s.append(": |");
			s.append(SerializationUtils.getName(eStructuralFeature));
			s.append("| = ");
			appendSumOfProducts(s);
		}
	}

	public static class CardinalityProduct
	{
		private final @NonNull List<@NonNull CardinalityVariable> variables;
		private final @NonNull GrammarRuleVector grammarRuleVector;

		public CardinalityProduct(@NonNull List<@NonNull CardinalityVariable> variables, @NonNull GrammarRuleVector grammarRuleVector) {
			this.variables = variables;
			this.grammarRuleVector = grammarRuleVector;
		}

	/*	public boolean gatherUnknownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull Collection<@NonNull CardinalityVariable> unknownVariables) {
			for (@NonNull CardinalityVariable variable : variables) {
				SerializationMatchTerm solution = serializationRuleAnalysis.basicGetMatchTerm(variable);
				if (solution == null) {
					if (unknownVariables == null) {
						unknownVariables = new ArrayList<>();
					}
					if (unknownVariables.contains(variable)) {
						return false;		// Quadratic cannot happen.
					}
					unknownVariables.add(variable);
				}
			}
			return true;
		} */

		public @NonNull GrammarRuleVector getGrammarRuleVector() {
			return grammarRuleVector;
		}

	/*	public @Nullable CardinalityProduct getUnknownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			CardinalityProduct unknownVariables = null;
			for (@NonNull CardinalityVariable variable : variables) {
				SerializationMatchTerm solution = serializationRuleAnalysis.basicGetMatchTerm(variable);
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
		} */

		public @NonNull List<@NonNull CardinalityVariable> getVariables() {
			return variables;
		}

		/**
		 * Simplify a product term returning
		 * - Integer for a constant
		 * - CardinalityVariable for a scaled variable
		 * - Set(CardinalityVariable) for a scaled product of non-quadratic variable
		 * - null for a quadratic variable
		 *
		public Object resolveProduct(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			Set<@NonNull CardinalityVariable> productVariables = null;
			int constantProduct = 1;
			for (@NonNull CardinalityVariable variable : variables) {
				Integer integerSolution = serializationRuleAnalysis.basicGetIntegerSolution(variable.getIndex());		// FIXME constants
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
		} */

	/*	protected int solve(@NonNull DynamicRuleMatch dynamicRuleMatch) {
			int product = 1;
			for (@NonNull CardinalityVariable variable : variables) {
				Integer value = dynamicRuleMatch.basicGetIntegerSolution(variable.getIndex());
				int intValue = value != null ? value.intValue() : 1;
				product *= intValue;
			}
			return product;
		} */

		@Override
		public @NonNull String toString() {
			DiagnosticStringBuilder s = new DiagnosticStringBuilder();
			toString(s);
			return s.toString();
		}

		public void toString(@NonNull DiagnosticStringBuilder s) {
			boolean gotOne = false;
			boolean isFirst = true;
			for (@NonNull CardinalityVariable variable : variables) {
				if (!variable.isOne()) {
					if (!isFirst) {
						s.append(" * ");
					}
					assert !variable.isOne();
					s.appendVariableName(variable.getIndex());
					gotOne = true;
					isFirst = false;
				}
			}
			if (!gotOne) {
				s.append("1");
			}
			s.append(":");
			s.append("{");
			grammarRuleVector.toString(s);
			s.append("}");
		}

		public int size() {
			return variables.size();
		}
	}

	protected final @NonNull String name;

	/**
	 * The cardinalities that determine the production of a particular feature/
	 *
	 * e.g.  { x+=X (',' x+=X)* }? is C0[?] + C0[?] * C1[*]
	 *
	 * NB an empty list for no variables is the consyamt 1 sice unit cardinalities are suppressed.
	 */
	private final @NonNull List<@NonNull CardinalityProduct> sumOfProducts = new ArrayList<>();

	protected CardinalityExpression(@NonNull String name) {
		this.name = name;
	}

	public void addMultiplicityProduct(@NonNull Iterable<@NonNull CardinalityVariable> variables, @NonNull GrammarRuleVector grammarRuleVector) {
		List<@NonNull CardinalityVariable> prunedList = new ArrayList<>();
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
			prunedList.add(variable);
		}
		sumOfProducts.add(new CardinalityProduct(prunedList, grammarRuleVector));
	}

/*	public boolean analyzeConstants(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		CardinalityVariable sumVariable = null;
		int sum = 0;
		int factor = 0;
		for (@NonNull CardinalityProduct products : sumOfProducts) {
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
		ruleMatch.addMatchTerm(sumVariable, solution);
		return true;
	} */

/*	public boolean analyzeConstants(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		CardinalityVariable sumVariable = null;
		int sum = 0;
		int factor = 0;
		for (@NonNull CardinalityProduct products : sumOfProducts) {
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
		ruleMatch.addMatchTerm(sumVariable, solution);
		return true;
	} */

	/*	public boolean analyzeNoVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			for (@NonNull CardinalityProduct products : sumOfProducts) {
				for (@NonNull CardinalityVariable variable : products) {
					CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
					if (!(solution instanceof IntegerCardinalitySolution)) {
						return false;
					}
				}
			}
			return true;
		} */

	/*	public boolean analyzePseudoBooleanFactors(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			CardinalityProduct intersection = computeUnsolvedCommonFactors(ruleMatch);
			if (intersection == null) {
				return false;
			}
			int sum = 0;
			CardinalityVariable productVariable = null;
			for (@NonNull CardinalityProduct product : sumOfProducts) {
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
			for (@NonNull CardinalityProduct products : sumOfProducts) {
				for (@NonNull CardinalityVariable variable : products) {
					if (variable == productVariable) {
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
						if (sum != 0) {
							solution = new SubtractCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
						}
						ruleMatch.addMatchTerm(variable, solution);
					}
					else {
						assert Iterables.contains(intersection, variable);
						CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
						solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
						ruleMatch.addMatchTerm(variable, solution);
					}
				}
			}
			return true;
		} */





	/*	public boolean analyzeConstants(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			CardinalityVariable sumVariable = null;
			int sum = 0;
			int factor = 0;
			for (@NonNull CardinalityProduct products : sumOfProducts) {
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
			CardinalityMatchTerm solution = new FeatureSizeCardinalityMatchTerm(eStructuralFeature, enumerationValue);
			if (sum != 0) {
				solution = new SubtractCardinalityMatchTerm(solution, new IntegerCardinalityMatchTerm(sum));
			}
			if (factor != 1) {
				solution = new DivideCardinalityMatchTerm(solution, new IntegerCardinalityMatchTerm(factor));
			}
			ruleMatch.addMatchTerm(sumVariable, solution);
			return true;
		} */

	/*	public boolean analyzeNoVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			for (@NonNull CardinalityProduct products : sumOfProducts) {
				for (@NonNull CardinalityVariable variable : products) {
					CardinalityMatchTerm solution = ruleMatch.basicGetMatchTerm(variable);
					if (!(solution instanceof IntegerCardinalityMatchTerm)) {
						return false;
					}
				}
			}
			return true;
		} */



/*	public boolean analyzeNoVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		for (@NonNull CardinalityProduct products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				CardinalityMatchTerm solution = ruleMatch.basicGetMatchTerm(variable);
				if (!(solution instanceof IntegerCardinalityMatchTerm)) {
					return false;
				}
			}
		}
		return true;
	} */

	protected void appendSumOfProducts(@NonNull DiagnosticStringBuilder s) {
		boolean isFirst = true;
		for (@NonNull CardinalityProduct product : sumOfProducts) {
			if (!isFirst) {
				s.append(" + ");
			}
			product.toString(s);
			isFirst = false;
		}
	}

/*	public void gatherRuntimeProblems(@NonNull PreSerializer preSerializer,
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unresolvedVariable2expressions,
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unresolvedVariables) {
		for (@NonNull CardinalityProduct products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products) {
				Object solution = preSerializer.getMatchTerm(variable);
				if ((solution == null) || ((solution instanceof MatchTerm) && ((MatchTerm)solution).isRuntime())) {
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
	private @Nullable Set<@NonNull CardinalityVariable> computeUnsolvedCommonFactors(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull CardinalityProduct products : sumOfProducts) {
			Object resolution = resolveProduct(serializationRuleAnalysis, products);
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

	protected abstract @NonNull SerializationMatchTerm createSizeCardinalityMatchTerm();

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
	public @NonNull SerializationMatchTerm createMatchTerm(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @Nullable CardinalityVariable solvedVariable, boolean mayBeMany, @NonNull GrammarRuleVector grammarRuleVector) {
		//
		// Determine slots = constantSumOfProducts + factorSumOfProducts * solvedVariable
		//
		List<@NonNull CardinalityProduct> constantSumOfProducts = new ArrayList<>();
		List<@NonNull CardinalityProduct> factorSumOfProducts = new ArrayList<>();
		for (@NonNull CardinalityProduct product : sumOfProducts) {
			boolean isFactor = false;
			List<@NonNull CardinalityVariable> residualProducts = new ArrayList<>();
			for (@NonNull CardinalityVariable term : product.getVariables()) {
				if ((solvedVariable != null) && (term == solvedVariable)) {
					assert !isFactor;		// Quadratic doesn't happen
					isFactor = true;
				}
				else {
					residualProducts.add(term);
				}
			}
			if (isFactor) {
				factorSumOfProducts.add(new CardinalityProduct(residualProducts, grammarRuleVector));
			}
			else {
				constantSumOfProducts.add(new CardinalityProduct(residualProducts, grammarRuleVector));
			}
		}
		//
		//	Divide out common Boolean factors from non-empty constantSumOfProducts, factorSumOfProducts
		//	or from empty constantSumOfProducts, non-empty factorSumOfProducts
		//	to avoid gratuitous divisions whose result is not actually used.
		//
		Set<@NonNull CardinalityVariable> factorCommonVariables = getKnownCommonVariables(serializationRuleAnalysis, factorSumOfProducts);
		if (factorCommonVariables != null) {
			Set<@NonNull CardinalityVariable> constantCommonVariables = getKnownCommonVariables(serializationRuleAnalysis, constantSumOfProducts);
			for (@NonNull CardinalityVariable commonVariable : factorCommonVariables) {
				if ((mayBeMany || !commonVariable.mayBeMany()) && (constantSumOfProducts.isEmpty() || ((constantCommonVariables != null) && constantCommonVariables.contains(commonVariable)))) {
					for (@NonNull CardinalityProduct constantProduct : constantSumOfProducts) {
						constantProduct.getVariables().remove(commonVariable);
					}
					for (@NonNull CardinalityProduct factorProduct : factorSumOfProducts) {
						factorProduct.getVariables().remove(commonVariable);
					}
				}
			}
		}
		//
		//	Convert the residual constantSumOfProducts, factorSumOfProducts to
		//	solvedVariable = (slots - constantSumOfProducts) / factorSumOfProducts
		//
		SerializationMatchTerm resultMatchTerm = createSizeCardinalityMatchTerm();
		for (@NonNull CardinalityProduct constantProduct : constantSumOfProducts) {
			SerializationMatchTerm sumMatchTerm = null;
			for (@NonNull CardinalityVariable constantTerm : constantProduct.getVariables()) {
				SerializationMatchTerm termMatchTerm = new SerializationMatchTermVariable(constantTerm.getIndex());
				sumMatchTerm = sumMatchTerm != null ? new SerializationMatchTermMultiply(sumMatchTerm, termMatchTerm) : termMatchTerm;
			}
			resultMatchTerm = new SerializationMatchTermSubtract(resultMatchTerm, sumMatchTerm != null ? sumMatchTerm : new SerializationMatchTermInteger(1));
		}
		for (@NonNull CardinalityProduct factorProduct : factorSumOfProducts) {
			SerializationMatchTerm sumMatchTerm = null;
			for (@NonNull CardinalityVariable factorTerm : factorProduct.getVariables()) {
				SerializationMatchTerm termMatchTerm = new SerializationMatchTermVariable(factorTerm.getIndex());
				sumMatchTerm = sumMatchTerm != null ? new SerializationMatchTermMultiply(sumMatchTerm, termMatchTerm) : termMatchTerm;
			}
			if (sumMatchTerm != null) {
				resultMatchTerm = new SerializationMatchTermDivide(resultMatchTerm, sumMatchTerm);
			}
		}
		return resultMatchTerm;
	}

	/*	public boolean analyzeConstants(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			CardinalityVariable sumVariable = null;
			int sum = 0;
			int factor = 0;
			for (@NonNull CardinalityProduct products : sumOfProducts) {
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
			CardinalityMatchTerm solution = new FeatureSizeCardinalityMatchTerm(eStructuralFeature, enumerationValue);
			if (sum != 0) {
				solution = new SubtractCardinalityMatchTerm(solution, new IntegerCardinalityMatchTerm(sum));
			}
			if (factor != 1) {
				solution = new DivideCardinalityMatchTerm(solution, new IntegerCardinalityMatchTerm(factor));
			}
			ruleMatch.addMatchTerm(sumVariable, solution);
			return true;
		} */

	/*	public boolean analyzeNoVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			for (@NonNull CardinalityProduct products : sumOfProducts) {
				for (@NonNull CardinalityVariable variable : products) {
					CardinalityMatchTerm solution = ruleMatch.basicGetMatchTerm(variable);
					if (!(solution instanceof IntegerCardinalityMatchTerm)) {
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
	//	if (cc + dd) or rather a v is optional the divide is redundant not by zero.
	//
	public boolean generateLinear(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, boolean mayBeMany) {
		CardinalityVariable linearVariable = null;
		GrammarRuleVector grammarRuleVector = null;
		for (@NonNull CardinalityProduct product : sumOfProducts) {
			List<@NonNull CardinalityVariable> unknownVariables = getUnknownVariables(serializationRuleAnalysis, product);
			if (unknownVariables == null) {
				// constant is easy
			}
			else if (unknownVariables.size() > 1) {
				return false;		// Two variables is not trivial
			}
			else {
				CardinalityVariable unknownVariable = SerializationUtils.nonNullState(unknownVariables.get(0));
				if (linearVariable == null) {
					linearVariable = unknownVariable;
					grammarRuleVector = product.getGrammarRuleVector();
				}
				else if (linearVariable != unknownVariable) {
					return false;		// Two variables is not trivial
				}
			}
		}
		if (grammarRuleVector == null) {
			grammarRuleVector = sumOfProducts.get(0).getGrammarRuleVector();		// FIXME only valid if homogeneous
		}
		assert grammarRuleVector != null;
		SerializationMatchTerm resultsMatchTerm = createMatchTerm(serializationRuleAnalysis, linearVariable, mayBeMany, grammarRuleVector);
		serializationRuleAnalysis.addMatchTerm(linearVariable, resultsMatchTerm);
		return true;
	}

	//
	//	slots = aa + bb + cc * X + dd * X => X = (slots - (aa + bb)) / (cc + dd)
	//
	//	aa, bb are known, cc, dd may involve unknown terms
	//
	public boolean generateMayBeZeroCommonFactors(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, boolean mayBeMany) {
		Set<@NonNull CardinalityVariable> intersection = getUnknownCommonVariables(serializationRuleAnalysis, sumOfProducts);
		if (intersection  != null) {
			List<@NonNull CardinalityVariable> intersectionList = new ArrayList<>(intersection);
			Collections.sort(intersectionList, SerializationUtils.INDEXED_COMPARATOR);
			for (@NonNull CardinalityVariable cardinalityVariable : intersectionList) {
				if (mayBeMany /*|| !cardinalityVariable.mayBeMany()*/) {
				//	assert cardinalityVariable.mayBeNone();
					SerializationMatchTerm solution = createSizeCardinalityMatchTerm();
					solution = new SerializationMatchTermGreaterThan(solution, new SerializationMatchTermInteger(0));
					serializationRuleAnalysis.addMatchTerm(cardinalityVariable, solution);
					return true;
				}
			}
		}
		return false;
	}

	/*	public boolean analyzeConstants(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			CardinalityVariable sumVariable = null;
			int sum = 0;
			int factor = 0;
			for (@NonNull CardinalityProduct products : sumOfProducts) {
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
			ruleMatch.addMatchTerm(sumVariable, solution);
			return true;
		} */

	/*	public boolean analyzeConstants(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			CardinalityVariable sumVariable = null;
			int sum = 0;
			int factor = 0;
			for (@NonNull CardinalityProduct products : sumOfProducts) {
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
			ruleMatch.addMatchTerm(sumVariable, solution);
			return true;
		} */

		/*	public boolean analyzeNoVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
				for (@NonNull CardinalityProduct products : sumOfProducts) {
					for (@NonNull CardinalityVariable variable : products) {
						CardinalitySolution solution = ruleMatch.basicGetSolution(variable);
						if (!(solution instanceof IntegerCardinalitySolution)) {
							return false;
						}
					}
				}
				return true;
			} */

		/*	public boolean analyzePseudoBooleanFactors(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
				CardinalityProduct intersection = computeUnsolvedCommonFactors(ruleMatch);
				if (intersection == null) {
					return false;
				}
				int sum = 0;
				CardinalityVariable productVariable = null;
				for (@NonNull CardinalityProduct product : sumOfProducts) {
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
				for (@NonNull CardinalityProduct products : sumOfProducts) {
					for (@NonNull CardinalityVariable variable : products) {
						if (variable == productVariable) {
							CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
							if (sum != 0) {
								solution = new SubtractCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
							}
							ruleMatch.addMatchTerm(variable, solution);
						}
						else {
							assert Iterables.contains(intersection, variable);
							CardinalitySolution solution = new FeatureSizeCardinalitySolution(eStructuralFeature, enumerationValue);
							solution = new GreaterThanCardinalitySolution(solution, new IntegerCardinalitySolution(sum));
							ruleMatch.addMatchTerm(variable, solution);
						}
					}
				}
				return true;
			} */

		public boolean generateRedundantProducts(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			Iterable<@NonNull CardinalityVariable> intersection = computeUnsolvedCommonFactors(serializationRuleAnalysis);
			if (intersection != null) {
				return false;
			}
			CardinalityProduct manyProducts = null;
			CardinalityVariable manyVariable = null;
			for (@NonNull CardinalityProduct products : sumOfProducts) {
				boolean mayBeNone = false;
				for (@NonNull CardinalityVariable variable : products.getVariables()) {
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
			for (@NonNull CardinalityProduct products : sumOfProducts) {
				List<@NonNull CardinalityVariable> variableList = new ArrayList<>(products.getVariables());
				Collections.sort(variableList, SerializationUtils.INDEXED_COMPARATOR);
				for (@NonNull CardinalityVariable variable : variableList) {
					if (serializationRuleAnalysis.basicGetMatchTerm(variable) == null) {
						if (products != manyProducts) {
							serializationRuleAnalysis.addMatchTerm(variable, new SerializationMatchTermInteger(0));
						}
						else if (variable == manyVariable) {
							SerializationMatchTerm solution = createSizeCardinalityMatchTerm();
							serializationRuleAnalysis.addMatchTerm(variable, solution);
						}
						else {
							SerializationMatchTerm solution = createSizeCardinalityMatchTerm();
							solution = new SerializationMatchTermGreaterThan(solution, new SerializationMatchTermInteger(0));
							serializationRuleAnalysis.addMatchTerm(variable, solution);
						}
					}
				}
			}
			return true;
		}

	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		throw new IllegalStateException();
	}

	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull GrammarRuleVector grammarRuleVector) {
		throw new IllegalStateException();
	}

	public abstract	@Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions();

	public abstract @NonNull EStructuralFeature getEStructuralFeature();

	protected @Nullable Set<@NonNull CardinalityVariable> getKnownCommonVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull Iterable<? extends @NonNull CardinalityProduct> sumOfProducts2) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull CardinalityProduct product : sumOfProducts2) {
			List<@NonNull CardinalityVariable> knownVariables = getKnownVariables(serializationRuleAnalysis, product);
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

	protected @Nullable List<@NonNull CardinalityVariable> getKnownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull CardinalityProduct product) {
		List<@NonNull CardinalityVariable> knownVariables = null;
		for (@NonNull CardinalityVariable variable : product.getVariables()) {
			SerializationMatchTerm solution = serializationRuleAnalysis.basicGetMatchTerm(variable);
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

	@Override
	public @NonNull String getName() {
		return name;
	}

	protected @Nullable Set<@NonNull CardinalityVariable> getUnknownCommonVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull Iterable<? extends @NonNull CardinalityProduct> sumOfProducts2) {
		Set<@NonNull CardinalityVariable> intersection = null;
		for (@NonNull CardinalityProduct product : sumOfProducts2) {
			List<@NonNull CardinalityVariable> unknownVariables = getUnknownVariables(serializationRuleAnalysis, product);
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
	public @Nullable List<@NonNull CardinalityVariable> getUnknownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		List<@NonNull CardinalityVariable> unknownVariables = null;
		for (@NonNull CardinalityProduct products : sumOfProducts) {
			for (@NonNull CardinalityVariable variable : products.getVariables()) {
				SerializationMatchTerm solution = serializationRuleAnalysis.basicGetMatchTerm(variable);
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

	public @Nullable List<@NonNull CardinalityVariable> getUnknownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull CardinalityProduct product) {
		List<@NonNull CardinalityVariable> unknownVariables = null;
		for (@NonNull CardinalityVariable variable : product.getVariables()) {
			SerializationMatchTerm solution = serializationRuleAnalysis.basicGetMatchTerm(variable);
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

	public boolean isOrderedAndHeterogeneous() {
		// Detect the degenerate E01: |ownedMultiplicity| = V0:{55} + V1:{55} that is soluble on the first term.
		// FIXME integrate this into the overall analysis - it is pessimitic for distinguishable PropertyContextDeclCS content
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull Integer>> variablesToRuleIndexes = new HashMap<>();
		int nonConstantProducts = 0;
		for (CardinalityProduct product : sumOfProducts) {
			List<@NonNull CardinalityVariable> variables = product.getVariables();
			if (variables.size() == 1) {
				nonConstantProducts++;
				for (CardinalityVariable variable : variables) {
					Set<@NonNull Integer> ruleIndexes = SerializationUtils.maybeNull(variablesToRuleIndexes.get(variable));
					if (ruleIndexes == null) {
						ruleIndexes = new HashSet<>();
						variablesToRuleIndexes.put(variable, ruleIndexes);
					}
					ruleIndexes.add(variable.getIndex());
				}
			}
		}
		if ((nonConstantProducts > 1) && (variablesToRuleIndexes.size() > 1)) {
			boolean allUnity = true;
			for (Set<@NonNull Integer> ruleIndexes : variablesToRuleIndexes.values()) {
				if (ruleIndexes.size() != 1) {
					allUnity = false;
					break;
				}
			}
			if (allUnity) {
				return true;
			}
		}
		EStructuralFeature eStructuralFeature = getEStructuralFeature();
		if (!eStructuralFeature.isMany()) {
			return false;
		}
		if (!eStructuralFeature.isOrdered()) {
			return false;
		}
		if (sumOfProducts.size() <= 1) {
			return false;
		}
		GrammarRuleVector homogeneousGrammarRuleVector = null;
		for (@NonNull CardinalityProduct cardinalityProduct : sumOfProducts) {
			GrammarRuleVector grammarRuleVector = cardinalityProduct.getGrammarRuleVector();
			if (homogeneousGrammarRuleVector == null) {
				homogeneousGrammarRuleVector = grammarRuleVector;
			}
			else if (!homogeneousGrammarRuleVector.equals(grammarRuleVector)){
				homogeneousGrammarRuleVector = null;
				break;
			}
		}
		if (homogeneousGrammarRuleVector != null) {
			return false;
		}
		return true;
	}

	/**
	 * Simplify a product term returning
	 * - Integer for a constant
	 * - CardinalityVariable for a scaled variable
	 * - Set(CardinalityVariable) for a scaled product of non-quadratic variable
	 * - null for a quadratic variable
	 */
	public Object resolveProduct(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull CardinalityProduct product) {
		Set<@NonNull CardinalityVariable> productVariables = null;
		int constantProduct = 1;
		for (@NonNull CardinalityVariable variable : product.getVariables()) {
			Integer integerSolution = serializationRuleAnalysis.basicGetIntegerSolution(variable.getIndex());		// FIXME constants
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
		for (@NonNull CardinalityProduct products : sumOfProducts) {
			int product = 1;
			for (@NonNull CardinalityVariable variable : products.getVariables()) {
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
	//		preSerializer.addMatchTerm(variable, runtimeSolution);
	//	}
	}

	@Override
	public @NonNull String toString() {
		DiagnosticStringBuilder s = new DiagnosticStringBuilder();
		toString(s);
		return s.toString();
	}

	public abstract void toString(@NonNull DiagnosticStringBuilder s);
}