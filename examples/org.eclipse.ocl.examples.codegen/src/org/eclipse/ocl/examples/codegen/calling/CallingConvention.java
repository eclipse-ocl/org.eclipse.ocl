/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

/**
 *  CallingConvention defines a particular style of Operation or Property call with support for
 *  generation of a declaration or invocation.
 */
public interface CallingConvention
{
	/**
	 * Return true if this feature needs generation as part of user code (rather than being referenced from built-in functionality).
	 */
	default boolean needsGeneration() {
		return true;
	}

	/**
	 * Return true if this feature needs generation with a distinct nested class or false if the generation of this
	 * feature produces a nested class directly..
	 */
	default boolean needsNestedClass() {
		return true;
	}
}
