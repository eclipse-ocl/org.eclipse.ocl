/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * An IntegerLiteralId supports use of an Integer literal as an ElementId as is required for the lower parameter of
 * Collections.
 *
 * @since 1.18
 */
public interface IntegerLiteralId extends ElementId
{
	@NonNull String getName();
	@NonNull IntegerValue getValue();
}