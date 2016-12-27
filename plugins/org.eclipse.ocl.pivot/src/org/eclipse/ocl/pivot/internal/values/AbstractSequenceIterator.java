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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.SequenceValue;

/**
 * AbstractCollectionValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 */
public abstract class AbstractSequenceIterator extends AbstractCollectionIterator implements SequenceValue
{
	//	private @Nullable SequenceValue iterable = null;

	protected AbstractSequenceIterator(@NonNull TypeId elementTypeId) {
		super(TypeId.SEQUENCE.getSpecializedId(elementTypeId));
	}

	@Override
	public @NonNull OrderedCollectionValue append(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue appendAll(@NonNull OrderedCollectionValue objects) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object at(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object first() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue insertAt(int index, @Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Iterable<? extends Object> iterable() {
		// TODO Auto-generated method stub
		return super.iterable();
	}

	@Override
	public @Nullable Object last() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue prepend(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue prependAll(@NonNull OrderedCollectionValue objects) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue reverse() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull SequenceValue subSequence(int lower, int upper) {
		throw new UnsupportedOperationException();
	}
}
