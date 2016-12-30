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

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * IncludingIterator provides a lazy evaluation of the Collection::including operation.
 *
 * @since 1.3
 */
public class CollectionLiteralIterator extends AbstractCollectionIterator
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

	protected final @NonNull CollectionLiteralExp collectionLiteralExp;
	protected final @NonNull Iterator<@Nullable Object> literalElements;
	private boolean canBeIterable = true;
	private boolean hasNext = false;
	private @Nullable Object next;
	private int nextInt = 0;
	private int lastInt = -1;

	public CollectionLiteralIterator(@NonNull CollectionLiteralExp collectionLiteralExp, @NonNull List<@Nullable Object> literalElements) {
		super((@NonNull CollectionTypeId) collectionLiteralExp.getTypeId());
		this.collectionLiteralExp = collectionLiteralExp;
		this.literalElements = literalElements.iterator();
	}

	@Override
	protected boolean canBeIterable() {
		return canBeIterable;
	}

	@Override
	public boolean hasNext() {
		if (hasNext) {
			return true;
		}
		if (nextInt <= lastInt) {
			hasNext = true;
			return true;
		}
		while (literalElements.hasNext()) {
			Object literalElement = literalElements.next();
			if (literalElement instanceof Range) {
				Range range = (Range)literalElement;
				nextInt = range.first;
				lastInt = range.last;
				if (nextInt <= lastInt) {
					hasNext = true;
					return true;
				}
			}
			else {
				next = literalElement;
				nextInt = 0;
				lastInt = -1;
				hasNext = true;
				return true;
			}
		}
		return false;
	}

	@Override
	public int hasNextCount() {
		assert basicGetIterable() != null;		// Ensure internal ierable can accumulate bags one at a time
		return hasNext() ? 1 : 0;
	}

	@Override
	public @Nullable Object next() {
		canBeIterable = false;
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		else if (nextInt <= lastInt) {
			hasNext = false;
			return ValueUtil.integerValueOf(nextInt++);
		}
		else {
			hasNext = false;
			return next;
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append(collectionLiteralExp);
	}
}
