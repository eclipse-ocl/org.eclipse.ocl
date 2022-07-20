/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.eclipse.uml2.uml.util.UMLUtil.UML2EcoreConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for Name access.
 */
@RunWith(value = Parameterized.class)
public class EvaluateUMLTest4 extends PivotTestSuite
{
	public static class MyOCL extends TestOCL
	{
		EPackage statefulEPackage;
		EFactory statefulEFactory;
		EClass c1Class;

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name, @Nullable ResourceSet externalResourceSet) {
			super(testFileSystem, testPackageName, name, useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, externalResourceSet);
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			Package asMetamodel = metamodelManager.getASmetamodel();
			if (asMetamodel != null) {
				metamodelManager.addGlobalNamespace(PivotConstants.OCL_NAME, asMetamodel);
			}
		}

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name, @NonNull ProjectManager projectManager, @Nullable ResourceSet externalResourceSet) {
			super(testFileSystem, testPackageName, name, projectManager, externalResourceSet);
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			Package asMetamodel = metamodelManager.getASmetamodel();
			if (asMetamodel != null) {
				metamodelManager.addGlobalNamespace(PivotConstants.OCL_NAME, asMetamodel);
			}
		}

		@SuppressWarnings("null")
		protected Resource getPivotFromUML(@NonNull MetamodelManagerInternal metamodelManager, Resource umlResource, @NonNull String @Nullable [] asValidationMessages) throws ParserException {
			//			String problem = UML2AS.initialize(metamodelManager.getExternalResourceSet());
			//			assertNull(problem);
			UML2AS uml2as = UML2AS.getAdapter(umlResource, metamodelManager.getEnvironmentFactory());
			Model pivotModel = uml2as.getASModel();
			Resource asResource = pivotModel.eResource();
			assertNoResourceErrors("Normalisation failed", asResource);
			assertValidationDiagnostics("Normalisation invalid", asResource, asValidationMessages);
			return asResource;
		}

		protected Resource initStateMachinePackage(URI uri, @NonNull String @Nullable [] asValidationMessages) throws ParserException {
			UMLStandaloneSetup.init();
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			ResourceSet resourceSet2 = getResourceSet();
			assert resourceSet2 != null;
			UML2AS.initialize(resourceSet2);
			Resource umlResource = resourceSet2.getResource(uri, true);
			List<EObject> contents = umlResource.getContents();
			Map<String, String> options = new HashMap<String, String>();
			options.put(UML2EcoreConverter.OPTION__ECORE_TAGGED_VALUES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__REDEFINING_OPERATIONS, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__REDEFINING_PROPERTIES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__SUBSETTING_PROPERTIES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__UNION_PROPERTIES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__DERIVED_FEATURES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATIONS, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATION_INHERITANCE, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURE_INHERITANCE, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__SUPER_CLASS_ORDER, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__ANNOTATION_DETAILS, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__INVARIANT_CONSTRAINTS, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__OPERATION_BODIES, UMLUtil.OPTION__PROCESS);
			options.put(UML2EcoreConverter.OPTION__COMMENTS,  UMLUtil.OPTION__PROCESS);
			UML2EcoreConverter uml2EcoreConverter = new UML2EcoreConverter();
			Collection<? extends EObject> ecoreContents = uml2EcoreConverter.convert(contents, options, null, null);
			Resource ecoreResource = resourceSet2.createResource(URI.createURI("StateMachines.ecore"));
			for (EObject eObject : ecoreContents) {
				assert eObject != null;
				ecoreResource.getContents().add(eObject);
			}
			statefulEPackage = (EPackage) ecoreResource.getContents().get(0);
			statefulEFactory = statefulEPackage.getEFactoryInstance();
			c1Class = (EClass) statefulEPackage.getEClassifier("C1");
			Resource asResource = getPivotFromUML(metamodelManager, umlResource, asValidationMessages);
			return asResource;
		}
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false} /*, {true}*/};
		return Arrays.asList(data);
	}

	public EvaluateUMLTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull MyOCL createOCL() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName(), null);
	}

	protected @NonNull MyOCL createOCLWithProjectMap() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName(), getProjectMap(), null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateUML";
	}

	@BeforeClass public static void resetCounter() throws Exception {
		PivotTestSuite.resetCounter();
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests construction of a type instance with property values
	 * @throws ParserException
	 */
	@Test public void test_oclIsInState() throws InvocationTargetException, ParserException {
		UMLStandaloneSetup.init();
		MyOCL ocl = createOCLWithProjectMap();
		ocl.initStateMachinePackage(getTestModelURI("models/uml/StateMachines.uml"),
			new @NonNull String[] {
			"The 'Feature::TypeIsNotNull' constraint is violated for 'Model::C1::o1() : «null»[1]'",
			"The 'Feature::TypeIsNotNull' constraint is violated for 'Model::C2::o2() : «null»[1]'"
		});
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		EObject context = ocl.statefulEFactory.create(ocl.c1Class);
		org.eclipse.ocl.pivot.Class contextType = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Class.class, ocl.c1Class);
		assert contextType != null;
		ocl.assertSemanticErrorQuery(contextType, "self.oclIsInState(S2b)", PivotMessagesInternal.UnresolvedElement_ERROR_, "Model::C1", "S2b");
		ocl.assertQueryInvalid(context, "self.oclIsInState(S1a)", StringUtil.bind(PivotMessagesInternal.FailedToEvaluate_ERROR_, "OclAny::oclIsInState(OclState[?]) : Boolean[1]", "C1", "self.oclIsInState(S1a)"), UnsupportedOperationException.class);
		ocl.dispose();
	}

	public EObject doLoadUML(@NonNull OCL ocl, @NonNull URI uri, String fragment) throws IOException {
		URI umlURI = uri.appendFragment(fragment);
		return ocl.getResourceSet().getEObject(umlURI, true);
	}

	/**
	 * Tests construction of a type instance with property values
	 * @throws ParserException
	 */
	@Test public void test_stereotypes_Bug431638() throws Exception {
		UMLStandaloneSetup.init();
		MyOCL ocl = createOCLWithProjectMap();
		//		UML2AS.ADD_ELEMENT_EXTENSION.setState(true);
		//		UML2AS.ADD_IMPORTED_RESOURCE.setState(true);
		//		UML2AS.ADD_PROFILE_APPLICATION.setState(true);
		//		UML2AS.CONVERT_RESOURCE.setState(true);
		//		AbstractTypeServer.ADD_BASE_PROPERTY.setState(true);
		//		AbstractTypeServer.ADD_EXTENSION_PROPERTY.setState(true);
		IdResolver idResolver = ocl.getIdResolver();
		EObject context = doLoadUML(ocl, getTestModelURI("models/uml/Bug431638.uml"), "Bug431638Model.Class1.Attribute1");
		assertNotNull(context);
		org.eclipse.ocl.pivot.Class contextType = idResolver.getStaticTypeOfValue(null, context);
		org.eclipse.ocl.pivot.Package contextPackage = contextType.getOwningPackage();
		//		assertEquals(XMI2UMLResource.UML_METAMODEL_NS_URI, contextPackage.getNsURI());
		//		assertEquals(IdManager.METAMODEL, contextPackage.getPackageId());
		assertEquals(PivotConstants.UML_METAMODEL_NAME, contextPackage.getPackageId().getDisplayName());
		ocl.assertValidQuery(contextType, "self.extension_vStereotype1");
		ocl.assertSemanticErrorQuery(contextType, "self.extension_Stereotype1", PivotMessagesInternal.UnresolvedProperty_ERROR_, "UML::Property", "extension_Stereotype1");
		ocl.assertValidQuery(contextType, "self.extension_vStereotype1.base_NamedElement");
		ocl.assertSemanticErrorQuery(contextType, "self.extension_vStereotype1.base_Class", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Bug431638Profile::vStereotype1", "base_Class");
		ocl.assertSemanticErrorQuery(contextType, "self.extension_vStereotype1.string", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Bug431638Profile::vStereotype1", "string");
		ocl.assertValidQuery(contextType, "self.extension_vStereotype1.oclAsType(Bug431638Profile::Stereotype1).string");

		//OK		ocl.assertQueryEquals(context, contextType, "self.oclType()");
		//OK		ocl.assertQueryEquals(context, "Property", "self.oclType().name");
		ocl.assertQueryEquals(context, "overrideValue", "self.extension_vStereotype1.oclAsType(Bug431638Profile::Stereotype1).string");



		//		ocl.assertValidQuery((Type)contextType, "self.extension_vStereotype1.base_Class.oclIsKindOf(Property)");
		//		ocl.assertQueryFalse(context, "self.extension_vStereotype1.base_Class.oclIsKindOf(Property)");
		//		ocl.assertQueryTrue(context, "self.oclType().oclIsKindOf(self.extension_vStereotype1.base_NamedElement)");
		ocl.assertSemanticErrorQuery(contextType, "self.extension_vStereotype1.base_Class = self.oclType()", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Bug431638Profile::vStereotype1", "base_Class");
		//		ocl.assertQueryTrue(context, "self.extension_vStereotype1.base_Class.oclIsKindOf(UML::Property)");
		//		ocl.assertSemanticErrorQuery2((Type)contextType, "self.extension_vStereotype1", OCLMessages.UnresolvedProperty_ERROR_, "Model::C1", "S2b");
		ocl.dispose();
	}

	/**
	 * Tests construction of a type instance with property values
	 */
	@Test public void test_enumerations_Bug455394() throws Exception {
		UMLStandaloneSetup.init();
		MyOCL ocl = createOCLWithProjectMap();
		IdResolver idResolver = ocl.getIdResolver();
		EObject context = doLoadUML(ocl, getTestModelURI("models/uml/Bug455394.uml"), "Model.Class1.class2");
		assertNotNull(context);
		assert context != null;
		org.eclipse.ocl.pivot.Class contextType = idResolver.getStaticTypeOfValue(null, context);
		ocl.assertQueryTrue(context, "self.aggregation=UML::AggregationKind::composite");
		ocl.assertQueryResults(context, "UML::AggregationKind::composite", "self.aggregation");
		EObject associationContext = doLoadUML(ocl, getTestModelURI("models/uml/Bug455394.uml"), "Model.A_class2_class1");
		CollectionTypeId collectionTypeId = TypeId.ORDERED_SET.getSpecializedId(contextType.getTypeId());
		ocl.assertQueryEquals(associationContext, idResolver.createOrderedSetOfEach(collectionTypeId, context), "self.memberEnd?->select(e|e.aggregation=AggregationKind::composite)");
		ocl.dispose();
	}

	/**
	 * Tests uses of allInstances on a stereotype
	 */
	@Test public void test_stereotype_allinstances_Bug485225() throws Exception {
		UMLStandaloneSetup.init();
		MyOCL ocl = createOCLWithProjectMap();
		IdResolver idResolver = ocl.getIdResolver();
		EObject train1 = doLoadUML(ocl, getTestModelURI("models/uml/Bug485225.uml"), "_zKtRgLUyEeWSV7DXeOPrdA"); //RootElement.Train1");
		org.eclipse.ocl.pivot.Class contextType = idResolver.getStaticTypeOfValue(null, train1);
		EObject application1 = doLoadUML(ocl, getTestModelURI("models/uml/Bug485225.uml"), "_zLFE8LUyEeWSV7DXeOPrdA");
		EObject application2 = doLoadUML(ocl, getTestModelURI("models/uml/Bug485225.uml"), "_8MmIwLUyEeWSV7DXeOPrdA");
		assert train1 != null;
		assert application1 != null;
		assert application2 != null;
		CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(contextType.getTypeId());
		ocl.assertQueryEquals(train1, ValueUtil.createSetOfEach(setTypeId, application1, application2), "TestProfile::Train.allInstances()");
		ocl.assertQueryEquals(train1, ValueUtil.createSetOfEach(setTypeId, application1, application2), "self.extension_Train.oclType().allInstances()");
		ocl.assertQueryResults(train1, "Bag{'Train1','Train2'}", "TestProfile::Train.allInstances().base_Class.name");
		ocl.dispose();
	}

	/**
	 * Tests uses of allInstances on a stereotype
	 */
	@Test public void test_type_literals_Bug580140() throws Exception {
		UMLStandaloneSetup.init();
		MyOCL ocl = createOCLWithProjectMap();
		EObject umlTrain = doLoadUML(ocl, getTestModelURI("models/uml/Bug485225.uml"), "_zKtRgLUyEeWSV7DXeOPrdA"); //RootElement.Train1");
		assert umlTrain != null;
		org.eclipse.ocl.pivot.Class asTrain = ((EnvironmentFactoryInternalExtension)ocl.getEnvironmentFactory()).getASOf(org.eclipse.ocl.pivot.Class.class, umlTrain);
		assert asTrain != null;
		//
		ocl.assertValidQuery(asTrain, "self.oclIsKindOf(Train1)");
		ocl.assertValidQuery(asTrain, "self.oclIsKindOf(Train2)");
		ocl.assertValidQuery(asTrain, "self.oclIsKindOf(RootElement::Train1)");		// Correct Class => true
		ocl.assertValidQuery(asTrain, "self.oclIsKindOf(RootElement::Train2)");		// Sibling class => false
		ocl.assertValidQuery(asTrain, "self.oclIsKindOf(TestProfile::Train)");		// Stereotype metaclass => false
		ocl.assertSemanticErrorQuery(asTrain, "self.oclIsKindOf(Train)", PivotMessagesInternal.UnresolvedElement_ERROR_, "RootElement::Train1", "Train");
		ocl.assertSemanticErrorQuery(asTrain, "self.oclIsKindOf(RootElement::Train)", PivotMessagesInternal.UnresolvedElement_ERROR_, "RootElement", "Train");
		//
		ocl.assertValidQuery(asTrain, "let t = Train1 in self.oclIsKindOf(t)");
		ocl.assertValidQuery(asTrain, "let t = Train2 in self.oclIsKindOf(t)");
		ocl.assertValidQuery(asTrain, "let t = RootElement::Train1 in self.oclIsKindOf(t)");
		ocl.assertValidQuery(asTrain, "let t = RootElement::Train2 in self.oclIsKindOf(t)");
		ocl.assertValidQuery(asTrain, "let t = TestProfile::Train in self.oclIsKindOf(t)");
		ocl.assertSemanticErrorQuery(asTrain, "let t = Train in self.oclIsKindOf(t)", PivotMessagesInternal.UnresolvedElement_ERROR_, "", "Train");
		ocl.assertSemanticErrorQuery(asTrain, "let t = RootElement::Train in self.oclIsKindOf(t)", PivotMessagesInternal.UnresolvedElement_ERROR_, "RootElement", "Train");
		//
		//	aTrain = ... no Ecore instance available
		//	ocl.assertQueryEquals(aTrain, false, "let t = TestProfile::Train in self.oclIsKindOf(t)");
		//	ocl.assertQueryEquals(aTrain, false, "self.oclIsKindOf(TestProfile::Train)");
		ocl.dispose();
	}

	/**
	 * Tests uses of allInstances on a signal
	 */
	@Test public void test_signal_allinstances_Bug496210() throws Exception {
		UMLStandaloneSetup.init();
		MyOCL ocl = createOCLWithProjectMap();
		URI umlURI = getTestModelURI("models/uml/Bug496210.uml");
		Resource umlResource = ocl.getResourceSet().getResource(umlURI, true);
		EObject umlClass1 = ClassUtil.nonNullState(umlResource.getEObject("Class1"));
		ExpressionInOCL expr1 = ocl.createInvariant(umlClass1, "Class1.allInstances()->size()");
		assertEquals("test::Class1.allInstances()->size()", expr1.toString());
		EObject umlSignal2 = ClassUtil.nonNullState(umlResource.getEObject("Signal2"));
		ExpressionInOCL expr2 = ocl.createInvariant(umlSignal2, "Signal2.allInstances()->size()");
		assertEquals("test::Signal2.allInstances()->size()", expr2.toString());
		ocl.dispose();
	}
}
