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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
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
public class EcoreForeignOperationCallingConvention extends ForeignOperationCallingConvention
{
	public static final @NonNull EcoreForeignOperationCallingConvention INSTANCE = new EcoreForeignOperationCallingConvention();

	@Override
	protected void appendForeignOperationName(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		Operation asReferredOperation = CGUtil.getReferredOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".op_");
		js.append(PivotUtil.getName(asReferredOperation));
	}

/*	@Override
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
	} */

	@Override		// XXX cf super
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		analyzer.addForeignFeature(asOperation);
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		addExecutorArgument(as2cgVisitor, cgForeignOperationCallExp);
		if (cgSource != null) {
			cgForeignOperationCallExp.getCgArguments().add(cgSource);
		}
		init(as2cgVisitor, cgForeignOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		return cgForeignOperationCallExp;
	}

/*	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		addExecutorParameter(as2cgVisitor, cgOperation);
		addExpressionInOCLParameters(as2cgVisitor, cgOperation, expressionInOCL);
	} */

	@Override	// XXX cf super
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		if (!generateLocals(cg2JavaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2JavaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2JavaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	}

/*	@Override
	public boolean isBoxed() {
		return true;
	} */
}
