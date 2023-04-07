/**
 * Copyright (c) 2014, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.OrphanCompletePackageImpl;
import org.eclipse.ocl.pivot.internal.PrimitiveCompletePackageImpl;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

public interface CompleteModelInternal extends CompleteModel
{
	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass);
	void didAddCompletePackage(@NonNull CompletePackageInternal completePackage);
	void didRemoveCompletePackage(@NonNull CompletePackageInternal completePackage);
	void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	@Override
	@NonNull Iterable<@NonNull CompletePackageInternal> getAllCompletePackages();
	@Override
	@NonNull CompleteClassInternal getCompleteClass(@NonNull Type partialClass);
	@Override
	@NonNull CompletePackageInternal getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage);
	@Override
	@Nullable CompletePackageInternal getCompletePackageByURI(@NonNull String packageURI);
	@NonNull CompleteURIs getCompleteURIs();
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	@NonNull PivotMetamodelManager getMetamodelManager();
	@Override
	@NonNull OrphanCompletePackageImpl getOrphanCompletePackage();
	@Override
	@NonNull PartialModels getPartialModels();
	@Override
	@NonNull PrimitiveCompletePackageImpl getPrimitiveCompletePackage();
	void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass);
	void dispose();
	void didAddPartialModel(@NonNull Model partialModel);
	void didAddNestedPackage(org.eclipse.ocl.pivot.@NonNull Package pivotPackage);
	void didRemoveNestedPackage(org.eclipse.ocl.pivot.@NonNull Package pivotPackage);
	void didRemovePartialModel(@NonNull Model partialModel);
	@Nullable String getCompleteURI(@Nullable String nsURI);
	@NonNull Orphanage getOrphanage();
	@NonNull StandardLibraryInternal getStandardLibrary();
	@Override
	@NonNull CompleteEnvironmentInternal getCompleteEnvironment();
	@NonNull CompleteModelInternal init(@NonNull CompleteEnvironmentInternal completeEnvironment);
}
