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

public class AlternativeKeywordsSerializationNode extends AbstractSerializationNode
{
	protected final @NonNull List<@NonNull String> values = new ArrayList<>();

	public AlternativeKeywordsSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(grammarAnalysis, multiplicativeCardinality);
	}

	public void addKeyword(@NonNull Keyword keyword) {
		assert keyword.getCardinality() == null;
		values.add(XtextGrammarUtil.getValue(keyword));
	}

	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		return NullRequiredSlots.INSTANCE;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
		preSerializer.addSerializedNode(this);
	}

	@Override
	public void serialize(@NonNull ConsumedSlotsConjunction consumedSlotsConjunction, @NonNull SerializationBuilder serializationBuilder) {
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