/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * An OverloadId provides a unique identifier for an operation overload identified by a name ansParamtersId but ignoring
 * the scope hierarchy..
 */
public interface OverloadId extends ElementId, Nameable, Comparable<OverloadId>
{
	@Override
	@NonNull String getName();
	@NonNull ParametersId getParametersId();
//	boolean matches(@NonNull String thatName, @NonNull ParametersId thatParametersId);
}