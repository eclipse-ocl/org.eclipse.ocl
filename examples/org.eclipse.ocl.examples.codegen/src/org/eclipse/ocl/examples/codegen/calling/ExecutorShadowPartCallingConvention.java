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
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ExecutorShadowPartCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull ExecutorShadowPartCallingConvention INSTANCE = new ExecutorShadowPartCallingConvention();

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		assert cgProperty instanceof CGExecutorShadowPart;
		CGExecutorShadowPart cgExecutorShadowPart = (CGExecutorShadowPart)cgProperty;
		js.appendDeclaration(cgExecutorShadowPart);
		js.append(" = ");
		js.appendValueName(cg2javaVisitor.getCodeGenerator().getGlobalContext().getLocalContext(cgExecutorShadowPart).getIdResolverVariable());
		js.append(".getProperty(");
		js.appendIdReference(cgExecutorShadowPart.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(
			@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty,
			@Nullable CGValuedElement cgSource,
			@NonNull NavigationCallExp asPropertyCallExp) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2JavaVisitor,
			@NonNull JavaStream js,
			@NonNull CGNavigationCallExp cgPropertyCallExp) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}
