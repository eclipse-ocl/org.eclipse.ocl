/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
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
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.values.SymbolicKnownValueImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * A SymbolicHypothesisEvaluationEnvironment defines a symbolic EvaluationEnvironment in which the values
 * of one or more expressions have hypothesized values, which may be exploited by evaluay=tion, but which when
 * recomputed must match their hypothesis else the hypothesis is contradicted and the evluation is invalid.
 *
 * @since 1.15
 */
public class HypothesizedSymbolicEvaluationEnvironment extends AbstractSymbolicEvaluationEnvironment
{
	protected final @NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment;
	protected final @NonNull CSEElement hypothesizedElement;
	private final @NonNull Map<@NonNull TypedElement, @NonNull List<@NonNull SymbolicValue>> expression2constrainingValues = new HashMap<>();
	private final @NonNull Map<@NonNull SymbolicValue, @NonNull List<@NonNull SymbolicValue>> unconstrainedValue2constrainingvalues = new HashMap<>();

	public HypothesizedSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull CSEElement hypothesizedElement) {
		super(symbolicEvaluationEnvironment, (TypedElement)hypothesizedElement.getElement());
		this.symbolicEvaluationEnvironment = symbolicEvaluationEnvironment;
		this.hypothesizedElement = hypothesizedElement;;
	}

/*	@Override
	public void add(@NonNull TypedElement expression, @Nullable Object recomputedValue) {
		if (recomputedValue == null) {
			recomputedValue = ValueUtil.NULL_VALUE;
		}
		Object hypothesizedValue = expression2value.get(expression);
		if (hypothesizedValue != null) {
			if (!getEnvironmentFactory().getIdResolver().oclEquals(hypothesizedValue, recomputedValue)) {
				throw new InvalidValueException("may-be-invalid");		// FIXME use return
			}
		}
	} */

