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
 * A CardinalitySolution defines the behaviour of nodes in an expression tree that provides the
 * limited capability to compute the cardinalities of SerilaizationRule terms from the actual
 * feture slot sizes of an actual element to be serialized.
 */
public interface CardinalitySolution
{
//	@NonNull CardinalitySolution addSolution(@NonNull CardinalitySolution solution);

	/**
	 * Return the value of the expression value using the actual characteristoc of the user element slots.
	 * Returns null if evaluation fails.
	 */
	@Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch);
	boolean isRuntime();
	void toString(@NonNull StringBuilder s, int depth);
}