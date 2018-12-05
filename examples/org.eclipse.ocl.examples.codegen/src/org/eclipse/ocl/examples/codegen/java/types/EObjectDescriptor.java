/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * An EObjectDescriptor describes a type gor an (unboxed) EObjecte. It has a pivot ElementId, a Java class name and an EClassifier.
 * <p>
 * This descriptor is appropriate for most Ecore types.
 */
public class EObjectDescriptor extends SimpleValueDescriptor implements SimpleDescriptor
{
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull Class<?> originalJavaClass;
	
	public EObjectDescriptor(@NonNull ElementId elementId, @NonNull EClassifier eClassifier, @NonNull Class<?> javaClass) {
		super(elementId, reClass(javaClass));
		this.eClassifier = eClassifier;
		this.originalJavaClass = javaClass;
	}

	@Override
	public @NonNull EClassifier getEClassifier() {
		return eClassifier;
	}

	public @NonNull Class<?> getOriginalJavaClass() {
		return originalJavaClass;
	}
}