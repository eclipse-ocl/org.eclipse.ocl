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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.eclipse.xtext.nodemodel.impl.LeafNode;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Lists;

public abstract class SerializationStep
{
	public static abstract class SerializationStepAbstractFeature extends SerializationStep
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;

		protected SerializationStepAbstractFeature(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(serializationSegments);
			assert eStructuralFeature != null;
			this.eStructuralFeature = eStructuralFeature;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 3 * eStructuralFeature.hashCode();
		}

		protected boolean equalTo(@NonNull SerializationStepAbstractFeature that) {
			return super.equalTo(that) && eStructuralFeature.equals(that.eStructuralFeature);
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eStructuralFeature;
		}

		// Provide key in feature-class-package order to provide rational first-letter-first sort
		@Override
		public final @NonNull String getGlobalSortKey(@NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id) {
			StringBuilder s = new StringBuilder();
			s.append("a-");
			s.append(eStructuralFeature.getName());
			s.append("-");
			s.append(eStructuralFeature.getEContainingClass().getName());
			s.append("-");
			s.append(eStructuralFeature.getEContainingClass().getEPackage().getName());
			getGlobalSortKey(s, serializationSegments2id);
			@SuppressWarnings("null") @NonNull String castString = s.toString();
			return castString;
		}
	}

	public static class SerializationStepAssignKeyword extends SerializationStepAbstractFeature
	{
		protected final @NonNull EnumerationValue enumerationValue;

		public SerializationStepAssignKeyword(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(eStructuralFeature, serializationSegments);
			this.enumerationValue = enumerationValue;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 3 * enumerationValue.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepAssignKeyword)) {
				return false;
			}
			return equalTo((SerializationStepAssignKeyword)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepAssignKeyword that) {
			return super.equalTo(that) && (this.enumerationValue == that.enumerationValue);
		}

		public @NonNull EnumerationValue getEnumerationValue() {
			return enumerationValue;
		}

//		@Override
//		public @NonNull String getGlobalSortKey() {
//			return getGlobalSortKey("ak");
//		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			if (node instanceof LeafNode) {
				EObject grammarElement = node.getGrammarElement();
