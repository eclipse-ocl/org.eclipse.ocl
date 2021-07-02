/**
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class SymbolicContent
{
	private @NonNull String name;
	private int cloneCount = 0;
	private @Nullable SymbolicValue sizeValue;

	protected SymbolicContent(@NonNull String name, @NonNull TypeId typeId) {
		this.name = name;
	}

	protected SymbolicContent(@NonNull SymbolicContent originalContent) {
		this.name = originalContent.name + originalContent.cloneCount++ + "%";
		this.sizeValue = originalContent.sizeValue;
	}

	public @NonNull SymbolicValue getSize() {
		SymbolicValue sizeValue2 = sizeValue;
		if (sizeValue2 == null) {
			sizeValue = sizeValue2 = new SymbolicUnknownValueImpl(name + "size", TypeId.INTEGER, false, false);
		}
		return sizeValue2;
	}

	public boolean isEmpty() {
		return getSize().isZero();
	}

	public boolean mayBeEmpty() {
		return getSize().mayBeZero();
	}

	public void setSize(@NonNull SymbolicValue sizeValue) {
		this.sizeValue = sizeValue;
	}

	public abstract @NonNull SymbolicContent shallowClone();

	@Override
	public /*final*/ @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s) {
		SymbolicValue sizeValue2 = sizeValue;
		if (sizeValue2 != null) {
			s.append("size:");
			sizeValue2.toString(s, 0);
		}
	}
}
