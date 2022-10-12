/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * An ExecutableNameManager supervises the parameter and variable names allocated within the scope of an Element with an executable body.
 */
public class ExecutableNameManager extends NestedNameManager
{
	protected final @NonNull ClassNameManager classNameManager;
	protected final @NonNull CGNamedElement cgScope;
	protected final @NonNull NamedElement asScope;
	protected final boolean isStatic;

	private /*@LazyNonNull*/ CGParameter anyParameter = null;				// A local parameter spelled "any" to be added to the static signature
	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor parameter / caached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// A convenience cache of execitpr.getIdResolver()
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// A convenience cache of execitpr.getModelManager()
	private /*@LazyNonNull*/ CGFinalVariable qualifiedThisVariable = null;	// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// A convenience cache of execitpr.getStandardVariable()
	private /*@LazyNonNull*/ CGParameter selfParameter = null;				// A local parameter spelled "self" to be added to the signature
	private /*@LazyNonNull*/ CGParameter thisParameter = null;				// A local orphan parameter spelled "this"
	private /*@LazyNonNull*/ CGParameter typeIdParameter = null;			// A local orphan parameter spelled "typeId"

	/**
	 * Mapping from an AS Variable to the CG Variable defined in this cgScope.
	 */
	private @NonNull Map<@NonNull VariableDeclaration, @NonNull CGVariable> asVariable2cgVariable = new HashMap<>();	// XXX Eliminate use CGA.as2cg

	public ExecutableNameManager(@NonNull ClassNameManager classNameManager, @NonNull NestedNameManager parentNameManager, @NonNull CGNamedElement cgScope) {
		super(classNameManager.getCodeGenerator(), parentNameManager, cgScope);
		this.classNameManager = classNameManager;
		this.cgScope = cgScope;
		this.asScope = CGUtil.getAST(cgScope);
		boolean staticFeature = (asScope instanceof Feature) && ((Feature)asScope).isIsStatic();
		this.isStatic = /*(asScope == null) ||*/ staticFeature;
		assert !(parent instanceof ExecutableNameManager) || (((ExecutableNameManager)parent).cgScope != cgScope);		// XXX
		assert !(cgScope instanceof CGClass) || (cgScope instanceof CGPackage);
	}

