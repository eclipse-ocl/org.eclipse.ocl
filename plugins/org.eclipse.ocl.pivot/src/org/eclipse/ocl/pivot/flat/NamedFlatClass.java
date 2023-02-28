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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.StandardLibrary;
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

	/**
	 * Return the immediate superinheritances without reference to the fragments.
	 */
	@Override
	protected @NonNull Iterable<@NonNull FlatClass> getInitialSuperFlatClasses() {
		List<EClass> eSuperTypes = Collections.<EClass>emptyList();
		final Iterator<EClass> iterator = eSuperTypes.iterator();
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
						if (!gotOne) {
							gotOne = true;
							if (!iterator.hasNext()) {
								org.eclipse.ocl.pivot.Class oclAnyType = standardLibrary.getOclAnyType();
								return standardLibrary.getFlatClass(oclAnyType);
							}
						}
						EClass next = iterator.next();
						assert next != null;
					//	return ((EcoreFlatPackage)getFlatPackage()).getIdResolver().getInheritance(next).getFlatClass();
						return getFlatModel().getEcoreFlatClass(next);
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
		return pivotClass;
	}

	@Override
	public @NonNull String toString() {
		return pivotClass.toString();
	}
}
