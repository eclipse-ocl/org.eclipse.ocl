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
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.EInvokeOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

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
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		EOperation eOperation;
		if (libraryOperation instanceof EInvokeOperation) {
			eOperation = ((EInvokeOperation)libraryOperation).getEOperation();
		}
		else {
			assert (libraryOperation instanceof EObjectOperation);
		// System.out.println("Non EInvokeOperation overload for " + this);		// XXX
			eOperation = (EOperation) asOperation.getESObject();
		}
		assert (eOperation != null);
		assert !PivotUtil.isStatic(eOperation);
		try {
			genModelHelper.getGenOperation(eOperation);
			CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
			cgEcoreOperation.setEOperation(eOperation);
			return cgEcoreOperation;
		}
		catch (GenModelException e) {
			// No genmodel so fallback
		}
	//	assert false : "Fallback overload for " + this;		// XXX
		System.out.println("Fallback overload for " + this);		// XXX
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
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
		//	cgEcoreOperationCallExp.setCgThis(cgSource);
			cgEcoreOperationCallExp.getCgArguments().add(cgSource);
			init(as2cgVisitor, cgEcoreOperationCallExp, asOperationCallExp, cgOperation, isRequired);
			return cgEcoreOperationCallExp;
		} catch (GenModelException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
	//	Operation asOperation = cgOperationCallExp.getReferredOperation();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		CodeGenAnalyzer analyzer = cg2JavaVisitor.getAnalyzer();
		GenModelHelper genModelHelper = cg2JavaVisitor.getGenModelHelper();
		JavaCodeGenerator codeGenerator = cg2JavaVisitor.getCodeGenerator();
		CGTypeId cgTypeId = analyzer.getTypeId(asOperation.getOwningClass().getTypeId());
		//		TypeDescriptor requiredTypeDescriptor = context.getUnboxedDescriptor(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
	//	CGValuedElement cgThis = cg2JavaVisitor.getExpression(cgOperationCallExp.getCgThis());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
	//	List<@NonNullParameter> asParameters = asOperation.getOwnedParameters();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		//
	//	if (!js.appendLocalStatements(cgThis)) {
	//		return false;
	//	}
		if (!generateLocals(cg2JavaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		//
		String operationAccessor = genModelHelper.getOperationAccessor(asOperation);
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
		int iMax = Math.min(cgParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			CGParameter cgParameter = cgParameters.get(i);
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				js.appendAtomicReferenceTo(requiredTypeDescriptor, cgArgument);
				js.append(".");
				js.append(operationAccessor);
				js.append("(");
			}
			else {
				if (i > 1) {
					js.append(", ");
				}
				CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
			//	Parameter asParameter = ClassUtil.nonNullState(asParameters.get(i));
				Parameter asParameter = CGUtil.getParameter(cgParameter);
				GenParameter genParameter = genModelHelper.getGenParameter(asParameter);
				if (genParameter != null) {
					String rawBoundType = ClassUtil.nonNullState(genParameter.getRawBoundType());
					TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(argument);
					typeDescriptor.appendEcoreValue(js, rawBoundType, argument);
				}
				else {	// ? never happens
					CGTypeId cgParameterTypeId = analyzer.getTypeId(asParameter.getTypeId());
					TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgParameterTypeId.getElementId()));
					js.appendReferenceTo(parameterTypeDescriptor, argument);

				}
			}
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();		// Ecore operations are declared by genmodel
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGEcoreOperationCallExp cgEcoreOperationCallExp = (CGEcoreOperationCallExp)cgOperationCallExp;
		if ("specializeIn".equals(cgOperationCallExp.getReferredOperation().getName())) {
			getClass();		// XXX
		}
		CGOperation cgOperation = CGUtil.getOperation(cgEcoreOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		EOperation eOperation = cgEcoreOperationCallExp.getEOperation();
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgEcoreOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGParameter cgParameter = cgParameters.get(i);
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				boxingAnalyzer.rewriteAsGuarded(cgArgument, boxingAnalyzer.isSafe(cgEcoreOperationCallExp), "source for '" + asOperation + "'");
				boxingAnalyzer.rewriteAsEcore(cgArgument, eOperation.getEContainingClass());
			}
			else {
				Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
				if (asParameter != null) {
					EParameter eParameter = (EParameter) asParameter.getESObject();
				boxingAnalyzer.rewriteAsEcore(cgArgument, eParameter.getEType());
			}
			}
		}
		if (eOperation.isMany()) {
			boxingAnalyzer.rewriteAsAssertNonNulled(cgEcoreOperationCallExp);
		}
	}
}
