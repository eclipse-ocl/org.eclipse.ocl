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
package org.eclipse.ocl.xtext.completeocl.formatting3

import com.google.inject.Inject
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS
import org.eclipse.ocl.xtext.essentialocl.formatting3.EssentialOCLFormatter
import org.eclipse.xtext.formatting2.IFormattableDocument

class CompleteOCLFormatter extends EssentialOCLFormatter {
	
	@Inject extension CompleteOCLGrammarAccess

	def dispatch void format(CompleteOCLDocumentCS completeOCLDocumentCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (importCS : completeOCLDocumentCS.ownedImports) {
			importCS.format
		}
		for (packageDeclarationCS : completeOCLDocumentCS.ownedPackages) {
			packageDeclarationCS.format
		}
		for (contextDeclCS : completeOCLDocumentCS.ownedContexts) {
			contextDeclCS.format
		}
	}

	def dispatch void format(ClassifierContextDeclCS classifierContextDeclCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		classifierContextDeclCS.ownedSignature.format
		classifierContextDeclCS.ownedPathName.format
		for (constraintCS : classifierContextDeclCS.ownedInvariants) {
			constraintCS.format
		}
		for (defCS : classifierContextDeclCS.ownedDefinitions) {
			defCS.format
		}
	}
	
	// TODO: implement for ConstraintCS, DefOperationCS, ParameterCS, DefPropertyCS, ImportCS, OperationContextDeclCS, PackageDeclarationCS, PropertyContextDeclCS, ExpSpecificationCS, TemplateSignatureCS
}
