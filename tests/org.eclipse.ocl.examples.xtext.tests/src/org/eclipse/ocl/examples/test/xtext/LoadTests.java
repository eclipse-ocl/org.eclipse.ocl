/*******************************************************************************
 * Copyright (c) 2010, 2013 E.D.Willink and others.
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.emf.common.EMFPlugin;
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
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.completeocl.as2cs.CompleteOCLSplitter;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.eclipse.xtext.resource.impl.ListBasedDiagnosticConsumer;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class LoadTests extends XtextTestCase
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
	
	public @NonNull TestOCL createOCL() {
		return new TestOCL("LoadTests", getName(), OCL.NO_PROJECTS);
	}

	public Resource doLoad(@NonNull OCL ocl, String stem, String extension) throws IOException {
//		long startTime = System.currentTimeMillis();
//		System.out.println("Start at " + startTime);
		String inputName = stem + "." + extension;
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		URI inputURI = getProjectFileURI(inputName);
		URI outputURI = getProjectFileURI(outputName);
		URI output2URI = getProjectFileURI(output2Name);
		Resource xtextResource = null;
		try {
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			xtextResource = ocl.getResourceSet().getResource(inputURI, true);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", xtextResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			if (xtextResource.getContents().size() > 0) {
				assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
			}
//			if (doSave) {
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
				xtextResource.setURI(output2URI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
				xtextResource.save(null);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
				assertNoResourceErrors("Save failed", xtextResource);
			}
//		}
		finally {
			if (xtextResource instanceof BaseCSResource) {
				((BaseCSResource)xtextResource).dispose();
			}
		}
		Resource xmiResource = ocl.getResourceSet().createResource(outputURI);
		xmiResource.getContents().addAll(xtextResource.getContents());
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
//		xmiResource.save(null);
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
//		assertNoResourceErrors("Save failed", xmiResource);
		return xmiResource;
	}

	public Resource doLoad_OCL(@NonNull OCL ocl, URI inputURI) throws IOException {
//		long startTime = System.currentTimeMillis();
//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = ocl.getResourceSet();
		getProjectMap().initializeResourceSet(resourceSet);
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		URI outputURI = getProjectFileURI(outputName);
		URI output2URI = getProjectFileURI(output2Name);
		BaseCSResource xtextResource = null;
		try {
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			xtextResource = (BaseCSResource) resourceSet.getResource(inputURI, true);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", xtextResource);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			xtextResource.setURI(output2URI);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			xtextResource.save(null);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
			assertNoResourceErrors("Save failed", xtextResource);
			//
			CS2AS cs2as = xtextResource.findCS2AS();
			if (cs2as != null) {
				ASResource asResource = cs2as.getASResource();
				assertNoValidationErrors("Loaded pivot", asResource);
			}
		}
		finally {
			xtextResource.dispose();
		}
		Resource xmiResource = resourceSet.createResource(outputURI);
		xmiResource.getContents().addAll(xtextResource.getContents());
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
//		xmiResource.save(null);
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
//		assertNoResourceErrors("Save failed", xmiResource);
		return xmiResource;
	}
	
	public void doLoadEcore(@NonNull OCL ocl, @NonNull ResourceSet resourceSet, URI inputURI) throws IOException {
//		long startTime = System.currentTimeMillis();
//		System.out.println("Start at " + startTime);
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
//		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
//		URI outputURI = getProjectFileURI(outputName);
		URI output2URI = getProjectFileURI(output2Name);
		Resource ecoreResource = null;
		try {
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			ecoreResource = resourceSet.getResource(inputURI, true);
			EcoreUtil.resolveAll(ecoreResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", ecoreResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", ecoreResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", ecoreResource.getContents().get(0));
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			ecoreResource.setURI(output2URI);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			ecoreResource.save(null);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
			assertNoResourceErrors("Save failed", ecoreResource);
			ecoreResource.setURI(inputURI);
		}
		finally {
//			metamodelManager.dispose();
		}		
//		Resource xmiResource = resourceSet.createResource(outputURI);
//		xmiResource.getContents().addAll(xtextResource.getContents());
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
//		xmiResource.save(null);
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
//		assertNoResourceErrors("Save failed", xmiResource);
//		return xmiResource;
	}
	
	public void doLoadUML(@NonNull OCL ocl, @NonNull URI inputURI, boolean ignoreNonExistence, boolean validateEmbeddedOCL, boolean validateCompleteOCL) throws IOException, ParserException {
		doLoadUML(ocl, inputURI, new AbstractLoadCallBack(ignoreNonExistence, validateCompleteOCL, validateEmbeddedOCL));
	}
	
	private static class AbstractLoadCallBack implements ILoadCallBack
	{
		private final boolean ignoreNonExistence;
		private final boolean validateCompleteOCL;
		private final boolean validateEmbeddedOCL;

		private AbstractLoadCallBack(boolean ignoreNonExistence, boolean validateCompleteOCL, boolean validateEmbeddedOCL) {
			this.ignoreNonExistence = ignoreNonExistence;
			this.validateCompleteOCL = validateCompleteOCL;
			this.validateEmbeddedOCL = validateEmbeddedOCL;
		}

		public boolean ignoreNonExistence() {
			return ignoreNonExistence;
		}

		public void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource) {}

		public void validateCompleteOCL(@NonNull OCL ocl, @NonNull BaseCSResource reloadCS) throws IOException {
			if (validateCompleteOCL) {
				reloadCS.load(null);
				assertNoResourceErrors("Load failed", reloadCS);
				Resource reloadAS = reloadCS.getASResource();
				assertNoUnresolvedProxies("Unresolved proxies", reloadAS);
				assertNoValidationErrors("Reloading", reloadAS);
			}
		}

		public void validateEmbeddedOCL(@NonNull OCL ocl, @NonNull Constraint constraint) throws ParserException {
			if (validateEmbeddedOCL) {
				validateConstraint(ocl, constraint);
			}
		}
	}

	public static interface ILoadCallBack {
		boolean ignoreNonExistence();
		void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource);
		void validateCompleteOCL(@NonNull OCL ocl, @NonNull BaseCSResource reloadCS) throws IOException;
		void validateEmbeddedOCL(@NonNull OCL ocl, @NonNull Constraint eObject) throws ParserException;
	}
	
	public void doLoadUML(@NonNull OCL ocl, @NonNull URI inputURI, @NonNull ILoadCallBack loadCallBacks) throws IOException, ParserException {
		UMLStandaloneSetup.init();
//		long startTime = System.currentTimeMillis();
//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = ocl.getResourceSet();
		UMLResourcesUtil.init(resourceSet);
		getProjectMap().initializeResourceSet(resourceSet);
//		XMI252UMLResourceFactoryImpl.install(resourceSet, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true));
		if (!resourceSet.getURIConverter().exists(inputURI, null)) {
			if (loadCallBacks.ignoreNonExistence()) {
				return;
			}
			TestCase.fail("No such resource + '" + inputURI + "'");			
		}			
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {			
			StandaloneProjectMap.IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor("org.eclipse.uml2.uml");
			projectDescriptor.initializeURIMap(URIConverter.URI_MAP);		// *.ecore2xml must be global
		}
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
//		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		String oclName = stem + ".ocl";
//		URI outputURI = getProjectFileURI(outputName);
		URI output2URI = getProjectFileURI(output2Name);
		URI oclURI = getProjectFileURI(oclName);
		EnvironmentFactoryInternal environmentFactory = (EnvironmentFactoryInternal) ocl.getEnvironmentFactory();
//		EnvironmentFactoryResourceSetAdapter.getAdapter(resourceSet, environmentFactory);
		Resource umlResource = null;
		try {
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
//		    usePackageNsURIAsLocation = !Boolean.FALSE.equals(options.get(XMLResource.OPTION_USE_PACKAGE_NS_URI_AS_LOCATION));
			umlResource = resourceSet.getResource(inputURI, true);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", umlResource);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", umlResource);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
//			assertNoValidationErrors("Validation errors", umlResource.getContents().get(0));
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			umlResource.setURI(output2URI);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			umlResource.save(null);
//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
			assertNoResourceErrors("Save failed", umlResource);
			umlResource.setURI(inputURI);
			UML2AS adapter = UML2AS.getAdapter(umlResource, environmentFactory);
			UML2AS.Outer rootAdapter = adapter.getRoot();
			Model pivotModel = rootAdapter.getASModel();
			List<Resource> allResources = new ArrayList<Resource>();
			allResources.add(pivotModel.eResource());
			List<Resource> importedResources = rootAdapter.getImportedResources();
			if (importedResources != null) {
				for (Resource uResource : importedResources) {
					External2AS anAdapter = UML2AS.findAdapter(uResource, environmentFactory);
					if (anAdapter == null) {
						anAdapter = UML2AS.getAdapter(uResource, environmentFactory);
					}
					Model asModel = anAdapter.getASModel();
					Resource asResource = asModel.eResource();
					allResources.add(asResource);
				}
			}
//			OCL ocl = OCL.newInstance(environmentFactory);
			int exceptions = 0;
//			int parses = 0;
			StringBuilder s = new StringBuilder();
			s.append("Parsing errors");
			for (Resource asResource : allResources) {
				assertNoResourceErrors("Load failed", asResource);
			}
			ASResource asResource = (ASResource) allResources.get(0); {
				@SuppressWarnings("unused") URI savedURI = asResource.getURI();
//				asResource.setURI(PivotUtil.getNonPivotURI(savedURI).appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION));
//				if (!EMFPlugin.IS_ECLIPSE_RUNNING) {			// Cannot save to plugins for JUnit plugin tests
//					asResource.save(null);
//				}
//				asResource.setURI(savedURI);
				for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof Constraint) {
						Constraint constraint = (Constraint)eObject;
//						boolean donePrint = false;
						try {
							loadCallBacks.validateEmbeddedOCL(ocl, constraint);
//							parses++;
						} catch (ParserException e) {
//							if (!donePrint) {
								System.out.println("\n" + constraint);
//								donePrint = true;
//							}
							System.out.println(e);
							exceptions++;
							s.append("\n" + e + "\n");
						}
					}
				}
			}
//			System.out.printf("Exceptions %d, Parses %d\n", exceptions, parses);
			/*for (Resource asResource : allResources)*/ {
				assertNoValidationErrors("Overall validation", asResource);
			}
			assertEquals(s.toString(), 0, exceptions);
			loadCallBacks.postLoad(ocl, asResource);
			//
			//	Split off any embedded OCL to a separate file
			//		
			ASResource oclResource = CompleteOCLSplitter.separate(environmentFactory, allResources.get(0));
			if (oclResource != null) {
				URI xtextURI = oclURI;// != null ? URI.createPlatformResourceURI(oclURI, true) : uri.trimFileExtension().appendFileExtension("ocl");
				ResourceSet csResourceSet = ocl.getResourceSet();
				environmentFactory.adapt(csResourceSet);
				BaseCSResource xtextResource = (BaseCSResource) csResourceSet.createResource(xtextURI, OCLinEcoreCSPackage.eCONTENT_TYPE);
				if (xtextResource != null) {
					xtextResource.updateFrom(oclResource, environmentFactory);
					xtextResource.save(null);
				}
				//
				//	Check that the split off file is loadable
				//		
				OCL ocl2 = createOCL();
				ResourceSet resourceSet2 = ocl2.getResourceSet();
				BaseCSResource reloadCS = (BaseCSResource) resourceSet2.createResource(oclURI);
				ocl2.getEnvironmentFactory().adapt(reloadCS);
				loadCallBacks.validateCompleteOCL(ocl2, reloadCS);
				ocl2.dispose();
			}
		}
		finally {
//			metamodelManager.dispose();
			unloadResourceSet(resourceSet);
		}		
