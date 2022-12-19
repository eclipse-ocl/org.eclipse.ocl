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
package org.eclipse.ocl.examples.codegen.calling;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;

/**
 *  MutableCachePropertyCallingConvention defines the support for the lazily mutable result property of a chahe realizing an operation call.
 */
@Deprecated // not needed
public class MutableCachePropertyCallingConvention extends AbstractCachePropertyCallingConvention
{
//	public static final @NonNull MutableCachePropertyCallingConvention INSTANCE = new MutableCachePropertyCallingConvention();

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.append("private");
		return super.generateJavaDeclaration(cg2javaVisitor, cgProperty);
	}
}
