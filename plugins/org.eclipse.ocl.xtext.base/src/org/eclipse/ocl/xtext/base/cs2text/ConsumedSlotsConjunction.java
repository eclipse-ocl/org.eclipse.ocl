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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class ConsumedSlotsConjunction extends AbstractConsumedSlots
{
	protected final @NonNull RequiredSlotsConjunction requiredSlotsConjunction;
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size;
//	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2lower = new HashMap<>();
//	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2upper = new HashMap<>();
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2consumed = new HashMap<>();

	protected ConsumedSlotsConjunction(@NonNull RequiredSlotsConjunction requiredSlotsConjunction, @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size) {
		this.requiredSlotsConjunction = requiredSlotsConjunction;
		this.eFeature2size = eFeature2size;
	}

/*	public void addConsumedSlot(@NonNull SimpleConsumedSlot consumedSlot) {
		EStructuralFeature eStructuralFeature = consumedSlot.getEStructuralFeature();
		int lower = consumedSlot.getLower();
		int upper = consumedSlot.getUpper();
		Integer oldLower = eFeature2lower.get(eStructuralFeature);
		Integer oldUpper = eFeature2upper.get(eStructuralFeature);
		eFeature2lower.put(eStructuralFeature, lower + (oldLower != null ? oldLower.intValue() : 0));
		eFeature2upper.put(eStructuralFeature, upper + (oldUpper != null ? oldUpper.intValue() : 0));
	} */

	public @Nullable List<@NonNull SerializationNode> selectSerializedNodes(@NonNull RequiredSlotsConjunction conjunction, @NonNull EObject element) {
		Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = requiredSlotsConjunction.selectSerializedNodes(element, eFeature2size);
		if (variable2value == null) {
			conjunction.selectSerializedNodes(element, eFeature2size);
		}
		Iterable<@NonNull EStructuralFeature> eStructuralFeatures = conjunction.getEStructuralFeatures();
		List<@NonNull SerializationNode> serializedNodes = conjunction.getSerializedNodes();
		List<@NonNull SerializationNode> resultSerializedNodes = new ArrayList<>();
		for (@NonNull SerializationNode serializedNode : serializedNodes) {
			RequiredSlots requiredSlots = serializedNode.getRequiredSlots();
		//	int minRequiredLowerBound = 0;
		//	int minRequiredUpperBound = 0;
			int minRepeatCount = 0;
			int maxRepeatCount = 0;
			for (@NonNull EStructuralFeature eStructuralFeature : eStructuralFeatures) {
				int requiredQuantum = requiredSlots.getQuantum(eStructuralFeature);
				int requiredUpperBound = requiredSlots.getUpperBound(eStructuralFeature);
				if (requiredQuantum != 0) {
					Object object = element.eGet(eStructuralFeature);
					int actualCount;
					if (eStructuralFeature.isMany()) {
						actualCount = ((List<?>)object).size();
					}
					else if (object != null) {
						actualCount = 1;
					}
					else {
						actualCount = element.eIsSet(eStructuralFeature) ? 1 : 0;
					}
					Integer oldConsumed = eFeature2consumed.get(eStructuralFeature);
					if (oldConsumed != null) {
						actualCount -= oldConsumed.intValue();
					}
					int iteratedCount = actualCount / requiredQuantum;		// XXX not more than upperBound
					int requiredLowerBound = requiredSlots.getLowerBound(eStructuralFeature);
					if (requiredLowerBound > 0) {
						int minIteratedCount = actualCount / requiredLowerBound;
						if ((minRepeatCount == 0) || (minIteratedCount < minRepeatCount)) {
							minRepeatCount = minIteratedCount;
						}
					}
					//	int maxRequiredCount = requiredUpperBound > 0 ? actualCount / iteratedCount : actualCount;
					if ((maxRepeatCount == 0) || (iteratedCount < maxRepeatCount)) {
						maxRepeatCount = iteratedCount;
					}


				//	if ((minRequiredUpperBound == 0) && (requiredUpperBound > 0) && (requiredUpperBound < minRequiredUpperBound)) {
				//		minRequiredUpperBound = requiredUpperBound;
				//	}
				}
			}
			for (int i = 0; i < maxRepeatCount; i++) {
				resultSerializedNodes.add(serializedNode);
			}
			for (@NonNull EStructuralFeature eStructuralFeature : requiredSlots.getEStructuralFeatures()) {
				int requiredQuantum = requiredSlots.getQuantum(eStructuralFeature);
				int consumed = requiredQuantum * maxRepeatCount;
				Integer oldConsumed = eFeature2consumed.get(eStructuralFeature);
				eFeature2consumed.put(eStructuralFeature, consumed + (oldConsumed != null ? oldConsumed.intValue() : 0));
			}
		//	requiredSlots.get
		//			int count = serializedNode.
		}
		return resultSerializedNodes;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		List<@NonNull EStructuralFeature> features = new ArrayList<>(eFeature2consumed.keySet());
		Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		boolean isFirst = true;
		for (@NonNull EStructuralFeature feature : features) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(XtextGrammarUtil.getName(feature));
			Integer consumed = eFeature2consumed.get(feature);
			assert consumed != null;
			s.append("[");
			s.append(consumed);
			s.append("]");
			isFirst = false;
		}
		return  s.toString();
	}
}