//		Resource xmiResource = resourceSet.createResource(outputURI);
//		xmiResource.getContents().addAll(xtextResource.getContents());
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
//		xmiResource.save(null);
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
//		assertNoResourceErrors("Save failed", xmiResource);
//		return xmiResource;
	}

	private static void validateConstraint(@NonNull OCL ocl, @NonNull Constraint constraint) throws ParserException {
		ExpressionInOCL specification;
//		long startParseTime = System.currentTimeMillis();
		specification = ocl.getSpecification(constraint);
		constraint.setOwnedSpecification(specification);
		if (specification != null) {
			LanguageExpression specification2 = constraint.getOwnedSpecification();
			String body = specification2.getBody();
			if (body != null) {
				String language = specification2.getLanguage();
				if (language == null) {
//					System.out.println("******** No languages");
				}
//				else if (languages.size() == 0) {
//					System.out.println("******** Empty languages");
//				}
				else if (!PivotConstants.OCL_LANGUAGE.equals(language)) {
//					System.out.println("******** Non-OCL \'" + languages.get(0) + "' languages");
//					languages.set(0, "OCL");
				}
			}
/*			long endParseTime = System.currentTimeMillis();
			int treeSize = 1;
			for (TreeIterator<EObject> tit2 = specification.eAllContents(); tit2.hasNext(); tit2.next()) {
				treeSize++;
			}
			double parseTime = 0.001 * (endParseTime - startParseTime);
			double timePerNode = parseTime/treeSize;
			if (timePerNode > 0.02) {
//				if (!donePrint) {
					System.out.println("\n" + constraint);
//					donePrint = true;
//				}
				System.out.printf("Size: %d, Time %6.3f, Time/Node %8.6f\n", treeSize, parseTime, timePerNode);
			} */
			assertNoValidationErrors("Local validation", specification);
		}
	}

	public Resource doLoad_Concrete(@NonNull OCL ocl, @NonNull String stem, @NonNull String extension) throws IOException {
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, stem, extension);
		Resource asResource = doLoad_Concrete2(xtextResource, stem, extension);
		return asResource;
	}
	protected BaseCSResource doLoad_Concrete1(@NonNull OCL ocl, @NonNull String stem, @NonNull String extension) throws IOException {
		String inputName = stem + "." + extension;
		URI inputURI = getProjectFileURI(inputName);
		BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().createResource(inputURI);
		xtextResource.setProjectManager(getProjectMap());
		JavaClassScope.getAdapter(xtextResource,  getClass().getClassLoader());
		ocl.getEnvironmentFactory().adapt(xtextResource);
		xtextResource.load(null);
		assertNoResourceErrors("Load failed", xtextResource);
		return xtextResource;
	}
	protected Resource doLoad_Concrete2(@NonNull BaseCSResource xtextResource, @NonNull String stem, @NonNull String extension) throws IOException {
		String inputName = stem + "." + extension;
		URI inputURI = getProjectFileURI(inputName);
		String cstName = inputName + ".xmi";
		String pivotName = inputName + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
		String savedName = stem + ".saved." + extension;
		URI cstURI = getProjectFileURI(cstName);
		URI pivotURI = getProjectFileURI(pivotName);
		URI savedURI = getProjectFileURI(savedName);
		Resource asResource = xtextResource.getASResource();
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
//FIXME		assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		xtextResource.setURI(savedURI);
		xtextResource.save(null);
		xtextResource.setURI(inputURI);
		assertNoResourceErrors("Save failed", xtextResource);
		saveAsXMI(xtextResource, cstURI);
		asResource.setURI(pivotURI);
		assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
		asResource.save(null);
		return asResource;
	}
	
	public Resource doLoad_Pivot(@NonNull OCL ocl, @NonNull String stem, @NonNull String extension) throws IOException {
//		long startTime = System.currentTimeMillis();
//		System.out.println("Start at " + startTime);
		String inputName = stem + "." + extension;
//		String outputName = stem + "." + extension + ".xmi";
//		String output2Name = stem + ".saved." + extension;
		URI inputURI = getProjectFileURI(inputName);
//		URI outputURI = getProjectFileURI(outputName);
//		URI output2URI = getProjectFileURI(output2Name);
		Resource asResource = null;
		try {
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			asResource = ocl.getMetamodelManager().getASResourceSet().getResource(inputURI, true);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", asResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", asResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", asResource.getContents().get(0));
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
//			xtextResource.setURI(output2URI);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
//			xtextResource.save(null);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
//			assertNoResourceErrors("Save failed", xtextResource);
		}
		finally {
//			unloadCS(resourceSet);
//			if (xtextResource instanceof BaseCSResource) {
//				CS2ASResourceAdapter adapter = CS2ASResourceAdapter.getAdapter((BaseCSResource)xtextResource, null);
//				adapter.dispose();
//			}
//			unloadPivot(metamodelManager);
		}
		return asResource;
	}
	
	public Resource doLoad_CS(@NonNull OCL ocl, @NonNull String stem, @NonNull String extension) throws IOException {
//		long startTime = System.currentTimeMillis();
//		System.out.println("Start at " + startTime);
		String inputName = stem + "." + extension;
//		String outputName = stem + "." + extension + ".xmi";
//		String output2Name = stem + ".saved." + extension;
		URI inputURI = getProjectFileURI(inputName);
//		URI outputURI = getProjectFileURI(outputName);
//		URI output2URI = getProjectFileURI(output2Name);
		Resource asResource = null;
		try {
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			asResource = ocl.getResourceSet().getResource(inputURI, true);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", asResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", asResource);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", asResource.getContents().get(0));
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
//			xtextResource.setURI(output2URI);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
//			xtextResource.save(null);
	//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
//			assertNoResourceErrors("Save failed", xtextResource);
		}
		finally {
//			unloadCS(resourceSet);
//			if (xtextResource instanceof BaseCSResource) {
//				CS2ASResourceAdapter adapter = CS2ASResourceAdapter.getAdapter((BaseCSResource)xtextResource, null);
//				adapter.dispose();
//			}
//			unloadPivot(metamodelManager);
		}
		return asResource;
	}

	protected void saveAsXMI(Resource resource, URI xmiURI) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl()); //$NON-NLS-1$
		Resource xmiResource = resourceSet.createResource(xmiURI);
		xmiResource.getContents().addAll(resource.getContents());
		Map<String, Object> options = new HashMap<String, Object>();
