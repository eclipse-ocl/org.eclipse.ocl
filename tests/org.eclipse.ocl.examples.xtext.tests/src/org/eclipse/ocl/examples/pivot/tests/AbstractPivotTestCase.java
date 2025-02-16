/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UnresolvedProxyCrossReferencer;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.scoping.Attribution;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.validation.ValidationContext;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.xtext.base.BaseStandaloneSetup;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.idioms.IdiomsStandaloneSetup;
import org.eclipse.ocl.xtext.markup.MarkupStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Rule;
import org.junit.rules.TestName;

import junit.framework.TestCase;

/**
 * Abstracted commonality of OCL and QVTd test cases.
 */
public class AbstractPivotTestCase extends TestCase
{
	public static final @NonNull String PLUGIN_ID = "org.eclipse.ocl.examples.xtext.tests";
	public static final @NonNull TracingOption TEST_START = new TracingOption(PLUGIN_ID, "test/start");

	/*
	 * The following may be tweaked to assist debugging.
	 */
	public static boolean DEBUG_GC = false;			// True performs an enthusuastic resource release and GC at the end of each test
	public static boolean DEBUG_ID = false;			// True prints the start and end of each test.
	{
//		PivotUtilInternal.noDebug = false;
//		DEBUG_GC = true;
//		DEBUG_ID = true;
//		AbstractEnvironmentFactory.liveEnvironmentFactories = new WeakHashMap<>();	// Prints the create/finalize of each EnvironmentFactory
	}

	static long startTime;

	public static class GlobalStateMemento
	{
		private @NonNull HashMap<EPackage, Object> validatorReg;
		private @NonNull HashMap<String, Object> epackageReg;
		private @NonNull HashMap<String, Object> protocolToFactoryMap;
		private @NonNull HashMap<String, Object> extensionToFactoryMap;
		private @NonNull HashMap<String, Object> contentTypeIdentifierToFactoryMap;
		private @NonNull HashMap<String, Object> protocolToServiceProviderMap;
		private @NonNull HashMap<String, Object> extensionToServiceProviderMap;
		private @NonNull HashMap<String, Object> contentTypeIdentifierToServiceProviderMap;

		public GlobalStateMemento() {
			validatorReg = new HashMap<EPackage, Object>(EValidator.Registry.INSTANCE);
			epackageReg = new HashMap<String, Object>(EPackage.Registry.INSTANCE);
			protocolToFactoryMap = new HashMap<String, Object>(Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap());
			extensionToFactoryMap = new HashMap<String, Object>(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap());
			contentTypeIdentifierToFactoryMap = new HashMap<String, Object>(Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap());

			protocolToServiceProviderMap = new HashMap<String, Object>(IResourceServiceProvider.Registry.INSTANCE.getProtocolToFactoryMap());
			extensionToServiceProviderMap = new HashMap<String, Object>(IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap());
			contentTypeIdentifierToServiceProviderMap = new HashMap<String, Object>(IResourceServiceProvider.Registry.INSTANCE.getContentTypeToFactoryMap());
		}

		public void restoreGlobalState() {
			clearGlobalRegistries();
			EValidator.Registry.INSTANCE.putAll(validatorReg);
			EPackage.Registry.INSTANCE.putAll(epackageReg);

			Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().putAll(protocolToFactoryMap);
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().putAll(extensionToFactoryMap);
			Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().putAll(contentTypeIdentifierToFactoryMap);

			IResourceServiceProvider.Registry.INSTANCE.getProtocolToFactoryMap().putAll(protocolToServiceProviderMap);
			IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().putAll(extensionToServiceProviderMap);
			IResourceServiceProvider.Registry.INSTANCE.getContentTypeToFactoryMap().putAll(contentTypeIdentifierToServiceProviderMap);
		}

		public static void clearGlobalRegistries() {
			//			Registry eValidatorRegistry = EValidator.Registry.INSTANCE;
			//			for (EPackage key : eValidatorRegistry.keySet()) {
			//				Object object = eValidatorRegistry.get(key);
			//				System.out.println("key : " + key.getNsURI() + " => " + object.getClass().getName());
			//			}
			EValidator.Registry.INSTANCE.clear();
			EPackage.Registry.INSTANCE.clear();
			Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().clear();
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().clear();
			Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().clear();

			IResourceServiceProvider.Registry.INSTANCE.getProtocolToFactoryMap().clear();
			IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().clear();
			IResourceServiceProvider.Registry.INSTANCE.getContentTypeToFactoryMap().clear();
			initializeDefaults();
		}

