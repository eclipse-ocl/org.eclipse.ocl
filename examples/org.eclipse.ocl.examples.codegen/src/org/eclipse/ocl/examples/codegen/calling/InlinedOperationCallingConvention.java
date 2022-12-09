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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.codegen.utilities.RereferencingCopier;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  InlinedOperationCallingConvention defines the support for an inlined call of a Complete OCL-defined operation.
 *
 *  -- only used by QVTd
 */
public class InlinedOperationCallingConvention extends AbstractOperationCallingConvention
{
	private static final @NonNull InlinedOperationCallingConvention INSTANCE = new InlinedOperationCallingConvention();

	public static @NonNull InlinedOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	protected void appendForeignOperationName(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asReferredOperation = CGUtil.getAsOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".");
		js.appendValueName(cgOperation);
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return CGModelFactory.eINSTANCE.createCGInlinedOperation();
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation finalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			finalOperation = analyzer.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		assert (finalOperation != null);
		ExpressionInOCL originalExpression = PivotUtil.getBodyExpression(asOperation);
		//
		// The AS clone provides the kit of parts from which the ParameterVariables of the original body are replaced with
		// a let variable tree to which source and arguments are assigned. The resulting tree replaces the call exp.
		//
		PivotHelper asHelper = analyzer.getCodeGenerator().getASHelper();
		Map<@NonNull Variable, @NonNull LetVariable> parameter2let = new HashMap<>();
		if (asSource != null) {
			Variable parameterVariable = originalExpression.getOwnedContext();
		//	PivotUtilInternal.resetContainer(asSource);
			LetVariable letVariable = asHelper.createLetVariable(parameterVariable.getName(), parameterVariable.getType(), parameterVariable.isIsRequired(), EcoreUtil.copy(asSource));
			parameter2let.put(parameterVariable, letVariable);
		}
		List<@NonNull OCLExpression> asArguments = ClassUtil.nullFree(asOperationCallExp.getOwnedArguments());
		List<@NonNull Variable> asParameterVariables = ClassUtil.nullFree(originalExpression.getOwnedParameters());
		int argumentsSize = asArguments.size();
		assert asParameterVariables.size() == argumentsSize;
		for (int i = 0; i < argumentsSize; i++) {
			Variable parameterVariable = asParameterVariables.get(i);
			OCLExpression asExpression = EcoreUtil.copy(asArguments.get(i));
		//	PivotUtilInternal.resetContainer(asExpression);
			LetVariable letVariable = asHelper.createLetVariable(parameterVariable.getName(), parameterVariable.getType(), parameterVariable.isIsRequired(), asExpression);
			parameter2let.put(parameterVariable, letVariable);
		}
		ExpressionInOCL asClone = RereferencingCopier.copy(originalExpression, parameter2let);
		OCLExpression asExpression = PivotUtil.getOwnedBody(asClone);
		PivotUtilInternal.resetContainer(asExpression);
		for (int i = argumentsSize; --i >= 0; ) {
			Variable parameterVariable = asParameterVariables.get(i);
			LetVariable letVariable = parameter2let.get(parameterVariable);
			assert letVariable != null;
			PivotUtilInternal.resetContainer(letVariable);
			asExpression = asHelper.createLetExp(letVariable, asExpression);
		}
		if (asSource != null) {
			Variable parameterVariable = originalExpression.getOwnedContext();
			LetVariable letVariable = parameter2let.get(parameterVariable);
			assert letVariable != null;
			PivotUtilInternal.resetContainer(letVariable);
			asExpression = asHelper.createLetExp(letVariable, asExpression);
		}
		PivotUtil.replaceChild(asOperationCallExp, asExpression);

	/*	analyzer.getCG
		PivotUtilInternal.resetContainer(asExpression);
		List<@NonNull OCLExpression> asArguments = ClassUtil.nullFree(asOperationCallExp.getOwnedArguments());
		int argumentsSize = asArguments.size();
		if (argumentsSize > 0) {
			List<@NonNull Parameter> asParameters = ClassUtil.nullFree(asOperation.getOwnedParameters());
			List<@NonNull Variable> asParameterVariables = ClassUtil.nullFree(asClone.getOwnedParameters());
			List<@NonNull Variable> asVariables = new ArrayList<>(asParameterVariables);
			asParameterVariables.clear();				// Defeat child-stealing detector
			for (@NonNull Variable asVariable : asVariables) {
				Parameter asParameter = asVariable.getRepresentedParameter();
				if (asParameter != null) {
					int index = asParameters.indexOf(asParameter);
					if ((0 <= index) && (index < argumentsSize)) {
						asExpression = createLetExp(asVariable, asArguments.get(index), asExpression);
					}
				}
			}
		}
		Variable asVariable = asClone.getOwnedContext();
	//	asClone.setOwnedContext(null);				// Defeat child-stealing detector
		OCLExpression ownedSource = asOperationCallExp.getOwnedSource();
		PivotUtilInternal.resetContainer(ownedSource);	// Defeat child-stealing detector
		PivotUtilInternal.resetContainer(asVariable);	// Defeat child-stealing detector
		PivotUtilInternal.resetContainer(asExpression);	// Defeat child-stealing detector
		asExpression = createLetExp(asVariable, ownedSource, asExpression); */
	//	ASResource asResource = (ASResource) originalExpression.eResource();
	//	try {
		//	boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
		//	asResource.getContents().add(asExpression);					// Ensure that asExpression is not a Resource-less orphan; needed for FlowAnalysis
		//	asResource.setUpdating(wasUpdating);
			return analyzer.createCGElement(CGValuedElement.class, asExpression);
	//	}
	//	finally {
		//	boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
		//	asResource.getContents().remove(asExpression);
		//	asResource.setUpdating(wasUpdating);
	//	}
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		//	InlinedOperation never actually used so doesn't need parameters
	}

//	protected <T extends EObject> @NonNull T createCopy(@NonNull T anEObject) {
//		return EcoreUtil.copy(anEObject);
//	}

	/**
	 * Wrap asIn in a LetExp in which a clone of asInit is assigned to asVariable.
	 * @since 1.3
	 *
	protected @NonNull OCLExpression createLetExp(@Nullable Variable asVariable, @Nullable OCLExpression asInit, @NonNull OCLExpression asIn) {
		if ((asVariable == null) || (asInit == null)) {
			return asIn;
		}
		OCLExpression asInitClone = createCopy(asInit);
		asVariable.setOwnedInit(asInitClone);
		return PivotUtil.createLetExp(asVariable, asIn);
	} */

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new IllegalStateException("Inlined operation cannot be called");
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		throw new IllegalStateException("Inlined operation cannot be declared");
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}
}
