/*******************************************************************************
 * Copyright (c) 2013, 2020 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCase;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystemHelper;
import org.eclipse.ocl.examples.xtext.tests.TestProject;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.ui.OCLProjectHelper;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.ocl.xtext.base.ui.utilities.BaseUIUtil;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import junit.framework.TestCase;

/**
 * Tests that exercise the new complete OCL creation wizard page.
 */
public class FileNewWizardTest extends TestCase
{
	private static final /*@NonNull*/ String PAGE_NAME = BaseUIMessages.NewWizardPage_pageName;

	private static final @NonNull String TEST_ECORE_NAME = "Test.ecore";
	private static final @NonNull String EXPECTED_OCL_NAME = "Test.ocl";
	private static final @NonNull String EXPECTED_OCLINECORE_NAME = "Test.oclinecore";
	private static final @NonNull String EXPECTED_OCLSTDLIB_NAME = "Test.oclstdlib";
	private static final @NonNull String EXPECTED_PACKAGE_NAME = "test_package";
	private static final @NonNull String EXPECTED_CLASS_NAME = "TestClass";
	private static final @NonNull String EXPECTED_FEATURE_NAME = "testFeature";

	private static final boolean SUPPRESS_OCL_NATURE = false;

	@Rule public @NonNull TestName testName = new TestName();
	public @Nullable TestFileSystem testFileSystem = null;
	public @Nullable TestProject testProject = null;
	public @Nullable TestFile testFile = null;
	private IProject testIProject = null;
	private IFile testIFile = null;

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

