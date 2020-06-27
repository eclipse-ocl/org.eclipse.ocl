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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class ConsumedSlotsConjunction extends AbstractConsumedSlots
{
	protected final @NonNull RequiredSlotsConjunction requiredSlotsConjunction;
	protected final @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternativesChoices;
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2lower = new HashMap<>();
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2upper = new HashMap<>();

	protected ConsumedSlotsConjunction(@NonNull RequiredSlotsConjunction requiredSlotsConjunction) {
		this.requiredSlotsConjunction = requiredSlotsConjunction;
		this.alternativesChoices = requiredSlotsConjunction.getAlternativesChoices();
	}

	public void addConsumedSlot(@NonNull SimpleConsumedSlot consumedSlot) {
		EStructuralFeature eStructuralFeature = consumedSlot.getEStructuralFeature();
		int lower = consumedSlot.getLower();
		int upper = consumedSlot.getUpper();
		Integer oldLower = eFeature2lower.get(eStructuralFeature);
		Integer oldUpper = eFeature2upper.get(eStructuralFeature);
		eFeature2lower.put(eStructuralFeature, lower + (oldLower != null ? oldLower.intValue() : 0));
		eFeature2upper.put(eStructuralFeature, upper + (oldUpper != null ? oldUpper.intValue() : 0));
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		List<@NonNull EStructuralFeature> features = new ArrayList<>(eFeature2lower.keySet());
		Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		boolean isFirst = true;
		for (@NonNull EStructuralFeature feature : features) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(XtextGrammarUtil.getName(feature));
			Integer lowerBound = eFeature2lower.get(feature);
			Integer upperBound = eFeature2upper.get(feature);
			assert (lowerBound != null) && (upperBound != null);
			XtextGrammarUtil.appendCardinality(s, lowerBound, upperBound);
			isFirst = false;
		}
		return  s.toString();
	}
}