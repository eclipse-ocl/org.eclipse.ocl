/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class SerializationUtils
{
	public static @NonNull EClass getEContainingClass(@NonNull EStructuralFeature eFeature) {
		return ClassUtil.nonNullState(eFeature.getEContainingClass());
	}

	public static @NonNull String getName(@NonNull ENamedElement eNamedElement) {
		return ClassUtil.nonNullState(eNamedElement.getName());
	}
}
