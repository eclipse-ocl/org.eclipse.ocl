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

import java.util.Iterator;

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

	public PartialFlatClass(@NonNull FlatModel flatModel, @NonNull Type asType, int flags) {
		super(flatModel, NameUtil.getName(asType), computeFlags(asType));
		this.asType = asType;
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new PartialReflectiveFragment(this, baseFlatClass);
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the immediate superinheritances without reference to the fragments.
	 */
	@Override
	protected @NonNull Iterable<@NonNull FlatClass> getInitialSuperFlatClasses() {
		Iterable<? extends org.eclipse.ocl.pivot.@NonNull Class> superClasses = ClassUtil.nullFree(((org.eclipse.ocl.pivot.Class)asType).getSuperClasses());
		final Iterator<? extends org.eclipse.ocl.pivot.@NonNull Class> iterator = superClasses.iterator();
		return new Iterable<@NonNull FlatClass>()
		{
			@Override
			public @NonNull Iterator<@NonNull FlatClass> iterator() {
				return new Iterator<@NonNull FlatClass>()
				{
					private @NonNull StandardLibrary standardLibrary = getStandardLibrary();
					private boolean gotOne = false;

					@Override
					public boolean hasNext() {
						return !gotOne || iterator.hasNext();
					}

					@Override
					public @NonNull FlatClass next() {
						org.eclipse.ocl.pivot.Class next = null;
						if (!gotOne) {
							gotOne = true;
							if (!iterator.hasNext()) {
								next = standardLibrary.getOclAnyType();
							}
						}
						if (next == null) {
							next = ClassUtil.nonNull(iterator.next());
						}
						return next.getFlatClass(standardLibrary);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
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
