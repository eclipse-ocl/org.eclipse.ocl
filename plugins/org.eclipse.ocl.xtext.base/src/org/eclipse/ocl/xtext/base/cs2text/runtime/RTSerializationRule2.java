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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;

public class RTSerializationRule2 extends SerializationRule
{
	public static @NonNull RTSerializationRule2 create(@NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
		if (serializationRuleAnalysis.getRuleValueIndex() == 71) {
			RTSerializationRule2.class.getName();		// XXX
		}
		SerializationNode rootSerializationNode = serializationRuleAnalysis.getRootSerializationNode();
		StaticRuleMatch staticRuleMatch = serializationRuleAnalysis.getStaticRuleMatch();
		List<@NonNull CardinalitySolutionStep> solutionStepsList = staticRuleMatch.getSteps();
		@NonNull CardinalitySolutionStep @NonNull [] solutionSteps = solutionStepsList.toArray(new @NonNull CardinalitySolutionStep[solutionStepsList.size()]);
		List<@NonNull RTSerializationStep> stepsList = new ArrayList<>();
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms = serializationRuleAnalysis.getSerializationNode2subIdioms();
		rootSerializationNode.gatherSteps(staticRuleMatch, stepsList);
		List<@Nullable SubIdiom> subIdiomsList = gatherSubIdioms(rootSerializationNode, serializationNode2subIdioms, new ArrayList<>());
		int size = stepsList.size();
		assert size == subIdiomsList.size();
		@NonNull RTSerializationStep @NonNull [] serializationSteps = stepsList.toArray(new @NonNull RTSerializationStep[size]);
		@Nullable Segment @NonNull [] @Nullable [] staticSegments = new @Nullable Segment @NonNull [size] @Nullable [];
		for (int i = 0; i < size; i++) {
			assert subIdiomsList != null;
			SubIdiom subIdiom = subIdiomsList.get(i);
			List<Segment> segments = subIdiom != null ? subIdiom.getSegments() : null;
			staticSegments[i] = segments != null ? segments.toArray(new @Nullable Segment [segments.size()]) : null;
		}
		return new RTSerializationRule2(serializationRuleAnalysis, solutionSteps, serializationSteps, staticSegments);
	}

	private static @NonNull List<@Nullable SubIdiom> gatherSubIdioms(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList) {
		SubIdiom subIdioms = serializationNode2subIdioms.get(serializationNode);
		subIdiomsList.add(subIdioms);
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				subIdiomsList = gatherSubIdioms(nestedSerializationNode, serializationNode2subIdioms, subIdiomsList);
			}
		}
		return subIdiomsList;
	}



	private static final @NonNull Map<@NonNull SerializationRuleAnalysis, @NonNull RTSerializationRule2> debugMap = new HashMap<>();
	private final @NonNull SerializationRuleAnalysis serializationRuleAnalysis;

	private RTSerializationRule2(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull CardinalitySolutionStep @NonNull [] solutionSteps, @NonNull RTSerializationStep @NonNull [] serializationSteps, @Nullable Segment @NonNull [] @Nullable [] staticSegments) {
		super(serializationRuleAnalysis.getRuleAnalysis().getIndex(), solutionSteps, serializationSteps, staticSegments,
			serializationRuleAnalysis.basicGetEAttribute2EnumerationValues(), serializationRuleAnalysis.basicGetEReference2AssignedRuleValueIndexes(),
			serializationRuleAnalysis.getStaticRuleMatch().basicGetNeedsDefaultEAttributes(),
			serializationRuleAnalysis.basicGetEAttribute2enumerationValue2multiplicativeCardinality(),
			serializationRuleAnalysis.basicGetEReference2ruleValueIndex2multiplicativeCardinality());
		this.serializationRuleAnalysis = serializationRuleAnalysis;
		RTSerializationRule2 old = debugMap.put(serializationRuleAnalysis, this);		// XXX debugging
		assert old == null;
	}

	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		serializationRuleAnalysis.toRuleString(s);
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		serializationRuleAnalysis.toSolutionString(s, depth);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		serializationRuleAnalysis.toString(s, depth);
		super.toString(s, depth);
	}
}