//		options.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		xmiResource.save(options);
		assertNoResourceErrors("Save failed", xmiResource);
		resource.getContents().addAll(xmiResource.getContents());
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		configurePlatformResources();
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoad_Annotations_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Annotations", "ecore");
		ocl.dispose();
	}

	public void testLoad_Annotations_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Annotations", "oclinecore");
		ocl.dispose();
	}

	public void testLoad_Ecore_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Ecore", "ecore");
		ocl.dispose();
	}	

	public void testLoad_Empty_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Empty", "ecore");
		ocl.dispose();
	}	

	public void testLoad_Expression_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
//		metamodelManager.loadLibrary(OCLstdlib.INSTANCE);
		Resource asResource = doLoad_Concrete(ocl, "Expression", "oclinecore");
		String ecoreName = "Expression" + ".saved.ecore";
		URI ecoreURI = getProjectFileURI(ecoreName);
		Map<String,Object> options = new HashMap<String,Object>();
		options.put(PivotConstants.PRIMITIVE_TYPES_URI_PREFIX, "primitives.ecore#//");
		XMLResource ecoreResource = AS2Ecore.createResource((EnvironmentFactoryInternal) ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(null);
		ocl.dispose();
	}	

	public void testLoad_Imports_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Imports", "ecore");
		ocl.dispose();
	}

	public void testLoad_Names_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Names", "ecore");
		ocl.dispose();
	}

	public void testLoad_Names_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Names", "oclinecore");
		ocl.dispose();
	}

	public void testLoad_Overloads_oclinecore() throws IOException, InterruptedException {
//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Overloads", "oclinecore");
		ocl.dispose();
	}

	public void testLoad_Refresh_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
