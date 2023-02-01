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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.AbstractOperationCallingConvention.CGParameterStyle;
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
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
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
	private /*@LazyNonNull*/ CGParameter contextObjectParameter = null;		// A local parameter spelled "contextObject" to distinguish unique evaluations
	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor parameter / cached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// A convenience cache of execitpr.getIdResolver()
	private /*@LazyNonNull*/ CGParameter idResolverParameter = null;		// A local orphan parameter spelled "idResolver" -- XXX probably doesn't need caching
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// A convenience cache of execitpr.getModelManager()
	private /*@LazyNonNull*/ CGFinalVariable qualifiedThisVariable = null;	// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// A convenience cache of executor.getStandardVariable()
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
//		analyzer.addVariable(asVariable, cgVariable);
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
			// throw new IllegalStateException(cgVariable + " is not a CGParameter");		-- might ber a let variable unboxing a parameter
			return null;
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

	public @Nullable Parameter basicGetExecutorParameter(@NonNull Operation asOperation) {
		NameResolution executorResolution = globalNameManager.getExecutorNameResolution();
		String executorName = executorResolution.getResolvedName();
		return NameUtil.getNameable(asOperation.getOwnedParameters(), executorName);
	}

	public @Nullable CGVariable basicGetExecutorVariable() {
		return executorVariable;
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

	public void createCGParameters(@NonNull CGParameterStyle @NonNull  [] cgParameterStyles, @Nullable TypedElement zzasOrigin) {
		CGOperation cgOperation = (CGOperation)getCGScope();
		Operation asOperation = (Operation)getASScope();
		if (cgParameterStyles.length > 0) {
			List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
			for (@NonNull CGParameterStyle cgParameterStyle : cgParameterStyles) {
				switch(cgParameterStyle) {
					case BODY_SELF: {
						ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
						CGParameter cgParameter = analyzer.getSelfParameter(this, PivotUtil.getOwnedContext(asExpressionInOCL));
						cgParameters.add(cgParameter);
						break;
					}
					case BOXED_VALUES: {
						Parameter asBoxedValuesParameter = getBoxedValuesParameter(asOperation);
						CGParameter cgParameter = getCGParameter(asBoxedValuesParameter, (String)null);
						globalNameManager.getBoxedValuesNameResolution().addCGElement(cgParameter);
						cgParameters.add(cgParameter);
						break;
					}
					case CONTEXT_OBJECT: {
						CGParameter cgParameter = getContextObjectParameter();
						cgParameters.add(cgParameter);
						break;
					}
					case EAGER_PARAMETERS: {
						for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
							CGParameter cgParameter = getCGParameter(asParameter, null);
							cgParameters.add(cgParameter);
							declareEagerName(cgParameter);
						}
						break;
					}
					case EAGER_PARAMETER_VARIABLES: {
						ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
						for (@NonNull Variable asParameterVariable : PivotUtil.getOwnedParameters(asExpressionInOCL)) {
							CGParameter cgParameter = getCGParameter(asParameterVariable, null);
							cgParameters.add(cgParameter);
							declareEagerName(cgParameter);
						}
						break;
					}
					case EXECUTOR: {
						CGParameter cgParameter;
						NameResolution executorNameResolution = globalNameManager.getExecutorNameResolution();
						Parameter asExecutorParameter = basicGetExecutorParameter(asOperation);
						if (asExecutorParameter != null) {
							cgParameter = getExecutorParameter(asExecutorParameter);
						}
						else {
							cgParameter = createExecutorParameter();
						}
						assert cgParameter.basicGetNameResolution() == executorNameResolution;
						cgParameters.add(cgParameter);
						break;
					}
					case ID_RESOLVER: {
						NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
						CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID);
						CGParameter cgParameter = analyzer.createCGParameter(idResolverNameResolution, cgTypeId, true);
						cgParameter.setNonInvalid();
						cgParameter.setRequired(true);
						cgParameters.add(cgParameter);
						break;
					}
					case JUNIT_SELF: {
						ExpressionInOCL expressionInOCL = (ExpressionInOCL) asOperation.getBodyExpression();
						Variable contextVariable = expressionInOCL.getOwnedContext();
						CGParameter cgParameter = getCGParameter(contextVariable, (String)null);			// XXX getSelf ???
						cgParameter.setIsSelf(true);
						cgParameter.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// JUnit evaluate overrides
						cgParameter.setRequired(false);										//  self : Object[?]
						NameResolution selfNameResolution = globalNameManager.getSelfNameResolution();
						selfNameResolution.addCGElement(cgParameter);
						cgParameters.add(cgParameter);
						break;
					}
					case OPTIONAL_BODY_SELF: {
						CGParameter cgParameter = analyzer.getSelfParameter(this, PivotUtil.getOwnedContext((ExpressionInOCL) asOperation.getBodyExpression()));
						cgParameter.setRequired(false);
						cgParameters.add(cgParameter);
						break;
					}
					case OPTIONAL_SELF: {
						CGParameter cgParameter = getSelfParameter();
						cgParameter.setRequired(false);
						cgParameters.add(cgParameter);
						break;
					}
					case PARAMETERS: {
						for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
							CGParameter cgParameter = getCGParameter(asParameter, null);
							cgParameters.add(cgParameter);
						}
						break;
					}
					case SELF: {
						CGParameter cgParameter = getSelfParameter();
						cgParameters.add(cgParameter);
						break;
					}
					case THIS: {
						CGParameter cgParameter = getThisParameter();
						cgParameters.add(cgParameter);
						break;
					}
					case TYPE_ID: {
						CGParameter cgParameter = createTypeIdParameter();
						NameResolution typeidNameResolution = globalNameManager.getTypeIdNameResolution();
						assert cgParameter.basicGetNameResolution() == typeidNameResolution;//	executorNameResolution.addCGElement(cgParameter);
						cgParameters.add(cgParameter);
						break;
					}
					default: {
						throw new UnsupportedOperationException();
					}
				}
			}
		}
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull VariableDeclaration asVariable) {
		assert basicGetCGVariable(asVariable) == null;
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		analyzer.initAst(cgVariable, asVariable, false);
//		cgVariable.setAst(asVariable);
//		cgVariable.setTypeId(analyzer.getCGTypeId(asVariable.getTypeId()));
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

	protected @NonNull CGParameter createContextObjectParameter() {
		assert !isStatic;
		org.eclipse.ocl.pivot.Class asContextClass = classNameManager.getASClass(); //codeGenerator.getContextClass();
		NameResolution thisObjectName = globalNameManager.getContextObjectNameResolution();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asContextClass.getTypeId());
		CGParameter thisObjectParameter = analyzer.createCGParameter(thisObjectName, cgTypeId, true);
		thisObjectParameter.setIsThis(false);		// Do not use Java's 'this' spelling
		thisObjectParameter.setNonInvalid();
		thisObjectParameter.setRequired(true);
		return thisObjectParameter;
	}

	public @NonNull CGParameter createExecutorParameter() {
	//	assert executorIsParameter;
		NameResolution executorName = globalNameManager.getExecutorNameResolution();
		CGParameter executorParameter = analyzer.createCGParameter(executorName, analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID), true);
	//	executorParameter.setValueName(executorName);
		executorParameter.setNonInvalid();
	//	executorParameter.setRequired(true);
		return executorParameter;
	}

	protected @NonNull CGVariable createExecutorVariable() {
		CGNativeOperationCallExp executorInit = analyzer.createCGNativeOperationCallExp(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD));
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
		executorVariable.setRequired(true);
		executorNameResolution.addCGElement(executorVariable);
		return executorVariable;
	}

	protected @NonNull CGParameter createIdResolverParameter() {
		assert !isStatic;
		assert idResolverParameter == null;
		NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID);
		CGParameter idResolverParameter = analyzer.createCGParameter(idResolverNameResolution, cgTypeId, true);
		idResolverParameter.setNonInvalid();
		return idResolverParameter;
	}

	public @NonNull CGVariable createIdResolverVariable() {
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID);
		CGNativeOperationCallExp idResolverInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD));
		NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
		idResolverNameResolution.addCGElement(idResolverInit);
		idResolverInit.setTypeId(cgTypeId);
		idResolverInit.setCgThis(analyzer.createCGVariableExp(analyzer.getExecutorVariable(this)));
		idResolverInit.setRequired(true);
		idResolverInit.setInvalidating(false);
		CGVariable idResolverVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		idResolverVariable.setTypeId(cgTypeId);
		idResolverVariable.setInit(idResolverInit);
		idResolverVariable.setNonInvalid();
		idResolverVariable.setRequired(true);
		idResolverNameResolution.addCGElement(idResolverVariable);
		return idResolverVariable;
	}

	public @NonNull CGVariable createModelManagerVariable() {
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.MODEL_MANAGER_TYPE_ID);
		CGNativeOperationCallExp modelManagerInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD));
		NameResolution modelManagerNameResolution = globalNameManager.getModelManagerNameResolution();
		modelManagerNameResolution.addCGElement(modelManagerInit);
		modelManagerInit.setTypeId(cgTypeId);
		modelManagerInit.setCgThis(analyzer.createCGVariableExp(analyzer.getExecutorVariable(this)));
		modelManagerInit.setRequired(true);
		modelManagerInit.setInvalidating(false);
		CGVariable modelManagerVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		modelManagerVariable.setTypeId(cgTypeId);
		modelManagerVariable.setInit(modelManagerInit);
		modelManagerVariable.setNonInvalid();
		modelManagerVariable.setRequired(true);
		modelManagerNameResolution.addCGElement(modelManagerVariable);
		return modelManagerVariable;
	}

	protected @NonNull CGFinalVariable createQualifiedThisVariable() {
		NameResolution qualifiedThisNameResolution = globalNameManager.declareEagerName(null, classNameManager.getASClass().getName() + "_" + JavaConstants.THIS_NAME);
		CGFinalVariable qualifiedThisVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		qualifiedThisVariable.setTypeId(analyzer.getCGTypeId(classNameManager.getASClass().getTypeId()));
		qualifiedThisVariable.setInit(getThisParameter());
		qualifiedThisVariable.setNonInvalid();
		qualifiedThisVariable.setRequired(true);
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
			selfParameter.setRequired(true);
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
		//	selfParameter.setRequired(true);
			return selfParameter;
		}
		else {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ".createSelfParameter for " + cgScope.eClass().getName());
		}
	}

	public @NonNull CGVariable createStandardLibraryVariable() {
		CGNativeOperationCallExp standardLibraryInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD));
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
		standardLibraryVariable.setRequired(true);
		standardLibraryNameResolution.addCGElement(standardLibraryVariable);
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createThisParameter() {
		assert !isStatic;
		org.eclipse.ocl.pivot.Class asThisClass = classNameManager.getASClass();
		NameResolution thisName = globalNameManager.getThisNameResolution();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asThisClass.getTypeId());
		CGParameter thisParameter = analyzer.createCGParameter(thisName, cgTypeId, true);
		thisParameter.setIsThis(true);		// Use Java's 'this' spelling
		thisParameter.setNonInvalid();
		return thisParameter;
	}

	public @NonNull CGParameter createTypeIdParameter() {
		assert typeIdParameter == null;
		NameResolution typeIdNameResolution = globalNameManager.getTypeIdNameResolution();
		CGParameter typeIdParameter = analyzer.createCGParameter(typeIdNameResolution, analyzer.getCGTypeId(JavaConstants.TYPE_ID_TYPE_ID), true);
		typeIdParameter.setNonInvalid();
		return typeIdParameter;
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

	public @NonNull Parameter getBoxedValuesParameter(@NonNull Operation asOperation) {
		NameResolution boxedValuesResolution = globalNameManager.getBoxedValuesNameResolution();
		String boxedValuesName = boxedValuesResolution.getResolvedName();
		return ClassUtil.nonNullState(NameUtil.getNameable(asOperation.getOwnedParameters(), boxedValuesName));
	}

	public @NonNull CGExecutorType getCGExecutorType(@NonNull Type asType) {
		//	if (parent instanceof ExecutableNameManager) {
		//		return ((ExecutableNameManager)parent).getCGExecutorType(asType);
		//	}
		//
		//	It would be better to share multi-uses, but that requires the ownership to move from the calling
		//	context to a dependency-sequenced list of let-variables to wrap the whole usage.
		//
		//	No. There is no ownership so local cache should be easier.
		//
		//	Map<@NonNull Type, @NonNull CGExecutorType> asType2cgType2 = asType2cgType;
		//	if (asType2cgType2 == null) {
		//		asType2cgType = asType2cgType2 = new HashMap<>();
		//	}
		//	CGExecutorType cgExecutorType = asType2cgType2.get(asType);
		//	if (cgExecutorType == null) {
	//	TypeId typeId = asType.getTypeId();
		CGExecutorType cgExecutorType = CGModelFactory.eINSTANCE.createCGExecutorType();
		analyzer.initAst(cgExecutorType, asType);
	//	CGTypeId cgTypeId = analyzer.getCGTypeId(typeId);
	//	cgExecutorType.setAst(asType);
		getNameResolution(cgExecutorType);		// Needs idResolver so cannot be global
		//	cgExecutorType.setTypeId(analyzer.getCGTypeId(JavaConstants.CLASS_TYPE_ID));
	//	cgExecutorType.setTypeId(cgTypeId);
		cgExecutorType.getDependsOn().add(cgExecutorType.getTypeId());
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
		}
		return cgParameter;
	}

	@Override
	public @NonNull CGNamedElement getCGScope() {
		return cgScope;
	}

	public @NonNull CGVariable getCGVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = basicGetCGVariable(asVariable);
		if (cgVariable == null) {			// XXX should never happen ?? lazyGet
			assert false;				// XXX
			cgVariable = createCGVariable(asVariable);
			if (asVariable.isIsRequired()) {
				cgVariable.setNonInvalid();
				cgVariable.setRequired(true);
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

	public @NonNull CGParameter getContextObjectParameter() {
		assert !isStatic;
		CGParameter contextObjectParameter2 = contextObjectParameter;
		if (contextObjectParameter2 == null) {
			contextObjectParameter = contextObjectParameter2 = createContextObjectParameter();
		}
		return contextObjectParameter2;
	}

	@Deprecated		// ?? but is ther always an asExecutorParameter
	public @NonNull CGParameter getExecutorParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getExecutorParameter();
		}
		CGParameter executorVariable2 = (CGParameter)executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorParameter();
		}
		return executorVariable2;
	}

	public @NonNull CGParameter getExecutorParameter(@NonNull Parameter asExecutorParameter) {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getExecutorParameter(asExecutorParameter);
		}
		CGParameter executorVariable2 = (CGParameter)executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorParameter();
			analyzer.initAst(executorVariable2, asExecutorParameter, true);
		}
		else {
			assert executorVariable2.getAst() == asExecutorParameter;
		}
		return executorVariable2;
	}

	@Deprecated // XXX review wrt CallingConvention flexibility
	public @NonNull CGVariable getExecutorVariableInternal() {	// Invoked from CodeGenAnalyzer that overrides for JUnit support
	//	CGParameter executorParameter2 = executorParameter;
	//	if (executorParameter2 != null) {
	//		return executorParameter2;
	//	}
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getExecutorVariableInternal();
		}
	//	if (asScope instanceof CallExp) {
	//		return ((ExecutableNameManager)parent).getExecutorVariable();
	//	}
		CGVariable executorVariable2 = executorVariable;		// XXX redirect to executorParameter
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorVariable();
		}
		return executorVariable2;
	}

	public @NonNull CGParameter getIdResolverParameter() {
		assert !isStatic;
		assert idResolverParameter != null;
		return idResolverParameter;
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
			if (cgScope instanceof CGOperation) {
				NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
				for (@NonNull CGParameter cgParameter : CGUtil.getParameters((CGOperation)cgScope)) {
					if (cgParameter.basicGetNameResolution() == idResolverNameResolution) {
						idResolverVariable2 = cgParameter;
						break;
					}
				}
			}
			if (idResolverVariable2 == null) {
				idResolverVariable2 = createIdResolverVariable();
			}
			idResolverVariable = idResolverVariable2;
		}
		return idResolverVariable2;
	}

	public @NonNull CGIterator getIterator(@NonNull VariableDeclaration asVariable) {
		CGIterator cgIterator = (CGIterator)basicGetCGVariable(asVariable);
		if (cgIterator == null) {
			cgIterator = CGModelFactory.eINSTANCE.createCGIterator();
			analyzer.initAst(cgIterator, asVariable, true);
//			cgIterator.setAst(asVariable);
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

	public @NonNull CGParameter getThisParameter(@NonNull VariableDeclaration asParameter) {		// XXX Why with/without arg variants, never exercised
		CGParameter cgParameter = basicGetCGParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			globalNameManager.getThisNameResolution().addCGElement(cgParameter);
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			cgParameter.setIsSelf(true);
		}
		return cgParameter;
	}

	public @NonNull CGParameter getTypeIdParameter() {
		assert typeIdParameter != null;
		return typeIdParameter;
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

	public @NonNull CGVariable lazyGetCGVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = basicGetCGVariable(asVariable);
	// XXX	assert cgVariable == null;			 // caller must knowingly create to establish ownership
		if (cgVariable == null) {
			cgVariable = createCGVariable(asVariable);
			if (asVariable.isIsRequired()) {
				cgVariable.setNonInvalid();
				cgVariable.setRequired(true);
			}
		}
		return cgVariable;
	}

	public @NonNull CGValuedElement wrapLetVariables(@NonNull CGValuedElement cgTree) {
		CGVariable qualifiedThisVariable = basicGetQualifiedThisVariable();
		if ((qualifiedThisVariable != null) && !(qualifiedThisVariable instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, qualifiedThisVariable);
		}
		CGVariable standardLibraryVariable = basicGetStandardLibraryVariable();
		if ((standardLibraryVariable != null) && !(standardLibraryVariable instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, standardLibraryVariable);
		}
		CGVariable modelManagerVariable = basicGetModelManagerVariable();
		if ((modelManagerVariable != null) && !(modelManagerVariable instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, modelManagerVariable);
		}
		CGVariable idResolverVariable = basicGetIdResolverVariable();
		if ((idResolverVariable != null) && !(idResolverVariable instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, idResolverVariable);
		}
		CGVariable executorVariable = basicGetExecutorVariable();
		if ((executorVariable != null) && !(executorVariable instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, executorVariable);
		}
		return cgTree;
	}
}
