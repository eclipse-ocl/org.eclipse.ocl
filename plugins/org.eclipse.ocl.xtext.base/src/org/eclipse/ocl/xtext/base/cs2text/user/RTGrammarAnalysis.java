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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue;

public class RTGrammarAnalysis extends AbstractGrammarAnalysis
{
	protected final @NonNull AbstractRuleValue @NonNull [] ruleValues;

	public RTGrammarAnalysis(/*@NonNull*/ EClassValue /*@NonNull*/ [] eClassValues, /*@NonNull*/ AbstractRuleValue /*@NonNull*/ [] ruleValues) {
		@SuppressWarnings("null")
		@NonNull EClassValue @NonNull [] castEClassValues = (@NonNull EClassValue @NonNull [])eClassValues;
		for (@NonNull EClassValue eClassValue : castEClassValues) {
			addEClassValue(eClassValue);
		}
		@SuppressWarnings("null")
		@NonNull AbstractRuleValue @NonNull [] castRuleValues = (@NonNull AbstractRuleValue @NonNull [])ruleValues;
		this.ruleValues = castRuleValues;
	}

	@Override
	public @NonNull String getRuleName(int ruleValueIndex) {
		return ruleValues[ruleValueIndex].getRuleName();
	}

	@Override
	public @NonNull AbstractRuleValue getRuleValue(int ruleValueIndex) {
		return ruleValues[ruleValueIndex];
	}
}
