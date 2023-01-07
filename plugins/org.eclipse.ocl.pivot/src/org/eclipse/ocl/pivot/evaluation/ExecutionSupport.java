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
 * ExecutionSupport provides additional execution functionality, typically as the result of compiling
 * some OCL source such as a Complete OCL document.
 */
public interface ExecutionSupport
{
	public @NonNull Executor getExecutor();
}
