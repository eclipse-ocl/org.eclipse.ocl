/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.manager;

import org.eclipse.jdt.annotation.NonNull;

/**
 * JavaTypeManager encapsulates the knowledge about known java type creation and access.
 *
 * @since 7.0
 */
public interface JavaTypeManager
{
	void dispose();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getJavaType(@NonNull Object object);
}