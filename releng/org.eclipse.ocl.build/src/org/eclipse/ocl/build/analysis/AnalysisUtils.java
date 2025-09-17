/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.analysis;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.serializer.SerializationUtils;

public class AnalysisUtils extends SerializationUtils
{
	/**
	 * Return true if the hierachical name of thisEClassifier is the same as thatEClassifier.
	 *
	 * This avoids problems from metamodel schizophrenia resulting from too many ResourceSets and inconsistent referencing.
	 */
	public static boolean isEqual(@Nullable EClassifier thisEClassifier, @Nullable EClassifier thatEClassifier) {
		if (thisEClassifier == thatEClassifier) {
			return true;
		}
		if ((thisEClassifier == null) || (thatEClassifier == null)) {
			return false;
		}
		if (thisEClassifier.eClass() != thatEClassifier.eClass()) {
			return false;
		}
		if (!ClassUtil.safeEquals(thisEClassifier.getName(), thatEClassifier.getName())) {
			return false;
		}
		return isEqual(thisEClassifier.getEPackage(), thatEClassifier.getEPackage());
	}

	/**
	 * Return true if the hierachical name of thisEPackage is the same as thatEPackage.
	 *
	 * This avoids problems from metamodel schizophrenia resulting from too many ResourceSets and inconsistent referencing.
	 */
	public static boolean isEqual(@Nullable EPackage thisEPackage, @Nullable EPackage thatEPackage) {
		if (thisEPackage == thatEPackage) {
			return true;
		}
		if ((thisEPackage == null) || (thatEPackage == null)) {
			return false;
		}
		if (thisEPackage.eClass() != thatEPackage.eClass()) {
			return false;
		}
		if (!ClassUtil.safeEquals(thisEPackage.getName(), thatEPackage.getName())) {
			return false;
		}
		EPackage thisESuperPackage = thisEPackage.getESuperPackage();
		EPackage thatESuperPackage = thatEPackage.getESuperPackage();
		if ((thisESuperPackage == null) && (thatESuperPackage == null)) {
			return ClassUtil.safeEquals(thisEPackage.getNsURI(), thatEPackage.getNsURI());
		}
		else {
			return isEqual(thisESuperPackage, thatESuperPackage);
		}
	}

	/**
	 * Return true if the hierachical name of thisEStructuralFeature is the same as thatEStructuralFeature.
	 *
	 * This avoids problems from metamodel schizophrenia resulting from too many ResourceSets and inconsistent referencing.
	 */
	public static boolean isEqual(@Nullable EStructuralFeature thisEStructuralFeature, @Nullable EStructuralFeature thatEStructuralFeature) {
		if (thisEStructuralFeature == thatEStructuralFeature) {
			return true;
		}
		if ((thisEStructuralFeature == null) || (thatEStructuralFeature == null)) {
			return false;
		}
		if (thisEStructuralFeature.eClass() != thatEStructuralFeature.eClass()) {
			return false;
		}
		if (!ClassUtil.safeEquals(thisEStructuralFeature.getName(), thatEStructuralFeature.getName())) {
			return false;
		}
		return isEqual(thisEStructuralFeature.getEContainingClass(), thatEStructuralFeature.getEContainingClass());
	}

	public static boolean isSuperTypeOf(@Nullable EClassifier thisEClassifier, @NonNull EClassifier thatEClassifier) {
		if (isEqual(thisEClassifier, thatEClassifier)) {
			return true;
		}if ((thisEClassifier instanceof EClass) && (thatEClassifier instanceof EClass)) {
			for (EClass thatSuperEClass : ((EClass)thatEClassifier).getEAllSuperTypes()) {
				if (isEqual(thisEClassifier, thatSuperEClass)) {
					return true;
				}
			}
		}
		return false;
	}
}
