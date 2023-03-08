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
package org.eclipse.ocl.pivot.internal.library.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;

/**
 * A ReflectiveFragment provides the description of the properties and operations defined by some class when accessed by the same
 * or another class. The descriptions are normally built lazily and one name at a time using reflective access to some meta-model.
 */
@Deprecated /* @deprecated no longer used */
public abstract class ReflectiveFragment extends FlatFragment
{
	public ReflectiveFragment(@NonNull FlatClass derivedFlatClass, @NonNull FlatClass baseFlatClass) {
		super(derivedFlatClass, baseFlatClass);
	}
}