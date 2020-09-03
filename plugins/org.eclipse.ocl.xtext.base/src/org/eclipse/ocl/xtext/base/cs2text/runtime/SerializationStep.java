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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.util.Strings;

public abstract class SerializationStep
{
	public static abstract class SerializationStepAbstractFeature extends SerializationStep
	{
		protected final @NonNull EStructuralFeature eStructuralFeature;

		protected SerializationStepAbstractFeature(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature) {
			super(variableIndex);
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
	}

	public static class SerializationStepAssignKeyword extends SerializationStepAbstractFeature
	{
		protected final @NonNull EnumerationValue enumerationValue;

		public SerializationStepAssignKeyword(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
			super(variableIndex, eStructuralFeature);
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

		@Override
		public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			Object object = serializer.consumeNext(eStructuralFeature);
			serializationBuilder.append(String.valueOf(object));
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			super.toString(s, depth);
			s.append(XtextGrammarUtil.getName(XtextGrammarUtil.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(XtextGrammarUtil.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			s.append(enumerationValue);
		}
	}

	public static class SerializationStepAssignedRuleCall extends SerializationStepAbstractFeature
	{
		private int calledRuleIndex;

		public SerializationStepAssignedRuleCall(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, int calledValueIndex) {
			super(variableIndex, eStructuralFeature);
			this.calledRuleIndex = calledValueIndex;
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
		public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		//	Object object = serializer.consumeNext(eStructuralFeature);
		//	serializationBuilder.append(String.valueOf(object));
			Object eGet = serializer.consumeNext(eStructuralFeature);
			if (eStructuralFeature instanceof EReference) {
				assert ((EReference)eStructuralFeature).isContainment();
				if (eGet != null) {
					serializer.serializeElement(serializationBuilder, (EObject)eGet, serializer.getModelAnalysis().getGrammarAnalysis().getRuleValue(calledRuleIndex));
				}
			}
			else {
				String val = serializer.getModelAnalysis().getValueConverterService().toString(eGet, serializer.getModelAnalysis().getGrammarAnalysis().getRuleValue(calledRuleIndex).getRuleName());
				serializationBuilder.append(String.valueOf(val));
			}
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			super.toString(s, depth);
			s.append(XtextGrammarUtil.getName(XtextGrammarUtil.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(XtextGrammarUtil.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			s.append(calledRuleIndex);
		}
	}


	public static class SerializationStepAssigns extends SerializationStepAbstractFeature
	{
		private @Nullable EnumerationValue enumerationValue;
		private @NonNull Integer @Nullable [] calledRuleIndexes;	// Cannot use GrammarRuleVEctor since must preserve declaration order

		public SerializationStepAssigns(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, @Nullable EnumerationValue enumerationValue, @NonNull Integer @Nullable [] calledRuleIndexes) {
			super(variableIndex, eStructuralFeature);
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
			if (!ClassUtil.safeEquals(this.enumerationValue, that.enumerationValue)) {
				return false;
			}
			if (!ClassUtil.safeEquals(this.calledRuleIndexes, that.calledRuleIndexes)) {
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
		public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			Object object = serializer.consumeNext(eStructuralFeature);
			UserModelAnalysis modelAnalysis = serializer.getModelAnalysis();
			SerializationGrammarAnalysis grammarAnalysis = modelAnalysis.getGrammarAnalysis();
			@NonNull Integer[] calledRuleIndexes2 = calledRuleIndexes;
			if (eStructuralFeature instanceof EAttribute) {
				EnumerationValue enumerationValue2 = enumerationValue;
				if (enumerationValue2 != null) {
					String string = String.valueOf(object);
					if (enumerationValue2.isElement(string)) {
						serializationBuilder.append(string);
						return;
					}
				}
				if (calledRuleIndexes2 != null) {
					for (int calledRuleIndex : calledRuleIndexes2) {
						@NonNull GrammarRuleValue calledRuleValue = grammarAnalysis.getRuleValue(calledRuleIndex);
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
				Segment[][] staticSegments = serializer.getStaticSegments();
				for (int calledRuleIndex : calledRuleIndexes2) {			// search for matching rule
					@NonNull GrammarRuleValue calledRuleValue = grammarAnalysis.getRuleValue(calledRuleIndex);
					for (@NonNull SerializationRule serializationRule : ((ParserRuleValue)calledRuleValue).getSerializationRules()) {
						DynamicRuleMatch match = serializationRule.match(slotsAnalysis, staticSegments);
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
			super.toString(s, depth);
			s.append(XtextGrammarUtil.getName(XtextGrammarUtil.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(XtextGrammarUtil.getName(eStructuralFeature));
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
		}
	}

	public static class SerializationStepCrossReference extends SerializationStepAbstractFeature
	{
		protected final @NonNull CrossReference crossReference;		// May be an equivalent - different container

		public SerializationStepCrossReference(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull CrossReference crossReference) {
			super(variableIndex, eStructuralFeature);
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
			if ("implementation".equals(eStructuralFeature.getName())) {
				getClass();		// XXX
			}
			AbstractElement thisTerminal = this.crossReference.getTerminal();
			AbstractElement thatTerminal = that.crossReference.getTerminal();
			if ((thisTerminal instanceof RuleCall) && (thatTerminal instanceof RuleCall)) {
				return ((RuleCall)thisTerminal).getRule() == ((RuleCall)thatTerminal).getRule();
			}
			else {
				return ClassUtil.safeEquals(thisTerminal, thatTerminal);		// Never happens
			}
		}

		public @NonNull CrossReference getCrossReference() {
			return crossReference;
		}

		@Override
		public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			EObject eGet = (EObject)serializer.consumeNext(eStructuralFeature);
			EObject context = serializer.getElement();
			String string = serializer.getModelAnalysis().getCrossReferenceSerializer().serializeCrossRef(context, crossReference, eGet, null, null);
			serializationBuilder.append(string);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			super.toString(s, depth);
			s.append(XtextGrammarUtil.getName(XtextGrammarUtil.getEContainingClass(eStructuralFeature)));
			s.append("::");
			s.append(XtextGrammarUtil.getName(eStructuralFeature));
			s.append(eStructuralFeature.isMany() ? "+=" : "=");
			s.append(((RuleCall)crossReference.getTerminal()).getRule().getName());
		}
	}

	public static class SerializationStepLiteral extends SerializationStep
	{
		protected final @NonNull String string;

		public SerializationStepLiteral(int variableIndex,  @NonNull String string) {
			super(variableIndex);
			this.string = string;
		}
		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * string.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof SerializationStepLiteral)) {
				return false;
			}
			return equalTo((SerializationStepLiteral)obj);
		}

		protected boolean equalTo(@NonNull SerializationStepLiteral that) {
			return super.equalTo(that) && this.string.equals(that.string);
		}

		public @NonNull String getString() {
			return string;
		}

		@Override
		public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			serializationBuilder.append(string);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			super.toString(s, depth);
			s.append("'");
			s.append(Strings.convertToJavaString(string));
			s.append("'");
		}
	}

	public static class SerializationStepSequence extends SerializationStep
	{
		/**
		 * The number of steps within the linearized steps for the rule that support the sequence.
		 */
		private int startIndex = 0;
		private int endIndex = 0;

		public SerializationStepSequence(int variableIndex, int startIndex, int endIndex) {
			super(variableIndex);
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public int computeHashCode() {
			return super.computeHashCode() + 5 * startIndex + 7 * endIndex;
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
			return super.equalTo(that) && (this.startIndex == that.startIndex) && (this.endIndex == that.endIndex);
		}

		public int getEndIndex() {
			return endIndex;
		}

		public int getStartIndex() {
			return startIndex;
		}

		public int getStepsRange() {
			return endIndex- startIndex;
		}

		@Override
		public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			serializer.getSerializationRule().serializeSubRule(startIndex, endIndex, serializer, serializationBuilder);
		}

		public void setSubRange(int startIndex, int endIndex) {
			assert startIndex >= 0;
			assert endIndex > startIndex;
			this.startIndex = startIndex ;
			this.endIndex = endIndex ;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			super.toString(s, depth);
			s.append("steps-");
			s.append(startIndex);
			s.append("..");
			s.append(endIndex);
		}
	}

	protected final int variableIndex;		// -ve not used
	private @Nullable Integer hashCode = null;

	protected SerializationStep(int variableIndex) {
		this.variableIndex = variableIndex;
	}

	protected int computeHashCode() {
		return getClass().hashCode() + 7 * variableIndex;
	}

	@Override
	public abstract boolean equals(Object obj);

	protected boolean equalTo(@NonNull SerializationStep that) {
		return this.variableIndex == that.variableIndex;
	}

	public int getVariableIndex() {
		return variableIndex;
	}

	@Override
	public final int hashCode() {
		Integer hashCode2 = hashCode;
		if (hashCode2 == null) {
			hashCode = hashCode2 = computeHashCode();
		}
		return hashCode2.intValue();
	}

	public abstract void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		if (variableIndex >= 0) {
			s.append(String.format("V%02d", variableIndex));
		}
		else {
			s.append("1");
		}
		s.append("*");
	//	if (cardinalityVariable != null) {
	//		s.append(cardinalityVariable.getName());
	//		s.append("*");
	//	}
	}
}