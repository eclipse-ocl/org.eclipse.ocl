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

import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.TypeRepresentation;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.qvtd.runtime.internal.evaluation.AbstractComputationConstructor;

/**
 * AbstractCachedOperationCallingConvention2 provides a temporary husing for functionality promoted from QVTd's FunctionOperationCallingConvention
 */
public abstract class AbstractCachedOperationCallingConvention2 extends AbstractOperationCallingConvention
{
	/**
	 * A CacheProperty instance supports evaluation of a property in a CGed cache. (It has no opposite).
	 */
	public static class CacheProperty extends AbstractProperty
	{
		private final @NonNull PropertyId propertyId;
		private final @Nullable OCLExpression initExpression;
		private final @Nullable Object defaultValue;

		public CacheProperty(@NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue) {
			this.propertyId = propertyId;
			this.initExpression = initExpression;
			this.defaultValue = defaultValue;
		}

		/** @deprecated use Executor */
		@Deprecated
		@Override
		public @Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
			return evaluate(getExecutor(evaluator), returnTypeId, sourceValue);
		}

		/**
		 * @since 1.1
		 */
		@Override
		public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
			throw new UnsupportedOperationException();
		}
	}

	public static abstract class AbstractEvaluateOperationCallingConvention extends AbstractCachedOperationCallingConvention
	{
		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis
		}

		public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asCacheClass) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x, y
			// AS Cache Operation - evaluate
			// AS Cache Operation.parameters - x, y
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters -
			// CG Cache Operation - evaluate
			// CG Cache Operation.lets -
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asConstructorClass = CGUtil.getAST(cgConstructorClass);
			//
			//	Create AS declaration for newInstance
			//
			NameResolution evaluateNameResolution = globalNameManager.getEvaluateNameResolution();
			String newInstanceName = evaluateNameResolution.getResolvedName();
			Operation asEvaluateOperation = PivotUtil.createOperation(newInstanceName, asCacheClass, null, null);
			asEvaluateOperation.setType(asOperation.getType());
			asEvaluateOperation.setIsRequired(asOperation.isIsRequired());

			List<@NonNull Parameter> asParameters = PivotUtilInternal.getOwnedParametersList(asOperation);
			List<@NonNull Parameter> asEvaluateParameters = PivotUtilInternal.getOwnedParametersList(asEvaluateOperation);
			Parameter asEvaluateSelfParameter = createConstructorEvaluateOperationSelfParameter(analyzer, asOperation);
			if (asEvaluateSelfParameter != null) {
				asEvaluateParameters.add(asEvaluateSelfParameter);
			}
			for (@NonNull Parameter asParameter : asParameters) {
				Parameter asEvaluateParameter = PivotUtil.createParameter(asParameter.getName(), asParameter.getType(), asParameter.isIsRequired());
				asEvaluateParameters.add(asEvaluateParameter);
			}
			asConstructorClass.getOwnedOperations().add(asEvaluateOperation);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgEvaluateOperation = createCGOperation(analyzer, asEvaluateOperation);
			analyzer.initAst(cgEvaluateOperation, asEvaluateOperation, true);
			cgEvaluateOperation.setCallingConvention(this);
			evaluateNameResolution.addCGElement(cgEvaluateOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgEvaluateOperation, asEvaluateOperation);
			List<@NonNull CGParameter> cgEvaluateParameters = CGUtil.getParametersList(cgEvaluateOperation);
			for (@NonNull Parameter asEvaluateParameter : asEvaluateParameters) {
				CGParameter cgParameter = operationNameManager.getCGParameter(asEvaluateParameter, null);
				String name = asEvaluateParameter.getName();
				if (globalNameManager.getSelfName().equals(name)) {
					globalNameManager.getSelfNameResolution().addCGElement(cgParameter);
				}
				else if (globalNameManager.getObjectName().equals(name)) {
					globalNameManager.getObjectNameResolution().addCGElement(cgParameter);
				}
				cgEvaluateParameters.add(cgParameter);
			}
			cgConstructorClass.getOperations().add(cgEvaluateOperation);
			return cgEvaluateOperation;
		}

		protected @Nullable Parameter createConstructorEvaluateOperationSelfParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			return null;
		}
	}

	public static class CacheConstructorCallingConvention extends AbstractCachedOperationCallingConvention
	{
		private static final @NonNull CacheConstructorCallingConvention INSTANCE = new CacheConstructorCallingConvention();

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		public static @NonNull CacheConstructorCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis.
			//	Needs an ability to specify a super() invocation and no return type.
		}

		public @NonNull CGOperation createConstructor(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters -
			// AS Cache Operation - yyy2zzz
			// AS Cache Operation.parameters -
			// AS Cache ExpressionInOCL.ownedContext -
			// AS Cache ExpressionInOCL.ownedParameters -
			// CG Cache Operation - yyy2zzz
			// CG Cache Operation.lets -
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asConstructorClass = CGUtil.getAST(cgConstructorClass);
			//
			//	Create AS declaration for newInstance
			//
			String constructorName = PivotUtil.getName(asConstructorClass);
			Type asReturnType = environmentFactory.getStandardLibrary().getOclVoidType();
			Operation asConstructorOperation = PivotUtil.createOperation(constructorName, asReturnType, null, null);
			asConstructorOperation.setIsRequired(true);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator);
			asConstructorOperation.getOwnedParameters().add(asBoxedValuesParameter);
			asConstructorClass.getOwnedOperations().add(asConstructorOperation);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgConstructorOperation = createCGOperation(analyzer, asConstructorOperation);
			analyzer.initAst(cgConstructorOperation, asConstructorOperation, true);
			cgConstructorOperation.setCallingConvention(this);
			//	newInstanceNameResolution.addCGElement(cgConstructorOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructorOperation, asConstructorOperation);
			List<@NonNull CGParameter> cgCacheParameters = CGUtil.getParametersList(cgConstructorOperation);
			CGParameter cgConstructorBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgConstructorBoxedValuesParameter);
			cgCacheParameters.add(cgConstructorBoxedValuesParameter);
			//
			cgConstructorClass.getOperations().add(cgConstructorOperation);
			//
			//	Create CG body
			//
			//	createCGBody(analyzer, cgConstructorOperation);
			return cgConstructorOperation;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			//	js.appendCommentWithOCL(title, asFeature);
			js.append("public ");
			js.appendValueName(cgOperation);
			js.append("() {\n");
			js.pushIndentation(null);
			js.append("super(");
			js.append(CGUtil.getContextCGClass(cgOperation).getName());
			js.append(".this.");
			js.append(cg2javaVisitor.getGlobalNameManager().getIdResolverName());
			js.append(");\n");
			js.popIndentation();
			js.append("}\n");
			return true;
		}
	}

	public static class CacheInstancePropertyCallingConvention extends ImmutableCachePropertyCallingConvention
	{
		private static final @NonNull CacheInstancePropertyCallingConvention INSTANCE = new CacheInstancePropertyCallingConvention();

		public static @NonNull PropertyCallingConvention getInstance(@NonNull Property asProperty) {
			INSTANCE.logInstance(asProperty);
			return INSTANCE;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
			TypeRepresentation boxedTypeRepresentation = js.getBoxedTypeRepresentation();
			js.append("protected final");
			js.append(" /*@NonInvalid*/ ");
			boxedTypeRepresentation.appendClassReference(cgProperty.isRequired(), cgProperty);
			js.append(" ");
			js.appendValueName(cgProperty);
			js.append(" = new ");
			boxedTypeRepresentation.appendClassReference(null, cgProperty);
			js.append("();\n");
			return true;
		}
	}

	public static class EvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		private static final @NonNull EvaluateOperationCallingConvention INSTANCE = new EvaluateOperationCallingConvention();

		public static @NonNull EvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
			GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
			Operation asOperation = CGUtil.getAST(cgOperation);
			org.eclipse.ocl.pivot.Class asConstructorClass = PivotUtil.getOwningClass(asOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = analyzer.getEntryClass(asConstructorClass);
			CGClass cgCacheClass = analyzer.getCGClass(asCacheClass);
			js.append("return ((");
			js.appendClassReference(cgCacheClass);
			js.append(")getUniqueComputation(");
			//	js.append(QVTiCGUtil.getContainingCGTransformation(cgOperation).getName());
			js.append("transformation");		// XXX
			//	js.append(globalNameManager.getIdResolverName());
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				js.append(", ");
				js.appendValueName(cgParameter);
			}
			js.append(")).");
			js.append(globalNameManager.getCachedResultNameResolution().getResolvedName());
			js.append(";\n");
		}
	}

	public static class GetResultOperationCallingConvention extends AbstractCachedOperationCallingConvention
	{
		private static final @NonNull GetResultOperationCallingConvention INSTANCE = new GetResultOperationCallingConvention();

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		public static @NonNull GetResultOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	super.createCGBody(analyzer, cgOperation);
			Element asOperation = cgOperation.getAst();
			ExpressionInOCL asExpressionInOCL = (ExpressionInOCL) (asOperation instanceof ExpressionInOCL ? asOperation : ((Operation)asOperation).getBodyExpression());
			assert (asExpressionInOCL != null);
			CGValuedElement cgResult = analyzer.createCGElement(CGValuedElement.class, asExpressionInOCL);
			cgOperation.setBody(cgResult);
		}

		public @NonNull CGOperation createCacheGetResultOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Operation asOperation) {
			//
			// AS Class - yyy2zzz
			// AS Properties - thisTransformer, x1, x2, cachedResult
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x1, x2
			// AS Cache Operation - isEqual
			// AS Cache Operation.parameters - boxedValues
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters - thisTransformer, x1, x2
			// CG Cache Operation - isEqual
			// CG Cache Operation.parameters - idResolver, boxedValues
			// CG Cache Operation.lets - thisTransformer, x1, x2
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			PivotHelper helper = new PivotHelper(environmentFactory);
			org.eclipse.ocl.pivot.@NonNull Class asCacheClass = CGUtil.getAST(cgCacheClass);
			//
			//	Create AS declaration
			//
			NameResolution getResultNameResolution = globalNameManager.getGetResultNameResolution();
			String getResultName = getResultNameResolution.getResolvedName();
			List<@NonNull Property> asCacheProperties = PivotUtilInternal.getOwnedPropertiesList(asCacheClass);
			Property asCachedResultProperty = asCacheProperties.get(asCacheProperties.size()-1);
			Type asCachedResultType = PivotUtil.getType(asCachedResultProperty);
			Operation asCacheOperation = PivotUtil.createOperation(getResultName, asCachedResultType, null, null);
			asCacheOperation.setIsRequired(asCachedResultProperty.isIsRequired());
			asCacheClass.getOwnedOperations().add(asCacheOperation);
			//
			//	Create AS body
			//
			ExpressionInOCL asExpressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
			ParameterVariable asThisVariable = PivotFactory.eINSTANCE.createParameterVariable();
			asThisVariable.setName(PivotConstants.SELF_NAME);
			asThisVariable.setType(asCacheClass);
			asThisVariable.setIsRequired(true);
			asExpressionInOCL.setOwnedContext(asThisVariable);
			OCLExpression asThisVariableExp = helper.createVariableExp(asThisVariable);
			OCLExpression asBody = helper.createPropertyCallExp(asThisVariableExp, asCachedResultProperty);
			asExpressionInOCL.setOwnedBody(asBody);
			asExpressionInOCL.setType(asBody.getType());
			asCacheOperation.setBodyExpression(asExpressionInOCL);
			//
			//	Create CG declaration
			//
			CGOperation cgCacheOperation = createCGOperation(analyzer, asCacheOperation);
			analyzer.initAst(cgCacheOperation, asCacheOperation, true);
			cgCacheOperation.setCallingConvention(this);
			getResultNameResolution.addCGElement(cgCacheOperation);
			analyzer.getOperationNameManager(cgCacheOperation, asCacheOperation);
			//
			cgCacheClass.getOperations().add(cgCacheOperation);
			//
			//	Create CG body
			//
			//	CGValuedElement cgResult = analyzer.createCGElement(CGValuedElement.class, asExpressionInOCL);
			//	cgCacheOperation.setBody(cgResult);
			//	createCGBody(analyzer, cgCacheOperation);
			return cgCacheOperation;
		}
	}

	public static class IsEqualOperationCallingConvention extends AbstractCachedOperationCallingConvention
	{
		private static final @NonNull IsEqualOperationCallingConvention INSTANCE = new IsEqualOperationCallingConvention();

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		public static @NonNull IsEqualOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgCacheOperation) {
			Operation asCacheOperation = CGUtil.getAST(cgCacheOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = PivotUtil.getOwningClass(asCacheOperation);
			ExpressionInOCL asExpressionInOCL = (ExpressionInOCL) asCacheOperation.getBodyExpression();
			assert (asExpressionInOCL != null);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgCacheOperation, asCacheOperation);
			Parameter asBoxedValuesParameter = PivotUtilInternal.getOwnedParametersList(asCacheOperation).get(0);
			CGParameter cgCacheBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			List<@NonNull Property> asCacheProperties = PivotUtilInternal.getOwnedPropertiesList(asCacheClass);
			List<@NonNull Variable> asCacheParameterVariables = PivotUtilInternal.getOwnedParametersList(asExpressionInOCL);
			Stack<@NonNull CGFinalVariable> cgLetVariables = new Stack<>();
			for (int i = 0; i < asCacheProperties.size()-1; i++) {		// not cachedResult
				ParameterVariable asParameterVariable = (ParameterVariable)asCacheParameterVariables.get(i);
				CGVariableExp cgVariableExp = analyzer.createCGVariableExp(cgCacheBoxedValuesParameter);
				CGIndexExp cgIndexExp = analyzer.createCGIndexExp(cgVariableExp, i);
				cgIndexExp.setAst(asParameterVariable);
				CGFinalVariable cgParameterVariable = operationNameManager.createCGVariable(cgIndexExp);
				operationNameManager.addVariable(asParameterVariable, cgParameterVariable);
				cgLetVariables.push(cgParameterVariable);
			}
			//
			CGValuedElement cgResult = analyzer.createCGElement(CGValuedElement.class, asExpressionInOCL);
			while (!cgLetVariables.isEmpty()) {
				CGFinalVariable cgLetVariable = cgLetVariables.pop();
				cgResult = analyzer.createCGLetExp(cgLetVariable, cgResult);
			}
			cgCacheOperation.setBody(cgResult);
		}

		public final @NonNull CGOperation createCacheIsEqualOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Operation asOperation) {
			//
			// AS Class - yyy2zzz
			// AS Properties - thisTransformer, x1, x2, cachedResult
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x1, x2
			// AS Cache Operation - isEqual
			// AS Cache Operation.parameters - boxedValues
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters - thisTransformer, x1, x2
			// CG Cache Operation - isEqual
			// CG Cache Operation.parameters - idResolver, boxedValues
			// CG Cache Operation.lets - thisTransformer, x1, x2
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			PivotHelper helper = new PivotHelper(environmentFactory);
			org.eclipse.ocl.pivot.@NonNull Class asCacheClass = CGUtil.getAST(cgCacheClass);
			//
			//	Create AS declaration for isEqual
			//
			NameResolution isEqualNameResolution = globalNameManager.getIsEqualNameResolution();
			String isEqualName = isEqualNameResolution.getResolvedName();
			Type asReturnType = environmentFactory.getStandardLibrary().getBooleanType();
			Operation asCacheOperation = PivotUtil.createOperation(isEqualName, asReturnType, null, null);
			asCacheOperation.setIsRequired(true);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator);
			asCacheOperation.getOwnedParameters().add(asBoxedValuesParameter);
			asCacheClass.getOwnedOperations().add(asCacheOperation);
			//
			//	Create AS body for isEqual
			//
			OCLExpression asBody = null;
			ExpressionInOCL asExpressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
			ParameterVariable asThisVariable = PivotFactory.eINSTANCE.createParameterVariable();
			asThisVariable.setName(PivotConstants.SELF_NAME);
			asThisVariable.setType(codeGenerator.getContextClass());
			asThisVariable.setIsRequired(true);
			asExpressionInOCL.setOwnedContext(asThisVariable);
			List<@NonNull Variable> asCacheParameterVariables = PivotUtilInternal.getOwnedParametersList(asExpressionInOCL);
			List<@NonNull Property> asCacheProperties = PivotUtilInternal.getOwnedPropertiesList(asCacheClass);
			Stack<@NonNull LetVariable> asLetVariables = new Stack<>();
			List<@NonNull Parameter> asParameters = PivotUtilInternal.getOwnedParametersList(asOperation);
			for (int i = 0; i < asCacheProperties.size()-1; i++) {		// not cachedResult
				Property asCacheProperty = asCacheProperties.get(i);
				@NonNull ParameterVariable asCacheParameterVariable;
				if (i == 0) {
					ParameterVariable asCacheThisVariable = PivotFactory.eINSTANCE.createParameterVariable();
					asCacheThisVariable.setName(PivotConstants.SELF_NAME);
					asCacheThisVariable.setType(asCacheClass);
					asCacheThisVariable.setIsRequired(true);
					asCacheParameterVariable = asCacheThisVariable;
				}
				else {
					Parameter asParameter = asParameters.get(i-1);
					asCacheParameterVariable = helper.createParameterVariable(asParameter);
					asCacheParameterVariable.setRepresentedParameter(asParameter);
				}
				asCacheParameterVariables.add(asCacheParameterVariable);
				String name = PivotUtil.getName(asCacheParameterVariable);
				VariableExp asInit = helper.createVariableExp(asCacheParameterVariable);
				LetVariable asLetVariable = helper.createLetVariable(name, asInit);
				asLetVariables.push(asLetVariable);

				OCLExpression asThisVariableExp = helper.createVariableExp(asThisVariable);
				OCLExpression asCacheParameterVariableExp = helper.createVariableExp(asCacheParameterVariable);
				OCLExpression asCachePropertyCallExp = helper.createPropertyCallExp(asThisVariableExp, asCacheProperty);
				OCLExpression asEquals = helper.createOperationCallExp(asCacheParameterVariableExp, "=", asCachePropertyCallExp);
				asBody = asBody != null ? helper.createOperationCallExp(asBody, LibraryConstants.AND2, asEquals) : asEquals;
			}
			assert asBody != null;
			while (!asLetVariables.isEmpty()) {
				LetVariable asVariable = asLetVariables.pop();
				asBody = helper.createLetExp(asVariable, asBody);
			}
			asExpressionInOCL.setOwnedBody(asBody);
			asExpressionInOCL.setType(asBody.getType());
			asCacheOperation.setBodyExpression(asExpressionInOCL);
			//
			//	Create CG declaration for isEqual
			//
			CGOperation cgCacheOperation = createCGOperation(analyzer, asCacheOperation);
			analyzer.initAst(cgCacheOperation, asCacheOperation, true);
			cgCacheOperation.setCallingConvention(this);
			isEqualNameResolution.addCGElement(cgCacheOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgCacheOperation, asCacheOperation);
			List<@NonNull CGParameter> cgCacheParameters = CGUtil.getParametersList(cgCacheOperation);
			CGParameter cgIdResolverParameter = operationNameManager.getIdResolverParameter();
			cgCacheParameters.add(cgIdResolverParameter);
			CGParameter cgCacheBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgCacheBoxedValuesParameter);
			cgCacheParameters.add(cgCacheBoxedValuesParameter);
			//
			cgCacheClass.getOperations().add(cgCacheOperation);
			//
			//	Create CG body for isEqual unpacking boxedValues to regular parameter variables
			//
			//	createCGBody(analyzer, cgCacheOperation);
			return cgCacheOperation;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			js.append("/**\n");
			js.append(" * Return true if the boxedValues exactly match OCL-wise the values from which this entry was constructed.\n");
			js.append(" * If so, the cachedResult can be re-used and no further ENTRY needs construction.\n");
			js.append(" */\n");
			return super.generateJavaDeclaration(cg2javaVisitor, js, cgOperation);
		}
	}

	public static class NewInstanceOperationCallingConvention extends AbstractCachedOperationCallingConvention
	{
		private static final @NonNull NewInstanceOperationCallingConvention INSTANCE = new NewInstanceOperationCallingConvention();

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		public static @NonNull NewInstanceOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis.
			//	Needs an ability to specify a new T invocation.
		}

		public final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass, org.eclipse.ocl.pivot.@NonNull Class asCacheClass) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters -
			// AS Cache Operation - newInstance
			// AS Cache Operation.parameters - boxedValues
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters -
			// CG Cache Operation - newInstance
			// CG Cache Operation.lets -
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asConstructorClass = CGUtil.getAST(cgConstructorClass);
			//
			//	Create AS declaration for newInstance
			//
			NameResolution newInstanceNameResolution = globalNameManager.getNewInstanceResolution();
			String newInstanceName = newInstanceNameResolution.getResolvedName();
			Operation asConstructorOperation = PivotUtil.createOperation(newInstanceName, asCacheClass, null, null);
			asConstructorOperation.setIsRequired(true);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator);
			asConstructorOperation.getOwnedParameters().add(asBoxedValuesParameter);
			asConstructorClass.getOwnedOperations().add(asConstructorOperation);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgConstructorOperation = createCGOperation(analyzer, asConstructorOperation);
			analyzer.initAst(cgConstructorOperation, asConstructorOperation, true);
			cgConstructorOperation.setCallingConvention(this);
			newInstanceNameResolution.addCGElement(cgConstructorOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructorOperation, asConstructorOperation);
			List<@NonNull CGParameter> cgCacheParameters = CGUtil.getParametersList(cgConstructorOperation);
			CGParameter cgConstructorBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgConstructorBoxedValuesParameter);
			cgCacheParameters.add(cgConstructorBoxedValuesParameter);
			//
			cgConstructorClass.getOperations().add(cgConstructorOperation);
			//
			//	Create CG body
			//
			//	createCGBody(analyzer, cgConstructorOperation);
			return cgConstructorOperation;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			js.append("return new ");
			js.appendClassReference(null, cgOperation);
			js.append("(");
			boolean isFirst = true;
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				if (!isFirst) {
					js.append(", ");
				}
				js.appendValueName(cgParameter);
				isFirst = false;
			}
			js.append(");\n");
		}
	}

	protected final org.eclipse.ocl.pivot.@NonNull Class createCacheClass(@NonNull ExecutableNameManager operationNameManager, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		Operation asOperation = CGUtil.getAST(cgOperation);
		//
		org.eclipse.ocl.pivot.@NonNull Package asPackage = getCachePackage(analyzer, asOperation);
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asPackage);
		String cacheClassName = packageNameManager.getUniqueClassName(NameManagerHelper.CACHE_CLASS_NAME_PREFIX, asOperation);
		org.eclipse.ocl.pivot.Class asCacheClass = AbstractLanguageSupport.getClass(asPackage, cacheClassName);
		org.eclipse.ocl.pivot.Class asCacheSuperClass = jLanguageSupport.getNativeClass(AbstractComputationConstructor.class);
		asCacheClass.getSuperClasses().add(asCacheSuperClass);
		importNameManager.reserveLocalName(PivotUtil.getName(asCacheClass));
		analyzer.addCachedOperation(asCacheClass, asOperation);
		//
		CGClass cgCacheClass = analyzer.generateClassDeclaration(asCacheClass, ConstructorClassCallingConvention.getInstance(asCacheClass));
		CGClass cgCacheSuperClass = analyzer.generateClassDeclaration(asCacheSuperClass, getClassCallingConvention(asCacheSuperClass));
		cgCacheClass.getSuperTypes().add(cgCacheSuperClass);
		//
		CacheConstructorCallingConvention.getInstance(asCacheClass).createConstructor(analyzer, cgCacheClass);
		getEvaluateOperationCallingConvention(asCacheClass).createOperation(analyzer, cgCacheClass, asOperation, asEntryClass);
		NewInstanceOperationCallingConvention.getInstance(asCacheClass).createOperation(analyzer, cgCacheClass, asEntryClass);
		//
		return asCacheClass;
	}

	protected final @NonNull Property createCacheInstance(@NonNull ExecutableNameManager operationNameManager, org.eclipse.ocl.pivot.@NonNull Class asCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();

		CGClass cgRootClass = analyzer.getCGRootClass(asEntryClass);
		ClassNameManager classNameManager = analyzer.getGlobalNameManager().getClassNameManager(cgRootClass);
		String propertyName = classNameManager.getUniquePropertyName(NameManagerHelper.INSTANCE_PROPERTY_NAME_PREFIX, asCacheClass);
		Property asProperty = PivotUtil.createProperty(propertyName, asCacheClass);
		Operation asOperation = (Operation)operationNameManager.getASScope();
		analyzer.addCacheInstance(asOperation, asProperty, asEntryClass);
		//
		//	CGClass cgClass = qvtiOperationNameManager.getClassNameManager().getCGClass();
		CGProperty cgProperty = analyzer.createCGElement(CGProperty.class, asProperty);
		cgProperty.setCallingConvention(CacheInstancePropertyCallingConvention.getInstance(asProperty));
		//	cgClass.getProperties().add(cgProperty);
		assert cgProperty.eContainer() != null;
		return asProperty;
	}

