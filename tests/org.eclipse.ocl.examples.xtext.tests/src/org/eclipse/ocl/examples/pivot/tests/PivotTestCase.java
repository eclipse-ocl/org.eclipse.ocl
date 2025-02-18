/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.delegate.ValidationDelegate;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.validation.ValidationContext;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.xtext.resource.XtextResource;

/**
 * Tests for OclAny operations.
 */
public class PivotTestCase extends AbstractPivotTestCase
{
	public static final @NonNull String @NonNull [] NO_MESSAGES = new @NonNull String[] {};
	public static final @NonNull String @NonNull [] SUPPRESS_VALIDATION = new @NonNull String[] {"FIXME"};	// FIXME should not be needed
	//	private static StandaloneProjectMap projectMap = null;
	private static Writer testLog = null;

	{
	//	PivotMetamodelManager.liveMetamodelManagers = new WeakHashMap<>();			// Prints the create/finalize of each MetamodelManager
	//	StandaloneProjectMap.liveStandaloneProjectMaps = new WeakHashMap<>();		// Prints the create/finalize of each StandaloneProjectMap
	//	ResourceSetImpl.liveResourceSets = new WeakHashMap<>();						// Requires edw-debug private EMF branch
	}

	protected static void appendChildren(StringBuilder s, List<Diagnostic> children, int depth) {
		for (Diagnostic child : children){
			s.append("\n");
			for (int i = 0; i < depth; i++) {
				s.append("    ");
			}
			s.append(child.getMessage());
			appendChildren(s, child.getChildren(), depth+1);
		}
	}

