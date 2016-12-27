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

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.SequenceValue;

import com.google.common.collect.Iterables;

/**
 * @generated NOT
 * @since 1.3
 */
public class ExcludingAllEvaluator
{
	public static @NonNull CollectionValue excludingAll(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		Iterable<? extends Object> elements = firstValue.iterable();
		if (firstValue.isOrdered()) {
			if (firstValue.isUnique()) {
				OrderedSet<Object> result = new OrderedSetImpl<Object>();
				for (Object element : elements) {
					boolean reject = false;
					if (element == null) {
						for (Object value : secondValue) {
							if (value == null) {
								reject = true;
								break;
							}
						}
					}
					else {
						for (Object value : secondValue) {
							if ((value != null) && value.equals(element)) {
								reject = true;
								break;
							}
						}
					}
					if (!reject) {
						result.add(element);
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
				return new SequenceExcludingAllIterator(firstValue, secondValue);
				/*				List<Object> result = new ArrayList<Object>();
				for (Object element : elements) {
					boolean reject = false;
					if (element == null) {
						for (Object value : secondValue) {
							if (value == null) {
								reject = true;
								break;
							}
						}
					}
					else {
						for (Object value : secondValue) {
							if ((value != null) && value.equals(element)) {
								reject = true;
								break;
							}
						}
					}
					if (!reject) {
						result.add(element);
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
				Set<Object> result = new HashSet<Object>();
				for (Object element : elements) {
					boolean reject = false;
					if (element == null) {
						for (Object value : secondValue) {
							if (value == null) {
								reject = true;
								break;
							}
						}
					}
					else {
						for (Object value : secondValue) {
							if ((value != null) && value.equals(element)) {
								reject = true;
								break;
							}
						}
					}
					if (!reject) {
						result.add(element);
					}
				}
				if (result.size() < Iterables.size(elements)) {
					return new SetValueImpl(firstValue.getTypeId(), result);
				}
				else {
					return firstValue;
				}
			}
			else {
				Bag<Object> result = new BagImpl<Object>();
				for (Object element : elements) {
					boolean reject = false;
					if (element == null) {
						for (Object value : secondValue) {
							if (value == null) {
								reject = true;
								break;
							}
						}
					}
					else {
						for (Object value : secondValue) {
							if ((value != null) && value.equals(element)) {
								reject = true;
								break;
							}
						}
					}
					if (!reject) {
						result.add(element);
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

	private static class SequenceExcludingAllIterator extends AbstractCollectionIterator implements SequenceValue
	{
		protected final @NonNull Iterator<@Nullable Object> iterator;
		protected final @NonNull Iterable<? extends Object> exclusions;
		private @Nullable EqualsStrategy equalsStrategy = null;		// Non-null once iteration starts
		private boolean hasNext = false;
		private @Nullable Object next;

		public SequenceExcludingAllIterator(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
			super(TypeId.SEQUENCE.getSpecializedId(firstValue.getElementTypeId()));
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
			s.append("SeqExcAll{");
			s.append(iterator);
			s.append(",}");
			s.append(exclusions);
			s.append("}");
		}
	}
}