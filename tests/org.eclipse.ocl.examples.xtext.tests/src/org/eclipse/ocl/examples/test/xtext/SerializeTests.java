/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
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
	protected interface ResourceSetInitializer
	{
		void initializeResourceSet(@NonNull ResourceSet resourceSet);
	}

	protected Map<Object, Object> createLoadedEcoreOptions() {
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(ResourceSetInitializer.class, new ResourceSetInitializer()
		{
			@Override
			public void initializeResourceSet(@NonNull ResourceSet resourceSet) {
				StandaloneProjectMap.IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor("org.eclipse.emf.ecore");
				if (projectDescriptor != null) {
					@NonNull URI ecoreURI = URI.createURI(EcorePackage.eNS_URI);
					StandaloneProjectMap.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(ecoreURI);
					if (packageDescriptor != null) {
						packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
					}
				}
			}
		});
		return options;
	}

	public XtextResource doSerialize(@NonNull OCL ocl, @NonNull URI inputURI) throws Exception {
		return doSerialize(ocl, inputURI, inputURI.trimFileExtension().lastSegment(), inputURI, null, true, NO_MESSAGES, NO_MESSAGES);
	}
	public XtextResource doSerialize(@NonNull OCL ocl, @NonNull TestFile inputFile) throws Exception {
		return doSerialize(ocl, inputFile, true);
	}
	public XtextResource doSerialize(@NonNull OCL ocl, @NonNull TestFile inputFile, boolean doCompare) throws Exception {
		return doSerialize(ocl, inputFile, doCompare, NO_MESSAGES);
	}
	public XtextResource doSerialize(@NonNull OCL ocl, @NonNull TestFile inputFile, boolean doCompare, @NonNull String @NonNull [] asValidationMessages) throws Exception {
		URI inputURI = inputFile.getFileURI();
		String stem = ClassUtil.nonNullState(inputURI.trimFileExtension().lastSegment());
		URI referenceURI = inputURI;
		return doSerialize(ocl, inputURI, stem, referenceURI, null, doCompare, asValidationMessages, asValidationMessages);
	}

	public XtextResource doSerialize(@NonNull OCL ocl, @NonNull URI inputURI, @NonNull String stem, @NonNull URI referenceURI, @Nullable Map<Object, Object> options,
			boolean doCompare, @NonNull String @NonNull [] asValidationMessages, @NonNull String @NonNull [] asValidationMessages2) throws Exception {
		String stem2 = inputURI.trimFileExtension().lastSegment();
		assert stem.equals(stem2);
		return doSerialize(ocl, inputURI, referenceURI, options, doCompare, asValidationMessages, asValidationMessages2);
	}

	public XtextResource doSerialize(@NonNull OCL ocl, @NonNull URI inputURI, @NonNull URI referenceURI, @Nullable Map<Object, Object> options,
			boolean doCompare, @NonNull String @NonNull [] asValidationMessages, @NonNull String @NonNull [] asValidationMessages2) throws Exception {
		ResourceSetInitializer resourceSetInitializer = options != null ? (ResourceSetInitializer)options.get(ResourceSetInitializer.class) : null;
		ResourceSet resourceSet = new ResourceSetImpl();
		getProjectMap().initializeResourceSet(resourceSet);
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + ".serialized.oclinecore";
		URI outputURI = getTestFileURI(outputName);
		//
		//	Load as Ecore
		//
		Resource ecoreResource = loadEcore(inputURI);
		//
		//	Ecore to Pivot
		//
		OCL ocl1 = OCL.newInstance(getProjectMap());
		XtextResource xtextResource1 = null;
		try {
			if (resourceSetInitializer != null) {
				resourceSetInitializer.initializeResourceSet(ocl1.getResourceSet());
			}
			ASResource asResource = ocl1.ecore2as(ecoreResource);
			assertNoResourceErrors("Normalisation failed", asResource);
			if (asValidationMessages != SUPPRESS_VALIDATION) {
				assertValidationDiagnostics("Normalisation invalid", asResource, asValidationMessages);
			}
			//
			//	Pivot to CS
			//
			xtextResource1 = as2cs(ocl1, resourceSet, asResource, outputURI);
			resourceSet.getResources().clear();
		}
		finally {
			ocl1.dispose();
			ocl1 = null;
		}
		OCL ocl2 = OCL.newInstance(getProjectMap());
		try {
			if (resourceSetInitializer != null) {
				resourceSetInitializer.initializeResourceSet(ocl2.getResourceSet());
			}
			BaseCSResource xtextResource2 = (BaseCSResource) resourceSet.createResource(outputURI);
			assert xtextResource2 != null;
			ocl2.getEnvironmentFactory().adapt(xtextResource2);
			xtextResource2.load(null);
			Object cs2asErrors = options != null ? options.get("cs2asErrors") : null;
			if (cs2asErrors != null) {
				String string = cs2asErrors.toString();
				assertResourceErrors("Reload failed", xtextResource2, string);
				if (string.length() > 0) {
					return null;
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
			Resource pivotResource2 = cs2as(ocl2, xtextResource2, pivotURI2);
			//
			//	Pivot to Ecore
			//
			String inputName2 = stem + "2.ecore";
			URI ecoreURI2 = getTestFileURI(inputName2);
			Resource ecoreResource2 = as2ecore(ocl2, pivotResource2, ecoreURI2, asValidationMessages2);
			//
			//
			//
			//		TestUtil.TestUtil.assertSameModel(asResource, pivotResource2);
			Resource referenceResource = loadEcore(referenceURI);
			if (doCompare) {	// Workaround for Bug 354621
				TestUtil.assertSameModel(referenceResource, ecoreResource2);
			}
			return xtextResource1;
		}
		finally {
			ocl2.dispose();
			ocl2 = null;
		}
	}

	public XtextResource doSerializeUML(@NonNull OCL ocl, @NonNull URI inputURI, @NonNull String @NonNull [] asValidationMessages) throws Exception {
		//		UML2AS.initialize(ocl.getResourceSet());
		UMLPackage.eINSTANCE.getClass();
		//
		//	Load as Ecore
		//
		Resource umlResource = loadUML(ocl, inputURI);
		//
		//	Ecore to Pivot
		//
		OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null); //, resourceSet);
		UML2AS.initialize(ocl1.getResourceSet());
		XtextResource xtextResource = null;
		try {
			MetamodelManagerInternal metamodelManager1 = ocl1.getMetamodelManager();
			@SuppressWarnings("unused")
			Resource asResource = getPivotFromUML(metamodelManager1, umlResource, asValidationMessages);
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
		return xtextResource;
	}

	protected Resource getPivotFromUML(MetamodelManagerInternal metamodelManager, @NonNull Resource umlResource, @NonNull String @NonNull [] asValidationMessages) throws ParserException {
		//		String problem = UML2AS.initialize(metamodelManager.getExternalResourceSet());
		//		assertNull(problem);
		UML2AS uml2as = UML2AS.getAdapter(umlResource, metamodelManager.getEnvironmentFactory());
		Model pivotModel = uml2as.getASModel();
		Resource asResource = ClassUtil.nonNullState(pivotModel.eResource());
		assertNoResourceErrors("Normalisation failed", asResource);
		assertValidationDiagnostics("Normalisation invalid", asResource, asValidationMessages);
		return asResource;
	}

	@SuppressWarnings("null")
	protected @NonNull Resource loadUML(@NonNull OCL ocl, @NonNull URI inputURI) {
		//		ResourceSet resourceSet = metamodelManager.getExternalResourceSet();
		//		assertNull(OCL.initialize(resourceSet));
		Resource umlResource = ocl.getResourceSet().getResource(inputURI, true);
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
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Bug320689.ecore"));
		ocl.dispose();
	}

	public void testSerialize_Bug323741() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Bug323741.ecore"));
		ocl.dispose();
	}

	public void testSerialize_Bug354336() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Bug354336.ecore"), getTestModelURI("models/ecore/Bug354336.ecore"), null, false, NO_MESSAGES, NO_MESSAGES);		// FIXME Model check suppressed because of Bug 354621
		ocl.dispose();
	}

	public void testSerialize_Bug362620() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Bug362620.ecore"));
		ocl.dispose();
	}

	public void testSerialize_Bug376488() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Bug376488.ecore"), getTestModelURI("models/ecore/Bug376488.ecore"), null, true, SUPPRESS_VALIDATION, SUPPRESS_VALIDATION);		// FIXME
		ocl.dispose();
	}

	public void testSerialize_Bug382956() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Bug388282() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Bug397917() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Bug404493() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile, false);
		ocl.dispose();
	}

	public void testSerialize_Bug425506() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Bug457043() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n" +
						"    name=\"bug457043\" nsURI=\"http://bug/457043\" nsPrefix=\"bug\">\n" +
						"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">\n" +
						"    <details key=\"documentation\"/>\n" +
						"  </eAnnotations>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug457043.ecore", testFile);
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Bug463877() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		String testFile =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"my\" nsURI=\"http://my\" nsPrefix=\"my\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Node\">\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EReference\"/>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Bug463877.ecore", testFile);
		doSerialize(ocl, ecoreFile, false, SUPPRESS_VALIDATION); //getMessages(
		//			"The 'Feature::NameIsNotNull' constraint is violated for 'my::Node::null'",
		//			"The 'Feature::TypeIsNotNull' constraint is violated for 'my::Node::null'"
		//				));
		ocl.dispose();
	}

	public void testSerialize_Bug464062() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Bug516274() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}
	public void testSerialize_Bug516301() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
		doSerialize(ocl, ecoreFile, false);
		ocl.dispose();
	}


	public void testSerialize_Company() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		//		Logger logger = Logger.getLogger(AbstractParseTreeConstructor.class);
		//		logger.setLevel(Level.TRACE);
		//		logger.addAppender(new ConsoleAppender(new SimpleLayout()));
		//		BaseScopeProvider.LOOKUP.setState(true);
		//		DocumentAttribution.WORK.setState(true);
		//		CS2ASConversion.CONTINUATION.setState(true);
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doSerialize(ocl, getTestModelURI("models/ecore/Company.ecore"), getTestModelURI("models/ecore/Company.reference.ecore"), null, true, NO_MESSAGES, NO_MESSAGES);
		ocl.dispose();
	}

	public void testSerialize_ConstraintMessages() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/ConstraintMessages.ecore"), getTestModelURI("models/ecore/ConstraintMessages.reference.ecore"), null, true, NO_MESSAGES, NO_MESSAGES);
		ocl.dispose();
	}

	public void testSerialize_Ecore() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Ecore.ecore"));
		ocl.dispose();
	}

	public void testSerialize_Expressions() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
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
						"      <details key=\"constraints\" value=\"SimpleIf SingleElseIf DoubleElseIf\"/>\n" +
						"    </eAnnotations>\n" +
						"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" +
						"      <details key=\"SimpleIf\" value=\"if true then 1 else 2 endif &lt;> 0\"/>\n" +
						"      <details key=\"SingleElseIf\" value=\"if true then 1 elseif true then 2 else 3 endif &lt;> 0\"/>\n" +
						"      <details key=\"DoubleElseIf\" value=\"if true then 1 elseif true then 2 elseif true then 3 else 4 endif &lt;> 0\"/>\n" +
						"    </eAnnotations>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n";
		TestFile ecoreFile = createOCLinEcoreFile("Expressions.ecore", testFile);
		doSerialize(ocl, ecoreFile);
		ocl.dispose();
	}

	public void testSerialize_Imports() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		getTestFile("LittleModel.ecore", ocl, getTestModelURI("models/ecore/LittleModel.ecore"));
		TestFile testFile = getTestFile("Imports.ecore", ocl, getTestModelURI("models/ecore/Imports.ecore"));
		XtextResource xtextResource = doSerialize(ocl, testFile);
		RootPackageCS documentCS = (RootPackageCS) xtextResource.getContents().get(0);
		List<ImportCS> imports = documentCS.getOwnedImports();
		assertEquals("One import", 1, imports.size());
		ocl.dispose();
	}

	public void testSerialize_Keys() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Keys.ecore"));
		ocl.dispose();
	}

	public void testSerialize_Names() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/Names.ecore"));
		ocl.dispose();
	}

	/*
	 * Requires support for lower bounds on generic types
	 * and better resolution of EAnnotation.references
	public void testSerialize_OCL() throws Exception {
		doSerialize(ocl, "OCL");
	} */

	public void testSerialize_BaseCST() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.base/model/BaseCS.ecore", true);
		@SuppressWarnings("null")@NonNull String stem = uri.trimFileExtension().lastSegment();
		doSerialize(ocl, uri, stem, uri, null, false, NO_MESSAGES, NO_MESSAGES);		// FIXME URIs don't quite compare
		ocl.dispose();
	}

	public void testSerialize_EssentialOCLCST() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.essentialocl/model/EssentialOCLCS.ecore", true);
		@SuppressWarnings("null")@NonNull String stem = uri.trimFileExtension().lastSegment();
		Map<Object, Object> options = createLoadedEcoreOptions();
		doSerialize(ocl, uri, stem, uri, options, false, NO_MESSAGES, NO_MESSAGES);		// FIXME URIs don't quite compare
	}

	public void testSerialize_OCLinEcoreCST() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.oclinecore/model/OCLinEcoreCS.ecore", true);
		@SuppressWarnings("null")@NonNull String stem = uri.trimFileExtension().lastSegment();
		Map<Object, Object> options = createLoadedEcoreOptions();
		doSerialize(ocl, uri, stem, uri, options, false, NO_MESSAGES, NO_MESSAGES);		// FIXME URIs don't quite compare
		//		doSerialize(ocl, "OCLinEcoreCST");
		ocl.dispose();
	}

	public void testSerialize_OCLstdlib() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/OCLstdlib.ecore"));
		ocl.dispose();
	}

	public void testSerialize_OCLCST() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/OCLCST.ecore"));
		ocl.dispose();
	}

	/* BUG 377626
	public void testSerialize_QVT() throws Exception {
		doSerialize(ocl, "QVT");
	} */

	public void testSerialize_RoyalAndLoyal_ecore() throws Exception {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {		// org.eclipse.ocl.examples.project.royalandloyal is not a plugin.
			OCL ocl = OCL.newInstance(getProjectMap());
			@NonNull URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ecore", true);
			doSerialize(ocl, inputURI, "RoyalAndLoyal", inputURI, null, true, NO_MESSAGES, NO_MESSAGES);
			ocl.dispose();
		}
	}

	public void testSerialize_States() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		Map<Object, Object> options = new HashMap<Object, Object>();
		String message = StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "substring", "1, 1");
		options.put("cs2asErrors", message);
		//		String message1 = StringUtil.bind("The ''State::NameIsLeadingUpperCase'' constraint is invalid: ''let firstLetter : String = invalid.substring(1, 1) in firstLetter.toUpperCase() = firstLetter''\n" +
		//				"1: Unresolved Operation ''OclInvalid::substring(1, 1)''");
		//		String message2 = StringUtil.bind("The ''CallExp::TypeIsNotInvalid'' constraint is violated for ''invalid.oclBadOperation()''");
		//		String message3 = StringUtil.bind("OCL Validation error for \"let firstLetter : String[?] = invalid.oclBadOperation() in firstLetter.toUpperCase() = firstLetter\"\n" +
		//				"	The ''LetVariable::CompatibleTypeForInitializer'' constraint is violated for ''firstLetter : String[?] = invalid.oclBadOperation()''");
		//		String message4 = StringUtil.bind("Parsing error ''org.eclipse.ocl.pivot.utilities.SemanticException: The ''states::State'' constraint is invalid: ''let firstLetter : String = invalid.substring(1, 1) in firstLetter.toUpperCase() = firstLetter''\n" +
		//				"1: Unresolved Operation ''OclInvalid::substring(1, 1)'''' for ''states::State'' ''NameIsLeadingUpperCase''");
		doSerialize(ocl, getTestModelURI("models/ecore/States.ecore"), getTestModelURI("models/ecore/States.ecore"), options, false, NO_MESSAGES/*getMessages(message1, message2)*/, NO_MESSAGES/*getMessages(message4)*/);
		//			new String[] {StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "substring", "1, 1")});
		ocl.dispose();
	}

	public void testSerialize_XMLNamespace() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, getTestModelURI("models/ecore/XMLNamespace.ecore"));
		ocl.dispose();
	}

	public void test_StateMachines_uml_Serialize() throws Exception {
		UMLStandaloneSetup.init();
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerializeUML(ocl, getTestModelURI("models/uml/StateMachines.uml"), new @NonNull String[] {
			"The 'Feature::TypeIsNotNull' constraint is violated for 'Model::C1::o1() : «null»[1]'",
			"The 'Feature::TypeIsNotNull' constraint is violated for 'Model::C2::o2() : «null»[1]'"
		});
		ocl.dispose();
	}
}
