/*******************************************************************************
 * Copyright (c) 2016, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Adolfo Sanchez-Barbudo Herrera
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.java;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.NamedElement;

public class AutoGlobalContext<@NonNull CG extends AutoCodeGenerator> extends JavaGlobalContext<CG> {

	public AutoGlobalContext(CG codeGenerator, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		super(codeGenerator);
		//		globalNameManager.reserveName(JavaConstants.EXECUTOR_NAME);
	//	globalNameManager.declareReservedName(null, JavaConstants.EVALUATION_CACHE_NAME);
	}

	@Override
	public @NonNull AutoLocalContext<CG> createLocalContext(@Nullable JavaLocalContext<@NonNull ?> outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asNamedElement) {
		return new AutoLocalContext<CG>(this, (AutoLocalContext<@NonNull CG>)outerContext, cgNamedElement, asNamedElement);
	}

	public @NonNull CGValuedElement getIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return codeGenerator.getIdResolverVariable();
	}
}
