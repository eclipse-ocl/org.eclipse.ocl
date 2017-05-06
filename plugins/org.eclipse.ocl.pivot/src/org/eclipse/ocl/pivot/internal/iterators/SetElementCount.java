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

import org.eclipse.jdt.annotation.NonNull;

/**
 * ElementCount is used as the count of a Bag element. It avoids thrashing Integer objects as counts evolve.
 * @since 1.3
 */
public class SetElementCount extends ElementCount
{
	private static final long serialVersionUID = 6802208578709751333L;

	public static final @NonNull SetElementCount ONE = new SetElementCount();

	private SetElementCount() {}

	@Override
	public int intValue() {
		return 1;
	}

	@Override
	public void setValue(int value) {
		if (value != 1) {
			throw new IllegalStateException("SetElementCount can not be chnaged from unity");
		};
	}
}