/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
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
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NonClassNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;

/**
 * JUnitNestedNameManager provides JUnit-specific overrides for nested contexts.
 */
public class JUnitNonClassNameManager extends NonClassNameManager
{
	public JUnitNonClassNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManager parent, @NonNull CGNamedElement cgScope) {
		super(codeGenerator, parent, cgScope);
	}

	@Override
	public @NonNull CGVariable getExecutorVariable() {
		return getExecutorParameter();
	}
}