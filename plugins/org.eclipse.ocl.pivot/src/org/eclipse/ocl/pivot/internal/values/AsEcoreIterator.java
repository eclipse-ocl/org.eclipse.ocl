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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.Value;

/**
 * AsEcoreIterator provides a lazy evaluation of the Collection::asEcore operation.
 *
 * @since 1.3
 */
public class AsEcoreIterator extends AbstractCollectionIterator
{
	private final @NonNull IdResolver idResolver;
	private final @Nullable Class<?> instanceClass;
	private final @NonNull Iterator<@Nullable Object> iterator;
	private boolean canBeIterable = true;

	public AsEcoreIterator(@NonNull CollectionValue firstValue, @NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		super(firstValue.getTypeId());
		this.idResolver = idResolver;
		this.instanceClass = instanceClass;
		this.iterator = firstValue.iterator();
	}

	@Override
	protected boolean canBeIterable() {
		return canBeIterable;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public @Nullable Object next() {
		canBeIterable = false;
		Object element = iterator.next();
		if (element instanceof Value)
			return ((Value)element).asEcoreObject(idResolver, instanceClass);
		else if (element instanceof EnumerationLiteralId) {
			return idResolver.unboxedValueOf(element);
		}
		else {
			return element;
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsEcore{");
		s.append(iterator);
		s.append("}");
	}
}
