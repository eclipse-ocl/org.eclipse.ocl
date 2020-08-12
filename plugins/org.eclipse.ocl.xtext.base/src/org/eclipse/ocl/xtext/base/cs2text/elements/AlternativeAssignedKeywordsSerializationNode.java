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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
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

	public AlternativeAssignedKeywordsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Iterable<@NonNull Keyword> keywords) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.keywords = keywords;
		this.enumerationValue = assignmentAnalysis.getGrammarAnalysis().getEnumerationValue(keywords);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) {
			multiplicativeCardinality = this.multiplicativeCardinality;
		}
		return new AlternativeAssignedKeywordsSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, keywords);
	}

	@Override
	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> steps) {
		steps.add(new RTSerializationAssignStep(staticRuleMatch.basicGetCardinalityVariable(this), eStructuralFeature));
	}

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
		return null;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("{");
		s.append(enumerationValue);
		s.append("}");
		appendCardinality(s, depth);
	}
}