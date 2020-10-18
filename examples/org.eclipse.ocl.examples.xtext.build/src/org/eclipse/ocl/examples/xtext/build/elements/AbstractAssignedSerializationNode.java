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
package org.eclipse.ocl.examples.xtext.build.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.build.analysis.AbstractRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueNull;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;

public abstract class AbstractAssignedSerializationNode extends AbstractSerializationNode implements AssignedSerializationNode
{
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The produced EClass wrt this and preceding nodes as initially defined by the rule's returned EClass and refined by Actions.
	 * This is often a sub-type of eStructuralFeature.getEContainerClass();
	 */
	protected final @NonNull EClass assignedEClass;
	protected final @NonNull EStructuralFeature eStructuralFeature;

	/**
	 * The grammar rules that can produce a compatible target value for the eStructuralFeature.
	 */
	protected final @NonNull Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses;

	protected AbstractAssignedSerializationNode(@NonNull GrammarAnalysis grammarAnalysis,
			@NonNull EClass assignedEClass,	@NonNull EStructuralFeature eStructuralFeature, @NonNull GrammarCardinality grammarCardinality,
			@NonNull Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses) {
		super(grammarCardinality);
		this.grammarAnalysis = grammarAnalysis;
		this.assignedEClass = assignedEClass;
		this.eStructuralFeature = eStructuralFeature;
		this.targetRuleAnalyses = targetRuleAnalyses;
	}

	@Override
	public @NonNull EClass getAssignedEClass() {
		return assignedEClass;
	}

	@Override
	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return EnumerationValueNull.INSTANCE;
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public @NonNull Iterable<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses() {
		return targetRuleAnalyses;
	}
}