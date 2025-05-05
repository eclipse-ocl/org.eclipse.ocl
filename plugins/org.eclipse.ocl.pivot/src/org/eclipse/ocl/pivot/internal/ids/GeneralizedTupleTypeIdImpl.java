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
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TupleTypeId;

public class GeneralizedTupleTypeIdImpl extends AbstractTypeId implements TupleTypeId
{
	private static class TupleTypeIdValue extends AbstractKeyAndValue<@NonNull TupleTypeId>
	{
		private final @NonNull IdManager idManager;
		private @NonNull String name;
		private @NonNull PartId @NonNull [] orderedPartIds;

		private TupleTypeIdValue(@NonNull IdManager idManager, @NonNull String name, @NonNull PartId @NonNull [] orderedPartIds) {
			super(GeneralizedTupleTypeIdImpl.computeHashCode(name, orderedPartIds));
			this.idManager = idManager;
			this.name = name;
			this.orderedPartIds = orderedPartIds;
		}

		@Override
		public @NonNull TupleTypeId createSingleton() {
			return new GeneralizedTupleTypeIdImpl(idManager, name, orderedPartIds);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof GeneralizedTupleTypeIdImpl) {
				GeneralizedTupleTypeIdImpl singleton = (GeneralizedTupleTypeIdImpl)that;
				return singleton.matches(name, orderedPartIds);
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class TupleTypeIdSingletonScope extends AbstractSingletonScope<@NonNull TupleTypeId, @NonNull TupleTypeIdValue>
	{
		/**
		 * @since 7.0
		 */
		public @NonNull TupleTypeId getSingleton(@NonNull IdManager idManager, @NonNull String name, @NonNull PartId @NonNull [] orderedPartIds) {
			assert assertIsOrdered(orderedPartIds);
			int index = 0;
			for (@NonNull PartId partId : orderedPartIds) {
				if (index != partId.getIndex()) {		// If part at wrong index, replace with correctly indexed partId
					orderedPartIds[index] = IdManager.getPartId(index, partId.getName(), partId.getTypeId(), partId.isRequired());
				}
				index++;
			}
			return getSingletonFor(new TupleTypeIdValue(idManager, name, orderedPartIds));
		}

		private boolean assertIsOrdered(@NonNull PartId @NonNull [] orderedPartIds) {
			for (int i = 0; i < orderedPartIds.length-1; i++) {
				PartId earlierPartId = orderedPartIds[i];
				PartId laterPartId = orderedPartIds[i+1];
				if (earlierPartId.compareTo(laterPartId) >= 0) {
					return false;
				}
			}
			return true;
		}
	}

	private static int computeHashCode(@NonNull String name, @NonNull PartId @NonNull [] orderedPartIds) {
		return IdHash.createTupleHash(name, orderedPartIds);
	}

	protected final @NonNull Integer hashCode;			// FIXME int
	protected final @NonNull String name;
	protected final @NonNull PartId @NonNull [] partIds;

	private GeneralizedTupleTypeIdImpl(@NonNull IdManager idManager, @NonNull String name, @NonNull PartId @NonNull [] orderedPartIds) {
		this.hashCode = computeHashCode(name, orderedPartIds);
		this.name = name;
		this.partIds = orderedPartIds;
		assert partsAreOrdered();
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitTupleTypeId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		StringBuilder s = new StringBuilder();
		s.append(name);
		s.append("{");
		boolean isFirst = true;
		for (PartId partId : partIds) {
			if (!isFirst) {
				s.append(", ");
			}
			s.append(partId.getDisplayName());
			isFirst = false;
		}
		s.append("}");
		return s.toString();
	}

	public @NonNull TupleTypeId getGeneralizedId() {
		return this;
	}

	@Override
	public @NonNull String getMetaclassName() {
		return TUPLE_TYPE_NAME;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @Nullable PartId getPartId(@NonNull String name) {
		for (PartId partId : partIds) {
			if (name.equals(partId.getName())) {
				return partId;
			}
		}
		return null;
	}

	@Override
	public @NonNull PartId @NonNull [] getPartIds() {
		return partIds;
	}

	@Override
	public final int hashCode() {
		return hashCode;
	}

	/**
	 * @since 1.7
	 */
	@Override
	public boolean isTemplated() {
		for (PartId partId : partIds) {
			if (partId.getTypeId().isTemplated()) {
				return true;
			}
		}
		return false;
	}

	private boolean matches(@NonNull String thatName, @NonNull PartId @NonNull [] thoseOrderedParts) {
		for (int i = 0; i < partIds.length; i++) {
			if (partIds[i] != thoseOrderedParts[i]) {
				return false;
			}
		}
		if (!this.name.equals(thatName)) {
			return false;
		}
		return true;
	}

	private boolean partsAreOrdered() {
		for (int i = 0; i < partIds.length-1; i++) {
			PartId earlierPartId = partIds[i];
			PartId laterPartId = partIds[i+1];
			if (earlierPartId.compareTo(laterPartId) >= 0) {
				return false;
			}
		}
		return true;
	}
}