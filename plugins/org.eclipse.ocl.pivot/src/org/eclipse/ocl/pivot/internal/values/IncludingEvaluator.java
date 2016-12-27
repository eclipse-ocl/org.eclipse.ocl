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
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * @generated NOT
 * @since 1.3
 */
public class IncludingEvaluator
{
	public static @NonNull CollectionValue including(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		if (firstValue.isOrdered()) {
			if (firstValue.isUnique()) {
				return new OrderedSetIncludingIterator(collectionTypeId, firstValue, secondValue);
			}
			else {
				return new SequenceIncludingIterator(collectionTypeId, firstValue, secondValue);
			}
		}
		else {
			if (firstValue.isUnique()) {
				return new SetIncludingIterator(collectionTypeId, firstValue, secondValue);
			}
			else {
				//				return new BagIncludingIterator(firstValue, secondValue);
				assert !(secondValue instanceof InvalidValueException);
				Iterable<? extends Object> elements = firstValue.iterable();
				Bag<Object> result = new BagImpl<Object>(elements);
				result.add(secondValue);
				return new BagValueImpl(firstValue.getTypeId(), result);
			}
		}
	}

	private static class AbstractIncludingIterator extends AbstractCollectionIterator
	{
		private enum NextIs { PREFIX, SUFFIX, END };

		protected final @NonNull Iterator<@Nullable Object> prefix;
		protected final @Nullable Object suffix;
		private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
		private @NonNull NextIs nextIs = NextIs.PREFIX;
		private boolean suffixIsInPrefix = false;

		public AbstractIncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
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

	private static class BagIncludingIterator extends AbstractIncludingIterator implements BagValue
	{
		public BagIncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(collectionTypeId, firstValue, secondValue);
		}
	}

	private static class OrderedSetIncludingIterator extends AbstractIncludingIterator implements OrderedSetValue
	{
		public OrderedSetIncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(collectionTypeId, firstValue, secondValue);
		}
	}

	private static class SequenceIncludingIterator extends AbstractIncludingIterator implements SequenceValue
	{
		public SequenceIncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(collectionTypeId, firstValue, secondValue);
		}

		//		@Override
		//		public @Nullable Object last() {
		//			return suffix;		-- need prefix.isNotInvalid check
		//		}
	}

	private static class SetIncludingIterator extends AbstractIncludingIterator implements SetValue
	{
		public SetIncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(collectionTypeId, firstValue, secondValue);
		}
	}
}