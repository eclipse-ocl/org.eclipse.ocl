/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.StandardLibrary;

public abstract class AbstractTypeManager
{
	protected final @NonNull StandardLibrary standardLibrary;
//	protected final @NonNull Orphanage orphanage;
	protected final boolean useWeakReferences;			// See Bug 581760

	protected AbstractTypeManager(@NonNull StandardLibrary standardLibrary, boolean useWeakReferences) {
		this.standardLibrary = standardLibrary;
		this.useWeakReferences = useWeakReferences;
//		this.orphanage = standardLibrary.getOrphanage();
	}

	public void dispose() {}

	public @NonNull Orphanage getOrphanage() {
		return standardLibrary.getSharedOrphanage(); // orphanage;
	}

	protected final @NonNull StandardLibrary getStandardLibrary() {
		return standardLibrary;
	}
}