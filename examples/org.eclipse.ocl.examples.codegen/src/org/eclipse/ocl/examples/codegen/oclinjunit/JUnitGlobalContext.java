/*******************************************************************************
 * Copyright (c) 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinjunit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * A JavaGlobalContext maintains the Java-specific global context for generation of code.
 */
public class JUnitGlobalContext extends JavaGlobalContext<@NonNull JUnitCodeGenerator>
{
	public JUnitGlobalContext(@NonNull JUnitCodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public @NonNull JUnitLocalContext createLocalContext(@Nullable JavaLocalContext<@NonNull ?> outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asNamedElement) {
		return new JUnitLocalContext(this, (JUnitLocalContext)outerContext, cgNamedElement, asNamedElement);
	}
}