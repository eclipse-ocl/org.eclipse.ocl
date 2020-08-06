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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicSerializationRules;

/**
 * The (static) SerializationRules identify the alternative rules that may be used to serialize a given EClass.
 * Once the actual EObject instance of EClass is known a DynamicSerializationRules identifies the subset of the
 * rules with compatiible containment ancestry.
 */
public class SerializationRules
{
	protected final @NonNull EClass eClass;
	protected final @NonNull Iterable<@NonNull SerializationRule> serializationRules;
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatingRuleAnalyses = null;

	public SerializationRules(@NonNull EClass eClass, @NonNull Iterable<@NonNull SerializationRule> serializationRules) {
		this.eClass = eClass;
		this.serializationRules = serializationRules;
	}

	public @NonNull DynamicSerializationRules createDynamicSerializationRules(@Nullable Set<@NonNull AbstractRuleAnalysis> targetRuleAnalyses) {
		if (targetRuleAnalyses == null)  {
			return new DynamicSerializationRules(eClass, serializationRules);
		}
		List<@NonNull SerializationRule> newSerializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			ParserRuleAnalysis ruleAnalysis = serializationRule.getRuleAnalysis();
			if (targetRuleAnalyses.contains(ruleAnalysis)) {
				newSerializationRules.add(serializationRule);
				Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> ruleDiscriminatingEReferences = ruleAnalysis.getEReference2DiscriminatingRuleAnalyses();
				if (ruleDiscriminatingEReferences != null) {
					Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatingRuleAnalyses2 = eReference2disciminatingRuleAnalyses;
					if (eReference2disciminatingRuleAnalyses2 == null) {
						eReference2disciminatingRuleAnalyses = eReference2disciminatingRuleAnalyses2 = new HashMap<>();
					}
					for (Map.Entry<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> entry : ruleDiscriminatingEReferences.entrySet()) {
						EReference eReference = entry.getKey();
						List<@NonNull ParserRuleAnalysis> list = eReference2disciminatingRuleAnalyses2.get(eReference);
						if (list == null) {
							list = new ArrayList<>();
							assert eReference.isOrdered();
							eReference2disciminatingRuleAnalyses2.put(eReference, list);
						}
						for (@NonNull ParserRuleAnalysis ruleAnalysis2 : entry.getValue()) {
							if (!list.contains(ruleAnalysis2)) {
								list.add(ruleAnalysis2);
							}
						}
					}
				}
			}
		}
		return new DynamicSerializationRules(eClass, newSerializationRules);
		/*	List<@NonNull SerializationRule> serializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : serializationRules2.getSerializationRules()) {
			ParserRuleAnalysis ruleAnalysis = serializationRule.getRuleAnalysis();
			if (targetRuleAnalyses.contains(ruleAnalysis)) {
				serializationRules.add(serializationRule);
				Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> ruleDiscriminatingEReferences = ruleAnalysis.getEReference2DiscriminatingRuleAnalyses();
				if (ruleDiscriminatingEReferences != null) {
					Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatingRuleAnalyses2 = eReference2disciminatingRuleAnalyses;
					if (eReference2disciminatingRuleAnalyses2 == null) {
						eReference2disciminatingRuleAnalyses = eReference2disciminatingRuleAnalyses2 = new HashMap<>();
					}
					for (Map.Entry<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> entry : ruleDiscriminatingEReferences.entrySet()) {
						EReference eReference = entry.getKey();
						List<@NonNull ParserRuleAnalysis> list = eReference2disciminatingRuleAnalyses2.get(eReference);
						if (list == null) {
							list = new ArrayList<>();
							eReference2disciminatingRuleAnalyses2.put(eReference, list);
						}
						for (@NonNull ParserRuleAnalysis ruleAnalysis2 : entry.getValue()) {
							if (!list.contains(ruleAnalysis2)) {
								list.add(ruleAnalysis2);
							}
						}
					}
				}
			}
		}
		return new DynamicSerializationRules(eClass, serializationRules); */
	}

	/**
	 * Return the rule analyses assigned by one or more of the serialization rules that can assign eContainmentFeature.
	 */
	public @NonNull Set<@NonNull AbstractRuleAnalysis> getAssignedTargetRuleAnalyses(@NonNull EReference eContainmentFeature) {
		Set<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = new HashSet<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = serializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleAnalyses.add(targetRuleAnalysis);
					}
				}
			}
		}
		return targetRuleAnalyses;
	}

	public @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> getEReference2disciminatedRuleAnalyses() {
		return eReference2disciminatingRuleAnalyses;
	}

	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
		return serializationRules;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(StringBuilder s, int i) {
		s.append(eClass.getEPackage().getName());
		s.append("::");
		s.append(eClass.getName());
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			StringUtil.appendIndentation(s, i);
			s.append("|&  ");
			serializationRule.toString(s, -1);
		}
	}
}