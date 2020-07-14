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
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractAssignedSerializationNode extends SimpleSerializationNode implements AssignedSerializationNode
{
	protected final @NonNull EClass eFeatureScope;

	protected final @NonNull XtextAssignmentAnalysis assignmentAnalysis;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull RequiredSlots requiredSlots;

	protected AbstractAssignedSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull EClass eFeatureScope, @NonNull XtextAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(ruleAnalysis, multiplicativeCardinality);
		this.eFeatureScope = eFeatureScope;
		this.assignmentAnalysis = assignmentAnalysis;
		this.eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		this.requiredSlots = new SimpleRequiredSlot(ruleAnalysis, eFeatureScope, assignmentAnalysis, multiplicativeCardinality);
	}

	@Override
	public @NonNull EClass getEFeatureScope() {
		return eFeatureScope;
	}

	@Override
	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		return requiredSlots;
	}

	protected @Nullable Object getValueOrValues() {
		return null;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
		super.preSerialize(preSerializer);
		Object valueOrValues = getValueOrValues();
		preSerializer.addAssignedNode(this, valueOrValues);
	}
}