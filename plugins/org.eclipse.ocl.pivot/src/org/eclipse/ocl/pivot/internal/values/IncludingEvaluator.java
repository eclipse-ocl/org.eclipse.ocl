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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.SequenceValue;

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

	private static class SequenceIncludingIterator extends AbstractCollectionIterator implements SequenceValue
	{
		private enum NextIs { PREFIX, SUFFIX, END };

		protected final @NonNull Iterator<@Nullable Object> prefix;
		protected final @Nullable Object suffix;
		private @Nullable NextIs nextIs = null;

		public SequenceIncludingIterator(@NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(TypeId.SEQUENCE.getSpecializedId(firstValue.getElementTypeId()));
			this.prefix = firstValue.iterator();
			this.suffix = secondValue;
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
				return true;
			}
			return false;
		}

		//		@Override
		//		public @Nullable Object last() {
		//			return suffix;		-- need prefix.isNotInvalid check
		//		}

		@Override
		public @Nullable Object next() {
			if (nextIs == null) {
				hasNext();
			}
			if (nextIs == NextIs.PREFIX) {
				return prefix.next();
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
			s.append("SeqInc{");
			s.append(prefix);
			s.append(",");
			s.append(suffix instanceof String ? "'" + suffix + "'" : suffix);
			s.append("}");
		}
	}
}