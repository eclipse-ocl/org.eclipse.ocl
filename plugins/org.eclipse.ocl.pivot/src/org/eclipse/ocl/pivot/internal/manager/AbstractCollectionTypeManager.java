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
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;

/**
 * CollectionTypeManagerInternal encapsulates the knowledge about known collection types.
 *
 * @since 7.0
 */
public abstract class AbstractCollectionTypeManager implements CollectionTypeManager
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
	private @NonNull /*WeakHash*/Map<@NonNull CollectionTypeArguments, @NonNull WeakReference<@Nullable CollectionType>> collectionTypes = new /*Weak*/HashMap<>();		// Keys are not singletons;

	protected AbstractCollectionTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	@Override
	public @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		synchronized (collectionTypes) {
			WeakReference<@Nullable CollectionType> weakReference = collectionTypes.get(typeArguments);
			if (weakReference != null) {
				CollectionType specializedType = weakReference.get();
				if (specializedType != null) {
					Type elementType = specializedType.getElementType();
					if (isValid(elementType)) {		// If no GC pending
						return specializedType;
					}
					weakReference.clear();
				}
				collectionTypes.remove(typeArguments);
			}
			return null;
		}
	}

	protected abstract @NonNull CollectionType createCollectionType(@NonNull CollectionTypeArguments typeArguments);

	public void dispose() {
		collectionTypes.clear();
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		synchronized (collectionTypes) {
			WeakReference<@Nullable CollectionType> weakReference = collectionTypes.get(typeArguments);
			if (weakReference != null) {
				CollectionType specializedType = weakReference.get();
				if (specializedType != null) {
					Type elementType = specializedType.getElementType();
					if (isValid(elementType)) {		// If no GC pending
						return specializedType;
					}
					weakReference.clear();
				}
				collectionTypes.remove(typeArguments);
			}
			CollectionType specializedType = createCollectionType(typeArguments);
			collectionTypes.put(typeArguments, new WeakReference<@Nullable CollectionType>(specializedType));
			return specializedType;
		}
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		assert collectionTypeId.getGeneralizedId() == collectionTypeId;
		if (collectionTypeId == TypeId.BAG) {
			return standardLibrary.getBagType();
		}
		else if (collectionTypeId == TypeId.COLLECTION) {
			return standardLibrary.getCollectionType();
		}
		else if (collectionTypeId == TypeId.ORDERED_COLLECTION) {
			return standardLibrary.getOrderedCollectionType();
		}
		else if (collectionTypeId == TypeId.ORDERED_SET) {
			return standardLibrary.getOrderedSetType();
		}
		else if (collectionTypeId == TypeId.SEQUENCE) {
			return standardLibrary.getSequenceType();
		}
		else if (collectionTypeId == TypeId.SET) {
			return standardLibrary.getSetType();
		}
		else if (collectionTypeId == TypeId.UNIQUE_COLLECTION) {
			return standardLibrary.getUniqueCollectionType();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	protected abstract boolean isValid(@Nullable Type type);
}