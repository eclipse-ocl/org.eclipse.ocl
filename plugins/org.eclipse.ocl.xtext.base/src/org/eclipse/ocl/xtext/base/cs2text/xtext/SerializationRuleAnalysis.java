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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarRuleVector;
import org.eclipse.ocl.xtext.base.cs2text.runtime.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.ToDebugString;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.ToDebugString.ToDebugStringable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;

import com.google.common.collect.Iterables;

public class SerializationRuleAnalysis implements Nameable, ToDebugStringable
{
	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	private @Nullable EClass producedEClass = null;

	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugSring = new ToDebugString(this){};

	/**
	 * The variables, expressions and solutions to determine if an actual user element matches.
	 */
	private @Nullable StaticRuleMatch staticRuleMatch = null;

	/**
	 * The subidioms to decorate each node duringf serialization.
	 */
	private @Nullable Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = null;

	private @Nullable Map<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> eAttribute2enumerationValues = null;
	private @Nullable Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes = null;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality = null;


	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality = null;

	private @Nullable SerializationRule runtime = null;

	public SerializationRuleAnalysis(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.rootSerializationNode = rootSerializationNode;
		if ("PackageCS".equals(ruleAnalysis.getRuleName()) ) {
			getClass();
		}
	}

	public void analyzeAssignment(@NonNull EAttribute eAttribute, @Nullable EnumerationValue enumerationValue, @NonNull GrammarCardinality netGrammarCardinality) {
		Map<@NonNull EAttribute, @NonNull Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
		if (eAttribute2enumerationValue2grammarCardinality2 == null) {
			eAttribute2enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality2 = new HashMap<>();
		}
		Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality2.get(eAttribute);
		if (enumerationValue2grammarCardinality == null) {
			enumerationValue2grammarCardinality = new HashMap<>();
			eAttribute2enumerationValue2grammarCardinality2.put(eAttribute, enumerationValue2grammarCardinality);
		}
		GrammarCardinality oldGrammarCardinality = enumerationValue2grammarCardinality.get(enumerationValue);
		GrammarCardinality newGrammarCardinality = refineGrammarCardinality(netGrammarCardinality, oldGrammarCardinality);
		enumerationValue2grammarCardinality.put(enumerationValue, newGrammarCardinality);
	}

