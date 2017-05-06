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
package org.eclipse.ocl.pivot.internal.iterators;

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.values.BaggableIterator;

/**
 * Optimized iterator over an empty Collection.
 */
class BaggableNullIterator implements BaggableIterator<@Nullable Object>
{
	/**
	 * Returns new array iterator over the given object array
	 */
	public BaggableNullIterator() {}

	/**
	 * Returns true if this iterator contains more elements.
	 */
	@Override
	public boolean hasNext() {
		return false;
	}

	/**
	 * Returns 1 if this iterator contains more elements.
	 */
	@Override
	public int hasNextCount() {
		return 0;
	}

	/**
	 * Returns the next element of this iterator.
	 */
	@Override
	public Object next() {
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}