		public static void initializeDefaults() {
			//EMF Standalone setup
			if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("ecore"))
				Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"ecore", new EcoreResourceFactoryImpl());
			if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("xmi"))
				Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"xmi", new XMIResourceFactoryImpl());
			if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eNS_URI))
				EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
			if (!EPackage.Registry.INSTANCE.containsKey(XtextPackage.eNS_URI))
				EPackage.Registry.INSTANCE.put(XtextPackage.eNS_URI, XtextPackage.eINSTANCE);
		}
	}

	/**
	 * Refine the standard CompleteOCLLoader to redirect the error callback to a TestCase.fail().
	 */
	public static final class TestCompleteOCLLoader extends CompleteOCLLoader
	{
		public TestCompleteOCLLoader(@NonNull EnvironmentFactory environmentFactory) {
			super(environmentFactory);
		}

		@Override
		protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
			TestCase.fail(primaryMessage + "\n\t" + detailMessage);
			return false;
		}
	}

	public static class TestHelper
	{
		public static final @NonNull TestHelper INSTANCE = new TestHelper();

		public void doStartUp() {
			IdiomsStandaloneSetup.class.getName();
			PivotStandaloneSetup.class.getName();
			BaseStandaloneSetup.class.getName();
			CompleteOCLStandaloneSetup.class.getName();
			EssentialOCLStandaloneSetup.class.getName();
			MarkupStandaloneSetup.class.getName();
			OCLinEcoreStandaloneSetup.class.getName();
			OCLstdlibStandaloneSetup.class.getName();
		}

		public void doTearDown() {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				Attribution.REGISTRY.clear();
			}
			IdiomsStandaloneSetup.doTearDown();
			PivotStandaloneSetup.doTearDown();
			BaseStandaloneSetup.doTearDown();
			CompleteOCLStandaloneSetup.doTearDown();
			EssentialOCLStandaloneSetup.doTearDown();
			MarkupStandaloneSetup.doTearDown();
			OCLinEcoreStandaloneSetup.doTearDown();
			OCLstdlibStandaloneSetup.doTearDown();
			GlobalEnvironmentFactory.disposeInstance();
			GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.removeDescriptors(org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI);
			GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.removeDescriptors(org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI);
			//		OCLstdlib.uninstall(); // should be able to persist
			//		if (projectMap != null) {
			//			projectMap.dispose();
			//			projectMap = null;
			//		}
		}
	}

	public static @NonNull List<Diagnostic> assertDiagnostics(@NonNull String prefix, @Nullable Resource resource, @NonNull List<Diagnostic> diagnostics, @NonNull String... messages) {
		Map<String, Integer> expected = new HashMap<String, Integer>();
		for (@NonNull String message : messages) {
			Integer count = expected.get(message);
			count = count == null ? 1 : count + 1;
			expected.put(message, count);
		}
		StringBuilder s1 = null;
		for (Diagnostic diagnostic : diagnostics) {
			String actual = diagnostic.getMessage();
			Integer expectedCount = expected.get(actual);
			if ((expectedCount == null) || (expectedCount <= 0)) {
				if (s1 == null) {
					s1 = new StringBuilder();
					s1.append("\nExtra errors");
					if (resource != null) {
						s1.append(" in '");
						s1.append(resource.getURI());
						s1.append("'");
					}
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
					if (resource != null) {
						s2.append(" in '");
						s2.append(resource.getURI());
						s2.append("'");
					}
				}
				s2.append("\n");
				s2.append(key);
			}
		}
		if (s1 == null) {
			if (s2 != null) {
				fail(prefix + s2.toString());
			}
		}
		else {
			if (s2 == null) {
				fail(prefix + s1.toString());
			}
			else {
				fail(prefix + s1.toString() + s2.toString());
			}
		}
		return diagnostics;
	}

	/* qvtd variant
	public static @NonNull List<Diagnostic> assertDiagnostics(@NonNull String prefix, @NonNull List<Diagnostic> diagnostics, String... messages) {
		Map<String, Integer> expected = new HashMap<String, Integer>();
		for (String message : messages) {
			Integer count = expected.get(message);
			count = count == null ? 1 : count + 1;
			expected.put(message, count);
		}
		StringBuilder s1 = null;
		for (Diagnostic diagnostic : diagnostics) {
			String actual = diagnostic.getMessage();
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
			if (s2 != null) {
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
		return diagnostics;
	} */

	public static void assertNoDiagnosticErrors(@NonNull String message, @NonNull XtextResource xtextResource) {
		List<Diagnostic> diagnostics = xtextResource.validateConcreteSyntax();
		if (diagnostics.size() > 0) {
			StringBuilder s = new StringBuilder();
			s.append(message);
			for (Diagnostic diagnostic : diagnostics) {
				s.append("\n");
				s.append(diagnostic.toString());
			}
			fail(s.toString());
		}
	}

	public static void assertNoResourceErrors(@NonNull String prefix, @NonNull Resource resource) {
		String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getErrors()), prefix, "\n\t");
		if (message != null)
			fail(message);
	}

	/* qvtd variant
	public static void assertNoResourceErrors(@NonNull String prefix, @NonNull Resource resource) {
		String message = PivotUtil.formatResourceDiagnostics(resource.getErrors(), prefix, "\n\t");
		if (message != null)
			fail(message);
	} */

	public static void assertNoUnresolvedProxies(@NonNull String message, @NonNull Resource resource) {
		Map<EObject, Collection<Setting>> unresolvedProxies = UnresolvedProxyCrossReferencer.find(resource);
		if (unresolvedProxies.size() > 0) {
			StringBuilder s = new StringBuilder();
			s.append(unresolvedProxies.size());
			s.append(" unresolved proxies in ");
			s.append(message);
			for (Map.Entry<EObject, Collection<Setting>> entry : unresolvedProxies.entrySet()) {
				s.append("\n");
				BasicEObjectImpl eTarget = (BasicEObjectImpl) entry.getKey();
				URI eProxyURI = eTarget.eProxyURI();
				s.append(eProxyURI);
				for (Setting setting : entry.getValue()) {
					s.append("\n\t");
					EObject eSource = setting.getEObject();
					EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
					try {
					//	Object eGet = eSource.eGet(eStructuralFeature);		// debugging
					//	if ((eStructuralFeature instanceof EReference) && (eGet instanceof EObject) && ((EObject)eGet).eIsProxy() && !((EReference)eStructuralFeature).isResolveProxies()) {
					//		EObject eObject = EcoreUtil.resolve((EObject)eGet, eSource);
					//	}
						s.append(eSource.toString());
					}
					catch (Exception e) {
						s.append(EcoreUtil.getURI(eSource).toString());
					}
				}
			}
			fail(s.toString());
		}
	}