	public void analyzeAssignment(@NonNull EReference eReference, @NonNull Integer @Nullable [] ruleIndexes, @NonNull GrammarCardinality netGrammarCardinality) {
		Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 == null) {
			eReference2ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality2 = new HashMap<>();
		}
		Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality2.get(eReference);
		if (ruleAnalysis2grammarCardinality == null) {
			ruleAnalysis2grammarCardinality = new HashMap<>();
			eReference2ruleAnalysis2grammarCardinality2.put(eReference, ruleAnalysis2grammarCardinality);
		}
		if (ruleIndexes != null) {
			for (@NonNull Integer ruleIndex : ruleIndexes) {
				@NonNull AbstractRuleAnalysis ruleAnalysis2 = ruleAnalysis.getGrammarAnalysis().getRuleAnalysis(ruleIndex);
				if (ruleAnalysis2 instanceof ParserRuleAnalysis) {
					GrammarCardinality oldGrammarCardinality = ruleAnalysis2grammarCardinality.get(ruleAnalysis2);
					GrammarCardinality newGrammarCardinality = refineGrammarCardinality(netGrammarCardinality, oldGrammarCardinality);
					ruleAnalysis2grammarCardinality.put((ParserRuleAnalysis) ruleAnalysis2, newGrammarCardinality);
				}
			}
		}
	}

	public void analyzeSolution(@NonNull List<@NonNull SerializationMatchStep> steps) {
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			for (Map.Entry<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> entry : eReference2ruleAnalysis2grammarCardinality.entrySet()) {
				EReference eReference = entry.getKey();
				if (eReference.isContainment()) {
					Collection<@Nullable ParserRuleAnalysis> assignedRuleAnalyses = entry.getValue().keySet();
					steps.add(new SerializationMatchStep.MatchStep_RuleCheck(eReference, new GrammarRuleVector(assignedRuleAnalyses)));
				}
			}
		}
	}

	public @NonNull EAttribute_EnumerationValues @Nullable [] basicGetEAttribute2EnumerationValues() {
		Map<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> eAttribute2enumerationValues = getEAttribute2EnumerationValues();
		int size = eAttribute2enumerationValues.size();
		if (size <= 0) {
			return null;
		}
		@NonNull EAttribute_EnumerationValues[] eAttributeDatas = new @NonNull EAttribute_EnumerationValues[size];
		int i = 0;
		for (Map.Entry<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> entry : eAttribute2enumerationValues.entrySet()) {
			@NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue> enumerationValuesSet = entry.getValue();
			@NonNull EnumerationValue @NonNull [] enumerationValuesArray = enumerationValuesSet.toArray(new org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue[enumerationValuesSet.size()]);
			Arrays.sort(enumerationValuesArray, NameUtil.NAMEABLE_COMPARATOR);
			eAttributeDatas[i++] = new EAttribute_EnumerationValues(entry.getKey(), enumerationValuesArray);
		}
		return eAttributeDatas;
	}

	public @NonNull EAttribute_EnumerationValue_GrammarCardinality @Nullable [] basicGetEAttribute2enumerationValue2grammarCardinality() {
		Map<@NonNull EAttribute, @NonNull Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
		if (eAttribute2enumerationValue2grammarCardinality2 == null) {
			return null;
		}
		@NonNull EAttribute_EnumerationValue_GrammarCardinality [] eAttribute2enumerationValue2grammarCardinality = new @NonNull EAttribute_EnumerationValue_GrammarCardinality[eAttribute2enumerationValue2grammarCardinality2.size()];
		int i1 = 0;
		for (Map.Entry<@NonNull EAttribute, @NonNull Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality>> entry1 : eAttribute2enumerationValue2grammarCardinality2.entrySet()) {
			EAttribute eAttribute = entry1.getKey();
			Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality> value = entry1.getValue();
			@NonNull EnumerationValue_GrammarCardinality [] enumerationValue_GrammarCardinality = new @NonNull EnumerationValue_GrammarCardinality [value.size()];
			int i2 = 0;
			for (Map.Entry<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality> entry2 : value.entrySet()) {
				EnumerationValue enumerationValue = entry2.getKey();
				enumerationValue_GrammarCardinality[i2++] = new EnumerationValue_GrammarCardinality(enumerationValue, entry2.getValue());
			}
			eAttribute2enumerationValue2grammarCardinality[i1++] = new EAttribute_EnumerationValue_GrammarCardinality(eAttribute, enumerationValue_GrammarCardinality);
		}
		return eAttribute2enumerationValue2grammarCardinality;
	}
//	public @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> basicGetEAttribute2enumerationValue2grammarCardinality() {
//		return eAttribute2enumerationValue2grammarCardinality;
//	}

