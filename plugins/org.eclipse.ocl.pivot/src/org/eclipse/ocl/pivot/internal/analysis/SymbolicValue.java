/*******************************************************************************
 * Copyright (c) 2017, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.analysis;

import org.eclipse.jdt.annotation.NonNull;

/**
 * SymbolicValue defines a symbolic evaluation result
 * .
 * @since 1.12
 */
public interface SymbolicValue
{
	public static final @NonNull SymbolicValue KNOWN_INVALID = new AbstractSymbolicValue.KnownInvalidValue();
	public static final @NonNull SymbolicValue KNOWN_NOT_NULL_LITERALS = new AbstractSymbolicValue.KnownLiteralsValue();
	public static final @NonNull SymbolicValue UNKNOWN_MAYBE_NULL_MAYBE_INVALID = new AbstractSymbolicValue.UnknownValue(false, false);
	public static final @NonNull SymbolicValue UNKNOWN_MAYBE_NULL_NOT_INVALID = new AbstractSymbolicValue.UnknownValue(false, true);
	public static final @NonNull SymbolicValue UNKNOWN_NOT_NULL_MAYBE_INVALID = new AbstractSymbolicValue.UnknownValue(true, false);
	public static final @NonNull SymbolicValue UNKNOWN_NOT_NULL_NOT_INVALID = new AbstractSymbolicValue.UnknownValue(true, true);
	public static final @NonNull SymbolicValue KNOWN_NULL = new AbstractSymbolicValue.KnownNullValue();

	boolean isInvalid();
	boolean isKnown();
	boolean isNonInvalid();
	boolean isNonNull();
	boolean isNull();
	boolean maybeInvalid();
	boolean maybeNull();
	@NonNull SymbolicValue setNonNullValue();
	@NonNull SymbolicValue union(@NonNull SymbolicValue thatValue);
}
