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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractDispatchOperation;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

import com.google.common.collect.Iterables;

/**
 *  VirtualOperationCallingConvention defines the support for the virtual dispatcher of a Complete OCL-defined operation
 *  that requires dynamic redirection to the appropriate overload. Creation of the dispatcher creates the full overload hierrachy.
 */
public class VirtualOperationCallingConvention extends AbstractCachedOperationCallingConvention
{
	public static final @NonNull VirtualOperationCallingConvention INSTANCE = new VirtualOperationCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
//		throw new UnsupportedOperationException();
		CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
	//	cgOperationCallExp.setSource(cgSource);
		if (cgSource != null) {
			cgOperationCallExp.getArguments().add(cgSource);
		}
		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		cgOperationCallExp.setAst(asOperationCallExp);
		cgOperationCallExp.setTypeId(analyzer.getCGTypeId(asOperationCallExp.getTypeId()));
		cgOperationCallExp.setReferredOperation(cgOperation);
		return cgOperationCallExp;
/*		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation finalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			finalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		CGClass currentClass = as2cgVisitor.basicGetCurrentClass();
		assert currentClass == null;
		CGOperationCallExp cgCallExp = null;
		if (finalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
				assert cgOperationCallExp2 == null;
				cgCallExp = volatileOperationCall(as2cgVisitor, asOperationCallExp, cgSource, finalOperation, (ConstrainedOperation)libraryOperation);
			}
		}
		if (cgCallExp == null) {
			Operation baseOperation = asOperation;	// FIXME
			cgCallExp = volatileOperationCall(as2cgVisitor, asOperationCallExp, cgSource, baseOperation, (ConstrainedOperation)libraryOperation);
		}
		cgCallExp.setCgOperation(cgOperation);
		return cgCallExp; */
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		assert analyzer.basicGetCGOperation(asOperation) == null;		// XXX
		CGCachedOperation cgDispatchOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();		// XXX ??? cache post rather than pre-dispatch
		System.out.println("createCGOperation " + NameUtil.debugSimpleName(cgDispatchOperation) + " => " +  NameUtil.debugSimpleName(asOperation) + " : " + asOperation);	// XXX debugging
		cgDispatchOperation.setRequired(asOperation.isIsRequired());
		cgDispatchOperation.setCallingConvention(this);
		analyzer.initAst(cgDispatchOperation, asOperation, false);				// XXX redundant wrt caller
		FinalAnalysis finalAnalysis = analyzer .getMetamodelManager().getFinalAnalysis();
		Iterable<@NonNull Operation> asOverrideOperations = finalAnalysis.getOverrides(asOperation);
		assert Iterables.contains(asOverrideOperations, asOperation);
		for (@NonNull Operation asOverrideOperation : asOverrideOperations) {
			analyzer.addVirtualCGOperation(asOverrideOperation, cgDispatchOperation);
		}
		List<@NonNull CGCachedOperation> cgOverrideOperations = new ArrayList<>();
		for (@NonNull Operation asOverrideOperation : asOverrideOperations) {
			CGOperation cgOverrideOperation = analyzer.generateNonVirtualOperationDeclaration(asOverrideOperation);
		/*	CGOperation cgOperation = asFinalOperation2cgOperation.get(asOverride);
			if (cgOperation == null) {
				cgOperation = createFinalCGOperationWithoutBody(asOverride);
				pushLocalContext(cgOperation, asOverride);
				popLocalContext(cgOperation);
				asNewOperations.add(asOverride);
			} */
			cgOverrideOperations.add((CGCachedOperation)cgOverrideOperation);		// XXX Bad cast
		}
	//		if (Iterables.size(asOverrides) > 1) {
	//			return VirtualOperationCallingConvention.INSTANCE;		// Need a polymorphic dispatcher
	//		}
	//	}
	//	throw new UnsupportedOperationException();
/*		assert as2cgVisitor.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);
		return CGModelFactory.eINSTANCE.createCGLibraryOperation(); */
	//	CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
	//	cgOperationCallExp.setSource(cgSource);
	//	cgOperationCallExp.setThisIsSelf(false);
	//	for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
	//		CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
	//		cgOperationCallExp.getArguments().add(cgArgument);
	//	}
	//	setAst(cgOperationCallExp, element);
	//	cgOperationCallExp.setReferredOperation(asOperation);
	//	if (asOverrideOperations != null) {
	//		CGOperation cgOperation = createVirtualCGOperationWithoutBody(asOperation, cgOperations);
				//				asNewOperations.add(asOperation);
	//			currentClass.getOperations().add(cgOperation);
	//		}
	//	}
		cgDispatchOperation.getFinalOperations().addAll(cgOverrideOperations);
		return cgDispatchOperation;
	}

	/*protected public @NonNull CGOperation createVirtualCGOperationWithoutBody(@NonNull Operation asOperation, @NonNull List<@NonNull CGCachedOperation> cgOperations) {
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		initAst(cgOperation, asOperation);
		LocalContext savedLocalContext = pushLocalContext(cgOperation, asOperation);
		cgOperation.setRequired(asOperation.isIsRequired());
		LanguageExpression specification = asOperation.getBodyExpression();
		if (specification != null) {
			Variables savedVariablesStack = variablesStack;
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				variablesStack = new Variables(null);
				createParameters(cgOperation, query);
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				variablesStack = savedVariablesStack;
			}
		}
		cgOperation.getFinalOperations().addAll(cgOperations);
		context.addVirtualCGOperation(asOperation, cgOperation);
		popLocalContext(savedLocalContext);
		return cgOperation;
	} */

	protected void doCachedOperationDispatchInstaller(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGCachedOperation cgOperation) {
		js.append("private ");
		js.append(getNativeOperationClassName(cgOperation));
		js.append("() {\n");
		js.pushIndentation(null);
		for (@NonNull CGCachedOperation cgFinalOperation : ClassUtil.nullFree(cgOperation.getFinalOperations())) {
			Operation asFinalOperation = CGUtil.getAST(cgFinalOperation);
			js.append("install(");
			js.appendClassReference(null, cgFinalOperation.getParameters().get(0));
			js.append(".class, ");
			js.append(getNativeOperationDirectInstanceName(asFinalOperation));
			js.append(");\n");
		}
		js.popIndentation();
		js.append("}\n");
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		CGCachedOperation cgCachedOperation = (CGCachedOperation)cgOperation;
		assert cgCachedOperation.getFinalOperations().size() > 0;
		String operationClassName = getNativeOperationClassName(cgCachedOperation);
		js.append("protected class ");
		js.append(operationClassName);
		js.append(" extends ");
		js.appendClassReference(null, AbstractDispatchOperation.class);
		js.pushClassBody(operationClassName);
		js.append("\n");					// XXX delete me
		doCachedOperationDispatchInstaller(cg2javaVisitor, js, cgCachedOperation);
		js.append("\n");
		doCachedOperationEvaluate(cg2javaVisitor, js, cgCachedOperation);
		js.popClassBody(false);
		//
		js.append("\n");
		doCachedOperationClassInstance(cg2javaVisitor, js, cgCachedOperation);
		return true;
	}

	@Override
	protected @NonNull String getNativeOperationClassName(@NonNull CGOperation cgOperation) {	// FIXME unique
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		return "VCACHE_" + getNativeOperationName(asOperation);
	}
}
