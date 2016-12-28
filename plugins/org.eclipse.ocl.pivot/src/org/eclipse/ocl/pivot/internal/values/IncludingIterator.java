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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * IncludingIterator provides a lazy evaluation of the Collection::including operation.
 *
 * @since 1.3
 */
public class IncludingIterator extends AbstractCollectionIterator
{
	public static @NonNull CollectionValue including(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		return new IncludingIterator(collectionTypeId, firstValue, secondValue);
	}

	private enum NextIs { PREFIX, SUFFIX, END };

	protected final @NonNull Iterator<@Nullable Object> prefix;
	protected final @Nullable Object suffix;
	private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
	private @NonNull NextIs nextIs = NextIs.PREFIX;
	private boolean suffixIsInPrefix = false;

	public IncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		super(collectionTypeId);
		this.prefix = firstValue.iterator();
		this.suffix = secondValue;
	}

	@Override
	protected boolean canBeIterable() {
		return equalsStrategy == null;
	}

	@Override
	public boolean hasNext() {
		if (nextIs == NextIs.PREFIX) {
			if (prefix.hasNext()) {
				return true;
			}
			nextIs = NextIs.SUFFIX;
		}
		if (nextIs == NextIs.SUFFIX) {
			if (!suffixIsInPrefix) {
				return true;
			}
			nextIs = NextIs.END;
		}
		return false;
	}

	@Override
	public @Nullable Object next() {
		EqualsStrategy equalsStrategy2 = equalsStrategy;
		if (equalsStrategy2 == null) {
			equalsStrategy2 = equalsStrategy = isUnique() ? TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false) : EqualsStrategy.NotEqualsStrategy.INSTANCE;
			hasNext();
		}
		if (nextIs == NextIs.PREFIX) {
			Object next = prefix.next();
			if (equalsStrategy2.isEqual(next, suffix)) {
				suffixIsInPrefix = true;
			}
			return next;
		}
		else if (nextIs == NextIs.SUFFIX) {
			nextIs = NextIs.END;
			return suffix;
		}
		else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Including{");
		s.append(prefix);
		s.append(",");
		s.append(suffix instanceof String ? "'" + suffix + "'" : suffix);
		s.append("}");
	}
}
