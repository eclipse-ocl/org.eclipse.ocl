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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;

/**
 * An EObjectsDescriptor describes a type for a collection of (unboxed) EObject representations. It has a pivot CollectionTypeId describing
 * both collection and elements, a Java class name for the elements and an EClassifier for the elements.
 * <br>
 * Note that in EMF, that Java class for all collections is java.util.List.
 * <p>
 * This descriptor is used whenever the Java classes actually exist.
 */
public class EObjectsDescriptor extends AbstractCollectionDescriptor implements EcoreDescriptor, UnboxedDescriptor
{
	protected final @NonNull EClassifier eClassifier;
	protected final @NonNull Class<?> javaClass;
	protected final @NonNull Class<?> oldJavaClass;

	public EObjectsDescriptor(@NonNull CollectionTypeId collectionTypeId, @NonNull EClassifier eClassifier, @NonNull Class<?> javaClass) {
		super(collectionTypeId);
		this.eClassifier = eClassifier;
		this.javaClass = javaClass;
		this.oldJavaClass = reClass(javaClass);
	}

	@Override
	public void append(@NonNull JavaStream javaStream, @Nullable Boolean isRequired) {
		javaStream.appendClassReference(isRequired, List.class, javaClass != oldJavaClass, oldJavaClass);
	}

	@Override
	public void append(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.appendClassReference(null, List.class, javaClass != oldJavaClass, reClass ? javaClass : oldJavaClass);
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.appendClassReference(null, reClass ? javaClass : oldJavaClass);
	}

	@Override
	public @NonNull String getClassName() {
		return javaClass.getName();
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return this;
	}

	@Override
	public @NonNull Class<?> getJavaClass() {
		return javaClass;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		return this;
	}

	@Override
	public @Nullable Class<?> hasJavaClass() {
		return javaClass;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (!(typeDescriptor instanceof EObjectsDescriptor)) {
			return false;
		}
		return javaClass.isAssignableFrom(typeDescriptor.getJavaClass());
	}
}