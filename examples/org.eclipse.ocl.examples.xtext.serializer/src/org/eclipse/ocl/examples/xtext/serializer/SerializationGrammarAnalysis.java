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
package org.eclipse.ocl.examples.xtext.serializer;

import org.eclipse.jdt.annotation.NonNull;

public class SerializationGrammarAnalysis extends AbstractGrammarAnalysis
{
	protected final @NonNull GrammarRuleValue @NonNull [] ruleValues;

	public SerializationGrammarAnalysis(/*@NonNull*/ EClassValue /*@NonNull*/ [] eClassValues, /*@NonNull*/ GrammarRuleValue /*@NonNull*/ [] ruleValues) {
		@SuppressWarnings("null")
		@NonNull EClassValue @NonNull [] castEClassValues = (@NonNull EClassValue @NonNull [])eClassValues;
		for (@NonNull EClassValue eClassValue : castEClassValues) {
			addEClassValue(eClassValue);
		}
		@SuppressWarnings("null")
		@NonNull GrammarRuleValue @NonNull [] castRuleValues = (@NonNull GrammarRuleValue @NonNull [])ruleValues;
		this.ruleValues = castRuleValues;
	}

	@Override
	public @NonNull String getRuleName(int ruleValueIndex) {
		return ruleValues[ruleValueIndex].getRuleName();
	}

	@Override
	public @NonNull GrammarRuleValue getRuleValue(int ruleValueIndex) {
		return ruleValues[ruleValueIndex];
	}
}
