/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;

public class AutoGlobalContext<@NonNull CG extends AutoCodeGenerator> extends JavaGlobalContext<CG> {

	public AutoGlobalContext(CG codeGenerator, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		super(codeGenerator);
		//		nameManager.reserveName(JavaConstants.EXECUTOR_NAME, null);
		nameManager.reserveName(JavaConstants.EVALUATION_CACHE_NAME, null);
	}

	@Override
	protected @NonNull AutoLocalContext<CG> createNestedContext(@NonNull CGElement cgScope) {
		return new AutoLocalContext<CG>(this, cgScope);
	}

	public @NonNull CGValuedElement getIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return codeGenerator.getIdResolverVariable();
	}
}
