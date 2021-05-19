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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 *
 * @since 1.15
 */
public interface SymbolicEvaluationEnvironment extends EvaluationEnvironment.EvaluationEnvironmentExtension
{
	@NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment();
	@NonNull SymbolicValue getSymbolicValue(@NonNull TypedElement element);
	boolean isFalse(@NonNull OCLExpression element);
	boolean isInvalid(@NonNull OCLExpression element);
	boolean isNull(@NonNull OCLExpression element);
	boolean isTrue(@NonNull OCLExpression element);
	boolean isZero(@NonNull OCLExpression element);
	boolean mayBeInvalid(@NonNull OCLExpression expression);
	boolean mayBeInvalidOrNull(@NonNull OCLExpression expression);
	boolean mayBeNull(@NonNull OCLExpression expression);
//	boolean mayBeNull(@NonNull OCLExpression expression, @Nullable Object value);
	boolean mayBeZero(@NonNull OCLExpression expression);
	@NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement element);
	@NonNull SymbolicValue traceSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue symbolicValue);
}
