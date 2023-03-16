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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * An EcoreFlatClass identifies an EClassifoer and a corresponding Pivot Class as the client for which caches are provided.
 */
public class EcoreFlatClass extends PartialFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull EClassifier eClassifier;

	public EcoreFlatClass(@NonNull EcoreFlatModel flatModel, @NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		super(flatModel, asClass);
		this.eClassifier = eClassifier;
	}

	@Override
	protected @NonNull Property @NonNull [] computeDirectProperties() {
		if (!(eClassifier instanceof EClass) ) {
			return NO_PROPERTIES;
		}
		List<EStructuralFeature> eStructuralFeatures = ((EClass)eClassifier).getEStructuralFeatures();
		int iSize = eStructuralFeatures.size();
		@NonNull Property @NonNull [] array = new @NonNull Property[iSize];
		for (int i = 0; i < iSize; i++) {
			EStructuralFeature eFeature = eStructuralFeatures.get(i);
			assert eFeature != null;
			array[i] = new EcoreExecutorProperty(eFeature, asClass, i);
		}
		return array;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {	// This occurs before AS superclasses are defined
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

	public @NonNull EClassifier getEClassifier() {
		return eClassifier;
	}

	@Override
	public @NonNull EcoreFlatModel getFlatModel() {
		return (EcoreFlatModel)flatModel;
	}

	@Override
	public @NonNull String toString() {
		return NameUtil.qualifiedNameFor(eClassifier);
	}
}
