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
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  AbstractPropertyCallingConvention defines the default support for a property declaration or call.
 */
public abstract class AbstractPropertyCallingConvention implements PropertyCallingConvention
{
//	@Override
//	public @NonNull CGValuedElement createCGOppositePropertyCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
//			@NonNull LibraryProperty libraryProperty, @NonNull CGValuedElement cgSource, @NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
//		throw new UnsupportedOperationException();
//	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Property asProperty) {
		CGProperty cgProperty = CGModelFactory.eINSTANCE.createCGProperty();
	//	if (!asProperty.isIsReadOnly()) {
	//		cgProperty.setSettable();
	//	}
	//	else {
	//		cgProperty.setNonNull();
	//	}
		return cgProperty;
	}

	@Override
	public @NonNull CGValuedElement createCGPropertyCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createImplementation(@NonNull AS2CGVisitor as2cgVisitor, @NonNull JavaLocalContext<?> localContext, @NonNull CGProperty cgProperty) {
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		throw new UnsupportedOperationException();	// A number of Property Calling Conventions are call-only
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName() + (isBoxed() ? " boxed" : "") + (isEcore() ? " ecore" : "") + (isUnboxed() ? " unboxed" : "");
	}
}
