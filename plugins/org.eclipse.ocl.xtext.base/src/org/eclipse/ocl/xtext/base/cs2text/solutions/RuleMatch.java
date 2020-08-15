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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

/**
 * A RuleMatch identifoes some common expression evaluation functionality for constant folding by the compile-time StaticRuleMatch
 * and full evaluation by the run-time DynamicRuleMatch.
 */
public interface RuleMatch
{
	/**#
	 * Return the known integer value of cardinalityVariable if known, or null if not yet knowable.
	 */
	@Nullable Integer basicGetIntegerSolution(int cardinalityVariableIndex);

	/**#
	 * Return thevalue of ca rdinalityVariable which may be null if no solution approach resolved so far..
	 */
	@Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable);
	@Nullable CardinalitySolution basicGetSolution(int cardinalityVariableIndex);

	/**
	 * Return the SerializationRule that this RuleMatch elaborates.
	 */
//	@NonNull BasicSerializationRule getSerializationRule();

	/**
	 * Return the number of eAttribute slot elements in the user model element. For a non-NullEnumerationValue, the
	 * number of slot elements that match known enumerationValue elements is returned. Returns null if not yet computable.
	 */
	@Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature);

	/**
	 * Return the number of eAttribute slot elements in the user model element. For a non-NullEnumerationValue, the
	 * number of slot elements that match known enumerationValue elements is returned. Returns null if not yet computable.
	 */
	@Nullable Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue);

	/**
	 * Return the number of eStructuralFeature slot elements in the user model element. For a non-NullEnumerationValue, the
	 * number of slot elements that match known enumerationValue elements is returned. Returns null if not yet computable.
	 */
	@Nullable Integer getSize(@NonNull EReference eReference, @NonNull ParserRuleValue parserRuleValue);
}