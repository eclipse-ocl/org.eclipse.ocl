/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;

public interface ComparableValue<T> extends Value, OCLValue, Comparable<T>
{
	/**
	 * Return -left.compareTo(this).
	 * <p>
	 * This is an internal method used to give righthand argument an opportunity to coerce its tyope to suit the lefthand. It is invoked
	 * when a direct compareTo is unable to compare left with this. Derived implementations must not invoke comoareTo in order to avoid
	 * an infinite commutation.
	 */
	int commutatedCompareTo(@NonNull ComparableValue<?> left);
}
