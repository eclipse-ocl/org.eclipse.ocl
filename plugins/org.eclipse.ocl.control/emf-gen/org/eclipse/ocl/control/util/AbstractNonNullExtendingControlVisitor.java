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
 * An AbstractExtendingNonNullControlVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 * The return is annotated as @NonNull.
 *
 * @deprecated Explicit 'NonNull' functionality is obsolete with Java 8 @NonNull annotations.
 */
 @Deprecated
public abstract class AbstractNonNullExtendingControlVisitor<R, C>
	extends AbstractControlVisitor<R, C>
	implements ControlVisitor<R>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractNonNullExtendingControlVisitor(C context) {
		super(context);
	}

	/**
	 * Perform a visit to the specified visitable.
	 *
	 * @param visitable a visitable
	 * @return the non-null result of visiting it
	 */
	@Override
	public @NonNull R visit(org.eclipse.ocl.control.@NonNull ControlElement visitable) {
		R result = visitable.accept(this);
		if (result == null) {
			throw new IllegalStateException("null return from non-null " + getClass().getName());
		}
		return result;
	}

	@Override
	public @NonNull R visitControlElement(org.eclipse.ocl.control.@NonNull ControlElement object) {
		return visiting(object);
	}

	@Override
	public @NonNull R visitControlModel(org.eclipse.ocl.control.@NonNull ControlModel object) {
		return visitControlElement(object);
	}

	@Override
	public @NonNull R visitControlSettings(org.eclipse.ocl.control.@NonNull ControlSettings object) {
		return visitControlElement(object);
	}

	/**
	 * Return the result of visiting a visitable for which no more specific pivot type method
	 * is available.
	 */
	@Override
	public abstract @NonNull R visiting(org.eclipse.ocl.control.@NonNull ControlElement visitable);
}
