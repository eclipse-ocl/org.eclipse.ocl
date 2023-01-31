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
package org.eclipse.ocl.examples.codegen.calling;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  AbstractOperationCallingConvention defines the default support for an operation declaration or call.
 */
public abstract class AbstractOperationCallingConvention extends AbstractCallingConvention implements OperationCallingConvention
{
	protected enum ASContextVariableStyle {
		SELF,					// The context variable is the original self and is called contextObject
		THIS					// The context variable is the Java this
	}

	protected enum ASParameterStyle {
		BOXED_VALUES_ALL,		// The next parameter is the array of original parameters passed as a boxed array of required parameters if all required
		BOXED_VALUES_OPTIONAL,	// The next parameter is the array of original parameters passed as a boxed array of optional parameters
		EXECUTOR,				// The next parameter is the executor
		PARAMETERS,				// The next parameters are replicated from the original in the new operation
		SELF					// The next parameter is the original self
	}

	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES = new @NonNull ASParameterStyle[] {};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_BOXED_VALUES_ALL = new @NonNull ASParameterStyle[] {ASParameterStyle.BOXED_VALUES_ALL};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_BOXED_VALUES_OPTIONAL = new @NonNull ASParameterStyle[] {ASParameterStyle.BOXED_VALUES_OPTIONAL};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_EXECUTOR = new @NonNull ASParameterStyle[] {ASParameterStyle.EXECUTOR};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_EXECUTOR_BOXED_VALUES_ALL = new @NonNull ASParameterStyle[] {ASParameterStyle.EXECUTOR, ASParameterStyle.BOXED_VALUES_ALL};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_EXECUTOR_BOXED_VALUES_OPTIONAL = new @NonNull ASParameterStyle[] {ASParameterStyle.EXECUTOR, ASParameterStyle.BOXED_VALUES_OPTIONAL};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_EXECUTOR_SELF = new @NonNull ASParameterStyle[] {ASParameterStyle.EXECUTOR, ASParameterStyle.SELF};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_PARAMETERS = new @NonNull ASParameterStyle[] {ASParameterStyle.PARAMETERS};
	protected static final @NonNull ASParameterStyle @NonNull [] AS_PARAMETER_STYLES_SELF_PARAMETERS = new @NonNull ASParameterStyle[] {ASParameterStyle.SELF, ASParameterStyle.PARAMETERS};

	protected enum ASParameterVariableStyle {
		ID_RESOLVER,			// The next parameter variable is the idResolver
		SELF,					// The next parameter variable is the passed original OCL self
		BOXED_VALUES			// The next parameter variables are split from the passed parameter array
	}

	protected static final @NonNull ASParameterVariableStyle @NonNull [] AS_PARAMETER_VARIABLE_STYLES = new @NonNull ASParameterVariableStyle[] {};
	protected static final @NonNull ASParameterVariableStyle @NonNull [] AS_PARAMETER_VARIABLE_STYLES_ID_RESOLVER_SELF_BOXED_VALUES = new @NonNull ASParameterVariableStyle[] {ASParameterVariableStyle.ID_RESOLVER, ASParameterVariableStyle.SELF, ASParameterVariableStyle.BOXED_VALUES};
	protected static final @NonNull ASParameterVariableStyle @NonNull [] AS_PARAMETER_VARIABLE_STYLES_SELF = new @NonNull ASParameterVariableStyle[] {ASParameterVariableStyle.SELF};

	protected enum ASResultStyle {
		BOOLEAN,			// The result type is precisely Boolean
		RESULT,				// The result type is that of the original AS operation.
		VOID				// There is no result type
	}

