/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public final class OCLinEcoreAS2CGVisitor extends AS2CGVisitor
{
	protected final @NonNull OCLinEcoreGlobalContext globalContext;

	public OCLinEcoreAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer, @NonNull OCLinEcoreGlobalContext globalContext) {
		super(analyzer);
		this.globalContext = globalContext;
		EnvironmentFactoryInternal environmentFactory = analyzer.getCodeGenerator().getEnvironmentFactory();
		createSeverityOperations(environmentFactory);
	}

	private void createSeverityOperations(@NonNull EnvironmentFactoryInternal environmentFactory) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addParameter(@NonNull VariableDeclaration aParameter, @NonNull CGParameter cgParameter) {
		super.addParameter(aParameter, cgParameter);
		Parameter representedParameter = (aParameter instanceof Variable) ? ((Variable)aParameter).getRepresentedParameter() : null;
		if (representedParameter != null) {
			GenParameter genParameter = genModelHelper.getGenParameter(representedParameter);
			if (genParameter != null) {
				String name = ClassUtil.nonNullState(genParameter.getName());
				cgParameter.setValueName(name);
				// reserve name
			}
		}
	}

	@Override
	public @NonNull CGParameter getParameter(@NonNull Variable aParameter, @Nullable String name) {
		CGParameter cgParameter = super.getParameter(aParameter, name);
		if (PivotConstants.SELF_NAME.equals(aParameter.getName())) {
			cgParameter.setValueName("this");
		}
		return cgParameter;
	}

	public @NonNull CGParameter getParameter(@NonNull OCLinEcoreLocalContext localContext, @NonNull Variable aParameter, @Nullable String explicitName) {
		CGParameter cgParameter = getVariablesStack().getParameter(aParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			if (explicitName == null) {
				//				localContext.setNames(cgParameter, aParameter);
				String name = localContext.getNameManagerContext().getUniqueName(cgParameter, aParameter.getName());
				cgParameter.setName(name);
				cgParameter.setValueName(name);
			}
			else {
				cgParameter.setName(aParameter.getName());
				cgParameter.setValueName(explicitName);
			}
			setAst(cgParameter, aParameter);
			//			cgParameter.setTypeId(context.getTypeId(aParameter.getTypeId()));
			addParameter(aParameter, cgParameter);
			cgParameter.setRequired(aParameter.isIsRequired());
			if (aParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		if (PivotConstants.SELF_NAME.equals(aParameter.getName())) {
			cgParameter.setValueName("this");
		}
		return cgParameter;
	}

	@Override
	public @Nullable CGConstraint visitConstraint(@NonNull Constraint element) {
		CGConstraint cgConstraint = CGModelFactory.eINSTANCE.createCGConstraint();
		setAst(cgConstraint, element);
		LanguageExpression specification = element.getOwnedSpecification();
		if (specification != null) {
			try {
				ExpressionInOCL oldQuery = metamodelManager.parseSpecification(specification);
				String constraintName = PivotUtil.getName(element);
				EObject eContainer = element.eContainer();
				if (eContainer instanceof NamedElement) {
					String containerName = ((NamedElement)eContainer).getName();
					if (containerName != null) {
						constraintName = containerName + "::" + constraintName;
					}
				}
				ExpressionInOCL asSynthesizedQuery = ((OCLinEcoreCodeGenerator)codeGenerator).rewriteQuery(oldQuery, constraintName);
				OCLExpression asSynthesizedExpression = asSynthesizedQuery.getOwnedBody();
				OCLinEcoreLocalContext localContext = (OCLinEcoreLocalContext) globalContext.getLocalContext(cgConstraint);
				Variable contextVariable = asSynthesizedQuery.getOwnedContext();
				if (contextVariable != null) {
					CGParameter cgParameter = getParameter(localContext, contextVariable, null);
					cgConstraint.getParameters().add(cgParameter);
				}
				for (@SuppressWarnings("null")@NonNull Variable parameterVariable : asSynthesizedQuery.getOwnedParameters()) {
					String diagnosticsName = localContext.getDiagnosticsName();
					String contextName = localContext.getContextName();
					CGParameter cgParameter;
					if ((diagnosticsName != null) && diagnosticsName.equals(parameterVariable.getName())) {
						cgParameter = getParameter(parameterVariable, diagnosticsName);
					}
					else if ((contextName != null) && contextName.equals(parameterVariable.getName())) {
						cgParameter = getParameter(parameterVariable, contextName);
					}
					else {
						cgParameter = getParameter(parameterVariable, null);
					}
					cgConstraint.getParameters().add(cgParameter);
				}
				cgConstraint.setBody(doVisit(CGValuedElement.class, asSynthesizedExpression));
			} catch (ParserException e) {
				throw new WrappedException(e);
			}
		}
		return cgConstraint;
	}
}
