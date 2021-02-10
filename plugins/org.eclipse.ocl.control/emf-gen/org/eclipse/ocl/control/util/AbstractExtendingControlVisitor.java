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

/**
 * An AbstractExtendingControlVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class' first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractExtendingControlVisitor<R, C>
	extends AbstractControlVisitor<R, C>
	implements ControlVisitor<R>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractExtendingControlVisitor(C context) {
		super(context);
	}

	@Override
	public R visitControlElement(org.eclipse.ocl.control.@NonNull ControlElement object) {
		return visiting(object);
	}

	@Override
	public R visitControlModel(org.eclipse.ocl.control.@NonNull ControlModel object) {
		return visitControlElement(object);
	}

	@Override
	public R visitControlSettings(org.eclipse.ocl.control.@NonNull ControlSettings object) {
		return visitControlElement(object);
	}
}
