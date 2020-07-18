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
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.PreSerializer;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class AlternativeUnassignedKeywordsSerializationNode extends AbstractSerializationNode
{
	protected final @NonNull List<@NonNull String> values = new ArrayList<>();

	public AlternativeUnassignedKeywordsSerializationNode(@NonNull GrammarAnalysis grammarAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @Nullable Iterable<@NonNull String> values) {
		super(grammarAnalysis, multiplicativeCardinality);
		if (values != null) {
			for (@NonNull String value : values) {
				this.values.add(value);
			}
		}
	}

	public void addKeyword(@NonNull Keyword keyword) {
		assert keyword.getCardinality() == null;
		values.add(XtextGrammarUtil.getValue(keyword));
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new AlternativeUnassignedKeywordsSerializationNode(grammarAnalysis, multiplicativeCardinality, values);
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		preSerializer.addSerializedNode(this, parentStack);
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.appendSoftSpace();
		serializationBuilder.append(values.get(0));
		serializationBuilder.appendSoftSpace();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst = true;
		if (values.size() > 1) {
			s.append("{");
		}
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append("\"");
			s.append(Strings.convertToJavaString(value));
			s.append("\"");
			isFirst = false;
		}
		if (values.size() > 1) {
			s.append("}");
		}
		appendCardinality(s, depth);
	}
}