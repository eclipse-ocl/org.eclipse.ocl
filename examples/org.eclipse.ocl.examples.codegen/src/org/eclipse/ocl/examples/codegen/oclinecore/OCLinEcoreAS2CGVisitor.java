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

import java.util.List;

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
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public final class OCLinEcoreAS2CGVisitor extends AS2CGVisitor
{
	protected final @NonNull OCLinEcoreGlobalContext globalContext;
	protected final @NonNull PivotHelper asHelper;

	public OCLinEcoreAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer, @NonNull OCLinEcoreGlobalContext globalContext) {
		super(analyzer);
		this.globalContext = globalContext;
		EnvironmentFactoryInternal environmentFactory = analyzer.getCodeGenerator().getEnvironmentFactory();
		asHelper = new PivotHelper(environmentFactory);
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

	@Override
	public @Nullable CGConstraint visitConstraint(@NonNull Constraint element) {
		CGConstraint cgConstraint = CGModelFactory.eINSTANCE.createCGConstraint();
		setAst(cgConstraint, element);
		LanguageExpression specification = element.getOwnedSpecification();
		if (specification != null) {
			String oclExpression = specification.getBody();
			if (oclExpression != null) {
				try {
					EObject contextElement = ClassUtil.nonNullState(specification.eContainer());
					//				if ((specification instanceof ExpressionInOCL) && ((ExpressionInOCL)specification).getOwnedBody() != null) {
					//					return (ExpressionInOCL)specification;
					//				}
					ParserContext parserContext = metamodelManager.createParserContext(specification);
					if (parserContext == null) {
						throw new ParserException(PivotMessagesInternal.UnknownContextType_ERROR_, NameUtil.qualifiedNameFor(contextElement), PivotUtilInternal.getSpecificationRole(specification));
					}
					parserContext.setRootElement(specification);
					if (specification instanceof ExpressionInOCL) {
						Variable diagnosticsVariable = asHelper.createParameterVariable("diagnostics", metamodelManager.getStandardLibrary().getOclAnyType(), false);
						((ExpressionInOCL)specification).getOwnedParameters().add(diagnosticsVariable);
						Variable contextVariable = asHelper.createParameterVariable("context", metamodelManager.getStandardLibrary().getOclAnyType(), false);
						((ExpressionInOCL)specification).getOwnedParameters().add(contextVariable);
					}
					String constraintName = element.getName();
					if (constraintName.startsWith("validate")) {
						constraintName = constraintName.substring(8);		// FIXME Use genModel/avoid wrong AS
					}
					EObject eContainer = element.eContainer();
					if (eContainer instanceof NamedElement) {
						String containerName = ((NamedElement)eContainer).getName();
						if (containerName != null) {
							constraintName = containerName + "::" + constraintName;
						}
					}
					@NonNull String statusExpression = oclExpression;
					String messageExpression = null;
					String severityExpression = null;
					ExpressionInOCL originalQuery = parserContext.parse(contextElement, oclExpression);
					OCLExpression ownedBody = originalQuery.getOwnedBody();
					if (ownedBody instanceof PropertyCallExp) {
						PropertyCallExp asPropertyCallExp = (PropertyCallExp)ownedBody;
						OCLExpression asSource = asPropertyCallExp.getOwnedSource();
						Property asReferredProperty = asPropertyCallExp.getReferredProperty();
						if ((asReferredProperty != null) && PivotConstants.STATUS_PART_NAME.equals(asReferredProperty.getName())) {
							if (asSource instanceof TupleLiteralExp) {
								TupleLiteralExp asTupleLiteralExp = (TupleLiteralExp)asSource;
								List<TupleLiteralPart> asTupleParts = asTupleLiteralExp.getOwnedParts();
								TupleLiteralPart asStatusPart = NameUtil.getNameable(asTupleParts, PivotConstants.STATUS_PART_NAME);
								if (asStatusPart != null) {
									OCLExpression asStatusInit = asStatusPart.getOwnedInit();
									if (asStatusInit != null) {
										statusExpression = PrettyPrinter.print(asStatusInit, PrettyPrinter.createOptions(PivotUtil.getContainingNamespace(element)));
									}
									TupleLiteralPart asMessagePart = NameUtil.getNameable(asTupleParts, PivotConstants.MESSAGE_PART_NAME);
									if (asMessagePart != null) {
										OCLExpression asMessageInit = asMessagePart.getOwnedInit();
										if (asMessageInit != null) {
											messageExpression = PrettyPrinter.print(asMessageInit);
										}
									}
									TupleLiteralPart asSeverityPart = NameUtil.getNameable(asTupleParts, PivotConstants.SEVERITY_PART_NAME);
									if (asSeverityPart != null) {
										OCLExpression asSeverityInit = asSeverityPart.getOwnedInit();
										if (asSeverityInit != null) {
											severityExpression = PrettyPrinter.print(asSeverityInit);
										}
									}
								}
							}
							else if (asSource.getType() instanceof TupleType) {
								statusExpression = PrettyPrinter.print(asSource);
							}
						}
					}
					StringBuilder s = new StringBuilder();
					if (severityExpression != null) {
						s.append("let severity : Integer = " + severityExpression + " in\n");
					}
					else {
						s.append("let severity : Integer = '" + constraintName + "'.getSeverity() in\n" +
								"if severity <= 0 then true\n" +
								"else ");

					}
					s.append("let status : OclAny = " + statusExpression + " in\n");
					if (messageExpression != null) {
						s.append("let message : String = if status <> true then " + messageExpression + " else null endif in\n");
					}
					s.append("'" + constraintName + "'.logDiagnostic(self, null, diagnostics, context, ");
					s.append(messageExpression != null ? "message" : "null");
					s.append(", severity, status, 0)\n");
					if (severityExpression == null) {
						s.append("endif\n");
					}
					String cgExpression = s.toString();
					ExpressionInOCL query = parserContext.parse(contextElement, cgExpression);
					OCLinEcoreLocalContext localContext = (OCLinEcoreLocalContext) globalContext.getLocalContext(cgConstraint);
					Variable contextVariable = query.getOwnedContext();
					if (contextVariable != null) {
						CGParameter cgParameter = getParameter(contextVariable, null);
						cgConstraint.getParameters().add(cgParameter);
					}
					for (@SuppressWarnings("null")@NonNull Variable parameterVariable : query.getOwnedParameters()) {
						String diagnosticsName = localContext != null ? localContext.getDiagnosticsName() : null;
						String contextName = localContext != null ? localContext.getContextName() : null;
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
					cgConstraint.setBody(doVisit(CGValuedElement.class, query.getOwnedBody()));
				} catch (ParserException e) {
					throw new WrappedException(e);
				}
			}
		}
		return cgConstraint;
	}
}
