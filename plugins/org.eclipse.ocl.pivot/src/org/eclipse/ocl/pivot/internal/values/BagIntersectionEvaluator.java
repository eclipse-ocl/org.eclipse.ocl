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
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;

import com.google.common.collect.Iterables;

/**
 * @generated NOT
 * @since 1.3
 */
public class BagIntersectionEvaluator extends AbstractCollectionValueImpl implements BagValue
{

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
private @Nullable Bag<@Nullable Object> elements = null;		// Using Value instances where necessary to ensure correct equals semantics

public BagIntersectionEvaluator(@NonNull CollectionTypeId typeId, @NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
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
	Collection<@Nullable Object> elements2 = elements;
	if (elements2 == null) {
		elements2 = elements = new BagImpl(iterator());
	}
	return elements2;
}

@Override
public @NonNull Iterator<@Nullable Object> iterator() {
	Collection<@Nullable Object> elements2 = elements;
	if (elements2 != null) {
		return elements2.iterator();
	}
	else {
		return new IntersectionIterator(firstValue, secondValue);
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
}