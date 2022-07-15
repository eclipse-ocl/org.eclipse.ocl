/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.BooleanLiteralId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedMapTypeIdImpl extends GeneralizedTypeIdImpl<@NonNull MapTypeId> implements MapTypeId
{
	private static class MapTypeIdValue extends AbstractKeyAndValue<@NonNull MapTypeId>
	{
		private final @NonNull IdManager idManager;
		private final @NonNull String value;

		public MapTypeIdValue(@NonNull IdManager idManager, @NonNull String value) {
			super(computeHashCode(value));
			this.idManager = idManager;
			this.value = value;
		}

		@Override
		public @NonNull MapTypeId createSingleton() {
			return new GeneralizedMapTypeIdImpl(idManager, value);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof GeneralizedMapTypeIdImpl) {
				GeneralizedMapTypeIdImpl singleton = (GeneralizedMapTypeIdImpl)that;
				return singleton.getName().equals(value);
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class MapTypeIdSingletonScope extends AbstractSingletonScope<@NonNull MapTypeId, @NonNull String>
	{
		public @NonNull MapTypeId getSingleton(@NonNull IdManager idManager, @NonNull String value) {
			return getSingletonFor(new MapTypeIdValue(idManager, value));
		}
	}

	private static int computeHashCode(@NonNull String name) {
		return IdHash.createGlobalHash(MapTypeId.class, name);
	}

	public GeneralizedMapTypeIdImpl(@NonNull IdManager idManager, @NonNull String name) {
		super(computeHashCode(name), 4, name);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitMapTypeId(this);
	}

	@Override
	protected @NonNull MapTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedMapTypeIdImpl(this, templateBindings);
	}

	@Override
	public @NonNull String getDisplayName() {
		return name;
	}

	@Override
	public @NonNull MapTypeId getGeneralizedId() {
		return this;
	}

	@Override
	public @NonNull TemplateParameterId getKeyTypeId() {
		return TypeId.T_1;
	}

	@Override
	public @Nullable String getLiteralName() {
		return "MAP";
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return name + "Type";
	}

	@Override		// FIXME refactor to share Generalized/Specialized functionality
	public @NonNull MapTypeId getSpecializedId(@NonNull BindingsId templateBindings) {
		if (templateBindings.size() == 2) {					// Legacy compatibility / generator default case optimization
			return getSpecializedMapTypeId(templateBindings.get(0), TypeId.FALSE_VALUE, templateBindings.get(1), TypeId.FALSE_VALUE);
		}
		else {
			return super.getSpecializedId(templateBindings);
		}
	}

	@Override		// FIXME refactor to share Generalized/Specialized functionality
	public @NonNull MapTypeId getSpecializedId(@NonNull ElementId... templateBindings) {
		if (templateBindings.length == 2) {					// Legacy compatibility / generator default case optimization
			return getSpecializedMapTypeId(templateBindings[0], TypeId.FALSE_VALUE, templateBindings[1], TypeId.FALSE_VALUE);
		}
		else {
			return super.getSpecializedId(templateBindings);
		}
	}

	@Override
	public @NonNull MapTypeId getSpecializedMapTypeId(@NonNull ElementId keyTypeId, @NonNull ElementId valueTypeId) {
		return getSpecializedId(keyTypeId, TypeId.FALSE_VALUE, valueTypeId, TypeId.FALSE_VALUE);
	}

	@Override
	public @NonNull MapTypeId getSpecializedMapTypeId(@NonNull ElementId keyTypeId, @NonNull BooleanLiteralId keysAreNullTypeId,
			@NonNull ElementId valueTypeId, @NonNull BooleanLiteralId valuesAreNullTypeId) {
		return getSpecializedId(keyTypeId, keysAreNullTypeId, valueTypeId, valuesAreNullTypeId);
	}

	@Override
	public @NonNull TemplateParameterId getValueTypeId() {
		return TypeId.T_3;
	}

    @Override
	public boolean isKeysAreNullFree() {
		return false;
	}

	@Override
	public boolean isValuesAreNullFree() {
		return false;
	}

	@Override
	public @NonNull MapTypeId specialize(@NonNull BindingsId templateBindings) {
    	return getSpecializedId(templateBindings);
	}
}