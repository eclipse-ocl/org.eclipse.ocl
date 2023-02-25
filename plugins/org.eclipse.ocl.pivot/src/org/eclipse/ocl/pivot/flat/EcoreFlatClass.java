/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreReflectiveFragment;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class EcoreFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull EClassifier eClassifier;		// XXX unify

	public EcoreFlatClass(@NonNull EClassifier eClassifier, int flags) {
		super(NameUtil.getName(eClassifier), flags);
		this.eClassifier = eClassifier;
	}

	@Override
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new EcoreReflectiveFragment(this, baseFlatClass);
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	public @NonNull EClassifier getEClassifier() {
		return eClassifier;
	}

	/**
	 * Return the immediate superinheritances without reference to the fragments.
	 */
	@Override
	protected @NonNull Iterable<@NonNull FlatClass> getInitialSuperFlatClasses() {
		List<EClass> eSuperTypes = eClassifier instanceof EClass ? ((EClass)eClassifier).getESuperTypes() : Collections.<EClass>emptyList();
		final Iterator<EClass> iterator = eSuperTypes.iterator();
		return new Iterable<@NonNull FlatClass>()
		{
			@Override
			public @NonNull Iterator<@NonNull FlatClass> iterator() {
				return new Iterator<@NonNull FlatClass>()
				{
					private @NonNull StandardLibrary standardLibrary = getStandardLibrary();
					private @NonNull IdResolver idResolver = getIdResolver();
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
						return idResolver.getInheritance(next).getFlatClass();
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
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull String toString() {
		return eClassifier.toString();
	}
}
