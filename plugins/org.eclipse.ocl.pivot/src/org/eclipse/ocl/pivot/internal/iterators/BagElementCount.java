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
public class BagElementCount extends ElementCount
{
	private static final long serialVersionUID = 4991759431989075544L;

	private int value;

	public BagElementCount(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object thatElement) {
		if (thatElement == this) {
			return true;
		}
		if (!(thatElement instanceof Number)) {
			return false;
		}
		return value == ((Number)thatElement).intValue();
	}

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}
}