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

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ForeignOperationCallingConvention defines the support for the call of an operation realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ForeignOperationCallingConvention extends AbstractOperationCallingConvention	// cf ConstrainedOperationCallingConvention
{
	public static final @NonNull ForeignOperationCallingConvention INSTANCE = new ForeignOperationCallingConvention();

/*	@Override
	protected void appendDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		appendCommentWithOCL(js, cgOperation);
		//
		js.append("public static ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" op_");
		js.appendValueName(cgOperation);
	} */

	protected void appendForeignOperationName(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asReferredOperation = CGUtil.getReferredOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".");
		js.appendValueName(cgOperation);
	}

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
 /*		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert !(libraryOperation instanceof EObjectOperation);
		assert (libraryOperation instanceof ForeignOperation);
		EOperation eOperation = (EOperation) asOperation.getESObject();
		if (eOperation != null) {
			boolean isForeign = PivotUtil.isStatic(eOperation);
			if (isForeign) {
				return CGModelFactory.eINSTANCE.createCGLibraryOperation();
			}
			else {
				try {
					genModelHelper.getGenOperation(eOperation);
					CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
					cgEcoreOperation.setEOperation(eOperation);
					return cgEcoreOperation;
				}
				catch (GenModelException e) {
					return CGModelFactory.eINSTANCE.createCGLibraryOperation();
				}
			}
		}
		assert false : "Fallback overload for " + this;		// XXX
		return CGModelFactory.eINSTANCE.createCGLibraryOperation(); */
	//	PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
	//	GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
	//	LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
	//	assert libraryOperation instanceof ForeignOperation;	-- ForeignOperationCallingConvention
	//	assert libraryOperation instanceof EObjectOperation;	-- EcoreForeignOperationCallingConvention
		EOperation eOperation = (EOperation) asOperation.getESObject();
	//	assert (eOperation == null) || asOperation.isIsStatic();
		if ((eOperation != null) && !PivotUtil.isStatic(eOperation)) {
			try {
				GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
				genModelHelper.getGenOperation(eOperation);
				CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
				cgEcoreOperation.setEOperation(eOperation);
				return cgEcoreOperation;
			}
			catch (GenModelException e) {
				// No genmodel so fallback
			}
		}
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		assert cgSource == null;
		assert asOperation.isIsStatic();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		analyzer.addForeignFeature(asOperation);
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		addExecutorArgument(as2cgVisitor, cgForeignOperationCallExp);
	//	addTypeIdArgument(as2cgVisitor, cgForeignOperationCallExp, asOperation.getTypeId());
		addExpressionInOCLParameters(as2cgVisitor, cgOperation, (ExpressionInOCL) asOperation.getBodyExpression());
		init(as2cgVisitor, cgForeignOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		return cgForeignOperationCallExp;
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		addExecutorParameter(as2cgVisitor, cgOperation);
	//	addTyParameter(as2cgVisitor, cgOperation);
	//	List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
	//	cgParameters.add(as2cgVisitor.getTypeIdParameter());
	//	cgParameters.add(as2cgVisitor.getSelfParameter(expressionInOCL.getOwnedContext()));
		super.createCGParameters(as2cgVisitor, cgOperation, expressionInOCL);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2javaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2javaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		generateJavaClass(cg2javaVisitor, js, cgOperation);
		js.append("\n");
		generateJavaFunction(cg2javaVisitor, js, cgOperation);
		return true;
	}

	/**
	 * Generate a nested class to provide the polymorphic invocation interface.
	 */
	protected void generateJavaClass(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = codeGenerator.getGlobalContext();
		Iterable<@NonNull CGParameter> cgParameters = CGUtil.getParameters(cgOperation);
		String operationName = JavaConstants.FOREIGN_OPERATION_PREFIX + cgOperation.getName();
		assert operationName != null;
		js.appendCommentWithOCL(null, cgOperation.getAst());
	//	assert false;		// XXX
		js.append("public static class ");
		js.append(operationName);
		js.append(" extends ");
		js.appendClassReference(null, AbstractOperation.class);
		js.pushClassBody(operationName);
		js.append("public static final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(operationName);
		js.append(" ");
		js.append(globalContext.getInstanceName());
		js.append(" = new ");
		js.append(operationName);
		js.append("();\n");
		js.append("\n");
		//
		js.append("@Override\n");
		js.append("public ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		js.append(globalContext.getEvaluateName());
		js.append("(");
		js.appendClassReference(true, Executor.class);
		js.append(" executor, ");
		js.appendClassReference(true, TypedElement.class);
		js.append(" typedElement, ");
		js.appendClassReference(false, Object.class);
		js.append(" ");
		js.appendIsRequired(true);
		js.append(" [] sourceAndArguments) {\n");
		js.pushIndentation(null);

		int i = 0;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeId asTypeId = cgTypeId.getASTypeId();
			if (asTypeId == JavaConstants.EXECUTOR_TYPE_ID) {
			}
			else if (asTypeId == JavaConstants.TYPE_ID_TYPE_ID) {
			}
			else {
				final int ii = i++;
				js.append("@SuppressWarnings(\"null\")\n");
				js.appendDeclaration(cgParameter);
				js.append(" = ");
				SubStream castBody = new SubStream() {
					@Override
					public void append() {
						js.append("sourceAndArguments[" + ii + "]");
					}
				};
				js.appendClassCast(cgParameter, castBody);
				js.append(";\n");
			}
		}
		js.append("return ");
		js.appendValueName(cgOperation);
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeId asTypeId = cgTypeId.getASTypeId();
			if (asTypeId == JavaConstants.EXECUTOR_TYPE_ID) {
				js.append("executor");
			}
			else if (asTypeId == JavaConstants.TYPE_ID_TYPE_ID) {
				js.append("typedElement.getTypeId()");
			}
			else {
				js.appendValueName(cgParameter);
			}
			isFirst = false;
		}
		js.append(");\n");
		js.popIndentation();
		js.append("}\n");
		js.popClassBody(false);
	}

	/**
	 * Generate a static function to implement the foreign operation.
	 */
	protected void generateJavaFunction(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		Element ast = cgOperation.getAst();
		if (ast instanceof Operation) {
			LanguageExpression expressionInOCL = ((Operation)ast).getBodyExpression();
		//	if (ast instanceof Operation) {
		//		String title = PrettyPrinter.printName(ast);
				js.appendCommentWithOCL(null/*title+"\n"*/, expressionInOCL);
		//	}
		}
		js.append("public static ");
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		js.appendValueName(cgOperation);
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(") {\n");
		js.pushIndentation(null);
		cg2javaVisitor.appendReturn(body);
		js.popIndentation();
		js.append("}\n");
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		CGLibraryOperation cgForeignOperation = (CGLibraryOperation)cgOperation;
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgForeignOperation);
		boxingAnalyzer.rewriteAsBoxed(cgForeignOperation.getBody());
	}

