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
package org.eclipse.ocl.xtext.base.cs2text.user;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

/**
 * A RuleMatch identifoes some common expression evaluation functionality for constant folding by the compile-time StaticRuleMatch
 * and full evaluation by the run-time DynamicRuleMatch.
 */
public interface RuleMatch
{
	/**#
	 * Return the known integer value of cardinalityVariable if known, or null if not yet knowable.
	 */
	@Nullable Integer basicGetIntegerSolution(@NonNull CardinalityVariable cardinalityVariable);

	/**
	 * Rerturn the SerializationRule that this RuleMatch elaborates.
	 */
	@NonNull BasicSerializationRule getSerializationRule();

	/**#
	 * Return the number of eStructuralFeature slot elements in the user model element. For a non-NullEnumerationValue, the
	 * number of slot elements that match known enumerationValue elements is returned. Returns null if not yet computable.
	 */
	@Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue);
}