//				return keyword.equals(node.getText());
			}
			return false;
		}


		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			Object object = serializer.consumeNext(eStructuralFeature);
			serializationBuilder.append(String.valueOf(object));
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(SerializationUtils.getName(SerializationUtils.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(SerializationUtils.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			s.append(enumerationValue);
			super.toString(s, depth);
		}
	}

	public static class SerializationStepAssignedRuleCall extends SerializationStepAbstractFeature
	{
		private int calledRuleIndex;

		public SerializationStepAssignedRuleCall(/*@NonNull*/ EStructuralFeature eStructuralFeature, int calledValueIndex, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(eStructuralFeature, serializationSegments);
			this.calledRuleIndex = calledValueIndex;
			if (serializationSegments == null) {
				getClass();		// XXX
			}
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * calledRuleIndex;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepAssignedRuleCall)) {
				return false;
			}
			return equalTo((SerializationStepAssignedRuleCall)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepAssignedRuleCall that) {
			return super.equalTo(that) && (this.calledRuleIndex == that.calledRuleIndex);
		}

		public int getCalledRuleIndex() {
			return calledRuleIndex;
		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			if (node instanceof LeafNode) {
				EObject grammarElement = node.getGrammarElement();
				if (grammarElement instanceof RuleCall) {
					AbstractRule rule = ((RuleCall)grammarElement).getRule();
					GrammarRuleValue grammarRuleValue = serializer.getSerializationMetaData().getGrammarRuleValue(calledRuleIndex);
					return grammarRuleValue.getName().equals(rule.getName());
				}
			}
			return false;
		}

		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			UserModelAnalysis modelAnalysis = serializer.getModelAnalysis();
			SerializationMetaData serializationMetaData = modelAnalysis.getSerializationMetaData();
			GrammarRuleValue grammarRuleValue = serializationMetaData.getGrammarRuleValue(calledRuleIndex);
			Object eGet = serializer.consumeNext(eStructuralFeature);
			if (eStructuralFeature instanceof EReference) {
				assert ((EReference)eStructuralFeature).isContainment();
				if (eGet != null) {
					serializer.serializeElement(serializationBuilder, (EObject)eGet, grammarRuleValue);
				}
			}
			else {
				String val = modelAnalysis.getValueConverterService().toString(eGet, grammarRuleValue.getRuleName());
				serializationBuilder.append(String.valueOf(val));
			}
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(SerializationUtils.getName(SerializationUtils.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(SerializationUtils.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			s.append(calledRuleIndex);
			super.toString(s, depth);
		}
	}


	public static class SerializationStepAssigns extends SerializationStepAbstractFeature
	{
		private @Nullable EnumerationValue enumerationValue;
		private @NonNull Integer @Nullable [] calledRuleIndexes;	// Cannot use GrammarRuleVEctor since must preserve declaration order

		public SerializationStepAssigns(/*@NonNull*/ EStructuralFeature eStructuralFeature, @Nullable EnumerationValue enumerationValue, @NonNull Integer @Nullable [] calledRuleIndexes, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(eStructuralFeature, serializationSegments);
			this.enumerationValue = enumerationValue;
			this.calledRuleIndexes = calledRuleIndexes;
		}

		@Override
		public int computeHashCode() {
			int hashCode = super.computeHashCode();
			if (enumerationValue != null) {
				enumerationValue.hashCode();
			}
			if (calledRuleIndexes != null) {
				calledRuleIndexes.hashCode();
			}
			return hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepAssigns)) {
				return false;
			}
			return equalTo((SerializationStepAssigns)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepAssigns that) {
			if (!super.equalTo(that)) {
				return false;
			}
			if (!SerializationUtils.safeEquals(this.enumerationValue, that.enumerationValue)) {
				return false;
			}
			if (!SerializationUtils.safeEquals(this.calledRuleIndexes, that.calledRuleIndexes)) {
				return false;
			}
			return true;
		}

		public @NonNull Integer @Nullable [] getCalledRuleIndexes() {
			return calledRuleIndexes;
		}

		public @Nullable EnumerationValue getEnumerationValue() {
			return enumerationValue;
		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			if (node instanceof LeafNode) {
				EObject grammarElement = node.getGrammarElement();
				if (grammarElement instanceof RuleCall) {		// FIXME enums too
					AbstractRule rule = ((RuleCall)grammarElement).getRule();
					String ruleName = rule.getName();
					SerializationMetaData serializationMetaData = serializer.getSerializationMetaData();
					if (calledRuleIndexes != null) {
						for (int calledRuleIndex : calledRuleIndexes) {
							GrammarRuleValue grammarRuleValue = serializationMetaData.getGrammarRuleValue(calledRuleIndex);
							if (grammarRuleValue.getName().equals(ruleName)) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}

		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			Object object = serializer.consumeNext(eStructuralFeature);
			UserModelAnalysis modelAnalysis = serializer.getModelAnalysis();
			SerializationMetaData serializationMetaData = modelAnalysis.getSerializationMetaData();
			@NonNull Integer[] calledRuleIndexes2 = calledRuleIndexes;
			if (eStructuralFeature instanceof EAttribute) {
				EnumerationValue enumerationValue2 = enumerationValue;
				if (enumerationValue2 != null) {
					@SuppressWarnings("null")
					@NonNull String string = String.valueOf(object);
					if (enumerationValue2.isElement(string)) {
						serializationBuilder.append(string);
						return;
					}
				}
				if (calledRuleIndexes2 != null) {
					for (int calledRuleIndex : calledRuleIndexes2) {
						@NonNull GrammarRuleValue calledRuleValue = serializationMetaData.getGrammarRuleValue(calledRuleIndex);
						try {
							String val = modelAnalysis.getValueConverterService().toString(object, calledRuleValue.getRuleName());
							serializationBuilder.append(String.valueOf(val));
							return;
						}
						catch (ValueConverterException e) {}
					}
				}
				serializationBuilder.appendError("Failed to convert '" + String.valueOf(object) + "'");
			}
			else if ((object != null) && (calledRuleIndexes2 != null)) {
				EReference eReference = (EReference)eStructuralFeature;
				EObject eObject = (EObject)object;
				assert eReference.isContainment();
				UserElementAnalysis elementAnalysis = modelAnalysis.getElementAnalysis(eObject);
				UserSlotsAnalysis slotsAnalysis = elementAnalysis.getSlotsAnalysis();
				for (int calledRuleIndex : calledRuleIndexes2) {			// search for matching rule
					@NonNull GrammarRuleValue calledRuleValue = serializationMetaData.getGrammarRuleValue(calledRuleIndex);
					for (@NonNull SerializationRule serializationRule : ((ParserRuleValue)calledRuleValue).getSerializationRules()) {
						DynamicRuleMatch match = serializationRule.match(slotsAnalysis);
						if (match != null) {
							serializer.serializeElement(serializationBuilder, eObject, calledRuleValue);
							return;
						}
					}
				}
				serializationBuilder.appendError("Failed to convert " + eObject.eClass().getName() + ": '" + String.valueOf(object) + "'");
			}
			// else {}		-- null never happens
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(SerializationUtils.getName(SerializationUtils.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(SerializationUtils.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			boolean isFirst = true;
			// enumerationValue
			if (calledRuleIndexes != null) {
				for (int calledRuleIndex : calledRuleIndexes) {
					if (!isFirst) {
						s.append("|");
					}
					s.append(calledRuleIndex);
					isFirst = false;
				}
			}
			super.toString(s, depth);
		}
	}

	public static class SerializationStepCrossReference extends SerializationStepAbstractFeature
	{
		protected final @NonNull CrossReference crossReference;		// May be an equivalent - different container

		public SerializationStepCrossReference(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull CrossReference crossReference, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(eStructuralFeature, serializationSegments);
			assert eStructuralFeature != null;
			this.crossReference = crossReference;
		}

		@Override
		public int computeHashCode() {
			int hash = super.computeHashCode();
			AbstractElement terminal = this.crossReference.getTerminal();
			if (terminal instanceof RuleCall) {
				hash += ((RuleCall)terminal).getRule().getName().hashCode();
			}
			else {
				hash += terminal.hashCode();		// Never happens
			}
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepCrossReference)) {
				return false;
			}
			return equalTo((SerializationStepCrossReference)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepCrossReference that) {
			if (!super.equalTo(that)) {		// Checks eStructuralFeature
				return false;
			}
			AbstractElement thisTerminal = this.crossReference.getTerminal();
			AbstractElement thatTerminal = that.crossReference.getTerminal();
			if ((thisTerminal instanceof RuleCall) && (thatTerminal instanceof RuleCall)) {
				return ((RuleCall)thisTerminal).getRule() == ((RuleCall)thatTerminal).getRule();
			}
			else {
				return SerializationUtils.safeEquals(thisTerminal, thatTerminal);		// Never happens
			}
		}

		public @NonNull CrossReference getCrossReference() {
			return crossReference;
		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			if (node instanceof LeafNode) {
				EObject grammarElement = node.getGrammarElement();
//				return keyword.equals(node.getText());
			}
			return false;
		}

		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			EObject eGet = (EObject)serializer.consumeNext(eStructuralFeature);
			EObject context = serializer.getElement();
			String string = serializer.getModelAnalysis().getCrossReferenceSerializer().serializeCrossRef(context, crossReference, eGet, null, null);
			serializationBuilder.append(string);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(SerializationUtils.getName(SerializationUtils.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(SerializationUtils.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			s.append(((RuleCall)crossReference.getTerminal()).getRule().getName());
			super.toString(s, depth);
		}
	}

	public static class SerializationStepKeyword extends SerializationStep
	{
		protected final @NonNull String keyword;

		public SerializationStepKeyword(@NonNull String keyword, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(serializationSegments);
			this.keyword = keyword;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * keyword.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepKeyword)) {
				return false;
			}
			return equalTo((SerializationStepKeyword)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepKeyword that) {
			return super.equalTo(that) && this.keyword.equals(that.keyword);
		}

		@Override
		public @NonNull String getGlobalSortKey(@NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id) {
			StringBuilder s = new StringBuilder();
			s.append("k-");
			s.append(keyword);
			getGlobalSortKey(s, serializationSegments2id);
			@SuppressWarnings("null") @NonNull String castString = s.toString();
			return castString;
		}

		public @NonNull String getKeyword() {
			return keyword;
		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			if (node instanceof LeafNode) {
				EObject grammarElement = node.getGrammarElement();
				if (grammarElement instanceof Keyword) {
					return keyword.equals(((Keyword)grammarElement).getValue());
				}
			}
			return false;
		}

		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			serializationBuilder.append(keyword);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("'");
			s.append(Strings.convertToJavaString(keyword));
			s.append("'");
			super.toString(s, depth);
		}
	}

	public static class SerializationStepSequence extends SerializationStep
	{
		protected final int variableIndex;		// -ve for a unit step (with outer segments)
		/**
		 * The number of steps within the linearized steps for the rule that support the sequence.
		 */
		private int stepsRange = 0;			// Exclusive

		public SerializationStepSequence(int variableIndex, int stepsRange, @NonNull SerializationSegment @Nullable [] serializationSegments) {
			super(serializationSegments);
			this.variableIndex = variableIndex;
			this.stepsRange = stepsRange;
		//	assert (variableIndex >= 0) || (serializationSegments != null);		// XXX
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 3 * variableIndex + 5 * stepsRange;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepSequence)) {
				return false;
			}
			return equalTo((SerializationStepSequence)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepSequence that) {
			return super.equalTo(that)
				&& (this.variableIndex == that.variableIndex)
				&& (this.stepsRange == that.stepsRange);
		}

		@Override
		public @NonNull String getGlobalSortKey(@NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id) {
			StringBuilder s = new StringBuilder();
			s.append("s-");
			s.append(variableIndex);
			s.append("-");
			s.append(stepsRange);
			getGlobalSortKey(s, serializationSegments2id);
			@SuppressWarnings("null") @NonNull String castString = s.toString();
			return castString;
		}

		public int getStepsRange() {
			return stepsRange;
		}

		public int getVariableIndex() {
			return variableIndex;
		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			return false;
		}

		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			@NonNull SerializationStep[] serializationSteps = serializer.getSerializationRule().getSerializationSteps();
			assert serializationSteps[thisSerializationStepIndex] == this;
			int startIndex = thisSerializationStepIndex + 1;
			int endIndex = startIndex + stepsRange;
			int stepLoopCount = variableIndex >= 0 ? serializer.getValue(variableIndex) : 1;
			for (int i = 0; i < stepLoopCount; i++) {
				for (int index = startIndex; index < endIndex; ) {
					SerializationStep serializationStep = serializationSteps[index];
					index = serializationStep.serializeOuterValue(index, serializer, serializationBuilder);
				}
			}
		}

		@Override
		public int serializeOuterValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			if ((variableIndex < 0) || (serializer.getValue(variableIndex) > 0)) {
				super.serializeOuterValue(thisSerializationStepIndex, serializer, serializationBuilder);
			}
			return thisSerializationStepIndex + 1 + stepsRange;
		}

		public void setStepsRange(int stepsRange) {
			assert stepsRange >= 0;
			this.stepsRange = stepsRange;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			if (variableIndex >= 0) {
				s.append(String.format("V%02d", variableIndex));
			}
			else {
				s.append("1");
			}
			s.append("*");
			s.append(stepsRange);
			s.append("-steps");
			super.toString(s, depth);
		}
	}

	public static class SerializationStepWrapper extends SerializationStep
	{
		public SerializationStepWrapper(@NonNull SerializationSegment @NonNull [] serializationSegments) {
			super(serializationSegments);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepWrapper)) {
				return false;
			}
			return equalTo((SerializationStepWrapper)obj);
		}

		@Override
		public @NonNull String getGlobalSortKey(@NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id) {
			StringBuilder s = new StringBuilder();
			s.append("w");
			getGlobalSortKey(s, serializationSegments2id);
			@SuppressWarnings("null") @NonNull String castString = s.toString();
			return castString;
		}

		@Override
		public boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer) {
			return node instanceof CompositeNodeWithSemanticElement;
		}

		@Override
		public void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			@NonNull SerializationStep[] serializationSteps = serializer.getSerializationRule().getSerializationSteps();
			for (int index = 1; index < serializationSteps.length; ) {
				SerializationStep serializationStep = serializationSteps[index];
				index = serializationStep.serializeOuterValue(index, serializer, serializationBuilder);
			}
		}

		@Override
		public int serializeOuterValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			assert thisSerializationStepIndex == 0;
			assert serializer.getSerializationRule().getSerializationSteps()[thisSerializationStepIndex] == this;
			assert serializationSegments != null;
			for (@NonNull SerializationSegment serializationSegment : serializationSegments) {
				serializationSegment.serialize(0, serializer, serializationBuilder);
			}
			return serializer.getSerializationRule().getSerializationSteps().length;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("wrapper");
			super.toString(s, depth);
		}
	}

	protected final @NonNull SerializationSegment @NonNull [] serializationSegments;

	private @Nullable Integer hashCode = null;

	protected SerializationStep(@NonNull SerializationSegment @Nullable [] serializationSegments) {
		this.serializationSegments = serializationSegments != null ? serializationSegments : SerializationSegment.VALUE_SEGMENTS_ARRAY;
	}

	protected int computeHashCode() {
		int hash = getClass().hashCode();
		if (serializationSegments != null) {
			for (@NonNull SerializationSegment serializationSegment : serializationSegments) {
				hash = 3 * hash + serializationSegment.hashCode();
			}
		}
		return hash;
	}

	@Override
	public abstract boolean equals(Object obj);

	protected boolean equalTo(@NonNull SerializationStep that) {
		@NonNull SerializationSegment[] theseSegments = this.serializationSegments;
		@NonNull SerializationSegment[] thoseSegments = that.serializationSegments;
		if ((theseSegments != null) || (thoseSegments != null)) {
			if ((theseSegments == null) || (thoseSegments == null)) {
				return false;
			}
			if (theseSegments.length != thoseSegments.length) {
				return false;
			}
			for (int i = 0; i < theseSegments.length; i++) {
				if (!theseSegments[i].equals(thoseSegments[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Accumulate the inner value of this serialization step as determined by the derived class.
	 *
	public void formatInnerValue(@NonNull UserElementFormatter formatter, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append(formatter.getLeafNode().getText());
	} */

	/**
	 * Accumulate the outer value of this serialization step as determined by its serialization segements,
	 * to serializationBuilder using the serializer for context. A coumpound step such as a sequen ce may
	 * use additional steps from serializer.getSerializationRule().getSerialiationSteps() starting at index,
	 * and returning the index following any additionally used steps.
	 *
	public void formatOuterValue(@NonNull UserElementFormatter formatter, @NonNull SerializationBuilder serializationBuilder) {
		if (serializationSegments != null) {
			for (@NonNull SerializationSegment serializationSegment : serializationSegments) {
				serializationSegment.format(formatter, serializationBuilder);
			}
		}
		else {
			formatInnerValue(formatter, serializationBuilder);
		}
	} */

	public abstract @NonNull String getGlobalSortKey(@NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id);

	protected void getGlobalSortKey(@NonNull StringBuilder s, @NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id) {
		@NonNull SerializationSegment[] serializationSegments2 = serializationSegments;
		s.append("-");
		if ((serializationSegments2 != null) && (serializationSegments2.length > 0)) {
			s.append(serializationSegments2id.get(Lists.newArrayList(serializationSegments2)));
		}
		else {
			s.append("0");
		}
	}

	public @NonNull SerializationSegment @Nullable [] getSerializationSegments() {
		return serializationSegments;
	}

	@Override
	public final int hashCode() {
		Integer hashCode2 = hashCode;
		if (hashCode2 == null) {
			hashCode = hashCode2 = computeHashCode();
		}
		return hashCode2.intValue();
	}

	/**
	 * Return true if this serialization step si appropriate for a node,
	 * @param childNode
	 * @return
	 */
	public abstract boolean matches(@NonNull INode node, @NonNull UserElementSerializer serializer);

	/**
	 * Accumulate the inner value of this serialization step as determined by the derived class.
	 */
	public abstract void serializeInnerValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);

	/**
	 * Accumulate the outer value of this serialization step as determined by its serialization segements,
	 * to serializationBuilder using the serializer for context. A coumpound step such as a sequence may
	 * use additional steps from serializer.getSerializationRule().getSerialiationSteps() starting at thisSerializationStepIndex+1,
	 * and returning the index following this and any additionally used steps.
	 */
	public int serializeOuterValue(int thisSerializationStepIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		assert serializer.getSerializationRule().getSerializationSteps()[thisSerializationStepIndex] == this;
		if (serializationSegments != null) {
			for (@NonNull SerializationSegment serializationSegment : serializationSegments) {
				serializationSegment.serialize(thisSerializationStepIndex, serializer, serializationBuilder);
			}
		}
		else {
			serializeInnerValue(thisSerializationStepIndex, serializer, serializationBuilder);
		}
		return thisSerializationStepIndex+1;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		@SuppressWarnings("null")
		@NonNull String castString = s.toString();
		return castString;
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		@NonNull SerializationSegment[] serializationSegments2 = serializationSegments;
		s.append(" ||");
		if (serializationSegments2 != null) {
			for (@NonNull SerializationSegment serializationSegment : serializationSegments2) {
				s.append(" ");
				s.append(serializationSegment.toString());
			}
		}
		else {
			s.append(" value");
		}
	}
}