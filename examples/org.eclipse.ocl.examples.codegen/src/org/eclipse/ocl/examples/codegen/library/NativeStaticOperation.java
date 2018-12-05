/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;

/**
 * The instance of NativeStaticOperation supports evaluation of an operation using native language facilities
 * (i.e X.operation()) when code generated).
 */
public class NativeStaticOperation extends AbstractBinaryOperation
{
	public static final @NonNull NativeStaticOperation INSTANCE = new NativeStaticOperation();

	public NativeStaticOperation() {}

//	@Override
//	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull DomainCallExp callExp, @Nullable Object sourceValue) {
//		throw new UnsupportedOperationException();	// Only evaluated in code generated form; see CG2JavaVisitor.visitCGNativeOperationCallExp
//	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		throw new UnsupportedOperationException();	// Only evaluated in code generated form; see CG2JavaVisitor.visitCGNativeOperationCallExp
	}
}