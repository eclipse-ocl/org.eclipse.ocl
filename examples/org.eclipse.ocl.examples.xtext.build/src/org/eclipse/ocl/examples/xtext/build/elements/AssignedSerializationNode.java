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
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;

public interface AssignedSerializationNode extends SerializationNode
{
	@NonNull EClass getAssignedEClass();

	/**
	 * Return a bit vector of the many ParserRules that produce values for assigment to this EReference SerializationNode.
	 */
	@NonNull GrammarRuleVector getAssignedGrammarRuleVector();

	/**
	 * Return an array of the many ParserRules that produce values for assigment to this EReference SerializationNode.
	 */
	int @NonNull [] getAssignedRuleIndexes();

	@NonNull EStructuralFeature getEStructuralFeature();

	/**
	 * Return the many alternative Strings that may be assigned to this EAttribute SerializationNode.
	 * Returns an EnumerationValueNull if no Srings assigned.
	 */
	@NonNull EnumerationValue getEnumerationValue();

	@NonNull Iterable<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses();
}