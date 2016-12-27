/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;

/**
 * AbstractCollectionValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 */
public abstract class AbstractCollectionIterator extends AbstractCollectionValueImpl implements Iterator<@Nullable Object>
{
	protected AbstractCollectionIterator(@NonNull CollectionTypeId typeId) {
		super(typeId);
	}

	@Override
	public @NonNull Collection<? extends Object> asCollection() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Object asObject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int intSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Iterable<? extends Object> iterable() {
		//		return Lists.newArrayList((Iterator<?>)this);
		throw new UnsupportedOperationException();
	}

}
