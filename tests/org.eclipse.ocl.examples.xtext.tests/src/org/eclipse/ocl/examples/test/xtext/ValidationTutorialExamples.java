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
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.PivotValidator;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;

import junit.framework.TestCase;

/**
 * Tests for the OCLinEcore tutorial using LPG or Pivot delegate URIs on LPG or Pivot evaluator.
 *
 * WARNING. These tests fail as plugin tests if an OCLinEcore tutorial project is open.
 */
public class ValidationTutorialExamples extends PivotTestCaseWithAutoTearDown
{
	public static final @NonNull String VIOLATED_TEMPLATE = "The ''{0}'' constraint is violated on ''{1}''";	// _UI_GenericConstraint_diagnostic = The ''{0}'' constraint is violated on ''{1}''

	private ResourceSet resourceSet;

	@Override
	protected void setUp() throws Exception {
		TestUtil.doEssentialOCLSetup();
		TestUtil.doCompleteOCLSetup();
		super.setUp();
		resetRegistries();
		OCLstdlib.install();
		resourceSet = new ResourceSetImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		if (resourceSet != null) {
			unloadResourceSet(resourceSet);
		}
		resourceSet = null;
		assert EValidator.Registry.INSTANCE.get(PivotPackage.eINSTANCE) == PivotValidator.INSTANCE;	// Verify global integrity - Bug 582494
		super.tearDown();
	}

	public void testValidationTutorial() throws Throwable {
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
	//	ASResourceImpl.SET_PROXY.setState(true);
		GlobalEnvironmentFactory.disposeInstance();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCLDelegateDomain.initialize2(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
		getProjectMap().initializeResourceSet(resourceSet);

		@NonNull URI ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/EcoreTestFile.ecore", true);
		@NonNull URI xmiURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/XMITestFile.xmi", true);
		@NonNull URI ocl4ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraEcoreValidation.ocl", true);
		@NonNull URI ocl4xmiURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraXMIValidation.ocl", true);
	//	doLoad(ocl, inputURI, "RoyalAndLoyal", "ecore");
		Resource ecoreResource = resourceSet.getResource(ecoreURI, true);
		assert ecoreResource != null;
		Resource xmiResource = resourceSet.getResource(xmiURI, true);
		assert xmiResource != null;
		//
		EObject xmiObject = xmiResource.getContents().get(0);
		EObject ecoreObject = ((EClass)((EPackage)ecoreResource.getContents().get(0)).getEClassifier("BadClass")).getEStructuralFeature("uncachedDerived");
	//	assertNoValidationErrors("XMI validation without extra OCL", xmiResource);
	//	assertNoValidationErrors("Ecore validation without extra OCL", ecoreResource);

		OCL ocl0 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
		CompleteOCLLoader helper = new CompleteOCLLoader(ocl0.getEnvironmentFactory()) {
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				TestCase.fail(primaryMessage + "\n\t" + detailMessage);
				return false;
			}
		};
		ASResource ocl4xmiResource = (ASResource)helper.loadResource(ocl4xmiURI);
		assert ocl4xmiResource != null;
		ASResource ocl4ecoreResource = (ASResource)helper.loadResource(ocl4ecoreURI);
		assert ocl4ecoreResource != null;
		helper.dispose();
		String xmiObjectLabel = LabelUtil.getLabel(xmiObject);			// Beware: uses settingDelegate and so the prevailing OCL
		String ecoreObjectLabel = LabelUtil.getLabel(ecoreObject);

		ocl0.dispose();

		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
//				GlobalEnvironmentFactory.disposeInstance();
				OCL ocl1 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
				EValidator eValidator = DelegateInstaller.init(ocl1.getEnvironmentFactory());		// XXX move to EF ctor
				//
				assertValidationDiagnostics("XMI validation with extra OCL", xmiResource, getMessages(
					StringUtil.bind(VIOLATED_TEMPLATE, "UncachedDerivedIsNull", xmiObjectLabel)));
				assertValidationDiagnostics("Ecore validation with extra OCL", ecoreResource, getMessages(
					StringUtil.bind(VIOLATED_TEMPLATE, "DerivationIsTransient", ecoreObjectLabel),
					StringUtil.bind(VIOLATED_TEMPLATE, "DerivationIsUninitialized", ecoreObjectLabel),
					StringUtil.bind(VIOLATED_TEMPLATE, "DerivationIsVolatile", ecoreObjectLabel)));

				ocl1.dispose();
			}
		});

		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
//				GlobalEnvironmentFactory.disposeInstance();
				OCL ocl2 = new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), resourceSet);
				assertValidationDiagnostics("XMI validation with extra OCL", xmiResource, getMessages(
					StringUtil.bind(VIOLATED_TEMPLATE, "UncachedDerivedIsNull", xmiObjectLabel)));

				ocl2.dispose();
			}
		});
	}
}