/* qvtd variant
	public static void assertNoUnresolvedProxies(String message, Resource resource) {
		Map<EObject, Collection<Setting>> unresolvedProxies = UnresolvedProxyCrossReferencer.find(resource);
		if (unresolvedProxies.size() > 0) {
			StringBuilder s = new StringBuilder();
			s.append(unresolvedProxies.size());
			s.append(" unresolved proxies in '" + resource.getURI() + "' ");
			s.append(message);
			for (Map.Entry<EObject, Collection<Setting>> unresolvedProxy : unresolvedProxies.entrySet()) {
				s.append("\n");
				BasicEObjectImpl key = (BasicEObjectImpl) unresolvedProxy.getKey();
				s.append(key.eProxyURI());
				for (Setting setting : unresolvedProxy.getValue()) {
					s.append("\n\t");
					EObject eObject = setting.getEObject();
					s.append(eObject.toString());
				}
			}
			fail(s.toString());
		}
	} */

	/**
	 * Assert that no test is currently setup() and not yet tearDown().
	 */
	public static boolean assertTestIsNotSetup() {
		try {
			assert SETUP_TEST_NAME == null : "Test '" + SETUP_TEST_NAME + "' failed to tearDown";
		}
		finally {
			SETUP_TEST_NAME = null;		// Avoid gratuitous failures of subsequent tests
		}
		return true;
	}

	public static @NonNull List<Diagnostic> assertValidationDiagnostics(@NonNull String prefix, @NonNull Resource resource, @NonNull String @Nullable [] messages) {
		ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(resource);
		ValidationContext validationContext = new ValidationContext(validationRegistry);
		validationContext.put(EnvironmentFactory.class, PivotUtilInternal.getEnvironmentFactory(null));
		return assertValidationDiagnostics(prefix, resource, validationContext, messages);
	}

	/* qvtd variant
	public static @NonNull List<Diagnostic> assertValidationDiagnostics(@NonNull String prefix, @NonNull Resource resource, String... messages) {
		ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(resource);
		ValidationContext validationContext = new ValidationContext(validationRegistry);
		return assertValidationDiagnostics(prefix, resource, validationContext, messages);
	} */

	public static @NonNull List<Diagnostic> assertValidationDiagnostics(@NonNull String prefix, @NonNull Resource resource, @NonNull ValidationContext validationContext, @NonNull String @Nullable [] messages) {
		Executor savedExecutor = ThreadLocalExecutor.basicGetExecutor();
		Executor savedInterpretedExecutor = savedExecutor != null ? savedExecutor.basicGetInterpretedExecutor() : null;
		try {
			List<Diagnostic> diagnostics = new ArrayList<>();
			for (EObject eObject : resource.getContents()) {
				Diagnostic diagnostic = PivotDiagnostician.BasicDiagnosticWithRemove.validate(eObject, validationContext);
				diagnostics.addAll(diagnostic.getChildren());
			}
			return messages != null ? assertDiagnostics(prefix, resource, diagnostics, messages) : Collections.emptyList();
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

	/* qvtd variant
	public static @NonNull List<Diagnostic> assertValidationDiagnostics(@NonNull String prefix, @NonNull Resource resource, @NonNull ValidationContext validationContext, String... messages) {
		Executor savedExecutor = ThreadLocalExecutor.basicGetExecutor();
		Executor savedInterpretedExecutor = savedExecutor != null ? savedExecutor.basicGetInterpretedExecutor() : null;
		try {
			Diagnostician diagnostician = validationContext.getDiagnostician();
			List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
			for (EObject eObject : resource.getContents()) {
				Diagnostic diagnostic = diagnostician.validate(eObject, validationContext);		// FIXME inline 1 call level
				diagnostics.addAll(diagnostic.getChildren());
			}
			return messages != null ? assertDiagnostics(prefix, diagnostics, messages) : Collections.emptyList();
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
	} */

	public static @Nullable StandaloneProjectMap basicGetProjectMap() {
		EnvironmentFactory globalEnvironmentFactory = GlobalEnvironmentFactory.basicGetInstance();
		return globalEnvironmentFactory != null ? (StandaloneProjectMap)globalEnvironmentFactory.getProjectManager() : null; //projectMap;
	}

	/**
	 * Install a platform:/resource/project... mapping for all folders in
	 * $WORKSPACE_LOC/* if defined, or $user.dir/../* otherwise.
	 */
	public static void configurePlatformResources() {
		if (!eclipseIsRunning()) {
			String urlString = System.getProperty("WORKSPACE_LOC");
			File workspaceLoc;
			if (urlString != null) {
				workspaceLoc = new File(urlString);
			}
			else {
				workspaceLoc = new File(System.getProperty("user.dir")).getParentFile();
			}
			File[] files = workspaceLoc.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					String name = file.getName();
					EcorePlugin.getPlatformResourceMap().put(name, URI.createFileURI(file.toString() + "/"));
				}
			}
		}
	}

	public static boolean eclipseIsRunning() {
		try {
			Class<?> platformClass = Class.forName("org.eclipse.core.runtime.Platform");
			Method isRunningMethod = platformClass.getDeclaredMethod("isRunning");
			return Boolean.TRUE.equals(isRunningMethod.invoke(null));
		} catch (Exception e) {
		}
		return false;
	}

	protected static Value failOn(@NonNull String expression, @Nullable Throwable e) {
		if (e instanceof EvaluationException) {
			Throwable eCause = e.getCause();
			if (eCause != null) {
				return failOn(expression, eCause);
			}
			throw new Error(StringUtil.bind("Failed to evaluate ''{0}'' : {1}", expression, e.getMessage()), e);
		}
		else if (e instanceof EvaluationException) {
			throw new Error("Failed to parse or evaluate \"" + expression + "\"", e);
		}
		else {
			throw new Error("Failure for \"" + expression + "\"", e);
		}
	}

	/* qvtd variant
	protected static Value failOn(String expression, Throwable e) {
		if (e instanceof EvaluationException) {
			Throwable eCause = e.getCause();
			if (eCause != null) {
				return failOn(expression, eCause);
			}
			throw new Error("Failed to evaluate \"" + expression + "\"", e);
		}
		else if (e instanceof EvaluationException) {
			throw new Error("Failed to parse or evaluate \"" + expression + "\"", e);
		}
		else {
			throw new Error("Failure for \"" + expression + "\"", e);
		}
	} */

	public static @NonNull StandaloneProjectMap getProjectMap() {
		return (StandaloneProjectMap)ProjectManager.CLASS_PATH;
		//		StandaloneProjectMap projectMap2 = projectMap;
		//		if (projectMap2 == null) {
		//			projectMap = projectMap2 = EcorePlugin.IS_ECLIPSE_RUNNING ? new ProjectMap() : new StandaloneProjectMap();
		//		}
		//		return projectMap2;
	}

	public static @NonNull URI getTestModelURI(@NonNull String localFileName) {
		String testPlugInPrefix = PLUGIN_ID + "/";
		URI testPlugURI = EcorePlugin.IS_ECLIPSE_RUNNING ? URI.createPlatformPluginURI(testPlugInPrefix, true) : URI.createPlatformResourceURI(testPlugInPrefix, true);
		URI localURI = URI.createURI(localFileName.startsWith("/") ? localFileName.substring(1) : localFileName);
		return localURI.resolve(testPlugURI);
	}

	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		return (os != null) && os.startsWith("Windows");
	}

	public static void unloadResourceSet(@NonNull ResourceSet resourceSet) {
		StandaloneProjectMap projectMap = StandaloneProjectMap.findAdapter(resourceSet);
		if (projectMap != null) {
			projectMap.unload(resourceSet);
		}
		EnvironmentFactoryAdapter environmentFactoryAdapter = EnvironmentFactoryAdapter.find(resourceSet);
		if (environmentFactoryAdapter != null) {
			EnvironmentFactoryInternal environmentFactory = environmentFactoryAdapter.getEnvironmentFactory();
			ProjectManager projectManager = environmentFactory.getProjectManager();
			projectManager.unload(resourceSet);
		}
		EList<@NonNull Resource> resources = resourceSet.getResources();
		for (int i = 0; i < resources.size(); i++) {		// Avoid rare CME - see Bug 582925
			Resource resource = resources.get(i);
			resource.unload();
		}
		resourceSet.eAdapters().clear();
	}

	/* qvtd variant
	public static void unloadResourceSet(ResourceSet resourceSet) {
		for (Resource resource : resourceSet.getResources()) {
			resource.unload();
		}
		resourceSet.eAdapters().clear();
	} */

	protected @NonNull TestHelper testHelper;

	@Rule public TestName testName = new TestName();

	private GlobalStateMemento makeCopyOfGlobalState = null;

	protected AbstractPivotTestCase(@NonNull TestHelper testHelper) {
		this.testHelper = testHelper;
	}

	protected void gc(String pfx) throws InterruptedException {
	//	if (DEBUG_GC) {
	//		AbstractEnvironmentFactory.diagnoseLiveEnvironmentFactories(null);
	//	}
		// UMLValidateTest.testValidate_Bug417062_uml is most demanding
		for (int i = 0; i < 3; i++) {
		//	System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " gc-" + i + "-pre");
			System.gc();
		//	System.runFinalization();
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				TestUIUtil.wait(180);
			}
			else {
				Thread.sleep(80);				// ?? need to sleep long enough to let finalizers run
			}
		//	System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " gc-" + i + "-post");
			if (ThreadLocalExecutor.resetFinalizerReleases() == 0) {
				break;				// need to loop till finalizers stop de-referencing
			}
		}
		if (DEBUG_GC) {
			if (pfx != null) {
				System.out.println(pfx + " " + Thread.currentThread().getName());
			}
			AbstractEnvironmentFactory.diagnoseLiveEnvironmentFactories();
		}
	}

	@Override
	public @NonNull String getName() {
		return TestUtil.getName(getTestName());
	}

	protected @NonNull String getProjectName() {
		return getClass().getPackage().getName().replace('.', '/') + "/models";
	}

	public @NonNull String getTestName() {
		String name = super.getName();
		if (name != null) {
			return name;
		}
		String methodName = testName.getMethodName();
		return methodName != null ? methodName : "<unnamed>";
	}

	/* qvtd variant
	public @NonNull String getTestName() {
		return ClassUtil.nonNullState(super.getName());
	} */

	private static @Nullable String SETUP_TEST_NAME = null;		// Debug flag to detect enforcement of init before memento

	@Override
	protected void setUp() throws Exception {
		SETUP_TEST_NAME = getTestName();
		PivotUtilInternal.debugReset();
		GlobalEnvironmentFactory.resetSafeNavigationValidations();
		assert ThreadLocalExecutor.basicGetEnvironmentFactory() == null : "previous test failed to detach EnvironmentFactory.";
		ThreadLocalExecutor.reset();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUIUtil.closeIntro();			// Ensure that the default part is a stable PackageExplorerPart
			TestUIUtil.flushEvents();
		}

		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		PivotUtilInternal.DEBUG_DEPRECATIONS.setState(true);
		ASResourceImpl.CHECK_IMMUTABILITY.setState(true);
		if (DEBUG_GC) {
			XMLNamespacePackage.eINSTANCE.getClass();
			testHelper.doStartUp();					// Ensure all plugins are started before saving global state
			makeCopyOfGlobalState = new GlobalStateMemento();
		}

		if (!TEST_START.isActive()) {
			PivotUtil.contextLine = "-----Starting " + getClass().getSimpleName() + "." + getName() + "-----";
		}
		super.setUp();
		if (DEBUG_ID) {
			PivotUtilInternal.debugPrintln("-----Starting " + getClass().getSimpleName() + "." + getName() + "-----");
		}
		//	TracingOption.resetAll();
		ThreadLocalExecutor.reset();
		ASResourceImpl.CHECK_IMMUTABILITY.setState(true);
		TEST_START.println("-----Starting " + getClass().getSimpleName() + "." + getName() + "-----");
		startTime = System.nanoTime();
	}

	@Override
	protected void tearDown() throws Exception {
		try {
			//		if (DEBUG_ID) {
			//			PivotUtilInternal.debugPrintln("==> Done " + getName());
			//		}
			ThreadLocalExecutor.reset();
			DelegateInstaller.ExtendedEObjectValidator.reset();
			if (DEBUG_GC) {
				testHelper.doTearDown();
				makeCopyOfGlobalState.restoreGlobalState();
				makeCopyOfGlobalState = null;
				gc(null);
				//			MetamodelManagerResourceAdapter.INSTANCES.show();
			}
			if (DEBUG_ID) {
				PivotUtilInternal.debugPrintln("==> Finish " + getClass().getSimpleName() + "." + getName());
			}
			AbstractEnvironmentFactory.diagnoseLiveEnvironmentFactories();
			/**
			 * Reset any PivotEObject.target that may have reverted to proxies when a ProjectMap unloaded,
			 * and which might be resolved using the wrong strategy in another test.
			 */
			OCLstdlib oclstdlib = OCLstdlib.basicGetDefault();
			if (oclstdlib != null) {
				for (TreeIterator<EObject> tit = oclstdlib.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof PivotObjectImpl) {
						PivotObjectImpl asObject = (PivotObjectImpl)eObject;
						asObject.tearDownESObject();
					}
				}
			}
			SETUP_TEST_NAME = null;
			TestCaseAppender.INSTANCE.assertNotInstalled();
			super.tearDown();
		}
		finally {
			assert ThreadLocalExecutor.basicGetEnvironmentFactory() == null : getName() + " failed to detach EnvironmentFactory.";
		}
	}
}
