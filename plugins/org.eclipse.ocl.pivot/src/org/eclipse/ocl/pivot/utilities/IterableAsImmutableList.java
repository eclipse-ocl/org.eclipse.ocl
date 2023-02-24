/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.jdt.annotation.NonNull;

import com.google.common.collect.Iterables;

/**
 * An IterableAsImmutableList wraps an Iterable to look like a List for read-only accesses.
 */
public class IterableAsImmutableList<T> implements List<T>
{
	public static <T1> @NonNull List<T1> asList(@NonNull Iterable<T1> anIterable) {
		if (anIterable instanceof List) {
			return (List<T1>)anIterable;
		}
		else {
			return new IterableAsImmutableList<T1>(anIterable);
		}
	}

	/**
	 * The Iterable providing the 'list' contents.
	 */
	private final @NonNull Iterable<T> elements;

	/**
	 * Lazily computed size of the elements.
	 */
	private int size = -1;

	private IterableAsImmutableList(@NonNull Iterable<T> elements) {
		this.elements = elements;
	}

	@Override
	public boolean add(T e) {
		return immutable();
	}

	@Override
	public void add(int index, T element) {
		immutable();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return immutable();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return immutable();
	}

	@Override
	public void clear() {
		immutable();
	}

	@Override
	public boolean contains(Object o) {
		return Iterables.contains(elements, o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(int index) {
		return Iterables.get(elements, index);
	}

	private boolean immutable() {
		throw new UnsupportedOperationException(getClass().getName() + " only supports read-only access");
	}

	@Override
	public int indexOf(Object o) {
		return Iterables.indexOf(elements, null);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public @NonNull Iterator<T> iterator() {
		return elements.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("null")
	@Override
	public T remove(int index) {
		immutable();
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return immutable();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return immutable();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return immutable();
	}

	@Override
	public T set(int index, T element) {
		immutable();
		return element;
	}

	@Override
	public int size() {
		if (size < 0) {
			size = Iterables.size(elements);
		}
		return size;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T1> T1 @NonNull [] toArray(T1 @NonNull [] a) {
		throw new UnsupportedOperationException();
	}
}