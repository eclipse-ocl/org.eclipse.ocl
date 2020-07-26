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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

/**
 * A CardinalitySolutionResult specifies a run-time action as pat of the cardinality varoable drtermination.
 * An expression may be assigned to or checked against some variable
 */
public class RuleMatch
{
	protected final @NonNull UserSlotsAnalysis slotsAnalysis;
	protected final @NonNull Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = new HashMap<>();

	public RuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis) {
		this.slotsAnalysis = slotsAnalysis;
	}

/*	public @NonNull CardinalityVariable getCardinalityVariable() {
		return cardinalityVariable;
	}

	public @NonNull CardinalitySolution getCardinalitySolution() {
		return cardinalitySolution;
	}

	public boolean isAssigned() {
		return isAssigned;
	} */

	public @Nullable Integer basicGetIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return variable2value != null ? variable2value.get(cardinalityVariable) : null;
	}

	public int getSize(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		return slotsAnalysis.getSize(eStructuralFeature, enumerationValue);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		slotsAnalysis.toString(s, depth);
	//	for ()
	//	s.append(isAssigned ? "assign " : "check ");
	//	s.append(cardinalityVariable);
	//	s.append(" = ");
	//	s.append(cardinalitySolution);
	}
}