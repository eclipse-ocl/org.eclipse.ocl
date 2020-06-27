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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractAssignedSerializationNode extends SimpleSerializationNode implements AssignedSerializationNode
{
	protected final @NonNull EStructuralFeature eFeature;
	protected final @NonNull RequiredSlots requiredSlots;

	protected AbstractAssignedSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature, @Nullable String cardinality) {
		super(grammarAnalysis, cardinality);
		this.eFeature = eFeature;
		this.requiredSlots = new SimpleRequiredSlot(this);
	}

	@Override
	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eFeature;
	}

	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		return requiredSlots;
	}
}