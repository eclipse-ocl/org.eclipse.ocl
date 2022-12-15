/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinjunit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * @since 1.3
 */
public final class JUnitAS2CGVisitor extends AS2CGVisitor
{
	public JUnitAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
	}

/*	@Override
	public @NonNull CGClass visitClass(@NonNull Class asClass) {		// Why overloaded ??
		CGClass cgClass = context.getCGClass(asClass);
		context.getClassNameManager(asClass);
	/*	for (@NonNull Constraint asConstraint : ClassUtil.nullFree(asClass.getOwnedInvariants())) {
			CGConstraint cgConstraint = doVisit(CGConstraint.class, asConstraint);
			cgClass.getInvariants().add(cgConstraint);
		}
		for (@NonNull Operation asOperation : ClassUtil.nullFree(asClass.getOwnedOperations())) {
			CGOperation cgOperation = doVisit(CGOperation.class, asOperation);
			cgClass.getOperations().add(cgOperation);
		}
		for (@NonNull Property asProperty : ClassUtil.nullFree(asClass.getOwnedProperties())) {
			CGProperty cgProperty = doVisit(CGProperty.class, asProperty);
			cgClass.getProperties().add(cgProperty);
		} * /
		return cgClass;
	} */

	@Override
	public @NonNull CGValuedElement visitExpressionInOCL(@NonNull ExpressionInOCL element) {
		ExecutableNameManager nameManager = context.useExecutableNameManager(element);
		Variable contextVariable = element.getOwnedContext();
		if (contextVariable != null) {
			CGVariable cgContext = nameManager.getCGParameter(contextVariable, (String)null);
			cgContext.setTypeId(context.getCGTypeId(TypeId.OCL_VOID));			// FIXME Java-specific
			cgContext.setNonInvalid();
//			cgContext.setNonNull();
		}
		for (@SuppressWarnings("null")@NonNull Variable parameterVariable : element.getOwnedParameters()) {
			@SuppressWarnings("unused") CGVariable cgVariable = nameManager.basicGetCGVariable(parameterVariable);
			if (cgVariable != null) {
				assert cgVariable.getAst() == parameterVariable;
			}
			else {
				@SuppressWarnings("unused") CGVariable cgParameter = nameManager.getCGParameter(parameterVariable, (String)null);
			}
		}
		CGValuedElement cgBody = context.createCGElement(CGValuedElement.class, element.getOwnedBody());
//		cgOperation.getDependsOn().add(cgBody);
		return cgBody;
	}
}
