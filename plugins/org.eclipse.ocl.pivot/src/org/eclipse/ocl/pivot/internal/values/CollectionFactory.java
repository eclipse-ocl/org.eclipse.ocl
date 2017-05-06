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
package org.eclipse.ocl.pivot.internal.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.iterators.LazyIterable;

/**
 * AbstractCollectionFactory provides the common functionality for CollectionValue creation.
 * @since 1.3
 */
public interface CollectionFactory
{
	/**
	 * Add count of anElement to the lazyIterable updating element occurrence counts.
	 */
	<@Nullable E> void addTo(@NonNull LazyIterable<E> lazyIterable, E anElement, int count);
	@NonNull String getKind();
	boolean isBag();
	boolean isOrdered();
	boolean isOrderedSet();
	boolean isSequence();
	boolean isSet();
	boolean isUnique();
	/**
	 * Remove count of anElement to the lazyIterable updating element occurrence counts.
	 */
	<@Nullable E> void removeFrom(@NonNull LazyIterable<E> lazyIterable, E anElement, int count);
}
