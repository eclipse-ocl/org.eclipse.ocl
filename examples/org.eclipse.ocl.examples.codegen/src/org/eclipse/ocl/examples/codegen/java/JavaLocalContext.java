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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A JavaLocalContext maintains the Java-specific context for generation of coide from a CGOperation.
 */
public class JavaLocalContext<@NonNull CG extends JavaCodeGenerator> extends AbstractJavaContext<CG> implements LocalContext
{
	protected final @NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext;
	protected final @Nullable JavaLocalContext<@NonNull ? extends CG> parentContext;
	protected final @NonNull CGElement cgScope;
	protected final @NonNull Type asType;
	protected final NameManager.@NonNull Context nameManagerContext;
	protected final boolean executorIsParameter;
	protected final boolean isStatic;

	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor paramter / caached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// A convenience cache of execitpr.getIdResolver()
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// A convenience cache of execitpr.getModelManager()
	private /*@LazyNonNull*/ CGVariable qualifiedThisVariable = null;		// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// A convenience cache of execitpr.getStandardVariable()
	private /*@LazyNonNull*/ CGParameter thisParameter = null;				// THe orphan "this" text.

	@Deprecated /* @deprecated specify executorIsParameter */
	public JavaLocalContext(@NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext, @NonNull CGElement cgScope) {
		this(globalContext, cgScope, false);
	}

	public JavaLocalContext(@NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext, @NonNull CGElement cgScope, boolean executorIsParameter) {
		super(globalContext.getCodeGenerator());
		this.globalContext = globalContext;
		this.parentContext = null;
		this.cgScope = cgScope;
		this.asType = ClassUtil.nonNullState(PivotUtil.getContainingType(((CGNamedElement)cgScope).getAst()));
		this.nameManagerContext = codeGenerator.getNameManager().createNestedContext();
		this.executorIsParameter = executorIsParameter;
		EObject esObject = cgScope instanceof CGNamedElement ? ((CGNamedElement)cgScope).getAst() : null;
		boolean staticFeature = (esObject instanceof Feature) && ((Feature)esObject).isIsStatic();
		this.isStatic = (esObject == null) || staticFeature;
	}

	public @Nullable CGVariable basicGetExecutorVariable() {
		return (executorVariable != null) && !(executorVariable instanceof CGParameter) ? executorVariable : null;
	}

	public @Nullable CGVariable basicGetIdResolverVariable() {
		return idResolverVariable;
	}

	public @Nullable CGVariable basicGetModelManagerVariable() {
		return modelManagerVariable;
	}

	public @Nullable CGVariable basicGetQualifiedThisVariable() {
		return qualifiedThisVariable;
	}

