/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.uml25.XMI252UMLResourceFactoryImpl;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.ProjectManager.IProjectDescriptor;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.util.PivotValidator;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.ocl.xtext.tests.pivot.tests.TestOCL;

import junit.framework.TestCase;

/**
 * Support for tests that load a UML model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class AbstractUMLLoadTests extends AbstractLoadTests
{
	public static interface ILoadCallBack {
		boolean ignoreNonExistence();
		void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource);
		void validateCompleteOCL(@NonNull OCL ocl, @NonNull BaseCSResource reloadCS) throws IOException;
		void validateEmbeddedOCL(@NonNull OCL ocl, @NonNull Constraint eObject) throws ParserException;
	}

	protected static class AbstractLoadCallBack implements ILoadCallBack
	{
		private final boolean ignoreNonExistence;
		private final @NonNull String @Nullable [] validateCompleteOCLMessages;
		private final boolean validateEmbeddedOCL;

		public AbstractLoadCallBack(boolean ignoreNonExistence, @NonNull String @Nullable [] validateCompleteOCLMessages, boolean validateEmbeddedOCL) {
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
				Resource reloadAS = reloadCS.getCS2AS(ocl.getEnvironmentFactory()).getASResource();
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
	protected @Nullable Map<URI, URI> extraURImap = null;

	public @NonNull TestOCL createOCL(@Nullable URI modelFolderURI) {
		if (modelFolderURI == null) {
			return super.createOCL();
		}
		UMLStandaloneSetup.init();
		TestOCL ocl = new TestOCL(getTestFileSystem(), "UML25LoadTests", getName(), OCL.NO_PROJECTS, null);
		ResourceSet resourceSet = ocl.getResourceSet();
		extraURImap = XMI252UMLResourceFactoryImpl.install(resourceSet, modelFolderURI);
		initializeExtraURIMappings(resourceSet);
		return ocl;
	}

	@Override
	public @NonNull TestOCL createOCLWithProjectMap() {
		UMLStandaloneSetup.init();
		TestOCL ocl = new TestOCL(getTestFileSystem(), "UML25LoadTests", getName(), getProjectMap(), null);
		ResourceSet resourceSet = ocl.getResourceSet();
		extraURImap = XMI252UMLResourceFactoryImpl.install(resourceSet, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true));
		initializeExtraURIMappings(resourceSet);
		return ocl;
	}

	public Model doLoadUML(@Nullable TestOCL ocl, @NonNull URI inputURI, boolean ignoreNonExistence, boolean validateEmbeddedOCL, @NonNull String @Nullable [] validateCompleteOCLMessages, @NonNull String @Nullable [] messages) throws IOException, ParserException {
		assert !ignoreNonExistence;
		assert validateEmbeddedOCL;
		AbstractLoadCallBack loadCallBacks = new AbstractLoadCallBack(ignoreNonExistence, validateCompleteOCLMessages, validateEmbeddedOCL);
		return doLoadUML(ocl, inputURI, loadCallBacks, messages);
	}

	public Model doLoadUML(@Nullable TestOCL externalOCL, @NonNull URI inputURI, @NonNull ILoadCallBack loadCallBacks, @NonNull String @Nullable [] messages) throws IOException, ParserException {
		UMLStandaloneSetup.init();
		String stem = inputURI.trimFileExtension().lastSegment();
		String oclName = stem + ".ocl";
		URI oclOutputURI = getOCLoutputURI(inputURI);		// NB getTestProject() may activate PackageExplorerPart
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
			IProjectDescriptor projectDescriptor1 = getProjectMap().getProjectDescriptor("org.eclipse.uml2.uml");
			StandaloneProjectMap.IProjectDescriptor projectDescriptor2 = ClassUtil.requireNonNull(projectDescriptor1);
			projectDescriptor2.initializeURIMap(URIConverter.URI_MAP);		// *.ecore2xml must be global
		}
		EnvironmentFactory environmentFactory = internalOCL.getEnvironmentFactory();
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
			umlResource.setURI(oclOutputURI);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			umlResource.save(XMIUtil.createSaveOptions(umlResource));
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
					UML2AS uml2as = UML2AS.getAdapter(uResource, environmentFactory);
					Model asModel = uml2as.getASModel();
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
								xtextResource.save(XMIUtil.createSaveOptions(xtextResource));
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
				// XXX	ocl2.getEnvironmentFactory().adapt(reloadCS);
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

	@Override
	protected void initializeExtraURIMappings(@NonNull ResourceSet resourceSet) {
		if (extraURImap != null) {
			resourceSet.getURIConverter().getURIMap().putAll(extraURImap);
		}
	}

	@Override
	protected void tearDown() throws Exception {
		extraURImap = null;
		assert EValidator.Registry.INSTANCE.get(PivotPackage.eINSTANCE) == PivotValidator.INSTANCE;	// Verify global integrity - Bug 582494
		super.tearDown();
	}
}
