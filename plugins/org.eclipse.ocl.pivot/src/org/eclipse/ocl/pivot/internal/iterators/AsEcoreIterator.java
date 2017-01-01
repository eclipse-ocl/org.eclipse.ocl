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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.Value;

/**
 * AsEcoreIterator provides a lazy evaluation of the Collection::asEcore operation.
 *
 * @since 1.3
 */
public class AsEcoreIterator extends AbstractBaggableIterator
{
	private final @NonNull IdResolver idResolver;
	private final @Nullable Class<?> instanceClass;
	private final @NonNull BaggableIterator<@Nullable Object> sourceIterator;

	public AsEcoreIterator(@NonNull CollectionValue sourceValue, @NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		super(sourceValue.getTypeId());
		this.idResolver = idResolver;
		this.instanceClass = instanceClass;
		this.sourceIterator = sourceValue.iterator();
	}

	@Override
	public int getNextCount() {
		int hasNextCount = sourceIterator.hasNextCount();
		if (hasNextCount > 0) {
			Object element = sourceIterator.next();
			if (element instanceof Value)
				return setNext(((Value)element).asEcoreObject(idResolver, instanceClass), hasNextCount);
			else if (element instanceof EnumerationLiteralId) {
				return setNext(idResolver.unboxedValueOf(element), hasNextCount);
			}
			else {
				return setNext(element, hasNextCount);
			}
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsEcore{");
		s.append(sourceIterator);
		s.append("}");
	}
}
