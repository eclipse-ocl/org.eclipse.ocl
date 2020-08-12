/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

public abstract class RTAbstractSerializationStep implements RTSerializationStep
{
	protected final @Nullable CardinalityVariable cardinalityVariable;

	public RTAbstractSerializationStep(@Nullable CardinalityVariable cardinalityVariable) {
		this.cardinalityVariable = cardinalityVariable;
	}

	@Override
	public abstract boolean equals(Object obj);

	protected boolean equalTo(@NonNull RTAbstractSerializationStep that) {
		return ClassUtil.safeEquals(this.getCardinalityVariable(), that.getCardinalityVariable());
	}

	public @Nullable CardinalityVariable getCardinalityVariable() {
		return cardinalityVariable;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + 7 * (cardinalityVariable != null ? cardinalityVariable.hashCode() : 0);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		if (cardinalityVariable != null) {
			s.append(cardinalityVariable.getName());
			s.append("*");
		}
	}
}