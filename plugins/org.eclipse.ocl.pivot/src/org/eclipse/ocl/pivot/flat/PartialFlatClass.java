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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.executor.PartialReflectiveFragment;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class PartialFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull Type asType;

	public PartialFlatClass(@NonNull FlatModel flatModel, @NonNull Type asType) {
		super(flatModel, NameUtil.getName(asType), computeFlags(asType));
		this.asType = asType;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		assert !isOclAny();
		List<@NonNull FlatClass> superFlatClasses = null;
		StandardLibrary standardLibrary = getStandardLibrary();
		for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : ClassUtil.nullFree(((org.eclipse.ocl.pivot.Class)asType).getSuperClasses())) {
			if (superFlatClasses == null) {
				superFlatClasses = new ArrayList<>();
			}
			FlatClass superFlatClass = asSuperClass.getFlatClass(standardLibrary);
			if (!superFlatClasses.contains(superFlatClass)) {		// (very) small list does not merit any usage of a Set within a UniqueList
				superFlatClasses.add(superFlatClass);
			}
		}
		if (superFlatClasses == null) {
			org.eclipse.ocl.pivot.@NonNull Class oclAnyClass = standardLibrary.getOclAnyType();
			FlatClass oclAnyFlatClass = oclAnyClass.getFlatClass(standardLibrary);
			superFlatClasses = Collections.singletonList(oclAnyFlatClass);
		}
		return superFlatClasses;
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new PartialReflectiveFragment(this, baseFlatClass);
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return (org.eclipse.ocl.pivot.Class)asType;			// FIXME cast
	}

	@Override
	public @NonNull String toString() {
		return asType.toString();
	}
}
