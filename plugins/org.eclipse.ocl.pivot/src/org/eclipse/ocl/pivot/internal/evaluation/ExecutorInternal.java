/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;

/**
 * @since 1.1
 */
public interface ExecutorInternal extends Executor
{
	/**
	 * @since 7.0
	 */
	@Nullable Object getCachedEvaluationResult(@NonNull LibraryOperation implementation, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues);
	@Override
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	/**
	 * @since 7.0
	 */
	@NonNull EvaluationCache getEvaluationCache();
	@NonNull EvaluationVisitor getEvaluationVisitor();
	@Override
	@NonNull MetamodelManagerInternal getMetamodelManager();
	@NonNull EvaluationEnvironment getRootEvaluationEnvironment();
	@Nullable Object getValueOf(@NonNull TypedElement referredVariable);
	@NonNull EvaluationEnvironment initializeEvaluationEnvironment(@NonNull NamedElement executableObject);
	@Nullable Object internalExecuteNavigationCallExp(@NonNull NavigationCallExp propertyCallExp, @NonNull Property referredProperty, @Nullable Object sourceValue);
	/**
	 * @since 7.0
	 */
	@Nullable Object internalExecuteOperationCallExp(@NonNull OperationCallExp operationCallExp, @Nullable Object @NonNull [] sourceAndArgumentValues);
	/**
	 * @since 7.0
	 */
	@Nullable Object internalExecuteShadowExp(@NonNull ShadowExp asShadowExp);
}
