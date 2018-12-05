/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.autogen.java.AutoGlobalContext;

/**
 * A LookupClassContext maintains the Java-specific global context for generation of a LookupVisitor.
 */
public class LookupFilterClassContext extends AutoGlobalContext<@NonNull LookupFilterGenerator>
{
	public static final @NonNull String APPLIES_FILTER_OP_PREFIX = "_appliesFilter_";

	public static final @NonNull String MATCHES_OP_NAME = "matches";
	public static final @NonNull String ELEMENT_NAME = "element";

	public LookupFilterClassContext(@NonNull LookupFilterGenerator codeGenerator, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		super(codeGenerator, asPackage);
		nameManager.reserveName(APPLIES_FILTER_OP_PREFIX, null);
		nameManager.reserveName(ELEMENT_NAME, null);
		nameManager.reserveName(MATCHES_OP_NAME, null);
	}
}