	public void addVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
		CGVariable old = asVariable2cgVariable.put(asVariable, cgVariable);
		assert old == null;
//		codeGenerator.getAnalyzer().addVariable(asVariable, cgVariable);
	}

	public @Nullable CGParameter basicGetAnyParameter() {
		return anyParameter;
	}

	public @Nullable CGParameter basicGetCGParameter(@NonNull VariableDeclaration asVariable) {		// XXX tighten to AS Parameter
		CGVariable cgVariable = asVariable2cgVariable.get(asVariable);
		if (cgVariable instanceof CGParameter) {
			return (CGParameter)cgVariable;
		}
		else if (cgVariable != null) {
			throw new IllegalStateException(cgVariable + " is not  a CGParameter");
		}
		else if (!(getASScope() instanceof Operation) && (parent instanceof ExecutableNameManager)) {				// XXX polymorphize
			return ((ExecutableNameManager)parent).basicGetCGParameter(asVariable);
		}
		else {
			return null;
		}
	}

	public @Nullable CGVariable basicGetCGParameterVariable(@NonNull VariableDeclaration asVariable) {				// XXX tighten to AS ParameterVariable
		return asVariable2cgVariable.get(asVariable);
	}

	public @Nullable CGVariable basicGetCGVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = asVariable2cgVariable.get(asVariable);
		if (cgVariable != null) {
			return cgVariable;
		}
		else if (parent instanceof ExecutableNameManager) {				// XXX polymorphize
			return ((ExecutableNameManager)parent).basicGetCGVariable(asVariable);
		}
		else {
			return null;
		}
	}

	public @Nullable CGVariable basicGetExecutorVariable() {
		return (executorVariable != null) && !(executorVariable instanceof CGParameter) ? executorVariable : null;
	}

	public @Nullable CGVariable basicGetIdResolverVariable() {
		return idResolverVariable;
	}

	public @Nullable CGVariable basicGetLocalVariable(@NonNull VariableDeclaration asVariable) {
		return asVariable2cgVariable.get(asVariable);
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
		NameResolution anyName = globalNameManager.getAnyNameResolution();
		CGParameter anyParameter = analyzer.createCGParameter(anyName, analyzer.getCGTypeId(TypeId.OCL_ANY), false);
		anyParameter.setNonInvalid();
		return anyParameter;
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull VariableDeclaration asVariable) {
		assert basicGetCGVariable(asVariable) == null;
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		cgVariable.setAst(asVariable);
		cgVariable.setTypeId(analyzer.getCGTypeId(asVariable.getTypeId()));
//		declarePreferredName(cgVariable);
		addVariable(asVariable, cgVariable);
		return cgVariable;
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull CGValuedElement cgInit) {
//		NameResolution nameResolution = getNameResolution(cgInit);
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		cgVariable.setAst(cgInit.getAst());
		cgVariable.setTypeId(cgInit.getTypeId());
		cgVariable.setInit(cgInit);
//		nameResolution.addCGElement(cgVariable);
		return cgVariable;
	}

	protected @NonNull CGVariable createExecutorVariable() {
		CGNativeOperationCallExp executorInit = analyzer.createCGNativeOperationCallExp(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution executorNameResolution = globalNameManager.getExecutorNameResolution();
	//	executorNameResolution.addCGElement(executorInit);
		executorInit.setTypeId(analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID));
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
		executorVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID));
		executorVariable.setInit(executorInit);
		executorVariable.setNonInvalid();
		executorVariable.setNonNull();
		executorNameResolution.addCGElement(executorVariable);
		return executorVariable;
	}

	public @NonNull CGVariable createIdResolverVariable() {
		CGNativeOperationCallExp idResolverInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
		idResolverNameResolution.addCGElement(idResolverInit);
		idResolverInit.setTypeId(analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID));
		idResolverInit.setCgThis(analyzer.createCGVariableExp(analyzer.getExecutorVariable(this)));
		idResolverInit.setRequired(true);
		idResolverInit.setInvalidating(false);
		CGVariable idResolverVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		idResolverVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID));
		idResolverVariable.setInit(idResolverInit);
		idResolverVariable.setNonInvalid();
		idResolverVariable.setNonNull();
		idResolverNameResolution.addCGElement(idResolverVariable);
		return idResolverVariable;
	}

	public @NonNull CGVariable createModelManagerVariable() {
		CGNativeOperationCallExp modelManagerInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution modelManagerNameResolution = globalNameManager.getModelManagerNameResolution();
		modelManagerNameResolution.addCGElement(modelManagerInit);
		modelManagerInit.setTypeId(analyzer.getCGTypeId(JavaConstants.MODEL_MANAGER_TYPE_ID));
		modelManagerInit.setCgThis(analyzer.createCGVariableExp(analyzer.getExecutorVariable(this)));
		modelManagerInit.setRequired(true);
		modelManagerInit.setInvalidating(false);
		CGVariable modelManagerVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		modelManagerVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.MODEL_MANAGER_TYPE_ID));
		modelManagerVariable.setInit(modelManagerInit);
		modelManagerVariable.setNonInvalid();
		modelManagerVariable.setNonNull();
		modelManagerNameResolution.addCGElement(modelManagerVariable);
		return modelManagerVariable;
	}

	public @NonNull CGFinalVariable createQualifiedThisVariable() {
		NameResolution qualifiedThisNameResolution = globalNameManager.declareEagerName(null, classNameManager.getASClass().getName() + "_" + JavaConstants.THIS_NAME);
		CGFinalVariable qualifiedThisVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		qualifiedThisVariable.setTypeId(analyzer.getCGTypeId(classNameManager.getASClass().getTypeId()));
		qualifiedThisVariable.setInit(getThisParameter());
		qualifiedThisVariable.setNonInvalid();
		qualifiedThisVariable.setNonNull();
		qualifiedThisNameResolution.addCGElement(qualifiedThisVariable);
		return qualifiedThisVariable;
	}

	protected @NonNull CGParameter createSelfParameter() {
	//	assert !isStatic;
		if (cgScope instanceof CGForeignProperty) {
		//	Property referredProperty = CGUtil.getAST(((CGForeignProperty)scope));
		//	OperationId operationId = referredOperation.getOperationId();
			boolean sourceMayBeNull = false; //analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
			NameResolution selfName = globalNameManager.getSelfNameResolution();
			CGParameter selfParameter = analyzer.createCGParameter(selfName, analyzer.getCGTypeId(classNameManager.getASClass().getTypeId()), !sourceMayBeNull);
			selfParameter.setIsSelf(true);
			selfParameter.setNonInvalid();
			selfParameter.setNonNull();
			return selfParameter;
		}
		else if (cgScope instanceof CGOperation) {
			Operation referredOperation = CGUtil.getAST(((CGOperation)cgScope));
			OperationId operationId = referredOperation.getOperationId();
			boolean sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
			NameResolution selfName = globalNameManager.getSelfNameResolution();
			CGParameter selfParameter = analyzer.createCGParameter(selfName, analyzer.getCGTypeId(classNameManager.getASClass().getTypeId()), !sourceMayBeNull);
			selfParameter.setIsSelf(true);
			selfParameter.setNonInvalid();
			selfParameter.setNonNull();
			return selfParameter;
		}
		else {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ".createSelfParameter for " + cgScope.eClass().getName());
		}
	}

	public @NonNull CGVariable createStandardLibraryVariable() {
		CGNativeOperationCallExp standardLibraryInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution standardLibraryNameResolution = globalNameManager.getStandardLibraryVariableNameResolution();
		standardLibraryNameResolution.addCGElement(standardLibraryInit);
		standardLibraryInit.setTypeId(analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID));
		standardLibraryInit.setCgThis(analyzer.createCGVariableExp(analyzer.getExecutorVariable(this)));
		standardLibraryInit.setRequired(true);
		standardLibraryInit.setInvalidating(false);
		CGVariable standardLibraryVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		standardLibraryVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID));
		standardLibraryVariable.setInit(standardLibraryInit);
		standardLibraryVariable.setNonInvalid();
		standardLibraryVariable.setNonNull();
		standardLibraryNameResolution.addCGElement(standardLibraryVariable);
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createThisParameter() {
		assert !isStatic;
		NameResolution thisName = globalNameManager.getThisNameResolution();
		CGParameter thisParameter = analyzer.createCGParameter(thisName, analyzer.getCGTypeId(classNameManager.getASClass().getTypeId()), true);
		thisParameter.setIsThis(true);
		thisParameter.setNonInvalid();
		thisParameter.setNonNull();
		return thisParameter;
	}

	@Override
	public @NonNull NamedElement getASScope() {
		return asScope;
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
		else if (cgScope instanceof CGBodiedProperty) {
			return ((CGBodiedProperty)cgScope).getBody();
		}
		assert false;;
		return null;
	}

	public @NonNull CGExecutorType getCGExecutorType(@NonNull Type asType) {
		//	if (parent instanceof ExecutableNameManager) {
		//		return ((ExecutableNameManager)parent).getCGExecutorType(asType);
		//	}
		//
		//	It would be better to share multi-uses, but that requires the ownership to move from the calling
		//	context to a dependency-sequenced list of let-variables to wrap the whole usage.
		//
		//	Map<@NonNull Type, @NonNull CGExecutorType> asType2cgType2 = asType2cgType;
		//	if (asType2cgType2 == null) {
		//		asType2cgType = asType2cgType2 = new HashMap<>();
		//	}
		//	CGExecutorType cgExecutorType = asType2cgType2.get(asType);
		//	if (cgExecutorType == null) {
		TypeId typeId = asType.getTypeId();
		CGExecutorType cgExecutorType = CGModelFactory.eINSTANCE.createCGExecutorType();
		CGTypeId cgTypeId = analyzer.getCGTypeId(typeId);
		cgExecutorType.setUnderlyingTypeId(cgTypeId);
		cgExecutorType.setAst(asType);
		getNameResolution(cgExecutorType);		// Needs idResolver so cannot be global
		cgExecutorType.setTypeId(analyzer.getCGTypeId(JavaConstants.CLASS_TYPE_ID));
		cgExecutorType.getDependsOn().add(cgTypeId);
		//	asType2cgType2.put(asType, cgExecutorType);
		//	}
		return cgExecutorType;
	}

	public @NonNull CGParameter getCGParameter(@NonNull VariableDeclaration asParameter, @Nullable String explicitName) {
		CGParameter cgParameter = basicGetCGParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			if (explicitName != null) {
				assert explicitName.equals(asParameter.getName());
				Operation asOperation = PivotUtil.getContainingOperation(asParameter);
				Constraint asConstraint = PivotUtil.getContainingConstraint(asParameter);
				assert ((asOperation != null) && (asOperation.getESObject() instanceof EOperation)) || ((asConstraint != null) && (asConstraint.getESObject() instanceof EOperation));
			}
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			if (asParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

	@Override
	public @NonNull CGNamedElement getCGScope() {
		return cgScope;
	}

	public @NonNull CGVariable getCGVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = basicGetCGVariable(asVariable);
		if (cgVariable == null) {
			cgVariable = createCGVariable(asVariable);
			if (asVariable.isIsRequired()) {
				cgVariable.setNonInvalid();
				cgVariable.setNonNull();
			}
		}
		return cgVariable;
	}

	public @NonNull ClassNameManager getClassNameManager() {
		return classNameManager;
	}

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	@Override
	public @NonNull ClassNameManager getClassParentNameManager() {
		return getRootExecutableNameManager().getClassNameManager();
	}

	public @NonNull CGParameter getExecutorParameter() {
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = codeGenerator.createExecutorParameter();
		}
		return (CGParameter)executorVariable2;
	}

	public @NonNull CGVariable getExecutorVariableInternal() {	// Invoked from CodeGenAnalyzer that overrides for JUnit support
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getExecutorVariableInternal();
		}
	//	if (asScope instanceof CallExp) {
	//		return ((ExecutableNameManager)parent).getExecutorVariable();
	//	}
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorVariable();
		}
		return executorVariable2;
	}

	public @NonNull CGVariable getIdResolverVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getIdResolverVariable();
		}
	//	if (asScope instanceof CallExp) {
	//		return ((ExecutableNameManager)parent).getIdResolverVariable();
	//	}
		CGVariable idResolverVariable2 = idResolverVariable;
		if (idResolverVariable2 == null) {
			idResolverVariable = idResolverVariable2 = createIdResolverVariable();
		}
		return idResolverVariable2;
	}

	public @NonNull CGIterator getIterator(@NonNull VariableDeclaration asVariable) {
		CGIterator cgIterator = (CGIterator)basicGetCGVariable(asVariable);
		if (cgIterator == null) {
			cgIterator = CGModelFactory.eINSTANCE.createCGIterator();
			cgIterator.setAst(asVariable);
			cgIterator.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// FIXME Java-specific type of polymorphic operation parameter
//			declarePreferredName(cgIterator);
			addVariable(asVariable, cgIterator);
		}
		return cgIterator;
	}

	public @NonNull CGVariable getModelManagerVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getModelManagerVariable();
		}
		CGVariable modelManagerVariable2 = modelManagerVariable;
		if (modelManagerVariable2 == null) {
			modelManagerVariable = modelManagerVariable2 = createModelManagerVariable();
		}
		return modelManagerVariable2;
	}



