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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class RTSerializationRule implements SerializationRule
{
	private final @NonNull RTSerializationStep @NonNull [] serializationSteps;
	private final @Nullable SubIdiom @NonNull [] staticSubIdioms;

	public RTSerializationRule(/*@NonNull*/ RTSerializationStep /*@NonNull*/ [] serializationSteps, /*@Nullable*/ SubIdiom /*@NonNull*/ [] staticSubIdioms) {
		this.serializationSteps = serializationSteps;
		this.staticSubIdioms = staticSubIdioms;
	}
	public void serializeRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializeSubRule(0, serializationSteps.length, serializer, serializationBuilder);
	}

	public @NonNull RTSerializationStep @NonNull [] getSerializationSteps() {
		return serializationSteps;
	}

	public @Nullable SubIdiom @NonNull [] getStaticSubIdioms() {
		return staticSubIdioms;
	}

	public void serializeSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (int index = startIndex; index < endIndex; ) {
			SubIdiom subIdiom = staticSubIdioms[index];		// XXX Could invite serializer to provide a dynamicSubIdiom.
			RTSerializationStep serializationStep = serializationSteps[index++];
			CardinalityVariable cardinalityVariable = serializationStep.getCardinalityVariable();
			int stepLoopCount = cardinalityVariable != null ? serializer.getValue(cardinalityVariable) : 1;
			if (serializationStep instanceof RTSerializationSequenceStep) {
				int stepsRange = ((RTSerializationSequenceStep)serializationStep).getStepsRange();
				if (subIdiom != null) {
					subIdiom.serialize(serializationStep, serializer, serializationBuilder);
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
					if (subIdiom != null) {
						subIdiom.serialize(serializationStep, serializer, serializationBuilder);
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

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		StringUtil.appendIndentation(s, depth);
		s.append("Serialization Steps");
		for (int i = 0; i < serializationSteps.length; i++) {
			StringUtil.appendIndentation(s, depth+1);
			SubIdiom subIdiom = staticSubIdioms[i];
			s.append(subIdiom != null ? subIdiom.getName() : "null");
			s.append(" == ");
			serializationSteps[i].toString(s, depth+1);
		}
	}

	@Override
	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(
			@NonNull EReference eReference) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public @NonNull String getName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public @NonNull EClass getProducedEClass() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public @NonNull SerializationNode getRootSerializationNode() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		// TODO Auto-generated method stub

	}
	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		// TODO Auto-generated method stub

	}
}