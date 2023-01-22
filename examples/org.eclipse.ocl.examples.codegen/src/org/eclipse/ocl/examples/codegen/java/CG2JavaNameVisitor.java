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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NestedNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;

/**
 * A CG2JavaNameVisitor prepares for Java code generation by priming the allocation of unique names by the
 * name resolver/assigner..
 */
public class CG2JavaNameVisitor extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull JavaCodeGenerator>
{
	protected final @NonNull GlobalNameManager globalNameManager;

	public CG2JavaNameVisitor(@NonNull JavaCodeGenerator codeGenerator) {
		super(codeGenerator);
		this.globalNameManager = codeGenerator.getGlobalNameManager();
	}

	@Override
	public @Nullable Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @Nullable Object visitCGCatchExp(@NonNull CGCatchExp cgCatchExp) {
		NestedNameManager nameManager = globalNameManager.useSelfExecutableNameManager(cgCatchExp);
		nameManager.addNameVariant(cgCatchExp, context.getTHROWN_NameVariant());
		return super.visitCGCatchExp(cgCatchExp);
	}

	@Override
	public @Nullable Object visitCGElement(@NonNull CGElement cgElement) {
//		List<?> owns = cgElement instanceof CGValuedElement ? ((CGValuedElement)cgElement).getOwns() : null;
		for (CGElement cgChild : cgElement.getChildren()) {
//			if ((owns == null) || !owns.contains(cgChild)) {
				cgChild.accept(this);
			}
//		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		CGValuedElement cgSource = cgIterationCallExp.getSource();
		NestedNameManager outerNameManager = globalNameManager.useSelfExecutableNameManager(cgIterationCallExp);
		if (cgSource != null) {
			cgSource.accept(this);
		}
		outerNameManager.addNameVariant(cgIterationCallExp, context.getBODY_NameVariant());
		outerNameManager.addNameVariant(cgIterationCallExp, context.getIMPL_NameVariant());
		outerNameManager.addNameVariant(cgIterationCallExp, context.getMGR_NameVariant());
		outerNameManager.addNameVariant(cgIterationCallExp, context.getTYPE_NameVariant());
		for (CGIterator cgIterator : CGUtil.getIterators(cgIterationCallExp)) {
			NestedNameManager innerNameManager = globalNameManager.useSelfExecutableNameManager(cgIterator);
			innerNameManager.addNameVariant(cgIterator, context.getITER_NameVariant());
			cgIterator.accept(this);
		}
		if (cgIterationCallExp instanceof CGBuiltInIterationCallExp) {
			CGIterator cgAccumulator = ((CGBuiltInIterationCallExp)cgIterationCallExp).getAccumulator();
			if (cgAccumulator != null) {
				cgAccumulator.accept(this);
			}
		}
		CGValuedElement cgBody = cgIterationCallExp.getBody();
		if (cgBody != null) {
			cgBody.accept(this);
		}
		return null;
	}
}
