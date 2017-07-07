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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.values.BaggableIterator;

/**
 * Optimized iterator over a List for use in OCL contents where the list is known to be stable
 * and any call to next() is guarded by hasNext().
 */
class BaggableListIterator<T> implements BaggableIterator<T>
{
	protected final @NonNull List<T> elements;
	protected final int size;
	private int index;

	/**
	 * Returns new array iterator over the given object array
	 */
	public BaggableListIterator(@NonNull List<T> elements) {
		this.elements = elements;
		index = 0;
		this.size = elements.size();
	}

	/**
	 * Returns true if this iterator contains more elements.
	 */
	@Override
	public boolean hasNext() {
		return index < size;
	}

	/**
	 * Returns 1 if this iterator contains more elements.
	 */
	@Override
	public int hasNextCount() {
		return index < size ? 1 : 0;
	}

	/**
	 * Returns the next element of this iterator.
	 */
	@Override
	public T next() {
		return elements.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("List");
		LazyCollectionValueImpl.appendIterable(s, elements, 50);
		return s.toString();
	}
}