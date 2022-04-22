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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.library.NativeStaticOperation;
import org.eclipse.ocl.examples.codegen.library.NativeVisitorOperation;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;

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
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert (libraryOperation instanceof NativeStaticOperation) || (libraryOperation instanceof NativeVisitorOperation);
		return CGModelFactory.eINSTANCE.createCGNativeOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		throw new UnsupportedOperationException();		// FIXME construction is irregular
	/*	Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		CGNativeOperationCallExp cgNativeOperationCallExp = as2cgVisitor.getAnalyzer().createCGNativeOperationCallExp(null);
		cgNativeOperationCallExp.setThisIsSelf(true);
		init(as2cgVisitor, cgNativeOperationCallExp, asOperationCallExp, cgOperation, cgSource, isRequired);
		return cgNativeOperationCallExp; */
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGNativeOperationCallExp cgNativeOperationCallExp = (CGNativeOperationCallExp)cgOperationCallExp;
		//	Operation asOperation = cgOperationCallExp.getReferredOperation();
		CGValuedElement cgThis = cgNativeOperationCallExp.getCgThis();
		CGValuedElement cgThis2 = cgThis != null ? cg2JavaVisitor.getExpression(cgThis) :  null;
		//
		if ((cgThis2 != null) && !js.appendLocalStatements(cgThis2)) {
			return false;
		}
		Method javaMethod = cgNativeOperationCallExp.getMethod();
		List<CGValuedElement> cgArguments = cgNativeOperationCallExp.getCgArguments();
		//	List<Parameter> pParameters = asOperation.getOwnedParameters();
		java.lang.reflect.Parameter[] javaParameters = javaMethod.getParameters();
		if (!generateLocals(cg2JavaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		//
		js.appendDeclaration(cgNativeOperationCallExp);
		js.append(" = ");
		if (cgThis2 != null) {
			js.appendValueName(cgThis2);
		}
		else {
			js.appendClassReference(null, javaMethod.getDeclaringClass());
		}
		js.append(".");
		js.append(javaMethod.getName());
		//		js.appendClassCast(cgOperationCallExp);
		/*		if (thisIsSelf) {
			js.appendValueName(source);
		}
		else {
			if (localPrefix != null) {
				js.append(localPrefix);
				js.append(".");
			}
			js.append(JavaConstants.THIS_NAME);
		} */
	//	js.appendThis(ClassUtil.nonNullState(cgOperationCallExp.getReferredOperation().getOwningClass().getName()));
	//	js.append(".");
	//	js.append(cgOperationCallExp.getReferredOperation().getName());
		js.append("(");
	//	if ((source != null) && !thisIsSelf) {
	//		js.appendValueName(source);
	//	}
		int iMax = Math.min(javaParameters.length, cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if ((i > 0)) {// || !thisIsSelf) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			java.lang.reflect.Parameter javaParameter = javaParameters[i];
		//	CGTypeId cgTypeId = analyzer.getTypeId(pParameter.getTypeId());
		//	TypeDescriptor parameterTypeDescriptor = context.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
		//	js.appendReferenceTo(parameterTypeDescriptor, argument);
			js.appendValueName(argument);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean isUnboxed() {
		return true;
	}
}
