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
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;

public class PartialFlatModel extends AbstractFlatModel
{
//	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull PartialFlatClass> asClass2flatClass =  new HashMap<>();

	public PartialFlatModel(@NonNull StandardLibrary standardLibrary) {
		super(standardLibrary, "");
	}

	@Override
	public @NonNull CompleteModel getCompleteModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PartialFlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		throw new UnsupportedOperationException();			// XXX
	/*	PartialFlatClass flatClass = asClass2flatClass.get(asClass);
		if (flatClass == null) {
			flatClass = new PartialFlatClass(this, asClass);
			asClass2flatClass.put(asClass, flatClass);
		}
		else {
			assert false;
		}
		return flatClass; */
	}

	@Override
	public @NonNull Type getPrimaryType(@NonNull Class owningType) {
		throw new UnsupportedOperationException();
	}
}
