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
import org.eclipse.emf.ecore.EReference;
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
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepCrossReference;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;

public class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull CrossReference crossReference;
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;
	protected final int @NonNull [] calledRuleIndexes;
	private @Nullable GrammarRuleVector grammarRuleVector =null;

	public AssignedCrossReferenceSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, @NonNull CrossReference crossReference) {
		this(assignmentAnalysis.getGrammarAnalysis(), assignmentAnalysis.getEClass(), assignmentAnalysis.getEStructuralFeature(), grammarCardinality, crossReference, assignmentAnalysis.getTargetRuleAnalyses());
	}

	private AssignedCrossReferenceSerializationNode(@NonNull GrammarAnalysis grammarAnalysis,
			@NonNull EClass assignedEClass,	@NonNull EStructuralFeature eStructuralFeature, @NonNull GrammarCardinality grammarCardinality,
			@NonNull CrossReference crossReference, @NonNull Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses) {
		super(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, targetRuleAnalyses);
		RuleCall ruleCall = (RuleCall) SerializationUtils.getTerminal(crossReference);
		AbstractRule calledRule = SerializationUtils.getRule(ruleCall);
		this.calledRuleAnalysis = grammarAnalysis.getRuleAnalysis(calledRule);
		this.calledRuleIndexes = new int [] { calledRuleAnalysis.getIndex() };
		this.crossReference = crossReference;
		assert !((EReference)eStructuralFeature).isContainment();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new AssignedCrossReferenceSerializationNode(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, crossReference, targetRuleAnalyses);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepCrossReference(eStructuralFeature, crossReference, calledRuleAnalysis.getIndex(), eachSerializationSegments));
	}

	@Override
	public @NonNull GrammarRuleVector getAssignedGrammarRuleVector() {
		GrammarRuleVector grammarRuleVector2 = grammarRuleVector;
		if (grammarRuleVector2 == null) {
			grammarRuleVector = grammarRuleVector2 = new GrammarRuleVector(calledRuleIndexes);
		}
		return grammarRuleVector2;
	}

	@Override
	public int @NonNull [] getAssignedRuleIndexes() {
		return calledRuleIndexes;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.appendEStructuralFeatureName(assignedEClass, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getName());
		appendCardinality(s, depth);
	}
}