protected final void createCacheProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass,
			@Nullable NameResolution nameResolution, @NonNull NamedElement asTypeOrTypedElement) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		//
		Property asCacheProperty = PivotFactory.eINSTANCE.createProperty();
		asCacheProperty.setName(nameResolution != null ? nameResolution.getResolvedName() : asTypeOrTypedElement.getName());
		if (asTypeOrTypedElement instanceof Type) {
			asCacheProperty.setType((Type)asTypeOrTypedElement);
			asCacheProperty.setIsRequired(true);
		}
		else if (asTypeOrTypedElement instanceof TypedElement) {
			TypedElement asTypedElement = (TypedElement)asTypeOrTypedElement;
			asCacheProperty.setType(PivotUtil.getType(asTypedElement));
			asCacheProperty.setIsRequired(asTypedElement.isIsRequired());
		}
		else {
			throw new IllegalStateException();
		}
		asCacheClass.getOwnedProperties().add(asCacheProperty);
		asCacheProperty.setImplementation(new CacheProperty(asCacheProperty.getPropertyId(), null, null));
		//
		CGProperty cgCacheProperty = analyzer.generatePropertyDeclaration(asCacheProperty, ImmutableCachePropertyCallingConvention.getInstance(asCacheProperty));
		if (nameResolution != null) {
			nameResolution.addCGElement(cgCacheProperty);
		}
		cgCacheClass.getProperties().add(cgCacheProperty);
		if (nameResolution == null) {
			ClassNameManager nameManager = analyzer.getClassNameManager(cgCacheClass, asCacheClass);
			//			nameManager.declareEagerName(cgCacheProperty)
			nameResolution = nameManager.getNameResolution(cgCacheProperty);
		}
	}

