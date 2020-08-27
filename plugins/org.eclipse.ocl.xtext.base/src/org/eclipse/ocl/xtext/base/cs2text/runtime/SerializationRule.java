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

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

import com.google.common.collect.Sets;

public class SerializationRule
{
	public static class EAttribute_EnumerationValues implements Nameable
	{
		protected final @NonNull EAttribute eAttribute;
		protected final @NonNull Set<@NonNull EnumerationValue> enumerationValues;

		public EAttribute_EnumerationValues(/*@NonNull*/ EAttribute eAttribute, @NonNull Set<@NonNull EnumerationValue> enumerationValues) {
			assert eAttribute != null;
			this.eAttribute = eAttribute;
			this.enumerationValues = enumerationValues;
		}

		public EAttribute_EnumerationValues(/*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue @NonNull ... enumerationValues) {
			assert eAttribute != null;
			this.eAttribute = eAttribute;
			this.enumerationValues = Sets.newHashSet(enumerationValues);		// XXX Prefer array
		}

		public @NonNull EAttribute getEAttribute() {
			return eAttribute;
		}

		public @NonNull Set<@NonNull EnumerationValue> getEnumerationValues() {
			return enumerationValues;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarUtil.getName(eAttribute);
		}

		@Override
		public @NonNull String toString() {
			return eAttribute.getEContainingClass().getName() + "::" + eAttribute.getName() + " " + enumerationValues;
		}
	}

	public static class EReference_RuleIndexes implements Nameable
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull IndexVector parserRuleValueIndexes;

		public EReference_RuleIndexes(/*@NonNull*/ EReference eReference, @NonNull IndexVector parserRuleValueIndexes) {
			assert eReference != null;
			this.eReference = eReference;
			this.parserRuleValueIndexes = parserRuleValueIndexes;
		}

		public @NonNull EReference getEReference() {
			return eReference;
		}

		public @NonNull IndexVector getAssignedTargetRuleValueIndexes() {
			return parserRuleValueIndexes;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarUtil.getName(eReference);
		}

		@Override
		public @NonNull String toString() {
			return eReference.getEContainingClass().getName() + "::" + eReference.getName() + " " + parserRuleValueIndexes;
		}
	}

	public static class EStructuralFeature_CardinalityExpression implements Nameable
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;
		protected final /*@NonNull*/ CardinalityExpression cardinalityExpression;

		public EStructuralFeature_CardinalityExpression(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull CardinalityExpression cardinalityExpression) {
			assert eStructuralFeature != null;
			this.eStructuralFeature = eStructuralFeature;
			this.cardinalityExpression = cardinalityExpression;
		}
		public EStructuralFeature_CardinalityExpression(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull String cardinalityExpression) {
			assert eStructuralFeature != null;
			this.eStructuralFeature = eStructuralFeature;
			this.cardinalityExpression = null;//cardinalityExpression;		// XXX
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eStructuralFeature;
		}

		public @NonNull CardinalityExpression getCardinalityExpression() {
			return cardinalityExpression;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarUtil.getName(eStructuralFeature);
		}

		@Override
		public @NonNull String toString() {
			return eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName() + " " + cardinalityExpression;
		}
	}

	private final int ruleValueIndex;
	private final @NonNull CardinalitySolutionStep @NonNull [] solutionSteps;
	private final @NonNull RTSerializationStep @NonNull [] serializationSteps;
	private final @NonNull Segment @NonNull [] @Nullable [] staticSegments;
	private final @NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues;
	private final @NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes;
