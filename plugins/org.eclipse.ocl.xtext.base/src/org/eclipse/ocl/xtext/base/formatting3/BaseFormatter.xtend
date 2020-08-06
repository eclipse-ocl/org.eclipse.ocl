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
package org.eclipse.ocl.xtext.base.formatting3

import com.google.inject.Inject
import org.eclipse.ocl.xtext.base.services.BaseGrammarAccess
import org.eclipse.ocl.xtext.basecs.PathNameCS
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class BaseFormatter extends AbstractFormatter2 {
	
	@Inject extension BaseGrammarAccess

	def dispatch void format(PathNameCS pathNameCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (pathElementCS : pathNameCS.ownedPathElements) {
			pathElementCS.format
		}
	}

	def dispatch void format(TemplateBindingCS templateBindingCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (templateParameterSubstitutionCS : templateBindingCS.ownedSubstitutions) {
			templateParameterSubstitutionCS.format
		}
		templateBindingCS.ownedMultiplicity.format
	}
	
	// TODO: implement for TemplateParameterSubstitutionCS, TemplateSignatureCS, TypeParameterCS, TypedTypeRefCS, WildcardTypeRefCS
}
