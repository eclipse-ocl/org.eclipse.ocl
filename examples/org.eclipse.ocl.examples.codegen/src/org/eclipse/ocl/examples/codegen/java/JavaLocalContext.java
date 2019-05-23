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
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * A JavaLocalContext maintains the Java-specific context for generation of coide from a CGOperation.
 */
public class JavaLocalContext<@NonNull CG extends JavaCodeGenerator> extends AbstractJavaContext<CG> implements LocalContext
{
	protected final @NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext;
	protected final @Nullable JavaLocalContext<@NonNull ? extends CG> parentContext;
	protected @NonNull CGElement cgScope;
	protected NameManager.@NonNull Context nameManagerContext;

	public JavaLocalContext(@NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext, @NonNull CGElement cgScope) {
		super(globalContext.getCodeGenerator());
		this.globalContext = globalContext;
		this.parentContext = null;
		this.cgScope = cgScope;
		this.nameManagerContext = codeGenerator.getNameManager().createNestedContext();
	}

	public @Nullable CGParameter createExecutorParameter() {
		CGParameter evaluatorParameter = CGModelFactory.eINSTANCE.createCGParameter();
		setNames2(evaluatorParameter, JavaConstants.EXECUTOR_NAME, JavaConstants.EXECUTOR_TYPE_ID);
		evaluatorParameter.setRequired(true);
		return evaluatorParameter;
	}

	public @Nullable CGValuedElement createExecutorVariable(@Nullable String contextName) {
		return null;
	}

	public @NonNull CGValuedElement createIdResolverVariable(@Nullable String contextName) {
		//		CGValuedElement evaluatorParameter = createEvaluatorParameter();
		CGText idResolver = CGModelFactory.eINSTANCE.createCGText();
		setNames2(idResolver, JavaConstants.ID_RESOLVER_NAME, JavaConstants.ID_RESOLVER_TYPE_ID);
		//		idResolver.setTextValue(evaluatorParameter.getValueName() + ".getIdResolver()");
		idResolver.setTextValue(JavaConstants.EXECUTOR_NAME + ".getIdResolver()");
		//		idResolver.getOwns().add(evaluatorParameter);
		return idResolver;
	}

	public @NonNull CGText createStandardLibraryVariable(@Nullable String contextName) {
		CGValuedElement idResolverVariable = createIdResolverVariable(contextName);
		CGText standardLibrary = CGModelFactory.eINSTANCE.createCGText();
		setNames2(standardLibrary, JavaConstants.STANDARD_LIBRARY_NAME, JavaConstants.STANDARD_LIBRARY_TYPE_ID);
		standardLibrary.setTextValue(JavaConstants.ID_RESOLVER_NAME + ".getStandardLibrary()");
		standardLibrary.getOwns().add(idResolverVariable);
		return standardLibrary;
	}

	public @Nullable CGParameter createTypeIdParameter() {
		CGParameter typeIdParameter = CGModelFactory.eINSTANCE.createCGParameter();
		setNames2(typeIdParameter, JavaConstants.TYPE_ID_NAME, JavaConstants.TYPE_ID_TYPE_ID);
		typeIdParameter.setRequired(true);
		return typeIdParameter;
	}

	public @NonNull JavaGlobalContext<@NonNull ? extends CG> getGlobalContext() {
		return globalContext;
	}

