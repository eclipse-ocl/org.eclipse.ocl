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
	protected final @NonNull XtextAssignmentAnalysis assignmentAnalysis;
	protected final @NonNull EStructuralFeature eStructuralFeature;

	protected AbstractAssignedSerializationNode(@NonNull XtextAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(assignmentAnalysis.getSourceRuleAnalysis(), multiplicativeCardinality);
		this.assignmentAnalysis = assignmentAnalysis;
		this.eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
	}

	@Override
	public @NonNull XtextAssignmentAnalysis getAssignmentAnalysis() {
		return assignmentAnalysis;
	}

	@Override
	public @NonNull EClass getEFeatureScope() {
		return assignmentAnalysis.getEClass();
	}

	@Override
	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
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