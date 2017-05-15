/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.values.BaggableIterator;

/**
 * A LazyIterator supports lazy iterations that may use the bag-aware iterator protocol.
 * A LazyIteratpr may not be used for a Collection with an undiscovered invalid value.
 *
 * @since 1.3
 */
public interface LazyIterator extends BaggableIterator<@Nullable Object>
{
}