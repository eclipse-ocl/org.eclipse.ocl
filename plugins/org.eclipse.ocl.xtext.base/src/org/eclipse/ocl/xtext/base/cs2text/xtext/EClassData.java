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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicSerializationRules;

public class EClassData implements Nameable
{
	protected final @NonNull String name;
	protected final @NonNull EClass eClass;
	protected final @NonNull SerializationRule @NonNull [] serializationRules;
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleValue>> eReference2disciminatingRuleValues = null;
	private final @Nullable Map<@NonNull EReference, @NonNull Set<@NonNull AbstractRuleValue>> eContainmentFeature2assignedTargetRuleValues;

	public EClassData(@NonNull String name, /*@NonNull*/ EClass eClass, @NonNull SerializationRule @NonNull [] serializationRules,
			@Nullable Map<@NonNull EReference, @NonNull Set<@NonNull AbstractRuleValue>> eContainmentFeature2assignedTargetRuleValues) {
		assert eClass != null;
		this.name = name;
		this.eClass = eClass;
		this.serializationRules = serializationRules;
		this.eContainmentFeature2assignedTargetRuleValues = eContainmentFeature2assignedTargetRuleValues;
	}

	public @NonNull DynamicSerializationRules createDynamicSerializationRules(@Nullable IndexVector targetRuleValueIndexes) {
		if (targetRuleValueIndexes == null)  {
			return new DynamicSerializationRules(this, serializationRules);
		}
		List<@NonNull SerializationRule> newSerializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			ParserRuleAnalysis ruleAnalysis = serializationRule.getRuleAnalysis();
			if (targetRuleValueIndexes.test(ruleAnalysis.getRuleValue().getIndex())) {
				newSerializationRules.add(serializationRule);
				Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> ruleDiscriminatingEReferences = ruleAnalysis.getEReference2DiscriminatingRuleAnalyses();
				if (ruleDiscriminatingEReferences != null) {
					Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleValue>> eReference2disciminatingRuleValues2 = eReference2disciminatingRuleValues;
					if (eReference2disciminatingRuleValues2 == null) {
						eReference2disciminatingRuleValues = eReference2disciminatingRuleValues2 = new HashMap<>();
					}
					for (Map.Entry<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> entry : ruleDiscriminatingEReferences.entrySet()) {
						EReference eReference = entry.getKey();
						List<@NonNull ParserRuleValue> disciminatingRuleValues = eReference2disciminatingRuleValues2.get(eReference);
						if (disciminatingRuleValues == null) {
							disciminatingRuleValues = new ArrayList<>();
							assert eReference.isOrdered();
							eReference2disciminatingRuleValues2.put(eReference, disciminatingRuleValues);
						}
						for (@NonNull ParserRuleAnalysis ruleAnalysis2 : entry.getValue()) {
							ParserRuleValue ruleValue = ruleAnalysis2.getRuleValue();
							if (!disciminatingRuleValues.contains(ruleValue)) {
								disciminatingRuleValues.add(ruleValue);
							}
						}
					}
				}
			}
		}
		return new DynamicSerializationRules(this, newSerializationRules.toArray(new @NonNull SerializationRule[newSerializationRules.size()]));
	}

	/**
	 * Return the rule analyses assigned by one or more of the serialization rules that can assign eContainmentFeature.
	 */
	public @Nullable Set<@NonNull AbstractRuleValue> getAssignedTargetRuleValues(@NonNull EReference eContainmentFeature) {
	/*	Set<@NonNull AbstractRuleValue> targetRuleValues = new HashSet<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = serializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleValues.add(targetRuleAnalysis.getRuleValue());
					}
				}
			}
		}
		return targetRuleValues; */
		return eContainmentFeature2assignedTargetRuleValues != null ? eContainmentFeature2assignedTargetRuleValues.get(eContainmentFeature) : null;
	}
/*	public @NonNull IndexVector getAssignedTargetRuleValueIndexes(@NonNull EReference eContainmentFeature) {
		IndexVector targetRuleValueIndexes = new IndexVector();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = serializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleValueIndexes.set(targetRuleAnalysis.getIndex());
					}
				}
			}
		}
		return targetRuleValueIndexes;
	} */

	public @NonNull EClass getEClass() {
		return eClass;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull SerializationRule @NonNull [] getSerializationRules() {
		return serializationRules;
	}

	@Override
	public @NonNull String toString() {
		return name;
	}
}