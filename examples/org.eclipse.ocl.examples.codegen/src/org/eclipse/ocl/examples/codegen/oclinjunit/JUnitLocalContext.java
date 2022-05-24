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
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;

/**
 * A JavaLocalContext maintains the Java-specific context for generation of code from a CGOperation.
 */
public class JUnitLocalContext extends JavaLocalContext
{
	public JUnitLocalContext(@NonNull NameManager outerNameManager, @NonNull NestedNameManager innerNameManager) {
		super(outerNameManager, innerNameManager);
	}

	@Override
	public @NonNull CGVariable getExecutorVariable() {
		return nameManager.getExecutorParameter();
	}
}
