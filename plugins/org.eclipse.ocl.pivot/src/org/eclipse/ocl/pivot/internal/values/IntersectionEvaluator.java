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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @generated NOT
 * @since 1.3
 */
public class IntersectionEvaluator extends AbstractCollectionValueImpl
{
	public static @NonNull CollectionValue intersection(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		if (!(firstValue instanceof UniqueCollectionValue) && !(secondValue instanceof UniqueCollectionValue)) {
			BagValue firstBagValue = firstValue instanceof BagValue ? (BagValue)firstValue : firstValue.asBagValue();
			BagValue secondBagValue = firstValue instanceof BagValue ? (BagValue)secondValue : secondValue.asBagValue();
			return new BagIntersectionIterable(firstValue.getBagTypeId(), firstBagValue, secondBagValue);
		}
		else {
			return new SetIntersectionEvaluator(firstValue.getSetTypeId(), firstValue, secondValue);
		}
	}

	private static class BagIntersectionIterable extends AbstractCollectionIterable implements BagValue
	{
		protected final @NonNull Bag<? extends Object> firstBag;
		protected final @NonNull Bag<? extends Object> secondBag;
		protected final @NonNull Iterator<? extends Object> firstKey;
		protected final @NonNull Iterator<? extends Object> secondKey;
		private Object next = null;
		private boolean hasNext = false;

		public BagIntersectionIterable(@NonNull CollectionTypeId collectionTypeId, @NonNull BagValue firstValue, @NonNull BagValue secondValue) {
			super(collectionTypeId);
			Set<@Nullable Object> firstEntries = firstBag.
					this.firstBag = firstValue.getElements();
			this.secondBag = secondValue.getElements();
			while (firstKey.hasNext()) {
				Object object = firstKey.next();
				int count2 = secondBag.count(object);
				if (count2 > 0) {
					int count1 = firstBag.count(object);
					hasNext = true;
					next = object;
					return;
				}
			}
		}

		private void advance() {
			assert hasNext == false;
			while (firstKey.hasNext()) {
				Object object = firstKey.next();
				int count2 = secondBag.count(object);
				if (count2 > 0) {
					int count1 = firstBag.count(object);
					hasNext = true;
					next = object;
					return;
				}
			}
			/*		assert !this.isUndefined() && !that.isUndefined();
			Collection<? extends Object> theseElements = this.asCollection();
			Collection<? extends Object> thoseElements = that.asCollection();
			int thisSize = theseElements.size();
			int thatSize = thoseElements.size();
			if (this instanceof UniqueCollectionValue || that instanceof UniqueCollectionValue) {
				@NonNull CollectionTypeId typeId = getSetTypeId();
				if ((thisSize == 0) || (thatSize == 0)) {
					return new SetValueImpl(typeId, ValueUtil.EMPTY_SET);
				}
				Set<Object> results;
				// loop over the smaller collection and add only elements
				// that are in the larger collection
				if (thisSize <= thatSize) {
					results = new HashSet<Object>(theseElements);
					results.retainAll(thoseElements);
				}
				else {
					results = new HashSet<Object>(thoseElements);
					results.retainAll(theseElements);
				}
				return new SetValueImpl(typeId, results.size() > 0 ? results : ValueUtil.EMPTY_SET);
			}
			else {
				@NonNull CollectionTypeId typeId = getBagTypeId();
				if ((thisSize == 0) || (thatSize == 0)) {
					return new BagValueImpl(typeId, ValueUtil.EMPTY_BAG);
				}
				Bag<Object> results = new BagImpl<Object>();
				// loop over the smaller collection and add only elements
				// that are in the larger collection
				Set<Object> minElements = new HashSet<Object>(thisSize < thatSize ? theseElements : thoseElements);
				for (Object e : minElements) {
					IntegerValue leftCount = this.count(e);
					IntegerValue rightCount = that.count(e);
					for (int i = Math.min(leftCount.asInteger(), rightCount.asInteger()); i > 0; i--) {
						results.add(e);
					}
				}
				return new BagValueImpl(typeId, results.size() > 0 ? results : ValueUtil.EMPTY_BAG);
			} */
		}

		private static class SetIntersectionIterator extends AbstractCollectionIterable implements SetValue
		{
			protected final @NonNull Iterable<? extends Object> firstValue;
			protected final @NonNull Iterator<@Nullable Object> secondValue;
			private @NonNull Set<@Nullable Object> elements = new HashSet<>();
			private Object next = null;
			private boolean hasNext = false;

