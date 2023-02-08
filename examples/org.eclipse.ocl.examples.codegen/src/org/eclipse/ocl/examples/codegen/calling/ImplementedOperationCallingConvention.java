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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSequence;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.PivotHelper;

/**
 *  ImplementedOperationCallingConvention defines the support for the call of an operation implemented by a Java class.
 */
public class ImplementedOperationCallingConvention extends ExternalOperationCallingConvention
{
	private static final @NonNull ImplementedOperationCallingConvention INSTANCE = new ImplementedOperationCallingConvention();

	public static @NonNull ImplementedOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	public static class ImplementedConstructorOperationCallingConvention extends AbstractConstructorOperationCallingConvention
	{
		private static final @NonNull ImplementedConstructorOperationCallingConvention INSTANCE = new ImplementedConstructorOperationCallingConvention();

		public static @NonNull ImplementedConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgConstructor) {	// merge with super
			Operation asOperation = CGUtil.getAST(cgConstructor);
			Operation asOrigin = analyzer.getOriginalOperation(cgConstructor);
			assert asOperation.getBodyExpression() == null;
			CGClass cgEntryClass = CGUtil.getContainingClass(cgConstructor);
			List<@NonNull CGProperty> cgProperties = CGUtil.getPropertiesList(cgEntryClass);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructor, asOperation, asOrigin);
			//
			PivotHelper asHelper = analyzer.getASHelper();
			CGParameter cgEntryBoxedValuesParameter = operationNameManager.getBoxedValuesParameter();
			CGTypeId cgTypeId = analyzer.getCGTypeId(TypeId.OCL_VOID);
			CGParameter cgThisParameter = operationNameManager.getThisParameter();
			CGSequence cgSequence = CGModelFactory.eINSTANCE.createCGSequence();
			List<@NonNull CGValuedElement> cgSourceAndArguments = new ArrayList<>();
			List<@NonNull CGValuedElement> cgStatements = CGUtil.getOwnedStatementsList(cgSequence);
			Stack<@NonNull CGFinalVariable> cgLetVariables = new Stack<>();
			//
			//	Unpack boxedValues and assign properties.
			//
			int iInclusiveMax = cgProperties.size()-1;
			for (int i = 0; i <= iInclusiveMax; i++) {
				CGProperty cgProperty = cgProperties.get(i);
				Property asProperty = CGUtil.getAST(cgProperty);
				CGValuedElement cgInitValue;
				if (i < iInclusiveMax) {
					//
					//	Unpack boxedValues[i] to a let-variable
					//
					CGVariableExp cgVariableExp = analyzer.createCGVariableExp(cgEntryBoxedValuesParameter);
					CGIndexExp cgCastExp = analyzer.createCGIndexExp(cgVariableExp, i, asProperty);
					CGFinalVariable cgLetVariable = (CGFinalVariable)operationNameManager.lazyGetCGVariable(asProperty);
					analyzer.setCGVariableInit(cgLetVariable, cgCastExp);
					cgProperty.getNameResolution().addCGElement(cgLetVariable);
					cgLetVariables.push(cgLetVariable);
					cgInitValue = analyzer.createCGVariableExp(cgLetVariable);
					cgSourceAndArguments.add(analyzer.createCGVariableExp(cgLetVariable));
				}
				else {
					//
					//	Compute value
					//
					LibraryFeature asImplementation = asOrigin.getImplementation();
					Field field = null;
					Method method = null;
					if (asImplementation instanceof JavaLanguageSupport.JavaNativeOperation) {
						method = ((JavaLanguageSupport.JavaNativeOperation)asImplementation).getMethod();
					}
					else if (asImplementation instanceof AbstractOperation) {
						AbstractOperation abstractOperation = (AbstractOperation)asImplementation;
						field = abstractOperation.getInstanceField();
						method = abstractOperation.getEvaluateMethod(asOrigin);
						int j = 0;
						for (Class<?> jParameterType : method.getParameterTypes()) {
							if (jParameterType == Executor.class) {
								CGVariable cgExecutorVariable = operationNameManager.lazyGetExecutorVariable();
								cgSourceAndArguments.add(j, analyzer.createCGVariableExp(cgExecutorVariable));
							}
							else if (jParameterType == TypeId.class) {
								CGValuedElement cgConstantExp = analyzer.createCGConstantExp(analyzer.getCGTypeId(asOrigin.getTypeId()));
								cgSourceAndArguments.add(j, cgConstantExp);
							}
							j++;
						}
					}
					assert method != null;			// XXX
					NativePropertyCallingConvention nativePropertyCallingConvention = NativePropertyCallingConvention.getInstance(asOperation);
					NativeOperationCallingConvention nativeOperationCallingConvention = NativeOperationCallingConvention.getInstance(asOperation, false);

					CGNativeOperationCallExp cgNativeOperationCallExp = analyzer.createCGNativeOperationCallExp(method, nativeOperationCallingConvention);
					cgNativeOperationCallExp.setTypeId(analyzer.getCGTypeId(asOrigin.getTypeId()));
					cgNativeOperationCallExp.setRequired(asOrigin.isIsRequired());
					cgNativeOperationCallExp.getArguments().addAll(cgSourceAndArguments);
					if (field != null) {
						CGNativePropertyCallExp cgNativePropertyCallExp = analyzer.createCGNativePropertyCallExp(field, nativePropertyCallingConvention);
						cgNativePropertyCallExp.setSource(null);
					//	cgNativePropertyCallExp.setTypeId(analyzer.getCGTypeId(asOrigin.getOwningClass().getTypeId()));
					//	cgNativePropertyCallExp.setRequired(true);
						cgNativeOperationCallExp.setCgThis(cgNativePropertyCallExp);
					}


					//	OCLExpression asEntryResult = asHelper.createOperationCallExp(null, asOrigin, asArguments);
				//	OCLExpression asEntryResult = asHelper.createIntegerLiteralExp(77) ; //asEntryExpressionInOCL.getOwnedBody();
					cgInitValue = cgNativeOperationCallExp;
				}
				//
				//	Assign  property from let-variable / computation
				//
				CGPropertyAssignment cgPropertyAssignment = CGModelFactory.eINSTANCE.createCGPropertyAssignment();
				cgPropertyAssignment.setAst(asProperty);
				cgPropertyAssignment.setTypeId(cgTypeId);
				cgPropertyAssignment.setOwnedSlotValue(analyzer.createCGVariableExp(cgThisParameter));
				cgPropertyAssignment.setReferredProperty(cgProperty);
				cgPropertyAssignment.setOwnedInitValue(cgInitValue);
				//	cgPropertyAssignment.setAsProperty(asProperty);
				cgStatements.add(cgPropertyAssignment);
				//	cgProperty.getNameResolution().addCGElement(cgCastExp);
			}
			//
			//	Wrap unpacked let-variables as let-expressions around sequenced assignments.
			//
			CGValuedElement cgBody = cgSequence;
			while (!cgLetVariables.isEmpty()) {
				CGFinalVariable cgLetVariable = cgLetVariables.pop();
				cgBody = analyzer.createCGLetExp(cgLetVariable, cgBody);
			}
			cgConstructor.setBody(cgBody);

		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			// TODO Auto-generated method stub
			return super.getASParameterStyles(asOrigin);
		}

		@Override
		protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
			Operation asOrigin = (Operation) operationNameManager.getASOrigin();
		//	LibraryFeature asImplementation = asOrigin.getImplementation();
			LibraryOperation asImplementation = (LibraryOperation)operationNameManager.getCodeGenerator().getEnvironmentFactory().getMetamodelManager().getImplementation(asOrigin);
			Method method = null;
		//	boolean hasExecutor = false;
			boolean hasTypeId = false;
		/*	if (asImplementation instanceof AbstractOperation) {
				method = ((AbstractOperation)asImplementation).getEvaluateMethod(asOrigin);
				for (Class<?> jParameterType : method.getParameterTypes()) {
					if (jParameterType == Executor.class) {
				//		hasExecutor = true;
					}
					else if (jParameterType == TypeId.class) {
						hasTypeId = true;
					}
				}
			} */
			if (hasTypeId) {
			//	assert hasExecutor;
				return CG_PARAMETER_STYLES_THIS_TYPE_ID_BOXED_VALUES;
			}
		//	else if (hasExecutor) {
		//		return CG_PARAMETER_STYLES_THIS_EXECUTOR_BOXED_VALUES;
		//	}
			else {
				return CG_PARAMETER_STYLES_THIS_BOXED_VALUES;
			}
		}
	}

	/**
	 *  ExternalEntryClassCallingConvention refines the standard EntryClassCallingConvention for the cache of a specific evaluation
	 *  to support a local class for an non-local facility.
	 */
	public static class ImplementedEntryClassCallingConvention extends ExternalEntryClassCallingConvention
	{
		private static final @NonNull ImplementedEntryClassCallingConvention INSTANCE = new ImplementedEntryClassCallingConvention();

		public static @NonNull ImplementedEntryClassCallingConvention getInstance(@NonNull Operation asOperation) {
			INSTANCE.logInstance(asOperation);
			return INSTANCE;
		}

		@Override
		protected void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
			AbstractConstructorOperationCallingConvention callingConvention = ImplementedConstructorOperationCallingConvention.getInstance(asEntryClass);
			callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
		}
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOuterOperation) {
		// direct synthesis
	}

	@Override
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, @Nullable ExpressionInOCL asExpressionInOCL) {// XXX cf super
	//	assert asOperation.getImplementationClass() == null;
		CGOperation cgOperation = createCGOperation(analyzer, asOperation);
		analyzer.initAst(cgOperation, asOperation, true);
		CGClass cgRootClass = analyzer.getCGRootClass(asOperation);
		cgRootClass.getOperations().add(cgOperation);
		createCachingClassesAndInstance(analyzer, cgOperation);
		cgOperation.setCallingConvention(this);
		assert asOperation == cgOperation.getAst();
		assert analyzer.basicGetCGElement(asOperation) != null;
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation, null);	// Needed to support downstream useOperationNameManager()
		assert cgOperation.eContainer() != null;
		@NonNull CGParameterStyle @NonNull [] cgParameterStyles = getCGParameterStyles(operationNameManager);
		operationNameManager.createCGOperationParameters(cgParameterStyles);
		return cgOperation;
	}

//	@Override
//	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
//		return CG_PARAMETER_STYLES_SELF;
//	}

	@Override
	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return ImplementedEntryClassCallingConvention.getInstance(asOperation);
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX Auto-generated method stub
			super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
	}
}
