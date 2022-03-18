/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.library.executor.ReflectiveFragment;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;

public class EcoreReflectiveFragment extends ReflectiveFragment
{
	protected final @NonNull EClassifier eClassifier;

	public EcoreReflectiveFragment(@NonNull EcoreReflectiveType derivedInheritance, @NonNull CompleteInheritance baseInheritance) {
		super(derivedInheritance, baseInheritance);
		this.eClassifier = derivedInheritance.getEClassifier();
	}

	public final @NonNull EClassifier getEClassifier() {
		return eClassifier;
	}

	@Override
	public @NonNull Iterable<@NonNull Operation> getLocalOperations() {
		Map<@NonNull Operation, @NonNull LibraryIterationOrOperation> operationMap2 = operationMap;
		if (operationMap2 == null) {
			synchronized (this) {
				operationMap2 = operationMap;
				if (operationMap2 == null) {
					operationMap = operationMap2 = initOperations();		// Optimize to reuse single super map if no local ops
				}
			}
		}
		return operationMap2.keySet();
	}

	@Override
	public @NonNull Iterable<@NonNull Property> getLocalProperties() {
		Map<@NonNull Property, @NonNull LibraryProperty> propertyMap2 = propertyMap;
		if (propertyMap2 == null) {
			synchronized (this) {
				propertyMap2 = propertyMap;
				if (propertyMap2 == null) {
					propertyMap = propertyMap2 = initProperties();		// Optimize to reuse single super map if no local ops
				}
			}
		}
		return propertyMap2.keySet();
	}

	/**
	 * @since 1.18
	 */
	protected @NonNull Map<@NonNull Operation, @NonNull LibraryIterationOrOperation> initOperations() {
		Map<@NonNull Operation, @NonNull LibraryIterationOrOperation> map = new HashMap<>();
		List<EOperation> eOperations = ((EClass) eClassifier).getEOperations();
		for (int i = 0; i < eOperations.size(); i++) {
			@SuppressWarnings("null")@NonNull EOperation eOperation = eOperations.get(i);
			EcoreExecutorOperation operationAndImplementation = new EcoreExecutorOperation(eOperation, (EcoreReflectiveType)getDerivedInheritance(), i);
			map.put(operationAndImplementation, operationAndImplementation);
		}
		return map;
	}

	protected @NonNull Map<@NonNull Property, @NonNull LibraryProperty> initProperties() {
		Map<@NonNull Property, @NonNull LibraryProperty> map = new HashMap<>();
		List<EStructuralFeature> eStructuralFeatures = ((EClass) eClassifier).getEStructuralFeatures();
		for (int i = 0; i < eStructuralFeatures.size(); i++) {
			@SuppressWarnings("null")@NonNull EStructuralFeature eFeature = eStructuralFeatures.get(i);
			EcoreExecutorProperty propertyAndImplementation = new EcoreExecutorProperty(eFeature, (EcoreReflectiveType)getDerivedInheritance(), i);
			map.put(propertyAndImplementation, propertyAndImplementation);
		}
		return map;
	}
}