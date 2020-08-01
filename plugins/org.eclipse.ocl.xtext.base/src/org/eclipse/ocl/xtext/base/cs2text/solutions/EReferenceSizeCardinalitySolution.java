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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.user.RuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

/**
 * An EAttributeSizeCardinalitySolution contributes the actual (constant) size of a, possibly enumerated, slot to an
 * expression determining the cardinality of a SerializationRule term.
 */
public class EReferenceSizeCardinalitySolution extends AbstractCardinalitySolution
{
	protected final @NonNull EReference eReference;
	protected final @Nullable ParserRuleAnalysis ruleAnalysis;

	public EReferenceSizeCardinalitySolution(@NonNull EReference eReference, @Nullable ParserRuleAnalysis ruleAnalysis) {
		this.eReference = eReference;
		this.ruleAnalysis = ruleAnalysis;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return ruleMatch.getSize(eReference, ruleAnalysis);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof EReferenceSizeCardinalitySolution)) {
			return false;
		}
		EReferenceSizeCardinalitySolution that = (EReferenceSizeCardinalitySolution) obj;
		if (this.eReference != that.eReference) return false;
		if (!ClassUtil.safeEquals(this.ruleAnalysis, that.ruleAnalysis)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + eReference.hashCode() + (ruleAnalysis != null ? ruleAnalysis.hashCode() * 7 : 0);
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
		s.append(eReference.getName());
		ParserRuleAnalysis ruleAnalysis2 = ruleAnalysis;
		if (ruleAnalysis2 != null) {
			s.append(".'");
			s.append(ruleAnalysis2.getName());
			s.append("'");
		}
		s.append("|");
	}
}