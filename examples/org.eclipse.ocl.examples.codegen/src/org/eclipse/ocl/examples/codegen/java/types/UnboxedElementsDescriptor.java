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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A UnboxedElementsDescriptor describes a collection type for which no Java class may ever exist. It has a pivot CollectionTypeId, and
 * a standardLibrary and the pivot type.
 * <p>
 * This descriptor is used in JUnit tests for expressions and when the genModel is unknown.
 */
public class UnboxedElementsDescriptor extends AbstractCollectionDescriptor implements UnboxedDescriptor
{
	protected final @NonNull StandardLibrary standardLibrary;
	protected final @NonNull Type type;

	public UnboxedElementsDescriptor(@NonNull CollectionTypeId collectionTypeId, @NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		super(collectionTypeId);
		this.standardLibrary = standardLibrary;
		this.type = type;
	}

	@Override
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.appendClassReference(isRequired, List.class, true, Object.class);
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.appendClassReference(null, getJavaClass());
	}

	@Override
	public @NonNull String getClassName() {
		return ClassUtil.nonNullModel(type.getName());
	}

	@Override
	@NonNull
	public Class<?> getJavaClass() {
		return Object.class;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		return this;
	}

	@Override
	@Nullable
	public Class<?> hasJavaClass() {
		return null;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (!(typeDescriptor instanceof UnboxedElementsDescriptor)) {
			return false;
		}
		Type thatType = ((UnboxedElementsDescriptor)typeDescriptor).type;
		return thatType.conformsTo(standardLibrary, type);
	}

	@Override
	public @NonNull String toString() {
		return elementId + " => List<Object/*" + type.getName() + "*/>";
	}
}