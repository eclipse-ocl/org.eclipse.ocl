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
import org.eclipse.xtext.Wildcard;

public class WildcardSerializationNode extends SimpleSerializationNode
{
	public WildcardSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull Wildcard wildcard) {
		super(ruleAnalysis, MultiplicativeCardinality.toEnum(wildcard));
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("«WILDCARD»");
		appendCardinality(s, depth);
	}
}