/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * A BaseSymbolicEvaluationEnvironment supports the control-blind symbolic evaluation initially to associate a
 * SymbolicValue with each CSEElement.
 *
 * The initial analyze() populates the cseElement2symbolicValue with the immutable control independent symbolic value
 * of each CSE.
 *
 * Subsequently reanalyze() assesses a hypothesis for a particular typed element and if contradicted may
 * populate typedElement2refinedSymbolicValue with a more precise value for the typed element.
 *
 * @since 1.16
 */
public class BaseSymbolicEvaluationEnvironment extends AbstractSymbolicEvaluationEnvironment
{
	protected final @NonNull ExpressionInOCL expressionInOCL;

	/**
	 * The known immutable control-blind (symbolic) value of each common sub-expression element.
	 */
	private @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> cseElement2symbolicValue = new HashMap<>();

	/**
	 * The refined symbolic values established after contradicting a hypothesis.
	 */
	private @Nullable Map<@NonNull TypedElement, @NonNull SymbolicValue> typedElement2refinedSymbolicValue = null;

	/**
	 * Non-null while reanalyze() is assessing a hypothesis for a typed element.
	 */
	private @Nullable HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = null;

	public BaseSymbolicEvaluationEnvironment(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull ExpressionInOCL expressionInOCL) {
		super(symbolicAnalysis);
		this.expressionInOCL = expressionInOCL;
	}

