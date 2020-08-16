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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class RTSerializationRule2 extends RTSerializationRule
{
	public static @NonNull RTSerializationRule2 create(@NonNull BasicSerializationRule basicSerializationRule, @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms) {
		List<@NonNull CardinalitySolutionStep> solutionStepsList = basicSerializationRule.getStaticRuleMatch().getSteps();
		@NonNull CardinalitySolutionStep @NonNull [] solutionSteps = solutionStepsList.toArray(new @NonNull CardinalitySolutionStep[solutionStepsList.size()]);
		List<@NonNull RTSerializationStep> stepsList = new ArrayList<>();
		List<@Nullable SubIdiom> subIdiomsList = new ArrayList<>();
		basicSerializationRule.getRootSerializationNode().gatherRuntime(basicSerializationRule.getStaticRuleMatch(), stepsList, serializationNode2subIdioms, subIdiomsList);
		int size = stepsList.size();
		assert size == subIdiomsList.size();
			@NonNull RTSerializationStep @NonNull [] serializationSteps = stepsList.toArray(new @NonNull RTSerializationStep[size]);
	//	@Nullable SubIdiom @NonNull [] staticSubIdioms = subIdiomsList.toArray(new @Nullable SubIdiom[size]);
		@Nullable Segment @NonNull [] @Nullable [] staticSegments = new @Nullable Segment [size] [];
		for (int i = 0; i < size; i++) {
			SubIdiom subIdiom = subIdiomsList.get(i);
			List<Segment> segments = subIdiom != null ? subIdiom.getSegments() : null;
			staticSegments[i] = segments != null ? segments.toArray(new @Nullable Segment [segments.size()]) : null;
		}
		return new RTSerializationRule2(basicSerializationRule, solutionSteps, serializationSteps, staticSegments);
	}

	private static final @NonNull Map<@NonNull BasicSerializationRule, @NonNull RTSerializationRule2> debugMap = new HashMap<>();
	private final @NonNull BasicSerializationRule basicSerializationRule;

	private RTSerializationRule2(@NonNull BasicSerializationRule basicSerializationRule, @NonNull CardinalitySolutionStep @NonNull [] solutionSteps, @NonNull RTSerializationStep @NonNull [] serializationSteps, @Nullable Segment @NonNull [] @Nullable [] staticSegments) {
		super(solutionSteps, serializationSteps, staticSegments);
		this.basicSerializationRule = basicSerializationRule;
		RTSerializationRule2 old = debugMap.put(basicSerializationRule, this);		// XXX debugging
		assert old == null;
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
		super.toString(s, depth);
	}
}