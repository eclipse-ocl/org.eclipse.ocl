/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.ParameterId;
import org.eclipse.ocl.pivot.ids.SingletonScope;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * ParameterId describes the typeId and typeOf of an operation parameter.
 * @since 7.0
 */
public class ParameterIdImpl implements ParameterId
{
	private static class ParameterIdValue extends AbstractKeyAndValue<@NonNull ParameterId>
	{
		private final @NonNull IdManager idManager;
		private final @NonNull TypeId typeId;
		private final boolean isTypeOf;

		private ParameterIdValue(@NonNull IdManager idManager, @NonNull TypeId typeId, boolean isTypeOf) {
			super(computeHashCode(typeId, isTypeOf));
			this.idManager = idManager;
			this.typeId = typeId;
			this.isTypeOf = isTypeOf;
		}

		@Override
		public @NonNull ParameterId createSingleton() {
			return new ParameterIdImpl(idManager, typeId, isTypeOf);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof ParameterIdImpl) {
				ParameterIdImpl singleton = (ParameterIdImpl)that;
				return (typeId == singleton.typeId) && (isTypeOf == singleton.isTypeOf);
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class ParameterIdSingletonScope extends AbstractSingletonScope<@NonNull ParameterId, @NonNull TypeId @NonNull []>
	{
		public @NonNull ParameterId getSingleton(@NonNull IdManager idManager, @NonNull TypeId typeId, boolean isTypeOf) {
			return getSingletonFor(new ParameterIdValue(idManager, typeId, isTypeOf));
		}
	}

	private static int computeHashCode(@NonNull TypeId typeId, boolean isTypeOf) {
		return typeId.hashCode() + (isTypeOf ? 0x1111 : 0);
	}

	private final int hashCode;
	private final @NonNull TypeId typeId;
	private final boolean isTypeOf;

	/**
	 * @since 1.18
	 */
	public ParameterIdImpl(@NonNull IdManager idManager, @NonNull TypeId typeId, boolean isTypeOf) {
		this.hashCode = computeHashCode(typeId, isTypeOf);
		this.typeId = typeId;
		this.isTypeOf = isTypeOf;
	}

	@Override
	public final boolean equals(Object that) {
		if (that instanceof SingletonScope.KeyAndValue) {			// A SingletonScope.Key may be used to lookup a ParameterId
			return that.equals(this);
		}
		else {														// But normally ParameterId instances are singletons
			return this == that;
		}
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return typeId;
	}

	@Override
	public boolean isTypeOf() {
		return isTypeOf;
	}

	@Override
	public @NonNull String toString() {
		if (isTypeOf) {
			return "typeOf(" + typeId + ")";
		}
		else {
			return typeId.toString();
		}
	}
}