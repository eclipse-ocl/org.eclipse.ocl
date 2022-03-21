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

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  EcoreOperationCallingConvention defines the support for the call of an Ecore operation within its *Impl file,
 *  </br>
 *  e.g. as eObject.eOperation(eArguments)
 */
public class EcoreOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull EcoreOperationCallingConvention INSTANCE = new EcoreOperationCallingConvention();

	public boolean canHandle(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		try {
			genModelHelper.getOperationAccessor(asOperation);
			return true;
		} catch (GenModelException e) {
			CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
			codeGenerator.addProblem(e);
		}
		return false;
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		EOperation eOperation = (EOperation) asOperation.getESObject();
		assert eOperation != null;
		boolean isRequired = asOperation.isIsRequired();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		try {
			genModelHelper.getOperationAccessor(asOperation);
			CGEcoreOperationCallExp cgEcoreOperationCallExp = CGModelFactory.eINSTANCE.createCGEcoreOperationCallExp();
			cgEcoreOperationCallExp.setEOperation(eOperation);
			Boolean ecoreIsRequired = codeGenerator.isNonNull(asOperationCallExp);
			if (ecoreIsRequired != null) {
				isRequired = ecoreIsRequired;
			}
			init(as2cgVisitor, cgEcoreOperationCallExp, cgSource, asOperationCallExp, isRequired);
			return cgEcoreOperationCallExp;
		} catch (GenModelException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public boolean isEcore() {
		return true;
	}
}
