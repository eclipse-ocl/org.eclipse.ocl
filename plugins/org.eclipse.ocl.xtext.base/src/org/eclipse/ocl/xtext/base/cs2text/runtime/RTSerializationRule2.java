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
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;

public class RTSerializationRule2 extends SerializationRule
{
	public static @NonNull RTSerializationRule2 create(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms) {
		List<@NonNull CardinalitySolutionStep> solutionStepsList = serializationRuleAnalysis.getStaticRuleMatch().getSteps();
		@NonNull CardinalitySolutionStep @NonNull [] solutionSteps = solutionStepsList.toArray(new @NonNull CardinalitySolutionStep[solutionStepsList.size()]);
		List<@NonNull RTSerializationStep> stepsList = new ArrayList<>();
		List<@Nullable SubIdiom> subIdiomsList = new ArrayList<>();
		serializationRuleAnalysis.getRootSerializationNode().gatherRuntime(serializationRuleAnalysis.getStaticRuleMatch(), stepsList, serializationNode2subIdioms, subIdiomsList);
		int size = stepsList.size();
		assert size == subIdiomsList.size();
		@NonNull RTSerializationStep @NonNull [] serializationSteps = stepsList.toArray(new @NonNull RTSerializationStep[size]);
	//	@Nullable SubIdiom @NonNull [] staticSubIdioms = subIdiomsList.toArray(new @Nullable SubIdiom[size]);
		@Nullable Segment @NonNull [] @Nullable [] staticSegments = new @Nullable Segment @NonNull [size] @Nullable [];
		for (int i = 0; i < size; i++) {
			SubIdiom subIdiom = subIdiomsList.get(i);
			List<Segment> segments = subIdiom != null ? subIdiom.getSegments() : null;
			staticSegments[i] = segments != null ? segments.toArray(new @Nullable Segment [segments.size()]) : null;
		}
		return new RTSerializationRule2(serializationRuleAnalysis, solutionSteps, serializationSteps, staticSegments);
	}

	private static final @NonNull Map<@NonNull SerializationRuleAnalysis, @NonNull RTSerializationRule2> debugMap = new HashMap<>();
	private final @NonNull SerializationRuleAnalysis serializationRuleAnalysis;

	private RTSerializationRule2(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull CardinalitySolutionStep @NonNull [] solutionSteps, @NonNull RTSerializationStep @NonNull [] serializationSteps, @Nullable Segment @NonNull [] @Nullable [] staticSegments) {
		super(serializationRuleAnalysis.getRuleAnalysis().getIndex(), solutionSteps, serializationSteps, staticSegments, null /*serializationRuleAnalysis.getEReference2AssignedRuleValues()*/);	// XXX
		this.serializationRuleAnalysis = serializationRuleAnalysis;
		RTSerializationRule2 old = debugMap.put(serializationRuleAnalysis, this);		// XXX debugging
		assert old == null;
	}

	@Override
	public @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis() {
		return serializationRuleAnalysis;
	}

	@Override
	public @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		return serializationRuleAnalysis.getEnumerationValues(eAttribute);
	}

	@Override
	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		return serializationRuleAnalysis.match(slotsAnalysis);
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