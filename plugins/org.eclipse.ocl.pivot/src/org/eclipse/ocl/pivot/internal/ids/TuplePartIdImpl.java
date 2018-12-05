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
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class TuplePartIdImpl implements TuplePartId, WeakHashMapOfListOfWeakReference4.MatchableId<Integer, String, TypeId>
{
	protected final @NonNull Integer hashCode;							
	protected final int index;							
	protected final @NonNull String name;
	protected final @NonNull TypeId typeId;
	
	public TuplePartIdImpl(@NonNull IdManager idManager, @NonNull Integer hashCode, int index, @NonNull String name, @NonNull TypeId typeId) {
		this.hashCode = hashCode;
		this.index = index;
		this.name = name;
		this.typeId = typeId;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitTuplePartId(this);
	}

	@Override
	public int compareTo(TuplePartId o) {
		String n1 = name;
		String n2 = o.getName();
		if (n1 == n2) {
			return 0;
		}
		return n1.compareTo(n2);
	}

	@Override
	public final boolean equals(Object obj) {
		return this == obj;
	}

	@Override
	public @NonNull String getDisplayName() {
		return String.valueOf(name) + " : " + String.valueOf(typeId);
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return typeId;
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean matches(@NonNull Integer thatIndex, @NonNull String thatName, @NonNull TypeId thatTypeid) {
		if (this.typeId != thatTypeid) {
			return false;
		}
		if (this.index != thatIndex) {
			return false;
		}
		if (!this.name.equals(thatName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(name) + " : " + String.valueOf(typeId);
	}
}