	protected enum CGParameterStyle {
		BODY_SELF,				// The next parameter variables is the non-null self of the ExpressionInOCL
		CONTEXT_OBJECT,			// The next parameter variable is the root context object
		EXECUTOR,				// The next parameter variable is the executor
		ID_RESOLVER,			// The next parameter variable is the idResolver
		BOXED_VALUES,			// The next parameter variable is the parameter array
		EAGER_PARAMETERS,		// The next parameter variables are copied and their names are reserved eagerly
		EAGER_PARAMETER_VARIABLES,	// The next parameter variables are copied and their names are reserved eagerly
		JUNIT_SELF,				// The next parameter variables is the original self for a JUnit test
		OPTIONAL_BODY_SELF,		// The next parameter variables is the nullable self of the ExpressionInOCL
		OPTIONAL_SELF,			// The next parameter variables is the original nullable self
		PARAMETERS,				// The next parameter variables are copied
		SELF,					// The next parameter variables is the original non-null self
		THIS,					// The next parameter variables is the Java this
		TYPE_ID					// The next parameter variable is the return type id
	}

	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES = new @NonNull CGParameterStyle[]{};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_BODY_SELF_EAGER_PARAMETER_VARIABLES = new @NonNull CGParameterStyle[]{CGParameterStyle.BODY_SELF, CGParameterStyle.EAGER_PARAMETER_VARIABLES};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_BODY_SELF_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.BODY_SELF, CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_BOXED_VALUES = new @NonNull CGParameterStyle[]{CGParameterStyle.BOXED_VALUES};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_CONTEXT_OBJECT_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.CONTEXT_OBJECT, CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EXECUTOR = new @NonNull CGParameterStyle[]{CGParameterStyle.EXECUTOR};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EXECUTOR_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.EXECUTOR, CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EXECUTOR_SELF = new @NonNull CGParameterStyle[]{CGParameterStyle.EXECUTOR, CGParameterStyle.SELF};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EXECUTOR_SELF_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.EXECUTOR, CGParameterStyle.SELF, CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EXECUTOR_TYPE_ID_JUNIT_SELF_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.EXECUTOR, CGParameterStyle.TYPE_ID, CGParameterStyle.JUNIT_SELF, CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EXECUTOR_TYPE_ID_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.EXECUTOR, CGParameterStyle.TYPE_ID, CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_ID_RESOLVER_BOXED_VALUES = new @NonNull CGParameterStyle[]{CGParameterStyle.ID_RESOLVER, CGParameterStyle.BOXED_VALUES};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EAGER_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_EAGER_PARAMETER_VARIABLES = new @NonNull CGParameterStyle[]{CGParameterStyle.EAGER_PARAMETER_VARIABLES};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.EAGER_PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_SELF = new @NonNull CGParameterStyle[]{CGParameterStyle.SELF};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_SELF_EAGER_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.SELF, CGParameterStyle.EAGER_PARAMETERS};
	protected static final @NonNull CGParameterStyle @NonNull [] CG_PARAMETER_STYLES_SELF_PARAMETERS = new @NonNull CGParameterStyle[]{CGParameterStyle.SELF, CGParameterStyle.PARAMETERS};

	protected void addExpressionInOCLParameters(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull ExpressionInOCL expressionInOCL) {
		ExecutableNameManager operationNameManager = analyzer.getGlobalNameManager().useOperationNameManager(cgOperation);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		Variable contextVariable = expressionInOCL.getOwnedContext();
	//	assert isStatic(cgOperation) == (contextVariable == null);
		if (contextVariable != null) {
			cgParameters.add(analyzer.getSelfParameter(operationNameManager, contextVariable));
		}
		boolean hasExternalNames = cgOperation instanceof CGEcoreOperation;		// Ecore has genmodel-defined names
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
			String name = hasExternalNames ? parameterVariable.getName() : null;
			CGParameter cgParameter = operationNameManager.getCGParameter(parameterVariable, name);
			cgParameters.add(cgParameter);
		}
	}

	protected void addTypeIdArgument(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp, @NonNull TypeId asTypeId) {
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asTypeId);
		cgArguments.add(analyzer.createCGConstantExp(cgTypeId));
	}

	protected void appendBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGValuedElement body) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.append(" {\n");
		js.pushIndentation(null);
		cg2javaVisitor.appendReturn(body);
		js.popIndentation();
		js.append("}\n");
	}

	protected void appendCommentWithOCL(@NonNull  JavaStream js, @NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		LanguageExpression expressionInOCL = asOperation.getBodyExpression();
		String title = PrettyPrinter.printName(asOperation);
		js.appendCommentWithOCL(title + "\n", expressionInOCL);
	}

	protected void appendParameterList(@NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(")");
	}

	protected void assertCGParameterStyles(@NonNull CGParameterStyle @NonNull [] cgParameterStyles, @NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		@NonNull CGParameterStyle@NonNull [] cgParameterStyles2 = getCGParameterStyles(operationNameManager, bodyExpression);
		if (cgParameterStyles != cgParameterStyles2) {
			cgParameterStyles2 = getCGParameterStyles(operationNameManager, bodyExpression);
		}
		assert cgParameterStyles == cgParameterStyles2;
	}

	protected @Nullable Parameter basicGetExecutorParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		NameResolution executorResolution = analyzer.getGlobalNameManager().getExecutorNameResolution();
		String executorName = executorResolution.getResolvedName();
		return NameUtil.getNameable(asOperation.getOwnedParameters(), executorName);
	}

	protected @NonNull ExpressionInOCL createASExpressionInOCL(@NonNull CodeGenAnalyzer analyzer, @NonNull Feature asOriginalFeature,
			@NonNull ASContextVariableStyle contextStyle, @NonNull ASParameterVariableStyle... parameterStyles) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
		PivotHelper asHelper = codeGenerator.getASHelper();
		org.eclipse.ocl.pivot.@NonNull Class asClass = PivotUtil.getOwningClass(asOriginalFeature);
		ExpressionInOCL asExpressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
		ParameterVariable asContextVariable;
		switch (contextStyle) {
			case SELF: {
				String selfName = globalNameManager.getSelfNameResolution().getResolvedName();
				asContextVariable = asHelper.createParameterVariable(selfName, asClass, true);
				break;
			}
			case THIS: {
				asContextVariable = createThisParameterVariable(analyzer);
				break;
			}
			default: {
				throw new UnsupportedOperationException();
			}
		}
		asExpressionInOCL.setOwnedContext(asContextVariable);
		if (parameterStyles.length > 0) {
			List<Variable> asParameterVariables = asExpressionInOCL.getOwnedParameters();
			for (ASParameterVariableStyle parameterStyle : parameterStyles) {
				ParameterVariable asParameterVariable;
				switch (parameterStyle) {
					case ID_RESOLVER: {
						LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
						String idResolverName = globalNameManager.getIdResolverNameResolution().getResolvedName();
						Class idResolverClass = jLanguageSupport.getNativeClass(IdResolver.class);
						asParameterVariable = asHelper.createParameterVariable(idResolverName, idResolverClass, true);
						asParameterVariables.add(asParameterVariable);
						break;
					}
					case SELF: {
						String selfName = globalNameManager.getSelfNameResolution().getResolvedName();
						asParameterVariable = asHelper.createParameterVariable(selfName, asClass, true);
						asParameterVariables.add(asParameterVariable);
						break;
					}
					case BOXED_VALUES: {
						@NonNull Operation asOriginalOperation = (Operation) asOriginalFeature;
						for (@NonNull Parameter asOriginalParameter : PivotUtilInternal.getOwnedParametersList(asOriginalOperation)) {
							asParameterVariable = asHelper.createParameterVariable(asOriginalParameter);
							asParameterVariable.setRepresentedParameter(asOriginalParameter);
							asParameterVariables.add(asParameterVariable);
						}
						break;
					}
					default: {
						throw new UnsupportedOperationException();
					}
				}
			}
		}
		return asExpressionInOCL;
	}

	/**
	 * Create the declaration for an AS Operation within asClass in support of asOriginalOperation.
	 * The declaration las a name, result and parameters configured by resultStyleOrClass and parameterStyles.
	 * The resultStyleOrClass may be an explicit class or a ResultStyle algorithm.
	 */
	protected @NonNull Operation createASOperationDeclaration(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull TypedElement asOrigin,
			@NonNull String name, @NonNull Object resultStyleOrClass) {
		@NonNull ASParameterStyle[] asParameterStyles = getASParameterStyles(asOrigin);
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Type asReturnType;
		boolean isRequired;
		if (resultStyleOrClass instanceof org.eclipse.ocl.pivot.Class) {
			asReturnType = (org.eclipse.ocl.pivot.Class)resultStyleOrClass;
			isRequired = true;
		}
		else if (resultStyleOrClass instanceof TypedElement) {
			TypedElement asTypedElement = (TypedElement)resultStyleOrClass;
			asReturnType = PivotUtil.getType(asTypedElement);
			isRequired = asTypedElement.isIsRequired();
		}
		else {
			ASResultStyle resultStyle = (ASResultStyle)resultStyleOrClass;
			switch (resultStyle) {
				case BOOLEAN: {
					EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
					asReturnType = environmentFactory.getStandardLibrary().getBooleanType();
					isRequired = true;
					break;
				}
				case RESULT: {
					asReturnType = PivotUtil.getType(asOrigin);
					isRequired = asOrigin.isIsRequired();
					break;
				}
				case VOID: {
					EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
					asReturnType = environmentFactory.getStandardLibrary().getOclVoidType();
					isRequired = true;
					break;
				}
				default: {
					throw new UnsupportedOperationException();
				}
			}
		}
		Operation asOperation = PivotUtil.createOperation(name, asReturnType, null, null);
		asOperation.setIsRequired(isRequired);
		asClass.getOwnedOperations().add(asOperation);
		if (asParameterStyles.length > 0) {
			List<Parameter> asParameters = asOperation.getOwnedParameters();
			for (@NonNull ASParameterStyle asParameterStyle : asParameterStyles) {
				switch (asParameterStyle) {
					case BOXED_VALUES_OPTIONAL: {
						Parameter asParameter = createBoxedValuesParameter(codeGenerator, false);
						asParameters.add(asParameter);
						break;
					}
					case BOXED_VALUES_ALL: {
						@NonNull Operation asOriginalOperation = (Operation)asOrigin;
						Parameter asParameter = createBoxedValuesParameter(codeGenerator, PivotUtil.allParametersRequired(asOriginalOperation));
						asParameters.add(asParameter);
						break;
					}
					case EXECUTOR: {
						NameResolution executorResolution = codeGenerator.getGlobalNameManager().getExecutorNameResolution();
						String executorName = executorResolution.getResolvedName();
						LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
						org.eclipse.ocl.pivot.Class executorType = jLanguageSupport.getNativeClass(Executor.class);
						Parameter asParameter = PivotUtil.createParameter(executorName, executorType, true);
						asParameters.add(asParameter);
						break;
					}
					case PARAMETERS: {
						@NonNull Operation asOriginalOperation = (Operation)asOrigin;
						for (@NonNull Parameter asOriginalParameter : PivotUtil.getOwnedParameters(asOriginalOperation)) {
							String parameterName = PivotUtil.getName(asOriginalParameter);
							Type parameterType = PivotUtil.getType(asOriginalParameter);
							boolean parameterIsRequired = asOriginalParameter.isIsRequired();
							Parameter asParameter = PivotUtil.createParameter(parameterName, parameterType, parameterIsRequired);
							asParameters.add(asParameter);
						}
						break;
					}
					case SELF: {
						@NonNull Feature asOriginalFeature = (Feature)asOrigin;
						GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
						String selfName = globalNameManager.getSelfNameResolution().getResolvedName();
						Parameter asParameter = PivotUtil.createParameter(selfName, PivotUtil.getOwningClass(asOriginalFeature), true);
						asParameters.add(asParameter);
						break;
					}
					default:
						throw new UnsupportedOperationException();
				}
			}
		}
		return asOperation;
	}

	private @NonNull Parameter createBoxedValuesParameter(@NonNull JavaCodeGenerator codeGenerator, boolean isRequired) {
		NameResolution boxedValuesResolution = codeGenerator.getGlobalNameManager().getBoxedValuesNameResolution();
		String boxedValuesName = boxedValuesResolution.getResolvedName();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		org.eclipse.ocl.pivot.Class boxedValueType = jLanguageSupport.getNativeClass(Object[].class);
		return PivotUtil.createParameter(boxedValuesName, boxedValueType, isRequired);
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {  // XXX perhaps always override
		Element asOperation = cgOperation.getAst();
		ExpressionInOCL asSpecification = (ExpressionInOCL) (asOperation instanceof ExpressionInOCL ? asOperation : ((Operation)asOperation).getBodyExpression());
		// XXX	assert (asSpecification != null);
		if (asSpecification != null) {
			OCLExpression asExpression = PivotUtil.getOwnedBody(asSpecification);
			CGValuedElement cgBody = analyzer.createCGElement(CGValuedElement.class, asExpression);
			cgOperation.setBody(cgBody);
		//	System.out.println("setBody " + NameUtil.debugSimpleName(cgOperation) + " : " + cgBody);
		}
	}

	protected abstract @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation);

	protected @NonNull CGOperation createCGOperationDeclaration(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgClass,
			@NonNull Operation asOperation, @Nullable NameResolution nameResolution, @Nullable TypedElement zzasOrigin) {
	//	JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
	//	GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		CGOperation cgOperation = createCGOperation(analyzer, asOperation);
		analyzer.initAst(cgOperation, asOperation, true);
		cgOperation.setCallingConvention(this);
		if (nameResolution != null) {
			nameResolution.addCGElement(cgOperation);
		}
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation);
		cgClass.getOperations().add(cgOperation);
		initCGParameters(operationNameManager, zzasOrigin);
		return cgOperation;
	}

