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
package org.eclipse.ocl.pivot.internal.library.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Type;

/**
 * @since 1.23
 */
public class ExecutorLambdaParameter
{
	protected final @NonNull String name;
	protected final @NonNull Type type;
	protected final boolean isRequired;

	public ExecutorLambdaParameter(@NonNull String name, @NonNull Type type, boolean isRequired) {
		this.name = name;
		this.type = type;
		this.isRequired = isRequired;
	}
}
