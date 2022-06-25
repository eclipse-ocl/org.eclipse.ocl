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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInlinedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  InlinedOperationCallingConvention defines the support for an inlined call of a Complete OCL-defined operation.
 *
 *  -- only used by QVTd
 */
public class InlinedOperationCallingConvention extends ConstrainedOperationCallingConvention	// CF ConstrainedOperationCallingConvention
{
	public static final @NonNull InlinedOperationCallingConvention INSTANCE = new InlinedOperationCallingConvention();

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @Nullable Type asSourceType, @NonNull Operation asOperation) {
		CGInlinedOperation cgOperation = CGModelFactory.eINSTANCE.createCGInlinedOperation();
		analyzer.installOperation(asOperation, cgOperation, this);
		return cgOperation;
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation finalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			finalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		assert (finalOperation != null);
		LanguageExpression bodyExpression = asOperation.getBodyExpression();
		assert (bodyExpression != null);
		//
		// The AS clone is presumably to ensaure a unique AS2CG mapping. Now the CGOperationCallExp etc have
		// CGOperation/CGParameter the AS2CG mapping could well be a redundant concern.
		//
		ExpressionInOCL asClone = createCopy((ExpressionInOCL)bodyExpression);
		OCLExpression asExpression = ClassUtil.nonNullState(asClone.getOwnedBody());
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
		asExpression = createLetExp(asVariable, ownedSource, asExpression);
		ASResource asResource = (ASResource) bodyExpression.eResource();
		try {
			boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
			asResource.getContents().add(asExpression);					// Ensure that asExpression is not a Resource-less orphan; needed for FlowAnalysis
			asResource.setUpdating(wasUpdating);
			return as2cgVisitor.doVisit(CGValuedElement.class, asExpression);
		}
		finally {
			boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
			asResource.getContents().remove(asExpression);
			asResource.setUpdating(wasUpdating);
		}
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL bodyExpression) {
		//	InlinedOperation never actually used so doesn't need parameters
	}

	protected <T extends EObject> @NonNull T createCopy(@NonNull T anEObject) {
		return EcoreUtil.copy(anEObject);
	}

	/**
	 * Wrap asIn in a LetExp in which a clone of asInit is assigned to asVariable.
	 * @since 1.3
	 */
	protected @NonNull OCLExpression createLetExp(@Nullable Variable asVariable, @Nullable OCLExpression asInit, @NonNull OCLExpression asIn) {
		if ((asVariable == null) || (asInit == null)) {
			return asIn;
		}
		OCLExpression asInitClone = createCopy(asInit);
		asVariable.setOwnedInit(asInitClone);
		return PivotUtil.createLetExp(asVariable, asIn);
	}

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