	// Relocated from StandaloneCommand where late init of the global URIConvert had the undesirable
	//	side effect of a late EcorePlugin.ExtensionProcessor.process wiping registrations.
	//	?? use the thread local
	private static @NonNull URIConverter getURIConverter() {
		URIConverter uriConverter = URIConverter.INSTANCE;
		if (!EcorePlugin.IS_ECLIPSE_RUNNING && uriConverter.getURIMap().isEmpty()) {
			EcorePlugin.ExtensionProcessor.process(null);
			uriConverter.getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
		}
		return uriConverter;
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
						Shell shell = getShell();
						if ((shell != null) && !shell.isDisposed()) {
							shell.getDisplay().asyncExec(new Runnable()
							{
								@Override
								public void run() {
									finishPressed();
								}
							});
						}
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

	@Override
	public String getName() {
		return TestUtil.getName(ClassUtil.nonNullState(super.getName()));
	}

	protected final @NonNull TestFileSystem getTestFileSystem() {
		return getTestFileSystem("");
	}

	protected @NonNull TestFileSystem getTestFileSystem(@NonNull String pathFromCurrentWorkingDirectoryToFileSystem) {
		TestFileSystem testFileSystem2 = testFileSystem;
		if (testFileSystem2 == null) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				File testBundleFile = new File(".project");
				assert !testBundleFile.exists() : "Default working directory should be the workspace rather than a project: " + testBundleFile.getAbsolutePath();
			}
			testFileSystem = testFileSystem2 = TestFileSystem.create(getTestFileSystemHelper(), pathFromCurrentWorkingDirectoryToFileSystem);
		}
		return testFileSystem2;
	}

	protected @NonNull TestFileSystemHelper getTestFileSystemHelper() {
		return new TestFileSystemHelper();
	}

	protected @NonNull TestFile getTestFile(@NonNull String filePath, @NonNull InputStream inputStream) throws IOException {
		TestProject testProject = getTestProject();
		return testProject.getOutputFile(filePath, inputStream);
		//		return URI.createFileURI(outFile.getFile().toString());
	}

	public @NonNull String getTestName() {
		String name = super.getName();
		if (name != null) {
			return name;
		}
		String methodName = testName.getMethodName();
		return methodName != null ? methodName : "<unnamed>";
	}

	protected final @NonNull TestProject getTestProject() {
		return getTestProject("");
	}

	protected @NonNull TestProject getTestProject(@NonNull String pathFromCurrentWorkingDirectoryToFileSystem) {
		TestProject testProject2 = testProject;
		if (testProject2 == null) {
			String testProjectName = "_OCL_" + getClass().getSimpleName() + "__" + getTestName();
			testProject = testProject2 = getTestFileSystem(pathFromCurrentWorkingDirectoryToFileSystem).getTestProject(testProjectName, true);
		}
		return testProject2;
	}

	protected @NonNull String readNewFile(String fileName) throws CoreException, IOException {
		IFile oclFile = getTestProject().getIProject().getFile(fileName);
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
		if (SUPPRESS_OCL_NATURE) {
			System.err.println("OCL nature suppressed for " + getTestName());
		}
		super.setUp();
		TestProject testProject = getTestProject();
		//
		IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(ResourcesPlugin.PI_RESOURCES);
		prefs.putBoolean(ResourcesPlugin.PREF_LIGHTWEIGHT_AUTO_REFRESH, true);
		//
		testIProject = testProject.getIProject();
		assertTrue(testIProject.exists());
		NullProgressMonitor nullMonitor = new NullProgressMonitor();
		IProjectDescription description = ClassUtil.nonNullState(testIProject.getDescription());
		if (!SUPPRESS_OCL_NATURE) {
			BaseUIUtil.toggleNature(description, OCLProjectHelper.NATURE_ID);
		}
		testIProject.setDescription(description, IResource.FORCE | IResource.KEEP_HISTORY, nullMonitor);
		//
		URI inputURI = URI.createPlatformPluginURI(PivotTestCase.PLUGIN_ID + "/models/wizard/" + TEST_ECORE_NAME, true);
		InputStream inputStream = getURIConverter().createInputStream(inputURI);
		assert inputStream != null;
		testFile = getTestFile(TEST_ECORE_NAME, inputStream);
		//
		testIFile = testIProject.getFile(TEST_ECORE_NAME);
		assertTrue(testIFile.exists());
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//		if (project.exists()) {
		//			project.delete(true, true, new NullProgressMonitor());
		//		}
	}

	@Test
	public void test_CompleteOCLFile_Dialog() {
		CompleteOCLFileNewWizard wizard = new CompleteOCLFileNewWizard();
		AbstractFileNewWizardPage wizardPage = wizard.createNewWizardPage(testIFile);
		CompleteOCLFileDialog dialog = new CompleteOCLFileDialog(wizard, wizardPage, testIFile);
		dialog.createDialogArea(new Shell());
		assertEquals("ocl", wizard.getNewFileExtension());
		assertEquals(CompleteOCLUIMessages.NewWizardPage_fileNameLabel, wizard.getNewFileLabel());
		assertEquals("/" + getTestProject().getName() + "/" + EXPECTED_OCL_NAME, dialog.getNewFilePath().toString());
		List<URI> uris = new ArrayList<URI>();
		uris.add(URI.createPlatformResourceURI(getTestProject().getName() + "/" + TEST_ECORE_NAME, true));
		assertEquals(uris, dialog.getURIs());
		wizard.dispose();
	}

	@Test
	public void test_CompleteOCLFile_NewWizardPage() {
		CompleteOCLFileNewWizard wizard = new CompleteOCLFileNewWizard();
		AbstractFileNewWizardPage wizardPage = wizard.createNewWizardPage(testIFile);
		assertEquals(PAGE_NAME, wizardPage.getName());
		assertEquals(CompleteOCLUIMessages.NewWizardPage_pageSummary, wizardPage.getTitle());
		assertEquals(CompleteOCLUIMessages.NewWizardPage_pageDescription, wizardPage.getDescription());
		assertNull(wizardPage.getErrorMessage());
		wizard.dispose();
	}

	/*	@Test
	public void test_CompleteOCLFile_NewWizardPage_FileContent() {
		CompleteOCLFileNewWizard wizard = new CompleteOCLFileNewWizard();
		AbstractFileNewWizardPage wizardPage = wizard.createNewWizardPage(testIFile);
		AbstractFileDialog dialog = wizardPage.initDialog(testIFile);
//		dialog.fillContentsFromWorkspacePath(testIFile.getFullPath().toString());
		String expectedContents = getExpectedContents();
		String actualContents = wizard.getInitialContentsAsString(testIFile, dialog);
		assertEquals(expectedContents, actualContents);
	} */

	@Test
	public void test_CompleteOCL_NewFileCreation() throws Exception {
		IWorkbenchWizard wizard = new CompleteOCLFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(testIFile));
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
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(testIFile));
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
		IFile testIFile = getTestProject().getIProject().getFile("Testing.xxx");
		IWorkbenchWizard wizard = new EcoreWithOCLFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(testIFile));
		createAndFinishWizardDialog(wizard);
		TestUIUtil.wait(1000);		// Wait for "Please wait" to go away
		XtextEditor activeEditor = getActiveEditor();
		assertEquals(OCLinEcorePlugin.LANGUAGE_ID, activeEditor.getLanguageName());
		IXtextDocument document = activeEditor.getDocument();
		String actualContents = document.get();
		StringBuilder s = new StringBuilder();
		s.append("package example : ex = 'http://www.example.org/examples/example.ecore' {\n");
		s.append("	class Example {\n");
		s.append("		operation ucName() : String[?] {\n");
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
	//	assertEquals(expectedContents, actualContents);
		activeEditor.close(false);
	}

	@Test
	public void test_OCLstdlib_NewFileCreation() throws Exception {
		IWorkbenchWizard wizard = new OCLstdlibFileNewWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(testIFile));
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
