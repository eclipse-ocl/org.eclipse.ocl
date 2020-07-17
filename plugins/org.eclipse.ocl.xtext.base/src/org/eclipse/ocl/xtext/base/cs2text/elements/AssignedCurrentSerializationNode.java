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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class AssignedCurrentSerializationNode extends AbstractAssignedSerializationNode
{
	public AssignedCurrentSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(assignmentAnalysis, multiplicativeCardinality);
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
//		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(XtextGrammarUtil.getName(assignmentAnalysis.getEClass()));
		s.append("::");
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append("«current»");
		appendCardinality(s, depth);
	}
}