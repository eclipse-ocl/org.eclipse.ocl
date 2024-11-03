/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *  E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;

/**
 * Tests for the OCLinEcore tutorial using LPG or Pivot delegate URIs on LPG or Pivot evaluator.
 *
 * WARNING. These tests fail as plugin tests if an OCLinEcore tutorial project is open.
 */
public class ValidationTutorialExamples extends PivotTestCaseWithAutoTearDown
{
	public static final @NonNull String VIOLATED_CONSTRAINT_TEMPLATE = "The ''{0}'' constraint is violated on ''{1}''";	// _UI_GenericConstraint_diagnostic = The ''{0}'' constraint is violated on ''{1}''
	public static final @NonNull String VIOLATED_INVARIANT_TEMPLATE = "The ''{0}'' invariant is violated on ''{1}''";

	protected @NonNull ResourceSet createExternalResourceSet() {
		//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
		//	AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
		//	ASResourceImpl.RESOLVE_PROXY.setState(true);
		//	ASResourceImpl.SET_PROXY.setState(true);
		ResourceSet resourceSet = new ResourceSetImpl();				// The Sample Ecore Model Editor ResourceSet
		GlobalEnvironmentFactory.disposeInstance();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCLDelegateDomain.lazyInitializeGlobalValidationRegistry(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL, true);
		OCLDelegateDomain.lazyInitializeLocalValidationRegistry(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL, true, null);
		getProjectMap().initializeResourceSet(resourceSet);
		return resourceSet;
	}

	@Override
	protected void setUp() throws Exception {
		TestUtil.doEssentialOCLSetup();
		TestUtil.doCompleteOCLSetup();
		super.setUp();
		resetRegistries();
		OCLstdlib.install();
	}

	public void testValidationTutorial() throws Throwable {
		@NonNull URI ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/EcoreTestFile.ecore", true);
		@NonNull URI xmiURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/XMITestFile.xmi", true);
		@NonNull URI ocl4ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraEcoreValidation.ocl", true);
		@NonNull URI ocl4xmiURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraXMIValidation.ocl", true);
		ResourceSet resourceSet = createExternalResourceSet();
		OCL ocl0 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
		//
		//	Load the XMI (and its ecore) - emulate Open XMITestFile.xmi with Sample Ecore Model Editor
		//
		Resource xmiResource = resourceSet.getResource(xmiURI, true);
		assert xmiResource != null;
		Resource ecoreResource = resourceSet.getResource(ecoreURI, false);			// Already loaded
		assert ecoreResource != null;
		//
		EObject xmiObject = xmiResource.getContents().get(0);
		EObject ecoreObject = ((EClass)((EPackage)ecoreResource.getContents().get(0)).getEClassifier("BadClass")).getEStructuralFeature("uncachedDerived");
		String xmiObjectLabel = LabelUtil.getLabel(xmiObject);			// Beware: uses settingDelegate and so the prevailing OCL
		String ecoreObjectLabel = LabelUtil.getLabel(ecoreObject);
	//	assertNoValidationErrors("XMI validation without extra OCL", xmiResource);
		assertValidationDiagnostics("XMI validation without extra OCL", xmiResource, getMessages(
			StringUtil.bind(VIOLATED_INVARIANT_TEMPLATE, "mustBeTrue", xmiObjectLabel)));
		assertNoValidationErrors("Ecore validation without extra OCL", ecoreResource);
		//
		//	Load the two Complete OCL documents - emulate OCL -> Load Document for the two *.ocls
		//
		CompleteOCLLoader helper = new TestCompleteOCLLoader(ocl0.getEnvironmentFactory());
		ASResource ocl4xmiResource = (ASResource)helper.loadResource(ocl4xmiURI);
		assert ocl4xmiResource != null;
		ASResource ocl4ecoreResource = (ASResource)helper.loadResource(ocl4ecoreURI);
		assert ocl4ecoreResource != null;
	//	String xmiObjectLabel = LabelUtil.getLabel(xmiObject);			// Beware: uses settingDelegate and so the prevailing OCL
	//	String ecoreObjectLabel = LabelUtil.getLabel(ecoreObject);
		helper.dispose();												// Does ocl0.dispose()
		//
		//	Validate the XMI - emulate live validation or manual validate on a worker thread inheriting OCL from main thread.
		//
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				assertValidationDiagnostics("XMI validation with extra OCL", xmiResource, getMessages(
					StringUtil.bind(VIOLATED_INVARIANT_TEMPLATE, "mustBeTrue", xmiObjectLabel),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "UncachedDerivedIsNull", xmiObjectLabel)));
				assertValidationDiagnostics("Ecore validation with extra OCL", ecoreResource, getMessages(
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsTransient", ecoreObjectLabel),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsUninitialized", ecoreObjectLabel),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsVolatile", ecoreObjectLabel)));
			}
		});

		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
