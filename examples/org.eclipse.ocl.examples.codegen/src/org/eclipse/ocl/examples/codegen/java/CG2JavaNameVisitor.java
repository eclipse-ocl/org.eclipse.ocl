/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.generator.IterationHelper;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A CG2JavaNameVisitor prepares for Java code generation by priming the allocation of unqiue names by the
 * name resolver/assigner..
 */
public class CG2JavaNameVisitor extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull JavaCodeGenerator>
{
	protected final @NonNull GlobalNameManager globalNameManager;
	private @Nullable NestedNameManager currentNameManager;

	public CG2JavaNameVisitor(@NonNull JavaCodeGenerator codeGenerator) {
		super(codeGenerator);
		this.globalNameManager = codeGenerator.getGlobalNameManager();
	}

	protected @NonNull NestedNameManager getNameManager() {
		return ClassUtil.nonNullState(currentNameManager);
	}

	protected void popNameManager(@Nullable NestedNameManager savedNameManager) {
		if (savedNameManager == null) {
			NestedNameManager currentNameManager2 = currentNameManager;
			assert currentNameManager2 != null;
		}
		currentNameManager = savedNameManager;
	}

	protected @Nullable NestedNameManager pushNameManager(@NonNull CGNamedElement cgNamedElement) {
		NestedNameManager savedNameManager = currentNameManager;
		currentNameManager = globalNameManager.findNameManager(cgNamedElement);
		return savedNameManager;
	}

	@Override
	public @Nullable Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @Nullable Object visitCGCatchExp(@NonNull CGCatchExp cgCatchExp) {
		NestedNameManager nameManager = getNameManager();
		nameManager.addNameVariant(cgCatchExp, context.getTHROWN_NameVariant());
		return super.visitCGCatchExp(cgCatchExp);
	}

	@Override
	public @Nullable Object visitCGClass(@NonNull CGClass cgClass) {
		if (cgClass.getAst() != null) {
			NestedNameManager savedNameManager = pushNameManager(cgClass);
			try {
				return super.visitCGClass(cgClass);
			}
			finally {
				popNameManager(savedNameManager);
			}
		}
		else {
			return super.visitCGClass(cgClass);
		}
	}

	@Override
	public @Nullable Object visitCGConstrainedProperty(@NonNull CGConstrainedProperty cgProperty) {
		NestedNameManager savedNameManager = pushNameManager(cgProperty);
		try {
			return super.visitCGConstrainedProperty(cgProperty);
		}
		finally {
			popNameManager(savedNameManager);
		}
	}

	@Override
	public @Nullable Object visitCGConstraint(@NonNull CGConstraint cgConstraint) {
		NestedNameManager savedNameManager = pushNameManager(cgConstraint);
		try {
			return super.visitCGConstraint(cgConstraint);
		}
		finally {
			popNameManager(savedNameManager);
		}
	}

	@Override
	public @Nullable Object visitCGElement(@NonNull CGElement cgElement) {
		List<?> owns = cgElement instanceof CGValuedElement ? ((CGValuedElement)cgElement).getOwns() : null;
		for (CGElement cgChild : cgElement.getChildren()) {
			if ((owns == null) || !owns.contains(cgChild)) {
				cgChild.accept(this);
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGForeignProperty(@NonNull CGForeignProperty cgForeignProperty) {
		NestedNameManager savedNameManager = pushNameManager(cgForeignProperty);
		try {
			return super.visitCGProperty(cgForeignProperty);
		}
		finally {
			popNameManager(savedNameManager);
		}
	}

	@Override
	public @Nullable Object visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		Iteration asIteration = ClassUtil.nonNullState(cgIterationCallExp.getReferredIteration());
		IterationHelper iterationHelper = context.getIterationHelper(asIteration);
		CGValuedElement cgSource = cgIterationCallExp.getSource();
		NestedNameManager outerNameManager = getNameManager();
		if (cgSource != null) {
			if (!cgSource.isGlobal()) {
				globalNameManager.declareScope(cgSource, outerNameManager);		// source must be declared in outer namespace
			}
			cgSource.accept(this);
		}
		globalNameManager.declareScope(cgIterationCallExp, outerNameManager);	// result must be declared in outer namespace
		outerNameManager.addNameVariant(cgIterationCallExp, context.getBODY_NameVariant());
		outerNameManager.addNameVariant(cgIterationCallExp, context.getIMPL_NameVariant());
		outerNameManager.addNameVariant(cgIterationCallExp, context.getMGR_NameVariant());
		outerNameManager.addNameVariant(cgIterationCallExp, context.getTYPE_NameVariant());
		NestedNameManager innerNameManager;
		NestedNameManager savedNameManager = null;
		if (iterationHelper == null) {					// No helper nests iterators/accumulators in a nested function.
			savedNameManager = pushNameManager(cgIterationCallExp);
			innerNameManager = getNameManager();
		}
		else {
			innerNameManager = outerNameManager;
		}
		for (CGIterator cgIterator : CGUtil.getIterators(cgIterationCallExp)) {
			innerNameManager.addNameVariant(cgIterator, context.getITER_NameVariant());
			cgIterator.accept(this);
		}
		if (cgIterationCallExp instanceof CGBuiltInIterationCallExp) {
			CGIterator cgAccumulator = ((CGBuiltInIterationCallExp)cgIterationCallExp).getAccumulator();
			if (cgAccumulator != null) {
				globalNameManager.declareScope(cgAccumulator, innerNameManager);
				cgAccumulator.accept(this);
			}
		}
		if (iterationHelper != null) {					// No helper only has a nested scope for the body.
			savedNameManager = pushNameManager(cgIterationCallExp);
			innerNameManager = getNameManager();
		}
		assert savedNameManager != null;
		try {
			CGValuedElement cgBody = cgIterationCallExp.getBody();
			if (cgBody != null) {
				cgBody.accept(this);
			}
			return null;
		}
		finally {
			popNameManager(savedNameManager);
		}
	}

	@Override
	public @Nullable Object visitCGNativeProperty(@NonNull CGNativeProperty cgProperty) {
		NestedNameManager savedNameManager = pushNameManager(cgProperty);
		try {
			return super.visitCGNativeProperty(cgProperty);
		}
		finally {
			popNameManager(savedNameManager);
		}
	}

	@Override
	public @Nullable Object visitCGOperation(@NonNull CGOperation cgOperation) {
		NestedNameManager savedNameManager = pushNameManager(cgOperation);
		try {
			return super.visitCGOperation(cgOperation);
		}
		finally {
			popNameManager(savedNameManager);
		}
	}
}
