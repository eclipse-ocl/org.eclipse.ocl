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
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

import com.google.common.collect.Lists;

/**
 * The DynamicSerializationRules identify the subset of the static SerializationRules that are compatiible with
 * the containment ancestry of an actual EObject instance of EClass for the static rules.
 */
public class DynamicSerializationRules
{
	protected final @NonNull EClassData eClassData;
	protected final @NonNull SerializationRule @NonNull [] serializationRules;

	public DynamicSerializationRules(@NonNull EClassData eClassData, @NonNull SerializationRule @NonNull [] serializationRules) {
		this.eClassData = eClassData;
		this.serializationRules = serializationRules;
	}

//	public DynamicSerializationRules(@NonNull EClass eClass2,
//			@NonNull SerializationRule @NonNull [] serializationRules2) {
		// TODO Auto-generated constructor stub
//	}

	public boolean allRulesNeedDefault(@NonNull EAttribute eAttribute) {
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			if (!serializationRule.getSerializationRuleAnalysis().needsDefault(eAttribute)) {
				return false;
			}
		}
		return true;
	}

	public @Nullable DynamicRuleMatch createDynamicRuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis, @Nullable ParserRuleValue targetParserRuleValue) {
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			if ((targetParserRuleValue == null) || targetParserRuleValue.subParserRuleValueClosureContains(serializationRule.getRuleValueIndex())) {
				DynamicRuleMatch dynamicRuleMatch = serializationRule.match(slotsAnalysis);
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
			for (@NonNull SerializationRule serializationRule : serializationRules) {
				SerializationRuleAnalysis serializationRuleAnalysis = serializationRule.getSerializationRuleAnalysis();
				MultiplicativeCardinality multiplicativeCardinality = serializationRuleAnalysis.getMultiplicativeCardinality(eStructuralFeature);
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
							SerializationRuleAnalysis serializationRuleAnalysis = serializationRule.getSerializationRuleAnalysis();
							MultiplicativeCardinality multiplicativeCardinality = serializationRuleAnalysis.getMultiplicativeCardinality(eAttribute, enumerationValue);
							s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
						}
					}
				}
			}
			else {
				EReference eReference = (EReference)eStructuralFeature;
				Iterable<@NonNull ParserRuleValue> assignedRuleValues = getAssignedRuleValues(eReference);
				if (assignedRuleValues != null) {
					List<@NonNull ParserRuleValue> sortedRuleValues = Lists.newArrayList(assignedRuleValues);
					Collections.sort(sortedRuleValues, NameUtil.NAMEABLE_COMPARATOR);
					for (@NonNull ParserRuleValue ruleValue : sortedRuleValues) {
						int size2 = slotsAnalysis.getSize(eReference, ruleValue);
						s.append(String.format("\n %-29.29s%8d", "'" + ruleValue.getName() + "'", size2));
						for (@NonNull SerializationRule serializationRule : serializationRules) {
							SerializationRuleAnalysis serializationRuleAnalysis = serializationRule.getSerializationRuleAnalysis();
							MultiplicativeCardinality multiplicativeCardinality = serializationRuleAnalysis.getMultiplicativeCardinality(eReference, ruleValue);
							s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
						}
					}
				}
			}
		}
		s.append("\n");
	}

	public @Nullable Iterable<@NonNull ParserRuleValue> getAssignedRuleValues(@NonNull EReference eReference) {
		Set<@NonNull ParserRuleValue> allAssignedRuleValues = null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Set<@NonNull ParserRuleValue> assignedRuleValues = serializationRule.getAssignedRuleValues(eReference);
			if (assignedRuleValues != null) {
				if (allAssignedRuleValues == null) {
					allAssignedRuleValues = new HashSet<>();
				}
				allAssignedRuleValues.addAll(assignedRuleValues);
			}
		}
		return allAssignedRuleValues;
	}

	public @Nullable Iterable<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		Set<@NonNull EnumerationValue> allEnumerationValues = null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Set<@NonNull EnumerationValue> enumerationValues = serializationRule.getEnumerationValues(eAttribute);
			if (enumerationValues != null) {
				if (allEnumerationValues == null) {
					allEnumerationValues = new HashSet<>();
				}
				allEnumerationValues.addAll(enumerationValues);
			}
		}
		return allEnumerationValues;
	}

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
		EClass eClass = eClassData.getEClass();
		s.append(eClass.getEPackage().getName());
		s.append("::");
		s.append(eClass.getName());
	//	boolean isMany = Iterables.size(serializationRules) > 1;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
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