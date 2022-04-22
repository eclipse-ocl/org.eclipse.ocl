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
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ForeignOperationCallingConvention defines the support for the call of an operation realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ForeignOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull ForeignOperationCallingConvention INSTANCE = new ForeignOperationCallingConvention();

	@Override
	protected void appendDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		appendCommentWithOCL(js, cgOperation);
		//
		js.append("public static ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" op_");
		js.appendValueName(cgOperation);
	}

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
 /*		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert !(libraryOperation instanceof EObjectOperation);
		assert (libraryOperation instanceof ForeignOperation);
		EOperation eOperation = (EOperation) asOperation.getESObject();
		if (eOperation != null) {
			boolean isForeign = PivotUtil.isStatic(eOperation);
			if (isForeign) {
				return CGModelFactory.eINSTANCE.createCGLibraryOperation();
			}
			else {
				try {
					genModelHelper.getGenOperation(eOperation);
					CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
					cgEcoreOperation.setEOperation(eOperation);
					return cgEcoreOperation;
				}
				catch (GenModelException e) {
					return CGModelFactory.eINSTANCE.createCGLibraryOperation();
				}
			}
		}
		assert false : "Fallback overload for " + this;		// XXX
		return CGModelFactory.eINSTANCE.createCGLibraryOperation(); */
	//	PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
	//	LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
	//	assert libraryOperation instanceof ForeignOperation;	-- ForeignOperationCallingConvention
	//	assert libraryOperation instanceof EObjectOperation;	-- EcoreForeignOperationCallingConvention
		EOperation eOperation = (EOperation) asOperation.getESObject();
		assert eOperation != null;
		if (!PivotUtil.isStatic(eOperation)) {
			try {
				genModelHelper.getGenOperation(eOperation);
				CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
				cgEcoreOperation.setEOperation(eOperation);
				return cgEcoreOperation;
			}
			catch (GenModelException e) {
				// No genmodel so fallback
			}
		}
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		assert cgSource == null;
		assert asOperation.isIsStatic();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		analyzer.addForeignFeature(asOperation);
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		addExecutorArgument(as2cgVisitor, cgForeignOperationCallExp);
		init(as2cgVisitor, cgForeignOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		return cgForeignOperationCallExp;
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		addExecutorParameter(as2cgVisitor, cgOperation);
		super.createCGParameters(as2cgVisitor, cgOperation, expressionInOCL);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		if (!generateLocals(cg2JavaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2JavaVisitor, js, cgOperationCallExp);
		js.append("(");
	/*	int iMax = Math.min(cgParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if (i > 0) { // || !isStatic) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			CGParameter cgParameter = cgParameters.get(i);
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		} */
		generateArgumentList(cg2JavaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}
}
