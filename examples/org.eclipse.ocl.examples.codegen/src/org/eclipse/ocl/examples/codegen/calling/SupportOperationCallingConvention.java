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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.library.UnboxedExplicitNavigationProperty;

/**
 *  The SupportOperationCallingConvention defines the support for the call of a native (Java) operation
 *  defined by a Java method and using boxed source and arguments.
 *  </br>
 *  e.g. as aModelManager.basicGetForeignPropertyValue(arguments)
 */
public class SupportOperationCallingConvention extends NativeOperationCallingConvention
{
	private static final @NonNull SupportOperationCallingConvention INSTANCE = new SupportOperationCallingConvention();

//	public static @NonNull SupportOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//		INSTANCE.logInstance(asOperation, maybeVirtual);
//	}

//	public static @NonNull SupportOperationCallingConvention getInstance(@NonNull Property asProperty) {
//		INSTANCE.logInstance(asProperty);
//	}

	public static @NonNull SupportOperationCallingConvention getInstance(@NonNull Method jMethod) {
		INSTANCE.logInstance(jMethod);
		return INSTANCE;
	}

	/**
	 * Java methods used by the code generator support that must use the SupportOperationCallingConvention.getInstance()
	 * to avoid inappropriate boxing / type conversion.
	 */
	private static final @NonNull Set<@NonNull Method> supportMethods = new HashSet<>();

	public static @NonNull Method addSupportMethod(@NonNull Method jMethod) {
		supportMethods.add(jMethod);
		return jMethod;
	}

	static {
		addSupportMethod(UnboxedExplicitNavigationProperty.CREATE_METHOD);
	}

	public boolean canHandle(@NonNull Method jMethod) {
		return supportMethods.contains(jMethod);
	}

	@Override
	public void newCreateCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL expressionInOCL) {
//		throw new UnsupportedOperationException();
		assert expressionInOCL == null;
	/*	CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		Operation asOperation = CGUtil.getAST(cgOperation);
		if (!asOperation.isIsStatic()) {						// XXX Static is a derived CC
			CGParameter cgParameter = operationNameManager.getSelfParameter();
			cgParameters.add(cgParameter);
			assertCGParameterStyles(CG_PARAMETER_STYLES_SELF_PARAMETERS, operationNameManager, expressionInOCL);
		}
		else {
			assertCGParameterStyles(CG_PARAMETER_STYLES_PARAMETERS, operationNameManager, expressionInOCL);
		}
		List<@NonNull Parameter> asParameters = PivotUtilInternal.getOwnedParametersList(asOperation);
		createCGParameters4asParameters(operationNameManager, cgParameters, asParameters); */
		initCGParameters(operationNameManager, null);
	}

	@Override
	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager, @Nullable TypedElement zzasOrigin) {
		Operation asOperation = (Operation)operationNameManager.getASScope();
		assert asOperation.getBodyExpression() == null;
		if (!asOperation.isIsStatic()) {
			return CG_PARAMETER_STYLES_SELF_PARAMETERS;
		}
		else {
			return CG_PARAMETER_STYLES_PARAMETERS;
		}
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		assert cgOperationCallExp instanceof CGNativeOperationCallExp;
		// No boxing for cgThis
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
		//	if (i == 0) {
		//		boxingAnalyzer.rewriteAsGuarded(cgArgument, boxingAnalyzer.isSafe(cgNativeOperationCallExp), "source for '" + asOperation + "'");
		//	}
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
		}
	}
}
