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

	public @NonNull CompleteModel getCompleteModel() {
		return completeModel;
	}

	public @NonNull CompleteFlatClass getCompleteFlatClass(@NonNull CompleteClass completeClass) {
		CompleteFlatClass flatClass = completeClass2flatClass.get(completeClass);
		if (flatClass == null) {
			flatClass = new CompleteFlatClass(this, completeClass);
			completeClass2flatClass.put(completeClass, flatClass);
		}
		return flatClass;
	}

	public @NonNull FlatClass getPartialFlatClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		CompleteClass completeClass = completeModel.getCompleteClass(partialClass);
	//	PartialFlatClass flatClass = partialClass2flatClass.get(partialClass);
	//	if (flatClass == null) {
	//		flatClass = new PartialFlatClass(this, partialClass);
	//		partialClass2flatClass.put(partialClass, flatClass);
	//	}
		return getCompleteFlatClass(completeClass);
	}
}
