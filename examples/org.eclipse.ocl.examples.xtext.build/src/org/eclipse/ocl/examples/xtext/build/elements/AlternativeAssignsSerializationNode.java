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
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueNull;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssigns;
import org.eclipse.xtext.Keyword;

/**
 * An AlternativeAssignsSerializationNode corresponds to the parsing specification of a variety of keywords or rule calls
 * one of which is assigned to a String feature in the parsed model. The alternative keywords behave as an enumeration.
 * Multiple AlternativeAssignsSerializationNode for the same feature may define orthogonal or overlapping
 * enumerations.
 */
public class AlternativeAssignsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @Nullable Iterable<@NonNull Keyword> keywords;
	protected final @NonNull EnumerationValue enumerationValue;
	protected final int @NonNull [] calledRuleIndexes;			// Cannot use GrammarRuleVector since need to preseve declaration order
	private @Nullable GrammarRuleVector grammarRuleVector;

	public AlternativeAssignsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, @Nullable Iterable<@NonNull Keyword> keywords, int @NonNull [] calledRuleIndexes) {
		this(assignmentAnalysis.getGrammarAnalysis(), assignmentAnalysis.getEClass(), assignmentAnalysis.getEStructuralFeature(), grammarCardinality, keywords, calledRuleIndexes, assignmentAnalysis.getTargetRuleAnalyses());
	}

	public AlternativeAssignsSerializationNode(@NonNull GrammarAnalysis grammarAnalysis,
			@NonNull EClass assignedEClass,	@NonNull EStructuralFeature eStructuralFeature, @NonNull GrammarCardinality grammarCardinality,
			@Nullable Iterable<@NonNull Keyword> keywords, int @NonNull [] calledRuleIndexes,
			@NonNull Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses) {
		super(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, targetRuleAnalyses);
		this.keywords = keywords;
		this.enumerationValue = keywords != null ? grammarAnalysis.getEnumerationValue(keywords) : EnumerationValueNull.INSTANCE;
		this.calledRuleIndexes = calledRuleIndexes;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) {
			grammarCardinality = this.grammarCardinality;
		}
		return new AlternativeAssignsSerializationNode(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, keywords, calledRuleIndexes, targetRuleAnalyses);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepAssigns(eStructuralFeature, enumerationValue, calledRuleIndexes, eachSerializationSegments));
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
	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.appendEStructuralFeatureName(assignedEClass, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("(");
		boolean isFirst = true;
		if (!enumerationValue.isNull()) {
			s.appendObject(enumerationValue);
			isFirst = false;
		}
		for (int calledRuleIndex : calledRuleIndexes) {
			if (!isFirst) {
				s.append("|");
			}
			GrammarRuleValue ruleValue = grammarAnalysis.basicGetRuleValue(calledRuleIndex);
			s.appendRuleName(ruleValue != null ? ruleValue.getIndex() : calledRuleIndex);
			isFirst = false;
		}
		s.append(")");
		appendCardinality(s, depth);
	}
}