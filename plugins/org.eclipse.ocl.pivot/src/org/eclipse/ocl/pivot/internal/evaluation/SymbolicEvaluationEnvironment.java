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
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 *
 * @since 1.16
 */
public interface SymbolicEvaluationEnvironment
{
	@Nullable SymbolicValue basicGetSymbolicValue(@NonNull TypedElement element);

	/**
	 * Return a SymbolicKnownValue for invalid, if typedElement is a collection and empty.
	 * Else return a mayBeNull SymbolicUnknownValue for typeId if typedElement is a collection.
	 * Else return null if typedElement is not null.
	 */
	@Nullable SymbolicValue checkNotEmpty(@NonNull TypedElement typedElement, @NonNull TypeId typeId);

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

	@NonNull SymbolicValue createUnknownValue(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalidOrNull);
	@NonNull SymbolicValue createUnknownValue(@NonNull TypedElement typedElement, boolean mayBeNull, boolean mayBeInvalid);
	@NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment();
	@NonNull EnvironmentFactory getEnvironmentFactory();
	@NonNull ExecutorInternal getExecutor();
	@NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue);
	@NonNull SymbolicAnalysis getSymbolicAnalysis();
	@NonNull SymbolicValue getSymbolicValue(@NonNull TypedElement element);
	boolean isFalse(@NonNull TypedElement element);
	boolean isInvalid(@NonNull TypedElement element);
	boolean isNull(@NonNull TypedElement element);
	boolean isTrue(@NonNull TypedElement element);
	boolean isZero(@NonNull TypedElement element);
	boolean mayBeInvalid(@NonNull OCLExpression expression);
	boolean mayBeInvalidOrNull(@NonNull OCLExpression expression);
	boolean mayBeNull(@NonNull OCLExpression expression);
	void setDead(@NonNull OCLExpression expression);
	@NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement element);
	@NonNull SymbolicValue traceSymbolicValue(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue);
}