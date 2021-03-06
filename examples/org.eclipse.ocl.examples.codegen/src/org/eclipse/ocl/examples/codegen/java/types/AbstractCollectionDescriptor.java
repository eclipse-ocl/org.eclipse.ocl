/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.TypeRepresentation;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * An AbstractCollectionDescriptor extends an AbstractDescriptor to describe a collection type.
 */
public abstract class AbstractCollectionDescriptor extends AbstractDescriptor implements CollectionDescriptor
{
	public AbstractCollectionDescriptor(@NonNull CollectionTypeId collectionTypeId) {
		super(collectionTypeId);
	}

	@Override
	public void append(@NonNull JavaStream javaStream, boolean reClass) {
		append(javaStream, null);
	}

	@Override
	public @NonNull Boolean appendBox(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGBoxExp cgBoxExp, @NonNull CGValuedElement unboxedValue) {
		TypeId typeId = unboxedValue.getASTypeId();
		js.appendDeclaration(cgBoxExp);
		js.append(" = ");
		if (!unboxedValue.isNonNull()) {
			js.appendReferenceTo(unboxedValue);
			js.append(" == null ? null : ");
		}
		@NonNull String collectionName = "Collection";
		if (typeId instanceof CollectionTypeId) {
			collectionName = ((CollectionTypeId)typeId).getGeneralizedId().getName();
		}
		js.appendReferenceTo(localContext.getIdResolverVariable(cgBoxExp));
		js.append(".create" + collectionName + "OfAll(");
		js.appendIdReference(typeId);
		js.append(", ");
		js.appendReferenceTo(Iterable.class, unboxedValue);
		js.append(");\n");
		return true;
	}

	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement nonEcoreValue) {
		//		TypeId typeId = nonEcoreValue.getASTypeId();
		EClassifier eClassifier = cgEcoreExp.getEcoreClassifier();
		Class<?> instanceClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
		EcoreDescriptor elementEcoreDescriptor = js.getCodeGenerator().getEcoreDescriptor(getElementId().getElementTypeId(), instanceClass);
		TypeRepresentation ecoreTypeRepresentation = js.getEcoreTypeRepresentation(instanceClass);
		ecoreTypeRepresentation.appendDeclaration(cgEcoreExp);
		js.append(" = ");
		if (!nonEcoreValue.isNonNull()) {
			js.appendReferenceTo(nonEcoreValue);
			js.append(" == null ? null : ");
		}
		js.appendAtomicReferenceTo(IdResolver.IdResolverExtension.class, localContext.getIdResolverVariable(cgEcoreExp));
		js.append(".ecoreValueOfAll(");
		//		js.appendIdReference(typeId);
		js.appendClassReference(null, elementEcoreDescriptor);
		js.append(".class, ");
		js.appendReferenceTo(Iterable.class, nonEcoreValue);
		js.append(")");
		js.append(";\n");
		return true;
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.append(getClassName());
	}

	@Override
	public @Nullable CollectionDescriptor asCollectionDescriptor() {
		return this;
	}

	@Override
	public @NonNull CollectionTypeId getElementId() {
		return (CollectionTypeId)super.getElementId();
	}

	@Override
	public boolean isAssignableTo(@NonNull Class<?> javaClass) {
		return javaClass.isAssignableFrom(List.class);
	}

	@Override
	public @NonNull String toString() {
		return elementId + " => List<" + getClassName() + ">";
	}
}