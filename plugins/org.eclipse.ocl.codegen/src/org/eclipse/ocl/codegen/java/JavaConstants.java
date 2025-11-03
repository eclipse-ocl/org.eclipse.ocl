/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.codegen.java;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.UnboxedCompositionProperty;
import org.eclipse.ocl.pivot.internal.library.UnboxedExplicitNavigationProperty;
import org.eclipse.ocl.pivot.internal.library.UnboxedOppositeNavigationProperty;

public class JavaConstants
{
	public static final @NonNull String CONSTRAINT_NAME_NAME = "constraintName";
	public static final @NonNull String E_CONTAINER_NAME = "eContainer";
	public static final @NonNull String E_NAME = "e";
	public static final @NonNull String EVALUATE_NAME = "evaluate";
	public static final @NonNull String EVALUATION_CACHE_NAME = "evaluationCache";
	public static final @NonNull String EXECUTOR_NAME = "executor";
	public static final @NonNull String ID_RESOLVER_NAME = "idResolver";
	public static final @NonNull String INSTANCE_NAME = "INSTANCE";
	public static final @NonNull String SELF_NAME = "self";
	public static final @NonNull String SOURCE_AND_ARGUMENT_VALUES_NAME = "sourceAndArgumentValues";
	public static final @NonNull String STANDARD_LIBRARY_NAME = "standardLibrary";
	public static final @NonNull String TYPE_ID_NAME = "typeId";

	public static final @NonNull TypeId CLASS_TYPE_ID = IdManager.getJavaTypeId(org.eclipse.ocl.pivot.Class.class);
	public static final @NonNull TypeId PROPERTY_TYPE_ID = IdManager.getJavaTypeId(Property.class);
	public static final @NonNull TypeId EXECUTOR_TYPE_ID = IdManager.getJavaTypeId(Executor.class);
	public static final @NonNull TypeId ID_RESOLVER_TYPE_ID = IdManager.getJavaTypeId(IdResolver.class);
	//	public static final @NonNull TypeId SELF_TYPE_ID = IdManager.getJavaTypeId(Object.class);
	public static final @NonNull TypeId STANDARD_LIBRARY_TYPE_ID = IdManager.getJavaTypeId(StandardLibrary.class);
	public static final @NonNull TypeId TYPE_ID_TYPE_ID = IdManager.getJavaTypeId(TypeId.class);
	public static final @NonNull TypeId UNBOXED_COMPOSITION_PROPERTY_TYPE_ID = IdManager.getJavaTypeId(UnboxedCompositionProperty.class);
	public static final @NonNull TypeId UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID = IdManager.getJavaTypeId(UnboxedExplicitNavigationProperty.class);
	public static final @NonNull TypeId UNBOXED_OPPOSITE_NAVIGATION_PROPERTY_TYPE_ID = IdManager.getJavaTypeId(UnboxedOppositeNavigationProperty.class);

	/**
	 * Return the named Java typeId.
	 */
	@Deprecated
	public static @NonNull TypeId getJavaTypeId(@NonNull Class<?> javaClass) {
		return IdManager.getJavaTypeId(javaClass);
	}
}
