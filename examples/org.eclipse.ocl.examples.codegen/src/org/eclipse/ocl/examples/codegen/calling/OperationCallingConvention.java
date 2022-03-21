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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;

/**
 *  OperationCallingConvention defines a particular style of Operation call with support for
 *  generation of a declaration or invocation.
 */
public interface OperationCallingConvention extends CallingConvention
{
	/**
	 * Return true if this OperationCallingConvention can handle asOperation.
	 */
//	boolean canHandle(@NonNull LibraryOperation libraryOperation);

	/**
	 * Create the appropriate CGOperationCallExp for asOperationCallExp with cgSource, or retirn null
	 * if this OperationCallingConvention cannot handle it.
	 */
	@NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp);
}
