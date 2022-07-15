/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.BooleanLiteralId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.IntegerLiteralId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.UnlimitedNaturalLiteralId;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class SpecializedCollectionTypeIdImpl extends AbstractSpecializedIdImpl<CollectionTypeId> implements CollectionTypeId
{
	private @Nullable TypeId elementTypeId;

	public SpecializedCollectionTypeIdImpl(@NonNull CollectionTypeId generalizedId, @NonNull BindingsId templateBindings) {
		super(generalizedId, templateBindings);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitCollectionTypeId(this);
	}

	@Override
	protected @NonNull CollectionTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedCollectionTypeIdImpl(this, templateBindings);
	}

	@Override
	public @NonNull TypeId getElementTypeId() {
		TypeId elementTypeId2 = elementTypeId;
		if (elementTypeId2 == null) {
			elementTypeId = elementTypeId2 = (TypeId) generalizedId.getElementTypeId().specialize(templateBindings);
		}
		return elementTypeId2;
	}

	@Override
	public @NonNull IntegerValue getLowerValue() {
		IntegerLiteralId upperValueId = (IntegerLiteralId) TypeId.T_3.specialize(templateBindings);
		return upperValueId.getValue();
	}

	/**
	 * @since 1.18
	 */
	@Override
	public @NonNull CollectionTypeId getSpecializedCollectionTypeId(@NonNull CollectionTypeId collectionTypeId) {
		return getSpecializedId(collectionTypeId.getElementTypeId(), TypeId.valueOf(collectionTypeId.isNullFree()), TypeId.valueOf(collectionTypeId.getLowerValue()), TypeId.valueOf(collectionTypeId.getUpperValue()));
	}

	@Override
	public @NonNull CollectionTypeId getSpecializedCollectionTypeId(@NonNull ElementId elementId) {
		return getSpecializedId(elementId, TypeId.FALSE_VALUE, TypeId.ZERO_VALUE, TypeId.UNLIMITED_VALUE);
	}

	@Override
	public @NonNull CollectionTypeId getSpecializedCollectionTypeId(@NonNull ElementId elementId, @NonNull BooleanLiteralId isNullFree,
			@NonNull IntegerLiteralId lowerValue, @NonNull UnlimitedNaturalLiteralId upperValue) {
		return getSpecializedId(elementId, isNullFree, lowerValue, upperValue);
	}


	@Override		// FIXME refactor to share Generalized/Specialized functionality
	public @NonNull CollectionTypeId getSpecializedId(@NonNull BindingsId templateBindings) {
		if (templateBindings.size() == 1) {					// Legacy compatibility / generator default case optimization
			return getSpecializedCollectionTypeId(templateBindings.get(0), TypeId.FALSE_VALUE, TypeId.ZERO_VALUE, TypeId.UNLIMITED_VALUE);
		}
		else if (templateBindings.size() == 2) {					// Legacy compatibility / generator default case optimization
			return getSpecializedCollectionTypeId(templateBindings.get(0), (BooleanLiteralId)templateBindings.get(1), TypeId.ZERO_VALUE, TypeId.UNLIMITED_VALUE);
		}
		else if (templateBindings.size() == 3) {					// Legacy compatibility / generator default case optimization
			return getSpecializedCollectionTypeId(templateBindings.get(0), (BooleanLiteralId)templateBindings.get(1), (IntegerLiteralId)templateBindings.get(2), TypeId.UNLIMITED_VALUE);
		}
		else {
			return super.getSpecializedId(templateBindings);
		}
	}

	@Override		// FIXME refactor to share Generalized/Specialized functionality
	public @NonNull CollectionTypeId getSpecializedId(@NonNull ElementId... templateBindings) {
		if (templateBindings.length == 1) {					// Legacy compatibility / generator default case optimization
			return getSpecializedCollectionTypeId(templateBindings[0], TypeId.FALSE_VALUE, TypeId.ZERO_VALUE, TypeId.UNLIMITED_VALUE);
		}
		else if (templateBindings.length == 2) {					// Legacy compatibility / generator default case optimization
			return getSpecializedCollectionTypeId(templateBindings[0], (BooleanLiteralId)templateBindings[1], TypeId.ZERO_VALUE, TypeId.UNLIMITED_VALUE);
		}
		else if (templateBindings.length == 3) {					// Legacy compatibility / generator default case optimization
			return getSpecializedCollectionTypeId(templateBindings[0], (BooleanLiteralId)templateBindings[1], (IntegerLiteralId)templateBindings[2], TypeId.UNLIMITED_VALUE);
		}
		else {
			return super.getSpecializedId(templateBindings);
		}
	}

	@Override
	public @NonNull UnlimitedNaturalValue getUpperValue() {
		UnlimitedNaturalLiteralId upperValueId = (UnlimitedNaturalLiteralId) TypeId.T_4.specialize(templateBindings);
		return upperValueId.getValue();
	}

	@Override
	public boolean isNullFree() {
		BooleanLiteralId keysAreNullFree = (BooleanLiteralId) TypeId.T_2.specialize(templateBindings);
		return keysAreNullFree.getValue();
	}

	/**
	 * @since 1.7
	 */
	@Override
	public boolean isTemplated() {
		return true;
	}

	@Override
	public @NonNull CollectionTypeId specialize(@NonNull BindingsId templateBindings) {
		return createSpecializedId(templateBindings);
	}
}