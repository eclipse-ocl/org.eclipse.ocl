/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
*******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

import com.google.common.collect.Iterables;

/**
 * An ListOfList is just a List of List but solves problems of nested downcasting from E to Element.
 */
public class ListOfList<E>
{
	private @NonNull List<@NonNull List<E>> outerIterable = new ArrayList<>();

	public ListOfList() {}

	public void add(@NonNull List<E> innerIterable) {
		outerIterable.add(innerIterable);
	}

	public @NonNull Iterable<E> getInnerIterable() {
		return Iterables.concat(outerIterable);
	}

	public @NonNull List<@NonNull List<E>> getOuterIterable() {
		return outerIterable;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirstOuter = true;
		for (@NonNull List<E> innerIterable : outerIterable) {
			if (!isFirstOuter) {
				s.append(",");
			}
			s.append("[");
			boolean isFirstInner = true;
			for (E element : innerIterable) {
				if (!isFirstInner) {
					s.append(",");
				}
				s.append(String.valueOf(element));
				isFirstInner = false;
			}
			s.append("]");
			isFirstOuter = false;
		}
		return s.toString();
	}
}
