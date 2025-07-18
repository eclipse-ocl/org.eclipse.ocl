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
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * @since 7.0
 */
public class CompleteFlatModel extends AbstractFlatModel
{
	protected final @NonNull CompleteModel completeModel;

	public CompleteFlatModel(@NonNull CompleteStandardLibrary standardLibrary, @NonNull CompleteModel completeModel) {
		super(standardLibrary, NameUtil.getSafeName(completeModel));
		this.completeModel = completeModel;
	}

	public @NonNull CompleteFlatClass createFlatClass(@NonNull CompleteClass completeClass) {
		return new CompleteFlatClass(this, completeClass);
	}

	@Override
	public @NonNull CompleteModel getCompleteModel() {
		return completeModel;
	}

	@Override
	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		CompleteClass completeClass = completeModel.getCompleteClass(partialClass);
		return completeClass.getFlatClass();
	}

	@Override
	public @NonNull Type getPrimaryType(org.eclipse.ocl.pivot.@NonNull Class owningType) {
		return ((CompleteModelInternal)completeModel).getCompleteClass(owningType).getPrimaryClass();
	}
}
