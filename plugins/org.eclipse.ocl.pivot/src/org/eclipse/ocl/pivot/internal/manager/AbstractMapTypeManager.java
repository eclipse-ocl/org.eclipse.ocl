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
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapTypeArguments;

/**
 * MapTypeManagerInternal encapsulates the knowledge about known map types.
 *
 * @since 7.0
 */
public abstract class AbstractMapTypeManager implements MapTypeManager
{
	protected final @NonNull MapType genericMapType;

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
	private @NonNull /*WeakHash*/Map<@NonNull MapTypeArguments, @NonNull WeakReference<@Nullable MapType>> mapTypes = new /*Weak*/HashMap<>();		// Keys are not singletons;

	private @Nullable Map<@NonNull MapType, @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull MapType>> mapType2entryClass2mapEntryType = null;

	protected AbstractMapTypeManager(@NonNull MapType genericMapType) {
		this.genericMapType = genericMapType;
	}

	@Override
	public @Nullable MapType basicGetMapType(@NonNull MapTypeArguments typeArguments) {
		synchronized (mapTypes) {
			WeakReference<@Nullable MapType> weakReference = mapTypes.get(typeArguments);
			if (weakReference != null) {
				MapType specializedType = weakReference.get();
				if (specializedType != null) {
					Type keyType = specializedType.getKeyType();
					Type valueType = specializedType.getValueType();
					if (isValid(keyType) && isValid(valueType)) {		// If no GC pending
						return specializedType;
					}
					weakReference.clear();
				}
				mapTypes.remove(typeArguments);
			}
			return null;
		}
	}

	protected abstract @NonNull MapType createMapType(@NonNull MapTypeArguments typeArguments, org.eclipse.ocl.pivot.@Nullable Class entryClass);

	public void dispose() {
		mapTypes.clear();
	}

	@Override
	public @NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		assert !entryClass.eIsProxy();
		synchronized (mapTypes) {
			MapTypeArguments typeArguments = PivotUtil.createMapTypeArguments(entryClass);
			MapType mapType = getMapType(typeArguments);
			Map<@NonNull MapType, @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull MapType>> mapType2entryClass2mapEntryType2 = mapType2entryClass2mapEntryType;
			if (mapType2entryClass2mapEntryType2 == null) {
				mapType2entryClass2mapEntryType = mapType2entryClass2mapEntryType2 = new HashMap<>();
			}
			Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull MapType> entryClass2mapEntryType = mapType2entryClass2mapEntryType2.get(mapType);
			if (entryClass2mapEntryType == null) {
				entryClass2mapEntryType = new HashMap<>();
				mapType2entryClass2mapEntryType2.put(mapType, entryClass2mapEntryType);
			}
			MapType mapEntryType = entryClass2mapEntryType.get(entryClass);
			if (mapEntryType == null) {
				mapEntryType = createMapType(typeArguments, entryClass);
				entryClass2mapEntryType.put(entryClass, mapEntryType);
			}
			return mapEntryType;
		}
	}

	@Override
	public @NonNull MapType getMapType(@NonNull MapTypeArguments typeArguments) {
		synchronized (mapTypes) {
			WeakReference<@Nullable MapType> weakReference = mapTypes.get(typeArguments);
			if (weakReference != null) {
				MapType specializedType = weakReference.get();
				if (specializedType != null) {
					Type keyType = specializedType.getKeyType();
					Type valueType = specializedType.getValueType();
					if (isValid(keyType) && isValid(valueType)) {		// If no GC pending
						return specializedType;
					}
					weakReference.clear();
				}
				mapTypes.remove(typeArguments);
			}
			MapType specializedType = createMapType(typeArguments, null);
			mapTypes.put(typeArguments, new WeakReference<@Nullable MapType>(specializedType));
			return specializedType;
		}
	}

	protected abstract boolean isValid(@Nullable Type type);
}