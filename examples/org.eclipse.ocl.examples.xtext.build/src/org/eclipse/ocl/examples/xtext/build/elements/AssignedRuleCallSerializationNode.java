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
package org.eclipse.ocl.examples.xtext.build.elements;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.AbstractRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.DirectAssignmentAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignedRuleCall;

public class AssignedRuleCallSerializationNode extends AbstractAssignedSerializationNode
{
	protected final int calledRuleIndex;
	protected final int @NonNull [] calledRuleIndexes;
	private @Nullable GrammarRuleVector grammarRuleVector = null;

	public AssignedRuleCallSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, int calledRuleIndex) {
		this(assignmentAnalysis.getGrammarAnalysis(), assignmentAnalysis.getEClass(), assignmentAnalysis.getEStructuralFeature(), grammarCardinality, calledRuleIndex, assignmentAnalysis.getTargetRuleAnalyses());
	}

	public AssignedRuleCallSerializationNode(@NonNull GrammarAnalysis grammarAnalysis,
			@NonNull EClass assignedEClass,	@NonNull EStructuralFeature eStructuralFeature, @NonNull GrammarCardinality grammarCardinality,
			int calledRuleIndex, @NonNull Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses) {
		super(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, targetRuleAnalyses);
		this.calledRuleIndex = calledRuleIndex;
		this.calledRuleIndexes = new int @NonNull [] { calledRuleIndex };
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new AssignedRuleCallSerializationNode(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, calledRuleIndex, targetRuleAnalyses);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepAssignedRuleCall(eStructuralFeature, calledRuleIndex, eachSerializationSegments));
	}

	@Override
	public @NonNull GrammarRuleVector getAssignedGrammarRuleVector() {
		GrammarRuleVector grammarRuleVector2 = grammarRuleVector;
		if (grammarRuleVector2 == null) {
			grammarRuleVector = grammarRuleVector2 = new GrammarRuleVector(calledRuleIndexes);
		}
		return grammarRuleVector2;
	}

	public int getAssignedRuleIndex() {
		return calledRuleIndex;
	}

	@Override
	public int @NonNull [] getAssignedRuleIndexes() {
		return calledRuleIndexes;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.appendEStructuralFeatureName(assignedEClass, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		AbstractRuleAnalysis ruleAnalysis = grammarAnalysis.basicGetRuleAnalysis(calledRuleIndex);
		if (ruleAnalysis != null) {
			s.append(ruleAnalysis.getName());
		}
		else {
			GrammarRuleValue ruleValue = grammarAnalysis.basicGetRuleValue(calledRuleIndex);
			if (ruleValue != null) {
				s.append(ruleValue.getName());
			}
			else {
				s.appendRuleName(calledRuleIndex);
			}
		}
		appendCardinality(s, depth);
	}
}