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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * CollectionLiteralIterator adapts a CollectionLiteralExp to comply with the BaggableIterator protocol.
 * Multiple entries are pruned from Sets and OrderedSets, counted in Bags,
 *
 * @since 1.3
 */
public class CollectionLiteralIterator extends LazyCollectionValueImpl
{
	public static class Range
	{
		private int first;
		private int last;

		public Range(int first, int last) {
			this.first = first;
			this.last = last;
		}
	}

	protected final @NonNull List<@Nullable Object> literalElements;
	protected final @NonNull Iterator<@Nullable Object> literalIterator;
	private int nextInt = 0;
	private int lastInt = -1;

	public CollectionLiteralIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull List<@Nullable Object> literalElements) {
		super(collectionTypeId, 0);
		this.literalElements = literalElements;
		this.literalIterator = literalElements.iterator();
		if (!isSequence()) {
			eagerIterable();//.getMapOfElement2elementCount();				// Need history to enforce uniqueness, count repeats
		}
	}

	@Override
	public int getNextCount() {
		if (nextInt <= lastInt) {
			return setNext(ValueUtil.integerValueOf(nextInt++), 1);
		}
		while (literalIterator.hasNext()) {
			Object literalElement = literalIterator.next();
			if (literalElement instanceof Range) {
				Range range = (Range)literalElement;
				nextInt = range.first;
				lastInt = range.last;
				if (nextInt <= lastInt) {
					return setNext(ValueUtil.integerValueOf(nextInt++), 1);
				}
			}
			else {
				nextInt = 0;
				lastInt = -1;
				return setNext(literalElement, 1);
			}
		}
		return 0;
	}

	@Override
	protected @NonNull LazyIterator reIterator() {
		return new CollectionLiteralIterator(typeId, literalElements);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append(getTypeId().getName());
		s.append("{");
		boolean isFirst = true;
		for (Object literalElement : literalElements) {
			if (!isFirst) {
				s.append(",");
			}
			if (literalElement instanceof Range) {
				Range range = (Range)literalElement;
				s.append(range.first + ".." + range.last);
			}
			else {
				s.append(literalElement);
			}
			isFirst = false;
		}
		s.append("}");
	}
}
