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

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * BagIterator provides a lazy evaluation of the Collection::asBag operation.
 *
 * @since 1.3
 */
public class AsBagIterator extends AbstractCollectionIterator implements BagValue
{
	private final @NonNull Iterator<@Nullable Object> iterator;
	private boolean canBeIterable = true;

	public AsBagIterator(@NonNull CollectionValue firstValue) {
		super(TypeId.BAG.getSpecializedId(firstValue.getElementTypeId()));
		this.iterator = firstValue.iterator();
	}

	@Override
	protected boolean canBeIterable() {
		return canBeIterable;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public @Nullable Object next() {
		canBeIterable = false;
		return iterator.next();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsBag{");
		s.append(iterator);
		s.append("}");
	}
}
