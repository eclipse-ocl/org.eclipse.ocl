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
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.values.SymbolicVariableValueImpl;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * A SymbolicHypothesisEvaluationEnvironment defines a symbolic EvaluationEnvironment in which the values
 * of one or more expressions have hypothesized values, which may be exploited by evaluay=tion, but which when
 * recomputed must match their hypothesis else the hypothesis is contradicted and the evluation is invalid.
 *
 * @since 1.15
 */
public class SymbolicHypothesisEvaluationEnvironment extends SymbolicEvaluationEnvironment
{
	private final @NonNull Map<@NonNull OCLExpression, @NonNull Object> expression2value = new HashMap<>();

	public SymbolicHypothesisEvaluationEnvironment(@NonNull SymbolicEvaluationEnvironment parent, @NonNull OCLExpression caller) {
		super(parent, caller, null);
	}

	@Override
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
	}

	public void addHypothesis(@NonNull OCLExpression hypothesizedExpression, @Nullable Object hypothesizedValue) {
		if (hypothesizedValue == null) {
			hypothesizedValue = ValueUtil.NULL_VALUE;
		}
		expression2value.put(hypothesizedExpression, hypothesizedValue);
	}

	@Override
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
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
		super.toString(s);
		List<@NonNull OCLExpression> expressions = new ArrayList<>(expression2value.keySet());
		if (expressions.size() > 1) {
			Collections.sort(expressions, NameUtil.NAMEABLE_COMPARATOR);
		}
		s.append("\n\t" + expressions.size() + " hypotheses expressions");
		for (@NonNull OCLExpression expression : expressions) {
			s.append("\n\t\t" + expression + " => ");
			@Nullable Object value = expression2value.get(expression);
			s.append(value);
		}
	}
}
