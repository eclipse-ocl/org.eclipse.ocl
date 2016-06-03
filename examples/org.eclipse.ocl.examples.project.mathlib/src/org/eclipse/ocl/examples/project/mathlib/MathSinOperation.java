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
package org.eclipse.ocl.examples.project.mathlib;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.RealValue;

/**
 * RealSinOperation realises the math::sin(x) library operation.
 */
public class MathSinOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull MathSinOperation INSTANCE = new MathSinOperation();

	@Override
	public @NonNull RealValue evaluate(@Nullable Object sourceVal, @Nullable Object argVal) {
		RealValue numericValue = asRealValue(argVal); 
		return ValueUtil.realValueOf(Math.sin(numericValue.doubleValue()));
	}
}
