/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.CompleteModelImpl;
import org.eclipse.ocl.pivot.internal.CompletePackageImpl;

public class NestedCompletePackages extends AbstractCompletePackages
{
	private static final long serialVersionUID = 1L;

	public NestedCompletePackages(@NonNull CompletePackageImpl owner) {
		super(CompletePackage.class, owner, PivotPackage.Literals.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES.getFeatureID(),
			PivotPackage.Literals.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE.getFeatureID());
		doRefreshNestedPackages();
	}

	@Override
	public @NonNull CompletePackage createCompletePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
	//	CompletePackageInternal completePackage2 = (CompletePackageInternal) PivotFactory.eINSTANCE.createCompletePackage();
	//	String completeURI = partialPackage.getURI();
	//	if (completeURI == null) {
	//		completeURI = ((CompletePackage)owner).getURI() + "#" + partialPackage.getName();
	//	}
		String nestedCompletePackageName = ((CompletePackage)owner).getName() + "#" + partialPackage.getName();
		return createCompletePackage(nestedCompletePackageName, partialPackage.getNsPrefix(), partialPackage.getURI());
	}

	protected void doRefreshNestedPackages() {
		for (org.eclipse.ocl.pivot.Package partialPackage : getPartialPackages()) {
//			for (org.eclipse.ocl.pivot.Package partialChildPackage : partialParentPackage.getOwnedPackages()) {
				if (partialPackage != null) {
					getOwnedCompletePackage(partialPackage);
				}
//			}
		}
	}

	@Override
	public @NonNull CompleteModelImpl getCompleteModel() {
		return (CompleteModelImpl)getCompletePackage().getCompleteModel();
	}

	/**
	 * @since 7.0
	 */
	public @NonNull CompletePackage getCompletePackage() {
		assert owner!= null;
		return (CompletePackage)owner;
	}

	@Override
	public @NonNull CompletePackage getOwnedCompletePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		CompletePackage completePackage = null;
		String packageURI = partialPackage.getURI();
		if (packageURI != null) {
			completePackage = getCompleteModel().basicGetCompletePackageForURI(packageURI);
		}
		if (completePackage == null) {
			String name = partialPackage.getName();
			completePackage = super.basicGetOwnedCompletePackage(name);
		}
		if (completePackage == null) {
			completePackage = createCompletePackage(partialPackage);
			add(completePackage);
		}
		completePackage.getPartialPackages().add(partialPackage);
		return completePackage;
	}

	@Override
	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages() {
		return ((CompletePackageImpl)getCompletePackage()).getPartialPackages().getNestedPartialPackages();
	}

	/**
	 * @since 7.0
	 */
	public CompletePackage getCompletePackage(@Nullable String name) {
		throw new UnsupportedOperationException();			// XXX
	}
}