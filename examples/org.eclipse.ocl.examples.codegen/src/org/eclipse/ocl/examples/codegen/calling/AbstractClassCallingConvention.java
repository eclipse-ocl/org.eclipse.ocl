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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ClassCallingConvention defines a particular style of Class declaration.
 */
public abstract class AbstractClassCallingConvention implements ClassCallingConvention
{
	/**
	 * Create the appropriate CGClass less properties and operation.
	 */
	@Override
	public @NonNull CGClass createCGClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return CGModelFactory.eINSTANCE.createCGClass();
	}

	@Override
	public @NonNull String getName(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Class asClass) {
		return PivotUtil.getName(asClass);
	}
}
