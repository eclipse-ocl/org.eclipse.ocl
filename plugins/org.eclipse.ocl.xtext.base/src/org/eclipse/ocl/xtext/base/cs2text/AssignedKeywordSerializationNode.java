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
package org.eclipse.ocl.xtext.base.cs2text;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.Keyword;

public class AssignedKeywordSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull Keyword keyword;
	protected final @NonNull String value;

	public AssignedKeywordSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull EClass eFeatureScope, @NonNull XtextAssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Keyword keyword) {
		super(ruleAnalysis, eFeatureScope, assignmentAnalysis, multiplicativeCardinality);
		this.keyword = keyword;
		this.value = XtextGrammarUtil.getValue(keyword);
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AssignedKeywordSerializationNode(ruleAnalysis, eFeatureScope, assignmentAnalysis, multiplicativeCardinality, keyword);
	}

	@Override
	protected @NonNull String getValueOrValues() {
		return value;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("\"");
		s.append(value);
		s.append("\"");
		appendCardinality(s, depth);
	}
}