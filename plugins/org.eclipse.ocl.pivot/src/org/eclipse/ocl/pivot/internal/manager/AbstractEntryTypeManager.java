/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.EntryType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.manager.EntryTypeManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateArguments;

/**
 * AbstractEntryTypeManager encapsulates the knowledge about known entry types.
 *
 * @since 7.0
 */
public abstract class AbstractEntryTypeManager implements EntryTypeManager
{
	protected final @NonNull StandardLibrary standardLibrary;

	/**
	 * Map from actual types to specialization.
	 * <br>
	 * The specializations are weakly referenced so that stale specializations are garbage collected.
	 */
	// FIXME tests fail if keys are weak since GC is too aggressive across tests
	// The actual types are weak keys so that parameterizations using stale types are garbage collected.
	// No. The problem is that MapTypeParameters is not a singleton since it passes key/value types. Attempting to use
	// a SingletonScope needs to use the IdResolver to convert the TemplateParameterId to its type which seemed reluctant
	// to work, and failing to GC within the scope of this CompleteClass is not a disaster. May change once CompleteClass goes.
	//
	private @NonNull /*WeakHash*/Map<@NonNull MapTypeArguments, @NonNull WeakReference<@Nullable EntryType>> entryTypes = new /*Weak*/HashMap<>();		// Keys are not singletons;

	private @Nullable Map<@NonNull EntryType, @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull EntryType>> entryType2entryClass2entryEntryType = null;

	protected AbstractEntryTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	@Override
	public @Nullable EntryType basicGetEntryType(@NonNull MapTypeArguments typeArguments) {
		synchronized (entryTypes) {
			WeakReference<@Nullable EntryType> weakReference = entryTypes.get(typeArguments);
			if (weakReference != null) {
				EntryType specializedType = weakReference.get();
				if (specializedType != null) {
					Type keyType = specializedType.getKeyType();
					Type valueType = specializedType.getValueType();
					if (isValid(keyType) && isValid(valueType)) {		// If no GC pending
						return specializedType;
					}
					weakReference.clear();
				}
				entryTypes.remove(typeArguments);
			}
			return null;
		}
	}

	/**
	 * @since 7.0
	 */
	@Override
	public boolean conformsToEntryType(@NonNull EntryType leftType, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull EntryType rightType, @Nullable TemplateArguments rightTemplateArguments, boolean enforceNullity) {
		Type leftKeyType = PivotUtil.getKeyType(leftType);
		Type rightKeyType = PivotUtil.getKeyType(rightType);
		if (enforceNullity) {
			boolean leftKeysAreNullFree =  leftType.isKeysAreNullFree();
			boolean rightKeysAreNullFree = rightType.isKeysAreNullFree();
			if (!standardLibrary.conformsTo(leftKeyType, leftKeysAreNullFree, leftTemplateArguments, rightKeyType, rightKeysAreNullFree, rightTemplateArguments)) {
				return false;
			}
		}
		else {
			if (!standardLibrary.conformsTo(leftKeyType, leftTemplateArguments, rightKeyType, rightTemplateArguments, false)) {
				return false;
			}
		}
		Type leftValueType = PivotUtil.getValueType(leftType);
		Type rightValueType = PivotUtil.getValueType(rightType);
		if (enforceNullity) {
			boolean leftValuesAreNullFree = leftType.isValuesAreNullFree();
			boolean rightValuesAreNullFree = rightType.isValuesAreNullFree();
			return standardLibrary.conformsTo(leftValueType, leftValuesAreNullFree, leftTemplateArguments, rightValueType, rightValuesAreNullFree, rightTemplateArguments);
		}
		else {
			return standardLibrary.conformsTo(leftValueType, leftTemplateArguments, rightValueType, rightTemplateArguments, false);
		}
	}

	protected @NonNull EntryType createEntryType(@NonNull MapTypeArguments typeArguments) {
		Type keyType = typeArguments.getKeyType();
		Type primaryKeyType = standardLibrary.getPrimaryType(keyType);
		boolean keysAreNullFree = typeArguments.isKeysAreNullFree();
		Type valueType = typeArguments.getValueType();
		Type primaryValueType = standardLibrary.getPrimaryType(valueType);
		boolean valuesAreNullFree = typeArguments.isValuesAreNullFree();
		EntryType genericEntryType = standardLibrary.getEntryType();
		EntryType entryType = PivotUtil.createEntryType(genericEntryType, primaryKeyType, keysAreNullFree, primaryValueType, valuesAreNullFree);
		standardLibrary.resolveSuperClasses(entryType, genericEntryType);
		standardLibrary.addOrphanClass(entryType);
		return entryType;
	}

