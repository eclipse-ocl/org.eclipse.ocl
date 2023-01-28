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

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention.AbstractEvaluateOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreCodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor.ExecutorExtension;
import org.eclipse.ocl.pivot.internal.library.ForeignProperty;
import org.eclipse.ocl.pivot.internal.library.StaticProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ForeignPropertyCallingConvention extends AbstractPropertyCallingConvention
{
	private static final @NonNull ForeignPropertyCallingConvention INSTANCE = new ForeignPropertyCallingConvention();

	public static @NonNull ForeignPropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	public static class ForeignGetterOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		private static final @NonNull ForeignGetterOperationCallingConvention INSTANCE = new ForeignGetterOperationCallingConvention();

		public static @NonNull ForeignGetterOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgForeignOperation) {
			Property asProperty = analyzer.getOriginalProperty(cgForeignOperation);
			Operation asForeignOperation = CGUtil.getAST(cgForeignOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgForeignOperation, asForeignOperation);
			List<@NonNull CGParameter> cgForeignParametersList = CGUtil.getParametersList(cgForeignOperation);
			CGParameter cgSelfParameter = cgForeignParametersList.size() > 1 ? cgForeignParametersList.get(1) : null;
			CGValuedElement cgInitValue = analyzer.getInitExpression(/*cgParameter,*/ asProperty);
			assert cgInitValue != null;
			CGParameter executorParameter = operationNameManager.getExecutorParameter();
			CGVariable modelManagerVariable = operationNameManager.getModelManagerVariable();
			CGElementId cgPropertyId = analyzer.getCGElementId(asProperty.getPropertyId());
			CGExecutorType cgCastType = operationNameManager.getCGExecutorType(PivotUtil.getType(asProperty));
			CGNativeOperationCallExp basicGetValueInit = createCGBoxedNativeOperationCallExp(analyzer, analyzer.createCGVariableExp(modelManagerVariable), JavaConstants.MODEL_MANAGER_BASIC_GET_FOREIGN_PROPERTY_VALUE_METHOD,
				cgSelfParameter != null ? analyzer.createCGVariableExp(cgSelfParameter) : analyzer.createCGConstantExp(analyzer.createCGNull()), analyzer.createCGConstantExp(cgPropertyId));
		//	basicGetValueInit.setTypeId(cacheTypeId);
			basicGetValueInit.setValueIsBoxed(true);
			CGValuedElement castBasicGetValueInit = analyzer.createCGCastExp(cgCastType, basicGetValueInit);
			CGFinalVariable basicGetValueVariable = operationNameManager.createCGVariable(castBasicGetValueInit);
			CGValuedElement cgCondition = analyzer.createCGIsEqual2(analyzer.createCGVariableExp(basicGetValueVariable), analyzer.createCGNull());
			CGNativeOperationCallExp getValue = createCGBoxedNativeOperationCallExp(analyzer, analyzer.createCGVariableExp(modelManagerVariable), JavaConstants.MODEL_MANAGER_GET_FOREIGN_PROPERTY_VALUE_METHOD,
				cgSelfParameter != null ? analyzer.createCGVariableExp(cgSelfParameter) : analyzer.createCGConstantExp(analyzer.createCGNull()), analyzer.createCGConstantExp(cgPropertyId), cgInitValue);
		//	getValue.setTypeId(cacheTypeId);
			getValue.setValueIsBoxed(true);
			CGValuedElement castGetValue = analyzer.createCGCastExp(cgCastType, getValue);
			if (asProperty.isIsRequired()) {
				getValue.setRequired(true);
			}
			CGValuedElement ifValue = analyzer.createCGIfExp(cgCondition, castGetValue, analyzer.createCGVariableExp(basicGetValueVariable));
		//	if (asProperty.isIsRequired() && !ifValue.isNonNull()) {
		//		ifValue = analyzer.createCGCastExp(cgCastType, ifValue);
		//		ifValue.setRequired(true);
		//	}
			CGValuedElement withBasicGetValue = analyzer.createCGLetExp(basicGetValueVariable, ifValue);
			cgForeignOperation.setBody(withBasicGetValue);
		}

		private @NonNull CGNativeOperationCallExp createCGBoxedNativeOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @Nullable CGValuedElement cgThis, @NonNull Method jMethod, @NonNull CGValuedElement... cgArguments) {
			CGNativeOperationCallExp cgCallExp = analyzer.createCGNativeOperationCallExp(jMethod, SupportOperationCallingConvention.getInstance(jMethod));
			cgCallExp.setCgThis(cgThis);
			if (cgArguments != null) {
				List<CGValuedElement> cgArguments2 = cgCallExp.getArguments();
				for (@NonNull CGValuedElement cgArgument : cgArguments) {
					cgArguments2.add(cgArgument);
				}
			}
			cgCallExp.setRequired(analyzer.getCodeGenerator().getIsNonNull(jMethod) == Boolean.TRUE);
		//	cgCallExp.setInvalidating(false));
			Class<?> jReturnType = jMethod.getReturnType();
			assert jReturnType != null;
			cgCallExp.setTypeId(analyzer.getCGTypeId(new JavaTypeId(jReturnType)));		// XXX cache
			return cgCallExp;
		}

		public final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgForeignClass, @NonNull Property asProperty, @Nullable ExpressionInOCL asExpressionInOCL) {
			//
			// AS Class - yyy2zzz
			// AS Properties - contextObject, x1, x2, cachedResult
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x1, x2
			// AS Entry Operation - isEqual
			// AS Entry Operation.parameters - boxedValues{x1, x2}
			// AS Entry ExpressionInOCL.ownedContext - this
			// AS Entry ExpressionInOCL.ownedParameter(Variable)s - idResolver, self, x1, x2
			// CG Entry Operation - isEqual
			// CG Entry Operation.parameters - idResolver, boxedValues{x1, x2}
			// CG Entry Operation.lets - x1, x2
			//
			org.eclipse.ocl.pivot.@NonNull Class asForeignClass = CGUtil.getAST(cgForeignClass);
			boolean isStatic = asProperty.isIsStatic();
			//
			//	Create AS declaration for property access operation
			//
			String qualifiedPropertyName = asProperty.toString().replace("::", "_");
			Operation asForeignOperation = createASOperationDeclaration(analyzer, asForeignClass, asProperty,
				qualifiedPropertyName, asProperty);
			//
			//	Create AS body for property access operation
			//
			ExpressionInOCL asForeignExpressionInOCL = createASExpressionInOCL(analyzer, asProperty,
				ASContextVariableStyle.THIS, isStatic ? AS_PARAMETER_VARIABLE_STYLES : AS_PARAMETER_VARIABLE_STYLES_SELF);
			OCLExpression asBody;
			if (asExpressionInOCL != null) {
				asBody = EcoreUtil.copy(asExpressionInOCL.getOwnedBody());
			}
			else {
				asBody = ValueUtil.createLiteralExp(asProperty.getDefaultValue());
			}
			installExpressionInOCLBody(asForeignOperation, asForeignExpressionInOCL, asBody);
			//
			//	Create CG declaration
			//
			CGOperation cgForeignOperation = createCGOperationDeclaration(analyzer, cgForeignClass, asForeignOperation,
				null, CGParameterStyle.EXECUTOR);
			return cgForeignOperation;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
			CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
			cg2javaVisitor.appendReturn(body);
		}

		@Override
		protected void generateUniqueComputationArguments(@NonNull CG2JavaVisitor cg2javaVisitor, boolean isFirst, @NonNull GlobalNameManager globalNameManager, @NonNull CGOperation cgOperation) {
			throw new IllegalStateException();		// not callable; generateJavaOperationBody overrides
		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			Property asProperty = (Property) asOrigin;
			return asProperty.isIsStatic() ? AS_PARAMETER_STYLES_EXECUTOR : AS_PARAMETER_STYLES_EXECUTOR_SELF;
		}

		@Override
		public void rewriteWithBoxingAndGuards(
				@NonNull BoxingAnalyzer boxingAnalyzer,
				@NonNull CGOperation cgOperation) {
			// TODO Auto-generated method stub
			super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperation);
		}

		@Override
		public void rewriteWithBoxingAndGuards(
				@NonNull BoxingAnalyzer boxingAnalyzer,
				@NonNull CGOperationCallExp cgOperationCallExp) {
			// TODO Auto-generated method stub
			super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
		}

	/*	@Override
		public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty) {
			super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgProperty);
			CGForeignProperty cgForeignProperty = (CGForeignProperty)cgProperty;
			boxingAnalyzer.rewriteAsBoxed(cgForeignProperty.getBody());
			if (cgForeignProperty.isRequired()) {
				CGValuedElement body = cgForeignProperty.getBody();
				if (body != null) {
					boxingAnalyzer.rewriteAsGuarded(body, false, "body for '" + cgForeignProperty.getAst() + "'");
				}
			}
		} */
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert (libraryProperty instanceof StaticProperty)			// test_static_property
			|| (libraryProperty instanceof ForeignProperty);		// test_static_id
	//	assert cgSource == null;
