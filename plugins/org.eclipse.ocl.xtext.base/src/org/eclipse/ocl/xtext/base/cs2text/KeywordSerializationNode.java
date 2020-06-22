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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class KeywordSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull String value;

	public KeywordSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Keyword keyword) {
		super(grammarAnalysis, keyword.getCardinality());
		this.value = XtextGrammarUtil.getValue(keyword);
	}

	@Override
	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
		serializationBuilder.appendSoftSpace();
		serializationBuilder.append(value);
		serializationBuilder.appendSoftSpace();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\"");
		s.append(Strings.convertToJavaString(value));
		s.append("\"");
		appendCardinality(s);
	}
}