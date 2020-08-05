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
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class EStructuralFeatureCardinalityExpression extends AbstractCardinalityExpression
{
	protected final @NonNull EStructuralFeature eStructuralFeature;

	public EStructuralFeatureCardinalityExpression(@NonNull String name, @NonNull EStructuralFeature eStructuralFeature) {
		super(name);
		this.eStructuralFeature = eStructuralFeature;
	}

	@Override
	public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
		int requiredCount = solve(dynamicRuleMatch);
		int actualCount = slotsAnalysis.getSize(eStructuralFeature);
		if (requiredCount != actualCount) {
			return false;
		}
		return true;
	}

	@Override
	protected @NonNull EStructuralFeatureSizeCardinalitySolution createSizeCardinalitySolution() {
		return new EStructuralFeatureSizeCardinalitySolution(eStructuralFeature);
	}

	@Override
	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		throw new IllegalStateException();
	}

	@Override
	public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
		return null;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eStructuralFeature.getName());
		s.append("| = ");
		appendSumOfProducts(s);
	}
}