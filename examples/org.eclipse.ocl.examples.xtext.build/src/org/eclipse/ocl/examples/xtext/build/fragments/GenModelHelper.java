/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.build.fragments;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class GenModelHelper		// Trimmed from org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper
{
	private @NonNull ResourceSet resourceSet;

	public GenModelHelper(@NonNull ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public @Nullable String getEcoreLiteralName(@NonNull EStructuralFeature eFeature) {
		try {
			GenFeature genFeature = getGenFeature(eFeature);
			return genFeature.getGenClass().getFeatureID(genFeature);
		} catch (IOException e) {
			return null;
		}
	}

	protected @NonNull GenClass getGenClass(@NonNull EClass eClass) throws IOException {
		GenPackage genPackage = getGenPackage(eClass);
		if (genPackage != null) {
			String name = eClass.getName();
			for (GenClass genClass : genPackage.getGenClasses()) {
				String clsName = genClass.getEcoreClass().getName();
				if (name.equals(clsName)) {
					return genClass;
				}
			}
		}
		throw new IOException("No GenClass for " + eClass);
	}

	public @NonNull GenFeature getGenFeature(@NonNull EStructuralFeature eStructuralFeature) throws IOException {
		EClass eClass = eStructuralFeature.getEContainingClass();
		if (eClass != null) {
			GenClass genClass = getGenClass(eClass);
			String name = eStructuralFeature.getName();
			for (GenFeature genFeature : genClass.getGenFeatures()) {
				String featureName = genFeature.getEcoreFeature().getName();
				if (name.equals(featureName)) {
					return genFeature;
				}
			}
		}
		throw new IOException("No GenFeature for " + eStructuralFeature);
	}

	public @Nullable GenPackage getGenPackage(@NonNull EClassifier eClassifier) {
		EPackage ePackage = eClassifier.getEPackage();
		if (ePackage == null) {
			return null;
		}
		return getGenPackage(ePackage);
	}

	public @Nullable GenPackage getGenPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		if (nsURI == null) {
			return null;
		}
		return /*metamodelManager.*/getGenPackage(nsURI);
	}

	private @Nullable Map<String, GenPackage> genPackageMap = null;

	private void addGenPackage(@NonNull GenPackage genPackage) {
		Map<String, GenPackage> genPackageMap2 = genPackageMap;
		if (genPackageMap2 == null) {
			genPackageMap = genPackageMap2 = new HashMap<>();
		}
		genPackageMap2.put(genPackage.getNSURI(), genPackage);
	}

	private @Nullable GenPackage getGenPackage(@NonNull String nsURI) {
		if (genPackageMap != null) {
			GenPackage genPackage = genPackageMap.get(nsURI);
			if (genPackage != null) {
				return genPackage;
			}
		}
		URI uri = EcorePlugin.getEPackageNsURIToGenModelLocationMap(true).get(nsURI);
		if (uri != null) {
			Resource resource = resourceSet.getResource(uri, true);
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

	@SuppressWarnings("null")
	public @NonNull String getLiteralName(@NonNull EClassifier eClassifier) {
		String name = eClassifier.getName();
		return CodeGenUtil.upperName(name != null ? name : "");
	}

	public @Nullable String getQualifiedPackageInterfaceName(@NonNull EPackage ePackage) {
		GenPackage genPackage = getGenPackage(ePackage);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedPackageInterfaceName();
	}
}
