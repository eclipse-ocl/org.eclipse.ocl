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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class EcoreFlatModel extends PartialFlatModel
{
	private final @NonNull Map<@NonNull EClassifier, @NonNull EcoreFlatClass> eClassifier2flatClass =  new HashMap<>();

	@Deprecated
	public EcoreFlatModel(@NonNull StandardLibrary standardLibrary) {
		super(standardLibrary);
	}

	public EcoreFlatModel(@NonNull Model model, @NonNull StandardLibrary standardLibrary) {
		super(model, standardLibrary);
	}

	public @NonNull EcoreFlatClass getEcoreFlatClass(@NonNull EClassifier eClassifier) {
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		return ClassUtil.nonNullState(flatClass);
	}

	public @NonNull EcoreFlatClass getEcoreFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		EClassifier eClassifier = (EClassifier)asClass.getESObject();
		assert eClassifier != null;
		return getEcoreFlatClass(eClassifier, asClass);
	}

	private @NonNull EcoreFlatClass getEcoreFlatClass(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		if (flatClass == null) {
			flatClass = new EcoreFlatClass(this, eClassifier, asClass);
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
