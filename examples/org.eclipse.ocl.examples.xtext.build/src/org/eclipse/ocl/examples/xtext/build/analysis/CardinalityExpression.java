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
package org.eclipse.ocl.examples.xtext.build.analysis;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;

/**
 * A CardinalityExpression equates the sum of CardinalityVariable products to the number of elements in an eStructuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public interface CardinalityExpression extends Nameable
{
	void addMultiplicityProduct(@NonNull Iterable<@NonNull CardinalityVariable> variables);
	boolean analyzeMayBeZeroCommonFactors(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, boolean mayBeMany);
	boolean analyzeRedundantProducts(@NonNull SerializationRuleAnalysis serializationRuleAnalysis);
	boolean analyzeTrivial(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, boolean mayBeMany);
	boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch);
	CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue);
	@Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions();
	@Nullable List<@NonNull Integer> getUnknownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis);
	@Nullable List<@NonNull Integer> getUnknownVariables(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull Iterable<@NonNull CardinalityVariable> product);
	boolean isOne();
	int solve(@NonNull DynamicRuleMatch dynamicRuleMatch);
	void toString(@NonNull StringBuilder s, int depth);
}