/**
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.15
 */
public abstract class AbstractSymbolicValue extends ValueImpl implements SymbolicValue
{
	@Override
	public @NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue) {
	//	@NonNull SymbolicValue thisBaseValue = this.getBaseValue();
		@NonNull SymbolicValue resultValue = unrefinedValue.getBaseValue();
		if (!mayBeInvalid() && unrefinedValue.mayBeInvalid()) {
			resultValue = AbstractRefinedSymbolicValue.createNotInvalidValue(resultValue);
		}
		if (!mayBeNull() && unrefinedValue.mayBeNull()) {
			resultValue = AbstractRefinedSymbolicValue.createNotNullValue(resultValue);
		}
		if (!mayBeZero() && unrefinedValue.mayBeZero()) {
			resultValue = AbstractRefinedSymbolicValue.createNotZeroValue(resultValue);
		}
		return resultValue;
	}

	@Override
	public boolean isRefinementOf(@NonNull SymbolicValue unrefinedValue) {
		@NonNull SymbolicValue thisBaseValue = this.getBaseValue();
		@NonNull SymbolicValue thatBaseValue = unrefinedValue.getBaseValue();
		if (thisBaseValue != thatBaseValue) {
			return false;
		}
		if (mayBeInvalid() && !unrefinedValue.mayBeInvalid()) {
			return false;
		}
		if (mayBeNull() && !unrefinedValue.mayBeNull()) {
			return false;
		}
		if (mayBeZero() && !unrefinedValue.mayBeZero()) {
			return false;
		}
		return true;
	}
}
