/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicNumericValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicSimpleStatus;

/**
 * Interface of a symbolic instance value.  OCL expressions that cannot be fully evaluated result in symbolic values. instances of this interface.
 */
public interface SymbolicValue
{
	void appendName(@NonNull StringBuilder s);

	/**
	 * Return a non-null String describing the incompatibility of this value if unconditionally incompatible, else null.
	 */
	@Nullable String asIncompatibility();

	/**
	 * Return an equivalent SymbolicValue to this value that is either a known literal value or a refinement of unrefinedValue,
	 *
	 * throws IllegalStateException if incompatoble.
	 */
	@NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue);

	@Nullable SymbolicSimpleStatus basicGetBooleanStatus();

	@Nullable SymbolicContent basicGetContent();

	// Returns null when invalid not applicable to e.g. a ZeroStatus
	@Nullable SymbolicSimpleStatus basicGetInvalidStatus();

	// Returns null when null not applicable to e.g. a ZeroStatus
	@Nullable SymbolicSimpleStatus basicGetNullStatus();

	@Nullable SymbolicNumericValue basicGetNumericValue();

	@NonNull SymbolicValue getBaseValue();

	@NonNull SymbolicSimpleStatus getBooleanStatus();

	@NonNull SymbolicCollectionContent getCollectionContent();

	@NonNull SymbolicContent getContent();

	@NonNull SymbolicSimpleStatus getDeadStatus();

	@NonNull SymbolicSimpleStatus getInvalidStatus();

	@Nullable Object getKnownValue();

	@NonNull NumberValue getLowerBound();

	@Nullable NumberValue getUpperBound();

	@NonNull SymbolicSimpleStatus getNullStatus();

	@NonNull SymbolicMapContent getMapContent();

	@NonNull TypeId getTypeId();

	@NonNull SymbolicNumericValue getNumericValue();

	boolean isCollection();

	boolean isDead();

	boolean isFalse();

	boolean isInvalid();

	boolean isKnown();

	boolean isMap();

	boolean isNull();

	boolean isNullFree();

	boolean isRefinementOf(@NonNull SymbolicValue unrefinedValue);

	boolean isTrue();

	boolean isZero();

	boolean mayBeFalse();

	boolean mayBeInvalid();

	@Nullable String mayBeInvalidReason();

	boolean mayBeNull();

	@Nullable String mayBeNullReason();

	boolean mayBeTrue();

	boolean mayBeZero();

	@Override @NonNull String toString();

	void toString(@NonNull StringBuilder s);
}
