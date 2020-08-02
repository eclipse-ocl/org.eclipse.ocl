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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;

public interface AssignedSerializationNode extends SerializationNode
{
	@NonNull EClass getAssignedEClass();

	/**
	 * Return the RuleAnalysis that specifes matches to this SerializationNode.
	 */
	@Nullable AbstractRuleAnalysis getAssignedRuleAnalysis();

	@NonNull AssignmentAnalysis getAssignmentAnalysis();
	@NonNull EStructuralFeature getEStructuralFeature();
	@NonNull EnumerationValue getEnumerationValue();
}