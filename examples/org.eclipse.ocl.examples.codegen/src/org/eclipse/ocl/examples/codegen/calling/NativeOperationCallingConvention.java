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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.library.NativeStaticOperation;
import org.eclipse.ocl.examples.codegen.library.NativeVisitorOperation;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.NativeOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  NativeOperationCallingConvention defines the support for the call of a native (Java) operation
 *  defined by a Java method and using unboxed source and arguments.
 *  </br>
 *  e.g. as anObject.anOperation(arguments)
 */
public class NativeOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull NativeOperationCallingConvention INSTANCE = new NativeOperationCallingConvention();

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert (libraryOperation instanceof NativeOperation) || (libraryOperation instanceof NativeStaticOperation) || (libraryOperation instanceof NativeVisitorOperation);
		CGNativeOperation cgOperation = CGModelFactory.eINSTANCE.createCGNativeOperation();
		initOperation(analyzer, cgOperation, asOperation);
		analyzer.addCGOperation(cgOperation);
		return cgOperation;
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
	//	throw new UnsupportedOperationException();		// FIXME construction is irregular
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		Method method = ((JavaLanguageSupport.JavaNativeOperation)asOperation.getImplementation()).getMethod();
		CGNativeOperationCallExp cgNativeOperationCallExp = analyzer.createCGNativeOperationCallExp(method, this);
	//	cgNativeOperationCallExp.setThisIsSelf(true);
		initCallExp(analyzer, cgNativeOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		initCallArguments(analyzer, cgNativeOperationCallExp);
		if ((cgSource != null) && !Modifier.isStatic(method.getModifiers())) {
			cgNativeOperationCallExp.setCgThis(cgSource);
		}
		return cgNativeOperationCallExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGNativeOperationCallExp cgNativeOperationCallExp = (CGNativeOperationCallExp)cgOperationCallExp;
		CGValuedElement cgThis = cgNativeOperationCallExp.getCgThis();
		CGValuedElement cgThis2 = cgThis != null ? cg2javaVisitor.getExpression(cgThis) :  null;
		//
		if ((cgThis2 != null) && !js.appendLocalStatements(cgThis2)) {
			return false;
		}
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		//
		Method jMethod = cgNativeOperationCallExp.getMethod();
		List<CGValuedElement> cgArguments = cgNativeOperationCallExp.getArguments();
		Class<?>[] jParameterTypes = jMethod.getParameterTypes();
		js.appendDeclaration(cgNativeOperationCallExp);
		js.append(" = ");
		if (cgThis2 != null) {
			js.appendValueName(cgThis2);
		}
		else {
			js.appendClassReference(null, jMethod.getDeclaringClass());
		}
		js.append(".");
		js.append(jMethod.getName());
		js.append("(");
		int iMax = Math.min(jParameterTypes.length, cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if (i > 0) {
				js.append(", ");
			}
			Class<?> jParameterType = jParameterTypes[i];
			CGValuedElement cgArgument = cgArguments.get(i);
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendValueName(argument);
			if (jParameterType == Object[].class) {
				js.append(".toArray(new Object[");
				js.appendValueName(argument);
				js.append(".size()])");
			}
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();		// Native operations are declared natively
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		CGNativeOperation cgNativeOperation = (CGNativeOperation)cgOperation;
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgNativeOperation);
		boxingAnalyzer.rewriteAsUnboxed(cgNativeOperation.getBody());
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		assert cgOperationCallExp  instanceof CGNativeOperationCallExp;
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		// No boxing for cgThis
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				boxingAnalyzer.rewriteAsGuarded(cgArgument, boxingAnalyzer.isSafe(cgOperationCallExp), "source for '" + asOperation + "'");
			}
			boxingAnalyzer.rewriteAsUnboxed(cgArgument);
		}
	}
}