			public SetIntersectionIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
				super(collectionTypeId);
				this.firstValue = firstValue.iterable();
				this.secondValue = secondValue.iterator();
				advance();
			}

			private void advance() {
				assert hasNext == false;
				while (secondValue.hasNext()) {
					Object object = secondValue.next();
					if (Iterables.contains(firstValue, object)) {
						hasNext = true;
						next = object;
						return;
					}
				}
				/*		assert !this.isUndefined() && !that.isUndefined();
			Collection<? extends Object> theseElements = this.asCollection();
			Collection<? extends Object> thoseElements = that.asCollection();
			int thisSize = theseElements.size();
			int thatSize = thoseElements.size();
			if (this instanceof UniqueCollectionValue || that instanceof UniqueCollectionValue) {
				@NonNull CollectionTypeId typeId = getSetTypeId();
				if ((thisSize == 0) || (thatSize == 0)) {
					return new SetValueImpl(typeId, ValueUtil.EMPTY_SET);
				}
				Set<Object> results;
				// loop over the smaller collection and add only elements
				// that are in the larger collection
				if (thisSize <= thatSize) {
					results = new HashSet<Object>(theseElements);
					results.retainAll(thoseElements);
				}
				else {
					results = new HashSet<Object>(thoseElements);
					results.retainAll(theseElements);
				}
				return new SetValueImpl(typeId, results.size() > 0 ? results : ValueUtil.EMPTY_SET);
			}
			else {
				@NonNull CollectionTypeId typeId = getBagTypeId();
				if ((thisSize == 0) || (thatSize == 0)) {
					return new BagValueImpl(typeId, ValueUtil.EMPTY_BAG);
				}
				Bag<Object> results = new BagImpl<Object>();
				// loop over the smaller collection and add only elements
				// that are in the larger collection
				Set<Object> minElements = new HashSet<Object>(thisSize < thatSize ? theseElements : thoseElements);
				for (Object e : minElements) {
					IntegerValue leftCount = this.count(e);
					IntegerValue rightCount = that.count(e);
					for (int i = Math.min(leftCount.asInteger(), rightCount.asInteger()); i > 0; i--) {
						results.add(e);
					}
				}
				return new BagValueImpl(typeId, results.size() > 0 ? results : ValueUtil.EMPTY_BAG);
			} */
			}

			@Override
			public boolean hasNext() {
				return hasNext;
			}

			@Override
			public Object next() {
				if (!hasNext) {
					throw new NoSuchElementException();
				}
				try {
					return next;
				}
				finally {
					hasNext = false;
					advance();
				}
			}
		}

		protected final @NonNull CollectionValue firstValue;
		protected final @NonNull CollectionValue secondValue;
		private @Nullable List<@Nullable Object> elements = null;		// Using Value instances where necessary to ensure correct equals semantics

		public IntersectionEvaluator(@NonNull CollectionTypeId typeId, @NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
			super(typeId);
			toString();
			this.firstValue = firstValue;
			this.secondValue = secondValue;
		}

		@Override
		public @NonNull Collection<? extends Object> asCollection() {
			return iterable();
		}

		@Override
		public @NonNull Object asObject() {
			return iterable();
		}

		@Override
		public int intSize() {
			return iterable().size();
		}

		@Override
		public @NonNull Collection<@Nullable Object> iterable() {
			List<@Nullable Object> elements2 = elements;
			if (elements2 == null) {
				elements2 = elements = Lists.newArrayList(iterator());
			}
			return elements2;
		}

		@Override
		public @NonNull Iterator<@Nullable Object> iterator() {
			List<@Nullable Object> elements2 = elements;
			if (elements2 != null) {
				return elements2.iterator();
			}
			else {
				return new SetIntersectionIterator(firstValue, secondValue);
			}
		}

		@Override
		public void toString(@NonNull StringBuilder s, int lengthLimit) {
			s.append(collectionFactory.getKind());
			s.append("{");
			boolean isFirst = true;
			if (elements != null) {
				for (Object element : elements) {
					if (!isFirst) {
						s.append(",");
					}
					if (s.length() < lengthLimit) {
						ValueUtil.toString(element, s, lengthLimit-1);
					}
					else {
						s.append("...");
						break;
					}
					isFirst = false;
				}
			}
			else {
				s.append("???");
			}
			s.append("}");
		}

		@Override
		public @NonNull UniqueCollectionValue minus(
				@NonNull UniqueCollectionValue set) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull UniqueCollectionValue symmetricDifference(
				@NonNull UniqueCollectionValue set) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	}