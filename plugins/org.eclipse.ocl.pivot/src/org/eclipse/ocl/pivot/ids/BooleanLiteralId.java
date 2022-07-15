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

/**
 * A BooleanLiteralId supports use of a Boolean literal as an ElementId as is required for the nullFree parameter of
 * Collections and Map.
 *
 * @since 1.18
 */
public interface BooleanLiteralId extends ElementId
{
	@NonNull String getName();
	boolean getValue();
}