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
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.RuleMatch;

/**
 * A FeatureSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class FeatureSizeCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull EnumerationValue enumerationValue;

	public FeatureSizeCardinalitySolution(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		this.eStructuralFeature = eStructuralFeature;
		this.enumerationValue = enumerationValue;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.getSize(eStructuralFeature, enumerationValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof FeatureSizeCardinalitySolution)) {
			return false;
		}
		FeatureSizeCardinalitySolution that = (FeatureSizeCardinalitySolution) obj;
		if (this.eStructuralFeature != that.eStructuralFeature) return false;
		if (!this.enumerationValue.equals(that.enumerationValue)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + eStructuralFeature.hashCode() + enumerationValue.hashCode() * 7;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("|");
		s.append(eStructuralFeature.getName());
		if (!enumerationValue.isNull()) {
			s.append(".\"");
			s.append(enumerationValue.getName());
			s.append("\"");
		}
		s.append("|");
	}
}