/*	public @NonNull CGParameter getParameter(@NonNull Variable asParameter, @NonNull NameResolution nameResolution) {
		CGParameter cgParameter = basicGetParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setName(asParameter.getName());
			nameResolution.addCGElement(cgParameter);
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			declareLazyName(cgParameter);
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			if (asParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	} */

	public @NonNull CGFinalVariable getQualifiedThisVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getQualifiedThisVariable();
		}
	//	if (asScope != classNameManager.getASClass()) {				// XXX
	//		return ((ExecutableNameManager)parent).getQualifiedThisVariable();
	//	}
		CGFinalVariable qualifiedThisVariable2 = qualifiedThisVariable;
		if (qualifiedThisVariable2 == null) {
			qualifiedThisVariable = qualifiedThisVariable2 = createQualifiedThisVariable();
		}
		return qualifiedThisVariable2;
	}

	public @NonNull ExecutableNameManager getRootExecutableNameManager() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getRootExecutableNameManager();
		}
		else {
			return this;
		}
	}

	public @NonNull CGParameter getSelfParameter() {
	//	assert !isStatic;
		CGParameter selfParameter2 = selfParameter;
		if (selfParameter2 == null) {
			selfParameter = selfParameter2 = createSelfParameter();
		}
		return selfParameter2;
	}

	public @NonNull CGVariable getStandardLibraryVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getStandardLibraryVariable();
		}
	//	if (asScope instanceof CallExp) {
	//		return ((ExecutableNameManager)parent).getStandardLibraryVariable();
	//	}
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

	public @NonNull CGParameter getThisParameter(@NonNull VariableDeclaration asParameter) {		// XXX Why with/without arg variants
		CGParameter cgParameter = basicGetCGParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			globalNameManager.getThisNameResolution().addCGElement(cgParameter);
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			if (asParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
			cgParameter.setIsSelf(true);
		}
		return cgParameter;
	}

	public @NonNull CGParameter getTypeIdParameter() {
	//	assert !isStatic;
		CGParameter typeIdParameter2 = typeIdParameter;
		if (typeIdParameter2 == null) {
			typeIdParameter = typeIdParameter2 = codeGenerator.createTypeIdParameter();
		}
		return typeIdParameter2;
	}

