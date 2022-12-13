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
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ConstructorOperationCallingConvention defines the support for the call of a cache constructor.
 */
public class ConstructorOperationCallingConvention extends AbstractOperationCallingConvention
{
	private static final @NonNull ConstructorOperationCallingConvention INSTANCE = new ConstructorOperationCallingConvention();

//	public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//		INSTANCE.logInstance(asOperation, maybeVirtual);
//	}

	public static @NonNull ConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		INSTANCE.logInstance(asClass);
		return INSTANCE;
	}

	@Override
	protected void appendBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGValuedElement body) {
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
		Parameter asBoxedValuesParameter = PivotUtilInternal.getOwnedParametersList(asEntryOperation).get(0);
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
				Type asType = PivotUtil.getType(asProperty);
				CGExecutorType cgExecutorType = operationNameManager.getCGExecutorType(asType);
				//
				//	Unpack boxedValues[i] to a let-variable
				//
				CGVariableExp cgVariableExp = analyzer.createCGVariableExp(cgEntryBoxedValuesParameter);
				CGIndexExp cgIndexExp = analyzer.createCGIndexExp(cgVariableExp, i);
				cgIndexExp.setAst(asProperty);
				CGCastExp cgCastExp = analyzer.createCGCastExp(cgExecutorType, cgIndexExp);
				cgCastExp.setAst(asProperty);
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
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		assert bodyExpression == null;
		Operation asOperation = CGUtil.getAST(cgOperation);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		//	cgParameters.add(qvtiOperationNameManager.getThisTransformerParameter());
		if (!asOperation.isIsStatic()) {
			//XXX			CGParameter cgParameter = nameManager.getSelfParameter();
			//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(contextVariable.isIsRequired());
			//			cgParameters.add(cgParameter);
		}
		for (@NonNull Parameter parameterVariable : ClassUtil.nullFree(asOperation.getOwnedParameters())) {
			CGParameter cgParameter = operationNameManager.getCGParameter(parameterVariable, (String)null);
			cgParameters.add(cgParameter);
		}
	}

	protected /*final*/ @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		//
		// AS Class - yyy2zzz
		// AS Properties -
		// AS Operation - yyy2zzz
		// AS Operation.ownedParameters - x1, x2
		// AS Entry Operation - newInstance
		// AS Entry Operation.parameters - boxedValues
		// AS Entry ExpressionInOCL.ownedContext - this
		// AS Entry ExpressionInOCL.ownedParameters - x1, x2
		// CG Entry Operation - newInstance
		// CG Entry Operation.lets -
		//
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
		org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
		//
		NameResolution ctorNameResolution = cgEntryClass.getNameResolution();
		String ctorName = ctorNameResolution.getResolvedName();
		Type asDummyType = environmentFactory.getStandardLibrary().getOclVoidType();
		Operation asEntryConstructor = PivotUtil.createOperation(ctorName, asDummyType, null, null);
		Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator);
		asEntryConstructor.getOwnedParameters().add(asBoxedValuesParameter);
		asEntryClass.getOwnedOperations().add(asEntryConstructor);
		//
		//	Wrap a copy of the original constructor bodies in a let expression per constructor parameter.
		//
		ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
		if (asExpressionInOCL != null) {
			ExpressionInOCL asEntryExpressionInOCL = EcoreUtil.copy(asExpressionInOCL);
			OCLExpression asEntryResult = asEntryExpressionInOCL.getOwnedBody();
			assert asEntryResult != null;
			PivotUtilInternal.resetContainer(asEntryResult);
			asEntryExpressionInOCL.setOwnedBody(asEntryResult);
			asEntryConstructor.setBodyExpression(asEntryExpressionInOCL);
			Variable asVariableVariable = asEntryExpressionInOCL.getOwnedContext();
			assert asVariableVariable != null;
			assert asVariableVariable.isIsRequired();
			assert asVariableVariable.getTypeValue() == null;
			asVariableVariable.setType(asEntryClass);		// Move from Transformation to Entry class
		}
		else {
			assert asOperation.getImplementationClass() != null;
		}
		//
		CGOperation cgConstructor = createCGOperation(analyzer, asEntryConstructor);
		cgConstructor.setCallingConvention(this);
		analyzer.initAst(cgConstructor, asEntryConstructor, true);
		ctorNameResolution.addCGElement(cgConstructor);
		analyzer.getOperationNameManager(cgConstructor, asEntryConstructor);
		cgEntryClass.getOperations().add(cgConstructor);
		//
		//	createCGBody(analyzer, cgConstructor);
		//	analyzer.scanBody(asEntryResult);
		return cgConstructor;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
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
		appendBody(cg2javaVisitor, js, body);
		return true;
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
