/*******************************************************************************
 * Copyright (c) 2010, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.eventmanager.util;

import java.util.Collection;
import java.util.Set;

/**
 * A {@link Bag} implementation that allows for multiple occurrences of elements
 * in the nested collections and allows for them to be counted. In particular,
 * if the nested collections in turn conform to the {@link Bag} interface, their
 * {@link #count(Object)} operation will be used to determine the composite
 * object count.
 * 
 * @author Axel Uhl
 */
public class CompositeBag<E> extends CompositeCollection<E> implements Bag<E> {
    public CompositeBag(Collection<? extends E>... collections) {
        super(collections);
    }
    
	public int count(Object o) {
		int result = 0;
		for (Iterable<? extends E> i : getIterables()) {
			if (i instanceof Set<?>) {
				if (((Set<?>) i).contains(o)) {
					result++;
				}
			} else if (i instanceof Bag<?>) {
				result += ((Bag<?>) i).count(o);
			} else {
				for (E e : i) {
					if (e.equals(o)) {
						result++;
					}
				}
			}
		}
		return result;
	}
}
