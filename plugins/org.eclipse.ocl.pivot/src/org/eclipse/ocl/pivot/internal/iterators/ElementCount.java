/*******************************************************************************
 * Copyright (c) 2010, 2016 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *   E.D.Willink - Polish
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.iterators;

/**
 * ElementCount is used as the count of a Bag element. It avoids thrashing Integer objects as counts evolve.
 * @since 1.3
 */
public abstract class ElementCount extends Number
{
	private static final long serialVersionUID = -4914749801229613980L;

	@Override
	public double doubleValue() {
		return intValue();
	}

	@Override
	public boolean equals(Object thatElement) {
		if (thatElement == this) {
			return true;
		}
		if (!(thatElement instanceof Number)) {
			return false;
		}
		return intValue() == ((Number)thatElement).intValue();
	}

	@Override
	public float floatValue() {
		return intValue();
	}

	@Override
	public int hashCode() {
		return intValue();
	}

	@Override
	public abstract int intValue();

	@Override
	public long longValue() {
		return intValue();
	}

	public abstract void setValue(int i);

	@Override
	public String toString() {
		return Integer.toString(intValue());
	}
}