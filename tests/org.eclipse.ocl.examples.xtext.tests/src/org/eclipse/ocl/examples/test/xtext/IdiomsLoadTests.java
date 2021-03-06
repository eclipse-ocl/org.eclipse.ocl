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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsStandaloneSetup;
import org.eclipse.ocl.examples.xtext.serializer.DeclarativeFormatter;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.BaseStandaloneSetup;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class IdiomsLoadTests extends XtextTestCase
{
	protected static final @NonNull String @NonNull [] NO_MESSAGES = new @NonNull String[] {};

	public @Nullable String readFile(@NonNull InputStream inputStream) throws IOException {
		Reader reader = new InputStreamReader(inputStream);
		StringBuilder s = new StringBuilder();
		char[] buf = new char[16384];
	//	try {
			for (int len; (len = reader.read(buf)) >= 0; ) {
				s.append(buf, 0, len);
			}
	//	} catch (IOException e) {
	//		LOG.error("Failed to read '" + fileName + "'", e);
	//		return null;
	//	}
	//	try {
			reader.close();
	//	} catch (IOException e) {
	//		LOG.error("Failed to close '" + fileName + "'", e);
	//		return null;
	//	}
		return s.toString();
	}

	public @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), "LoadTests", getName(), OCL.NO_PROJECTS, null);
	}

	public @NonNull TestOCL createOCLWithProjectMap() {
		return new TestOCL(getTestFileSystem(), "LoadTests", getName(), getProjectMap(), null);
	}

	public Resource doLoad_Idioms(@NonNull OCL ocl, URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = ocl.getResourceSet();
		getProjectMap().initializeResourceSet(resourceSet);
		InputStream oldStream = resourceSet.getURIConverter().createInputStream(inputURI, null);
		String oldText = readFile(oldStream);
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		URI outputURI = getTestFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);
		Resource xtextResource = null;
		try {
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			xtextResource = resourceSet.getResource(inputURI, true);
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


			InputStream newStream = resourceSet.getURIConverter().createInputStream(xtextResource.getURI(), null);
			String newText = readFile(newStream);
			assertEquals(oldText, newText);
			//
		//	CS2AS cs2as = xtextResource.findCS2AS();
		//	if (cs2as != null) {
		//		ASResource asResource = cs2as.getASResource();
		//		assertNoValidationErrors("Loaded pivot", asResource);
		//	}
		}
		finally {
		//	xtextResource.dispose();
		}
		Resource xmiResource = resourceSet.createResource(outputURI);
		xmiResource.getContents().addAll(xtextResource.getContents());
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		//		xmiResource.save(null);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		//		assertNoResourceErrors("Save failed", xmiResource);
		return xmiResource;
	}

	public void doReformat_Idioms(@NonNull OCL ocl, URI inputURI) throws IOException {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = ocl.getResourceSet();
		getProjectMap().initializeResourceSet(resourceSet);
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		@SuppressWarnings("unused")
		URI outputURI = getTestFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);
		Resource xtextResource = null;
		try {
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
			xtextResource = resourceSet.getResource(inputURI, true);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
			assertNoResourceErrors("Load failed", xtextResource);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			xtextResource.setURI(output2URI);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		//	DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());

			EObject rootEObject = xtextResource.getContents().get(0);
			ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
			assert rootNode != null;
			int rootOffset = rootNode.getOffset();
			int rootLength = rootNode.getLength();
			@SuppressWarnings("unused")
			int totalOffset = rootNode.getTotalOffset();
			@SuppressWarnings("unused")
			int totalLength = rootNode.getTotalLength();
			String text = rootNode.getText();
		//	xtextResource.save(XMIUtil.createSaveOptions());
			DeclarativeFormatter instance = new IdiomsStandaloneSetup().createInjector().getInstance(DeclarativeFormatter.class);
			rootOffset += rootLength/4;
			rootLength /= 2;
			IFormattedRegion region = instance.format(rootNode, rootOffset, rootLength);
			String formattedText = region.getFormattedText();
			if (rootOffset == 0) {
				assertEquals(text, formattedText);
			}
			else if (!text.contains(formattedText)) {
				assertEquals(text, formattedText);
			}
		//	debugTimestamp.log("Serialization save done");
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		//	assertNoResourceErrors("Save failed", xtextResource);
			//
		//	CS2AS cs2as = xtextResource.findCS2AS();
		//	if (cs2as != null) {
		//		ASResource asResource = cs2as.getASResource();
		//		assertNoValidationErrors("Loaded pivot", asResource);
		//	}
		}
		finally {
		//	xtextResource.dispose();
		}
	//	Resource xmiResource = resourceSet.createResource(outputURI);
	//	xmiResource.getContents().addAll(xtextResource.getContents());
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		//		xmiResource.save(null);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		//		assertNoResourceErrors("Save failed", xmiResource);
	//	return xmiResource;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		IdiomsStandaloneSetup.doSetup();
//		configurePlatformResources();
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testIdiomsLoad_Base_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Base.idioms", BaseStandaloneSetup.class.getResourceAsStream("Base.idioms"));
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_CompleteOCL_idioms() throws IOException, InterruptedException {
		if (CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire()) {				// FIXME BUG 569138
			System.err.println(getName() + " has been disabled -see Bug 569138");
			return;
		}
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("CompleteOCL.idioms", CompleteOCLStandaloneSetup.class.getResourceAsStream("CompleteOCL.idioms"));
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	/**
	 * A temporary test to help debugging a troublesome construct.
	 */
	public void testIdiomsLoad_DebugTest_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/DebugTest.idioms", true);
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	/**
	 * A temporary test to help debugging a troublesome construct.
	 */
	public void testIdiomsLoad_Reformat_DebugTest_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/DebugTest.idioms", true);
		doReformat_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_EssentialOCL_idioms() throws IOException, InterruptedException {
		if (CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire()) {				// FIXME BUG 569138
			System.err.println(getName() + " has been disabled -see Bug 569138");
			return;
		}
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("EssentialOCL.idioms", EssentialOCLStandaloneSetup.class.getResourceAsStream("EssentialOCL.idioms"));
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_Idioms_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Idioms.idioms", IdiomsStandaloneSetup.class.getResourceAsStream("Idioms.idioms"));
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_Reformat_Idioms_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Idioms.idioms", IdiomsStandaloneSetup.class.getResourceAsStream("Idioms.idioms"));
		doReformat_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_OCLinEcore_idioms() throws IOException, InterruptedException {
		if (CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire()) {				// FIXME BUG 569138
			System.err.println(getName() + " has been disabled -see Bug 569138");
			return;
		}
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("OCLinEcore.idioms", OCLinEcoreStandaloneSetup.class.getResourceAsStream("OCLinEcore.idioms"));
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_OCLstdlib_idioms() throws IOException, InterruptedException {
		if (CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire()) {				// FIXME BUG 569138
			System.err.println(getName() + " has been disabled -see Bug 569138");
			return;
		}
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("OCLstdlib.idioms", OCLstdlibStandaloneSetup.class.getResourceAsStream("OCLstdlib.idioms"));
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	/**
	 * A test that exercises some constructs not adequately covered by regular editpr idioms files.
	 */
	public void testIdiomsLoad_Test_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/Test.idioms", true);
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_Xtext_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/Xtext.idioms", true);
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_Reformat_Xtext_idioms() throws IOException, InterruptedException {
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/Xtext.idioms", true);
		doReformat_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}
}
