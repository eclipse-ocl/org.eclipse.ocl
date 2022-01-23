/*******************************************************************************
 * Copyright (c) 2016, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Class;

/**
 * A NullModelManager is used when a null model context is provided.
 * There are no models but there may be statoc properties,.
 *
 * @since 1.18
 */
public class NullModelManager extends AbstractModelManager
{
	public NullModelManager() {}

	@Override
	public @NonNull Set<@NonNull ? extends Object> get(@NonNull Class type) {
		return Collections.<@NonNull Object>emptySet();
	}
}