//				GlobalEnvironmentFactory.disposeInstance();
//				OCL ocl2 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
				assertValidationDiagnostics("XMI validation with extra OCL", xmiResource, getMessages(
					StringUtil.bind(VIOLATED_INVARIANT_TEMPLATE, "mustBeTrue", xmiObjectLabel),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "UncachedDerivedIsNull", xmiObjectLabel)));

//				ocl2.dispose();
			}
		});
	}

	public void testValidationTutorial_EcoreTestFile() throws Throwable {
		@NonNull URI ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/EcoreTestFile.ecore", true);
		@NonNull URI ocl4ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraEcoreValidation.ocl", true);
		ResourceSet independentResourceSet = new ResourceSetImpl();				// The Sample Ecore Model Editor ResourceSet
		getProjectMap().initializeResourceSet(independentResourceSet);
	//	URI littleURI = getTestModelURI("models/ecore/LittleModel.ecore");
		Resource independentEcoreResource = independentResourceSet.getResource(ecoreURI, true);
		assert independentEcoreResource != null;
		EClass independentEcoreClass = ((EClass)((EPackage)independentEcoreResource.getContents().get(0)).getEClassifier("BadClass"));
	//	EObject independentEcoreObject = independentEcoreClass.getEStructuralFeature("uncachedDerived");
	//	assertNoValidationErrors("Independent Ecore validation without extra OCL", independentEcoreResource);
		independentEcoreClass.setName("M i n u t e");
		String badMinuteName = "The name 'M i n u t e' is not well formed";
	//	assertLazyValidationDiagnostics("Corrupted Independent Ecore validation without OCL support", independentEcoreResource, getMessages(badMinuteName));
		ThreadLocalExecutor.resetEnvironmentFactory();

		ResourceSet resourceSet = createExternalResourceSet();
		OCL ocl0 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
		//
		//	Load the ecore - emulate Open EcoreTestFile.ecore with Sample Ecore Model Editor
		//
		Resource ecoreResource = resourceSet.getResource(ecoreURI, true);
		assert ecoreResource != null;
		//
		EClass ecoreClass = ((EClass)((EPackage)ecoreResource.getContents().get(0)).getEClassifier("BadClass"));
		EStructuralFeature ecoreFeature = ecoreClass.getEStructuralFeature("uncachedDerived");
	//	assertNoValidationErrors("Ecore validation without extra OCL", ecoreResource);
		String ecoreObjectLabel = LabelUtil.getLabel(ecoreFeature);
		//
		//	Load the Complete OCL document - emulate OCL -> Load Document for the *.ocl
		//
		CompleteOCLLoader helper = new TestCompleteOCLLoader(ocl0.getEnvironmentFactory());
		ASResource ocl4ecoreResource = (ASResource)helper.loadResource(ocl4ecoreURI);
		assert ocl4ecoreResource != null;
		//	helper.dispose();												// Does ocl0.dispose()
		ocl0.dispose();
		//
		//	Verify that the Independent Ecore is not affected by the loaded OCL.
		//
	//	assertLazyValidationDiagnostics("Corrupted Independent Ecore validation with OCL support", independentEcoreResource, getMessages(badMinuteName));
		independentEcoreClass.setName("Minute");
	//	assertLazyValidationDiagnostics("Uncorrupted Independent Ecore validation with OCL support", independentEcoreResource, null);
		//
		//	Validate the ecore - emulate live validation or manual validate on a worker thread inheriting OCL from main thread.
		//
	/*	doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				String ecoreObjectLabel = LabelUtil.getLabel(ecoreFeature);
				assertLazyValidationDiagnostics("Ecore validation with extra OCL", ecoreResource, getMessages(
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsTransient", ecoreObjectLabel),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsUninitialized", ecoreObjectLabel),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsVolatile", ecoreObjectLabel)));
			}
		}); */
		//
		//	Revalidate the ecore after changing some errors.
		//
		ecoreClass.setName("M i n u t e");
		ecoreFeature.setTransient(true);
		ecoreFeature.setVolatile(true);
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				String ecoreObjectLabel = LabelUtil.getLabel(ecoreFeature);
				assertValidationDiagnostics("Ecore validation with extra OCL", ecoreResource, getMessages(
					badMinuteName,
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "DerivationIsUninitialized", ecoreObjectLabel)));
			}
		});
		helper.unloadDocument(ocl4ecoreURI);
		helper.dispose();												// Does ocl0.dispose()
	}

	public void testValidationTutorial_PapyrusTestFile() throws Throwable {
		UMLStandaloneSetup.init();
		@NonNull URI umlURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/PapyrusTestFile.uml", true);
		@NonNull URI ocl4umlURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraUMLValidation.ocl", true);
		String NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE = "Named element ''{0}'' is not distinguishable from all other members of namespace ''{1}''.";
		String MEMBERS_NOT_DISTINGUISHABLE_TEMPLATE = "Not all the members of namespace ''{0}'' are distinguishable within it.";
		ResourceSet independentResourceSet = new ResourceSetImpl();				// The Sample UML Model Editor ResourceSet
		getProjectMap().initializeResourceSet(independentResourceSet);
		Resource independentUMLResource = independentResourceSet.getResource(umlURI, true);
		assert independentUMLResource != null;
		org.eclipse.uml2.uml.Model independentUMLModel = (org.eclipse.uml2.uml.Model)independentUMLResource.getContents().get(0);
		org.eclipse.uml2.uml.Package independentUMLPackage = (org.eclipse.uml2.uml.Model)independentUMLResource.getContents().get(0);
		org.eclipse.uml2.uml.Class independentUML_UPPERCASE_Class = (org.eclipse.uml2.uml.Class)independentUMLPackage.getOwnedTypes().get(0);
		org.eclipse.uml2.uml.Class independentUML_lowercase_Class = (org.eclipse.uml2.uml.Class)independentUMLPackage.getOwnedTypes().get(1);
		assertNoValidationErrors("Independent UML validation without extra OCL", independentUMLResource);
		independentUML_UPPERCASE_Class.setName("lowercase");
		String independentUML_UPPERCASE_Label = LabelUtil.getLabel(independentUML_UPPERCASE_Class);
		String independentUML_lowercase_Label = LabelUtil.getLabel(independentUML_lowercase_Class);
		String independentUMLModelLabel = LabelUtil.getLabel(independentUMLModel);
		assertLazyValidationDiagnostics("Corrupted Independent UML validation without OCL support", independentUMLResource, getMessages(
				StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, independentUML_UPPERCASE_Label, independentUMLModelLabel),
				StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, independentUML_lowercase_Label, independentUMLModelLabel),
				StringUtil.bind(MEMBERS_NOT_DISTINGUISHABLE_TEMPLATE, independentUMLModelLabel)));

		ResourceSet resourceSet = createExternalResourceSet();
		OCL ocl0 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
		//
		//	Load the uml - emulate Open PapyrusTestFile.uml with Sample UML Model Editor
		//
		Resource umlResource = resourceSet.getResource(umlURI, true);
		assert umlResource != null;
		//
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Package umlPackage = (org.eclipse.uml2.uml.Package)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class uml_UPPERCASE_Class = (org.eclipse.uml2.uml.Class)umlPackage.getOwnedTypes().get(0);
		org.eclipse.uml2.uml.Class uml_lowercase_Class = (org.eclipse.uml2.uml.Class)umlPackage.getOwnedTypes().get(1);
		assertNoValidationErrors("UML validation without extra OCL", umlResource);
		//
		//	Load the Complete OCL document - emulate OCL -> Load Document for the *.ocl
		//
		CompleteOCLLoader helper = new TestCompleteOCLLoader(ocl0.getEnvironmentFactory());
		ASResource ocl4umlResource = (ASResource)helper.loadResource(ocl4umlURI);
		assert ocl4umlResource != null;
		//
		//	Verify that the Independent UML is not affected by the loaded OCL.
		//
		assertLazyValidationDiagnostics("Corrupted Independent UML validation with OCL support", independentUMLResource, getMessages(
			StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, independentUML_UPPERCASE_Label, independentUMLModelLabel),
			StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, independentUML_lowercase_Label, independentUMLModelLabel),
			StringUtil.bind(MEMBERS_NOT_DISTINGUISHABLE_TEMPLATE, independentUMLModelLabel)));
		independentUML_UPPERCASE_Class.setName("UPPERCASE");
		assertLazyValidationDiagnostics("Uncorrupted Independent Ecore validation with OCL support", independentUMLResource, null);
		//
		//	Validate the UML - emulate live validation or manual validate on a worker thread inheriting OCL from main thread.
		//
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				String uml_lowercase_ClassLabel = LabelUtil.getLabel(uml_lowercase_Class);
				assertLazyValidationDiagnostics("UML validation with extra OCL", umlResource, getMessages(
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "CamelCaseName", uml_lowercase_ClassLabel)));
			}
		});
		//
		//	Revalidate the UML after removing errors.
		//
		uml_lowercase_Class.setName("LowerCase");
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				assertValidationDiagnostics("UML validation with extra OCL", umlResource, getMessages());
			}
		});
		//
		//	Revalidate the UML with a UML 'error'.
		//
		uml_UPPERCASE_Class.setName("LowerCase");
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				String uml_UPPERCASE_Label = LabelUtil.getLabel(uml_UPPERCASE_Class);
				String uml_lowercase_Label = LabelUtil.getLabel(uml_lowercase_Class);
				String umlModelLabel = LabelUtil.getLabel(umlModel);
				assertValidationDiagnostics("UML validation with extra OCL", umlResource, getMessages(
					StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, uml_UPPERCASE_Label, umlModelLabel),
					StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, uml_lowercase_Label, umlModelLabel),
					StringUtil.bind(MEMBERS_NOT_DISTINGUISHABLE_TEMPLATE, umlModelLabel)));
			}
		});
		//
		//	Revalidate the UML with UML and OCL errors.
		//
		uml_UPPERCASE_Class.setName("uppercase");
		uml_lowercase_Class.setName("uppercase");
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				String uml_UPPERCASE_Label = LabelUtil.getLabel(uml_UPPERCASE_Class);
				String uml_lowercase_Label = LabelUtil.getLabel(uml_lowercase_Class);
				String umlModelLabel = LabelUtil.getLabel(umlModel);
				assertValidationDiagnostics("UML validation with extra OCL", umlResource, getMessages(
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "CamelCaseName", uml_UPPERCASE_Label),
					StringUtil.bind(VIOLATED_CONSTRAINT_TEMPLATE, "CamelCaseName", uml_lowercase_Label),
					StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, uml_UPPERCASE_Label, umlModelLabel),
					StringUtil.bind(NAMED_ELEMENT_NOT_DISTINGUISHABLE_TEMPLATE, uml_lowercase_Label, umlModelLabel),
					StringUtil.bind(MEMBERS_NOT_DISTINGUISHABLE_TEMPLATE, umlModelLabel)));
			}
		});
		helper.unloadDocument(ocl4umlURI);
		helper.dispose();												// Does ocl0.dispose()
	}
}
