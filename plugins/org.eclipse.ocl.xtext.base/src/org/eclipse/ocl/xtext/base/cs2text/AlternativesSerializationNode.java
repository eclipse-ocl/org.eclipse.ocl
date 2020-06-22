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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;

public class AlternativesSerializationNode extends CompositeSerializationNode
{
	public AlternativesSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality, @NonNull List<@NonNull SerializationNode> alternatives) {
		super(grammarAnalysis, cardinality, alternatives);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\t");
		s.append("{");
	//	boolean isFirst = true;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
		//	if (!isFirst) {
				s.append("\n");
		//	}
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("| ");
			serializationNode.toString(s, depth+1);
		//	isFirst = false;
		}
		s.append("\n");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s);
	}
}