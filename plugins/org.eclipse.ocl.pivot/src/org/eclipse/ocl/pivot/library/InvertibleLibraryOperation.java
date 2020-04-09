/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * An InvertibleLibraryOperation support deduction of operation input values from known output values.
 *
 * @since 1.12
 */
public interface InvertibleLibraryOperation
{
	/**
	 * Update symbolicExecutor from any deductions that can be made from knowing that resultValue satisfies resultConstraint.
	 */
	void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicValue resultValue, @NonNull SymbolicConstraint resultConstraint);
}
