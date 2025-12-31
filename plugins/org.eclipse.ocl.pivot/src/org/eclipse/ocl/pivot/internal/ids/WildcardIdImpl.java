/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.WildcardId;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * @since 7.0
 */
public class WildcardIdImpl extends UnscopedId implements WildcardId
{
	private static class WildcardIdValue extends AbstractKeyAndValue<@NonNull WildcardId>
	{
		private final @NonNull IdManager idManager;
		private final int index;

		private WildcardIdValue(@NonNull IdManager idManager, int index) {
			super(computeHashCode(index));
			this.idManager = idManager;
			this.index = index;
		}

		@Override
		public @NonNull WildcardId createSingleton() {
			return new WildcardIdImpl(idManager, index);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof WildcardIdImpl) {
				WildcardIdImpl singleton = (WildcardIdImpl)that;
				return index == singleton.getIndex();
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 7.0
	 */
	public static class WildcardIdSingletonScope extends AbstractSingletonScope<@NonNull WildcardId, @NonNull Integer>
	{
		public @NonNull WildcardId getSingleton(@NonNull IdManager idManager, int index) {
			return getSingletonFor(new WildcardIdValue(idManager, index));
		}
	}

	private static int computeHashCode(int index) {
		return index;
	}

	/**
	 * @since 7.0
	 */
	protected final int index;

	/**
	 * @since 7.0
	 */
	public WildcardIdImpl(@NonNull IdManager idManager, int index) {
		super(computeHashCode(index), PivotConstants.WILDCARD_NAME + index);
		this.index = index;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitWildcardId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return PivotConstants.WILDCARD_NAME + index;
	}

	@Override
	public int getIndex() {
		return index;
	}

//	@Override
//	public @NonNull String getName() {
//		return name;
//	}
}