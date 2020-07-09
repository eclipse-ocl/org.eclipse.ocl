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
package org.eclipse.ocl.xtext.oclinecore.formatting2

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder
import org.eclipse.ocl.xtext.base.cs2text.UserModelAnalysis
import org.eclipse.ocl.xtext.base.cs2text.XtextGrammarAnalysis
import org.eclipse.ocl.xtext.base.utilities.AbstractGrammarResource
import org.eclipse.ocl.xtext.basecs.ImportCS
import org.eclipse.ocl.xtext.basecs.TypedRefCS
import org.eclipse.ocl.xtext.essentialocl.formatting2.EssentialOCLFormatter
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreConstraintCS
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS
import org.eclipse.xtext.conversion.IValueConverterService
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion
import org.eclipse.xtext.linking.impl.LinkingHelper
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer

class OCLinEcoreFormatter extends EssentialOCLFormatter {
	
	@Inject extension OCLinEcoreGrammarAccess

	@Inject
	private IValueConverterService valueConverterService;

	@Inject
	private LinkingHelper linkingHelper;
	
	@Inject
	private ICrossReferenceSerializer crossReferenceSerializer;

	def dispatch void format(TopLevelCS topLevelCS, extension IFormattableDocument document) {
		val IEObjectRegion regionForEObject = topLevelCS.regionForEObject;
		val EObject grammarElement = regionForEObject.grammarElement;
		var XtextGrammarAnalysis grammarAnalysis = new XtextGrammarAnalysis(grammarElement.eResource() as AbstractGrammarResource, crossReferenceSerializer, valueConverterService, linkingHelper);
		grammarAnalysis.analyze();
		var String s1 = grammarAnalysis.toString();
		System.out.println(s1);
		System.out.println("\n");
		var UserModelAnalysis modelAnalysis = new UserModelAnalysis(grammarAnalysis);
		modelAnalysis.analyze(topLevelCS);
		var String s2 = modelAnalysis.toString();
		System.out.println(s2);
		var SerializationBuilder serializationBuilder = new SerializationBuilder();
		modelAnalysis.serialize(serializationBuilder, topLevelCS);
		var String s3 = serializationBuilder.toRenderedString();
		System.out.println(s3);
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
		val regionFor2 = oCLinEcoreConstraintCS.regionFor;
		oCLinEcoreConstraintCS.ownedMessageSpecification.format
		oCLinEcoreConstraintCS.ownedSpecification.format
	}
	
	// TODO: implement for AnnotationCS, AttributeCS, DataTypeCS, DocumentationCS, EnumerationCS, EnumerationLiteralCS, ImportCS, ModelElementRefCS, OperationCS, PackageCS, ParameterCS, ImplicitOppositeCS, ReferenceCS, ExpSpecificationCS, StructuredClassCS, SysMLCS, TypedRefCS, TemplateSignatureCS, TypedTypeRefCS

	def dispatch void format(ImportCS csImport, extension IFormattableDocument document) {
		val IEObjectRegion regionForEObject = csImport.regionForEObject;
		val EObject grammarElement = regionForEObject.grammarElement;
		val regionFor2 = csImport.regionFor;
		regionFor2.keyword(";").prepend[noSpace].append[newLine];
	}

	def dispatch void format(TypedRefCS csTypedRef, extension IFormattableDocument document) {
		val regionFor2 = csTypedRef.regionFor;
		regionFor2.keyword(";");//.prepend[noSpace].append[newLine];
	}
}
