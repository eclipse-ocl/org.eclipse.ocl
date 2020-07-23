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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class UnassignedKeywordSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull Keyword keyword;
	protected final @NonNull String value;

	public UnassignedKeywordSerializationNode(@NonNull GrammarAnalysis grammarAnalysis, @NonNull Keyword keyword, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(grammarAnalysis, multiplicativeCardinality);
		this.keyword = keyword;
		this.value = XtextGrammarUtil.getValue(keyword);
		assert !multiplicativeCardinality.mayBeZero();
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new UnassignedKeywordSerializationNode(grammarAnalysis, keyword, multiplicativeCardinality);
	}

	public @NonNull String getValue() {
		return value;
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append(value);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\"");
		s.append(Strings.convertToJavaString(value));
		s.append("\"");
		appendCardinality(s, depth);
	}
}