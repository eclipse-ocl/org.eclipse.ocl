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

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A CardinalitySolution defines the behaviour of nodes in an expression tree that provides the
 * limited capability to compute the cardinalities of SerilaizationRule terms from the actual
 * feture slot sizes of an actual element to be serialized.
 */
public interface CardinalitySolution
{
	/**
	 * Return the value of the expression value using the actual characteristic of the user element slots if available.
	 * Returns null if evaluation fails.
	 */
	@Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch);

	/**
	 * Traverse the solution tree adding a blank entry for each colution term to solution2id.
	 */
	void gatherSolutions(@NonNull Map<@NonNull CardinalitySolution, @NonNull String> solution2id);

	/**
	 * Return true if this is a foldable constant value at compile time. i.e an expression involving integer literals.
	 */
	boolean isConstant(@NonNull StaticRuleMatch ruleMatch);

	/**
	 * Return true if this will be a known constant value at run time. i.e. an expression involving actual feature slot counts.
	 */
	boolean isKnown(@NonNull StaticRuleMatch ruleMatch);

	/**
	 * Return true if this expression is a two-valued optional cardinality.
	 */
	boolean isOptional();

	boolean isRuntime();
	void toString(@NonNull StringBuilder s, int depth);
}