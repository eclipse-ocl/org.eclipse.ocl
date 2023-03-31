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
 * An IterableOfIterable is just an Iterable of Iterable but solves problems of nested downcasting from E to Element.
 */
public class IterableOfIterable<E>
{
	private @NonNull List<@NonNull Iterable<@NonNull E>> outerIterable = new ArrayList<>();

	public IterableOfIterable() {}

	public void add(@NonNull Iterable<@NonNull E> innerIterable) {
		outerIterable.add(innerIterable);
	}

	public @NonNull Iterable<@NonNull E> getInnerIterable() {
		return Iterables.concat(outerIterable);
	}

	public @NonNull Iterable<@NonNull Iterable<@NonNull E>> getOuterIterable() {
		return outerIterable;
	}
}
