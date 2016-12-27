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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;

/**
 * AbstractCollectionValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 */
public abstract class AbstractCollectionIterable extends AbstractCollectionValueImpl implements Iterable<@Nullable Object>
{
	private int hashCode = 0;

	protected AbstractCollectionIterable(@NonNull CollectionTypeId typeId) {
		super(typeId);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public final int hashCode() {		// Need hash to be independent of the Set/List/OrderedSet/Bag actually in use as elements
		if (hashCode == 0) {
			synchronized (this) {
				if (hashCode == 0) {
					hashCode = computeCollectionHashCode(isOrdered(), isUnique(), iterable());
				}
			}
		}
		return hashCode;
	}
}
