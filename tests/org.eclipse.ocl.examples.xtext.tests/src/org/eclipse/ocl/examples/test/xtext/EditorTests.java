/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
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
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Appender;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestCaseLogger;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.ui.model.BaseEditorCallback;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.PivotDiagnosticConverter;
import org.eclipse.ocl.xtext.base.utilities.PivotResourceValidator;
import org.eclipse.ocl.xtext.completeocl.ui.CompleteOCLUiModule;
import org.eclipse.ocl.xtext.completeocl.ui.internal.CompleteOCLActivator;
import org.eclipse.ocl.xtext.essentialocl.ui.EssentialOCLUiModule;
import org.eclipse.ocl.xtext.essentialocl.ui.internal.EssentialOCLActivator;
import org.eclipse.ocl.xtext.oclinecore.ui.OCLinEcoreUiModule;
import org.eclipse.ocl.xtext.oclinecore.ui.internal.OCLinEcoreActivator;
import org.eclipse.ocl.xtext.oclstdlib.ui.OCLstdlibUiModule;
import org.eclipse.ocl.xtext.oclstdlib.ui.internal.OCLstdlibActivator;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.CheckMode;

import com.google.inject.Injector;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
@SuppressWarnings("null")
public class EditorTests extends XtextTestCase
{
	protected FileEditorInput createEcoreFileEditorInput(String projectName, String fileName, String testDocument)throws IOException, CoreException {
		OCL ocl = OCL.newInstance(getProjectMap());
		String ecoreString = createEcoreString(ocl, fileName, testDocument, true);
		InputStream inputStream = new URIConverter.ReadableInputStream(ecoreString, "UTF-8");
		FileEditorInput fileEditorInput = createFileEditorInput(projectName, fileName, inputStream);
		ocl.dispose();
		return fileEditorInput;
	}

	protected FileEditorInput createFileEditorInput(String projectName, String testFile, InputStream inputStream) throws CoreException {
		IProject project = createProject(projectName);
		IFile file1 = project.getFile(testFile);
		file1.create(inputStream, true, null);
		return new FileEditorInput(file1);
	}

