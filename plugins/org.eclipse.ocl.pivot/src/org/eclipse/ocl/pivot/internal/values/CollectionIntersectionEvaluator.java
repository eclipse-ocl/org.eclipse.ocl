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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;

/**
 * @generated NOT
 * @since 1.3
 */
public class CollectionIntersectionEvaluator extends AbstractCollectionValueImpl
{
	protected final @NonNull Collection<? extends Object> elements;		// Using Value instances where necessary to ensure correct equals semantics
	private int hashCode = 0;

	public CollectionIntersectionEvaluator(@NonNull CollectionTypeId typeId, @NonNull Collection<? extends Object> values) {
		super(typeId);
		this.elements = values;
		assert checkElementsAreValues(values);
	}

	@Override
	public @NonNull Collection<? extends Object> asCollection() {
		return elements;
	}

	@Override
	public @NonNull Object asObject() {
		return elements;
	}

	@Override
	public int intSize() {
		return elements.size();
	}

	@Override
	public @NonNull Iterable<? extends Object> iterable() {
		return elements;
	}
}
