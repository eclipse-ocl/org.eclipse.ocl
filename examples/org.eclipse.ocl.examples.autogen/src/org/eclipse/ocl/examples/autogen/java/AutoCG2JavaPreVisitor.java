/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaPreVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;

public class AutoCG2JavaPreVisitor extends CG2JavaPreVisitor
{
	public AutoCG2JavaPreVisitor(@NonNull JavaGlobalContext<@NonNull ?> javaContext) {
		super(javaContext);
	}

	@Override
	protected @Nullable CGValuedElement installIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return localContext.getIdResolverVariable(cgValuedElement);
	}
}
