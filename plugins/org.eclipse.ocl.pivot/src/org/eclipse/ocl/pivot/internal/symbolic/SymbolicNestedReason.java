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
import org.eclipse.jdt.annotation.Nullable;

/**
 * SymbolicNestedReason maintains the reason for a symbolic evaluation failure as a nested hierarchy of reasons.
 *
 * FIXME Maintaining the hierarchy has some advantages for debugging, but is not obviously really necessary and
 * somewhat conficts with attempts to maximize SymbolicValue re-use by avoiding unnecessary context.
 *
 * @since 1.17
 */
public class SymbolicNestedReason implements SymbolicReason
{
	private final @NonNull SymbolicReason nestedReason;
	private final @Nullable String nestedRole;
	private final @NonNull SymbolicSimpleReason outerReason;

	public SymbolicNestedReason(@NonNull SymbolicReason nestedReason, @Nullable String nestedRole, @NonNull SymbolicSimpleReason outerReason) {
		this.outerReason = outerReason;
		this.nestedRole = nestedRole;
		this.nestedReason = nestedReason;
	}

	@Override
	public @NonNull String toString() {
		@NonNull StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
		nestedReason.toString(s);
		if (nestedRole != null) {
			s.append(" as ");
			s.append(nestedRole);
		}
		s.append(" => ");
		outerReason.toString(s);
	}
}