/*	protected final @NonNull Property createConstructorInstance2(@NonNull ExecutableNameManager operationNameManager, org.eclipse.ocl.pivot.@NonNull Class asCacheClass) {
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		Property asProperty = PivotUtil.createProperty("INST_" + asCacheClass.getName(), asCacheClass);		// XXX name allocation
		Operation asOperation = (Operation)operationNameManager.getASScope();
		analyzer.addCacheConstructorInstance(asOperation, asProperty, asCacheClass);
		//
		//	CGClass cgClass = qvtiOperationNameManager.getClassNameManager().getCGClass();
		CGProperty cgProperty = analyzer.createCGElement(CGProperty.class, asProperty);
		cgProperty.setCallingConvention(ConstructorInstancePropertyCallingConvention.getInstance());
		//	cgClass.getProperties().add(cgProperty);
		assert cgProperty.eContainer() != null;
		return asProperty;
	} */

	@Override	@Deprecated /* @deprecated use generateJavaEvaluateCall always */
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
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
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		return true;		 // functionality realized by finer-grained CG elements
	}

	protected final boolean generateJavaEvaluateCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		// FIXME could be a regular CG call if CG and AS separately created by generateDeclaration
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		Iterable<@NonNull CGValuedElement> cgArguments = CGUtil.getArguments(cgOperationCallExp);
		Property asCacheInstance = analyzer.getCacheInstance(asOperation);
		CGProperty cgCacheInstance = analyzer.getCGProperty(asCacheInstance);
		//
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		js.appendValueName(cgCacheInstance);
		js.append(".evaluate(");
		boolean isFirst = true;
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			if (!isFirst) {
				js.append(", ");
			}
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendValueName(argument);
			isFirst = false;
		}
		js.append(");\n");
		return true;
	}

	protected final @NonNull CGProperty getCGCacheProperty(@NonNull CGClass cgCacheClass, @NonNull String name) {
		for (@NonNull CGProperty cgProperty : CGUtil.getProperties(cgCacheClass)) {
			Property asProperty = CGUtil.getAST(cgProperty);
			if (name.equals(asProperty.getName())) {
				return cgProperty;
			}
		}
		throw new IllegalStateException();
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getCachePackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {	// XXX Regularly overridden
		return AbstractLanguageSupport.getCachePackage(asOperation);
	}

	protected @NonNull AbstractEvaluateOperationCallingConvention getEvaluateOperationCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return EvaluateOperationCallingConvention.getInstance(asClass);
	}

	private @NonNull String getFunctionCtorName(@NonNull CGOperation cgOperation) {
		return JavaStream.convertToJavaIdentifier("FTOR_" + cgOperation.getName());
	}
}
