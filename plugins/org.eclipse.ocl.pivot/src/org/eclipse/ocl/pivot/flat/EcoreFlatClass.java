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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.PropertyImpl;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

import com.google.common.collect.Lists;

/**
 * An EcoreFlatClass identifies an EClassifier and a corresponding Pivot Class as the client for which caches are provided.
 * @since 7.0
 */
public class EcoreFlatClass extends PartialFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull EClassifier eClassifier;

	public EcoreFlatClass(@NonNull EcoreFlatModel flatModel, @NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class asClass, int flags) {
		super(flatModel, asClass, flags);
		this.eClassifier = eClassifier;
	}

	@Override
	protected @NonNull Operation @NonNull [] computeDirectOperations() {
		if (!(eClassifier instanceof EClass) ) {
			return NO_OPERATIONS;
		}
		List<EOperation> eOperations = ((EClass)eClassifier).getEOperations();
		int iSize = eOperations.size();
		@NonNull Operation @NonNull [] array = new @NonNull Operation[iSize];
		for (int i = 0; i < iSize; i++) {
			EOperation eOperation = eOperations.get(i);
			assert eOperation != null;
			for (EParameter eParameter : eOperation.getEParameters()) {
				EClassifier eClassifier = eParameter.getEType();
				//
			}

		//	ParameterTypes parameterTypes = TypeUtil.createParameterTypes(CodegencompanyTables.Types._Employee);

		//	array[i] = new ExecutorOperation(eOperation, asClass, i);
			throw new UnsupportedOperationException();
		}
		return array;
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
		//	EcoreExecutorProperty asProperty = new EcoreExecutorProperty(eFeature, asClass, i);
			PropertyImpl asProperty = (PropertyImpl)PivotFactory.eINSTANCE.createProperty();
			asProperty.setName(eFeature.getName());
			asProperty.setESObject(eFeature);
		//	asProperty.setIndex(propertyIndex);
		//	asProperty.setImplementation(implementation);
		//	asClass.getOwnedProperties().add(asProperty);
			array[i] = asProperty;
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
		else  {
			Class<?> instanceClass = eClassifier.getInstanceClass();
			if (instanceClass != null) {
				StandardLibrary standardLibrary = getStandardLibrary();
				if (instanceClass == IntegerValue.class) {
					FlatClass superFlatClass = standardLibrary.getRealType().getFlatClass(standardLibrary);
					superFlatClasses = Collections.singletonList(superFlatClass);
				}
				else if (instanceClass == RealValue.class) {
					FlatClass superFlatClass1 = standardLibrary.getOclComparableType().getFlatClass(standardLibrary);
					FlatClass superFlatClass2 = standardLibrary.getOclSummableType().getFlatClass(standardLibrary);
					superFlatClasses = Lists.newArrayList(superFlatClass1, superFlatClass2);
				}
				else if (instanceClass == String.class) {
					FlatClass superFlatClass1 = standardLibrary.getOclComparableType().getFlatClass(standardLibrary);
					FlatClass superFlatClass2 = standardLibrary.getOclSummableType().getFlatClass(standardLibrary);
					superFlatClasses = Lists.newArrayList(superFlatClass1, superFlatClass2);
				}
				else if (instanceClass == UnlimitedNaturalValue.class) {
					FlatClass superFlatClass = standardLibrary.getOclComparableType().getFlatClass(standardLibrary);
					superFlatClasses = Collections.singletonList(superFlatClass);
				}
			//	org.eclipse.ocl.pivot.Class behavioralClass = standardLibrary.getBehavioralClass(instanceClass);
			//	if (behavioralClass != null) {
			//		FlatClass behavioralFlatClass = behavioralClass.getFlatClass(standardLibrary);
			//		superFlatClasses = Collections.singletonList(behavioralFlatClass);
			//	}
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
