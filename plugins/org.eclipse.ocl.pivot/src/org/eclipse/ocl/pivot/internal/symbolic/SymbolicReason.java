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
 * SymbolicReason maintains the reason for a failing symbolic evaluatin.
 *
 * @since 1.17
 */
public interface SymbolicReason
{
	@Override
	@NonNull String toString();
	void toString(@NonNull StringBuilder s);
}