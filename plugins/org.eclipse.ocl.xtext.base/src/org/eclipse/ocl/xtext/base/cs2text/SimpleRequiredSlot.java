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

public class SimpleRequiredSlot implements RequiredSlots //implements Iterable<@NonNull RequiredSlots>
{
	protected final @NonNull AssignedSerializationNode serializationNode;
	protected final @NonNull XtextAssignmentAnalysis assignmentAnalysis;
	protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public SimpleRequiredSlot(@NonNull AssignedSerializationNode serializationNode, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.serializationNode = serializationNode;
		this.assignmentAnalysis = serializationNode.getAssignmentAnalysis();
		this.ruleAnalysis = assignmentAnalysis.getSourceRuleAnalysis();
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	public @NonNull XtextAssignmentAnalysis getAssignmentAnalysis() {
		return assignmentAnalysis;
	}

	@Override
	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunction() {
		return Collections.singletonList(this);
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return assignmentAnalysis.getEStructuralFeature();
	}

	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	public @NonNull AssignedSerializationNode getSerializationNode() {
		return serializationNode;
	}

	@Override
	public @NonNull List<@NonNull SerializationRule> getSerializationRules() {		// XXX eliminate me
		SerializationRule requiredSlotsConjunction = new SerializationRule(ruleAnalysis);
		requiredSlotsConjunction.accumulate(serializationNode, multiplicativeCardinality, MultiplicativeCardinality.ONE);
		requiredSlotsConjunction.getConjunction();		// XXX eager
		return Collections.singletonList(requiredSlotsConjunction);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

//	@Override
	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append("[");
		s.append(multiplicativeCardinality);
		s.append("]");
	}
}