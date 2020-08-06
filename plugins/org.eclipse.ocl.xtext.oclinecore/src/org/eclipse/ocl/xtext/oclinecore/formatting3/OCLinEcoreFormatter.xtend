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
package org.eclipse.ocl.xtext.oclinecore.formatting3

import com.google.inject.Inject
import org.eclipse.ocl.xtext.essentialocl.formatting3.EssentialOCLFormatter
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreConstraintCS
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS
import org.eclipse.xtext.formatting2.IFormattableDocument

class OCLinEcoreFormatter extends EssentialOCLFormatter {
	
	@Inject extension OCLinEcoreGrammarAccess

	def dispatch void format(TopLevelCS topLevelCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (importCS : topLevelCS.ownedImports) {
			importCS.format
		}
		for (packageCS : topLevelCS.ownedPackages) {
			packageCS.format
		}
	}

	def dispatch void format(OCLinEcoreConstraintCS oCLinEcoreConstraintCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		oCLinEcoreConstraintCS.ownedMessageSpecification.format
		oCLinEcoreConstraintCS.ownedSpecification.format
	}
	
	// TODO: implement for AnnotationCS, AttributeCS, DataTypeCS, DocumentationCS, EnumerationCS, EnumerationLiteralCS, ImportCS, ModelElementRefCS, OperationCS, PackageCS, ParameterCS, ImplicitOppositeCS, ReferenceCS, ExpSpecificationCS, StructuredClassCS, SysMLCS, TypedRefCS, TemplateSignatureCS, TypedTypeRefCS
}