	public @NonNull CGValuedElement getIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return getOwned(cgValuedElement, JavaConstants.ID_RESOLVER_NAME);
	}

	@Override
	public NameManager.@NonNull Context getNameManagerContext() {
		return nameManagerContext;
	}

	public @NonNull JavaLocalContext<@NonNull ? extends CG> getOuterContext() {
		return parentContext != null ? parentContext.getOuterContext() : this;
	}

	public @NonNull CGValuedElement getOwned(@NonNull CGValuedElement cgValuedElement, @NonNull String name) {
		for (CGValuedElement cgOwned : cgValuedElement.getOwns()) {
			if (name.equals(cgOwned.getName())) {
				return cgOwned;
			}
			if (cgOwned instanceof CGVariableExp) {
				CGVariable cgVariable = ((CGVariableExp)cgOwned).getReferredVariable();
				if (cgVariable != null) {
					CGValuedElement cgInit = cgVariable.getInit();
					if (name.equals(cgInit.getName())) {
						return cgInit;
					}
				}
			}
		}
		if (JavaConstants.ID_RESOLVER_NAME.equals(name)) {
			// This was once needed to support Pivot validation code generation
			throw new IllegalStateException("Variable '" + name + "' must now be accessible as a getOwns() of " + cgValuedElement);
		/*	for (EObject eObject = cgValuedElement; eObject != null; eObject = eObject.eContainer()) {
				if (eObject instanceof CGLetExp) {
					CGVariable cgInit = ((CGLetExp)eObject).getInit();
					if (cgInit != null) {
						CGTypeId cgTypeId = cgInit.getTypeId();
						if ((cgTypeId != null) && JavaConstants.ID_RESOLVER_TYPE_ID.equals(cgTypeId.getASTypeId())) {
							return cgInit;
						}
					}
				}
			} */
		}
		throw new IllegalStateException("No '" + name + "' in " + cgValuedElement);
	}

	public @NonNull CGValuedElement getStandardLibraryVariable(@NonNull CGValuedElement cgValuedElement) {
		return getOwned(cgValuedElement, JavaConstants.STANDARD_LIBRARY_NAME);
	}

	public @NonNull String getValueName(@NonNull CGValuedElement cgElement) {
		CGValuedElement cgValue = cgElement;
		String valueName = cgElement.getValueName();
		if (valueName != null) {
			return valueName;
		}
		/*		if (cgValue != cgValue.getValue()) {
			CGValuedElement cgValue2 = cgValue.getValue();
			String valueName2 = cgElement.getValueName();
			String valueName3 = cgValue.getValueName();
			assert false;
		} */
		//FIXME		assert cgValue == cgValue.getValue();
		cgValue = cgValue.getNamedValue();
		valueName = cgValue.getValueName();
		if (valueName == null) {
			valueName = nameManagerContext.getSymbolName(cgValue, cgValue.getName());
			cgValue.setValueName(valueName);
		}
		return valueName;
	}

	@Override
	public void setNames(@NonNull CGValuedElement cgValueElement, @NonNull CGValuedElement cgExpression) {
		String nameHint = cgExpression.getName();
		if (nameHint == null) {
			nameHint = nameManagerContext.getSymbolName(cgExpression);
		}
		String name = nameManagerContext.getSymbolName(null, nameHint);
		cgValueElement.setName(nameHint);
		cgValueElement.setValueName(name);
	}

	protected void setNames(@NonNull CGValuedElement cgValuedElement, @NonNull String nameHint, @NonNull TypeId typeId) {
		String name = nameManagerContext.getSymbolName(null, nameHint);
		cgValuedElement.setName(nameHint);
		cgValuedElement.setValueName(name);
		cgValuedElement.setTypeId(analyzer.getTypeId(typeId));
		if (cgValuedElement instanceof CGVariable) {
			CGVariable cgVariable = (CGVariable)cgValuedElement;
			cgVariable.setNonInvalid();
			cgVariable.setNonNull();
		}
	}

	protected void setNames2(@NonNull CGValuedElement cgValuedElement, @NonNull String nameHint, @NonNull TypeId typeId) {
		//		String name = nameManagerContext.getSymbolName(null, nameHint);
		cgValuedElement.setName(nameHint);
		//		cgValuedElement.setValueName(name);
		cgValuedElement.setTypeId(analyzer.getTypeId(typeId));
		if (cgValuedElement instanceof CGVariable) {
			CGVariable cgVariable = (CGVariable)cgValuedElement;
			cgVariable.setNonInvalid();
			cgVariable.setNonNull();
		}
	}
}
