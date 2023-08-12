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
import org.eclipse.ocl.pivot.CompleteClass;

public class BasicCompleteFlatClass extends CompleteFlatClass
{
	public BasicCompleteFlatClass(@NonNull CompleteFlatModel flatModel, @NonNull CompleteClass completeClass) {
		super(flatModel, completeClass);
		assert (asClass.getOwnedSignature()== null) && (asClass.getGeneric() == null) && (asClass.getOwnedBindings().size() == 0);
	}
}
