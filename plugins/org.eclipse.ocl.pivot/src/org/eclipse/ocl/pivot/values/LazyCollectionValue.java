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
package org.eclipse.ocl.pivot.values;

/**
 * LazyCollectionValue extends the inherently eager CollectionValue to support lazy and lazily cached iterations.
 *
 * A lazy iteration avoids any cache overheads by computing each output value from an input value on the fly.
 * A collection that is invalid becuase of an invalid content may not be detected until the offending input is
 * traverse.
 *
 * A lazily cached iteration similarly computes output from input on the fly, but caches the results so that a
 * subsequent iteration can re-use the computations.
 * A collection that is invalid becuase of an invalid content may not be detected until the offending input is
 * traverse.
 *
 * An eagerly cached iteration computes every cache entry so that ant invalid content is detected before any output
 * iteration occurs.
 *
 * @since 1.3
 */
public interface LazyCollectionValue extends CollectionValue
{
}
