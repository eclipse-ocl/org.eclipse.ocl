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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * An EcoreFlatClass identifies an EClassifier and a corresponding Pivot Class as the client for which caches are provided.
 * @since 7.0
 */
public class EcoreFlatClass extends PartialFlatClass
{
	protected final @NonNull EClassifier eClassifier;

	public EcoreFlatClass(@NonNull EcoreFlatModel flatModel, @NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class asClass, int flags) {
		super(flatModel, asClass, flags);
		this.eClassifier = eClassifier;
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
