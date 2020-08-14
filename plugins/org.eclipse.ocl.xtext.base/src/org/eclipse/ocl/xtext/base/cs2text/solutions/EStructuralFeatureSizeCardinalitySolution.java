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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class EStructuralFeatureSizeCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull EStructuralFeature eStructuralFeature;

	public EStructuralFeatureSizeCardinalitySolution(@NonNull EStructuralFeature eStructuralFeature) {
		this.eStructuralFeature = eStructuralFeature;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.getSize(eStructuralFeature);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof EStructuralFeatureSizeCardinalitySolution)) {
			return false;
		}
		EStructuralFeatureSizeCardinalitySolution that = (EStructuralFeatureSizeCardinalitySolution)obj;
		if (this.eStructuralFeature != that.eStructuralFeature) return false;
		return true;
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + eStructuralFeature.hashCode();
	}

	@Override
	public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
		return false;
	}

	@Override
	public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("|");
		s.append(eStructuralFeature.getName());
		s.append("|");
	}
}