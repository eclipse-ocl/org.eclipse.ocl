/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.test.ecore;

import org.apache.log4j.Appender;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestCaseLogger;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 */
public abstract class AbstractProjectMapTest extends PivotTestCaseWithAutoTearDown
{
/*	protected void doTestProjectMapRegistered(/ *@NonNull* / EPackage modelPackage, @NonNull String modelPath) {
		URI nsURI = URI.createURI(modelPackage.getNsURI());
		URI pluginURI = URI.createPlatformPluginURI(modelPath, true);
		URI resourceURI = URI.createPlatformResourceURI(modelPath, true);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		ProjectMap getProjectMap() = new ProjectMap();
		getProjectMap().initializeResourceSet(resourceSet);
//		IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor(project);
//		IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
		getProjectMap().configure(resourceSet, StandaloneProjectMap.LoadModelStrategy.INSTANCE, null);
		Resource registeredResource = resourceSet.getPackageRegistry().getEPackage(nsURI.toString()).eResource();
		assertNull(registeredResource.getResourceSet());		// Registered packages have a private shared resource
		assertEquals(registeredResource.getURI(), nsURI);
		Resource platformPluginEObject = resourceSet.getResource(pluginURI, true);
		Resource platformResourceEObject = resourceSet.getResource(resourceURI, true);
		assertEquals(registeredResource, platformPluginEObject);
		assertEquals(registeredResource, platformResourceEObject);
		assertNull(registeredResource.getResourceSet());
	} */

/*	protected void doTestProjectMapLocal(@NonNull EPackage ePackage, @NonNull String project, @NonNull String modelPath) {
		ProjectMap.getResourceFactoryRegistry(null).getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		@NonNull URI nsURI = URI.createURI(ePackage.getNsURI());
		@NonNull URI platformPluginURI = URI.createPlatformPluginURI(modelPath, true);
		@NonNull URI platformResourceURI = URI.createPlatformResourceURI(modelPath, true);
		ProjectMap getProjectMap() = new ProjectMap();
		IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor(project);
		assert projectDescriptor != null;
		IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadEPackageStrategy.INSTANCE, null);
			Resource resource = resourceSet.getPackageRegistry().getEPackage(nsURI.toString()).eResource();
			assertTrue(ClassUtil.isRegistered(resource));
			assertEquals(nsURI, resource.getURI());
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			assertEquals(resource, platformPluginEObject);
			assertEquals(resource, platformResourceEObject);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadModelStrategy.INSTANCE, null);
			Resource resource = resourceSet.getPackageRegistry().getEPackage(nsURI.toString()).eResource();
			assertFalse(ClassUtil.isRegistered(resource));
			assertEquals(platformResourceURI, resource.getURI());
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			assertEquals(resource, platformPluginEObject);
			assertEquals(resource, platformResourceEObject);
		}
	} */

	@Override
	protected void setUp() throws Exception {
		super.setUp();
    	TestCaseAppender.INSTANCE.install();
	}

	public @Nullable ResourceSet destroyResourceSet(@NonNull ResourceSet resourceSet) {
		getProjectMap().unload(resourceSet);
		disposeResourceSet(resourceSet);
		return null;
	}

