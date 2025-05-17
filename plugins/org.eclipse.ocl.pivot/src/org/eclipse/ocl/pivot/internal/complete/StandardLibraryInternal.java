/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.manager.MapTypeManagerInternal;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;

/**
 */
public interface StandardLibraryInternal extends StandardLibrary
{
	/**
	 * @since 7.0
	 */
	@Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments);
	/**
	 * @since 1.17
	 */
	@Nullable AnyType basicGetOclAnyType();
	@Nullable Operation basicGetOclInvalidOperation();
	@Nullable Property basicGetOclInvalidProperty();
	@Nullable InvalidType basicGetOclInvalidType();
	/**
	 * @since 1.17
	 */
	void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes);
	void dispose();
	@Nullable PrimitiveType getBehavioralClass(@NonNull Class<?> javaClass);

	/**
	 * @since 7.0
	 */
//	org.eclipse.ocl.pivot.@NonNull Class getCollectionType(@NonNull String collectionTypeName, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);
	@NonNull CompleteModelInternal getCompleteModel();
	@NonNull String getDefaultStandardLibraryURI();
	org.eclipse.ocl.pivot.Class getLibraryType(@NonNull String typeName);
	/**
	 * @since 7.0
	 */
	@Override
	@NonNull MapTypeManagerInternal getMapTypeManager();
	@Override
	@NonNull Property getOclInvalidProperty();
	org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType();
	org.eclipse.ocl.pivot.@NonNull Class getOclTypeType();
	org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className);
	org.eclipse.ocl.pivot.@NonNull Class getRequiredLibraryType(@NonNull String typeName);
	@NonNull StandardLibraryInternal init(@NonNull CompleteModelInternal completeModel);
	boolean isExplicitDefaultStandardLibraryURI();
	@Nullable Resource loadDefaultLibrary(@Nullable String uri);
	void setDefaultStandardLibraryURI(@NonNull String defaultStandardLibraryURI);
}
