/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.consumers.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class ConsumerLoadTests extends XtextTestCase
{
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
		CS2AS cs2as = xtextResource.findCS2AS();
		if (cs2as != null) {
			ASResource asResource = cs2as.getASResource();
			assertNoValidationErrors("Loaded pivot", asResource);
		}
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

	public void testLoad_Bug457203_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = OCL.newInstance(getProjectMap());
		String bug457203 = 
				"import marte: _'http://www.eclipse.org/papyrus/MARTE/1'\n" + 
				"import sysml: _'http://www.eclipse.org/papyrus/0.7.0/SysML'\n" + 
				"\n";
		createOCLinEcoreFile("Bug457203.ocl", bug457203);
		Resource asResource = doLoad_Concrete(ocl, "Bug457203", "ocl");
		assertNoResourceErrors("Save", asResource);
		ocl.dispose();
	}
}
