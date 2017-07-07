/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.collection;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.library.numeric.NumericMinOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * CollectionMinOperation realises the Collection::min() library operation.
 */
public class CollectionMinOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull CollectionMinOperation INSTANCE = new CollectionMinOperation();

	@Override
	public @NonNull Object evaluate(@Nullable Object sourceVal) {
		CollectionValue collectionValue = asCollectionValue(sourceVal);
		// FIXME Bug 301351 Look for user-defined min
		Object result = null;
		Iterator<@Nullable Object> iterator = collectionValue.lazyIterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (result == null) {
				result = element;
			}
			else if (element != null) {
				result = NumericMinOperation.INSTANCE.evaluate(result, element);
			}
		}
		if (result == null) {
			throw new InvalidValueException(PivotMessages.EmptyCollection, collectionValue.getKind(), "min"); //$NON-NLS-1$
		}
		return result;
	}
}
