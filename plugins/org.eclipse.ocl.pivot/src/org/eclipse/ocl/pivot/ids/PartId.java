/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A PartId provides a unique identifier for a Tuple Part or Lambda Parameter such as first:String[1].
 *
 * @since 7.0
 */
public interface PartId extends ElementId, Nameable, Comparable<@NonNull PartId>
{
	int getIndex();
	@Override
	@NonNull String getName();
	@NonNull TypeId getTypeId();
	boolean isRequired();
}