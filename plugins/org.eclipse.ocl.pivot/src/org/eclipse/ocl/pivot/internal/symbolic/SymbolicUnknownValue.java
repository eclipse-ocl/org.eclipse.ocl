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
import org.eclipse.ocl.pivot.Type;

/**
 * @since 1.17
 */
public class SymbolicUnknownValue extends AbstractLeafSymbolicValue {

	public SymbolicUnknownValue(@NonNull String name, @NonNull Type type, @Nullable SymbolicReason mayBeNullReason, @Nullable SymbolicReason mayBeInvalidReason) { //, @NonNull SymbolicValue value) {
		super(name, type, mayBeNullReason, mayBeInvalidReason, null);
	}
}
