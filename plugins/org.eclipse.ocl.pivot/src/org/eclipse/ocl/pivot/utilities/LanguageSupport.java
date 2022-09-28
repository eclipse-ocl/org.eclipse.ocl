/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;

/**
 * LanguageSupport defines the abstract support for invocation of native language facilities from OCL.
 * @since 1.18
 */
public interface LanguageSupport
{
	/**
	 * Return the AS cache class for asClass.
	 */
//	public abstract org.eclipse.ocl.pivot.@NonNull Class getCacheClass(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull String name);

	/**
	 * Return the AS cache class for asFeature.
	 */
//	public abstract org.eclipse.ocl.pivot.@NonNull Class getCacheClass(@NonNull Feature asFeature);

	/**
	 * Return the AS cache constructor class for asFeature.
	 */
//	public abstract org.eclipse.ocl.pivot.@NonNull Class getConstructorClass(@NonNull Feature asFeature);

	/**
	 * Return the AS class for a Java Class.
	 */
	public abstract org.eclipse.ocl.pivot.@NonNull Class getNativeClass(/*@NonNull */Class<?> jClass);
	public abstract org.eclipse.ocl.pivot.@NonNull Class getNativeClass(@NonNull String qualifiedClassName);
	public abstract @Nullable Invocations resolveInvocations(@NonNull Type requiredSourceType, boolean hasExplicitSourceExp, @NonNull String qualifiedOperationName);
}