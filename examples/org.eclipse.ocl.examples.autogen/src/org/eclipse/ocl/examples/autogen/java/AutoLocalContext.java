/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * A AutoLocalContext maintains the Java-specific local context for generation of Auto code.
 */
public class AutoLocalContext<@NonNull CG extends AutoCodeGenerator> extends JavaLocalContext<CG>
{
	public AutoLocalContext(@NonNull AutoGlobalContext<CG> globalContext, @Nullable AutoLocalContext<@NonNull CG> outerContext,
			@NonNull CGNamedElement cgScope, @NonNull NamedElement asScope) {
		super(globalContext, outerContext, cgScope, asScope, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public @NonNull AutoGlobalContext<CG> getGlobalContext() {
		return (AutoGlobalContext<CG>) globalContext;
	}
}
