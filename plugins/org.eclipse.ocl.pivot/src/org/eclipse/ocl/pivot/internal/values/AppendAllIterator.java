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
 * AppendAllIterator provides a lazy evaluation of the Collection::appendAll operation.
 *
 * @since 1.3
 */
public class AppendAllIterator extends AbstractCollectionIterator
{
	public static @NonNull CollectionValue appendAll(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		return new AppendAllIterator(firstValue, secondValue);
	}

	private final @NonNull Iterator<@Nullable Object> prefix;
	private final @NonNull Iterable<? extends Object> suffix;
	private final @NonNull Iterator<@Nullable Object> suffixIterator;
	private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
	private boolean hasNext = false;
	private @Nullable Object next;

	public AppendAllIterator(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		super(firstValue.getTypeId());
		this.prefix = firstValue.iterator();
		this.suffix = secondValue.iterable();
		this.suffixIterator = secondValue.iterator();
	}

	@Override
	protected boolean canBeIterable() {
		return equalsStrategy == null;
	}

	@Override
	public boolean hasNext() {
		EqualsStrategy equalsStrategy2 = equalsStrategy;
		if (equalsStrategy2 == null) {
			equalsStrategy2 = equalsStrategy = isUnique() ? TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false) : EqualsStrategy.NotEqualsStrategy.INSTANCE;
		}
		if (hasNext) {
			return true;
		}
		while (prefix.hasNext()) {
			next = prefix.next();
			boolean reject = false;
			for (Object exclusion : suffix) {		// FIXME set contains
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
		while (suffixIterator.hasNext()) {
			next = suffixIterator.next();
			hasNext = true;
			return true;
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
		s.append("AppendAll{");
		s.append(prefix);
		s.append(",");
		s.append(suffix);
		s.append("}");
	}
}
