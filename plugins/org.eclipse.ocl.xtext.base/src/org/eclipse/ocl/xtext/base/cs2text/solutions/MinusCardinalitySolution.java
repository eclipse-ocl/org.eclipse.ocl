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
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;

public class MinusCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull CardinalitySolution left;
	protected final @NonNull CardinalitySolution right;

	public MinusCardinalitySolution(@NonNull CardinalitySolution left, @NonNull CardinalitySolution right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		Integer intLeft = left.basicGetIntegerSolution(dynamicRuleMatch);
		if (intLeft == null) {
			return null;
		}
		Integer intRight = right.basicGetIntegerSolution(dynamicRuleMatch);
		if (intRight == null) {
			return null;
		}
		return intLeft - intRight;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MinusCardinalitySolution)) {
			return false;
		}
		MinusCardinalitySolution that = (MinusCardinalitySolution) obj;
		if (!this.left.equals(that.left)) return false;
		if (!this.right.equals(that.right)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + left.hashCode() + right.hashCode() * 7;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("(");
		left.toString(s, depth);
		s.append(" - ");
		right.toString(s, depth);
		s.append(")");
	}
}