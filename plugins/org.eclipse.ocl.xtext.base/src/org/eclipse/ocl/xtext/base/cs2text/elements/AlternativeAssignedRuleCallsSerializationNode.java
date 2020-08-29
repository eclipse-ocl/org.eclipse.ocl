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
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

import com.google.common.collect.Iterables;

/**
 * An AlternativeAssignedKeywordsSerializationNode corresponds to the parsing specification of a variety of keywords
 * one of which is assigned to a String feature in the parsed model. The alternative keywords behave as an enumeration.
 * Multiple AlternativeAssignedKeywordsSerializationNode for the same feature may define orthogonal or overlapping
 * enumerations.
 */
public class AlternativeAssignedRuleCallsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull Iterable<@NonNull AbstractRuleAnalysis> calledRuleAnalyses;
	private @Nullable Integer semanticHashCode = null;

	public AlternativeAssignedRuleCallsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Iterable<@NonNull AbstractRuleAnalysis> calledRuleAnalyses) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.calledRuleAnalyses = calledRuleAnalyses;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AlternativeAssignedRuleCallsSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, calledRuleAnalyses);
	}

	@Override
	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList) {
		@NonNull AbstractRuleValue [] calledRuleValues = new @NonNull AbstractRuleValue [Iterables.size(calledRuleAnalyses)];
		int i= 0;
		for (@NonNull AbstractRuleAnalysis ruleAnalysis : calledRuleAnalyses) {
			calledRuleValues[i++] = new ProxyRuleValue(ruleAnalysis);
		}
		stepsList.add(new RTSerializationAssignedRuleCallsStep(staticRuleMatch.getCardinalityVariableIndex(this), eStructuralFeature, calledRuleValues));
		subIdiomsList.add(serializationNode2subIdioms.get(this));
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
		return calledRuleAnalyses;
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
		if (!this.calledRuleAnalyses.equals(that.calledRuleAnalyses)) {
			return false;
		}
		return true;
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode();
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : calledRuleAnalyses) {
				hash = 3*hash + + ruleAnalysis.hashCode();;
			}
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("(");
		boolean isFirst = true;
		for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(calledRuleAnalysis.getRuleName());
			isFirst = false;
		}
		s.append(")");
		appendCardinality(s, depth);
	}
}