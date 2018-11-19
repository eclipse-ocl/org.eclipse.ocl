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
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystemHelper;
import org.eclipse.ocl.examples.xtext.tests.TestProject;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.xtext.base.ui.model.BaseEditorCallback;
import org.eclipse.ocl.xtext.oclinecore.ui.OCLinEcoreUiModule;
import org.eclipse.ocl.xtext.oclinecore.ui.internal.OCLinEcoreActivator;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;
import org.eclipse.ocl.xtext.oclstdlib.ui.OCLstdlibUiModule;
import org.eclipse.ocl.xtext.oclstdlib.ui.internal.OCLstdlibActivator;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateProposal;

import com.google.inject.Injector;

/**
 * Tests that completion proposals include expected results and/or exclude unwanted offerings.
 *
 * It is not intended to provide exhaustive testing of all possible proposals.
 *
 * It is assumed that the grammar-driven capabilities of Xtext will be generally correct.
 *
 * There we test first to conform that completion propsals exist and so detect whether some
 * unwanted evolution has occurred in Xtext or its usage.
 *
 * Then we test known challenging cases especially regressions.
 */
@SuppressWarnings("null")
public class CompletionProposalTests extends XtextTestCase
{
	public static interface IReferenceCompletionProposal
	{
		boolean covers(@NonNull ICompletionProposal aProposal);
	}

	public static class ReferenceCompletionProposal implements IReferenceCompletionProposal
	{
		protected final @NonNull String name;

		public ReferenceCompletionProposal(@NonNull String name) {
			this.name = name;
		}

