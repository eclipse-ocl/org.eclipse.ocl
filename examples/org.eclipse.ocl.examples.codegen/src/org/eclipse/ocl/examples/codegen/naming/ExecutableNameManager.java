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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.AbstractOperationCallingConvention.CGParameterStyle;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
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
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * An ExecutableNameManager supervises the parameter and variable names allocated within the scope of an Element with an executable body.
 */
public class ExecutableNameManager extends NestedNameManager
{
	protected final @NonNull ClassNameManager classNameManager;
	protected final @NonNull CGNamedElement cgScope;
	protected final @NonNull NamedElement asScope;
	protected final @Nullable TypedElement asOrigin;	// Only used to detect Property.isStatic
	protected final boolean isStatic;

	private /*@LazyNonNull*/ CGParameter boxedValuesParameter = null;		// Passed parameter spelled "boxedValues"
	private /*@LazyNonNull*/ CGParameter contextObjectParameter = null;		// Passed parameter spelled "contextObject" to distinguish unique evaluations
	private /*@LazyNonNull*/ CGParameter executorParameter = null;			// Passed parameter spelled "executor"
	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor parameter / cached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// Passed idResolver parameter / cache of executor.getIdResolver()
	private /*@LazyNonNull*/ CGParameter idResolverParameter = null;		// Passed parameter spelled "idResolver"
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// Cache of executor.getModelManager()
	private /*@LazyNonNull*/ CGFinalVariable qualifiedThisVariable = null;	// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// Cache of executor.getStandardVariable()
	private /*@LazyNonNull*/ CGParameter selfParameter = null;				// Passed parameter spelled "self"
	private /*@LazyNonNull*/ CGParameter thisParameter = null;				// A local orphan parameter spelled "this"
	private /*@LazyNonNull*/ CGParameter typeIdParameter = null;			// Passed parameter spelled "typeId"

	/**
	 * Mapping from an AS Variable to the CG Variable defined in this cgScope.
	 */
	private @NonNull Map<@NonNull VariableDeclaration, @NonNull CGVariable> asVariable2cgVariable = new HashMap<>();	// XXX Eliminate use CGA.as2cg

	public ExecutableNameManager(@NonNull ClassNameManager classNameManager, @NonNull NestedNameManager parentNameManager, @NonNull CGNamedElement cgScope, @Nullable TypedElement asOrigin) {
		super(classNameManager.getCodeGenerator(), parentNameManager, cgScope);
		this.classNameManager = classNameManager;
		this.cgScope = cgScope;
		this.asScope = CGUtil.getAST(cgScope);
		this.asOrigin = asOrigin;
		boolean staticFeature = (asScope instanceof Feature) && ((Feature)asScope).isIsStatic();
		this.isStatic = /*(asScope == null) ||*/ staticFeature;
		assert !(parent instanceof ExecutableNameManager) || (((ExecutableNameManager)parent).cgScope != cgScope);		// XXX
		assert !(cgScope instanceof CGClass) || (cgScope instanceof CGPackage);
		this.executorVariable = classNameManager.basicGetRootExecutorVariable();
	}

