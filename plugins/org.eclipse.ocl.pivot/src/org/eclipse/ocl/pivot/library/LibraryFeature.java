/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.lang.reflect.Method;

import org.eclipse.jdt.annotation.NonNull;

/**
 */
public interface LibraryFeature
{
	/**
	 * @since 1.18
	 */
	default @NonNull Method getEvaluateMethod() { throw new UnsupportedOperationException(); }
}
