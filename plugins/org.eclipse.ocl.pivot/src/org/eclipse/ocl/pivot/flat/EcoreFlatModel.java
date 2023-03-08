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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreReflectiveType;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class EcoreFlatModel extends AbstractFlatModel
{
	private final @NonNull Map<@NonNull EClassifier, @NonNull EcoreFlatClass> eClassifier2flatClass =  new HashMap<>();

	public EcoreFlatModel(@NonNull StandardLibrary standardLibrary) {
		super(standardLibrary, "");
	}

	public @NonNull FlatClass getEcoreFlatClass(@NonNull EClassifier eClassifier) {
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		return ClassUtil.nonNullState(flatClass);
	}

	public @NonNull EcoreFlatClass getEcoreFlatClass(@NonNull EcoreExecutorType type) {
		EClassifier eClassifier = type.getEClassifier();
		assert eClassifier != null;
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		if (flatClass == null) {
			flatClass = new EcoreFlatClass(this, eClassifier, type);
			eClassifier2flatClass.put(eClassifier, flatClass);
		}
		else {
			assert false;
		}
		return flatClass;
	}

	public @NonNull EcoreFlatClass getEcoreFlatClass(@NonNull EcoreReflectiveType type) {
		EClassifier eClassifier = type.getEClassifier();
		assert eClassifier != null;
		EcoreFlatClass flatClass = eClassifier2flatClass.get(eClassifier);
		if (flatClass == null) {
			flatClass = new EcoreFlatClass(this, eClassifier, type);
			eClassifier2flatClass.put(eClassifier, flatClass);
		}
		return flatClass;
	}
}