	protected IProject createProject(String projectName) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectName);
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);
		return project;
	}

	protected void doTearDown(XtextEditor editor) {
		TestUIUtil.flushEvents();
		editor.close(false);
		TestUIUtil.flushEvents();
	}

	public void doTestEditor(String editorId, String testFile, String testContent) throws Exception {
		XtextEditor editor = doStartUp(editorId, testFile, testContent);
		doTearDown(editor);
	}

	public XtextEditor doStartUp(String editorId, String testFile, String testContent)
			throws CoreException, PartInitException {
		TestUIUtil.closeIntro();
		InputStream inputStream = new URIConverter.ReadableInputStream(testContent, "UTF-8");
		FileEditorInput fileEditorInput = createFileEditorInput("test", testFile, inputStream);
		XtextEditor editor = doTestEditor(editorId, fileEditorInput);
		IXtextDocument document = editor.getDocument();
		String content = document.get();
		assertEquals(testContent, content);
		return editor;
	}

	protected XtextEditor doTestEditor(String editorId, FileEditorInput fileEditorInput) throws PartInitException, CoreException {
		Injector injector = null;
		if (editorId == CompleteOCLUiModule.EDITOR_ID) {
			injector = CompleteOCLActivator.getInstance().getInjector(CompleteOCLActivator.ORG_ECLIPSE_OCL_XTEXT_COMPLETEOCL_COMPLETEOCL);
		}
		else if (editorId == EssentialOCLUiModule.EDITOR_ID) {
			injector = EssentialOCLActivator.getInstance().getInjector(EssentialOCLActivator.ORG_ECLIPSE_OCL_XTEXT_ESSENTIALOCL_ESSENTIALOCL);
		}
		else if (editorId == OCLinEcoreUiModule.EDITOR_ID) {
			injector = OCLinEcoreActivator.getInstance().getInjector(OCLinEcoreActivator.ORG_ECLIPSE_OCL_XTEXT_OCLINECORE_OCLINECORE);
		}
		else if (editorId == OCLstdlibUiModule.EDITOR_ID) {
			injector = OCLstdlibActivator.getInstance().getInjector(OCLstdlibActivator.ORG_ECLIPSE_OCL_XTEXT_OCLSTDLIB_OCLSTDLIB);
		}
		if (injector != null) {
			injector.getInstance(BaseEditorCallback.class).setDontAskForNatureAgain();
		}
		IFile file = fileEditorInput.getFile();
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = activeWorkbenchWindow.getActivePage();
		XtextEditor editor = (XtextEditor) IDE.openEditor(page, fileEditorInput, editorId, true);
		TestUIUtil.wait(750);
		String languageName = editor.getLanguageName();
		assertEquals(editorId, languageName);
		file.refreshLocal(IResource.DEPTH_INFINITE, null);
		IMarker[] markers = file.findMarkers(null, true, IResource.DEPTH_INFINITE);
		if (markers.length > 0) {
			StringBuilder s = new StringBuilder();
			for (IMarker marker : markers) {
				s.append("\n  ");
				Object location = marker.getAttribute(IMarker.LOCATION);
				if (location != null) {
					s.append(location.toString());
					s.append(": ");
				}
				else {
					Object lineNumber = marker.getAttribute(IMarker.LINE_NUMBER);
					if (lineNumber != null) {
						s.append(lineNumber.toString());
						s.append(": ");
					}
				}
				s.append(marker.getAttribute(IMarker.MESSAGE));
			}
			fail("Markers" + s.toString());
		}
		return editor;
	}

	private String doTestEditor(String editorId, URI testFile) throws CoreException, PartInitException {
		IEditorInput input = new EMFURIEditorInput(testFile);
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = activeWorkbenchWindow.getActivePage();
		IEditorPart openEditor = IDE.openEditor(page, input, editorId, true);
		TestUIUtil.wait(750);
		XtextEditor editor = (XtextEditor) openEditor;
		String languageName = editor.getLanguageName();
		assertEquals(editorId, languageName);
		XtextDocument document = (XtextDocument) editor.getDocument();
		document.modify(new IUnitOfWork<Object, XtextResource>()
		{
			@Override
			public Object exec(@Nullable XtextResource resource) throws Exception {
				resource.setValidationDisabled(false);
				PivotResourceValidator resourceValidator = new PivotResourceValidator();
				resourceValidator.setDiagnostician(Diagnostician.INSTANCE);
				resourceValidator.setDiagnosticConverter(new PivotDiagnosticConverter());
				resourceValidator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
				return null;
			}
		});
		document.readOnly(new IUnitOfWork<Object, XtextResource>()
		{
			@Override
			public Object exec(@Nullable XtextResource resource) throws Exception {
				assertNoResourceErrorsOrWarnings("Loaded CS", resource);
				BaseCSResource csResource = (BaseCSResource)resource;
				CS2AS cs2as = csResource.findCS2AS();
				if (cs2as != null) {
					ASResource asResource = cs2as.getASResource();
					assertNoResourceErrorsOrWarnings("Loaded pivot", asResource);
				}
				return null;
			}
		});
		try {
			return document.get();
		}
		finally {
			doTearDown(editor);
		}
	}

	protected Set<EObject> indexPivotContent(XtextDocument document, final String prefix) {
		@SuppressWarnings("unused") String doc = document.get();
		final Set<EObject> pivotContent = new HashSet<EObject>();
		document.readOnly(new IUnitOfWork<Object, XtextResource>()
		{
			@Override
			public Object exec(@Nullable XtextResource resource) throws Exception {
				//				assertNoResourceErrors("Loaded CS", resource);
				CS2AS cs2as = ((BaseCSResource)resource).getCS2AS();
				if (cs2as != null) {
					Resource asResource = cs2as.getASResource();
					assertNoResourceErrors(prefix, asResource);
					for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
						EObject eObject = tit.next();
						//						System.out.println(PivotUtil.debugSimpleName(eObject));
						pivotContent.add(eObject);
					}
				}
				return null;
			}
		});
		return pivotContent;
	}

	@Override
	protected void setUp() throws Exception {
		TestUIUtil.suppressGitPrefixPopUp();
		super.setUp();
	}

	public void testEditor_OpenCompleteOCLEditor() throws Exception {
		doTestEditor(CompleteOCLUiModule.EDITOR_ID, "test.ocl",
			"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/'\n"+
					"package ecore\n"+
				"endpackage");
	}

	public void testEditor_OpenCompleteOCLEditor4Pivot_OCL() throws Exception {
		URI uri = URI.createPlatformPluginURI("org.eclipse.ocl.pivot/model/Pivot.ocl", true);
		String documentText = doTestEditor(CompleteOCLUiModule.EDITOR_ID, uri);
		assertTrue(documentText.contains("inv SourceIsCollection: ownedSource?.type.oclIsKindOf(CollectionType)"));
	}

	public void testEditor_OpenCompleteOCLEditor4Fruit_OCL() throws Exception {
		URI uri = getTestModelURI("models/uml/Fruit.ocl");
		String documentText = doTestEditor(CompleteOCLUiModule.EDITOR_ID, uri);
		assertTrue(documentText.contains("body: Color::red"));
	}

	public void testEditor_OpenEssentialOCLEditor() throws Exception {
		doTestEditor(EssentialOCLUiModule.EDITOR_ID, "test.essentialocl", "1 + 4");
	}

	public void testEditor_OpenOCLinEcoreEditor() throws Exception {
		doTestEditor(OCLinEcoreUiModule.EDITOR_ID, "test.oclinecore", "package test : test = 'test' { }");
	}

	public void testEditor_OpenOCLinEcoreEditor4Ecore() throws Exception {
		doTestEditor(OCLinEcoreUiModule.EDITOR_ID, "test.ecore", "package test : test = 'test' { }");
	}

	public void testEditor_OpenOCLinEcoreEditor_Bug460625() throws Exception {
		//		BaseOutlineWithEditorLinker.LOCATE.setState(true);;
		String testContent = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
				"\n" +
				"package test : tut = 'http://bug/382087'\n" +
				"{\n" +
				"	class Test\n" +
				"	{\n" +
				"		invariant Testing: self->select(self);\n" +
				"	}\n" +
				"}\n";
		XtextEditor editor = doStartUp(OCLinEcoreUiModule.EDITOR_ID, "Bug460625.ecore", testContent);
		int selectionOffset = testContent.indexOf("->");
		int selectionLength = "->".length();
		editor.selectAndReveal(selectionOffset, selectionLength);
		//		TestUtil.flushEvents();
		//		ISelection editorSelection = editor.getSelectionProvider().getSelection();
		//		TestUtil.flushEvents();
		//		IContentOutlinePage outlinePage = (IContentOutlinePage) editor.getAdapter(IContentOutlinePage.class);
		// FIXME This only works if debugger slows everything down. ? How to wait for Outline progress ?
		/*		BaseOutlineNode outlineNode = null;
		for (int i= 0; i < 1000; i++) {
			TestUtil.flushEvents();
			Thread.yield();
			ISelection outlineSelection = outlinePage.getSelection();
			outlineNode = (BaseOutlineNode) ((StructuredSelection)outlineSelection).getFirstElement();
			if (outlineNode != null) {
				break;
			}
		}
		assertTrue(outlineNode.isImplicit());
		ITextRegion significantTextRegion = outlineNode.getSignificantTextRegion();
		assertEquals(selectionOffset, significantTextRegion.getOffset());
		assertEquals(selectionLength, significantTextRegion.getLength()); */
		doTearDown(editor);
	}

	public void testEditor_OpenOCLinEcoreEditor4Pivot() throws Exception {
		URI uri = URI.createPlatformPluginURI("org.eclipse.ocl.pivot/model/Pivot.ecore", true);
		String documentText = doTestEditor(OCLinEcoreUiModule.EDITOR_ID, uri);
		assertTrue(documentText.contains("primitive datatype _'Boolean'"));
	}

	public void testEditor_OpenOCLStdLibEditor() throws Exception {
		doTestEditor(OCLstdlibUiModule.EDITOR_ID, "test.oclstdlib",
			"import '" + LibraryConstants.STDLIB_URI + "';\n" +
					"library test : xxx = '" + LibraryConstants.STDLIB_URI + "' { }");
	}

	public void testEditor_OpenOCLStdLibEditor4OCL_OCLstdlib() throws Exception {
		URI uri = URI.createPlatformPluginURI("org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib", true);
		String documentText = doTestEditor(OCLstdlibUiModule.EDITOR_ID, uri);
		assertTrue(documentText.contains("type UniqueCollection(T) : CollectionType conformsTo Collection(T) {"));
	}

	public void testEditor_OpenOCLinEcoreEditor4Ecore_Ecore() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			URI uri = URI.createPlatformPluginURI("org.eclipse.emf.ecore/model/Ecore.ecore", true);
			String documentText = doTestEditor(OCLinEcoreUiModule.EDITOR_ID, uri);
			assertTrue(documentText.contains("abstract class ETypedElement extends ENamedElement"));		// No ecore:: qualification
			assertEquals("Conflicting access to '" + EcorePackage.eNS_URI + "' already accessed as '" + uri.toString() + "'", TestCaseLogger.INSTANCE.get());
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	// FIXME Disabled for BUG 425505
	public void zztestEditor_OpenOCLinEcoreEditor4Pivot_Ecore() throws Exception {
		URI uri = URI.createPlatformPluginURI(PivotConstantsInternal.PIVOT_ECORE, true);
		String documentText = doTestEditor(OCLinEcoreUiModule.EDITOR_ID, uri);
		assertTrue(documentText.contains("abstract class Visitable : 'org.eclipse.ocl.pivot.util.Visitable' { interface };"));
		assertTrue(documentText.contains("reference Type::ownedAttribute"));							// Tests Bug 363141 EAnnotation reference
	}

	// FIXME Bug 399762 fails on Hudson
	public void zztestEditor_OpenOCLinEcoreEditor4Test_Ecore_Update() throws Exception {
		String testDocument =
				"package tutorial : tuttut = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Library\n" +
						"	{\n" +
						"		property books#library : Book[*] { composes };\n" +
						"	}\n" +
						"	class Book\n" +
						"	{\n" +
						"		property library#books : Library[?];\n" +
						"	}\n" +
						"}\n";
		FileEditorInput fileEditorInput = createEcoreFileEditorInput("test", "RefreshTest.ecore", testDocument);
		XtextEditor editor = doTestEditor(OCLinEcoreUiModule.EDITOR_ID, fileEditorInput);
		XtextDocument document = (XtextDocument) editor.getDocument();
		Set<EObject> oldPivotContent = indexPivotContent(document, "Loaded pivot");
		oldPivotContent.toString();
		IProject iProject = createProject("test");
		IFile iFile = iProject.getFile("RefreshTest.ecore");
		Reader reader = new InputStreamReader(iFile.getContents());
		char[] cbuf = new char[4096];
		StringBuilder s = new StringBuilder();
		for (int i; (i = reader.read(cbuf)) > 0; ) {
			s.append(cbuf, 0, i);
		}
		String updatedDocument = s.toString().replace("tuttut", "tut");
		iFile.setContents(new URIConverter.ReadableInputStream(updatedDocument, "UTF-8"), true, false, null);
		TestUIUtil.flushEvents();
		@SuppressWarnings("unused") String newDoc = document.get();
		Set<EObject> newPivotContent = indexPivotContent(document, "Loaded pivot");
		assertEquals(oldPivotContent.size(), newPivotContent.size());
		TestUIUtil.flushEvents();
		assertEquals(oldPivotContent, newPivotContent);
		TestUIUtil.flushEvents();
		doTearDown(editor);
	}
}
