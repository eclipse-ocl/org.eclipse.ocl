/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.resource.ASSaver;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.as2es.AS2UML;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
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
 */
public class RoundTripTests extends XtextTestCase
{
	public @NonNull Resource createEcoreFromPivot(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource, @NonNull URI ecoreURI) throws IOException {
		Resource ecoreResource = AS2Ecore.createResource(environmentFactory, asResource, ecoreURI, null);
		assertNoResourceErrors("To Ecore errors", ecoreResource);
		//		if (ecoreURI != null) {
		ecoreResource.save(null);
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
		xtextResource.save(null);
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
		xtextResource.save(null);
		assertNoResourceErrors("Conversion failed", xtextResource);
		assertNoDiagnosticErrors("Concrete Syntax validation failed", (XtextResource) xtextResource);
		return xtextResource;
	}

	public void doRoundTripFromCompleteOCL(@NonNull OCL ocl, URI inputURI) throws IOException, InterruptedException {
		ResourceSet resourceSet = ocl.getResourceSet();
		MessageBinder savedMessageBinder = CS2AS.setMessageBinder(CS2AS.MessageBinderWithLineContext.INSTANCE);
		StandaloneProjectMap projectMap = (StandaloneProjectMap)ocl.getEnvironmentFactory().getProjectManager();
		try {
			projectMap.initializeResourceSet(resourceSet);
			if (!resourceSet.getURIConverter().exists(inputURI, null)) {
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
			pivotResource1.save(null);
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
			//			Map<String,Object> options = new HashMap<String,Object>();
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

	public void doRoundTripFromEcore(String stem) throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore(stem, stem, null);
	}
	public void doRoundTripFromEcore(String stem, String reference, Map<String,Object> saveOptions) throws IOException, InterruptedException, ParserException {
		String inputName = stem + ".ecore";
		URI inputURI = getProjectFileURI(inputName);
		String referenceName = reference + ".ecore";
		URI referenceURI = getProjectFileURI(referenceName);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromEcore(ocl.getEnvironmentFactory(), inputURI, referenceURI, saveOptions);
		ocl.dispose();
	}
	public void doRoundTripFromEcore(URI inputURI, URI referenceURI, Map<String,Object> saveOptions) throws IOException, InterruptedException, ParserException {
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromEcore(ocl.getEnvironmentFactory(), inputURI, referenceURI, saveOptions);
		ocl.dispose();
	}
	protected void doRoundTripFromEcore(@NonNull EnvironmentFactoryInternal environmentFactory, URI inputURI, URI referenceURI, Map<String,Object> saveOptions) throws IOException, InterruptedException, ParserException {
		String stem = inputURI.trimFileExtension().lastSegment();
		String pivotName = stem + ".ecore.oclas";
		String outputName = stem + ".regenerated.ecore";
		URI pivotURI = getProjectFileURI(pivotName);
		URI outputURI = getProjectFileURI(outputName);
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		Resource inputResource = ClassUtil.nonNullState(resourceSet.getResource(inputURI, true));
		assertNoResourceErrors("Ecore load", inputResource);
		assertNoValidationErrors("Ecore load", inputResource);

		Ecore2AS ecore2as = Ecore2AS.getAdapter(inputResource, environmentFactory);
		Model pivotModel = ecore2as.getASModel();
		Resource asResource = pivotModel.eResource();
		asResource.setURI(pivotURI);
		assertNoResourceErrors("Ecore2AS failed", asResource);
		asResource.save(null);
		assertNoValidationErrors("Ecore2AS invalid", asResource);
		//		int i = 0;
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof ExpressionInOCL) {
				//				System.out.println(++i + ": " + eObject);
				ExpressionInOCL specification = (ExpressionInOCL) eObject;
				if ((specification.getOwnedBody() != null) || (specification.getBody() != null)) {
					environmentFactory.getMetamodelManager().parseSpecification(specification);
				}
				tit.prune();
			}
		}
		Resource outputResource = AS2Ecore.createResource(environmentFactory, asResource, inputURI, saveOptions);
		assertNoResourceErrors("Ecore2AS failed", outputResource);
		OutputStream outputStream = resourceSet.getURIConverter().createOutputStream(outputURI);
		outputResource.save(outputStream, null);
		outputStream.close();
		assertNoValidationErrors("Ecore2AS invalid", outputResource);

		//		RootPackageCS csDocument = null; // FIXME Ecore2OCLinEcore.importFromEcore(resourceSet, null, leftResource);
		//		assertNoResourceErrors("From Ecore errors", csDocument.eResource());
		//		List<PackageCS> csObjects = new ArrayList<PackageCS>();
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
		}
	}

	public void doRoundTripFromOCLinEcore(@NonNull OCL ocl1, String stem) throws IOException, InterruptedException {
		String inputName = stem + ".oclinecore";
		String ecoreName = stem + ".ecore";
		String outputName = stem + ".regenerated.oclinecore";
		URI inputURI = getProjectFileURI(inputName);
		URI ecoreURI = getProjectFileURI(ecoreName);
		URI outputURI = getProjectFileURI(outputName);
		EnvironmentFactoryInternal environmentFactory1 = (EnvironmentFactoryInternal) ocl1.getEnvironmentFactory();
		//		environmentFactory1.adapt(resourceSet);
		BaseCSResource xtextResource1 = createXtextFromURI(environmentFactory1, inputURI);
		ASResource pivotResource1 = createPivotFromXtext(environmentFactory1, xtextResource1, 1);
		Resource ecoreResource = createEcoreFromPivot(environmentFactory1, pivotResource1, ecoreURI);
		//
		OCLInternal ocl2 = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory2 = ocl2.getEnvironmentFactory();
		ASResource pivotResource2 = createPivotFromEcore(environmentFactory2, ecoreResource);
		@SuppressWarnings("unused")
		BaseCSResource xtextResource2 = createXtextFromPivot(environmentFactory2, pivotResource2, outputURI);
		ocl2.dispose();
		//
		OCLInternal ocl3 = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory3 = ocl3.getEnvironmentFactory();
		BaseCSResource xtextResource3 = createXtextFromURI(environmentFactory3, outputURI);
		ASResource pivotResource3 = createPivotFromXtext(environmentFactory3, xtextResource3, 1);
		ASSaver asSaver1 = new ASSaver(pivotResource1);
		asSaver1.localizeSpecializations();
		ASSaver asSaver3 = new ASSaver(pivotResource3);
		asSaver3.localizeSpecializations();
		String expected = EmfFormatter.listToStr(pivotResource1.getContents());
		String actual = EmfFormatter.listToStr(pivotResource3.getContents()).replace(".regenerated.oclinecore", ".oclinecore");
		assertEquals(expected, actual);
		ocl3.dispose();
	}

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
		URI pivotURI = getProjectFileURI(pivotName);
		URI outputURI = getProjectFileURI(outputName);
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
		asResource.save(null);
		assertNoValidationErrors("UML2AS invalid", asResource);

		List<? extends @NonNull EObject> outputObjects = new ArrayList<@NonNull EObject>(AS2UML.createResource(environmentFactory, asResource));
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
		outputResource.save(null);
		assertNoValidationErrors("UML2AS invalid", outputResource);
		TestUtil.assertSameModel(inputResource, outputResource);
		ocl.dispose();
	}

	public static <T extends org.eclipse.uml2.uml.NamedElement> @Nullable T getNamedElement(Collection<T> elements, String name) {
		if (elements == null)
			return null;
		for (T element : elements)
			if (ClassUtil.safeEquals(name, element.getName()))
				return element;
		return null;
	}

	public void testBug350894RoundTrip() throws IOException, InterruptedException {
		String testFileA =
				"package a : aa = 'aaa'\n" +
						"{\n" +
						"class A;\n" +
						"}\n";
		OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null);
		createEcoreFile(ocl1, "Bug350894A", testFileA);
		ocl1.dispose();
		String testFileB =
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
		createOCLinEcoreFile("Bug350894B.oclinecore", testFileB);
		OCLInternal ocl2 = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl2, "Bug350894B");
		ocl2.dispose();
	}

	public void testBug356243_oclinecore() throws IOException, InterruptedException {
		String testFile =
				"package any : any = 'http:/any'\n" +
						"{\n" +
						"	class Bug356243\n" +
						"	{\n" +
						"		property is_always_typed : OclAny { ordered };\n" +
						"	}\n" +
						"}\n";
		createOCLinEcoreFile("Bug356243.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Bug356243");
		ocl.dispose();
	}

	public void testBug426927_oclinecore() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("Bug426927.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Bug426927");
		ocl.dispose();
	}

	public void testAggregatesRoundTrip() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("Aggregates.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Aggregates");
		ocl.dispose();
	}

	public void testAnnotationsRoundTrip_480635() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("Annotations.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Annotations");
		ocl.dispose();
	}

	public void testCardinalityRoundTrip_402767() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("Cardinality.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Cardinality");
		ocl.dispose();
	}

	public void testCommentsRoundTrip_405145() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("Comments.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Comments");
		ocl.dispose();
	}

	public void testGenericsRoundTrip_468846() throws IOException, InterruptedException {
		String testFile =
				"package basket : basket = 'http://www.example.org/basket'\n" +
						"{\n" +
						"	abstract class Fruit;\n" +
						"	abstract class Basket(T extends Fruit)\n" +
						"	{\n" +
						"		property fruit : T[*] { ordered };\n" +
						"	}\n" +
						"}";
		createOCLinEcoreFile("Bug468846.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Bug468846");
		ocl.dispose();
	}

	public void testGenericsRoundTrip_492800() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("Bug492800.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Bug492800");
		ocl.dispose();
	}

	public void testInvariantCommentsRoundTrip_410682() throws IOException, InterruptedException {
		String testFile =
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
		createOCLinEcoreFile("InvariantComments.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "InvariantComments");
		ocl.dispose();
	}

	public void testCompanyRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Company", "Company.reference", null);
	}

	public void testEcoreRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Ecore");
	}

	public void testEmptyRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Empty");
	}

	public void testImportsRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Imports");
	}

	public void testKeysRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Keys");
	}

	public void testBug492960RoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Bug492960");
	}

	public void testCompleteOCLRoundTrip_Bug496768() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
		doRoundTripFromCompleteOCL(ocl, getProjectFileURI("Bug496768.ocl"));
		ocl.dispose();
	}

	public void testCompleteOCLRoundTrip_Fruit() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = OCL.newInstance(getProjectMap());
		Map<URI, URI> uriMap = ocl.getResourceSet().getURIConverter().getURIMap();
		uriMap.putAll(UML402UMLExtendedMetaData.getURIMap());
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		doRoundTripFromCompleteOCL(ocl, getProjectFileURI("Fruit.ocl"));
		ocl.dispose();
	}

	//	public void testCompleteOCLRoundTrip_Infrastructure() throws IOException, InterruptedException {
	//		doRoundTripFromCompleteOCL("Infrastructure");
	//	}

	public void testCompleteOCLRoundTrip_Names() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
		doRoundTripFromCompleteOCL(ocl, getProjectFileURI("Names.ocl"));
		ocl.dispose();
	}

	public void testCompleteOCLRoundTrip_UML() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
		URI uml_2_5 = URI.createPlatformResourceURI("UML-2.5/XMI-5-Jan-2012/Semanticed UML.ocl", false);
		doRoundTripFromCompleteOCL(ocl, uml_2_5);
		ocl.dispose();
	}

	public void testOCLinEcoreCSTRoundTrip() throws IOException, InterruptedException, ParserException {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.oclinecore/model/OCLinEcoreCS.ecore", true);
		//		String stem = uri.trimFileExtension().lastSegment();
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.nonNullState(getProjectMap().getProjectDescriptor("org.eclipse.emf.ecore"));
		StandaloneProjectMap.IPackageDescriptor packageDescriptor = ClassUtil.nonNullState(projectDescriptor.getPackageDescriptor(URI.createURI(EcorePackage.eNS_URI)));
		packageDescriptor.configure(environmentFactory.getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		doRoundTripFromEcore(environmentFactory, uri, uri, null); //null);				// FIXME Compare is not quite right
		ocl.dispose();
	}

	public void testPivotRoundTrip() throws IOException, InterruptedException, ParserException {
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ecore", true);
		doRoundTripFromEcore(uri, uri, null);
	}

	//	public void testEssentialOCLCSTRoundTrip() throws IOException, InterruptedException {
	//		ProjectMap.getAdapter(resourceSet);
	//		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.essentialocl/model/EssentialOCLCST.ecore", true);
	//		doRoundTripFromEcore(uri, "EssentialOCLCST");
	//	}

	public void testOCLstdlibRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("OCLstdlib");
	}

	public void testOCLRoundTrip() throws IOException, InterruptedException, ParserException {
		Map<String,Object> options = new HashMap<String, Object>();
		options.put(AS2Ecore.OPTION_ADD_INVARIANT_COMMENTS, true);
		doRoundTripFromEcore("OCL", "OCL", options); // "OCL.reference");
	}

	public void testOCLCSTRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("OCLCST");
	}

	public void testOCLEcoreRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("OCLEcore");
	}

	/* BUG 377626
	public void testQVTRoundTrip() throws IOException, InterruptedException {
		doRoundTripFromEcore("QVT");
	} */

	public void testUML25RoundTrip() throws IOException, InterruptedException, ParserException {
		UMLStandaloneSetup.init();
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/UML.ecore", true);
		Map<String,Object> options = new HashMap<String, Object>();
		options.put(AS2Ecore.OPTION_ADD_INVARIANT_COMMENTS, true);
		options.put(DelegateInstaller.OPTION_BOOLEAN_INVARIANTS, true);
		options.put(OCLConstants.OCL_DELEGATE_URI, OCLConstants.OCL_DELEGATE_URI);
		options.put(DelegateInstaller.OPTION_OMIT_SETTING_DELEGATES, true);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromEcore(ocl.getEnvironmentFactory(), uri, uri, options);
		ocl.dispose();
	}

	public void testSysMLRoundTrip() throws IOException, InterruptedException {
		String testFile =
				"package b : bb = 'bbb'\n" +
						"{\n" +
						"class B\n" +
						"{\n" +
						"sysml { stereotype = 'SysML::Block'; }\n" +
						"}\n" +
						"}\n";
		createOCLinEcoreFile("SysML.oclinecore", testFile);
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "SysML");
		ocl.dispose();
	}

	public void testTypes_ecore() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("Types");
	}

	public void testTypes_oclinecore() throws IOException, InterruptedException {
		//		BaseScopeProvider.LOOKUP.setState(true);
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		doRoundTripFromOCLinEcore(ocl, "Types");
		ocl.dispose();
	}

	public void testXMLNamespaceRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("XMLNamespace");
	}

	public void testXMLTypeRoundTrip() throws IOException, InterruptedException, ParserException {
		doRoundTripFromEcore("XMLType");
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
