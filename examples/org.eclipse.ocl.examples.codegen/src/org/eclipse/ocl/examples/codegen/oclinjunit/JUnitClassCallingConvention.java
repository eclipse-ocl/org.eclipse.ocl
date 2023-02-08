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
package org.eclipse.ocl.examples.codegen.oclinjunit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.AbstractClassCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.evaluation.AbstractExecutionSupport;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;

/**
 *  JUnitClassCallingConvention defines the style of a JUnit root Class declaration.
 */
public class JUnitClassCallingConvention extends AbstractClassCallingConvention
{
	private static final @NonNull JUnitClassCallingConvention INSTANCE = new JUnitClassCallingConvention();

	public static @NonNull JUnitClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		INSTANCE.logInstance(asClass);
		return INSTANCE;
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGDefaultClassParent(analyzer, cgClass, asClass);
		LanguageSupport languageSupport = analyzer.getCodeGenerator().getLanguageSupport();
		org.eclipse.ocl.pivot.@NonNull Class asSuperClass = languageSupport.getNativeClass(AbstractExecutionSupport.class);
		CGClass cgSuperClass = analyzer.generateClassDeclaration(asSuperClass, null);
		cgClass.getSuperTypes().add(cgSuperClass);
		return cgClass;
	}

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgClass) {
	//	if (isEmpty(cgClass)) {
	//		return true;
	//	}
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.appendOptionalBlankLine();;
		GlobalNameManager globalNameManager = cg2javaVisitor.getGlobalNameManager();
		NameResolution rootExecutorName = globalNameManager.getRootExecutorName();
		String className = CGUtil.getName(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		js.appendClassHeader(asClass);
		Operation asOperation = asClass.getOwnedOperations().get(0);
		ExpressionInOCL expInOcl = (ExpressionInOCL)asOperation.getBodyExpression();
	//	Class<?> baseClass = cg2javaVisitor.getGenModelHelper().getAbstractOperationClass(expInOcl.getOwnedParameters().size());
		Class<?> baseClass = AbstractExecutionSupport.class;
		String title = cgClass.getName() + " provides the Java implementation for";
		js.appendCommentWithOCL(title, expInOcl);
		assert className != null;
		//	js.append("@SuppressWarnings(\"nls\")\n");
		if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
			js.append("// " + cgClass.getCallingConvention() + "\n");
		}
		js.append("public class " + className + " extends ");
		js.appendClassReference(null, baseClass);
	//	appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		js.appendOptionalBlankLine();;
		Iterable<@NonNull CGValuedElement> sortedGlobals = cg2javaVisitor.getAnalyzer().getGlobals();
		if (sortedGlobals != null) {
			cg2javaVisitor.generateGlobals(sortedGlobals);
	//		js.appendOptionalBlankLine();
		}
		generatePropertyDeclarations(cg2javaVisitor, cgClass);
		//
	/*	js.appendOptionalBlankLine();
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(className);
		js.append(" ");
		js.appendName(globalNameManager.getRootThisName());
		js.append(" = this;\n");
		//
	//	if (globalNameManager.needsExecutor()) {
		//	js.append("protected final ");
		//	js.appendClassReference(true, Executor.class);
		//	js.append(" ");
		//	js.appendName(globalNameManager.getRootExecutorName());
		//	js.append(";\n");
			//
		/*	js.appendOptionalBlankLine();
			js.append("public ");
			js.append(className);
			js.append("() {\n");
			js.pushIndentation(null);
			js.append("this(");
			js.appendClassReference(null, PivotUtil.class);
			js.append(".getExecutor(null));\n");
			js.popIndentation();
			js.append("}\n"); */
			//
			js.appendOptionalBlankLine();
			js.append("public ");
			js.append(className);
			js.append("(");
			js.appendClassReference(true, Executor.class);
			js.append(" ");
			js.appendName(rootExecutorName);
			js.append(") {\n");
			js.pushIndentation(null);
		//	js.append("this.");
		//	js.appendName(rootExecutorName);
		//	js.append(" = ");
		//	js.appendName(rootExecutorName);
		//	js.append(";\n");
			js.append("super(");
			js.appendName(rootExecutorName);
			js.append(");\n");
			generatePropertyInitializations(cg2javaVisitor, cgClass);
			js.popIndentation();
			js.append("}\n");
	//	}
		//
		if (expInOcl.getOwnedContext() != null) {
//			generatePropertyDeclarations(cg2javaVisitor, cgClass);
			generateOperations(cg2javaVisitor, cgClass);
		}
		else {
			js.append("/*\n");
			js.append("«IF expInOcl.messageExpression != null»«(expInOcl.messageExpression as StringLiteralExp).stringSymbol»«ENDIF»\n");
			js.append("* /\n");
		}
		generateClasses(cg2javaVisitor, cgClass);
		js.popClassBody(false);
		assert js.peekClassNameStack() == null;
		return true;
	}
}
