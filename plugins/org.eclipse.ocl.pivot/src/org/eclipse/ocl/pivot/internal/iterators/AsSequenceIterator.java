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

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.SequenceValue;

/**
 * AsSequenceIterator provides a lazy evaluation of the Collection::asSequence operation.
 *
 * @since 1.3
 */
public class AsSequenceIterator extends AbstractBaggableIterator implements SequenceValue
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	public AsSequenceIterator(@NonNull CollectionValue sourceValue) {
		this(TypeId.SEQUENCE.getSpecializedId(sourceValue.getElementTypeId()), sourceValue.iterator());
	}

	public AsSequenceIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> sourceIterator) {
		super(typeId);
		this.sourceIterator = sourceIterator;
		assert isOrdered();
		assert !isUnique();
	}

	@Override
	protected int getNextCount() {
		if (sourceIterator.hasNext()) {
			return setNext(sourceIterator.next(), 1);
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsSequence{");
		LazyIterable<@Nullable Object> iterable = basicGetIterable();
		if (iterable != null) {
			s.append(iterable);
			if (hasNext()) {
				s.append(";«future»");
			}
		}
		else {
			s.append("«future»");
		}
		s.append("}");
	}
}
