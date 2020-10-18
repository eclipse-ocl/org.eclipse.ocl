/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.serializer;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.tokens.CrossReferenceSerializer;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class IdiomsCrossReferenceSerializer extends CrossReferenceSerializer
{
	@Inject
	private LinkingHelper linkingHelper;

	@Inject
	private IValueConverterService valueConverter;

	public IdiomsCrossReferenceSerializer() {
		super();
	}

	@Override
	protected String getCrossReferenceNameFromScope(EObject semanticObject,
			CrossReference crossref, EObject target, IScope scope, Acceptor errors) {
		EReference eReference = GrammarUtil.getReference(crossref);
		final String ruleName = linkingHelper.getRuleNameFrom(crossref);
		String unconvertedString = null;
		if ("ID".equals(ruleName)) {
			if ((eReference == IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS)
			 || (eReference == IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE)
			 || (eReference == IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE)
			 || (eReference == IdiomsPackage.Literals.IDIOM__FOR_ECLASS)
			 || (eReference == IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE)
			 || (eReference == IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS)
			 || (eReference == IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE)) {
				unconvertedString = ((ENamedElement)target).getName();
			}
			else if (eReference == IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION) {
				unconvertedString = ((LocatorDeclaration)target).getName();
			}
			else if (eReference == IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION) {
				unconvertedString = ((SegmentDeclaration)target).getName();
			}
		}
		else if ("STRING".equals(ruleName)) {
			if (eReference == IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE) {
				unconvertedString = EcoreUtil.getURI(target).toString();
			}
			if (eReference == IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL) {
				unconvertedString = target.eResource().getURI().toString();
			}
		}
		if (unconvertedString != null) {
			return valueConverter.toString(unconvertedString, ruleName);
		}
		throw new UnsupportedOperationException(ruleName + " for " +
				eReference.getEContainingClass().getEPackage().getName() + "::" +
				eReference.getEContainingClass().getName() + "." + eReference.getName());
	//	return super.getCrossReferenceNameFromScope(semanticObject, crossref, target, scope, errors);
	}
}
