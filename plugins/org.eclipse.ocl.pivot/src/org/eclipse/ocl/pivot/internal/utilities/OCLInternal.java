/*******************************************************************************
 * Copyright (c) 2015, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.resource.BasicProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;

public class OCLInternal extends OCL
{
	public static @NonNull OCLInternal newInstance() {
		return newInstance((ResourceSet)null);
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLInternal newInstance(@Nullable ResourceSet resourceSet) {
		return newInstance(BasicProjectManager.createDefaultProjectManager(), resourceSet);
	}

	public static @NonNull OCLInternal newInstance(@NonNull ProjectManager projectManager, @Nullable ResourceSet userResourceSet) {
		EnvironmentFactory environmentFactory = ASResourceFactoryRegistry.INSTANCE.createEnvironmentFactory(projectManager, userResourceSet);
		return newInstance(environmentFactory);
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull OCLInternal newInstance(@NonNull EnvironmentFactory environmentFactory) {
		return new OCLInternal(environmentFactory);
	}

	/**
	 * @since 7.0
	 */
	public OCLInternal(@NonNull EnvironmentFactory environmentFactory) {
		super(environmentFactory);
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		assert environmentFactory != null;
		return environmentFactory;
	}

	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		assert environmentFactory != null;
		return environmentFactory.getMetamodelManager();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		assert environmentFactory != null;
		return environmentFactory.getStandardLibrary();
	}
}