//	private final @Nullable Map<@NonNull EReference, @NonNull IndexVector> eReference2assignedRuleValueIndexes;

	/**
	 * The per-feature expression that (re-)computes the required number of assigned slots from the solved
	 * cardinality variables. This is checked gainst the actual number of slots in an actual user element.
	 */
	protected final @NonNull EStructuralFeature_CardinalityExpression @NonNull [] eStructuralFeature2cardinalityExpression;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality>> eAttribute2enumerationValue2multiplicativeCardinality = null;

	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable Integer, @NonNull MultiplicativeCardinality>> eReference2ruleValueIndex2multiplicativeCardinality = null;

	public SerializationRule(int ruleValueIndex,
			/*@NonNull*/ CardinalitySolutionStep /*@NonNull*/ [] solutionSteps,
			/*@NonNull*/ RTSerializationStep /*@NonNull*/ [] serializationSteps,
			/*@Nullable*/ Segment /*@NonNull*/ [] /*@NonNull*/ [] staticSegments,
			@NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues,
			@NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes,
			@NonNull EStructuralFeature_CardinalityExpression @NonNull [] eStructuralFeature2cardinalityExpression,
			@Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality>> eAttribute2enumerationValue2multiplicativeCardinality,
			@Nullable Map<@NonNull EReference, @NonNull Map<@Nullable Integer, @NonNull MultiplicativeCardinality>> eReference2ruleValueIndex2multiplicativeCardinality) {
		this.ruleValueIndex = ruleValueIndex;
		this.solutionSteps = solutionSteps;
		this.serializationSteps = serializationSteps;
		this.staticSegments = staticSegments;
		this.eAttribute2enumerationValues = eAttribute2enumerationValues;
		this.eReference2assignedRuleValueIndexes = eReference2assignedRuleValueIndexes;
		this.eStructuralFeature2cardinalityExpression = eStructuralFeature2cardinalityExpression;
		this.eAttribute2enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality;
		this.eReference2ruleValueIndex2multiplicativeCardinality = eReference2ruleValueIndex2multiplicativeCardinality;
	}

	public SerializationRule(int ruleValueIndex,
			/*@NonNull*/ CardinalitySolutionStep /*@NonNull*/ [] solutionSteps,
			/*@NonNull*/ RTSerializationStep /*@NonNull*/ [] serializationSteps,
			/*@Nullable*/ Segment /*@NonNull*/ [] /*@NonNull*/ [] staticSegments,
			@NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues,
			@NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes,
			@NonNull EStructuralFeature_CardinalityExpression @NonNull [] eStructuralFeature2cardinalityExpression) {
		this(ruleValueIndex, solutionSteps, serializationSteps, staticSegments, eAttribute2enumerationValues, eReference2assignedRuleValueIndexes, eStructuralFeature2cardinalityExpression, null, null);
	}

	public @Nullable IndexVector getAssignedRuleValueIndexes(@NonNull EReference eReference) {
		if (eReference2assignedRuleValueIndexes != null) {
			for (@NonNull EReference_RuleIndexes eReferenceData : eReference2assignedRuleValueIndexes) {
				if (eReferenceData.getEReference() == eReference) {
					return eReferenceData.getAssignedTargetRuleValueIndexes();
				}
			}
		}
		return null;
	}

//	@Override
//	public @Nullable Map<@NonNull EReference, @NonNull IndexVector> getEReference2DiscriminatingRuleValueIndexes() {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException();
//	}

	public @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		if (eAttribute2enumerationValues != null) {
			for (@NonNull EAttribute_EnumerationValues eAttributeData : eAttribute2enumerationValues) {
				if (eAttributeData.getEAttribute() == eAttribute) {
					return eAttributeData.getEnumerationValues();
				}
			}
		}
		return null;
	}

