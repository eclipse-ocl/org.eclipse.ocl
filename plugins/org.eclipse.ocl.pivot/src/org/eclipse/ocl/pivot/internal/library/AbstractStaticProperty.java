/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * @since 1.18
 */
public abstract class AbstractStaticProperty extends AbstractOperation
{
	protected final @NonNull PropertyId propertyId;

	protected AbstractStaticProperty(@NonNull String[] propertyPath) {
		int length = propertyPath.length;
		assert length >= 3;
		PackageId packageId = IdManager.getNsURIPackageId(propertyPath[0], null, null);
		for (int i = 1; i < length-2; i++) {
			packageId = packageId.getNestedPackageId(propertyPath[i]);
		}
		ClassId classId = packageId.getClassId(propertyPath[length-2], 0);
		this.propertyId = classId.getPropertyId(propertyPath[length-1]);
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		ModelManager modelManager = executor.getModelManager();
		Object value = modelManager.basicGetForeignPropertyValue(null, propertyId);
		if (value == null) {
			value = initialValue(executor);
			modelManager.setForeignPropertyValue(null, propertyId, value == null ? ValueUtil.NULL_VALUE : value);
		}
		return value == ValueUtil.NULL_VALUE ? null : value;
	}

	abstract @Nullable Object initialValue(@NonNull Executor executor);
}