	public void analyze(@Nullable Object selfObject, @Nullable Object resultObject, @Nullable Object @Nullable [] parameters) {
		//
		//	Initialize self/context parameter
		//
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			initParameter(contextVariable, selfObject);
		}
		//
		//	Initialize result parameter
		//
		Variable resultVariable = expressionInOCL.getOwnedResult();
		if (resultVariable != null) {
			initParameter(resultVariable, resultObject);
		}
		//
		//	Initialize other parameters
		//
		int i = 0;
		assert parameters != null;
		for (@NonNull Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
			initParameter(parameterVariable, parameters[i++]);
		}
		//
		//	Analyze each typed element in transitive bottom up order.
		//
		List<@NonNull TypedElement> typedElements = new ArrayList<>();
		for (@NonNull EObject eObject : new TreeIterable(expressionInOCL, true)) {
			if (eObject instanceof TypedElement) {
				typedElements.add((TypedElement) eObject);
			}
		}
		Collections.sort(typedElements, cseAnalysis.getTypedElementHeightComparator());
		for (@NonNull TypedElement typedElement : typedElements) {
			symbolicEvaluate(typedElement, true);				// Multi-TypedElement CSEs re-use per-CSE cache
		}
	}

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull TypedElement element) {
		if (typedElement2refinedSymbolicValue != null) {
			SymbolicValue refinedSymbolicValue = typedElement2refinedSymbolicValue.get(element);
			if (refinedSymbolicValue != null) {
				return refinedSymbolicValue;
			}
		}
		CSEElement cseElement = symbolicAnalysis.getCSEElement(element);
		return cseElement2symbolicValue.get(cseElement);
	}

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement) {
		return cseElement2symbolicValue.get(cseElement);
	}

	/**
	 * Install the control path constraints that ensure that typedElement is executable as part of the hypothesis.
	 */
	private void gatherAffectedTypedElements(@NonNull Set<@NonNull TypedElement> affectedExpressions, @NonNull TypedElement typedElement) {
		if (typedElement instanceof VariableDeclaration) {
			CSEElement variableCSE = symbolicAnalysis.getCSEElement(typedElement);
			for (@NonNull Element element : variableCSE.getElements()) {
				if (element instanceof VariableExp) {
					gatherAffectedTypedElements(affectedExpressions, (VariableExp)element);
				}
			}
		}
		else if (typedElement instanceof VariableExp) {
			OCLExpression expression = (VariableExp)typedElement;
			EObject eContainer = expression.eContainer();
			if ((eContainer instanceof OCLExpression) || (eContainer instanceof ExpressionInOCL) || (eContainer instanceof VariableDeclaration)) {
				assert eContainer != null;
				gatherAffectedTypedElements(affectedExpressions, (TypedElement)eContainer);
			}
		}
		else if (typedElement instanceof OCLExpression) {
			OCLExpression expression = (OCLExpression)typedElement;
			if (affectedExpressions.add(expression) ) {
				EObject eContainer = expression.eContainer();
				if ((eContainer instanceof OCLExpression) || (eContainer instanceof ExpressionInOCL) || (eContainer instanceof VariableDeclaration)) {
					assert eContainer != null;
					gatherAffectedTypedElements(affectedExpressions, (TypedElement)eContainer);
				}
			}
		}
		else if (typedElement instanceof ExpressionInOCL) {
			ExpressionInOCL expression = (ExpressionInOCL)typedElement;
			affectedExpressions.add(expression);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	protected @NonNull Iterable<@NonNull TypedElement> getAffectedTypedElements(@NonNull TypedElement typedElement) {
		CSEElement cseElement = symbolicAnalysis.getCSEElement(typedElement);
		return cseElement.getElements();
	}

	@Override
	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return this;
	}

	public @NonNull Set<@NonNull CSEElement> getCSEElements() {
		return cseElement2symbolicValue.keySet();
	}

	public @NonNull SymbolicEvaluationEnvironment getSymbolicEvaluationEnvironment() {
		return hypothesizedSymbolicEvaluationEnvironment != null ? hypothesizedSymbolicEvaluationEnvironment : this;
	}

	protected @NonNull SymbolicValue initParameter(@NonNull Variable parameter, @Nullable Object value) {
		CSEElement cseElement = cseAnalysis.getCSEElement(parameter);
		SymbolicValue symbolicValue;
		if (value instanceof SymbolicValue) {
			symbolicValue = (SymbolicValue) value;
		}
		else {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(value);
			symbolicValue = getKnownValue(boxedValue);
		}
		return setSymbolicValue(cseElement, symbolicValue);
	}

	public @Nullable String reanalyze(@NonNull Hypothesis hypothesis, @NonNull TypedElement typedElement) {
		if (typedElement2refinedSymbolicValue == null) {
			typedElement2refinedSymbolicValue = new HashMap<>();
		}
		assert this.hypothesizedSymbolicEvaluationEnvironment == null;
		this.hypothesizedSymbolicEvaluationEnvironment = symbolicAnalysis.createHypothesizedSymbolicEvaluationEnvironment(hypothesis, typedElement);
		String incompatibility = hypothesizedSymbolicEvaluationEnvironment.analyze();
		assert this.hypothesizedSymbolicEvaluationEnvironment != null;
		hypothesizedSymbolicEvaluationEnvironment = null;
		return incompatibility;
	}

	public void refineSymbolicValue(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("    refined: " + symbolicValue);
		}
	//	Hypothesis hypothesis = refinedValue.getHypothesis();
	//	assert expression == hypothesis.getExpression();
	//	toString();		// XXX
		assert typedElement2refinedSymbolicValue != null;
		SymbolicValue old = typedElement2refinedSymbolicValue.put(typedElement, symbolicValue);
		if (old != null) {
	//		assert refinedValue.getBaseValue() == old.getBaseValue();
			// XXX verify that refined Value is stronger
		}
		Set<@NonNull TypedElement> affectedExpressionsSet = new HashSet<>();
		gatherAffectedTypedElements(affectedExpressionsSet, typedElement);
		List<@NonNull TypedElement> affectedExpressionsList = new ArrayList<>(affectedExpressionsSet);
		Collections.sort(affectedExpressionsList, cseAnalysis.getTypedElementHeightComparator());
		for (@NonNull TypedElement affectedExpression : affectedExpressionsList) {
			SymbolicValue oldValue = getSymbolicValue(affectedExpression);
			if (affectedExpression != typedElement) {
				if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
					SymbolicAnalysis.HYPOTHESIS.println("   re-evaluating: " + SymbolicUtil.printPath(affectedExpression));
				}
					if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
					SymbolicAnalysis.HYPOTHESIS.println("    old: " + oldValue);
				}
				SymbolicValue newValue = symbolicReEvaluate(affectedExpression);
				if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
					SymbolicAnalysis.HYPOTHESIS.println("    new: " + newValue);
				}
			}
		}
	}

	@Override
	public void setDead(@NonNull OCLExpression expression) {
		for (@NonNull EObject eObject : new TreeIterable(expression, true)) {
			if (eObject instanceof OCLExpression) {
				TypedElement typedElement = (TypedElement)eObject;
				SymbolicValue symbolicValue = getSymbolicValue(typedElement);
				if (!symbolicValue.isDead()) {
					symbolicValue = AbstractSymbolicRefinedValue.createDeadValue(symbolicValue);
					Map<@NonNull TypedElement, @NonNull SymbolicValue> typedElement2refinedSymbolicValue2 = typedElement2refinedSymbolicValue;
					if (typedElement2refinedSymbolicValue2 == null) {
						typedElement2refinedSymbolicValue = typedElement2refinedSymbolicValue2 = new HashMap<>();
					}
					typedElement2refinedSymbolicValue2.put(typedElement, symbolicValue);
				}
			}
		}
	}

	@Override
	public @NonNull SymbolicValue setSymbolicValue(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		CSEElement cseElement = symbolicAnalysis.getCSEElement(typedElement);
		return setSymbolicValue(cseElement, symbolicValue);
	}

	protected @NonNull SymbolicValue setSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue symbolicValue) {
		SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);
		assert (old == null) || (old == symbolicValue); //old.equals(symbolicValue);
		return symbolicValue;
	}

	@Override
	public final @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement typedElement) {
		return symbolicEvaluate(typedElement, false);
	}

	protected @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement typedElement, boolean showReUse) {
		SymbolicValue symbolicValue = basicGetSymbolicValue(typedElement);			// Re-use old value
		if (symbolicValue != null) {
			if (showReUse && SymbolicAnalysis.HYPOTHESIS.isActive()) {
				SymbolicAnalysis.HYPOTHESIS.println("  re-used: " + SymbolicUtil.printPath(typedElement) + " as: " + symbolicValue);
			}
			return symbolicValue;
		}
		SymbolicValue resultValue;
		try {
			resultValue = typedElement.accept(symbolicEvaluationVisitor);
		}
		catch (InvalidValueException e) {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(e);
			resultValue = getKnownValue(boxedValue);
		}
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("  evaluated: " + SymbolicUtil.printPath(typedElement) + " as: " + resultValue);
		}
		return setSymbolicValue(typedElement, resultValue);								// Record new value
	}

	public @NonNull SymbolicValue symbolicReEvaluate(@NonNull TypedElement typedElement) {
		SymbolicValue unrefinedValue = getSymbolicValue(typedElement);			// Get the unrefined value
		SymbolicValue resultValue;
		try {
			resultValue = typedElement.accept(symbolicEvaluationVisitor);
		}
		catch (InvalidValueException e) {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(e);
			resultValue = getKnownValue(boxedValue);
		}
		SymbolicValue refinedValue = resultValue.asRefinementOf(unrefinedValue);
		assert typedElement2refinedSymbolicValue != null;
		SymbolicValue old = typedElement2refinedSymbolicValue.put(typedElement, refinedValue);
		if (old != null) {
			assert old == unrefinedValue;
		}
		return refinedValue;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
	//	super.toString(s);
		StringUtil.appendIndentation(s, 0);
	/*	List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
		if (keys.size() > 1) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
		s.append("\t" + keys.size() + " cses");
		for (@NonNull CSEElement key : keys) {
			Object value = cseElement2symbolicValue.get(key);
			s.append("\n\t\t" + key + " => " + value);
		} */
		Map<@NonNull TypedElement, @NonNull SymbolicValue> typedElement2refinedSymbolicValue2 = typedElement2refinedSymbolicValue;
		if (typedElement2refinedSymbolicValue2 != null) {
			List<@NonNull TypedElement> refinedKeys = new ArrayList<>(typedElement2refinedSymbolicValue2.keySet());
			if (refinedKeys.size() > 0) {
				if (refinedKeys.size() > 1) {
					Collections.sort(refinedKeys, NameUtil.TO_STRING_COMPARATOR);
				}
				StringUtil.appendIndentation(s, 0);
				s.append("\t" + refinedKeys.size() + " refined");
				for (@NonNull TypedElement refinedKey : refinedKeys) {
					Object value = typedElement2refinedSymbolicValue2.get(refinedKey);
					s.append("\n\t\t" + refinedKey + " => " + value);
				}
			}
		}
	}
}
