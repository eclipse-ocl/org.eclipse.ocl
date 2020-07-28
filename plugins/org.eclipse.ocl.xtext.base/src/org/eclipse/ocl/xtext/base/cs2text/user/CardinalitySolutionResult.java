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
package org.eclipse.ocl.xtext.base.cs2text.user;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

/**
 * A CardinalitySolutionResult specifies a run-time action as part of the cardinality varoable drtermination.
 * An expression may be assigned to or checked against some variable
 */
public class CardinalitySolutionResult
{
	protected final @Nullable CardinalityVariable cardinalityVariable;		// Null for a constant check
	protected final @NonNull CardinalitySolution cardinalitySolution;
	protected final boolean isAssigned;		// True to assign, false to check for consistency

	public CardinalitySolutionResult(@Nullable CardinalityVariable cardinalityVariable, @NonNull CardinalitySolution cardinalitySolution, boolean isAssigned) {
		this.cardinalityVariable = cardinalityVariable;
		this.cardinalitySolution = cardinalitySolution;
		this.isAssigned = isAssigned;
	}

	public @Nullable CardinalityVariable getCardinalityVariable() {
		return cardinalityVariable;
	}

	public @NonNull CardinalitySolution getCardinalitySolution() {
		return cardinalitySolution;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int i) {
		s.append(isAssigned ? "assign " : "check ");
		if (cardinalityVariable != null) {
			s.append(cardinalityVariable);
			s.append(" = ");
		}
		s.append(cardinalitySolution);
		if (cardinalityVariable == null) {
			s.append(" == 0");
		}
	}
}