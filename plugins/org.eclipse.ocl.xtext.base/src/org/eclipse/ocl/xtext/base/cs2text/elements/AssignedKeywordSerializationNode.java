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
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.Keyword;

public class AssignedKeywordSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull Keyword keyword;
	protected final @NonNull EnumerationValue enumerationValue;

	public AssignedKeywordSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Keyword keyword) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.keyword = keyword;
		this.enumerationValue = assignmentAnalysis.getGrammarAnalysis().getEnumerationValue(keyword);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AssignedKeywordSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, keyword);
	}

	@Override
	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList) {
		stepsList.add(new RTSerializationLiteralStep(staticRuleMatch.basicGetCardinalityVariable(this), enumerationValue.getName()));
		subIdiomsList.add(serializationNode2subIdioms.get(this));
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
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
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		assert !eStructuralFeature.isUnsettable() || serializer.getElement().eIsSet(eStructuralFeature);
		super.serialize(serializer, serializationBuilder);
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