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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.library.PartialStandardLibrary;

/**
 * ReflectiveEcoreFlatModel supports use of the OCL AS and Flat models when the first encounter of a user model
 * is the reference to an Ecore model (typically an EPackage::nsURI).
 * The Pivot class elements are determined reflectively at run-time rather than at compile-time.
 *
 * @since 7.0
 */
public class ReflectiveEcoreFlatModel extends EcoreFlatModel
{
	public ReflectiveEcoreFlatModel(@NonNull PartialStandardLibrary standardLibrary, @NonNull EPackage ePackage) {
		super(standardLibrary);
	}

	@Override
	protected EcoreFlatClass createFlatClass(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class asClass, int flags) {
		return new ReflectiveEcoreFlatClass(this, eClassifier, asClass, flags);
	}
}
