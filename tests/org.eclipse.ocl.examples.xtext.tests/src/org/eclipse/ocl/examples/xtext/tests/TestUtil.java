/*******************************************************************************
 * Copyright (c) 2015, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform; */
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.AbstractPivotTestCase;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase.EAnnotationConstraintsNormalizer;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase.EAnnotationsNormalizer;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase.EDetailsNormalizer;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase.EOperationsNormalizer;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase.ETypedElementNormalizer;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase.Normalizer;
import org.eclipse.ocl.pivot.internal.validation.PivotEAnnotationValidator;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.idioms.IdiomsStandaloneSetup;
import org.eclipse.ocl.xtext.markup.MarkupStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.util.EmfFormatter;

import com.google.inject.Guice;

import junit.framework.TestCase;

public class TestUtil
{
	public static void assertNoResourceErrors(@NonNull String prefix, @NonNull Resource resource) {
		@NonNull EList<Diagnostic> errors = resource.getErrors();
		String message = PivotUtil.formatResourceDiagnostics(errors, prefix, "\n\t");
		if (message != null)
			TestCase.fail(message);
	}

	public static void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) throws IOException, InterruptedException {
		List<Normalizer> expectedNormalizations = normalize(expectedResource);
		List<Normalizer> actualNormalizations = normalize(actualResource);
		String expected = EmfFormatter.listToStr(expectedResource.getContents())/*.replaceAll(" : ", ": ")*/;		// Workround BUG 552035
		String actual = EmfFormatter.listToStr(actualResource.getContents())/*.replaceAll(" : ", ": ")*/;
		TestCase.assertEquals(expected, actual);
		for (Normalizer normalizer : expectedNormalizations) {
			normalizer.denormalize();
		}
		for (Normalizer normalizer : actualNormalizations) {
			normalizer.denormalize();
		}
	}

	public static void deleteDirectory(@NonNull File dir) {
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				deleteDirectory(file);
			}
		}
		dir.delete();
	}

	/**
	 * Perform the appropriate initialization to support Complete OCL parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 */
	public static void doCompleteOCLSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			CompleteOCLStandaloneSetup.doSetup();
		}
		else {
			Guice.createInjector(new org.eclipse.ocl.xtext.completeocl.CompleteOCLRuntimeModule());
		}
	}

	/**
	 * Perform the appropriate initialization to support Essential OCL parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 */
	public static void doEssentialOCLSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			EssentialOCLStandaloneSetup.doSetup();
		}
		else {
			Guice.createInjector(new org.eclipse.ocl.xtext.essentialocl.EssentialOCLRuntimeModule());
		}
	}

	/**
	 * Perform the appropriate initialization to support Idioms parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 */
	public static void doIdiomsSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			IdiomsStandaloneSetup.doSetup();
		}
		else {
			Guice.createInjector(new org.eclipse.ocl.xtext.idioms.IdiomsRuntimeModule());
		}
	}

	/**
	 * Perform the appropriate initialization to support Markup parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 */
	public static void doMarkupSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			MarkupStandaloneSetup.doSetup();
		}
		else {
			Guice.createInjector(new org.eclipse.ocl.xtext.markup.MarkupRuntimeModule());
		}
	}

	/**
	 * Perform the appropriate initialization to support OCLinEcore parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 */
	public static void doOCLinEcoreSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLinEcoreStandaloneSetup.doSetup();
		}
		else {
			Guice.createInjector(new org.eclipse.ocl.xtext.oclinecore.OCLinEcoreRuntimeModule());
		}
	}

	/**
	 * Perform the appropriate initialization to support OCLstdlib parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 */
	public static void doOCLstdlibSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			OCLstdlibStandaloneSetup.doSetup();			// FIXME BUG 382058
		}
		else {
			Guice.createInjector(new org.eclipse.ocl.xtext.oclstdlib.OCLstdlibRuntimeModule());
		}
	}

	public static void doXtextSetup() {
		assert AbstractPivotTestCase.assertTestIsNotSetup();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			XtextStandaloneSetup.doSetup();			// FIXME BUG 382058
		}
		else {
			Guice.createInjector(new org.eclipse.xtext.XtextRuntimeModule());
		}
	}

	public static @NonNull String getName(@NonNull String name) {
		String testNameSuffix = System.getProperty("testNameSuffix", "");
		return name + " <" + testNameSuffix + ">";
	}

	public static boolean initializeEcoreEAnnotationValidators() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;
		Map<String, Object> eAnnotationValidatorRegistry2 = PivotEAnnotationValidator.getEAnnotationValidatorRegistry();
		if (eAnnotationValidatorRegistry2 != null) {
			try {
				PivotEAnnotationValidator.installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.emf.ecore.util.EcoreAnnotationValidator");
				PivotEAnnotationValidator.installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.emf.ecore.util.ExtendedMetaDataAnnotationValidator");
				PivotEAnnotationValidator.installAnnotationValidator(eAnnotationValidatorRegistry2, "org.eclipse.emf.codegen.ecore.genmodel.util.GenModelAnnotatonValidator");
				EPackage.Registry.INSTANCE.put("http:///org/eclipse/emf/ecore/util/EcoreAnnotation"/*ePackage.getNsURI()*/, new EPackage.Descriptor()
				{
					@Override
					public EPackage getEPackage() {
						ResourceSet resourceSet = new ResourceSetImpl();
						OCL.CLASS_PATH.initializeResourceSet(resourceSet);
						resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
						Resource eResource = resourceSet.getResource(URI.createPlatformResourceURI("org.eclipse.emf.ecore/model/EcoreAnnotation.ecore", true), true);
						return (EPackage) eResource.getContents().get(0);
					}

					@Override
					public EFactory getEFactory() {
						return getEPackage().getEFactoryInstance();
					}
				});
				EPackage.Registry.INSTANCE.put("http:///org/eclipse/emf/ecore/util/ExtendedMetaData"/*ePackage.getNsURI()*/, new EPackage.Descriptor()
				{
					@Override
					public EPackage getEPackage() {
						ResourceSet resourceSet = new ResourceSetImpl();
						OCL.CLASS_PATH.initializeResourceSet(resourceSet);
						resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
						Resource eResource = resourceSet.getResource(URI.createPlatformResourceURI("org.eclipse.emf.ecore/model/ExtendedMetaData.ecore", true), true);
						return (EPackage) eResource.getContents().get(0);
					}

					@Override
					public EFactory getEFactory() {
						return getEPackage().getEFactoryInstance();
					}
				});
				return true;
			}
			catch (Exception e) {}
		}
		return false;
	}

	public static List<Normalizer> normalize(Resource resource) {
		List<Normalizer> normalizers = new ArrayList<Normalizer>();
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof ETypedElement) {
				ETypedElement eTypedElement = (ETypedElement) eObject;
				if (eTypedElement.getUpperBound() == 1) {
					if (!eTypedElement.isOrdered() || !eTypedElement.isUnique()) {
						normalizers.add(new ETypedElementNormalizer(eTypedElement));
					}
					else if (eTypedElement.getLowerBound() == 0) {
						EClassifier eType = eTypedElement.getEType();
						if ((eType instanceof EDataType) && ElementUtil.isPrimitiveInstanceClass((EDataType) eType)) {
							normalizers.add(new ETypedElementNormalizer(eTypedElement));
						}
					}
				}
			}
			if (eObject instanceof EClass) {
				EClass eClass = (EClass) eObject;
				if (eClass.getEOperations().size() >= 2) {
					normalizers.add(new EOperationsNormalizer(eClass));		// FIXME Until AS2Ecore has consistent ops/inv ordering
				}
			}
			if (eObject instanceof EModelElement) {
				EModelElement eModelElement = (EModelElement) eObject;
				if (eModelElement.getEAnnotations().size() >= 2) {
					normalizers.add(new EAnnotationsNormalizer(eModelElement));
				}
			}
			if (eObject instanceof EAnnotation) {
				EAnnotation eAnnotation = (EAnnotation) eObject;
				EMap<String, String> eDetails = eAnnotation.getDetails();
				if (eDetails.size() > 1) {
					normalizers.add(new EDetailsNormalizer(eAnnotation));
				}
				if (EcorePackage.eNS_URI.equals(eAnnotation.getSource()) && eDetails.containsKey("constraints")) {
					normalizers.add(new EAnnotationConstraintsNormalizer(eAnnotation));
				}
			}
		}
		for (Normalizer normalizer : normalizers) {
			normalizer.normalize();
		}
		return normalizers;
	}

	public static void saveAsXMI(Resource resource, URI xmiURI, Map<?, ?> options) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl()); //$NON-NLS-1$
		Resource xmiResource = resourceSet.createResource(xmiURI);
		xmiResource.getContents().addAll(resource.getContents());
		xmiResource.save(options);
		assertNoResourceErrors("Save failed", xmiResource);
		resource.getContents().addAll(xmiResource.getContents());
	}

	/**
	 * Ensure that the correct URI mappings are installed for org.eclipse.uml2.uml.
	 * This method must be called after UMLResourcesUtil.init when there are rival Model/UML.ecore files visible to a standalone class loader.
	 */
	public static void workaroundUMLissue123(@Nullable ResourceSet resourceSet) {
		URI umlURI = workaroundUMLissue123_getBaseUMLURI();
		URI modelURI = umlURI.appendSegment("model");
		workaroundUMLissue123_mapURIs(URIConverter.URI_MAP, "platform:/plugin/org.eclipse.uml2.uml/model/", modelURI, "org.eclipse.uml2.uml");
		if (resourceSet != null) {
			Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
			workaroundUMLissue123_mapURIs(uriMap, "platform:/plugin/org.eclipse.uml2.uml/model/", modelURI, "org.eclipse.uml2.uml");
		}
	}

	// Clone of the private UMLResourcesUtil.getBaseUMLURI with the fix for uml#123
	private static URI workaroundUMLissue123_getBaseUMLURI() {
		URL resultURL = UMLUtil.class.getClassLoader()
			.getResource(String.format("model/%s", "CMOF241_2_UML.ecore2xml"));
		URI result;

		if (resultURL != null) {
			// remove the /model/UML.ecore segments of the resource
			// we found
			result = URI.createURI(resultURL.toExternalForm(), true)
				.trimSegments(2);
		} else {
			// probably, we're not running with JARs, so assume the source
			// project folder layout
			resultURL = UMLUtil.class.getResource("UMLUtil.class");

			String baseURL = resultURL.toExternalForm();

			int index = baseURL.lastIndexOf("/bin/");

			if (index != -1) {
				baseURL = baseURL.substring(0, index);
			}

			result = URI.createURI(baseURL, true);
		}

		return result;
	}

	// Unchanged clone of the private UMLResourcesUtil.mapURIs
	private static void workaroundUMLissue123_mapURIs(Map<URI, URI> uriMap, String uri, URI location,
			String pluginID) {

		URI prefix = URI.createURI(uri);

		// ensure trailing separator (make it a "URI prefix")
		if (!prefix.hasTrailingPathSeparator()) {
			prefix = prefix.appendSegment(""); //$NON-NLS-1$
		}

		// same with the location
		if (!location.hasTrailingPathSeparator()) {
			location = location.appendSegment(""); //$NON-NLS-1$
		}

		uriMap.put(prefix, location);

		// and platform URIs, too
		String folder = location.segment(location.segmentCount() - 2);
		String platformURI = String.format("%s/%s/", //$NON-NLS-1$
			pluginID, folder);
		uriMap.put(URI.createPlatformPluginURI(platformURI, true), location);
		uriMap.put(URI.createPlatformResourceURI(platformURI, true), location);
	}
}