	@Override
	public void dispose() {
		entryTypes.clear();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull EntryType getCommonEntryType(@NonNull EntryType leftEntryType, @Nullable TemplateArguments leftTemplateArguments,
				@NonNull EntryType rightEntryType, @Nullable TemplateArguments rightTemplateArguments) {
		Type leftKeyType = PivotUtil.getKeyType(leftEntryType);
		Type rightKeyType = PivotUtil.getKeyType(rightEntryType);
		Type commonKeyType = standardLibrary.getCommonType(leftKeyType, leftTemplateArguments, rightKeyType, rightTemplateArguments);
		Type leftValueType = PivotUtil.getValueType(leftEntryType);
		Type rightValueType = PivotUtil.getValueType(rightEntryType);
		Type commonValueType = standardLibrary.getCommonType(leftValueType, leftTemplateArguments, rightValueType, rightTemplateArguments);
		boolean leftKeysAreNullFree = leftEntryType.isKeysAreNullFree();
		boolean rightKeysAreNullFree = rightEntryType.isKeysAreNullFree();
		boolean commonKeysAreNullFree = standardLibrary.getCommonIsRequired(leftKeysAreNullFree, rightKeysAreNullFree);
		boolean leftValuesAreNullFree = leftEntryType.isValuesAreNullFree();
		boolean rightValuesAreNullFree = rightEntryType.isValuesAreNullFree();
		boolean commonValuesAreNullFree = standardLibrary.getCommonIsRequired(leftValuesAreNullFree, rightValuesAreNullFree);
		if ((commonKeyType == leftKeyType) && (commonValueType == leftValueType) && (commonKeysAreNullFree == leftKeysAreNullFree) && (commonValuesAreNullFree == leftValuesAreNullFree)) {
			return leftEntryType;
		}
		if ((commonKeyType == rightKeyType) && (commonValueType == rightValueType) && (commonKeysAreNullFree == rightKeysAreNullFree) && (commonValuesAreNullFree == rightValuesAreNullFree)) {
			return rightEntryType;
		}
		MapTypeArguments typeArguments = new MapTypeArguments(commonKeyType, commonKeysAreNullFree, commonValueType, commonValuesAreNullFree);
		return getEntryType(typeArguments);
	}

	@Override
	public @NonNull EntryType getEntryType(@NonNull MapTypeArguments typeArguments) {
		synchronized (entryTypes) {
			WeakReference<@Nullable EntryType> weakReference = entryTypes.get(typeArguments);
			if (weakReference != null) {
				EntryType specializedType = weakReference.get();
				if (specializedType != null) {
					Type keyType = specializedType.getKeyType();
					Type valueType = specializedType.getValueType();
					if (isValid(keyType) && isValid(valueType)) {		// If no GC pending
						return specializedType;
					}
					weakReference.clear();
				}
				entryTypes.remove(typeArguments);
			}
			EntryType specializedType = createEntryType(typeArguments);
			entryTypes.put(typeArguments, new WeakReference<@Nullable EntryType>(specializedType));
			return specializedType;
		}
	}

	@Override
	public boolean isEqualToEntryType(@NonNull EntryType leftEntryType, @NonNull EntryType rightEntryType) {
		Type leftKeyType = leftEntryType.getKeyType();
		Type rightKeyType = rightEntryType.getKeyType();
		if (leftKeyType != rightKeyType) {
			if ((leftKeyType == null) || (rightKeyType == null)) {
				return false;
			}
			if (!standardLibrary.isEqualTo(leftKeyType, rightKeyType)) {
				return false;
			}
		}
		Type leftValueType = leftEntryType.getValueType();
		Type rightValueType = rightEntryType.getValueType();
		if (leftValueType != rightValueType) {
			if ((leftValueType == null) || (rightValueType == null)) {
				return false;
			}
			if (!standardLibrary.isEqualTo(leftValueType, rightValueType)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isValid(@Nullable Type type) {
		return type != null;
	}

	@Override
	public void load(@NonNull EntryType asEntryType) {
		Type keyType = PivotUtil.getKeyType(asEntryType);
		Type valueType = PivotUtil.getValueType(asEntryType);
		boolean keysAreNullFree = asEntryType.isKeysAreNullFree();
		boolean valuesAreNullFree = asEntryType.isValuesAreNullFree();
		MapTypeArguments entryTypeArguments = new MapTypeArguments(keyType, keysAreNullFree, valueType, valuesAreNullFree);
		WeakReference<@Nullable EntryType> ref = entryTypes.get(entryTypeArguments);
		EntryType old = ref != null ? ref.get() : null;
		if (old == null) {
			entryTypes.put(entryTypeArguments, new WeakReference<>(asEntryType));
		}
		else {
			assert old == asEntryType;
		}
	}
}