//			OCLDelegateDomain.initialize(null);
		}
		String testFile = 
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
		createOCLinEcoreFile("Refresh.oclinecore", testFile);
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, "Refresh", "oclinecore");
		Resource asResource = doLoad_Concrete2(xtextResource, "Refresh", "oclinecore");
		assertNoValidationErrors("First validation", asResource);
		try {
			xtextResource.update(new ListBasedDiagnosticConsumer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		assertNoValidationErrors("Second validation", asResource);
		ocl.dispose();
	}

	public void testLoad_Refresh2_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
//			OCLDelegateDomain.initialize(null);
		}
		String testFile = 
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
		createOCLinEcoreFile("Refresh2.oclinecore", testFile);
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, "Refresh2", "oclinecore");
		Resource asResource = doLoad_Concrete2(xtextResource, "Refresh2", "oclinecore");
		assertNoValidationErrors("First validation", asResource);
		try {
			xtextResource.update(new ListBasedDiagnosticConsumer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		assertNoValidationErrors("Second validation", asResource);
		ocl.dispose();
	}

	public void testLoad_RoyalAndLoyal_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "RoyalAndLoyal", "ecore");
		ocl.dispose();
	}	

	public void testLoad_oclstdlib_oclstdlib() throws IOException, InterruptedException {
		OCL ocl = createOCL();
//		StandardLibraryContribution.REGISTRY.put(MetamodelManager.DEFAULT_OCL_STDLIB_URI, StandardLibraryContribution.NULL);
		Resource asResource = doLoad_Concrete(ocl, "oclstdlib", "oclstdlib");
//		checkMonikers(asResource);
		String ecoreName = "oclstdlib" + ".saved.ecore";
		URI ecoreURI = getProjectFileURI(ecoreName);
		Map<String,Object> options = new HashMap<String,Object>();
		options.put(PivotConstants.PRIMITIVE_TYPES_URI_PREFIX, "primitives.ecore#//");
		XMLResource ecoreResource = AS2Ecore.createResource((EnvironmentFactoryInternal) ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(null);
		ocl.dispose();
	}

	public void testLoad_OCL_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "OCL", "ecore");
		ocl.dispose();
	}

	public void testLoad_Types_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Types", "oclinecore");
		ocl.dispose();
	}

	public void testLoad_Wildcard_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Wildcard", "oclinecore");
		ocl.dispose();
	}
	
	public void testLoad_BaseCST_ecore() throws IOException, InterruptedException {
		StandaloneProjectMap projectMap = getProjectMap();
		OCL ocl = OCL.newInstance(projectMap);
		ResourceSet resourceSet = ocl.getResourceSet();
		projectMap.initializeResourceSet(resourceSet);
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = projectMap.getProjectDescriptor("org.eclipse.emf.ecore");
		StandaloneProjectMap.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(URI.createURI(EcorePackage.eNS_URI));
		packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.xtext.base/model/BaseCS.ecore", true);
		try {
			doLoadEcore(ocl, resourceSet, uri);
		}
		finally {
			unloadResourceSet(resourceSet);
		}
		ocl.dispose();
	}
	
