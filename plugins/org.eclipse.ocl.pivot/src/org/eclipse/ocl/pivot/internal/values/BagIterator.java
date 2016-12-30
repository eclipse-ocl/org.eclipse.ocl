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

import org.eclipse.jdt.annotation.Nullable;

/**
 * A BagIterator supports efficient iteration of a Bag, using an element repeat count returned by
 * hasNextCount in place of the conventional element existence returned by hasNext().
 *
 * @since 1.3
 */
public interface BagIterator<E> extends Iterator<E>
{
	/**
	 * Return the number of repeats of the next element, or zero if the iteration has completed.
	 */
	int hasNextCount();

	public static class Null<@Nullable E> implements BagIterator<E>
	{
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public int hasNextCount() {
			return 0;
		}

		@Override
		public E next() {
			return null;
		}

		@Override
		public void remove() {}
	}
}