/*	protected @NonNull CGParameter createCGParameter(@NonNull ExecutableNameManager operationNameManager, @NonNull Variable asParameterVariable) {
		return operationNameManager.getCGParameter(asParameterVariable, (String)null);
	} */

/*	protected void createCGParameters4asParameters(@NonNull ExecutableNameManager operationNameManager, @NonNull List<@NonNull CGParameter> cgParameters, @NonNull Iterable<@NonNull Parameter> asParameters) {
		for (@NonNull Parameter asParameterVariable : asParameters) {
			CGParameter cgParameter = operationNameManager.getCGParameter(asParameterVariable, (String)null);
			cgParameters.add(cgParameter);
		}
	} */

/*	protected void createCGParameters4asParameterVariables(@NonNull ExecutableNameManager operationNameManager, @NonNull List<@NonNull CGParameter> cgParameters, @NonNull Iterable<@NonNull Variable> asParameterVariables) {
		for (@NonNull Variable asParameterVariable : asParameterVariables) {
			CGParameter cgParameter = createCGParameter(operationNameManager, asParameterVariable);
			cgParameters.add(cgParameter);
		}
	} */

	@Override
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, @Nullable ExpressionInOCL asExpressionInOCL) {
		assert (asExpressionInOCL == null) || (asExpressionInOCL == asOperation.getBodyExpression());
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
		ClassNameManager classNameManager = analyzer.getClassNameManager(null, asClass);
		CGClass cgClass = classNameManager.getCGClass();
		CGOperation cgOperation = createCGOperation(analyzer, asOperation);
		assert cgOperation.getCallingConvention() == null;
		cgOperation.setCallingConvention(this);
		assert cgOperation.getAst() == null;						// Lightweight createCGOperation just creates
		assert analyzer.basicGetCGElement(asOperation) == null;
		analyzer.initAst(cgOperation, asOperation, true);
		assert analyzer.basicGetCGElement(asOperation) != null;
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation);	// Needed to support downstream useOperationNameManager()
		assert cgOperation.eContainer() == null;
		cgClass.getOperations().add(cgOperation);
		initCGParameters(operationNameManager, asExpressionInOCL);
		return cgOperation;
	}

	protected @NonNull ParameterVariable createThisParameterVariable(@NonNull CodeGenAnalyzer analyzer) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
		PivotHelper asHelper = codeGenerator.getASHelper();
		ParameterVariable asContextVariable;
		org.eclipse.ocl.pivot.Class asOldContextClass = codeGenerator.getContextClass();
		String thisName = globalNameManager.getThisNameResolution().getResolvedName();
		asContextVariable = asHelper.createParameterVariable(thisName, asOldContextClass, true);
		return asContextVariable;
	}

	protected void generateArgumentList(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();
		for (int i = 0; i < iMax; i++) {
			if (i > 0) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			CGParameter cgParameter = cgParameters.get(i);
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		}
	}

	@Deprecated /* @deprecated use generateJavaEvaluateCall always */
	protected boolean generateDeprecatedJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		//	Operation pOperation = cgFunctionCallExp.getReferredOperation();
		//	CGFunction cgFunction = ClassUtil.nonNullState(cgFunctionCallExp.getFunction());
		boolean useClassToCreateObject = PivotUtil.basicGetShadowExp(asOperation) != null;
		boolean useCache = !asOperation.isIsTransient();
		boolean isIdentifiedInstance = useCache;
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		List<Parameter> asParameters = asOperation.getOwnedParameters();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		//
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		boolean needComma = false;
		if (isIdentifiedInstance) {
			js.append("((");
			js.appendValueName(cgOperation);
			js.append(")");
			js.append(getFunctionCtorName(cgOperation));
			js.append(".getUniqueComputation(");
			//			if (useCache && !useClassToCreateObject) {
			//				CGClass cgClass = ClassUtil.nonNullState(cgFunction.getContainingClass());
			//				//				js.appendClassReference(cgClass);
			//				//				js.append(".this");
			//				qvticg2javaVisitor.appendThis(cgClass);
			//				needComma = true;
			//			}
		}
		else {
			//	js.append(asFunction.getName());
			js.appendValueName(cgOperation);
			js.append("(");
		}
		//	int iMax = cgParameters.size();
		//	assert iMax == cgArguments.size();
		for (int i = 0; i < cgArguments.size(); i++) {
			if (needComma) {
				js.append(", ");
			}
			//	CGParameter cgParameter = cgParameters.get(i);
			CGValuedElement cgArgument = cgArguments.get(i);
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			//	TypeId asTypeId = cgParameter.getTypeId().getASTypeId();
			//	assert asTypeId != null;
			//	TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(asTypeId);
			//	js.appendReferenceTo(parameterTypeDescriptor, argument);
			js.appendValueName(argument);
			needComma = true;
		}
		js.append(")");
		if (isIdentifiedInstance) {
			js.append(")");
			//	String cachedResultName = qvticg2javaVisitor.getVariantResolvedName(cgFunction, codeGenerator.getCACHED_RESULT_NameVariant());
			GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
			String cachedResultName = globalNameManager.getCachedResultNameResolution().getResolvedName();
			js.append(".");
			js.append(cachedResultName);
		}
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
	//	throw new UnsupportedOperationException("Unexpected " + this + " for an Ecore Operation");
	//	cg2javaVisitor.getJavaStream().append("Unexpected " + this + " for the Ecore Operation for " + cgOperation);
		return false;		// requires manual implementation
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX abstract
		return false;
	}

	protected boolean generateLocals(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		Iterable<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		return true;
	}

	protected /*abstract*/ @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
		throw new UnsupportedOperationException();
	}

	protected @NonNull Parameter getBoxedValuesParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		NameResolution boxedValuesResolution = analyzer.getGlobalNameManager().getBoxedValuesNameResolution();
		String boxedValuesName = boxedValuesResolution.getResolvedName();
		return ClassUtil.nonNullState(NameUtil.getNameable(asOperation.getOwnedParameters(), boxedValuesName));
	}

	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager, @Nullable TypedElement zzasOrigin) {
		return CG_PARAMETER_STYLES_PARAMETERS;
	}

	protected @NonNull Parameter getExecutorParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return ClassUtil.nonNullState(basicGetExecutorParameter(analyzer, asOperation));
	}

	private @NonNull String getFunctionCtorName(@NonNull CGOperation cgOperation) {
		return JavaStream.convertToJavaIdentifier("FTOR_" + cgOperation.getName());
	}

	@Deprecated /* temporary sub createCGOperationDeclaration functionality*/ // XXX create rather than lazy get
	protected final void initCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable TypedElement zzasOrigin) {
		@NonNull CGParameterStyle @NonNull  [] cgParameterStyles = getCGParameterStyles(operationNameManager, zzasOrigin);
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		Operation asOperation = (Operation)operationNameManager.getASScope();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		if (cgParameterStyles.length > 0) {
			List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
			for (@NonNull CGParameterStyle cgParameterStyle : cgParameterStyles) {
				switch(cgParameterStyle) {
					case BODY_SELF: {
						ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
						CGParameter cgParameter = analyzer.getSelfParameter(operationNameManager, PivotUtil.getOwnedContext(asExpressionInOCL));
						cgParameters.add(cgParameter);
						break;
					}
					case BOXED_VALUES: {
						Parameter asBoxedValuesParameter = getBoxedValuesParameter(analyzer, asOperation);
						CGParameter cgParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
						globalNameManager.getBoxedValuesNameResolution().addCGElement(cgParameter);
						cgParameters.add(cgParameter);
						break;
					}
					case CONTEXT_OBJECT: {
						CGParameter cgParameter = operationNameManager.getContextObjectParameter();
						cgParameters.add(cgParameter);
						break;
					}
					case EAGER_PARAMETERS: {
						for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
							CGParameter cgParameter = operationNameManager.getCGParameter(asParameter, null);
							cgParameters.add(cgParameter);
							operationNameManager.declareEagerName(cgParameter);
						}
						break;
					}
					case EAGER_PARAMETER_VARIABLES: {
						ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
						for (@NonNull Variable asParameterVariable : PivotUtil.getOwnedParameters(asExpressionInOCL)) {
							CGParameter cgParameter = operationNameManager.getCGParameter(asParameterVariable, null);
							cgParameters.add(cgParameter);
							operationNameManager.declareEagerName(cgParameter);
						}
						break;
					}
					case EXECUTOR: {
						CGParameter cgParameter;
						NameResolution executorNameResolution = globalNameManager.getExecutorNameResolution();
						Parameter asExecutorParameter = basicGetExecutorParameter(analyzer, asOperation);
						if (asExecutorParameter != null) {
							cgParameter = operationNameManager.getExecutorParameter(asExecutorParameter);
						}
						else {
							cgParameter = operationNameManager.createExecutorParameter();
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
						CGParameter cgParameter = operationNameManager.getCGParameter(contextVariable, (String)null);			// XXX getSelf ???
						cgParameter.setIsSelf(true);
						cgParameter.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// JUnit evaluate overrides
						cgParameter.setRequired(false);										//  self : Object[?]
						NameResolution selfNameResolution = globalNameManager.getSelfNameResolution();
						selfNameResolution.addCGElement(cgParameter);
						cgParameters.add(cgParameter);
						break;
					}
					case OPTIONAL_BODY_SELF: {
						CGParameter cgParameter = analyzer.getSelfParameter(operationNameManager, PivotUtil.getOwnedContext((ExpressionInOCL) asOperation.getBodyExpression()));
						cgParameter.setRequired(false);
						cgParameters.add(cgParameter);
						break;
					}
					case OPTIONAL_SELF: {
						CGParameter cgParameter = operationNameManager.getSelfParameter();
						cgParameter.setRequired(false);
						cgParameters.add(cgParameter);
						break;
					}
					case PARAMETERS: {
						for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
							CGParameter cgParameter = operationNameManager.getCGParameter(asParameter, null);
							cgParameters.add(cgParameter);
						}
						break;
					}
					case SELF: {
						CGParameter cgParameter = operationNameManager.getSelfParameter();
						cgParameters.add(cgParameter);
						break;
					}
					case THIS: {
						CGParameter cgParameter = operationNameManager.getThisParameter();
						cgParameters.add(cgParameter);
						break;
					}
					case TYPE_ID: {
						CGParameter cgParameter = operationNameManager.createTypeIdParameter();
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

	protected void initCallArguments(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		OperationCallExp asOperationCallExp = CGUtil.getAST(cgOperationCallExp);
		Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
		List<@NonNull OCLExpression> asArguments = PivotUtilInternal.getOwnedArgumentsList(asOperationCallExp);
		List<@NonNull OCLExpression> asArgumentsCopy = new ArrayList<>(asArguments);		// XXX inlining can wrap a LetExp
		assert asArguments.size() == asOperation.getOwnedParameters().size();
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		for (@NonNull OCLExpression asArgument : asArgumentsCopy) {
			cgArguments.add(analyzer.createCGElement(CGValuedElement.class, asArgument));
		}
		assert cgArguments.size() == CGUtil.getParametersList(CGUtil.getReferredOperation(cgOperationCallExp)).size();
	}

	protected void initCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp, @NonNull OperationCallExp asOperationCallExp,
			@NonNull CGOperation cgOperation, boolean isRequired) {		// XXX wip eliminate isRequired
		Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
	//	boolean isRequired2 = asOperation.isIsRequired();
	//	Boolean ecoreIsRequired = as2cgVisitor.getCodeGenerator().isNonNull(asOperationCallExp);
	//	if (ecoreIsRequired != null) {
	//		isRequired2 = ecoreIsRequired;
	//	}
	//	assert isRequired == isRequired2;
		cgOperationCallExp.setAsOperation(asOperation);
		analyzer.initAst(cgOperationCallExp, asOperationCallExp, true);
		cgOperationCallExp.setReferredOperation(cgOperation);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		if (cgOperation.isRequired()) {
			CGValuedElement body = cgOperation.getBody();
			if (body != null) {
				String message = "body for '" + cgOperation.getAst() + "'";
				boxingAnalyzer.rewriteAsGuarded(body, false, message);
			}
		}
	}

	// Default guards and boxes all terms. Derived implementations for unboxed/ecore/simple-boxed
	@Override		// XXX review for all derived implementations
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
	//	Operation referredOperation = cgLibraryOperationCallExp.getReferredOperation();
		org.eclipse.ocl.pivot.Class asClass = asOperation.getOwningClass();
		if ("_unqualified_env_Class".equals(asOperation.getName())) {
			getClass();		// XXX
		}
		OperationId operationId = asOperation.getOperationId();
		CodeGenAnalyzer analyzer = boxingAnalyzer.getAnalyzer();
		GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
		boolean sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);

		Operation asBaseOperation = analyzer.basicGetOriginalOperation(cgOperation);
		if (asBaseOperation != null) {
			getClass();	// XXX
		}
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int maxArgument = cgArguments.size();
		int maxParameter = cgParameters.size();
		assert (maxArgument == maxParameter) || (maxParameter > 0);	// Correct or may be varargs
		CGParameter cgParameter = null;
		for (int i = 0; i < maxArgument; i++) {			// Avoid CME from rewrite
			if (i < maxParameter) {
				cgParameter = cgParameters.get(i);
			}
			else {
				assert cgParameter != null;
				ElementId elementId = cgParameter.getTypeId().getElementId();
				boolean isArray = ((JavaTypeId)elementId).getJavaClass().getComponentType() != null;
				assert isArray;
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				CGValuedElement cgSource = cgArgument;
				if (!sourceMayBeNull) {
					if (cgSource.isNull()) {
//						CGInvalid cgInvalid = context.getInvalid("null value1 for source parameter");
						CGInvalid cgInvalid = analyzer.getCGInvalid("''" + asClass.getName() + "'' rather than ''OclVoid'' value required");
						CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgOperationCallExp), cgInvalid);
						globalNameManager.replace(cgOperationCallExp, cgLiteralExp);
						return;
					}
				}
			}
			else if (!asOperation.isIsValidating()) {
				Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
				if ((asParameter != null) && asParameter.isIsRequired()) {
					if (cgArgument.isNull()) {
	//					CGInvalid cgInvalid = context.getInvalid("null value2 for " + asParameter.getName() + " parameter");
						CGInvalid cgInvalid = analyzer.getCGInvalid("''" + asParameter.getType().getName() + "'' rather than ''OclVoid'' value required");
						CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgOperationCallExp), cgInvalid);
						globalNameManager.replace(cgOperationCallExp, cgLiteralExp);
						return;
					}
				}
			}
		}

		cgParameter = null;
		for (int i = 0; i < maxArgument; i++) {			// Avoid CME from rewrite
			if (i < maxParameter) {
				cgParameter = cgParameters.get(i);
			}
			assert cgParameter != null;
			CGValuedElement cgArgument = cgArguments.get(i);
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
			if (i == 0) {
				if (!sourceMayBeNull && !cgArgument.isRequiredOrNonNull()) {
//					rewriteAsGuarded(cgSource, false, "value3 for source parameter");
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + asClass.getName() + "'' rather than ''OclVoid'' value required");
				}
			}
			else {
			//	Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
			//	if ((asParameter != null) && asParameter.isIsRequired() && !cgArgument.isNonNull()) {
				if (cgParameter.isRequired() && !cgArgument.isRequiredOrNonNull()) {
//					rewriteAsGuarded(cgArgument, false, "value4 for " + asParameter.getName() + " parameter");
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + cgParameter.getTypeId() + "'' rather than ''OclVoid'' elementId");
				}
			}
		}
		if (cgOperationCallExp.isRequired() && !cgOperationCallExp.isRequiredOrNonNull()) {
			cgOperationCallExp.setRequired(false);
			boxingAnalyzer.rewriteAsGuarded(cgOperationCallExp, false, "result from ''" + asOperation + "''");
		}
	}
}
