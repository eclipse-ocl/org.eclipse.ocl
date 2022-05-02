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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.Property;

/**
 *  AbstractPropertyCallingConvention defines the default support for a property declaration or call.
 */
public abstract class AbstractPropertyCallingConvention implements PropertyCallingConvention
{
	@Override
	public @NonNull CGProperty createCGProperty(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Property asProperty) {
		return CGModelFactory.eINSTANCE.createCGConstrainedProperty();  // XXX Overrides may add state
	}

	@Override
	public void createImplementation(@NonNull AS2CGVisitor as2cgVisitor, @NonNull JavaLocalContext<?> localContext, @NonNull CGProperty cgProperty) {
	}		  // Overrides may synthesize an implementation body

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		throw new UnsupportedOperationException("Missing/No support for " + getClass().getSimpleName() + ".generateJavaDeclaration");	// A number of Property Calling Conventions are call-only
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName();
	}
}
