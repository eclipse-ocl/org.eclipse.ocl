/*******************************************************************************
 * Copyright (c) 2013, 2016 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Obeo - initial API and implementation 
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ocl.examples.test.xtext.models.ParserModels;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.ocl.xtext.base.ui.wizards.AbstractFileNewWizardPage;
import org.eclipse.ocl.xtext.completeocl.ui.messages.CompleteOCLUIMessages;
import org.eclipse.ocl.xtext.completeocl.ui.wizards.CompleteOCLFileDialog;
import org.eclipse.ocl.xtext.completeocl.ui.wizards.CompleteOCLFileNewWizard;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLPlugin;
import org.eclipse.ocl.xtext.oclinecore.ui.wizards.EcoreWithOCLFileNewWizard;
import org.eclipse.ocl.xtext.oclinecore.ui.wizards.OCLinEcoreFileNewWizard;
import org.eclipse.ocl.xtext.oclinecore.utilities.OCLinEcorePlugin;
import org.eclipse.ocl.xtext.oclstdlib.ui.wizards.OCLstdlibFileNewWizard;
import org.eclipse.ocl.xtext.oclstdlib.utilities.OCLstdlibPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.junit.Test;

/**
 * Tests that exercise the new complete OCL creation wizard page.
 */
public class FileNewWizardTest extends TestCase
{
	private static final String PAGE_NAME = BaseUIMessages.NewWizardPage_pageName;

	private static final String TEST_ECORE_NAME = "Test.ecore";
	private static final String TEST_PROJECT_NAME = "test-project";
	private static final String TEST_ECORE_PATH = "/" + TEST_PROJECT_NAME + "/" + TEST_ECORE_NAME;

	private static final String EXPECTED_OCL_NAME = "Test.ocl";
	private static final String EXPECTED_OCLINECORE_NAME = "Test.oclinecore";
	private static final String EXPECTED_OCLSTDLIB_NAME = "Test.oclstdlib";
	private static final String EXPECTED_PACKAGE_NAME = "test_package";
	private static final String EXPECTED_CLASS_NAME = "TestClass";
	private static final String EXPECTED_FEATURE_NAME = "testFeature";

	private IProject project = null;

	private IFile modelFile = null;

	public static String getExpectedContents() {
		StringBuilder s = new StringBuilder();
		s.append("import '" + TEST_ECORE_NAME + "'\n\n");
		s.append("package " + EXPECTED_PACKAGE_NAME + "\n\n");
		s.append("context " + EXPECTED_CLASS_NAME + "\n");
		s.append("--\n");
		s.append("-- example invariant with a custom error message to verify that\n");
		s.append("-- the '" + EXPECTED_FEATURE_NAME + "' property of all '" + EXPECTED_PACKAGE_NAME + "::" + EXPECTED_CLASS_NAME + "' instances is non-null\n");
		s.append("--\n");
		s.append("inv NonNull_" + EXPECTED_FEATURE_NAME + "('The \\'" + EXPECTED_FEATURE_NAME + "\\' property of \"' + self.toString() + '\" is null'):\n");
		s.append("\t" + EXPECTED_FEATURE_NAME + " <> null\n\n");
		s.append("endpackage\n");
		return s.toString();
	}

	public FileNewWizardTest(String name) {
		super(name);
	}

	/**
	 * Create the wizard dialog, open it and press Finish.
	 */
	protected int createAndFinishWizardDialog(@NonNull IWorkbenchWizard wizard) {
		WizardDialog dialog = new WizardDialog(new Shell(), wizard)
		{
			@Override
			public int open() {
				final Thread thread = new Thread("Press Finish")
				{
					@Override
					public void run() {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {}
						getShell().getDisplay().asyncExec(new Runnable()
						{
							@Override
							public void run() {
								finishPressed();
							}
						});
					}			
				};
				thread.start();
				return super.open();
			}
		};
		return dialog.open();
	}

	protected XtextEditor getActiveEditor() {
		IWorkbenchPage currentPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		return (XtextEditor)currentPage.getActiveEditor();
	}

