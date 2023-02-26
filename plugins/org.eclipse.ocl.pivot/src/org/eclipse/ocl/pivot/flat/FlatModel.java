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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.utilities.Nameable;

public interface FlatModel extends Nameable
{
//	void addFlatPackage(@NonNull FlatPackage flatPackage);
//	@NonNull CompleteFlatPackage getFlatPackage(@NonNull CompletePackage completePackage);
	@NonNull StandardLibrary getStandardLibrary();
}
