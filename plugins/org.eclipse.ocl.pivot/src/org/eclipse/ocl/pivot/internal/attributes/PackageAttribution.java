/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.attributes;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.scoping.AbstractAttribution;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.ScopeView;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

public class PackageAttribution extends AbstractAttribution
{
	public static final PackageAttribution INSTANCE = new PackageAttribution();

	@Override
	public ScopeView computeLookup(@NonNull EObject target, @NonNull EnvironmentView environmentView, @NonNull ScopeView scopeView) {
		org.eclipse.ocl.pivot.Package targetPackage = (org.eclipse.ocl.pivot.Package)target;
		EnvironmentFactoryInternal environmentFactory = environmentView.getEnvironmentFactory();
		//		if (targetPackage.getImportedPackage().size() > 0) {
		Set<org.eclipse.ocl.pivot.Package> allPackages = new HashSet<org.eclipse.ocl.pivot.Package>();
		gatherAllPackages(environmentFactory, allPackages, targetPackage);
		for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Package aPackage : allPackages) {
			environmentView.addAllPackages(aPackage);
			environmentView.addAllTypes(aPackage);
		}
		//		}
		//		else {
		//			environmentView.addAllPackages(targetPackage);
		//			environmentView.addAllTypes(targetPackage);
		//		}
		return scopeView.getParent();
	}

	private void gatherAllPackages(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Set<org.eclipse.ocl.pivot.Package> allPackages,
			org.eclipse.ocl.pivot.@NonNull Package targetPackage) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		org.eclipse.ocl.pivot.Package primaryPackage = metamodelManager.getPrimaryElement(targetPackage);
		if (allPackages.add(primaryPackage)) {
			for (org.eclipse.ocl.pivot.@NonNull Package partialPackage : metamodelManager.getPartialPackages(primaryPackage, false)) {
				for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Package importedPackage : partialPackage.getImportedPackages()) {
					gatherAllPackages(environmentFactory, allPackages, importedPackage);
				}
			}
		}
	}
}
