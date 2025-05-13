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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * ModelManager provides the models to be used during evaluation. In particular
 * the managed models constitute the extent from which Classifier.allInstances
 * returns are made.
 *
 * @since 7.0
 */
public interface ModelManager
{
	/**
	 * @since 7.0
	 */
	void dispose();

	/**
	 * @since 7.0
	 */
	@NonNull TreeIterator<? extends Object> eAllContents(@NonNull Object object);

	/**
	 * @since 7.0
	 */
	@NonNull EClass eClass(@NonNull Object object);

	/**
	 * @since 7.0
	 */
	@Nullable Object eContainer(@NonNull Object object);

	/**
	 * @since 7.0
	 */
	@Nullable Object eGet(@NonNull Object object, @NonNull EStructuralFeature eFeature);

	/**
	 * Return the instances of the (Pivot) type and its subtypes, returning null for none.
	 * A lazy analyze() is triggered.
	 *
	 * @since 1.18
	 */
	@Nullable Iterable<@NonNull ? extends Object> getInstances(org.eclipse.ocl.pivot.@NonNull Class type);
}
