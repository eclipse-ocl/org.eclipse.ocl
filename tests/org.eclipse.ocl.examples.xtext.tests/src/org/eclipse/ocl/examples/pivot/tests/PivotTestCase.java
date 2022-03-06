/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
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
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UnresolvedProxyCrossReferencer;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsStandaloneSetup;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.delegate.ValidationDelegate;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.xtext.base.BaseStandaloneSetup;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.markup.MarkupStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecore.OCLinEcoreStandaloneSetup;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Rule;
import org.junit.rules.TestName;

import junit.framework.TestCase;

/**
 * Tests for OclAny operations.
 */
public class PivotTestCase extends TestCase
{
	public static final @NonNull String @NonNull [] NO_MESSAGES = new @NonNull String[] {};
	public static final @NonNull String @NonNull [] SUPPRESS_VALIDATION = new @NonNull String[] {"FIXME"};	// FIXME should not be needed
	public static final @NonNull String PLUGIN_ID = "org.eclipse.ocl.examples.xtext.tests";
	public static final @NonNull TracingOption TEST_START = new TracingOption(PLUGIN_ID, "test/start");
	//	private static StandaloneProjectMap projectMap = null;
	private static Writer testLog = null;

	/*
	 * The following may be tweaked to assist debugging.
	 */
	public static boolean DEBUG_GC = false;			// True performs an enthusuastic resource release and GC at the end of each test
	public static boolean DEBUG_ID = false;			// True prints the start and end of each test.
	{
	//	PivotUtilInternal.noDebug = false;
	//	DEBUG_GC = true;
	//	DEBUG_ID = true;
	//	AbstractEnvironmentFactory.liveEnvironmentFactories = new WeakHashMap<>();	// Prints the create/finalize of each EnvironmentFactory
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

	@Deprecated /* @deprecated provide resource argument */
	public static @NonNull List<Diagnostic> assertDiagnostics(@NonNull String prefix, @NonNull List<Diagnostic> diagnostics, @NonNull String... messages) {
		return assertDiagnostics(prefix, null, diagnostics, messages);
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
					s1.append("\nUnexpected errors");
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
	}

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

	public static void assertNoResourceErrorsOrWarnings(@NonNull String prefix, @NonNull Resource resource) {
		String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getErrors()), prefix, "\n\t");
		if (message != null)
			fail(message);
		message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getWarnings()), prefix, "\n\t");
		if (message != null)
			fail(message);
	}

	public static void assertNoUnresolvedProxies(@NonNull String message, @NonNull Resource resource) {
		Map<EObject, Collection<Setting>> unresolvedProxies = UnresolvedProxyCrossReferencer.find(resource);
		if (unresolvedProxies.size() > 0) {
			StringBuilder s = new StringBuilder();
			s.append(unresolvedProxies.size());
			s.append(" unresolved proxies in ");
			s.append(message);
			for (Map.Entry<EObject, Collection<Setting>> unresolvedProxy : unresolvedProxies.entrySet()) {
				s.append("\n");
				BasicEObjectImpl key = (BasicEObjectImpl) unresolvedProxy.getKey();
				s.append(key.eProxyURI());
				for (Setting setting : unresolvedProxy.getValue()) {
					s.append("\n\t");
					EObject eObject = setting.getEObject();
					try {
						s.append(eObject.toString());
					}
					catch (Exception e) {
						s.append(EcoreUtil.getURI(eObject).toString());
					}
				}
			}
			fail(s.toString());
		}
	}

	public static void assertNoValidationErrors(@NonNull String string, @NonNull Resource resource) {
		Executor savedExecutor = ThreadLocalExecutor.basicGetExecutor();
		Executor savedInterpretedExecutor = savedExecutor != null ? savedExecutor.basicGetInterpretedExecutor() : null;
		try {
			for (EObject eObject : resource.getContents()) {
				assertNoValidationErrorsInternal(string, ClassUtil.nonNullEMF(eObject));
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
			assertNoValidationErrorsInternal(string, eObject);
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

	protected static void assertNoValidationErrorsInternal(@NonNull String string, @NonNull EObject eObject) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		//		Resource eResource = ClassUtil.nonNullState(eObject.eResource());
		//		PivotUtilInternal.getMetamodelManager(eResource);	// FIXME oclIsKindOf fails because ExecutableStandardLibrary.getMetaclass is bad
		//		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject, validationContext);
		BasicDiagnostic diagnostics = PivotDiagnostician.BasicDiagnosticWithRemove.validate(eObject, validationContext);
		List<Diagnostic> children = diagnostics.getChildren();
		if (children.size() <= 0) {
			return;
		}
		StringBuilder s = new StringBuilder();
		s.append(children.size() + " validation errors");
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

	public static @NonNull List<Diagnostic> assertValidationDiagnostics(@NonNull String prefix, @NonNull Resource resource, @NonNull String @Nullable [] messages) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		return assertValidationDiagnostics(prefix, resource, validationContext, messages);
	}

	public static @NonNull List<Diagnostic> assertValidationDiagnostics(@NonNull String prefix, @NonNull Resource resource, Map<Object, Object> validationContext, @NonNull String @Nullable [] messages) {
		Executor savedExecutor = ThreadLocalExecutor.basicGetExecutor();
		Executor savedInterpretedExecutor = savedExecutor != null ? savedExecutor.basicGetInterpretedExecutor() : null;
		try {
			List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
			for (EObject eObject : resource.getContents()) {
				//			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject, validationContext);
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

	public static @Nullable StandaloneProjectMap basicGetProjectMap() {
		EnvironmentFactory globalEnvironmentFactory = GlobalEnvironmentFactory.basicGetInstance();
		return globalEnvironmentFactory != null ? (StandaloneProjectMap)globalEnvironmentFactory.getProjectManager() : null; //projectMap;
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
		environmentFactory.adapt(xtextResource);
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Loading Xtext", xtextResource);
		Resource asResource = cs2as(xtextResource, null);
		Resource ecoreResource = as2ecore(environmentFactory, asResource, ecoreURI, NO_MESSAGES);
		return ecoreResource;
	}

	public static @NonNull Resource cs2as(@NonNull OCL ocl, @NonNull String testDocument) throws IOException {
		InputStream inputStream = new URIConverter.ReadableInputStream(testDocument, "UTF-8");
		URI xtextURI = URI.createURI("test.oclinecore");
		ResourceSet resourceSet = new ResourceSetImpl();
		EssentialOCLCSResource xtextResource = ClassUtil.nonNullState((EssentialOCLCSResource) resourceSet.createResource(xtextURI, null));
		ocl.getEnvironmentFactory().adapt(xtextResource);
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Loading Xtext", xtextResource);
		Resource asResource = cs2as(xtextResource, null);
		return asResource;
	}

	public static @NonNull Resource cs2as(@NonNull CSResource xtextResource, @Nullable URI pivotURI) throws IOException {
		ASResource asResource = xtextResource.getASResource();
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
	 * Perform the appropriate initialization to support Complete OCL parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 * @deprecated Use TestUtil
	 */
	@Deprecated
	public static void doCompleteOCLSetup() {
		TestUtil.doCompleteOCLSetup();
	}

	/**
	 * Perform the appropriate initialization to support Essential OCL parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 * @deprecated Use TestUtil
	 */
	@Deprecated
	public static void doEssentialOCLSetup() {
		TestUtil.doEssentialOCLSetup();
	}

	/**
	 * Perform the appropriate initialization to support OCLinEcore parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 * @deprecated Use TestUtil
	 */
	@Deprecated
	public static void doOCLinEcoreSetup() {
		TestUtil.doOCLinEcoreSetup();
	}

	/**
	 * Perform the appropriate initialization to support OCLstdlib parsing and editing using Xtext.
	 * NB. This must be called before setUp() creates a GlobalStateMemento if the aggressive DEBUG_GC
	 * garbage collection is enabled.
	 * @deprecated Use TestUtil
	 */
	@Deprecated
	public static void doOCLstdlibSetup() {
		TestUtil.doOCLstdlibSetup();
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

	public static @NonNull StandaloneProjectMap getProjectMap() {
		return (StandaloneProjectMap)ProjectManager.CLASS_PATH;
		//		StandaloneProjectMap projectMap2 = projectMap;
		//		if (projectMap2 == null) {
		//			projectMap = projectMap2 = EcorePlugin.IS_ECLIPSE_RUNNING ? new ProjectMap() : new StandaloneProjectMap();
		//		}
		//		return projectMap2;
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
			EnvironmentFactoryInternal environmentFactory = environmentFactoryAdapter.getMetamodelManager().getEnvironmentFactory();
			ProjectManager projectManager = environmentFactory.getProjectManager();
			projectManager.unload(resourceSet);
		}
		for (Resource resource : resourceSet.getResources()) {
			resource.unload();
		}
		resourceSet.eAdapters().clear();
	}

	protected static abstract class TestRunnable implements Runnable
	{
		static int count = 0;

		String name = null;
		Throwable throwable = null;

		protected TestRunnable() {
			name = "test" + count++;
		}

		@Override
		public void run() {
			try {
				runWithThrowable();
			}
			catch (Throwable t) {
				throwable = t;
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
				if (DEBUG_GC) {
					System.gc();
					System.runFinalization();
				}
				if (testRunnable.throwable != null) {
					throw testRunnable.throwable;
				}
			}
		}
	}

	@Deprecated /* @deprecated not used */
	protected @NonNull File getProjectFile() {
		String projectName = getProjectName();
		URL projectURL = getTestResource(projectName);
		assertNotNull(projectURL);
		return new File(projectURL.getFile());
	}

	@Deprecated /* @deprecated use getTestModelURI to facilitate legacy inherited testing */
	protected @NonNull URI getProjectFileURI(@NonNull String referenceName) {
		throw new UnsupportedOperationException();
		//		File projectFile = getProjectFile();
		//		return ClassUtil.nonNullState(URI.createFileURI(projectFile.toString() + "/" + referenceName));
	}

	protected @NonNull String getProjectName() {
		return getClass().getPackage().getName().replace('.', '/') + "/models";
	}

	@Rule public TestName testName = new TestName();

	@Override
	public @NonNull String getName() {
		return TestUtil.getName(getTestName());
	}

	public @NonNull String getTestName() {
		String name = super.getName();
		if (name != null) {
			return name;
		}
		String methodName = testName.getMethodName();
		return methodName != null ? methodName : "<unnamed>";
	}

	public static @NonNull URI getTestModelURI(@NonNull String localFileName) {
		String testPlugInPrefix = PLUGIN_ID + "/";
		URI testPlugURI = EcorePlugin.IS_ECLIPSE_RUNNING ? URI.createPlatformPluginURI(testPlugInPrefix, true) : URI.createPlatformResourceURI(testPlugInPrefix, true);
		URI localURI = URI.createURI(localFileName.startsWith("/") ? localFileName.substring(1) : localFileName);
		return localURI.resolve(testPlugURI);
	}

	@Deprecated /* @deprecated not used */
	protected @NonNull URL getTestResource(@NonNull String resourceName) {
		URL projectURL = getClass().getClassLoader().getResource(resourceName);
		try {
			if ((projectURL != null) && Platform.isRunning()) {
				try {
					projectURL = FileLocator.resolve(projectURL);
				} catch (IOException e) {
					TestCase.fail(e.getMessage());
					assert false;;
				}
			}
		}
		catch (Throwable e) {}
		return ClassUtil.nonNullState(projectURL);
	}

	private GlobalStateMemento makeCopyOfGlobalState = null;

	public void resetRegistries() {
		final Object object = ValidationDelegate.Factory.Registry.INSTANCE.get(OCLConstants.OCL_DELEGATE_URI);
		if (object instanceof org.eclipse.ocl.common.internal.delegate.OCLValidationDelegateMapping) {
			((org.eclipse.ocl.common.internal.delegate.OCLValidationDelegateMapping)object).reset();
		}
	}

	@Override
	protected void setUp() throws Exception {
		PivotUtilInternal.debugReset();
		GlobalEnvironmentFactory.resetSafeNavigationValidations();
		ThreadLocalExecutor.reset();
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		ASResourceImpl.CHECK_IMMUTABILITY.setState(true);
		if (DEBUG_GC) {
			XMLNamespacePackage.eINSTANCE.getClass();
			makeCopyOfGlobalState = new GlobalStateMemento();
		}
		super.setUp();
		if (DEBUG_ID) {
			PivotUtilInternal.debugPrintln("-----Starting " + getClass().getSimpleName() + "." + getName() + "-----");
		}
		TEST_START.println("-----Starting " + getClass().getSimpleName() + "." + getName() + "-----");
		EcorePackage.eINSTANCE.getClass();						// Workaround Bug 425841
		//		EPackage.Registry.INSTANCE.put(UML302UMLResource.STANDARD_PROFILE_NS_URI, L2Package.eINSTANCE);
	}

	@Override
	protected void tearDown() throws Exception {
		//		if (DEBUG_ID) {
		//			PivotUtilInternal.debugPrintln("==> Done " + getName());
		//		}
		ThreadLocalExecutor.reset();
		if (DEBUG_GC) {
			uninstall();
			makeCopyOfGlobalState.restoreGlobalState();
			makeCopyOfGlobalState = null;
			System.gc();
			System.runFinalization();
			//			MetamodelManagerResourceAdapter.INSTANCES.show();
		}
		if (DEBUG_ID) {
			PivotUtilInternal.debugPrintln("==> Finish " + getClass().getSimpleName() + "." + getName());
		}
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
					EObject eTarget = asObject.getESObject();
					if ((eTarget != null) && eTarget.eIsProxy()) {
						asObject.setESObject(null);
					}
				}
			}
		}
		super.tearDown();
	}

	protected void uninstall() {
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
}
