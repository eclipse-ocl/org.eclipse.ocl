/**
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.library.executor;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.flat.PartialFlatModel;

/**
 * @since 7.0
 *
 */
public interface PartialStandardLibrary extends StandardLibrary
{
	@Override
	@NonNull PartialFlatModel getFlatModel();

	org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EPackage ePackage);
}
