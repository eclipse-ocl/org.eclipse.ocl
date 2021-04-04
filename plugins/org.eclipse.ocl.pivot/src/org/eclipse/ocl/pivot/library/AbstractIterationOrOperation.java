/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractIterationOrOperation realizes shared characteristics of library iterations and operations.
 * @since 1.15
 */
public abstract class AbstractIterationOrOperation extends AbstractFeature implements LibraryIterationOrOperation
{
	/**
	 * Special case processing for return collection types based on the source collection types and multiplicities.
	 */
	protected @Nullable Type resolveCollectionAsCollectionReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		if (returnType instanceof CollectionType) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				CollectionType returnCollectionType = (CollectionType)returnType;
				if (sourceType instanceof CollectionType) {
					CollectionType sourceCollectionType = (CollectionType)sourceType;
					Type elementType = PivotUtil.getElementType(sourceCollectionType);
					PivotMetamodelManager metamodelManager = (PivotMetamodelManager)environmentFactory.getMetamodelManager();
					returnType = metamodelManager.getCollectionType(returnCollectionType.isOrdered(), returnCollectionType.isUnique(),
						elementType, sourceCollectionType.isIsNullFree(), sourceCollectionType.getLowerValue(), sourceCollectionType.getUpperValue());
				}
			}
		}
		return returnType;
	}

	/**
	 * Special case processing for return types based on the source collection element types.
	 */
	protected boolean resolveCollectionSourceElementReturnNullity(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, boolean returnIsRequired) {
		OCLExpression ownedSource = callExp.getOwnedSource();
		if (ownedSource != null) {
			Type sourceType = ownedSource.getType();
			if (sourceType instanceof CollectionType) {
				returnIsRequired = ((CollectionType)sourceType).isIsNullFree();
			}
		}
		return returnIsRequired;
	}

	/**
	 * Special case processing for return collection types based on the source collection types.
	 */
	protected @Nullable Type resolveCollectionSourceReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		if (returnType instanceof CollectionType) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				CollectionType collectionType = (CollectionType)returnType;
				if ((sourceType instanceof CollectionType) && ((CollectionType)sourceType).isIsNullFree() && !collectionType.isIsNullFree()) {
					@SuppressWarnings("null")@NonNull Type elementType = collectionType.getElementType();
					PivotMetamodelManager metamodelManager = (PivotMetamodelManager)environmentFactory.getMetamodelManager();
					returnType = metamodelManager.getCollectionType(collectionType.isOrdered(), collectionType.isUnique(),
						elementType, true, collectionType.getLowerValue(), collectionType.getUpperValue());
				}
			}
		}
		return returnType;
	}
}
