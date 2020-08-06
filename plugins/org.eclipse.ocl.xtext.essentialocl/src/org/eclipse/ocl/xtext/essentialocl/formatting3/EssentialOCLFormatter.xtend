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
package org.eclipse.ocl.xtext.essentialocl.formatting3

import com.google.inject.Inject
import org.eclipse.ocl.xtext.base.formatting3.BaseFormatter
import org.eclipse.ocl.xtext.basecs.PathNameCS
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS
import org.eclipse.xtext.formatting2.IFormattableDocument

class EssentialOCLFormatter extends BaseFormatter {
	
	@Inject extension EssentialOCLGrammarAccess

	def dispatch void format(ContextCS contextCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		contextCS.ownedExpression.format
	}

	override dispatch void format(PathNameCS pathNameCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (pathElementCS : pathNameCS.ownedPathElements) {
			pathElementCS.format
		}
	}
	
	// TODO: implement for CollectionTypeCS, MapTypeCS, TupleTypeCS, TuplePartCS, CollectionLiteralExpCS, CollectionLiteralPartCS, CollectionPatternCS, ShadowPartCS, PatternExpCS, LambdaLiteralExpCS, MapLiteralExpCS, MapLiteralPartCS, TupleLiteralExpCS, TupleLiteralPartCS, TypedRefCS, TypeLiteralExpCS, TypeNameExpCS, InfixExpCS, PrefixExpCS, NameExpCS, CurlyBracketedClauseCS, RoundBracketedClauseCS, SquareBracketedClauseCS, NavigatingArgCS, VariableCS, IfExpCS, IfThenExpCS, LetExpCS, LetVariableCS, NestedExpCS
}
