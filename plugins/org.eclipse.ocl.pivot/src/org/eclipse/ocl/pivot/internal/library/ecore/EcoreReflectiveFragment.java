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
package org.eclipse.ocl.pivot.internal.library.ecore;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.flat.EcoreFlatClass;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.internal.library.executor.ReflectiveFragment;

@Deprecated /* @deprecated no longer used */
public class EcoreReflectiveFragment extends ReflectiveFragment
{
	protected final @NonNull EClassifier eClassifier;

/*	public EcoreReflectiveFragment(@NonNull EcoreReflectiveType derivedInheritance, @NonNull FlatClass baseFlatClass) {
		super(derivedInheritance.getFlatClass(), baseFlatClass);
		this.eClassifier = derivedInheritance.getEClassifier();
	} */

	public EcoreReflectiveFragment(@NonNull EcoreFlatClass derivedFlatClass, @NonNull FlatClass baseFlatClass) {
		super(derivedFlatClass, baseFlatClass);
		this.eClassifier = derivedFlatClass.getEClassifier();
	}

	public final @NonNull EClassifier getEClassifier() {
		return eClassifier;
	}

	@Override
	public @Nullable Operation getLocalOperation(@NonNull Operation baseOperation) {
		throw new UnsupportedOperationException();		// FIXME
	}
}