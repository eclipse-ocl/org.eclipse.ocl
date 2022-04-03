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
package org.eclipse.ocl.examples.codegen.java;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
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
import org.eclipse.ocl.pivot.NamedElement;
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
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @Nullable JavaLocalContext<@NonNull ? extends CG> outerContext;
	protected final @NonNull CGNamedElement cgScope;
	protected final @NonNull NamedElement asScope;
	protected final @NonNull Type asType;
	protected final @NonNull NestedNameManager nameManager;
	protected final boolean executorIsParameter;
	protected final boolean isStatic;

	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor paramter / caached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// A convenience cache of execitpr.getIdResolver()
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// A convenience cache of execitpr.getModelManager()
	private /*@LazyNonNull*/ CGVariable qualifiedThisVariable = null;		// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// A convenience cache of execitpr.getStandardVariable()
	private /*@LazyNonNull*/ CGParameter selfParameter = null;				// A local parameter spelled "self" to be added to the signature
	private /*@LazyNonNull*/ CGParameter thisParameter = null;				// A local orphan parameter spelled "this"
	private /*@LazyNonNull*/ CGParameter typeIdParameter = null;			// A local orphan parameter spelled "typeId"
	private /*@LazyNonNull*/ CGParameter anyParameter = null;				// A local parameter spelled "any" to be added to the static signature

	@Deprecated /* @deprecated specify executorIsParameter */
	public JavaLocalContext(@NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext, @NonNull CGElement cgScope) {
		this(globalContext, null, (CGNamedElement)cgScope, (NamedElement)((CGNamedElement)cgScope).getAst(), false);
	}

	public JavaLocalContext(@NonNull JavaGlobalContext<@NonNull ? extends CG> globalContext, @Nullable JavaLocalContext<@NonNull ? extends CG> outerContext,
			@NonNull CGNamedElement cgScope, @NonNull NamedElement asScope, boolean zzexecutorIsParameter) {
		super(globalContext.getCodeGenerator());
		assert zzexecutorIsParameter == false;
		this.globalContext = globalContext;
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.outerContext = outerContext;
		this.cgScope = cgScope;
		this.asScope = asScope;
		if (outerContext != null) {
			this.asType = outerContext.asType;
			this.nameManager = outerContext.getNameManager().createNestedNameManager(cgScope);
		}
		else {
			this.asType = ClassUtil.nonNullState(PivotUtil.getContainingType(asScope));
			this.nameManager = globalNameManager.createNestedNameManager(cgScope);
		}
		if (cgScope instanceof CGOperation) {
			OperationCallingConvention callingConvention = ((CGOperation)cgScope).getCallingConvention();
			this.executorIsParameter = callingConvention.getExecutorIsParameter();
		}
		else {
			this.executorIsParameter = zzexecutorIsParameter;
		}
		boolean staticFeature = (asScope instanceof Feature) && ((Feature)asScope).isIsStatic();
		this.isStatic = /*(asScope == null) ||*/ staticFeature;
	}

	public @Nullable CGParameter basicGetAnyParameter() {
		return anyParameter;
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

	public @Nullable CGParameter basicGetSelfParameter() {
		return selfParameter;
	}

	public @Nullable CGVariable basicGetStandardLibraryVariable() {
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createAnyParameter() {
		assert isStatic;
		NameResolution anyName = globalContext.getAnyNameResolution();
		CGParameter anyParameter = analyzer.createCGParameter(anyName.getResolvedName(), analyzer.getTypeId(asType.getTypeId()), false);
		anyParameter.setNonInvalid();
		anyName.addSecondaryElement(anyParameter);
		return anyParameter;
	}

	protected @NonNull CGParameter createExecutorParameter() {
	//	assert executorIsParameter;
		NameResolution executorName = globalContext.getExecutorNameResolution();
		CGParameter executorParameter = analyzer.createCGParameter(executorName.getResolvedName(), analyzer.getTypeId(JavaConstants.EXECUTOR_TYPE_ID), true);
	//	executorParameter.setValueName(executorName);
		executorParameter.setNonInvalid();
		executorParameter.setNonNull();
		executorName.addSecondaryElement(executorParameter);
		return executorParameter;
	}

	protected @NonNull CGVariable createExecutorVariable() {
	//	assert !executorIsParameter;
		CGNativeOperationCallExp executorInit = analyzer.createCGNativeOperationCallExp(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD);
		NameResolution executorName = globalContext.getExecutorNameResolution();
		setNames2(executorInit, executorName, JavaConstants.EXECUTOR_TYPE_ID);
	//	executorInit.setValueName(executorName.getResolvedName());
		CGValuedElement contextParameter;
		if (!isStatic) {
			contextParameter = analyzer.createCGVariableExp(getThisParameter());
		}
		else {
			CGParameter selfParameter = basicGetSelfParameter();
			if (selfParameter != null) {
				contextParameter = analyzer.createCGVariableExp(selfParameter);
			}
			else {
				CGParameter anyParameter = basicGetAnyParameter();
				if (anyParameter != null) {
					contextParameter = analyzer.createCGVariableExp(anyParameter);
				}
				else {
					contextParameter = analyzer.createCGNull();
				}
			}
		}
		executorInit.getArguments().add(contextParameter);
		executorInit.setRequired(true);
		executorInit.setInvalidating(false);
		CGVariable executorVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(executorVariable, executorName, JavaConstants.EXECUTOR_TYPE_ID);
		executorVariable.setInit(executorInit);
	//	executorVariable.setValueName(executorName);
		executorVariable.setNonInvalid();
		executorVariable.setNonNull();
		executorName.addSecondaryElement(executorVariable);
		return executorVariable;
	}

	public @NonNull CGVariable createIdResolverVariable() {
		CGNativeOperationCallExp idResolverInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD);
		NameResolution idResolverNameResolution = globalContext.getIdResolverNameResolution();
		setNames2(idResolverInit, idResolverNameResolution, JavaConstants.ID_RESOLVER_TYPE_ID);
		idResolverInit.setSource(analyzer.createCGVariableExp(getExecutorVariable()));
		idResolverInit.setRequired(true);
		idResolverInit.setInvalidating(false);
		CGVariable idResolverVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(idResolverVariable, idResolverNameResolution, JavaConstants.ID_RESOLVER_TYPE_ID);
		idResolverVariable.setInit(idResolverInit);
	//	idResolverVariable.setValueName(idResolverName);
		idResolverVariable.setNonInvalid();
		idResolverVariable.setNonNull();
		idResolverNameResolution.addSecondaryElement(idResolverVariable);
		return idResolverVariable;
	}

	public @NonNull CGVariable createModelManagerVariable() {
		CGNativeOperationCallExp modelManagerInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD);
		NameResolution modelManagerName = globalContext.getModelManagerNameResolution();
		setNames2(modelManagerInit, modelManagerName, JavaConstants.MODEL_MANAGER_TYPE_ID);
		modelManagerInit.setSource(analyzer.createCGVariableExp(getExecutorVariable()));
		modelManagerInit.setRequired(true);
		modelManagerInit.setInvalidating(false);
		CGVariable modelManagerVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(modelManagerVariable, modelManagerName, JavaConstants.MODEL_MANAGER_TYPE_ID);
		modelManagerVariable.setInit(modelManagerInit);
	//	modelManagerVariable.setValueName(modelManagerName);
		modelManagerVariable.setNonInvalid();
		modelManagerVariable.setNonNull();
		modelManagerName.addSecondaryElement(modelManagerVariable);
		return modelManagerVariable;
	}

	public @NonNull CGVariable createQualifiedThisVariable() {
		NameResolution qualifiedThisName = globalNameManager.declareGlobalName(null, asType.getName() + "_" + JavaConstants.THIS_NAME);
		CGVariable qualifiedThisVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(qualifiedThisVariable, qualifiedThisName, asType.getTypeId());
		qualifiedThisVariable.setInit(getThisParameter());
		qualifiedThisVariable.setNonInvalid();
		qualifiedThisVariable.setNonNull();
		qualifiedThisName.addSecondaryElement(qualifiedThisVariable);
		return qualifiedThisVariable;
	}

	protected @NonNull CGParameter createSelfParameter() {
	//	assert !isStatic;
		NameResolution selfName = globalContext.getSelfNameResolution();
		CGParameter selfParameter = analyzer.createCGParameter(selfName.getResolvedName(), analyzer.getTypeId(asType.getTypeId()), true);
		selfParameter.setNonInvalid();
		selfParameter.setNonNull();
		selfName.addSecondaryElement(selfParameter);
		return selfParameter;
	}

	public @NonNull CGVariable createStandardLibraryVariable() {
		CGNativeOperationCallExp standardLibraryInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD);
		NameResolution standardLibraryNameResolution = globalContext.getStandardLibraryVariableNameResolution();
		setNames2(standardLibraryInit, standardLibraryNameResolution, JavaConstants.STANDARD_LIBRARY_TYPE_ID);
		standardLibraryInit.setSource(analyzer.createCGVariableExp(getExecutorVariable()));
		standardLibraryInit.setRequired(true);
		standardLibraryInit.setInvalidating(false);
		CGVariable standardLibraryVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		setNames2(standardLibraryVariable, standardLibraryNameResolution, JavaConstants.STANDARD_LIBRARY_TYPE_ID);
		standardLibraryVariable.setInit(standardLibraryInit);
	//	standardLibraryVariable.setValueName(standardLibraryName);
		standardLibraryVariable.setNonInvalid();
		standardLibraryVariable.setNonNull();
		standardLibraryNameResolution.addSecondaryElement(standardLibraryVariable);
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createThisParameter() {
		assert !isStatic;
		NameResolution thisName = globalContext.getThisNameResolution();
		CGParameter thisParameter = analyzer.createCGParameter(thisName.getResolvedName(), analyzer.getTypeId(asType.getTypeId()), true);
		thisParameter.setNonInvalid();
		thisParameter.setNonNull();
		thisName.addSecondaryElement(thisParameter);
		return thisParameter;
	}

	@Deprecated /* @deprecated no longer used */
	public @NonNull CGParameter createTypeIdParameter() {
		NameResolution typeIdNameResolution = globalContext.getTypeIdNameResolution();
		CGParameter typeIdParameter = analyzer.createCGParameter(typeIdNameResolution.getResolvedName(), analyzer.getTypeId(JavaConstants.TYPE_ID_TYPE_ID), true);
	//	typeIdParameter.setValueName(typeIdName);
		typeIdParameter.setNonInvalid();
		typeIdParameter.setNonNull();
		typeIdNameResolution.addSecondaryElement(typeIdParameter);
		return typeIdParameter;
	}

	public @NonNull CGParameter getAnyParameter() {
	//	assert !isStatic;
		CGParameter anyParameter2 = anyParameter;
		if (anyParameter2 == null) {
			anyParameter = anyParameter2 = createAnyParameter();
		}
		return anyParameter2;
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
		assert false;;
		return null;
	}

	public @NonNull CGParameter getExecutorParameter() {
	//	assert executorIsParameter;
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorParameter();
		}
		return (CGParameter)executorVariable2;
	}

	public @NonNull CGVariable getExecutorVariable() {
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorVariable();
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
	public @NonNull NestedNameManager getNameManager() {
		return nameManager;
	}

	public @NonNull JavaLocalContext<@NonNull ? extends CG> getOuterContext() {
		return outerContext != null ? outerContext.getOuterContext() : this;
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
		throw new IllegalStateException("No '" + name + "' in " + cgValuedElement);
	}

	public @NonNull CGVariable getQualifiedThisVariable() {
		CGVariable qualifiedThisVariable2 = qualifiedThisVariable;
		if (qualifiedThisVariable2 == null) {
			qualifiedThisVariable = qualifiedThisVariable2 = createQualifiedThisVariable();
		}
		return qualifiedThisVariable2;
	}

	@Override
	public @NonNull CGNamedElement getScope() {
		return cgScope;
	}

	public @NonNull CGParameter getSelfParameter() {
	//	assert !isStatic;
		CGParameter selfParameter2 = selfParameter;
		if (selfParameter2 == null) {
			selfParameter = selfParameter2 = createSelfParameter();
		}
		return selfParameter2;
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

	public @NonNull CGParameter getTypeIdParameter() {
		assert !isStatic;
		CGParameter typeIdParameter2 = typeIdParameter;
		if (typeIdParameter2 == null) {
			typeIdParameter = typeIdParameter2 = createTypeIdParameter();
		}
		return typeIdParameter2;
	}

	protected void setNames2(@NonNull CGValuedElement cgValuedElement, @NonNull NameResolution nameHint, @NonNull TypeId typeId) {
		//		String name = nameManagerContext.getSymbolName(null, nameHint);
		cgValuedElement.setName(nameHint.getResolvedName());
		//		cgValuedElement.setValueName(name);
		cgValuedElement.setTypeId(analyzer.getTypeId(typeId));
		if (cgValuedElement instanceof CGVariable) {
			CGVariable cgVariable = (CGVariable)cgValuedElement;
			cgVariable.setNonInvalid();
			cgVariable.setNonNull();
		}
	}

	@Override
	public @NonNull String toString() {
		return nameManager.toString();
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
