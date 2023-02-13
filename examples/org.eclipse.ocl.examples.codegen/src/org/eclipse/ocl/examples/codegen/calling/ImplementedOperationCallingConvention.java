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
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSequence;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

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
			Operation asOrigin = null;  // XXX analyzer.getOriginalOperation(cgConstructor);
		//	ExpressionInOCL asCacheExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
			assert asOperation.getBodyExpression() == null;
		//	JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		//	GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		//	List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgConstructor);
			CGClass cgEntryClass = CGUtil.getContainingClass(cgConstructor);
			List<@NonNull CGProperty> cgProperties = CGUtil.getPropertiesList(cgEntryClass);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructor, asOperation, asOrigin);
		//	ExpressionInOCL asEntryExpressionInOCL = (ExpressionInOCL)asEntryOperation.getBodyExpression();
		//	assert (asEntryExpressionInOCL != null);
		//	List<@NonNull Variable> asEntryParameterVariables = PivotUtilInternal.getOwnedParametersList(asEntryExpressionInOCL);
			//
			List<@NonNull Parameter> asEntryParameters = PivotUtilInternal.getOwnedParametersList(asOperation);
		/*	CGVariable executorVariable = operationNameManager.lazyGetExecutorVariable();


			Parameter asExecutorParameter = asEntryParameters.get(0);
			CGParameter cgEntryExecutorParameter = operationNameManager.lazyGetCGParameter(asExecutorParameter);
			globalNameManager.getExecutorNameResolution().addCGElement(cgEntryExecutorParameter);
			cgParameters.add(cgEntryExecutorParameter);
			Parameter asBoxedValuesParameter = asEntryParameters.get(1);
			CGParameter cgEntryBoxedValuesParameter = operationNameManager.lazyGetCGParameter(asBoxedValuesParameter);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgEntryBoxedValuesParameter);
			cgParameters.add(cgEntryBoxedValuesParameter); */

			CGParameter cgEntryBoxedValuesParameter = operationNameManager.getBoxedValuesParameter();

			CGTypeId cgTypeId = analyzer.getCGTypeId(TypeId.OCL_VOID);
			CGParameter cgThisParameter = operationNameManager.getThisParameter();
			CGSequence cgSequence = CGModelFactory.eINSTANCE.createCGSequence();
			List<@NonNull CGValuedElement> cgStatements = CGUtil.getOwnedStatementsList(cgSequence);
			Stack<@NonNull CGFinalVariable> cgLetVariables = new Stack<>();
			//
			//	Unpack boxedValues and assign properties.
			//
		//	VariableDeclaration asThisParameterVariable = null;  //PivotUtil.get     (ParameterVariable)asEntryExpressionInOCL.getOwnedContext();
		//	assert asThisParameterVariable != null;		// Must have a Java 'this' to initialize its properties
			// FIXME wrong type
			int iInclusiveMax = cgProperties.size()-1;
			for (int i = 0; i <= iInclusiveMax; i++) {
				CGProperty cgProperty = cgProperties.get(i);
				Property asProperty = CGUtil.getAST(cgProperty);
				CGValuedElement cgInitValue;
				if (i >= iInclusiveMax) {
					OCLExpression asEntryResult = analyzer.getASHelper().createIntegerLiteralExp(77) ; //asEntryExpressionInOCL.getOwnedBody();
					cgInitValue = analyzer.createCGElement(CGValuedElement.class, asEntryResult);
				}
				else {
				//	TypedElement asEntryParameterVariable;
				//	if (i == 0) {
				//		asEntryParameterVariable = asThisParameterVariable;
				//	}
				//	else {
				//		asEntryParameterVariable = asEntryParameters.get(i-1);
				//	}
				//	assert asEntryParameterVariable != null;
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
			return CG_PARAMETER_STYLES_THIS_BOXED_VALUES;
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
		//	super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
	}
}
