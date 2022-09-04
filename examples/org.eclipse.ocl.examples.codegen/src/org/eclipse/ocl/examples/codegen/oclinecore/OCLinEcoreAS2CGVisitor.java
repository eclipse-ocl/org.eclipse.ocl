/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.FeatureNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public final class OCLinEcoreAS2CGVisitor extends AS2CGVisitor
{
	public OCLinEcoreAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
	}

//	private void createSeverityOperations(@NonNull EnvironmentFactoryInternal environmentFactory) {
		// TODO Auto-generated method stub
//	}

/*	@Override
	protected void addParameter(@NonNull VariableDeclaration aParameter, @NonNull CGParameter cgParameter) {
		super.addParameter(aParameter, cgParameter);
		Parameter representedParameter = (aParameter instanceof Variable) ? ((Variable)aParameter).getRepresentedParameter() : null;
		if (representedParameter != null) {
			GenParameter genParameter = genModelHelper.getGenParameter(representedParameter);
		//	if (genParameter != null) {
		//		String name = ClassUtil.nonNullState(genParameter.getName());
		//		cgParameter.setValueName(name);
			//	nameManager.getGlobalNameManager().queueValueName(executorParameter, null, executorName);
				// reserve name
		//	}
		}
	} */

/*	@Override
	public @NonNull CGParameter getParameter(@NonNull Variable aParameter, @Nullable String name) {
		CGParameter cgParameter = super.getParameter(aParameter, name);
		assert !PivotConstants.SELF_NAME.equals(aParameter.getName());
		//	globalContext.getThisName().addSecondaryElement(cgParameter);;
		//	cgParameter.setValueName(JavaConstants.THIS_NAME);
	//	}
		return cgParameter;
	} */

/*	@Override
	public @NonNull CGClass visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		List<Constraint> asConstraints = asClass.getOwnedConstraints();
		if (!asConstraints.isEmpty()) {
			LocalContext classContext = pushClassContext(asClass);
		//	classContext.getNameManager().
		//	CGClass cgClass = (CGClass)classContext.getScope();
			popClassContext();
		}
		return super.visitClass(asClass);
	} */

	@Override
	public @Nullable CGConstraint visitConstraint(@NonNull Constraint asConstraint) {
		CGConstraint cgConstraint = CGModelFactory.eINSTANCE.createCGConstraint();
		LanguageExpression specification = asConstraint.getOwnedSpecification();
		if (specification != null) {
			assert cgConstraint.basicGetNameResolution() == null;
			cgConstraint.setAst(asConstraint);
//			getNameManager().declarePreferredName(cgConstraint);
			FeatureNameManager nameManager = context.pushConstraintNameManager(cgConstraint);
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
				cgConstraint.setBody(context.createCGElement(CGValuedElement.class, asSynthesizedExpression));
			} catch (ParserException e) {
				throw new WrappedException(e);
			} finally {
				context.popNestedNameManager();
			}
		}
		return cgConstraint;
	}
}
