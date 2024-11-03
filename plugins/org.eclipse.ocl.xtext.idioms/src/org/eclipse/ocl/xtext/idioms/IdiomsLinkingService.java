/**
 * Copyright (c) 2020, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.xtext.base.utilities.ClasspathURIHandler;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;

public class IdiomsLinkingService extends DefaultLinkingService
{
	@Override
	public List<EObject> getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		String text = getCrossRefNodeAsString(node);
		//
		//	Resolve imported features by importing.
		//
		if (ref == IdiomsPackage.Literals.EPACKAGE_DECLARATION__EPACKAGE) {
			Resource resource = context.eResource();
			ResourceSet resourceSet = resource.getResourceSet();
			URI baseURI = resource.getURI();
			URI userURI = URI.createURI(text, true);
			URI resolvedURI = userURI.resolve(baseURI);
			if (!resolvedURI.hasFragment()) {
				resolvedURI = resolvedURI.appendFragment("/");
			}
			EObject eObject = resourceSet.getEObject(resolvedURI, true);
			return Collections.singletonList(eObject);
		}
		else if (ref == IdiomsPackage.Literals.GRAMMAR_DECLARATION__GRAMMAR) {
			Resource idiomsResource = context.eResource();
			ResourceSet resourceSet = idiomsResource.getResourceSet();
			ClasspathURIHandler.init(resourceSet);		// Ensure that the missing classpath: support is worked around - Xtext Bug 446073
			URI baseURI = idiomsResource.getURI();
			URI userURI = URI.createURI(text, true);
			URI resolvedURI = userURI.resolve(baseURI);
			Resource grammarResource = resourceSet.getResource(resolvedURI, true);
			return Collections.singletonList(grammarResource.getContents().get(0));
		}
		else if (ref == IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL) {
			Resource resource = context.eResource();
			ResourceSet resourceSet = resource.getResourceSet();
			URI baseURI = resource.getURI();
			URI userURI = URI.createURI(text, true);
			URI resolvedURI = userURI.resolve(baseURI);
			Resource importedResource = resourceSet.getResource(resolvedURI, true);
			return Collections.singletonList(importedResource.getContents().get(0));
		}
		//
		//	Resolve qualified features wrt a non-null qualifying feature.
		//
		else if (ref == IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS) {
			AssignmentLocator assignmentLocator = (AssignmentLocator)context;
			EPackage ePackage = assignmentLocator.getEPackage();
			if (ePackage != null) {
				EClassifier eClassifier = ePackage.getEClassifier(text);
				if (eClassifier != null) {
					return Collections.singletonList(eClassifier);
				}
				else {
					return Collections.emptyList();
				}
			}
		}
		else if (ref == IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE) {
			AssignmentLocator assignmentLocator = (AssignmentLocator)context;
			EClass eClass = assignmentLocator.getEClass();
			if (eClass != null) {
				EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(text);
				if (eStructuralFeature != null) {
					return Collections.singletonList(eStructuralFeature);
				}
				else {
					return Collections.emptyList();
				}
			}
		}
		else if (ref == IdiomsPackage.Literals.IDIOM__FOR_ECLASS) {
			Idiom idiom = (Idiom)context;
			EPackage ePackage = idiom.getForEPackage();
			if (ePackage != null) {
				EClassifier eClassifier = ePackage.getEClassifier(text);
				if (eClassifier != null) {
					return Collections.singletonList(eClassifier);
				}
				else {
					return Collections.emptyList();
				}
			}
		}
		else if (ref == IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION) {
			ReferredLocator locatorRef = (ReferredLocator)context;
			IdiomsModel idiomsModel = locatorRef.getIdiomsModel();
			if (idiomsModel == null) {
				idiomsModel = (IdiomsModel)EcoreUtil.getRootContainer(locatorRef);
			}
			LocatorDeclaration locator = idiomsModel.getOwnedLocator(text);
			if (locator != null) {
				return Collections.singletonList(locator);
			}
			else {
				return Collections.emptyList();
			}
		}
		else if (ref == IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS) {
			ReturnsLocator returnsLocator = (ReturnsLocator)context;
			EPackage ePackage = returnsLocator.getEPackage();
			if (ePackage != null) {
				EClassifier eClassifier = ePackage.getEClassifier(text);
				if (eClassifier != null) {
					return Collections.singletonList(eClassifier);
				}
				else {
					return Collections.emptyList();
				}
			}
		}
		else if (ref == IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION) {
			ReferredSegment segmentRef = (ReferredSegment)context;
			IdiomsModel idiomsModel = segmentRef.getIdiomsModel();
			if (idiomsModel == null) {
				idiomsModel = (IdiomsModel)EcoreUtil.getRootContainer(segmentRef);
			}
			SegmentDeclaration segment = idiomsModel.getOwnedSegment(text);
			if (segment != null) {
				return Collections.singletonList(segment);
			}
			else {
				return Collections.emptyList();
			}
		}
		else if (ref == IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_GRAMMAR) {
			IdiomsModel idiomsModel = (IdiomsModel)EcoreUtil.getRootContainer(context);
			for (GrammarDeclaration grammar : idiomsModel.getOwnedGrammarDeclarations()) {
				if (text.equals(grammar.getAs())) {
					return Collections.singletonList(grammar);
				}
			}
			return Collections.emptyList();
		}
		else if (ref == IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_RULE) {
			RuleLocator ruleLocator = (RuleLocator)context;
			GrammarDeclaration referredGrammar = ruleLocator.getReferredGrammar();
			for (AbstractRule rule : referredGrammar.getGrammar().getRules()) {
				if (text.equals(rule.getName())) {
					return Collections.singletonList(rule);
				}
			}
			return Collections.emptyList();
		}
		return super.getLinkedObjects(context, ref, node);
	}

/*	private void initName2Rule(@NonNull ResourceSet resourceSet) {
		Map<@NonNull String, @NonNull AbstractRule> name2rule2 = name2rule;
		assert name2rule2 != null;
		assert name2rule2.isEmpty();
		for (Resource resource : resourceSet.getResources()) {
			if (resource instanceof GrammarResource) {
				for (EObject root : resource.getContents()) {
					if (root instanceof Grammar) {
						for (AbstractRule rule : ((Grammar)root).getRules()) {
							name2rule2.put(rule.getName(), rule);
						}
					}
				}

			}
		}
	} */

	@Override
	protected IScope getScope(EObject context, EReference reference) {
		IScopeProvider scopeProvider = getScopeProvider();
		if (scopeProvider == null)
			throw new IllegalStateException("scopeProvider must not be null.");
		//		try {
		//			registerImportedNamesAdapter(context);
		return scopeProvider.getScope(context, reference);
		//		} finally {
		//			unRegisterImportedNamesAdapter();
		//		}
	}
}
