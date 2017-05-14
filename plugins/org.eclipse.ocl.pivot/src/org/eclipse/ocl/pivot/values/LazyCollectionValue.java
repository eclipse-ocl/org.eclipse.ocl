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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.iterators.LazyIterable;

/**
 * LazyCollectionValue extends the CollectionValue to support lazy iterations. Lazy access attempts to
 * compute each output value from an input value without any cache for the entire contents. Cached access
 * computes in a similar fashion, but caches the output to facilitate efficient repeated iterations.
 * Eager access is similar to cached access, but eagerly populates every cache entry so that ant invalid#
 * content is detect before any output iteration occurs.
 *
 * @since 1.3
 */
public interface LazyCollectionValue extends CollectionValue
{
	/**
	 * Return an iterable that is lazily populated and which my be re-iterated exploiting cached
	 * values from a first iteration. This provides opportunities for redundant iterations to be skipped.
	 */
	@NonNull LazyIterable<@Nullable Object> cachedIterable();

	/**
	 * Return an iterable that has been eagerly populated. This inhibits opportunities for
	 * redundant iterations to be skipped but may improve the speed of subsequent iterations.
	 *
	 * An eager evaluation is needed to ensure that any invalid content is discovered before any element is used.
	 */
	@NonNull LazyIterable<@Nullable Object> eagerIterable();

	/**
	 * Return an iterator that avoids creating and populating a cache of the contents.
	 *
	 * If a re-iteration is attempted, the cache is activated and lazily populated by the second iteration.
	 * A third re-teration exploits the cache.
	 */
	@NonNull BaggableIterator<@Nullable Object> lazyIterator();
}
