/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * An EcoreModelManager provides the models to be used during evaluation exploiting the EMF API to
 * determine EClass instantiation and EReference opposite navigation.
 *
 * @since 7.0
 */
public interface EcoreModelManager extends ModelManager
{
	/**
	 * Register allInstancesEClass as an EClass for which allInstances may be invoked.
	 * This invokes resetAnalysis to force a lazy reanalysis.
	 */
	void addAllInstancesEClass(@NonNull EClass allInstancesEClass);

	/**
	 * Register implicitOppositeEReference as an EReference for which implicit opposite navigation may be invoked.
	 * This invokes resetAnalysis to force a lazy reanalysis.
	 */
	void addImplicitOppositeEReference(@NonNull EReference implicitOppositeEReference);

	/**
	 *  Eagerly perform the totla ResourceSet traversal to discover allInstances/implicitOpposites.
	 */
	void analyze();

	/**
	 * Return the instances of eClass and its subtypes, returning null for none.
	 * A lazy analyze() is triggered.
	 */
	@Nullable Iterable<@NonNull EObject> getInstances(@NonNull EClass eClass);

	/**
	 * Return the instances of the class whose id is typeId and its subtypes, returning null for none.
	 * A lazy analyze() is triggered.
	 */
	@Nullable Iterable<@NonNull EObject> getInstances(@NonNull TypeId typeId);

	/**
	 * Return the source EObjects for which the opposite of eReference navigates to eTarget, returning null if none.
	 * A lazy analyze() is triggered.
	 */
	@Nullable Iterable<@NonNull EObject> getOpposites(@NonNull EReference eReference, @NonNull EObject eTarget);

	/**
	 * Reset the analysis forcing a re-analysis of the model. This may be necessary after a late discovery of
	 * an allInstances/implicitOpposites from uncompiled OCL.
	 */
	void resetAnalysis();
}