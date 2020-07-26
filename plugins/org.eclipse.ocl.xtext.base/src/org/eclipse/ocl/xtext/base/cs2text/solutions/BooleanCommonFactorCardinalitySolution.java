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
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;

public class BooleanCommonFactorCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull EnumerationValue enumerationValue;
	protected final int subtrahend;

	public BooleanCommonFactorCardinalitySolution(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue, int subtrahend) {
		this.eStructuralFeature = eStructuralFeature;
		this.enumerationValue = enumerationValue;
		this.subtrahend = subtrahend;
		assert subtrahend >= 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof BooleanCommonFactorCardinalitySolution)) {
			return false;
		}
		BooleanCommonFactorCardinalitySolution that = (BooleanCommonFactorCardinalitySolution) obj;
		if (this.eStructuralFeature != that.eStructuralFeature) return false;
		if (this.subtrahend != that.subtrahend) return false;
		if (!this.enumerationValue.equals(that.enumerationValue)) return false;
		return true;
	}

	@Override
	public @NonNull Integer basicGetIntegerSolution(@NonNull UserSlotsAnalysis slotsAnalysis, @Nullable Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value) {
		int intSize = CardinalityExpression.getSize(slotsAnalysis, eStructuralFeature, enumerationValue);
		return (intSize - subtrahend) > 0 ? 1 : 0;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + eStructuralFeature.hashCode() + 3 * subtrahend + enumerationValue.hashCode() * 7;
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
		s.append("|>");
		s.append(subtrahend);
	}
}