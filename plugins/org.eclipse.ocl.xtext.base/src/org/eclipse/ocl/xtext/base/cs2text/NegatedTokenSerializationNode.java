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
import org.eclipse.xtext.NegatedToken;

public class NegatedTokenSerializationNode extends AbstractSerializationNode
{
	protected final @NonNull SerializationNode serializationNode;

	public NegatedTokenSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull NegatedToken negatedToken, @NonNull SerializationNode serializationNode) {
		super(grammarAnalysis, negatedToken.getCardinality());
		this.serializationNode = serializationNode;
	}

	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		return NullRequiredSlots.INSTANCE;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\t");
		s.append("~");
		appendCardinality(s);
		s.append(" ");
		serializationNode.toString(s, depth);
	}
}