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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

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
import org.eclipse.ocl.xtext.base.cs2text.runtime.EClassValue.SerializationRule_SegmentsList;

import com.google.common.collect.Lists;

/**
 * The DynamicSerializationRules identify the subset of the static SerializationRules that are compatiible with
 * the containment ancestry of an actual EObject instance of EClass for the static rules.
 */
public class DynamicSerializationRules
{
	protected final @NonNull EClassValue eClassValue;
	protected final @NonNull SerializationRule_SegmentsList @NonNull [] serializationRuleSegmentsLists;

	public DynamicSerializationRules(@NonNull EClassValue eClassValue, @NonNull SerializationRule_SegmentsList @NonNull [] serializationRuleSegmentsLists) {
		this.eClassValue = eClassValue;
		this.serializationRuleSegmentsLists = serializationRuleSegmentsLists;
	}

	public boolean allRulesNeedDefault(@NonNull EAttribute eAttribute) {
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			if (!serializationRuleSegmentsList.getSerializationRule().needsDefault(eAttribute)) {
				return false;
			}
		}
		return true;
	}

	public @Nullable DynamicRuleMatch createDynamicRuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis, @Nullable ParserRuleValue targetParserRuleValue) {
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
			if ((targetParserRuleValue == null) || targetParserRuleValue.subParserRuleValueClosureContains(serializationRule.getRuleValueIndex())) {
				DynamicRuleMatch dynamicRuleMatch = serializationRule.match(slotsAnalysis, serializationRuleSegmentsList.getStaticSegments());
				if (dynamicRuleMatch != null) {
					return dynamicRuleMatch;
				}
			}
		}
		return null;
	}

	public void diagnose(@NonNull StringBuilder s, @NonNull UserSlotsAnalysis slotsAnalysis) {
		char c = 'A';
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
			s.append("\n  [");
			s.append(c++);
			s.append("] ");
			serializationRule.toRuleString(s);
		}
		s.append("\n");
		c = 'A';
		s.append(String.format("%-30.30s%9s", "feature", "actual"));
	//	Set<@NonNull EStructuralFeature> allFeatures = new HashSet<>();
		for (@SuppressWarnings("unused") @NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			s.append(" [");
			s.append(c++);
			s.append("]");
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
			for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
				SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
				GrammarCardinality grammarCardinality = serializationRule.getGrammarCardinality(eStructuralFeature);
				s.append(String.format("%4s", grammarCardinality != null ? grammarCardinality.toString() : "0"));
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
						for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
							SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
							GrammarCardinality grammarCardinality = serializationRule.getGrammarCardinality(eAttribute, enumerationValue);
							s.append(String.format("%4s", grammarCardinality != null ? grammarCardinality.toString() : "0"));
						}
					}
				}
			}
			else {
				EReference eReference = (EReference)eStructuralFeature;
				GrammarRuleVector assignedRuleValueIndexes = getAssignedRuleValueIndexes(eReference);
				if (assignedRuleValueIndexes != null) {
					SerializationGrammarAnalysis grammarAnalysis = slotsAnalysis.getModelAnalysis().getGrammarAnalysis();
					for (int ruleValueIndex : assignedRuleValueIndexes) {
						ParserRuleValue ruleValue = (ParserRuleValue)grammarAnalysis.getRuleValue(ruleValueIndex);
						int size2 = slotsAnalysis.getSize(eReference, ruleValue);
						s.append(String.format("\n %-29.29s%8d", "'" + ruleValue.getName() + "'", size2));
						for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
							SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
							GrammarCardinality grammarCardinality = serializationRule.getGrammarCardinality(eReference, ruleValue);
							s.append(String.format("%4s", grammarCardinality != null ? grammarCardinality.toString() : "0"));
						}
					}
				}
			}
		}
		s.append("\n");
	}

	public @Nullable GrammarRuleVector getAssignedRuleValueIndexes(@NonNull EReference eReference) {
		GrammarRuleVector allAssignedRuleValueIndexes = null;
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
			GrammarRuleVector assignedRuleValueIndexes = serializationRule.getAssignedRuleValueIndexes(eReference);
			if (assignedRuleValueIndexes != null) {
				if (allAssignedRuleValueIndexes == null) {
					allAssignedRuleValueIndexes = new GrammarRuleVector();
				}
				allAssignedRuleValueIndexes.setAll(assignedRuleValueIndexes);
			}
		}
		return allAssignedRuleValueIndexes;
	}

	public @Nullable Iterable<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		Set<@NonNull EnumerationValue> allEnumerationValues = null;
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
			@NonNull EnumerationValue[] enumerationValues = serializationRule.getEnumerationValues(eAttribute);
			if (enumerationValues != null) {
				if (allEnumerationValues == null) {
					allEnumerationValues = new HashSet<>();
				}
				for (@NonNull EnumerationValue enumerationValue : enumerationValues) {
					allEnumerationValues.add(enumerationValue);
				}
			}
		}
		return allEnumerationValues;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		@SuppressWarnings("null")
		@NonNull String castString = (@NonNull String)s.toString();
		return castString;
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
		EClass eClass = eClassValue.getEClass();
		s.append(eClass.getEPackage().getName());
		s.append("::");
		s.append(eClass.getName());
	//	boolean isMany = Iterables.size(serializationRules) > 1;
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
	//		if (isMany) {
				StringUtil.appendIndentation(s, depth+1);
	//		}
	//		else {
	//			s.append(" ");
	//		}
	//		s.append(serializationRule.getName());
	//		s.append(" - ");
		//	serializationRuleAnalysis.toRuleString(s);
			serializationRule.toSolutionString(s, depth+2);
		}
	}
}