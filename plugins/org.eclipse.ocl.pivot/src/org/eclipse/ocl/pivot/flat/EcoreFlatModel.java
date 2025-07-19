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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibrary;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 7.0
 */
public class EcoreFlatModel extends PartialFlatModel
{
	private final @NonNull Map<@NonNull EClassifier, @NonNull EcoreFlatClass> eClassifier2flatClass =  new HashMap<>();

	@Deprecated
	public EcoreFlatModel(@NonNull PartialStandardLibrary standardLibrary) {
		super(standardLibrary);
	}

	public EcoreFlatModel(@NonNull Model model, @NonNull PartialStandardLibrary standardLibrary) {
		super(model, standardLibrary);
	}

	public @NonNull EcoreFlatClass getEcoreFlatClass(@NonNull EClassifier eClassifier) {
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		if (flatClass == null) {
			EPackage ePackage = eClassifier.getEPackage();
			assert ePackage != null;
			org.eclipse.ocl.pivot.Package execPackage = getStandardLibrary().getPackage(ePackage);
			if (execPackage == null) {
				PackageId packageId = IdManager.getPackageId(ePackage);
				Element asPackage = packageId.accept(standardLibrary.getIdResolver());
				if (asPackage instanceof org.eclipse.ocl.pivot.Package) {
					execPackage = (org.eclipse.ocl.pivot.Package) asPackage;
				}
			}
			if (execPackage != null) {
				org.eclipse.ocl.pivot.Class asType = execPackage.getOwnedClass(eClassifier.getName());
				if (asType != null) {
					flatClass = (EcoreFlatClass) standardLibrary.getFlatClass(asType);
					eClassifier2flatClass.put(eClassifier, flatClass);
				}
			}
		}
		return ClassUtil.requireNonNull(flatClass);
	}

/*	public @NonNull EcoreFlatClass getEcoreFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		EClassifier eClassifier = (EClassifier)asClass.getESObject();
		assert eClassifier != null;
		return getEcoreFlatClass(eClassifier, asClass);
	} */

	private @NonNull EcoreFlatClass getEcoreFlatClass(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		if (flatClass == null) {
			int flags = 0;
			if ((eClassifier == OCLstdlibPackage.Literals.ORDERED_COLLECTION) || (eClassifier == OCLstdlibPackage.Literals.ORDERED_SET) || (eClassifier == OCLstdlibPackage.Literals.SEQUENCE)) {
				flags |= FlatClass.ORDERED;
			}
			if ((eClassifier == OCLstdlibPackage.Literals.ORDERED_SET) || (eClassifier == OCLstdlibPackage.Literals.SET) || (eClassifier == OCLstdlibPackage.Literals.UNIQUE_COLLECTION)) {
				flags |= FlatClass.UNIQUE;
			}
			else if (eClassifier == OCLstdlibPackage.Literals.OCL_ANY) {
				flags |= FlatClass.OCL_ANY;
			}
			else if (eClassifier == OCLstdlibPackage.Literals.OCL_VOID) {
				flags |= FlatClass.OCL_VOID;
			}
			else if (eClassifier == OCLstdlibPackage.Literals.OCL_INVALID) {
				flags |= FlatClass.OCL_INVALID;
			}
			if ((eClassifier instanceof EClass) && ((EClass)eClassifier).isAbstract()) {
				flags |= FlatClass.ABSTRACT;
			}
			flatClass = new EcoreFlatClass(this, eClassifier, asClass, flags);
			eClassifier2flatClass.put(eClassifier, flatClass);
		}
		return flatClass;
	}

	@Override
	public @NonNull PartialFlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		EObject esObject = PivotUtil.getUnspecializedTemplateableElement(asClass).getESObject();
		if (esObject instanceof EClassifier) {
			return getEcoreFlatClass((EClassifier)esObject, asClass);
		}
		return super.getFlatClass(asClass);
	}
}
