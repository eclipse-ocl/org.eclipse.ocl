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
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.SequenceValue;

import com.google.common.collect.Sets;

/**
 * @generated NOT
 * @since 1.3
 */
public class IncludingAllEvaluator
{
	public static @NonNull CollectionValue includingAll(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		if (firstValue.isOrdered()) {
			if (firstValue.isUnique()) {
				Iterable<? extends Object> elements = firstValue.iterable();
				OrderedSet<Object> result = new OrderedSetImpl<Object>(elements);
				for (Object value : secondValue) {
					result.add(value);
				}
				return new SparseOrderedSetValueImpl(firstValue.getTypeId(), result);
			}
			else {
				return new SequenceIncludingAllIterator(firstValue, secondValue);
			}
		}
		else {
			if (firstValue.isUnique()) {
				Iterable<? extends Object> elements = firstValue.iterable();
				Set<Object> result = Sets.newHashSet(elements);
				for (Object value : secondValue) {
					result.add(value);
				}
				return new SetValueImpl(firstValue.getTypeId(), result);
			}
			else {
				Iterable<? extends Object> elements = firstValue.iterable();
				Bag<Object> result = new BagImpl<Object>(elements);
				for (Object value : secondValue) {
					result.add(value);
				}
				return new BagValueImpl(firstValue.getTypeId(), result);
			}
		}
	}

	private static class AbstractIncludingAllIterator extends AbstractCollectionIterator
	{
		private enum NextIs { PREFIX, SUFFIX, END };

		protected final @NonNull Iterator<@Nullable Object> prefix;
		protected final @NonNull Iterator<@Nullable Object> suffix;
		private @Nullable NextIs nextIs = null;

		public AbstractIncludingAllIterator(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
			super(firstValue.getTypeId());
			this.prefix = firstValue.iterator();
			this.suffix = secondValue.iterator();
		}

		@Override
		protected boolean canBeIterable() {
			return nextIs == null;
		}

		@Override
		public boolean hasNext() {
			if (nextIs == null) {
				nextIs = NextIs.PREFIX;
			}
			if (nextIs == NextIs.PREFIX) {
				if (prefix.hasNext()) {
					return true;
				}
				nextIs = NextIs.SUFFIX;
			}
			if (nextIs == NextIs.SUFFIX) {
				if (suffix.hasNext()) {
					return true;
				}
				nextIs = NextIs.END;
			}
			return false;
		}

		@Override
		public @Nullable Object next() {
			if (nextIs == null) {
				hasNext();
			}
			if (nextIs == NextIs.PREFIX) {
				return prefix.next();
			}
			else if (nextIs == NextIs.SUFFIX) {
				return suffix.next();
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("IncludingAll{");
			s.append(prefix);
			s.append(",");
			s.append(suffix);
			s.append("}");
		}
	}

	private static class SequenceIncludingAllIterator extends AbstractIncludingAllIterator implements SequenceValue
	{
		public SequenceIncludingAllIterator(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
			super(firstValue, secondValue);
		}
	}
}