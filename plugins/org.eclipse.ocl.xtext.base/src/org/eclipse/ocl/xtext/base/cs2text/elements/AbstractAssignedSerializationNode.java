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

import java.util.Stack;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.PreSerializer;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.NullEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;

public abstract class AbstractAssignedSerializationNode extends SimpleSerializationNode implements AssignedSerializationNode
{
	protected final @NonNull AssignmentAnalysis assignmentAnalysis;
	protected final @NonNull EStructuralFeature eStructuralFeature;

	protected AbstractAssignedSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(assignmentAnalysis.getGrammarAnalysis(), multiplicativeCardinality);
		this.assignmentAnalysis = assignmentAnalysis;
		this.eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
	}

	@Override
	public @NonNull AssignmentAnalysis getAssignmentAnalysis() {
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

	@Override
	public @NonNull EnumerationValue getEnumerationValue() {
		return NullEnumerationValue.INSTANCE;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		super.preSerialize(preSerializer, parentStack);
		EnumerationValue enumerationValue = getEnumerationValue();
		preSerializer.addAssignedNode(this, enumerationValue, parentStack);
	}
}