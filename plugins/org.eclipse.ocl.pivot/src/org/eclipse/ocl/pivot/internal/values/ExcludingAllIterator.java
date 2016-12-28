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
import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * ExcludingAllIterator provides a lazy evaluation of the Collection::excludingAll operation.
 *
 * @since 1.3
 */
public class ExcludingAllIterator extends AbstractCollectionIterator
{
	public static @NonNull CollectionValue excludingAll(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		return new ExcludingAllIterator(firstValue, secondValue);
	}

	protected final @NonNull Iterator<@Nullable Object> iterator;
	protected final @NonNull Iterable<? extends Object> exclusions;
	private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
	private boolean hasNext = false;
	private @Nullable Object next;

	public ExcludingAllIterator(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		super(firstValue.getTypeId());
		this.iterator = firstValue.iterator();
		this.exclusions = secondValue.iterable();
	}

	@Override
	protected boolean canBeIterable() {
		return equalsStrategy == null;
	}

	@Override
	public boolean hasNext() {
		EqualsStrategy equalsStrategy2 = equalsStrategy;
		if (equalsStrategy2 == null) {
			equalsStrategy2 = equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}
		if (hasNext) {
			return true;
		}
		while (iterator.hasNext()) {
			next = iterator.next();
			boolean reject = false;
			for (Object exclusion : exclusions) {		// FIXME set contains
				if (equalsStrategy2.isEqual(next, exclusion)) {
					reject = true;
					break;
				}
			}
			if (!reject) {
				hasNext = true;
				return true;
			}
		}
		next = null;					// Unnecessary but might help garbage collection
		return false;
	}

	@Override
	public @Nullable Object next() {
		if (hasNext) {
			hasNext = false;
			return next;
		}
		else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("ExcludingAll{");
		s.append(iterator);
		s.append(",");
		s.append(exclusions);
		s.append("}");
	}
}