//	public @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable Integer, @NonNull GrammarCardinality>> basicGetEReference2ruleValueIndex2grammarCardinality() {
	public @NonNull EReference_RuleIndex_GrammarCardinality @Nullable [] basicGetEReference2ruleValueIndex2grammarCardinality() {
		Map<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 == null) {
			return null;
		}
		@NonNull EReference_RuleIndex_GrammarCardinality [] eReference2ruleValueIndex2grammarCardinality = new @NonNull EReference_RuleIndex_GrammarCardinality[eReference2ruleAnalysis2grammarCardinality2.size()];
		int i1 = 0;
		for (Map.Entry<@NonNull EReference, @NonNull Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality>> entry1 : eReference2ruleAnalysis2grammarCardinality2.entrySet()) {
			EReference eReference = entry1.getKey();
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> value = entry1.getValue();
			@NonNull RuleIndex_GrammarCardinality [] ruleValueIndex2grammarCardinality = new @NonNull RuleIndex_GrammarCardinality [value.size()];
			int i2 = 0;
			for (Map.Entry<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> entry2 : value.entrySet()) {
				ParserRuleAnalysis ruleAnalysis = entry2.getKey();
				Integer ruleValueIndex = ruleAnalysis != null ? ruleAnalysis.getIndex() : null;
				ruleValueIndex2grammarCardinality[i2++] = new RuleIndex_GrammarCardinality(ruleValueIndex, entry2.getValue());
			}
			Arrays.sort(ruleValueIndex2grammarCardinality);
			eReference2ruleValueIndex2grammarCardinality[i1++] = new EReference_RuleIndex_GrammarCardinality(eReference, ruleValueIndex2grammarCardinality);
		}
		return eReference2ruleValueIndex2grammarCardinality;
	}

	public @NonNull EReference_RuleIndexes @Nullable [] basicGetEReference2AssignedRuleValueIndexes() {
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes = getEReference2AssignedRuleValueIndexes();
		int size = eReference2assignedRuleIndexes.size();
		if (size <= 0) {
			return null;
		}
		@NonNull EReference_RuleIndexes[] eReferenceDatas = new @NonNull EReference_RuleIndexes[size];
		int i = 0;
		for (Map.Entry<@NonNull EReference, @NonNull GrammarRuleVector> entry : eReference2assignedRuleIndexes.entrySet()) {
			eReferenceDatas[i++] = new EReference_RuleIndexes(entry.getKey(), entry.getValue());
		}
		return eReferenceDatas;
	}

	public @NonNull EAttribute @Nullable [] basicGetNeedsDefaultEAttributes() {
		assert staticRuleMatch != null;
		return staticRuleMatch.basicGetNeedsDefaultEAttributes();
	}

	private @Nullable List<@NonNull AssignedSerializationNode> gatherAssignedSerializationNodes(@NonNull EReference eReference, @NonNull SerializationNode serializationNode, @Nullable List<@NonNull AssignedSerializationNode> assignedSerializationNodes) {
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			if (assignedSerializationNode.getEStructuralFeature() == eReference) {
				if (assignedSerializationNodes == null) {
					assignedSerializationNodes = new ArrayList<>();
				}
				assignedSerializationNodes.add(assignedSerializationNode);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				assignedSerializationNodes = gatherAssignedSerializationNodes(eReference, nestedSerializationNode, assignedSerializationNodes);
			}
		}
		return assignedSerializationNodes;
	}

	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference) {
		return gatherAssignedSerializationNodes(eReference, rootSerializationNode, null);
	}


/*	public @NonNull EAttributeData @Nullable [] basicGetEAttribute2EnumerationValues() {
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes2 = getEReference2AssignedRuleIndexes();
		if (eReference2assignedRuleIndexes2.size() <= 0) {
			return null;
		}
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2AssignedRuleValueIndexes = new HashMap<>(eReference2assignedRuleIndexes2.size());
		for (Map.Entry<@NonNull EReference, @NonNull GrammarRuleVector> entry : eReference2assignedRuleIndexes2.entrySet()) {
			GrammarRuleVector assignedRuleIndexes = entry.getValue();
			eReference2AssignedRuleValueIndexes.put(entry.getKey(), assignedRuleIndexes);
		}
		@NonNull EReferenceData[] eReferenceDatas = new @NonNull EReferenceData[eReference2AssignedRuleValueIndexes.size()];
		int i = 0;
		for (Map.Entry<@NonNull EReference, @NonNull GrammarRuleVector> entry : eReference2AssignedRuleValueIndexes.entrySet()) {
			eReferenceDatas[i++] = new EReferenceData(entry.getKey(), entry.getValue());
		}
		return eReferenceDatas;
	} */

	public @NonNull Map<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> getEAttribute2EnumerationValues() {
		Map<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> eAttribute2enumerationValues2 = eAttribute2enumerationValues;
		if (eAttribute2enumerationValues2 == null) {
			eAttribute2enumerationValues = eAttribute2enumerationValues2 = new HashMap<>();
			getEAttribute2EnumerationValues(getRootSerializationNode(), eAttribute2enumerationValues2);
		}
		if (eAttribute2enumerationValues2.size() > 0) {
			for (Map.Entry<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> entry : eAttribute2enumerationValues2.entrySet()) {
				Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue> assignedRuleIndexes = entry.getValue();
				eAttribute2enumerationValues2.put(entry.getKey(), assignedRuleIndexes);
			}
		}
		return eAttribute2enumerationValues2;
	}

