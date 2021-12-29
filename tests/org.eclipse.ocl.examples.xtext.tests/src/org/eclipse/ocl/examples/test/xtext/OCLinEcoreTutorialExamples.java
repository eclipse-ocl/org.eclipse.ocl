/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *  E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.util.PivotValidator;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * Tests for the OCLinEcore tutorial using LPG or Pivot delegate URIs on LPG or Pivot evaluator.
 *
 * WARNING. These tests fail as plugin tests if an OCLinEcore tutorial project is open.
 */
public class OCLinEcoreTutorialExamples extends PivotTestCaseWithAutoTearDown
{
	public void testOCLinEcoreTutorialUsingLPGForLPG() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
		org.eclipse.ocl.ecore.OCL.initialize(resourceSet);
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		doTestOCLinEcoreTutorialUsingLPG(getTestModelURI("models/documentation/OCLinEcoreTutorialForLPG.xmi"), true);
		GlobalEnvironmentFactory.disposeInstance();
	}
	public void testOCLinEcoreTutorialUsingLPGForPivot() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
		//	GlobalEnvironmentFactory.getInstance().setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		PivotUtilInternal.getEnvironmentFactory(null).setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		doTestOCLinEcoreTutorialUsingLPG(getTestModelURI("models/documentation/OCLinEcoreTutorialForPivot.xmi"), true);
		GlobalEnvironmentFactory.disposeInstance();
	}
	public void testOCLinEcoreTutorialUsingPivotForLPG() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		getProjectMap().initializeResourceSet(resourceSet);
		doTestOCLinEcoreTutorialUsingPivot(getTestModelURI("models/documentation/OCLinEcoreTutorialForLPG.xmi"));
		GlobalEnvironmentFactory.disposeInstance();
	}
	public void testOCLinEcoreTutorialUsingPivotForPivot() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
