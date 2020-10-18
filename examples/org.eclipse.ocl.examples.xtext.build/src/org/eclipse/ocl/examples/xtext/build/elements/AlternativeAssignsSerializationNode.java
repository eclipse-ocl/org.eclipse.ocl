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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.AnalysisUtils;
import org.eclipse.ocl.examples.xtext.build.analysis.DirectAssignmentAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
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
	protected final @Nullable EnumerationValue enumerationValue;
	protected final @NonNull Integer @Nullable [] calledRuleIndexes;			// Cannot use GrammarRuleVector since need to preseve declaration order
//	private @Nullable Integer semanticHashCode = null;

	public AlternativeAssignsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis,
			@NonNull GrammarCardinality grammarCardinality, @Nullable Iterable<@NonNull Keyword> keywords, @NonNull Integer @Nullable [] calledRuleIndexes) {
		super(assignmentAnalysis, grammarCardinality);
		this.keywords = keywords;
		this.enumerationValue = keywords != null ? assignmentAnalysis.getGrammarAnalysis().getEnumerationValue(keywords) : null;
		this.calledRuleIndexes = calledRuleIndexes;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) {
			grammarCardinality = this.grammarCardinality;
		}
		return new AlternativeAssignsSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, grammarCardinality, keywords, calledRuleIndexes);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepAssigns(eStructuralFeature, enumerationValue, calledRuleIndexes, eachSerializationSegments));
	}

	@Override
	public @NonNull Integer @Nullable [] getAssignedRuleIndexes() {
		return calledRuleIndexes;
	}

	@Override
	public @Nullable EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

/*	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AlternativeAssignsSerializationNode)) {
			return false;
		}
		AlternativeAssignsSerializationNode that = (AlternativeAssignsSerializationNode)serializationNode;
		if (this.eStructuralFeature != that.eStructuralFeature) {
			return false;
		}
		if (this.enumerationValue != that.enumerationValue) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.calledRuleIndexes, that.calledRuleIndexes)) {
			return false;
		}
		return true;
	} */

/*	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode();
			if (enumerationValue != null) {
				 hash += enumerationValue.hashCode();
			}
			if (calledRuleIndexes != null) {
				 hash += calledRuleIndexes.hashCode();
			}
			this.semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		GrammarAnalysis grammarAnalysis = assignmentAnalysis.getGrammarAnalysis();
		AnalysisUtils.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("(");
		boolean isFirst = true;
		if (enumerationValue != null) {
			s.append(enumerationValue);
			isFirst = false;
		}
		if (calledRuleIndexes != null) {
			for (@NonNull Integer calledRuleIndex : calledRuleIndexes) {
				if (!isFirst) {
					s.append("|");
				}
				GrammarRuleValue ruleValue = grammarAnalysis.basicGetRuleValue(calledRuleIndex);
				s.append(ruleValue != null ? ruleValue.getRuleName() : calledRuleIndex);
				isFirst = false;
			}
		}
		s.append(")");
		appendCardinality(s, depth);
	}
}