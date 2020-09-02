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
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;

/**
 * A VariableCardinalitySolution contributes the already computed value of a cardinality variable to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class SerializationMatchTermVariable extends SerializationMatchTermAbstract
{
	protected final int cardinalityVariableIndex;

	public SerializationMatchTermVariable(int cardinalityVariableIndex) {
		this.cardinalityVariableIndex = cardinalityVariableIndex;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.basicGetIntegerSolution(cardinalityVariableIndex);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SerializationMatchTermVariable)) {
			return false;
		}
		SerializationMatchTermVariable that = (SerializationMatchTermVariable) obj;
		return this.cardinalityVariableIndex == that.cardinalityVariableIndex;
	}

	public int getVariableIndex() {
		return cardinalityVariableIndex;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + cardinalityVariableIndex;
	}

	@Override
	public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
		return false;
	}

	@Override
	public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
		return false;
	}

//	@Override
//	public boolean isOptional() {
//		return cardinalityVariable.mayBeNone() && !cardinalityVariable.mayBeMany();
//	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("V" + cardinalityVariableIndex);
	}
}