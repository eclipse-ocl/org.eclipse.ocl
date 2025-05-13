/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
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

/**
 * BindingsId provides a hashable list of Class-typed elementIds and/or DataType-typed values that parameterize a template specialization.
 */
public interface BindingsId
{
	/**
	 * @since 1.18
	 */
	int elementIdSize();
	/**
	 * @since 1.18
	 */
	@NonNull ElementId getElementId(int i);
	/**
	 * @since 1.18
	 */
	@NonNull Object getValue(int i);
	/**
	 * @since 1.18
	 */
	int valuesSize();
}
