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
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.resource.ICS2AS;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.PivotableElementCS;
import org.eclipse.ocl.xtext.basecs.impl.PivotableElementCSImpl;
import org.eclipse.ocl.xtext.tests.XtextTestCase;
import org.eclipse.ocl.xtext.tests.pivot.tests.TestOCL;

/**
 * Support for tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class AbstractLoadTests extends XtextTestCase
{
	protected static final @NonNull String @NonNull [] NO_MESSAGES = new @NonNull String[] {};

	protected @NonNull ASResource checkLoadable(@NonNull URI textCSuri, @NonNull String... messages) throws IOException {
		OCL ocl = createOCLWithProjectMap();
		try {
			ASResource asResource = doLoad_Concrete(ocl, textCSuri, messages);
			return asResource;
		}
		finally {
			ocl.dispose();
		}
	}

	@SuppressWarnings("finally")
	protected void checkLoadableFromXMI(@NonNull URI xmiCSuri, @NonNull String... messages) throws IOException {
	//	System.out.println("checkLoadableFromXMI " + xmiCSuri);
		OCL ocl = createOCLWithProjectMap();
		try {
			//
			//	Load the CS resource.
			//
			CSResource csResource = (CSResource)ocl.getResourceSet().getResource(xmiCSuri, true);
			{
				assertNoResourceErrors("Load failed", csResource);
				assertNoUnresolvedProxies("Load failed", csResource);
				assertNoUnresolvedPivots("Load failed", csResource);
				ICS2AS cs2as = csResource.getCS2AS(ocl.getEnvironmentFactory());
				ASResource asResource = cs2as.getASResource();
				assertNoValidationErrors("Loaded pivot", asResource);
				Map<Object, Object> saveOptions = XMIUtil.createSaveOptions(asResource);
				saveOptions.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
				saveOptions.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
				AS2ID.assignIds(asResource, saveOptions);
				assertResourceErrors("Pre-save", asResource, messages);
				cs2as = null;
			}
			//
			//	Explicitly unload the dynamic AS resources.
			//
			{
				EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
				ResourceSet asResourceSet = environmentFactory.getASResourceSet();
				List<@NonNull Resource> asResources = asResourceSet.getResources();
				List<@NonNull Resource> asResourcesCopy = new ArrayList<>(asResources);
				for (Resource resource : asResourcesCopy) {
					ASResource asUnloadResource = (ASResource)resource;
					String scheme = asUnloadResource.getURI().scheme();
					if (!"http".equals(scheme) && !"pathmap".equals(scheme)) {
					//	System.out.println("checkLoadableFromXMI unload " + asUnloadResource.getURI());
						ocl.getEnvironmentFactory().getMetamodelManager().removeASResource(asUnloadResource);
						asResources.remove(asUnloadResource);
					}
				}
				environmentFactory = null;
				asResourcesCopy = null;
			}
			getClass();		// XXX
			//
			//	Check that AS resource proxies can be resolved.
			//
			{
				for (EObject eObject : new TreeIterable(csResource)) {
					if (eObject instanceof PivotableElementCS) {
						for (EStructuralFeature eStructuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
							if (eStructuralFeature instanceof EReference) {
								EReference eReference = (EReference)eStructuralFeature;
								EClass eType = eReference.getEReferenceType();
								if (!eReference.isContainment() && !BaseCSPackage.Literals.ELEMENT_CS.isSuperTypeOf(eType)) {
							//	if (!eReference.isResolveProxies()) {
							//		System.out.println("!isResolveProxies " + eReference + " " + eReference.isResolveProxies());
							//	}
								}
							}
						}
						Element pivot = ((PivotableElementCS)eObject).getPivot();
						if (pivot != null) {
							Resource eResource = pivot.eResource();
							assert eResource != null : "Failed to proxify " + NameUtil.debugSimpleName(pivot);
						}
					}
				}
			}
		//	System.out.println("checkLoadableFromXMI pre-resolveAll");
			EcoreUtil.resolveAll(csResource);
		//	System.out.println("checkLoadableFromXMI post-resolveAll");
			getClass();		// XXX
			for (EObject eObject : new TreeIterable(csResource)) {		// XXX redundant
				if (eObject instanceof PivotableElementCSImpl) {
					Element pivot = ((PivotableElementCSImpl)eObject).getPivot();
					if (pivot != null) {
						assert !pivot.eIsProxy() : "Failed to deproxify " + NameUtil.debugSimpleName(pivot);
					}
				}
			}
			assertNoUnresolvedProxies("Unload then resolve failed", csResource);
			assertNoUnresolvedPivots("Unload then resolve failed", csResource);
		}
		catch (Throwable e) {
			try {
				ocl.dispose();
			}
			finally {
				throw e;		// Don't let a dispose failure occlude the first problem.
			}
		}
		finally {
			ocl.dispose();
		}
	}

	protected void checkMultiplicity(TypedElement typedElement, int lower, int upper) {
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

	public BaseCSResource doLoad_CS(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		BaseCSResource csResource = null;
		try {
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			csResource = (BaseCSResource)ocl.getResourceSet().getResource(inputURI, true);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", csResource);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", csResource);
			//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoUnresolvedPivots("Unresolved pivots", csResource);
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

	public @NonNull ASResource doLoad_Concrete(@NonNull OCL ocl, @NonNull URI inputURI, String... resourceErrors) throws IOException {
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
		return doLoad_Concrete2(ocl, xtextResource, inputURI);
	}
	protected BaseCSResource doLoad_Concrete1(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().createResource(inputURI);
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

	protected @NonNull ASResource doLoad_Concrete2(@NonNull OCL ocl, @NonNull BaseCSResource xtextResource, @NonNull URI inputURI) throws IOException {
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String inputName = stem + "." + extension;
		String pivotName = inputName + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
		URI xmiOutputURI = getXMIoutputURI(inputURI);
		URI pivotURI = getTestFileURI(pivotName);
		URI oclOutputURI = getOCLoutputURI(inputURI);
		ICS2AS cs2as = xtextResource.getCS2AS(ocl.getEnvironmentFactory());
		ASResource asResource = cs2as.getASResource();
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
		assertNoUnresolvedPivots("Unresolved pivots", xtextResource);
		//FIXME		assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		xtextResource.setURI(oclOutputURI);
		Map<Object, Object> saveOptions = XMIUtil.createSaveOptions(xtextResource);
		saveOptions.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
		saveOptions.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
		DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());
		xtextResource.save(saveOptions);
		debugTimestamp.log("Serialization save done");
		xtextResource.setURI(inputURI);
		assertNoResourceErrors("Save failed", xtextResource);
		xtextResource.saveAsXMI(xmiOutputURI);
		assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
		if (asResource.isSaveable()) {
			asResource.setURI(pivotURI);
			asResource.save(saveOptions);
		}
		return asResource;
	}

	public Resource doLoad_OCL(@NonNull OCL ocl, URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = ocl.getResourceSet();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		BaseCSResource xtextResource = null;
		try {
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			xtextResource = (BaseCSResource) resourceSet.getResource(inputURI, true);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", xtextResource);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoUnresolvedPivots("Unresolved pivots", xtextResource);
			assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			xtextResource.setURI(oclOutputURI);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
			DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());
			xtextResource.save(XMIUtil.createSaveOptions(xtextResource));
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
			//	xtextResource.dispose();	-- early dispose may be tidy but it inhibits AS unload proxification
			}
		//	unloadResourceSet(resourceSet);		-- early unload may be tidy but it inhibits AS unload proxification
		}
		assert xtextResource != null;
	//	Resource xmiResource = resourceSet.createResource(xmiOutputURI);
	//	xmiResource.getContents().addAll(xtextResource.getContents());
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		//		xmiResource.save(null);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		//		assertNoResourceErrors("Save failed", xmiResource);
		return xtextResource;
	}

	public Resource doLoad_URI(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		CSResource csResource = (CSResource) ocl.getResourceSet().getResource(inputURI, true);
		assertNoResourceErrors("Load failed", csResource);
		assertNoUnresolvedProxies("Unresolved proxies", csResource);
		assertNoUnresolvedPivots("Unresolved pivots", csResource);
		assertNoValidationErrors("Validation errors", csResource.getContents().get(0));
		return csResource;
	}

	public void doLoadEcore(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		doLoadEcore(ocl, ocl.getResourceSet(), inputURI);
	}

	public void doLoadEcore(@NonNull OCL ocl, @NonNull ResourceSet resourceSet, URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		URI oclOutputURI = getOCLoutputURI(inputURI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
		Resource ecoreResource = resourceSet.getResource(inputURI, true);
		EcoreUtil.resolveAll(ecoreResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
		assertNoResourceErrors("Load failed", ecoreResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
		assertNoUnresolvedProxies("Unresolved proxies", ecoreResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
		EList<@NonNull EObject> contents = ecoreResource.getContents();
		if (contents.size() > 0) {
			assertNoValidationErrors("Validation errors", contents.get(0));
		}
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		ecoreResource.setURI(oclOutputURI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		ecoreResource.save(XMIUtil.createSaveOptions(ecoreResource));
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		assertNoResourceErrors("Save failed", ecoreResource);
		ecoreResource.setURI(inputURI);
	}

	public @NonNull BaseCSResource doLoadOCL(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		URI xmiOutputURI = getXMIoutputURI(inputURI);
		URI oclOutputURI = getOCLoutputURI(inputURI);
		BaseCSResource csResource = null;
		ResourceSet resourceSet = ocl.getResourceSet();
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
		csResource = (BaseCSResource)resourceSet.getResource(inputURI, true);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
		assertNoResourceErrors("Load failed", csResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
		assertNoUnresolvedProxies("Unresolved proxies", csResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
		assertNoUnresolvedPivots("Unresolved pivots", csResource);
		if (csResource.getContents().size() > 0) {
			assertNoValidationErrors("Validation errors", csResource.getContents().get(0));
		}
		CS2AS cs2as = csResource.findCS2AS();
		if (cs2as != null) {
			ASResource asResource = cs2as.getASResource();
			assertNoValidationErrors("Loaded pivot", asResource);
		}
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		csResource.setURI(oclOutputURI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		csResource.save(XMIUtil.createSaveOptions(csResource));
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		assertNoResourceErrors("Save failed", csResource);
		CSResource xmiResource = csResource.saveAsXMI(xmiOutputURI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		assertNoResourceErrors("Save failed", xmiResource);
		return csResource;
	}

	protected @NonNull URI getOCLoutputURI(@NonNull URI inputURI) {
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String savedOutputName = stem + ".saved." + extension;
		return getTestFileURI(savedOutputName);
	}

	protected @NonNull URI getXMIoutputURI(@NonNull URI inputURI) {
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String xmiOutputName = stem + "." + extension + "cs";
		return getTestFileURI(xmiOutputName);
	}

	protected void initializeExtraURIMappings(@NonNull ResourceSet resourceSet) {
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		configurePlatformResources();
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	protected boolean siriusHasUID() {
		try {
			Class<?> jClass = Class.forName("org.eclipse.sirius.viewpoint.ViewpointPackage");
			Field fPackage = jClass.getDeclaredField("eINSTANCE");
			if (fPackage == null) {
				return false;
			}
			Object ePackage = fPackage.get(null);
			if (!(ePackage instanceof EPackage)) {
				return false;
			}
			EClassifier eClass = ((EPackage)ePackage).getEClassifier("IdentifiedElement");	// ViewpointPackage.Literals.IDENTIFIED_ELEMENT
			if (!(eClass instanceof EClass)) {
				return false;
			}
			return ((EClass)eClass).getEStructuralFeature("uid") != null;		// ViewpointPackage.Literals.IDENTIFIED_ELEMENT__UID
		}
		catch (Exception e) {
			return false;
		}
	}
}
