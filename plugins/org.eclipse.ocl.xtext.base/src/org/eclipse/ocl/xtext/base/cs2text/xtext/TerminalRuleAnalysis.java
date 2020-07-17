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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.TerminalRule;

/**
 * An XtextTerminalRuleAnalysis provides the extended analysis of an Xtext TerminalRule
 */
public class TerminalRuleAnalysis extends AbstractRuleAnalysis
{
	public TerminalRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, @NonNull TerminalRule terminalRule) {
		super(grammarAnalysis, terminalRule);
	}

	public @NonNull TerminalRule getTerminalRule() {
		return (TerminalRule)abstractRule;
	}
}