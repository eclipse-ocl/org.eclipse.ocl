/*******************************************************************************
 * Copyright (c) 2015, 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.EntryType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * EntryKeyProperty realizes the Entry::key library property.
 *
 * @since 7.0
 */
public class EntryKeyProperty extends AbstractProperty
{
	public static final @NonNull EntryKeyProperty INSTANCE = new EntryKeyProperty();

	@Override
	public @NonNull Type evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
	/*	if (sourceValue instanceof MapValue) {				// Legacy compatibility.
			MapValue mapValue = asMapValue(sourceValue);
			assert false: "Use MapType.getKeyType() directly";
			MapTypeId mapTypeId = mapValue.getTypeId();
			TypeId keyTypeId = mapTypeId.getKeyTypeId();
			IdResolver idResolver = executor.getIdResolver();
			return idResolver.getType(keyTypeId);
		} */
		EntryType entryType = asEntryType(sourceValue);
		return PivotUtil.getKeyType(entryType);			// XXX
	}
}
