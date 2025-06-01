/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.codegen.java.iteration;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.codegen.java.JavaStream;

public class GatherIteration2Java extends AbstractAccumulation2Java
{
	public static final @NonNull GatherIteration2Java INSTANCE = new GatherIteration2Java();

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
		js.appendValueName(cgAccumulator);
		js.append(".add(");
		js.appendValueName(cgBody);
		js.append(");\n");
		return true;
	}
}