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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class AdjustedFeatureCardinalitySolution  extends AbstractCardinalitySolution
{
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @Nullable String value;
	protected final int subtrahend;
	protected final int divisor;

	public AdjustedFeatureCardinalitySolution(@NonNull EStructuralFeature eStructuralFeature, @Nullable String value, int subtrahend, int divisor) {
		this.eStructuralFeature = eStructuralFeature;
		this.value = value;
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
		if (!ClassUtil.safeEquals(this.value, that.value)) return false;
		return true;
	}

	@Override
	public @NonNull Integer getIntegerSolution(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		int intSize = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, value);
		return (intSize - subtrahend) / divisor;
	}

	@Override
	public int hashCode() {
		return eStructuralFeature.hashCode() + 3 * subtrahend + 7 * (divisor-1) + (value != null ? value.hashCode() * 7 : 0);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		if (((subtrahend != 0)) && (divisor != 1)) {
			s.append("(");
		}
		s.append("|");
		s.append(eStructuralFeature.getName());
		if (value != null) {
			s.append(".\"");
			s.append(value);
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