//		CGForeignOperationCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		CGForeignPropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGForeignPropertyCallExp();
		CGElementId cgPropertyId = analyzer.getCGElementId(asProperty.getPropertyId());
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.addReferencedExtraChild(cgPropertyCallExp, cgPropertyId);
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager propertyNameManager, @Nullable ExpressionInOCL initExpression) {
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull Property asProperty) {
		return CGModelFactory.eINSTANCE.createCGForeignProperty();
	}

	@Override
	public void createImplementation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty) {
		// Implemented as a Foreign operation
	}

	@Override
	public @NonNull CGProperty createProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull Property asProperty, @Nullable ExpressionInOCL asExpressionInOCL) {
		CGProperty cgProperty = createCGProperty(analyzer, asProperty);
		cgProperty.setCallingConvention(this);
		analyzer.initAst(cgProperty, asProperty, true);
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asProperty);
		org.eclipse.ocl.pivot.@NonNull Package asParentPackage = analyzer.getRootClassParentPackage(asClass);
		CGClass cgForeignClass = analyzer.getForeignCGClass(asParentPackage);
		CGClass cgClass = analyzer.basicGetCGClass(asClass);
		if (cgClass == null) {
			cgClass = cgForeignClass;				// XXX a fudge to support test_staticProperty ??? no Foreign class
		}
		cgClass.getProperties().add(cgProperty);
		installGetterOperation(analyzer, cgForeignClass, asProperty, asExpressionInOCL);
		return cgProperty;
	}

	@Override
	public boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		CGValuedElement cgBody = ((CGBodiedProperty)cgProperty).getBody();
		assert cgBody == null;
		JavaStream js = cg2javaVisitor.getJavaStream();
		OCLinEcoreCodeGenerator codeGenerator = (OCLinEcoreCodeGenerator)cg2javaVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		Property asProperty = CGUtil.getAST(cgProperty);
		CGOperation cgOperation = analyzer.getForeignCGOperation(asProperty);
		String qualifiedSupportClassName = codeGenerator.getQualifiedSupportClassName();
		String returnClassName = cg2javaVisitor.getGenModelHelper().getPropertyResultType(asProperty);
		//
		js.appendClassReference(null, ExecutorExtension.class);
		js.append(" executor = (");
		js.appendClassReference(null, ExecutorExtension.class);
		js.append(")");
		js.appendClassReference(null, PivotUtil.class);
		js.append(".getExecutor(null);\n");
		//
		js.appendClassReference(null, qualifiedSupportClassName);
		js.append(" support = executor.getExecutionSupport(");
		js.appendClassReference(null, qualifiedSupportClassName);
		js.append(".class);\n");
		//
		js.appendClassReference(null, Object.class);
		js.append(" value = support.");
		js.append(cgOperation.getName());
		js.append("(executor);\n");
		//
		js.appendClassReference(null, Object.class);
		js.append(" ecoreValue = executor.getIdResolver().ecoreValueOf(");			// XXX Collection
		js.appendClassReference(null, returnClassName);
		js.append(".class, value);\n");
		//
		js.append("return (");
		js.appendClassReference(null, returnClassName);
		js.append(")ecoreValue;\n");
		return true;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		CGForeignProperty cgProperty = (CGForeignProperty)CGUtil.getProperty(cgPropertyCallExp);
		Property asProperty = CGUtil.getAST(cgProperty);
		CGOperation cgForeignOperation = analyzer.getForeignCGOperation(asProperty);
		CGValuedElement cgSource = cgPropertyCallExp.getSource();
		//
		if (cgSource != null) {
			CGValuedElement source = cg2javaVisitor.getExpression(cgSource);
			if (!js.appendLocalStatements(source)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		CGClass cgForeignClass = CGUtil.getContainingClass(cgProperty);
	//	js.appendClassReference(cgForeignClass);		// XXX
	//	js.append(".");
		js.appendValueName(cgForeignOperation);
		js.append("(");
		js.append(analyzer.getGlobalNameManager().getExecutorNameResolution().getResolvedName());
		if (cgSource != null) {
			js.append(", ");
			js.appendValueName(cgSource);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		return true;		// The property is reified as an operation; the PropertyCallExp is reified as an operation call
	}

	private void installGetterOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgForeignClass, @NonNull Property asProperty, @Nullable ExpressionInOCL asExpressionInOCL) {
		org.eclipse.ocl.pivot.Class asForeignClass = CGUtil.getAST(cgForeignClass);
		ForeignGetterOperationCallingConvention callingConvention = ForeignGetterOperationCallingConvention.getInstance(asForeignClass);
		CGOperation cgForeignOperation = callingConvention.createOperation(analyzer, cgForeignClass, asProperty, asExpressionInOCL);
		analyzer.addForeignCGOperation(asProperty, cgForeignOperation);
	}
}
