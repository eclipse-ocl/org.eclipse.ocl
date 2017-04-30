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
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public interface BagValue extends CollectionValue
{
	/**
	 * @generated NOT
	 */
	interface Accumulator extends CollectionValue.Accumulator, BagValue {}

	/**
	 * @since 1.3
	 */
	public interface Internal extends BagValue
	{
		@Override
		@NonNull Bag<@Nullable Object> getElements();
	}
}
