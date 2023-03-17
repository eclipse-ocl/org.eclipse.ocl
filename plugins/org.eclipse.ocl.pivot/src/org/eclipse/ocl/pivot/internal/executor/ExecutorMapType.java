/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.TypeUtil;

/**
 * @since 1.18
 */
public class ExecutorMapType extends AbstractExecutorClass implements MapType
{
	protected final org.eclipse.ocl.pivot.@NonNull Class containerType;
	protected final @NonNull Type keyType;
	/**
	 * @since 1.18
	 */
	protected final boolean keyValuesAreNullFree;
	protected final @NonNull Type valueType;
	/**
	 * @since 1.18
	 */
	protected final boolean valuesAreNullFree;
	protected final @NonNull MapTypeId typeId;

	@Deprecated
	public ExecutorMapType(@NonNull String name,
			org.eclipse.ocl.pivot.@NonNull Class containerType, @NonNull Type keyType, @NonNull Type valueType) {
		this(name, containerType, keyType, false, valueType, false);
	}

	/**
	 * @since 1.18
	 */
	public ExecutorMapType(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class containerType,
			@NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		super(name, 0);
		this.containerType = containerType;
		this.keyType = keyType;
		this.keyValuesAreNullFree = keyValuesAreNullFree;
		this.valueType = valueType;
		this.valuesAreNullFree = valuesAreNullFree;
		this.typeId = IdManager.getMapTypeId(name).getSpecializedId(keyType.getTypeId(), valueType.getTypeId(), keyValuesAreNullFree, valuesAreNullFree);
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		if (this == type) {
			return true;
		}
		if (!(type instanceof MapType)) {
			return false;
		}
		return TypeUtil.conformsToMapType(standardLibrary, this, (MapType)type);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonType(@NonNull IdResolver idResolver, @NonNull Type type) {
		StandardLibrary standardLibrary = idResolver.getStandardLibrary();
		if (!(type instanceof ExecutorMapType)) {
			return standardLibrary.getOclAnyType();
		}
		ExecutorMapType thatClass = (ExecutorMapType) type;
		// FIXME kind
		org.eclipse.ocl.pivot.Class commonContainerClass = containerType;		// FIXME WIP
		Type commonKeyClass = keyType.getCommonType(idResolver, thatClass.getKeyType());
		Type commonValueClass = valueType.getCommonType(idResolver, thatClass.getValueType());
		if ((commonContainerClass == containerType) && (commonKeyClass == keyType) && (commonValueClass == valueType)) {
			return this;
		}
		else if ((commonContainerClass == thatClass.containerType) && (commonKeyClass == keyType) && (commonValueClass == valueType)) {
			return thatClass;
		}
		else {
			return standardLibrary.getMapType(standardLibrary.getMapType(), commonKeyClass, commonValueClass);
		}
	}

	@Override
	public @NonNull MapType getContainerType() {
		return (MapType)containerType;
	}

	@Override
	public Class getEntryClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Type getKeyType() {
		return keyType;
	}

	@Override
	public @NonNull MapTypeId getTypeId() {
		return typeId;
	}

	@Override
	public TemplateableElement getUnspecializedElement() {
		return containerType;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public String getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Type getValueType() {
		return valueType;
	}

	@Override
	public boolean isEqualTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		if (this == type) {
			return true;
		}
		if (!(type instanceof MapType)) {
			return false;
		}
		return TypeUtil.isEqualToMapType(standardLibrary, this, (MapType)type);
	}

	@Override
	public boolean isKeysAreNullFree() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOrdered() {
		return containerType.isOrdered();
	}

	@Override
	public boolean isUnique() {
		return containerType.isUnique();
	}

	@Override
	public boolean isValuesAreNullFree() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return containerType.lookupActualOperation(standardLibrary, apparentOperation);
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return containerType.lookupImplementation(standardLibrary, apparentOperation);
	}

	@Override
	public void setEntryClass(Class value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setKeyType(Type value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setKeysAreNullFree(boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValueType(Type value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValuesAreNullFree(boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return String.valueOf(containerType) + "(" + String.valueOf(keyType) + "," + String.valueOf(valueType) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}