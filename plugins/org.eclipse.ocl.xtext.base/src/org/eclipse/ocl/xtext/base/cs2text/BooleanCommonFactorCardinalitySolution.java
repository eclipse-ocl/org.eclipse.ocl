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

public class BooleanCommonFactorCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @Nullable String value;
	protected final int subtrahend;

	public BooleanCommonFactorCardinalitySolution(@NonNull EStructuralFeature eStructuralFeature, @Nullable String value, int subtrahend) {
		this.eStructuralFeature = eStructuralFeature;
		this.value = value;
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
		if (!ClassUtil.safeEquals(this.value, that.value)) return false;
		return true;
	}

	@Override
	public @NonNull Integer getIntegerSolution(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		int intSize = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, value);
		return (intSize - subtrahend) > 0 ? 1 : 0;
	}

	@Override
	public int hashCode() {
		return eStructuralFeature.hashCode() + 3 * subtrahend + (value != null ? value.hashCode() * 7 : 0);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("|");
		s.append(eStructuralFeature.getName());
		if (value != null) {
			s.append(".\"");
			s.append(value);
			s.append("\"");
		}
		s.append("|>");
		s.append(subtrahend);
	}
}