	public @Nullable CGVariable basicGetStandardLibraryVariable() {
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createExecutorParameter() {
		assert executorIsParameter;
		String executorName = globalContext.getExecutorName();
		CGParameter executorParameter = analyzer.createCGParameter(executorName, analyzer.getTypeId(JavaConstants.EXECUTOR_TYPE_ID), true);
		executorParameter.setValueName(executorName);
		executorParameter.setNonInvalid();
		executorParameter.setNonNull();
		return executorParameter;
	}

	protected @NonNull CGVariable createExecutorVariable() {
		assert !executorIsParameter;
		CGNativeOperationCallExp executorInit = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		String executorName = globalContext.getExecutorName();
		setNames2(executorInit, executorName, JavaConstants.EXECUTOR_TYPE_ID);
		executorInit.setValueName(executorName);
		executorInit.setMethod(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD);
		executorInit.getArguments().add(isStatic ? analyzer.createCGNull() : analyzer.createCGVariableExp(getThisParameter()));
		executorInit.setRequired(true);
		executorInit.setInvalidating(false);
		CGVariable executorVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(executorVariable, globalContext.getExecutorName(), JavaConstants.EXECUTOR_TYPE_ID);
		executorVariable.setInit(executorInit);
		executorVariable.setValueName(executorName);
		executorVariable.setNonInvalid();
		executorVariable.setNonNull();
		return executorVariable;
	}

	public @NonNull CGVariable createIdResolverVariable() {
		CGNativeOperationCallExp idResolverInit = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		String idResolverName = JavaConstants.ID_RESOLVER_NAME;
		setNames2(idResolverInit, idResolverName, JavaConstants.ID_RESOLVER_TYPE_ID);
		idResolverInit.setSource(analyzer.createCGVariableExp(getExecutorVariable()));
		idResolverInit.setMethod(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD);
		idResolverInit.setRequired(true);
		idResolverInit.setInvalidating(false);
		CGVariable idResolverVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(idResolverVariable, idResolverName, JavaConstants.ID_RESOLVER_TYPE_ID);
		idResolverVariable.setInit(idResolverInit);
		idResolverVariable.setValueName(idResolverName);
		idResolverVariable.setNonInvalid();
		idResolverVariable.setNonNull();
		return idResolverVariable;
	}

	public @NonNull CGVariable createModelManagerVariable() {
		CGNativeOperationCallExp modelManagerInit = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		String modelManagerName = JavaConstants.MODEL_MANAGER_NAME;
		setNames2(modelManagerInit, modelManagerName, JavaConstants.MODEL_MANAGER_TYPE_ID);
		modelManagerInit.setSource(analyzer.createCGVariableExp(getExecutorVariable()));
		modelManagerInit.setMethod(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD);
		modelManagerInit.setRequired(true);
		modelManagerInit.setInvalidating(false);
		CGVariable modelManagerVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(modelManagerVariable, modelManagerName, JavaConstants.MODEL_MANAGER_TYPE_ID);
		modelManagerVariable.setInit(modelManagerInit);
		modelManagerVariable.setValueName(modelManagerName);
		modelManagerVariable.setNonInvalid();
		modelManagerVariable.setNonNull();
		return modelManagerVariable;
	}

	public @NonNull CGVariable createQualifiedThisVariable() {
		String qualifiedThisName = asType.getName() + "_" + JavaConstants.THIS_NAME;
		CGVariable qualifiedThisVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(qualifiedThisVariable, qualifiedThisName, asType.getTypeId());
		qualifiedThisVariable.setInit(getThisParameter());
		qualifiedThisVariable.setNonInvalid();
		qualifiedThisVariable.setNonNull();
		return qualifiedThisVariable;
	}

	public @NonNull CGVariable createStandardLibraryVariable() {
		CGNativeOperationCallExp standardLibraryInit = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		String standardLibraryName = JavaConstants.STANDARD_LIBRARY_NAME;
		setNames2(standardLibraryInit, standardLibraryName, JavaConstants.STANDARD_LIBRARY_TYPE_ID);
		standardLibraryInit.setSource(analyzer.createCGVariableExp(getExecutorVariable()));
		standardLibraryInit.setMethod(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD);
		standardLibraryInit.setRequired(true);
		standardLibraryInit.setInvalidating(false);
		CGVariable standardLibraryVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(standardLibraryVariable, standardLibraryName, JavaConstants.STANDARD_LIBRARY_TYPE_ID);
		standardLibraryVariable.setInit(standardLibraryInit);
		standardLibraryVariable.setValueName(standardLibraryName);
		standardLibraryVariable.setNonInvalid();
		standardLibraryVariable.setNonNull();
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createThisParameter() {
		assert !isStatic;
		String thisName = JavaConstants.THIS_NAME;
		CGParameter thisVariable = analyzer.createCGParameter(thisName, analyzer.getTypeId(asType.getTypeId()), true);
		thisVariable.setValueName(thisName);
		thisVariable.setNonInvalid();
		thisVariable.setNonNull();
		return thisVariable;
	}

	@Deprecated /* @deprecated no longer used */
	public @Nullable CGParameter createTypeIdParameter() {
		String typeIdName = JavaConstants.TYPE_ID_NAME;
		CGParameter typeIdParameter = analyzer.createCGParameter(typeIdName, analyzer.getTypeId(JavaConstants.TYPE_ID_TYPE_ID), true);
		typeIdParameter.setValueName(typeIdName);
		typeIdParameter.setNonInvalid();
		typeIdParameter.setNonNull();
		return typeIdParameter;
	}

	public @Nullable CGValuedElement getBody() {
		if (cgScope instanceof CGConstraint) {
			return ((CGConstraint)cgScope).getBody();
		}
		else if (cgScope instanceof CGOperation) {
			return ((CGOperation)cgScope).getBody();
		}
		else if (cgScope instanceof CGProperty) {
			return ((CGProperty)cgScope).getBody();
		}
		throw new UnsupportedOperationException();
	}

	public @NonNull CGVariable getExecutorVariable() {
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = executorIsParameter ? createExecutorParameter() : createExecutorVariable();
		}
		return executorVariable2;
	}

	public @NonNull JavaGlobalContext<@NonNull ? extends CG> getGlobalContext() {
		return globalContext;
	}

	@Deprecated /* @deprecated unnecessary argument */
	public @NonNull CGVariable getIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return getIdResolverVariable();
	}

