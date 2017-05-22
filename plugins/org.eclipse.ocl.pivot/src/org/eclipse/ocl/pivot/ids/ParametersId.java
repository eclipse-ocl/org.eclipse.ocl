/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 *
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface ParametersId extends IndexableIterable<@NonNull TypeId>
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