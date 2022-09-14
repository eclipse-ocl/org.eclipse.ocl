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
package org.eclipse.ocl.examples.codegen.naming;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.pivot.LoopExp;

/**
 * A LoopNameManager supervises the variable names allocated within the scope of a loop.
 */
public class LoopNameManager extends ExecutableNameManager
{
	public LoopNameManager(@NonNull ClassNameManager classNameManager, @NonNull ExecutableNameManager parentNameManager, @NonNull CGIterationCallExp cgLoop) {
		super(classNameManager, parentNameManager, cgLoop);
	}

	public @NonNull LoopExp getASLoopExp() {
		return (LoopExp)asScope;
	}

	public @NonNull CGIterationCallExp getCGIterationCallExp() {
		return (CGIterationCallExp)cgScope;
	}
}
