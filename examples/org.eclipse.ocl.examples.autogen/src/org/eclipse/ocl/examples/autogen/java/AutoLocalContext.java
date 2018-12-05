/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.java;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;

/**
 * A AutoLocalContext maintains the Java-specific local context for generation of Auto code.
 */
public class AutoLocalContext<@NonNull CG extends AutoCodeGenerator> extends JavaLocalContext<CG>
{
	public AutoLocalContext(@NonNull AutoGlobalContext<CG> globalContext, @NonNull CGElement cgScope) {
		super(globalContext, cgScope);
	}

	@SuppressWarnings("unchecked")
	@Override
	public @NonNull AutoGlobalContext<CG> getGlobalContext() {
		return (AutoGlobalContext<CG>) globalContext;
	}

	@Override
	public @NonNull CGValuedElement getIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return getGlobalContext().getIdResolverVariable(cgValuedElement);
	}
}
