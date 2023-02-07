/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.pivot.TypedElement;

/**
 * SupportedExecutableNameManager provides overrides to use the rootExecutor of a Support class.
 */
public class SupportedExecutableNameManager extends ExecutableNameManager
{
	public SupportedExecutableNameManager(@NonNull ClassNameManager classNameManager, @NonNull NestedNameManager parentNameManager, @NonNull CGNamedElement cgScope, @Nullable TypedElement asOrigin) {
		super(classNameManager, parentNameManager, cgScope, asOrigin);
	}

	@Override
	public @Nullable CGVariable basicGetExecutorVariable() {
		CGVariable executorVariable = super.basicGetExecutorVariable();
		if (executorVariable == null) {
			executorVariable = lazyGetExecutorVariable();		// Always exists so must create
		}
		return executorVariable;
	}

	@Override
	protected @NonNull CGVariable createExecutorVariable() {
		// create a variable that exposes the static field name as a variable
		NameResolution rootExecutorNameResolution = globalNameManager.getRootExecutorNameResolution();
		CGVariable cgExecutorVariable = analyzer.createCGFinalVariable(rootExecutorNameResolution, analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID), true);
		cgExecutorVariable.setNonInvalid();
		rootExecutorNameResolution.addCGElement(cgExecutorVariable);
		return cgExecutorVariable;
	}
}