		@Override
		public boolean covers(@NonNull ICompletionProposal aProposal) {
			if (!name.equals(aProposal.getDisplayString())) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static class ReferenceConfigurableCompletionProposal extends ReferenceCompletionProposal implements IReferenceCompletionProposal
	{
		public ReferenceConfigurableCompletionProposal(@NonNull String name) {
			super(name);
		}

		@Override
		public boolean covers(@NonNull ICompletionProposal aProposal) {
			if (!(aProposal instanceof ConfigurableCompletionProposal)) {
				return false;
			}
			return super.covers(aProposal);
		}
	}

	public static class ReferenceXtextTemplateProposal extends ReferenceCompletionProposal
	{
		public ReferenceXtextTemplateProposal(@NonNull String name) {
			super(name);
		}

		@Override
		public boolean covers(@NonNull ICompletionProposal aProposal) {
			if (!(aProposal instanceof XtextTemplateProposal)) {
				return false;
			}
			return super.covers(aProposal);
		}
	}

	public static final @NonNull IReferenceCompletionProposal abstractKeywordProposal = new ReferenceConfigurableCompletionProposal("abstract");
	public static final @NonNull IReferenceCompletionProposal annotationTemplateProposal = new ReferenceXtextTemplateProposal("Annotation - annotation declaration");
	public static final @NonNull IReferenceCompletionProposal selfKeywordProposal = new ReferenceConfigurableCompletionProposal(PivotConstants.SELF_NAME);
	public static final @NonNull IReferenceCompletionProposal sNameProposal = new ReferenceConfigurableCompletionProposal("s");

	public static void assertExcludes(ICompletionProposal[] actualProposals, IReferenceCompletionProposal expectedProposal) {
		for (ICompletionProposal actualProposal : actualProposals) {
			if (expectedProposal.covers(actualProposal)) {
				fail("Unexpected completion proposal " + expectedProposal);
			}
		}
	}

	public static void assertIncludes(ICompletionProposal[] actualProposals, IReferenceCompletionProposal expectedProposal) {
		for (ICompletionProposal actualProposal : actualProposals) {
			if (expectedProposal.covers(actualProposal)) {
				return;
			}
		}
		fail("Missing completion proposal " + expectedProposal);
	}

	protected XtextContentAssistProcessor contentAssistProcessor = null;
	protected XtextEditor editor = null;

	protected @NonNull FileEditorInput createEcoreFileEditorInput(@NonNull OCL ocl, @NonNull IContainer container, @NonNull String fileName, @NonNull String testDocument)throws IOException, CoreException {
		String ecoreString = createEcoreString(ocl, fileName, testDocument, true);
		InputStream inputStream = new URIConverter.ReadableInputStream(ecoreString, "UTF-8");
		FileEditorInput fileEditorInput = TestUIUtil.createFileEditorInput(container, fileName, inputStream);
		return fileEditorInput;
	}

	protected void doTearDown(XtextEditor editor) {
		TestUIUtil.flushEvents();
		editor.close(false);
		TestUIUtil.flushEvents();
	}

	public void doTestEditor(@NonNull String testContent, @NonNull IReferenceCompletionProposal @Nullable [] expectedProposals,
			@NonNull IReferenceCompletionProposal @Nullable [] unexpectedProposals) throws Exception {
		int cursorIndex = testContent.indexOf("$");
		String trueContent = testContent.replace("$",  "");
		IXtextDocument document = editor.getDocument();
		document.set(trueContent);
		TestUIUtil.flushEvents();
		ITextViewer viewer = editor.getInternalSourceViewer();
		ICompletionProposal[] actualProposals = contentAssistProcessor.computeCompletionProposals(viewer, cursorIndex);
		//		for (ICompletionProposal actualProposal : actualProposals) {
		//			System.out.println(actualProposal);
		//		}
		if (expectedProposals != null) {
			for (IReferenceCompletionProposal expectedProposal : expectedProposals) {
				assertIncludes(actualProposals, expectedProposal);
			}
		}
		if (unexpectedProposals != null) {
			for (IReferenceCompletionProposal unexpectedProposal : unexpectedProposals) {
				assertExcludes(actualProposals, unexpectedProposal);
			}
		}
	}

	@Override
	protected void setUp() throws Exception {
		JavaClassScope.SUPPRESS_WORK_THREAD = true;
		TestUIUtil.suppressGitPrefixPopUp();
		super.setUp();
	}

	protected void doSetUp(@NonNull String editorId, Injector injector, @NonNull String fileName, @NonNull String initialContent)
			throws CoreException, PartInitException, IOException {
		injector.getInstance(BaseEditorCallback.class).setDontAskForNatureAgain();
		contentAssistProcessor = injector.getInstance(XtextContentAssistProcessor.class);
		InputStream inputStream = new URIConverter.ReadableInputStream(initialContent, "UTF-8");
		TestProject testProject = getTestProject();
		createIFile(testProject, "META-INF/MANIFEST.MF",
			"Manifest-Version: 1.0\n"+
					"Bundle-ManifestVersion: 2\n"+
					"Bundle-Name: " + testProject.getName() + "\n"+
					"Bundle-SymbolicName: " + testProject.getName() + "\n"+
					"Bundle-Version: 1.0.0.qualifier\n"+
					"Require-Bundle: org.eclipse.emf.ecore.edit,\n"+
					"  org.eclipse.ocl.pivot\n"+
				"");
		createIFile(testProject, ".classpath",
			"<classpath>\n" +
					"  <classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER\"/>\n" +
					"  <classpathentry kind=\"con\" path=\"org.eclipse.pde.core.requiredPlugins\"/>\n" +
					"  <classpathentry kind=\"src\" path=\"src\"/>\n" +
					"  <classpathentry kind=\"output\" path=\"bin\"/>\n" +
					"</classpath>\n"+
				"");
		createIFile(testProject, "src/Test.java",
			"import org.eclipse.emf.ecore.provider.*;\n"+
					"public class Test {}\n"+
				"");
		IProject project = testProject.getIProject();
		//		project.open(null);
		assert project.isOpen();
		TestUIUtil.flushEvents();;
		project.build(IncrementalProjectBuilder.FULL_BUILD, null);
		TestUIUtil.flushEvents();
		FileEditorInput fileEditorInput = TestUIUtil.createFileEditorInput(project, fileName, inputStream);
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = activeWorkbenchWindow.getActivePage();
		editor = (XtextEditor) IDE.openEditor(page, fileEditorInput, editorId, true);
		TestUIUtil.flushEvents();
	}

	private @NonNull TestFile createIFile(@NonNull TestProject testProject, @NonNull String testFilePath, @NonNull String fileContents) throws IOException {
		InputStream inputStream = new URIConverter.ReadableInputStream(fileContents, "UTF-8");
		return testProject.getOutputFile(testFilePath, inputStream);
	}

	@Override
	protected @NonNull TestFileSystemHelper getTestFileSystemHelper() {
		return new TestFileSystemHelper()
		{
			@Override
			public @Nullable IProjectDescription updateProjectDescription(@NonNull IProjectDescription projectDescription) {
				projectDescription.setNatureIds(new String[]{"org.eclipse.jdt.core.javanature", "org.eclipse.pde.PluginNature"});
				ICommand javaCommand1 = projectDescription.newCommand();
				javaCommand1.setBuilderName("org.eclipse.jdt.core.javabuilder");
				ICommand javaCommand2 = projectDescription.newCommand();
				javaCommand2.setBuilderName("org.eclipse.pde.ManifestBuilder");
				ICommand javaCommand3 = projectDescription.newCommand();
				javaCommand3.setBuilderName("org.eclipse.pde.SchemaBuilder");
				projectDescription.setBuildSpec(new ICommand[]{javaCommand1, javaCommand2, javaCommand3});
				return projectDescription;
			}
		};
	}

	public void testEditor_OCLinEcore_Completions() throws Exception {
		Injector injector = OCLinEcoreActivator.getInstance().getInjector(OCLinEcoreActivator.ORG_ECLIPSE_OCL_XTEXT_OCLINECORE_OCLINECORE);
		doSetUp(OCLinEcoreUiModule.EDITOR_ID, injector, "completion.oclinecore", "package test : test = 'test' {}");
		//		for (int i = 0; i < 100; i++) {
		//			TestUIUtil.flushEvents();
		//			Thread.sleep(100);
		//		}
		doTestEditor("package p : p = 'p' {$}",
			new IReferenceCompletionProposal[]{abstractKeywordProposal, annotationTemplateProposal}, null);
		doTestEditor("package p : p = 'p' { class C { invariant I:$}}",
			new IReferenceCompletionProposal[]{selfKeywordProposal}, null);
		doTestEditor("package p : p = 'p' { class C { property s:String; invariant I:self.$}}",
			new IReferenceCompletionProposal[]{sNameProposal}, null/*new IReferenceCompletionProposal[]{selfKeywordProposal}*/);
		doTearDown(editor);
	}

	public void testEditor_OCLstdlib_Completions() throws Exception {
		boolean isTycho = CGUtil.isTychoSurefire();
		if (isTycho) {				// FIXME BUG 526252
			System.err.println(getName() + " has been disabled -see Bug 526252");
			return;
		}
		Injector injector = OCLstdlibActivator.getInstance().getInjector(OCLstdlibActivator.ORG_ECLIPSE_OCL_XTEXT_OCLSTDLIB_OCLSTDLIB);
		doSetUp(OCLstdlibUiModule.EDITOR_ID, injector, "completion.oclstdlib",
			"import 'http://www.eclipse.org/ocl/2015/Library';\n" +
					"library ocl : ocl = 'http://www.eclipse.org/ocl/2015/Library' {\n" +
					"	type Complex : PrimitiveType {\n" +
					"	}\n" +
				"}\n");
		try {
			IReferenceCompletionProposal proposal1a = new ReferenceConfigurableCompletionProposal("PrimitiveType");
			IReferenceCompletionProposal proposal1b = new ReferenceConfigurableCompletionProposal("{");
			doTestEditor(
				"import 'http://www.eclipse.org/ocl/2015/Library';\n" +
						"library ocl : ocl = 'http://www.eclipse.org/ocl/2015/Library' {\n" +
						"	type Complex : Primitive$ {\n" +
						"	}\n" +
						"}\n",
						new IReferenceCompletionProposal[]{proposal1a, proposal1b}, null);
			//
			//	Completion proposal that probably resolves to a Jar entry.
			//
			IReferenceCompletionProposal proposal2a = new ReferenceConfigurableCompletionProposal("org.eclipse.emf.common.util.Reflect");
			IReferenceCompletionProposal proposal2b = new ReferenceConfigurableCompletionProposal("org.eclipse.emf.common.util.ResourceLocator");
			doTestEditor(
				"import 'http://www.eclipse.org/ocl/2015/Library';\n" +
						"library ocl : ocl = 'http://www.eclipse.org/ocl/2015/Library' {\n" +
						"	type Complex : PrimitiveType {\n" +
						"		operation testing() : String => 'org.eclipse.emf.common.util.R$';\n" +
						"	}\n" +
						"}\n",
						new IReferenceCompletionProposal[]{proposal2a, proposal2b}, null);
			//
			//	Completion proposal that probably resolves to a folder entry.
			//
			IReferenceCompletionProposal proposal3a = new ReferenceConfigurableCompletionProposal("org.eclipse.ocl.pivot.internal.ids.OclInvalidTypeIdImpl");
			IReferenceCompletionProposal proposal3b = new ReferenceConfigurableCompletionProposal("org.eclipse.ocl.pivot.internal.ids.OclVoidTypeIdImpl");
			doTestEditor(
				"import 'http://www.eclipse.org/ocl/2015/Library';\n" +
						"library ocl : ocl = 'http://www.eclipse.org/ocl/2015/Library' {\n" +
						"	type Complex : PrimitiveType {\n" +
						"		operation testing() : String => 'org.eclipse.ocl.pivot.internal.ids.O$';\n" +
						"	}\n" +
						"}\n",
						new IReferenceCompletionProposal[]{proposal3a, proposal3b}, null);
		}
		finally {
			doTearDown(editor);
		}
	}
}
