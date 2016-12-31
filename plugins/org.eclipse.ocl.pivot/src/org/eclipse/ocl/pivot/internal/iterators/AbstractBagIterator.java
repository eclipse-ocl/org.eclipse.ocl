/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.iterators;

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.values.AbstractCollectionIterator;

/**
 * AbstractBagIterator provides the common functionality for lzay evaluation using the hasNextCount/next
 * BagIterator protocol. Derived bag iterators must implement getNextCount() to describe the next entry that
 * may then be obtained by getNext().
 *
 * @since 1.3
 */
public abstract class AbstractBagIterator extends AbstractCollectionIterator
{
	private boolean canBeIterable = true;
	private int hasNextCount = 0;
	private int useCount = 0;

	protected AbstractBagIterator(@NonNull CollectionTypeId collectionTypeId) {
		super(collectionTypeId);
	}

	@Override
	protected final boolean canBeIterable() {
		return canBeIterable;
	}

	protected abstract Object getNext();

	protected abstract int getNextCount();

	@Override
	public final boolean hasNext() {
		if ((hasNextCount > 0) || (hasNextCount() > 0)) {
			useCount = 1;
			return true;
		}
		else {
			useCount = 0;
			return false;
		}
	}

	@Override
	public final int hasNextCount() {
		if (hasNextCount <= 0) {
			canBeIterable = false;
			hasNextCount = getNextCount();
		}
		useCount = hasNextCount;
		return useCount;
	}

	@Override
	public final Object next() {
		if (hasNextCount <= 0) {				// If no prior hasNext() / hasNextCount()
			if (hasNextCount() <= 0) {
				throw new NoSuchElementException();
			}
			useCount = 1;						// Assume iterating one element at a time
		}
		hasNextCount -= useCount;
		useCount = 0;
		return getNext();
	}
}