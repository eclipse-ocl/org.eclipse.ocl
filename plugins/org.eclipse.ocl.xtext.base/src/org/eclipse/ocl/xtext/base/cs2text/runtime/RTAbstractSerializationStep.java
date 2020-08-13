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
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

public abstract class RTAbstractSerializationStep implements RTSerializationStep
{
	protected final int variableIndex;		// -ve not used
	protected final @Nullable CardinalityVariable cardinalityVariable;

	protected RTAbstractSerializationStep(@Nullable CardinalityVariable cardinalityVariable) {
		this.variableIndex = cardinalityVariable != null ? cardinalityVariable.getIndex() : -1;
		this.cardinalityVariable = cardinalityVariable;
	}

	protected RTAbstractSerializationStep(int variableIndex) {
		this.variableIndex = variableIndex;
		this.cardinalityVariable = null;
	}

	@Override
	public abstract boolean equals(Object obj);

	protected boolean equalTo(@NonNull RTAbstractSerializationStep that) {
		return this.variableIndex == that.variableIndex;
	}

	@Override
	public @Nullable CardinalityVariable getCardinalityVariable() {
		return cardinalityVariable;
	}

	public int getVariableIndex() {
		return variableIndex;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + 7 * variableIndex;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		if (variableIndex >= 0) {
			s.append(String.format("V%02d", variableIndex));
		}
		else {
			s.append("1");
		}
		s.append("*");
	//	if (cardinalityVariable != null) {
	//		s.append(cardinalityVariable.getName());
	//		s.append("*");
	//	}
	}
}