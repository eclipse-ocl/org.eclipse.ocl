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
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.values.AbstractRefinedSymbolicValue;
import org.eclipse.ocl.pivot.internal.values.SymbolicKnownValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OCLValue;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * A BaseSymbolicEvaluationEnvironment supports the control-blind symbolic evaluation initially to associate a
 * SymbolicValue with each CSEElement. Subsequently, evaluation of hypothesies may refine the value of specific expressions.
 *
 * @since 1.15
 */
public class BaseSymbolicEvaluationEnvironment extends AbstractSymbolicEvaluationEnvironment
{
	/**
	 * The known control-blind (symbolic) value of each common expression element, null if not yet computed.
	 */
	private @NonNull Map<@NonNull CSEElement, @NonNull SymbolicValue> cseElement2symbolicValue = new HashMap<>();

	/**
	 * The known symbolic value of known literal values.
	 */
	private @NonNull Map<@Nullable Object, @NonNull SymbolicKnownValue> value2symbolicValue = new HashMap<>();

	/**
	 * The maybe-invalid symbolic value of known TYpeIds.
	 */
	private @NonNull Map<@NonNull TypeId, @NonNull SymbolicUnknownValue> typeid2symbolicValue = new HashMap<>();

	/**
	 * The expression-specific refined symbolic values established after contradicting a hypothesis.
	 */
	private @NonNull Map<@NonNull TypedElement, @NonNull SymbolicValue> expression2refinedSymbolicValue = new HashMap<>();

	public BaseSymbolicEvaluationEnvironment(@NonNull SymbolicAnalysis executor, @NonNull NamedElement executableObject) {
		super(executor, executableObject);
	}

