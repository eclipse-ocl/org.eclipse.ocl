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

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.values.BaggableIterator;

/**
 * WrappedBaggableIterator wraps a conventional iterator so that it supports the BaggableIterator protocol.
 *
 * @since 1.3
 */
public class WrappedBaggableIterator<T> implements BaggableIterator<T>
{
	protected final @NonNull Iterator<? extends T> iterator;

	/**
	 * Returns new array iterator over the given object array
	 */
	public WrappedBaggableIterator(@NonNull Iterator<? extends T> iterator) {
		assert !(iterator instanceof BaggableIterator);
		this.iterator = iterator;
	}

	/**
	 * Returns true if this iterator contains more elements.
	 */
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	/**
	 * Returns 1 if this iterator contains more elements.
	 */
	@Override
	public int hasNextCount() {
		return iterator.hasNext() ? 1 : 0;
	}

	/**
	 * Returns the next element of this iterator.
	 */
	@Override
	public T next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return iterator.toString();
	}
}