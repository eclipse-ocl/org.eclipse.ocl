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
package org.eclipse.ocl.xtext.markup.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.ocl.xtext.markup.parser.antlr.internal.InternalMarkupParser;
import org.eclipse.ocl.xtext.markup.services.MarkupGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class MarkupParser extends AbstractAntlrParser {

	@Inject
	private MarkupGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens();
	}


	@Override
	protected InternalMarkupParser createParser(XtextTokenStream stream) {
		return new InternalMarkupParser(stream, getGrammarAccess());
	}

	@Override
	protected String getDefaultRuleName() {
		return "Markup";
	}

	public MarkupGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(MarkupGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