	public void addVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
		CGVariable old = asVariable2cgVariable.put(asVariable, cgVariable);
		assert old == null;
//		analyzer.addVariable(asVariable, cgVariable);
	}

	private @Nullable Parameter basicGetASParameter(@NonNull Operation asOperation, @NonNull String asName) {
		for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
			if (asName == asParameter.getName()) {	// NB == to only match the AS-specific singleton
				return asParameter;
			}
		}
		return null;
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

	public @Nullable CGVariable basicGetExecutorVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).basicGetExecutorVariable();
		}
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

	public void createCGConstraintParameters() {		// Constraint CallingConvention
	//	@NonNull CGParameterStyle @NonNull [] cgParameterStyles = AbstractOperationCallingConvention.CG_PARAMETER_STYLES_SELF_THIS_EAGER_PARAMETER_VARIABLES;
		CGConstraint cgConstraint = (CGConstraint)getCGScope();
		Constraint asConstraint = (Constraint)getASScope();
		ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asConstraint.getOwnedSpecification();
		assert asExpressionInOCL != null;
		Variable asContextVariable = asExpressionInOCL.getOwnedContext();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgConstraint);
	//	for (@NonNull CGParameterStyle cgParameterStyle : cgParameterStyles) {
	//		switch(cgParameterStyle) {
	/*			case SELF_THIS:*/ {
					assert selfParameter == null;
					assert thisParameter == null;
					assert asContextVariable != null;
					CGParameter cgParameter = lazyGetThisParameter(asContextVariable);
					cgParameter.setIsSelf(true);
					assert cgParameter.isIsThis();
					assert cgParameter.getAst() == asContextVariable;
					cgParameters.add(cgParameter);
					assert thisParameter == cgParameter;
					selfParameter = cgParameter;
	//				break;
				}
	/*			case EAGER_PARAMETER_VARIABLES:*/ {
					assert asExpressionInOCL != null;
					for (@NonNull Variable asParameterVariable : PivotUtil.getOwnedParameters(asExpressionInOCL)) {
						CGParameter cgParameter = lazyGetCGParameter(asParameterVariable);
						cgParameters.add(cgParameter);
						declareEagerName(cgParameter);
					}
	//				break;
				}
	//			default: {
	//				throw new UnsupportedOperationException("createCGConstraintParameter for " + cgParameterStyle);
	//			}
	//		}
	//	}
	}

	public void createCGOperationParameters(@NonNull CGParameterStyle @NonNull [] cgParameterStyles) {
		assert !(parent instanceof ExecutableNameManager);
		if (cgParameterStyles.length > 0) {
			CGOperation cgOperation = (CGOperation)getCGScope();
			Operation asOperation = (Operation)getASScope();
			ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
			Variable asContextVariable = asExpressionInOCL != null ? asExpressionInOCL.getOwnedContext() : null;
			List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
			for (@NonNull CGParameterStyle cgParameterStyle : cgParameterStyles) {
				boolean eagerNames = false;
				boolean isRequired = true;
				switch(cgParameterStyle) {
					case BOXED_VALUES: {
						assert boxedValuesParameter == null;
						Parameter asBoxedValuesParameter = getASParameter(asOperation, globalNameManager.getASBoxedValuesName());
						NameResolution nameResolution = globalNameManager.getBoxedValuesName();
						CGTypeId cgTypeId = analyzer.getCGTypeId(asBoxedValuesParameter.getTypeId());
						CGParameter cgParameter = analyzer.createCGParameter(nameResolution, cgTypeId, true);
						analyzer.initAst(cgParameter, asBoxedValuesParameter, true);
						cgParameters.add(cgParameter);
						boxedValuesParameter = cgParameter;
						break;
					}
					case EAGER_PARAMETERS:
						eagerNames = true;
					case PARAMETERS: {
						Iterable<? extends @NonNull VariableDeclaration> ownedParameters;
						if (asExpressionInOCL != null) {
							ownedParameters = PivotUtil.getOwnedParameters(asExpressionInOCL);
						}
						else {
							ownedParameters = PivotUtil.getOwnedParameters(asOperation);
						}
						for (@NonNull VariableDeclaration asParameterVariable : ownedParameters) {
							CGParameter cgParameter = lazyGetCGParameter(asParameterVariable);
							cgParameters.add(cgParameter);
							if (eagerNames) {
								declareEagerName(cgParameter);
							}
						}
						break;
					}
					case EXECUTOR: {
						assert executorParameter == null;
						assert executorVariable == null;
						NameResolution nameResolution = globalNameManager.getExecutorName();
						Parameter asExecutorParameter = basicGetASParameter(asOperation, globalNameManager.getASExecutorName());
						CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID);
						CGParameter cgParameter = analyzer.createCGParameter(nameResolution, cgTypeId, true);
						cgParameter.setNonInvalid();
						if (asExecutorParameter != null) {			// Distinct style
							analyzer.initAst(cgParameter, asExecutorParameter, true);
						}
						cgParameters.add(cgParameter);
						executorVariable = executorParameter = cgParameter;
						break;
					}
					case ID_RESOLVER: {
						assert idResolverParameter == null;
						assert idResolverVariable == null;
						NameResolution nameResolution = globalNameManager.getIdResolverName();
						CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID);
						CGParameter cgParameter = analyzer.createCGParameter(nameResolution, cgTypeId, true);
						cgParameter.setNonInvalid();
						cgParameters.add(cgParameter);
						idResolverVariable = idResolverParameter = cgParameter;
						break;
					}
					case JUNIT_SELF: {
						assert selfParameter == null;
						assert asContextVariable != null;
						CGParameter cgParameter = lazyGetCGParameter(asContextVariable);			// XXX getSelf ???
						cgParameter.setIsSelf(true);
						cgParameter.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// JUnit evaluate overrides
						cgParameter.setRequired(false);										//  self : Object[?]
						NameResolution selfNameResolution = globalNameManager.getSelfName();
						selfNameResolution.addCGElement(cgParameter);
						cgParameters.add(cgParameter);
						break;
					}
					case OPTIONAL_SELF:
						isRequired = false;
					case SELF: {
						assert selfParameter == null;
						CGParameter cgParameter = createSelfParameter();
						if (asContextVariable != null) {
							cgParameter.setAst(asContextVariable);
							addVariable(asContextVariable, cgParameter);
						}
						cgParameter.setRequired(isRequired);
						cgParameters.add(cgParameter);
						assert selfParameter == cgParameter;
						break;
					}
					case SELF_THIS: {
						assert selfParameter == null;
						assert thisParameter == null;
						assert asContextVariable != null;
						CGParameter cgParameter = lazyGetThisParameter(asContextVariable);
						cgParameter.setIsSelf(true);
						assert cgParameter.isIsThis();
						assert cgParameter.getAst() == asContextVariable;
						cgParameters.add(cgParameter);
						assert thisParameter == cgParameter;
						selfParameter = cgParameter;
						break;
					}
					case THIS: {
						assert thisParameter == null;
						CGParameter cgParameter = lazyGetThisParameter();
					//	cgParameters.add(cgParameter);	-- 'this' is a convenience for a global - not a passed parameter
						assert thisParameter == cgParameter;
						break;
					}
					case TYPE_ID: {
						assert typeIdParameter == null;
						NameResolution nameResolution = globalNameManager.getTypeIdName();
						CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.TYPE_ID_TYPE_ID);
						CGParameter cgParameter = analyzer.createCGParameter(nameResolution, cgTypeId, true);
						cgParameter.setNonInvalid();
						cgParameters.add(cgParameter);
						typeIdParameter = cgParameter;
						break;
					}
					default: {
						throw new UnsupportedOperationException("createCGOperationParameter for " + cgParameterStyle);
					}
				}
			}
		}
	}

	public void createCGPropertyParameter(@NonNull CGParameterStyle cgParameterStyle) {
		assert !(parent instanceof ExecutableNameManager);
		Property asProperty = (Property)getASScope();
		ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asProperty.getOwnedExpression();
		Variable asContextVariable = asExpressionInOCL != null ? asExpressionInOCL.getOwnedContext() : null;
		switch(cgParameterStyle) {
			case SELF: {
				assert selfParameter == null;
				CGParameter cgParameter = createSelfParameter();
				assert selfParameter == cgParameter;
				break;
			}
			case SELF_THIS: {
				assert selfParameter == null;
				assert thisParameter == null;
				assert asContextVariable != null;
				CGParameter cgParameter = lazyGetThisParameter(asContextVariable);
				cgParameter.setIsSelf(true);
				assert cgParameter.isIsThis();
				assert cgParameter.getAst() == asContextVariable;
				assert thisParameter == cgParameter;
				selfParameter = cgParameter;
				break;
			}
			default: {
				throw new UnsupportedOperationException("createCGPropertyParameter for " + cgParameterStyle);
			}
		}
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull VariableDeclaration asVariable) {
		assert basicGetCGVariable(asVariable) == null;
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		analyzer.initAst(cgVariable, asVariable, false);
		addVariable(asVariable, cgVariable);
		return cgVariable;
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull CGValuedElement cgInit) {
		CGFinalVariable cgVariable = analyzer.createCGFinalVariable(null, CGUtil.getTypeId(cgInit), cgInit.isRequired());
		cgVariable.setAst(cgInit.getAst());
		cgVariable.setInit(cgInit);
		return cgVariable;
	}

	private @NonNull CGParameter createContextObjectParameter() {
		assert !isStatic;
		org.eclipse.ocl.pivot.Class asContextClass = classNameManager.getASClass(); //codeGenerator.getContextClass();
		NameResolution nameResolution = globalNameManager.getContextObjectName();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asContextClass.getTypeId());
		CGParameter cgParameter = analyzer.createCGParameter(nameResolution, cgTypeId, true);
		cgParameter.setIsThis(false);		// Do not use Java's 'this' spelling
		cgParameter.setNonInvalid();
		return cgParameter;
	}

	protected @NonNull CGVariable createExecutorVariable() {		// XXX QVTi overrides to use global rootExecutor
		CGNativeOperationCallExp executorInit = analyzer.createCGNativeOperationCallExp(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD));
		NameResolution nameResolution = globalNameManager.getExecutorName();
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID);
		executorInit.setTypeId(cgTypeId);
		CGValuedElement contextParameter;
		if (!isStatic) {
			contextParameter = analyzer.createCGVariableExp(lazyGetThisParameter());
		}
		else {
			CGParameter selfParameter = basicGetSelfParameter();
			if (selfParameter != null) {
				contextParameter = analyzer.createCGVariableExp(selfParameter);
			}
			else {
				contextParameter = analyzer.createCGNull();
			}
		}
		executorInit.getArguments().add(contextParameter);
		executorInit.setRequired(true);
		executorInit.setInvalidating(false);
		CGVariable executorVariable = analyzer.createCGFinalVariable(nameResolution, cgTypeId, true);
		executorVariable.setInit(executorInit);
		executorVariable.setNonInvalid();
		return executorVariable;
	}

	private @NonNull CGVariable createIdResolverVariable() {
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID);
		CGNativeOperationCallExp idResolverInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD));
		NameResolution idResolverNameResolution = globalNameManager.getIdResolverName();
		idResolverNameResolution.addCGElement(idResolverInit);
		idResolverInit.setTypeId(cgTypeId);
		idResolverInit.setCgThis(analyzer.createCGVariableExp(lazyGetExecutorVariable()));
		idResolverInit.setRequired(true);
		idResolverInit.setInvalidating(false);
		CGVariable idResolverVariable = analyzer.createCGFinalVariable(idResolverNameResolution, cgTypeId, true);
		idResolverVariable.setInit(idResolverInit);
		idResolverVariable.setNonInvalid();
		return idResolverVariable;
	}

	private @NonNull CGVariable createModelManagerVariable() {
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.MODEL_MANAGER_TYPE_ID);
		CGNativeOperationCallExp modelManagerInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD));
		NameResolution modelManagerNameResolution = globalNameManager.getModelManagerName();
		modelManagerNameResolution.addCGElement(modelManagerInit);
		modelManagerInit.setTypeId(cgTypeId);
		modelManagerInit.setCgThis(analyzer.createCGVariableExp(lazyGetExecutorVariable()));
		modelManagerInit.setRequired(true);
		modelManagerInit.setInvalidating(false);
		CGVariable modelManagerVariable = analyzer.createCGFinalVariable(modelManagerNameResolution, cgTypeId, true);
		modelManagerVariable.setInit(modelManagerInit);
		modelManagerVariable.setNonInvalid();
		return modelManagerVariable;
	}

	protected @NonNull CGFinalVariable createQualifiedThisVariable() {		// QVTi overides to use global context
		NameResolution qualifiedThisNameResolution = globalNameManager.declareEagerName(null, classNameManager.getASClass().getName() + "_" + JavaConstants.THIS_NAME);
		CGTypeId cgTypeId = analyzer.getCGTypeId(classNameManager.getASClass().getTypeId());
		CGFinalVariable qualifiedThisVariable = analyzer.createCGFinalVariable(qualifiedThisNameResolution, cgTypeId, true);
		qualifiedThisVariable.setInit(lazyGetThisParameter());
		qualifiedThisVariable.setNonInvalid();
		return qualifiedThisVariable;
	}

	private @NonNull CGParameter createSelfParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).createSelfParameter();
		}
		assert selfParameter == null;
		NameResolution selfName = globalNameManager.getSelfName();
		CGTypeId cgTypeId = analyzer.getCGTypeId(classNameManager.getASClass().getTypeId());
		boolean sourceMayBeNull = false;
		if (cgScope instanceof CGForeignProperty) {
				sourceMayBeNull = false;
		}
		else if (cgScope instanceof CGConstrainedProperty) {		// XXX from caller
				sourceMayBeNull = false;
		}
		else if (cgScope instanceof CGOperation) {
			Operation referredOperation = CGUtil.getAST(((CGOperation)cgScope));
			OperationId operationId = referredOperation.getOperationId();
			sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
		}
		else {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ".createSelfParameter for " + cgScope.eClass().getName());
		}
		CGParameter cgParameter = analyzer.createCGParameter(selfName, cgTypeId, !sourceMayBeNull);
		cgParameter.setIsSelf(true);
		cgParameter.setNonInvalid();
		selfParameter = cgParameter;
		return cgParameter;
	}

	private @NonNull CGVariable createStandardLibraryVariable() {
		CGNativeOperationCallExp standardLibraryInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD, SupportOperationCallingConvention.getInstance(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD));
		NameResolution nameResolution = globalNameManager.getStandardLibraryVariableName();
		nameResolution.addCGElement(standardLibraryInit);
		CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID);
		standardLibraryInit.setTypeId(cgTypeId);
		standardLibraryInit.setCgThis(analyzer.createCGVariableExp(lazyGetExecutorVariable()));
		standardLibraryInit.setRequired(true);
		standardLibraryInit.setInvalidating(false);
		CGVariable standardLibraryVariable = analyzer.createCGFinalVariable(nameResolution, cgTypeId, true);
		standardLibraryVariable.setInit(standardLibraryInit);
		standardLibraryVariable.setNonInvalid();
		return standardLibraryVariable;
	}

	private @NonNull CGParameter createThisParameter() {
		assert !isStatic;
		org.eclipse.ocl.pivot.Class asThisClass = classNameManager.getASClass();
		NameResolution nameResolution = globalNameManager.getThisName();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asThisClass.getTypeId());
		CGParameter cgParameter = analyzer.createCGParameter(nameResolution, cgTypeId, true);
		cgParameter.setIsThis(true);		// Use Java's 'this' spelling
		cgParameter.setNonInvalid();
		return cgParameter;
	}

	public @NonNull TypedElement getASOrigin() {
		return ClassUtil.nonNullState(asOrigin);
	}

	private @NonNull Parameter getASParameter(@NonNull Operation asOperation, @NonNull String asName) {
		Parameter asParameter = basicGetASParameter(asOperation, asName);
		if (asParameter != null) {
			return asParameter;
		}
		throw new IllegalStateException("Missing " + asName + " AS Parameter for " + asOperation);
	}

	@Override
	public @NonNull NamedElement getASScope() {
		return asScope;
	}

	public @Nullable CGValuedElement getBody() {		// QVTi extends
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

	public @NonNull CGParameter getBoxedValuesParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getBoxedValuesParameter();
		}
		assert boxedValuesParameter != null;
		return boxedValuesParameter;
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
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getClassParentNameManager();
		}
		return classNameManager;
	}

	public @NonNull CGParameter getIdResolverParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getIdResolverParameter();
		}
		assert !isStatic;
		assert idResolverParameter != null;
		return idResolverParameter;
	}

	public @NonNull ExecutableNameManager getRootExecutableNameManager() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getRootExecutableNameManager();
		}
		return this;
	}

	public @NonNull CGParameter getSelfParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getSelfParameter();
		}
		assert selfParameter != null;
		return selfParameter;
	}

	public @NonNull CGParameter getTypeIdParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).getTypeIdParameter();
		}
		assert typeIdParameter != null;
		return typeIdParameter;
	}

	public @NonNull CGParameter lazyGetCGParameter(@NonNull VariableDeclaration asParameter) {
		CGParameter cgParameter = basicGetCGParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = analyzer.createCGParameter(asParameter);
		/*	if (explicitName != null) {
				assert explicitName.equals(asParameter.getName());
				Operation asOperation = PivotUtil.getContainingOperation(asParameter);
				Constraint asConstraint = PivotUtil.getContainingConstraint(asParameter);
				assert ((asOperation != null) && (asOperation.getESObject() instanceof EOperation)) || ((asConstraint != null) && (asConstraint.getESObject() instanceof EOperation));
			} */
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
		}
		return cgParameter;
	}

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

	public @NonNull CGVariable lazyGetExecutorVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetExecutorVariable();
		}
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorVariable();
		}
		return executorVariable2;
	}

	public @NonNull CGVariable lazyGetIdResolverVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetIdResolverVariable();
		}
		CGVariable idResolverVariable2 = idResolverVariable;
		if (idResolverVariable2 == null) {
			idResolverVariable2 = idResolverVariable = createIdResolverVariable();
		}
		return idResolverVariable2;
	}

	public @NonNull CGIterator lazyGetIterator(@NonNull VariableDeclaration asVariable) {
		CGIterator cgIterator = (CGIterator)basicGetCGVariable(asVariable);
		if (cgIterator == null) {
			cgIterator = CGModelFactory.eINSTANCE.createCGIterator();
			analyzer.initAst(cgIterator, asVariable, true);
			cgIterator.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// FIXME Java-specific type of polymorphic operation parameter
			addVariable(asVariable, cgIterator);
		}
		return cgIterator;
	}

	public @NonNull CGVariable lazyGetModelManagerVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetModelManagerVariable();
		}
		CGVariable modelManagerVariable2 = modelManagerVariable;
		if (modelManagerVariable2 == null) {
			modelManagerVariable = modelManagerVariable2 = createModelManagerVariable();
		}
		return modelManagerVariable2;
	}

	public @NonNull CGFinalVariable lazyGetQualifiedThisVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetQualifiedThisVariable();
		}
		CGFinalVariable qualifiedThisVariable2 = qualifiedThisVariable;
		if (qualifiedThisVariable2 == null) {
			qualifiedThisVariable = qualifiedThisVariable2 = createQualifiedThisVariable();
		}
		return qualifiedThisVariable2;
	}

	/**
	 * Create the 'self' CG parameter unless already created with a known AS parameter.
	 * This can update a previously created 'this'parameter with anAS origin.
	 */
	public @NonNull CGParameter lazyGetSelfParameter(@NonNull VariableDeclaration asParameter) {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetSelfParameter(asParameter);
		}
		CGParameter cgParameter = selfParameter;
		if (cgParameter == null) {
			selfParameter = cgParameter = createThisParameter();		// XXX typo ???
		}
		if (cgParameter.getAst() == null) {
			cgParameter.setAst(asParameter);
			addVariable(asParameter, cgParameter);
		}
		else {
			assert cgParameter.getAst() == asParameter;
		}
		return cgParameter;
	}

	public @NonNull CGVariable lazyGetStandardLibraryVariable() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetStandardLibraryVariable();
		}
		CGVariable standardLibraryVariable2 = standardLibraryVariable;
		if (standardLibraryVariable2 == null) {
			standardLibraryVariable = standardLibraryVariable2 = createStandardLibraryVariable();
		}
		return standardLibraryVariable2;
	}

	/**
	 * Create the 'this' CG parameter unless already created (without any known AS parameter).
	 */
	public @NonNull CGParameter lazyGetThisParameter() {
		if (parent instanceof ExecutableNameManager) {
			return ((ExecutableNameManager)parent).lazyGetThisParameter();
		}
		assert !isStatic;
		CGParameter thisParameter2 = thisParameter;
		if (thisParameter2 == null) {
			thisParameter = thisParameter2 = createThisParameter();
		}
		return thisParameter2;
	}

	/**
	 * Create the 'this' CG parameter unless already created with a known AS parameter.
	 * This can update a previously created 'this'parameter with anAS origin.
	 */
	public @NonNull CGParameter lazyGetThisParameter(@NonNull VariableDeclaration asParameter) {		// XXX Why with/without arg variants, never exercised
		assert !(parent instanceof ExecutableNameManager);
		CGParameter cgParameter = lazyGetThisParameter();
		org.eclipse.ocl.pivot.Class asThisClass = classNameManager.getASClass();
		CGTypeId cgTypeId2 = analyzer.getCGTypeId(asThisClass.getTypeId());
		CGTypeId cgTypeId3 = cgParameter.getTypeId();
		assert cgTypeId3 == cgTypeId2;
		if (cgParameter.getAst() == null) {
			cgParameter.setAst(asParameter);
			addVariable(asParameter, cgParameter);
		}
		else {
			assert cgParameter.getAst() == asParameter;
		}
		return cgParameter;
	}

	public @NonNull CGValuedElement wrapLetVariables(@NonNull CGValuedElement cgTree) {
		assert !(parent instanceof ExecutableNameManager);
		CGVariable qualifiedThisVariable2 = qualifiedThisVariable;
		if (qualifiedThisVariable2 != null) {
			assert !(qualifiedThisVariable2 instanceof CGParameter);
			cgTree = globalNameManager.rewriteAsLet(cgTree, qualifiedThisVariable2);
		}
		CGVariable standardLibraryVariable2 = standardLibraryVariable;
		if (standardLibraryVariable2 != null) {
			assert !(standardLibraryVariable2 instanceof CGParameter);
			cgTree = globalNameManager.rewriteAsLet(cgTree, standardLibraryVariable2);
		}
		CGVariable modelManagerVariable2 = modelManagerVariable;
		if (modelManagerVariable2 != null) {
			assert !(modelManagerVariable2 instanceof CGParameter);
			cgTree = globalNameManager.rewriteAsLet(cgTree, modelManagerVariable2);
		}
		CGVariable idResolverVariable2 = idResolverVariable;
		if ((idResolverVariable2 != null) && !(idResolverVariable2 instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, idResolverVariable2);
		}
		CGVariable executorVariable2 = executorVariable;
		if ((executorVariable2 != null) && !(executorVariable2 instanceof CGParameter)) {
			cgTree = globalNameManager.rewriteAsLet(cgTree, executorVariable2);
		}
		return cgTree;
	}
}
