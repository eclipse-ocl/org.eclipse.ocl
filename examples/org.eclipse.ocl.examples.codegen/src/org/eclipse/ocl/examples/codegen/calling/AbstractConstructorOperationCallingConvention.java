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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSequence;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ConstructorOperationCallingConvention defines the support for the call of a cache constructor.
 */
public abstract class AbstractConstructorOperationCallingConvention extends AbstractUncachedOperationCallingConvention
{
/*	public static class DefaultConstructorOperationCallingConvention extends AbstractConstructorOperationCallingConvention
	{
		private static final @NonNull DefaultConstructorOperationCallingConvention INSTANCE = new DefaultConstructorOperationCallingConvention();

		public static @NonNull DefaultConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}
	} */

	@Override
	protected void appendBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGValuedElement body) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.append(" {\n");
		js.pushIndentation(null);
		if (cg2javaVisitor.getCodeGenerator().getOptions().isIncremental()) {
			js.append("super(");
			js.appendString(((CGNamedElement)body.eContainer()).getResolvedName());
			js.append(");\n");
		}
		//	cg2javaVisitor.appendReturn(body);
		if (js.appendLocalStatements(body)) {
			if (body instanceof CGThrowExp) {				// FIXME generalize
				body.accept(cg2javaVisitor);
			}
			else {
				CGInvalid cgInvalidValue = body.getInvalidValue();
				if (cgInvalidValue != null) {
					js.append("throw ");
					js.appendValueName(cgInvalidValue);
					js.append(";\n");
				}
				//	else {
				//		js.append("return ");
				//		js.appendValueName(body);
				//	}
			}
		}
		js.popIndentation();
		js.append("}\n");
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgConstructor) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgConstructor);
		CGClass cgEntryClass = CGUtil.getContainingClass(cgConstructor);
		List<@NonNull CGProperty> cgProperties = CGUtil.getPropertiesList(cgEntryClass);
		Operation asEntryOperation = CGUtil.getAST(cgConstructor);
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructor, asEntryOperation);
		ExpressionInOCL asEntryExpressionInOCL = (ExpressionInOCL)asEntryOperation.getBodyExpression();
		assert (asEntryExpressionInOCL != null);
		List<@NonNull Variable> asEntryParameterVariables = PivotUtilInternal.getOwnedParametersList(asEntryExpressionInOCL);
		//
		List<@NonNull Parameter> asEntryParameters = PivotUtilInternal.getOwnedParametersList(asEntryOperation);
		Parameter asExecutorParameter = asEntryParameters.get(0);
		CGParameter cgEntryExecutorParameter = operationNameManager.getCGParameter(asExecutorParameter, (String)null);
		globalNameManager.getExecutorNameResolution().addCGElement(cgEntryExecutorParameter);
		cgParameters.add(cgEntryExecutorParameter);
		Parameter asBoxedValuesParameter = asEntryParameters.get(1);
		CGParameter cgEntryBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
		globalNameManager.getBoxedValuesNameResolution().addCGElement(cgEntryBoxedValuesParameter);
		cgParameters.add(cgEntryBoxedValuesParameter);
		CGTypeId cgTypeId = analyzer.getCGTypeId(TypeId.OCL_VOID);
		CGParameter cgThisParameter = operationNameManager.getThisParameter();
		CGSequence cgSequence = CGModelFactory.eINSTANCE.createCGSequence();
		List<@NonNull CGValuedElement> cgStatements = CGUtil.getOwnedStatementsList(cgSequence);
		Stack<@NonNull CGFinalVariable> cgLetVariables = new Stack<>();
		//
		//	Unpack boxedValues and assign properties.
		//
		ParameterVariable asThisParameterVariable = (ParameterVariable)asEntryExpressionInOCL.getOwnedContext();
		assert asThisParameterVariable != null;		// Must have a Java 'this' to initialize its properties
		// FIXME wrong type
		int iInclusiveMax = cgProperties.size()-1;
		for (int i = 0; i <= iInclusiveMax; i++) {
			CGProperty cgProperty = cgProperties.get(i);
			Property asProperty = CGUtil.getAST(cgProperty);
			CGValuedElement cgInitValue;
			if (i >= iInclusiveMax) {
				OCLExpression asEntryResult = asEntryExpressionInOCL.getOwnedBody();
				cgInitValue = analyzer.createCGElement(CGValuedElement.class, asEntryResult);
			}
			else {
				ParameterVariable asEntryParameterVariable;
				if (i == 0) {
					asEntryParameterVariable = asThisParameterVariable;
				}
				else {
					asEntryParameterVariable = (ParameterVariable)asEntryParameterVariables.get(i-1);
				}
				assert asEntryParameterVariable != null;
				//
				//	Unpack boxedValues[i] to a let-variable
				//
				CGVariableExp cgVariableExp = analyzer.createCGVariableExp(cgEntryBoxedValuesParameter);
				CGIndexExp cgCastExp = analyzer.createCGIndexExp(cgVariableExp, i, asProperty);
				CGFinalVariable cgLetVariable = (CGFinalVariable)operationNameManager.lazyGetCGVariable(asEntryParameterVariable);
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
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		//	assert asOperation instanceof NullOperationImpl;
		//		return fallbackCreateCGOperationWithoutBody(as2cgVisitor, asOperation);
		//	PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		//	LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		//	assert !(libraryOperation instanceof EObjectOperation);
		//	assert !(libraryOperation instanceof ForeignOperation);
		//	assert !(libraryOperation instanceof ConstrainedOperation);
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			CGIsInvalidExp cgIsInvalidExp = CGModelFactory.eINSTANCE.createCGIsInvalidExp();
			cgIsInvalidExp.setSource(cgSource);
			analyzer.initAst(cgIsInvalidExp, asOperationCallExp, true);
			//	as2cgVisitor.declareLazyName(cgIsInvalidExp);
			cgIsInvalidExp.setInvalidating(false);
			cgIsInvalidExp.setValidating(true);
			return cgIsInvalidExp;
		}
		if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			CGIsUndefinedExp cgIsUndefinedExp = CGModelFactory.eINSTANCE.createCGIsUndefinedExp();
			cgIsUndefinedExp.setSource(cgSource);
			analyzer.initAst(cgIsUndefinedExp, asOperationCallExp, true);
			//	as2cgVisitor.declareLazyName(cgIsUndefinedExp);
			cgIsUndefinedExp.setInvalidating(false);
			cgIsUndefinedExp.setValidating(true);
			return cgIsUndefinedExp;
		}
		if (libraryOperation instanceof OclAnyEqualOperation) {
			OCLExpression pArgument = PivotUtil.getOwnedArgument(asOperationCallExp, 0);
			CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, pArgument);
			CGIsEqualExp cgIsEqualExp = CGModelFactory.eINSTANCE.createCGIsEqualExp();
			cgIsEqualExp.setNotEquals(libraryOperation instanceof OclAnyNotEqualOperation);
			cgIsEqualExp.setSource(cgSource);
			cgIsEqualExp.setArgument(cgArgument);
			analyzer.initAst(cgIsEqualExp, asOperationCallExp, true);
			//	as2cgVisitor.declareLazyName(cgIsEqualExp);
			cgIsEqualExp.setInvalidating(false);
			cgIsEqualExp.setValidating(true);
			return cgIsEqualExp;
		}
		throw new IllegalStateException("Unsupported built-in " + libraryOperation);
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Create the AS entry operation, AS body and CG operation declaration for the AS operation within cgEntryClass.
	 */
	protected /*final*/ @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		//
		// AS Class - yyy2zzz
		// AS Properties -
		// AS Operation - f
		// AS Operation.ownedParameters - x1, x2
		// AS Entry Operation - newInstance
		// AS Entry Operation.parameters - boxedValues
		// AS Entry ExpressionInOCL.ownedContext - this for AS Entry class
		// AS Entry ExpressionInOCL.ownedParameters - x1, x2
		// CG Entry Operation - AS Entry class name
		// CG Entry Operation.lets -
		//
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
		//
		NameResolution ctorNameResolution = cgEntryClass.getNameResolution();
		String ctorName = ctorNameResolution.getResolvedName();
		Operation asEntryConstructor = createASOperationDeclaration(analyzer, asEntryClass, asOperation,
			ctorName, ASResultStyle.VOID);
		//
		//	Wrap a copy of the original constructor bodies in a let expression per constructor parameter.
		//
		ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
		if (asExpressionInOCL != null) {
			ExpressionInOCL asEntryExpressionInOCL = EcoreUtil.copy(asExpressionInOCL);		// Copy replaces the ParameterVariables too.
			Variable asContextVariable = asEntryExpressionInOCL.getOwnedContext();
			if (asContextVariable == null) {			// QVTi FunctionBody has eager ownedContext, OCL ExpressionInOCL is lazy
				PivotHelper asHelper = codeGenerator.getASHelper();
				GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
				String thisName = globalNameManager.getThisNameResolution().getResolvedName();
				asContextVariable = asHelper.createParameterVariable(thisName, asEntryClass, true);
				asEntryExpressionInOCL.setOwnedContext(asContextVariable);
			}
			else {
				asContextVariable.setType(asEntryClass);		// Change copied context from context class to entry class
			}
			assert asContextVariable.isIsRequired();
			assert asContextVariable.getTypeValue() == null;
			asEntryConstructor.setBodyExpression(asEntryExpressionInOCL);
		}
		else {
			assert asOperation.getImplementationClass() != null;
		}
		//
		CGOperation cgConstructor = createCGOperationDeclaration(analyzer, cgEntryClass, asEntryConstructor,
			ctorNameResolution, CG_PARAMETER_STYLES);
/*		CGOperation cgConstructor = createCGOperation(analyzer, asEntryConstructor);
		cgConstructor.setCallingConvention(this);
		analyzer.initAst(cgConstructor, asEntryConstructor, true);
		ctorNameResolution.addCGElement(cgConstructor);
		analyzer.getOperationNameManager(cgConstructor, asEntryConstructor);
		//
		cgEntryClass.getOperations().add(cgConstructor); */
		return cgConstructor;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGClass cgEntryClass = CGUtil.getContainingClass(cgOperation);
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		Operation asOperation = cg2javaVisitor.getAnalyzer().getCachedOperation(asEntryClass);
		LanguageExpression asExpression = asOperation.getBodyExpression();
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		js.appendCommentWithOCL(null, asExpression);
		js.append("protected ");
		js.appendValueName(cgOperation);
		appendParameterList(js, cgOperation);
		appendBody(cg2javaVisitor, body);
		return true;
	}

	@Override
	protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
		return AS_PARAMETER_STYLES_EXECUTOR_BOXED_VALUES_ALL;
	}

	@Override
	public boolean needsGeneration() {
		return true;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		//	CGValuedElement body = cgOperation.getBody();
		//	assert body instanceof CGSequence;		-- no value
	}
}
