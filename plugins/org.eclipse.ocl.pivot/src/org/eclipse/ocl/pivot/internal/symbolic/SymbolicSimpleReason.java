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
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;

/**
 * SymbolicSimpleReason maintains the reason for a symbolic evaluation failure as a simple string.
 *
 * @since 1.17
 */
public class SymbolicSimpleReason implements SymbolicReason
{
	public static final @NonNull SymbolicSimpleReason IS_VALIDATING = new SymbolicSimpleReason("isValidating");
	@Deprecated /* @deprecated - be more precise */
	public static final @NonNull SymbolicSimpleReason MAY_BE_INVALID_REASON = new SymbolicSimpleReason("mayBeInvalidReason");
	public static final @NonNull SymbolicSimpleReason MAY_BE_INVALID_VALUE = new SymbolicSimpleReason("may-be-invalid value");
	@Deprecated /* @deprecated - be more precise */
	public static final @NonNull SymbolicSimpleReason MAY_BE_NULL_REASON = new SymbolicSimpleReason("mayBeNullReason");
	public static final @NonNull SymbolicSimpleReason MAY_BE_NULL_VALUE = new SymbolicSimpleReason("may-be-null value");
	public static final @NonNull SymbolicSimpleReason NULL_VALUE = new SymbolicSimpleReason("null value");

	private final @NonNull String string;

	public SymbolicSimpleReason(@NonNull String string) {
		this.string = string;
		assert !"variable \"object\" may be null".equals(string);	// XXX
	}

	@Override
	public @NonNull String toString() {
		@NonNull StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
		s.append(string);
	}
}