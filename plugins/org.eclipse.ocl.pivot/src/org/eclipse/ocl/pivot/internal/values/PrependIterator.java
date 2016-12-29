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
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;

/**
 * PrependIterator provides a lazy evaluation of the Collection::prepend operation.
 *
 * @since 1.3
 */
public class PrependIterator extends AbstractCollectionIterator implements OrderedCollectionValue
{
	public static @NonNull OrderedCollectionValue prepend(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		return new PrependIterator(collectionTypeId, firstValue, secondValue);
	}

	protected final @Nullable Object prefix;
	protected final @NonNull Iterator<@Nullable Object> suffix;
	private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
	private boolean hasNext = true;
	private @Nullable Object next;

	public PrependIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		super(collectionTypeId);
		this.prefix = secondValue;
		this.suffix = firstValue.iterator();
		this.next = prefix;
	}

	@Override
	protected boolean canBeIterable() {
		return equalsStrategy == null;
	}

	@Override
	public boolean hasNext() {
		if (!hasNext) {
			EqualsStrategy equalsStrategy2 = equalsStrategy;
			assert equalsStrategy2 != null;
			while (suffix.hasNext()) {
				next = suffix.next();
				if (!equalsStrategy2.isEqual(next, suffix)) {
					hasNext = true;
					break;
				}
			}
		}
		return hasNext;
	}

	@Override
	public @Nullable Object next() {
		EqualsStrategy equalsStrategy2 = equalsStrategy;
		if (equalsStrategy2 == null) {
			equalsStrategy2 = equalsStrategy = isUnique() ? TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false) : EqualsStrategy.NotEqualsStrategy.INSTANCE;
		}
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		hasNext = false;
		return next;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Prepend{");
		s.append(prefix instanceof String ? "'" + prefix + "'" : prefix);
		s.append(",");
		s.append(suffix);
		s.append("}");
	}
}
