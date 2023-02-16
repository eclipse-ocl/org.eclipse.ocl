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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.NativeProperty;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class NativePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	private static final @NonNull NativePropertyCallingConvention INSTANCE = new NativePropertyCallingConvention();

	public static @NonNull NativePropertyCallingConvention getInstance(@NonNull TypedElement asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert libraryProperty instanceof NativeProperty;
		CGNativePropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGNativePropertyCallExp();
		cgPropertyCallExp.setReferredProperty(cgProperty);
		analyzer.initAst(cgPropertyCallExp, asPropertyCallExp, true);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull Property asProperty) {
		CGNativeProperty cgProperty = CGModelFactory.eINSTANCE.createCGNativeProperty();
	/*	if (!asProperty.isIsReadOnly()) {
			cgProperty.setSettable();
		}
		else {
			cgProperty.setNonNull();
		} */
		return cgProperty;
	}

	@Override
	public @NonNull CGProperty createProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull Property asProperty, @Nullable ExpressionInOCL asExpressionInOCL) {
		CGNativeProperty cgProperty = (CGNativeProperty)super.createProperty(analyzer, asProperty, asExpressionInOCL);
		if (!asProperty.isIsReadOnly()) {
			cgProperty.setSettable();
		}
		else {
			cgProperty.setRequired(true);
		}
		return cgProperty;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGValuedElement cgSource = cgPropertyCallExp.getSource();
		Property asProperty = cgPropertyCallExp.getAsProperty();
		if (cgSource == null) {
			assert asProperty.isIsStatic();
			js.appendClassReference(null, asProperty.getImplementationClass());
			js.append(".");
			js.append(asProperty.getName());
		}
		else {
			assert !asProperty.isIsStatic();
			CGValuedElement source  = cg2javaVisitor.getExpression(cgSource);
			if (!js.appendLocalStatements(source)) {
				return false;
			}
			//
			js.appendDeclaration(cgPropertyCallExp);
			js.append(" = ");
			SubStream castBody = new SubStream() {
				@Override
				public void append() {
					js.appendValueName(source);
					js.append(".");
					js.append(asProperty.getName());
				}
			};
			js.appendClassCast(cgPropertyCallExp, castBody);
			js.append(";\n");
		}
		return true;
	}

//	@Override
//	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
//		js.append("protected ");
//		js.appendDeclaration(cgProperty);
//		js.append(";\n");
//		return true;
//	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		throw new UnsupportedOperationException();		// Native operations are declared natively
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty) {
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgProperty);
		CGNativeProperty cgNativeProperty = (CGNativeProperty)cgProperty;
		if (cgNativeProperty.isRequired()) {
			CGValuedElement body = cgNativeProperty.getBody();
			if (body != null) {
				boxingAnalyzer.rewriteAsGuarded(body, false, "body for '" + cgNativeProperty.getAst() + "'");
			}
		}
	}
}
