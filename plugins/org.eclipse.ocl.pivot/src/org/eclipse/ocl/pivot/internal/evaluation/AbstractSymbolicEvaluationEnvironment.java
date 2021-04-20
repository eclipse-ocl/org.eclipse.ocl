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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.manager.SymbolicOCLExecutor;
import org.eclipse.ocl.pivot.internal.values.SymbolicKnownValueImpl;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OCLValue;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * Basic implementation of the {@link EvaluationEnvironment} interface,
 * providing some useful common behaviors.  Implementors of metamodel-specific
 * environments are encourage to extend this class rather than implement
 * an evaluation environment "from scratch."
 *
 * @since 1.15
 */
public abstract class AbstractSymbolicEvaluationEnvironment extends BasicEvaluationEnvironment implements SymbolicEvaluationEnvironment
{
//	private final @NonNull Map<@NonNull SymbolicValue, @NonNull List<@NonNull SymbolicConstraint>> value2constraint = new HashMap<>();

	/**
	 * The known (symbolic) value of each expression element, null if not yet computed.
	 */
	private @NonNull Map<@NonNull Element, @NonNull SymbolicValue> element2symbolicValue = new HashMap<>();

	/**
	 * The known symbolic value of known values.
	 */
	private @NonNull Map<@Nullable Object, @NonNull SymbolicKnownValue> value2symbolicValue = new HashMap<>();

//	private final @NonNull Map<@NonNull TypedElement, @NonNull Map<@Nullable List<@Nullable Object>, @NonNull List<@Nullable Object>>> typedElement2values2constraints = new HashMap<>();

	protected AbstractSymbolicEvaluationEnvironment(@NonNull SymbolicExecutor executor, @NonNull NamedElement executableObject) {
		super(executor, executableObject);
	}

	protected AbstractSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment parent, @NonNull NamedElement element) {
		super(parent, element, element);
	}

/*	public void addSymbolicResult(@NonNull TypedElement feature, @Nullable List<@Nullable Object> sourceAndArgumentValues, @Nullable Object symbolicConstraint) {
		Map<@Nullable List<@Nullable Object>, @NonNull List<@Nullable Object>> values2constraints = typedElement2values2constraints.get(feature);
		if (values2constraints == null) {
			values2constraints = new HashMap<>();
			typedElement2values2constraints.put(feature, values2constraints);
		}
		List<@Nullable Object> constraints = values2constraints.get(sourceAndArgumentValues);
		if (constraints == null) {
			constraints = new ArrayList<>();
			values2constraints.put(sourceAndArgumentValues, constraints);
		}
		constraints.add(symbolicConstraint);
	} */

/*	public void addSymbolicConstraint(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicConstraint symbolicConstraint) {
		List<@NonNull SymbolicConstraint> constraints = value2constraint.get(symbolicValue);
		if (constraints == null) {
			SymbolicEvaluationEnvironment parent2 = (SymbolicEvaluationEnvironment) parent;
			if (parent2 != null) {
				Iterable<@NonNull SymbolicConstraint> parentConstraints = parent2.getSymbolicConstraintsOn(symbolicValue);
				if (parentConstraints != null) {
					constraints = Lists.newArrayList(parentConstraints);
				}
			}
			if (constraints == null) {
				constraints = new ArrayList<>();
			}
			value2constraint.put(symbolicValue, constraints);
		}
		constraints.add(symbolicConstraint);
	} */

	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull EObject/*Element*/ element) {
		return element2symbolicValue.get(element);
	}

//	public @NonNull Map<@NonNull Element, @NonNull SymbolicValue> getElement2SymbolicValue() {
//		return element2symbolicValue;
//	}

	public @NonNull Set<@NonNull Element> getElements() {
		return element2symbolicValue.keySet();
	}

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
	public @Nullable AbstractSymbolicEvaluationEnvironment getParent() {
		return (AbstractSymbolicEvaluationEnvironment) super.getParent();
	}

	private @NonNull SymbolicAnalysis getSymbolicAnalysis() {
		return (SymbolicAnalysis)getSymbolicExecutor();
	}

	private @NonNull SymbolicOCLExecutor getSymbolicExecutor() {
		return (SymbolicOCLExecutor)getExecutor();
	}

