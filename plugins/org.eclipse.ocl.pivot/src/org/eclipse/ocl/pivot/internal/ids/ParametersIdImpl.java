/*******************************************************************************
 * Copyright (c) 2013, 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.ParameterId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.SingletonScope;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;

/**
 * ParametersId provides a hashed list of typeIds suitable for characterizing an operation signature.
 * parameter ids suitable for use when indexing operation overloads.
 */
public class ParametersIdImpl implements ParametersId
{
	protected class Iterator implements java.util.Iterator<@NonNull ParameterId>
	{
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < parameterIds.length;
		}

		@Override
		public @NonNull ParameterId next() {
			return parameterIds[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private static class ParametersIdValue extends AbstractKeyAndValue<@NonNull ParametersId>
	{
		private final @NonNull IdManager idManager;
		private final @NonNull ParameterId @NonNull [] parameterIds;

		private ParametersIdValue(@NonNull IdManager idManager, @NonNull ParameterId @NonNull [] parameterIds) {
			super(computeHashCode(parameterIds));
			this.idManager = idManager;
			this.parameterIds = parameterIds;
		}

		@Override
		public @NonNull ParametersId createSingleton() {
			return new ParametersIdImpl(idManager, parameterIds);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof ParametersIdImpl) {
				ParametersIdImpl singleton = (ParametersIdImpl)that;
				return computeEquals(singleton.parameterIds, parameterIds);
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class ParametersIdSingletonScope extends AbstractSingletonScope<@NonNull ParametersId, @NonNull ParameterId @NonNull []>
	{
		/**
		 * @since 7.0
		 */
		public @NonNull ParametersId getSingleton(@NonNull IdManager idManager, @NonNull ParameterId @NonNull [] parameterIds) {
			return getSingletonFor(new ParametersIdValue(idManager, parameterIds));
		}
	}

	private static boolean computeEquals(@NonNull ParameterId @NonNull [] theseParameterIds, @NonNull ParameterId @NonNull [] thoseParameterIds) {
		if (theseParameterIds.length != thoseParameterIds.length) {
			return false;
		}
		for (int i = 0; i < theseParameterIds.length; i++) {
			if (theseParameterIds[i] != thoseParameterIds[i]) {
				return false;
			}
		}
		return true;
	}

	private static int computeHashCode(@NonNull ParameterId @NonNull [] parameterIds) {
		return IdHash.createParametersHash(ParametersIdImpl.class, parameterIds);
	}

	private final int hashCode;
	private final @NonNull ParameterId @NonNull [] parameterIds;

	/**
	 * @since 7.0
	 */
	public ParametersIdImpl(@NonNull IdManager idManager, @NonNull ParameterId @NonNull [] parameterIds) {
		this.hashCode = computeHashCode(parameterIds);
		this.parameterIds = parameterIds;
	}

	@Override
	public final boolean equals(Object that) {
		if (that instanceof SingletonScope.KeyAndValue) {			// A SingletonScope.Key may be used to lookup a ParametersId
			return that.equals(this);
		}
		else {												// But normally ParametersId instances are singletons
			return this == that;
		}
	}

	@Override
	public @NonNull ParameterId get(int index) {
		return parameterIds[index];
	}

	public @NonNull ParameterId @NonNull [] get() {
		return parameterIds;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public java.util.@NonNull Iterator<@NonNull ParameterId> iterator() {
		return new Iterator();
	}

	@Override
	public int size() {
		return parameterIds.length;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < parameterIds.length; i++) {
			if (i > 0) {
				s.append(',');
			}
			ParameterId parameterId = parameterIds[i];
			@SuppressWarnings("null")boolean isNonNull = parameterId != null;			// Never happens NE guard
			s.append(isNonNull ? parameterId.toString() : "null");
		}
		s.append(')');
		return s.toString();
	}
}