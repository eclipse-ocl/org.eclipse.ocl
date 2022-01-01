/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
//import org.eclipse.ocl.pivot.internal.validation.BasicEAnnotationValidator2;
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.uml.internal.validation.UMLOCLEValidator;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Tests that OCL for model validation works.
 */
public abstract class AbstractValidateTests extends PivotTestCaseWithAutoTearDown
{
	public static final @NonNull String VIOLATED_TEMPLATE = "The ''{0}'' constraint is violated on ''{1}''";	// _UI_GenericConstraint_diagnostic = The ''{0}'' constraint is violated on ''{1}''

	public static @NonNull List<Diagnostic> assertUMLOCLValidationDiagnostics(@Nullable OCL ocl, @NonNull String prefix, @NonNull Resource resource, @NonNull String... messages) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		if (ocl != null) {
			PivotDiagnostician.setOCL(validationContext, ocl);
		}
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		for (EObject eObject : resource.getContents()) {
			EValidatorRegistryImpl registry = new EValidatorRegistryImpl();
			registry.put(UMLPackage.eINSTANCE, UMLOCLEValidator.INSTANCE);
			Diagnostician dignostician = new Diagnostician(registry);
			Diagnostic diagnostic = dignostician.validate(eObject, validationContext);
			diagnostics.addAll(diagnostic.getChildren());
		}
		return assertDiagnostics(prefix, resource, diagnostics, messages);
	}

	public static void checkValidationDiagnostics(@NonNull EObject testInstance, int severity, @NonNull String... expectedMessage) {
		Bag<@NonNull String> expectedMessages = new BagImpl<>();
		for (@NonNull String message : expectedMessage) {
			expectedMessages.add(message);
		}
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic diagnostics = PivotDiagnostician.BasicDiagnosticWithRemove.validate(testInstance, validationContext);
		Bag<String> actualMessages = new BagImpl<>();
		for (Diagnostic diagnostic : diagnostics.getChildren()) {
			//			assertEquals(severity, diagnostic.getSeverity());
			actualMessages.add(diagnostic.getMessage());
		}
		String s = formatMessageDifferences(expectedMessages, actualMessages);
		if (s != null) {
			fail("Inconsistent validation: (expected/actual) message" + s);
		}
	}

	protected @NonNull OCL createOCL() {
		OCL ocl = OCL.newInstance(getProjectMap());
		//		ResourceSet resourceSet = ocl.getResourceSet();
		//		ProjectMap.initializeURIResourceMap(resourceSet);
		//		Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
		//    	if (EMFPlugin.IS_ECLIPSE_RUNNING) {
		//    		uriMap.putAll(EcorePlugin.computePlatformURIMap());
		//    	}
		return ocl;
	}

	public @NonNull Resource doLoadOCLinEcore(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		String lastSegment = inputURI.trimFileExtension().appendFileExtension("ecore").lastSegment();
		assert lastSegment != null;
		URI ecoreURI = getTestFileURI(lastSegment);
		return doLoadOCLinEcore(ocl, inputURI, ecoreURI);
	}

	public @NonNull Resource doLoadOCLinEcore(@NonNull OCL ocl, @NonNull URI inputURI, @NonNull URI ecoreURI) throws IOException {
		CSResource xtextResource = ocl.getCSResource(inputURI);
		assertNoResourceErrors("Load failed", xtextResource);
		ASResource asResource = xtextResource.getASResource();
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		assertNoValidationErrors("Pivot validation errors", asResource.getModel());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		Resource ecoreResource = as2ecore(environmentFactory, asResource, ecoreURI, NO_MESSAGES);
		return ecoreResource;
	}

	public Resource doLoadUML(@NonNull OCL ocl, @NonNull URI umlURI) throws IOException {
		return ocl.getResourceSet().getResource(umlURI, true);
	}

	public @NonNull List<Diagnostic> doValidateOCLinEcore(OCL ocl, String stem, @NonNull String @NonNull [] validationDiagnostics) throws IOException {
		String inputName = stem + ".oclinecore";
		URI inputURI = getTestFileURI(inputName);
		BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().createResource(inputURI);
		assert xtextResource != null;
		ocl.getEnvironmentFactory().adapt(xtextResource);
		InputStream inputStream = ocl.getResourceSet().getURIConverter().createInputStream(inputURI);
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Load failed", xtextResource);
		ASResource asResource = ocl.cs2as(xtextResource);
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		return assertValidationDiagnostics("Pivot validation errors", asResource, validationDiagnostics);
	}

	protected @NonNull EObject eCreate(@NonNull EPackage ePackage, @NonNull String className) {
		EClass eClass = (EClass) ePackage.getEClassifier(className);
		EFactory eFactoryInstance = ePackage.getEFactoryInstance();
		return eFactoryInstance.create(eClass);
	}

/*
 * eInvoke would bypass CompleteClasses. Use ocl.assertQuery...
 *
  	protected Object eInvoke(@NonNull EObject eObject, @NonNull String eOperationName, Object... values) throws InvocationTargetException {
 */

	protected void eSet(@NonNull EObject eObject, @NonNull String eFeatureName, Object value) {
		EClass eClass = eObject.eClass();
		EStructuralFeature eFeature = eClass.getEStructuralFeature(eFeatureName);
		assert eFeature != null;
		eObject.eSet(eFeature, value);
	}


	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestCaseAppender.INSTANCE.install();
		TestUtil.doCompleteOCLSetup();
		TestUtil.doOCLinEcoreSetup();
		TestUtil.doOCLstdlibSetup();
		//		OCLstdlib.install();
		//        OCLDelegateDomain.initialize(null);
	}

	@Override
	protected void tearDown() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
		super.tearDown();
	}
}
