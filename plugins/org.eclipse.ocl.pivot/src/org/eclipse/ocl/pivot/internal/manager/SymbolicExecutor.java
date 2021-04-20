/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.evaluation.ConstrainedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;
import org.eclipse.ocl.pivot.internal.evaluation.HypothesizedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;

/**
 * @since 1.15
 */
public interface SymbolicExecutor extends ExecutorInternalExtension
{
	@Override
	@NonNull AbstractSymbolicEvaluationEnvironment getEvaluationEnvironment();

	void popConstrainedSymbolicEvaluationEnvironment();

	/**
	 * Created a nested SymbolicEvaluationEnvironment on behalf of caller in which symbolicValue has a constantValue.
	 */
//	@NonNull SymbolicEvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull SymbolicValue symbolicValue, @Nullable Object constantValue, @NonNull OCLExpression caller);

	/**
	 * Created a nested SymbolicEvaluationEnvironment on behalf of caller in which expression was computed to have expressionValue but has a knownVlue in contexte.
	 * @param constantValue
	 */
//	@NonNull SymbolicEvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull OCLExpression expression, @NonNull SymbolicValue computedValue, @NonNull SymbolicValue knownValue, @NonNull OCLExpression caller);
	@NonNull ConstrainedSymbolicEvaluationEnvironment pushConstrainedSymbolicEvaluationEnvironment(@NonNull OCLExpression expression);
	@NonNull HypothesizedSymbolicEvaluationEnvironment createHypothesizedSymbolicEvaluationEnvironment(@NonNull TypedElement element);

	/**
	 * Created a nested SymbolicEvaluationEnvironment on behalf of caller in which symbolicValue has a constantValue.
	 */
//	@NonNull HypothesizedSymbolicEvaluationEnvironment pushSymbolicHypothesis(@NonNull OCLExpression caller);

	void popSymbolicHypothesis();
}