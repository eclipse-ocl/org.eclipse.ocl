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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * CollectionAsBagOperation realises the Collection::asBag() library operation.
 */
public class CollectionAsBagOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull CollectionAsBagOperation INSTANCE = new CollectionAsBagOperation();

	@Override
	public @NonNull CollectionValue evaluate(@Nullable Object argument) {
		return asBagValue(argument);
	}
}
