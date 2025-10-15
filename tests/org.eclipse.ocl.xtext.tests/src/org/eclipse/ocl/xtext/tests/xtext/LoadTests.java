/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.xtext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.ecore.annotations.EAnnotationConverter;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.BaseCSXMIResource;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.tests.TestCaseLogger;
import org.eclipse.ocl.xtext.tests.TestFile;
import org.eclipse.ocl.xtext.tests.TestUtil;
import org.eclipse.ocl.xtext.tests.pivot.tests.TestOCL;
import org.eclipse.xtext.resource.impl.ListBasedDiagnosticConsumer;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class LoadTests extends AbstractLoadTests
{
	/*	public void checkMonikers(Resource resource) {
		Map<String, NamedElementCS> sigMap = new HashMap<String, NamedElementCS>();
		for (Iterator<EObject> it = resource.getAllContents(); it.hasNext(); ) {
			EObject eObject = it.next();
			@SuppressWarnings("unused")
			String toString = eObject.toString();
			if (eObject instanceof NamedElementCS) {
				NamedElementCS namedElementCS = (NamedElementCS)eObject;
				String moniker = CS2Moniker.toString(namedElementCS);
				if (sigMap.containsKey(moniker)) {
					System.out.println("Duplicate moniker " + moniker + " from "  + namedElementCS.eClass().getName());
					CS2Moniker.toString(namedElementCS);
				}
				sigMap.put(moniker, namedElementCS);
			}
			else if (eObject instanceof ModelElementCS) {
				ModelElementCS nameableElementCS = (ModelElementCS)eObject;
				String moniker = CS2Moniker.toString(nameableElementCS);
				System.out.println(moniker + "                              -> " + nameableElementCS.eClass().getName()); // + " : " + value.toString());
			}
		}
		List<String> keys = new ArrayList<String>(sigMap.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			ModelElementCS value = sigMap.get(key);
			System.out.println(key + "                              => " + value.eClass().getName()); // + " : " + value.toString());
		}
	} */

	public void testLoad_Annotations_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoadEcore(ocl, getTestModelURI("models/ecore/Annotations.ecore"));
		ocl.dispose();
	}

	public void testLoad_Annotations_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Annotations.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Ecore_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoadEcore(ocl, getTestModelURI("models/ecore/Ecore.ecore"));
		ocl.dispose();
	}

	public void testLoad_Empty_ecore() throws IOException, InterruptedException {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUtil.initializeEcoreEAnnotationValidators();
		}
		OCL ocl = createOCLWithProjectMap();
		doLoadEcore(ocl, getTestModelURI("models/ecore/Empty.ecore"));
		ocl.dispose();
	}

	public void testLoad_Expression_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		metamodelManager.loadLibrary(OCLstdlib.INSTANCE);
		Resource asResource = doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Expression.oclinecore"));
		String ecoreName = "Expression" + ".saved.ecore";
		URI ecoreURI = getTestFileURI(ecoreName);
		Map<String,Object> options = new HashMap<String,Object>();
		options.put(PivotConstants.PRIMITIVE_TYPES_URI_PREFIX, "models/ecore/primitives.ecore#//");
		XMLResource ecoreResource = AS2Ecore.createResource(ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(XMIUtil.createSaveOptions(ecoreResource));
		ocl.dispose();
	}

	public void testLoad_Imports_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoadEcore(ocl, getTestModelURI("models/ecore/Imports.ecore"));
		ocl.dispose();
	}

	public void testLoad_Names_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoadEcore(ocl, getTestModelURI("models/ecore/Names.ecore"));
		ocl.dispose();
	}

	public void testLoad_Names_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Names.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Overloads_oclinecore() throws IOException, InterruptedException {
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Overloads.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Refresh_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			//			OCLDelegateDomain.initialize(null);
		}
		String testFileContents =
				"package tutorial : tuttut = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Library\n" +
						"	{\n" +
						"		property books#library : Book[*] { composes };\n" +
						"	}\n" +
						"	class Book\n" +
						"	{\n" +
						"		property library#books : Library[?];\n" +
						"		property name : String;\n" +
						"		invariant NameNotEmpty: name->notEmpty();\n" +
						"	}\n" +
						"}\n";
		TestFile testFile = createFile("Refresh.oclinecore", testFileContents);
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, testFile.getFileURI());
		Resource asResource = doLoad_Concrete2(ocl, xtextResource, testFile.getFileURI());
		assertNoValidationErrors("First validation", asResource);
		try {
			xtextResource.update(ocl.getEnvironmentFactory(), new ListBasedDiagnosticConsumer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		assertNoUnresolvedPivots("Unresolved pivots", xtextResource);
		assertNoValidationErrors("Second validation", asResource);
		ocl.dispose();
	}

	public void testLoad_Refresh2_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			//			OCLDelegateDomain.initialize(null);
		}
		String testFileContents =
				"package example : ex = 'http://www.example.org/examples/example.ecore'\n" +
						"{\n" +
						"	class NamedElement;\n" +
						"	class Package;\n" +
						"	class Class;\n" +
						"	class Operation;\n" +
						"	class Property;\n" +
						"\n" +
						"	class CompletePackageParent extends NamedElement\n" +
						"	{\n" +
						"		property nestedPackages#completePackage : Set(CompletePackage) { composes };\n" +
						"	}\n" +
						"	/** MetamodelManager/PackageManager API */\n" +
						"	class CompleteModel extends CompletePackageParent\n" +
						"	{\n" +
						"	}\n" +
						"	class CompletePackage extends CompletePackageParent\n" +
						"	{\n" +
						"		property completePackage#nestedPackages : CompletePackageParent;\n" +
						"		property nestedPackages : Set(CompletePackage) { composes };\n" +
						"		property partialPackages : OrderedSet(Package);\n" +
						"		property nestedClasses#completePackage : CompleteClass[*] { composes };\n" +
						"	}\n" +
						"	class CompleteClass extends NamedElement\n" +
						"	{\n" +
						"		property completePackage#nestedClasses : CompletePackage;\n" +
						"		property partialClasses : OrderedSet(Class);\n" +
						"	}\n" +
						"	class CompleteOperation extends NamedElement\n" +
						"	{\n" +
						"	}\n" +
						"	class CompleteProperty extends NamedElement\n" +
						"	{\n" +
						"	}\n" +
						"}";
		TestFile testFile = createFile("Refresh2.oclinecore", testFileContents);
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, testFile.getFileURI());
		Resource asResource = doLoad_Concrete2(ocl, xtextResource, testFile.getFileURI());
		assertNoValidationErrors("First validation", asResource);
		try {
			xtextResource.update(ocl.getEnvironmentFactory(), new ListBasedDiagnosticConsumer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		assertNoUnresolvedPivots("Unresolved pivots", xtextResource);
		assertNoValidationErrors("Second validation", asResource);
		ocl.dispose();
	}

	public void testLoad_RoyalAndLoyal_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoadEcore(ocl, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ecore", true));
		ocl.dispose();
	}

	public void testLoad_OCL_2_5_oclstdlib() throws IOException, InterruptedException {
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.omg.org/ocl");
		OCL ocl = createOCLWithProjectMap();
		//		StandardLibraryContribution.REGISTRY.put(MetamodelManager.DEFAULT_OCL_STDLIB_URI, StandardLibraryContribution.NULL);
		Resource asResource = doLoad_Concrete(ocl, getTestModelURI("platform:/resource/org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib"));
		//		checkMonikers(asResource);
		String ecoreName = "oclstdlib" + ".saved.ecore";
		URI ecoreURI = getTestFileURI(ecoreName);
		Map<String,Object> options = new HashMap<String,Object>();
		options.put(PivotConstants.PRIMITIVE_TYPES_URI_PREFIX, "models/ecore/primitives.ecore#//");
		XMLResource ecoreResource = AS2Ecore.createResource(ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(XMIUtil.createSaveOptions(ecoreResource));
		ocl.dispose();
	}

	public void testLoad_oclstdlib_oclstdlib() throws IOException, InterruptedException {
		EAnnotationConverter.addDefaultEAnnotationConverter("http://www.omg.org/ocl");
		OCL ocl = createOCLWithProjectMap();
		//		StandardLibraryContribution.REGISTRY.put(MetamodelManager.DEFAULT_OCL_STDLIB_URI, StandardLibraryContribution.NULL);
		Resource asResource = doLoad_Concrete(ocl, getTestModelURI("models/oclstdlib/oclstdlib.oclstdlib"));
		//		checkMonikers(asResource);
		String ecoreName = "oclstdlib" + ".saved.ecore";
		URI ecoreURI = getTestFileURI(ecoreName);
		Map<String,Object> options = new HashMap<String,Object>();
		options.put(PivotConstants.PRIMITIVE_TYPES_URI_PREFIX, "models/ecore/primitives.ecore#//");
		XMLResource ecoreResource = AS2Ecore.createResource(ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(XMIUtil.createSaveOptions(ecoreResource));
		ocl.dispose();
	}

	public void testLoad_OCL_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoadEcore(ocl, getTestModelURI("models/ecore/OCL.ecore"));
		ocl.dispose();
	}

	public void testLoad_Types_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Types.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Wildcard_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Wildcard.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_BaseCST_ecore() throws IOException, InterruptedException {
		StandaloneProjectMap projectMap = getProjectMap();
		OCL ocl = OCL.newInstance(projectMap);
		ResourceSet resourceSet = ocl.getResourceSet();
		projectMap.configureLoadFirst(resourceSet, EcorePackage.eNS_URI);
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.base/model/BaseCS.ecore", true);
		try {
			doLoadEcore(ocl, resourceSet, uri);
		}
		finally {
			unloadResourceSet(resourceSet);
		}
		ocl.dispose();
	}

	//	public void testLoad_TypeConformance_ocl() throws IOException, InterruptedException {
	//		doLoad_Concrete("TypeConformance", "ocl");
	//	}

	//	public void testLoad_MiniTypeWFRs_ocl() throws IOException, InterruptedException {
	//		doLoad_Concrete("MiniTypeWFRs", "ocl");
	//	}

	//	public void testLoad_TypeWFRs_ocl() throws IOException, InterruptedException {
	//		doLoad_Concrete("TypeWFRs", "ocl");
	//	}

	public void testLoad_Bug321171_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug321171.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Bug321903_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug321903.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Bug323741_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoadEcore(ocl, getTestModelURI("models/ecore/Bug323741.ecore"));
		ocl.dispose();
	}

	//FIXME	public void testLoad_Bug323741_pivot() throws IOException, InterruptedException {
	//		doLoad_Pivot("Bug323741", "pivot");
	//	}

	public void testLoad_Bug323741_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug323741.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Bug325058_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug325058.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Bug328480_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug328480.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Bug328485_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		Resource asResource = doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug328485.oclinecore"));
		VariableDeclaration referredVariable = null;
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext();  ) {
			EObject eObject = tit.next();
			if (eObject instanceof VariableExp) {
				assertNull(referredVariable);
				VariableExp variableExp = (VariableExp)eObject;
				if ("name".equals(variableExp.getReferredVariable().getName())) {
					referredVariable = variableExp.getReferredVariable();
					assertEquals("Named", referredVariable.getType().getName());
				}
			}
		}
		assertNotNull(referredVariable);
		ocl.dispose();
	}

	public void testLoad_Bug401921_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Bug401921.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Bug402767_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		String testFileContents =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"class B\n" +
						"{\n" +
						"property vBlank : Real;\n" +
						"property vQuery : Real[?];\n" +
						"property vPlus : Real[+];\n" +
						"property vStar : Real[*];\n" +
						"property vOne : Real[1];\n" +
						"property vThree : Real[3];\n" +
						"property vOne2Three : Real[1..3];\n" +
						"property vThree2Three : Real[3..3];\n" +
						"property vThree2Star : Real[3..*];\n" +
						"}\n" +
						"}\n";
		TestFile testFile = createFile("Bug402767.oclinecore", testFileContents);
		Resource resource = doLoad_Concrete(ocl, testFile.getFileURI());
		Model root = (Model) resource.getContents().get(0);
		org.eclipse.ocl.pivot.Package pkg = root.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class cls = pkg.getOwnedClasses().get(0);
		List<Property> ownedAttributes = cls.getOwnedProperties();
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vBlank"), 0, 1);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vQuery"), 0, 1);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vPlus"), 1, -1);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vStar"), 0, -1);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vOne"), 1, 1);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vThree"), 3, 3);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vOne2Three"), 1, 3);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vThree2Three"), 3, 3);
		checkMultiplicity(NameUtil.getNameable(ownedAttributes, "vThree2Star"), 3, -1);
		ocl.dispose();
	}

	public void testLoad_Bug403070_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		String testFileContents =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"package temp : EAAT = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Class1\n" +
						"	{\n" +
						"		operation testOpt(values : ecore::EDouble[*]) : ecore::EDouble\n" +
						"		{\n" +
						"			body: values->sum();\n" +
						"		}\n" +
						"		attribute variable : ecore::EDouble;\n" +
						"		attribute testAttribute : ecore::EDoubleObject { derived volatile }\n" +
						"		{\n" +
						"			derivation: self.testOpt(self.variable->asSet());\n" +
						"		}\n" +
						"	}\n" +
						"}\n";
		TestFile testFile = createFile("Bug403070.oclinecore", testFileContents);
		doLoad_Concrete(ocl, testFile.getFileURI());
		ocl.dispose();
	}

	public void testLoad_Bug401953_essentialocl() {
		OCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			EssentialOCLStandaloneSetup.doSetup();
		}
		URI uri = getTestModelURI("models/essentialocl/Bug401953.essentialocl");
		ResourceSet resourceSet = ocl.getResourceSet();
		long start = System.currentTimeMillis();
		@SuppressWarnings("unused") Resource csResource = resourceSet.getResource(uri, true);
		long end = System.currentTimeMillis();
		if ((end-start) > 5000) {		// Takes minutes when grammar bad, miniscule when grammar good but isolated test may have substantial JVM costs
			fail("Took " + 0.001*(end - start) + " seconds");
		}
		ocl.dispose();
	}

	public void testLoad_Bug450950_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		String bug450950A =
				"package bug450950 : bug450950A = 'http://www.eclipse.org/ocl/Bug450950A'\n" +
						"{\n" +
						"	abstract class Bug450950A;\n" +
						"}\n";
		createFile("Bug450950A.oclinecore", bug450950A);
		String bug450950B =
				"package bug450950 : bug450950B = 'http://www.eclipse.org/ocl/Bug450950B'\n" +
						"{\n" +
						"	abstract class Bug450950B;\n" +
						"}\n";
		createFile("Bug450950B.oclinecore", bug450950B);
		String bug450950 =
				"import bug450950a : 'Bug450950A.oclinecore'::bug450950\n" +
						"import bug450950b : 'Bug450950B.oclinecore'::bug450950\n" +
						"package bug450950a\n" +
						"context Bug450950A\n" +
						"def : isA() : Boolean = true\n" +
						"endpackage\n" +
						"package bug450950b\n" +
						"context Bug450950B\n" +
						"def : isB() : Boolean = true\n" +
						"endpackage\n";
		TestFile testFile = createFile("Bug450950.ocl", bug450950);
		String message = "\n\tambiguous xmi:id TVXWp\n\t\tbug450950\n\t\tbug450950\n\tcollision at 693728595\n\t\tbug450950\n\t\tbug450950";
		URI inputURI = testFile.getFileURI();
		String inputMessages = StringUtil.bind(PivotMessagesInternal.UnstableXMIid_ERROR_, message);
		Resource asResource = doLoad_Concrete(ocl, inputURI, inputMessages);
		assertResourceErrors("Save", asResource, inputMessages);
		ocl.dispose();
		checkLoadable(getOCLoutputURI(inputURI), inputMessages);
	}

	public void testLoad_Bug441620_completeocl() throws IOException {
		OCL ocl = createOCL();
		BaseCSResource csResource = doLoad_CS(ocl, getTestModelURI("models/ocl/Bug441620.ocl"));
		CS2AS cs2as = csResource.getCS2AS(ocl.getEnvironmentFactory());
		Resource oclResource = cs2as.getASResource();
		Model root = (Model) oclResource.getContents().get(0);
		org.eclipse.ocl.pivot.Package oclDocPackage = root.getOwnedPackages().get(0);
		assertEquals("pivot", oclDocPackage.getName());
		assertEquals("http://www.eclipse.org/ocl/2015/Pivot", oclDocPackage.getURI());

		Import _import = root.getOwnedImports().get(0);
		assertEquals("pivot", _import.getName());
		Namespace nSpace = _import.getImportedNamespace();
		assertTrue(nSpace instanceof org.eclipse.ocl.pivot.Package);
		org.eclipse.ocl.pivot.Package refPackage = (org.eclipse.ocl.pivot.Package)nSpace;
		assertEquals("pivot", nSpace.getName());
		assertEquals("http://www.eclipse.org/ocl/2015/Pivot", refPackage.getURI());
		assertNotSame(oclDocPackage, nSpace);
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		assertEquals(metamodelManager.getPrimaryPackage(oclDocPackage), metamodelManager.getPrimaryPackage(refPackage));
		ocl.dispose();
	}

	public void testLoad_Bug441620b_completeocl() throws IOException {
		OCL ocl = createOCL();
		BaseCSResource csResource = doLoad_CS(ocl, getTestModelURI("models/ocl/Bug441620b.ocl"));
		CS2AS cs2as = csResource.getCS2AS(ocl.getEnvironmentFactory());
		Resource oclResource = cs2as.getASResource();
		Model root = (Model) oclResource.getContents().get(0);
		org.eclipse.ocl.pivot.Package oclDocPackage = root.getOwnedPackages().get(0);
		assertEquals("ocl", oclDocPackage.getName());
		assertEquals("http://www.eclipse.org/ocl/2015/Library", oclDocPackage.getURI());

		Import _import = root.getOwnedImports().get(0);
		assertEquals("pivot", _import.getName());
		Namespace nSpace = _import.getImportedNamespace();
		assertTrue(nSpace instanceof org.eclipse.ocl.pivot.Package);
		org.eclipse.ocl.pivot.Package refPackage = (org.eclipse.ocl.pivot.Package)nSpace;
		assertEquals("pivot", nSpace.getName());
		assertEquals("http://www.eclipse.org/ocl/2015/Pivot", ((org.eclipse.ocl.pivot.Package)nSpace).getURI());
		assertNotSame(oclDocPackage, nSpace);
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		assertEquals(metamodelManager.getPrimaryPackage(oclDocPackage), metamodelManager.getPrimaryPackage(refPackage));
		ocl.dispose();
	}

	public void testLoad_Bug535712_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		String testEcoreContents =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"bug535712a\" nsURI=\"bug535712a\" nsPrefix=\"bug535712a\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Bug535712a\"/>\n" +
						"</ecore:EPackage>\n" +
						"\n";
		InputStream ecoreStream = new URIConverter.ReadableInputStream(testEcoreContents, "UTF-8");
		getTestFileURI("Bug535712a.ecore", ecoreStream);
		String testOCLinEcoreContents =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"import bug535712a : 'Bug535712a.ecore#/';\n" +
						"package bug535712b\n" +
						"{\n" +
						"	class Bug535712b extends bug535712a::Bug535712a\n" +
						"	{\n" +
						"	}\n" +
						"}\n";
		InputStream oclInEcoreStream = new URIConverter.ReadableInputStream(testOCLinEcoreContents, "UTF-8");
		getTestFileURI("Bug535712b.oclinecore", oclInEcoreStream);
		String testOclContents =
				"import oclstdlib : 'http://www.eclipse.org/ocl/2015/Library#/'\n" +
						"import pivot : 'http://www.eclipse.org/ocl/2015/Pivot#/'\n" +
						"import bug535712b : 'Bug535712b.oclinecore#/'\n" +
						"\n" +
						"package pivot\n" +
						"context Element\n" +
						"inv : 2 = 1 + 1\n" +
						"endpackage\n";
		InputStream inputStream = new URIConverter.ReadableInputStream(testOclContents, "UTF-8");
		URI testFileURI = getTestFileURI("Bug535712.ocl", inputStream);
		doLoad_OCL(ocl, testFileURI);
		for (Resource asResource : ocl.getEnvironmentFactory().getMetamodelManager().getASResourceSet().getResources()) {
			asResource.save(null);
		}
		ocl.dispose();
		TestFile testFileA1 = getTestFile("Bug535712a.ecore");
		TestFile testFileB1= getTestFile("Bug535712b.oclinecore");
		TestFile testFileC1 = getTestFile("Bug535712.ocl");
		assertTrue("Bug535712a.ecore should exist", testFileA1.getFile().exists());
		assertTrue("Bug535712b.oclinecore should exist", testFileB1.getFile().exists());
		assertTrue("Bug535712.ocl should exist", testFileC1.getFile().exists());
		TestFile testFileA2 = getTestFile("Bug535712a.ecore.oclas");
		TestFile testFileB2 = getTestFile("Bug535712b.oclinecore.oclas");
		TestFile testFileC2 = getTestFile("Bug535712.ocl.oclas");
		assertFalse("Bug535712a.ecore.oclas should not exist", testFileA2.getFile().exists());
		assertFalse("Bug535712b.oclinecore.oclas should not exist", testFileB2.getFile().exists());
		assertFalse("Bug535712.ocl.oclas should not exist", testFileC2.getFile().exists());
	}

	/**
	 * Verifies that adding an ASResource to an aird-containing ResourceSt fails with Resource and log errors.
	 * @throws IOException
	 */
	public void testLoad_Bug582958() throws IOException {
		if (!siriusHasUID()) {
			System.err.println(getName() + " has been disabled - prevailing Sirius version lacks IdentifiedElement.uid");
			return;
		}
		OCL ocl = createOCLWithProjectMap();
		String testEcoreContents =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n"
				+ "    name=\"p\" nsURI=\"p\" nsPrefix=\"p\"/>\n"
				+ "\n";
		InputStream ecoreStream = new URIConverter.ReadableInputStream(testEcoreContents, "UTF-8");
		URI ecoreFileURI = getTestFileURI("Bug582958.ecore", ecoreStream);
		String testOCLContents =
				"import 'Bug582958.ecore'\n"
				+ "package p\n"
				+ "endpackage\n";
		InputStream oclStream = new URIConverter.ReadableInputStream(testOCLContents, "UTF-8");
		URI oclFileURI = getTestFileURI("Bug582958.ocl", oclStream);
		String testOCLasContents =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<pivot:Model xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:pivot=\"http://www.eclipse.org/ocl/2015/Pivot\"\n"
				+ "    xsi:schemaLocation=\"http://www.eclipse.org/ocl/2015/Pivot java://org.eclipse.ocl.pivot.PivotPackage\" xmi:id=\"AAAAA\" name=\"Bug582958.ocl\" externalURI=\"platform:/resource/Bug582958/Bug582958.ocl\" xmiidVersion=\"1\">\n"
				+ "  <ownedImports importedNamespace=\"pivot:Model Bug582958.ecore#/\" xmiidVersion=\"1\"/>\n"
				+ "  <ownedPackages xmi:id=\"qHh3I\" name=\"p\" URI=\"p\"/>\n"
				+ "</pivot:Model>\n";
		InputStream oclasStream = new URIConverter.ReadableInputStream(testOCLasContents, "UTF-8");
		URI oclasFileURI = getTestFileURI("Bug582958.ocl.oclas", oclasStream);
		String testAirdContents =	// 0.0.0.0 seems to suppress migration diagnostic
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<viewpoint:DAnalysis xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:viewpoint=\"http://www.eclipse.org/sirius/1.1.0\" uid=\"_4Vj5cNO1Ee6OOqXtF7xr6g\" version=\"0.0.0.0\">\n"
				+ "  <semanticResources>" + ecoreFileURI + "</semanticResources>\n"
				+ "  <semanticResources>" + oclFileURI + "</semanticResources>\n"
				+ "  <semanticResources>" + oclasFileURI + "</semanticResources>\n"
				+ "</viewpoint:DAnalysis>\n";
		InputStream inputStream = new URIConverter.ReadableInputStream(testAirdContents, "UTF-8");
		URI airdFileURI = getTestFileURI("Bug582958.aird", inputStream);
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			String message = StringUtil.bind(PivotMessages.BadASResourceForSirius, ASResourceImpl.class.getSimpleName(), airdFileURI.toString(), oclasFileURI.toString());
			ResourceSet resourceSet = ocl.getResourceSet();
			Resource resource = resourceSet.getResource(airdFileURI, true);
			assertNoResourceErrors("Load failed", resource);
			assertNoUnresolvedProxies("Unresolved proxies", resource);
			assertResourceErrors("Load failed", resource, message);
			assertNoValidationErrors("Validation errors", resource.getContents().get(0));
			EList<@NonNull Resource> resources = resourceSet.getResources();
			assertEquals("Expected ResourceSet resources count", 3, resources.size());
			assertEquals(airdFileURI, resources.get(0).getURI());
			assertEquals(ecoreFileURI, resources.get(1).getURI());
			assertEquals(oclFileURI, resources.get(2).getURI());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertEquals("Expected error log message", message, logMessage);
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	public void testLoad_Imports_ocl() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
		OCL ocl = createOCLWithProjectMap();
		URI inputURI = getTestModelURI("models/ecore/Imports.ocl");
		doLoadOCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	public void testLoad_MiniPivot_ocl() throws IOException, InterruptedException {
		TestOCL ocl = createOCLWithProjectMap();
		ocl.getEnvironmentFactory().setSafeNavigationValidationSeverity(StatusCodes.Severity.WARNING);
		URI inputURI = getTestModelURI("models/ocl/MiniPivot.ocl");
		doLoad_OCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	/*
	 * Repeat the final checkLoadableFromXMI stage of testLoad_MiniPivot_ocl as a debug aid.
	 */
	public void testLoad_MiniPivot_oclcs() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
		URI oclOutputURI = getTestModelURI("models/ocl/MiniPivot.saved.oclcs");		// Copied from testLoad_MiniPivot_ocl outputs
		checkLoadableFromXMI(oclOutputURI);
	}

	public void testLoad_Names_ocl() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
	//	ASResourceImpl.SET_PROXY.setState(true);
	//	PartialModels.PARTIAL_MODELS.setState(true);
	//	PartialPackages.PARTIAL_PACKAGES.setState(true);
	//	PartialClasses.PARTIAL_CLASSES.setState(true);
		//	SerializationBuilder.SERIALIZATION.setState(true);
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		URI inputURI = getTestModelURI("models/ecore/Names.ocl");
		doLoadOCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	/*
	 * Repeat the final checkLoadableFromXMI stage of testLoad_Names_ocl as a debug aid.
	 */
	public void testLoad_Names_oclcs() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
		URI oclOutputURI = getTestModelURI("models/ecore/Names.saved.oclcs");		// Copied from testLoad_Names_ocl outputs
		checkLoadableFromXMI(oclOutputURI);
	}

	public void testLoad_OCLTest_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		URI inputURI = getTestModelURI("models/ecore/OCLTest.ocl");
		doLoadOCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	public void testLoadUnloadReload_OCLTest_ocl() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
	//	ASResourceImpl.SET_PROXY.setState(true);
	//	SerializationBuilder.SERIALIZATION.setState(true);
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		URI inputURI = getTestModelURI("models/ecore/OCLTest.ocl");
		@SuppressWarnings("unused") BaseCSResource csResource = doLoadOCL(ocl, inputURI);
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		ResourceSet asResourceSet = environmentFactory.getMetamodelManager().getASResourceSet();
		List<@NonNull Resource> resources = asResourceSet.getResources();
		((ASResource)resources.get(0)).setSaveable(true);
		for (Resource asResource : resources) {
			asResource.unload();
		}
		ResourceSet externalResourceSet = environmentFactory.getResourceSet();
		EcoreUtil.resolveAll(externalResourceSet);
		assertNoResourceErrors("Post resolve", externalResourceSet);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	public void testLoadSaveAsCSLoad_OCLTest_ocl() throws IOException, InterruptedException {
		URI inputURI = getTestModelURI("models/ecore/OCLTest.ocl");
		OCL ocl1 = createOCLWithProjectMap();
		try {
			@SuppressWarnings("unused") BaseCSResource csResource = doLoadOCL(ocl1, inputURI);
		}
		finally {
			ocl1.dispose();
		}
		checkLoadable(getOCLoutputURI(inputURI));

		URI xmiOutputURI = getXMIoutputURI(inputURI);
		OCL ocl2 = createOCLWithProjectMap();
		try {
			Resource xmiResource = ocl2.getResourceSet().getResource(xmiOutputURI, true);
			assertNoResourceErrors("CS load", xmiResource);
			assertNoUnresolvedProxies("CS load", xmiResource);
			assertNoValidationErrors("CS load", xmiResource);
			CS2AS cs2as = ((BaseCSXMIResource)xmiResource).getCS2AS(ocl2.getEnvironmentFactory());
			ASResource asResource = cs2as.getASResource();
			//	asResource.setSaveable(true);					// Override default AS not-saveable
			asResource.save(XMIUtil.createSaveOptions(asResource));
			assertNoResourceErrors("CS2AS load", asResource);
			assertNoUnresolvedProxies("CS2AS load", asResource);
			assertNoValidationErrors("CS2AS load", asResource);
		}
		finally {
			ocl2.dispose();
		}
	}

	public void testLoad_Pivot_ocl() throws IOException, InterruptedException {
		TestOCL ocl = createOCLWithProjectMap();
		ocl.getEnvironmentFactory().setSafeNavigationValidationSeverity(StatusCodes.Severity.WARNING);
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ocl", true);
		doLoad_OCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	public void testLoad_RoyalAndLoyal_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ocl", true);
		doLoadOCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	public void testReload_AsReload() throws Exception {
		OCL ocl1 = createOCL();
		String oclinecoreFileA =
				"package PackageA : nsPrefixA = 'http://A3'{\n" +
						"    class ClassA {\n" +
						"    	invariant InvA: self.toString() = 'ClassA';\n" +
						"    }\n" +
						"}\n";
		String ecoreFileA = createEcoreString(ocl1, "Bug382230A", oclinecoreFileA, false);
		String oclinecoreFileB =
				"package PackageB : nsPrefixB = 'http://A3'{\n" +
						"    datatype ClassB {\n" +
						"    	invariant InvB: self.toString() = 'ClassB';\n" +
						"    }\n" +
						"}\n";
		String ecoreFileB = createEcoreString(ocl1, "Bug382230B", oclinecoreFileB, false);
		String ecoreFileName = "Bug382230.ecore";
		ocl1.dispose();
		OCL ocl2 = createOCL();
		URI ecoreURI = URI.createURI(ecoreFileName);
		XMLResource ecoreResource = (XMLResource) ocl2.getResourceSet().createResource(ecoreURI, null);
		ecoreResource.load(new URIConverter.ReadableInputStream(ecoreFileA), null);
		Ecore2AS conversion = Ecore2AS.getAdapter(ecoreResource, ocl2.getEnvironmentFactory());
		Resource asResource = conversion.getASModel().eResource();
		assertEquals(1, asResource.getContents().size());
		Model pivotModel1 = (Model) asResource.getContents().get(0);
		assertEquals(ecoreFileName, pivotModel1.getName());
		assertEquals(1, pivotModel1.getOwnedPackages().size());
		org.eclipse.ocl.pivot.Package pivotPackage1 = pivotModel1.getOwnedPackages().get(0);
		assertEquals("PackageA", pivotPackage1.getName());
		assertEquals("nsPrefixA", pivotPackage1.getNsPrefix());
		assertEquals(1, pivotPackage1.getOwnedClasses().size());
		Type pivotType1 = pivotPackage1.getOwnedClasses().get(0);
		assertEquals("ClassA", pivotType1.getName());
		assertEquals("Class", pivotType1.eClass().getName());
		//
		ecoreResource.unload();
		ecoreResource.load(new URIConverter.ReadableInputStream(ecoreFileB), null);
		conversion.update(asResource, ecoreResource.getContents());
		assertEquals(1, asResource.getContents().size());
		Model pivotModel2 = (Model) asResource.getContents().get(0);
		assertEquals(ecoreFileName, pivotModel2.getName());
		assertEquals(1, pivotModel2.getOwnedPackages().size());
		org.eclipse.ocl.pivot.Package pivotPackage2 = pivotModel2.getOwnedPackages().get(0);
		assertEquals("PackageB", pivotPackage2.getName());
		assertEquals("nsPrefixB", pivotPackage2.getNsPrefix());
		assertEquals(1, pivotPackage2.getOwnedClasses().size());
		Type pivotType2 = pivotPackage2.getOwnedClasses().get(0);
		assertEquals("ClassB", pivotType2.getName());
		assertEquals("DataType", pivotType2.eClass().getName());
		//
		List<org.eclipse.ocl.pivot.Package> allPackages = new ArrayList<org.eclipse.ocl.pivot.Package>();
		//		for (org.eclipse.ocl.pivot.Package aPackage : metamodelManager2.getAllPackages()) {
		for (CompletePackage completePackage : ocl2.getStandardLibrary().getAllCompletePackages()) {
			org.eclipse.ocl.pivot.Package aPackage = completePackage.getPrimaryPackage();
			if (aPackage instanceof Model) {}
			else if (aPackage instanceof Library) {}
			else if (PivotConstants.ORPHANAGE_NAME.equals(aPackage.getName())) {}
			else {
				allPackages.add(aPackage);
			}
		}
		assertEquals(1, allPackages.size());
		ocl2.dispose();
	}

	public void testReload_AsUpdate() throws Exception {
		OCL ocl1 = createOCL();
		String oclinecoreFileXXX =
				"package PackageXXX : nsPrefixXXX = 'http://XXX'{\n" +
						"    class MutableXXX {\n" +
						"    }\n" +
						"    class ClassXXX {\n" +
						"    	invariant InvXXX: self.toString() = 'ClassXXX';\n" +
						"    	property fromXXX#toXXX: ClassXXX;\n" +
						"    	property toXXX#fromXXX: ClassXXX;\n" +
						"    }\n" +
						"}\n";
		String ecoreFileXXX = createEcoreString(ocl1, "Bug382230", oclinecoreFileXXX, true);
		String ecoreFileYYY = ecoreFileXXX
				.replaceFirst("xsi:type=\"ecore:EClass\"", "xsi:type=\"ecore:EDataType\"")
				.replaceAll("XXX", "YYY");
		String ecoreFileName = "Bug382230.ecore";
		ocl1.dispose();
		OCL ocl2 = createOCL();
		URI ecoreURI = URI.createURI(ecoreFileName);
		XMLResource ecoreResource = (XMLResource) ocl2.getResourceSet().createResource(ecoreURI, null);
		ecoreResource.load(new URIConverter.ReadableInputStream(ecoreFileXXX), null);
		Ecore2AS conversion = Ecore2AS.getAdapter(ecoreResource, ocl2.getEnvironmentFactory());
		Resource asResource = conversion.getASModel().eResource();
		assertEquals(1, asResource.getContents().size());
		Model pivotModelXXX = (Model) asResource.getContents().get(0);
		assertEquals(ecoreFileName, pivotModelXXX.getName());
		assertEquals(1, pivotModelXXX.getOwnedPackages().size());
		org.eclipse.ocl.pivot.Package pivotPackageXXX = pivotModelXXX.getOwnedPackages().get(0);
		assertEquals("PackageXXX", pivotPackageXXX.getName());
		assertEquals("nsPrefixXXX", pivotPackageXXX.getNsPrefix());
		assertEquals(2, pivotPackageXXX.getOwnedClasses().size());
		org.eclipse.ocl.pivot.Class pivotTypeXXX0 = pivotPackageXXX.getOwnedClasses().get(0);
		assertEquals("MutableXXX", pivotTypeXXX0.getName());
		assertEquals("Class", pivotTypeXXX0.eClass().getName());
		org.eclipse.ocl.pivot.Class pivotTypeXXX1 = pivotPackageXXX.getOwnedClasses().get(1);
		assertEquals("ClassXXX", pivotTypeXXX1.getName());
		assertEquals("Class", pivotTypeXXX1.eClass().getName());
		assertEquals(2, pivotTypeXXX1.getOwnedProperties().size());
		Property pivotPropertyXXX0 = pivotTypeXXX1.getOwnedProperties().get(0);
		Property pivotPropertyXXX1 = pivotTypeXXX1.getOwnedProperties().get(1);
		assertEquals("fromXXX", pivotPropertyXXX0.getName());
		assertEquals("toXXX", pivotPropertyXXX1.getName());
		assertEquals(pivotPropertyXXX1, pivotPropertyXXX0.getOpposite());
		assertEquals(pivotPropertyXXX0, pivotPropertyXXX1.getOpposite());
		//
		ecoreResource.unload();
		ecoreResource.load(new URIConverter.ReadableInputStream(ecoreFileYYY), null);
		conversion.update(asResource, ecoreResource.getContents());
		assertEquals(1, asResource.getContents().size());
		Model pivotModelYYY = (Model) asResource.getContents().get(0);
		assertEquals(ecoreFileName, pivotModelYYY.getName());
		assertEquals(1, pivotModelYYY.getOwnedPackages().size());
		org.eclipse.ocl.pivot.Package pivotPackageYYY = pivotModelYYY.getOwnedPackages().get(0);
		assertEquals("PackageYYY", pivotPackageYYY.getName());
		assertEquals("nsPrefixYYY", pivotPackageYYY.getNsPrefix());
		assertEquals(2, pivotPackageYYY.getOwnedClasses().size());
		org.eclipse.ocl.pivot.Class pivotTypeYYY0 = pivotPackageYYY.getOwnedClasses().get(0);
		assertEquals("MutableYYY", pivotTypeYYY0.getName());
		assertEquals("DataType", pivotTypeYYY0.eClass().getName());
		org.eclipse.ocl.pivot.Class pivotTypeYYY1 = pivotPackageYYY.getOwnedClasses().get(1);
		assertEquals("ClassYYY", pivotTypeYYY1.getName());
		assertEquals("Class", pivotTypeYYY1.eClass().getName());
		assertEquals(2, pivotTypeYYY1.getOwnedProperties().size());
		Property pivotPropertyYYY0 = pivotTypeYYY1.getOwnedProperties().get(0);
		Property pivotPropertyYYY1 = pivotTypeYYY1.getOwnedProperties().get(1);
		assertEquals("fromYYY", pivotPropertyYYY0.getName());
		assertEquals("toYYY", pivotPropertyYYY1.getName());
		assertEquals(pivotPropertyYYY1, pivotPropertyYYY0.getOpposite());
		assertEquals(pivotPropertyYYY0, pivotPropertyYYY1.getOpposite());

		//
		List<org.eclipse.ocl.pivot.Package> allPackages = new ArrayList<org.eclipse.ocl.pivot.Package>();
		//		for (org.eclipse.ocl.pivot.Package aPackage : metamodelManager2.getAllPackages()) {
		for (CompletePackage completePackage : ocl2.getStandardLibrary().getAllCompletePackages()) {
			org.eclipse.ocl.pivot.Package aPackage = completePackage.getPrimaryPackage();
			if (aPackage instanceof Model) {}
			else if (aPackage instanceof Library) {}
			else if (PivotConstants.ORPHANAGE_NAME.equals(aPackage.getName())) {}
			else {
				allPackages.add(aPackage);
			}
		}
		assertEquals(1, allPackages.size());
		ocl2.dispose();
	}

	public void testReload_As418412() throws Exception {
		OCL ocl1 = createOCL();
		String oclinecoreFileXXX =
				"package PackageXXX : nsPrefixXXX = 'http://XXX'{\n" +
						"    class ClassXXX {\n" +
						"    	property children: ClassXXX[*];\n" +
						"    }\n" +
						"}\n";
		String ecoreFileXXX = createEcoreString(ocl1, "Bug418412", oclinecoreFileXXX, true);
		String ecoreFileName = "Bug418412.ecore";
		ocl1.dispose();
		OCL ocl2 = createOCL();
		URI ecoreURI = URI.createURI(ecoreFileName);
		XMLResource ecoreResource = (XMLResource) ocl2.getResourceSet().createResource(ecoreURI, null);
		ecoreResource.load(new URIConverter.ReadableInputStream(ecoreFileXXX), null);
		Ecore2AS conversion = Ecore2AS.getAdapter(ecoreResource, ocl2.getEnvironmentFactory());
		ASResource asResource = (ASResource) conversion.getASModel().eResource();
		//
		//	Save the *.oclas and cache the xmi:ids
		//
		URI esasURI = getTestFileURI(ecoreFileName + PivotConstants.DOT_OCL_AS_FILE_EXTENSION);
		asResource.setSaveable(true);
		asResource.setURI(esasURI);
		Map<String, Object> options = new HashMap<String, Object>();
		//		options.put(ASResource.OPTION_INTERNAL_UUIDS, Boolean.TRUE);
		asResource.save(options);
		Map<EObject, String> eObject2id = new HashMap<EObject, String>();
		Map<String, EObject> id2eObject = new HashMap<String, EObject>();
		int oldIdCount = 0;
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			String id = asResource.getID(eObject);
			eObject2id.put(eObject, id);
			//			System.out.println(id + " ==> " + eObject);
			if (id != null) {
				id2eObject.put(id, eObject);
				oldIdCount++;
			}
		}
		assertEquals(9, oldIdCount);
		assertEquals(oldIdCount, id2eObject.size());
		//
		//	Save the *.oclas again and check that the xmi:ids are consistent
		//
		URI asURI = esasURI.trimFileExtension().trimFileExtension().appendFileExtension("oclas");
		asResource.setURI(asURI);
		asResource.save(XMIUtil.createSaveOptions(asResource));		// Bug 418412 gave a duplicate xmi:id ISE failure here.
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			String id = asResource.getID(eObject);
			//			System.out.println(id + " ==> " + eObject);
			assertEquals(eObject2id.get(eObject), id);
		}
		ocl2.dispose();
		//
		//	Load the *.oclas in a relatively standard EMF ResourceSet and check that the xmi:ids are consistent
		//
		ResourceSet resourceSet = new ResourceSetImpl();
		ASResourceFactoryRegistry.INSTANCE.configureResourceSets(null, resourceSet);
		ASResource reloadedAsResource = (ASResource)resourceSet.getResource(asURI, true);
		int newIdCount = 0;
		for (TreeIterator<EObject> tit = reloadedAsResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			String id = reloadedAsResource.getID(eObject);
			if (id != null) {
				EObject eObject1 = id2eObject.get(id);
				EObject eObject2 = ClassUtil.requireNonNull(eObject1);
				assertEquals(eObject2.getClass(), eObject.getClass());
				newIdCount++;
			}
		}
		assertEquals(oldIdCount, newIdCount);
	}
}
