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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.xtext.Keyword;

public class AssignedKeywordSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull String value;

	public AssignedKeywordSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature, @Nullable String cardinality, @NonNull Keyword keyword) {
		super(grammarAnalysis, eFeature, cardinality);
		this.value = XtextGrammarUtil.getValue(keyword);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(XtextGrammarUtil.getName(eFeature));
		s.append(eFeature.isMany() ? "+=" : "=");
		s.append("\"");
		s.append(value);
		s.append("\"");
		appendCardinality(s);
	}
}