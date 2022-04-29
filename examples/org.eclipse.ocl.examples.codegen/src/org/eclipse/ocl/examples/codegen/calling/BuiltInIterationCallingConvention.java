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
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.library.ForeignOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;

/**
 *  BuiltInIterationCallingConvention defines the support for the call of an iteration optionally realized by inline code.
 */
public class BuiltInIterationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull BuiltInIterationCallingConvention INSTANCE = new BuiltInIterationCallingConvention();

/*	public boolean canHandle(@NonNull LibraryOperation libraryOperation) {
		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			return true;
		}
		else if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			return true;
		}
		else if (libraryOperation instanceof OclAnyEqualOperation) {
			return true;
		}
		return false;
	} */

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @Nullable Type asSourceType, @NonNull Operation asOperation) {
		assert asOperation instanceof Iteration;
//		return fallbackCreateCGOperationWithoutBody(as2cgVisitor, asOperation);
 		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert !(libraryOperation instanceof EObjectOperation);
		assert !(libraryOperation instanceof ForeignOperation);
		assert !(libraryOperation instanceof ConstrainedOperation);
		CGLibraryOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		analyzer.installOperation(asOperation, cgOperation, this);
		return cgOperation;
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
/*		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			CGIsInvalidExp cgIsInvalidExp = CGModelFactory.eINSTANCE.createCGIsInvalidExp();
			cgIsInvalidExp.setSource(cgSource);
			as2cgVisitor.initAst(cgIsInvalidExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsInvalidExp);
			cgIsInvalidExp.setInvalidating(false);
			cgIsInvalidExp.setValidating(true);
			return cgIsInvalidExp;
		}
		if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			CGIsUndefinedExp cgIsUndefinedExp = CGModelFactory.eINSTANCE.createCGIsUndefinedExp();
			cgIsUndefinedExp.setSource(cgSource);
			as2cgVisitor.initAst(cgIsUndefinedExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsUndefinedExp);
			cgIsUndefinedExp.setInvalidating(false);
			cgIsUndefinedExp.setValidating(true);
			return cgIsUndefinedExp;
		}
		if (libraryOperation instanceof OclAnyEqualOperation) {
			OCLExpression pArgument = PivotUtil.getOwnedArgument(asOperationCallExp, 0);
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			CGIsEqualExp cgIsEqualExp = CGModelFactory.eINSTANCE.createCGIsEqualExp();
			cgIsEqualExp.setNotEquals(libraryOperation instanceof OclAnyNotEqualOperation);
			cgIsEqualExp.setSource(cgSource);
			cgIsEqualExp.setArgument(cgArgument);
			as2cgVisitor.initAst(cgIsEqualExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsEqualExp);
			cgIsEqualExp.setInvalidating(false);
			cgIsEqualExp.setValidating(true);
			return cgIsEqualExp;
		}
		throw new IllegalStateException("Unsupported built-in " + libraryOperation); */
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();		// Built-in operations are declared inline
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}
}
