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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;

import com.google.common.collect.Iterables;

/**
 * @generated NOT
 * @since 1.3
 */
public class ExcludingEvaluator
{
	public static @NonNull CollectionValue excluding(@NonNull CollectionValue firstValue, @Nullable Object secondValue) {
		Iterable<? extends Object> elements = firstValue.iterable();
		if (firstValue.isOrdered()) {
			if (firstValue.isUnique()) {
				OrderedSet<Object> result = new OrderedSetImpl<Object>();
				if (secondValue == null) {
					for (Object element : elements) {
						if (element != null) {
							result.add(element);
						}
					}
				}
				else {
					for (Object element : elements) {
						if (!secondValue.equals(element)) {
							result.add(element);
						}
					}
				}
				if (result.size() < Iterables.size(elements)) {
					return new SparseOrderedSetValueImpl(firstValue.getTypeId(), result);
				}
				else {
					return firstValue;
				}
			}
			else {
				return new SequenceExcludingIterator(firstValue, secondValue);
				/*				List<Object> result = new ArrayList<Object>();
				if (secondValue == null) {
					for (Object element : elements) {
						if (element != null) {
							result.add(element);
						}
					}
				}
				else {
					for (Object element : elements) {
						if (!secondValue.equals(element)) {
							result.add(element);
						}
					}
				}
				if (result.size() < Iterables.size(elements)) {
					return new SparseSequenceValueImpl(firstValue.getTypeId(), result);
				}
				else {
					return firstValue;
				} */
			}
		}
		else {
			if (firstValue.isUnique()) {
				return new SetExcludingIterator(firstValue, secondValue);
				/*				Set<Object> result = new HashSet<Object>();
				if (secondValue == null) {
					for (Object element : elements) {
						if (element != null) {
							result.add(element);
						}
					}
				}
				else {
					for (Object element : elements) {
						if (!secondValue.equals(element)) {
							result.add(element);
						}
					}
				}
				if (result.size() < Iterables.size(elements)) {
					return new SetValueImpl(firstValue.getTypeId(), result);
				}
				else {
					return firstValue;
				} */
			}
			else {
				Bag<Object> result = new BagImpl<Object>();
				if (secondValue == null) {
					for (Object element : elements) {
						if (element != null) {
							result.add(element);
						}
					}
				}
				else {
					for (Object element : elements) {
						if (!secondValue.equals(element)) {
							result.add(element);
						}
					}
				}
				if (result.size() < Iterables.size(elements)) {
					return new BagValueImpl(firstValue.getTypeId(), result);
				}
				else {
					return firstValue;
				}
			}
		}
	}

	private static abstract class AbstractExcludingIterator extends AbstractCollectionIterator implements SequenceValue
	{
		protected final @NonNull Iterator<@Nullable Object> iterator;
		protected final @Nullable Object exclusion;
		private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
		private boolean hasNext = false;
		private @Nullable Object next;

		public AbstractExcludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(collectionTypeId);
			this.iterator = firstValue.iterator();
			this.exclusion = secondValue;
		}

		@Override
		protected boolean canBeIterable() {
			return equalsStrategy == null;
		}

		@Override
		public boolean hasNext() {
			EqualsStrategy equalsStrategy2 = equalsStrategy;
			if (equalsStrategy2 == null) {
				equalsStrategy2 = equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), exclusion == null);
			}
			if (hasNext) {
				return true;
			}
			while (iterator.hasNext()) {
				next = iterator.next();
				if (!equalsStrategy2.isEqual(next, exclusion)) {
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
			s.append("Excluding{");
			s.append(iterator);
			s.append(",}");
			s.append(exclusion instanceof String ? "'" + exclusion + "'" : exclusion);
			s.append("}");
		}
	}


	private static class SequenceExcludingIterator extends AbstractExcludingIterator implements SequenceValue
	{
		public SequenceExcludingIterator(@NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(TypeId.SEQUENCE.getSpecializedId(firstValue.getElementTypeId()), firstValue, secondValue);
		}
	}

	private static class SetExcludingIterator extends AbstractExcludingIterator implements SetValue
	{
		public SetExcludingIterator(@NonNull CollectionValue firstValue, @Nullable Object secondValue) {
			super(TypeId.SET.getSpecializedId(firstValue.getElementTypeId()), firstValue, secondValue);
		}
	}
}