	protected @NonNull String readNewFile(String fileName) throws CoreException, IOException {
		IFile oclFile = project.getFile(fileName);
		assertTrue(oclFile.exists());
		InputStream inputStream = oclFile.getContents();
		Reader reader = new InputStreamReader(inputStream);
		StringBuilder s = new StringBuilder();
		char[] cbuf = new char[4096];
		for (int len = 0; (len = reader.read(cbuf)) > 0; ) {
			s.append(cbuf, 0, len);
		}
		reader.close();
		return s.toString();
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(ResourcesPlugin.PI_RESOURCES);
		prefs.putBoolean(ResourcesPlugin.PREF_LIGHTWEIGHT_AUTO_REFRESH, true);

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IWorkspaceRoot root = workspace.getRoot();
		IProjectDescription description = workspace.newProjectDescription(TEST_PROJECT_NAME);
		project = root.getProject(description.getName());
		NullProgressMonitor nullMonitor = new NullProgressMonitor();
		if (project.exists()) {
			project.delete(true, true, nullMonitor);
		}
		project.create(description, nullMonitor);
		assertTrue(project.exists());
		project.open(nullMonitor);
		URL url = ParserModels.class.getResource(TEST_ECORE_NAME);
		assertNotNull(url);
		project.getFile(TEST_ECORE_NAME).create(url.openStream(), true,nullMonitor);
		modelFile = project.getFile(TEST_ECORE_NAME);
		assertTrue(modelFile.exists());
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (project.exists()) {
			project.delete(true, true, new NullProgressMonitor());
		}
	}

	@Test
	public void test_CompleteOCLFile_Dialog() {
		CompleteOCLFileNewWizard wizard = new CompleteOCLFileNewWizard();
		AbstractFileNewWizardPage wizardPage = wizard.createNewWizardPage(modelFile);
		CompleteOCLFileDialog dialog = new CompleteOCLFileDialog(wizard, wizardPage, modelFile);
		dialog.createDialogArea(new Shell());
		assertEquals("ocl", wizard.getNewFileExtension());
		assertEquals(CompleteOCLUIMessages.NewWizardPage_fileNameLabel, wizard.getNewFileLabel());
		assertEquals("/" + TEST_PROJECT_NAME + "/" + EXPECTED_OCL_NAME, dialog.getNewFilePath().toString());
		List<URI> uris = new ArrayList<URI>();
		uris.add(URI.createPlatformResourceURI(TEST_ECORE_PATH, true));
		assertEquals(uris, dialog.getURIs());
		wizard.dispose();
	}

	@Test
	public void test_CompleteOCLFile_NewWizardPage() {
		CompleteOCLFileNewWizard wizard = new CompleteOCLFileNewWizard();
		AbstractFileNewWizardPage wizardPage = wizard.createNewWizardPage(modelFile);
		assertEquals(PAGE_NAME, wizardPage.getName());
		assertEquals(CompleteOCLUIMessages.NewWizardPage_pageSummary, wizardPage.getTitle());
		assertEquals(CompleteOCLUIMessages.NewWizardPage_pageDescription, wizardPage.getDescription());
		assertNull(wizardPage.getErrorMessage());
		wizard.dispose();
	}

/*	@Test
	public void test_CompleteOCLFile_NewWizardPage_FileContent() {
		CompleteOCLFileNewWizard wizard = new CompleteOCLFileNewWizard();
		AbstractFileNewWizardPage wizardPage = wizard.createNewWizardPage(modelFile);
		AbstractFileDialog dialog = wizardPage.initDialog(modelFile);
//		dialog.fillContentsFromWorkspacePath(modelFile.getFullPath().toString());
		String expectedContents = getExpectedContents();
		String actualContents = wizard.getInitialContentsAsString(modelFile, dialog);
		assertEquals(expectedContents, actualContents);
	} */

