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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

/**
 * @generated NOT
 * @since 1.3
 */
public class IntersectionEvaluator //extends AbstractCollectionValueImpl
{
	public static @NonNull CollectionValue intersection(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		TypeId elementTypeId = firstValue.getElementTypeId();
		if (!(firstValue instanceof UniqueCollectionValue)) {
			BagValue firstBagValue = firstValue.asBagValue();
			if (!(secondValue instanceof UniqueCollectionValue)) {
				Bag.Internal<?> firstBag = (Bag.Internal<?>)firstBagValue.getElements();
				Bag.Internal<?> secondBag = (Bag.Internal<?>)secondValue.asBagValue().getElements();
				CollectionTypeId bagTypeId = TypeId.BAG.getSpecializedId(elementTypeId);
				if (firstBag.getMap().size() < secondBag.getMap().size()) {
					return new BagIntersectionIterable(bagTypeId, firstBag, secondBag);
				}
				else {
					return new BagIntersectionIterable(bagTypeId, secondBag, firstBag);
				}
			}
			else {
				return new SetIntersectionIterable(TypeId.SET.getSpecializedId(elementTypeId), (UniqueCollectionValue)secondValue, firstBagValue);
			}
		}
		else {
			CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(elementTypeId);
			if (!(secondValue instanceof UniqueCollectionValue)) {
				BagValue secondBagValue = secondValue.asBagValue();
				return new SetIntersectionIterable(setTypeId, (UniqueCollectionValue)firstValue, secondBagValue);
			}
			else if (firstValue.intSize() > secondValue.intSize()) {
				return new SetIntersectionIterable(setTypeId, (UniqueCollectionValue)firstValue, secondValue);
			}
			else {
				return new SetIntersectionIterable(setTypeId, (UniqueCollectionValue)secondValue, firstValue);
			}
		}
	}

	private static class BagIntersectionIterable extends BagValueImpl
	{
		private static @NonNull Bag<Object> createIntersectionBag(Bag.@NonNull Internal<?> smallerBag, Bag.@NonNull Internal<?> largerBag) {
			BagImpl<Object> resultBag = new BagImpl<>();
			for (Object element : smallerBag.getMap().keySet()) {
				int count = largerBag.count(element);
				if (count > 0) {
					count = Math.min(count, smallerBag.count(element));
					resultBag.put(element, count);
				}
			}
			return resultBag;
		}

		public BagIntersectionIterable(@NonNull CollectionTypeId collectionTypeId, Bag.@NonNull Internal<?> smallerBag, Bag.@NonNull Internal<?> largerBag) {
			super(collectionTypeId, createIntersectionBag(smallerBag, largerBag));
		}
	}

	private static class SetIntersectionIterable extends SetValueImpl
	{
		private static @NonNull Set<Object> createIntersectionSet(@NonNull UniqueCollectionValue largerUniqueValue, @NonNull CollectionValue otherValue) {
			Set<Object> resultSet = new HashSet<>();
			for (Object element : otherValue) {
				if (largerUniqueValue.includes(element)) {
					resultSet.add(element);
				}
			}
			return resultSet;
		}

		//		protected final @NonNull CollectionValue firstValue;
		//		protected final @NonNull Iterator<@Nullable Object> secondIterator;
		//		private @NonNull Set<@Nullable Object> result = new HashSet<>();
		//		private Object next = null;
		//		private boolean hasNext = false;

		public SetIntersectionIterable(@NonNull CollectionTypeId collectionTypeId, @NonNull UniqueCollectionValue firstValue, @NonNull CollectionValue secondValue) {
			super(collectionTypeId, createIntersectionSet(firstValue, secondValue));
			//			this.firstValue = firstValue;
			//			this.secondIterator = secondValue.iterator();
			//			advance();
		}

		/*		private void advance() {
			assert hasNext == false;
			while (secondIterator.hasNext()) {
				Object object = secondIterator.next();
				if (firstValue.includes(object)) {
					hasNext = true;
					next = object;
					result.add(object);
					return;
				}
			}
		} */

		/*		@Override
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
		} */
	}

	/*	protected final @NonNull CollectionValue firstValue;
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
			return new SetIntersectionIterator(typeId, firstValue, secondValue);
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
	} */
}