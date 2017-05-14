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
package org.eclipse.ocl.pivot.internal.iterators;

import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;

/**
 * FlattenIterator provides a lazy evaluation of the Collection::flatten operation.
 *
 * @since 1.3
 */
public class FlattenIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue flatten(@NonNull CollectionValue sourceValue) {
		CollectionTypeId collectionTypeId = sourceValue.getTypeId();
		TypeId typeId = collectionTypeId;
		while ((typeId instanceof CollectionTypeId) && !(typeId instanceof OclVoidTypeId)) {
			typeId = ((CollectionTypeId)typeId).getElementTypeId();
		}
		return new FlattenIterator(collectionTypeId.getGeneralizedId().getSpecializedId(typeId), sourceValue);
		/*		if (isOrdered()) {
			if (isUnique()) {
				OrderedSet<Object> flattened = new OrderedSetImpl<Object>();
				if (flatten(flattened)) {
					return new SparseOrderedSetValueImpl(getTypeId(), flattened);
				}
				else {
					return this;
				}
			}
			else {
				List<Object> flattened = new ArrayList<Object>();
				if (flatten(flattened)) {
					return new SparseSequenceValueImpl(getTypeId(), flattened);
				}
				else {
					return this;
				}
			}
		}
		else {
			if (isUnique()) {
				Set<Object> flattened = new HashSet<Object>();
				if (flatten(flattened)) {
					return new SetValueImpl(getTypeId(), flattened);
				}
				else {
					return this;
				}
			}
			else {
				Bag<Object> flattened = new BagImpl<Object>();
				if (flatten(flattened)) {
					return new BagValueImpl(getTypeId(), flattened);
				}
				else {
					return this;
				}
			}
		} */
	}

	private @NonNull CollectionValue sourceValue;
	private @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	private @Nullable Stack<@NonNull BaggableIterator<@Nullable Object>> iteratorStack = null;

	public FlattenIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue) {
		super(collectionTypeId);
		this.sourceValue = sourceValue;
		this.sourceIterator = baggableIterator(sourceValue);
	}

	@Override
	public int getNextCount() {
		int nextCount = sourceIterator.hasNextCount();
		if (nextCount > 0) {
			Object next = sourceIterator.next();
			if (next instanceof CollectionValue) {
				Stack<BaggableIterator<@Nullable Object>> iteratorStack2 = iteratorStack;
				if (iteratorStack2 == null) {
					iteratorStack2 = iteratorStack = new Stack<>();
				}
				iteratorStack2.push(sourceIterator);
				sourceIterator = baggableIterator((CollectionValue)next);
				return hasNextCount();
			}
			return setNext(next, nextCount);
		}
		Stack<BaggableIterator<@Nullable Object>> iteratorStack2 = iteratorStack;
		if ((iteratorStack2 == null) || iteratorStack2.isEmpty()) {
			return 0;
		}
		BaggableIterator<@Nullable Object> popped = iteratorStack2.pop();
		assert popped != null;
		sourceIterator = popped;
		return hasNextCount();
	}

	@Override
	protected @NonNull LazyCollectionValue reIterator() {
		return new FlattenIterator(typeId, sourceValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Flatten{");
		s.append(sourceIterator);
		s.append("}");
	}
}
