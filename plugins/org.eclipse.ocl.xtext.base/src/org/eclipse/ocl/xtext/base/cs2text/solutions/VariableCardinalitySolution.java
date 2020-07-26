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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.user.RuleMatch;

/**
 * A VariableCardinalitySolution contributes the already computed value of a cardinality variable to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class VariableCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull CardinalityVariable cardinalityVariable;

	public VariableCardinalitySolution(@NonNull CardinalityVariable cardinalityVariable) {
		this.cardinalityVariable = cardinalityVariable;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.basicGetIntegerSolution(cardinalityVariable);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof VariableCardinalitySolution)) {
			return false;
		}
		VariableCardinalitySolution that = (VariableCardinalitySolution) obj;
		if (!this.cardinalityVariable.equals(that.cardinalityVariable)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + cardinalityVariable.hashCode();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(cardinalityVariable.getName());
	}
}