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
package org.eclipse.ocl.xtext.base.cs2text.user;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedRuleCallsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The DynamicSerializationRules identify the subset of the static SerializationRules that are compatiible with
 * the containment ancestry of an actual EObject instance of EClassfoor the static rules.
 */
public class DynamicSerializationRules
{
	protected final @NonNull EClass eClass;
	protected final @NonNull Iterable<@NonNull SerializationRule> serializationRules;

	public DynamicSerializationRules(@NonNull EClass eClass, @NonNull Iterable<@NonNull SerializationRule> serializationRules) {
		this.eClass = eClass;
		this.serializationRules = serializationRules;
	}

	public boolean allRulesNeedDefault(@NonNull EAttribute eAttribute) {
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			if (!serializationRule.getBasicSerializationRule().needsDefault(eAttribute)) {
				return false;
			}
		}
		return true;
	}


	public @Nullable DynamicRuleMatch createDynamicRuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis, @Nullable AbstractRuleAnalysis targetRuleAnalysis) {
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			if ((targetRuleAnalysis == null) || ((ParserRuleAnalysis)targetRuleAnalysis).getSubRuleAnalysesClosure().contains(serializationRule.getRuleAnalysis())) {
				BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
				DynamicRuleMatch dynamicRuleMatch = basicSerializationRule.match(slotsAnalysis);
				if (dynamicRuleMatch != null) {
					return dynamicRuleMatch;
				}
			}
		}
		return null;
	}

	public void diagnose(@NonNull StringBuilder s, @NonNull UserSlotsAnalysis slotsAnalysis) {
		char c = 'A';
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			s.append("\n  [");
			s.append(c++);
			s.append("] ");
			serializationRule.toRuleString(s);
		}
		s.append("\n");
		c = 'A';
		s.append(String.format("%-30.30s%9s", "feature", "actual"));
	//	Set<@NonNull EStructuralFeature> allFeatures = new HashSet<>();
		for (@SuppressWarnings("unused") @NonNull SerializationRule serializationRule : serializationRules) {
			s.append(" [");
			s.append(c++);
			s.append("] ");
	//		for (@NonNull EStructuralFeature eStructuralFeature : serializationRule.getEStructuralFeatures()) {
	//			allFeatures.add(eStructuralFeature);
	//		}
		}
		List<@NonNull EStructuralFeature> sortedFeatures = Lists.newArrayList(slotsAnalysis.getEStructuralFeatures());
		Collections.sort(sortedFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EStructuralFeature eStructuralFeature : sortedFeatures) {
			s.append("\n");
			int size = slotsAnalysis.getSize(eStructuralFeature);
			s.append(String.format("%-30.30s%8d", eStructuralFeature.getName(), size));
			for (@NonNull SerializationRule serializationRule : serializationRules) {
				BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
				MultiplicativeCardinality multiplicativeCardinality = basicSerializationRule.getMultiplicativeCardinality(eStructuralFeature);
				s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
			}
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute)eStructuralFeature;
				Iterable<@NonNull EnumerationValue> enumerationValues = getEnumerationValues(eAttribute);
				if (enumerationValues != null) {
					List<@NonNull EnumerationValue> sortedEnumerationValues = Lists.newArrayList(enumerationValues);
					Collections.sort(sortedEnumerationValues, NameUtil.NAMEABLE_COMPARATOR);
					for (@NonNull EnumerationValue enumerationValue : sortedEnumerationValues) {
						int size2 = slotsAnalysis.getSize(eAttribute, enumerationValue);
						s.append(String.format("\n %-29.29s%8d", "'" + enumerationValue.getName() + "'", size2));
						for (@NonNull SerializationRule serializationRule : serializationRules) {
							BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
							MultiplicativeCardinality multiplicativeCardinality = basicSerializationRule.getMultiplicativeCardinality(eAttribute, enumerationValue);
							s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
						}
					}
				}
			}
			else {
				EReference eReference = (EReference)eStructuralFeature;
				Iterable<@NonNull ParserRuleAnalysis> assignedRuleAnalyses = getAssignedRuleAnalyses(eReference);
				if (assignedRuleAnalyses != null) {
					List<@NonNull ParserRuleAnalysis> sortedRuleAnalyses = Lists.newArrayList(assignedRuleAnalyses);
					Collections.sort(sortedRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
					for (@NonNull ParserRuleAnalysis ruleAnalysis : sortedRuleAnalyses) {
						int size2 = slotsAnalysis.getSize(eReference, ruleAnalysis);
						s.append(String.format("\n %-29.29s%8d", "'" + ruleAnalysis.getName() + "'", size2));
						for (@NonNull SerializationRule serializationRule : serializationRules) {
							BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
							MultiplicativeCardinality multiplicativeCardinality = basicSerializationRule.getMultiplicativeCardinality(eReference, ruleAnalysis);
							s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
						}
					}
				}
			}
		}
		s.append("\n");
	}

	private @Nullable Set<@NonNull ParserRuleAnalysis> gatherAssignedRuleAnalyses(@Nullable Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses,
			@Nullable Set<@NonNull ParserRuleAnalysis> assignedRuleAnalyses) {
		if (ruleAnalyses != null) {
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
				if (ruleAnalysis instanceof ParserRuleAnalysis) {
					if (assignedRuleAnalyses == null) {
						assignedRuleAnalyses = new HashSet<>();
					}
					assignedRuleAnalyses.add((ParserRuleAnalysis) ruleAnalysis);
				}
			}
		}
		return assignedRuleAnalyses;
	}

	public @Nullable Iterable<@NonNull ParserRuleAnalysis> getAssignedRuleAnalyses(@NonNull EReference eReference) {
		Set<@NonNull ParserRuleAnalysis> assignedRuleAnalyses = null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			assignedRuleAnalyses = getAssignedRuleAnalyses(eReference, serializationRule.getRootSerializationNode(), assignedRuleAnalyses);
		}
		return assignedRuleAnalyses;
	}

	private @Nullable Set<@NonNull ParserRuleAnalysis> getAssignedRuleAnalyses(@NonNull EReference eReference, @NonNull SerializationNode serializationNode, @Nullable Set<@NonNull ParserRuleAnalysis> assignedRuleAnalyses) {
		/* if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			if (assignedKeywordsSerializationNode.getEStructuralFeature() == eReference) {
				ParserRuleAnalysis enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
				if (!enumerationValue.isNull()) {
					ruleAnalyses.add(enumerationValue);
				}
			}
		}
		else*/ if ((serializationNode instanceof AssignedRuleCallSerializationNode) || (serializationNode instanceof AlternativeAssignedRuleCallsSerializationNode)) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			if (assignedSerializationNode.getEStructuralFeature() == eReference) {
				Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses = assignedSerializationNode.getAssignedRuleAnalyses();
				assignedRuleAnalyses = gatherAssignedRuleAnalyses(ruleAnalyses, assignedRuleAnalyses);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				assignedRuleAnalyses = getAssignedRuleAnalyses(eReference, nestedSerializationNode, assignedRuleAnalyses);
			}
		}
		return assignedRuleAnalyses;
	}

	public @Nullable Iterable<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		Set<@NonNull EnumerationValue> enumerationValues = null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			enumerationValues = getEnumerationValues(eAttribute, serializationRule.getRootSerializationNode(), enumerationValues);
		}
		return enumerationValues;
	}
	private @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute, @NonNull SerializationNode serializationNode, @Nullable Set<@NonNull EnumerationValue> enumerationValues) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			if (assignedKeywordsSerializationNode.getEStructuralFeature() == eAttribute) {
				EnumerationValue enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
				if (enumerationValues == null) {
					enumerationValues = new HashSet<>();
				}
				enumerationValues.add(enumerationValue);
			}
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			AssignedKeywordSerializationNode assignedKeywordSerializationNode = (AssignedKeywordSerializationNode)serializationNode;
			if (assignedKeywordSerializationNode.getEStructuralFeature() == eAttribute) {
				EnumerationValue enumerationValue = assignedKeywordSerializationNode.getEnumerationValue();
				if (enumerationValues == null) {
					enumerationValues = new HashSet<>();
				}
				enumerationValues.add(enumerationValue);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				enumerationValues = getEnumerationValues(eAttribute, nestedSerializationNode, enumerationValues);
			}
		}
		return enumerationValues;
	}



//	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
//		return serializationRules;
//	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

/*	public void toString(StringBuilder s, int i) {
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			s.append(" ");;
			StringUtil.appendIndentation(s, i);
			s.append("|&  ");
			serializationRule.toString(s, -1);
		}
	} */

	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isMany = Iterables.size(serializationRules) > 1;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
			if (isMany) {
				StringUtil.appendIndentation(s, depth+1);
			}
			else {
				s.append(" ");
			}
			s.append(serializationRule.getName());
			s.append(" - ");
			basicSerializationRule.toRuleString(s);
			basicSerializationRule.toSolutionString(s, depth+2);
		}
	}
}