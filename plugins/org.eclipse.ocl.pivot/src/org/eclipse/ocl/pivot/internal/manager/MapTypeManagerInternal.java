/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapTypeArguments;

/**
 * MapTypeManagerInternal encapsulates the knowledge about known map types.
 *
 * @since 7.0
 */
public class MapTypeManagerInternal extends AbstractMapTypeManager
{
	protected final @NonNull CompleteModelInternal completeModel;

	public MapTypeManagerInternal(@NonNull CompleteModelInternal completeModel) {
		super(completeModel.getStandardLibrary().getMapType());
		this.completeModel = completeModel;
	}

	@Override
	protected @NonNull MapType createMapType(@NonNull MapTypeArguments typeArguments, org.eclipse.ocl.pivot.@Nullable Class entryClass) {
		Type keyType = typeArguments.getKeyType();
		boolean keysAreNullFree = typeArguments.isKeysAreNullFree();
		Type valueType = typeArguments.getValueType();
		boolean valuesAreNullFree = typeArguments.isValuesAreNullFree();
		MapType mapType;
		if (entryClass == null) {
			mapType = PivotUtil.createMapType(genericMapType, keyType, keysAreNullFree, valueType, valuesAreNullFree);
		}
		else {
			MapType specializedMapType = getMapType(typeArguments);
			mapType = PivotUtil.createMapEntryType(specializedMapType, entryClass);
		}
		completeModel.resolveSuperClasses(mapType, genericMapType);
		Orphanage orphanage = completeModel.getOrphanage();
		mapType.setOwningPackage(orphanage);
		return mapType;
	}

	@Override
	protected boolean isValid(@Nullable Type type) {
		return (type != null) && (type.eResource() != null);
	}
}