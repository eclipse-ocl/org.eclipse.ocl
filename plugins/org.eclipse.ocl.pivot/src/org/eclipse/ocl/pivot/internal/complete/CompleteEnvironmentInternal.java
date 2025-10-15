/*******************************************************************************
 * Copyright (c) 2014, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

public interface CompleteEnvironmentInternal extends CompleteEnvironment
{
	/**
	 * @since 7.0
	 */
	@Nullable CompleteClassInternal basicGetCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass);
	void dispose();
	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass);
	void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	@Override
	@NonNull CompleteModelInternal getOwnedCompleteModel();
	@Override
	@NonNull CompleteStandardLibrary getOwnedStandardLibrary();
	/**
	 * @since 7.0
	 */
	@NonNull CompleteEnvironmentInternal init(@NonNull EnvironmentFactory environmentFactory);
	boolean isCodeGeneration();
	void setCodeGeneration(boolean isCodeGeneration);
	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactory getEnvironmentFactory();
}