	public BaseSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment parent, @NonNull TypedElement executableObject) {
		super(parent, executableObject);
	}

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull Element element) {
		SymbolicValue refinedSymbolicValue = expression2refinedSymbolicValue.get(element);
		if (refinedSymbolicValue != null) {
			return refinedSymbolicValue;
		}
		CSEElement cseElement = getSymbolicAnalysis().getCSEElement(element);
		return basicGetSymbolicValue(cseElement);
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
			CSEElement variableCSE = getSymbolicAnalysis().getCSEElement(typedElement);
			Iterable<@NonNull OCLExpression> variableExps = variableCSE.getOutputs();
			if (variableExps != null) {
				for (@NonNull OCLExpression variableExp : variableExps) {
					gatherAffectedTypedElements(affectedExpressions, variableExp);
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
	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return this;
	}

	public @NonNull Set<@NonNull CSEElement> getCSEElements() {
		return cseElement2symbolicValue.keySet();
	}

	@Override
	public @NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue) {
		assert ValueUtil.isBoxed(boxedValue);
		SymbolicKnownValue symbolicKnownValue = value2symbolicValue.get(boxedValue);
		if (symbolicKnownValue == null) {
			if (boxedValue instanceof OCLValue) {
				for (@Nullable Object key : value2symbolicValue.keySet()) {		// FIXME ?? smarter cache ?? Redundant OCLValue is already smart
					if ((key instanceof OCLValue) && ((OCLValue)boxedValue).oclEquals((OCLValue)key)) {
						symbolicKnownValue = value2symbolicValue.get(key);
					}
				}
			}
			if (symbolicKnownValue == null) {
				Type type = getEnvironmentFactory().getIdResolver().getStaticTypeOfValue(null, boxedValue);
				symbolicKnownValue = new SymbolicKnownValueImpl(type.getTypeId(), boxedValue);
				value2symbolicValue.put(boxedValue, symbolicKnownValue);
			}
		}
		return symbolicKnownValue;
	}

	@Override
	public @Nullable SymbolicValue getMayBeInvalidValue(@NonNull TypeId typeid) {
		SymbolicUnknownValue symbolicUnknownValue = typeid2symbolicValue.get(typeid);
		if (symbolicUnknownValue == null) {
			symbolicUnknownValue = new SymbolicUnknownValueImpl(typeid, false, true);
			typeid2symbolicValue.put(typeid, symbolicUnknownValue);
		}
		return symbolicUnknownValue;
	}

	@Override
	public @Nullable Object getValueOf(@NonNull TypedElement variable) {
		Object variableValue = super.getValueOf(variable);
	/*	if (variableValue instanceof SymbolicValue) {
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
		} */
		return variableValue;
	}

	/**
	 * Return true if typedElement may have some symbolic constraints. Conversely avoid the need to compute the boxed source and
	 * argument lists if there are no such constraints that have been deduced.
	 *
	 *
	@Override
	public boolean hasSymbolicConstraints(@NonNull TypedElement typedElement) {
		Map<@Nullable List<@Nullable Object>, @NonNull List<@Nullable Object>> values2constraints = typedElement2values2constraints.get(typedElement);
		return values2constraints != null;
	} */

	public boolean isDead(@NonNull OCLExpression element) {
		return basicGetSymbolicValue(element) == null;
	}

	public void refineValue(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
	//	Hypothesis hypothesis = refinedValue.getHypothesis();
	//	assert expression == hypothesis.getExpression();
	//	toString();		// XXX
		SymbolicValue old = expression2refinedSymbolicValue.put(typedElement, symbolicValue);
		if (old != null) {
	//		assert refinedValue.getBaseValue() == old.getBaseValue();
			// XXX verify that refined Value is stronger
		}
		Set<@NonNull TypedElement> affectedExpressionsSet = new HashSet<>();
		gatherAffectedTypedElements(affectedExpressionsSet, typedElement);
		List<@NonNull TypedElement> affectedExpressionsList = new ArrayList<>(affectedExpressionsSet);
		Collections.sort(affectedExpressionsList, getSymbolicAnalysis().getTypedElementHeightComparator());
		for (@NonNull TypedElement affectedExpression : affectedExpressionsList) {
			symbolicReEvaluate(affectedExpression);
		}
	}

	@Override
	public void setDead(@NonNull OCLExpression expression) {
		for (@NonNull EObject eObject : new TreeIterable(expression, true)) {
			if (eObject instanceof OCLExpression) {
				TypedElement typedElement = (TypedElement)eObject;
				SymbolicValue symbolicValue = getSymbolicValue(typedElement);
				if (!symbolicValue.isDead()) {
					symbolicValue = AbstractRefinedSymbolicValue.createIsDeadValue(symbolicValue);
					expression2refinedSymbolicValue.put(typedElement, symbolicValue);
				}
			}
		}
	}

	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement element) {
		SymbolicValue symbolicValue = basicGetSymbolicValue(element);			// Re-use old value
		if (symbolicValue != null) {
			return symbolicValue;
		}
		Object result;
		try {
			EvaluationVisitor undecoratedVisitor = getUndecoratedVisitor();
			result = element.accept(undecoratedVisitor);
		}
		catch (InvalidValueException e) {
			result = e;
		}
	/*	catch (Exception e) {
			// This is a backstop. Library operations should catch their own exceptions
			//  and produce a better reason as a result.
			result = new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, element, "-", "-");
		}
		catch (AssertionError e) {
			// This is a backstop. Library operations should catch their own exceptions
			//  and produce a better reason as a result.
			result = new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, element, "-", "-");
			throw e;
		} */
		CSEElement cseElement = getSymbolicAnalysis().getCSEElement(element);
		return traceValue(cseElement, result);								// Record new value
	}

	public @NonNull SymbolicValue symbolicReEvaluate(@NonNull TypedElement element) {
		SymbolicValue unrefinedValue = getSymbolicValue(element);			// Get the unrefined value
		Object result;
		try {
			EvaluationVisitor undecoratedVisitor = getUndecoratedVisitor();
			result = element.accept(undecoratedVisitor);
		}
		catch (InvalidValueException e) {
			result = e;
		}
		SymbolicValue refinedValue;
		if (result instanceof SymbolicValue) {
			refinedValue = (SymbolicValue) result;
		}
		else {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(result);
			refinedValue = getKnownValue(boxedValue);
		}
		refinedValue = refinedValue.asRefinementOf(unrefinedValue);
		SymbolicValue old = expression2refinedSymbolicValue.put(element, refinedValue);
		if (old != null) {
			assert old == unrefinedValue;
		}
		return refinedValue;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
	//	super.toString(s);
		StringUtil.appendIndentation(s, 0);
		List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
		if (keys.size() > 1) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
		s.append("\t" + keys.size() + " cses");
		for (@NonNull CSEElement key : keys) {
			Object value = cseElement2symbolicValue.get(key);
			s.append("\n\t\t" + key + " => " + value);
		}
		List<@NonNull TypedElement> refinedKeys = new ArrayList<>(expression2refinedSymbolicValue.keySet());
		if (refinedKeys.size() > 0) {
			if (refinedKeys.size() > 1) {
				Collections.sort(refinedKeys, NameUtil.TO_STRING_COMPARATOR);
			}
			StringUtil.appendIndentation(s, 0);
			s.append("\t" + refinedKeys.size() + " refined");
			for (@NonNull TypedElement refinedKey : refinedKeys) {
				Object value = expression2refinedSymbolicValue.get(refinedKey);
				s.append("\n\t\t" + refinedKey + " => " + value);
			}
		}
	}

	@Override
	public @NonNull SymbolicValue traceSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue symbolicValue) {
		if ("self.name".equals(cseElement.toString())) {
			getClass();		// XXX
		}
		SymbolicValue old = cseElement2symbolicValue.put(cseElement, symbolicValue);
		assert (old == null) || (old == symbolicValue); //old.equals(symbolicValue);
		return symbolicValue;
	}

//	@Override
	public @NonNull SymbolicValue traceValue(@NonNull CSEElement cseElement, @Nullable Object value) {
		SymbolicValue symbolicValue;
		if (value instanceof SymbolicValue) {
			symbolicValue = (SymbolicValue) value;
		}
		else {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(value);
			symbolicValue = getKnownValue(boxedValue);
		}
		return traceSymbolicValue(cseElement, symbolicValue);
	}
}
