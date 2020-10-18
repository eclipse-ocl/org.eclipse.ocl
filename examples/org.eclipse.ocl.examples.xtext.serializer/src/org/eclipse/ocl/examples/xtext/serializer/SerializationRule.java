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
package org.eclipse.ocl.examples.xtext.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.examples.xtext.serializer.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class SerializationRule implements Nameable
{
	public static class EAttribute_EnumerationValues implements Nameable
	{
		protected final @NonNull EAttribute eAttribute;
		protected final @NonNull EnumerationValue @NonNull [] enumerationValues;

		public EAttribute_EnumerationValues(/*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue @NonNull ... enumerationValues) {
			assert eAttribute != null;
			this.eAttribute = eAttribute;
			this.enumerationValues = enumerationValues;
		}

		public @NonNull EAttribute getEAttribute() {
			return eAttribute;
		}

		public @NonNull EnumerationValue @NonNull [] getEnumerationValues() {
			return enumerationValues;
		}

		@Override
		public @NonNull String getName() {
			return SerializationUtils.getName(eAttribute);
		}

		@Override
		public @NonNull String toString() {
			return eAttribute.getEContainingClass().getName() + "::" + eAttribute.getName() + " " + enumerationValues;
		}
	}

	public static class EAttribute_EnumerationValue_GrammarCardinality implements Nameable
	{
		protected final @NonNull EAttribute eAttribute;
		protected final @NonNull EnumerationValue_GrammarCardinality @NonNull [] enumerationValue2grammarCardinality;

		public EAttribute_EnumerationValue_GrammarCardinality(/*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue_GrammarCardinality @NonNull [] enumerationValue2grammarCardinality) {
			assert eAttribute != null;
			this.eAttribute = eAttribute;
			this.enumerationValue2grammarCardinality = enumerationValue2grammarCardinality;
		}

		public @NonNull EAttribute getEAttribute() {
			return eAttribute;
		}

		public @NonNull EnumerationValue_GrammarCardinality @NonNull [] getEnumerationValue_GrammarCardinality() {
			return enumerationValue2grammarCardinality;
		}

		@Override
		public @NonNull String getName() {
			return SerializationUtils.getName(eAttribute);
		}

		@Override
		public @NonNull String toString() {
			return eAttribute.getEContainingClass().getName() + "::" + eAttribute.getName() + " " + enumerationValue2grammarCardinality;
		}
	}

	public static class EReference_RuleIndexes implements Nameable
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull GrammarRuleVector parserRuleValueIndexes;

		public EReference_RuleIndexes(/*@NonNull*/ EReference eReference, @NonNull GrammarRuleVector parserRuleValueIndexes) {
			assert eReference != null;
			this.eReference = eReference;
			this.parserRuleValueIndexes = parserRuleValueIndexes;
		}

		public @NonNull EReference getEReference() {
			return eReference;
		}

		public @NonNull GrammarRuleVector getAssignedTargetRuleValueIndexes() {
			return parserRuleValueIndexes;
		}

		@Override
		public @NonNull String getName() {
			return SerializationUtils.getName(eReference);
		}

		@Override
		public @NonNull String toString() {
			return eReference.getEContainingClass().getName() + "::" + eReference.getName() + " " + parserRuleValueIndexes;
		}
	}

	public static class EReference_RuleIndex_GrammarCardinality implements Nameable
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull RuleIndex_GrammarCardinality @NonNull [] ruleIndex2grammarCardinality;

		public EReference_RuleIndex_GrammarCardinality(/*@NonNull*/ EReference eReference, @NonNull RuleIndex_GrammarCardinality @NonNull [] ruleIndex2grammarCardinality) {
			assert eReference != null;
			this.eReference = eReference;
			this.ruleIndex2grammarCardinality = ruleIndex2grammarCardinality;
		}

		public @NonNull EReference getEReference() {
			return eReference;
		}

		@Override
		public @NonNull String getName() {
			return SerializationUtils.getName(eReference);
		}

		public @NonNull RuleIndex_GrammarCardinality @NonNull [] getRuleIndex_GrammarCardinality() {
			return ruleIndex2grammarCardinality;
		}

		@Override
		public @NonNull String toString() {
			return eReference.getEContainingClass().getName() + "::" + eReference.getName() + " " + ruleIndex2grammarCardinality;
		}
	}

	public static class EnumerationValue_GrammarCardinality implements Nameable
	{
		protected final @Nullable EnumerationValue enumerationValue;
		protected final @NonNull GrammarCardinality grammarCardinality;

		public EnumerationValue_GrammarCardinality(/*@NonNull*/ EnumerationValue enumerationValue, @NonNull GrammarCardinality grammarCardinality) {
//			assert enumerationValue != null;
			this.enumerationValue = enumerationValue;
			this.grammarCardinality = grammarCardinality;
		}

		public @Nullable EnumerationValue getEnumerationValue() {
			return enumerationValue;
		}

		public @NonNull GrammarCardinality getGrammarCardinality() {
			return grammarCardinality;
		}

		@Override
		public @NonNull String getName() {
			return enumerationValue != null ? enumerationValue.getName() : "«null»";
		}

		@Override
		public @NonNull String toString() {
			return getName() + " " + grammarCardinality;
		}
	}

	public static class RuleIndex_GrammarCardinality implements Comparable<@NonNull RuleIndex_GrammarCardinality>
	{
		protected final @NonNull Integer ruleIndex;
		protected final @NonNull GrammarCardinality grammarCardinality;

		public RuleIndex_GrammarCardinality(/*@NonNull*/ Integer ruleIndex, @NonNull GrammarCardinality grammarCardinality) {
			assert ruleIndex != null;
			this.ruleIndex = ruleIndex;
			this.grammarCardinality = grammarCardinality;
		}

		@Override
		public int compareTo(@NonNull RuleIndex_GrammarCardinality that) {
			return this.ruleIndex - that.ruleIndex;
		}

		public @NonNull GrammarCardinality getGrammarCardinality() {
			return grammarCardinality;
		}

		public @NonNull Integer getRuleIndex() {
			return ruleIndex;
		}

		@Override
		public @NonNull String toString() {
			return ruleIndex + " " + grammarCardinality;
		}
	}

	protected final @NonNull String name;		// unqualified
	protected final int ruleValueIndex;
	private final @NonNull SerializationMatchStep @NonNull [] matchSteps;
	private final @NonNull SerializationStep @NonNull [] serializationSteps;
	private final @NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues;
	private final @NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes;

	/**
	 * The EAttributes whose default value must be used to satisfy grammar rule multiplicities..
	 */
	private final @NonNull EAttribute @Nullable [] needsDefaultEAttributes;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private final @NonNull EAttribute_EnumerationValue_GrammarCardinality @Nullable [] eAttribute2enumerationValue2grammarCardinality;

	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private final @NonNull EReference_RuleIndex_GrammarCardinality @Nullable [] eReference2ruleValueIndex2grammarCardinality;

	private @Nullable SerializationRuleHelper helper = null;

	public SerializationRule(@NonNull String name, int ruleValueIndex,
			@NonNull SerializationMatchStep @NonNull [] matchSteps,
			@NonNull SerializationStep @NonNull [] serializationSteps,
			@NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues,
			@NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes,
			/*@NonNull*/ EAttribute @Nullable [] needsDefaultEAttributes,
			@NonNull EAttribute_EnumerationValue_GrammarCardinality @Nullable [] eAttribute2enumerationValue2grammarCardinality,
			@NonNull EReference_RuleIndex_GrammarCardinality @Nullable [] eReference2ruleValueIndex2grammarCardinality) {
		this.name = name;
		this.ruleValueIndex = ruleValueIndex;
		this.matchSteps = matchSteps;
		this.serializationSteps = serializationSteps;
		this.eAttribute2enumerationValues = eAttribute2enumerationValues;
		this.eReference2assignedRuleValueIndexes = eReference2assignedRuleValueIndexes;
		@SuppressWarnings("null") @NonNull EAttribute[] castNeedsDefaultEAttributes2 = needsDefaultEAttributes;
		this.needsDefaultEAttributes = castNeedsDefaultEAttributes2;
		this.eAttribute2enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality;
		this.eReference2ruleValueIndex2grammarCardinality = eReference2ruleValueIndex2grammarCardinality;
	}

	public @Nullable GrammarRuleVector getAssignedRuleValueIndexes(@NonNull EReference eReference) {
		if (eReference2assignedRuleValueIndexes != null) {
			for (@NonNull EReference_RuleIndexes eReferenceData : eReference2assignedRuleValueIndexes) {
				if (eReferenceData.getEReference() == eReference) {
					return eReferenceData.getAssignedTargetRuleValueIndexes();
				}
			}
		}
		return null;
	}

	public @NonNull EnumerationValue @Nullable [] getEnumerationValues(@NonNull EAttribute eAttribute) {
		if (eAttribute2enumerationValues != null) {
			for (@NonNull EAttribute_EnumerationValues eAttributeData : eAttribute2enumerationValues) {
				if (eAttributeData.getEAttribute() == eAttribute) {
					return eAttributeData.getEnumerationValues();
				}
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			for (@NonNull EAttribute_EnumerationValue_GrammarCardinality eAttribute_EnumerationValue_GrammarCardinality : eAttribute2enumerationValue2grammarCardinality) {
				if (eAttribute_EnumerationValue_GrammarCardinality.getEAttribute() == eStructuralFeature) {
					for (@NonNull EnumerationValue_GrammarCardinality enumerationValue2grammarCardinality : eAttribute_EnumerationValue_GrammarCardinality.getEnumerationValue_GrammarCardinality()) {
						if (enumerationValue2grammarCardinality.getEnumerationValue() == null) {
							return enumerationValue2grammarCardinality.getGrammarCardinality();
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			for (@NonNull EAttribute_EnumerationValue_GrammarCardinality eAttribute_EnumerationValue_GrammarCardinality : eAttribute2enumerationValue2grammarCardinality) {
				if (eAttribute_EnumerationValue_GrammarCardinality.getEAttribute() == eAttribute) {
					for (@NonNull EnumerationValue_GrammarCardinality enumerationValue2grammarCardinality : eAttribute_EnumerationValue_GrammarCardinality.getEnumerationValue_GrammarCardinality()) {
						if (enumerationValue2grammarCardinality.getEnumerationValue() == enumerationValue) {
							return enumerationValue2grammarCardinality.getGrammarCardinality();
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		if (eReference2ruleValueIndex2grammarCardinality != null) {
			for (@NonNull EReference_RuleIndex_GrammarCardinality eReference_RuleIndex_GrammarCardinality : eReference2ruleValueIndex2grammarCardinality) {
				if (eReference_RuleIndex_GrammarCardinality.getEReference() == eReference) {
					for (@NonNull RuleIndex_GrammarCardinality ruleIndex2grammarCardinality : eReference_RuleIndex_GrammarCardinality.getRuleIndex_GrammarCardinality()) {
						if (ruleIndex2grammarCardinality.getRuleIndex() == ruleValue.getIndex()) {
							return ruleIndex2grammarCardinality.getGrammarCardinality();
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public int getRuleValueIndex() {
		return ruleValueIndex;
	}

	public @NonNull SerializationStep @NonNull [] getSerializationSteps() {
		return serializationSteps;
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		DynamicRuleMatch dynamicRuleMatch = slotsAnalysis.basicGetDynamicRuleMatch(this); // new DynamicRuleMatch(this, slotsAnalysis);
		if (dynamicRuleMatch == null) {
		//	dynamicRuleMatch = slotsAnalysis.createDynamicRuleMatch(this);
			assert slotsAnalysis.basicGetDynamicRuleMatch(this) == null;
			dynamicRuleMatch = new DynamicRuleMatch(slotsAnalysis, this, matchSteps, this);
			slotsAnalysis.addDynamicRuleMatch(dynamicRuleMatch);

			if (!dynamicRuleMatch.analyze()) {
				return null;
			}
			//
			//	Evaluate the expressions to determine the required size of each slot.
			/* -- re-evaluation is redundant - so no expression at run-time
			for (@NonNull EStructuralFeature_CardinalityExpression eStructuralFeatureData : eStructuralFeature2cardinalityExpression) {
				CardinalityExpression expression = eStructuralFeatureData.getCardinalityExpression();
				assert expression != null;
				if (!expression.checkSize(dynamicRuleMatch)) {
					return null;
				}
			} */
			//
			//	Check that no 'unused' features are used.
			//
			@NonNull EAttribute_EnumerationValue_GrammarCardinality[] eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
			@NonNull EReference_RuleIndex_GrammarCardinality[] eReference2ruleValueIndex2grammarCardinality2 = eReference2ruleValueIndex2grammarCardinality;
			for (@NonNull EStructuralFeature eStructuralFeature : slotsAnalysis.getEStructuralFeatures()) {
				boolean gotIt = false;
				if (eAttribute2enumerationValue2grammarCardinality2 != null) {
					for (@NonNull EAttribute_EnumerationValue_GrammarCardinality eAttributeData : eAttribute2enumerationValue2grammarCardinality2) {
						if (eAttributeData.getEAttribute() == eStructuralFeature) {
							gotIt = true;
						}
					}
				}
				if (eReference2ruleValueIndex2grammarCardinality2 != null) {
					for (@NonNull EReference_RuleIndex_GrammarCardinality eReferenceData : eReference2ruleValueIndex2grammarCardinality2) {
						if (eReferenceData.getEReference() == eStructuralFeature) {
							gotIt = true;
						}
					}
				}
				if (!gotIt) {
					UserSlotAnalysis object = slotsAnalysis.getSlotAnalysis(eStructuralFeature);
					if (!object.isCounted() || (object.asCounted() != 0)) {
						return null;
					}
				}
			}
			dynamicRuleMatch.setChecked();
		}
		else {
			if (!dynamicRuleMatch.isChecked()) {
				return null;
			}
		}
		return dynamicRuleMatch;
		// TODO Auto-generated method stub
	//	throw new UnsupportedOperationException();
	}

	public boolean needsDefault(@NonNull EAttribute eAttribute) {
		@NonNull EAttribute[] needsDefaultEAttributes2 = needsDefaultEAttributes;
		if (needsDefaultEAttributes2 != null) {
			for (@NonNull EAttribute needsDefaultEAttribute : needsDefaultEAttributes2) {
				if (needsDefaultEAttribute == eAttribute) {
					return true;
				}
			}
		}
		return false;
	}

	public void formatRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		formatSubRule(0, serializationSteps.length, serializer, serializationBuilder);
	}

	public void formatSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		UserModelAnalysis modelAnalysis = serializer.getModelAnalysis();
		SerializationMetaData serializationMetaData = modelAnalysis.getSerializationMetaData();
		Map<@NonNull String, @NonNull List<@NonNull ILeafNode>> keyword2leafNodes = new HashMap<>();
		Map<@NonNull EReference, @NonNull List<@NonNull ILeafNode>> eReference2leafNodes = new HashMap<>();
		Map<@NonNull AbstractRule, @NonNull List<@NonNull ILeafNode>> calledRule2leafNodes = new HashMap<>();
		Map<@NonNull SerializationRule, @NonNull List<@NonNull ICompositeNode>> serializationRule2compositeNodes = new HashMap<>();
		ICompositeNode parentNode = NodeModelUtils.getNode(serializer.getElement());
		for (@NonNull INode childNode : SerializationUtils.getChildren(parentNode)) {
			if (childNode instanceof ILeafNode) {
				ILeafNode leafNode = (ILeafNode)childNode;
				if (!leafNode.isHidden()) {
					EObject grammarElement = leafNode.getGrammarElement();
					if (grammarElement instanceof Keyword) {
						String value = ((Keyword)grammarElement).getValue();
						if (value != null) {
							List<@NonNull ILeafNode> leafNodes = SerializationUtils.maybeNull(keyword2leafNodes.get(value));
							if (leafNodes == null) {
								leafNodes = new ArrayList<>();
								keyword2leafNodes.put(value, leafNodes);
							}
							leafNodes.add(leafNode);
						}
					}
					else if (grammarElement instanceof CrossReference) {
						EReference eReference = GrammarUtil.getReference((CrossReference)grammarElement);
						if (eReference != null) {
							List<@NonNull ILeafNode> leafNodes = SerializationUtils.maybeNull(eReference2leafNodes.get(eReference));
							if (leafNodes == null) {
								leafNodes = new ArrayList<>();
								eReference2leafNodes.put(eReference, leafNodes);
							}
							leafNodes.add(leafNode);
						}
					}
					else if (grammarElement instanceof RuleCall) {
						AbstractRule calledRule = ((RuleCall)grammarElement).getRule();
						if (calledRule != null) {
							List<@NonNull ILeafNode> leafNodes = SerializationUtils.maybeNull(calledRule2leafNodes.get(calledRule));
							if (leafNodes == null) {
								leafNodes = new ArrayList<>();
								calledRule2leafNodes.put(calledRule, leafNodes);
							}
							leafNodes.add(leafNode);
						}
					}
					else {
						throw new UnsupportedOperationException();
					}
				}
			}
			else if (childNode instanceof ICompositeNode) {
				ICompositeNode compositeNode = (ICompositeNode)childNode;
				EObject grammarElement = compositeNode.getGrammarElement();
				if (grammarElement instanceof RuleCall) {
					AbstractRule calledRule = ((RuleCall)grammarElement).getRule();
					if (calledRule != null) {
						GrammarRuleValue calledRuleValue = serializationMetaData.basicGetGrammarRuleValue(calledRule.getName());
						assert calledRuleValue != null;
						EObject childElement = compositeNode.getSemanticElement();
						UserElementAnalysis childElementAnalysis = modelAnalysis.getElementAnalysis(childElement);
						DynamicRuleMatch dynamicRuleMatch = childElementAnalysis.createDynamicRuleMatch(calledRuleValue.getIndex());
						if (dynamicRuleMatch != null) {
							SerializationRule childSerializationRule = dynamicRuleMatch.getSerializationRule();
							List<@NonNull ICompositeNode> compositeNodes = SerializationUtils.maybeNull(serializationRule2compositeNodes.get(childSerializationRule));
							if (compositeNodes == null) {
								compositeNodes = new ArrayList<>();
								serializationRule2compositeNodes.put(childSerializationRule, compositeNodes);
							}
							compositeNodes.add(compositeNode);
						}
					}
				}
				else {
					throw new UnsupportedOperationException();
				}
			}
			else {
				throw new UnsupportedOperationException();
			}
		}



		for (int index = startIndex; index < endIndex; ) {
			SerializationStep serializationStep = serializationSteps[index++];
			@NonNull SerializationSegment @Nullable [] segments = serializationStep.getSerializationSegments(); // XXX Could invite serializer to provide a dynamicSerializationSegments.
			if (serializationStep instanceof SerializationStepSequence) {
				int stepsRange = ((SerializationStepSequence)serializationStep).getStepsRange();
				if (segments != null) {
					for (@NonNull SerializationSegment segment : segments) {
						segment.serialize(index-1, serializer, serializationBuilder);
					}
				}
				else {
					int cardinalityVariableIndex = ((SerializationStepSequence)serializationStep).getVariableIndex();
					int stepLoopCount = cardinalityVariableIndex >= 0 ? serializer.getValue(cardinalityVariableIndex) : 1;
					for (int i = 0; i < stepLoopCount; i++) {
						formatSubRule(index-1, index + stepsRange, serializer, serializationBuilder);
					}
				}
				index += stepsRange;
			}
			else {
				if (segments != null) {
					for (@NonNull SerializationSegment segment : segments) {
						segment.serialize(index-1, serializer, serializationBuilder);
					}
				}
				else {
					serializationStep.serializeInnerValue(index-1, serializer, serializationBuilder);
				}
			}
		}
	}

	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (int index = 0; index < serializationSteps.length; ) {
			SerializationStep serializationStep = serializationSteps[index];
			index = serializationStep.serializeOuterValue(index, serializer, serializationBuilder);
		}
	}

	public void setHelper(SerializationRuleHelper helper) {
		this.helper = helper;
	}

	public void toMatchTermString(@NonNull StringBuilder s, int depth) {
		if (helper != null) {
			helper.toMatchTermString(s, depth);
		}
	}

	public String toRuleString() {
		StringBuilder s = new StringBuilder();
		toRuleString(s);
		return s.toString();
	}

	public void toRuleString(@NonNull StringBuilder s) {
		if (helper != null) {
			helper.toRuleString(s);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		if (helper != null) {
			helper.toString(s, depth);
		}
		else {
			s.append(getName());
		}
	}
}