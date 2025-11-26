/*******************************************************************************
 * Copyright (c) 2014, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.CompleteModelImpl;

public class RootCompletePackages extends AbstractCompletePackages
{
	private static final long serialVersionUID = 1L;

	public RootCompletePackages(@NonNull CompleteModelImpl owner) {
		super(CompletePackage.class, owner, PivotPackage.Literals.COMPLETE_MODEL__OWNED_COMPLETE_PACKAGES.getFeatureID(), PivotPackage.Literals.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL.getFeatureID());
	}

	@Override
	public @NonNull CompleteModelImpl getCompleteModel() {
		assert owner != null;
		return (CompleteModelImpl)owner;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CompletePackage getOwnedCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return getCompleteModel().getCompletePackage(asPackage);
	}

	@Override
	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages() {
		return getCompleteModel().getPartialModels().getNestedPartialPackages();
	}
}