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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.Keyword;

public class AlternativeAssignedKeywordsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull List<@NonNull Keyword> keywords = new ArrayList<>();
	protected final @NonNull EnumerationValue enumerationValue;

	public AlternativeAssignedKeywordsSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Iterable<@NonNull Keyword> keywords) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.enumerationValue = grammarAnalysis.getEnumerationValue(keywords);
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AlternativeAssignedKeywordsSerializationNode(assignmentAnalysis, multiplicativeCardinality, keywords);
	}

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object object = serializer.getElement().eGet(eStructuralFeature);
		serializationBuilder.append(String.valueOf(object));
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