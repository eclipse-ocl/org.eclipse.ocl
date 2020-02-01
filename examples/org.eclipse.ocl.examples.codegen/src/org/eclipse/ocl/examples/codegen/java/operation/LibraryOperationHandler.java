/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations Ltd and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.operation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;

/**
 * LibraryOperationHandler defines the API for a custom code generator for a library operation.
 */
public interface LibraryOperationHandler
{
	@NonNull Boolean generate(@NonNull CGLibraryOperationCallExp cgOperationCallExp);
	@NonNull Class<? extends LibraryOperation> getLibraryOperationClass();
}