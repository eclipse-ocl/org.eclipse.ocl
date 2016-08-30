/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.completeocl;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.pivot.Model;

/**
 * @since 1.3
 */
public final class CompleteOCLAS2CGVisitor extends AS2CGVisitor
{
	public CompleteOCLAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
	}

	/*	@Override
	public @NonNull CGValuedElement visitExpressionInOCL(@NonNull ExpressionInOCL element) {
		Variable contextVariable = element.getOwnedContext();
		if (contextVariable != null) {
			CGVariable cgContext = getParameter(contextVariable, null);
			cgContext.setTypeId(context.getTypeId(TypeId.OCL_VOID));			// FIXME Java-specific
			cgContext.setNonInvalid();
//			cgContext.setNonNull();
		}
		for (@SuppressWarnings("null")@NonNull Variable parameterVariable : element.getOwnedParameters()) {
			@SuppressWarnings("unused") CGVariable cgParameter = getParameter(parameterVariable, null);
		}
		CGValuedElement cgBody = doVisit(CGValuedElement.class, element.getOwnedBody());
//		cgOperation.getDependsOn().add(cgBody);
		return cgBody;
	} */

	@Override
	public @Nullable CGNamedElement visitModel(@NonNull Model asModel) {
		CGPackage cgRootPackage = CGModelFactory.eINSTANCE.createCGPackage();
		setAst(cgRootPackage, asModel);
		for (org.eclipse.ocl.pivot.Package asPackage : asModel.getOwnedPackages()) {
			CGPackage cgNestedPackage = doVisit(CGPackage.class, asPackage);
			cgRootPackage.getPackages().add(cgNestedPackage);
		}
		return cgRootPackage;
	}
}
