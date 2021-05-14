/**
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.cse;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.TypedElement;

/**
 * A CSEElement defines the behaviour of a node in the Common Sub-Expression tree.
 *
 * THe CSEElement has the same actual value wherever used since OCL's side effect-free characterisics prohibit
 * a different evaluation having a variant result. NB this applies to a particular iteration.
 * Eaxh iteration may have a different actual value.
 *
 * When evaluating a hypothesis for a particular expression, all CSEs again have the same narrower symbolic
 * value for that hypothesis. CSEs therefore have narrower variant symbolic values that are access dependent.
 *
 * @since 1.15
 */
public interface CSEElement
{
	@NonNull CommonSubExpressionAnalysis getCommonSubExpressionAnalysis();

	/**
	 * The model element that first triggered creation of this CSE element.
	 */
	@NonNull TypedElement getElement();

	/**
	 * The transitive depth of getInputs().
	 */
	int getHeight();

	/**
	 * Add an 'if' for ifExp with this CSE as the condition.
	 */
	@NonNull CSEElement getIfCSE(@NonNull IfExp ifExp, @NonNull CSEElement thenCSE, @NonNull CSEElement elseCSE);

	/**
	 * The CSEs directly used by evaluation of this CSE.
	 */
	@Nullable Iterable<@NonNull CSEElement> getInputs();

	/**
	 * Add an iteration/operation for callExp with this CSE as the source.
	 */
	@NonNull CSEElement getOperationCSE(@NonNull CallExp callExp, @NonNull Operation operation, @NonNull List<@Nullable CSEElement> argumentCSEs);

	/**
	 * The expressions whose evaluation uses this CSE.
	 */
	@Nullable Iterable<@NonNull OCLExpression> getOutputs();

	/**
	 * Add a navigation for navigationCallExp with this CSE as the source.
	 */
	@NonNull CSEElement getPropertyCSE(@NonNull NavigationCallExp navigationCallExp);
}
