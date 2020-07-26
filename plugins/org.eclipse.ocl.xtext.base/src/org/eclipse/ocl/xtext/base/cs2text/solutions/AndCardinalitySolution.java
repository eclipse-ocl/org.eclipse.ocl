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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;

public class AndCardinalitySolution  extends AbstractCardinalitySolution
{
	protected final @NonNull List<@NonNull CardinalitySolution> solutions;

	public AndCardinalitySolution() {
		this.solutions = new ArrayList<>();
	}

	@Override
	public @NonNull AndCardinalitySolution addSolution(@NonNull CardinalitySolution solution) {
		if (!solutions.contains(solution)) {
			if (solutions.size() >= 3) {
				getClass();			// XXX debugging
			}
			solutions.add(solution);
		}
		return this;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull UserSlotsAnalysis slotsAnalysis, @Nullable Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value) {
		Integer netIntegerSolution = null;
		for (@NonNull CardinalitySolution solution : solutions) {
			Integer integerSolution = solution.basicGetIntegerSolution(slotsAnalysis, variable2value);
			if (netIntegerSolution == null) {
				netIntegerSolution = integerSolution;
			}
			else if (!netIntegerSolution.equals(integerSolution)) {
				return null;
			}
		}
		return netIntegerSolution;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AndCardinalitySolution)) {
			return false;
		}
		AndCardinalitySolution that = (AndCardinalitySolution) obj;
		if (!this.solutions.equals(that.solutions)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return solutions.hashCode();
	}

	@Override
	public boolean isRuntime() {
		for (@NonNull CardinalitySolution solution : solutions) {
			if (solution.isRuntime()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst = true;
		for (@NonNull CardinalitySolution solution : solutions) {
			if (!isFirst) {
				s.append(" & ");
			}
			s.append(solution);
			isFirst = false;
		}
	}
}