//	public void testLoad_Bug7_ocl() throws IOException, InterruptedException {
//		doLoad_Concrete("Bug7", "ocl");
//	}
	
//	public void testLoad_Bug9_ocl() throws IOException, InterruptedException {
//		doLoad_Concrete("Bug9", "ocl");
//	}
	
//	public void testLoad_TypeConformance_ocl() throws IOException, InterruptedException {
//		doLoad_Concrete("TypeConformance", "ocl");
//	}
	
//	public void testLoad_MiniTypeWFRs_ocl() throws IOException, InterruptedException {
//		doLoad_Concrete("MiniTypeWFRs", "ocl");
//	}
	
//	public void testLoad_TypeWFRs_ocl() throws IOException, InterruptedException {
//		doLoad_Concrete("TypeWFRs", "ocl");
//	}
	
//	public void testLoad_MiniOCLOperations_ocl() throws IOException, InterruptedException {
//		doLoad_Concrete("MiniOCLOperations", "ocl");
//	}

//	public void testLoad_Bug11_oclinecore() throws IOException, InterruptedException {
//		doLoad_Concrete("Bug11", "oclinecore");
//	}	

//	public void testLoad_Bug14_oclstdlib() throws IOException, InterruptedException {
//		doLoad_Concrete("Bug14", "oclstdlib");
//	}	
	
	public void testLoad_Bug321171_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Bug321171", "oclinecore");
		ocl.dispose();
	}

	public void testLoad_Bug321903_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Bug321903", "oclinecore");
		ocl.dispose();
	}	
	
	public void testLoad_Bug323741_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Bug323741", "ecore");
		ocl.dispose();
	}
	
