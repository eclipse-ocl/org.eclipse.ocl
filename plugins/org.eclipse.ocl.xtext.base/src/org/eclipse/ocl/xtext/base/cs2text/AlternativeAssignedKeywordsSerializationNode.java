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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class AlternativeAssignedKeywordsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull List<@NonNull String> values = new ArrayList<>();

	public AlternativeAssignedKeywordsSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature) {
		super(grammarAnalysis, eFeatureScope, eStructuralFeature, null);
	}

	public void addKeyword(@NonNull Keyword keyword) {
		assert keyword.getCardinality() == null;
		values.add(XtextGrammarUtil.getValue(keyword));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst = true;
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("{");
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append("\"");
			s.append(Strings.convertToJavaString(value));
			s.append("\"");
			isFirst = false;
		}
		s.append("}");
		appendCardinality(s);
	}
}