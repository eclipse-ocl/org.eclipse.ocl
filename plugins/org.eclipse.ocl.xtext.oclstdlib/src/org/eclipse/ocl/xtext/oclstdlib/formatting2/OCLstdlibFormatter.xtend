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
package org.eclipse.ocl.xtext.oclstdlib.formatting2

import com.google.inject.Inject
import org.eclipse.ocl.xtext.basecs.PathNameCS
import org.eclipse.ocl.xtext.essentialocl.formatting2.EssentialOCLFormatter
import org.eclipse.ocl.xtext.oclstdlib.services.OCLstdlibGrammarAccess
import org.eclipse.ocl.xtext.oclstdlibcs.LibRootPackageCS
import org.eclipse.xtext.formatting2.IFormattableDocument

class OCLstdlibFormatter extends EssentialOCLFormatter {
	
	@Inject extension OCLstdlibGrammarAccess

	def dispatch void format(LibRootPackageCS libRootPackageCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (importCS : libRootPackageCS.ownedImports) {
			importCS.format
		}
		for (packageCS : libRootPackageCS.ownedPackages) {
			packageCS.format
		}
	}

	override dispatch void format(PathNameCS pathNameCS, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (pathElementCS : pathNameCS.ownedPathElements) {
			pathElementCS.format
		}
	}
	
	// TODO: implement for ParameterCS, AnnotationCS, LibClassCS, DocumentationCS, ImportCS, LibConstraintCS, LibCoercionCS, LibIterationCS, LambdaTypeCS, TypedTypeRefCS, LibOperationCS, LibOppositeCS, LibPackageCS, PackageCS, LibPropertyCS, ExpSpecificationCS, TypedRefCS, TuplePartCS
}
