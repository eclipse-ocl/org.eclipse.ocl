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

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
import org.eclipse.xtext.TerminalRule;

/**
 * An XtextTerminalRuleAnalysis provides the extended analysis of an Xtext TerminalRule
 */
public class TerminalRuleAnalysis extends AbstractRuleAnalysis
{
	protected final @NonNull EDataType eDataType;
	private @Nullable TerminalRuleValue terminalRuleValue = null;

	public TerminalRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull TerminalRule terminalRule) {
		super(grammarAnalysis, index, terminalRule);
		this.eDataType = (EDataType)SerializationUtils.getClassifier(terminalRule.getType());
	}

	@Override
	public @Nullable TerminalRuleValue basicGetRuleValue() {
		return terminalRuleValue;
	}

	@Override
	public @NonNull TerminalRuleValue getRuleValue() {
		TerminalRuleValue terminalRuleValue2 = terminalRuleValue;
		if (terminalRuleValue2 == null) {
			terminalRuleValue = terminalRuleValue2 = new TerminalRuleValue(index, getName());
		}
		return terminalRuleValue2;
	}

	public @NonNull TerminalRule getTerminalRule() {
		return (TerminalRule)abstractRule;
	}
}