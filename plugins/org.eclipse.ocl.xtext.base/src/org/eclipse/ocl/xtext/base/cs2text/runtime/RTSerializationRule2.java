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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class RTSerializationRule2 extends RTSerializationRule
{
	private final @NonNull BasicSerializationRule basicSerializationRule;
	private final @NonNull RTSerializationStep @NonNull [] serializationSteps;
	private final @Nullable SubIdiom @NonNull [] staticSubIdioms;

	public RTSerializationRule2(@NonNull BasicSerializationRule basicSerializationRule, @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms) {
		this.basicSerializationRule = basicSerializationRule;
		List<@NonNull RTSerializationStep> stepsList = new ArrayList<>();
		List<@Nullable SubIdiom> subIdiomsList = new ArrayList<>();
		basicSerializationRule.getRootSerializationNode().gatherRuntime(basicSerializationRule.getStaticRuleMatch(), stepsList, serializationNode2subIdioms, subIdiomsList);
		int size = stepsList.size();
		assert size == subIdiomsList.size();
		this.serializationSteps = stepsList.toArray(new @NonNull RTSerializationStep[size]);
		this.staticSubIdioms = subIdiomsList.toArray(new @Nullable SubIdiom[size]);
	}

	@Override
	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference) {
		return basicSerializationRule.getAssignedSerializationNodes(eReference);
	}

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return basicSerializationRule;
	}

	@Override
	public @NonNull String getName() {
		return basicSerializationRule.getName();
	}

	@Override
	public @NonNull EClass getProducedEClass() {
		return basicSerializationRule.getProducedEClass();
	}

	@Override
	public @NonNull SerializationNode getRootSerializationNode() {
		return basicSerializationRule.getRootSerializationNode();
	}

	@Override
	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		return basicSerializationRule.getRuleAnalysis();
	}

	@Override
	public void serializeRule(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializeSubRule(0, serializationSteps.length, serializer, serializationBuilder);
	}
	private void serializeSubRule(int startIndex, int endIndex, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (int index = startIndex; index < endIndex; ) {
			RTSerializationStep serializationStep = serializationSteps[index];
			CardinalityVariable cardinalityVariable = serializationStep.getCardinalityVariable();
			int stepLoopCount = cardinalityVariable != null ? serializer.getValue(cardinalityVariable) : 1;
			if (serializationStep instanceof RTSerializationSequenceStep) {
				index += 1;
				int stepsRange = ((RTSerializationSequenceStep)serializationStep).getStepsRange();
				for (int i = 0; i < stepLoopCount; i++) {
					serializeSubRule(index, index + stepsRange, serializer, serializationBuilder);
				}
				index += stepsRange;
			}
			else {
				SubIdiom subIdiom = staticSubIdioms[index];		// XXX Could invite serializer to provide a dynamicSubIdiom.
				for (int i = 0; i < stepLoopCount; i++) {
					if (subIdiom != null) {
						subIdiom.serialize(serializationStep, serializer, serializationBuilder);
					}
					else {
						serializationStep.serialize(serializer, serializationBuilder);
					}
				}
				index += 1;
			}
		}
	}

	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		basicSerializationRule.toRuleString(s);
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		basicSerializationRule.toSolutionString(s, depth);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		basicSerializationRule.toString(s, depth);
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
}