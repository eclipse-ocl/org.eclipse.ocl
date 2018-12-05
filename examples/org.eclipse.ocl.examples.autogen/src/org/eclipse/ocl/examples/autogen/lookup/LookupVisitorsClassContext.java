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
public class LookupVisitorsClassContext extends AutoGlobalContext<@NonNull LookupVisitorsCodeGenerator>
{
	public static final @NonNull String CHILD_NAME = "child";
	public static final @NonNull String CONTEXT_NAME = "context";
	public static final @NonNull String ELEMENT_NAME = "element";
	public static final @NonNull String UNQUALIFIED_ENV_NAME = "_unqualified_env";
	public static final @NonNull String EXPORTED_ENV_NAME = "_exported_env";
	public static final @NonNull String HAS_FINAL_RESULT_NAME = "hasFinalResult";
	public static final @NonNull String INMPORTER_NAME = "importer";
	//public static final @NonNull String INNER_ENV_NAME = "innerEnv";
	public static final @NonNull String NESTED_ENV_NAME = "nestedEnv";
	//public static final @NonNull String OUTER_ENV_NAME = "outerEnv";
	public static final @NonNull String PARENT_NAME = "parent";
	public static final @NonNull String PARENT_ENV_NAME = "parentEnv";
	public static final @NonNull String QUALIFIED_ENV_NAME = "_qualified_env";

	public LookupVisitorsClassContext(@NonNull LookupVisitorsCodeGenerator codeGenerator, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		super(codeGenerator, asPackage);
		nameManager.reserveName(CHILD_NAME, null);
		//		nameManager.reserveName(CONTEXT_NAME, null);
		nameManager.reserveName(ELEMENT_NAME, null);
		nameManager.reserveName(UNQUALIFIED_ENV_NAME, null);
		nameManager.reserveName(EXPORTED_ENV_NAME, null);
		nameManager.reserveName(HAS_FINAL_RESULT_NAME, null);
		nameManager.reserveName(INMPORTER_NAME, null);
		nameManager.reserveName(NESTED_ENV_NAME, null);
		nameManager.reserveName(PARENT_NAME, null);
		nameManager.reserveName(PARENT_ENV_NAME, null);
		nameManager.reserveName(QUALIFIED_ENV_NAME, null);
	}

}