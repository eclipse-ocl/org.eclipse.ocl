/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.markup.formatting2

import com.google.inject.Inject
import org.eclipse.ocl.xtext.markup.services.MarkupGrammarAccess
import org.eclipse.ocl.xtext.markupcs.BulletElement
import org.eclipse.ocl.xtext.markupcs.Markup
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class MarkupFormatter extends AbstractFormatter2 {
	
	@Inject extension MarkupGrammarAccess

	def dispatch void format(Markup markup, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (markupElement : markup.elements) {
			markupElement.format
		}
	}

	def dispatch void format(BulletElement bulletElement, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (markupElement : bulletElement.elements) {
			markupElement.format
		}
	}
	
	// TODO: implement for FontElement, FootnoteElement, HeadingElement, NullElement, OCLCodeElement, OCLEvalElement, OCLTextElement
}
