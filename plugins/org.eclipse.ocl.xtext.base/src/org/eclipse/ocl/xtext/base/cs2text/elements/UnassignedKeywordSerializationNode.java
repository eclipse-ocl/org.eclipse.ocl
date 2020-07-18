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

import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.PreSerializer;
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
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new UnassignedKeywordSerializationNode(grammarAnalysis, keyword, multiplicativeCardinality);
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return MultiplicativeCardinality.ONE;			// ?? could more accurately be ZERO or ONE
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		if (!multiplicativeCardinality.mayBeZero()) {
			super.preSerialize(preSerializer, parentStack);
		}
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.appendSoftSpace();
		serializationBuilder.append(value);
		serializationBuilder.appendSoftSpace();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\"");
		s.append(Strings.convertToJavaString(value));
		s.append("\"");
		appendCardinality(s, depth);
	}
}