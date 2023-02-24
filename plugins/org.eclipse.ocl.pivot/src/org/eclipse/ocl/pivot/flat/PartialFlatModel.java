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
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class PartialFlatModel extends AbstractFlatModel
{
	protected final /*@NonNull*/ Model model;
//	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull PartialFlatClass> asClass2flatClass =  new HashMap<>();

	@Deprecated
	public PartialFlatModel(@NonNull StandardLibrary standardLibrary) {
		super(standardLibrary, "");
		this.model = null;
	}

	public PartialFlatModel(@NonNull Model model, @NonNull StandardLibrary standardLibrary) {
		super(standardLibrary, PivotUtil.getName(model));
		this.model = model;
	}

	@Override
	public @NonNull CompleteModel getCompleteModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PartialFlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		throw new UnsupportedOperationException();			// XXX
	/*	PartialFlatClass flatClass = asClass2flatClass.get(asClass);		// Used by Ecore specializations
		if (flatClass == null) {
			flatClass = new PartialFlatClass(this, asClass);
			asClass2flatClass.put(asClass, flatClass);
		}
		else {
			assert false;
		}
		return flatClass; */
	}

	public @NonNull Model getModel() {
		assert model != null;
		return model;
	}

	@Override
	public @NonNull Type getPrimaryType(org.eclipse.ocl.pivot.@NonNull Class owningType) {
		throw new UnsupportedOperationException();
	}
}
