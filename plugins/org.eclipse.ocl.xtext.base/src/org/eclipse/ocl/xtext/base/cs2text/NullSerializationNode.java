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

/**
 * A NullSerializationNode is used to indicate nothing to serialize avoiding the need for a genuinely
 * null value that confuses the XtextSwitch.
 */
public class NullSerializationNode extends SimpleSerializationNode
{
	public NullSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis) {
		super(grammarAnalysis, MultiplicativeCardinality.ZERO_OR_MORE);
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("«null»");
		appendCardinality(s, depth);
	}
}