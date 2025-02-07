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
package org.eclipse.ocl.xtext.essentialocl.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.ocl.xtext.essentialocl.parser.antlr.internal.InternalEssentialOCLParser;
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class EssentialOCLParser extends AbstractAntlrParser {

	@Inject
	private EssentialOCLGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}


	@Override
	protected InternalEssentialOCLParser createParser(XtextTokenStream stream) {
		return new InternalEssentialOCLParser(stream, getGrammarAccess());
	}

	@Override
	protected String getDefaultRuleName() {
		return "Model";
	}

	public EssentialOCLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(EssentialOCLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
