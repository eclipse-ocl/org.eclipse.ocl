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
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;

public class GeneralizedTupleTypeIdImpl extends AbstractTypeId implements TupleTypeId, WeakHashMapOfListOfWeakReference3.MatchableId<String, @NonNull TuplePartId @NonNull []>
{
	protected final @NonNull Integer hashCode;
	protected final @NonNull String name;
	protected final @NonNull TuplePartId @NonNull [] partIds;

	public GeneralizedTupleTypeIdImpl(@NonNull IdManager idManager, @NonNull Integer hashCode, @NonNull String name, @NonNull TuplePartId @NonNull [] orderedPartIds) {
		this.hashCode = hashCode;
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
		for (TuplePartId partId : partIds) {
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
	public @NonNull String getMetaTypeName() {
		return TUPLE_TYPE_NAME;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @Nullable TuplePartId getPartId(@NonNull String name) {
		for (TuplePartId partId : partIds) {
			if (name.equals(partId.getName())) {
				return partId;
			}
		}
		return null;
	}

	@Override
	public @NonNull TuplePartId @NonNull [] getPartIds() {
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
		for (TuplePartId partId : partIds) {
			if (partId.getTypeId().isTemplated()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean matches(@NonNull String thatName, @NonNull TuplePartId @NonNull [] thoseOrderedParts) {
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
			TuplePartId earlierPartId = partIds[i];
			TuplePartId laterPartId = partIds[i+1];
			if (earlierPartId.compareTo(laterPartId) >= 0) {
				return false;
			}
		}
		return true;
	}
}