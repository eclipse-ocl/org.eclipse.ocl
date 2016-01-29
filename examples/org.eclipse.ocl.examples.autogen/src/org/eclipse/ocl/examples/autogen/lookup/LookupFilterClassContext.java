/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations, University of York and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Adolfo Sanchez-Barbudo Herrera (University of York)
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.autogen.java.AutoGlobalContext;

/**
 * A LookupClassContext maintains the Java-specific global context for generation of a LookupVisitor.
 */
public class LookupFilterClassContext extends AutoGlobalContext<LookupFilterGenerator>
{	
	public static final @NonNull String APPLIES_FILTER_OP_PREFIX = "_appliesFilter_";
	
	public static final @NonNull String MATCHES_OP_NAME = "matches";
	public static final @NonNull String ELEMENT_NAME = "element";
	
	public LookupFilterClassContext(@NonNull LookupFilterGenerator codeGenerator, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		super(codeGenerator, asPackage);
	}
}