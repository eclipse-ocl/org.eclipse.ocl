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
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  ForeignOperationCallingConvention defines the support for the call of an operation realized by an
 *  implementation in the *Tables class.
 *
 *  ???
 */
public class EcoreForeignOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull EcoreForeignOperationCallingConvention INSTANCE = new EcoreForeignOperationCallingConvention();

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		assert cgSource != null;
		assert !asOperation.isIsStatic();
		boolean isRequired = asOperation.isIsRequired();
		as2cgVisitor.getAnalyzer().addForeignFeature(asOperation);
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		init(as2cgVisitor, cgForeignOperationCallExp, cgSource, asOperationCallExp, isRequired);
		return cgForeignOperationCallExp;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}
}