//FIXME	public void testLoad_Bug323741_pivot() throws IOException, InterruptedException {
//		doLoad_Pivot("Bug323741", "pivot");
//	}
	
	public void testLoad_Bug323741_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Bug323741", "oclinecore");
		ocl.dispose();
	}
	
	public void testLoad_Bug328480_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, "Bug328480", "oclinecore");
		ocl.dispose();
	}
	
	public void testLoad_Bug328485_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		Resource asResource = doLoad_Concrete(ocl, "Bug328485", "oclinecore");
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
		doLoad_Concrete(ocl, "Bug401921", "oclinecore");
		ocl.dispose();
	}

	public void testLoad_Bug402767_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
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
		createOCLinEcoreFile("Bug402767.oclinecore", testFile);
		Resource resource = doLoad_Concrete(ocl, "Bug402767", "oclinecore");
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
		String testFile = 
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
		createOCLinEcoreFile("Bug403070.oclinecore", testFile);
		doLoad_Concrete(ocl, "Bug403070", "oclinecore");
		ocl.dispose();
	}
	
	public void testLoad_Bug401953_essentialocl() {
		OCL ocl = createOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			EssentialOCLStandaloneSetup.doSetup();
		}
		URI uri = getProjectFileURI("Bug401953.essentialocl");
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
		OCL ocl = createOCL();
		String bug450950A = 
				"package bug450950 : bug450950A = 'http://www.eclipse.org/ocl/Bug450950A'\n" +
				"{\n" +
				"	abstract class Bug450950A;\n" +
				"}\n";
		createOCLinEcoreFile("Bug450950A.oclinecore", bug450950A);
		String bug450950B = 
				"package bug450950 : bug450950B = 'http://www.eclipse.org/ocl/Bug450950B'\n" +
				"{\n" +
				"	abstract class Bug450950B;\n" +
				"}\n";
		createOCLinEcoreFile("Bug450950B.oclinecore", bug450950B);
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
		createOCLinEcoreFile("Bug450950.ocl", bug450950);
		Resource asResource = doLoad_Concrete(ocl, "Bug450950", "ocl");
		assertResourceErrors("Save", asResource, StringUtil.bind(PivotMessagesInternal.UnstableXMIid_ERROR_, "\\n Package 'P.bug450950'"));
		ocl.dispose();
	}	

	public void testLoad_Bug467340_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		String bug467340 = 
				"package ocl\n" + 
				"\n" + 
				"context Package \n" + 
				"def : ast1(aType : ocl::OclType) : String =\n" + 
				"	ast2(aType)\n" + 
				"	\n" + 
				"context Package \n" + 
				"def : ast2(aType : ocl::OclType) : String =\n" + 
				"	null\n" + 
				"\n" + 
				"endpackage\n";
		createOCLinEcoreFile("Bug467340.ocl", bug467340);
		Resource asResource = doLoad_Concrete(ocl, "Bug467340", "ocl");
		assertResourceErrors("Save", asResource);
		ocl.dispose();
	}	
	
	public void testLoad_Bug441620_completeocl() throws IOException {
		OCL ocl = createOCL();
		BaseCSResource csResource = (BaseCSResource) doLoad_CS(ocl, "Bug441620", "ocl");
		Resource oclResource = csResource.getASResource();
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
		BaseCSResource csResource = (BaseCSResource) doLoad_CS(ocl, "Bug441620b", "ocl");
		Resource oclResource = csResource.getASResource();
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
	
	private void checkMultiplicity(TypedElement typedElement, int lower, int upper) {
		Type type = typedElement.getType();
		if ((0 <= upper) && (upper <= 1)) {
			assertFalse(type instanceof CollectionType);
			assertEquals(lower > 0, typedElement.isIsRequired());
		}
		else {
			assertTrue(typedElement.isIsRequired());
			CollectionType collType = (CollectionType)type;
			assertEquals(lower, collType.getLower());
			assertEquals(upper >= 0 ? upper : Unlimited.INSTANCE, collType.getUpper());
		}
	}

	public void testLoad_Fruit_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCL();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, "Fruit", "ocl");
		ocl.dispose();
	}	

	public void testLoad_example1_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCL();
