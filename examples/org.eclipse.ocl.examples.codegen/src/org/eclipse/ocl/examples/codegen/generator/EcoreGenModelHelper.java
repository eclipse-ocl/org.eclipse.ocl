/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.generator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;

public class EcoreGenModelHelper extends AbstractGenModelHelper
{
	private @Nullable Map<@NonNull EPackage, @NonNull GenPackage> ePackage2genPackage = null;
	private @Nullable Map<@NonNull EClassifier, @NonNull GenClassifier> eClassifier2genClassifier = null;
	public EcoreGenModelHelper(@NonNull PivotMetamodelManager metamodelManager, @Nullable GenModel genModel) {
		super(metamodelManager);
		if (genModel != null) {
			for (@NonNull GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
				EPackage ePackage = genPackage.getEcorePackage();
				if (ePackage != null) {
					Map<@NonNull EPackage, @NonNull GenPackage> ePackage2genPackage2 = ePackage2genPackage;
					if (ePackage2genPackage2 == null) {
						ePackage2genPackage = ePackage2genPackage2 = new HashMap<>();
					}
					ePackage2genPackage2.put(ePackage, genPackage);
					for (@NonNull GenClassifier genClassifier : genPackage.getGenClassifiers()) {
						EClassifier eClassifier = genClassifier.getEcoreClassifier();
						if (eClassifier != null) {
							Map<@NonNull EClassifier, @NonNull GenClassifier> eClassifier2genClassifier2 = eClassifier2genClassifier;
							if (eClassifier2genClassifier2 == null) {
								eClassifier2genClassifier = eClassifier2genClassifier2 = new HashMap<>();
							}
							eClassifier2genClassifier2.put(eClassifier, genClassifier);
						}
					}
				}
			}
		}
	}

	@Override
	protected @NonNull GenClass getGenClass(@NonNull Class asClass) throws GenModelException {
		EObject esObject = asClass.getESObject();
		if (esObject instanceof EClass) {
			return getGenClass((EClass)esObject);
		}
		return super.getGenClass(asClass);
	}

	@Override
	protected @NonNull GenClass getGenClass(@NonNull EClass eClass) throws GenModelException {
		if (eClassifier2genClassifier != null) {
			GenClassifier genClassifier = eClassifier2genClassifier.get(eClass);
			if (genClassifier instanceof GenClass) {
				return (GenClass)genClassifier;
			}
		}
		return super.getGenClass(eClass);
	}

	@Override
	protected @NonNull GenClassifier getGenClassifier(@NonNull EClassifier eClassifier) throws GenModelException {
		if (eClassifier2genClassifier != null) {
			GenClassifier genClassifier = eClassifier2genClassifier.get(eClassifier);
			if (genClassifier != null) {
				return genClassifier;
			}
		}
		return super.getGenClassifier(eClassifier);
	}

	@Override
	public @NonNull GenClassifier getGenClassifier(@NonNull Class asClass) throws GenModelException {
		EObject esObject = asClass.getESObject();
		if (esObject instanceof EClassifier) {
			return getGenClassifier((EClassifier)esObject);
		}
		return super.getGenClass(asClass);
	}

	@Override
	public @Nullable GenPackage getGenPackage(@NonNull Package asPackage) {
		EObject esObject = asPackage.getESObject();
		if (esObject instanceof EPackage) {
			return getGenPackage((EPackage)esObject);
		}
		return super.getGenPackage(asPackage);
	}

	@Override
	public @Nullable GenPackage getGenPackage(@NonNull EPackage ePackage) {
		if (ePackage2genPackage != null) {
			GenPackage genPackage = ePackage2genPackage.get(ePackage);
			if (genPackage != null) {
				return genPackage;
			}
		}
		return super.getGenPackage(ePackage);
	}

	@Override
	public @NonNull String getName(@Nullable ENamedElement eNamedElement) {
		if (eNamedElement == null) {
			return "";
		}
		String name = eNamedElement.getName();
		if (name == null) {
			name = "";
		}
		return name;
	}
}
