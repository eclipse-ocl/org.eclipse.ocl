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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.NameUtil;

@Deprecated /* @deprecated temporary usage to support EClassifier-less names such as LibraryFeature */
public class NamedFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final org.eclipse.ocl.pivot.@NonNull Class pivotClass;

	public NamedFlatClass(@NonNull EcoreFlatModel flatModel, org.eclipse.ocl.pivot.@NonNull Class pivotClass) { //, int flags) {
		super(flatModel, NameUtil.getName(pivotClass), 0);
		this.pivotClass = pivotClass;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		throw new UnsupportedOperationException();
//		return new EcoreReflectiveFragment(this, baseFlatClass);
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull EcoreFlatModel getFlatModel() {
		return (EcoreFlatModel)flatModel;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return pivotClass;
	}

	@Override
	public @NonNull String toString() {
		return pivotClass.toString();
	}
}
