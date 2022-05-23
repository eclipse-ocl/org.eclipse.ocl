/*******************************************************************************
 * Copyright (c) 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinjunit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * A JavaLocalContext maintains the Java-specific context for generation of code from a CGOperation.
 */
public class JUnitLocalContext extends JavaLocalContext<@NonNull JUnitCodeGenerator>
{
	public JUnitLocalContext(@NonNull JavaGlobalContext<@NonNull JUnitCodeGenerator> globalContext, @Nullable JUnitLocalContext outerContext, @NonNull CGNamedElement cgScope, @NonNull NamedElement asScope) {
		super(globalContext, outerContext, cgScope, asScope);
	}

	@Override
	public @NonNull CGParameter createExecutorParameter() {
		return super.createExecutorParameter();
	}

	@Override
	public @NonNull CGParameter createTypeIdParameter() {
		return super.createTypeIdParameter();
	}

	@Override
	public @NonNull CGVariable getExecutorVariable() {
		return getExecutorParameter();
	}
}
