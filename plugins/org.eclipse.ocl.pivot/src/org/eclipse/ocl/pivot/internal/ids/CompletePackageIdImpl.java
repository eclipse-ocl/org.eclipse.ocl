/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;

/**
 * @since 7.0
 */
public class CompletePackageIdImpl extends AbstractElementId implements CompletePackageId
{
	private static class CompletePackageIdValue extends AbstractKeyAndValue<@NonNull CompletePackageId>
	{
		private final @NonNull IdManager idManager;
		private final @NonNull String value;

		public CompletePackageIdValue(@NonNull IdManager idManager, @NonNull String value) {
			super(computeHashCode(value));
			this.idManager = idManager;
			this.value = value;
		}

		@Override
		public @NonNull CompletePackageId createSingleton() {
			return new CompletePackageIdImpl(idManager, value);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof CompletePackageIdImpl) {
				CompletePackageIdImpl singleton = (CompletePackageIdImpl)that;
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
	public static class CompletePackageIdSingletonScope extends AbstractSingletonScope<@NonNull CompletePackageId, @NonNull String>
	{
		public @NonNull CompletePackageId getSingleton(@NonNull IdManager idManager, @NonNull String value) {
			return getSingletonFor(new CompletePackageIdValue(idManager, value));
		}
	}

	private static int computeHashCode(@NonNull String name) {
		return IdHash.createGlobalHash(CompletePackageId.class, name);
	}

	protected final @NonNull String name;
	protected final @NonNull Integer hashCode;		// FIXME int

	public CompletePackageIdImpl(@NonNull IdManager idManager, @NonNull String name) {
		this.hashCode = computeHashCode(name);
		this.name = name;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitCompletePackageId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return name;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}
}