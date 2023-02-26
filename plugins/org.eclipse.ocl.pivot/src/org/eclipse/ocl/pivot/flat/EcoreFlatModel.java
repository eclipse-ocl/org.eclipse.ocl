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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.StandardLibrary;

public class EcoreFlatModel extends AbstractFlatModel
{
	private final @NonNull Map<@NonNull EClassifier, @NonNull EcoreFlatClass> flatClasses =  new HashMap<>();

	public EcoreFlatModel(@NonNull StandardLibrary standardLibrary, @Nullable EPackage ePackage) {
		super(standardLibrary, ""/*NameUtil.getSafeName(ePackage)*/);
	//	this.ePackage = ePackage;
	}

	public @NonNull EcoreFlatClass getFlatClass(@NonNull EClassifier eClassifier) {
		EcoreFlatClass flatPackage = flatClasses.get(eClassifier);
		if (flatPackage == null) {
			flatPackage = new EcoreFlatClass(this, eClassifier);
			flatClasses.put(eClassifier, flatPackage);
		}
		return flatPackage;
	}
}
