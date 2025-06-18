/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.executor;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.MapTypeId;

/**
 * @since 1.18
 */
public class ExecutorMapType extends AbstractSpecializedType implements MapType
{
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
	private org.eclipse.ocl.pivot.Class entryClass = null;

	/**
	 * @since 1.18
	 */
	public ExecutorMapType(@NonNull String name, org.eclipse.ocl.pivot.@NonNull Class containerType,
			@NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		super(name, containerType);
		this.keyType = keyType;
		this.keyValuesAreNullFree = keyValuesAreNullFree;
		this.valueType = valueType;
		this.valuesAreNullFree = valuesAreNullFree;
		this.typeId = IdManager.getMapTypeId(name).getSpecializedId(keyType.getTypeId(), valueType.getTypeId(), keyValuesAreNullFree, valuesAreNullFree);
	}

	@Override
	public @NonNull MapType getContainerType() {
		return (MapType)containerType;
	}

	@Override
	public org.eclipse.ocl.pivot.Class getEntryClass() {
		return entryClass;
	}

	@Override
	public @NonNull Type getKeyType() {
		return keyType;
	}

	//	@Override
	//	public @NonNull String getMetaTypeName() {
	//		return getTypeId().getCollectionTypeId().getMetaTypeName();
	//	}

	@Override
	public @NonNull List<Operation> getOwnedOperations() {
		return containerType.getOwnedOperations();
	}

	@Override
	public @NonNull MapTypeId getTypeId() {
		return typeId;
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
	public boolean isKeysAreNullFree() {
		return keyValuesAreNullFree;
	}

	@Override
	public boolean isValuesAreNullFree() {
		return valuesAreNullFree;
	}

	@Override
	public void setEntryClass(org.eclipse.ocl.pivot.Class entryClass) {
		this.entryClass = entryClass;
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
		StringBuilder s = new StringBuilder();
		s.append(containerType);
		s.append("(");
		s.append(keyType);
		if (isKeysAreNullFree()) {
			s.append("[1]");
		}
		s.append(",");
		s.append(valueType);
		if (isValuesAreNullFree()) {
			s.append("[1]");
		}
		s.append(")");
		return s.toString();
	}
}