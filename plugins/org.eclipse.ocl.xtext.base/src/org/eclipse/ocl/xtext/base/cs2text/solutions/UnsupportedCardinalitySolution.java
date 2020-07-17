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

public class UnsupportedCardinalitySolution extends AbstractCardinalitySolution
{
	public UnsupportedCardinalitySolution() {}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof UnsupportedCardinalitySolution)) {
			return false;
		}
		return true;
	}

	@Override
	public @Nullable Integer getIntegerSolution(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		return null;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("?");
	}
}