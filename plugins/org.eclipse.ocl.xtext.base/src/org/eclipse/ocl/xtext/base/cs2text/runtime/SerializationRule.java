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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.xtext.base.cs2text.runtime.UserSlotsAnalysis.UserSlotAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class SerializationRule
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
			return XtextGrammarUtil.getName(eAttribute);
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
			return XtextGrammarUtil.getName(eAttribute);
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
			return XtextGrammarUtil.getName(eReference);
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
			return XtextGrammarUtil.getName(eReference);
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

	private final int ruleValueIndex;
	private final @NonNull SerializationMatchStep @NonNull [] solutionSteps;
	private final @NonNull SerializationStep @NonNull [] serializationSteps;
	private final @NonNull SerializationSegment @NonNull [] @Nullable [] staticSegments;
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

	public SerializationRule(int ruleValueIndex,
			/*@NonNull*/ SerializationMatchStep /*@NonNull*/ [] solutionSteps,
			/*@NonNull*/ SerializationStep /*@NonNull*/ [] serializationSteps,
			/*@Nullable*/ SerializationSegment /*@NonNull*/ [] /*@NonNull*/ [] staticSegments,
			@NonNull EAttribute_EnumerationValues @Nullable [] eAttribute2enumerationValues,
			@NonNull EReference_RuleIndexes @Nullable [] eReference2assignedRuleValueIndexes,
			/*@NonNull*/ EAttribute @Nullable [] needsDefaultEAttributes,
			@NonNull EAttribute_EnumerationValue_GrammarCardinality @Nullable [] eAttribute2enumerationValue2grammarCardinality,
			@NonNull EReference_RuleIndex_GrammarCardinality @Nullable [] eReference2ruleValueIndex2grammarCardinality) {
		this.ruleValueIndex = ruleValueIndex;
		this.solutionSteps = solutionSteps;
		this.serializationSteps = serializationSteps;
		this.staticSegments = staticSegments;
		this.eAttribute2enumerationValues = eAttribute2enumerationValues;
		this.eReference2assignedRuleValueIndexes = eReference2assignedRuleValueIndexes;
		this.needsDefaultEAttributes = needsDefaultEAttributes;
		this.eAttribute2enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality;
		this.eReference2ruleValueIndex2grammarCardinality = eReference2ruleValueIndex2grammarCardinality;
	}

/*	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SerializationRule)) {
			return false;
		}
		SerializationRule that = (SerializationRule)obj;
		if (this.ruleValueIndex != that.ruleValueIndex) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.solutionSteps, that.solutionSteps)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.serializationSteps, that.serializationSteps)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.staticSegments, that.staticSegments)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.eAttribute2enumerationValues, that.eAttribute2enumerationValues)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.eReference2assignedRuleValueIndexes, that.eReference2assignedRuleValueIndexes)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.needsDefaultEAttributes, that.needsDefaultEAttributes)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.eAttribute2enumerationValue2grammarCardinality, that.eAttribute2enumerationValue2grammarCardinality)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.eReference2ruleValueIndex2grammarCardinality, that.eReference2ruleValueIndex2grammarCardinality)) {
			return false;
		}
		return true;
	} */

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

	public int getRuleValueIndex() {
		return ruleValueIndex;
	}

	public @NonNull SerializationStep @NonNull [] getSerializationSteps() {
		return serializationSteps;
	}

	public @NonNull SerializationSegment @NonNull [] @Nullable [] getStaticSegments() {
		return staticSegments;
	}

/*	@Override
	public int hashCode() {
		if (hashCode == null) {
			int hash = getClass().hashCode() + ruleValueIndex;
			hash += 3*arrayHash(solutionSteps);
			hash += 7*arrayHash(serializationSteps);
			hash += 11*arrayHash(staticSegments);
			hash += 13*arrayHash(eAttribute2enumerationValues);
			hash += 17*arrayHash(eReference2assignedRuleValueIndexes);
			hash += 19*arrayHash(needsDefaultEAttributes);
			hash += 23*arrayHash(eAttribute2enumerationValue2grammarCardinality);
			hash += 29*arrayHash(eReference2ruleValueIndex2grammarCardinality);
			this.hashCode = hash;
		}
		assert hashCode != null;
		return hashCode.intValue();
	} */

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis, @NonNull SerializationSegment @NonNull [] @Nullable [] staticSegments) {
		//
		//	Compute the solutions and assign to/check against each CardinalityVariable
		//
		DynamicRuleMatch dynamicRuleMatch = slotsAnalysis.basicGetDynamicRuleMatch(this); // new DynamicRuleMatch(this, slotsAnalysis);
		if (dynamicRuleMatch == null) {
		//	dynamicRuleMatch = slotsAnalysis.createDynamicRuleMatch(this);


			assert slotsAnalysis.basicGetDynamicRuleMatch(this) == null;
			dynamicRuleMatch = new DynamicRuleMatch(slotsAnalysis, this, solutionSteps, staticSegments, this);
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

	public void serializeRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializeSubRule(0, serializationSteps.length, serializer, serializationBuilder);
	}

	public void serializeSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		@NonNull SerializationSegment @NonNull [] @Nullable [] staticSegments = serializer.getStaticSegments();
		for (int index = startIndex; index < endIndex; ) {
			@NonNull SerializationSegment @Nullable [] segments = staticSegments[index];		// XXX Could invite serializer to provide a dynamicSubIdiom.
			SerializationStep serializationStep = serializationSteps[index++];
			int cardinalityVariableIndex = serializationStep.getVariableIndex();
			int stepLoopCount = cardinalityVariableIndex >= 0 ? serializer.getValue(cardinalityVariableIndex) : 1;
			if (serializationStep instanceof SerializationStepSequence) {
				int stepsRange = ((SerializationStepSequence)serializationStep).getStepsRange();
				if (segments != null) {
					for (@NonNull SerializationSegment segment : segments) {
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
						for (@NonNull SerializationSegment segment : segments) {
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