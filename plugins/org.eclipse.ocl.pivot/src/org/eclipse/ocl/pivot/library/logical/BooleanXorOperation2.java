/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.logical;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;

/**
 * BooleanXorOperation2 realises the 2-valued Boolean::xor() library operation.
 * 
 * @since 1.3
 */
public class BooleanXorOperation2 extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanXorOperation2 INSTANCE = new BooleanXorOperation2();

	@Override
	public @Nullable Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		if (left != right) {
			return TRUE_VALUE;
		}
		else  {
			return FALSE_VALUE;
		}
	}
}
