/*******************************************************************************
 * Copyright (c) 2010, 2023 Willink Transformations and others.
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
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.ClasspathURIHandler;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.BaseStandaloneSetup;
import org.eclipse.ocl.xtext.base.serializer.DeclarativeFormatter;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.idioms.IdiomsStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class IdiomsLoadTests extends XtextTestCase
{
	protected static final @NonNull String @NonNull [] NO_MESSAGES = new @NonNull String[] {};

	public @NonNull String readFile(@NonNull InputStream inputStream) throws IOException {
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
		ResourceSet resourceSet = doReformatInit(ocl);
		String oldText = doReformatReference(resourceSet, inputURI);
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

	protected void doReformat(@NonNull OCL ocl, @NonNull DeclarativeFormatter declarativeFormatter, @NonNull URI inputURI, @NonNull URI referenceURI) throws IOException {
		ResourceSet resourceSet = doReformatInit(ocl);
		String referenceText = doReformatReference(resourceSet, referenceURI);
		EObject rootEObject = doReformatLoad(resourceSet, inputURI);
		ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
		assert rootNode != null;
		int rootOffset = rootNode.getOffset();
		int rootLength = rootNode.getLength();
		@SuppressWarnings("unused")
		int totalOffset = rootNode.getTotalOffset();
		@SuppressWarnings("unused")
		int totalLength = rootNode.getTotalLength();
	//	xtextResource.save(XMIUtil.createSaveOptions());
		int selectOffset = rootOffset + rootLength/4;
		int selectLength = rootLength / 2;
		doReformatText(declarativeFormatter, rootNode, selectOffset, selectLength, referenceText);
	}

	protected @NonNull ResourceSet doReformatInit(@NonNull OCL ocl) {
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		ResourceSet resourceSet = ocl.getResourceSet();
		getProjectMap().initializeResourceSet(resourceSet);
		return resourceSet;
	}

	private @NonNull EObject doReformatLoad(@NonNull ResourceSet resourceSet, @NonNull URI inputURI) {
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		@SuppressWarnings("unused")
		URI outputURI = getTestFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);
		//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
		Resource xtextResource = resourceSet.getResource(inputURI, true);
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
	//	ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
	//	assert rootNode != null;
		return rootEObject;
	}

	protected @NonNull String doReformatReference(@NonNull ResourceSet resourceSet, @NonNull URI referenceURI) throws IOException {
		InputStream referenceStream = resourceSet.getURIConverter().createInputStream(referenceURI, null);
		String referenceText = readFile(referenceStream);
		return referenceText;
	}

	protected void doReformatText(@NonNull DeclarativeFormatter declarativeFormatter, @NonNull ICompositeNode rootNode, int selectOffset, int selectLength, String referenceText) {
		String text = rootNode.getText();
		int selectEnd = selectOffset+selectLength;
		String unformattedText = text.substring(selectOffset, selectEnd);
		IFormattedRegion region = declarativeFormatter.format(rootNode, selectOffset, selectLength);
		String formattedText = text.substring(0, selectOffset) + region.getFormattedText() + text.substring(selectEnd);
		assertEquals(referenceText, formattedText);
//	xtextResource.save(XMIUtil.createSaveOptions());		-- skip save since it does a serialize
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

	protected @NonNull String replace(@NonNull LazyLinkingResource xtextResource, @NonNull ICompositeNode rootNode, @NonNull String oldString, @NonNull String newString) {
		String xtextContent = rootNode.getText();
		int index = xtextContent.indexOf(oldString);
		assert index >= 0;
		xtextResource.update(index, oldString.length(), newString);
		return rootNode.getText();
	}

	@Override
	protected void setUp() throws Exception {
		TestUtil.doIdiomsSetup();
		//	TestUtil.doCompleteOCLSetup();
		//	TestUtil.doOCLstdlibSetup();
			TestUtil.doEssentialOCLSetup();
			TestUtil.doXtextSetup();
		super.setUp();
//		configurePlatformResources();
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
//		DeclarativeFormatter.FORMATTER_FRAGMENTS.setState(true);
//		DeclarativeSerializer.SERIALIZER_FRAGMENTS.setState(true);
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
		DeclarativeFormatter declarativeFormatter = IdiomsStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/DebugTest.idioms", true);
		doReformat(ocl, declarativeFormatter, idiomsURI, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_EssentialOCL_idioms() throws IOException, InterruptedException {
		if (CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire()) {				// FIXME BUG 569138
			System.err.println(getName() + " has been disabled -see Bug 569138");
			return;
		}
		TestOCL ocl = createOCL();
		ClasspathURIHandler.init(ocl.getResourceSet());
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
		DeclarativeFormatter declarativeFormatter = IdiomsStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Idioms.idioms", IdiomsStandaloneSetup.class.getResourceAsStream("Idioms.idioms"));
		ResourceSet resourceSet = doReformatInit(ocl);
		String referenceText = doReformatReference(resourceSet, idiomsURI);
		EObject rootEObject = doReformatLoad(resourceSet, idiomsURI);
		ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
		assert rootNode != null;

		// total selection
		doReformatText(declarativeFormatter, rootNode, 0, referenceText.length(), referenceText);
		// almost total selection
		for (int i = 0; i <= 5; i++) {
		//	System.out.println(i);
			doReformatText(declarativeFormatter, rootNode, i, referenceText.length()-i, referenceText);
			doReformatText(declarativeFormatter, rootNode, 0, referenceText.length()-i, referenceText);
		}
		int endHeaderOffset = referenceText.indexOf("*******/") - 8;
		assert endHeaderOffset >= 0;
		// nothing in middle of long /**/ comment
		doReformatText(declarativeFormatter, rootNode, endHeaderOffset, 0, referenceText);
		// nothing across end of long /**/ comment
		for (int i = endHeaderOffset-5; i < endHeaderOffset+5; i++) {
		//	System.out.println(i);
			doReformatText(declarativeFormatter, rootNode, endHeaderOffset, 0, referenceText);
		}
		// selection across end of long /**/ comment
		for (int i = endHeaderOffset-15; i < endHeaderOffset+5; i++) {
		//	System.out.println(i);
			doReformatText(declarativeFormatter, rootNode, endHeaderOffset, 10, referenceText);
		}

		String key = "//mixin idiom COMMENTS at final do PRE_COMMENT value POST_COMMENT;";
		int keyOffset = referenceText.indexOf(key);
		assert (100 < keyOffset) && ((keyOffset + 100) < referenceText.length());
		int keyLength = key.length();
		doReformatText(declarativeFormatter, rootNode, keyOffset + 32, 100, referenceText);
		// selection across end of long // comment
		for (int i = -5; i < keyLength + 5; i++) {
		//	System.out.println(i);
			doReformatText(declarativeFormatter, rootNode, keyOffset + i, 100, referenceText);
		}

		// selection before/at/after deficient indentation
		String indentedKey = "\tat \"{\"";
		String deficientIndentedKey = "at \"{\"";
		String replacedText = replace((LazyLinkingResource) rootEObject.eResource(), rootNode, indentedKey, deficientIndentedKey);
		int indentedOffset = referenceText.indexOf(indentedKey);
		int indentedWindow = 15;
		assert rootNode == NodeModelUtils.getNode(rootEObject);
		int i1 = -indentedWindow-2;
	//	System.out.println(i1 + " " + (indentedOffset+i1));
		doReformatText(declarativeFormatter, rootNode, indentedOffset+i1, indentedWindow, replacedText);
	//	String indentedKey2 = "ES {\n\tat \"{\"";
	//	String reformattedKey2 = "\t at \"{\"";
		int i2 = -indentedWindow-1;
	//	System.out.println(i2 + " " + (indentedOffset+i2));
		doReformatText(declarativeFormatter, rootNode, indentedOffset+i2, indentedWindow, replacedText);//.replace(indentedKey2, reformattedKey2));
		for (int i = -indentedWindow-2; i < -indentedWindow; i++) {
	//		System.out.println(i + " " + (indentedOffset+i));
			doReformatText(declarativeFormatter, rootNode, indentedOffset+i, indentedWindow, replacedText);
		}
		for (int i = -indentedWindow; i < 0; i++) {
		//	System.out.println(i + " " + (indentedOffset+i));
			doReformatText(declarativeFormatter, rootNode, indentedOffset+i, indentedWindow, referenceText);
		}
		for (int i = 0; i < +indentedWindow; i++) {
		//	System.out.println(i + " " + (indentedOffset+i));
			doReformatText(declarativeFormatter, rootNode, indentedOffset+i, indentedWindow, replacedText);
		}
		replace((LazyLinkingResource) rootEObject.eResource(), rootNode, deficientIndentedKey, indentedKey);

		// selection before/at/after excess indentation
		indentedKey = "at \"{\"";
		String excessIndentedKey = "\tat \"{\"";
		replacedText = replace((LazyLinkingResource) rootEObject.eResource(), rootNode, indentedKey, excessIndentedKey);
		indentedOffset = referenceText.indexOf(indentedKey);
		indentedWindow = 15;
		assert rootNode == NodeModelUtils.getNode(rootEObject);
		for (int i = -indentedWindow-2; i < -indentedWindow; i++) {
		//	System.out.println(i + " " + (indentedOffset+i));
			doReformatText(declarativeFormatter, rootNode, indentedOffset+i, indentedWindow, replacedText);
		}
		for (int i = -indentedWindow; i < 0; i++) {
		//	System.out.println(i + " " + (indentedOffset+i));
			doReformatText(declarativeFormatter, rootNode, indentedOffset+i, indentedWindow, referenceText);
		}
		for (int i = 0; i < +indentedWindow; i++) {
		//	System.out.println(i + " " + (indentedOffset+i));
			doReformatText(declarativeFormatter, rootNode, indentedOffset+i, indentedWindow, replacedText);
		}
		replace((LazyLinkingResource) rootEObject.eResource(), rootNode, excessIndentedKey, indentedKey);



		ocl.dispose();
	}


	public void zztestIdiomsLoad_Reformat_Idioms_idioms1() throws IOException, InterruptedException {
		DeclarativeFormatter declarativeFormatter = IdiomsStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Idioms.idioms", IdiomsStandaloneSetup.class.getResourceAsStream("Idioms.idioms"));
		ResourceSet resourceSet = doReformatInit(ocl);
		String referenceText = doReformatReference(resourceSet, idiomsURI);
		EObject rootEObject = doReformatLoad(resourceSet, idiomsURI);
		ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
		assert rootNode != null;

	/*	// selection before/at/after deficient indentation
		String indentedKey = "ES {\n\tat \"{\"";
		String tweakedKey = "ES {\n \t at \"{\"";
		String reformattedKey = "ES {\n\t at \"{\"";
		String replacedText = replace((LazyLinkingResource) rootEObject.eResource(), rootNode, indentedKey, tweakedKey);
		int indentedOffset = referenceText.indexOf(indentedKey);
		doReformatText(declarativeFormatter, rootNode, indentedOffset, 6, referenceText.replace(indentedKey, reformattedKey)); */

		String indentedKey = "\tat \"{\"";
		String deficientIndentedKey = "at \"{\"";
		String replacedText = replace((LazyLinkingResource) rootEObject.eResource(), rootNode, indentedKey, deficientIndentedKey);
		int indentedOffset = referenceText.indexOf(indentedKey);
		int indentedWindow = 15;
		assert rootNode == NodeModelUtils.getNode(rootEObject);
		int i1 = -indentedWindow-2;
	//	System.out.println(i1 + " " + (indentedOffset+i1));
	//	doReformatText(declarativeFormatter, rootNode, indentedOffset+i1, indentedWindow, replacedText);
	//	String indentedKey2 = "ES {\n\tat \"{\"";
	//	String reformattedKey2 = "\t at \"{\"";
		int i2 = -indentedWindow-1;
		System.out.println(i2 + " " + (indentedOffset+i2));
		doReformatText(declarativeFormatter, rootNode, indentedOffset+i2, indentedWindow, replacedText);//.replace(indentedKey2, reformattedKey2));

		ocl.dispose();
	}

	public void zztestIdiomsLoad_Reformat_Idioms_idioms2() throws IOException, InterruptedException {
		DeclarativeFormatter declarativeFormatter = IdiomsStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Idioms.idioms", IdiomsStandaloneSetup.class.getResourceAsStream("Idioms.idioms"));
		ResourceSet resourceSet = doReformatInit(ocl);
		String referenceText = doReformatReference(resourceSet, idiomsURI);
		EObject rootEObject = doReformatLoad(resourceSet, idiomsURI);
		ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
		assert rootNode != null;

	//	int endHeaderOffset = referenceText.indexOf("*******/") - 8;
	//	assert endHeaderOffset >= 0;
		// nothing in middle of long /**/ comment
	//	doReformatText(declarativeFormatter, rootNode, endHeaderOffset, 1, referenceText);
		String key = "//mixin idiom COMMENTS at final do PRE_COMMENT value POST_COMMENT;";
		int keyOffset = referenceText.indexOf(key);
		assert (100 < keyOffset) && ((keyOffset + 100) < referenceText.length());
		int keyLength = key.length();
		doReformatText(declarativeFormatter, rootNode, keyOffset + 32, 100, referenceText);

		ocl.dispose();
	}

	public void zztestIdiomsLoad_Reformat_Idioms_idioms3() throws IOException, InterruptedException {
		DeclarativeFormatter declarativeFormatter = IdiomsStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI idiomsURI = getTestFileURI("Idioms.idioms", IdiomsStandaloneSetup.class.getResourceAsStream("Idioms.idioms"));
		ResourceSet resourceSet = doReformatInit(ocl);
		String referenceText = doReformatReference(resourceSet, idiomsURI);
		EObject rootEObject = doReformatLoad(resourceSet, idiomsURI);
		ICompositeNode rootNode = NodeModelUtils.getNode(rootEObject);
		assert rootNode != null;

//			doReformatText(declarativeFormatter, rootNode, 0, referenceText.length()-1, referenceText);
		String key = "//mixin idiom COMMENTS at final do PRE_COMMENT value POST_COMMENT;";
		int keyOffset = referenceText.indexOf(key);
		assert (100 < keyOffset) && ((keyOffset + 100) < referenceText.length());
		int i = -2;
			System.out.println(i);
			doReformatText(declarativeFormatter, rootNode, keyOffset + i, 100, referenceText);

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
	//	SerializationBuilder.SERIALIZATION.setState(true);
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/Xtext.idioms", true);
		doLoad_Idioms(ocl, idiomsURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_Reformat_Tutorial_oclinecore() throws IOException, InterruptedException {
		DeclarativeFormatter declarativeFormatter = OCLinEcoreStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI inputURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/documentation/Tutorial.oclinecore", true);
		URI referenceURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/documentation/Tutorial.reformatted.oclinecore", true);
		doReformat(ocl, declarativeFormatter, inputURI, referenceURI);
		ocl.dispose();
	}

	public void testIdiomsLoad_Reformat_Xtext_idioms() throws IOException, InterruptedException {
		DeclarativeFormatter declarativeFormatter = IdiomsStandaloneSetup.getInjector().getInstance(DeclarativeFormatter.class);
		TestOCL ocl = createOCL();
		URI idiomsURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/idioms/Xtext.idioms", true);
		doReformat(ocl, declarativeFormatter, idiomsURI, idiomsURI);
		ocl.dispose();
	}
}
