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
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

public interface CompleteModelInternal extends CompleteModel
{
	/**
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage);

	/**
	 * @since 7.0
	 */
	@Deprecated
	@Nullable CompletePackage basicGetCompletePackage(@NonNull String packageURI);
	/**
	 * @since 7.0
	 */
	@Nullable CompletePackage basicGetCompletePackageForPackageURI(@NonNull String packageURI);

	/**
	 * @since 7.0
	 */
	@Nullable CompleteClassInternal basicGetSharedCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass);
	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass);
	void didAddCompletePackage(@NonNull CompletePackageInternal completePackage);
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
	@Override
	@Nullable CompletePackage getCompletePackageByURI(@NonNull String packageURI);
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

	/**
	 * @since 7.0
	 */
	@Deprecated @Nullable CompletePackage getCompletePackage2(org.eclipse.ocl.pivot.@NonNull Package pivotPackage);
}
