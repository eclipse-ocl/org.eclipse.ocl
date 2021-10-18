/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.idioms.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.ocl.examples.xtext.idioms.parser.antlr.internal.InternalIdiomsParser;
import org.eclipse.ocl.examples.xtext.idioms.services.IdiomsGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class IdiomsParser extends AbstractAntlrParser {

	@Inject
	private IdiomsGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}


	@Override
	protected InternalIdiomsParser createParser(XtextTokenStream stream) {
		return new InternalIdiomsParser(stream, getGrammarAccess());
	}

	@Override
	protected String getDefaultRuleName() {
		return "IdiomsModel";
	}

	public IdiomsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(IdiomsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