/*	public @NonNull EAttributeData @NonNull [] getEAttribute2EnumerationValues() {
		Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues2 = eAttribute2enumerationValues;
		if (eAttribute2enumerationValues2 == null) {
			eAttribute2enumerationValues = eAttribute2enumerationValues2 = new HashMap<>();
			getEAttribute2EnumerationValues(getRootSerializationNode(), eAttribute2enumerationValues2);
		}
		return eAttribute2enumerationValues2;
	} */
	private void getEAttribute2EnumerationValues(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EAttribute, @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue>> eAttribute2enumerationValues) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			EAttribute eAttribute = (EAttribute)assignedKeywordsSerializationNode.getEStructuralFeature();
			Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue> enumerationValues = eAttribute2enumerationValues.get(eAttribute);
			if (enumerationValues == null) {
				enumerationValues = new HashSet<>();
				eAttribute2enumerationValues.put(eAttribute, enumerationValues);
			}
			EnumerationValue enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
			enumerationValues.add(enumerationValue);
		}
		else if (serializationNode instanceof AlternativeAssignsSerializationNode) {
			AlternativeAssignsSerializationNode assignsSerializationNode = (AlternativeAssignsSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignsSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute)eStructuralFeature;
				EnumerationValue enumerationValue = assignsSerializationNode.getEnumerationValue();
				if (enumerationValue != null) {
					Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue> enumerationValues = eAttribute2enumerationValues.get(eAttribute);
					if (enumerationValues == null) {
						enumerationValues = new HashSet<>();
						eAttribute2enumerationValues.put(eAttribute, enumerationValues);
					}
					enumerationValues.add(enumerationValue);
				}
			}
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			AssignedKeywordSerializationNode assignedKeywordSerializationNode = (AssignedKeywordSerializationNode)serializationNode;
			EAttribute eAttribute = (EAttribute)assignedKeywordSerializationNode.getEStructuralFeature();
			Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue> enumerationValues = eAttribute2enumerationValues.get(eAttribute);
			if (enumerationValues == null) {
				enumerationValues = new HashSet<>();
				eAttribute2enumerationValues.put(eAttribute, enumerationValues);
			}
			EnumerationValue enumerationValue = assignedKeywordSerializationNode.getEnumerationValue();
			enumerationValues.add(enumerationValue);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEAttribute2EnumerationValues(nestedSerializationNode, eAttribute2enumerationValues);
			}
		}
	}

	public @NonNull Map<@NonNull EReference, @NonNull GrammarRuleVector> getEReference2AssignedRuleValueIndexes() {
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes2 = eReference2assignedRuleIndexes;
		if (eReference2assignedRuleIndexes2 == null) {
			eReference2assignedRuleIndexes = eReference2assignedRuleIndexes2 = new HashMap<>();
			getEReference2AssignedRuleIndexes(getRootSerializationNode(), eReference2assignedRuleIndexes2);
		//	for (Map.Entry<@NonNull EReference, @NonNull GrammarRuleVector> entry : eReference2assignedRuleIndexes.entrySet()) {
		//		EReference eReference = entry.getKey();
		//		GrammarRuleVector assignedRuleIndexes = entry.getValue();
		//		eReference2assignedRuleIndexes2.put(eReference, new EReferenceData(eReference, assignedRuleIndexes));
		//	}
		}
		return eReference2assignedRuleIndexes2;
	}

	private void getEReference2AssignedRuleIndexes(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes) {
		/* if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			if (assignedKeywordsSerializationNode.getEStructuralFeature() == eReference) {
				ParserRuleAnalysis enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
				if (!enumerationValue.isNull()) {
					ruleAnalyses.add(enumerationValue);
				}
			}
		}
		else*/ if ((serializationNode instanceof AssignedRuleCallSerializationNode) || (serializationNode instanceof AlternativeAssignsSerializationNode)) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				GrammarRuleVector assignedRuleIndexes = eReference2assignedRuleIndexes.get(eReference);
				if (assignedRuleIndexes == null) {
					assignedRuleIndexes = new GrammarRuleVector();
					eReference2assignedRuleIndexes.put(eReference, assignedRuleIndexes);
				}
				@NonNull Integer @Nullable [] ruleIndexes = assignedSerializationNode.getAssignedRuleIndexes();
				if (ruleIndexes != null) {
					for (@NonNull Integer ruleIndex : ruleIndexes) {
					//	if (ruleAnalysis instanceof ParserRuleAnalysis) {
							assignedRuleIndexes.set(ruleIndex);
					//	}
					}
				}
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEReference2AssignedRuleIndexes(nestedSerializationNode, eReference2assignedRuleIndexes);
			}
		}
	}

	public @Nullable Set<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		return getEAttribute2EnumerationValues().get(eAttribute);
	}

	private void getIdiomMatches(@NonNull SerializationNode serializationNode, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				idiomMatches[idiomIndex] = idiom.firstMatch(serializationNode, this);
			}
			else {
				idiomMatch.nextMatch(serializationNode, this);
			}
			idiomIndex++;
		}
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getIdiomMatches(nestedSerializationNode, idioms, idiomMatches);
			}
		}
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		assert staticRuleMatch != null;
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality.get(eStructuralFeature);
			if (enumerationValue2grammarCardinality != null) {
				return enumerationValue2grammarCardinality.get(null);
			}
		}
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality.get(eStructuralFeature);
			if (ruleAnalysis2grammarCardinality != null) {
				return ruleAnalysis2grammarCardinality.get(null);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		assert staticRuleMatch != null;
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			Map<org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality.get(eAttribute);
			if (enumerationValue2grammarCardinality != null) {
				return enumerationValue2grammarCardinality.get(enumerationValue);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		assert staticRuleMatch != null;
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality.get(eReference);
			if (ruleAnalysis2grammarCardinality != null) {
				return ruleAnalysis2grammarCardinality.get(ruleAnalysis);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		assert staticRuleMatch != null;
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@Nullable ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality.get(eReference);
			if (ruleAnalysis2grammarCardinality != null) {
				for (@Nullable ParserRuleAnalysis parserRuleAnalysis : ruleAnalysis2grammarCardinality.keySet()) {
					if ((parserRuleAnalysis != null) && (parserRuleAnalysis.getRuleValue() == ruleValue)) {
						return ruleAnalysis2grammarCardinality.get(parserRuleAnalysis);
					}
				}
			}
		}
		return null;
	}

	@Override
	public @NonNull String getName() {
		return ruleAnalysis.getName();
	}

	public @NonNull EClass getProducedEClass() {
		EClass producedEClass2 = producedEClass;
		if (producedEClass2 == null) {
			EClass returnedEClass = ruleAnalysis.getReturnedEClass();
			producedEClass = producedEClass2 = refineProducedEClass(rootSerializationNode, returnedEClass);
		}
		return producedEClass2;
	}

	public @NonNull SerializationNode getRootSerializationNode() {
		return rootSerializationNode;
	}

	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	public int getRuleValueIndex() {
		return ruleAnalysis.getIndex();
	}

	public @NonNull SerializationRule getRuntime() {
		SerializationRule runtime2 = runtime;
		if (runtime2 == null) {
			getStaticRuleMatch();
			runtime2 = runtime;
			if (runtime2 == null) {
				runtime = runtime2 = RTSerializationRule2.create(this);
			}
		}
		return runtime2;
	}

	public @NonNull StaticRuleMatch getStaticRuleMatch() {
		StaticRuleMatch staticRuleMatch2 = staticRuleMatch;
		if (staticRuleMatch2 == null) {
			//
			staticRuleMatch = staticRuleMatch2 = new StaticRuleMatch(this);
			//
			//	Traverse the chosen serialization tree path to determine the
			//	cardinality variables and expressions to be solved to characterize the serialization.
			//
			staticRuleMatch2.analyzeSerialization();
			//
			//	Analyze the cardinality expressions to determine the steps that compute/validate each cardinality variable.
			//
			staticRuleMatch2.analyzeSolution();
		}
		return staticRuleMatch2;
	}

	public @Nullable SubIdiom getSubIdiom(@NonNull SerializationNode serializationNode) {
		return getSerializationNode2subIdioms().get(serializationNode);
	}

	public @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> getSerializationNode2subIdioms() {
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom2 = serializationNode2subIdiom;
		if (serializationNode2subIdiom2 == null) {
			assert staticRuleMatch != null;
			@NonNull Iterable<@NonNull Idiom> idioms = staticRuleMatch.getSerializationRuleAnalysis().getRuleAnalysis().getGrammarAnalysis().getIdioms();
			//
			//	Locate the matches for each idiom.
			//
			@Nullable IdiomMatch @NonNull [] idiomMatches = new org.eclipse.ocl.xtext.base.cs2text.xtext.IdiomMatch[Iterables.size(idioms)];
			getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
			//
			//	Install the subidioms for each first full idiom match.
			//
			serializationNode2subIdiom2 = new HashMap<>();
			for (@Nullable IdiomMatch idiomMatch : idiomMatches) {
				if (idiomMatch != null) {
					idiomMatch.installIn(serializationNode2subIdiom2);
				}
			}
			serializationNode2subIdiom = serializationNode2subIdiom2;
		}
		return serializationNode2subIdiom2;
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getVariables() {
		assert staticRuleMatch != null;
		return staticRuleMatch.getCardinalityVariables();
	}

	private @NonNull GrammarCardinality refineGrammarCardinality(@NonNull GrammarCardinality netGrammarCardinality, @Nullable GrammarCardinality oldGrammarCardinality) {
		if (oldGrammarCardinality == null) {
			return netGrammarCardinality;
		}
		boolean newMayBeMany = netGrammarCardinality.mayBeMany();
		boolean newMayBeZero = netGrammarCardinality.mayBeZero();
		boolean oldMayBeMany = oldGrammarCardinality.mayBeMany();
		boolean oldMayBeZero = oldGrammarCardinality.mayBeZero();
		if (!oldMayBeZero) {
			newMayBeZero = false;
		}
		if (oldMayBeMany) {
			newMayBeMany = true;
		}
		return newMayBeMany
			? newMayBeZero ? GrammarCardinality.ZERO_OR_MORE : GrammarCardinality.ONE_OR_MORE
			: newMayBeZero ? GrammarCardinality.ZERO_OR_ONE : GrammarCardinality.ONE;
	}

	protected @NonNull EClass refineProducedEClass(@NonNull SerializationNode serializationNode, @NonNull EClass producedEClass) {
		if (serializationNode instanceof AssignedSerializationNode) {
			EClass assignedEClass = ((AssignedSerializationNode)serializationNode).getAssignedEClass();
			if (producedEClass.isSuperTypeOf(assignedEClass)) {
				producedEClass = assignedEClass;
			}
			else {
				assert assignedEClass.isSuperTypeOf(producedEClass);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				producedEClass = refineProducedEClass(nestedSerializationNode, producedEClass);
			}
		}
		return producedEClass;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		rootSerializationNode.toString(s, -1);
	}

	public void toRuleString(@NonNull StringBuilder s) {
		rootSerializationNode.toString(s, -1);
	}

	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		StaticRuleMatch staticRuleMatch2 = staticRuleMatch;
		if (staticRuleMatch2 != null) {
			staticRuleMatch2.toSolutionString(s, depth);
		}
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, -1);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(ruleAnalysis.getName());
		s.append("(");
		EClass producedEClass = getProducedEClass();
		s.append(producedEClass.getEPackage().getName());
		s.append("::");
		s.append(producedEClass.getName());
		s.append("): ");
		rootSerializationNode.toString(s, depth);
	}
}