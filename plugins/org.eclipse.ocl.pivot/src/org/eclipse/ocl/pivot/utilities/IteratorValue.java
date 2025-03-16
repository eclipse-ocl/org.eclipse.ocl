/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.ocl.pivot.values.IterableValue;

/**
 * A IteratorValue supports ...
 *
 * @since 1.23
 */
public interface IteratorValue extends DelegatedValue
{
	IterableValue getIterableValue();
}