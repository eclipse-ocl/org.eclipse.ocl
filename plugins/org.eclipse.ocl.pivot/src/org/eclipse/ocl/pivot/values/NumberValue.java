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
import org.eclipse.jdt.annotation.Nullable;


public interface NumberValue extends ComparableValue<NumberValue>
{
	/**
	 * @generated NOT
	 */
	int commutatedCompareToInteger(@NonNull IntegerValue right);

	/**
	 * @generated NOT
	 */
	int commutatedCompareToReal(@NonNull RealValue left);
	
	/**
	 * @generated NOT
	 */
	@Nullable UnlimitedNaturalValue isUnlimitedNaturalValue();
}