//		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, "Source2Target", "ocl");
		ocl.dispose();
	}	

	public void testLoad_Imports_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, "Imports", "ocl");
		ocl.dispose();
	}	

	public void testLoad_Names_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad(ocl, "Names", "ocl");
		ocl.dispose();
	}	

	public void testLoad_OCLTest_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad(ocl, "OCLTest", "ocl");
		ocl.dispose();
	}	

	public void testLoad_Pivot_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad_OCL(ocl, URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ocl", true));
		ocl.dispose();
	}	

	public void testLoad_RoyalAndLoyal_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad(ocl, "RoyalAndLoyal", "ocl");
		ocl.dispose();
	}
	
	public void testLoad_Internationalized_profile_uml() throws IOException, InterruptedException, ParserException {
		OCL ocl = createOCL();
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/model/Internationalized.profile.uml", true);
		doLoadUML(ocl, uri, false, false, false);
		ocl.dispose();
	}
	
	public void testLoad_StereotypeApplications_uml() throws IOException, InterruptedException, ParserException {
		OCLInternal ocl = createOCL();
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uri = getProjectFileURI("StereotypeApplications.uml");
		doLoadUML(ocl, uri, new AbstractLoadCallBack(false, false, false) {
			@Override
			public void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource) {
		        for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
		            EObject obj = tit.next();
		            if (obj instanceof Type) {
		                ((MetamodelManagerInternal)ocl.getMetamodelManager()).getAllInvariants((Type) obj);		// This gives the Bug 422938 CCE
		            }
		        }
			}
			
		});
		ocl.dispose();
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
		Ecore2AS conversion = Ecore2AS.getAdapter(ecoreResource, (EnvironmentFactoryInternal) ocl2.getEnvironmentFactory());
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
		Ecore2AS conversion = Ecore2AS.getAdapter(ecoreResource, (EnvironmentFactoryInternal) ocl2.getEnvironmentFactory());
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
		Ecore2AS conversion = Ecore2AS.getAdapter(ecoreResource, (EnvironmentFactoryInternal) ocl2.getEnvironmentFactory());
		ASResource asResource = (ASResource) conversion.getASModel().eResource();
		//
		//	Save the *.oclas and cache that the xmi:ids
		//
		URI asURI = getProjectFileURI(ecoreFileName + ".oclas");
		asResource.setURI(asURI);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(ASResource.OPTION_INTERNAL_UUIDS, Boolean.TRUE);
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
		asResource.save(null);		// Bug 418412 gave a duplicate xmi:id ISE failure here.		
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
		ASResourceFactoryRegistry.INSTANCE.configureResourceSet(resourceSet);
		ASResource reloadedAsResource = (ASResource)resourceSet.getResource(asURI, true);
		int newIdCount = 0;
		for (TreeIterator<EObject> tit = reloadedAsResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			String id = reloadedAsResource.getID(eObject);
			if (id != null) {
				EObject eObject2 = id2eObject.get(id);
				assertNotNull(eObject2);
				assertEquals(eObject2.getClass(), eObject.getClass());
				newIdCount++;
			}
		}
		assertEquals(oldIdCount, newIdCount);
	}
}
