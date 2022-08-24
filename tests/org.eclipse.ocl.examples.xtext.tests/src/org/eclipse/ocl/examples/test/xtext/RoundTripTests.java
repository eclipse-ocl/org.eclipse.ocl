/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.as2es.AS2UML;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS.MessageBinder;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.completeocl.as2cs.CompleteOCLSplitter;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UML402UMLExtendedMetaData;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.EmfFormatter;

/**
 * Test that an Ecore file can be loaded as OCLinEcore then saved back as Ecore.
 * Or loaded as Ecore and saved back as ECore; NB this does not serialize.
 */
public class RoundTripTests extends XtextTestCase
{
	private static final @NonNull String AS2ES_VALIDATION_ERRORS = "AS2ES_VALIDATION_ERRORS";

	public @NonNull Resource createEcoreFromPivot(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource, @NonNull URI ecoreURI) throws IOException {
		Resource ecoreResource = AS2Ecore.createResource(environmentFactory, asResource, ecoreURI, null);
		assertNoResourceErrors("To Ecore errors", ecoreResource);
		//		if (ecoreURI != null) {
		ecoreResource.save(XMIUtil.createSaveOptions());
		//		}
		return ecoreResource;
	}
	public @NonNull ASResource createPivotFromEcore(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Resource ecoreResource) throws IOException {
		Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
		Model pivotModel = ecore2as.getASModel();
		ASResource asResource = (ASResource) ClassUtil.nonNullState(pivotModel.eResource());
		assertNoResourceErrors("Ecore2AS failed", asResource);
		assertNoValidationErrors("Ecore2AS invalid", asResource);
		return asResource;
	}
	public @NonNull ASResource createPivotFromXtext(@NonNull EnvironmentFactoryInternal environmentFactory, BaseCSResource xtextResource, int expectedContentCount) throws IOException {
		try {
			ASResource asResource = xtextResource.getASResource();
			assertNoResourceErrors("To Pivot errors", xtextResource);
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			List<EObject> pivotContents = asResource.getContents();
			assertEquals(expectedContentCount, pivotContents.size());
			assertNoValidationErrors("Pivot validation errors", ClassUtil.nonNullState(pivotContents.get(0)));
			return asResource;
		}
		finally {
			xtextResource.dispose();
		}
	}
	public BaseCSResource createXtextFromPivot(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource, @NonNull URI xtextURI) throws IOException {
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		XtextResource xtextResource = (XtextResource) resourceSet.createResource(xtextURI, OCLinEcoreCSPackage.eCONTENT_TYPE);
		((BaseCSResource) xtextResource).updateFrom(asResource, environmentFactory);
		DebugTimestamp debugTimestamp = new DebugTimestamp(ClassUtil.nonNullState(xtextResource.getURI().toString()));
		xtextResource.save(XMIUtil.createSaveOptions());
		debugTimestamp.log("Serialization save done");
		assertNoResourceErrors("Conversion failed", xtextResource);
		assertNoDiagnosticErrors("Concrete Syntax validation failed", xtextResource);
		return (BaseCSResource) xtextResource;
	}
	public BaseCSResource createXtextFromURI(@NonNull EnvironmentFactoryInternal environmentFactory, URI xtextURI) throws IOException {
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		//		ProjectMap.initializeURIResourceMap(resourceSet2);
		ProjectMap.initializeURIResourceMap(null);
		//		UMLUtils.initializeContents(resourceSet2);
		BaseCSResource xtextResource = (BaseCSResource) ClassUtil.nonNullState(resourceSet.getResource(xtextURI, true));
		assertNoResourceErrors("Load failed", xtextResource);
		return xtextResource;
	}

	public CSResource createCompleteOCLXtextFromPivot(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource, @NonNull URI xtextURI) throws IOException {
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		CSResource xtextResource = (CSResource) resourceSet.createResource(xtextURI, OCLinEcoreCSPackage.eCONTENT_TYPE);
		xtextResource.updateFrom(asResource, environmentFactory);
		DebugTimestamp debugTimestamp = new DebugTimestamp(ClassUtil.nonNullState(xtextResource.getURI().toString()));
		xtextResource.save(XMIUtil.createSaveOptions());
		debugTimestamp.log("Serialization save done");
		assertNoResourceErrors("Conversion failed", xtextResource);
		assertNoDiagnosticErrors("Concrete Syntax validation failed", (XtextResource) xtextResource);
		return xtextResource;
	}