	public static void appendLog(String name, Object context, String testExpression, String parseVerdict, String evaluationVerdict, String evaluationTolerance) {
		if (testLog != null) {
			try {
				testLog.append("\"" + name.replace("\"", "\"\"") + "\"");
				testLog.append(";");
				try {
					if (context instanceof EObject) {
						URI contextURI = EcoreUtil.getURI((EObject)context);
						testLog.append("\"" + contextURI.toString().replace("\"", "\"\"") + "\"");
					}
				} catch(Throwable e) {
					testLog.append("\"null\"");
				}
				testLog.append(";");
				if (testExpression != null) {
					testLog.append("\"" + StringUtil.convertToOCLString(testExpression) + "\"");
				}
				testLog.append(";");
				if (parseVerdict != null) {
					testLog.append("\"" + StringUtil.convertToOCLString(parseVerdict) + "\"");
				}
				testLog.append(";");
				if (evaluationVerdict != null) {
					testLog.append("\"" + StringUtil.convertToOCLString(evaluationVerdict) + "\"");
				}
				testLog.append(";");
				if (evaluationTolerance != null) {
					testLog.append("\"" + evaluationTolerance.replace("\"", "\"\"") + "\"");
				}
				testLog.append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static @NonNull XtextResource as2cs(@NonNull OCL ocl, @NonNull ASResource asResource, @NonNull URI outputURI) throws IOException {
		@NonNull ResourceSet resourceSet = ocl.getResourceSet();
		XtextResource xtextResource = ClassUtil.nonNullState((XtextResource) resourceSet.createResource(outputURI, OCLinEcoreCSPackage.eCONTENT_TYPE));
		//		ResourceSet csResourceSet = resourceSet; //new ResourceSetImpl();
		//		csResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cs", new EcoreResourceFactoryImpl());
		//		csResourceSet.getPackageRegistry().put(PivotPackage.eNS_URI, PivotPackage.eINSTANCE);
		//		Resource csResource = csResourceSet.createResource(uri);
		//		URI oclinecoreURI = ecoreResource.getURI().appendFileExtension("oclinecore");
		ocl.as2cs(asResource, (CSResource) xtextResource);
		assertNoResourceErrors("Conversion failed", xtextResource);
		//		csResource.save(null);
		//
		//	CS save and reload
		//
		URI savedURI = ClassUtil.nonNullState(asResource.getURI());
		//		asResource.setURI(PivotUtil.getNonPivotURI(savedURI).appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION));
		if (asResource.isSaveable()) {
			asResource.setURI(outputURI.trimFileExtension().trimFileExtension().appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION));
			asResource.save(null);
			asResource.setURI(savedURI);
		}
		assertNoDiagnosticErrors("Concrete Syntax validation failed", xtextResource);
		try {
			DebugTimestamp debugTimestamp = new DebugTimestamp(ClassUtil.nonNullState(xtextResource.getURI().toString()));
			xtextResource.save(null);
			debugTimestamp.log("Serialization save done");
		}
		catch (Exception e) {
			e.printStackTrace();
			URI xmiURI = outputURI.appendFileExtension(".xmi");
			Resource xmiResource = resourceSet.createResource(xmiURI);
			xmiResource.getContents().addAll(xtextResource.getContents());
			xmiResource.save(null);
		//	fail(e.toString());
			throw e;
		}
		return xtextResource;
	}

	public static @NonNull Resource as2ecore(@NonNull EnvironmentFactory environmentFactory, @NonNull Resource asResource, @NonNull URI ecoreURI, @NonNull String @NonNull [] asValidationMessages) throws IOException {
		assert ThreadLocalExecutor.basicGetEnvironmentFactory() == environmentFactory;
		Resource ecoreResource = AS2Ecore.createResource((EnvironmentFactoryInternal) environmentFactory, asResource, ecoreURI, null);
		ecoreResource.save(null);
		if (asValidationMessages != SUPPRESS_VALIDATION) {
			//			assertNoValidationErrors("AS2Ecore invalid", ecoreResource);
			assertValidationDiagnostics("AS2Ecore invalid", ecoreResource, asValidationMessages);
		}
		return ecoreResource;
	}

	public static void assertNoResourceErrorsOrWarnings(@NonNull String prefix, @NonNull Resource resource) {
		String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getErrors()), prefix, "\n\t");
		if (message != null)
			fail(message);
		message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getWarnings()), prefix, "\n\t");
		if (message != null)
			fail(message);
	}

	public static void assertNoValidationErrors(@NonNull String string, @NonNull Resource resource) {
		Executor savedExecutor = ThreadLocalExecutor.basicGetExecutor();
		Executor savedInterpretedExecutor = savedExecutor != null ? savedExecutor.basicGetInterpretedExecutor() : null;
		try {
			for (EObject eObject : resource.getContents()) {
				assertNoValidationErrorsInternal(string + " " + resource.getURI(), ClassUtil.nonNullEMF(eObject));
			}
		}
		finally {
			if (savedExecutor != ThreadLocalExecutor.basicGetExecutor()) {
				ThreadLocalExecutor.setExecutor(null);
			}
			else if (savedExecutor != null) {
				if (savedInterpretedExecutor != savedExecutor.basicGetInterpretedExecutor()) {
					savedExecutor.setInterpretedExecutor(null);
				}
			}
		}
	}

	public static void assertNoValidationErrors(@NonNull String string, @NonNull EObject eObject) {
		Executor savedExecutor = ThreadLocalExecutor.basicGetExecutor();
		Executor savedInterpretedExecutor = savedExecutor != null ? savedExecutor.basicGetInterpretedExecutor() : null;
		try {
			assertNoValidationErrorsInternal(string + " " + EcoreUtil.getURI(eObject), eObject);
		}
		finally {
			if (savedExecutor != ThreadLocalExecutor.basicGetExecutor()) {
				ThreadLocalExecutor.setExecutor(null);
			}
			else if (savedExecutor != null) {
				if (savedInterpretedExecutor != savedExecutor.basicGetInterpretedExecutor()) {
					savedExecutor.setInterpretedExecutor(null);
				}
			}
		}
	}

	protected static void assertNoValidationErrorsInternal(@NonNull String prefix, @NonNull EObject eObject) {
		ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(eObject);
		ValidationContext validationContext = new ValidationContext(validationRegistry);
		//		Resource eResource = ClassUtil.nonNullState(eObject.eResource());
		//		PivotUtilInternal.getMetamodelManager(eResource);	// FIXME oclIsKindOf fails because ExecutableStandardLibrary.getMetaclass is bad
		BasicDiagnostic diagnostics = PivotDiagnostician.BasicDiagnosticWithRemove.validate(eObject, validationContext);
		List<Diagnostic> children = diagnostics.getChildren();
		if (children.size() <= 0) {
			return;
		}
		StringBuilder s = new StringBuilder();
		s.append(prefix + ": " + children.size() + " validation errors");
		appendChildren(s, children, 0);
		fail(s.toString());
	}

	public static void assertResourceErrors(@NonNull String prefix, @NonNull Resource resource, String... messages) {
		assertResourceDiagnostics(prefix, ClassUtil.nonNullEMF(resource.getErrors()), messages);
	}

	public static void assertResourceDiagnostics(@NonNull String prefix, @NonNull List<Resource.Diagnostic> resourceDiagnostics, String... messages) {
		Map<String, Integer> expected = new HashMap<String, Integer>();
		if (messages != null) {
			for (String message : messages) {
				Integer count = expected.get(message);
				count = count == null ? 1 : count + 1;
				expected.put(message, count);
			}
		}
		StringBuilder s1 = null;
		for (Resource.Diagnostic error : resourceDiagnostics) {
			String actual = error.getMessage();
			Integer expectedCount = expected.get(actual);
			if ((expectedCount == null) || (expectedCount <= 0)) {
				if (s1 == null) {
					s1 = new StringBuilder();
					s1.append("\nUnexpected errors");
				}
				s1.append("\n");
				s1.append(actual);
			}
			else {
				expected.put(actual, expectedCount-1);
			}
		}
		StringBuilder s2 = null;
		for (String key : expected.keySet()) {
			Integer count = expected.get(key);
			assert count != null;
			while (count-- > 0) {
				if (s2 == null) {
					s2 = new StringBuilder();
					s2.append("\nMissing errors");
				}
				s2.append("\n");
				s2.append(key);
			}
		}
		if (s1 == null) {
			if (s2 == null) {
				return;
			}
			else {
				fail(s2.toString());
			}
		}
		else {
			if (s2 == null) {
				fail(s1.toString());
			}
			else {
				fail(s1.toString() + s2.toString());
			}
		}
	}

	public static void closeTestLog() {
		if (testLog != null) {
			try {
				testLog.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void createTestLog(File file) {
		try {
			testLog = file != null ? new FileWriter(file) : null;
			if (testLog != null) {
				try {
					testLog.append("Test Group");
					testLog.append(";");
					testLog.append("Context Object");
					testLog.append(";");
					testLog.append("Test Expression");
					testLog.append(";");
					testLog.append("Parser Result");
					testLog.append(";");
					testLog.append("Evaluation Result");
					testLog.append(";");
					testLog.append("Result Tolerance");
					testLog.append("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static @NonNull Resource cs2ecore(@NonNull EnvironmentFactory environmentFactory, @NonNull String testDocument, @NonNull URI ecoreURI) throws IOException {
		InputStream inputStream = new URIConverter.ReadableInputStream(testDocument, "UTF-8");
		URI xtextURI = URI.createURI("test.oclinecore");
		ResourceSet resourceSet = new ResourceSetImpl();
		EssentialOCLCSResource xtextResource = ClassUtil.nonNullState((EssentialOCLCSResource) resourceSet.createResource(xtextURI, null));
	// XXX	environmentFactory.adapt(xtextResource);
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Loading Xtext", xtextResource);
		Resource asResource = cs2as(environmentFactory, xtextResource, null);
		Resource ecoreResource = as2ecore(environmentFactory, asResource, ecoreURI, NO_MESSAGES);
		return ecoreResource;
	}

	public static @NonNull Resource cs2as(@NonNull OCL ocl, @NonNull String testDocument) throws IOException {
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		InputStream inputStream = new URIConverter.ReadableInputStream(testDocument, "UTF-8");
		URI xtextURI = URI.createURI("test.oclinecore");
		ResourceSet resourceSet = new ResourceSetImpl();
		EssentialOCLCSResource xtextResource = ClassUtil.nonNullState((EssentialOCLCSResource) resourceSet.createResource(xtextURI, null));
	// XXX	environmentFactory.adapt(xtextResource);
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Loading Xtext", xtextResource);
		Resource asResource = cs2as(environmentFactory, xtextResource, null);
		return asResource;
	}

	public static @NonNull Resource cs2as(@NonNull EnvironmentFactory environmentFactory, @NonNull BaseCSResource xtextResource, @Nullable URI pivotURI) throws IOException {
		CS2AS cs2as = xtextResource.getCS2AS(environmentFactory);
		ASResource asResource = cs2as.getASResource();
		assertNoUnresolvedProxies("Unresolved proxies", asResource);
		if ((pivotURI != null) && asResource.isSaveable()) {
			asResource.setURI(pivotURI);
			asResource.save(null);
		}
		return asResource;
	}

	public static void disposeResourceSet(@NonNull ResourceSet resourceSet) {
		for (Resource next : resourceSet.getResources()) {
			next.unload();
			next.eAdapters().clear();
		}
		resourceSet.getResources().clear();
		resourceSet.eAdapters().clear();
	}

	/**
	 * Return the difference between expectedMessages and actualMessages, or null if no differences.
	 *
	 * The return is formatted one message per line with a leading new-line followed by
	 * an expected/actual count in parentheses followed by the messages
	 */
	public static String formatMessageDifferences(Bag<String> expectedMessages, @NonNull Bag<String> actualMessages) {
		Set<String> allMessages = new HashSet<String>(expectedMessages);
		allMessages.addAll(actualMessages);
		StringBuilder s = null;
		for (String message : allMessages) {
			int actualCount = actualMessages.count(message);
			int expectedCount = expectedMessages.count(message);
			if (actualCount != expectedCount) {
				if (s == null) {
					s = new StringBuilder();
				}
				s.append("\n  (" + expectedCount + "/" + actualCount + ") " + message);
			}
		}
		return s != null ? s.toString() : null;
	}

	public static @NonNull String @NonNull [] getMessages(String... messages) {
		@NonNull String[] messageArray = new @NonNull String[messages.length];
		for (int i = 0; i < messages.length; i++) {
			String message = messages[i];
			assert message != null;
			messageArray[i] = message;
		}
		return messageArray;
	}

	protected static abstract class TestRunnable implements Runnable
	{
		static int count = 0;

		private @Nullable EnvironmentFactoryInternal environmentFactory;
		protected final @NonNull String name;
		private @Nullable Throwable throwable = null;

		protected TestRunnable() {
			this(null);
		}

		protected TestRunnable(@Nullable EnvironmentFactory environmentFactory) {
			this.environmentFactory = (EnvironmentFactoryInternal)environmentFactory;
			this.name = "test" + count++;
		}

		@Override
		public void run() {
			try {
				if (environmentFactory != null) {
					ThreadLocalExecutor.attachEnvironmentFactory(environmentFactory);
				}
				runWithThrowable();
			}
			catch (Throwable t) {
				throwable = t;
				if (environmentFactory != null) {
					ThreadLocalExecutor.detachEnvironmentFactory(environmentFactory);
					environmentFactory = null;
				}
			}
		}
		protected abstract void runWithThrowable() throws Throwable;
	}

	/**
	 * Execute the test as a Runnable on its own thread so that the thread terminates and the
	 * release of resources by the finalizr is demonstrated.
	 * @throws Throwable
	 */
	protected void doTestRunnable(@NonNull TestRunnable testRunnable) throws Throwable {
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			testRunnable.run();		// Use directly -- too hard to interact with UI thread otherwise
		}
		else {
			Thread testThread = new Thread(testRunnable, testRunnable.name)
			{
				@Override
				public void run() {
					try {
						super.run();
					}
					finally {
						synchronized (this) {
							this.notify();
						}
					}
				}
			};
			testThread.start();
			synchronized (testThread) {
				try {
					testThread.wait(1000000);		// Needlessly long wait to avoid confusing debug session
				} catch (InterruptedException e) {
					// Don't care -- e.printStackTrace();
				}
				if (testRunnable.throwable != null) {
					throw testRunnable.throwable;
				}
			}
		//	assert ThreadLocalExecutor.basicGetEnvironmentFactory() == null;			// XXX
		//	assert ThreadLocalExecutor.basicGetExecutor() == null;
		}
		if (testRunnable.throwable != null) {
			throw testRunnable.throwable;
		}
		// tearDown() cleans up / resets
	}

	@Deprecated /* @deprecated use getTestModelURI to facilitate legacy inherited testing */
	protected @NonNull URI getProjectFileURI(@NonNull String referenceName) {
		throw new UnsupportedOperationException();
		//		File projectFile = getProjectFile();
		//		return ClassUtil.nonNullState(URI.createFileURI(projectFile.toString() + "/" + referenceName));
	}

	protected PivotTestCase() {
		this(TestHelper.INSTANCE);
	}

	protected PivotTestCase(@NonNull TestHelper testHelper) {
		super(testHelper);
	}

	public void resetRegistries() {
		final Object object = ValidationDelegate.Factory.Registry.INSTANCE.get(OCLConstants.OCL_DELEGATE_URI);
		if (object instanceof org.eclipse.ocl.common.internal.delegate.OCLValidationDelegateMapping) {
			((org.eclipse.ocl.common.internal.delegate.OCLValidationDelegateMapping)object).reset();
		}
	}
}
