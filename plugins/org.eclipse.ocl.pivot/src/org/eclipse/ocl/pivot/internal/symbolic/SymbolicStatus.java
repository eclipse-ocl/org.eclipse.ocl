/**
 * Copyright (c) 2010, 2021 Willink Transformations and others.
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
 * SymbolicStatus maintains the status of a partial knowledge aspect such as isInvalid/isZero/isNull/isTrue
 * @since 1.16
 */
public class SymbolicStatus
{
	public static @NonNull SymbolicStatus SATISFIED = new SymbolicStatus(Enum.SATISFIED);
	public static @NonNull SymbolicStatus UNDECIDED = new SymbolicStatus(Enum.UNDECIDED);
	public static @NonNull SymbolicStatus UNSATISFIED = new SymbolicStatus(Enum.UNSATISFIED);

	private enum Enum {SATISFIED, UNSATISFIED, UNDECIDED};

	public static @NonNull SymbolicStatus valueOf(boolean isOk) {
		return isOk ? SATISFIED : UNSATISFIED;
	}

	private final @NonNull Enum state;

	private SymbolicStatus(@NonNull Enum state) {
		this.state = state;
	}

	public @Nullable Boolean asSame(@NonNull SymbolicStatus that) {
		if ((this.state == Enum.UNDECIDED) || (that.state == Enum.UNDECIDED)) {
			return null;
		}
		else if ((this.state == Enum.UNSATISFIED) && (that.state == Enum.UNSATISFIED)) {
			return null;
		}
		else {
			return this.state == that.state;
		}
	}

	public boolean isSatisfied() {
		return state == Enum.SATISFIED;
	}

	public boolean isUndecided() {
		return state == Enum.UNDECIDED;
	}

	public boolean isUnsatisfied() {
		return state == Enum.UNSATISFIED;
	}

/*	public boolean mayBeEquals(@NonNull SymbolicStatus that) {
		switch (state) {
			case SATISFIED: return that.state != Enum.UNSATISFIED;
			case UNSATISFIED: return that.state != Enum.SATISFIED;
			default: return true;
		}
	} */

	public boolean mayBeSatisfied() {
		return state != Enum.UNSATISFIED;
	}

	public boolean mayBeUnsatisfied() {
		return state != Enum.SATISFIED;
	}

	public @NonNull SymbolicStatus not() {
		switch (state) {
			case SATISFIED: return UNSATISFIED;
			case UNSATISFIED: return SATISFIED;
			default: return UNDECIDED;			// UNDECIDED => UNDECIDED
		}
	}

	@SuppressWarnings("null")
	@Override
	public @NonNull String toString() {
		return state.name();
	}
}