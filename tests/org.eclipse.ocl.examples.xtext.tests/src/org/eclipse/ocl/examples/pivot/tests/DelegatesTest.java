/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *  C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 * 	E.D.Willink - Bug 306079, 322159, 353171
 *  K.Hussey - Bug 331143
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.ocl.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.ocl.examples.extlibrary.Library;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextVersionUtil;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyFactory;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.util.CodegencompanyValidator;
import org.eclipse.ocl.examples.xtext.tests.company.Bug418716;
import org.eclipse.ocl.examples.xtext.tests.company.CompanyFactory;
import org.eclipse.ocl.examples.xtext.tests.company.CompanyPackage;
import org.eclipse.ocl.examples.xtext.tests.company.util.CompanyValidator;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.NoreflectioncompanyFactory;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.NoreflectioncompanyPackage;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.util.NoreflectioncompanyValidator;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.internal.delegate.DelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.DelegateEPackageAdapter;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.delegate.InvocationBehavior;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateException;
import org.eclipse.ocl.pivot.internal.delegate.OCLInvocationDelegate;
import org.eclipse.ocl.pivot.internal.delegate.OCLInvocationDelegateFactory;
import org.eclipse.ocl.pivot.internal.delegate.OCLQueryDelegateFactory;
import org.eclipse.ocl.pivot.internal.delegate.OCLSettingDelegate;
import org.eclipse.ocl.pivot.internal.delegate.OCLSettingDelegateFactory;
import org.eclipse.ocl.pivot.internal.delegate.OCLValidationDelegateFactory;
import org.eclipse.ocl.pivot.internal.delegate.SettingBehavior;
import org.eclipse.ocl.pivot.internal.delegate.ValidationDelegate;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.evaluation.OCLEvaluationVisitor;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.oclinecore.validation.OCLinEcoreEObjectValidator;
import org.junit.AfterClass;

import junit.framework.TestCase;

/**
 * Tests for the OCL delegate implementations.
 *
 * Beware these tests may fail as plugin tests if a CG project such as _OCL_UsageTests__testCodegenCompany
 * is open as a project in the development workspace.
 */
public class DelegatesTest extends PivotTestCaseWithAutoTearDown
{
	protected static final @NonNull String COMPANY_XMI = "/models/genmodel/Company.xmi";
	protected static final @NonNull String NO_REFLECTION_COMPANY_XMI = "/models/genmodel/NoReflectionCompany.xmi";
	protected static final @NonNull String MODEL_WITH_ERRORS_XMI = "/models/ecore/ModelWithErrors.xmi";
	protected static final @NonNull String MODEL_WITH_ERRORS_OCL = "/models/ecore/ModelWithErrors.ocl";

	public Resource testResource;
	public EPackage companyPackage;
	public EFactory companyFactory;
	public EClass companyClass;
	public EAttribute companyName;
	public EReference companyEmployees;
	public EAttribute companySize;
	public EClass employeeClass;
	public EAttribute employeeName;
	public EReference employeeManager;
	public EReference employeeDirectReports;
	public EReference employeeAllReports;
	public EOperation employeeReportsTo;
	public EEnum sizeKind;
	public Enumerator sizeSmall;
	public Enumerator sizeMedium;
	public Enumerator sizeLarge;
	public EObject acme;
	public Map<String, EObject> employees;
	public EClass badClassClass;
	public EReference companyDetritus;

	public boolean usedLocalRegistry;

