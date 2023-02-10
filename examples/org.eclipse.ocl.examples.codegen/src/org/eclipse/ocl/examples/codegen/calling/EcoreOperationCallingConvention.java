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

import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.EInvokeOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  EcoreOperationCallingConvention defines the support for the call of an Ecore operation within its *Impl file,
 *  </br>
 *  e.g. as eObject.eOperation(eArguments)
 */
public class EcoreOperationCallingConvention extends AbstractUncachedOperationCallingConvention
{
	private static final @NonNull EcoreOperationCallingConvention INSTANCE = new EcoreOperationCallingConvention();

	public static @NonNull EcoreOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	public boolean canHandle(@NonNull CodeGenerator codeGenerator, @NonNull Operation asOperation) {
		GenModelHelper genModelHelper = codeGenerator.getGenModelHelper();
		try {
			genModelHelper.getOperationAccessor(asOperation);
			return true;
		} catch (GenModelException e) {
			codeGenerator.addProblem(e);
		}
		return false;
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		Element asOperation = cgOperation.getAst();
		ExpressionInOCL asSpecification = (ExpressionInOCL) (asOperation instanceof ExpressionInOCL ? asOperation : ((Operation)asOperation).getBodyExpression());
		if (asSpecification != null) {
			OCLExpression asExpression = PivotUtil.getOwnedBody(asSpecification);
			CGValuedElement cgBody = analyzer.createCGElement(CGValuedElement.class, asExpression);
			cgOperation.setBody(cgBody);
		//	System.out.println("setBody " + NameUtil.debugSimpleName(cgOperation) + " : " + cgBody);
		}
	}

	@Override
	public @NonNull CGEcoreOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return CGModelFactory.eINSTANCE.createCGEcoreOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		EOperation eOperation = (EOperation) asOperation.getESObject();
		assert eOperation != null;
		boolean isRequired = asOperation.isIsRequired();
		GenModelHelper genModelHelper = analyzer.getGenModelHelper();
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		try {
			genModelHelper.getOperationAccessor(asOperation);
			Boolean ecoreIsRequired = codeGenerator.isNonNull(asOperationCallExp);
			if (ecoreIsRequired != null) {
				isRequired = ecoreIsRequired;
			}
			CGEcoreOperationCallExp cgEcoreOperationCallExp = CGModelFactory.eINSTANCE.createCGEcoreOperationCallExp();
			cgEcoreOperationCallExp.setEOperation(eOperation);
			initCallExp(analyzer, cgEcoreOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		//	cgEcoreOperationCallExp.setCgThis(cgSource);
			cgEcoreOperationCallExp.getArguments().add(cgSource);
			initCallArguments(analyzer, cgEcoreOperationCallExp);
			return cgEcoreOperationCallExp;
		} catch (GenModelException e) {
			throw new IllegalStateException(e);
		}
	}

/*	@Override
	protected @NonNull CGParameter createCGParameter(@NonNull ExecutableNameManager operationNameManager, @NonNull Variable asParameterVariable) {
		CGParameter cgParameter = operationNameManager.getCGParameter(asParameterVariable, PivotUtil.getName(asParameterVariable));
		operationNameManager.declareEagerName(cgParameter);
		return cgParameter;
	}*/

