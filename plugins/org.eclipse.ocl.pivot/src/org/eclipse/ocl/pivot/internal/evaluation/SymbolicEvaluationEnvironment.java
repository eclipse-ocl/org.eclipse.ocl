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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 *
 * @since 1.15
 */
public interface SymbolicEvaluationEnvironment extends EvaluationEnvironment.EvaluationEnvironmentExtension
{
	/**
	 * Return a SymbolicKnownValue for invalid, if typedElement isInvalid.
	 * Else return a mayBeNull SymbolicUnknownValue for typeId if typedElement mayBeInvalid.
	 * Else return null if typedElement is not invalid.
	 */
	@Nullable SymbolicValue checkNotInvalid(@NonNull TypedElement typedElement, @NonNull TypeId typeId);

	/**
	 * Return a SymbolicKnownValue for invalid, if typedElement isNull.
	 * Else return a mayBeNull SymbolicUnknownValue for typeId if typedElement mayBeNull.
	 * Else return null if typedElement is not null.
	 */
	@Nullable SymbolicValue checkNotNull(@NonNull TypedElement typedElement, @NonNull TypeId typeId);

	/**
	 * Return a SymbolicKnownValue for invalid, if typedElement isZero.
	 * Else return a mayBeNull SymbolicUnknownValue for typeId if typedElement mayBeZero.
	 * Else return null if typedElement is not zero.
	 */
	@Nullable SymbolicValue checkNotZero(@NonNull TypedElement typedElement, @NonNull TypeId typeId);

	@NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment();
	@NonNull SymbolicValue getSymbolicValue(@NonNull Element element);
	boolean isFalse(@NonNull TypedElement element);
	boolean isInvalid(@NonNull TypedElement element);
	boolean isNull(@NonNull TypedElement element);
	boolean isTrue(@NonNull TypedElement element);
	boolean isZero(@NonNull TypedElement element);
	boolean mayBeInvalid(@NonNull OCLExpression expression);
	boolean mayBeInvalidOrNull(@NonNull OCLExpression expression);
	boolean mayBeNull(@NonNull OCLExpression expression);
	@NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement element);
	@NonNull SymbolicValue traceSymbolicValue(@NonNull CSEElement cseElement, @NonNull SymbolicValue symbolicValue);
//	@NonNull SymbolicValue traceValue(@NonNull CSEElement cseElement, @Nullable Object value);
}