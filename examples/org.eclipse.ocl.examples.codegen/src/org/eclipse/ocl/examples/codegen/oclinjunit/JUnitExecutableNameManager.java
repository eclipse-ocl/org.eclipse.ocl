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
package org.eclipse.ocl.examples.codegen.oclinjunit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.NestedNameManager;
import org.eclipse.ocl.pivot.TypedElement;

/**
 * JUnitExecutableNameManager provides JUnit-specific overrides.
 */
public class JUnitExecutableNameManager extends ExecutableNameManager
{
	public JUnitExecutableNameManager(@NonNull ClassNameManager classNameManager, @NonNull NestedNameManager parentNameManager, @NonNull CGNamedElement cgScope, @Nullable TypedElement asOrigin) {
		super(classNameManager, parentNameManager, cgScope, asOrigin);
	}

/*	@Override
	public @Nullable CGVariable basicGetExecutorVariable() {
		CGVariable executorVariable = super.basicGetExecutorVariable();
		if (executorVariable == null) {
			executorVariable = lazyGetExecutorVariable();		// Always exists so must create
		}
		return executorVariable;
	} */

/*	@Override
	protected @NonNull CGVariable createExecutorVariable() {
		// create a 'parameter' variable that exposes the static field name as a variable
		//	assert outerContext == null;
		//	assert asScope instanceof Transformation;
		NameResolution executorNameResolution = globalNameManager.getExecutorNameResolution();
		//	Variable asExecutorVariable = PivotFactory.eINSTANCE.createVariable();
		//	asExecutorVariable.setName(executorNameResolution.getResolvedName());
		//	asVariable.setType(asType);
		//	asExecutorVariable.setIsRequired(true);
		//	asVariable.setOwnedInit(asInitExpression);
		//	PivotUtil.createVariable(executorNameResolution.getResolvedName(), JavaConstants.EXECUTOR_TYPE_ID, null);
		CGVariable cgExecutorVariable = analyzer.createCGFinalVariable(executorNameResolution, analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID), true);
		cgExecutorVariable.setNonInvalid();
		executorNameResolution.addCGElement(cgExecutorVariable);			// XXX share via createExecutor(init)
		return cgExecutorVariable;			// XXX who owns the variable ??
	} */
}