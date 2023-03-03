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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.CompleteClassImpl;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class CompleteFlatModel extends AbstractFlatModel
{
	protected final @NonNull CompleteModel completeModel;
	private final @NonNull Map<@NonNull CompleteClass, @NonNull CompleteFlatClass> completeClass2flatClass =  new HashMap<>();
//	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull PartialFlatClass> partialClass2flatClass =  new HashMap<>();

	public CompleteFlatModel(@NonNull StandardLibrary standardLibrary, @NonNull CompleteModel completeModel) {
		super(standardLibrary, NameUtil.getSafeName(completeModel));
		this.completeModel = completeModel;
	}

	public @NonNull CompleteFlatClass createFlatClass(@NonNull CompleteClassImpl completeClass) {
		CompleteFlatClass completeFlatClass = new CompleteFlatClass(this, completeClass);
		CompleteFlatClass old = completeClass2flatClass.put(completeClass, completeFlatClass);
		assert old == null;
		return completeFlatClass;
	}

	public @NonNull CompleteModel getCompleteModel() {
		return completeModel;
	}

	public @NonNull FlatClass getPartialFlatClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		CompleteClass completeClass = completeModel.getCompleteClass(partialClass);
	//	PartialFlatClass flatClass = partialClass2flatClass.get(partialClass);
	//	if (flatClass == null) {
	//		flatClass = new PartialFlatClass(this, partialClass);
	//		partialClass2flatClass.put(partialClass, flatClass);
	//	}
		return completeClass.getFlatClass();
	}
}
