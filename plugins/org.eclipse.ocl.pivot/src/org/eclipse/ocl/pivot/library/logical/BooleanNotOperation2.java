/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.logical;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;

/**
 * BooleanNotOperation2 realises the 2-valued not() library operation.
 *
 * @since 1.3
 */
public class BooleanNotOperation2 extends AbstractSimpleUnaryOperation
{
	public static final @NonNull BooleanNotOperation2 INSTANCE = new BooleanNotOperation2();

	@Override
	public @NonNull Boolean evaluate(@Nullable Object argument) {
		if (argument == Boolean.FALSE) {
			return TRUE_VALUE;
		}
		else {
			return FALSE_VALUE;
		}
	}
}
