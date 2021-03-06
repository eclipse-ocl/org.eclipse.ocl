/*******************************************************************************
 * Copyright (c) 2013, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * An EcoreListDescriptor describes a collection type represented as an Ecore List.
 */
public class EcoreListDescriptor extends AbstractCollectionDescriptor implements EcoreDescriptor, UnboxedDescriptor
{
	protected final @NonNull StandardLibrary standardLibrary;
	protected final @NonNull Type elementType;
	protected final @NonNull EcoreDescriptor ecoreElementTypeDescriptor;

	public EcoreListDescriptor(@NonNull CollectionTypeId collectionTypeId, @NonNull StandardLibrary standardLibrary, @NonNull Type elementType, @NonNull EcoreDescriptor ecoreElementTypeDescriptor) {
		super(collectionTypeId);
		this.standardLibrary = standardLibrary;
		this.elementType = elementType;
		this.ecoreElementTypeDescriptor = ecoreElementTypeDescriptor;
	}

	@Override
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.appendClassReference(isRequired, /*E*/List.class, false, ecoreElementTypeDescriptor);
	}

	@Override
	public void appendEcoreValue(@NonNull JavaStream js, @NonNull String requiredClassName, @NonNull CGValuedElement cgValue) {
		boolean suppressEMFTypes = true;
		GenPackage genPackage = js.getGenPackage();
		if (genPackage != null) {
			GenModel genModel = genPackage.getGenModel();
			if (genModel != null) {
				suppressEMFTypes = genModel.isSuppressEMFTypes();
			}
		}
		Class<?> listClass = suppressEMFTypes ? List.class : EList.class;
		if (requiredClassName.startsWith(listClass.getName())) {
			js.append("(");
			js.appendClassReference(null, listClass, false, ecoreElementTypeDescriptor);
			js.append(")");
		}
		js.appendValueName(cgValue);
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.appendClassReference(null, getJavaClass());
	}

	@Override
	public @NonNull String getClassName() {
		return ClassUtil.nonNullModel(elementType.getName());
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return this;
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
		if (!(typeDescriptor instanceof EcoreListDescriptor)) {
			return false;
		}
		Type thatType = ((EcoreListDescriptor)typeDescriptor).elementType;
		return thatType.conformsTo(standardLibrary, elementType);
	}

	@Override
	public @NonNull String toString() {
		return elementId + " => EList<" + elementType.getName() + ">";
	}
}