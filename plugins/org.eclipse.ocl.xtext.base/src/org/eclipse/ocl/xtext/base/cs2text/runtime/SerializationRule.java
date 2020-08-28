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

	public static class EAttribute_EnumerationValue_MultiplicativeCardinality implements Nameable
	{
		protected final @NonNull EAttribute eAttribute;
		protected final @NonNull EnumerationValue_MultiplicativeCardinality @NonNull [] enumerationValue2multiplicativeCardinality;

		public EAttribute_EnumerationValue_MultiplicativeCardinality(/*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue_MultiplicativeCardinality @NonNull [] enumerationValue2multiplicativeCardinality) {
			assert eAttribute != null;
			this.eAttribute = eAttribute;
			this.enumerationValue2multiplicativeCardinality = enumerationValue2multiplicativeCardinality;
		}

		public @NonNull EAttribute getEAttribute() {
			return eAttribute;
		}

		public @NonNull EnumerationValue_MultiplicativeCardinality @NonNull [] getEnumerationValue_MultiplicativeCardinality() {
			return enumerationValue2multiplicativeCardinality;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarUtil.getName(eAttribute);
		}

		@Override
		public @NonNull String toString() {
			return eAttribute.getEContainingClass().getName() + "::" + eAttribute.getName() + " " + enumerationValue2multiplicativeCardinality;
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

	public static class EReference_RuleIndex_MultiplicativeCardinality implements Nameable
	{
		protected final @NonNull EReference eReference;
		protected final @NonNull RuleIndex_MultiplicativeCardinality @NonNull [] ruleIndex2multiplicativeCardinality;

		public EReference_RuleIndex_MultiplicativeCardinality(/*@NonNull*/ EReference eReference, @NonNull RuleIndex_MultiplicativeCardinality @NonNull [] ruleIndex2multiplicativeCardinality) {
			assert eReference != null;
			this.eReference = eReference;
			this.ruleIndex2multiplicativeCardinality = ruleIndex2multiplicativeCardinality;
		}

		public @NonNull EReference getEReference() {
			return eReference;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarUtil.getName(eReference);
		}

		public @NonNull RuleIndex_MultiplicativeCardinality @NonNull [] getRuleIndex_MultiplicativeCardinality() {
			return ruleIndex2multiplicativeCardinality;
		}

		@Override
		public @NonNull String toString() {
			return eReference.getEContainingClass().getName() + "::" + eReference.getName() + " " + ruleIndex2multiplicativeCardinality;
		}
	}

	public static class EStructuralFeature_NeedsDefault implements Nameable
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;

		public EStructuralFeature_NeedsDefault(/*@NonNull*/ EStructuralFeature eStructuralFeature) {
			assert eStructuralFeature != null;
			this.eStructuralFeature = eStructuralFeature;
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eStructuralFeature;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarUtil.getName(eStructuralFeature);
		}

		public boolean needsDefault() {
			return true;
		}

		@Override
		public @NonNull String toString() {
			return eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName();
		}
	}

	public static class EnumerationValue_MultiplicativeCardinality implements Nameable
	{
		protected final @Nullable EnumerationValue enumerationValue;
		protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

		public EnumerationValue_MultiplicativeCardinality(/*@NonNull*/ EnumerationValue enumerationValue, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
//			assert enumerationValue != null;
			this.enumerationValue = enumerationValue;
			this.multiplicativeCardinality = multiplicativeCardinality;
		}

		public @Nullable EnumerationValue getEnumerationValue() {
			return enumerationValue;
		}

		public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
			return multiplicativeCardinality;
		}

		@Override
		public @NonNull String getName() {
			return enumerationValue != null ? enumerationValue.getName() : "«null»";
		}

		@Override
		public @NonNull String toString() {
			return getName() + " " + multiplicativeCardinality;
		}
	}

	public static class RuleIndex_MultiplicativeCardinality
	{
		protected final @NonNull Integer ruleIndex;
		protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

		public RuleIndex_MultiplicativeCardinality(/*@NonNull*/ Integer ruleIndex, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
			assert ruleIndex != null;
			this.ruleIndex = ruleIndex;
			this.multiplicativeCardinality = multiplicativeCardinality;
		}

		public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
			return multiplicativeCardinality;
		}

		public @NonNull Integer getRuleIndex() {
			return ruleIndex;
		}

		@Override
		public @NonNull String toString() {
			return ruleIndex + " " + multiplicativeCardinality;
		}
	}

	private final int ruleValueIndex;
	private final @NonNull CardinalitySolutionStep @NonNull [] solutionSteps;
	private final @NonNull RTSerializationStep @NonNull [] serializationSteps;
	private final @NonNull Segment @NonNull [] @Nullable [] staticSegments;
	private final @NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues;
	private final @NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes;

	/**
	 * The per-feature expression that (re-)computes the required number of assigned slots from the solved
	 * cardinality variables. This is checked gainst the actual number of slots in an actual user element.
	 */
	private final @NonNull EStructuralFeature_NeedsDefault @Nullable [] eStructuralFeature2cardinalityExpression;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private final @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality @Nullable [] eAttribute2enumerationValue2multiplicativeCardinality;
//	private @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull MultiplicativeCardinality>> eAttribute2enumerationValue2multiplicativeCardinality = null;

	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private final @NonNull EReference_RuleIndex_MultiplicativeCardinality @Nullable [] eReference2ruleValueIndex2multiplicativeCardinality;