	public @NonNull CGVariable getIdResolverVariable() {
		CGVariable idResolverVariable2 = idResolverVariable;
		if (idResolverVariable2 == null) {
			idResolverVariable = idResolverVariable2 = createIdResolverVariable();
		}
		return idResolverVariable2;
	}

	public @NonNull CGVariable getModelManagerVariable() {
		CGVariable modelManagerVariable2 = modelManagerVariable;
		if (modelManagerVariable2 == null) {
			modelManagerVariable = modelManagerVariable2 = createModelManagerVariable();
		}
		return modelManagerVariable2;
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

	public @NonNull CGVariable getQualifiedThisVariable() {
		CGVariable qualifiedThisVariable2 = qualifiedThisVariable;
		if (qualifiedThisVariable2 == null) {
			qualifiedThisVariable = qualifiedThisVariable2 = createQualifiedThisVariable();
		}
		return qualifiedThisVariable2;
	}

	@Deprecated /* @deprecated unnecessary argument */
	public @NonNull CGVariable getStandardLibraryVariable(@NonNull CGValuedElement cgValuedElement) {
		return getStandardLibraryVariable();
	}

	public @NonNull CGVariable getStandardLibraryVariable() {
		CGVariable standardLibraryVariable2 = standardLibraryVariable;
		if (standardLibraryVariable2 == null) {
			standardLibraryVariable = standardLibraryVariable2 = createStandardLibraryVariable();
		}
		return standardLibraryVariable2;
	}

	public @NonNull CGParameter getThisParameter() {
		assert !isStatic;
		CGParameter thisParameter2 = thisParameter;
		if (thisParameter2 == null) {
			thisParameter = thisParameter2 = createThisParameter();
		}
		return thisParameter2;
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

	public @NonNull CGValuedElement wrapLetVariables(@NonNull CGValuedElement cgTree) {
		CGVariable qualifiedThisVariable = basicGetQualifiedThisVariable();
		if (qualifiedThisVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, qualifiedThisVariable);
		}
		CGVariable standardLibraryVariable = basicGetStandardLibraryVariable();
		if (standardLibraryVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, standardLibraryVariable);
		}
		CGVariable modelManagerVariable = basicGetModelManagerVariable();
		if (modelManagerVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, modelManagerVariable);
		}
		CGVariable idResolverVariable = basicGetIdResolverVariable();
		if (idResolverVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, idResolverVariable);
		}
		CGVariable executorVariable = basicGetExecutorVariable();
		if (executorVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, executorVariable);
		}
		return cgTree;
	}
}
