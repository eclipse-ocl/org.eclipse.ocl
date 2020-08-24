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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

public class SerializationRule
{
	private final int ruleValueIndex;
	private final @NonNull RTSerializationStep @NonNull [] serializationSteps;
	private final @Nullable Segment @NonNull [] @NonNull [] staticSegments;

	public SerializationRule(int ruleValueIndex,
			/*@NonNull*/ CardinalitySolutionStep /*@NonNull*/ [] solutionSteps,
			/*@NonNull*/ RTSerializationStep /*@NonNull*/ [] serializationSteps,
			/*@Nullable*/ Segment /*@NonNull*/ [] /*@NonNull*/ [] staticSegments) {
		this.ruleValueIndex = ruleValueIndex;
		this.serializationSteps = serializationSteps;
		this.staticSegments = staticSegments;
	}

//	@Override
//	public @Nullable Map<@NonNull EReference, @NonNull IndexVector> getEReference2DiscriminatingRuleValueIndexes() {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException();
//	}

	public @NonNull RTSerializationStep @NonNull [] getSerializationSteps() {
		return serializationSteps;
	}

//	public @Nullable SubIdiom @NonNull [] getStaticSubIdioms() {
//		return staticSubIdioms;
//	}

	public @Nullable Segment @NonNull [] @NonNull [] getStaticSegments() {
		return staticSegments;
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
//		assert staticRuleMatch != null;
//		return staticRuleMatch.match(slotsAnalysis);
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public void serializeRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializeSubRule(0, serializationSteps.length, serializer, serializationBuilder);
	}

	public void serializeSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (int index = startIndex; index < endIndex; ) {
			Segment[] segments = staticSegments[index];		// XXX Could invite serializer to provide a dynamicSubIdiom.
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

	public @Nullable Set<@NonNull ParserRuleValue> getAssignedRuleValues(@NonNull EReference eReference) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public int getRuleValueIndex() {
		return ruleValueIndex;
	}

	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public void toRuleString(@NonNull StringBuilder s) {
		// TODO Auto-generated method stub
	}

	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		// TODO Auto-generated method stub
	}
}