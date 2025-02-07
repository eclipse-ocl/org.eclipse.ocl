/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.completeocl.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.ocl.xtext.completeocl.parser.antlr.internal.InternalCompleteOCLParser;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class CompleteOCLParser extends AbstractAntlrParser {

	@Inject
	private CompleteOCLGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}


	@Override
	protected InternalCompleteOCLParser createParser(XtextTokenStream stream) {
		return new InternalCompleteOCLParser(stream, getGrammarAccess());
	}

	@Override
	protected String getDefaultRuleName() {
		return "CompleteOCLDocumentCS";
	}

	public CompleteOCLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(CompleteOCLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
