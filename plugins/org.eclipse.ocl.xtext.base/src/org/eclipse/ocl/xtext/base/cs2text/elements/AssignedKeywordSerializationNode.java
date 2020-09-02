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
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepLiteral;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.Keyword;

public class AssignedKeywordSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull Keyword keyword;
	protected final @NonNull EnumerationValue enumerationValue;
	private @Nullable Integer semanticHashCode = null;

	public AssignedKeywordSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, @NonNull Keyword keyword) {
		super(assignmentAnalysis, grammarCardinality);
		this.keyword = keyword;
		this.enumerationValue = assignmentAnalysis.getGrammarAnalysis().getEnumerationValue(keyword);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new AssignedKeywordSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, grammarCardinality, keyword);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		stepsList.add(new SerializationStepLiteral(staticRuleMatch.getCardinalityVariableIndex(this), enumerationValue.getName()));
	}

	@Override
	public @NonNull Integer @Nullable [] getAssignedRuleIndexes() {
		return null;
	}

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	public @NonNull String getValue() {
		return enumerationValue.getName();
	}

	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AssignedKeywordSerializationNode)) {
			return false;
		}
		AssignedKeywordSerializationNode that = (AssignedKeywordSerializationNode)serializationNode;
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
		s.append("'");
		s.append(getValue());
		s.append("'");
		appendCardinality(s, depth);
	}
}