	protected @NonNull OCLInternal configureMetamodelManagerForDelegate(@NonNull EPackage ePackage) {
		DelegateEPackageAdapter adapter = DelegateEPackageAdapter.getAdapter(ePackage);
		DelegateDomain delegateDomain = adapter.getDelegateDomain(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (delegateDomain == null) {
			delegateDomain = adapter.loadDelegateDomain(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		}
		EnvironmentFactory environmentFactory = ((OCLDelegateDomain)delegateDomain).getEnvironmentFactory();
		return OCLInternal.newInstance((EnvironmentFactoryInternal)environmentFactory);
	}

	protected @NonNull ResourceSet createResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		getProjectMap().initializeResourceSet(resourceSet);
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("xmi", new EcoreResourceFactoryImpl());
		extensionToFactoryMap.put("ecore", new EcoreResourceFactoryImpl());

		OCLDelegateDomain.lazyInitializeLocals(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT, true, new OCLDelegateDomain.FactoryFactory()
		{
			@Override
			public @NonNull OCLInvocationDelegateFactory createInvocationDelegateFactory(@NonNull String oclDelegateURI) {
				return new OCLInvocationDelegateFactory(oclDelegateURI)
				{
					@Override
					public EOperation.Internal.InvocationDelegate createInvocationDelegate(EOperation operation) {
						usedLocalRegistry = true;
						return super.createInvocationDelegate(operation);
					}
				};
			}

			@Override
			public @NonNull OCLQueryDelegateFactory createQueryDelegateFactory(@NonNull String oclDelegateURI) {
				return new OCLQueryDelegateFactory(oclDelegateURI)
				{
					@Override
					public QueryDelegate createQueryDelegate(EClassifier context, Map<String, EClassifier> parameters, String expression) {
						usedLocalRegistry = true;
						return super.createQueryDelegate(context, parameters, expression);
					}
				};
			}

			@Override
			public @NonNull OCLSettingDelegateFactory createSettingDelegateFactory(@NonNull String oclDelegateURI) {
				return new OCLSettingDelegateFactory(oclDelegateURI)
				{
					@Override
					public EStructuralFeature.Internal.SettingDelegate createSettingDelegate(EStructuralFeature structuralFeature) {
						usedLocalRegistry = true;
						return super.createSettingDelegate(structuralFeature);
					}
				};
			}

			@Override
			public @NonNull OCLValidationDelegateFactory createValidationDelegateFactory(@NonNull String oclDelegateURI) {
				return new OCLValidationDelegateFactory(oclDelegateURI)
				{
					@Override
					public ValidationDelegate createValidationDelegate(@NonNull EClassifier classifier) {
						usedLocalRegistry = true;
						return super.createValidationDelegate(classifier);
					}
				};
			}
		});
		//		EcorePlugin.ExtensionProcessor.process(null);
		//		resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));
		return resourceSet;
	}

	protected void initModel(@NonNull ResourceSet resourceSet, @NonNull String testModelName) {
		URI uri = getTestModelURI(testModelName);
		testResource = resourceSet.getResource(uri, true);

		acme = testResource.getContents().get(0);

		companyClass = acme.eClass();
		companyPackage = companyClass.getEPackage();
		companyFactory = companyPackage.getEFactoryInstance();

		companyName = (EAttribute) companyClass.getEStructuralFeature("name");
		companyEmployees = (EReference) companyClass
				.getEStructuralFeature("employees");
		companySize = (EAttribute) companyClass.getEStructuralFeature("size");

		employeeClass = companyEmployees.getEReferenceType();
		employeeName = (EAttribute) employeeClass.getEStructuralFeature("name");
		employeeManager = (EReference) employeeClass
				.getEStructuralFeature("manager");
		employeeDirectReports = (EReference) employeeClass
				.getEStructuralFeature("directReports");
		employeeAllReports = (EReference) employeeClass
				.getEStructuralFeature("allReports");
		employeeReportsTo = getOperation(employeeClass, "reportsTo");

		sizeKind = (EEnum) companySize.getEAttributeType();
		sizeSmall = sizeKind.getEEnumLiteral("small").getInstance();
		sizeMedium = sizeKind.getEEnumLiteral("medium").getInstance();
		sizeLarge = sizeKind.getEEnumLiteral("large").getInstance();

		employees = new java.util.HashMap<String, EObject>();
		//		MetamodelManagerResourceAdapter.getAdapter(companyPackage.eResource(), metamodelManager);
	}

	protected Resource initModelWithErrors(@NonNull ResourceSet resourceSet) {
		URI uri = getTestModelURI(MODEL_WITH_ERRORS_XMI);
		testResource = resourceSet.getResource(uri, true);
		acme = testResource.getContents().get(0);
		companyClass = acme.eClass();
		companyPackage = companyClass.getEPackage();
		companyFactory = companyPackage.getEFactoryInstance();
		badClassClass = (EClass) companyPackage.getEClassifier("BadClass");
		companyDetritus = (EReference) companyClass .getEStructuralFeature("detritus");
		return companyPackage.eResource();
	}

	@SuppressWarnings("null")
	protected void initModelWithErrorsAndOcl(@NonNull ResourceSet resourceSet) {
		TestUtil.doCompleteOCLSetup();
		Resource ecoreResource = initModelWithErrors(resourceSet);
		OCLInternal ocl = configureMetamodelManagerForDelegate(companyPackage);
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		environmentFactory.adapt(resourceSet);
		String message = PivotUtil.formatResourceDiagnostics(ecoreResource.getErrors(), "Model load", "\n\t");
		if (message != null)
			fail(message);
		Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
		Model pivotModel = ecore2as.getASModel();
		message = PivotUtil.formatResourceDiagnostics(pivotModel.eResource().getErrors(), "Pivot load", "\n\t");
		if (message != null)
			fail(message);
		URI oclURI = getTestModelURI(MODEL_WITH_ERRORS_OCL);
		CSResource xtextResource = (CSResource) resourceSet.getResource(oclURI, true);
		message = PivotUtil.formatResourceDiagnostics(xtextResource.getErrors(), "OCL load", "\n\t");
		if (message != null)
			fail(message);
		Resource asResource = xtextResource.getASResource();
		message = PivotUtil.formatResourceDiagnostics(asResource.getErrors(), "Pivot OCL load", "\n\t");
		if (message != null)
			fail(message);
		DelegateInstaller pivotInstaller = new DelegateInstaller(environmentFactory, null);
		for (org.eclipse.ocl.pivot.Package nestedPackage : pivotModel.getOwnedPackages()) {
			pivotInstaller.installDelegates(metamodelManager.getCompletePackage(nestedPackage));
		}
		ocl.dispose();
	}

	protected void initPackageRegistrations(@NonNull ResourceSet resourceSet) {
		Registry packageRegistry = resourceSet.getPackageRegistry();
		packageRegistry.put(CompanyPackage.eNS_URI, CompanyPackage.eINSTANCE);
		packageRegistry.put(NoreflectioncompanyPackage.eNS_URI, NoreflectioncompanyPackage.eINSTANCE);
		EValidator.Registry.INSTANCE.put(CompanyPackage.eINSTANCE, CompanyValidator.INSTANCE);
		EValidator.Registry.INSTANCE.put(NoreflectioncompanyPackage.eINSTANCE, NoreflectioncompanyValidator.INSTANCE);
	}

	protected void initCodeGeneratedPackageRegistrations(@NonNull ResourceSet resourceSet) {
		resourceSet.getPackageRegistry().put(CodegencompanyPackage.eNS_URI, CodegencompanyPackage.eINSTANCE);
		EValidator.Registry.INSTANCE.put(CodegencompanyPackage.eINSTANCE, CodegencompanyValidator.INSTANCE);
	}

	/*	protected void removePackageRegistrations() {
		resourceSet.getPackageRegistry().remove(CompanyPackage.eNS_URI);
		resourceSet.getPackageRegistry().remove(NoreflectioncompanyPackage.eNS_URI);
		resourceSet.getPackageRegistry().remove(CodegencompanyPackage.eNS_URI);
		EValidator.Registry.INSTANCE.remove(CompanyPackage.eNS_URI);
		EValidator.Registry.INSTANCE.remove(NoreflectioncompanyPackage.eNS_URI);
		EValidator.Registry.INSTANCE.remove(CodegencompanyPackage.eNS_URI);
	} */
	//
	// Test framework
	//
	@Override
	protected void setUp() throws Exception {
	//	TEST_START.setState(true);
	//	AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
		super.setUp();
		TestCaseAppender.INSTANCE.install();
		TestUtil.doEssentialOCLSetup();
		//
		usedLocalRegistry = false;
		/**
		 * Ensure that there is support for custom messages and severities.
		 */
		EValidator.Registry.INSTANCE.put(null, new OCLinEcoreEObjectValidator());
		EPackage.Registry.INSTANCE.remove(CompanyPackage.eNS_URI);						// Reference and nullify the side effect of the reference
		//		resourceSet.getPackageRegistry().remove(CompanyPackage.eNS_URI);				// In case previous test failed
		EPackage.Registry.INSTANCE.remove(NoreflectioncompanyPackage.eNS_URI);			// Reference and nullify the side effect of the reference
		//		resourceSet.getPackageRegistry().remove(NoreflectioncompanyPackage.eNS_URI);	// In case previous test failed
		//		PivotUtilInternal.debugPrintln("Done Setup");
	}

	@SuppressWarnings("null")
	@Override
	protected void tearDown() throws Exception {
		if (testResource != null) {
			testResource.unload();
		}
		EValidator.Registry.INSTANCE.remove(null);
		//		OCL.Internal.disposeGlobalEnvironmentFactory();
		if (EPackage.Registry.INSTANCE.getEFactory(CompanyPackage.eNS_URI) instanceof CompanyFactory) {
			DelegateEPackageAdapter adapter = DelegateEPackageAdapter.findAdapter(CompanyPackage.eINSTANCE);
			if (adapter != null) {
				adapter.unloadDelegates();
			}
		}
		if (EPackage.Registry.INSTANCE.getEFactory(NoreflectioncompanyPackage.eNS_URI) instanceof NoreflectioncompanyFactory) {
			DelegateEPackageAdapter adapter = DelegateEPackageAdapter.findAdapter(NoreflectioncompanyPackage.eINSTANCE);
			if (adapter != null) {
				adapter.unloadDelegates();
			}
		}
		if (EPackage.Registry.INSTANCE.getEFactory(CodegencompanyPackage.eNS_URI) instanceof CodegencompanyFactory) {
			DelegateEPackageAdapter adapter = DelegateEPackageAdapter.findAdapter(CodegencompanyPackage.eINSTANCE);
			if (adapter != null) {
				adapter.unloadDelegates();
			}
		}
		autoTearDown();
		super.tearDown();
	//	System.gc();
	//	System.runFinalization();
	}

	@AfterClass
	protected void tearDownClass() throws Exception {
		GlobalEnvironmentFactory.disposeInstance();
	}

	public void doTest_allInstances(@NonNull ResourceSet resourceSet, @NonNull String modelName) {
		initModel(resourceSet, modelName);
		Collection<EObject> amyAllReports = allReports(employee("Amy"));
		assertEquals(5, amyAllReports.size());
		assertTrue(amyAllReports.contains(employee("Bob")));
		assertTrue(amyAllReports.contains(employee("Jane")));
		assertTrue(amyAllReports.contains(employee("Fred")));
		assertTrue(amyAllReports.contains(employee("Norbert")));
		assertTrue(amyAllReports.contains(employee("Sally")));

		// change the set of all instances of Employee
		set(create(acme, companyEmployees, employeeClass, "Manuel"), employeeManager, employee("Bob"));
		ThreadLocalExecutor.reset();		// invalidate ModelManager caches
		amyAllReports = allReports(employee("Amy"));
		assertEquals(6, amyAllReports.size());
		assertTrue(amyAllReports.contains(employee("Manuel")));
	}

	public void test_changeableNonVolatileAttribute_418716() {
		Bug418716 m = CompanyFactory.eINSTANCE.createBug418716();
		assertEquals(0, m.getAttributeWithoutInitital());
		assertEquals(100, m.getAttributeWithInitital());
		m.setAttributeWithInitital(200);
		assertEquals(0, m.getAttributeWithoutInitital());
		assertEquals(200, m.getAttributeWithInitital());
	}

	public void doTest_constraintValidation(@NonNull ResourceSet resourceSet, @NonNull String modelName) {
		initModel(resourceSet, modelName);
		EObject employee = create(acme, companyEmployees, employeeClass, null);
		set(employee, employeeManager, employee("Bob"));
		validateConstraintWithSeverity("mustHaveName", Diagnostic.WARNING, employee, "Employee must have a name");
		set(employee, employeeName, "Joe");
		validateWithoutError(employee);

		validateWithoutError(acme);
	}

	public void doTest_crossReferences(@NonNull ResourceSet resourceSet, @NonNull String modelName) {
		initModel(resourceSet, modelName);
		EcoreUtil.CrossReferencer.find(testResource.getContents());
	}

	public void doTest_eAttributeDerivation(@NonNull ResourceSet resourceSet, @NonNull String modelName) {
		initModel(resourceSet, modelName);
		assertSame(sizeSmall, size(acme));

		// add a load of employees
		EList<EObject> emps = employees(acme);

		for (int i = 0; i < 60; i++) {
			emps.add(companyFactory.create(employeeClass));
		}

		assertSame(sizeMedium, size(acme));

		// and another bunch
		for (int i = 0; i < 1000; i++) {
			emps.add(companyFactory.create(employeeClass));
		}

		assertSame(sizeLarge, size(acme));
	}

	public void doTest_eReferenceDerivation(@NonNull ResourceSet resourceSet, @NonNull String modelName) {
		initModel(resourceSet, modelName);
		EList<EObject> amyReports = directReports(employee("Amy"));
		assertEquals(3, amyReports.size());
		assertTrue(amyReports.contains(employee("Bob")));
		assertTrue(amyReports.contains(employee("Jane")));
		assertTrue(amyReports.contains(employee("Fred")));

		EList<EObject> bobReports = directReports(employee("Bob"));
		assertEquals(2, bobReports.size());
		assertTrue(bobReports.contains(employee("Norbert")));
		assertTrue(bobReports.contains(employee("Sally")));

		EList<EObject> sallyReports = directReports(employee("Sally"));
		assertEquals(0, sallyReports.size());
	}

	public void doTest_invariantValidation(@NonNull ResourceSet resourceSet, @NonNull String modelName, boolean hasInvariants, int severity) {
		initModel(resourceSet, modelName);
		EObject joe = create(acme, companyEmployees, employeeClass, "Joe");
		if (hasInvariants) {
			validateInvariantWithSeverity("noManagerImpliesDirectReports", severity, joe);
		}
		else {
			validateConstraintWithSeverity("noManagerImpliesDirectReports", severity, joe, null);
		}

		set(employee("Amy"), employeeManager, joe);
		validateWithoutError(joe);
	}

	public void doTest_operationInvocation(@NonNull ResourceSet resourceSet, @NonNull String modelName) throws InvocationTargetException {
		initModel(resourceSet, modelName);
		EObject amy = employee("Amy");

		// allReports is implemented using reportsTo()
		Collection<EObject> amyAllReports = allReports(amy);
		assertEquals(5, amyAllReports.size());

		for (EObject next : amyAllReports) {
			assertTrue(this.<Boolean> invoke(next, employeeReportsTo, amy));
		}
	}

	@SuppressWarnings("null")
	public void doTest_queryExecution(@NonNull ResourceSet resourceSet, @NonNull String modelName) {
		initModel(resourceSet, modelName);
		OCLInternal ocl = configureMetamodelManagerForDelegate(companyPackage);
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		QueryDelegate.Factory factory = QueryDelegate.Factory.Registry.INSTANCE
				.getFactory(PivotConstants.OCL_DELEGATE_URI_PIVOT);

		String n = "n";
		String expression = "self.employees->select(employee | employee.manager <> null and employee.manager.name = n)";
		EObject amy = employee("Amy");
		Map<String, EClassifier> parameters = new HashMap<String, EClassifier>();
		parameters.put(n, EcorePackage.Literals.ESTRING);

		QueryDelegate delegate = factory.createQueryDelegate(companyClass, parameters, expression);
		executeWithException(delegate, amy, null,
			PivotMessagesInternal.WrongContextClassifier_ERROR_, getType(ocl, amy), getType(ocl, acme));

		executeWithException(delegate, acme, null,
			PivotMessagesInternal.MismatchedArgumentCount_ERROR_, 0, 1);
		Map<String, Object> badArguments = new HashMap<String, Object>();
		badArguments.put(n, amy);
		executeWithException(delegate, acme, badArguments,
			PivotMessagesInternal.MismatchedArgumentType_ERROR_, n, getType(ocl, amy), PivotUtilInternal.findTypeOf(metamodelManager, EcorePackage.Literals.ESTRING));

		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put(n, "Amy");

		Collection<?> amyReports = (Collection<?>) execute(delegate, acme, arguments);
		assertEquals(3, amyReports.size());
		assertTrue(amyReports.contains(employee("Bob")));
		assertTrue(amyReports.contains(employee("Jane")));
		assertTrue(amyReports.contains(employee("Fred")));

		executeWithException(delegate, employee("Bob"), null,
			PivotMessagesInternal.WrongContextClassifier_ERROR_, getType(ocl, amy), getType(ocl, acme));

		arguments = new HashMap<String, Object>();
		arguments.put(n, "Bob");

		Collection<?> bobReports = (Collection<?>) execute(delegate, acme, arguments);
		assertEquals(2, bobReports.size());
		assertTrue(bobReports.contains(employee("Norbert")));
		assertTrue(bobReports.contains(employee("Sally")));

		executeWithException(delegate, employee("Sally"), null,
			PivotMessagesInternal.WrongContextClassifier_ERROR_, getType(ocl, amy), getType(ocl, acme));

		arguments = new HashMap<String, Object>();
		arguments.put(n, "Sally");

		Collection<?> sallyReports = (Collection<?>) execute(delegate, acme, arguments);
		assertEquals(0, sallyReports.size());
		ocl.dispose();
	}

	@SuppressWarnings("null")
	public void doTest_queryExecutionWithExceptions(@NonNull ResourceSet resourceSet, @NonNull String modelName) throws InvocationTargetException {
		initModel(resourceSet, modelName);
		OCL ocl = configureMetamodelManagerForDelegate(companyPackage);
		QueryDelegate.Factory factory = QueryDelegate.Factory.Registry.INSTANCE
				.getFactory(PivotConstants.OCL_DELEGATE_URI_PIVOT);

		String okName = "ok";
		String badName = "xyzzy";
		EObject amy = employee("Amy");
		Map<String, Object> okBindings = new HashMap<String, Object>();
		okBindings.put(okName, Integer.valueOf(123));
		Map<String, EClassifier> variables = new HashMap<String, EClassifier>();
		variables.put(okName, EcorePackage.Literals.ESTRING);
		QueryDelegate delegate;
		//
		//	Syntax error in expression
		//
		delegate = factory.createQueryDelegate(companyClass, null, "n=");
		executeWithException2(delegate, amy, null, getErrorsInMessage(PivotConstantsInternal.QUERY_ROLE, "company::Company", "n=") +
			StringUtil.bind("1:2: no viable alternative following input ''{0}''", "="));
		//
		//	Undeclared variable
		//
		delegate = factory.createQueryDelegate(companyClass, variables, badName);
		executeWithException2(delegate, acme, null, getErrorsInMessage(PivotConstantsInternal.QUERY_ROLE, "company::Company", badName) +
			StringUtil.bind("1:1: " + PivotMessagesInternal.UnresolvedElement_ERROR_, "", badName));
		//
		//	Definition of undeclared variable
		//
		delegate = factory.createQueryDelegate(companyClass, variables, PivotConstants.SELF_NAME);
		Map<String, Object> bindings = new HashMap<String, Object>();
		bindings.put(okName, "xx");
		bindings.put(badName, Integer.valueOf(123));
		executeWithException(delegate, acme, bindings,
			PivotMessagesInternal.MismatchedArgumentCount_ERROR_, 2, 1);
		//
		//	Mis-definition of context
		//
		delegate = factory.createQueryDelegate(companyClass, variables, PivotConstants.SELF_NAME);
		delegate.prepare();
		executeWithException(delegate, amy, okBindings,
			PivotMessagesInternal.WrongContextClassifier_ERROR_, getType(ocl, amy), getType(ocl, acme));
		//
		//	Mis-definition of variable
		//
		delegate = factory.createQueryDelegate(companyClass, variables, PivotConstants.SELF_NAME);
		delegate.prepare();
		executeWithException(delegate, acme, okBindings,
			PivotMessagesInternal.MismatchedArgumentType_ERROR_, okName, "Integer", "String");
		ocl.dispose();
	}

	public void test_allInstances() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				doTest_allInstances(resourceSet, COMPANY_XMI);
				assertTrue(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_allInstances_registered() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				initPackageRegistrations(resourceSet);
				doTest_allInstances(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_allInstances_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_allInstances(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeDefinedWithDerivationAndInitial() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				Object actual = get(badClassInstance, (EAttribute)badClassClass.getEStructuralFeature("attributeDefinedWithDerivationAndInitial"));
				assertEquals(42, actual);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeDefinedWithInitial() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				Object actual = get(badClassInstance, (EAttribute)badClassClass.getEStructuralFeature("attributeDefinedWithInitial"));
				assertEquals(-42, actual);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeDefinedWithoutDerivation() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EStructuralFeature eStructuralFeature = getStructuralFeature(badClassClass, "attributeDefinedWithoutDerivation");
				Property property = metamodelManager.getASOfEcore(Property.class, eStructuralFeature);
				getWithException(badClassInstance, eStructuralFeature.getName(),
					StringUtil.bind(PivotMessagesInternal.MissingDerivationForSettingDelegate_ERROR_, property));
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeDefinedWithoutDerivationBody() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EStructuralFeature eStructuralFeature = getStructuralFeature(badClassClass, "attributeDefinedWithoutDerivationBody");
				Property property = metamodelManager.getASOfEcore(Property.class, eStructuralFeature);
				getWithException(badClassInstance, eStructuralFeature.getName(),
					StringUtil.bind(PivotMessagesInternal.MissingSpecificationBody_ERROR_, property, PivotConstantsInternal.INITIALIZER_ROLE));
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeEvaluatingToInvalid() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EStructuralFeature eStructuralFeature = getStructuralFeature(badClassClass, "attributeEvaluatingToInvalid");
				Property property = metamodelManager.getASOfEcore(Property.class, eStructuralFeature);
				getWithException(badClassInstance, eStructuralFeature.getName(),
					StringUtil.bind(PivotMessagesInternal.EvaluationResultIsInvalid_ERROR_, property));
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeEvaluatingToNull() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EStructuralFeature eStructuralFeature = badClassInstance.eClass().getEStructuralFeature("attributeEvaluatingToNull");
				assertEquals(null, get(badClassInstance, eStructuralFeature));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeEvaluatingToWrongType() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject testEObject = create(acme, companyDetritus, badClassClass, null);
				EStructuralFeature structuralFeature = getStructuralFeature(badClassClass, "attributeEvaluatingToWrongType");
				EStructuralFeature.Internal.SettingDelegate settingDelegate = ((EStructuralFeature.Internal)structuralFeature).getSettingDelegate();
				Property property = ((OCLSettingDelegate) settingDelegate).getProperty();
				String objectLabel = LabelUtil.getLabel(property);
				getWithException(testEObject, "attributeEvaluatingToWrongType",
					StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Property::CompatibleDefaultExpression", objectLabel));
				//			ClassUtil.bind(OCLMessages.InitOrDerConstraintConformance_ERROR_, "String", "attributeEvaluatingToWrongType", "Boolean"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeParsingToLexicalError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				getWithException(badClassInstance, "attributeParsingToLexicalError",
					getErrorsInMessage(PivotConstantsInternal.INITIALIZER_ROLE, "modelWithErrors::BadClass::attributeParsingToLexicalError", "gh##jk") +
					StringUtil.bind("1:3: missing EOF at ''{0}''", "#"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeParsingToSemanticError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				getWithException(badClassInstance, "attributeParsingToSemanticError",
					getErrorsInMessage(PivotConstantsInternal.INITIALIZER_ROLE, "modelWithErrors::BadClass::attributeParsingToSemanticError", "'5' and 6") +
					StringUtil.bind("1: " + PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "String", "and", "Integer"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_attributeParsingToSyntacticError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				String location = XtextVersionUtil.hasXtextSyntaxDiagnosticColumn() ? "1:9" : "1";
				getWithException(badClassInstance, "attributeParsingToSyntacticError",
					getErrorsInMessage(PivotConstantsInternal.INITIALIZER_ROLE, "modelWithErrors::BadClass::attributeParsingToSyntacticError", "invalid null") +
					StringUtil.bind(location + ": extraneous input ''{0}'' expecting EOF", "null"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	/**
	 * Ensures that {@link InvocationBehavior#getOperationBody(OCL, EOperation)}
	 * consistently returns <code>null</code> for stdlib operations that don't
	 * have a body defined at all instead of returning an <code>invalid</code> literal.
	 * @throws ParserException
	 *
	public void test_attributeNotDefinedInOCLRemainsNull() throws ParserException {
		helper.setContext(EcorePackage.eINSTANCE.getEClassifier());
		OCLExpression expr = (OCLExpression) helper.createQuery("self.name");
		assertTrue(expr instanceof PropertyCallExp);
		PropertyCallExp pce = (PropertyCallExp) expr;
		Property p = pce.getReferredProperty();
		OCLExpression body = SettingBehavior.INSTANCE.getFeatureBody((OCL) ocl, p);
		assertNull(body);
		// and again, now reading from cache
		OCLExpression bodyStillNull = SettingBehavior.INSTANCE.getFeatureBody((OCL) ocl, p);
		assertNull(bodyStillNull);
	} */

	public void test_constraintValidation() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				doTest_constraintValidation(resourceSet, COMPANY_XMI);
				assertTrue(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_constraintValidation_withoutReflection() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				doTest_constraintValidation(resourceSet, NO_REFLECTION_COMPANY_XMI);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_constraintValidation_registered() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				initPackageRegistrations(resourceSet);
				doTest_constraintValidation(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_constraintValidation_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				//		PivotTables.PACKAGE.getClass();
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_constraintValidation(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_crossReferences_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				PivotTables.PACKAGE.getClass();
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_crossReferences(resourceSet, COMPANY_XMI);	// Verify Bug 412690 comment 2
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_defaultIsPivot() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				assertEquals(OCLConstants.OCL_DELEGATE_URI_PIVOT, CommonOptions.DEFAULT_DELEGATION_MODE.getPreferredValue());
			}
		});
	}

	//	public void test_defaultIsPivot() {
	//		assertEquals(OCLDelegateDomain.OCL_DELEGATE_URI_PIVOT, CommonOptions.DEFAULT_DELEGATION_MODE.getPreferredValue());
	//	}

	public void test_eAttributeDerivation() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				doTest_eAttributeDerivation(resourceSet, COMPANY_XMI);
				unloadResourceSet(resourceSet);
			}
		});
	}

	/*	public void test_eAttributeDerivation_registered() {
		OCL ocl = OCL.newInstance();
		initPackageRegistrations(ocl);
		doTest_eAttributeDerivation(COMPANY_XMI);
		ocl.dispose();
	} */

	public void test_eReferenceDerivation() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				doTest_eReferenceDerivation(resourceSet, COMPANY_XMI);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_eReferenceDerivation_registered() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				initPackageRegistrations(resourceSet);
				doTest_eReferenceDerivation(resourceSet, COMPANY_XMI);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_eReferenceDerivation_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_eReferenceDerivation(resourceSet, COMPANY_XMI);
				unloadResourceSet(resourceSet);
			}
		});
	}

	/**
	 * Caches an operation AST in the annotation used by the {@link SettingBehavior} implementation
	 * and ensures that it's used by the delegate as well as the {@link OCLEvaluationVisitor}
	 * @throws ParserException
	 * @throws InvocationTargetException
	 *
	public void test_eReferenceDerivationUsedFromCache() throws ParserException, InvocationTargetException {
		initModel(COMPANY_XMI);
		EObject company = companyFactory.create(companyClass);
		EObject manager = companyFactory.create(employeeClass);
		manager.eSet(employeeClass.getEStructuralFeature("company"), company);
		EObject employee = companyFactory.create(employeeClass);
		employee.eSet(employeeClass.getEStructuralFeature("company"), company);
		employee.eSet(employeeClass.getEStructuralFeature("manager"), manager);
		OCL ocl = OCL.newInstance();
		Helper helper = ocl.createOCLHelper();
		helper.setContext(employeeClass);
		OCLExpression expr = helper.createQuery("self.directReports");
		assertTrue(((Collection<?>) ocl.evaluate(manager, expr)).contains(employee));
		EStructuralFeature directReportsRef = employeeClass.getEStructuralFeature("directReports");
		// Now cache a NullLiteralExp as the derivation expression for directReports:
		NullLiteralExp nullLiteralExp = PivotFactory.eINSTANCE.createNullLiteralExp();
		EAnnotation directReportsAnn = directReportsRef.getEAnnotation(OCLDelegateDomain.OCL_DELEGATE_URI);
		assertTrue(directReportsAnn.getDetails().containsKey(SettingBehavior.DERIVATION_CONSTRAINT_KEY));
		String derivationExpression =  directReportsAnn.getDetails().get(SettingBehavior.DERIVATION_CONSTRAINT_KEY);
		try {
			directReportsAnn.getDetails().remove(SettingBehavior.DERIVATION_CONSTRAINT_KEY);
			// ensure that the plugin cache doesn't have an expression cached:
			SettingBehavior.INSTANCE.cacheOCLExpression(directReportsRef, nullLiteralExp);
			assertNull(ocl.evaluate(manager, expr));
		} finally {
			directReportsAnn.getDetails().put(SettingBehavior.DERIVATION_CONSTRAINT_KEY, derivationExpression);
			SettingBehavior.INSTANCE.cacheOCLExpression(directReportsRef, null);
		}
		ocl.dispose();
	} */

	/*	public void test_invariantCacheBeingUsed() throws ParserException {
		 = OCL.newInstance();
		initPackageRegistrations(ocl);
		initModel(COMPANY_XMI);
		EAnnotation annotation = employeeClass.getEAnnotation(OCLDelegateDomain.OCL_DELEGATE_URI);

		DiagnosticChain diagnostics = new BasicDiagnostic();
		// first ensure that contents are padded up to where we need it:
		assertTrue("Expecting \"Amy\" to be a valid name",
			CompanyValidator.INSTANCE.validateEmployee_mustHaveName((Employee) employee("Amy"), diagnostics, context));
		final String constraintName = "mustHaveName";
		String mustHaveNameConstraint = annotation.getDetails().get(constraintName);
		Helper helper = OCL.newInstance().createOCLHelper();
		helper.setContext(employeeClass);
		OCLExpression query = helper.createQuery("false"); // a constraint always returning false
		try {
			annotation.getDetails().remove(constraintName);
			ValidationBehavior.INSTANCE.cacheOCLExpression(employeeClass,
				constraintName, query);
			assertFalse(
				"Expected the always-false cached constraint to be used",
				CompanyValidator.INSTANCE.validateEmployee_mustHaveName(
					(Employee) employee("Amy"), diagnostics, context));
		} finally {
			// restore annotation detail
			annotation.getDetails().put(constraintName, mustHaveNameConstraint);
		}
		ocl.dispose();
	} */

	/*	public void test_invariantCachingForFirst() {
		OCL ocl = OCL.newInstance();
		initPackageRegistrations(ocl);
		initModel(COMPANY_XMI);
		DiagnosticChain diagnostics = new BasicDiagnostic();
		ValidationBehavior.INSTANCE.cacheOCLExpression(employeeClass, "mustHaveName", null);
		CompanyValidator.INSTANCE.validateEmployee_mustHaveName((Employee) employee("Amy"), diagnostics, context);
		OCLExpression cached = ValidationBehavior.INSTANCE.getCachedOCLExpression(employeeClass, "mustHaveName");
		assertTrue("Expected to find compiled expression in cache",
			cached != null && !ValidationBehavior.isNoOCLDefinition(cached));
	} */

	/*	public void test_invariantCachingForSecond() {
		OCL ocl = OCL.newInstance();
		initPackageRegistrations(ocl);
		initModel(COMPANY_XMI);
		DiagnosticChain diagnostics = new BasicDiagnostic();
		ValidationBehavior.INSTANCE.cacheOCLExpression(employeeClass, "mustHaveNonEmptyName", null);
		CompanyValidator.INSTANCE.validateEmployee_mustHaveNonEmptyName((Employee) employee("Amy"), diagnostics, context);
		OCLExpression cached = ValidationBehavior.INSTANCE.getCachedOCLExpression(employeeClass, "mustHaveNonEmptyName");
		assertTrue("Expected to find compiled expression in cache",
			cached != null && !ValidationBehavior.isNoOCLDefinition(cached));
		ocl.dispose();
	} */

	public void test_invariantValidation() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				doTest_invariantValidation(resourceSet, COMPANY_XMI, false, Diagnostic.WARNING);
				assertTrue(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_invariantValidation_registered() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				initPackageRegistrations(resourceSet);
				doTest_invariantValidation(resourceSet, COMPANY_XMI, true, Diagnostic.ERROR);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_invariantValidation_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_invariantValidation(resourceSet, COMPANY_XMI, false, Diagnostic.WARNING);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_invariantValidation_withoutReflection() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				doTest_invariantValidation(resourceSet, NO_REFLECTION_COMPANY_XMI, false, Diagnostic.WARNING);
				unloadResourceSet(resourceSet);
			}
		});
	}
	/*	public void test_invariantValidation_withoutReflection_registered() {
		OCL ocl = OCL.newInstance();
		initPackageRegistrations(ocl);
		doTest_invariantValidation(NO_REFLECTION_COMPANY_XMI, true);
		ocl.dispose();
	} */

	public void test_operationDefinedWithoutBody() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EOperation eOperation = getOperation(badClassClass, "operationDefinedWithoutBody");
				Operation operation = metamodelManager.getASOfEcore(Operation.class, eOperation);
				invokeWithException(badClassInstance, eOperation.getName(),
					StringUtil.bind(PivotMessagesInternal.MissingSpecificationBody_ERROR_, NameUtil.qualifiedNameFor(operation), PivotConstantsInternal.BODY_ROLE));
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationDefinedWithoutBodyBody() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EOperation eOperation = getOperation(badClassClass, "operationDefinedWithoutBodyBody");
				Operation operation = metamodelManager.getASOfEcore(Operation.class, eOperation);
				invokeWithException(badClassInstance, eOperation.getName(),
					StringUtil.bind(PivotMessagesInternal.MissingSpecificationBody_ERROR_, NameUtil.qualifiedNameFor(operation), PivotConstantsInternal.BODY_ROLE));
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationEvaluatingToInvalid() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EOperation eOperation = getOperation(badClassClass, "operationEvaluatingToInvalid");
				Operation operation = metamodelManager.getASOfEcore(Operation.class, eOperation);
				invokeWithException(badClassInstance, eOperation.getName(),
					StringUtil.bind(PivotMessagesInternal.EvaluationResultIsInvalid_ERROR_, operation));
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationEvaluatingToNull() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				TestUtil.doEssentialOCLSetup();
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EOperation operation = getOperation(badClassInstance.eClass(), "operationEvaluatingToNull");
				assertEquals(null, invoke(badClassInstance, operation));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationEvaluatingToWrongType() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				EOperation eOperation = getOperation(badClassClass, "operationEvaluatingToWrongType");
				EOperation.Internal.InvocationDelegate invocationDelegate = ((EOperation.Internal)eOperation).getInvocationDelegate();
				Operation operation = ((OCLInvocationDelegate) invocationDelegate).getOperation();
				String objectLabel = LabelUtil.getLabel(operation);
				invokeWithException(badClassInstance, "operationEvaluatingToWrongType",
					StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Operation::CompatibleReturn", objectLabel));
				//			OCLMessages.BodyConditionConformance_ERROR_, "operationEvaluatingToWrongType", "Integer", "Boolean");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationInvocation() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				doTest_operationInvocation(resourceSet, COMPANY_XMI);
				assertTrue(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	/*	public void test_operationInvocation_registered() throws InvocationTargetException {
		OCL ocl = OCL.newInstance();
		initPackageRegistrations(ocl);
		doTest_operationInvocation(COMPANY_XMI);
		assertFalse(usedLocalRegistry);
		ocl.dispose();
	} */

	public void test_operationParsingToLexicalError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				String location = XtextVersionUtil.hasXtextSyntaxDiagnosticColumn() ? "1:1" : "1";
				invokeWithException(badClassInstance, "operationParsingToLexicalError",
					getErrorsInMessage(PivotConstantsInternal.BODY_ROLE, "modelWithErrors::BadClass::operationParsingToLexicalError", "@@") + StringUtil.bind(location + ": no viable alternative at input ''{0}''", "@"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationParsingToSemanticError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				invokeWithException(badClassInstance, "operationParsingToSemanticError",
					getErrorsInMessage(PivotConstantsInternal.BODY_ROLE, "modelWithErrors::BadClass::operationParsingToSemanticError", "self->at(1)") + StringUtil.bind("1:7: " + PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Set(modelWithErrors::BadClass)", "at", "1"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_operationParsingToSyntacticError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
				String location = XtextVersionUtil.hasXtextSyntaxDiagnosticColumn() ? "1:5" : "1";
				invokeWithException(badClassInstance, "operationParsingToSyntacticError",
					getErrorsInMessage(PivotConstantsInternal.BODY_ROLE, "modelWithErrors::BadClass::operationParsingToSyntacticError", "let in") + StringUtil.bind(location + ": no viable alternative at input ''{0}''", "in"));
				unloadResourceSet(resourceSet);
			}
		});
	}

	/**
	 * Ensures that {@link InvocationBehavior#getOperationBody(OCL, EOperation)}
	 * consistently returns <code>null</code> for stdlib operations that don't
	 * have a body defined at all instead of returning an <code>invalid</code> literal.
	 * @throws ParserException
	 */
	public void test_operationDefinedInStdlibBodyRemainsNull() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws ParserException {
				ResourceSet resourceSet = createResourceSet();
				OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
				MetamodelManager metamodelManager = ocl.getMetamodelManager();
				ExpressionInOCL expr = ocl.createQuery(null, "'abc'.oclAsType(String)");
				OperationCallExp oce = (OperationCallExp) expr.getOwnedBody();
				Operation o = oce.getReferredOperation();
				try {
					@SuppressWarnings({"unused", "null"})
					ExpressionInOCL body = InvocationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, o);
					fail("Expected to catch OCLDelegateException");
				}
				catch (OCLDelegateException e) {
				}
				// and again, now reading from cache
				try {
					@SuppressWarnings({"unused", "null"})
					ExpressionInOCL bodyStillNull = InvocationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, o);
					fail("Expected to catch OCLDelegateException");
				}
				catch (OCLDelegateException e) {
				}
				ocl.dispose();
				unloadResourceSet(resourceSet);
			}
		});
	}

	/**
	 * Caches an operation AST in the annotation used by the {@link InvocationBehavior} implementation
	 * and ensures that it's used by the delegate as well as the {@link OCLEvaluationVisitor}.
	 * Implicitly, the test ensures that no modification is applied to the original textual annotation,
	 * so that the annotation's contents are <em>not</em> used to cache the compiled AST because that
	 * may make some clients expecting the metamodel resources to remain unchanged angry.
	 *
	 * @throws ParserException
	 * @throws InvocationTargetException
	 *
	public void test_operationUsedFromCache() throws ParserException, InvocationTargetException {
		initModel(COMPANY_XMI);
		EObject manager = companyFactory.create(employeeClass);
		EObject employee = companyFactory.create(employeeClass);
		employee.eSet(employeeClass.getEStructuralFeature("manager"), manager);
		helper.setContext(employeeClass);
		OCLExpression expr = (OCLExpression) helper.createQuery("self.reportsTo(self.manager)");
		assertTrue((Boolean) ocl.evaluate(employee, expr)); // by the default impl, employee reports to manager
		EOperation reportsToOp = employeeClass.getEOperation(CompanyPackage.EMPLOYEE___REPORTS_TO__EMPLOYEE);
		// Now cache a BooleanLiteralExp with the "false" literal as the implementation for reportsTo:
		BooleanLiteralExp falseLiteralExp = EcoreFactory.eINSTANCE.createBooleanLiteralExp();
		falseLiteralExp.setBooleanSymbol(false);
		EAnnotation reportsToAnn = reportsToOp.getEAnnotation(OCLDelegateDomain.OCL_DELEGATE_URI);
		assertTrue(reportsToAnn.getDetails().containsKey(InvocationBehavior.BODY_CONSTRAINT_KEY));
		String body = reportsToAnn.getDetails().get(InvocationBehavior.BODY_CONSTRAINT_KEY);
		try {
			reportsToAnn.getDetails().remove(InvocationBehavior.BODY_CONSTRAINT_KEY);
			// ensure that the plugin cache doesn't have an expression cached:
			InvocationBehavior.INSTANCE.cacheOCLExpression(reportsToOp, falseLiteralExp);
			assertFalse((Boolean) ocl.evaluate(employee, expr));
		} finally {
			reportsToAnn.getDetails().put(InvocationBehavior.BODY_CONSTRAINT_KEY, body);
			InvocationBehavior.INSTANCE.cacheOCLExpression(reportsToOp, null);
		}
	} */

	/*	public void test_performanceOfCacheRetrieval() throws ParserException {
		initModel(COMPANY_XMI);
		EObject manager = companyFactory.create(employeeClass);
		EObject employee = companyFactory.create(employeeClass);
		employee.eSet(employeeClass.getEStructuralFeature("manager"), manager);
		OCL ocl = OCL.newInstance();
		Helper helper = ocl.createOCLHelper();
		helper.setContext(employeeClass);
		String expression = "self.reportsTo(self.manager)";
		OCLExpression expr = helper.createQuery(expression);
		final int TIMES = 1;
		final int REPEAT = 1;
		for (int r = 0; r < REPEAT; r++) {
			long start = System.currentTimeMillis();
			for (int i = 0; i < TIMES; i++) {
				ocl.evaluate(employee, expr);
			}
			long end = System.currentTimeMillis();
			System.out.println("Executing " + expression + " " + TIMES
				+ " times took " + (end - start) + "ms");
		}
		ocl.dispose();
	} */

	public void test_queryExecution() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				doTest_queryExecution(resourceSet, COMPANY_XMI);
				assertTrue(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_queryExecution_registered() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				initPackageRegistrations(resourceSet);
				doTest_queryExecution(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_queryExecution_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				GlobalEnvironmentFactory.disposeInstance();
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_queryExecution(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_queryExecutionWithExceptions() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				doTest_queryExecutionWithExceptions(resourceSet, COMPANY_XMI);
				assertTrue(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_queryExecutionWithExceptions_registered() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initPackageRegistrations(resourceSet);
				doTest_queryExecutionWithExceptions(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_queryExecutionWithExceptions_codeGenerated() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InvocationTargetException {
				ResourceSet resourceSet = createResourceSet();
				initCodeGeneratedPackageRegistrations(resourceSet);
				doTest_queryExecutionWithExceptions(resourceSet, COMPANY_XMI);
				assertFalse(usedLocalRegistry);
				unloadResourceSet(resourceSet);
			}
		});
	}

	/**
	 * Verify that query delegates work independently of other EAnnotation declared delegates.
	 * @throws Throwable
	 */
	public void test_queryExecution_Bug353171() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				QueryDelegate.Factory factory = QueryDelegate.Factory.Registry.INSTANCE.getFactory(PivotConstants.OCL_DELEGATE_URI_PIVOT);
				String n = "n";
				String expression = "self.name";
				Library library = EXTLibraryFactory.eINSTANCE.createLibrary();
				library.setName("test");
				Map<String, EClassifier> parameters = new HashMap<String, EClassifier>();
				parameters.put(n, EcorePackage.Literals.ESTRING);
				QueryDelegate delegate = factory.createQueryDelegate(EXTLibraryPackage.Literals.LIBRARY, parameters, expression);
				Map<String, Object> bindings = new HashMap<String, Object>();
				bindings.put(n, "test");
				Object result = execute(delegate, library, bindings);
				assertEquals(result, "test");
			}
		});
	}

	public void test_tutorialValidationMessage() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws InterruptedException {
				GlobalEnvironmentFactory.disposeInstance();
				validateTutorial("models/documentation/Tutorial1.ecore", "There are 3 loans for the 2 copies of b2");
				ThreadLocalExecutor.resetEnvironmentFactory();
				GlobalEnvironmentFactory.disposeInstance();
				validateTutorial("models/documentation/Tutorial2.ecore", "There are 3 loans for the 2 copies of ''b2''");		// Doubled quotes for NLS.bind
				ThreadLocalExecutor.resetEnvironmentFactory();
				GlobalEnvironmentFactory.disposeInstance();
				validateTutorial("models/documentation/Tutorial1.ecore", "There are 3 loans for the 2 copies of b2");
				ThreadLocalExecutor.resetEnvironmentFactory();
				GlobalEnvironmentFactory.disposeInstance();
			}
		});
	}

	/**
	 * EObjectValidator  .validateDelegatedConstraints just skips over missing constraints.
	 *
	public void test_validationOfMissingConstraint() {
		OCL ocl = OCL.newInstance();
		initModelWithErrors(ocl.getResourceSet());
		EObject badClassInstance = create(acme, companyDetritus, badClassClass, null);
		validateConstraintWithError("MissingConstraint", badClassInstance);
		ocl.dispose();
	} */

	/**
	 * EObjectValidator  .validateDelegatedConstraints just skips over null bodies.
	 *
	public void test_validationOfMissingConstraintBody() {
		OCL ocl = OCL.newInstance();
		initModelWithErrors(ocl.getResourceSet());
		EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("MissingConstraintBody"), null);
		validateConstraintWithError("MissingConstraint", badClassInstance);
		ocl.dispose();
	} */

	public void test_validationEvaluatingToInvalid() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationEvaluatingToInvalid"), null);
				validateWithDelegationSeverity("evaluatingToInvalid", Diagnostic.ERROR, badClassInstance, null,
					EvaluationException.class, PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, "ValidationEvaluatingToInvalid", "evaluatingToInvalid", LabelUtil.getLabel(badClassInstance), "invalid");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationEvaluatingToNull() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationEvaluatingToNull"), null);
				validateWithDelegationSeverity("evaluatingToNull", Diagnostic.ERROR, badClassInstance, null,
					EvaluationException.class, PivotMessagesInternal.ValidationResultIsNull_ERROR_, badClassInstance.eClass().getName(), "evaluatingToNull", LabelUtil.getLabel(badClassInstance));
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationEvaluatingToWrongType() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationEvaluatingToWrongType"), null);
				validateWithDelegationSeverity("evaluatingToWrongType", Diagnostic.ERROR, badClassInstance, null,
					EvaluationException.class, PivotMessagesInternal.ValidationConstraintIsNotBooleanType_ERROR_, "ValidationEvaluatingToWrongType", "evaluatingToWrongType", "OclInvalid");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationParsingToLexicalError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				String location = XtextVersionUtil.hasXtextSyntaxDiagnosticColumn() ? "1:1" : "1";
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationParsingToLexicalError"), null);
				validateWithDelegationSeverity("modelWithErrors::ValidationParsingToLexicalError::parsingToLexicalError", Diagnostic.ERROR, badClassInstance, "'part",
					SemanticException.class, location + ": Invalid token {0}", "'part");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationParsingToSemanticError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationParsingToSemanticError"), null);
				validateWithDelegationSeverity("modelWithErrors::ValidationParsingToSemanticError::parsingToSemanticError", Diagnostic.ERROR, badClassInstance, "not '5'",
					SemanticException.class, "1: " + PivotMessagesInternal.UnresolvedOperation_ERROR_, "String", "not");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationParsingToSyntacticError() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationParsingToSyntacticError"), null);
				String location = XtextVersionUtil.hasXtextSyntaxDiagnosticColumn() ? "1:1" : "1";
				validateWithDelegationSeverity("modelWithErrors::ValidationParsingToSyntacticError::parsingToSyntacticError", Diagnostic.ERROR, badClassInstance, "else",
					SemanticException.class, location + ": no viable alternative at input ''{0}''", "else");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationWithMessage() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrors(resourceSet);
				EObject badClassInstance = create(acme, companyDetritus, (EClass) companyPackage.getEClassifier("ValidationWithMessage"), null);
				validateWithSeverity("ValidationWithMessage", Diagnostic.WARNING, badClassInstance,
						"custom message ");
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void test_validationWithCompleteOCL() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() {
				ResourceSet resourceSet = createResourceSet();
				initModelWithErrorsAndOcl(resourceSet);
				EClass eClassifier = (EClass) companyPackage.getEClassifier("Detritus");
				EObject badClassInstance = create(acme, companyDetritus, eClassifier, null);
				validateWithSeverity("CompleteOCLInvariant", Diagnostic.WARNING, badClassInstance,
					"Failure on " + eClassifier.getName());
				unloadResourceSet(resourceSet);
			}
		});
	}

	public void testDelegates_Import_476968() throws Throwable {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {			// No test files standalone, this is a standalone test so just bypass it.
			doTestRunnable(new TestRunnable() {
				@Override
				public void runWithThrowable() {
					/*Global*/EnvironmentFactory environmentFactory = PivotUtilInternal.getEnvironmentFactory(null); //GlobalEnvironmentFactory.getInstance();
					OCL ocl = environmentFactory.createOCL();
					//
					//	Projects on classpath should be accessible as platform:/plugin or platform:/project
					//
					URI uri1 = URI.createPlatformPluginURI("org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ecore", true);
					Resource resource1 = ocl.getResourceSet().getResource(uri1, true);
					assertNotNull(resource1);
					URI uri2 = URI.createPlatformResourceURI("org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ecore", true);
					Resource resource2 = ocl.getResourceSet().getResource(uri2, true);
					assertNotNull(resource2);
					//
					//	Projects not on classpath should not be accessible as platform:/plugin or platform:/project
					//
					try {
						URI uri8 = URI.createPlatformPluginURI("org.eclipse.ocl.examples.project.oclinecoretutorial/models/documentation/Tutorial.ecore", true);
						ocl.getResourceSet().getResource(uri8, true);
						TestCase.fail("Should have thrown a MalformedURLException");	// unknown protocol: platform
					}
					catch (WrappedException e) {
						assertTrue(e.getCause() instanceof MalformedURLException);
					}
					try {
						URI uri9 = URI.createPlatformResourceURI("org.eclipse.ocl.examples.project.oclinecoretutorial/models/documentation/Tutorial.ecore", true);
						ocl.getResourceSet().getResource(uri9, true);
						TestCase.fail("Should have thrown an IOException");				// The path '/org.eclipse.ocl.examples.project.oclinecoretutorial/models/documentation/Tutorial.ecore' is unmapped
					}
					catch (WrappedException e) {
						assertTrue(e.getCause() instanceof IOException);
					}
				}
			});
		}
	}

	public void validateTutorial(@NonNull String ecoreURI, @NonNull String message) {
		ResourceSet resourceSet = createResourceSet();
		OCL ocl = OCL.newInstance(getProjectMap(), resourceSet);
		try {
			ocl.getEnvironmentFactory().adapt(resourceSet);
			URI xmiURI = getTestModelURI("models/documentation/Tutorial.xmi");
			Resource ecoreResource = resourceSet.getResource(getTestModelURI(ecoreURI), true);
			EPackage ePackage = (EPackage) ecoreResource.getContents().get(0);
			resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
			Resource xmiResource = resourceSet.getResource(xmiURI, true);
			EObject rootContent = xmiResource.getContents().get(0);
			EObject book = null;
			for (EObject eObject : rootContent.eContents()) {
				EClass eClass = eObject.eClass();
				if ("Book".equals(eClass.getName())) {
					for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
						if ("name".equals(eFeature.getName())) {
							String name = (String) eObject.eGet(eFeature);
							if ("b2".equals(name)) {
								book = eObject;
								break;
							}
						}
					}
				}
			}
			validateWithSeverity("ValidationWithMessage", Diagnostic.WARNING, book, message);
			unloadResourceSet(resourceSet);
		} finally {
			ocl.dispose();
			unloadResourceSet(resourceSet);
		}
	}

	void add(EObject owner, EStructuralFeature feature, Object value) {
		this.<EList<Object>> get(owner, feature).add(value);
	}

	Collection<EObject> allReports(EObject employee) {
		Collection<EObject> collection = get(employee, employeeAllReports);
		assertTrue(collection instanceof InternalEList<?>);				// Check EMF internal API (fixing Bug 412690)
		assertTrue(collection instanceof EStructuralFeature.Setting);	// Check EMF internal API (fixing Bug 412690)
		return collection;
	}

	EObject create(EObject owner, EReference containment, EClass type,
			String name) {
		EObject result = companyFactory.create(type);

		if (containment.isMany()) {
			add(owner, containment, result);
		} else {
			set(owner, containment, result);
		}

		if (name != null) {
			set(result, type.getEStructuralFeature("name"), name);
		}

		return result;
	}

	EList<EObject> directReports(EObject employee) {
		return get(employee, employeeDirectReports);
	}

	EObject employee(String name) {
		EObject result = employees.get(name);

		if (result == null) {
			EList<EObject> emps = get(acme, companyEmployees);

			for (EObject next : emps) {

				if (name.equals(name(next))) {
					result = next;
					employees.put(name, result);
					break;
				}
			}
		}

		return result;
	}

	EList<EObject> employees(EObject company) {
		return get(company, companyEmployees);
	}

	@SuppressWarnings("unchecked")
	<T> T get(EObject owner, EStructuralFeature feature) {
		Object result = owner.eGet(feature);
		return (T) result;
	}

	protected String getErrorsInMessage(@NonNull String role, String qualifiedName, String source) {
		return source != null ? (StringUtil.bind(PivotMessagesInternal.ValidationConstraintIsInvalid_ERROR_, role, qualifiedName, source) + "\n") : "";
	}

	public EOperation getOperation(EClass eClass, String name) {
		for (EOperation eOperation : eClass.getEOperations()) {
			if (name.equals(eOperation.getName())) {
				return eOperation;
			}
		}
		fail("Expected to find operation: " + name);
		return null;
	}

	public EStructuralFeature getStructuralFeature(EClass eClass, String name) {
		for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
			if (name.equals(eStructuralFeature.getName())) {
				return eStructuralFeature;
			}
		}
		fail("Expected to find structural feature: " + name);
		return null;
	}

	protected org.eclipse.ocl.pivot.Class getType(@NonNull OCL ocl, EObject eObject) {
		return ocl.getIdResolver().getStaticTypeOfValue(null, eObject);
	}

	public void getWithException(EObject eObject, String featureName, String expectedMessage) {
		EClass eClass = eObject.eClass();
		EAttribute eAttribute = (EAttribute) eClass.getEStructuralFeature(featureName);
		try {
			@SuppressWarnings("unused")
			Object object = get(eObject, eAttribute);
			fail("Expected to catch OCLDelegateException: " + expectedMessage);
		} catch (OCLDelegateException e) {
			assertEquals("OCLDelegateException: ", expectedMessage, e.getCause().getLocalizedMessage());
		}
	}

	@SuppressWarnings("unchecked")
	<T> T invoke(EObject target, EOperation operation, Object... arguments) throws InvocationTargetException {
		//		try {
		return (T) target.eInvoke(operation, (arguments.length == 0)
			? ECollections.<Object> emptyEList()
				: new BasicEList.UnmodifiableEList<Object>(arguments.length,
						arguments));
		//		} catch (InvocationTargetException ite) {
		//			fail("Failed to invoke operation: " + ite.getLocalizedMessage());
		//			return null;
		//		}
	}

	public void invokeWithException(EObject eObject, String name, String expectedMessage) throws InvocationTargetException {
		EClass eClass = eObject.eClass();
		for (EOperation eOperation : eClass.getEOperations()) {
			if (name.equals(eOperation.getName())) {
				try {
					@SuppressWarnings("unused")
					Object object = invoke(eObject, eOperation);
					fail("Expected to catch InvocationTargetException: " + expectedMessage);
				} catch (OCLDelegateException e) {
					Throwable cause = e.getCause();
					assertEquals("OCLDelegateException: ", expectedMessage, cause.getLocalizedMessage());
					return;
				}
			}
		}
		fail("Expected to find: " + name);
	}

	Object execute(QueryDelegate delegate, Object target,
			Map<String, Object> bindings) {
		try {
			return delegate.execute(target, bindings);
		} catch (InvocationTargetException ite) {
			fail("Failed to execute query: " + ite.getCause().getLocalizedMessage());
			return null;
		}
	}

	public void executeWithException(QueryDelegate delegate, Object target,
			Map<String, Object> bindings, String messageTemplate,
			Object... messageBindings) {
		String expectedMessage = StringUtil.bind(messageTemplate, messageBindings);
		try {
			@SuppressWarnings("unused")
			Object object = delegate.execute(target, bindings);
			fail("Expected to catch InvocationTargetException: " + expectedMessage);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			assertEquals(cause.getClass().getSimpleName() + ": ", expectedMessage, cause.getLocalizedMessage());
		}
	}

	public void executeWithException2(QueryDelegate delegate, Object target, Map<String, Object> bindings, String expectedMessage) {
		try {
			@SuppressWarnings("unused")
			Object object = delegate.execute(target, bindings);
			fail("Expected to catch InvocationTargetException: " + expectedMessage);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			assertEquals(cause.getClass().getSimpleName() + ": ", expectedMessage, cause.getLocalizedMessage());
		}
	}

	EObject manager(EObject employee) {
		return get(employee, employeeManager);
	}

	String name(EObject employeeOrCompany) {
		EAttribute name = employeeClass.isInstance(employeeOrCompany)
				? employeeName
					: companyName;

		return get(employeeOrCompany, name);
	}

	void set(EObject owner, EStructuralFeature feature, Object value) {
		owner.eSet(feature, value);
	}

	Enumerator size(EObject company) {
		return get(company, companySize);
	}

	protected void validateWithoutError(EObject eObject) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic validation = Diagnostician.INSTANCE.validate(eObject, validationContext);
		if (validation.getSeverity() != Diagnostic.OK) {
			List<Diagnostic> diagnostics = validation.getChildren();
			if (!diagnostics.isEmpty()) {
				StringBuilder s = null;
				for (Diagnostic diagnostic : diagnostics) {
					if (s == null) {
						s = new StringBuilder();
					}
					else {
						s.append("\n");
					}
					s.append(diagnostic.getMessage());
				}
				assert s != null;
				String string = s.toString();
				fail(string);
			}
		}
		assertEquals("Validation severity:", Diagnostic.OK, validation.getSeverity());
		List<Diagnostic> diagnostics = validation.getChildren();
		assertEquals("Validation child count:", 0, diagnostics.size());
	}

	protected void validateConstraintWithSeverity(String constraintName, int severity, EObject eObject, String message) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic validation = Diagnostician.INSTANCE.validate(eObject, validationContext);
		List<Diagnostic> diagnostics = validation.getChildren();
		assertEquals("Validation of '" + constraintName + "' child count:", 1, diagnostics.size());
		Diagnostic diagnostic = diagnostics.get(0);
		assertEquals("Validation of '" + constraintName + "' data count:", 1, diagnostic.getData().size());
		assertEquals("Validation of '" + constraintName + "' data object:", eObject, diagnostic.getData().get(0));
		Object objectLabel = NameUtil.qualifiedNameFor(eObject);
		//		Object objectLabel = ClassUtil.getLabel(eObject);
		String message2 = message != null ? message : StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_,
			eObject.eClass().getName() + "::" + constraintName, objectLabel);
		assertEquals("Validation of '" + constraintName + "' message:", message2, diagnostic.getMessage());
		assertEquals("Validation of '" + constraintName + "' severity:", severity, validation.getSeverity());
	}

	protected void validateInvariantWithSeverity(String constraintName, int severity, EObject eObject) {
		validateWithSeverity(constraintName, severity, eObject, EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic"), constraintName, LabelUtil.getLabel(eObject));
	}

	protected void validateWithSeverity(String constraintName, int severity, EObject eObject, String messageTemplate, Object... bindings) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic validation = Diagnostician.INSTANCE.validate(eObject, validationContext);
		List<Diagnostic> diagnostics = validation.getChildren();
		assertEquals("Validation of '" + constraintName + "' child count:", 1, diagnostics.size());
		Diagnostic diagnostic = diagnostics.get(0);
		assertEquals("Validation of '" + constraintName + "' data count:", 1, diagnostic.getData().size());
		assertEquals("Validation of '" + constraintName + "' data object:", eObject, diagnostic.getData().get(0));
		String message = StringUtil.bind(messageTemplate, bindings);
		assertEquals("Validation of '" + constraintName + "' message:", message, diagnostic.getMessage());
		assertEquals("Validation of '" + constraintName + "' severity:", severity, validation.getSeverity());
	}

	protected void validateWithDelegationSeverity(String constraintName, int severity, EObject eObject, String source, Class<? extends Exception> exceptionClass, String messageTemplate, Object... bindings) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic validation = Diagnostician.INSTANCE.validate(eObject, validationContext);
		assertEquals("Validation of '" + constraintName + "' severity:", severity, validation.getSeverity());
		List<Diagnostic> diagnostics = validation.getChildren();
		assertEquals("Validation of '" + constraintName + "' child count:", 1, diagnostics.size());
		Diagnostic diagnostic = diagnostics.get(0);
		List<?> data = diagnostic.getData();
		int size = data.size();
		if (size == 2)	{				// EMF 2.10.0M3 and later
			Object data1 = data.get(1);
			assert data1 != null;
			assertEquals("Validation of '" + constraintName + "' exception:", exceptionClass, data1.getClass());
		}
		else if (size != 1) {			// EMF 2.10.0M2 and earlier
			fail("Validation of '" + constraintName + "' child count: " + size);
		}
		String message = getErrorsInMessage(PivotConstantsInternal.INVARIANT_ROLE, constraintName, source);
		message += StringUtil.bind(messageTemplate, bindings);
		assertEquals("Validation of '" + constraintName + "' data object:", eObject, data.get(0));
		assertEquals("Validation of '" + constraintName + "' message:", message, diagnostic.getMessage());
	}
}
