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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractCardinalitySolution implements CardinalitySolution
{
	@Override
	public @NonNull CardinalitySolution addSolution(@NonNull CardinalitySolution solution) {
		if (solution.equals(this)) {
			return this;
		}
		AndCardinalitySolution andCardinalitySolution = new AndCardinalitySolution();
		andCardinalitySolution.addSolution(this);
		andCardinalitySolution.addSolution(solution);
		return andCardinalitySolution;
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public @Nullable Integer getIntegerSolution(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		return null;
	}

	@Override
	public abstract int hashCode();

	@Override
	public boolean isRuntime() {
		return false;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}