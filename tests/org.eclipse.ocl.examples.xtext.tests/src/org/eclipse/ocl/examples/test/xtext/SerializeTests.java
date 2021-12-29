/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.xtext.resource.XtextResource;

/**
 * Tests that check that an Ecore model can be serialized to OCLinEcore.
 */
public class SerializeTests extends XtextTestCase
{
	/**
	 * SerializeTestHelper enables a test to contribute before the dispose()/GC takes over.
	 */
	public interface SerializeTestHelper
	{
		default void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) throws IOException, InterruptedException {
			TestUtil.assertSameModel(expectedResource, actualResource);
		}
		default @NonNull String @NonNull [] asFirstReValidationMessages() {
			return asFirstValidationMessages();
		}
		default @NonNull String @NonNull [] asFirstValidationMessages() {
			return NO_MESSAGES;
		}
		default @NonNull String @NonNull [] asSecondValidationMessages() {
			return asFirstValidationMessages();
		}
		default @Nullable String cs2asErrorMessages() {
			return null;
		}
		default void extraXtextResourceValidate(@NonNull BaseCSResource xtextResource) {
			return;
		}
		default void initializeResourceSet(@NonNull ResourceSet resourceSet) {
			return;
		}
	}

	public static final @NonNull SerializeTestHelper DEFAULT_HELPER = new SerializeTestHelper() {};

	public static final @NonNull SerializeTestHelper NO_VALIDATION = new SerializeTestHelper()
	{
		@Override
		public @NonNull String @NonNull [] asFirstValidationMessages() {
			return SUPPRESS_VALIDATION;
		}
	};

	@Override
	protected void setUp() throws Exception {
	//	TEST_START.setState(true);
	//	AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		if (DEBUG_GC) {
			System.gc();
			System.runFinalization();
		}
		super.tearDown();
	}

	public void doSerialize(@NonNull URI inputURI, @NonNull SerializeTestHelper testHelper) throws Exception {
		doSerialize(inputURI, inputURI, testHelper);
	}

	public void doSerialize(@NonNull URI inputURI, @NonNull URI referenceURI, @NonNull SerializeTestHelper testHelper) throws Exception {
	//	ResourceSetInitializer resourceSetInitializer = options != null ? (ResourceSetInitializer)options.get(ResourceSetInitializer.class) : null;
	//	ResourceSet resourceSet9 = new ResourceSetImpl();
	//	getProjectMap().initializeResourceSet(resourceSet9);
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + ".serialized.oclinecore";
		URI outputURI = getTestFileURI(outputName);
		//
		//	Load as Ecore
		//
		OCL ocl1 = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet1 = ocl1.getResourceSet();
		testHelper.initializeResourceSet(resourceSet1);
		Resource ecoreResource = loadEcore(resourceSet1, inputURI);
		//
		//	Ecore to Pivot
		//
		XtextResource xtextResource1 = null;
		try {
			ASResource asResource = ocl1.ecore2as(ecoreResource);
			assertNoResourceErrors("Normalisation failed", asResource);
			@NonNull String @Nullable [] asValidationMessages = testHelper.asFirstValidationMessages();
			if (asValidationMessages != SUPPRESS_VALIDATION) {
				assertValidationDiagnostics("Normalisation invalid 1", asResource, asValidationMessages);
				@NonNull String @Nullable [] asReValidationMessages = testHelper.asFirstReValidationMessages();
				if (asReValidationMessages != SUPPRESS_VALIDATION) {
					// See Bug 577928 whereby a first validation was lightweight.
					assertValidationDiagnostics("Normalisation invalid 2", asResource, asReValidationMessages);
				}
			}
			//
			//	Pivot to CS
			//
			xtextResource1 = as2cs(ocl1, asResource, outputURI);
		//	resourceSet.getResources().clear();
		}
		finally {
			ocl1.dispose();
			ocl1 = null;
		}
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl2 = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet2 = ocl2.getResourceSet();
		testHelper.initializeResourceSet(resourceSet2);
		BaseCSResource xtextResource2 = null;
		try {
			xtextResource2 = (BaseCSResource) resourceSet2.createResource(outputURI);
			assert xtextResource2 != null;
			ocl2.getEnvironmentFactory().adapt(xtextResource2);
			xtextResource2.load(null);
			Object cs2asErrors = testHelper.cs2asErrorMessages();
			if (cs2asErrors != null) {
				String string = cs2asErrors.toString();
				assertResourceErrors("Reload failed", xtextResource2, string);
				if (string.length() > 0) {
					return;
				}
			}
			else {
				assertNoResourceErrors("Reload failed", xtextResource2);
				assertNoUnresolvedProxies("unresolved reload proxies", xtextResource2);
			}
			//
			//	CS to Pivot
			//
			String pivotName2 = stem + "2.ecore.oclas";
			URI pivotURI2 = getTestFileURI(pivotName2);
			Resource pivotResource2 = cs2as(xtextResource2, pivotURI2);
			//
			//	Pivot to Ecore
			//
			String inputName2 = stem + "2.ecore";
			URI ecoreURI2 = getTestFileURI(inputName2);
			@NonNull String @Nullable [] asValidationMessages2 = testHelper.asSecondValidationMessages();
			Resource ecoreResource2 = as2ecore(ocl2.getEnvironmentFactory(), pivotResource2, ecoreURI2, asValidationMessages2);
			//
		//	ThreadLocalExecutor.resetEnvironmentFactory();
			//
			//
			//		TestUtil.TestUtil.assertSameModel(asResource, pivotResource2);
			Resource referenceResource = loadEcore(resourceSet2, referenceURI);
			testHelper.assertSameModel(referenceResource, ecoreResource2);
			testHelper.extraXtextResourceValidate(xtextResource2);
			return;
		}
		finally {
			if (xtextResource2 != null) {
				xtextResource2.eAdapters().remove(ocl2.getEnvironmentFactory().adapt(xtextResource2));
			}
			ocl2.dispose();
			ocl2 = null;
		//	System.gc();
		//	System.runFinalization();
		}
	}

	public void doSerializeUML(@NonNull URI inputURI, @NonNull SerializeTestHelper testHelper) throws Exception {
		UMLPackage.eINSTANCE.getClass();
		//
		//	Load as Ecore
		//
		OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null); //, resourceSet);
		ResourceSet resourceSet1 = ocl1.getResourceSet();
		UML2AS.initialize(resourceSet1);
		Resource umlResource = loadUML(resourceSet1, inputURI);
		//
		//	Ecore to Pivot
		//
		XtextResource xtextResource = null;
		try {
			EnvironmentFactoryInternal environmentFactory1 = ocl1.getEnvironmentFactory();
			@SuppressWarnings("unused")
			Resource asResource = getPivotFromUML(environmentFactory1, umlResource, testHelper.asFirstValidationMessages());
			//
			//	Pivot to CS
			/*
			String outputName = stem + ".serialized.oclinecore";
			URI outputURI = getProjectFileURI(outputName);
			xtextResource = as2cs(ocl1, resourceSet, asResource, outputURI);
			resourceSet.getResources().clear();
			BaseCSResource xtextResource2 = (BaseCSResource) resourceSet.getResource(outputURI, true);
			assertNoResourceErrors("Reload failed", xtextResource2);
			assertNoUnresolvedProxies("unresolved reload proxies", xtextResource2); */
		}
		finally {
			ocl1.dispose();
			ocl1 = null;
		}
		/*		//
		//	CS to Pivot
		//
		String pivotName2 = stem + "2.ecore.oclas";
		URI pivotURI2 = getProjectFileURI(pivotName2);
		Resource pivotResource2 = cs2as(ocl, xtextResource2, pivotURI2);
		//
		//	Pivot to Ecore
		//
		Resource ecoreResource2;
		{
			String inputName2 = stem + "2.ecore";
			URI ecoreURI2 = getProjectFileURI(inputName2);
			ecoreResource2 = as2ecore(ocl, pivotResource2, ecoreURI2, true);
		}
		//
		//
		//
		TestUtil.assertSameModel(asResource, pivotResource2);
		UML2Ecore2AS uml2ecore2as = UML2Ecore2Pivot.getAdapter(umlResource, metamodelManager);	// FIXME Use UML2AS
		Resource ecoreResource = uml2ecore2as.getEcoreResource();
		TestUtil.assertSameModel(ecoreResource, ecoreResource2);		*/
	}

	protected Resource getPivotFromUML(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Resource umlResource, @NonNull String @NonNull [] asValidationMessages) throws ParserException {
		//		String problem = UML2AS.initialize(metamodelManager.getExternalResourceSet());
		//		assertNull(problem);
		UML2AS uml2as = UML2AS.getAdapter(umlResource, environmentFactory);
		Model pivotModel = uml2as.getASModel();
		Resource asResource = ClassUtil.nonNullState(pivotModel.eResource());
		assertNoResourceErrors("Normalisation failed", asResource);
		assertValidationDiagnostics("Normalisation invalid", asResource, asValidationMessages);
		return asResource;
	}

	@SuppressWarnings("deprecation")
	protected @NonNull Resource loadEcore(@NonNull ResourceSet resourceSet, @NonNull URI inputURI) {
		Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			uriMap.putAll(EcorePlugin.computePlatformURIMap(true));
		}
		Resource ecoreResource = ClassUtil.nonNullState(resourceSet.getResource(inputURI, true));
		mapOwnURI(ecoreResource);
		//		List<String> conversionErrors = new ArrayList<String>();
		//		RootPackageCS documentCS = Ecore2OCLinEcore.importFromEcore(resourceSet, null, ecoreResource);
		//		Resource eResource = documentCS.eResource();
		assertNoResourceErrors("Load failed", ecoreResource);
		//		Resource xtextResource = resourceSet.createResource(outputURI, OCLinEcoreCSPackage.eCONTENT_TYPE);
		//		XtextResource xtextResource = (XtextResource) resourceSet.createResource(outputURI);
		//		xtextResource.getContents().add(documentCS);
		return ecoreResource;
	}

	@SuppressWarnings("null")
	protected @NonNull Resource loadUML(@NonNull ResourceSet resourceSet, @NonNull URI inputURI) {
		//		ResourceSet resourceSet = metamodelManager.getExternalResourceSet();
		//		assertNull(OCL.initialize(resourceSet));
		Resource umlResource = resourceSet.getResource(inputURI, true);
		mapOwnURI(umlResource);
		//		List<String> conversionErrors = new ArrayList<String>();
		//		RootPackageCS documentCS = Ecore2OCLinEcore.importFromEcore(resourceSet, null, ecoreResource);
		//		Resource eResource = documentCS.eResource();
		assertNoResourceErrors("Load failed", umlResource);
		//		Resource xtextResource = resourceSet.createResource(outputURI, OCLinEcoreCSPackage.eCONTENT_TYPE);
		//		XtextResource xtextResource = (XtextResource) resourceSet.createResource(outputURI);
		//		xtextResource.getContents().add(documentCS);
		return umlResource;
	}

	public void testSerialize_Bug320689() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Bug320689.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Bug323741() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Bug323741.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Bug354336() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Bug354336.ecore"), getTestModelURI("models/ecore/Bug354336.ecore"), DEFAULT_HELPER);		// FIXME Model check suppressed because of Bug 354621
	}

	public void testSerialize_Bug362620() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Bug362620.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Bug376488() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Bug376488.ecore"), getTestModelURI("models/ecore/Bug376488.ecore"), NO_VALIDATION);		// FIXME
	}

	public void testSerialize_Bug382956() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"Bug382956\" nsURI=\"http://Bug382956\" nsPrefix=\"Bug382956\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EEnum\" name=\"ComparisonKind\">\n" +
						"    <eLiterals name=\"EQ\" literal=\"=\"/>\n" +
						"    <eLiterals name=\"GT\" value=\"1\" literal=\">\"/>\n" +
						"    <eLiterals name=\"LT\" value=\"2\" literal=\"&lt;\"/>\n" +
						"  </eClassifiers>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"MyClass\">\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"comparison\" eType=\"#//ComparisonKind\"\n" +
						"        defaultValueLiteral=\"=\"/>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n" +
						"";
		TestFile ecoreFile = createOCLinEcoreFile("Bug382956.ecore", testFile);		// FIXME rename as createTextFile
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Bug388282() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"rootPackage\" nsURI=\"http://www.example.com/rootPackage/1.0\"\n" +
						"    nsPrefix=\"rootPackage\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Element\" abstract=\"true\">\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
						"        defaultValueLiteral=\"\"/>\n" +
						"  </eClassifiers>\n" +
						"  <eSubpackages name=\"subPackage\" nsURI=\"http://www.example.com/subPackage/1.0\" nsPrefix=\"subPackage\">\n" +
						"    <eClassifiers xsi:type=\"ecore:EClass\" name=\"Element\" abstract=\"true\" eSuperTypes=\"#//Element\"/>\n" +
						"  </eSubpackages>\n" +
						"</ecore:EPackage>\n" +
						"\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug388282.ecore", testFile);		// FIXME rename as createTextFile
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Bug397917() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"   xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"test1\" nsURI=\"http://test1/1.0\" nsPrefix=\"test1\">\n" +
						" <eClassifiers xsi:type=\"ecore:EClass\" name=\"Model\">\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"node\" upperBound=\"-1\" eType=\"#//Node\" containment=\"true\"/>\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"link\" upperBound=\"-1\" eType=\"#//Link\" containment=\"true\"/>\n" +
						" </eClassifiers>\n" +
						" <eClassifiers xsi:type=\"ecore:EClass\" name=\"Node\">\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"uuid\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\" iD=\"true\"/>\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"outgoing\" eType=\"#//Link\" eOpposite=\"#//Link/from\" eKeys=\"#//Link/uuid\"/>\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"incoming\" eType=\"#//Link\" eOpposite=\"#//Link/to\" eKeys=\"#//Link/uuid\"/>\n" +
						" </eClassifiers>\n" +
						" <eClassifiers xsi:type=\"ecore:EClass\" name=\"Link\">\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"uuid\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\" defaultValueLiteral=\"\" iD=\"true\"/>\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"from\" lowerBound=\"1\" eType=\"#//Node\" eOpposite=\"#//Node/outgoing\" eKeys=\"#//Node/uuid\"/>\n" +
						"   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"to\" lowerBound=\"1\" eType=\"#//Node\" eOpposite=\"#//Node/incoming\" eKeys=\"#//Node/uuid\"/>\n" +
						" </eClassifiers>\n" +
						"</ecore:EPackage>";
		TestFile ecoreFile = createOCLinEcoreFile("Bug397917.ecore", testFile);		// FIXME rename as createTextFile
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Bug404493() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"company\" nsURI=\"http://www.eclipse.org/ocl/test/Pivot/Company.ecore\"\n" +
						"    nsPrefix=\"co\">\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
						"    <details key=\"invocationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"settingDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"validationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"  </eAnnotations>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Employee\">\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"hasNameAsAttribute\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean\"\n" +
						"        changeable=\"false\" volatile=\"true\" transient=\"true\" derived=\"true\">\n" +
						"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" +
						"        <details key=\"derivation\" value=\"name &lt;> null -- trailing comment\"/>\n" +
						"      </eAnnotations>\n" +
						"    </eStructuralFeatures>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug404493.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), new SerializeTestHelper()
		{
			@Override
			public void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) {
				return;		// Trailing comment gets lost
			}
		});
	}

	public void testSerialize_Bug425506() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"p\" nsURI=\"p\" nsPrefix=\"p\">\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
						"    <details key=\"invocationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"settingDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"validationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"  </eAnnotations>\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/OCL/Import\">\n" +
						"    <details key=\"ecore\" value=\"http://www.eclipse.org/emf/2002/Ecore\"/>\n" +
						"  </eAnnotations>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"A\">\n" +
						"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
						"      <details key=\"constraints\" value=\"inv2\"/>\n" +
						"    </eAnnotations>\n" +
						"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" +
						"      <details key=\"inv2\" value=\"true\"/>\n" +
						"    </eAnnotations>\n" +
						"    <eOperations name=\"f\">\n" +
						"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">\n" +
						"        <details key=\"documentation\" value=\"function doc\"/>\n" +
						"        <details key=\"body\" value=\"return 1;\"/>\n" +
						"      </eAnnotations>\n" +
						"    </eOperations>\n" +
						"    <eOperations name=\"inv\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean\">\n" +
						"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">\n" +
						"        <details key=\"documentation\" value=\"invariant doc\"/>\n" +
						"        <details key=\"body\" value=\"return 1;\"/>\n" +
						"      </eAnnotations>\n" +
						"      <eParameters name=\"diagnostics\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDiagnosticChain\"/>\n" +
						"      <eParameters name=\"context\">\n" +
						"        <eGenericType eClassifier=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EMap\">\n" +
						"          <eTypeArguments eClassifier=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EJavaObject\"/>\n" +
						"          <eTypeArguments eClassifier=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EJavaObject\"/>\n" +
						"        </eGenericType>\n" +
						"      </eParameters>\n" +
						"    </eOperations>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n" ;
		TestFile ecoreFile = createOCLinEcoreFile("Bug425506.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Bug457043() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n" +
						"    name=\"bug457043\" nsURI=\"http://bug/457043\" nsPrefix=\"bug\">\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">\n" +
						"    <details key=\"documentation\"/>\n" +
						"  </eAnnotations>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug457043.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Bug463877() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"my\" nsURI=\"http://my\" nsPrefix=\"my\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Node\">\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EReference\"/>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug463877.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), new SerializeTestHelper()
		{
			@Override
			public void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) {
				return;		// Strange % character
			}

			@Override
			public @NonNull String @NonNull [] asFirstValidationMessages() {
				return SUPPRESS_VALIDATION;
			}
		}); //getMessages(
		//			"The 'Feature::NameIsNotNull' constraint is violated for 'my::Node::null'",
		//			"The 'Feature::TypeIsNotNull' constraint is violated for 'my::Node::null'"
		//				));
	}

	public void testSerialize_Bug464062() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"env\" nsURI=\"http://cs2as/tests/example2/env/1.0\" nsPrefix=\"env\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Environment\">\n" +
						"    <eOperations name=\"addElements\" eType=\"#//Environment\">\n" +
						"      <eTypeParameters name=\"E\">\n" +
						"        <eBounds eClassifier=\"#//Element\"/>\n" +
						"      </eTypeParameters>\n" +
						"      <eParameters name=\"elements\" upperBound=\"-1\">\n" +
						"        <eGenericType eTypeParameter=\"#//Environment/addElements/E\"/>\n" +
						"      </eParameters>\n" +
						"    </eOperations>\n" +
						"  </eClassifiers>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Element\" abstract=\"true\"/>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug464062.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Bug516274() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"bug516274\" nsURI=\"http:/org/eclipse/ocl/examples/test/xtext/models/Bug516274.oclinecore\"\n" +
						"    nsPrefix=\"my\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Generic\" abstract=\"true\">\n" +
						"    <eTypeParameters name=\"T\">\n" +
						"      <eBounds eClassifier=\"#//Generic\">\n" +
						"        <eTypeArguments eTypeParameter=\"#//Generic/T\"/>\n" +
						"      </eBounds>\n" +
						"    </eTypeParameters>\n" +
						"  </eClassifiers>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Concrete\">\n" +
						"    <eGenericSuperTypes eClassifier=\"#//Generic\">\n" +
						"      <eTypeArguments eClassifier=\"#//Concrete\"/>\n" +
						"    </eGenericSuperTypes>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug516274.ecore", testFile);		// FIXME rename as createTextFile
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}
	public void testSerialize_Bug516301() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"My\" nsURI=\"http://example.org/my\" nsPrefix=\"my\">\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/OCL/Import\">\n" +
						"    <details key=\"ecore\" value=\"http://www.eclipse.org/emf/2002/Ecore\"/>\n" +
						"  </eAnnotations>\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
						"    <details key=\"invocationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"settingDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"validationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"  </eAnnotations>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"DocTest\">\n" +
						"    <eOperations name=\"testJava\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt\">\n" +
						"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">\n" +
						"        <details key=\"documentation\" value=\"Java Documentation\"/>\n" +
						"        <details key=\"body\" value=\"return 1;\"/>\n" +
						"      </eAnnotations>\n" +
						"    </eOperations>\n" +
						"    <eOperations name=\"testOCL\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt\">\n" +
						"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">\n" +
						"        <details key=\"documentation\" value=\"OCL Documentation\"/>\n" +
						"      </eAnnotations>\n" +
						"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" +
						"        <details key=\"body\" value=\"1\"/>\n" +
						"      </eAnnotations>\n" +
						"    </eOperations>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>";
		TestFile ecoreFile = createOCLinEcoreFile("Bug516301.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}


	public void testSerialize_Company() throws Exception {
		//		Logger logger = Logger.getLogger(AbstractParseTreeConstructor.class);
		//		logger.setLevel(Level.TRACE);
		//		logger.addAppender(new ConsoleAppender(new SimpleLayout()));
		//		BaseScopeProvider.LOOKUP.setState(true);
		//		DocumentAttribution.WORK.setState(true);
		//		CS2ASConversion.CONTINUATION.setState(true);
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doSerialize(getTestModelURI("models/ecore/Company.ecore"), getTestModelURI("models/ecore/Company.reference.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_ConstraintMessages() throws Exception {
		doSerialize(getTestModelURI("models/ecore/ConstraintMessages.ecore"), getTestModelURI("models/ecore/ConstraintMessages.reference.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Ecore() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Ecore.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Expressions() throws Exception {
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"b\" nsURI=\"bbb\" nsPrefix=\"bb\">\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
						"    <details key=\"invocationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"settingDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"    <details key=\"validationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
						"  </eAnnotations>\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Expressions\">\n" +
						"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
						"      <details key=\"constraints\" value=\"SimpleIf SingleElseIf DoubleElseIf MapIterators\"/>\n" +
						"    </eAnnotations>\n" +
						"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" +
						"      <details key=\"SimpleIf\" value=\"if true then 1 else 2 endif &lt;> 0\"/>\n" +
						"      <details key=\"SingleElseIf\" value=\"if true then 1 elseif true then 2 else 3 endif &lt;> 0\"/>\n" +
						"      <details key=\"DoubleElseIf\" value=\"if true then 1 elseif true then 2 elseif true then 3 else 4 endif &lt;> 0\"/>\n" +
						"      <details key=\"MapIterators\" value=\"Map{1 &lt;- 1}-&gt;collect(v &lt;- k | v * k)-&gt;notEmpty()\"/>\n" +
						"    </eAnnotations>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Expressions.ecore", testFile);
		doSerialize(ecoreFile.getFileURI(), DEFAULT_HELPER);
	}

	public void testSerialize_Imports() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		getTestFile("LittleModel.ecore", ocl, getTestModelURI("models/ecore/LittleModel.ecore"));
		TestFile testFile = getTestFile("Imports.ecore", ocl, getTestModelURI("models/ecore/Imports.ecore"));
		ocl.dispose();
		doSerialize(testFile.getFileURI(), new SerializeTestHelper()
		{
			@Override
			public void extraXtextResourceValidate(@NonNull BaseCSResource xtextResource) {
				RootPackageCS documentCS = (RootPackageCS) xtextResource.getContents().get(0);
				List<ImportCS> imports = documentCS.getOwnedImports();
				assertEquals("One import", 1, imports.size());
			}
		});
	}

	public void testSerialize_Keys() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Keys.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Names() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Names.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_Opposites() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Opposites.ecore"), DEFAULT_HELPER);
	}

	/*
	 * Requires support for lower bounds on generic types
	 * and better resolution of EAnnotation.references
	public void testSerialize_OCL() throws Exception {
		doSerialize(ocl, "OCL");
	} */

	public void testSerialize_BaseCST() throws Exception {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.base/model/BaseCS.ecore", true);
		doSerialize(uri, new SerializeTestHelper()
		{
			@Override
			public void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) {
				return;		// Changes from Pivot to Ecore types
			}
		});		// FIXME URIs don't quite compare
	}

	public void testSerialize_EssentialOCLCST() throws Exception {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.essentialocl/model/EssentialOCLCS.ecore", true);
		doSerialize(uri, new SerializeTestHelper()
		{
			@Override
			public void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) {
				return;		// Extra import
			}
			@Override
			public void initializeResourceSet(@NonNull ResourceSet resourceSet) {
				getProjectMap().configureLoadFirst(resourceSet, EcorePackage.eNS_URI);
			}
		});		// FIXME URIs don't quite compare
	}

	public void testSerialize_OCLinEcoreCST() throws Exception {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.oclinecore/model/OCLinEcoreCS.ecore", true);
		doSerialize(uri, new SerializeTestHelper()
		{
			@Override
			public void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) {
				return;		// Source has implicit import
			}
			@Override
			public void initializeResourceSet(@NonNull ResourceSet resourceSet) {
				getProjectMap().configureLoadFirst(resourceSet, EcorePackage.eNS_URI);
			}
		});		// FIXME URIs don't quite compare
		//		doSerialize(ocl, "OCLinEcoreCST");
	}

	public void testSerialize_OCLstdlib() throws Exception {
		doSerialize(getTestModelURI("models/ecore/OCLstdlib.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_OCLCST() throws Exception {
		doSerialize(getTestModelURI("models/ecore/OCLCST.ecore"), DEFAULT_HELPER);
	}

	public void testSerialize_QVT() throws Exception {
		doSerialize(getTestModelURI("models/ecore/QVT.ecore"), new SerializeTestHelper() {
			// Reset the expectedResource xmi:ids since serialization does not recompute algorithmic xmi:ids.
			@Override
			public void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) throws IOException, InterruptedException {
				XMLResourceImpl xmlResourceImpl = (XMLResourceImpl)expectedResource;
				for (EObject eObject : new TreeIterable(expectedResource)) {
					xmlResourceImpl.setID(eObject, null);
				}
				SerializeTestHelper.super.assertSameModel(expectedResource, actualResource);
			}
		});
	}

	public void testSerialize_RoyalAndLoyal_ecore() throws Exception {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {		// org.eclipse.ocl.examples.project.royalandloyal is not a plugin.
			@NonNull URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ecore", true);
			doSerialize(inputURI, DEFAULT_HELPER);
		}
	}

	public void testSerialize_States() throws Exception {
		String message1 = StringUtil.bind(PivotMessagesInternal.ValidationConstraintIsInvalid_ERROR_, PivotConstantsInternal.INVARIANT_ROLE,
					"states::State::NameIsLeadingUpperCase", "let firstLetter : String = invalid.substring(1, 1) in firstLetter.toUpperCase() = firstLetter'"
						+ "\n" + "1:36: Unresolved Operation 'OclInvalid::substring(1, 1)");
		String message2 = StringUtil.bind("The ''CallExp::TypeIsNotInvalid'' constraint is violated for ''invalid.oclBadOperation()''");
		doSerialize(getTestModelURI("models/ecore/States.ecore"), getTestModelURI("models/ecore/States.ecore"), new SerializeTestHelper()
		{
			@Override
			public @NonNull String @NonNull [] asFirstReValidationMessages() {
				return getMessages(message2);
			}
			@Override
			public @NonNull String @NonNull [] asFirstValidationMessages() {
				return getMessages(message1, message2);
			}
			@Override
			public @NonNull String cs2asErrorMessages() {
				return StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "substring", "1, 1");
			}
		});
	}

	public void testSerialize_XMLNamespace() throws Exception {
		doSerialize(getTestModelURI("models/ecore/XMLNamespace.ecore"), DEFAULT_HELPER);
	}

	public void test_StateMachines_uml_Serialize() throws Exception {
		UMLStandaloneSetup.init();
		doSerializeUML(getTestModelURI("models/uml/StateMachines.uml"), new SerializeTestHelper()
		{
			@Override
			public @NonNull String @NonNull [] asFirstValidationMessages() {
				return new @NonNull String[] {
					"The 'Feature::TypeIsNotNull' constraint is violated for 'Model::C1::o1() : «null»[1]'",
					"The 'Feature::TypeIsNotNull' constraint is violated for 'Model::C2::o2() : «null»[1]'"
				};
			}

		});
	}
}
