/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.DependencyVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace;
import org.eclipse.ocl.pivot.ids.TypeId;

public class JavaDependencyVisitor extends DependencyVisitor
{
	protected final @NonNull JavaGlobalContext<@NonNull ?> globalContext;
	protected final JavaLocalContext<@NonNull ?> localContext;

	public JavaDependencyVisitor(@NonNull CodeGenAnalyzer analyzer, @NonNull JavaGlobalContext<@NonNull ?> globalContext, @NonNull GlobalPlace globalPlace) {
        super(analyzer, globalPlace);
        this.globalContext = globalContext;
        this.localContext = null;
	}

	@Override
	public @Nullable Object visitCGBoxExp(@NonNull CGBoxExp cgBoxExp) {
		TypeId typeId = cgBoxExp.getSource().getASTypeId();
		if (typeId != null) {
			addDependency(cgBoxExp, context.getElementId(typeId));
			typeId.accept(id2DependencyVisitor);						// FIXME this should be automatic (needed for OclAny testNotEqual)
		}
		return super.visitCGBoxExp(cgBoxExp);
	}

	@Override
	public @Nullable Object visitCGEcoreExp(@NonNull CGEcoreExp cgEcoreExp) {
		TypeId typeId = cgEcoreExp.getSource().getASTypeId();
		if (typeId != null) {
			addDependency(cgEcoreExp, context.getElementId(typeId));
			typeId.accept(id2DependencyVisitor);						// FIXME this should be automatic (needed for OclAny testNotEqual)
		}
		return super.visitCGEcoreExp(cgEcoreExp);
	}

	@Override
	public @Nullable Object visitCGExecutorType(@NonNull CGExecutorType cgTypeWithReflection) {
//		addDependency(cgTypeWithReflection, localContext.getIdResolverVariable());
//		cgTypeWithReflection.setTypeId(context.getTypeId(JavaConstants.DOMAIN_TYPE_TYPE_ID));		// FIXME
		return super.visitCGExecutorType(cgTypeWithReflection);
	}

	@Override
	public @Nullable Object visitCGTemplateParameterExp(@NonNull CGTemplateParameterExp cgTemplateParameterExp) {
/*		CGValuedElement variableValue = cgTemplateParameterExp.getNamedValue();
		variableValue.accept(this);
		addDependency(cgTemplateParameterExp, variableValue);
//		addDependency(cgVariable, cgVariable.getTypeId());
//		ElementId elementId = ((CGType)cgTypeVariable.getVariableValue()).getElementId();
//		CGElementId cgElementId = context.getElementId(elementId);
//		addDependency(cgTypeVariable, cgElementId);
//		addDependency(cgTypeExp, cgTypeExp.getReferredType()); */
		return super.visitCGTemplateParameterExp(cgTemplateParameterExp);
	}

	@Override
	public @Nullable Object visitCGTypeExp(@NonNull CGTypeExp cgTypeExp) {
		CGValuedElement variableValue = cgTypeExp.getNamedValue();
		variableValue.accept(this);
		addDependency(cgTypeExp, variableValue);
//		addDependency(cgVariable, cgVariable.getTypeId());
//		ElementId elementId = ((CGType)cgTypeVariable.getVariableValue()).getElementId();
//		CGElementId cgElementId = context.getElementId(elementId);
//		addDependency(cgTypeVariable, cgElementId);
//		addDependency(cgTypeExp, cgTypeExp.getReferredType());
		return super.visitCGTypeExp(cgTypeExp);
	}
}
