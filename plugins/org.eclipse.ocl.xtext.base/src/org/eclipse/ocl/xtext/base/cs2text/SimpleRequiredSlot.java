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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

public class SimpleRequiredSlot extends AbstractRequiredSlots //implements Iterable<@NonNull RequiredSlots>
{
//	protected final @NonNull AssignedSerializationNode serializationNode;
//	protected final @NonNull EClass debugEFeatureScope;
	protected final @NonNull XtextAssignmentAnalysis assignmentAnalysis;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public SimpleRequiredSlot(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull XtextAssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(ruleAnalysis);
	//	this.debugEFeatureScope = debugEFeatureScope;
		this.assignmentAnalysis = assignmentAnalysis;
		this.multiplicativeCardinality = multiplicativeCardinality;
	//	EClassifier eClassifierScope = XtextGrammarUtil.getEClassifierScope(assignmentAnalysis.getEClass());
	//	assert debugEFeatureScope == eClassifierScope;
		//	assert multiplicativeCardinality == assignmentAnalysis.getMultiplicativeCardinality();
	}

	public @NonNull XtextAssignmentAnalysis getAssignmentAnalysis() {
		return assignmentAnalysis;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlots> getConjunction() {
		return Collections.singletonList(this);
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return assignmentAnalysis.getEStructuralFeature();
	}

	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Override
	public @NonNull List<@NonNull SerializationRule> getSerializationRules() {		// XXX eliminate me
		SerializationRule requiredSlotsConjunction = new SerializationRule(ruleAnalysis);
		requiredSlotsConjunction.accumulate(assignmentAnalysis, multiplicativeCardinality, MultiplicativeCardinality.ONE);
		requiredSlotsConjunction.getConjunction();		// XXX eager
		return Collections.singletonList(requiredSlotsConjunction);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append("[");
		s.append(multiplicativeCardinality);
		s.append("]");
	}
}