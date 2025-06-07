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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Type;

/**
 * SpecializationManager encapsulates the knowledge about known class type specializations.
 *
 * @since 7.0
 */
public interface SpecializedTypeManager
{
	void dispose();

	org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull List<@NonNull ? extends Type> templateArguments);
}