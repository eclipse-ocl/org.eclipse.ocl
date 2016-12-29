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
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * FlattenIterator provides a lazy evaluation of the Collection::flatten operation.
 *
 * @since 1.3
 */
public class FlattenIterator extends AbstractCollectionIterator
{
	public static @NonNull CollectionValue flatten(@NonNull CollectionValue firstValue) {
		CollectionTypeId collectionTypeId = firstValue.getTypeId();
		TypeId typeId = collectionTypeId;
		while ((typeId instanceof CollectionTypeId) && !(typeId instanceof OclVoidTypeId)) {
			typeId = ((CollectionTypeId)typeId).getElementTypeId();
		}
		return new FlattenIterator(collectionTypeId.getGeneralizedId().getSpecializedId(typeId), firstValue);
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

	private @NonNull Iterator<@Nullable Object> iterator;
	private @Nullable Stack<@NonNull Iterator<@Nullable Object>> iteratorStack = null;
	private boolean canBeIterable = true;
	private boolean hasNext = false;
	private Object next;

	public FlattenIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue) {
		super(collectionTypeId);
		this.iterator = firstValue.iterator();
	}

	@Override
	protected boolean canBeIterable() {
		return canBeIterable;
	}

	@Override
	public boolean hasNext() {
		if (iterator.hasNext()) {
			canBeIterable = false;
			next = iterator.next();
			if (next instanceof CollectionValue) {
				Stack<Iterator<@Nullable Object>> iteratorStack2 = iteratorStack;
				if (iteratorStack2 == null) {
					iteratorStack2 = iteratorStack = new Stack<>();
				}
				iteratorStack2.push(iterator);
				iterator = ((CollectionValue)next).iterator();
				return hasNext();
			}
			hasNext = true;
			return true;
		}
		Stack<Iterator<@Nullable Object>> iteratorStack2 = iteratorStack;
		if ((iteratorStack2 == null) || iteratorStack2.isEmpty()) {
			return false;
		}
		Iterator<@Nullable Object> popped = iteratorStack2.pop();
		assert popped != null;
		iterator = popped;
		return hasNext();
	}

	@Override
	public @Nullable Object next() {
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		hasNext = false;
		return next;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Flatten{");
		s.append(iterator);
		s.append("}");
	}
}
