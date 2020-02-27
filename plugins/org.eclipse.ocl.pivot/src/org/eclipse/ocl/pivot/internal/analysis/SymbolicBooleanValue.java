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
 * SymbolicValue defines a symbolic evauation result
 * .
 * @since 1.12
 */
public interface SymbolicBooleanValue extends SymbolicValue
{
	public static final @NonNull SymbolicBooleanValue KNOWN_FALSE = new AbstractSymbolicValue.KnownBooleanValue(false);
	public static final @NonNull SymbolicBooleanValue KNOWN_TRUE = new AbstractSymbolicValue.KnownBooleanValue(true);

	boolean isFalse();
	boolean isTrue();
}
