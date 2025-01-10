/*******************************************************************************
 * Copyright (c) 2014, 2023 Willink Transformations and others.
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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestCaseLogger;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.evaluation.AbstractModelManager;
import org.eclipse.ocl.pivot.internal.delegate.InvocationBehavior;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.SettingBehavior;
import org.eclipse.ocl.pivot.internal.delegate.ValidationBehavior;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractExecutor;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorManager;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.validation.EcoreOCLEValidator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.validation.ValidationContext;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;
import org.eclipse.ocl.xtext.base.utilities.CSI2ASMapping;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader.CompleteOCLLoaderWithLog;
import org.eclipse.ocl.xtext.completeocl.validation.CompleteOCLEObjectValidator;
import org.eclipse.ocl.xtext.oclinecore.validation.OCLinEcoreEObjectValidator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.junit.After;
import org.junit.Before;

/**
 * Tests that OCL for model validation works.
 */
public class ValidateTests extends AbstractValidateTests
{
	public static @NonNull List<Diagnostic> assertEcoreOCLValidationDiagnostics(@Nullable OCL ocl, @NonNull String prefix, @NonNull Resource resource, @NonNull String... messages) {
		ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(resource);
		validationRegistry.put(EcorePackage.eINSTANCE, EcoreOCLEValidator.INSTANCE);
		ValidationContext validationContext = new ValidationContext(validationRegistry);
		if (ocl != null) {
			validationContext.put(OCL.class, ocl);
		}
		Diagnostician dignostician = validationContext.getDiagnostician();
		List<Diagnostic> diagnostics = new ArrayList<>();
		for (EObject eObject : resource.getContents()) {
			Diagnostic diagnostic = dignostician.validate(eObject, validationContext);
			diagnostics.addAll(diagnostic.getChildren());
		}
		return assertDiagnostics(prefix, resource, diagnostics, messages);
	}

	public static void assertLoggerText(@NonNull String expectedConsoleText) {
		assertEquals(expectedConsoleText, TestCaseLogger.INSTANCE.get());
		TestCaseLogger.INSTANCE.clear();
	}

