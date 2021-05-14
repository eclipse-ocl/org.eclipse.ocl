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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
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
	protected final @NonNull Hypothesis hypothesis;
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;

	/**
	 * All CSEs for a stronger value may be used while assessing the the hypothesis.
	 */
//	private final @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> cseElement2constrainingValue = new HashMap<>();
	private final @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> primaryCSE2constrainingValue = new HashMap<>();
	private final @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> corollaryCSE2constrainingValue = new HashMap<>();

	/**
	 * Expressions and Variables from which the hypothesis requires a re-evaluation.
	 *
	 * NB. While each common sub-expression has a unique value for a given hypothesis, it must be computed wrt
	 * the expression that is being hypothesized to ensure that control path constraints are observed.
	 */
	private final @NonNull Set<@NonNull TypedElement> affectedTypedElements = new HashSet<>();

	public HypothesizedSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull Hypothesis hypothesis) {
		super(symbolicEvaluationEnvironment, hypothesis.getExpression());
		this.symbolicEvaluationEnvironment = symbolicEvaluationEnvironment;
		this.hypothesis = hypothesis;
		this.symbolicAnalysis = getSymbolicAnalysis();
	}

	/**
	 * Install the control path constraints that ensure that expression is executable wrt its containingTypedElement.
	 * </br>'and'/'implies' guards are set true
	 * </br>'or' guards false
	 * </br>'if' conditions true/false as appropriate.
	 * </br>navigation sources not-invalid
	 */
	private void addAffectedControlExpressions(@NonNull EObject containingTypedElement, @NonNull OCLExpression expression) {
		OCLExpression constrainedExpression = null;
		SymbolicValue symbolicConstrainedValue = null;
		Boolean symbolicKnownValue = null;
		if (containingTypedElement instanceof IfExp) {
			IfExp ifExp = (IfExp)containingTypedElement;
			constrainedExpression = PivotUtil.getOwnedCondition(ifExp);
//		symbolicPathValue = symbolicEvaluationEnvironment.getSymbolicValue2(constrainedExpression);
			if (expression == ifExp.getOwnedThen()) {
				symbolicKnownValue = Boolean.TRUE;
			}
			else if (expression == ifExp.getOwnedElse()) {
				symbolicKnownValue = Boolean.FALSE;
			}
		}
		else if (containingTypedElement instanceof OperationCallExp) {
			OperationCallExp operationCallExp = (OperationCallExp)containingTypedElement;
			constrainedExpression = PivotUtil.getOwnedSource(operationCallExp);
			List<OCLExpression> ownedArguments = operationCallExp.getOwnedArguments();
			if (ownedArguments.size() == 1) {
				OCLExpression argument = ownedArguments.get(0);
				if (expression == argument) {
			//		symbolicPathValue = symbolicEvaluationEnvironment.getSymbolicValue2(constrainedExpression);
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
			// XXX isSafe
			if (symbolicKnownValue == null) {
				CSEElement cseElement = getSymbolicAnalysis().getCSEElement(constrainedExpression);
				SymbolicValue baseSymbolicValue = getBaseSymbolicValue(cseElement);
				symbolicConstrainedValue = baseSymbolicValue; // new SymbolicUnknownValueImpl(operationCallExp.getTypeId(), operationCallExp.isIsSafe(), false);
			}
		}
		else if (containingTypedElement instanceof NavigationCallExp) {
			NavigationCallExp navigationCallExp = (NavigationCallExp)containingTypedElement;
			constrainedExpression = PivotUtil.getOwnedSource(navigationCallExp);
			// XXX isSafe
			CSEElement cseElement = getSymbolicAnalysis().getCSEElement(constrainedExpression);
			SymbolicValue baseSymbolicValue = getBaseSymbolicValue(cseElement);
			symbolicConstrainedValue = baseSymbolicValue; // new SymbolicUnknownValueImpl(navigationCallExp.getTypeId(), navigationCallExp.isIsSafe(), false);
		}
		else if (containingTypedElement instanceof LoopExp) {
			LoopExp loopExp = (LoopExp)containingTypedElement;
			if (expression == loopExp.getOwnedBody()) {
			//	constrainedExpression = PivotUtil.getOwnedSource(loopExp);
			//	symbolicPathValue = symbolicEvaluationEnvironment.getSymbolicValue2(constrainedExpression);
			// XXX	symbolicKnownValue = SIZE_NOT_EMPTY;
				// XXX isSafe
			}
			constrainedExpression = PivotUtil.getOwnedSource(loopExp);
			// XXX isSafe
			CSEElement cseElement = getSymbolicAnalysis().getCSEElement(constrainedExpression);
			SymbolicValue baseSymbolicValue = getBaseSymbolicValue(cseElement);
			symbolicConstrainedValue = baseSymbolicValue; // new SymbolicUnknownValueImpl(loopExp.getTypeId(), loopExp.isIsSafe(), false);
		}
	/*	else if (containingTypedElement instanceof Variable) {
			Variable yariable = (Variable)containingTypedElement;
			if (expression == yariable.getOwnedInit()) {
				CSEElement variableCSE = symbolicAnalysis.getCSEElement(yariable);
				Iterable<@NonNull OCLExpression> outputs = variableCSE.getOutputs();
				if (outputs != null) {
					for (@NonNull OCLExpression variableExp : outputs) {
						addAffectedTypedElements(variableExp, false);
					}
				}
			}
		} */
		if (constrainedExpression != null) {
			if (symbolicKnownValue != null) {
				assert symbolicConstrainedValue == null;
				symbolicConstrainedValue = getKnownValue(symbolicKnownValue);
			}
		//	symbolicConstrainedValue = new SymbolicUnknownValueImpl(expression.getTypeId(), false, false);


		//	SymbolicKnownValueImpl constrainingValue = new SymbolicKnownValueImpl(TypeId.BOOLEAN, symbolicKnownValue);
		//	putHypothesizedValue(symbolicAnalysis.getCSEElement(constrainedExpression), constrainingValue);
		//	putHypothesizedValue(symbolicPathValue, constrainingValue);
		}
		if ((constrainedExpression != null) && (symbolicConstrainedValue != null)) {
			putHypothesizedValue(constrainedExpression, symbolicConstrainedValue, false);
		}
	}

	/**
	 * Install the control path constraints that ensure that typedElement is executable as part of the hypothesis.
	 */
	private void addAffectedTypedElements(@NonNull TypedElement typedElement, boolean isHypothesisPath) {
		if (affectedTypedElements.add(typedElement) ) {
			EObject eContainer = typedElement.eContainer();
			if ((eContainer instanceof OCLExpression) || (eContainer instanceof ExpressionInOCL) || (eContainer instanceof VariableDeclaration)) {
				TypedElement containingTypedElement = (TypedElement)eContainer;
				assert containingTypedElement != null;
				addAffectedTypedElements(containingTypedElement, isHypothesisPath);
				if (isHypothesisPath && (typedElement instanceof OCLExpression)) {
					addAffectedControlExpressions(containingTypedElement, (OCLExpression)typedElement);
				}
			}
			if (typedElement instanceof VariableExp) {							// If a VariableExp is affected, all accesses to that variable must be re-evaluated.
				VariableExp variableExp = (VariableExp)typedElement;
				CSEElement variableCSE = symbolicAnalysis.getCSEElement(PivotUtil.getReferredVariable(variableExp));
				Iterable<@NonNull OCLExpression> variableExps = variableCSE.getOutputs();
				if (variableExps != null) {
					for (@NonNull OCLExpression aVariableExp : variableExps) {
						if (aVariableExp != variableExp) {
							addAffectedTypedElements(aVariableExp, false);
						}
					}
				}
			}
		}
	}

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull TypedElement element) {
		CSEElement cseElement = getSymbolicAnalysis().getCSEElement(element);
		SymbolicValue symbolicValue = primaryCSE2constrainingValue.get(cseElement);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		symbolicValue = corollaryCSE2constrainingValue.get(cseElement);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		return super.basicGetSymbolicValue(element);
	}

	@Override
	protected @NonNull AbstractSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return getParent().getBaseSymbolicEvaluationEnvironment();
	}

	@Override
	public @NonNull AbstractSymbolicEvaluationEnvironment getParent() {
		return ClassUtil.nonNullState(super.getParent());
	}

	@Override
	public @NonNull SymbolicValue getSymbolicValue(@NonNull TypedElement element) {
		CSEElement cseElement = getSymbolicAnalysis().getCSEElement(element);
		SymbolicValue symbolicValue = primaryCSE2constrainingValue.get(cseElement);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		symbolicValue = corollaryCSE2constrainingValue.get(cseElement);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		return super.getSymbolicValue(element);
	}

	public boolean isContradiction() {
		List<@NonNull TypedElement> typedElementsList = new ArrayList<>(affectedTypedElements);
		if (typedElementsList.size() > 1) {
			Collections.sort(typedElementsList, symbolicAnalysis.getTypedElementHeightComparator());
		}
		for (@NonNull TypedElement typedElement : typedElementsList) {
			CSEElement cseElement = symbolicAnalysis.getCSEElement(typedElement);
			SymbolicValue constrainingValue = corollaryCSE2constrainingValue.get(cseElement);
		//	assert constrainingValue != null;
			SymbolicValue constrainedValue = symbolicEvaluate(typedElement);
			//	assert constrainedValue == constrainingValue;
			SymbolicValue constrainedValue2 = symbolicEvaluate(typedElement);		// XXX debugging
			assert constrainedValue == constrainedValue2;
			SymbolicValue reevaluatedValue = super.getSymbolicValue(typedElement);
			if (!constrainedValue.equals(reevaluatedValue)) {
				return true;
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

	public void putHypothesizedValue(@NonNull OCLExpression expression, @NonNull SymbolicValue constrainingValue) {
		CSEElement cseElement = symbolicAnalysis.getCSEElement(expression);
		SymbolicValue old = primaryCSE2constrainingValue.put(cseElement, constrainingValue);
	//	putSymbolicValue(cseElement, constrainingValue);					// Avoid symbolicEvaluate re-evaluating
		putHypothesizedValue(expression, constrainingValue, true);
	}

	private void putHypothesizedValue(@NonNull OCLExpression expression, @NonNull SymbolicValue constrainingValue, boolean isHypothesisPath) {
		CSEElement cseElement = symbolicAnalysis.getCSEElement(expression);
		SymbolicValue baseSymbolicValue = getBaseSymbolicValue(cseElement);
		SymbolicValue mergedValue = symbolicAnalysis.mergeValue(baseSymbolicValue, constrainingValue);
	//	putSymbolicValue(cseElement, constrainingValue);					// Avoid symbolicEvaluate re-evaluating
		SymbolicValue old = corollaryCSE2constrainingValue.put(cseElement, mergedValue);
		assert old == null;			// XXX maybe merge
		if (expression instanceof VariableExp) {
			VariableDeclaration variable = PivotUtil.getReferredVariable((VariableExp)expression);
			CSEElement cseElement2 = symbolicAnalysis.getCSEElement(variable);
			assert cseElement2 == cseElement;				// Design principle: VariableExps do not have distinct CSEs from their variable
		}
		addAffectedTypedElements(expression, isHypothesisPath);
	}

	public void resolveHypothesis(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment) {
		for (Entry<@NonNull CSEElement, @NonNull SymbolicValue> entry : primaryCSE2constrainingValue.entrySet()) {
			@NonNull CSEElement cseElement = entry.getKey();
			@NonNull SymbolicValue constrainingValue = entry.getValue();
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

	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement typedElement) {
		SymbolicValue symbolicValue = super.basicGetSymbolicValue(typedElement);			// Re-use old value
		if (symbolicValue == null) {
			CSEElement cseElement = getSymbolicAnalysis().getCSEElement(typedElement);
			Object result = primaryCSE2constrainingValue.get(cseElement);
			if (result == null) {
				try {
					EvaluationVisitor undecoratedVisitor = getUndecoratedVisitor();
					result = typedElement.accept(undecoratedVisitor);
				}
				catch (InvalidValueException e) {
					result = e;
				}
			}
			SymbolicValue traceValue = traceValue(cseElement, result);		// Record re-evaluated value
		}
		return getSymbolicValue(typedElement);							// Return hypothesisied override
/*		SymbolicValue symbolicValue = cseElement2constrainingValue.get(cseElement);
		if (symbolicValue != null) {
			SymbolicValue basicSymbolicValue = basicGetSymbolicValue(cseElement);
			if (symbolicValue != basicSymbolicValue) {
				return traceSymbolicValue(cseElement, symbolicValue);								// Record new value
			}
			return symbolicValue;
		}
		return super.symbolicEvaluate(cseElement); */
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
		toString(s, 0);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("hypothesis for '" + executableObject + "' in '" + executableObject.eContainer() + "'");
		super.toString(s);
		List<@NonNull CSEElement> cseElements = new ArrayList<>(primaryCSE2constrainingValue.keySet());
	//	if (unconstrainedValues.size() > 1) {
	//		Collections.sort(unconstrainedValues, NameUtil.NAMEABLE_COMPARATOR);
	//	}
		StringUtil.appendIndentation(s, depth);
		s.append("primary hypotheses");
		for (@NonNull CSEElement cseElement : cseElements) {
			StringUtil.appendIndentation(s, depth+1);
			s.append(cseElement + " => ");
			SymbolicValue constrainingValue = primaryCSE2constrainingValue.get(cseElement);
			assert constrainingValue != null;
		//	StringUtil.appendIndentation(s, depth+2);
			s.append(constrainingValue);
		}
		StringUtil.appendIndentation(s, depth);
		s.append("corollary hypotheses");
		cseElements = new ArrayList<>(corollaryCSE2constrainingValue.keySet());
		for (@NonNull CSEElement cseElement : cseElements) {
			StringUtil.appendIndentation(s, depth+1);
			s.append(cseElement + " => ");
			SymbolicValue constrainingValue = corollaryCSE2constrainingValue.get(cseElement);
			assert constrainingValue != null;
		//	StringUtil.appendIndentation(s, depth+2);
			s.append(constrainingValue);
		}
		StringUtil.appendIndentation(s, depth);
		s.append("re-evaluation required for");
		for (@NonNull TypedElement affectedTypedElement : affectedTypedElements) {
			StringUtil.appendIndentation(s, depth+1);
			s.append(affectedTypedElement.eClass().getName());
			s.append(" : \"");
			s.append(affectedTypedElement);
			s.append("\"");
		}
	//	StringUtil.appendIndentation(s, depth);
	//	basicSymbolicEvaluationEnvironment.toString(s, depth+1);
	}

	@Override
	public @NonNull SymbolicValue traceSymbolicValue(@NonNull CSEElement cselement, @NonNull SymbolicValue symbolicValue) {
	//	SymbolicValue constrainingValue = cseElement2constrainingValue.get(cselement);
	//	assert constrainingValue != null;
	//	if (!symbolicValue.equals(constrainingValue)) {
	//		return super.traceSymbolicValue(cselement, constrainingValue);
	//	}
		return super.traceSymbolicValue(cselement, symbolicValue);
	}
}
