/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSet;

import com.google.common.collect.Sets;

/**
 * @generated NOT
 * @since 1.3
 */
public class IncludingEvaluator
{
	public static @NonNull CollectionValue including(@NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		if (firstValue.isOrdered()) {
			if (firstValue.isUnique()) {
				if (secondValue instanceof InvalidValueException) {
					throw new InvalidValueException(PivotMessages.InvalidSource, "including");
				}
				Iterable<? extends Object> elements = firstValue.iterable();
				OrderedSet<Object> result = new OrderedSetImpl<Object>(elements);
				result.add(secondValue);
				return new SparseOrderedSetValueImpl(firstValue.getTypeId(), result);
			}
			else {
				return new SequenceIncludingIterator(firstValue, secondValue);
			}
		}
		else {
			if (firstValue.isUnique()) {
				assert !(secondValue instanceof InvalidValueException);
				Iterable<? extends Object> elements = firstValue.iterable();
				Set<Object> result = Sets.newHashSet(elements);
				result.add(secondValue);
				return new SetValueImpl(firstValue.getTypeId(), result);
			}
			else {
				assert !(secondValue instanceof InvalidValueException);
				Iterable<? extends Object> elements = firstValue.iterable();
				Bag<Object> result = new BagImpl<Object>(elements);
				result.add(secondValue);
				return new BagValueImpl(firstValue.getTypeId(), result);
			}
		}
	}

	private static class SequenceIncludingIterator extends AbstractSequenceIterator
	{
		enum NextIs { PREFIX, SUFFIX, NONE };

		protected final @NonNull Iterator<@Nullable Object> prefix;
		protected final @Nullable Object suffix;
		private @Nullable NextIs nextIs;
		private @Nullable LazyIterable<@Nullable Object> iterable = null;
		private int hashCode = 0;

		public SequenceIncludingIterator(@NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(firstValue.getElementTypeId());
			this.prefix = firstValue.iterator();
			this.suffix = secondValue;
			this.nextIs = null;
		}

		@Override
		public boolean equals(Object obj) {
			throw new IllegalIteratorStateException();
		}

		@Override
		public boolean hasNext() {
			return nextIs != NextIs.NONE;
		}

		@Override
		public int hashCode() {
			if (hashCode == 0) {
				synchronized (this) {
					if (hashCode == 0) {
						hashCode = computeCollectionHashCode(isOrdered(), isUnique(), iterable().getElements());
					}
				}
			}
			return hashCode;
		}

		@Override
		public @NonNull LazyIterable<@Nullable Object> iterable() {
			LazyIterable<@Nullable Object> iterable2 = iterable;
			if (iterable2 == null) {
				if (nextIs != null) {
					throw new IllegalStateException();
				}
				iterable2 = iterable = new LazyIterable<>(this);
			}
			return iterable2;
		}

		@Override
		public @NonNull Iterator<@Nullable Object> iterator() {
			return iterable != null ? iterable.iterator() : this;
		}

		@Override
		public @Nullable Object next() {
			if (nextIs == null) {
				this.nextIs = prefix.hasNext() ? NextIs.PREFIX : NextIs.SUFFIX;
			}
			if (nextIs == NextIs.PREFIX) {
				Object next = prefix.next();
				if (!prefix.hasNext()) {
					nextIs = NextIs.SUFFIX;
				}
				return next;
			}
			else if (nextIs == NextIs.SUFFIX) {
				nextIs = NextIs.NONE;
				return suffix;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		@Override
		public @Nullable Object last() {
			return suffix;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("SeqInc{");
			s.append(prefix);
			s.append(",");
			s.append(suffix instanceof String ? "'" + suffix + "'" : suffix);
			s.append("}");
		}
	}
}