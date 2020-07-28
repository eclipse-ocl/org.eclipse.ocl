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
import org.eclipse.ocl.xtext.base.cs2text.user.StaticRuleMatch;

public abstract class AbstractBinaryCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull CardinalitySolution left;
	protected final @NonNull CardinalitySolution right;

	public AbstractBinaryCardinalitySolution(@NonNull CardinalitySolution left, @NonNull CardinalitySolution right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + left.hashCode() + right.hashCode() * 7;
	}

	@Override
	public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
		return left.isConstant(ruleMatch) && right.isConstant(ruleMatch);
	}

	@Override
	public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
		return left.isKnown(ruleMatch) && right.isKnown(ruleMatch);
	}
}