/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.oclinecore.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.ocl.xtext.oclinecore.parser.antlr.internal.InternalOCLinEcoreParser;
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class OCLinEcoreParser extends AbstractAntlrParser {

	@Inject
	private OCLinEcoreGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}


	@Override
	protected InternalOCLinEcoreParser createParser(XtextTokenStream stream) {
		return new InternalOCLinEcoreParser(stream, getGrammarAccess());
	}

	@Override
	protected String getDefaultRuleName() {
		return "TopLevelCS";
	}

	public OCLinEcoreGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(OCLinEcoreGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
