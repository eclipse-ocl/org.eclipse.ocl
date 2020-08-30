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
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

/**
 * An AlternativeAssignedKeywordsSerializationNode corresponds to the parsing specification of a variety of keywords
 * one of which is assigned to a String feature in the parsed model. The alternative keywords behave as an enumeration.
 * Multiple AlternativeAssignedKeywordsSerializationNode for the same feature may define orthogonal or overlapping
 * enumerations.
 */
public class AlternativeAssignedRuleCallsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull IndexVector calledRuleIndexes;
	private @Nullable Integer semanticHashCode = null;

	public AlternativeAssignedRuleCallsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull IndexVector calledRuleIndexes) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.calledRuleIndexes = calledRuleIndexes;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AlternativeAssignedRuleCallsSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, calledRuleIndexes);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList) {
		stepsList.add(new RTSerializationAssignedRuleCallsStep(staticRuleMatch.getCardinalityVariableIndex(this), eStructuralFeature, calledRuleIndexes));
	}

	@Override
	public @Nullable IndexVector getAssignedRuleIndexes() {
		return calledRuleIndexes;
	}

	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AlternativeAssignedRuleCallsSerializationNode)) {
			return false;
		}
		AlternativeAssignedRuleCallsSerializationNode that = (AlternativeAssignedRuleCallsSerializationNode)serializationNode;
		if (this.eStructuralFeature != that.eStructuralFeature) {
			return false;
		}
		if (!this.calledRuleIndexes.equals(that.calledRuleIndexes)) {
			return false;
		}
		return true;
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode() + calledRuleIndexes.hashCode();;
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		GrammarAnalysis grammarAnalysis = assignmentAnalysis.getGrammarAnalysis();
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("(");
		boolean isFirst = true;
		for (@NonNull Integer calledRuleIndex : calledRuleIndexes) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(grammarAnalysis.getRuleValue(calledRuleIndex).getName());
			isFirst = false;
		}
		s.append(")");
		appendCardinality(s, depth);
	}
}