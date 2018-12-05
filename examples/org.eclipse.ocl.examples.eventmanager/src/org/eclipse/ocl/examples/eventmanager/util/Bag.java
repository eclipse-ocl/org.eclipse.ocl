/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.eventmanager.util;

import java.util.Collection;

/**
 * A bag is a read-only collection that is non-unique and unordered. It is,
 * therefore, like a set except that any element may occur multiple times. All
 * modifying operations will throw an {@link UnsupportedOperationException}.<p>
 * 
 * Note, that no specific {@link Object#equals(Object)} or {@link Object#hashCode()}
 * requirements are added by this interface. This means that implementations may
 * simply use the {@link Object}-provided, identity-based comparison semantics.
 * 
 * @author Christian W. Damus (cdamus), Axel Uhl
 */
public interface Bag<E> extends Collection<E> {
    /**
     * Queries how many times the specified object occurs in me.
     * If I do not contain the object, then the count is zero.
     * 
     * @param o an object
     * @return the number of times that it occurs in me
     */
    int count(Object o);
}
