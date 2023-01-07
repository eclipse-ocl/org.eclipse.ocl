/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Common support functionality to augment the OCLinEcore Tables classes.
 */
public abstract class AbstractExecutionSupport implements ExecutionSupport
{
	protected final @NonNull Executor executor;// = PivotUtil.getExecutor(null);
	protected final @NonNull AbstractExecutionSupport rootObject = this;

	protected AbstractExecutionSupport(@NonNull Executor executor) {
		this.executor = executor;
	}

	@Override
	public @NonNull Executor getExecutor() {
		return executor;
	}
}