/*	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGForeignOperationCallExp cgForeignOperationCallExp = (CGForeignOperationCallExp)cgOperationCallExp;
		CodeGenAnalyzer analyzer = boxingAnalyzer.getAnalyzer();
		if ("specializeIn".equals(cgOperationCallExp.getReferredOperation().getName())) {
			getClass();		// XXX
		}
		CGOperation cgOperation = CGUtil.getOperation(cgForeignOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgForeignOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();


		boolean isBoxed = true;
		boolean isEcore = false;
		boolean isUnboxed = false;
		assert isBoxed == cgForeignOperationCallExp.isBoxed();
		assert isEcore == cgForeignOperationCallExp.isEcore();
		assert isUnboxed == cgForeignOperationCallExp.isUnboxed();
		CGValuedElement cgThis = cgForeignOperationCallExp.getCgThis();
		EOperation eOperation = null;
		//
		//	Guard / cast this
		// XXX cgThis / cgSource
		boolean isStatic = cgThis != null; // XXXcallingConvention.isStatic(cgOperation);
		if (isStatic) {
			assert cgThis != null;
			OperationId operationId = asOperation.getOperationId();
			boolean sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);		// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
			if (!sourceMayBeNull && cgThis.isNull()) {
	//			CGInvalid cgInvalid = context.getInvalid("null value1 for source parameter");
				CGInvalid cgInvalid = analyzer.getInvalid("''" + asOperation.getOwningClass().getName() + "'' rather than ''OclVoid'' value required");
				CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgForeignOperationCallExp), cgInvalid);
				CGUtil.replace(cgForeignOperationCallExp, cgLiteralExp);
				return;
			}
			boxingAnalyzer.rewriteAsBoxed(cgThis);
			if (!sourceMayBeNull && !cgThis.isNonNull()) {
	//			rewriteAsGuarded(cgThis, false, "value3 for source parameter");
				boxingAnalyzer.rewriteAsGuarded(cgThis, false, "''" + asOperation.getOwningClass().getName() + "'' rather than ''OclVoid'' value required");
			}
		}
		//
		//	Null check boxed arguments
		//
		if (!asOperation.isIsValidating()) {
			for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
				CGValuedElement cgArgument = cgArguments.get(i);
				CGParameter cgParameter = cgParameters.get(i);
				if (cgParameter.isRequired()) {
					if (cgArgument.isNull()) {
	//					CGInvalid cgInvalid = context.getInvalid("null value2 for " + asParameter.getName() + " parameter");
						StringBuilder s= new StringBuilder();
						s.append("''");
						if (cgParameter.isIsSelf()) {
							s.append(asOperation.getOwningClass().getName());
						}
						else {
							s.append(cgParameter.getTypeId());
						}
						s.append("'' rather than ''OclVoid'' value required");
						CGInvalid cgInvalid = analyzer.getInvalid(s.toString());
						CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgForeignOperationCallExp), cgInvalid);
						CGUtil.replace(cgForeignOperationCallExp, cgLiteralExp);
						return;
					}
				}
			}
		}
		//
		//	Guard / cast arguments
		//
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
			CGParameter cgParameter = cgParameters.get(i);
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
			if (cgParameter.isRequired() && !cgArgument.isNonNull()) {
				//	rewriteAsGuarded(cgArgument, false, "value4 for " + asParameter.getName() + " parameter");
				boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + cgParameter.getTypeId() + "'' rather than ''OclVoid'' value required");
			}
		}
	} */
}
