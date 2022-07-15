/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * A CollectionTypeId provides a unique identifier for an unspecialized collection type such as Set(T).
 */
public interface CollectionTypeId extends BuiltInTypeId, TemplateableId
{
	@NonNull TypeId getElementTypeId();
	@Override
	@NonNull CollectionTypeId getGeneralizedId();
	/**
	 * @since 1.18
	 */
	@NonNull IntegerValue getLowerValue();
	@Override
	@NonNull String getMetaTypeName();
	@Override
	@NonNull CollectionTypeId getSpecializedId(@NonNull BindingsId templateBindings);
	@Deprecated
	@NonNull CollectionTypeId getSpecializedId(@NonNull ElementId... templateBindings);
	/**
	 * @since 1.18
	 */
	@NonNull CollectionTypeId getSpecializedCollectionTypeId(@NonNull ElementId elementId);
	/**
	 * @since 1.18
	 */
	@NonNull CollectionTypeId getSpecializedCollectionTypeId(@NonNull ElementId elementId, @NonNull BooleanLiteralId isNullFree, @NonNull IntegerLiteralId lowerValue, @NonNull UnlimitedNaturalLiteralId upperValue);
	/**
	 * @since 1.18
	 */
	@NonNull CollectionTypeId getSpecializedCollectionTypeId(@NonNull CollectionTypeId collectionTypeId);
	/**
	 * @since 1.18
	 */
	@NonNull UnlimitedNaturalValue getUpperValue();
	/**
	 * @since 1.18
	 */
	boolean isNullFree();
}