/*	public @NonNull CGParameter getTypeIdParameter() {		-- no addVariable - no AST
		//	assert !isStatic;
		CGParameter typeIdParameter2 = typeIdParameter;
		if (typeIdParameter2 == null) {
			typeIdParameter = typeIdParameter2 = codeGenerator.createTypeIdParameter();
		}
		assert typeIdParameter2.eContainer() == null;
		addVariable(CGUtil.getAST(typeIdParameter2), typeIdParameter2);
		return typeIdParameter2;
	} */

	public @NonNull CGValuedElement wrapLetVariables(@NonNull CGValuedElement cgTree) {
		CGVariable qualifiedThisVariable = basicGetQualifiedThisVariable();
		if (qualifiedThisVariable != null) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, qualifiedThisVariable);
		}
		CGVariable standardLibraryVariable = basicGetStandardLibraryVariable();
		if (standardLibraryVariable != null) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, standardLibraryVariable);
		}
		CGVariable modelManagerVariable = basicGetModelManagerVariable();
		if (modelManagerVariable != null) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, modelManagerVariable);
		}
		CGVariable idResolverVariable = basicGetIdResolverVariable();
		if (idResolverVariable != null) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, idResolverVariable);
		}
		CGVariable executorVariable = basicGetExecutorVariable();
		if (executorVariable != null) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, executorVariable);
		}
		return cgTree;
	}
}
