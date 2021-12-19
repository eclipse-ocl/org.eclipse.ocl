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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.model.OCLmetamodel;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;

import com.google.common.collect.Iterables;

/**
 * Tests.
 */
@SuppressWarnings("null")
public class OCLstdlibTests extends XtextTestCase
{
	public static class MonikeredComparator implements Comparator<Element>
	{
		public static final Comparator<? super Element> INSTANCE = new MonikeredComparator();

		@Override
		public int compare(Element o1, Element o2) {
			String m1 = AS2Moniker.toString(o1);
			String m2 = AS2Moniker.toString(o2);
			return m1.compareTo(m2);
		}
	}

	//	protected MetamodelManager metamodelManager = null;

	public Map<String, Element> computeMoniker2ASMap(Collection<? extends Resource> pivotResources) {
		Map<String, Element> map = new HashMap<String, Element>();
		for (Resource asResource : pivotResources) {
			assert asResource != null;
			for (Iterator<EObject> it = asResource.getAllContents(); it.hasNext();) {
				EObject eObject = it.next();
				assert eObject.eResource() == asResource;
				if ((eObject instanceof Element) && !(eObject instanceof TemplateParameter) && !(eObject instanceof Comment) /*&& (eObject != orphanagePackage)*/) {
					Element newElement = (Element) eObject;
					String moniker = AS2Moniker.toString(newElement);
					assert moniker != null;
					Element oldElement = map.get(moniker);
					if (oldElement == null) {
						map.put(moniker, newElement);
					}
					else {
						assert newElement.getClass() == oldElement.getClass();
					}
				}
			}
		}
		return map;
	}

	protected Resource doLoadAS(@NonNull ResourceSet resourceSet, @NonNull URI libraryURI, @NonNull Resource javaResource, boolean validateMonikers) {
		Resource asResource = resourceSet.getResource(libraryURI, true);
		assertNoResourceErrors("Load failed", asResource);
		assertNoUnresolvedProxies("File Model", asResource);
		assertNoValidationErrors("File Model", asResource);
		//		PivotAliasCreator.refreshPackageAliases(javaResource);
		assertNoResourceErrors("Java Model", javaResource);
		assertNoUnresolvedProxies("Java Model", javaResource);
		assertNoValidationErrors("Java Model", javaResource);
		if (!validateMonikers) {
			return asResource;
		}
		//
		//	Check similar content
		//
		Map<String,Element> fileMoniker2asMap = computeMoniker2ASMap(Collections.singletonList(asResource));
		//		for (String moniker : fileMoniker2asMap.keySet()) {
		//			System.out.println("File : " + moniker);
		//		}
		Map<String,Element> javaMoniker2asMap = computeMoniker2ASMap(Collections.singletonList(javaResource));
		//		for (String moniker : javaMoniker2asMap.keySet()) {
		//			System.out.println("Java : " + moniker);
		//		}
		//		assertEquals(fileMoniker2asMap.size(), javaMoniker2asMap.size());
		for (String moniker : fileMoniker2asMap.keySet()) {
			Element fileElement = fileMoniker2asMap.get(moniker);
			Element javaElement = javaMoniker2asMap.get(moniker);
			if (javaElement == null) {
				boolean isExpression = false;
				for (EObject eObject = fileElement; eObject != null; eObject = eObject.eContainer()) {
					if ((eObject instanceof ExpressionInOCL) || (eObject instanceof Constraint) || (eObject instanceof Annotation)) {
						isExpression = true;		// Embedded OCL not present in Java
						break;
					}
				}
				if (isExpression) {
					continue;
				}
			}
			assertNotNull("Missing java element for '" + moniker + "'", javaElement);
			//			@SuppressWarnings("null")	// Can be null and we'll have an NPE as the test failure.
			assert javaElement != null;
			assert fileElement != null;
			Class<? extends Element> javaElementClass = javaElement.getClass();
			assertEquals(fileElement.getClass(), javaElementClass);
			if (fileElement instanceof TypedElement) {
				Type fileType = ((TypedElement)fileElement).getType();
				Type javaType = ((TypedElement)javaElement).getType();
				assertEquals(fileType.getClass(), javaType.getClass());
				String fileMoniker = AS2Moniker.toString(fileType);
				String javaMoniker = AS2Moniker.toString(javaType);
				assertEquals(fileMoniker, javaMoniker);
			}
			if (fileElement instanceof Feature) {
				String fileClass = ((Feature)fileElement).getImplementationClass();
				String javaClass = ((Feature)javaElement).getImplementationClass();
				if (fileClass == null) {
					LibraryFeature implementation = ((Feature)fileElement).getImplementation();
					if (implementation != null) {
						fileClass = implementation.getClass().getCanonicalName();
					}
				}
				if (javaClass == null) {
					LibraryFeature implementation = ((Feature)javaElement).getImplementation();
					if (implementation != null) {
						javaClass = implementation.getClass().getCanonicalName();
					}
				}
				assertEquals(fileClass, javaClass);
			}
			if (fileElement instanceof org.eclipse.ocl.pivot.Class) {
				List<Element> fileTypes = new ArrayList<Element>(((org.eclipse.ocl.pivot.Class)fileElement).getSuperClasses());
				List<Element> javaTypes = new ArrayList<Element>(((org.eclipse.ocl.pivot.Class)javaElement).getSuperClasses());
				Collections.sort(fileTypes, MonikeredComparator.INSTANCE);
				Collections.sort(javaTypes, MonikeredComparator.INSTANCE);
				assertEquals(fileTypes.size(), javaTypes.size());
				for (int i = 0; i < fileTypes.size(); i++) {
					Element fileType = fileTypes.get(i);
					Element javaType = javaTypes.get(i);
					String fileMoniker = AS2Moniker.toString(fileType);
					String javaMoniker = AS2Moniker.toString(javaType);
					assertEquals(fileMoniker, javaMoniker);
				}
			}
		}
		return asResource;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//		metamodelManager = OCL.createEnvironmentFactory(getProjectMap()).getMetamodelManager();
	}

