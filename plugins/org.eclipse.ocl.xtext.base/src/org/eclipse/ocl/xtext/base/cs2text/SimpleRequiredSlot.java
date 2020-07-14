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

import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

public class SimpleRequiredSlot extends AbstractRequiredSlots //implements Iterable<@NonNull RequiredSlots>
{
//	protected final @NonNull AssignedSerializationNode serializationNode;
	protected final @NonNull EClass eFeatureScope;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public SimpleRequiredSlot(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(ruleAnalysis);
		this.eFeatureScope = eFeatureScope;
		this.eStructuralFeature = eStructuralFeature;
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlots> getConjunction() {
		return Collections.singletonList(this);
	}

	@Override
	public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
		assert conjunctionIndex == 0;
		RequiredSlotsConjunction requiredSlotsConjunction = new RequiredSlotsConjunction(ruleAnalysis);
		requiredSlotsConjunction.accumulate(this, MultiplicativeCardinality.ONE);
		requiredSlotsConjunction.getConjunction();		// XXX eager
		return requiredSlotsConjunction;
	}

	@Override
	public int getConjunctionCount() {
		return 1;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction() {
		throw new UnsupportedOperationException();
	}

	public @NonNull EClass getEFeatureScope() {
		return eFeatureScope;
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append("[");
		s.append(multiplicativeCardinality);
		s.append("]");
	}
}