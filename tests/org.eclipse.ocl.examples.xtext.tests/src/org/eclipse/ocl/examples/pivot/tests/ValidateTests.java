/*******************************************************************************
 * Copyright (c) 2014, 2021 Willink Transformations and others.
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
import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.evaluation.AbstractModelManager;
import org.eclipse.ocl.pivot.internal.delegate.InvocationBehavior;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.SettingBehavior;
import org.eclipse.ocl.pivot.internal.delegate.ValidationBehavior;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractExecutor;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorManager;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.validation.EcoreOCLEValidator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;
import org.eclipse.ocl.xtext.completeocl.validation.CompleteOCLEObjectValidator;
import org.eclipse.ocl.xtext.oclinecore.validation.OCLinEcoreEObjectValidator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.junit.After;
import org.junit.Before;

import junit.framework.TestCase;

/**
 * Tests that OCL for model validation works.
 */
public class ValidateTests extends AbstractValidateTests
{
	public static @NonNull List<Diagnostic> assertEcoreOCLValidationDiagnostics(@Nullable OCL ocl, @NonNull String prefix, @NonNull Resource resource, @NonNull String... messages) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		if (ocl != null) {
			validationContext.put(OCL.class,  ocl);
		}
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		for (EObject eObject : resource.getContents()) {
			EValidatorRegistryImpl registry = new EValidatorRegistryImpl();
			registry.put(EcorePackage.eINSTANCE, EcoreOCLEValidator.INSTANCE);
			Diagnostician dignostician = new Diagnostician(registry);
			Diagnostic diagnostic = dignostician.validate(eObject, validationContext);
			diagnostics.addAll(diagnostic.getChildren());
		}
		return assertDiagnostics(prefix, resource, diagnostics, messages);
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