//	private @Nullable Map<@NonNull EReference, @NonNull Map<@Nullable Integer, @NonNull MultiplicativeCardinality>> eReference2ruleValueIndex2multiplicativeCardinality = null;

	public SerializationRule(int ruleValueIndex,
			/*@NonNull*/ CardinalitySolutionStep /*@NonNull*/ [] solutionSteps,
			/*@NonNull*/ RTSerializationStep /*@NonNull*/ [] serializationSteps,
			/*@Nullable*/ Segment /*@NonNull*/ [] /*@NonNull*/ [] staticSegments,
			@NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues,
			@NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes,
			@NonNull EStructuralFeature_NeedsDefault @Nullable [] eStructuralFeature2cardinalityExpression,
			@NonNull EAttribute_EnumerationValue_MultiplicativeCardinality @Nullable [] eAttribute2enumerationValue2multiplicativeCardinality,
			@NonNull EReference_RuleIndex_MultiplicativeCardinality @Nullable [] eReference2ruleValueIndex2multiplicativeCardinality) {
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

//	public SerializationRule(int ruleValueIndex,
//			/*@NonNull*/ CardinalitySolutionStep /*@NonNull*/ [] solutionSteps,
//			/*@NonNull*/ RTSerializationStep /*@NonNull*/ [] serializationSteps,
//			/*@Nullable*/ Segment /*@NonNull*/ [] /*@NonNull*/ [] staticSegments,
//			@NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues,
//			@NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes,
//			@NonNull EStructuralFeature_CardinalityExpression @NonNull [] eStructuralFeature2cardinalityExpression) {
//		this(ruleValueIndex, solutionSteps, serializationSteps, staticSegments, eAttribute2enumerationValues, eReference2assignedRuleValueIndexes, eStructuralFeature2cardinalityExpression, null, null);
//	}

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
			for (@NonNull EAttribute_EnumerationValue_MultiplicativeCardinality eAttribute_EnumerationValue_MultiplicativeCardinality : eAttribute2enumerationValue2multiplicativeCardinality) {
				if (eAttribute_EnumerationValue_MultiplicativeCardinality.getEAttribute() == eStructuralFeature) {
					for (@NonNull EnumerationValue_MultiplicativeCardinality enumerationValue2multiplicativeCardinality : eAttribute_EnumerationValue_MultiplicativeCardinality.getEnumerationValue_MultiplicativeCardinality()) {
						if (enumerationValue2multiplicativeCardinality.getEnumerationValue() == null) {
							return enumerationValue2multiplicativeCardinality.getMultiplicativeCardinality();
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		if (eAttribute2enumerationValue2multiplicativeCardinality != null) {
			for (@NonNull EAttribute_EnumerationValue_MultiplicativeCardinality eAttribute_EnumerationValue_MultiplicativeCardinality : eAttribute2enumerationValue2multiplicativeCardinality) {
				if (eAttribute_EnumerationValue_MultiplicativeCardinality.getEAttribute() == eAttribute) {
					for (@NonNull EnumerationValue_MultiplicativeCardinality enumerationValue2multiplicativeCardinality : eAttribute_EnumerationValue_MultiplicativeCardinality.getEnumerationValue_MultiplicativeCardinality()) {
						if (enumerationValue2multiplicativeCardinality.getEnumerationValue() == enumerationValue) {
							return enumerationValue2multiplicativeCardinality.getMultiplicativeCardinality();
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		if (eReference2ruleValueIndex2multiplicativeCardinality != null) {
			for (@NonNull EReference_RuleIndex_MultiplicativeCardinality eReference_RuleIndex_MultiplicativeCardinality : eReference2ruleValueIndex2multiplicativeCardinality) {
				if (eReference_RuleIndex_MultiplicativeCardinality.getEReference() == eReference) {
					for (@NonNull RuleIndex_MultiplicativeCardinality ruleIndex2multiplicativeCardinality : eReference_RuleIndex_MultiplicativeCardinality.getRuleIndex_MultiplicativeCardinality()) {
						if (ruleIndex2multiplicativeCardinality.getRuleIndex() == ruleValue.getIndex()) {
							return ruleIndex2multiplicativeCardinality.getMultiplicativeCardinality();
						}
					}
					return null;
				}
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
			@NonNull EAttribute_EnumerationValue_MultiplicativeCardinality[] eAttribute2enumerationValue2multiplicativeCardinality2 = eAttribute2enumerationValue2multiplicativeCardinality;
			@NonNull EReference_RuleIndex_MultiplicativeCardinality[] eReference2ruleValueIndex2multiplicativeCardinality2 = eReference2ruleValueIndex2multiplicativeCardinality;
			for (@NonNull EStructuralFeature eStructuralFeature : slotsAnalysis.getEStructuralFeatures()) {
				boolean gotIt = false;
				if (eAttribute2enumerationValue2multiplicativeCardinality2 != null) {
					for (@NonNull EAttribute_EnumerationValue_MultiplicativeCardinality eAttributeData : eAttribute2enumerationValue2multiplicativeCardinality2) {
						if (eAttributeData.getEAttribute() == eStructuralFeature) {
							gotIt = true;
						}
					}
				}
				if (eReference2ruleValueIndex2multiplicativeCardinality2 != null) {
					for (@NonNull EReference_RuleIndex_MultiplicativeCardinality eReferenceData : eReference2ruleValueIndex2multiplicativeCardinality2) {
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
		@NonNull EStructuralFeature_NeedsDefault[] eStructuralFeature2cardinalityExpression2 = eStructuralFeature2cardinalityExpression;
		if (eStructuralFeature2cardinalityExpression2 != null) {
			for (@NonNull EStructuralFeature_NeedsDefault eStructuralFeatureData : eStructuralFeature2cardinalityExpression2) {
				if (eStructuralFeatureData.getEStructuralFeature() == eAttribute) {
					return eStructuralFeatureData.needsDefault();
				}
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