	@Test
	public void test_CompleteOCL_NewFileCreation() throws Exception {
		IWorkbenchWizard wizard = new CompleteOCLFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(modelFile));
		createAndFinishWizardDialog(wizard);
		String actualContents = readNewFile(EXPECTED_OCL_NAME);
		StringBuilder s = new StringBuilder();
		s.append("import '" + TEST_ECORE_NAME + "'\n\n");
		s.append("package " + EXPECTED_PACKAGE_NAME + "\n\n");
		s.append("context " + EXPECTED_CLASS_NAME + "\n");
		s.append("--\n");
		s.append("-- example invariant with a custom error message to verify that\n");
		s.append("-- the '" + EXPECTED_FEATURE_NAME + "' property of all '" + EXPECTED_PACKAGE_NAME + "::" + EXPECTED_CLASS_NAME + "' instances is non-null\n");
		s.append("--\n");
		s.append("inv NonNull_" + EXPECTED_FEATURE_NAME + "('The \\'" + EXPECTED_FEATURE_NAME + "\\' property of \"' + self.toString() + '\" is null'):\n");
		s.append("\t" + EXPECTED_FEATURE_NAME + " <> null\n\n");
		s.append("endpackage\n");
		String expectedContents = s.toString();
		assertEquals(expectedContents, actualContents);
		XtextEditor activeEditor = getActiveEditor();
		assertEquals(CompleteOCLPlugin.LANGUAGE_ID, activeEditor.getLanguageName());
		activeEditor.close(false);
	}

	@Test
	public void test_OCLinEcore_NewFileCreation() throws Exception {
		IWorkbenchWizard wizard = new OCLinEcoreFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(modelFile));
		createAndFinishWizardDialog(wizard);
		String actualContents = readNewFile(EXPECTED_OCLINECORE_NAME);
		StringBuilder s = new StringBuilder();
		s.append("import 'Test.ecore';\n");
		s.append("\n");
		s.append("package example : ex = 'http://www.example.org/examples/example.ecore'\n");
		s.append("{\n");
		s.append("	-- Example Class with hierarchical properties and an invariant\n");
		s.append("	class Example\n");
		s.append("	{\n");
		s.append("		property name : String[?];\n");
		s.append("		property children#parent : Example[*] { composes, ordered } ;\n");
		s.append("		property parent#children : Example[?];\n");
		s.append("		operation ucName() : String[?] {\n");
		s.append("			body: name?.toUpperCase();\n");
		s.append("		}\n");
		s.append("		invariant NameIsLowerCase('Expected a lowercase name rather than '' + name + '''):\n");
		s.append("			name = name?.toLowerCase();\n");
		s.append("	}\n");
		s.append("}\n");
		String expectedContents = s.toString();
		assertEquals(expectedContents, actualContents);
		XtextEditor activeEditor = getActiveEditor();
		assertEquals(OCLinEcorePlugin.LANGUAGE_ID, activeEditor.getLanguageName());
		activeEditor.close(false);
	}

	@Test
	public void test_EcoreWithOCL_NewFileCreation() throws Exception {
		IFile modelFile = project.getFile("Testing.xxx");
		IWorkbenchWizard wizard = new EcoreWithOCLFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(modelFile));
		createAndFinishWizardDialog(wizard);
		TestUIUtil.wait(1000);		// Wait for "Please wait" to go away
		XtextEditor activeEditor = getActiveEditor();
		assertEquals(OCLinEcorePlugin.LANGUAGE_ID, activeEditor.getLanguageName());
		IXtextDocument document = activeEditor.getDocument();
		String actualContents = document.get();
		StringBuilder s = new StringBuilder();
		s.append("package example : ex = 'http://www.example.org/examples/example.ecore'\n");
		s.append("{\n");
		s.append("	class Example\n");
		s.append("	{\n");
		s.append("		operation ucName() : String[?]\n");
		s.append("		{\n");
		s.append("			body: name?.toUpperCase();\n");
		s.append("		}\n");
		s.append("		attribute name : String[?];\n");
		s.append("		property children#parent : Example[*] { ordered composes };\n");
		s.append("		property parent#children : Example[?];\n");
		s.append("		invariant NameIsLowerCase('Expected a lowercase name rather than '' + name + '''):\n");
		s.append("			name = name?.toLowerCase();\n");
		s.append("	}\n");
		s.append("}\n");
		String expectedContents = s.toString();
		assertEquals(expectedContents.trim().replaceAll("\\s+", " "), actualContents.trim().replaceAll("\\s+", " "));
		activeEditor.close(false);
	}

	@Test
	public void test_OCLstdlib_NewFileCreation() throws Exception {
		IWorkbenchWizard wizard = new OCLstdlibFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(modelFile));
		createAndFinishWizardDialog(wizard);
		String actualContents = readNewFile(EXPECTED_OCLSTDLIB_NAME);
		StringBuilder s = new StringBuilder();
		s.append("-- import an existing library to be extended\n");
		s.append("import '" + LibraryConstants.STDLIB_URI + "';\n");
		s.append("\n");
		s.append("-- import an extension library re-using the imported library nsURI\n");
		s.append("library lib : lib = '" + LibraryConstants.STDLIB_URI + "' {\n");
		s.append("    type String : PrimitiveType {\n");
		s.append("    	-- define an additional operation accessed by my.strings.ExtraOperation.INSTANCE.evaluate\n");
		s.append("    	operation extraOperation(elem : Boolean) : Boolean => 'my.strings.ExtraOperation';\n");
		s.append("    }\n");
		s.append("}\n");
		String expectedContents = s.toString();
		assertEquals(expectedContents, actualContents);
		XtextEditor activeEditor = getActiveEditor();
		assertEquals(OCLstdlibPlugin.LANGUAGE_ID, activeEditor.getLanguageName());
		activeEditor.close(false);
	}
}
