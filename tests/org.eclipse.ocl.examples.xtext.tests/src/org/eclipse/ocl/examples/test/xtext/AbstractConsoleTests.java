/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.xtext.console.ColorManager;
import org.eclipse.ocl.examples.xtext.console.OCLConsole;
import org.eclipse.ocl.examples.xtext.console.OCLConsolePage;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.library.executor.LazyEcoreModelManager;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.values.ObjectValue;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocument;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.part.IPageBookViewPage;

/**
 * Tests that exercise the Xtext OCL Console.
 */
public abstract class AbstractConsoleTests extends PivotTestCaseWithAutoTearDown
{
	public static class TestConsole extends OCLConsole
	{
		private static TestConsole instance;

		public static TestConsole getInstance() {
			if (instance == null) {
				instance = new TestConsole();
				ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {instance});
			}
			return instance;
		}

		private TestConsolePage page;

		@Override
		public void close() {
			super.close();
			TestUIUtil.flushEvents();
			instance = null;
		}

		@Override
		public IPageBookViewPage createPage(IConsoleView view) {
			page = new TestConsolePage(this);
			return page;
		}

		public final TestConsolePage getPage() {
			return page;
		}
	}

	public static class TestConsolePage extends OCLConsolePage
	{
		private StringBuilder s = new StringBuilder();
		private boolean popUpModelTypesUsageInformation = false;

		public TestConsolePage(TestConsole testConsole) {
			super(testConsole);
		}

		@Override
		protected void append(String text, RGB rgb, boolean bold) {
			super.append(text, rgb, bold);
			String boldTag;
			if (bold) { boldTag = "b"; }
			else { boldTag = null; }
			String rgbTag;
			if (rgb == ColorManager.DEFAULT) { rgbTag = null; }
			else if (rgb == ColorManager.OUTPUT_ERROR) { rgbTag = "error"; }
			else if (rgb == ColorManager.OUTPUT_RESULTS) { rgbTag = null; }
			else { rgbTag = "?"; }
			if (boldTag != null) {
				s.append("<" + boldTag + ">");
			}
			if (rgbTag != null) {
				s.append("<" + rgbTag + ">");
			}
			s.append(text + "\n");
			if (rgbTag != null) {
				s.append("</" + rgbTag + ">");
			}
			if (boldTag != null) {
				s.append("</" + boldTag + ">");
			}
		}

		@Override
		protected @Nullable EnvironmentFactoryAdapter createEditor(Composite s1) {
			EnvironmentFactoryInternal testEnvironmentFactory = new TestEnvironmentFactory();
			ThreadLocalExecutor.attachEnvironmentFactory(testEnvironmentFactory);
			return super.createEditor(s1);
		}

		@Override
		public boolean evaluate(String expression) {
			return super.evaluate(expression);
		}

		public String get() {
			return s.toString();
		}

		public boolean isPopUpModelTypesUsageInformation() {
			return popUpModelTypesUsageInformation;
		}

		public ILaunch launchDebugger() {
			return internalLaunchDebugger();
		}

		@Override
		protected void popUpModelTypesUsageInformation() {
			this.popUpModelTypesUsageInformation  = true;
		}

		@Override
		public void refreshSelection(Object selected) {
			super.refreshSelection(selected);
		}

		@Override
		public void resetDocument() {
			super.resetDocument();
			s = new StringBuilder();
		}

		public void resetPopUpModelTypesUsageInformation() {
			this.popUpModelTypesUsageInformation = false;
		}
	}

	/**
	 * See Bug 570894. Overridden to override LazyEcoreModelManager coonstruction to avoid
	 * all the sibling ResourceSet roots being found in the extent.
	 */
	protected static class TestEnvironmentFactory extends PivotEnvironmentFactory
	{
		protected TestEnvironmentFactory() {
			super(getProjectMap(), null, null);
		}

		@Override
		public @NonNull ModelManager createModelManager(@Nullable Object object) {
			if (object instanceof ObjectValue) {
				object = ((ObjectValue) object).getObject();
			}
			if (object instanceof EObject) {
				EObject rootContainer = EcoreUtil.getRootContainer((EObject)object);
				assert rootContainer != null;
				List<@NonNull EObject> rootList = Collections.singletonList(rootContainer);
				return new LazyEcoreModelManager(rootList, null, null);
			}
			return ModelManager.NULL;
		}
	}

	public static void assertConsoleResult(TestConsolePage consolePage, EObject contextObject, String testExpression, String expectedResult) {
		consolePage.resetDocument();
		TestUIUtil.flushEvents();
		consolePage.refreshSelection(contextObject);
		TestUIUtil.flushEvents();
		BaseDocument editorDocument = consolePage.getEditorDocument();
		//		System.out.println("Set " + testExpression);
		editorDocument.set(testExpression);
		TestUIUtil.flushEvents();			// Let ValidationJob and other activities have a go
		consolePage.evaluate(testExpression);
		TestUIUtil.flushEvents();			// FIXME on more than one occasion the previous result was returned (perhaps the new input was not set) (before additional flushEvents added above)
		String string = consolePage.get();
		assertEquals("<b>Evaluating:\n</b>" + testExpression + "\n<b>Results:\n</b>" + expectedResult, string);
	}

	public TestConsolePage consolePage;

	protected void doDelete(@NonNull String testProjectName) throws Exception {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUIUtil.suppressGitPrefixPopUp();
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject project = workspace.getRoot().getProject(testProjectName);
			project.delete(true, true, null);
		}
		/*		else {
			File dir = new File("src-gen/" + testProjectName);
			if (dir.exists()) {
				doDeleteDirectory(dir);
			}
		} */
	}

	protected @NonNull TestConsolePage openConsole() {
		TestUIUtil.closeIntro();
		TestUIUtil.flushEvents();
		TestConsole console = TestConsole.getInstance();
		IConsoleManager mgr = ConsolePlugin.getDefault().getConsoleManager();
		mgr.showConsoleView(console);
		TestUIUtil.flushEvents();
		@Nullable TestConsolePage consolePage = console.getPage();
		for (int i = 0; (consolePage == null) && (i < 100000); i++) {
			TestUIUtil.flushEvents();
			consolePage = console.getPage();
		}
		assert consolePage != null;
		return consolePage;
	}

	@Override
	protected void setUp() throws Exception {
		TestUIUtil.suppressGitPrefixPopUp();
		super.setUp();
		OCLstdlib.install();
		consolePage = openConsole();
	}

	@Override
	protected void tearDown() throws Exception {
		TestUIUtil.cancelAndWaitForValidationJob();
		//		System.out.println(Thread.currentThread().getName() + " pre-tearDown " + NameUtil.debugSimpleName(this));
		TestConsole.getInstance().close();
		consolePage = null;
		super.tearDown();
		//		System.out.println(Thread.currentThread().getName() + " post-tearDown " + NameUtil.debugSimpleName(this));
	}
}
