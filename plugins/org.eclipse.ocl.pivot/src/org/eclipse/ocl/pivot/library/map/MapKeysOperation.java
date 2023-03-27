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
package org.eclipse.ocl.pivot.library.map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * MapKeysOperation realises the Map::keys() library operation.
 */
public class MapKeysOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull MapKeysOperation INSTANCE = new MapKeysOperation();

	@Override
	public @NonNull SetValue evaluate(@Nullable Object source) {
		MapValue mapValue = asMapValue(source);
		return mapValue.getKeys();
	}

	@Override
	public @Nullable Type resolveReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		assert returnType != null;
		CollectionType collectionReturnType = (CollectionType)returnType;
		CollectionType genericReturnType = PivotUtil.getUnspecializedTemplateableElement(collectionReturnType);
		Type elementReturnType = PivotUtil.getElementType(collectionReturnType);
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		MapType sourceMapType = (MapType) PivotUtil.getType(ownedSource);
		boolean isNullFree = sourceMapType.isKeysAreNullFree();
		return environmentFactory.getStandardLibrary().getCollectionType(genericReturnType, elementReturnType,
			isNullFree, collectionReturnType.getLowerValue(), collectionReturnType.getUpperValue());
	}
}
