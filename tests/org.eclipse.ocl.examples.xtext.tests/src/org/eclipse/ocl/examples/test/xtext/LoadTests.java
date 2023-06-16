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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
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
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.xtext.resource.impl.ListBasedDiagnosticConsumer;

import junit.framework.TestCase;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class LoadTests extends XtextTestCase
{
	protected static final @NonNull String @NonNull [] NO_MESSAGES = new @NonNull String[] {};

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

	public @NonNull TestOCL createOCL() {
		return createOCL(null);
	}

	public @NonNull TestOCL createOCL(@Nullable ResourceSet externalResourceSet) {
		return new TestOCL(getTestFileSystem(), "LoadTests", getName(), OCL.NO_PROJECTS, externalResourceSet);
	}

	public @NonNull TestOCL createOCLWithProjectMap() {
		return new TestOCL(getTestFileSystem(), "LoadTests", getName(), getProjectMap(), null);
	}

	public Resource doLoad(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		return doLoad(ocl, inputURI, stem, extension);
	}

	public Resource doLoad(@NonNull OCL ocl, @NonNull URI inputURI, String stem, String extension) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		URI outputURI = getTestFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);
		Resource resource = null;
		try {
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			resource = ocl.getResourceSet().getResource(inputURI, true);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", resource);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", resource);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			if (resource.getContents().size() > 0) {
				assertNoValidationErrors("Validation errors", resource.getContents().get(0));
			}
			if (resource instanceof BaseCSResource) {
				CS2AS cs2as = ((BaseCSResource)resource).findCS2AS();
				if (cs2as != null) {
					ASResource asResource = cs2as.getASResource();
					assertNoValidationErrors("Loaded pivot", asResource);
				}
			}
			//			if (doSave) {
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			resource.setURI(output2URI);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			resource.save(XMIUtil.createSaveOptions());
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
			assertNoResourceErrors("Save failed", resource);
		}
		//		}
		finally {
			if (resource instanceof BaseCSResource) {
				((BaseCSResource)resource).dispose();
			}
		}
		Resource xmiResource = ocl.getResourceSet().createResource(outputURI);
		xmiResource.getContents().addAll(resource.getContents());
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
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		URI outputURI = getTestFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);
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
			DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());
			xtextResource.save(XMIUtil.createSaveOptions());
			debugTimestamp.log("Serialization save done");
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
			if (xtextResource != null) {
				xtextResource.dispose();
			}
			unloadResourceSet(resourceSet);
		}
		assert xtextResource != null;
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
		URI output2URI = getTestFileURI(output2Name);
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
			ecoreResource.save(XMIUtil.createSaveOptions());
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

	public Model doLoadUML(@Nullable TestOCL ocl, @NonNull URI inputURI, boolean ignoreNonExistence, boolean validateEmbeddedOCL, @NonNull String @Nullable [] validateCompleteOCLMessages, @NonNull String @Nullable [] messages) throws IOException, ParserException {
		assert !ignoreNonExistence;
		assert validateEmbeddedOCL;
		AbstractLoadCallBack loadCallBacks = new AbstractLoadCallBack(ignoreNonExistence, validateCompleteOCLMessages, validateEmbeddedOCL);
		return doLoadUML(ocl, inputURI, loadCallBacks, messages);
	}

	private static class AbstractLoadCallBack implements ILoadCallBack
	{
		private final boolean ignoreNonExistence;
		private final @NonNull String @Nullable [] validateCompleteOCLMessages;
		private final boolean validateEmbeddedOCL;

		private AbstractLoadCallBack(boolean ignoreNonExistence, @NonNull String @Nullable [] validateCompleteOCLMessages, boolean validateEmbeddedOCL) {
			this.ignoreNonExistence = ignoreNonExistence;
			this.validateCompleteOCLMessages = validateCompleteOCLMessages;
			this.validateEmbeddedOCL = validateEmbeddedOCL;
		}

		@Override
		public boolean ignoreNonExistence() {
			return ignoreNonExistence;
		}

		@Override
		public void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource) {}

		@Override
		public void validateCompleteOCL(@NonNull OCL ocl, @NonNull BaseCSResource reloadCS) throws IOException {
			if (validateCompleteOCLMessages != null) {
				reloadCS.load(null);
				assertNoResourceErrors("Load failed", reloadCS);
				Resource reloadAS = reloadCS.getASResource();
				assertNoUnresolvedProxies("Unresolved proxies", reloadAS);
				assertValidationDiagnostics("Reloading", reloadAS, validateCompleteOCLMessages);
			}
		}

		@Override
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

	public Model doLoadUML(@Nullable TestOCL externalOCL, @NonNull URI inputURI, @NonNull ILoadCallBack loadCallBacks, @NonNull String @Nullable [] messages) throws IOException, ParserException {
		UMLStandaloneSetup.init();
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		//		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		String oclName = stem + ".ocl";
		//		URI outputURI = getProjectFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);		// NB getTestProject() may activate PackageExplorerPart
		URI oclURI = getTestFileURI(oclName);
		OCLInternal internalOCL = externalOCL != null ? externalOCL : createOCLWithProjectMap();
	//	UMLASResourceFactory.getInstance();
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = internalOCL.getResourceSet();
		UML2AS.initializeUML(resourceSet);
		//		XMI252UMLResourceFactoryImpl.install(resourceSet, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true));
		if (!resourceSet.getURIConverter().exists(inputURI, null)) {
			if (loadCallBacks.ignoreNonExistence()) {
				return null;
			}
			TestCase.fail("No such resource + '" + inputURI + "'");
		}
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.nonNullState(getProjectMap().getProjectDescriptor("org.eclipse.uml2.uml"));
			projectDescriptor.initializeURIMap(URIConverter.URI_MAP);		// *.ecore2xml must be global
		}
		EnvironmentFactoryInternal environmentFactory = internalOCL.getEnvironmentFactory();
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
			umlResource.save(XMIUtil.createSaveOptions());
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
							loadCallBacks.validateEmbeddedOCL(internalOCL, constraint);
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
				assertValidationDiagnostics("Overall validation", asResource, messages);
			}
			assertEquals(s.toString(), 0, exceptions);
			loadCallBacks.postLoad(internalOCL, asResource);
			//
			//	Split off any embedded OCL to a separate file
			//
			ASResource oclResource = (ASResource)allResources.get(0);//CompleteOCLSplitter.separate(environmentFactory, allResources.get(0));
			if (oclResource != null) {
				URI xtextURI = oclURI;// != null ? URI.createPlatformResourceURI(oclURI, true) : uri.trimFileExtension().appendFileExtension("ocl");
				ResourceSet csResourceSet = internalOCL.getResourceSet();
				environmentFactory.adapt(csResourceSet);
				boolean hasOCLcontent = false;
				BaseCSResource xtextResource = (BaseCSResource) csResourceSet.createResource(xtextURI, OCLinEcoreCSPackage.eCONTENT_TYPE);
				if (xtextResource != null) {
					xtextResource.updateFrom(oclResource, environmentFactory);
					List<@NonNull EObject> csContents = xtextResource.getContents();
					if (csContents.size() > 0) {
						EObject csRoot = csContents.get(0);
						if (csRoot instanceof CompleteOCLDocumentCS) {
							CompleteOCLDocumentCS csDocument = (CompleteOCLDocumentCS)csRoot;
							if (csDocument.getOwnedPackages().size() > 0) {
								hasOCLcontent = true;
								DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());
								xtextResource.save(XMIUtil.createSaveOptions());
								debugTimestamp.log("Serialization save done");
							}
						}
					}
				}
				if (externalOCL == null) {
					internalOCL.dispose();
				}
				//
				//	Check that the split off file is loadable
				//
				if (hasOCLcontent) {
					ThreadLocalExecutor.resetEnvironmentFactory();
					OCL ocl2 = createOCLWithProjectMap();
					ResourceSet resourceSet2 = ocl2.getResourceSet();
					initializeExtraURIMappings(resourceSet2);
					BaseCSResource reloadCS = (BaseCSResource) resourceSet2.createResource(oclURI);
					ocl2.getEnvironmentFactory().adapt(reloadCS);
					loadCallBacks.validateCompleteOCL(ocl2, reloadCS);
					ocl2.dispose();
					unloadResourceSet(resourceSet2);
				}
			}
			return pivotModel;
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

	public Resource doLoad_Concrete(@NonNull OCL ocl, @NonNull URI inputURI, String... resourceErrors) throws IOException {
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, inputURI);
		CS2AS cs2as = xtextResource.findCS2AS();
		if (cs2as != null) {
			ASResource asResource = cs2as.getASResource();
			Map<Object, Object> saveOptions = XMIUtil.createSaveOptions(asResource);
			saveOptions.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
			saveOptions.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
			AS2ID.assignIds(asResource, saveOptions);
			assertResourceErrors("Pre-save", asResource, resourceErrors);
		}
		Resource asResource = doLoad_Concrete2(xtextResource, inputURI);
		return asResource;
	}
	protected BaseCSResource doLoad_Concrete1(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().createResource(inputURI);
		JavaClassScope.getAdapter(xtextResource,  getClass().getClassLoader());
		ocl.getEnvironmentFactory().adapt(xtextResource);
		InputStream inputStream = ocl.getResourceSet().getURIConverter().createInputStream(inputURI);
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Load failed", xtextResource);
		CS2AS cs2as = xtextResource.findCS2AS();
		if (cs2as != null) {
			ASResource asResource = cs2as.getASResource();
			assertNoValidationErrors("Loaded pivot", asResource);
		//	Map<Object, Object> saveOptions = XMIUtil.createSaveOptions(asResource);
		//	saveOptions.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
		//	saveOptions.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
		//	AS2ID.assignIds(asResource, saveOptions);
		}
		return xtextResource;
	}
	protected Resource doLoad_Concrete2(@NonNull BaseCSResource xtextResource, @NonNull URI inputURI) throws IOException {
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String inputName = stem + "." + extension;
		String cstName = inputName + ".xmi";
		String pivotName = inputName + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
		String savedName = stem + ".saved." + extension;
		URI cstURI = getTestFileURI(cstName);
		URI pivotURI = getTestFileURI(pivotName);
		URI savedURI = getTestFileURI(savedName);
		ASResource asResource = xtextResource.getASResource();
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
		//FIXME		assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		xtextResource.setURI(savedURI);
		Map<Object, Object> saveOptions = XMIUtil.createSaveOptions();
		saveOptions.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
		saveOptions.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
		DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());
		xtextResource.save(saveOptions);
		debugTimestamp.log("Serialization save done");
		xtextResource.setURI(inputURI);
		assertNoResourceErrors("Save failed", xtextResource);
		saveAsXMI(xtextResource, cstURI);
		assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
		if (asResource.isSaveable()) {
			asResource.setURI(pivotURI);
			asResource.save(saveOptions);
		}
		return asResource;
	}

	@Deprecated /* @deprecated - not used */
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

	public Resource doLoad_CS(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		BaseCSResource csResource = null;
		try {
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			csResource = (BaseCSResource) ocl.getResourceSet().getResource(inputURI, true);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", csResource);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", csResource);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", csResource.getContents().get(0));
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			//			xtextResource.setURI(output2URI);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			//			xtextResource.save(null);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
			//			assertNoResourceErrors("Save failed", xtextResource);
			CS2AS cs2as = csResource.findCS2AS();
			if (cs2as != null) {
				ASResource asResource = cs2as.getASResource();
				assertNoValidationErrors("Loaded pivot", asResource);
			}
		}
		finally {
			//			unloadCS(resourceSet);
			//			if (xtextResource instanceof BaseCSResource) {
			//				CS2ASResourceAdapter adapter = CS2ASResourceAdapter.getAdapter((BaseCSResource)xtextResource, null);
			//				adapter.dispose();
			//			}
			//			unloadPivot(metamodelManager);
		}
		return csResource;
	}

	protected void initializeExtraURIMappings(@NonNull ResourceSet resourceSet) {
	}

	protected void saveAsXMI(Resource resource, URI xmiURI) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl()); //$NON-NLS-1$
		Resource xmiResource = resourceSet.createResource(xmiURI);
		xmiResource.getContents().addAll(resource.getContents());
		Map<Object, Object> options = XMIUtil.createSaveOptions();
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
		doLoad(ocl, getTestModelURI("models/ecore/Annotations.ecore"));
		ocl.dispose();
	}

	public void testLoad_Annotations_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad_Concrete(ocl, getTestModelURI("models/oclinecore/Annotations.oclinecore"));
		ocl.dispose();
	}

	public void testLoad_Ecore_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, getTestModelURI("models/ecore/Ecore.ecore"));
		ocl.dispose();
	}

	public void testLoad_Empty_ecore() throws IOException, InterruptedException {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUtil.initializeEcoreEAnnotationValidators();
		}
		OCL ocl = createOCL();
		doLoad(ocl, getTestModelURI("models/ecore/Empty.ecore"));
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
		XMLResource ecoreResource = AS2Ecore.createResource((EnvironmentFactoryInternal) ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(XMIUtil.createSaveOptions());
		ocl.dispose();
	}

	public void testLoad_Imports_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, getTestModelURI("models/ecore/Imports.ecore"));
		ocl.dispose();
	}

	public void testLoad_Names_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		doLoad(ocl, getTestModelURI("models/ecore/Names.ecore"));
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
		TestFile testFile = createOCLinEcoreFile("Refresh.oclinecore", testFileContents);
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, testFile.getFileURI());
		Resource asResource = doLoad_Concrete2(xtextResource, testFile.getFileURI());
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
		TestFile testFile = createOCLinEcoreFile("Refresh2.oclinecore", testFileContents);
		BaseCSResource xtextResource = doLoad_Concrete1(ocl, testFile.getFileURI());
		Resource asResource = doLoad_Concrete2(xtextResource, testFile.getFileURI());
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
		OCL ocl = createOCLWithProjectMap();
		@NonNull URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ecore", true);
		doLoad(ocl, inputURI, "RoyalAndLoyal", "ecore");
		ocl.dispose();
	}

	public void testLoad_oclstdlib_oclstdlib() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		StandardLibraryContribution.REGISTRY.put(MetamodelManager.DEFAULT_OCL_STDLIB_URI, StandardLibraryContribution.NULL);
		Resource asResource = doLoad_Concrete(ocl, getTestModelURI("models/oclstdlib/oclstdlib.oclstdlib"));
		//		checkMonikers(asResource);
		String ecoreName = "oclstdlib" + ".saved.ecore";
		URI ecoreURI = getTestFileURI(ecoreName);
		Map<String,Object> options = new HashMap<String,Object>();
		options.put(PivotConstants.PRIMITIVE_TYPES_URI_PREFIX, "models/ecore/primitives.ecore#//");
		XMLResource ecoreResource = AS2Ecore.createResource((EnvironmentFactoryInternal) ocl.getEnvironmentFactory(), asResource, ecoreURI, options);
		ecoreResource.save(XMIUtil.createSaveOptions(ecoreResource));
		ocl.dispose();
	}

	public void testLoad_OCL_ecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, getTestModelURI("models/ecore/OCL.ecore"));
		ocl.dispose();
	}

	public void testLoad_Types_oclinecore() throws IOException, InterruptedException {
		OCL ocl = createOCL();
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
		doLoad(ocl, getTestModelURI("models/ecore/Bug323741.ecore"));
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
		ocl.getEnvironmentFactory().setOption(PivotValidationOptions.OptionalDefaultMultiplicity, Boolean.TRUE);
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
		TestFile testFile = createOCLinEcoreFile("Bug402767.oclinecore", testFileContents);
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
		TestFile testFile = createOCLinEcoreFile("Bug403070.oclinecore", testFileContents);
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
		TestFile testFile = createOCLinEcoreFile("Bug450950.ocl", bug450950);
		String message = "\n\tambiguous xmi:id TVXWp\n\t\tbug450950\n\t\tbug450950\n\tcollision at 693728595\n\t\tbug450950\n\t\tbug450950";
		Resource asResource = doLoad_Concrete(ocl, testFile.getFileURI(), StringUtil.bind(PivotMessagesInternal.UnstableXMIid_ERROR_, message));
		assertResourceErrors("Save", asResource, StringUtil.bind(PivotMessagesInternal.UnstableXMIid_ERROR_, message));
		ocl.dispose();
	}

	public void testLoad_Bug441620_completeocl() throws IOException {
		OCL ocl = createOCL();
		BaseCSResource csResource = (BaseCSResource) doLoad_CS(ocl, getTestModelURI("models/ocl/Bug441620.ocl"));
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
		BaseCSResource csResource = (BaseCSResource) doLoad_CS(ocl, getTestModelURI("models/ocl/Bug441620b.ocl"));
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

	/*
	 * Supporting Stereotypes on Ecore models is an enthusiasm too far.
	 *
	public void testLoad_Bug580143ecore_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, getTestModelURI("models/uml/Bug580143ecore.ocl"));
		ocl.dispose();
	} */

	/*
	 * FIXME we really ought to sort out why UML model names are not redirected to their local models.
	 *
	public void testLoad_Bug580143omg_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, getTestModelURI("models/uml/Bug580143omg.ocl"));
		ocl.dispose();
	} */

	public void testLoad_Bug580143uml_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, getTestModelURI("models/uml/Bug580143uml.ocl"));
		ocl.dispose();
	}

	public void testLoad_Fruit_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, getTestModelURI("models/uml/Fruit.ocl"));
		ocl.dispose();
	}

	public void testLoad_Imports_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		doLoad(ocl, getTestModelURI("models/ecore/Imports.ocl"));
		ocl.dispose();
	}

	public void testLoad_MiniPivot_ocl() throws IOException, InterruptedException {
		TestOCL ocl = createOCLWithProjectMap();
		ocl.getEnvironmentFactory().setSafeNavigationValidationSeverity(StatusCodes.Severity.WARNING);
		doLoad_OCL(ocl, getTestModelURI("models/ocl/MiniPivot.ocl"));
		ocl.dispose();
	}

	public void testLoad_Names_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad(ocl, getTestModelURI("models/ecore/Names.ocl"));
		ocl.dispose();
	}

	public void testLoad_OCLTest_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad(ocl, getTestModelURI("models/ecore/OCLTest.ocl"));
		ocl.dispose();
	}

	public void testLoad_Pivot_ocl() throws IOException, InterruptedException {
		TestOCL ocl = createOCLWithProjectMap();
		ocl.getEnvironmentFactory().setSafeNavigationValidationSeverity(StatusCodes.Severity.WARNING);
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doLoad_OCL(ocl, URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ocl", true));
		ocl.dispose();
	}

	public void testLoad_RoyalAndLoyal_ocl() throws IOException, InterruptedException {
		OCL ocl = createOCLWithProjectMap();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		@NonNull URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.project.royalandloyal/model/RoyalAndLoyal.ocl", true);
		doLoad(ocl, inputURI, "RoyalAndLoyal", "ocl");
		ocl.dispose();
	}

	public void testLoad_Internationalized_profile_uml() throws IOException, InterruptedException, ParserException {
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/uml/Internationalized.profile.uml", true);
		doLoadUML(null, uri, false, true, null, null);
	}

	public void testLoad_NullFree_uml() throws IOException, InterruptedException, ParserException {
		UMLStandaloneSetup.init();
		TestOCL ocl = createOCL();
		URI uri = getTestModelURI("models/uml/NullFree.uml");
		Model model = doLoadUML(ocl, uri, false, true, NO_MESSAGES, null);
		org.eclipse.ocl.pivot.Package asPackage = model.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class asInheritedNullFree = ClassUtil.nonNullState(NameUtil.getNameable(asPackage.getOwnedClasses(), "InheritedNullFree"));
		org.eclipse.ocl.pivot.Class asNonNullFree = ClassUtil.nonNullState(NameUtil.getNameable(asPackage.getOwnedClasses(), "NonNullFree"));
		Property inf_nf = ClassUtil.nonNullState(NameUtil.getNameable(asInheritedNullFree.getOwnedProperties(), "nf"));
		Property inf_nnf = ClassUtil.nonNullState(NameUtil.getNameable(asInheritedNullFree.getOwnedProperties(), "nnf"));
		Property inf_inf = ClassUtil.nonNullState(NameUtil.getNameable(asInheritedNullFree.getOwnedProperties(), "inf"));
		Property nnf_nf = ClassUtil.nonNullState(NameUtil.getNameable(asNonNullFree.getOwnedProperties(), "nf"));
		Property nnf_nnf = ClassUtil.nonNullState(NameUtil.getNameable(asNonNullFree.getOwnedProperties(), "nnf"));
		Property nnf_inf = ClassUtil.nonNullState(NameUtil.getNameable(asNonNullFree.getOwnedProperties(), "inf"));
		assertEquals(true, ((CollectionType)inf_nf.getType()).isIsNullFree());
		assertEquals(false, ((CollectionType)inf_nnf.getType()).isIsNullFree());
		assertEquals(true, ((CollectionType)inf_inf.getType()).isIsNullFree());
		assertEquals(true, ((CollectionType)nnf_nf.getType()).isIsNullFree());
		assertEquals(false, ((CollectionType)nnf_nnf.getType()).isIsNullFree());
		assertEquals(false, ((CollectionType)nnf_inf.getType()).isIsNullFree());
		ocl.dispose();
	}

	public void testLoad_StereotypeApplications_uml() throws IOException, InterruptedException, ParserException {
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uri = getTestModelURI("models/uml/StereotypeApplications.uml");
		doLoadUML(null, uri, new AbstractLoadCallBack(false, NO_MESSAGES, false) {
			@Override
			public void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource) {
				for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
					EObject obj = tit.next();
					if (obj instanceof Type) {
						((MetamodelManagerInternal)ocl.getMetamodelManager()).getAllInvariants((Type) obj);		// This gives the Bug 422938 CCE
					}
				}
			}
		}, null);
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
		//	Save the *.oclas and cache the xmi:ids
		//
		URI esasURI = getTestFileURI(ecoreFileName + ".oclas");
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
				EObject eObject2 = ClassUtil.nonNullState(id2eObject.get(id));
				assertEquals(eObject2.getClass(), eObject.getClass());
				newIdCount++;
			}
		}
		assertEquals(oldIdCount, newIdCount);
	}
}
