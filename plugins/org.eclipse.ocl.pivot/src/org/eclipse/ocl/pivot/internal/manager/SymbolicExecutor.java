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
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;
import org.eclipse.ocl.pivot.internal.evaluation.Hypothesis;
import org.eclipse.ocl.pivot.internal.evaluation.HypothesizedSymbolicEvaluationEnvironment;

/**
 * @since 1.16
 */
public interface SymbolicExecutor extends ExecutorInternalExtension
{
	void addHypothesis(@NonNull TypedElement typedElement, @NonNull Hypothesis hypothesis);

	@NonNull HypothesizedSymbolicEvaluationEnvironment createHypothesizedSymbolicEvaluationEnvironment(@NonNull Hypothesis hypothesis);
//	@NonNull SymbolicReEvaluationEnvironment createSymbolicReEvaluationEnvironment(@NonNull Hypothesis hypothesis);

	@NonNull AbstractSymbolicEvaluationEnvironment getSymbolicEvaluationEnvironment();

	/**
	 * Created a nested SymbolicEvaluationEnvironment on behalf of caller in which symbolicValue has a constantValue.
	 */
//	@NonNull SymbolicEvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull SymbolicValue symbolicValue, @Nullable Object constantValue, @NonNull OCLExpression caller);

	/**
	 * Created a nested SymbolicEvaluationEnvironment on behalf of caller in which symbolicValue has a constantValue.
	 */
//	@NonNull HypothesizedSymbolicEvaluationEnvironment pushSymbolicHypothesis(@NonNull OCLExpression caller);

//	void popSymbolicHypothesis();
}