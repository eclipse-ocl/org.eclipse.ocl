/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * This code is auto-generated
 * from: org.eclipse.ocl.control/model/Control.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.control.util;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * An AbstractNullControlVisitor provides a default implementation for each
 * visitXxx method that returns null.
 *
 * @deprecated Explicit 'Null' functionality is obsolete with Java 8 @Nullable annotations.
 */
 @Deprecated
public abstract class AbstractNullControlVisitor<@Nullable R, C>
	extends AbstractControlVisitor<R, C>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractNullControlVisitor(C context) {
		super(context);
	}

	@Override
	public R visitControlElement(org.eclipse.ocl.control.@NonNull ControlElement object) {
		return null;
	}

	@Override
	public R visitControlModel(org.eclipse.ocl.control.@NonNull ControlModel object) {
		return null;
	}

	@Override
	public R visitControlSettings(org.eclipse.ocl.control.@NonNull ControlSettings object) {
		return null;
	}
}
