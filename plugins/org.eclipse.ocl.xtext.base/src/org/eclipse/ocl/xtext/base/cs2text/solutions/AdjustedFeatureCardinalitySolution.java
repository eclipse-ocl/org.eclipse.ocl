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
import org.eclipse.ocl.xtext.base.cs2text.CardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;

public class AdjustedFeatureCardinalitySolution  extends AbstractCardinalitySolution
{
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull EnumerationValue enumerationValue;
	protected final int subtrahend;
	protected final int divisor;

	public AdjustedFeatureCardinalitySolution(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue, int subtrahend, int divisor) {
		this.eStructuralFeature = eStructuralFeature;
		this.enumerationValue = enumerationValue;
		this.subtrahend = subtrahend;
		this.divisor = divisor;
		assert subtrahend >= 0;
		assert divisor >= 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AdjustedFeatureCardinalitySolution)) {
			return false;
		}
		AdjustedFeatureCardinalitySolution that = (AdjustedFeatureCardinalitySolution) obj;
		if (this.eStructuralFeature != that.eStructuralFeature) return false;
		if (this.subtrahend != that.subtrahend) return false;
		if (this.divisor != that.divisor) return false;
		if (!this.enumerationValue.equals(that.enumerationValue)) return false;
		return true;
	}

	@Override
	public @NonNull Integer getIntegerSolution(@NonNull UserSlotsAnalysis slotsAnalysis) {
		int intSize = CardinalityExpression.getSize(slotsAnalysis, eStructuralFeature, enumerationValue);
		return (intSize - subtrahend) / divisor;
	}

	@Override
	public int hashCode() {
		return eStructuralFeature.hashCode() + 3 * subtrahend + 7 * (divisor-1) + enumerationValue.hashCode() * 7;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		if (((subtrahend != 0)) && (divisor != 1)) {
			s.append("(");
		}
		s.append("|");
		s.append(eStructuralFeature.getName());
		if (!enumerationValue.isNull()) {
			s.append(".\"");
			s.append(enumerationValue.getName());
			s.append("\"");
		}
		s.append("|");
		if (subtrahend != 0) {
			s.append(" - ");
			s.append(subtrahend);
		}
		if (((subtrahend != 0)) && (divisor != 1)) {
			s.append(")");
		}
		if (divisor != 1) {
			s.append(" / ");
			s.append(divisor);
		}
	}
}