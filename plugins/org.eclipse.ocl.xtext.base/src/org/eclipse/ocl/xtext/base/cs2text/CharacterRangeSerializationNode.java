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
import org.eclipse.xtext.CharacterRange;
import org.eclipse.xtext.util.Strings;

public class CharacterRangeSerializationNode extends SimpleSerializationNode
{
//	protected final @NonNull CharacterRange characterRange;
	protected final @NonNull String left;
	protected final @NonNull String right;


	public CharacterRangeSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull CharacterRange characterRange) {
		super(grammarAnalysis, characterRange.getCardinality());
	//	this.characterRange = characterRange;
		this.left = XtextGrammarUtil.getValue(XtextGrammarUtil.getLeft(characterRange));
		this.right = XtextGrammarUtil.getValue(XtextGrammarUtil.getRight(characterRange));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("[\"");
		s.append(Strings.convertToJavaString(left));
		s.append("\"-\"");
		s.append(Strings.convertToJavaString(right));
		s.append("\"]");
		appendCardinality(s);
	}
}