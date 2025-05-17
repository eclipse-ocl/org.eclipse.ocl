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
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.values.MapTypeArguments;

/**
 * MapTypeManager encapsulates the knowledge about known map type creation and access.
 *
 * @since 7.0
 */
public interface MapTypeManager
{
	/**
	 * Return the map type characterized by the typeArguments if it exists else null.
	 */
	@Nullable MapType basicGetMapType(@NonNull MapTypeArguments typeArguments);

	/**
	 * Return, and if necessary create, the map entry type characterized by the typeArguments and entryClass.
	 */
	@NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass);

	/**
	 * Return, and if necessary create, the map type characterized by the typeArguments.
	 */
	@NonNull MapType getMapType(@NonNull MapTypeArguments typeArguments);
}