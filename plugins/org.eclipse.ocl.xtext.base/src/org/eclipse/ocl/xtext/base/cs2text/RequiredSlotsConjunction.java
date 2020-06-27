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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class RequiredSlotsConjunction extends AbstractRequiredSlots
{
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2lower = new HashMap<>();
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2upper = new HashMap<>();
	private @Nullable List<@NonNull SimpleRequiredSlot> conjunction = null;
	private @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = null;

	protected RequiredSlotsConjunction() {}

	public void accumulate(@NonNull AlternativesSerializationNode alternatives, @Nullable SerializationNode choice) {
		Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice2 = alternatives2choice;
		if (alternatives2choice2 == null) {
			alternatives2choice2 = alternatives2choice = new HashMap<>();
		}
		boolean hadKey = alternatives2choice2.containsKey(alternatives);
		SerializationNode oldChoice = alternatives2choice2.put(alternatives, choice);
		assert !hadKey || (oldChoice == choice);
	}

	public void accumulate(@NonNull RequiredSlotsConjunction innerConjunction, @NonNull String cardinality) {
		for (@NonNull SimpleRequiredSlot simpleRequiredSlot : innerConjunction.getConjunction()) {
			accumulate(simpleRequiredSlot, cardinality);
		}
		Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternativesChoices = innerConjunction.getAlternativesChoices();
		if (alternativesChoices != null) {
			for (Entry<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> entry : alternativesChoices.entrySet()) {
				accumulate(entry.getKey(), entry.getValue());
			}
		}
	//	getConjunction();		// XXX eager
	}

	public void accumulate(@NonNull SimpleRequiredSlot requiredSlot, @NonNull String cardinality) {
		EStructuralFeature eStructuralFeature = requiredSlot.getEStructuralFeature();
		if ("ownedProperties".equals(eStructuralFeature.getName())) {
			getClass();	// XXX
		}
		int lower = requiredSlot.getLowerBound();
		int upper = requiredSlot.getUpperBound();
		if ("*".equals(cardinality)) {
			lower = 0;
			upper = -1;
		}
		else if ("?".equals(cardinality)) {
			lower = 0;
		}
		else if ("+".equals(cardinality)) {
			upper = -1;
		}
		Integer oldLower = eFeature2lower.get(eStructuralFeature);
		Integer oldUpper = eFeature2upper.get(eStructuralFeature);
		int newLower = oldLower != null ? oldLower.intValue() : 0;
		int newUpper = oldUpper != null ? oldUpper.intValue() : 0;
		newLower += lower;
		newUpper = (newUpper < 0) || (upper < 0) ? -1 : (newUpper + upper);
	//	assert newLower < 2;
	//	assert newUpper < 2;
		eFeature2lower.put(eStructuralFeature, newLower);
		eFeature2upper.put(eStructuralFeature, newUpper);
	}

	public @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> getAlternativesChoices() {
		return alternatives2choice;
	}

	@Override
	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunction() {
		List<@NonNull SimpleRequiredSlot> conjunction = this.conjunction;
		if (conjunction == null) {
			this.conjunction = conjunction = new ArrayList<>();
			List<@NonNull EStructuralFeature> features = new ArrayList<>(eFeature2lower.keySet());
			Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
			for (@NonNull EStructuralFeature eStructuralFeature : features) {
				Integer lower = eFeature2lower.get(eStructuralFeature);
				Integer upper = eFeature2upper.get(eStructuralFeature);
				assert (lower != null) && (upper != null);
				EClass eFeatureScope = XtextGrammarUtil.getEContainingClass(eStructuralFeature);		// XXX do we need scope ??
				conjunction.add(new SimpleRequiredSlot(eFeatureScope, eStructuralFeature, lower, upper));
			}
		}
		return conjunction;
	}

	@Override
	public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
		assert conjunctionIndex == 0;
		return this;
	}

	@Override
	public int getConjunctionCount() {
		return 1;
	}

//	@Override
//	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunctionTerms(int conjunctionIndex) {
//		return getConjunction();
//	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction() {
		return Collections.singletonList(this);
	}

	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getTerms() {
		return getConjunction();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
	//	List<@NonNull SimpleRequiredSlot> conjunction = this.conjunction;
	//	if (conjunction != null) {
		boolean isFirst = true;
		for (@NonNull SimpleRequiredSlot requiredSlot : getConjunction()) {	// XXX lazy
			if (!isFirst) {
				s.append(" & ");
			}
			requiredSlot.toString(s, depth);
			isFirst = false;
		}
	//	}
	}
}