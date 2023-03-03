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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreReflectiveFragment;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class EcoreFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull EClassifier eClassifier;		// XXX unify
	protected final org.eclipse.ocl.pivot.@NonNull Class pivotClass;

	public EcoreFlatClass(@NonNull EcoreFlatModel flatModel, @NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class pivotClass) { //, int flags) {
		super(flatModel, NameUtil.getName(eClassifier), 0);
		this.eClassifier = eClassifier;
		this.pivotClass = pivotClass;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		assert !isOclAny();
		List<@NonNull FlatClass> superFlatClasses = null;
		if (eClassifier instanceof EClass) {
			EcoreFlatModel flatModel2 = getFlatModel();
			for (@NonNull EClass eSuperType : ClassUtil.nullFree(((EClass)eClassifier).getESuperTypes())) {
				if (superFlatClasses == null) {
					superFlatClasses = new ArrayList<>();
				}
				FlatClass superFlatClass = flatModel2.getEcoreFlatClass(eSuperType);
				if (!superFlatClasses.contains(superFlatClass)) {		// (very) small list does not merit any usage of a Set within a UniqueList
					superFlatClasses.add(superFlatClass);
				}
			}
		}
		if (superFlatClasses == null) {
			StandardLibrary standardLibrary = getStandardLibrary();
			org.eclipse.ocl.pivot.@NonNull Class oclAnyClass = standardLibrary.getOclAnyType();
			FlatClass oclAnyFlatClass = oclAnyClass.getFlatClass(standardLibrary);
			superFlatClasses = Collections.singletonList(oclAnyFlatClass);
		}
		return superFlatClasses;
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
		return eClassifier.toString();
	}
}