/*	public @Nullable Iterable<@Nullable Object> getSymbolicConstraints(@NonNull TypedElement typedElement, @Nullable List<@Nullable Object> sourceAndArgumentValues) {
		Map<@Nullable List<@Nullable Object>, @NonNull List<@Nullable Object>> values2constraints = typedElement2values2constraints.get(typedElement);
		if (values2constraints == null) {
			return null;
		}
		return values2constraints.get(sourceAndArgumentValues);
	} */

	public @NonNull SymbolicValue getSymbolicValue1(@NonNull Element element) {
		return ClassUtil.nonNullState(element2symbolicValue.get(element));
	}

	public @NonNull SymbolicValue getSymbolicValue2(@NonNull TypedElement element) {
		SymbolicValue symbolicValue = basicGetSymbolicValue(element);
		if (symbolicValue == null) {
			symbolicValue = symbolicEvaluate(element);
		}
		return symbolicValue;
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

/*	private @Nullable Iterable<@NonNull SymbolicConstraint> getSymbolicConstraintsOn(@NonNull SymbolicValue symbolicValue) {
		Iterable<@NonNull SymbolicConstraint> constraints = value2constraint.get(symbolicValue);
		if (constraints == null) {
			SymbolicEvaluationEnvironment parent2 = (SymbolicEvaluationEnvironment) parent;
			if (parent2 != null) {
				constraints = parent2.getSymbolicConstraintsOn(symbolicValue);
			}
		}
		return constraints;
	} */

	/**
	 * Return true if typedElement may have some symbolic constraints. Conversely avoid the need to compute the boxed source and
	 * argument lists if there are no such constraints that have been deduced.
	 *
	 *
	public boolean hasSymbolicConstraints(@NonNull TypedElement typedElement) {
		Map<@Nullable List<@Nullable Object>, @NonNull List<@Nullable Object>> values2constraints = typedElement2values2constraints.get(typedElement);
		return values2constraints != null;
	} */

	public boolean isDead(@NonNull TypedElement element) {
		return basicGetSymbolicValue(element) == null;
	}

	public boolean isFalse(@NonNull TypedElement element) {
		return getSymbolicValue2(element).isFalse();
	}

	public boolean isInvalid(@NonNull TypedElement element) {
		return getSymbolicValue2(element).isInvalid();
	}

	public boolean isNull(@NonNull TypedElement element) {
		return getSymbolicValue2(element).isNull();
	}

	public boolean isTrue(@NonNull TypedElement element) {
		return getSymbolicValue2(element).isTrue();
	}

	public boolean isZero(@NonNull TypedElement element) {
		return getSymbolicValue2(element).isZero();
	}

	public boolean mayBeInvalid(@NonNull TypedElement element) {
		SymbolicValue symbolicValue = getSymbolicValue1(element);
		if (!symbolicValue.mayBeInvalid()) {
			return false;
		}
		SymbolicExecutor symbolicExecutor = getSymbolicExecutor();
		HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = symbolicExecutor.createHypothesizedSymbolicEvaluationEnvironment(element);
	//	try {
			SymbolicValue hypothesizedValue = new SymbolicKnownValueImpl(element.getTypeId(), ValueUtil.INVALID_VALUE);
			hypothesizedSymbolicEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
		//	boolean isContradiction = hypothesizedSymbolicEvaluationEnvironment.isContradiction(this);
		//	return !isContradiction;
	//	}
	//	finally {
	//		symbolicExecutor.popEvaluationEnvironment();
	//	}
		return true;
	}

	public boolean mayBeInvalidOrNull(@NonNull TypedElement element) {
		boolean mayBeInvalid = mayBeInvalid(element);		// FIXME it would be nice to do both at once
		boolean mayBeNull = mayBeNull(element);				// but that needs e.g. may-be-invalid + true.
		return mayBeInvalid || mayBeNull;					// hypothesize both before the potential logiical short circuit.
	}

	public boolean mayBeNull(@NonNull TypedElement element) {
		SymbolicValue symbolicValue = getSymbolicValue1(element);
		if (!symbolicValue.mayBeNull()) {
			return false;
		}
		SymbolicExecutor symbolicExecutor = getSymbolicExecutor();
		HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = symbolicExecutor.createHypothesizedSymbolicEvaluationEnvironment(element);
	//	try {
			SymbolicValue hypothesizedValue = new SymbolicKnownValueImpl(element.getTypeId(), ValueUtil.NULL_VALUE);
			hypothesizedSymbolicEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
		//	boolean isContradiction = hypothesizedSymbolicEvaluationEnvironment.isContradiction(this);
		//	return !isContradiction;
	//	}
	//	finally {
	//		symbolicExecutor.popEvaluationEnvironment();
	//	}
		return true;
	}

	public boolean mayBeNull(@NonNull OCLExpression expression, @Nullable Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mayBeZero(@NonNull OCLExpression element) {
		SymbolicValue symbolicValue = getSymbolicValue1(element);
		if (!symbolicValue.mayBeZero()) {
			return false;
		}
		if (symbolicValue.isZero()) {
			return true;
		}
		SymbolicExecutor symbolicExecutor = getSymbolicExecutor();
		HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = symbolicExecutor.createHypothesizedSymbolicEvaluationEnvironment(element);
	//	try {
			SymbolicValue hypothesizedValue = new SymbolicKnownValueImpl(element.getTypeId(), ValueUtil.ZERO_VALUE);
			hypothesizedSymbolicEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
			hypothesizedSymbolicEvaluationEnvironment.putHypothesizedTerm(getSymbolicAnalysis(), element);
		//	boolean isContradiction = hypothesizedSymbolicEvaluationEnvironment.isContradiction(this);
		//	return !isContradiction;
	//	}
	//	finally {
	//		symbolicExecutor.popEvaluationEnvironment();
	//	}
		return true;
	}

	public @Nullable SymbolicValue putSymbolicValue(@NonNull TypedElement element, @NonNull SymbolicValue symbolicValue) {
		return element2symbolicValue.put(element, symbolicValue);
	}

	public @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement element) {
		SymbolicValue symbolicValue = basicGetSymbolicValue(element);			// Re-use old value
		if (symbolicValue != null) {
			return symbolicValue;
		}
		Object result;
		try {
			EvaluationVisitor undecoratedVisitor = getSymbolicExecutor().getEvaluationVisitor().getUndecoratedVisitor();
			result = element.accept(undecoratedVisitor);
		}
		catch (InvalidValueException e) {
			result = e;
		}
		return traceValue(element, result);								// Record new value
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
		super.toString(s);
/*		List<@NonNull TypedElement> features = new ArrayList<>(typedElement2values2constraints.keySet());
		if (features.size() > 1) {
			Collections.sort(features, NameUtil.NAMEABLE_COMPARATOR);
		}
		s.append("\n\t" + features.size() + " feature constraints");
		for (@NonNull TypedElement feature : features) {
			s.append("\n\t\t" + feature + " => ");
			Map<@Nullable List<@Nullable Object>, @NonNull List<@Nullable Object>> values2constraints = typedElement2values2constraints.get(feature);
			assert values2constraints != null;
			List<@Nullable List<@Nullable Object>> values = new ArrayList<>(values2constraints.keySet());
			for (@Nullable List<@Nullable Object> value : values) {
				s.append("\n\t\t\t[");
				if (value != null) {
					boolean isFirst = true;
					for (@Nullable Object val : value) {
						if (!isFirst) {
							s.append(",");
						}
						s.append(val);
						isFirst = false;
					}
				}
				s.append("] => ");
				List<@Nullable Object> constraints = values2constraints.get(value);
				assert constraints != null;
				boolean isFirst = true;
				for (@Nullable Object constraint : constraints) {
					if (!isFirst) {
						s.append(",");
					}
					s.append(constraint);
					isFirst = false;
				}
			}
		} */
	}


	public @NonNull SymbolicValue traceSymbolicValue(@NonNull TypedElement expression, @NonNull SymbolicValue symbolicValue) {
		if ("self.name".equals(expression.toString())) {
			getClass();		// XXX
		}
		SymbolicValue old = putSymbolicValue(expression, symbolicValue);
		assert (old == null);// || (old == symbolicValue) || old.equals(symbolicValue);
		return symbolicValue;
	}

	public @NonNull SymbolicValue traceValue(@NonNull TypedElement expression, @Nullable Object value) {
		SymbolicValue symbolicValue;
		if (value instanceof SymbolicValue) {
			symbolicValue = (SymbolicValue) value;
		}
		else {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(value);
			symbolicValue = getKnownValue(boxedValue);
		}
		return traceSymbolicValue(expression, symbolicValue);
	}
}
