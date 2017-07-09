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
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * SelectIterator provides the framework for lazy evaluation of a Collection::select iteration.
 *
 * @since 1.3
 */
public abstract class SelectIterator extends AbstractLazyIterator
{
	//	public static @NonNull CollectionValue select(@NonNull CollectionValue sourceValue) {
	//		LazyIterator inputIterator = new SelectIterator(sourceValue);
	//		return new SmartCollectionValueImpl(sourceValue.getTypeId(), inputIterator, sourceValue);
	//	}

	protected final @NonNull CollectionValue sourceValue;
	private final @NonNull LazyIterator sourceIterator;

	protected SelectIterator(@NonNull CollectionValue sourceValue) {
		this.sourceValue = sourceValue;
		this.sourceIterator = sourceValue.lazyIterator();
	}

	protected abstract boolean body(Object next);

	@Override
	public int getNextCount() {
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			boolean hasNext = body(next);
			if (hasNext) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Select{");
		//		if (hasCache()) {
		//			appendIterable(s);
		//			if (hasNext()) {
		//				s.append(";«future»");
		//			}
		//		}
		//		else {
		//			s.append("«future»");
		//		}
		s.append("}");
	}
}