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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class KeywordSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull String value;

	public KeywordSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Keyword keyword) {
		super(grammarAnalysis, MultiplicativeCardinality.toEnum(keyword.getCardinality()));
		this.value = XtextGrammarUtil.getValue(keyword);
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return MultiplicativeCardinality.ONE;			// ?? could more ccurately be ZERO or ONE
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
		if (!multiplicativeCardinality.mayBeZero()) {
			super.preSerialize(preSerializer);
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