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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;

public class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull CrossReference crossReference;
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;
//	private @NonNull ICrossReferenceSerializer crossReferenceSerializer;

	public AssignedCrossReferenceSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull CrossReference crossReference) {
		super(assignmentAnalysis, multiplicativeCardinality);
		RuleCall ruleCall = (RuleCall) XtextGrammarUtil.getTerminal(crossReference);
		AbstractRule calledRule = XtextGrammarUtil.getRule(ruleCall);
		this.calledRuleAnalysis = assignmentAnalysis.getGrammarAnalysis().getRuleAnalysis(calledRule);
		this.crossReference = crossReference;
//		this.crossReferenceSerializer = assignmentAnalysis.getGrammarAnalysis().getCrossReferenceSerializer();
		assert !((EReference)eStructuralFeature).isContainment();
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
		return Collections.singletonList(calledRuleAnalysis);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AssignedCrossReferenceSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, crossReference);
	}

	@Override
	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList) {
		stepsList.add(new RTSerializationCrossReferenceStep(staticRuleMatch.basicGetCardinalityVariable(this), eStructuralFeature, crossReference));
		subIdiomsList.add(serializationNode2subIdioms.get(this));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}