	@Override
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, @Nullable ExpressionInOCL asExpressionInOCL) {
		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		GenModelHelper genModelHelper = analyzer.getGenModelHelper();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		EOperation eOperation;
		if (libraryOperation instanceof EInvokeOperation) {
			eOperation = ((EInvokeOperation)libraryOperation).getEOperation();
		}
		else {
			assert (libraryOperation instanceof EObjectOperation);
		// System.out.println("Non EInvokeOperation overload for " + this);		// XXX
			eOperation = (EOperation) asOperation.getESObject();
		}
		assert (eOperation != null);
		assert !PivotUtil.isStatic(eOperation);
		CGOperation cgOperation = null;
		try {
			genModelHelper.getGenOperation(eOperation);
			CGEcoreOperation cgEcoreOperation = createCGOperation(analyzer, asOperation);
			cgEcoreOperation.setEOperation(eOperation);
			cgOperation = cgEcoreOperation;
		}
		catch (GenModelException e) {
			// No genmodel so fallback
		}
		if (cgOperation == null) {
			//	assert false : "Fallback overload for " + this;		// XXX
			System.out.println("Fallback overload for " + this);		// XXX
			cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		}

		assert cgOperation.getCallingConvention() == null;
		cgOperation.setCallingConvention(this);
		Element asOperation2 = cgOperation.getAst();
		assert asOperation2 == null;
		assert analyzer.basicGetCGElement(asOperation) == null;
		analyzer.initAst(cgOperation, asOperation, true);
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation, null);	// Needed to support downstream useOperationNameManager()
		assert cgOperation.eContainer() == null;
		CGClass cgClass = analyzer.getCGClass(PivotUtil.getOwningClass(asOperation));
		cgClass.getOperations().add(cgOperation);
		@NonNull CGParameterStyle @NonNull [] cgParameterStyles = getCGParameterStyles(operationNameManager);
		operationNameManager.createCGOperationParameters(cgParameterStyles);
		return cgOperation;
	}

	@Override
	public boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		if (cgOperation.getBody() == null) {
			return false;
		}
		cgOperation.accept(cg2javaVisitor);
		return true;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
	//	Operation asOperation = cgOperationCallExp.getReferredOperation();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		GenModelHelper genModelHelper = cg2javaVisitor.getGenModelHelper();
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asOperation.getOwningClass().getTypeId());
		//		TypeDescriptor requiredTypeDescriptor = context.getUnboxedDescriptor(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
	//	CGValuedElement cgThis = cg2javaVisitor.getExpression(cgOperationCallExp.getCgThis());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
	//	List<@NonNullParameter> asParameters = asOperation.getOwnedParameters();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		//
	//	if (!js.appendLocalStatements(cgThis)) {
	//		return false;
	//	}
		if (!generateLocals(cg2javaVisitor, cgOperationCallExp)) {
			return false;
		}
		//
		String operationAccessor = genModelHelper.getOperationAccessor(asOperation);
		//		Class<?> actualBoxedReturnClass = getBoxedReturnClass(libraryOperation, arguments.size());
		//		Class<?> unboxedSourceClass;
		//		try {		// FIXME this peeking is only needed for the Pivot Domain/non-Domain levels
		//			unboxedSourceClass = genModelHelper.getEcoreInterfaceClass(eOperation.getEContainingClass());
		//		}
		//		catch (GenModelException e) {
		//			unboxedSourceClass = getJavaClass(source);
		//		}
		Element asOperationCallExp = cgOperationCallExp.getAst();
		Boolean ecoreIsRequired = asOperationCallExp instanceof OperationCallExp ? codeGenerator.isNonNull((OperationCallExp) asOperationCallExp) : null;
		cg2javaVisitor.appendSuppressWarningsNull(cgOperationCallExp, ecoreIsRequired);
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		int iMax = Math.min(cgParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			CGParameter cgParameter = cgParameters.get(i);
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				js.appendAtomicReferenceTo(requiredTypeDescriptor, cgArgument);
				js.append(".");
				js.append(operationAccessor);
				js.append("(");
			}
			else {
				if (i > 1) {
					js.append(", ");
				}
				CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			//	Parameter asParameter = ClassUtil.nonNullState(asParameters.get(i));
				Parameter asParameter = CGUtil.getParameter(cgParameter);
				GenParameter genParameter = genModelHelper.getGenParameter(asParameter);
				if (genParameter != null) {
					String rawBoundType = ClassUtil.nonNullState(genParameter.getRawBoundType());
					TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(argument);
					typeDescriptor.appendEcoreValue(js, rawBoundType, argument);
				}
				else {	// ? never happens
					CGTypeId cgParameterTypeId = analyzer.getCGTypeId(asParameter.getTypeId());
					TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgParameterTypeId.getElementId()));
					js.appendReferenceTo(parameterTypeDescriptor, argument);

				}
			}
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		List<CGParameter> cgParameters = cgOperation.getParameters();
		CGValuedElement cgBody = cgOperation.getBody();
		if (cgBody == null) {
			return false;
		}
		String returnClassName = cg2javaVisitor.getGenModelHelper().getOperationReturnType(asOperation);
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.appendCommentWithOCL(null, cgBody.getAst());
		js.append("// " + cgOperation.getCallingConvention() + "\n");
		for (@SuppressWarnings("null")@NonNull CGParameter cgParameter : cgParameters) {
			VariableDeclaration asParameter = CGUtil.getAST(cgParameter);
			Type asType = PivotUtil.getType(asParameter);
			if (asType instanceof CollectionType) {
				js.append("assert ");
				js.appendValueName(cgParameter);
				js.append(" != null;\n");
			}
		}
		js.appendLocalStatements(cgBody);
		CGInvalid cgInvalidValue = cgBody.getInvalidValue();
		if (cgInvalidValue  != null) {
			js.append("throw ");
			js.appendValueName(cgInvalidValue);
		}
		else {
			TypeDescriptor typeDescriptor = cg2javaVisitor.getCodeGenerator().getTypeDescriptor(cgBody);
			//			String className = typeDescriptor.getClassName();
			//			Class<?> javaClass = typeDescriptor.getJavaClass();
			js.append("return ");
			//			if (returnClassName.contains("<")) {
			//				js.append("(" + returnClassName + ")");
			//			}
			//			js.appendValueName(cgBody);
			typeDescriptor.appendEcoreValue(js, returnClassName, cgBody);
		}
		js.append(";");
		return true;
	}

	@Override
	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
		Operation asOperation = (Operation)operationNameManager.getASScope();
		ExpressionInOCL bodyExpression = (ExpressionInOCL)asOperation.getBodyExpression();
		if (bodyExpression != null) {
			Variable asContextVariable = bodyExpression.getOwnedContext();
			if (asContextVariable != null) {
				return CG_PARAMETER_STYLES_SELF_THIS_EAGER_PARAMETERS;
			}
			else {
				return CG_PARAMETER_STYLES_EAGER_PARAMETERS;
			}
		}
		else {
			if (!asOperation.isIsStatic()) {
				return CG_PARAMETER_STYLES_SELF_EAGER_PARAMETERS;
			}
			else {
				return CG_PARAMETER_STYLES_EAGER_PARAMETERS;
			}
		}
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		CGEcoreOperation cgEcoreOperation = (CGEcoreOperation)cgOperation;
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgEcoreOperation);
		CGValuedElement body = cgEcoreOperation.getBody();
		if (body != null) {
			boxingAnalyzer.rewriteAsEcore(body, cgEcoreOperation.getEOperation().getEType());
		}
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGEcoreOperationCallExp cgEcoreOperationCallExp = (CGEcoreOperationCallExp)cgOperationCallExp;
		if ("specializeIn".equals(cgEcoreOperationCallExp.getAsOperation().getName())) {
			getClass();		// XXX
		}
		CGOperation cgOperation = CGUtil.getOperation(cgEcoreOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		EOperation eOperation = cgEcoreOperationCallExp.getEOperation();
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgEcoreOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGParameter cgParameter = cgParameters.get(i);
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				boxingAnalyzer.rewriteAsGuarded(cgArgument, boxingAnalyzer.isSafe(cgEcoreOperationCallExp), "source for '" + asOperation + "'");
				boxingAnalyzer.rewriteAsEcore(cgArgument, eOperation.getEContainingClass());
			}
			else {
				Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
				if (asParameter != null) {
					EParameter eParameter = (EParameter) asParameter.getESObject();
				boxingAnalyzer.rewriteAsEcore(cgArgument, eParameter.getEType());
			}
			}
		}
		if (eOperation.isMany()) {
			boxingAnalyzer.rewriteAsAssertNonNulled(cgEcoreOperationCallExp);
		}
	}
}
