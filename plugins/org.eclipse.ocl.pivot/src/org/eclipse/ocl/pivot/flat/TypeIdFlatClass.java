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
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.types.AbstractFragment;

public class TypeIdFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull TypeId typeId;		// XXX unify

	public TypeIdFlatClass(@NonNull FlatModel flatModel, @NonNull TypeId typeId) { //, int flags) {
		super(flatModel, typeId.getDisplayName(), 0);		// XXX
		this.typeId = typeId;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
	//	return new EcoreReflectiveFragment(this, baseFlatClass);
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
//		assert pivotClass != null;
//		return pivotClass;
		throw new UnsupportedOperationException();
	}

//	public void setPivotClass(org.eclipse.ocl.pivot.@NonNull Class pivotClass) {		// XXX
//		this.pivotClass = pivotClass;
//	}

	public @NonNull TypeId getTypeId() {
		return typeId;
	}

	@Override
	public @NonNull String toString() {
		return typeId.toString();
	}
}
