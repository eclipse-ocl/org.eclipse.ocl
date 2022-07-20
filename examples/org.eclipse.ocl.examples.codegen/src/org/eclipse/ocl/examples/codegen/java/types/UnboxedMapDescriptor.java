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

import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
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
	public void append(@NonNull JavaStream js, @Nullable Boolean isRequired) {
		js.appendClassReference(isRequired, EMap.class, true, Object.class, Object.class);
	}

	@Override
	public @NonNull Boolean appendBox(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGBoxExp cgBoxExp, @NonNull CGValuedElement unboxedValue) {
		TypeId typeId = unboxedValue.getASTypeId();
		MapTypeId mapTypeId = typeId instanceof MapTypeId ? (MapTypeId)typeId : null;
		if (js.getCodeGenerator().isRequired(cgBoxExp) == Boolean.TRUE) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgBoxExp);
		js.append(" = ");
		if (!unboxedValue.isNonNull()) {
			js.appendReferenceTo(unboxedValue);
			js.append(" == null ? null : ");
		}
		js.appendReferenceTo(localContext.getIdResolverVariable(cgBoxExp));
		js.append(".createMapOfAll(");
		js.appendIdReference(mapTypeId);// != null ? mapTypeId.getKeyTypeId() : null);
		js.append(", ");
	//	js.appendBooleanString((mapTypeId != null) && mapTypeId.isKeysAreNullFree());
	//	js.append(", ");
	//	js.appendIdReference(mapTypeId != null ? mapTypeId.getValueTypeId() : null);
	//	js.append(", ");
	//	js.appendBooleanString((mapTypeId != null) && mapTypeId.isValuesAreNullFree());
	//	js.append(", ");
		js.appendAtomicReferenceTo(true, EMap.class, true, unboxedValue, Object.class, Object.class);
		js.append(".map());\n");
		return true;
	}

	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {
		// FIXME It seems unlikely that we should ever want to create an EMap. Rather we might want to unbox a MapValue into an existing EMap.
		// return appendEcoreLegacy(js, localContext, cgEcoreExp, unboxedValue);
		throw new UnsupportedOperationException("UnboxedMapDescriptor::appendEcore");
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		javaStream.appendClassReference(null, getJavaClass());
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
	public @NonNull Class<?> getJavaClass() {
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
	public boolean isAssignableTo(@NonNull Class<?> javaClass) {
		return javaClass.isAssignableFrom(Set.class);
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