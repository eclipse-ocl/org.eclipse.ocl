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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.UserSlotsAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize;

/**
 * A CardinalityExpression equates the sum of CardinailtyVariable products to the number of elements in an eStructuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class EStructuralFeatureCardinalityExpression extends AbstractCardinalityExpression
{
	protected final @NonNull EStructuralFeature eStructuralFeature;

	public EStructuralFeatureCardinalityExpression(@NonNull String name, /*@NonNull*/ EStructuralFeature eStructuralFeature) {
		super(name);
		assert eStructuralFeature != null;
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
	protected @NonNull SerializationMatchTermEStructuralFeatureSize createSizeCardinalitySolution() {
		return new SerializationMatchTermEStructuralFeatureSize(eStructuralFeature);
	}

	@Override
	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		throw new IllegalStateException();
	}

	@Override
	public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
		return null;
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
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