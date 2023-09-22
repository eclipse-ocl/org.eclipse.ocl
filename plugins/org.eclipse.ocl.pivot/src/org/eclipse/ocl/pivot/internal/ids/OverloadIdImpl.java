/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.OverloadId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;

public class OverloadIdImpl implements OverloadId, WeakHashMapOfListOfWeakReference3.MatchableId<String, ParametersId>
{
	private static class OverloadIdValue extends AbstractKeyAndValue<@NonNull OverloadId>
	{
		private @NonNull IdManager idManager;
		private @NonNull String name;
		private @NonNull ParametersId parametersId;

		private OverloadIdValue(@NonNull IdManager idManager, @NonNull String name, @NonNull ParametersId parametersId) {
			super(computeHashCode(name, parametersId));
			this.idManager = idManager;
			this.name = name;
			this.parametersId = parametersId;
		}

		@Override
		public @NonNull OverloadId createSingleton() {
			return new OverloadIdImpl(idManager, name, parametersId);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof OverloadIdImpl) {
				OverloadIdImpl singleton = (OverloadIdImpl)that;
				return name.equals(singleton.getName()) && (parametersId == singleton.getParametersId());
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class OverloadIdSingletonScope extends AbstractSingletonScope<@NonNull OverloadId, @NonNull OverloadIdValue>
	{
		public @NonNull OverloadId getSingleton(@NonNull IdManager idManager, @NonNull String name, @NonNull ParametersId parametersId) {
			return getSingletonFor(new OverloadIdValue(idManager, name, parametersId));
		}
	}

	private static int computeHashCode(@NonNull String name, @NonNull ParametersId parametersId) {
		return name.hashCode() + 7 * parametersId.hashCode();
	}

	protected final @NonNull Integer hashCode;
	protected final @NonNull String name;
	protected final @NonNull ParametersId parametersId;

//	@Deprecated /* @deprecated use simpler constructor */
//	public OverloadIdImpl(@NonNull IdManager idManager, @NonNull Integer hashCode, @NonNull String name, @NonNull ParametersId parametersId) {
//		this(idManager, name, parametersId);
//	}

	/**
	 * @since 1.18
	 */
	private OverloadIdImpl(@NonNull IdManager idManager, @NonNull String name, @NonNull ParametersId parametersId) {
		this.name = name;
		this.parametersId = parametersId;
		this.hashCode = computeHashCode(name, parametersId);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitOverloadId(this);
	}

	@Override
	public int compareTo(OverloadId o) {
		String n1 = name;
		String n2 = o.getName();
		int diff = n1.compareTo(n2);
		if (diff != 0) {
			return diff;
		}
		return o.getParametersId().hashCode() - parametersId.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof org.eclipse.ocl.pivot.ids.SingletonScope.KeyAndValue) {
			return obj.equals(this);
		}
		else {
			return this == obj;
		}
	}

	@Override
	public @NonNull String getDisplayName() {
		return String.valueOf(name) + String.valueOf(parametersId);
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull ParametersId getParametersId() {
		return parametersId;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean matches(@NonNull String thatName, @NonNull ParametersId thatParametersId) {
		if (this.parametersId != thatParametersId) {
			return false;
		}
		if (!this.name.equals(thatName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(name) + String.valueOf(parametersId);
	}
}