/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.compatibility.EMF_2_9;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * GenPackageManager manages the known GenPackages and GenModels.
 * @since 7.0
 */
public class GenPackageManager
{
	protected final @NonNull EnvironmentFactory environmentFactory;
	private @Nullable Map<@NonNull String, @NonNull GenPackage> genPackageMap = null;
	private boolean hasUML = false;

	/**
	 * Construct a MetamodelManager that will use environmentFactory to create its artefacts
	 * such as an asResourceSet to contain pivot copies of meta-models.
	 * @since 7.0
	 */
	public GenPackageManager(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
	}

	public void addGenModel(@NonNull GenModel genModel) {
		for (GenPackage genPackage : genModel.getAllGenUsedAndStaticGenPackagesWithClassifiers()) {
			assert genPackage != null;
			addGenPackage(genPackage);
		}
	}

	public void addGenPackage(@NonNull GenPackage genPackage) {
		GenModel genModel = genPackage.getGenModel();
		String genModelPackageClass = genModel.getClass().getPackage().getName();
		if (genModelPackageClass.startsWith("org.eclipse.uml2")) {
			hasUML = true;
		}
		Map<@NonNull String, @NonNull GenPackage> genPackageMap2 = genPackageMap;
		if (genPackageMap2 == null) {
			genPackageMap = genPackageMap2 = new HashMap<>();
		}
		genPackageMap2.put(ClassUtil.requireNonNull(genPackage.getNSURI()), genPackage);
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	public @Nullable GenPackage getGenPackage(@NonNull String nsURI) {
		if (genPackageMap != null) {
			GenPackage genPackage = genPackageMap.get(nsURI);
			if (genPackage != null) {
				return genPackage;
			}
		}
		ResourceSet externalResourceSet = environmentFactory.getResourceSet();
		URI uri = EMF_2_9.EcorePlugin.getEPackageNsURIToGenModelLocationMap(true).get(nsURI);
		if (uri != null) {
			Resource resource = externalResourceSet.getResource(uri, true);
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof GenModel) {
					GenModel genModel = (GenModel)eObject;
					genModel.reconcile();
					for (GenPackage genPackage : genModel.getGenPackages()) {
						if (genPackage != null) {
							addGenPackage(genPackage);
							return genPackage;
						}
					}
				}
			}
		}
		return null;
	}

	public boolean hasUML() {
		return hasUML;
	}
}
