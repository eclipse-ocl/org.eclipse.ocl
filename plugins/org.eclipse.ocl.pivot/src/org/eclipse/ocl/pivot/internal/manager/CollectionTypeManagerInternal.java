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
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * CollectionTypeManagerInternal encapsulates the knowledge about known collection types.
 *
 * @since 7.0
 */
public class CollectionTypeManagerInternal extends AbstractCollectionTypeManager
{
	protected final @NonNull CompleteModelInternal completeModel;

	public CollectionTypeManagerInternal(@NonNull CompleteModelInternal completeModel) {
		super(completeModel.getStandardLibrary());
		this.completeModel = completeModel;
	}

	@Override
	protected @NonNull CollectionType createCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		CollectionTypeId collectionTypeId = typeArguments.getCollectionTypeId();
		Type elementType = typeArguments.getElementType();
		boolean isNullFree = typeArguments.isNullFree();
		IntegerValue lower = typeArguments.getLower();
		UnlimitedNaturalValue upper = typeArguments.getUpper();
		CollectionType genericCollectionType = completeModel.getStandardLibrary().getCollectionType(collectionTypeId);
		CollectionType collectionType = PivotUtil.createCollectionType(genericCollectionType, elementType, isNullFree, lower, upper);
		completeModel.resolveSuperClasses(collectionType, genericCollectionType);
		Orphanage orphanage = completeModel.getOrphanage();
		collectionType.setOwningPackage(orphanage);
		return collectionType;
	}

	@Override
	protected boolean isValid(@Nullable Type type) {
		return (type != null) && (type.eResource() != null);
	}
}