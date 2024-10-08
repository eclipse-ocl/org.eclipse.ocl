/*******************************************************************************
 * Copyright (c) 2013, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;


/**
 * ParametersId provides a hashable list of operation
 * parameter ids suitable for use when indexing operation overloads.
 */
public interface ParametersId extends IndexableIterable<@NonNull TypeId>	//  ?? ElementId
{
	/**
	 * @since 1.3
	 */
	public static final @NonNull ParametersId EMPTY = IdManager.getParametersId();

	/**
	 * @since 1.3
	 */
	public static final @NonNull ParametersId BOOLEAN = IdManager.getParametersId(TypeId.BOOLEAN);

	/**
	 * @since 1.3
	 */
	public static final @NonNull ParametersId OCL_SELF = IdManager.getParametersId(TypeId.OCL_SELF);
}