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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * ExcludingAllIterator provides a lazy evaluation of the Collection::excludingAll operation.
 *
 * @since 1.3
 */
public class ExcludingAllIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue excludingAll(@NonNull CollectionValue sourceValue, @NonNull CollectionValue excludeValue) {
		ExcludingAllIterator inputIterator = new ExcludingAllIterator(sourceValue, excludeValue);
		return new LazyCollectionValueImpl(sourceValue.getTypeId(), inputIterator, sourceValue);
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull CollectionValue excludeValue;
	private final @NonNull LazyIterator sourceIterator;

	public ExcludingAllIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue excludeValue) {
		this.sourceValue = sourceValue;
		this.excludeValue = ValueUtil.eagerCollectionValue(excludeValue);
		this.sourceIterator = sourceValue.lazyIterator();
	}

	@Override
	public int getNextCount() {
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			if (!excludeValue.includes(next)) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new ExcludingAllIterator(sourceValue, excludeValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("ExcludingAll{");
		sourceIterator.reIterator().toString(s, sizeLimit-20);
		s.append(",");
		excludeValue.toString(s, sizeLimit-1);
		s.append("}");
	}
}