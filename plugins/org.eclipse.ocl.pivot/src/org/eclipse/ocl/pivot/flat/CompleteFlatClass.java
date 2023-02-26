/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import java.util.Collections;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.executor.CompleteReflectiveFragment;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class CompleteFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull CompleteClass completeClass;

	public CompleteFlatClass(@NonNull CompleteFlatModel flatModel, @NonNull CompleteClass completeClass) {
		super(flatModel, NameUtil.getName(completeClass), computeFlags(completeClass.getPrimaryClass()));
		this.completeClass = completeClass;
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new CompleteReflectiveFragment(this, baseFlatClass);
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		return completeClass;
	}

	/**
	 * Return the immediate superinheritances without reference to the fragments.
	 */
	@Override
	protected @NonNull Iterable<@NonNull FlatClass> getInitialSuperFlatClasses() {
		if (isOclAny()) {
			return Collections.emptyList();
		}
		return ((CompleteClassInternal)getCompleteClass()).getPartialClasses().getInitialSuperFlatClasses();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeClass.getPrimaryClass();
	}

	@Override
	public @NonNull String toString() {
		return completeClass.toString();
	}
}