//		getProjectMap().initializeResourceSet(resourceSet);
		doTestOCLinEcoreTutorialUsingPivot(getTestModelURI("models/documentation/OCLinEcoreTutorialForPivot.xmi"));
		GlobalEnvironmentFactory.disposeInstance();
	}
	public void testOCLinEcoreTutorialUsingLPGForDefault() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
	//	GlobalEnvironmentFactory.getInstance().setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		PivotUtilInternal.getEnvironmentFactory(null).setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		doTestOCLinEcoreTutorialUsingLPG(getTestModelURI("models/documentation/OCLinEcoreTutorial.xmi"), true);
		GlobalEnvironmentFactory.disposeInstance();
	}
	public void testOCLinEcoreTutorialUsingPivotForDefault() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		getProjectMap().initializeResourceSet(resourceSet);
		doTestOCLinEcoreTutorialUsingPivot(getTestModelURI("models/documentation/OCLinEcoreTutorial.xmi"));
		GlobalEnvironmentFactory.disposeInstance();
	}

	protected void doTestOCLinEcoreTutorialUsingLPG(@NonNull URI testModelURI, boolean isLPG) throws Exception {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new EcoreResourceFactoryImpl());
		//		resourceSet.getURIConverter().getURIMap().put(URI.createURI("http://www.eclipse.org/ocl/1.1.0/oclstdlib.ecore"), URI.createPlatformPluginURI("/org.eclipse.ocl.ecore/model/oclstdlib.ecore", true));
		URIConverter.URI_MAP.put(URI.createURI(EcoreEnvironment.OCL_STANDARD_LIBRARY_NS_URI), URI.createPlatformPluginURI("/org.eclipse.ocl.ecore/model/oclstdlib.ecore", true));
		Resource xmiResource = resourceSet.getResource(testModelURI, true);
		EObject xmiLibrary = xmiResource.getContents().get(0);
		EClass ecoreLibrary = xmiLibrary.eClass();
		if (isLPG) {
			Resource ecoreLibraryResource = ecoreLibrary.eResource();
			assert ecoreLibraryResource != null;
			removeSafeNavigationOperatorsForLPG(ecoreLibraryResource);
		}
		EStructuralFeature ecoreBooks = ecoreLibrary.getEStructuralFeature("books");
		EClass ecoreBook = (EClass) ecoreBooks.getEType();
		EStructuralFeature bookName = ecoreBook.getEStructuralFeature("name");
		EStructuralFeature bookCopies = ecoreBook.getEStructuralFeature("copies");
		EStructuralFeature bookLoans = ecoreBook.getEStructuralFeature("loans");
		EOperation bookIsAvailable = NameUtil.getENamedElement(ecoreBook.getEOperations(), "isAvailable");
		@SuppressWarnings("unchecked")
		List<EObject> xmiBooks = (List<EObject>) xmiLibrary.eGet(ecoreBooks);
		EObject b2Book = null;
		for (EObject xmiBook : xmiBooks) {
			if (xmiBook.eGet(bookName).equals("b2")) {
				b2Book = xmiBook;
			}
		}
		if (b2Book == null) {
			fail();
			return;
		}
		org.eclipse.ocl.ecore.EcoreEnvironmentFactory environmentFactory = new org.eclipse.ocl.ecore.EcoreEnvironmentFactory(resourceSet.getPackageRegistry());
		org.eclipse.ocl.ecore.OCL ocl = org.eclipse.ocl.ecore.OCL.newInstance(environmentFactory);
		org.eclipse.ocl.ecore.OCL.Helper helper = ocl.createOCLHelper();

		Object b2Copies = b2Book.eGet(bookCopies);			// Static eGet
		assertEquals(2, ((Number)b2Copies).intValue());

		Object b2Loans = b2Book.eGet(bookLoans);			// Dynamic eGet
		//		SettingDelegate settingDelegate = ((EStructuralFeature.Internal) bookLoans).getSettingDelegate();
		//		assert settingDelegate instanceof org.eclipse.ocl.pivot.delegate.OCLSettingDelegate;
		assertEquals(3, ((List<?>)b2Loans).size());

		Object b2IsAvailable = b2Book.eInvoke(bookIsAvailable, new BasicEList<EObject>());			// Dynamic eInvoke
		//		SettingDelegate settingDelegate = ((EStructuralFeature.Internal) bookLoans).getSettingDelegate();
		//		assert settingDelegate instanceof org.eclipse.ocl.pivot.delegate.OCLSettingDelegate;
		assertEquals(false, ((Boolean)b2IsAvailable).booleanValue());

		helper.setContext(b2Book.eClass());
		org.eclipse.ocl.ecore.OCLExpression query = helper.createQuery("isAvailable()");
		org.eclipse.ocl.ecore.OCL.Query queryEval = ocl.createQuery(query);
		Object b2Available = queryEval.evaluate(b2Book);
		assertFalse((Boolean)b2Available);

		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic diagnostics = Diagnostician.INSTANCE.validate(xmiLibrary, validationContext);
		assertEquals(3, diagnostics.getChildren().size());

		b2Book.eSet(bookCopies, BigInteger.valueOf(4));
		b2Available = queryEval.evaluate(b2Book);
		assertTrue((Boolean)b2Available);

		diagnostics = Diagnostician.INSTANCE.validate(xmiLibrary, validationContext);
		assertEquals(2, diagnostics.getChildren().size());

		b2Book.eSet(bookCopies, BigInteger.valueOf(3));
		b2Available = queryEval.evaluate(b2Book);
		assertFalse((Boolean)b2Available);

		List<?> b2loans = (List<?>)b2Book.eGet(bookLoans);
		assertEquals(3, b2loans.size());
		assertTrue(b2loans.get(1) instanceof EObject);

		ocl.dispose();
	}

	protected void doTestOCLinEcoreTutorialUsingPivot(@NonNull URI testModelURI) throws Exception {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new EcoreResourceFactoryImpl());
		Resource xmiResource = resourceSet.getResource(testModelURI, true);
		EObject xmiLibrary = xmiResource.getContents().get(0);
		EClass ecoreLibrary = xmiLibrary.eClass();
		EStructuralFeature ecoreBooks = ecoreLibrary.getEStructuralFeature("books");
		EClass ecoreBook = (EClass) ecoreBooks.getEType();
		EStructuralFeature bookName = ecoreBook.getEStructuralFeature("name");
		EStructuralFeature bookCopies = ecoreBook.getEStructuralFeature("copies");
		EStructuralFeature bookLoans = ecoreBook.getEStructuralFeature("loans");
		EOperation bookIsAvailable = NameUtil.getENamedElement(ecoreBook.getEOperations(), "isAvailable");
		@SuppressWarnings("unchecked")
		List<EObject> xmiBooks = (List<EObject>) xmiLibrary.eGet(ecoreBooks);
		EObject b2Book = null;
		for (EObject xmiBook : xmiBooks) {
			if (xmiBook.eGet(bookName).equals("b2")) {
				b2Book = xmiBook;
			}
		}
		if (b2Book == null) {
			fail();
			return;
		}
		OCL ocl = OCL.newInstance(resourceSet.getPackageRegistry());
		try {
			EClass b2EClass = b2Book.eClass();
			assert b2EClass != null;
			Resource b2EClassResource = b2EClass.eResource();
			assert b2EClassResource != null;
			Ecore2AS ecore2as = Ecore2AS.getAdapter(b2EClassResource, (EnvironmentFactoryInternal) ocl.getEnvironmentFactory());
			org.eclipse.ocl.pivot.Class bookType = ecore2as.getCreated(org.eclipse.ocl.pivot.Class.class, b2EClass);

			Object b2Copies = b2Book.eGet(bookCopies);			// Static eGet
			assertEquals(2, ((Number)b2Copies).intValue());

			Object b2Loans = b2Book.eGet(bookLoans);			// Dynamic eGet
			//			SettingDelegate settingDelegate = ((EStructuralFeature.Internal) bookLoans).getSettingDelegate();
			//			assert settingDelegate instanceof org.eclipse.ocl.pivot.delegate.OCLSettingDelegate;
			assertEquals(3, ((List<?>)b2Loans).size());

			Object b2IsAvailable = b2Book.eInvoke(bookIsAvailable, new BasicEList<EObject>());			// Dynamic eInvoke
			//			SettingDelegate settingDelegate = ((EStructuralFeature.Internal) bookLoans).getSettingDelegate();
			//			assert settingDelegate instanceof org.eclipse.ocl.pivot.delegate.OCLSettingDelegate;
			assertEquals(false, ((Boolean)b2IsAvailable).booleanValue());

			ExpressionInOCL query = ocl.createQuery(bookType, "isAvailable()");
			org.eclipse.ocl.pivot.utilities.Query queryEval = ocl.createQuery(query);
			Object b2Available = queryEval.evaluateEcore(b2Book);
			assertFalse(ValueUtil.asBoolean(b2Available));

			Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
			Diagnostic diagnostics = Diagnostician.INSTANCE.validate(xmiLibrary, validationContext);
			assertEquals(3, diagnostics.getChildren().size());

			//		    queryEval.invalidateCaches();
			b2Book.eSet(bookCopies, BigInteger.valueOf(4));
			b2Available = queryEval.evaluateEcore(b2Book);
			assertFalse(ValueUtil.asBoolean(b2Available));			// uses previously cached value
			queryEval = ocl.createQuery(query);						// new query for changed model
			b2Available = queryEval.evaluateEcore(b2Book);
			assertTrue(ValueUtil.asBoolean(b2Available));

			diagnostics = Diagnostician.INSTANCE.validate(xmiLibrary, validationContext);
			assertEquals(2, diagnostics.getChildren().size());

			b2Book.eSet(bookCopies, BigInteger.valueOf(3));
			queryEval = ocl.createQuery(query);						// new query for changed model
			b2Available = queryEval.evaluateEcore(b2Book);
			assertFalse(ValueUtil.asBoolean(b2Available));

			List<?> b2loans = (List<?>)b2Book.eGet(bookLoans);
			assertEquals(3, b2loans.size());
			assertTrue(b2loans.get(1) instanceof EObject);
		} finally {
			ocl.dispose();
		}
	}

	protected void removeSafeNavigationOperatorsForLPG(@NonNull Resource eResource) {
		for (TreeIterator<EObject> tit = eResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof EAnnotation) {
				EAnnotation eAnnotation = (EAnnotation)eObject;
				if (OCLCommon.isDelegateURI(eAnnotation.getSource())) {
					EMap<String, String> details = eAnnotation.getDetails();
					for (String key : details.keySet()) {
						String value = details.get(key);
						String unsafeValue = value.replace("?.", ".").replace("?->", "->");
						if (!ClassUtil.safeEquals(unsafeValue, value)) {
							details.put(key, unsafeValue);
						}
					}
				}
				tit.prune();
			}
		}
	}

	private ResourceSet resourceSet;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		resetRegistries();
		OCLstdlib.install();
		TestUtil.doEssentialOCLSetup();
		resourceSet = new ResourceSetImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		if (resourceSet != null) {
			unloadResourceSet(resourceSet);
		}
		resourceSet = null;
		EValidator.Registry.INSTANCE.put(PivotPackage.eINSTANCE, PivotValidator.INSTANCE);
		super.tearDown();
	}
}
