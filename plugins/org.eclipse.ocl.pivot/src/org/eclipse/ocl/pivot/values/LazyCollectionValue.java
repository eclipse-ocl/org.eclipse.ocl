/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.iterators.LazyIterable;

public interface LazyCollectionValue extends CollectionValue
{
	@NonNull BaggableIterator<@Nullable Object> baggableIterator();

	/**
	 * Return an iterable that is lazily populated and which my be re-iterated exploiting cached
	 * values from a first iteration. This provides opportunities for redundant iterations to be skipped.
	 */
	@NonNull Iterable<@Nullable Object> cachedIterable();

	/**
	 * Return an iterable that has been eagerly populated. This inhibits opportunities for
	 * redundant iterations to be skipped but may improve the speed of subsequent iterations.
	 *
	 * An eager evaluation is needed to ensure that any invalid content is discovered before any element is used.
	 */
	@NonNull LazyIterable<@Nullable Object> eagerIterable();

	/**
	 * Return an iterable that is intended to be iterated at most once.
	 */
	@NonNull LazyIterable<@Nullable Object> lazyIterable();

	/**
	 * @generated NOT
	 */
	@NonNull Iterator<@Nullable Object> lazyIterator();
}