/*	public void addHypothesis(@NonNull OCLExpression hypothesizedExpression, @Nullable Object hypothesizedValue) {
		if (hypothesizedValue == null) {
			hypothesizedValue = ValueUtil.NULL_VALUE;
		}
		expression2value.put(hypothesizedExpression, hypothesizedValue);
	} */

	private void addAffectedExpressions(@NonNull Set<@NonNull TypedElement> expressions, @NonNull TypedElement expression) {
		if (expressions.add(expression)) {
			// all VariableExps for a variable
			EObject eContainer = expression.eContainer();
			if ((eContainer instanceof TypedElement) && !(eContainer instanceof Feature)) {
				addAffectedExpressions(expressions, (TypedElement)eContainer);
			}
		}
	}

	public @NonNull CSEElement getHypothesizedElement() {
		return hypothesizedElement;
	}

	/*	@Override
	public @Nullable Object getValueOf(@NonNull TypedElement variable) {
		Object variableValue = super.getValueOf(variable);
		if (variableValue instanceof SymbolicValue) {
			SymbolicValue symbolicValue = (SymbolicValue)variableValue;
			Iterable<@Nullable Object> constraints = getSymbolicConstraints(variable, null);
			if (constraints != null) {
				boolean mayBeInvalid = true;
				boolean mayBeNull = true;
				for (@Nullable Object constraint : constraints) {
					if (!ValueUtil.mayBeInvalid(constraint)) {
						mayBeInvalid = false;
					}
					if (!ValueUtil.mayBeNull(constraint)) {
						mayBeNull = false;
					}
				}
				if ((mayBeInvalid != symbolicValue.mayBeInvalid()) || (mayBeNull != symbolicValue.mayBeNull())) {
					return new SymbolicVariableValueImpl((VariableDeclaration) variable, mayBeNull, mayBeInvalid);
				}
			}
		}
		return variableValue;
	} */

	public boolean isContradiction() {
		Set<@NonNull TypedElement> expressionsSet = new HashSet<>();
		for (@NonNull TypedElement expression : expression2constrainingValues.keySet()) {
			addAffectedExpressions(expressionsSet, expression);
		}
		List<@NonNull TypedElement> expressionsList = new ArrayList<>(expressionsSet);


		// Add all ancestral expressions
		if (expressionsList.size() > 1) {
			Collections.sort(expressionsList, ((SymbolicAnalysis)getSymbolicExecutor()).getHeightComparator());
		}
		for (@NonNull TypedElement expression : expressionsList) {
			List<@NonNull SymbolicValue> constrainingValues = expression2constrainingValues.get(expression);
			assert constrainingValues != null;
			SymbolicValue constrainedValue = symbolicEvaluate(expression);
			for (@NonNull SymbolicValue constrainingValue : constrainingValues) {
				if (!constrainedValue.equals(constrainingValue)) {
					return true;
				}
			}
		}
	//	hypothesizedElement.acc
	/*	for (ConstrainedSymbolicEvaluationEnvironment evaluationEnvironment = constrainedSymbolicEvaluationEnvironment; evaluationEnvironment != null; evaluationEnvironment = evaluationEnvironment.getParent()) {
			ConstrainedSymbolicEvaluationEnvironment constrainedSymbolicEvaluationEnvironment = evaluationEnvironment;
			for (@NonNull OCLExpression constrainedExpression : constrainedSymbolicEvaluationEnvironment.getConstrainedExpressions()) {
				SymbolicValue constrainingValue = constrainedSymbolicEvaluationEnvironment.getConstrainingValue(constrainedExpression);
				SymbolicValue constrainedValue = symbolicAnalysis.symbolicEvaluate(constrainedExpression);
				if (!constrainedValue.equals(constrainingValue)) {
					return true;
				}
			}
		} */
		return false;
	}

	/**
	 * Install the control path constraints that ensure that expression is executable as part of the hypothesis.
	 * ana//implies guards are set true, or guards false and if conditions true/false as appropriate.
	 */
	public void putHypothesizedTerm(@NonNull OCLExpression expression) {
		EObject eContainer = expression.eContainer();
		OCLExpression constrainedExpression = null;
		SymbolicValue symbolicPathValue = null;
		Boolean symbolicKnownValue = null;
		if (eContainer instanceof IfExp) {
			IfExp ifExp = (IfExp)eContainer;
			constrainedExpression = PivotUtil.getOwnedCondition(ifExp);
			symbolicPathValue = symbolicEvaluationEnvironment.getSymbolicValue2(constrainedExpression);
			if (expression == ifExp.getOwnedThen()) {
				symbolicKnownValue = Boolean.TRUE;
			}
			else if (expression == ifExp.getOwnedElse()) {
				symbolicKnownValue = Boolean.FALSE;
			}
		}
		else if (eContainer instanceof OperationCallExp) {
			OperationCallExp operationCallExp = (OperationCallExp)eContainer;
			List<OCLExpression> ownedArguments = operationCallExp.getOwnedArguments();
			if (ownedArguments.size() == 1) {
				OCLExpression argument = ownedArguments.get(0);
				if (expression == argument) {
					constrainedExpression = PivotUtil.getOwnedSource(operationCallExp);
					symbolicPathValue = symbolicEvaluationEnvironment.getSymbolicValue2(constrainedExpression);
					Operation operation = operationCallExp.getReferredOperation();
					LibraryFeature implementation = operation.getImplementation();
					if ((implementation instanceof BooleanAndOperation) || (implementation instanceof BooleanAndOperation2)) {
						symbolicKnownValue = Boolean.TRUE;
					}
					else if ((implementation instanceof BooleanImpliesOperation) || (implementation instanceof BooleanImpliesOperation2)) {
						symbolicKnownValue = Boolean.TRUE;
					}
					else if ((implementation instanceof BooleanOrOperation) || (implementation instanceof BooleanOrOperation2)) {
						symbolicKnownValue = Boolean.FALSE;
					}
				}
			}
		}
		if ((constrainedExpression != null) && (symbolicPathValue != null) && (symbolicKnownValue != null)) {
			SymbolicKnownValueImpl constrainingValue = new SymbolicKnownValueImpl(TypeId.BOOLEAN, symbolicKnownValue);
			putHypothesizedValue(constrainedExpression, constrainingValue);
			putHypothesizedValue(symbolicPathValue, constrainingValue);
		}
		if (eContainer instanceof OCLExpression) {
			putHypothesizedTerm((OCLExpression)eContainer);
		}
	}

	public void putHypothesizedValue(@NonNull TypedElement expression, @NonNull SymbolicValue constrainingValue) {
		SymbolicValue symbolicValue = symbolicEvaluationEnvironment.basicGetSymbolicValue(expression);
		List<@NonNull SymbolicValue> constrainingValues = expression2constrainingValues.get(expression);
		if (constrainingValues == null) {
			constrainingValues = new ArrayList<>();
			expression2constrainingValues.put(expression, constrainingValues);
		}
		constrainingValues.add(constrainingValue);
	}

	public void putHypothesizedValue(@NonNull SymbolicValue unconstrainedValue, @NonNull SymbolicValue constrainingValue) {
		List<@NonNull SymbolicValue> constrainingValues = unconstrainedValue2constrainingvalues.get(unconstrainedValue);
		if (constrainingValues == null) {
			constrainingValues = new ArrayList<>();
			unconstrainedValue2constrainingvalues.put(unconstrainedValue, constrainingValues);
		}
		constrainingValues.add(constrainingValue);
		/* 	if (constrainingElement instanceof VariableExp) {
			VariableDeclaration constrainedVariable = PivotUtil.getReferredVariable((VariableExp)constrainingElement);
			add(constrainedVariable, hypothesizedValue);
		}
		else if (constrainingElement instanceof NavigationCallExp) {
			OCLExpression constrainedSource = PivotUtil.getOwnedSource((NavigationCallExp)constrainingElement);
			SymbolicValue constrainingSourceValue = new SymbolicUnknownValue(constrainedSource.getTypeId(), )
			SymbolicValue constrainedSourceValue = putHypothesizedValue(@NonNull TypedElement constrainingElement, @NonNull SymbolicValue hypothesizedValue) {
			throw new UnsupportedOperationException();
		}
		else if (constrainingElement instanceof OperationCallExp) {
			throw new UnsupportedOperationException();
		}
		else if (constrainingElement instanceof LoopExp) {
			throw new UnsupportedOperationException();
		}
		else {
			throw new UnsupportedOperationException();
		} */
	}

	public void resolveHypothesis(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment) {
		for (Entry<@NonNull SymbolicValue, @NonNull List<@NonNull SymbolicValue>> entry : unconstrainedValue2constrainingvalues.entrySet()) {
			SymbolicValue constrainedValue = entry.getKey();
			List<@NonNull SymbolicValue> constrainingValues = entry.getValue();
		//	for (@NonNull OCLExpression constrainedExpression : constrainedEvaluationEnvironment.getConstrainedExpressions()) {
		//		SymbolicValue constrainingValue = constrainedEvaluationEnvironment.getConstrainingValue(constrainedExpression);
		//		evaluationEnvironment.add(constrainedExpression, constrainingValue);
		//	}
		}
	//	for (ConstrainedSymbolicEvaluationEnvironment constrainedEvaluationEnvironment = constrainedSymbolicEvaluationEnvironment; constrainedEvaluationEnvironment != null; constrainedEvaluationEnvironment = constrainedEvaluationEnvironment.getParent()) {
	//		for (@NonNull OCLExpression constrainedExpression : constrainedEvaluationEnvironment.getConstrainedExpressions()) {
	//			SymbolicValue constrainingValue = constrainedEvaluationEnvironment.getConstrainingValue(constrainedExpression);
	//			evaluationEnvironment.add(constrainedExpression, constrainingValue);
	//		}
	//	}
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		List<@NonNull SymbolicValue> unconstrainedValues = new ArrayList<>(unconstrainedValue2constrainingvalues.keySet());
	//	if (unconstrainedValues.size() > 1) {
	//		Collections.sort(unconstrainedValues, NameUtil.NAMEABLE_COMPARATOR);
	//	}
		s.append("hypotheses for '" + hypothesizedElement + /*"' in '" + hypothesizedElement.eContainer() +*/ "'");
		for (@NonNull SymbolicValue unconstrainedValue : unconstrainedValues) {
			StringUtil.appendIndentation(s, depth+1);
			s.append(unconstrainedValue + " => ");
			List<@NonNull SymbolicValue> constrainingvalues = unconstrainedValue2constrainingvalues.get(unconstrainedValue);
			assert constrainingvalues != null;
			for (@NonNull SymbolicValue constrainingValue : constrainingvalues) {
				StringUtil.appendIndentation(s, depth+2);
				s.append(constrainingValue);
			}
		}
	//	StringUtil.appendIndentation(s, depth);
	//	basicSymbolicEvaluationEnvironment.toString(s, depth+1);
	}

	@Override
	public @NonNull SymbolicValue traceSymbolicValue(@NonNull TypedElement expression, @NonNull SymbolicValue symbolicValue) {
		List<@NonNull SymbolicValue> constrainingValues = expression2constrainingValues.get(expression);
		if (constrainingValues != null) {
			for (@NonNull SymbolicValue constrainingValue : constrainingValues) {
				if (!symbolicValue.equals(constrainingValue)) {
					return super.traceSymbolicValue(expression, constrainingValue);
				}
			}
		}
		return super.traceSymbolicValue(expression, symbolicValue);
	}
}
