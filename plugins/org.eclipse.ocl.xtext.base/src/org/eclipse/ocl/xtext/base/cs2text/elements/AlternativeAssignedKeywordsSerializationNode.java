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
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.StaticRuleMatch;
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
	private @Nullable Integer semanticHashCode = null;

	public AlternativeAssignedKeywordsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis,
			@NonNull GrammarCardinality grammarCardinality, @NonNull Iterable<@NonNull Keyword> keywords) {
		super(assignmentAnalysis, grammarCardinality);
		this.keywords = keywords;
		this.enumerationValue = assignmentAnalysis.getGrammarAnalysis().getEnumerationValue(keywords);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) {
			grammarCardinality = this.grammarCardinality;
		}
		return new AlternativeAssignedKeywordsSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, grammarCardinality, keywords);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		stepsList.add(new SerializationStepAssignKeyword(staticRuleMatch.getCardinalityVariableIndex(this), eStructuralFeature, enumerationValue));
	}

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public @NonNull Integer @Nullable [] getAssignedRuleIndexes() {
		return null;
	}

	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AlternativeAssignedKeywordsSerializationNode)) {
			return false;
		}
		AlternativeAssignedKeywordsSerializationNode that = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
		if (this.eStructuralFeature != that.eStructuralFeature) {
			return false;
		}
		if (this.enumerationValue != that.enumerationValue) {
			return false;
		}
		return true;
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode() + enumerationValue.hashCode();
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
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