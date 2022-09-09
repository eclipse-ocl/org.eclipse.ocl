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
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.naming.FeatureNameManager;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class OCLinEcoreAnalyzer extends CodeGenAnalyzer
{
	public OCLinEcoreAnalyzer(@NonNull OCLinEcoreCodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	protected void generateConstraintBody(@NonNull CGConstraint cgConstraint, @NonNull Constraint asConstraint) {
		LanguageExpression specification = asConstraint.getOwnedSpecification();
		if (specification != null) {
			assert cgConstraint.basicGetNameResolution() == null;
			FeatureNameManager nameManager = getConstraintNameManager(cgConstraint, asConstraint);
			try {
				ExpressionInOCL oldQuery = environmentFactory.parseSpecification(specification);
				String constraintName = PivotUtil.getName(asConstraint);
				EObject eContainer = asConstraint.eContainer();
				if (eContainer instanceof NamedElement) {
					String containerName = ((NamedElement)eContainer).getName();
					if (containerName != null) {
						constraintName = containerName + "::" + constraintName;
					}
				}
				ExpressionInOCL asSynthesizedQuery = ((OCLinEcoreCodeGenerator)codeGenerator).rewriteQuery(oldQuery);
				OCLExpression asSynthesizedExpression = asSynthesizedQuery.getOwnedBody();
			//	OCLinEcoreLocalContext localContext = (OCLinEcoreLocalContext) globalContext.basicGetLocalContext(cgConstraint);
				Variable contextVariable = asSynthesizedQuery.getOwnedContext();
				if (contextVariable != null) {
					CGParameter cgParameter = nameManager.getSelfParameter(contextVariable);
					cgConstraint.getParameters().add(cgParameter);
				}
				for (@NonNull Variable parameterVariable : PivotUtil.getOwnedParameters(asSynthesizedQuery)) {
					CGParameter cgParameter = nameManager.getParameter(parameterVariable, parameterVariable.getName());
					cgConstraint.getParameters().add(cgParameter);
				}
				cgConstraint.setBody(createCGElement(CGValuedElement.class, asSynthesizedExpression));
			} catch (ParserException e) {
				cgConstraint.setBody(createCGConstantExp(getCGInvalid()));
				throw new WrappedException(e);
			}
		}
	}
}
