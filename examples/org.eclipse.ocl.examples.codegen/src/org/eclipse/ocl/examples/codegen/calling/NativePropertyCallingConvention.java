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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
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
import org.eclipse.ocl.examples.codegen.library.NativeProperty;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class NativePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull NativePropertyCallingConvention INSTANCE = new NativePropertyCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		assert libraryProperty instanceof NativeProperty;
		CGNativePropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGNativePropertyCallExp();
		cgPropertyCallExp.setCgProperty(cgProperty);
		cgPropertyCallExp.setReferredProperty(asProperty);
		as2cgVisitor.initAst(cgPropertyCallExp, asPropertyCallExp);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Property asProperty) {
		CGNativeProperty cgNativeProperty = CGModelFactory.eINSTANCE.createCGNativeProperty();
		if (!asProperty.isIsReadOnly()) {
			cgNativeProperty.setSettable();
		}
		else {
			cgNativeProperty.setNonNull();
		}
		return cgNativeProperty;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		//
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
				js.append(cgPropertyCallExp.getReferredProperty().getName());
			}
		};
		js.appendClassCast(cgPropertyCallExp, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		js.append("protected ");
		js.appendDeclaration(cgProperty);
		js.append(";\n");
		return true;
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