	public void testValidate_Bug366229_oclinecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl1 = createOCL();
		Resource ecoreResource = doLoadOCLinEcore(ocl1, getTestModelURI("models/oclinecore/Bug366229.oclinecore"));
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl2 = createOCL();
		ocl2.getResourceSet().getResources().add(ecoreResource);
		EPackage overloadsPackage = (EPackage) ecoreResource.getContents().get(0);
		EObject testInstance = eCreate(overloadsPackage, "SubClass");
		//
		//	Check EObjectValidator errors
		//
		EValidator.Registry.INSTANCE.put(overloadsPackage, EObjectValidator.INSTANCE);
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR);
		ocl1.dispose();
		ocl2.dispose();
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
		assertEquals("ExecutorManager.CONSTRUCTION_COUNT", 1, ExecutorManager.CONSTRUCTION_COUNT-oldExecutorManager_CONSTRUCTION_COUNT);  // 1 for outer validation, 2 more for inner validations
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
			checkValidationDiagnostics(testPackage, Diagnostic.WARNING);
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
			EObject testInstance = eCreate(validatePackage, "MyNumber");
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING);
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
			EValidator.Registry.INSTANCE.remove(validatePackage);
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
		CompleteOCLEObjectValidator completeOCLEObjectValidator = new CompleteOCLEObjectValidator(validatePackage1, oclURI);
		EValidator.Registry.INSTANCE.put(validatePackage1, completeOCLEObjectValidator);
		try {
			EObject testInstance1 = eCreate(validatePackage1, "Level3");
			EObject testInstance2 = eCreate(validatePackage2, "Level3");
			String template = PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_;
			String objectLabel;
			//
			//	No errors
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
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING);
			checkValidationDiagnostics(testInstance2, Diagnostic.WARNING);
			//
			//	CompleteOCL errors all round
			//
			ThreadLocalExecutor.resetEnvironmentFactory();
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
			objectLabel = LabelUtil.getLabel(testInstance1);
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::V1", objectLabel),
				StringUtil.bind(template, "Level2a::V2a", objectLabel),
				StringUtil.bind(template, "Level2b::V2b", objectLabel),
				StringUtil.bind(template, "Level3::V3", objectLabel));
			checkValidationDiagnostics(testInstance2, Diagnostic.WARNING);
			//
			//	One CompleteOCl and one OCLinEcore
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
			objectLabel = LabelUtil.getLabel(testInstance1);
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING,
				StringUtil.bind(template,  "Level2a::L2a", objectLabel),
				StringUtil.bind(template,  "Level2a::V2a", objectLabel));
			objectLabel = LabelUtil.getLabel(testInstance2);
			checkValidationDiagnostics(testInstance2, Diagnostic.ERROR,
				StringUtil.bind(VIOLATED_TEMPLATE, "L2a", "Level3 ok", objectLabel));
		}
		finally {
			ocl0.dispose();
			ocl1.dispose();
			ocl2.dispose();
			EValidator.Registry.INSTANCE.remove(validatePackage1);
		}
	}

	public void testValidate_Validate_completeocl_loadresource() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
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
		assertValidationDiagnostics("Without Complete OCL", resource, getMessages(
			StringUtil.bind(VIOLATED_TEMPLATE, "SufficientCopies", "Library lib::Book b2"),
			StringUtil.bind(VIOLATED_TEMPLATE, "AtMostTwoLoans", "Library lib::Member m3"),
			StringUtil.bind(VIOLATED_TEMPLATE, "UniqueLoans", "Library lib::Member m3")));
		//
		CompleteOCLLoader helper = new CompleteOCLLoader(ocl.getEnvironmentFactory()) {
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				TestCase.fail(primaryMessage + "\n\t" + detailMessage);
				return false;
			}
		};
		assertTrue(helper.loadMetamodels());
		assertTrue(helper.loadDocument(testFile.getFileURI()));
		helper.installPackages();

		assertValidationDiagnostics("Without Complete OCL", resource, getMessages(//validationContext,
			StringUtil.bind(VIOLATED_TEMPLATE, "SufficientCopies", "Library lib::Book b2"),
			StringUtil.bind(VIOLATED_TEMPLATE, "AtMostTwoLoans", "Library lib::Member m3"),
			StringUtil.bind(VIOLATED_TEMPLATE, "UniqueLoans", "Library lib::Member m3"),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Book::ExactlyOneCopy", "Library lib::Book b2")));
		//		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
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
		CompleteOCLLoader helper = new CompleteOCLLoader(ocl.getEnvironmentFactory()) {
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				TestCase.fail(primaryMessage + "\n\t" + detailMessage);
				return false;
			}
		};
		assertTrue(helper.loadMetamodels());
		assertTrue(helper.loadDocument(testFile.getFileURI()));
		helper.installPackages();
		String objectLabel1 = LabelUtil.getLabel(uNamed);
		//		String objectLabel3 = ClassUtil.getLabel(uNamed.getOwnedAttribute("r", null).getLowerValue());
		//		String objectLabel4 = ClassUtil.getLabel(uNamed.getOwnedAttribute("s", null).getLowerValue());
		assertValidationDiagnostics("Without Complete OCL", resource, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Classifier::IsClassifierWrtLeaf", objectLabel1),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class::IsClassWrtLeaf", objectLabel1)/*,
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
		OCL ocl1 = createOCL();
		Resource ecoreResource = doLoadOCLinEcore(ocl1, getTestModelURI("models/oclinecore/Validate.oclinecore"));
		ocl1.getEnvironmentFactory().adapt(ecoreResource.getResourceSet());
		EPackage validatePackage = (EPackage) ecoreResource.getContents().get(0);
		EObject testInstance = eCreate(validatePackage, "Level3");
		eSet(testInstance, "ref", "ref");
		eSet(testInstance, "l1", "l1");
		eSet(testInstance, "l2a", "l2a");
		eSet(testInstance, "l2b", "l2b");
		eSet(testInstance, "l3", "l3");
		String objectLabel = LabelUtil.getLabel(testInstance);
		ThreadLocalExecutor.resetEnvironmentFactory();
		//
		//	Check EObjectValidator errors
		//
		EValidator.Registry.INSTANCE.put(validatePackage, EObjectValidator.INSTANCE);
		try {
			String template = EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic");
			checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
				StringUtil.bind(template,  "L1", objectLabel),
				StringUtil.bind(template,  "L2a", objectLabel),
				//BUG355184		ClassUtil.bind(template,  "L2b", objectLabel),
				StringUtil.bind(template,  "L3", objectLabel));
			//
			//	Check OCLinEcoreEObjectValidator warnings and distinct message
			//
			EValidator.Registry.INSTANCE.put(validatePackage, new OCLinEcoreEObjectValidator());
			template = PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_;
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::L1", objectLabel),
				StringUtil.bind(template, "Level2a::L2a", objectLabel),
				//BUG355184		ClassUtil.bind(template,  "L2b", objectLabel),
				StringUtil.bind(template, "Level3::L3", objectLabel));
			//
			//	No errors
			//
			eSet(testInstance, "ref", "ok");
			eSet(testInstance, "l1", "ok");
			eSet(testInstance, "l2a", "ok");
			eSet(testInstance, "l2b", "ok");
			eSet(testInstance, "l3", "ok");
			objectLabel = LabelUtil.getLabel(testInstance);
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING);
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
				StringUtil.bind(template, "Level1::L1", objectLabel));
		} finally {
			ocl1.dispose();
			EValidator.Registry.INSTANCE.remove(validatePackage);
		}
	}
}