	protected void doTestProjectMap_LoadBoth(/*@NonNull*/ EPackage ePackage, @NonNull String project, @NonNull String modelPath, @NonNull String fragment) {
		ProjectMap.getResourceFactoryRegistry(null).getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		@NonNull URI platformPluginURI = URI.createPlatformPluginURI(modelPath, true);
		@NonNull URI platformResourceURI = URI.createPlatformResourceURI(modelPath, true);
		@NonNull URI nsURI = URI.createURI(ePackage.getNsURI());
		@NonNull URI platformPluginEObjectURI = platformPluginURI.appendFragment(fragment);
		@NonNull URI platformResourceEObjectURI = platformResourceURI.appendFragment(fragment);
		ProjectManager.IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor(project);
		assert projectDescriptor != null;
		ProjectManager.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
		assert packageDescriptor != null;
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadBothStrategy.INSTANCE, null);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertFalse(ClassUtil.isRegistered(platformPluginEObject.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(platformPluginURI, platformPluginEObject.eResource().getURI());
			assertFalse(nsEPackage == platformPluginEObject);
			assertEquals(platformPluginEObject, platformResourceEObject);
			resourceSet = destroyResourceSet(resourceSet);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadBothStrategy.INSTANCE, null);
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertFalse(ClassUtil.isRegistered(platformPluginEObject.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(platformPluginURI, platformPluginEObject.eResource().getURI());
			assertFalse(nsEPackage == platformPluginEObject);
			assertEquals(platformPluginEObject, platformResourceEObject);
			resourceSet = destroyResourceSet(resourceSet);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadBothStrategy.INSTANCE, null);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertFalse(ClassUtil.isRegistered(platformPluginEObject.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(platformResourceURI, platformPluginEObject.eResource().getURI());
			assertFalse(nsEPackage == platformPluginEObject);
			assertEquals(platformPluginEObject, platformResourceEObject);
			resourceSet = destroyResourceSet(resourceSet);
		}
	}

	protected void doTestProjectMap_LoadDefault(/*@NonNull*/ EPackage ePackage, @NonNull String project, @NonNull String modelPath, @NonNull String fragment, boolean selfReferential) {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			ProjectMap.getResourceFactoryRegistry(null).getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
			@NonNull URI platformPluginURI = URI.createPlatformPluginURI(modelPath, true);
			@NonNull URI platformResourceURI = URI.createPlatformResourceURI(modelPath, true);
			@NonNull URI nsURI = URI.createURI(ePackage.getNsURI());
			@NonNull URI platformPluginEObjectURI = platformPluginURI.appendFragment(fragment);
			@NonNull URI platformResourceEObjectURI = platformResourceURI.appendFragment(fragment);
			{
				ResourceSet resourceSet = new ResourceSetImpl();
				getProjectMap().initializeResourceSet(resourceSet);
				EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
				EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
				EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
				assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
				assertEquals(nsURI.toString(), nsEPackage.getNsURI());
				assertEquals(nsEPackage, platformPluginEObject);
				assertEquals(nsEPackage, platformResourceEObject);
				assertEquals("Conflicting access to '" + platformResourceURI + "' or '" + platformPluginURI + "' already accessed as '" + nsURI + "'", TestCaseLogger.INSTANCE.get());
				resourceSet = destroyResourceSet(resourceSet);
			}
			TestCaseLogger.INSTANCE.clear();
			{
				ResourceSet resourceSet = new ResourceSetImpl();
				getProjectMap().initializeResourceSet(resourceSet);
				EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
				EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
				EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
				assertEquals(selfReferential, ClassUtil.isRegistered(nsEPackage.eResource()));
				assertEquals(selfReferential, !platformPluginURI.equals(nsEPackage.eResource().getURI()));
				assertEquals(selfReferential, nsEPackage != platformPluginEObject);
				assertEquals(platformPluginEObject, platformResourceEObject);
				if (!selfReferential) {
					assertEquals(selfReferential ? "" : "Conflicting access to '" + nsURI + "' already accessed as '" + platformPluginURI + "'", TestCaseLogger.INSTANCE.get());
				}
				resourceSet = destroyResourceSet(resourceSet);
			}
			TestCaseLogger.INSTANCE.clear();
			{
				ResourceSet resourceSet = new ResourceSetImpl();
				getProjectMap().initializeResourceSet(resourceSet);
				EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
				EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
				EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
				assertEquals(selfReferential, ClassUtil.isRegistered(nsEPackage.eResource()));
				assertEquals(selfReferential, !platformResourceURI.equals(nsEPackage.eResource().getURI()));
				assertEquals(selfReferential, nsEPackage != platformPluginEObject);
				assertEquals(platformPluginEObject, platformResourceEObject);
				if (!selfReferential) {
					assertEquals(selfReferential ? "" : "Conflicting access to '" + nsURI + "' already accessed as '" + platformResourceURI + "'", TestCaseLogger.INSTANCE.get());
				}
				resourceSet = destroyResourceSet(resourceSet);
			}
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	protected void doTestProjectMap_LoadEPackage(/*@NonNull*/ EPackage ePackage, @NonNull String project, @NonNull String modelPath, @NonNull String fragment) {
		ProjectMap.getResourceFactoryRegistry(null).getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		@NonNull URI platformPluginURI = URI.createPlatformPluginURI(modelPath, true);
		@NonNull URI platformResourceURI = URI.createPlatformResourceURI(modelPath, true);
		@NonNull URI nsURI = URI.createURI(ePackage.getNsURI());
		@NonNull URI platformPluginEObjectURI = platformPluginURI.appendFragment(fragment);
		@NonNull URI platformResourceEObjectURI = platformResourceURI.appendFragment(fragment);
		ProjectManager.IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor(project);
		assert projectDescriptor != null;
		ProjectManager.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
		assert packageDescriptor != null;
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, null);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(nsEPackage, platformPluginEObject);
			assertEquals(nsEPackage, platformResourceEObject);
			resourceSet = destroyResourceSet(resourceSet);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, null);
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(nsEPackage, platformPluginEObject);
			assertEquals(nsEPackage, platformResourceEObject);
			resourceSet = destroyResourceSet(resourceSet);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			getProjectMap().initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, null);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(nsEPackage, platformPluginEObject);
			assertEquals(nsEPackage, platformResourceEObject);
			resourceSet = destroyResourceSet(resourceSet);
		}
	}

	protected void doTestProjectMap_LoadFirst(/*@NonNull*/ EPackage ePackage, @NonNull String project, @NonNull String modelPath, @NonNull String fragment, boolean selfReferential) {
		ProjectMap.getResourceFactoryRegistry(null).getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		@NonNull URI platformPluginURI = URI.createPlatformPluginURI(modelPath, true);
		@NonNull URI platformResourceURI = URI.createPlatformResourceURI(modelPath, true);
		@NonNull URI nsURI = URI.createURI(ePackage.getNsURI());
		@NonNull URI platformPluginEObjectURI = platformPluginURI.appendFragment(fragment);
		@NonNull URI platformResourceEObjectURI = platformResourceURI.appendFragment(fragment);
		StandaloneProjectMap projectMap = getProjectMap();
		ProjectManager.IProjectDescriptor projectDescriptor = projectMap.getProjectDescriptor(project);
		assert projectDescriptor != null;
		ProjectManager.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
		assert packageDescriptor != null;
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			projectMap.initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadFirstStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			assertTrue(ClassUtil.isRegistered(nsEPackage.eResource()));
			assertEquals(nsURI.toString(), nsEPackage.getNsURI());
			assertEquals(nsEPackage, platformPluginEObject);
			assertEquals(nsEPackage, platformResourceEObject);
			resourceSet.eAdapters().remove(projectMap);
			resourceSet = destroyResourceSet(resourceSet);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			projectMap.initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadFirstStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			assertEquals(selfReferential, ClassUtil.isRegistered(nsEPackage.eResource()));
			assertEquals(selfReferential, !platformPluginURI.equals(nsEPackage.eResource().getURI()));
			assertEquals(selfReferential, nsEPackage != platformPluginEObject);
			assertEquals(platformPluginEObject, platformResourceEObject);
			resourceSet.eAdapters().remove(projectMap);
			resourceSet = destroyResourceSet(resourceSet);
		}
		{
			ResourceSet resourceSet = new ResourceSetImpl();
			projectMap.initializeResourceSet(resourceSet);
			packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadFirstStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
			EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
			EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
			EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
			assertEquals(selfReferential, ClassUtil.isRegistered(nsEPackage.eResource()));
			assertEquals(selfReferential, !platformResourceURI.equals(nsEPackage.eResource().getURI()));
			assertEquals(selfReferential, nsEPackage != platformPluginEObject);
			assertEquals(platformPluginEObject, platformResourceEObject);
			resourceSet.eAdapters().remove(projectMap);
			resourceSet = destroyResourceSet(resourceSet);
		}
	}

	protected void doTestProjectMap_LoadModel(/*@NonNull*/ EPackage ePackage, @NonNull String project, @NonNull String modelPath, @NonNull String fragment, boolean selfReferential) {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			ProjectMap.getResourceFactoryRegistry(null).getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
			@NonNull URI platformPluginURI = URI.createPlatformPluginURI(modelPath, true);
			@NonNull URI platformResourceURI = URI.createPlatformResourceURI(modelPath, true);
			@NonNull URI nsURI = URI.createURI(ePackage.getNsURI());
			@NonNull URI platformPluginEObjectURI = platformPluginURI.appendFragment(fragment);
			@NonNull URI platformResourceEObjectURI = platformResourceURI.appendFragment(fragment);
			ProjectManager.IProjectDescriptor projectDescriptor = getProjectMap().getProjectDescriptor(project);
			assert projectDescriptor != null;
			ProjectManager.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
			assert packageDescriptor != null;
			{
				ResourceSet resourceSet = new ResourceSetImpl();
				getProjectMap().initializeResourceSet(resourceSet);
				packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadDynamicResourceStrategy.INSTANCE, null);
				EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
				EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
				EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
				assertFalse(ClassUtil.isRegistered(nsEPackage.eResource()));
				assertEquals(platformResourceURI, nsEPackage.eResource().getURI());
				assertEquals(nsEPackage, platformPluginEObject);
				assertEquals(nsEPackage, platformResourceEObject);
				assertEquals(selfReferential ? "Attempt to load self-referential '" + nsURI + "' as model replaced by registered EPackage" : "", TestCaseLogger.INSTANCE.get());
				resourceSet = destroyResourceSet(resourceSet);
			}
			TestCaseLogger.INSTANCE.clear();
			{
				ResourceSet resourceSet = new ResourceSetImpl();
				getProjectMap().initializeResourceSet(resourceSet);
				packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadDynamicResourceStrategy.INSTANCE, null);
				EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
				EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
				EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
				assertEquals(false/*selfReferential*/, ClassUtil.isRegistered(nsEPackage.eResource()));
				assertEquals(false/*selfReferential*/, !platformPluginURI.equals(nsEPackage.eResource().getURI()));
				assertEquals(false/*selfReferential*/, nsEPackage != platformPluginEObject);
				assertEquals(platformPluginEObject, platformResourceEObject);
	//			assertEquals(false/*selfReferential*/ ? "Attempt to load self-referential '" + nsURI + "' as model replaced by registered EPackage" : "", TestCaseLogger.INSTANCE.get());
				resourceSet = destroyResourceSet(resourceSet);
			}
			TestCaseLogger.INSTANCE.clear();
			{
				ResourceSet resourceSet = new ResourceSetImpl();
				getProjectMap().initializeResourceSet(resourceSet);
				packageDescriptor.configure(resourceSet, StandaloneProjectMap.LoadDynamicResourceStrategy.INSTANCE, null);
				EObject platformResourceEObject = resourceSet.getEObject(platformResourceEObjectURI, true);
				EObject platformPluginEObject = resourceSet.getEObject(platformPluginEObjectURI, true);
				EPackage nsEPackage = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
				assertEquals(false/*selfReferential*/, ClassUtil.isRegistered(nsEPackage.eResource()));
				assertEquals(false/*selfReferential*/, !platformResourceURI.equals(nsEPackage.eResource().getURI()));
				assertEquals(false/*selfReferential*/, nsEPackage != platformPluginEObject);
				assertEquals(platformPluginEObject, platformResourceEObject);
	//			assertEquals(false/*selfReferential*/ ? "Attempt to load self-referential '" + nsURI + "' as model replaced by registered EPackage" : "", TestCaseLogger.INSTANCE.get());
				resourceSet = destroyResourceSet(resourceSet);
			}
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}
}
