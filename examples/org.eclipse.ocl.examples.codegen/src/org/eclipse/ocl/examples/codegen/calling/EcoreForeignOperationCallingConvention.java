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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  EcoreForeignOperationCallingConvention defines the support for the call of an operation for which an EOPeration is
 *  defined but for which there is no genmodelled implementation. THe implementation must therefore be realized
 *  in the *Tables class.
 *
 *  ???
 */
public class EcoreForeignOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull EcoreForeignOperationCallingConvention INSTANCE = new EcoreForeignOperationCallingConvention();

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert libraryOperation instanceof EObjectOperation;
		EOperation eOperation = (EOperation) asOperation.getESObject();
		assert eOperation != null;
		assert (!PivotUtil.isStatic(eOperation));
		try {
			genModelHelper.getGenOperation(eOperation);
			CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
			cgEcoreOperation.setEOperation(eOperation);
			return cgEcoreOperation;
		}
		catch (GenModelException e) {
			// No genmodel so fallback
		}
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
	//	assert cgSource != null;
	//	assert !asOperation.isIsStatic();
		boolean isRequired = asOperation.isIsRequired();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		analyzer.addForeignFeature(asOperation);
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		addExecutorArgument(as2cgVisitor, cgForeignOperationCallExp);
	//	boolean isStatic = isStatic(cgOperation);
	//	assert isStatic == (cgSource == null);
		if (cgSource != null) {
			cgForeignOperationCallExp.getCgArguments().add(cgSource);
		}
		init(as2cgVisitor, cgForeignOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		return cgForeignOperationCallExp;
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		addExecutorParameter(as2cgVisitor, cgOperation);
		addExpressionInOCLParameters(as2cgVisitor, cgOperation, expressionInOCL);
//		super.createParameters(as2cgVisitor, cgOperation, expressionInOCL);
	}

	@Override
	public @NonNull Boolean generateJava(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGForeignOperationCallExp cgForeignOperationCallExp = (CGForeignOperationCallExp)cgOperationCallExp;
		//
		if (!generateLocals(cg2JavaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		//
		js.appendDeclaration(cgForeignOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2JavaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2JavaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}

//	@Override
//	public boolean isStatic(@NonNull CGOperation cgOperation) {
//		return true;
//	}
}
