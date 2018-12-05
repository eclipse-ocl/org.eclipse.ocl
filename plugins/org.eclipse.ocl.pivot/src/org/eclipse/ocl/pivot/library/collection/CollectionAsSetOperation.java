/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * CollectionAsSetOperation realises the Collection::asSet() library operation.
 */
public class CollectionAsSetOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull CollectionAsSetOperation INSTANCE = new CollectionAsSetOperation();

	@Override
	public @NonNull SetValue evaluate(@Nullable Object argument) {
		return asSetValue(argument);
	}
}
