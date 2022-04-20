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

import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
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

	public boolean canHandle(@NonNull CodeGenerator codeGenerator, @NonNull Operation asOperation) {
		GenModelHelper genModelHelper = codeGenerator.getGenModelHelper();
		try {
			genModelHelper.getOperationAccessor(asOperation);
			return true;
		} catch (GenModelException e) {
			codeGenerator.addProblem(e);
		}
		return false;
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
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
			init(as2cgVisitor, cgEcoreOperationCallExp, asOperationCallExp, cgOperation, cgSource, isRequired);
			return cgEcoreOperationCallExp;
		} catch (GenModelException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public @NonNull Boolean generateJava(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		Operation pOperation = cgOperationCallExp.getReferredOperation();
		CodeGenAnalyzer analyzer = cg2JavaVisitor.getAnalyzer();
		GenModelHelper genModelHelper = cg2JavaVisitor.getGenModelHelper();
		JavaCodeGenerator codeGenerator = cg2JavaVisitor.getCodeGenerator();
		CGTypeId cgTypeId = analyzer.getTypeId(pOperation.getOwningClass().getTypeId());
		//		TypeDescriptor requiredTypeDescriptor = context.getUnboxedDescriptor(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
		CGValuedElement cgThis = cg2JavaVisitor.getExpression(cgOperationCallExp.getCgThis());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
		if (!js.appendLocalStatements(cgThis)) {
			return false;
		}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		String operationAccessor = genModelHelper.getOperationAccessor(pOperation);
		//		Class<?> actualBoxedReturnClass = getBoxedReturnClass(libraryOperation, arguments.size());
		//		Class<?> unboxedSourceClass;
		//		try {		// FIXME this peeking is only needed for the Pivot Domain/non-Domain levels
		//			unboxedSourceClass = genModelHelper.getEcoreInterfaceClass(eOperation.getEContainingClass());
		//		}
		//		catch (GenModelException e) {
		//			unboxedSourceClass = getJavaClass(source);
		//		}
		Element asOperationCallExp = cgOperationCallExp.getAst();
		Boolean ecoreIsRequired = asOperationCallExp instanceof OperationCallExp ? codeGenerator.isNonNull((OperationCallExp) asOperationCallExp) : null;
		cg2JavaVisitor.appendSuppressWarningsNull(cgOperationCallExp, ecoreIsRequired);
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		js.appendAtomicReferenceTo(requiredTypeDescriptor, cgThis);
		js.append(".");
		js.append(operationAccessor);
		js.append("(");
		int iMax = Math.min(pParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if (i > 0) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
			Parameter pParameter = ClassUtil.nonNullState(pParameters.get(i));
			GenParameter genParameter = genModelHelper.getGenParameter(pParameter);
			if (genParameter != null) {
				String rawBoundType = ClassUtil.nonNullState(genParameter.getRawBoundType());
				TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(argument);
				typeDescriptor.appendEcoreValue(js, rawBoundType, argument);
			}
			else {	// ? never happens
				CGTypeId cgParameterTypeId = analyzer.getTypeId(pParameter.getTypeId());
				TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgParameterTypeId.getElementId()));
				js.appendReferenceTo(parameterTypeDescriptor, argument);

			}
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean isEcore() {
		return true;
	}
}
