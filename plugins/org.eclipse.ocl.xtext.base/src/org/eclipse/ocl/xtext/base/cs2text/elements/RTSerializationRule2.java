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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class RTSerializationRule2 extends RTSerializationRule
{
	private final @NonNull BasicSerializationRule basicSerializationRule;

	protected RTSerializationRule2(@NonNull BasicSerializationRule basicSerializationRule) {
		this.basicSerializationRule = basicSerializationRule;
	}

	@Override
	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference) {
		return basicSerializationRule.getAssignedSerializationNodes(eReference);
	}

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return basicSerializationRule;
	}

	@Override
	public @NonNull String getName() {
		return basicSerializationRule.getName();
	}

	@Override
	public @NonNull EClass getProducedEClass() {
		return basicSerializationRule.getProducedEClass();
	}

	@Override
	public @NonNull SerializationNode getRootSerializationNode() {
		return basicSerializationRule.getRootSerializationNode();
	}

	@Override
	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		return basicSerializationRule.getRuleAnalysis();
	}

	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		basicSerializationRule.toRuleString(s);
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		basicSerializationRule.toSolutionString(s, depth);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		basicSerializationRule.toString(s, depth);
	}
}