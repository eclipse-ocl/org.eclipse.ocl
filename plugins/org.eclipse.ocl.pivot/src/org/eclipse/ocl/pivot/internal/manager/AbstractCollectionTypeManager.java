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
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

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
					Type elementType = PivotUtil.getElementType(specializedType);
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

	@Override
	public boolean conformsToCollectionType(@NonNull CollectionType leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull CollectionType rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions, boolean enforceNullity) {
		org.eclipse.ocl.pivot.Class leftContainerType = leftType.getContainerType();
		org.eclipse.ocl.pivot.Class rightContainerType = rightType.getContainerType();
		if (!standardLibrary.conformsToSimpleType(leftContainerType, rightContainerType)) {
			return false;
		}
		IntegerValue leftLower = leftType.getLowerValue();
		IntegerValue rightLower = rightType.getLowerValue();
		if (leftLower.compareTo(rightLower) < 0) {
			return false;
		}
		UnlimitedNaturalValue leftUpper = leftType.getUpperValue();
		UnlimitedNaturalValue rightUpper = rightType.getUpperValue();
		if (rightUpper instanceof Unlimited) {
			if (leftUpper instanceof Unlimited) {
				return false;
			}
			if (leftUpper.compareTo(rightUpper) > 0) {
				return false;
			}
		}
		Type leftElementType = PivotUtil.getElementType(leftType);
		Type rightElementType = PivotUtil.getElementType(rightType);
		if (enforceNullity) {
			boolean leftIsNullFree = leftType.isIsNullFree();
			boolean rightIsNullFree = rightType.isIsNullFree();
			return standardLibrary.conformsTo(leftElementType, leftIsNullFree, leftSubstitutions, rightElementType, rightIsNullFree, rightSubstitutions);
		}
		else {
			return standardLibrary.conformsTo(leftElementType, leftSubstitutions, rightElementType, rightSubstitutions, false);
		}
	}

	protected @NonNull CollectionType createCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		CollectionTypeId collectionTypeId = typeArguments.getCollectionTypeId();
		Type elementType = typeArguments.getElementType();
		boolean isNullFree = typeArguments.isNullFree();
		IntegerValue lower = typeArguments.getLower();
		UnlimitedNaturalValue upper = typeArguments.getUpper();
		CollectionType genericCollectionType = getCollectionType(collectionTypeId);
		return PivotUtil.createCollectionType(genericCollectionType, elementType, isNullFree, lower, upper);
	}

	@Override
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
					Type elementType = PivotUtil.getElementType(specializedType);
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
//		if (collectionTypeId instanceof SpecializedCollectionTypeIdImpl) {
//			SpecializedCollectionTypeIdImpl specializedCollectionTypeId = (SpecializedCollectionTypeIdImpl)collectionTypeId;
//			Boolean isNullFree = specializedCollectionTypeId.isNullFree();
//			IntegerValue lower = specializedCollectionTypeId.getLowerValue();
//			UnlimitedNaturalValue upper = specializedCollectionTypeId.getUpperValue();
//			return getCollectionType(new CollectionTypeArguments(collectionTypeId, isNullFree, lower, upper));
//		}
//		else {
//			return getCollectionType(typeId, false, null, null);
//		}
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
//		}
	}

	@Override
	public @NonNull CollectionType getCommonCollectionType(@NonNull CollectionType leftCollectionType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull CollectionType rightCollectionType, @Nullable TemplateParameterSubstitutions rightSubstitutions) {
		CollectionType leftGenericType = PivotUtil.getUnspecializedTemplateableElement(leftCollectionType);
		CollectionType rightGenericType = PivotUtil.getUnspecializedTemplateableElement(rightCollectionType);
		Type leftElementType = PivotUtil.getElementType(leftCollectionType);
		Type rightElementType = PivotUtil.getElementType(rightCollectionType);
		FlatClass leftInheritance = leftGenericType.getFlatClass(standardLibrary);				// XXX promote
		FlatClass rightInheritance = rightGenericType.getFlatClass(standardLibrary);
		FlatClass commonInheritance = leftInheritance.getCommonInheritance(rightInheritance);
		CollectionType commonGenericType = (CollectionType) commonInheritance.getPivotClass();
		Type commonElementType = standardLibrary.getCommonType(leftElementType, leftSubstitutions, rightElementType, rightSubstitutions);
		boolean commonIsNullFree = standardLibrary.getCommonIsRequired(leftCollectionType.isIsNullFree(), rightCollectionType.isIsNullFree());
		IntegerValue commonLower = getCommonLowerValue(leftCollectionType.getLowerValue(), rightCollectionType.getLowerValue());
		UnlimitedNaturalValue commonUpper = getCommonUpperValue(leftCollectionType.getUpperValue(), rightCollectionType.getUpperValue());
		CollectionTypeArguments typeArguments = new CollectionTypeArguments(commonGenericType.getTypeId(), commonElementType, commonIsNullFree, commonLower, commonUpper);
		return getCollectionType(typeArguments);
	}

	protected @NonNull IntegerValue getCommonLowerValue(@NonNull IntegerValue thisLowerValue, @NonNull IntegerValue thatLowerValue) {
		return (IntegerValue)thisLowerValue.max(thatLowerValue);
	}

	protected @NonNull UnlimitedNaturalValue getCommonUpperValue(@NonNull UnlimitedNaturalValue thisUpperValue, @NonNull UnlimitedNaturalValue thatUpperValue) {
		if (thisUpperValue.isUnlimited()) {
			if (thatUpperValue.isUnlimited()) {
				return thisUpperValue;
			}
			else {
				return thatUpperValue;
			}
		}
		else {
			if (thatUpperValue.isUnlimited()) {
				return thisUpperValue;
			}
			else {
				return thisUpperValue.min(thatUpperValue);
			}
		}
	}

	@Override
	public boolean isEqualToCollectionType(@NonNull CollectionType leftCollectionType, @NonNull CollectionType rightCollectionType) {
		Type leftContainerType = leftCollectionType.getContainerType();
		Type rightContainerType = rightCollectionType.getContainerType();
		if ((leftContainerType != rightContainerType) && !leftContainerType.isEqualToUnspecializedType(standardLibrary, rightContainerType)) {
			return false;
		}
		Type leftElementType = leftCollectionType.getElementType();
		Type rightElementType = rightCollectionType.getElementType();
		if (leftElementType != rightElementType) {
			if ((leftElementType == null) || (rightElementType == null)) {
				return false;
			}
			if (!standardLibrary.isEqualTo(leftElementType, rightElementType)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isValid(@Nullable Type type) {
		return type != null;
	}
}