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
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.xtext.Keyword;

/**
 * An AlternativeAssignedKeywordsSerializationNode corresponds to the parsing specification of a variety of keywords
 * one of which is assigned to a String feature in the parsed model. The alternative keywords behave as an enumeration.
 * Multiple AlternativeAssignedKeywordsSerializationNode for the same feature may define orthogonal or overlapping
 * enumerations.
 */
public class AlternativeAssignedKeywordsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull Iterable<@NonNull Keyword> keywords;
	protected final @NonNull EnumerationValue enumerationValue;

	public AlternativeAssignedKeywordsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, @NonNull Iterable<@NonNull Keyword> keywords) {
		this(assignmentAnalysis.getGrammarAnalysis(), assignmentAnalysis.getEClass(), assignmentAnalysis.getEStructuralFeature(), grammarCardinality, keywords, assignmentAnalysis.getTargetRuleAnalyses());
	}

	private AlternativeAssignedKeywordsSerializationNode(@NonNull GrammarAnalysis grammarAnalysis,
			@NonNull EClass assignedEClass,	@NonNull EStructuralFeature eStructuralFeature, @NonNull GrammarCardinality grammarCardinality,
			@NonNull Iterable<@NonNull Keyword> keywords, @NonNull Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses) {
		super(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, targetRuleAnalyses);
		this.keywords = keywords;
		this.enumerationValue = grammarAnalysis.getEnumerationValue(keywords);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) {
			grammarCardinality = this.grammarCardinality;
		}
		return new AlternativeAssignedKeywordsSerializationNode(grammarAnalysis, assignedEClass, eStructuralFeature, grammarCardinality, keywords, targetRuleAnalyses);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepAssignKeyword(eStructuralFeature, enumerationValue, eachSerializationSegments));
	}

	@Override
	public @NonNull GrammarRuleVector getAssignedGrammarRuleVector() {
		return GrammarRuleVector.NO_INDEXES_VECTOR;
	}

	@Override
	public int @NonNull [] getAssignedRuleIndexes() {
		return GrammarRuleVector.NO_INDEXES;
	}

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.appendEStructuralFeatureName(assignedEClass, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("{");
		s.appendObject(enumerationValue);
		s.append("}");
		appendCardinality(s, depth);
	}
}