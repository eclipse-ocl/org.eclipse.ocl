/**
 * Copyright (c) 2014, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.OrphanCompletePackageImpl;
import org.eclipse.ocl.pivot.internal.PrimitiveCompletePackageImpl;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

public interface CompleteModelInternal extends CompleteModel
{
	/**
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackageForPackageURI(@NonNull String packageURI);

	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass);
	/**
	 * @since 7.0
	 */
	void didRemoveCompletePackage(@NonNull CompletePackage completePackage);
	void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	@Override
	@NonNull Iterable<@NonNull CompletePackage> getAllCompletePackages();
	@Override
	@NonNull CompleteClassInternal getCompleteClass(@NonNull Type partialClass);
	@Override
	@NonNull CompletePackage getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage);
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	/**
	 * @since 7.0
	 */
	@NonNull MetamodelManager getMetamodelManager();
	@Override
	@NonNull OrphanCompletePackageImpl getOrphanCompletePackage();
	@Override
	@NonNull PartialModels getPartialModels();
	@Override
	@NonNull PrimitiveCompletePackageImpl getPrimitiveCompletePackage();

	void dispose();
	void didAddPartialModel(@NonNull Model partialModel);
	/**
	 * @since 7.0
	 */
	void didAddPackage(org.eclipse.ocl.pivot.@NonNull Package pivotPackage);
	void didRemoveNestedPackage(org.eclipse.ocl.pivot.@NonNull Package pivotPackage);
	void didRemovePartialModel(@NonNull Model partialModel);

	/**
	 * Ensure that a CompleteClass is referenceable for all classes in asPackage.
	 * @since 7.0
	 */
	void getCompleteClasses(@NonNull ASResource asResource);
	/**
	 * Ensure that a CompleteClass is referenceable for all classes in asPackage.
	 * @since 7.0
	 */
	void getCompleteClasses(org.eclipse.ocl.pivot.@NonNull Package asPackage);
	@Nullable String getCompleteURI(@Nullable String nsURI);
	/**
	 * @since 7.0
	 */
	@NonNull CompleteStandardLibrary getStandardLibrary();
	@Override
	@NonNull CompleteEnvironmentInternal getCompleteEnvironment();
	/**
	 * @since 7.0
	 */
	@NonNull CompleteModelInternal init(@NonNull EnvironmentFactoryInternal environmentFactory);
}
