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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class AlternativeAssignedKeywordsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull List<@NonNull Keyword> keywords = new ArrayList<>();
	protected final @NonNull List<@NonNull String> values = new ArrayList<>();

	public AlternativeAssignedKeywordsSerializationNode(@NonNull XtextAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Iterable<@NonNull Keyword> values) {
		super(assignmentAnalysis, multiplicativeCardinality);
		for (@NonNull Keyword keyword : keywords) {
			this.values.add(XtextGrammarUtil.getValue(keyword));
		}
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, multiplicativeCardinality, keywords);
	}

	@Override
	protected @NonNull Iterable<@NonNull String> getValueOrValues() {
		return values;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst = true;
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("{");
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append("\"");
			s.append(Strings.convertToJavaString(value));
			s.append("\"");
			isFirst = false;
		}
		s.append("}");
		appendCardinality(s, depth);
	}
}