/*	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	} */

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		if (eAttribute2enumerationValue2multiplicativeCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
			if (enumerationValue2multiplicativeCardinality != null) {
				return enumerationValue2multiplicativeCardinality.get(null);
			}
		}
		if (eReference2ruleValueIndex2multiplicativeCardinality != null) {
			Map<@Nullable Integer, @NonNull MultiplicativeCardinality> ruleValueIndex2multiplicativeCardinality = eReference2ruleValueIndex2multiplicativeCardinality.get(eStructuralFeature);
			if (ruleValueIndex2multiplicativeCardinality != null) {
				return ruleValueIndex2multiplicativeCardinality.get(null);
			}
		}
		return null;
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		if (eAttribute2enumerationValue2multiplicativeCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eAttribute2enumerationValue2multiplicativeCardinality.get(eAttribute);
			if (enumerationValue2multiplicativeCardinality != null) {
				return enumerationValue2multiplicativeCardinality.get(enumerationValue);
			}
		}
		return null;
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		if (eReference2ruleValueIndex2multiplicativeCardinality != null) {
			Map<@Nullable Integer, @NonNull MultiplicativeCardinality> ruleValueIndex2multiplicativeCardinality = eReference2ruleValueIndex2multiplicativeCardinality.get(eReference);
			if (ruleValueIndex2multiplicativeCardinality != null) {
				return ruleValueIndex2multiplicativeCardinality.get(ruleValue);
			}
		}
		return null;
	}

	public int getRuleValueIndex() {
		return ruleValueIndex;
	}

	public @NonNull RTSerializationStep @NonNull [] getSerializationSteps() {
		return serializationSteps;
	}

	public @NonNull Segment @NonNull [] @Nullable [] getStaticSegments() {
		return staticSegments;
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		DynamicRuleMatch dynamicRuleMatch = slotsAnalysis.basicGetDynamicRuleMatch(this); // new DynamicRuleMatch(this, slotsAnalysis);
		if (dynamicRuleMatch == null) {
		//	dynamicRuleMatch = slotsAnalysis.createDynamicRuleMatch(this);


			assert slotsAnalysis.basicGetDynamicRuleMatch(this) == null;
			dynamicRuleMatch = new DynamicRuleMatch(slotsAnalysis, this, solutionSteps, this);
			slotsAnalysis.addDynamicRuleMatch(dynamicRuleMatch);

			if (!dynamicRuleMatch.analyze()) {
				return null;
			}
			//
			//	Evaluate the expressions to determine the required size of each slot.
			//
			for (@NonNull EStructuralFeature_CardinalityExpression eStructuralFeatureData : eStructuralFeature2cardinalityExpression) {
				CardinalityExpression expression = eStructuralFeatureData.getCardinalityExpression();
				assert expression != null;
				if (!expression.checkSize(dynamicRuleMatch)) {
					return null;
				}
			}
			//
			//	Check that no 'unused' features are used.
			//
			for (@NonNull EStructuralFeature eStructuralFeature : slotsAnalysis.getEStructuralFeatures()) {
				boolean gotIt = false;
				for (@NonNull EStructuralFeature_CardinalityExpression eStructuralFeatureData : eStructuralFeature2cardinalityExpression) {
					if (eStructuralFeatureData.getEStructuralFeature() == eStructuralFeature) {
						gotIt = true;
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
		for (@NonNull EStructuralFeature_CardinalityExpression eStructuralFeatureData : eStructuralFeature2cardinalityExpression) {
			if (eStructuralFeatureData.getEStructuralFeature() == eAttribute) {
				CardinalityExpression expression = eStructuralFeatureData.getCardinalityExpression();
			//	if (expression == null) {
			//		return false;
			//	}
				return expression.isOne();
			}
		}
		return false;
	}

	public void serializeRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializeSubRule(0, serializationSteps.length, serializer, serializationBuilder);
	}

	public void serializeSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (int index = startIndex; index < endIndex; ) {
			@NonNull Segment @Nullable [] segments = staticSegments[index];		// XXX Could invite serializer to provide a dynamicSubIdiom.
			RTSerializationStep serializationStep = serializationSteps[index++];
			int cardinalityVariableIndex = serializationStep.getVariableIndex();
			int stepLoopCount = cardinalityVariableIndex >= 0 ? serializer.getValue(cardinalityVariableIndex) : 1;
			if (serializationStep instanceof RTSerializationSequenceStep) {
				int stepsRange = ((RTSerializationSequenceStep)serializationStep).getStepsRange();
				if (segments != null) {
					for (Segment segment : segments) {
						segment.serialize(serializationStep, serializer, serializationBuilder);
					}
				}
				else {
					for (int i = 0; i < stepLoopCount; i++) {
						serializeSubRule(index, index + stepsRange, serializer, serializationBuilder);
					}
				}
				index += stepsRange;
			}
			else {
				for (int i = 0; i < stepLoopCount; i++) {
					if (segments != null) {
						for (Segment segment : segments) {
							segment.serialize(serializationStep, serializer, serializationBuilder);
						}
					}
					else {
						serializationStep.serialize(serializer, serializationBuilder);
					}
				}
			}
		}
	}

	public String toRuleString() {
		StringBuilder s = new StringBuilder();
		toRuleString(s);
		return s.toString();
	}

	public void toRuleString(@NonNull StringBuilder s) {
		// TODO Auto-generated method stub
	}

	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		StringUtil.appendIndentation(s, depth);
		s.append("Serialization Steps");
	//	for (int i = 0; i < serializationSteps.length; i++) {
	//		StringUtil.appendIndentation(s, depth+1);
		//	SubIdiom subIdiom = staticSegments[i];
		//	s.append(subIdiom != null ? subIdiom.getName() : "null");
		//	s.append(" == ");
		//	serializationSteps[i].toString(s, depth+1);
	//	}
	}
}