	public void doRoundTripFromCompleteOCL(URI inputURI) throws IOException, InterruptedException {
		OCL ocl0 = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet = ocl0.getResourceSet();
		MessageBinder savedMessageBinder = CS2AS.setMessageBinder(CS2AS.MessageBinderWithLineContext.INSTANCE);
		StandaloneProjectMap projectMap = getProjectMap(); //(StandaloneProjectMap)ocl0.getEnvironmentFactory().getProjectManager();
		try {
			ocl0.dispose();
			if (!resourceSet.getURIConverter().exists(inputURI, null)) {
				;				System.err.println(getTestName() + " skipped since '" + inputURI + "' is missing.");
				return;
			}
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.nonNullState(projectMap.getProjectDescriptor("org.eclipse.uml2.uml"));
				projectDescriptor.initializeURIMap(ClassUtil.nonNullState(URIConverter.URI_MAP));		// *.ecore2xml must be global
			}
			//			UMLUtils.initializeContentHandlers(resourceSet);
			//			UMLUtils.initializeContents(resourceSet);
			//			String inputName = stem + ".ocl";
			//			String outputName = stem + ".regenerated.ocl";
			URI outputURI = inputURI.trimFileExtension().appendFileExtension("regenerated.ocl");
			OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null);
			EnvironmentFactoryInternal environmentFactory1 = ocl1.getEnvironmentFactory();
			environmentFactory1.adapt(resourceSet);
			BaseCSResource xtextResource1 = createXtextFromURI(environmentFactory1, inputURI);
			ASResource pivotResource1 = createPivotFromXtext(environmentFactory1, xtextResource1, 1);
			pivotResource1.save(XMIUtil.createSaveOptions());
			ASResource pivotResource2 = ClassUtil.nonNullState(CompleteOCLSplitter.separate(environmentFactory1, pivotResource1));
			@SuppressWarnings("unused")
			CSResource xtextResource2 = createCompleteOCLXtextFromPivot(environmentFactory1, pivotResource2, outputURI);
			ocl1.dispose();
			ocl1 = null;
			//
			OCLInternal ocl3 = OCLInternal.newInstance(getProjectMap(), null);
			EnvironmentFactoryInternal environmentFactory3 = ocl3.getEnvironmentFactory();
			BaseCSResource xtextResource3 = createXtextFromURI(environmentFactory3, outputURI);
			@SuppressWarnings("unused")
			ASResource pivotResource3 = createPivotFromXtext(environmentFactory3, xtextResource3, 1);
			//			Map<@NonNull String, @Nullable Object> options = new HashMap<>();
			//			options.put(MatchOptions.OPTION_IGNORE_ID, Boolean.TRUE);
			//			options.put(MatchOptions.OPTION_IGNORE_XMI_ID, Boolean.TRUE);
			//			((NamedElement)pivotResource3.getContents().get(0)).setName(((NamedElement)pivotResource1.getContents().get(0)).getName());
			//	    	TestUtil.assertSameModel(pivotResource1, pivotResource3, options);
			ocl3.dispose();
		}
		finally {
			CS2AS.setMessageBinder(savedMessageBinder);
		}
	}

	public void doRoundTripFromEcore(@NonNull URI inputURI) throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(inputURI, inputURI, null);
	}
	public void doRoundTripFromEcore(@NonNull URI inputURI, @NonNull URI referenceURI, Map<@NonNull String, @Nullable Object> saveOptions) throws IOException, InterruptedException, ParserException {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUtil.initializeEcoreEAnnotationValidators();
		}
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromEcore(ocl.getEnvironmentFactory(), inputURI, referenceURI, saveOptions);
		ocl.dispose();
	}
	protected void doRoundTripFromEcore(@NonNull EnvironmentFactoryInternal environmentFactory, URI inputURI, URI referenceURI, Map<@NonNull String, @Nullable Object> saveOptions) throws IOException, InterruptedException, ParserException {
		String stem = inputURI.trimFileExtension().lastSegment();
		String pivotName = stem + ".ecore.oclas";
		String outputName = stem + ".regenerated.ecore";
		URI pivotURI = getTestFileURI(pivotName);
		URI outputURI = getTestFileURI(outputName);
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		Resource inputResource = ClassUtil.nonNullState(resourceSet.getResource(inputURI, true));
		assertNoResourceErrors("Ecore load", inputResource);
		assertNoValidationErrors("Ecore load", inputResource);

		Ecore2AS ecore2as = Ecore2AS.getAdapter(inputResource, environmentFactory);
		Model pivotModel = ecore2as.getASModel();
		ASResource asResource = (ASResource) pivotModel.eResource();
		boolean wasSaveable = asResource.setSaveable(true);
		asResource.setURI(pivotURI);
		assertNoResourceErrors("Ecore2AS failed", asResource);
		//		int i = 0;
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof ExpressionInOCL) {
				//				System.out.println(++i + ": " + eObject);
				ExpressionInOCL specification = (ExpressionInOCL) eObject;
				if ((specification.getOwnedBody() != null) || (specification.getBody() != null)) {
					((EnvironmentFactoryInternalExtension)environmentFactory).parseSpecification(specification);
				}
				tit.prune();
			}
		}
		asResource.save(XMIUtil.createSaveOptions(asResource));
		asResource.setSaveable(wasSaveable);
		@NonNull String @NonNull[] validationDiagnostics = saveOptions != null ? (@NonNull String @NonNull[])saveOptions.get(AS2ES_VALIDATION_ERRORS) : NO_MESSAGES;
		assertValidationDiagnostics("Ecore2AS invalid", asResource, validationDiagnostics);
		XMLResource outputResource = AS2Ecore.createResource(environmentFactory, asResource, inputURI, saveOptions);
		assertNoResourceErrors("Ecore2AS failed", outputResource);
		OutputStream outputStream = resourceSet.getURIConverter().createOutputStream(outputURI);
		outputResource.save(outputStream, XMIUtil.createSaveOptions(outputResource));
		outputStream.close();
		assertNoValidationErrors("Ecore2AS invalid", outputResource);

		//		RootPackageCS csDocument = null; // FIXME Ecore2OCLinEcore.importFromEcore(resourceSet, null, leftResource);
		//		assertNoResourceErrors("From Ecore errors", csDocument.eResource());
		//		List<PackageCS> csObjects = new ArrayList<>();
		//		csObjects.addAll(csDocument.getPackages());
		//		Resource middleResource = resourceSet.createResource(middleURI);
		//		middleResource.getContents().addAll(csObjects);
		//		middleResource.getContents().add(csDocument);
		//		middleResource.save(null);
		//		OCLinEcore2Ecore cs2e = new OCLinEcore2Ecore(resourceSet, middleResource, outputURI);
		//		Resource rightResource = cs2e.exportToEcore();
		//		assertNoResourceErrors("To Ecore errors", rightResource);
		//		rightResource.save(null);
		//		resourceSet.getResources().add(rightResource);
		if (referenceURI != null) {
			ResourceSetImpl resourceSet2 = new ResourceSetImpl();
			StandaloneProjectMap.getAdapter(resourceSet).initializeResourceSet(resourceSet2);
			Resource referenceResource = ClassUtil.nonNullState(resourceSet2.getResource(referenceURI, true));
			TestUtil.assertSameModel(referenceResource, outputResource);
			unloadResourceSet(resourceSet2);
		}
	}

	public void doRoundTripFromOCLinEcore(@NonNull TestFile testFile) throws IOException, InterruptedException {
		doRoundTripFromOCLinEcore(testFile.getFileURI());
	}

	public void doRoundTripFromOCLinEcore(@NonNull URI inputURI) throws IOException, InterruptedException {
		OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null);
		String stem = inputURI.trimFileExtension().lastSegment();
		String ecoreName = stem + ".ecore";
		String outputName = stem + ".regenerated.oclinecore";
		URI ecoreURI = getTestFileURI(ecoreName);
		URI outputURI = getTestFileURI(outputName);
		EnvironmentFactoryInternal environmentFactory1 = ocl1.getEnvironmentFactory();
		//		environmentFactory1.adapt(resourceSet);
		BaseCSResource xtextResource1 = createXtextFromURI(environmentFactory1, inputURI);
		ASResource pivotResource1 = createPivotFromXtext(environmentFactory1, xtextResource1, 1);
		Resource ecoreResource = createEcoreFromPivot(environmentFactory1, pivotResource1, ecoreURI);
		ThreadLocalExecutor.resetEnvironmentFactory();
		//
		OCLInternal ocl2 = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory2 = ocl2.getEnvironmentFactory();
		ASResource pivotResource2 = createPivotFromEcore(environmentFactory2, ecoreResource);
		@SuppressWarnings("unused")
		BaseCSResource xtextResource2 = createXtextFromPivot(environmentFactory2, pivotResource2, outputURI);
		ocl2.dispose();
		ocl2 = null;
		ThreadLocalExecutor.resetEnvironmentFactory();
		//
		OCLInternal ocl3 = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory3 = ocl3.getEnvironmentFactory();
		BaseCSResource xtextResource3 = createXtextFromURI(environmentFactory3, outputURI);
		ASResource pivotResource3 = createPivotFromXtext(environmentFactory3, xtextResource3, 1);
		pivotResource1.setSaveable(true);
		pivotResource1.save(XMIUtil.createSaveOptions(pivotResource1));
		pivotResource3.setSaveable(true);
		pivotResource3.save(XMIUtil.createSaveOptions(pivotResource3));
		String expected = EmfFormatter.listToStr(pivotResource1.getContents());
		String actual = EmfFormatter.listToStr(pivotResource3.getContents()).replace(".regenerated.oclinecore", ".oclinecore");
		assertEquals(expected, actual);
		ocl3.dispose();
		ocl1.dispose();
	}

	@Deprecated /* @deprecated - not used */
	public void doRoundTripFromUml(String stem) throws IOException, InterruptedException, ParserException {
		//		Environment.Registry.INSTANCE.registerEnvironment(
		//			OCL.createEnvironmentFactory().createEnvironment());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		//		assertNull(org.eclipse.ocl.uml.OCL.initialize(null));
		//		org.eclipse.uml2.uml.Package umlMetamodel = (org.eclipse.uml2.uml.Package) resourceSet.getResource(
		//			URI.createURI(UMLResource.UML_METAMODEL_URI),
		//			true).getContents().get(0);
		//		org.eclipse.uml2.uml.Package umlPrimitiveTypes = (org.eclipse.uml2.uml.Package) resourceSet.getResource(
		//			URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI),
		//			true).getContents().get(0);
		//		org.eclipse.uml2.uml.Package ecorePrimitiveTypes = (org.eclipse.uml2.uml.Package) resourceSet.getResource(
		//			URI.createURI(UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI),
		//			true).getContents().get(0);
		String inputName = stem + ".uml";
		String pivotName = stem + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
		String outputName = stem + ".regenerated.uml";
		URI inputURI = getProjectFileURI(inputName);
		URI pivotURI = getTestFileURI(pivotName);
		URI outputURI = getTestFileURI(outputName);
		Resource inputResource = ClassUtil.nonNullState(resourceSet.getResource(inputURI, true));
		assertNoResourceErrors("UML load", inputResource);
		assertNoValidationErrors("UML load", inputResource);

		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		UML2AS uml2as = UML2AS.getAdapter(inputResource, environmentFactory);
		Model pivotModel = uml2as.getASModel();
		Resource asResource = pivotModel.eResource();
		asResource.setURI(pivotURI);
		assertNoResourceErrors("UML2AS failed", asResource);
		asResource.save(XMIUtil.createSaveOptions());
		assertNoValidationErrors("UML2AS invalid", asResource);

		List<? extends @NonNull EObject> outputObjects = new ArrayList<>(AS2UML.createResource(environmentFactory, asResource));
		@SuppressWarnings("unchecked")
		List<? extends org.eclipse.uml2.uml.@NonNull NamedElement> castOutputObjects = (List<? extends org.eclipse.uml2.uml.@NonNull NamedElement>)outputObjects;
		outputObjects.remove(getNamedElement(castOutputObjects, "orphanage"));
		if (outputObjects.size() == 1) {
			org.eclipse.uml2.uml.Package outputPackages = ClassUtil.nonNullState(((org.eclipse.uml2.uml.Package)outputObjects.get(0)));
			outputObjects = ClassUtil.nullFree(outputPackages.getNestedPackages());
		}
		Resource outputResource = resourceSet.createResource(outputURI);
		outputResource.getContents().addAll(outputObjects);
		assertNoResourceErrors("UML2AS failed", outputResource);
		outputResource.save(XMIUtil.createSaveOptions());
		assertNoValidationErrors("UML2AS invalid", outputResource);
		TestUtil.assertSameModel(inputResource, outputResource);
		ocl.dispose();
	}

	public static <T extends org.eclipse.uml2.uml.NamedElement> @Nullable T getNamedElement(Collection<T> elements, String name) {
		if (elements == null)
			return null;
		for (T element : elements)
			if (ClassUtil.safeEquals(name, ClassUtil.nonNullState(element).getName()))
				return element;
		return null;
	}

	public void testBug350894RoundTrip() throws IOException, InterruptedException {
		String testFileContentsA =
				"package a : aa = 'aaa'\n" +
						"{\n" +
						"class A;\n" +
						"}\n";
		OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null);
		createEcoreFile(ocl1, "Bug350894A", testFileContentsA);
		ocl1.dispose();
		String testFileContentsB =
				"import aa : 'Bug350894A.ecore#/';\n" +
						"package b : bb = 'bbb'\n" +
						"{\n" +
						"class B\n" +
						"{\n" +
						"invariant alias: not oclIsKindOf(aa::A);\n" +
						"invariant nsURI: not oclIsKindOf(aaa::A);\n" +
						"invariant file: not oclIsKindOf(_'Bug350894A.ecore#/'::A);\n" +
						"}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Bug350894B.oclinecore", testFileContentsB);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testBug356243_oclinecore() throws IOException, InterruptedException {
		String testFileContents =
				"package any : any = 'http:/any'\n" +
						"{\n" +
						"	class Bug356243\n" +
						"	{\n" +
						"		property is_always_typed : OclAny { ordered };\n" +
						"	}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Bug356243.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testBug426927_oclinecore() throws IOException, InterruptedException {
		String testFileContents =
				"package any : any = 'http:/any'\n" +
						"{\n" +
						"	enum Enums\n" +
						"	{\n" +
						"		literal ONE = 1;\n" +
						"		literal MINUS_ONE = -1;\n" +
						"	}\n" +
						"	class Bug426927\n" +
						"	{\n" +
						"		annotation {\n" +
						"			reference Enums::MINUS_ONE;\n" +
						"		}\n" +
						"	}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Bug426927.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testAggregatesRoundTrip() throws IOException, InterruptedException {
		String testFileContents =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"class B\n" +
						"{\n" +
						"property bag0 : B[3..5|1] {!unique};\n" +
						"property bag1 : B[*] {!unique};\n" +
						"property bag2 : Bag(B);\n" +
						"property bag3 : B[3..5] {!unique};\n" +
						"property bag4 : Bag(B/*[1..3]*/)[4..6];\n" +	// Bug 467443
						"property bag5 : Bag(B)[4..6|1];\n" +
						"property setCollection : Set(Collection(B));\n" +
						"property collection2 : Collection(B);\n" +
						"property orderedset1 : B[*] {ordered};\n" +
						"property orderedset2 : OrderedSet(B);\n" +
						"property sequence1 : B[*] {ordered, !unique};\n" +
						"property sequence2 : Sequence(B);\n" +
						"property set1 : B[*];\n" +
						"property set2 : Set(B);\n" +
						//				"property tuple : Tuple(b : B);\n" +		// Bug 401938
						"}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Aggregates.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testAnnotationsRoundTrip_480635() throws IOException, InterruptedException {
		String testFileContents =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"enum Parameter_kind { serializable }\n" +
						"{\n" +
						"   literal DB_BOOLEAN { annotation documentation\n" +
						"   (doc = 'test');\n" +
						"   } \n" +
						"   literal ENUMERATED = 1;\n" +
						"   literal INT8 = 2;\n" +
						"   literal INT16 = 3;\n" +
						"}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Annotations.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testCardinalityRoundTrip_402767() throws IOException, InterruptedException {
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
		TestFile testFile = createOCLinEcoreFile("Cardinality.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testCommentsRoundTrip_405145() throws IOException, InterruptedException {
		String testFileContents =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"/* a simple comment */\n" +
						"class B\n" +
						"{\n" +
						"/*\n" +
						" * a multi line comment\n" +
						" */\n" +
						"property c1 : Real;\n" +
						"/* another \n" +
						" * multi line comment\n" +
						" */\n" +
						"property c2 : Real;\n" +
						"/* an unformatted \n" +
						" multi line comment\n" +
						" */\n" +
						"property c3 : Real;\n" +
						"}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Comments.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testGenericsRoundTrip_468846() throws IOException, InterruptedException {
		String testFileContents =
				"package basket : basket = 'http://www.example.org/basket'\n" +
						"{\n" +
						"	abstract class Fruit;\n" +
						"	abstract class Basket(T extends Fruit)\n" +
						"	{\n" +
						"		property fruit : T[*] { ordered };\n" +
						"	}\n" +
						"}";
		TestFile testFile = createOCLinEcoreFile("Bug468846.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testGenericsRoundTrip_492800() throws IOException, InterruptedException {
		String testFileContents =
				"package example : example = 'http://www.example.org/generics/opposite'\n" +
						"{\n" +
						"	abstract class Interface(T extends Event)\n" +
						"	{\n" +
						"		property events#interface : T[*] { ordered composes };\n" +
						"		attribute name : String[1];\n" +
						"	}\n" +
						"	class CallInterface extends Interface(CallEvent);\n" +
						"	class ReplyInterface extends Interface(ReplyEvent);\n" +
						"	abstract class Event\n" +
						"	{\n" +
						"		property interface#events : Interface(Event)[?];\n" +
						"		attribute name : String[1];\n" +
						"	}\n" +
						"	abstract class CallEvent extends Event;\n" +
						"	class ReplyEvent extends Event;\n" +
						"}";
		TestFile testFile = createOCLinEcoreFile("Bug492800.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testInvariantCommentsRoundTrip_410682() throws IOException, InterruptedException {
		String testFileContents =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"class B\n" +
						"{\n" +
						"/* an invariant comment */\n" +
						"invariant t : true;\n" +
						"/* an operation comment */\n" +
						"operation op(/* a parameter comment */p : Boolean, /* another parameter comment */q : Boolean) : Boolean\n" +
						"{transient}\n" +
						"{\n" +
						"/* a precondition comment */\n" +
						"precondition: p;\n" +
						"/* another precondition comment */\n" +
						"precondition too: p;\n" +
						//Not supported				"/* a body comment */\n" +
						"body: p or q;\n" +
						"/* a postcondition comment */\n" +
						"postcondition: result = p;\n" +
						"/* another postcondition comment */\n" +
						"postcondition too: result = q;\n" +
						"}\n" +
						"}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("InvariantComments.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testCompanyRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Company.ecore"), getTestModelURI("models/ecore/Company.reference.ecore"), null);
	}

	public void testEcoreRoundTrip() throws IOException, InterruptedException, ParserException {
		// FIXME need a private ProjectMap to avoid merging a relic Ecore EPackage and the provate copy
		//   for now just update the provate copy to eliminate divergence
		doRoundTripFromEcore(getTestModelURI("models/ecore/Ecore.ecore"));
	}

	public void testEmptyRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Empty.ecore"));
	}

	public void testImportsRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Imports.ecore"));
	}

	public void testKeysRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Keys.ecore"));
	}

	public void testBug492960RoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Bug492960.ecore"));
	}

	public void testBug510729_oclinecore() throws IOException, InterruptedException {
		String testFileContents =
				"package bug510729 : pfx = 'http:/org/eclipse/ocl/examples/test/xtext/models/Bug510729.oclinecore' {\n" +
						"	class Artefact {\n" +
						"		operation paths(types : ocl::OclType) : ocl::Sequence(Artefact)[*];\n" +
						"	}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Bug510729.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testBug516274_oclinecore() throws IOException, InterruptedException {
		String testFileContents =
				"package bug516274 : my = 'http:/org/eclipse/ocl/examples/test/xtext/models/Bug516274.oclinecore'\n" +
						"{\n" +
						"  abstract class Generic(T extends Generic(T));\n" +
						"  class Concrete extends Generic(Concrete);\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Bug516274.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testBug521094_oclinecore() throws IOException, InterruptedException {
		String testFileContents =
				"import 'http://www.eclipse.org/emf/2002/Ecore';\n" +
						"import 'http://www.eclipse.org/emf/2003/XMLType';\n" +
						"\n" +
						"package stk : stk = 'http://stk' {}";
		TestFile testFile = createOCLinEcoreFile("Bug521094.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testCompleteOCLRoundTrip_Bug496768() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
		getTestFileURI("Bug496768.ecore", ocl, getTestModelURI("models/ecore/Bug496768.ecore"));
		URI inputURI = getTestFileURI("Fruit.ocl", ocl, getTestModelURI("models/ecore/Bug496768.ocl"));
		ocl.dispose();
		doRoundTripFromCompleteOCL(inputURI);
	}

	public void testCompleteOCLRoundTrip_Fruit() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = OCL.newInstance(getProjectMap());
		Map<URI, URI> uriMap = ocl.getResourceSet().getURIConverter().getURIMap();
		uriMap.putAll(UML402UMLExtendedMetaData.getURIMap());
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		getTestFileURI("Fruit.uml", ocl, getTestModelURI("models/uml/Fruit.uml"));
		URI inputURI = getTestFileURI("Fruit.ocl", ocl, getTestModelURI("models/uml/Fruit.ocl"));
		ocl.dispose();
		doRoundTripFromCompleteOCL(inputURI);
	}

	//	public void testCompleteOCLRoundTrip_Infrastructure() throws IOException, InterruptedException {
	//		OCL ocl = OCL.newInstance(getProjectMap());
	//		doRoundTripFromCompleteOCL("models/uml/Infrastructure.ocl");
	//		ocl.dispose();
	//	}

	public void testCompleteOCLRoundTrip_Maps() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
		getTestFileURI("Maps.ecore", ocl, getTestModelURI("models/ecore/Maps.ecore"));
		URI inputURI = getTestFileURI("Maps.ocl", ocl, getTestModelURI("models/ecore/Maps.ocl"));
		ocl.dispose();
		doRoundTripFromCompleteOCL(inputURI);
	}

	public void testCompleteOCLRoundTrip_Names() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
		getTestFileURI("Names.ecore", ocl, getTestModelURI("models/ecore/Names.ecore"));
		URI inputURI = getTestFileURI("Names.ocl", ocl, getTestModelURI("models/ecore/Names.ocl"));
		ocl.dispose();
		doRoundTripFromCompleteOCL(inputURI);
	}

	public void testCompleteOCLRoundTrip_UML() throws IOException, InterruptedException {
		URI uml_2_5 = URI.createPlatformResourceURI("UML-2.5/XMI-5-Jan-2012/Semanticed UML.ocl", false);
		doRoundTripFromCompleteOCL(uml_2_5);
	}

	public void testMaps_oclinecore() throws IOException, InterruptedException {
		String testFileContents =
				"package any : any = 'http:/any'\n" +
						"{\n" +
						"	class Maps\n" +
						"	{\n" +
						//			"		property eMap1 : OrderedSet(KeyToValue);\n" +
						"		property eMap2 : KeyToValue[*|1] { ordered, unique };\n" +
						"		property eMap2o : KeyToValue[*|?] { ordered, unique };\n" +
						"		property oMap1 : Map(String[1],Integer[?]);\n" +
						"		property oMap2 : Map(String[1],Integer[1]);\n" +
						"		property oMap3 : Map(String[?],Integer[?]);\n" +
						"		property oMap4 : Map(String[?],Integer[1]);\n" +
						"		property oMap5 : Map(String[1],Integer[?])[1];\n" +
						"		property oMap6 : Map(String[1],Integer[1])[1];\n" +
						"		property oMap7 : Map(String[?],Integer[?])[1];\n" +
						"		property oMap8 : Map(String[?],Integer[1])[1];\n" +
						"		invariant MapIterators: Map{1 with 1}->collect(k with v | v * k)->notEmpty();\n" +
						"	}\n" +
						"	class KeyToValue : 'java.util.Map$Entry'\n" +
						"	{\n" +
						"		property key : String[1];\n" +
						"		property value : Integer[1];\n" +
						"	}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("Maps.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testMultiplicitiesRoundTrip_540244() throws IOException, InterruptedException {
		String testFileContents =
				"package p : p = 'p'\n" +
						"{\n" +
						"	class c\n" +
						"	{\n" +
						"		property endpoints : Port[*|1] { ordered };\n" +
						"		property ports : Port[2|1] { ordered } {\n" +
						"			initial: let v = OrderedSet{endpoints->first(), endpoints->last()}->oclAsType(OrderedSet(Port[2|1])) in v;\n" +
						"		}\n" +
						"	}\n" +
						"	class Port;\n" +
						"}";
		TestFile testFile = createOCLinEcoreFile("Multiplicities.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testOCLinEcoreCSTRoundTrip() throws IOException, InterruptedException, ParserException {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.oclinecore/model/OCLinEcoreCS.ecore", true);
		//		String stem = uri.trimFileExtension().lastSegment();
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		getProjectMap().configureLoadFirst(environmentFactory.getResourceSet(), EcorePackage.eNS_URI);
		doRoundTripFromEcore(environmentFactory, uri, uri, null); //null);				// FIXME Compare is not quite right
		ocl.dispose();
	}

	public void testPivotRoundTrip() throws IOException, InterruptedException, ParserException {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ecore", true);
		Map<@NonNull String, @Nullable Object> saveOptions = new HashMap<>();
		saveOptions.put(AS2Ecore.OPTION_INVARIANT_PREFIX, "validate");
		saveOptions.put(AS2Ecore.OPTION_GENERATE_STRUCTURAL_XMI_IDS, Boolean.TRUE);
		doRoundTripFromEcore(uri, uri, saveOptions);
	}

	//	public void testEssentialOCLCSTRoundTrip() throws IOException, InterruptedException {
	//		ProjectMap.getAdapter(resourceSet);
	//		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.essentialocl/model/EssentialOCLCST.ecore", true);
	//		doRoundTripFromEcore(uri, "EssentialOCLCST");
	//	}

	public void testOCLstdlibRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/OCLstdlib.ecore"));
	}

	public void testOCLRoundTrip() throws IOException, InterruptedException, ParserException {
		Map<@NonNull String, @Nullable Object> options = new HashMap<>();
		options.put(AS2Ecore.OPTION_ADD_INVARIANT_COMMENTS, true);
		doRoundTripFromEcore(getTestModelURI("models/ecore/OCL.ecore"), getTestModelURI("models/ecore/OCL.ecore"), options); // "OCL.reference");
	}

	public void testOCLCSTRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/OCLCST.ecore"));
	}

	public void testOCLEcoreRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/OCLEcore.ecore"));
	}

	/** FIXME
	public void testQVTRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/QVT.ecore"));
	} */

	/* BUG 528359
	public void testUML25RoundTrip() throws IOException, InterruptedException, ParserException {
		UMLStandaloneSetup.init();
		OCLDelegateDomain.lazyInitializeGlobals(ClassUtil.nonNullState(OCLConstants.OCL_DELEGATE_URI), true);
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/UML.ecore", true);
		Map<@NonNull String, @Nullable Object> options = new HashMap<>();
		options.put(AS2Ecore.OPTION_ADD_INVARIANT_COMMENTS, true);
		options.put(DelegateInstaller.OPTION_BOOLEAN_INVARIANTS, true);
		options.put(ClassUtil.nonNullState(OCLConstants.OCL_DELEGATE_URI), OCLConstants.OCL_DELEGATE_URI);
		options.put(DelegateInstaller.OPTION_OMIT_SETTING_DELEGATES, true);
		options.put(AS2ES_VALIDATION_ERRORS, new @NonNull String[] {
			// FIXME result conformance invariant is inadequate
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::Association::endType() : Set(UML::Type[+|1])'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::LiteralUnlimitedNatural::unlimitedValue() : UML::UnlimitedNaturalObject[?]'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::MultiplicityElement::upper() : UML::UnlimitedNaturalObject[?]'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::MultiplicityElement::upperBound() : UnlimitedNatural[1]'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::Operation::returnResult() : Set(UML::Parameter)'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::StructuredClassifier::part() : Set(UML::Property)'"
		});
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromEcore(ocl.getEnvironmentFactory(), uri, uri, options);
		ocl.dispose();
	} */

	/* BUG 528359
	public void testUMLRoundTrip() throws IOException, InterruptedException, ParserException {
		UMLStandaloneSetup.init();
		OCLDelegateDomain.lazyInitializeGlobals(ClassUtil.nonNullState(OCLConstants.OCL_DELEGATE_URI), true);
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		URI uri = URI.createPlatformResourceURI("/org.eclipse.uml2.uml/model/UML.ecore", true);
		Map<@NonNull String, @Nullable Object> options = new HashMap<>();
		options.put(AS2Ecore.OPTION_ADD_INVARIANT_COMMENTS, true);
		options.put(DelegateInstaller.OPTION_BOOLEAN_INVARIANTS, true);
		options.put(ClassUtil.nonNullState(OCLConstants.OCL_DELEGATE_URI), OCLConstants.OCL_DELEGATE_URI);
		options.put(DelegateInstaller.OPTION_OMIT_SETTING_DELEGATES, true);
		options.put(AS2ES_VALIDATION_ERRORS, new @NonNull String[] {
			// FIXME result conformance invariant is inadequate
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::Association::endType() : Set(UML::Type[+|1])'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::LiteralUnlimitedNatural::unlimitedValue() : UML::UnlimitedNaturalObject[?]'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::MultiplicityElement::upper() : UML::UnlimitedNaturalObject[?]'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::MultiplicityElement::upperBound() : UnlimitedNatural[1]'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::Operation::returnResult() : Set(UML::Parameter)'",
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::StructuredClassifier::part() : Set(UML::Property)'"
		});
		StandaloneProjectMap projectMap = EMFPlugin.IS_ECLIPSE_RUNNING ? new ProjectMap(true) : new StandaloneProjectMap(true);
		OCLInternal ocl = OCLInternal.newInstance(projectMap, null);
		projectMap.configure(ocl.getEnvironmentFactory().getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		doRoundTripFromEcore(ocl.getEnvironmentFactory(), uri, uri, options);
		ocl.dispose();
	} */

	public void testSysMLRoundTrip() throws IOException, InterruptedException {
		String testFileContents =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"class B\n" +
						"{\n" +
						"sysml { stereotype = 'SysML::Block'; }\n" +
						"}\n" +
						"}\n";
		TestFile testFile = createOCLinEcoreFile("SysML.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testTuplesRoundTrip_509533a() throws IOException, InterruptedException {
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		String testFileContents =
				"package bug509533 : bug509533 = 'http://www.example.org/bug509533'\n" +
						"{\n" +
						"	datatype HSV : 'java.lang.String';\n" +
						"	datatype RGB : 'java.lang.String';\n" +
						"	class Bug509533\n" +
						"	{\n" +
						"		operation hsv2rgb(color : HSV) : RGB[1]\n" +
						"{\n" +
						"	body: let hsv : Sequence(String) = color.tokenize(',') in\n" +
						"	let h : Integer = hsv->at(1).toReal().round() in\n" +
						"	let s : Real = hsv->at(2).toReal()/100.0 in\n" +
						"	let v : Real = hsv->at(3).toReal()/100.0 in\n" +
						"	let c : Real = v * s in\n" +
						"	let hh1 : Real = h/120 in\n" +
						"	let hh2 : Real = 2 * (hh1 - hh1.floor()) in\n" +
						"	let x : Real = c * (1 - (hh2 - 1).abs()) in\n" +
						"	let m : Real = v -c in\n" +
						"	let t : Tuple(r:Real,g:Real,b:Real) =\n" +
						"	if h < 60 then Tuple{r=c,g=x,b=0.0}\n" +
						"	elseif h < 120 then Tuple{r=x,g=c,b=0.0}\n" +
						"	elseif h < 180 then Tuple{r=0.0,g=c,b=x}\n" +
						"	elseif h < 240 then Tuple{r=0.0,g=x,b=c}\n" +
						"	elseif h < 300 then Tuple{r=x,g=0.0,b=c}\n" +
						"	else Tuple{r=c,g=0.0,b=x} endif in\n" +
						"	let r = (255 * (t.r + m)).round() in\n" +
						"	let g = (255 * (t.g + m)).round() in\n" +
						"	let b = (255 * (t.b + m)).round() in\n" +
						"	RGB{value=r.toString() + ',' + g.toString() + ',' + b.toString()};\n" +
						"}\n" +
						"\n" +
						"	}\n" +
						"}";
		TestFile testFile = createOCLinEcoreFile("Bug509533a.oclinecore", testFileContents);
		doRoundTripFromOCLinEcore(testFile);
	}

	public void testTuplesRoundTrip_509533b() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Bug509533b.ecore"));
	}

	public void testTypes_ecore() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/Types.ecore"));
	}

	public void testTypes_oclinecore() throws IOException, InterruptedException {
		//		BaseScopeProvider.LOOKUP.setState(true);
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		TestFile testFile = getTestFile("Types.oclinecore", ocl, getTestModelURI("models/oclinecore/Types.oclinecore"));
		ocl.dispose();
		doRoundTripFromOCLinEcore(testFile.getFileURI());
	}

	public void testXMLNamespaceRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/XMLNamespace.ecore"));
	}

	public void testXMLTypeRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(getTestModelURI("models/ecore/XMLType.ecore"));
	}

	//	public void testMy_uml() throws IOException, InterruptedException {
	//		doRoundTripFromUml("My");
	//	}

	//	public void testTriangle_uml() throws IOException, InterruptedException {
	//		doRoundTripFromUml("Triangle");
	//	}

	//	public void testProfile_less_Ecore_metamodel_uml() throws IOException, InterruptedException {
	//		doRoundTripFromUml("Profile-less-Ecore.metamodel");
	//	}
}
