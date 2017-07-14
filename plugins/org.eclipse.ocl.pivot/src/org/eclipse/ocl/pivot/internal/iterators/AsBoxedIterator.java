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
package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * AsBoxedIterator provides a lazy evaluation of the IdResolver::boxedValueOf operation.
 *
 * @since 1.3
 */
public class AsBoxedIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue asBoxed(@NonNull CollectionValue sourceValue, @NonNull IdResolver idResolver) {
		LazyCollectionValueImpl collectionValue = new LazyCollectionValueImpl(sourceValue.getTypeId(), new AsBoxedIterator(sourceValue, idResolver), null);
		return collectionValue;
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull IdResolver idResolver;
	private final @NonNull LazyIterator sourceIterator;

	public AsBoxedIterator(@NonNull CollectionValue sourceValue, @NonNull IdResolver idResolver) {
		this.sourceValue = sourceValue;
		this.idResolver = idResolver;
		this.sourceIterator = sourceValue.lazyIterator();
	}

	@Override
	public int getNextCount() {
		int hasNextCount = sourceIterator.hasNextCount();
		if (hasNextCount > 0) {
			Object element = sourceIterator.next();
			Object boxedElement = idResolver.boxedValueOf(element);
			return setNext(boxedElement, hasNextCount);
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new AsBoxedIterator(sourceValue, idResolver);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsBoxed");
		sourceIterator.reIterator().toString(s, sizeLimit);
	}
}
