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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * Basic implementation of the {@link EvaluationEnvironment} interface,
 * providing some useful common behaviors.  Implementors of metamodel-specific
 * environments are encourage to extend this class rather than implement
 * an evaluation environment "from scratch."
 *
 * @since 1.15
 */
public class ConstrainedSymbolicEvaluationEnvironment extends AbstractSymbolicEvaluationEnvironment
{
//	private final @NonNull AbstractSymbolicEvaluationEnvironment parent;

	/**
	 * The expression for which a re-evaluation with constraints may give a better result.
	 */
//	private final @NonNull OCLExpression clientExpression;

	/**
	 * The contextual constrainting value of each expression element, null if not constrained.
	 */
	private @NonNull Map<@NonNull OCLExpression, @NonNull SymbolicValue> constrainedExpression2constrainingValue = new HashMap<>();

	public ConstrainedSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment parent, @NonNull OCLExpression clientExpression) {
		super(parent, clientExpression);
//		this.clientExpression = clientExpression;
	}

	public void addConstraint(@NonNull OCLExpression expression/*, @NonNull SymbolicValue unconstrainedValue*/, @NonNull SymbolicValue constrainingValue) {
		SymbolicValue old = constrainedExpression2constrainingValue.put(expression, constrainingValue);
		// XXX Heuristic deductions can avoid later hypotheses
		assert old == null;		// FIXME Need list
	}

	public @NonNull Iterable<@NonNull OCLExpression> getConstrainedExpressions() {
		return constrainedExpression2constrainingValue.keySet();
	}

	public @NonNull SymbolicValue getConstrainingValue(@NonNull OCLExpression constrainedExpression) {
		return  ClassUtil.nonNullState(constrainedExpression2constrainingValue.get(constrainedExpression));
	}

//	@Override
//	public @Nullable AbstractSymbolicEvaluationEnvironment getParent() {
//		return parent;
//	}

//	public void setUnconstrainedValue(@NonNull SymbolicUnknownValue unconstrainedValue) {
		// TODO Auto-generated method stub

//	}

	/**
	 * Returns a string representation of the hypotheses
	 *
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	} */

/*	public void toString(@NonNull StringBuilder s, int depth) {
		if (parent != null) {
			parent.toString(s); //, depth);
			s.append("\n");
		}
		s.append(StringUtil.defaultIndentation);
		s.append("constraint for '" + clientExpression + "' in '" + clientExpression.eContainer() + "'");
		List<@NonNull OCLExpression> constrainedExpressions = new ArrayList<>(constrainedExpression2constrainingValue.keySet());
		if (constrainedExpressions.size() > 1) {
			Collections.sort(constrainedExpressions, NameUtil.TO_STRING_COMPARATOR);
		}
	//	s.append("\n\t " + constrainedExpressions.size() + " expression constraints");
		for (@NonNull OCLExpression constrainedExpression : constrainedExpressions) {
			StringUtil.appendIndentation(s, depth+1);
			s.append("'"+ constrainedExpression + "' => ");
			SymbolicValue constrainingValue = constrainedExpression2constrainingValue.get(constrainedExpression);
			assert constrainingValue != null;
			s.append(constrainingValue);
		}
	} */
}
