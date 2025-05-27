/*******************************************************************************
 * Copyright (c) 2015, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A MapTypeId provides a unique identifier for an unspecialized map type such as Map(K,V).
 */
public interface MapTypeId extends BuiltInTypeId, TemplateableId
{
	@Override
	@NonNull MapTypeId getGeneralizedId();
	@NonNull TypeId getKeyTypeId();
	@Override
	@NonNull String getMetaclassName();
	@Override
	@NonNull MapTypeId getSpecializedId(@NonNull BindingsId bindingsId);

	/**
	 * @since 1.18
	 */
	default @NonNull MapTypeId getSpecializedId(@NonNull ElementId keyTypeId, @NonNull ElementId valueTypeId, boolean keysAreNullFree, boolean valuesAreNullFree) {
		return getSpecializedId(IdManager.getBindingsId(new @NonNull ElementId[] {keyTypeId, valueTypeId}, new @NonNull Object[] {keysAreNullFree, valuesAreNullFree}));
	}

	/**
	 * @since 7.0
	 */
	default @NonNull MapTypeId getSpecializedId(@NonNull TypeId entryTypeId, @NonNull ElementId keyTypeId, @NonNull ElementId valueTypeId, boolean keysAreNullFree, boolean valuesAreNullFree) {
		return getSpecializedId(IdManager.getBindingsId(new @NonNull ElementId[] {keyTypeId, valueTypeId}, new @NonNull Object[] {entryTypeId, keysAreNullFree, valuesAreNullFree}));
	}

	@NonNull TypeId getValueTypeId();
	/**
	 * @since 1.18
	 */
	boolean isKeysAreNullFree();
	/**
	 * @since 1.18
	 */
	boolean isValuesAreNullFree();
}
