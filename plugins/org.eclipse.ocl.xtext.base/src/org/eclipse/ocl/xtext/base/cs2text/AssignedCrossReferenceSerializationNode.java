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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;

public class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull CrossReference crossReference;
	protected final @NonNull RuleCall ruleCall;

	public AssignedCrossReferenceSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull CrossReference crossReference) {
		super(grammarAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality);
		this.crossReference = crossReference;
		this.ruleCall = (RuleCall)XtextGrammarUtil.getTerminal(crossReference);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(ruleCall.getRule().getName());
		appendCardinality(s);
	}
}