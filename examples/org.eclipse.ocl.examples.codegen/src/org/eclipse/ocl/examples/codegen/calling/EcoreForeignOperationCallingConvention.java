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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  EcoreForeignOperationCallingConvention defines the support for the call of an operation for which an EOperation is
 *  defined but for which there is no genmodelled implementation. The implementation must therefore be realized
 *  in the *Tables class.
 *
 *  ???
 */
public class EcoreForeignOperationCallingConvention extends ForeignOperationCallingConvention
{
	private static final @NonNull EcoreForeignOperationCallingConvention INSTANCE = new EcoreForeignOperationCallingConvention();

	public static @NonNull EcoreForeignOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

/*	@Override
	protected void appendForeignOperationName(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		Operation asReferredOperation = CGUtil.getReferredOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".op_");
		js.append(PivotUtil.getName(asReferredOperation));
	} */

	@Override		// XXX cf super
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		initCallExp(analyzer, cgForeignOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgForeignOperationCallExp);
		ExecutableNameManager operationNameManager = analyzer.getGlobalNameManager().useOperationNameManager(cgOperation);
		CGVariable executorVariable = operationNameManager.getExecutorVariable();
		cgArguments.add(analyzer.createCGVariableExp(executorVariable));
		if (cgSource != null) {
			cgArguments.add(cgSource);
		}
		initCallArguments(analyzer, cgForeignOperationCallExp);
		return cgForeignOperationCallExp;
	}
}
