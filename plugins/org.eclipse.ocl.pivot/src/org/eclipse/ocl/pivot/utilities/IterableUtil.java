/*******************************************************************************
 * Copyright (c) 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 * E.D.Willink - Bug 360072
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * IterableUtil remedies defects in com.google.common.collect.Iterables, in particular the dual Iterables such as addAll.
 *
 * @since 1.11
 */
public class IterableUtil
{
	/**
	 * Adds every element of one iterable to another, returning true if anything added.
	 *
	 * @throws UnsupportedOperationException if addTo does not support add().
	 */
	public <T> void addAll(@NonNull Iterable<T> addTo, @NonNull Iterable<? extends T> elementsToAdd) {
		if (elementsToAdd instanceof Collection) {
			if (addTo instanceof Collection) {
				((Collection<T>)addTo).addAll((Collection<? extends T>)elementsToAdd);
			}
			else {
				Iterators.addAll((Collection<T>)addTo, elementsToAdd.iterator());
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Removes every element of one iterable from another, returning true if anything removed.
	 *
	 * @throws UnsupportedOperationException if removeFrom does not support remove().
	 */
	public static boolean removeAll(@NonNull Iterable<?> removeFrom, @NonNull Iterable<?> elementsToRemove) {
		if (elementsToRemove instanceof Collection) {
			if (removeFrom instanceof Collection) {
				return ((Collection<?>)removeFrom).removeAll((Collection<?>)elementsToRemove);
			}
			else {
				return Iterators.removeAll(removeFrom.iterator(), (Collection<?>)elementsToRemove);
			}
		}
		else {
			Iterator<?> removeFromIterator = removeFrom.iterator();
			boolean didRemove = false;
			while (removeFromIterator.hasNext()) {
				if (Iterables.contains(elementsToRemove, removeFromIterator.next())) {
					removeFromIterator.remove();
					didRemove = true;
				}
			}
			return didRemove;
		}
	}

	/**
	 * Retain only those elements of one iterable that are present in another, returning true if anything removed.
	 *
	 * @throws UnsupportedOperationException if removeFrom does not support remove().
	 */
	public static boolean retainAll(@NonNull Iterable<?> removeFrom, @NonNull Iterable<?> elementsToRetain) {
		if (elementsToRetain instanceof Collection) {
			if (removeFrom instanceof Collection) {
				return ((Collection<?>)removeFrom).retainAll((Collection<?>)elementsToRetain);
			}
			else {
				return Iterators.retainAll(removeFrom.iterator(), (Collection<?>)elementsToRetain);
			}
		}
		else {
			Iterator<?> removeFromIterator = removeFrom.iterator();
			boolean didRemove = false;
			while (removeFromIterator.hasNext()) {
				if (!Iterables.contains(elementsToRetain, removeFromIterator.next())) {
					removeFromIterator.remove();
					didRemove = true;
				}
			}
			return didRemove;
		}
	}
}
