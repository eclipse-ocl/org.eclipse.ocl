/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import org.eclipse.jdt.annotation.NonNull;

/**
 *  The SupportOperationCallingConvention defines the support for the call of a native (Java) operation
 *  defined by a Java method and using boxed source and arguments.
 *  </br>
 *  e.g. as aModelManager.basicGetForeignPropertyValue(arguments)
 */
public class SupportOperationCallingConvention extends NativeOperationCallingConvention
{
	public static final @NonNull SupportOperationCallingConvention INSTANCE = new SupportOperationCallingConvention();

	@Override
	public boolean isBoxed() {
		return true;
	}

	@Override
	public boolean isUnboxed() {
		return false;
	}
}
