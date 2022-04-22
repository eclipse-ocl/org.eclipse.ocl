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
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class NativePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull NativePropertyCallingConvention INSTANCE = new NativePropertyCallingConvention();

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
	public boolean isUnboxed() {
		return true;
	}
}
