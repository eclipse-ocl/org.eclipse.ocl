/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;

/**
 * A FutureEObjectsDescriptor describes a yet-to-be-created collection type. It has a pivot CollectionTypeId, and EClassifier and a Java class name.
 * <p>
 * The Java class is only known by name; it is not yet loadable, since genmodel has not yet generated it.
 * <p>
 * There is no EClassifier available to perform type conformance checks since the Java class name was provided as an instanceClassName.
 */
public class FutureEObjectsDescriptor extends AbstractCollectionDescriptor implements EcoreDescriptor, UnboxedDescriptor
{
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull String className;

	public FutureEObjectsDescriptor(@NonNull CollectionTypeId collectionTypeId, @NonNull EClassifier eClassifier, @NonNull String className) {
		super(collectionTypeId);
		this.eClassifier = eClassifier;
		this.className = className;
	}

	@Override
	public void append(@NonNull JavaStream javaStream, @Nullable Boolean isRequired) {
		javaStream.appendClassReference(isRequired, List.class, false, className);
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return this;
	}

	@Override
	public @NonNull String getClassName() {
		return className;
	}

	@Override
	public @NonNull Class<?> getJavaClass() {
		return NamedFuture.class;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		return this;
	}

	@Override
	public @Nullable Class<?> hasJavaClass() {
		return null;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (!(typeDescriptor instanceof FutureEObjectsDescriptor)) {
			return false;
		}
		EClassifier thatEClassifier = ((FutureEObjectsDescriptor)typeDescriptor).eClassifier;
		if (eClassifier == thatEClassifier) {
			return true;
		}
		if (!(eClassifier instanceof EClass) || !(thatEClassifier instanceof EClass)) {
			return false;
		}
		return ((EClass)eClassifier).isSuperTypeOf((EClass)thatEClassifier);
	}
}