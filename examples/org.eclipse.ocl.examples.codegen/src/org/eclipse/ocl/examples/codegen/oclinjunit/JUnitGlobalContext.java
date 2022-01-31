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
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;

/**
 * A JavaGlobalContext maintains the Java-specific global context for generation of code.
 */
public class JUnitGlobalContext extends JavaGlobalContext<@NonNull JUnitCodeGenerator>
{
	public JUnitGlobalContext(@NonNull JUnitCodeGenerator codeGenerator) {
		super(codeGenerator);
	}

	@Override
	public @NonNull JUnitLocalContext createNestedContext(@NonNull CGElement cgScope) {
		return new JUnitLocalContext(this, cgScope);
	}
}