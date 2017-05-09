/*******************************************************************************
 * Copyright (c) 2011, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A range of integer values from the first up to and including the last.
 * If last is less than the first. the range is empty.
 */
public interface IntegerRange extends List<@Nullable Object>
{
	@NonNull IntegerValue getFirst();
	@NonNull IntegerValue getLast();
	@NonNull IntegerValue getSize();
	//	@NonNull ValueFactory getValueFactory();
	//	@Override
	//	@NonNull Iterator<@NonNull Object> iterator();
}