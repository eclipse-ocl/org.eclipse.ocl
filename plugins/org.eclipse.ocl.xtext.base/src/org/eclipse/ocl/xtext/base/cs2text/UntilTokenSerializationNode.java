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
import org.eclipse.xtext.UntilToken;
import org.eclipse.xtext.util.Strings;

public class UntilTokenSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull String value;

	public UntilTokenSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull UntilToken untilToken) {
		super(grammarAnalysis, MultiplicativeCardinality.toEnum(untilToken.getCardinality()));
		this.value = XtextGrammarUtil.getValue((Keyword)XtextGrammarUtil.getTerminal(untilToken));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(" -> \"");
		s.append(Strings.convertToJavaString(value));
		s.append("\"");
		appendCardinality(s);
	}
}