	//	@Override
	protected @NonNull TestOCL createTestOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getTestName(), OCL.NO_PROJECTS, null);
	}

	public Resource doLoadEcore(@NonNull OCL ocl, @NonNull URI ecoreURI) throws IOException {
		Resource ecoreResource = ocl.getResourceSet().getResource(ecoreURI, true);
		return ecoreResource;
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testValidate_Simple_oclinecore() throws IOException, InterruptedException {
	//	EPackage ecoreEPackage = EcorePackage.eINSTANCE;
	//	EcoreUtil.setAnnotation(ecoreEPackage, PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL, "key", "value");
		//
		//	Create model
		//
		OCL ocl = createOCL();
		URI inputURI = getTestFile("Simple.oclinecore", ocl, getTestModelURI("models/oclinecore/Simple.oclinecore")).getFileURI();
		URI ecoreURI = getTestFile("Simple.ecore").getFileURI();
		Resource ecoreResource = doLoadOCLinEcore(ocl, inputURI, ecoreURI);
		EPackage simplePackage = ClassUtil.nonNullState((EPackage) ecoreResource.getContents().get(0));
		//
		//	Create test model
		//
		ResourceSet testResourceSet = new ResourceSetImpl();
		Resource testResource = testResourceSet.createResource(URI.createURI("test:test.test"));
		EObject testInstance = eCreate(simplePackage, "Simple");
		testResource.getContents().add(testInstance);
		//
		//	Validate it
		//
		String objectLabel1 = LabelUtil.getLabel(testInstance);
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
			StringUtil.bind(VIOLATED_TEMPLATE, "OCLinEcoreAlwaysFalse", objectLabel1));

		testResource.unload();
		ocl.dispose();
	}


	public void testValidate_Simple_oclinecore_and_ocl() throws IOException, InterruptedException {
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
	//	AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
		assert EValidator.ValidationDelegate.Registry.INSTANCE.containsKey(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
		//
		//	Load the model from OCLinEcore and Save as Ecore.
		//
		OCL ocl = createOCL();
		EnvironmentFactoryInternal environmentFactory = (EnvironmentFactoryInternal)ocl.getEnvironmentFactory();
		URI inputURI = getTestFile("Simple.oclinecore", ocl, getTestModelURI("models/oclinecore/Simple.oclinecore")).getFileURI();
		URI ecoreURI = getTestFile("Simple.ecore").getFileURI();
		Resource ecoreResource = doLoadOCLinEcore(ocl, inputURI, ecoreURI);
		EPackage simplePackage = ClassUtil.nonNullState((EPackage) ecoreResource.getContents().get(0));
		//
		//	Create an instance of Simple.
		//
		ResourceSet testResourceSet = new ResourceSetImpl();
		Resource testResource = testResourceSet.createResource(URI.createURI("test:test.test"));
		EObject testInstance = eCreate(simplePackage, "Simple");
		testResource.getContents().add(testInstance);
		//
		//	Validate using the OCLinEcore constraints.
		//
		String objectLabel = LabelUtil.getLabel(testInstance);
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
			StringUtil.bind(VIOLATED_TEMPLATE, "OCLinEcoreAlwaysFalse", objectLabel));
		//
		//	Load Simple.ocl using the already loaded Simple.oclinecore.
		//
		URI oclURI = getTestFile("Simple.ocl", ocl, getTestModelURI("models/oclinecore/Simple.ocl")).getFileURI();
		CompleteOCLLoaderWithLog helper = new CompleteOCLLoaderWithLog(environmentFactory, testResourceSet);
		String problems = helper.installDocuments(oclURI);
		assertNull("Failed to load " + oclURI, problems);
		//
		//	Validate using the OCLinEcore and Complete OCL constraints.
		//
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
			StringUtil.bind(VIOLATED_TEMPLATE, "OCLinEcoreAlwaysFalse", objectLabel),
			StringUtil.bind(VIOLATED_TEMPLATE, "CompleteOCLAlwaysFalse", objectLabel));
		//
		//	Unload the Complete OCL.
		//
		ASResource oclASResource = helper.unloadDocument(oclURI);
		CSResource oclCSResource = (CSResource)environmentFactory.getResourceSet().getResource(oclURI, false);
		assert oclCSResource != null;
		CSI2ASMapping iCSI2ASMapping = (CSI2ASMapping) environmentFactory.getCSI2ASMapping();
		assert iCSI2ASMapping != null;
		ASResource oclASResource2 = iCSI2ASMapping.getASResource(oclCSResource);
		assert oclASResource2 == oclASResource;
		Model asModel = PivotUtil.getModel(oclASResource);
		((AbstractEnvironmentFactory)environmentFactory).unload(oclCSResource);
		assert asModel.eResource() == null;
		assert oclASResource.getResourceSet() == null;
		assert !oclASResource.isLoaded();
		assert oclCSResource.getResourceSet() == null;
		assert !oclCSResource.isLoaded();
		//
		//	Validate using just the OCLinEcore constraints.
		//
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
			StringUtil.bind(VIOLATED_TEMPLATE, "OCLinEcoreAlwaysFalse", objectLabel));
		//
		//	Load again
		//
		String problems2 = helper.installDocuments(oclURI);
		assertNull("Failed to reload " + oclURI, problems2);
		//
		//	Validate using the OCLinEcore and Complete OCL constraints.
		//
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
			StringUtil.bind(VIOLATED_TEMPLATE, "OCLinEcoreAlwaysFalse", objectLabel),
			StringUtil.bind(VIOLATED_TEMPLATE, "CompleteOCLAlwaysFalse", objectLabel));
		//
		testResource.unload();
		ocl.dispose();
	}

	public void testValidate_Bug366229_oclinecore() throws IOException, InterruptedException {
	//	EPackage ecoreEPackage = EcorePackage.eINSTANCE;
	//	EcoreUtil.setAnnotation(ecoreEPackage, "xyzzy", "key", "value");
		//
		//	Create model
		//
		OCL ocl1 = createOCL();
		Resource ecoreResource = doLoadOCLinEcore(ocl1, getTestModelURI("models/oclinecore/Bug366229.oclinecore"));
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl2 = createOCL();
		ocl2.getResourceSet().getResources().add(ecoreResource);
		EPackage overloadsPackage = (EPackage) ecoreResource.getContents().get(0);
		ResourceSet testResourceSet = new ResourceSetImpl();
		Resource testResource = testResourceSet.createResource(URI.createURI("test:test.test"));
		EObject testInstance = eCreate(overloadsPackage, "SubClass");
		testResource.getContents().add(testInstance);
		//
		//	Check EObjectValidator errors
		//
		ValidationRegistryAdapter.getAdapter(testResourceSet).put(overloadsPackage, EObjectValidator.INSTANCE);
		checkValidationDiagnostics(testInstance, Diagnostic.OK);
		ocl2.dispose();
		ocl1.activate();
		ocl1.dispose();
	}

	public void testValidate_Bug418551_ecore() throws IOException, InterruptedException {
		String targetRelease = System.getProperty("targetRelease");
		if (targetRelease != null) {
			System.err.println(getTestName() + " skipped for " + targetRelease + " - message text changes");
			return;
		}
		//
		//	Create model
		//
		OCL ocl = createOCL();
		Resource ecoreResource = doLoadEcore(ocl, getTestModelURI("models/ecore/Bug418551.ecore"));
		EPackage temp = (EPackage) ecoreResource.getContents().get(0);
		EClass tester = (EClass) temp.getEClassifier("Tester");
		EOperation badOp = NameUtil.getENamedElement(tester.getEOperations(), "badOp");
		//
		//	Check EObjectValidator errors
		//
		@NonNull String[] messages1 = new @NonNull String[] {
			//			StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, InvocationBehavior.NAME, LabelUtil.getLabel(temp)),
			//			StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, SettingBehavior.NAME, LabelUtil.getLabel(temp)),
			//			StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, ValidationBehavior.NAME, LabelUtil.getLabel(temp)),
			StringUtil.bind(EcoreOCLEValidator.MISSING_CONSTRAINTS_ANNOTATION_ENTRY, PivotConstantsInternal.INVARIANT_ROLE, LabelUtil.getLabel(tester), "extraInvariant"),
			StringUtil.bind(EcoreOCLEValidator.EXTRA_CONSTRAINTS_ANNOTATION_ENTRY, PivotConstantsInternal.INVARIANT_ROLE, LabelUtil.getLabel(tester), "missingInvariant"),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "String", PivotConstantsInternal.BODY_ROLE, LabelUtil.getLabel(badOp)),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Integer", PivotConstantsInternal.PRECONDITION_ROLE, LabelUtil.getLabel(badOp)),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Integer", PivotConstantsInternal.POSTCONDITION_ROLE, LabelUtil.getLabel(badOp)),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Boolean", PivotConstantsInternal.INITIALIZER_ROLE, LabelUtil.getLabel(tester.getEStructuralFeature("badType"))),
			StringUtil.bind(EcoreOCLEValidator.MISSING_PROPERTY_KEY, LabelUtil.getLabel(tester.getEStructuralFeature("badDetailName"))),
			StringUtil.bind(EcoreOCLEValidator.DOUBLE_PROPERTY_KEY, LabelUtil.getLabel(tester.getEStructuralFeature("derivationAndInitial")))};
			checkValidationDiagnostics(temp, Diagnostic.ERROR, messages1);
			@NonNull String[] messages2 = new @NonNull String[] {
				StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, InvocationBehavior.NAME, LabelUtil.getLabel(temp)),
				StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, SettingBehavior.NAME, LabelUtil.getLabel(temp)),
				StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, ValidationBehavior.NAME, LabelUtil.getLabel(temp)),
				StringUtil.bind(EcoreOCLEValidator.MISSING_CONSTRAINTS_ANNOTATION_ENTRY, PivotConstantsInternal.INVARIANT_ROLE, LabelUtil.getLabel(tester), "extraInvariant"),
				StringUtil.bind(EcoreOCLEValidator.EXTRA_CONSTRAINTS_ANNOTATION_ENTRY, PivotConstantsInternal.INVARIANT_ROLE, LabelUtil.getLabel(tester), "missingInvariant"),
				StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "String", PivotConstantsInternal.BODY_ROLE, LabelUtil.getLabel(badOp)),
				StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Integer", PivotConstantsInternal.PRECONDITION_ROLE, LabelUtil.getLabel(badOp)),
				StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Integer", PivotConstantsInternal.POSTCONDITION_ROLE, LabelUtil.getLabel(badOp)),
				StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Boolean", PivotConstantsInternal.INITIALIZER_ROLE, LabelUtil.getLabel(tester.getEStructuralFeature("badType"))),
				StringUtil.bind(EcoreOCLEValidator.MISSING_PROPERTY_KEY, LabelUtil.getLabel(tester.getEStructuralFeature("badDetailName"))),
				StringUtil.bind(EcoreOCLEValidator.DOUBLE_PROPERTY_KEY, LabelUtil.getLabel(tester.getEStructuralFeature("derivationAndInitial")))};
				assertEcoreOCLValidationDiagnostics(ocl, "Ecore Load", ecoreResource, messages2);
				//
				ocl.dispose();
	}

	public void testValidate_Bug418552_oclinecore() throws IOException, InterruptedException {
		String testDocument =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"\n" +
						"package temp : Test = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Tester\n" +
						"	{\n" +
						"		attribute total : ecore::EDoubleObject { derived volatile }\n" +
						"		{\n" +
						"			derivation: true;\n" +
						"		}\n" +
						"	}\n" +
						"}\n";
		createFile("Bug418552.oclinecore", testDocument);
		OCL ocl1 = createOCL();
		@NonNull List<Diagnostic> diagnostics = doValidateOCLinEcore(ocl1, "Bug418552", getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Property::CompatibleDefaultExpression", "temp::Tester::total")));
		Object property = diagnostics.get(0).getData().get(0);
		assert property != null;
		assertEquals(PivotPackage.Literals.PROPERTY, ((EObject)property).eClass());
		ModelElementCS csElement = ElementUtil.getCsElement((Element) property);
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		assert node != null;
		assertEquals(7, node.getStartLine());
		assertEquals(10, node.getEndLine());
		ocl1.dispose();
	}

	public void testValidate_Bug543173_ecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl = OCL.newInstance(getProjectMap());
		URI ecoreURI = getTestModelURI("models/ecore//Bug543173.ecore");
		Resource ecoreResource = ocl.getResourceSet().getResource(ecoreURI, true);
		assert ecoreResource != null;
		//
		//	Check EObjectValidator errors
		//
		assertEcoreOCLValidationDiagnostics(ocl, "Ecore Load", ecoreResource);
		//
		ocl.dispose();
	}

	public void testValidate_Bug543187_ecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl = OCL.newInstance(getProjectMap());
		URI ecoreURI = getTestModelURI("models/ecore/Bug543187.ecore");
		Resource ecoreResource = ocl.getResourceSet().getResource(ecoreURI, true);
		assert ecoreResource != null;
		//
		//	Check EObjectValidator errors
		//
		assertEcoreOCLValidationDiagnostics(ocl, "Ecore Load", ecoreResource);
		//
		ocl.dispose();
	}

	public void testValidate_Bug543187_xmi() throws IOException, InterruptedException {
		int oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT = AbstractEnvironmentFactory.CONSTRUCTION_COUNT;
		int oldAbstractModelManager_CONSTRUCTION_COUNT = AbstractModelManager.CONSTRUCTION_COUNT;
		int oldExecutorManager_CONSTRUCTION_COUNT = ExecutorManager.CONSTRUCTION_COUNT;
		int oldAbstractExecutor_CONSTRUCTION_COUNT = AbstractExecutor.CONSTRUCTION_COUNT;
		OCL ocl = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet = ocl.getResourceSet();
		URI xmiURI = getTestModelURI("models/ecore/Bug543187.xmi");
		Resource resource = resourceSet.getResource(xmiURI, true);
		assertNoValidationErrors("Validating", ClassUtil.nonNullState(resource));
		assertEquals("AbstractEnvironmentFactory.CONSTRUCTION_COUNT", 1, AbstractEnvironmentFactory.CONSTRUCTION_COUNT-oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT);
		assertEquals("AbstractModelManager.CONSTRUCTION_COUNT", 1, AbstractModelManager.CONSTRUCTION_COUNT-oldAbstractModelManager_CONSTRUCTION_COUNT);
		assertEquals("ExecutorManager.CONSTRUCTION_COUNT", 0, ExecutorManager.CONSTRUCTION_COUNT-oldExecutorManager_CONSTRUCTION_COUNT);  // 0 - no longer used, 1 for outer validation, 2 more for inner validations
		assertEquals("AbstractExecutor.CONSTRUCTION_COUNT", 8, AbstractExecutor.CONSTRUCTION_COUNT-oldAbstractExecutor_CONSTRUCTION_COUNT);  // 8 validation evaluations
		ocl.dispose();
	}

	// See Bug 574324
	public void testValidate_IsPrimeNumber_completeocl() throws IOException, InterruptedException, InvocationTargetException {
		TestOCL ocl = createTestOCL();
		String xmiDocument =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n"
				+ "    name=\"IsPrimeNumber\" nsURI=\"isPrimeNumber\" nsPrefix=\"isp\">\n"
				+ "</ecore:EPackage>\n"
				+ "\n";
		TestFile xmiTestFile = createFile("IsPrimeNumber.xmi", xmiDocument);
		URI xmiURI = xmiTestFile.getFileURI();
		Resource ecoreResource = ocl.getResourceSet().getResource(xmiURI, true);
		EPackage testPackage = ClassUtil.nonNullState((EPackage) ecoreResource.getContents().get(0));
		String oclTestDocument =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/'\n"
				+ "\n"
				+ "package ecore\n"
				+ "\n"
				+ "context EModelElement\n"
				+ "def: isPrimeNumber(candidatePrime : Integer) : Boolean =\n"
				+ "	(0 < candidatePrime)\n"
				+ "	  and ((candidatePrime < 4)\n"
				+ "		  or ((candidatePrime.div(2) * 2) <> candidatePrime)\n"
				+ "			 and isPrimeNumber(candidatePrime, 3))\n"
				+ "\n"
				+ "def: isPrimeNumber(candidatePrime : Integer, candidateFactor:Integer) : Boolean =\n"
				+ "	((candidatePrime.div(candidateFactor) * candidateFactor) <> candidatePrime)\n"
				+ "	  and ((candidateFactor*candidateFactor > candidatePrime)\n"
				+ "		  or isPrimeNumber(candidatePrime, candidateFactor+2))\n"
				+ "endpackage\n";
		TestFile oclTestFile = createFile("IsPrimeNumber.ocl", oclTestDocument);
		URI oclURI = oclTestFile.getFileURI();
		ocl.getResourceSet().getResource(oclURI, true);
		try {
			checkValidationDiagnostics(testPackage, Diagnostic.OK);
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(0)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(1)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(2)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(3)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(4)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(5)");

			ocl.assertQueryFalse(testPackage, "isPrimeNumber(15)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(16)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(17)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(18)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(19)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(20)");

			ocl.assertQueryFalse(testPackage, "isPrimeNumber(96)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(97)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(98)");

			ocl.assertQueryFalse(testPackage, "isPrimeNumber(996)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(997)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(998)");

			ocl.assertQueryFalse(testPackage, "isPrimeNumber(10005)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(10006)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(10007)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(10008)");
			ocl.assertQueryTrue(testPackage, "isPrimeNumber(10009)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(10010)");
			ocl.assertQueryFalse(testPackage, "isPrimeNumber(10011)");
		}
		finally {
			ocl.dispose();
		}
	}

	// See Bug 574324, Bug 574483
	public void testValidate_IsPrimeNumber_completeocl_Integer() throws IOException, InterruptedException, InvocationTargetException {
		TestOCL ocl = createTestOCL();
		String oclTestDocument =
				"package ocl\n"
				+ "\n"
				+ "context Integer\n"
				+ "def: isPrimeNumber() : Boolean =\n"
				+ "	(0 < self)\n"
				+ "	  and ((self < 4)\n"
				+ "		  or ((self.div(2) * 2) <> self)\n"
				+ "			 and self.isPrimeNumber(3))\n"
				+ "\n"
				+ "def: isPrimeNumber(candidateFactor:Integer) : Boolean =\n"
				+ "	((self.div(candidateFactor) * candidateFactor) <> self)\n"
				+ "	  and ((candidateFactor*candidateFactor > self)\n"
				+ "		  or self.isPrimeNumber(candidateFactor+2))\n"
				+ "endpackage\n";
		TestFile oclTestFile = createFile("IsPrimeNumber.ocl", oclTestDocument);
		URI oclURI = oclTestFile.getFileURI();
		CSResource csResource = (CSResource) ocl.getResourceSet().getResource(oclURI, true);
		assert csResource != null;
		assertNoResourceErrors("Load", csResource);
		try {
			ocl.assertQueryFalse(null, "0.isPrimeNumber()");
			ocl.assertQueryTrue(null, "1.isPrimeNumber()");
			ocl.assertQueryTrue(null, "2.isPrimeNumber()");
			ocl.assertQueryTrue(null, "3.isPrimeNumber()");
			ocl.assertQueryFalse(null, "4.isPrimeNumber()");
			ocl.assertQueryTrue(null, "5.isPrimeNumber()");

			ocl.assertQueryFalse(null, "15.isPrimeNumber()");
			ocl.assertQueryFalse(null, "16.isPrimeNumber()");
			ocl.assertQueryTrue(null, "17.isPrimeNumber()");
			ocl.assertQueryFalse(null, "18.isPrimeNumber()");
			ocl.assertQueryTrue(null, "19.isPrimeNumber()");
			ocl.assertQueryFalse(null, "20.isPrimeNumber()");

			ocl.assertQueryFalse(null, "96.isPrimeNumber()");
			ocl.assertQueryTrue(null, "97.isPrimeNumber()");
			ocl.assertQueryFalse(null, "98.isPrimeNumber()");

			ocl.assertQueryFalse(null, "996.isPrimeNumber()");
			ocl.assertQueryTrue(null, "997.isPrimeNumber()");
			ocl.assertQueryFalse(null, "998.isPrimeNumber()");

			ocl.assertQueryFalse(null, "10005.isPrimeNumber()");
			ocl.assertQueryFalse(null, "10006.isPrimeNumber()");
			ocl.assertQueryTrue(null, "10007.isPrimeNumber()");
			ocl.assertQueryFalse(null, "10008.isPrimeNumber()");
			ocl.assertQueryTrue(null, "10009.isPrimeNumber()");
			ocl.assertQueryFalse(null, "10010.isPrimeNumber()");
			ocl.assertQueryFalse(null, "10011.isPrimeNumber()");
		}
		finally {
			ocl.dispose();
		}
	}

	// See Bug 574324
	public void testValidate_IsPrimeNumber_oclinecore() throws IOException, InterruptedException, InvocationTargetException {
		TestOCL ocl = createTestOCL();
		String testDocument =
				"package isPrimeNumber : isp = 'isPrimeNumber'\n"
				+ "{\n"
				+ "	class MyNumber\n"
				+ "	{\n"
				+ "		operation isPrimeNumber(candidatePrime : Integer) : Boolean {\n"
				+ "			body: (0 < candidatePrime)\n"
				+ "				  and ((candidatePrime < 4)\n"
				+ "					  or ((candidatePrime.div(2) * 2) <> candidatePrime)\n"
				+ "						 and isPrimeNumber(candidatePrime, 3));\n"
				+ "		}\n"
				+ "\n"
				+ "		operation isPrimeNumber(candidatePrime : Integer, candidateFactor:Integer) : Boolean {\n"
//				+ "			body: false.oclLog('\ncandidatePrime='+candidatePrime.toString() + ', candidateFactor='+candidateFactor.toString() +'\n') or\n"
				+ "			body: ((candidatePrime.div(candidateFactor) * candidateFactor) <> candidatePrime)\n"
				+ "				  and ((candidateFactor*candidateFactor > candidatePrime)\n"
				+ "					  or isPrimeNumber(candidatePrime, candidateFactor+2));\n"
				+ "		}\n"
				+ "	}\n"
				+ "}\n";
		TestFile testFile = createFile("IsPrimeNumber.oclinecore", testDocument);
		URI inputURI = testFile.getFileURI();
		URI ecoreURI = getTestFile("IsPrimeNumber.ecore").getFileURI();
		Resource ecoreResource = doLoadOCLinEcore(ocl, inputURI, ecoreURI);
		EPackage validatePackage = ClassUtil.nonNullState((EPackage) ecoreResource.getContents().get(0));
		try {
			ResourceSet testResourceSet = new ResourceSetImpl();
			Resource testResource = testResourceSet.createResource(URI.createURI("test:test.test"));
			EObject testInstance = eCreate(validatePackage, "MyNumber");
			testResource.getContents().add(testInstance);
			checkValidationDiagnostics(testInstance, Diagnostic.OK);
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(0)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(1)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(2)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(3)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(4)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(5)");

			ocl.assertQueryFalse(testInstance, "isPrimeNumber(15)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(16)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(17)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(18)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(19)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(20)");

			ocl.assertQueryFalse(testInstance, "isPrimeNumber(96)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(97)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(98)");

			ocl.assertQueryFalse(testInstance, "isPrimeNumber(996)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(997)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(998)");

			ocl.assertQueryFalse(testInstance, "isPrimeNumber(10005)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(10006)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(10007)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(10008)");
			ocl.assertQueryTrue(testInstance, "isPrimeNumber(10009)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(10010)");
			ocl.assertQueryFalse(testInstance, "isPrimeNumber(10011)");
		}
		finally {
			ocl.dispose();
		}
	}

	public void testValidate_Pivot_ecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl = OCL.newInstance(getProjectMap());
		URI ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ecore", true);
		Resource ecoreResource = ocl.getResourceSet().getResource(ecoreURI, true);
		assert ecoreResource != null;
		//
		//	Check EObjectValidator errors
		//
		assertEcoreOCLValidationDiagnostics(ocl, "Ecore Load", ecoreResource);
		//
		ocl.dispose();
	}

	public void testValidate_OCL_2_5_oclas() throws IOException, InterruptedException {
		ResourceSet resourceSet = new ResourceSetImpl();
		//		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
		getProjectMap().initializeResourceSet(resourceSet);
		//		}
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model-gen/OCL-2.5.oclas", true), true);
		assertNoValidationErrors("Validating", ClassUtil.nonNullState(resource));
	}

	public void testValidate_Pivot_oclas() throws IOException, InterruptedException {
		ResourceSet resourceSet = new ResourceSetImpl();
		//		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
		getProjectMap().initializeResourceSet(resourceSet);
		OCLASResourceFactory.getInstance().configure(resourceSet);
		//		}
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model-gen/Pivot.oclas", true), true);
		assertNoValidationErrors("Validating", ClassUtil.nonNullState(resource));
	}

	public void testValidate_Validate_completeocl() throws IOException, InterruptedException {
		TestCaseAppender.INSTANCE.uninstall();
		TestCaseLogger.INSTANCE.install();
		//
		//	Create model
		//
		//	0 - the complementing type system for the validator
		//	1 - the evolving complemented type system under test
		//	2 - the stable complemented type system under test
		//
		OCL ocl1 = createOCL();
		URI inputURI = getTestFile("Validate.oclinecore", ocl1, getTestModelURI("models/oclinecore/Validate.oclinecore")).getFileURI();
		URI ecoreURI = getTestFile("Validate.ecore").getFileURI();
		Resource ecoreResource1 = doLoadOCLinEcore(ocl1, inputURI, ecoreURI);
		EPackage validatePackage1 = ClassUtil.nonNullState((EPackage) ecoreResource1.getContents().get(0));
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl2 = createOCL();
		Resource ecoreResource2 = doLoadOCLinEcore(ocl2, inputURI, ecoreURI);
		EPackage validatePackage2 = ClassUtil.nonNullState((EPackage) ecoreResource2.getContents().get(0));
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl0 = createOCL();
		URI oclURI = getTestFile("Validate.ocl", ocl0, getTestModelURI("models/oclinecore/Validate.ocl")).getFileURI();
		CompleteOCLEObjectValidator completeOCLEObjectValidator1 = new CompleteOCLEObjectValidator(validatePackage1, oclURI);
		CompleteOCLEObjectValidator completeOCLEObjectValidator2 = new CompleteOCLEObjectValidator(validatePackage2, oclURI);
		ResourceSetImpl testResourceSet = new ResourceSetImpl();
		ValidationRegistryAdapter.getAdapter(testResourceSet).putWithGlobalDelegation(validatePackage1, completeOCLEObjectValidator1);
		ValidationRegistryAdapter.getAdapter(testResourceSet).putWithGlobalDelegation(validatePackage2, completeOCLEObjectValidator2);
		EObject testInstance1 = eCreate(validatePackage1, "Level3");
		EObject testInstance2 = eCreate(validatePackage2, "Level3");
		Resource testResource = testResourceSet.createResource(URI.createURI("test:test.test"));
		testResource.getContents().add(testInstance1);
		testResource.getContents().add(testInstance2);
		String template = PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_;
		String objectLabel1; // = LabelUtil.getLabel(testInstance1);
		String objectLabel2; // = LabelUtil.getLabel(testInstance2);
		String message1;
		String message2;
		String conflictingResourceMessage = StringUtil.bind(PivotMessages.ConflictingResource, ecoreURI.toString());
		try {
			//
			//	No errors when EPackage is consistent
			//
			ThreadLocalExecutor.resetEnvironmentFactory();
			eSet(testInstance1, "ref", "xx");
			eSet(testInstance1, "l1", "xx");
			eSet(testInstance1, "l2a", "xx");
			eSet(testInstance1, "l2b", "xx");
			eSet(testInstance1, "l3", "xx");
			eSet(testInstance2, "ref", "yy");
			eSet(testInstance2, "l1", "yy");
			eSet(testInstance2, "l2a", "yy");
			eSet(testInstance2, "l2b", "yy");
			eSet(testInstance2, "l3", "yy");
			objectLabel1 = LabelUtil.getLabel(testInstance1);
			objectLabel2 = LabelUtil.getLabel(testInstance2);
			message1 = StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_,  "Level1", "L1_size", objectLabel1, "The feature 'l1' is not a valid feature");
			message2 = StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_,  "Level1", "L1_size", objectLabel2, "The feature 'l1' is not a valid feature");
			//
			// yet another OCL - dynamic OCL's EPackage is loaded to match first testInstance1 and so not testInstance2 - CompleteOCLEObjectValidator's Validate.ecore EPackage is inconsistent with Validate.oclinecore import
			//
			checkValidationDiagnostics(testInstance1, Diagnostic.OK);
			assertLoggerText(conflictingResourceMessage);
			EnvironmentFactoryInternal dynamicEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert dynamicEnvironmentFactory != null;
			Model dynamicModel = PivotUtil.getModel(dynamicEnvironmentFactory.getASResourceSet().getResources().get(0));
			assert dynamicEnvironmentFactory != ocl0.getEnvironmentFactory();
			assert dynamicEnvironmentFactory != ocl1.getEnvironmentFactory();
			assert dynamicEnvironmentFactory != ocl2.getEnvironmentFactory();
			assert dynamicEnvironmentFactory.getResourceSet() != testResourceSet;
			assert dynamicModel.getOwnedPackages().get(0).getESObject() == validatePackage1;
			//
			checkValidationDiagnostics(testInstance1, Diagnostic.OK);
			assertLoggerText("");			// Only error once on reload by new EnvironmentFactory
			//
			checkValidationDiagnostics(testInstance2, Diagnostic.ERROR, conflictingResourceMessage);		// Fails to load ePackage2 from conflicting ecoreResource
			assertLoggerText("");
			dynamicEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert dynamicEnvironmentFactory != null;
			dynamicModel = PivotUtil.getModel(dynamicEnvironmentFactory.getASResourceSet().getResources().get(0));
			assert dynamicModel.getOwnedPackages().get(0).getESObject() == validatePackage1;
			//
			checkValidationDiagnostics(testInstance1, Diagnostic.OK);
			assertLoggerText("");
			//
			ThreadLocalExecutor.resetEnvironmentFactory();
			dynamicEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert dynamicEnvironmentFactory == null;
			//
			// yet another OCL - but testResourceSet has CS resources for reload
			checkValidationDiagnostics(testInstance1, Diagnostic.OK);
			assertLoggerText(conflictingResourceMessage);
			checkValidationDiagnostics(testInstance2, Diagnostic.ERROR, conflictingResourceMessage);
			assertLoggerText("");
			//
			ThreadLocalExecutor.resetEnvironmentFactory();
			dynamicEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert dynamicEnvironmentFactory == null;
		//	testResource.getContents().move(1, 0);					// Reverse order
		//	testResourceSet.getURIResourceMap().clear();			//  lose order-affected cache
			//
			// yet another OCL - but testResourceSet has CS resources for reload, but now testInstance2 is first
			checkValidationDiagnostics(testInstance2, Diagnostic.OK);
		//	checkValidationDiagnostics(testInstance1, Diagnostic.ERROR, conflictingResourceMessage);
			assertLoggerText(conflictingResourceMessage);
			checkValidationDiagnostics(testInstance1, Diagnostic.ERROR, conflictingResourceMessage);
		//	checkValidationDiagnostics(testInstance2, Diagnostic.OK);
			assertLoggerText("");
			//
		//	testResource.getContents().move(1, 0);					// Restore order
		//	testResourceSet.getURIResourceMap().clear();			//  lose order-affected cache
			//
			ocl0.activate();	// OCL's EPackage matches for first loaded testInstance and dynamically parsed Complete OCL
			checkValidationDiagnostics(testInstance2, Diagnostic.OK);
			checkValidationDiagnostics(testInstance1, Diagnostic.ERROR, conflictingResourceMessage);
			ocl1.activate();	// OCL's EPackage is inconsistent for testInstance2 and statically parsed Complete OCL
			checkValidationDiagnostics(testInstance1, Diagnostic.OK);
			checkValidationDiagnostics(testInstance2, Diagnostic.ERROR, conflictingResourceMessage);
			ocl2.activate();	// OCL's EPackage is inconsistent for testInstance1 and statically parsed Complete OCL
			checkValidationDiagnostics(testInstance1, Diagnostic.ERROR, conflictingResourceMessage);
			checkValidationDiagnostics(testInstance2, Diagnostic.OK);
			//
			//	OCLinEcore/CompleteOCL errors all levels - too long sizes, consistent texts
			//
			ValidationRegistryAdapter.getAdapter(testResourceSet).putWithGlobalDelegation(validatePackage2, null);
		//	ThreadLocalExecutor.resetEnvironmentFactory();
			eSet(testInstance1, "ref", "xxx");
			eSet(testInstance1, "l1", "xxx");
			eSet(testInstance1, "l2a", "xxx");
			eSet(testInstance1, "l2b", "xxx");
			eSet(testInstance1, "l3", "xxx");
			eSet(testInstance2, "ref", "yyy");
			eSet(testInstance2, "l1", "yyy");
			eSet(testInstance2, "l2a", "yyy");
			eSet(testInstance2, "l2b", "yyy");
			eSet(testInstance2, "l3", "yyy");
			objectLabel1 = LabelUtil.getLabel(testInstance1);
			objectLabel2 = LabelUtil.getLabel(testInstance2);
			ocl1.activate();	// with Complete OCL - bad sizes, ok texts
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::L1_size", objectLabel1),
				StringUtil.bind(template, "Level2a::L2a_size", objectLabel1),
				StringUtil.bind(template, "Level2b::L2b_size", objectLabel1),
				StringUtil.bind(template, "Level3::L3_size", objectLabel1));
			ocl2.activate();	// without Complete OCL - ok texts
			checkValidationDiagnostics(testInstance2, Diagnostic.OK);
			//
			//	OCLinEcore/CompleteOCL errors one level - too long size, bad text
			//
			ThreadLocalExecutor.resetEnvironmentFactory();
			eSet(testInstance1, "ref", "ok");
			eSet(testInstance1, "l1", "ok");
			eSet(testInstance1, "l2a", "bad");
			eSet(testInstance1, "l2b", "ok");
			eSet(testInstance1, "l3", "ok");
			eSet(testInstance2, "ref", "ok");
			eSet(testInstance2, "l1", "ok");
			eSet(testInstance2, "l2a", "bad");
			eSet(testInstance2, "l2b", "ok");
			eSet(testInstance2, "l3", "ok");
			objectLabel1 = LabelUtil.getLabel(testInstance1);
			objectLabel2 = LabelUtil.getLabel(testInstance2);
			ocl1.activate();	// with Complete OCL - 1 bad size, 1 bad text
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING,
				StringUtil.bind(template,  "Level2a::L2a_text", objectLabel1),
				StringUtil.bind(template,  "Level2a::L2a_size", objectLabel1));
			ocl2.activate();	// without Complete OCL - 1 bad text
			checkValidationDiagnostics(testInstance2, Diagnostic.ERROR,
				StringUtil.bind(VIOLATED_TEMPLATE, "L2a_text", objectLabel2));
		}
		finally {
			testResource.unload();
			ocl0.activate();
			ocl0.dispose();
			ocl1.activate();
			ocl1.dispose();
			ocl2.activate();
			ocl2.dispose();
		}
	}

	public void testValidate_Validate_completeocl_loadresource() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		try {
			CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
			ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
			org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
			OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
			OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
			//
			URI ecoreURI = getTestModelURI("models/documentation/OCLinEcoreTutorial.ecore");
			URI xmiURI = getTestModelURI("models/documentation/OCLinEcoreTutorial.xmi");
			//		URI oclURI = getTestModelURI("ExtraOCLinEcoreTutorial.ocl");
			String testDocument =
					"import '" + ecoreURI.toString() + "'\n" +
							"package tutorial\n" +
							"context Book\n" +
							"inv ExactlyOneCopy: copies=1\n" +
							"endpackage\n";
			TestFile testFile = createFile("ExtraOCLinEcoreTutorial.ocl", testDocument);
			//
			Resource resource = ClassUtil.nonNullState(resourceSet.getResource(xmiURI, true));
//			assertValidationDiagnostics("Without Complete OCL", resource, getMessages(
//				StringUtil.bind(VIOLATED_TEMPLATE, "SufficientCopies", "Library::lib::Book::b2"),
//				StringUtil.bind(VIOLATED_TEMPLATE, "AtMostTwoLoans", "Library::lib::Member::m3"),
//				StringUtil.bind(VIOLATED_TEMPLATE, "UniqueLoans", "Library::lib::Member::m3")));
			//
			CompleteOCLLoader helper = new TestCompleteOCLLoader(ocl.getEnvironmentFactory());
			URI oclURI = testFile.getFileURI();
			String problems = helper.installDocuments(oclURI);
			assertNull("Failed to load " + oclURI, problems);
			assertTrue(helper.loadMetamodels());

			ThreadLocalExecutor.resetEnvironmentFactory();		// Emulate interactive Load then Validate

			@NonNull String[] messages = getMessages(//validationContext,
				StringUtil.bind(VIOLATED_TEMPLATE, "SufficientCopies", "Library::lib::Book::b2"),
				StringUtil.bind(VIOLATED_TEMPLATE, "AtMostTwoLoans", "Library::lib::Member::m3"),
				StringUtil.bind(VIOLATED_TEMPLATE, "UniqueLoans", "Library::lib::Member::m3"),
				StringUtil.bind(VIOLATED_TEMPLATE, "ExactlyOneCopy", "Library::lib::Book::b2"));
			//	StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Book::ExactlyOneCopy", "Library lib::Book b2"));
			assertValidationDiagnostics("With Complete OCL", resource, messages);
		//	ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(resource);
		//	ValidationContext validationContext = new ValidationContext(validationRegistry);
		//	EnvironmentFactoryInternal environmentFactory = ValidationContext.getEnvironmentFactory(validationContext, resource);			// Eager EnvironmentFactory resolution
		//	assertValidationDiagnostics("With Complete OCL", resource, validationContext, messages);
			//		disposeResourceSet(resourceSet);
			ocl.activate();
			helper.dispose();
		}
		finally {
			ocl.dispose();
		}
	}

	public void testValidate_Validate_completeocl_Bug422583() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();

		ProjectMap.initializeURIResourceMap(resourceSet);
		Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			uriMap.putAll(EcorePlugin.computePlatformURIMap(true));
		}
		UML2AS.initialize(resourceSet);


		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		//		MetamodelManagerResourceSetAdapter adapter = MetamodelManagerResourceSetAdapter.getAdapter(resourceSet, metamodelManager);
		//
		URI umlURI = getTestModelURI("models/uml/Names.uml");
		String testDocument =
				//				"import uml : '" + UMLResource.UML_METAMODEL_URI + "#/'\n" +
				//				"import uml : '" + XMI2UMLResource.UML_METAMODEL_NS_URI + "'\n" +
				"import uml : 'http://www.eclipse.org/uml2/5.0.0/UML#/'\n" +
				"package uml\n" +
				"  context Element\n" +
				"  def: alwaysTrue() : Boolean = true\n" +
				"  def: rootFalse() : Boolean = false\n" +
				"  inv IsElement: self.alwaysTrue()\n" +
				"  context Classifier\n" +
				"  def: rootFalse() : Boolean = true\n" +
				"  def: leafFalse() : Boolean = true\n" +
				"  inv IsClassifier: self.alwaysTrue()\n" +
				"  inv IsClassifierWrtLeaf: self.leafFalse()\n" +
				"  context Class\n" +
				"  def: leafFalse() : Boolean = false\n" +
				"  inv IsClass: self.alwaysTrue()\n" +
				"  inv IsClassWrtRoot: self.rootFalse()\n" +
				"  inv IsClassWrtLeaf: self.leafFalse()\n" +
				"endpackage\n";
		TestFile testFile = createFile("Bug422583.ocl", testDocument);
		//
		Resource resource = ClassUtil.nonNullState(resourceSet.getResource(umlURI, true));
		org.eclipse.uml2.uml.Class uNamed = null;
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.uml2.uml.Class) {
				if ("UNamed".equals(((org.eclipse.uml2.uml.Class)eObject).getName())) {
					uNamed = (org.eclipse.uml2.uml.Class)eObject;
					break;
				}
			}
		}
		assert uNamed != null;
		assertValidationDiagnostics("Without Complete OCL", resource, NO_MESSAGES);
		//
		CompleteOCLLoader helper = new TestCompleteOCLLoader(ocl.getEnvironmentFactory());
		URI oclURI = testFile.getFileURI();
		String problems = helper.installDocuments(oclURI);
		assertNull("Failed to load " + oclURI, problems);
		assertTrue(helper.loadMetamodels());
		String objectLabel1 = LabelUtil.getLabel(uNamed);
		//		String objectLabel3 = ClassUtil.getLabel(uNamed.getOwnedAttribute("r", null).getLowerValue());
		//		String objectLabel4 = ClassUtil.getLabel(uNamed.getOwnedAttribute("s", null).getLowerValue());
		assertValidationDiagnostics("Without Complete OCL", resource, getMessages(
		//	StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Classifier::IsClassifierWrtLeaf", objectLabel1),
		//	StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class::IsClassWrtLeaf", objectLabel1)/*,
			StringUtil.bind(VIOLATED_TEMPLATE, "IsClassifierWrtLeaf", objectLabel1),
			StringUtil.bind(VIOLATED_TEMPLATE, "IsClassWrtLeaf", objectLabel1)/*,
			ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "NamedElement", "visibility_needs_ownership", objectLabel3),	// FIXME BUG 437450
			ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "NamedElement", "visibility_needs_ownership", objectLabel4)*/));	// FIXME BUG 437450
		//		adapter.getMetamodelManager().dispose();
		//		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
	}

	@SuppressWarnings("null")
	public void testValidate_Validate_oclinecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl = createOCL();
		Resource ecoreResource = doLoadOCLinEcore(ocl, getTestModelURI("models/oclinecore/Validate.oclinecore"));
		EPackage validatePackage = (EPackage) ecoreResource.getContents().get(0);
		ResourceSet testResourceSet = new ResourceSetImpl();
		Resource testResource = testResourceSet.createResource(URI.createURI("test:test.test"));
		EObject testInstance = eCreate(validatePackage, "Level3");
		eSet(testInstance, "ref", "ref");
		eSet(testInstance, "l1", "l1");
		eSet(testInstance, "l2a", "l2a");
		eSet(testInstance, "l2b", "l2b");
		eSet(testInstance, "l3", "l3");
		testResource.getContents().add(testInstance);
		String objectLabel = LabelUtil.getLabel(testInstance);
		//
		//	Check EObjectValidator errors
		//
		ValidationRegistryAdapter.getAdapter(testResourceSet).put(validatePackage, EObjectValidator.INSTANCE);
		try {
			String template = EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic");
			checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
				StringUtil.bind(template,  "L1_text", objectLabel),
				StringUtil.bind(template,  "L2a_text", objectLabel),
				//BUG355184		ClassUtil.bind(template,  "L2b", objectLabel),
				StringUtil.bind(template,  "L3_text", objectLabel));
			//
			//	Check OCLinEcoreEObjectValidator warnings and distinct message
			//
			ValidationRegistryAdapter.getAdapter(testResourceSet).put(validatePackage, new OCLinEcoreEObjectValidator());
			template = PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_;
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::L1_text", objectLabel),
				StringUtil.bind(template, "Level2a::L2a_text", objectLabel),
				//BUG355184		ClassUtil.bind(template,  "L2b_text", objectLabel),
				StringUtil.bind(template, "Level3::L3_text", objectLabel));
			//
			//	No errors
			//
			eSet(testInstance, "ref", "ok");
			eSet(testInstance, "l1", "ok");
			eSet(testInstance, "l2a", "ok");
			eSet(testInstance, "l2b", "ok");
			eSet(testInstance, "l3", "ok");
			objectLabel = LabelUtil.getLabel(testInstance);
			checkValidationDiagnostics(testInstance, Diagnostic.OK);
			//
			//	Just one error
			//
			eSet(testInstance, "ref", "ok");
			eSet(testInstance, "l1", "bad");
			eSet(testInstance, "l2a", "ok");
			eSet(testInstance, "l2b", "ok");
			eSet(testInstance, "l3", "ok");
			objectLabel = LabelUtil.getLabel(testInstance);
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::L1_text", objectLabel));
		} finally {
			ocl.dispose();
		}
	}
}
