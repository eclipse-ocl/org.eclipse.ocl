/*******************************************************************************
 * Copyright (c) 2012, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cse;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.CodeGenConstants;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.pivot.utilities.TracingOption;

/**
 * A CommonSubexpressionEliminator supervises the rewrites of structurally equivalent CG nodes with shared CG nodes.
 */
public class CommonSubexpressionEliminator
{	
	public static final @NonNull TracingOption CSE_BUILD = new TracingOption(CodeGenConstants.PLUGIN_ID, "cse/build");
	public static final @NonNull TracingOption CSE_PLACES = new TracingOption(CodeGenConstants.PLUGIN_ID, "cse/places");
	public static final @NonNull TracingOption CSE_PRUNE = new TracingOption(CodeGenConstants.PLUGIN_ID, "cse/prune");
	public static final @NonNull TracingOption CSE_PULL_UP = new TracingOption(CodeGenConstants.PLUGIN_ID, "cse/pullUp");
	public static final @NonNull TracingOption CSE_PUSH_UP = new TracingOption(CodeGenConstants.PLUGIN_ID, "cse/pushUp");
	public static final @NonNull TracingOption CSE_REWRITE = new TracingOption(CodeGenConstants.PLUGIN_ID, "cse/rewrite");

	
	protected final @NonNull CodeGenerator codeGenerator;
	
	public CommonSubexpressionEliminator(@NonNull CodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
	}

	/**
	 * Optimize the cgRoot tree by eliminating common subexpressions.
	 */
	public void optimize(@NonNull CGElement cgRoot) {
		GlobalPlace globalPlace = codeGenerator.getGlobalPlace();
		globalPlace.optimize(cgRoot);
	}
}