	@Override
	protected void tearDown() throws Exception {
		//		EnvironmentFactoryResourceSetAdapter adapter = EnvironmentFactoryResourceSetAdapter.findAdapter(resourceSet);
		//		if (adapter != null) {
		//			MetamodelManager metamodelManager = adapter.getMetamodelManager();
		//			if (metamodelManager != null) {
		//				metamodelManager.dispose();
		//			}
		//		}
		//		metamodelManager.dispose();
		//		metamodelManager = null;
		super.tearDown();
	}

	public void testLoadAsString() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		String testFile =
				"library lib : lib = 'http://mylib'{\n"+
						"    type OclAny : AnyType {\n"+
						"    	operation a(elem : Boolean) : Integer {\n"+
						"           post a: elem;\n"+
						"       }\n"+
						"    }\n"+
						"    type Bag(T) : BagType conformsTo Collection(T) {}\n"+
						"    type Class conformsTo OclAny {}\n"+
						"    type Boolean : BooleanType conformsTo OclAny {}\n"+
						"    type Collection(T) : CollectionType conformsTo OclAny {}\n"+
						"    type Integer : PrimitiveType conformsTo Real {}\n"+
						"    type OclComparable conformsTo OclAny {\n"+
						"        operation compareTo(that : OclSelf) : Integer[1] => 'org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation';\n"+
						"    }\n"+
						"    type OclElement conformsTo OclAny {}\n"+
						"    type OclEnumeration conformsTo OclAny {}\n"+
						"    type OclInvalid : InvalidType {}\n"+
						"    type OclSelf : SelfType conformsTo OclAny {}\n"+
						"    type OclVoid : VoidType {}\n"+
						"    type OrderedCollection(T) : CollectionType conformsTo Collection(T) {}\n"+
						"    type OrderedSet(T) : OrderedSetType conformsTo Collection(T) {}\n"+
						"    type Real : PrimitiveType conformsTo OclComparable {}\n"+
						"    type Sequence(T) : SequenceType conformsTo Collection(T) {}\n"+
						"    type Set(T) : SetType conformsTo Collection(T) {}\n"+
						"    type String : PrimitiveType conformsTo OclAny {}\n"+
						"    type UniqueCollection(T) : CollectionType conformsTo Collection(T) {}\n"+
						"    type UnlimitedNatural : PrimitiveType conformsTo Integer {}\n"+
						"}\n";
		doLoadFromString(ocl, "string.oclstdlib", testFile);
		ocl.dispose();
	}

	public void testImport() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		getTestFileURI("minimal.oclstdlib", ocl, getTestModelURI("models/oclstdlib/minimal.oclstdlib"));
		String testFile =
				"import 'minimal.oclstdlib';\n"+
						"import 'minimal.oclstdlib';\n"+
						"library lib : lib = 'http://minimal.oclstdlib'{\n"+
						"    type OclAny : AnyType {\n"+
						"    	operation a(elem : Boolean) : Boolean {\n"+
						"           post a: result = elem;\n"+
						"       }\n"+
						"    }\n"+
						"}\n";
		Resource asResource = doLoadASResourceFromString(ocl, "string.oclstdlib", testFile);
		PivotMetamodelManager metamodelManager = PivotMetamodelManager.getAdapter(asResource.getResourceSet());
		AnyType oclAnyType = metamodelManager.getStandardLibrary().getOclAnyType();
		Iterable<Operation> ownedOperations = metamodelManager.getAllOperations(oclAnyType, FeatureFilter.SELECT_NON_STATIC);
		assertEquals(2, Iterables.size(ownedOperations));		// one from OclAny::=
		ocl.dispose();
	}

	/**
	 * Checks that the local oclstdlib.oclstdlib is the same as the pre-compiled
	 * Java implementation.
	 *
	 * FIXME check the library/model version instead.
	 */
	public void testOCLstdlib() throws Exception {
		OCL ocl = OCL.newInstance(OCL.CLASS_PATH);
		//
		//	Load OCL stdlib as a file.
		//
		ResourceSet resourceSet = ocl.getResourceSet();
		URI libraryURI = URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib", true);
		BaseCSResource xtextResource = (BaseCSResource) resourceSet.createResource(libraryURI);
		JavaClassScope.getAdapter(xtextResource, getClass().getClassLoader());
		ocl.getEnvironmentFactory().adapt(xtextResource);
		InputStream inputStream = ocl.getResourceSet().getURIConverter().createInputStream(libraryURI);
		xtextResource.load(inputStream, null);
		CS2AS cs2as = ClassUtil.nonNullState(xtextResource.findCS2AS());
		assertNoResourceErrors("Load failed", xtextResource);
		Resource fileResource = cs2as.getASResource();
		assertNoResourceErrors("File Model", fileResource);
		assertNoUnresolvedProxies("File Model", fileResource);
		assertNoValidationErrors("File Model", fileResource);
		//
		//	Load 'oclstdlib.oclstdlib' as pre-code-generated Java.
		//
		Resource javaResource = OCLstdlib.getDefault();
		//		PivotAliasCreator.refreshPackageAliases(javaResource);
		assertNoResourceErrors("Java Model", javaResource);
		assertNoUnresolvedProxies("Java Model", javaResource);
	//	assertNoValidationErrors("Java Model", javaResource);	-- FIXME BUG 577825 do not validate since parsing embedded OCL references custom library from standard leadig to proxies
		//
		//	Check similar content
		//
		Map<String,Element> fileMoniker2asMap = computeMoniker2ASMap(Collections.singletonList(fileResource));
		//		for (String moniker : fileMoniker2asMap.keySet()) {
		//			System.out.println("File : " + moniker);
		//		}
		Map<String,Element> javaMoniker2asMap = computeMoniker2ASMap(Collections.singletonList(javaResource));
		//		for (String moniker : javaMoniker2asMap.keySet()) {
		//			System.out.println("Java : " + moniker);
		//		}
		//		assertEquals(fileMoniker2asMap.size(), javaMoniker2asMap.size());
		for (String moniker : fileMoniker2asMap.keySet()) {
			Element fileElement = fileMoniker2asMap.get(moniker);
			Element javaElement = javaMoniker2asMap.get(moniker);
			if (javaElement == null) {
				boolean isExpression = false;
				for (EObject eObject = fileElement; eObject != null; eObject = eObject.eContainer()) {
					if ((eObject instanceof ExpressionInOCL) || (eObject instanceof Constraint) || (eObject instanceof Annotation)) {
						isExpression = true;		// Embedded OCL not present in Java
						break;
					}
				}
				if (isExpression) {
					continue;
				}
			}
			assertNotNull("Missing java element for '" + moniker + "'", javaElement);
			//			@SuppressWarnings("null")	// Can be null and we'll have an NPE as the test failure.
			assert javaElement != null;
			assert fileElement != null;
			Class<? extends Element> javaElementClass = javaElement.getClass();
			assertEquals(fileElement.getClass(), javaElementClass);
			if (fileElement instanceof TypedElement) {
				Type fileType = ((TypedElement)fileElement).getType();
				Type javaType = ((TypedElement)javaElement).getType();
				assertEquals(fileType.getClass(), javaType.getClass());
				String fileMoniker = AS2Moniker.toString(fileType);
				String javaMoniker = AS2Moniker.toString(javaType);
				assertEquals(fileMoniker, javaMoniker);
			}
			if (fileElement instanceof Feature) {
				String fileClass = ((Feature)fileElement).getImplementationClass();
				String javaClass = ((Feature)javaElement).getImplementationClass();
				if (fileClass == null) {
					LibraryFeature implementation = ((Feature)fileElement).getImplementation();
					if (implementation != null) {
						fileClass = implementation.getClass().getCanonicalName();
					}
				}
				if (javaClass == null) {
					LibraryFeature implementation = ((Feature)javaElement).getImplementation();
					if (implementation != null) {
						javaClass = implementation.getClass().getCanonicalName();
					}
				}
				assertEquals(fileClass, javaClass);
			}
			if (fileElement instanceof org.eclipse.ocl.pivot.Class) {
				List<Element> fileTypes = new ArrayList<Element>(((org.eclipse.ocl.pivot.Class)fileElement).getSuperClasses());
				List<Element> javaTypes = new ArrayList<Element>(((org.eclipse.ocl.pivot.Class)javaElement).getSuperClasses());
				Collections.sort(fileTypes, MonikeredComparator.INSTANCE);
				Collections.sort(javaTypes, MonikeredComparator.INSTANCE);
				assertEquals(fileTypes.size(), javaTypes.size());
				for (int i = 0; i < fileTypes.size(); i++) {
					Element fileType = fileTypes.get(i);
					Element javaType = javaTypes.get(i);
					String fileMoniker = AS2Moniker.toString(fileType);
					String javaMoniker = AS2Moniker.toString(javaType);
					assertEquals(fileMoniker, javaMoniker);
				}
			}
		}
		ocl.dispose();
	}

	/**
	 * Checks that the OCL 2.5 AS model is the same as the pre-compiled
	 * Java implementation.
	 */
	public void testOCLstdlib_AS() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		//
		//	Load OCL stdlib as an AS file.
		//
		//		ResourceSet resourceSet = ocl.getResourceSet();
		//		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
		//			getProjectMap().initializeResourceSet(resourceSet);
		//		}
		URI libraryURI = URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model-gen/OCL-2.5.oclas", true);
		//
		//	Load 'oclstdlib.oclstdlib' as pre-code-generated Java.
		//
		Resource javaResource = OCLstdlib.getDefault();
		@SuppressWarnings("unused")Resource asResource = doLoadAS(ocl.getMetamodelManager().getASResourceSet(), libraryURI, javaResource, true);
		ocl.dispose();
	}

	/**
	 * Checks that the OCL 2.5 AS model is the same as the pre-compiled
	 * Java implementation accessed as a platform:/resource.
	 */
	public void testPivot_AS_resource() throws Exception {
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		//
		//	Load OCL stdlib as an AS file.
		//
		//		ResourceSet resourceSet = new ResourceSetImpl();
		//		getProjectMap().initializeResourceSet(resourceSet);
		//		ASResourceFactoryRegistry.INSTANCE.configureResourceSet(resourceSet);
		URI pivotURI = URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model-gen/Pivot.oclas", true);
		//
		//	Load OCLmetamodel as pre-code-generated Java.
		//
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		Library asLibrary = (Library) standardLibrary.getPackage();
		org.eclipse.ocl.pivot.Package oclMetamodel = OCLmetamodel.create(standardLibrary, asLibrary.getName(), asLibrary.getNsPrefix(), OCLmetamodel.PIVOT_URI);
		Resource javaResource = oclMetamodel.eResource();
		@SuppressWarnings("unused")
		Resource asResource = doLoadAS(ocl.getMetamodelManager().getASResourceSet(), pivotURI, javaResource, false);		// FIXME Contents are far from identical
		ocl.dispose();
	}

	/**
	 * Checks that the OCL 2.5 AS model is the same as the pre-compiled
	 * Java implementation accessed as a platform:/plugin.
	 */
	public void testPivot_AS_bundle() throws Exception {
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		//
		//	Load OCL stdlib as an AS file.
		//
		//		ResourceSet resourceSet = new ResourceSetImpl();
		//		getProjectMap().initializeResourceSet(resourceSet);
		//		ASResourceFactoryRegistry.INSTANCE.configureResourceSet(resourceSet);
		URI pivotURI = URI.createPlatformPluginURI("org.eclipse.ocl.pivot/model-gen/Pivot.oclas", true);
		//
		//	Load OCLmetamodel as pre-code-generated Java.
		//
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		Library asLibrary = (Library) standardLibrary.getPackage();
		org.eclipse.ocl.pivot.Package oclMetamodel = OCLmetamodel.create(standardLibrary, asLibrary.getName(), asLibrary.getNsPrefix(), OCLmetamodel.PIVOT_URI);
		Resource javaResource = oclMetamodel.eResource();
		@SuppressWarnings("unused")
		Resource asResource = doLoadAS(ocl.getMetamodelManager().getASResourceSet(), pivotURI, javaResource, false);		// FIXME Contents are far from identical
		ocl.dispose();
	}
}
