/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A UnboxedElementsDescriptor describes a collection type for which no Java class may eveer exist. It has a pivot CollectionTypeId, and
 * a stamdardLibrary and the pivot type.
 * <p>
 * Theis descriptor is used in JUnit tests for expressions and when the genModel is unknown.
 */
public class UnboxedMapDescriptor extends /*AbstractCollectionDescriptor*/AbstractDescriptor implements EcoreDescriptor, UnboxedDescriptor, MapDescriptor
{
	protected final @NonNull StandardLibrary standardLibrary;
	protected final @NonNull Type keyType;
	protected final @NonNull Type valueType;
	
	public UnboxedMapDescriptor(@NonNull MapTypeId mapTypeId, @NonNull StandardLibrary standardLibrary, @NonNull Type keyType, @NonNull Type valueType) {
		super(mapTypeId);
		this.standardLibrary = standardLibrary;
		this.keyType = keyType;
		this.valueType = valueType;
	}

	@Override
	public void append(@NonNull JavaStream javaStream, @Nullable Boolean isRequired) {
		javaStream.appendClassReference(List.class, true, Object.class);
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.appendClassReference(getJavaClass());
	}

	@Override
	public @NonNull String getClassName() {
		return ClassUtil.nonNullModel(keyType.getName());
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return this;
	}

	@Override
	public @NonNull MapTypeId getElementId() {
		return (MapTypeId) elementId;
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
		if (!(typeDescriptor instanceof UnboxedMapDescriptor)) {
			return false;
		}
		Type thatType = ((UnboxedMapDescriptor)typeDescriptor).keyType;
		return thatType.conformsTo(standardLibrary, keyType);
	}

	@Override
	public @NonNull String toString() {
		return elementId + " => Map<Object/*" + keyType.getName() + ",Object/*" + valueType.getName() + "*/>";
	}

	@Override
	public void append(@NonNull JavaStream javaStream, boolean reClass) {
		// TODO Auto-generated method stub
		
	}
}