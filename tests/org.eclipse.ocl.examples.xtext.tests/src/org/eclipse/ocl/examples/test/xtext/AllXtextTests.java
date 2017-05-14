/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.test.xtext;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.tests.DelegatesTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateBooleanOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateClassifierOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateCollectionOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateConstructsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateMapOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateModelOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateNameVisibilityTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateNumericOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateOclAnyOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateStringOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateTupleOperationsTest;
import org.eclipse.ocl.examples.pivot.tests.EvaluateUMLTest;
import org.eclipse.ocl.examples.pivot.tests.InheritanceTests;
import org.eclipse.ocl.examples.pivot.tests.IteratorsTest;
import org.eclipse.ocl.examples.pivot.tests.LeakTests;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCase;
import org.eclipse.ocl.examples.pivot.tests.PrettyPrinterTest;
import org.eclipse.ocl.examples.pivot.tests.StereotypesTest;
import org.eclipse.ocl.examples.pivot.tests.UMLValidateTest;
import org.eclipse.ocl.examples.pivot.tests.ValidateTests;
import org.eclipse.ocl.examples.test.ecore.ProjectMapTest;
import org.eclipse.ocl.examples.test.label.PluginLabelTests;
import org.eclipse.ocl.examples.test.label.StandaloneLabelTests;
import org.eclipse.ocl.examples.test.standalone.StandaloneExecutionTests;
import org.eclipse.ocl.examples.test.standalone.StandaloneParserTests;
import org.eclipse.ocl.pivot.internal.iterators.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.iterators.LazyIterable;
import org.eclipse.ocl.pivot.internal.values.CollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Tests for the Xtext editor support.
 */
public class AllXtextTests
extends TestCase {

	public AllXtextTests() {
		super("");
	}

	public static Test suite() {
		//		if (System.getProperty("standalone") != null) {
		// running tests stand-alone:  must set up the environment registry
		//			Environment.Registry.INSTANCE.registerEnvironment(
		//					EcoreEnvironmentFactory.INSTANCE.createEnvironment());
		//		}

		String testSuiteName = System.getProperty("testSuiteName", "Xtext Editor Support");
		String testLogFile = System.getProperty("testLogFile", null);
		if (testLogFile != null) {
			PivotTestCase.createTestLog(new File(testLogFile));
		}
		TestSuite result = new TestSuite(testSuiteName) {

			@Override
			public void run(TestResult result) {
				Map<@NonNull Class<?>, @NonNull Integer> collectionClass2count = CollectionValueImpl.ExtensionImpl.collectionClass2count = new HashMap<>();
				Map<@NonNull Class<?>, @NonNull Integer> collectionClass2lazyList = LazyIterable.debugCollectionClass2lazyList = new HashMap<>();
				Map<@NonNull Class<?>, @NonNull Integer> collectionClass2lazyMap = LazyIterable.debugCollectionClass2lazyMap = new HashMap<>();
				Map<@NonNull Class<?>, @NonNull Integer> collectionClass2lazy = LazyCollectionValueImpl.debugCollectionClass2lazy = new HashMap<>();
				Map<@NonNull Class<?>, @NonNull Integer> collectionClass2cached = LazyCollectionValueImpl.debugCollectionClass2cached = new HashMap<>();
				Map<@NonNull Class<?>, @NonNull Integer> collectionClass2reiterated = LazyCollectionValueImpl.debugCollectionClass2reiterated = new HashMap<>();
				super.run(result);
				int iteratorCounts = 0;
				int nonIteratorCounts = 0;
				int lazyCounts = 0;
				int cachedCounts = 0;
				int reiteratedCounts = 0;
				Set<@NonNull Class<?>> keySet = new HashSet<>();
				keySet.addAll(collectionClass2count.keySet());
				keySet.addAll(collectionClass2lazyList.keySet());
				keySet.addAll(collectionClass2lazyMap.keySet());
				keySet.addAll(collectionClass2lazy.keySet());
				keySet.addAll(collectionClass2cached.keySet());
				keySet.addAll(collectionClass2reiterated.keySet());
				List<@NonNull Class<?>> keyList = new ArrayList<>(keySet);
				Collections.sort(keyList, new Comparator<@NonNull Class<?>>()
				{
					@Override
					public int compare(@NonNull Class<?> o1, @NonNull Class<?> o2) {
						boolean h1 = LazyCollectionValue.class.isAssignableFrom(o1);
						boolean h2 = LazyCollectionValue.class.isAssignableFrom(o2);
						if (h1 != h2) {
							return h1 ? 1 : -1;
						}
						return o1.getName().compareTo(o2.getName());
					}
				});
				for (@NonNull Class<?> collectionClass : keyList) {
					Integer count = collectionClass2count.get(collectionClass);
					Integer lazyListCount = collectionClass2lazyList.get(collectionClass);
					Integer lazyMapCount = collectionClass2lazyMap.get(collectionClass);
					Integer lazyCount = collectionClass2lazy.get(collectionClass);
					Integer cachedCount = collectionClass2cached.get(collectionClass);
					Integer reiteratedCount = collectionClass2reiterated.get(collectionClass);
					System.out.println(collectionClass.getName() + " : " + count + " : " + lazyListCount + " : " + lazyMapCount + "   " + lazyCount + " : " + cachedCount + " : " + reiteratedCount);
					if (count != null) {
						if (LazyCollectionValue.class.isAssignableFrom(collectionClass)) {
							iteratorCounts += count;
						}
						else {
							nonIteratorCounts += count;
						}
					}
					if (lazyCount != null) {
						lazyCounts += lazyCount;
					}
					if (cachedCount != null) {
						cachedCounts += cachedCount;
					}
					if (reiteratedCount != null) {
						reiteratedCounts += reiteratedCount;
					}
				}
				System.out.println(">= " + LazyCollectionValue.class.getName() + " : " + iteratorCounts);
				System.out.println("!>= " + LazyCollectionValue.class.getName() + " : " + nonIteratorCounts);
				System.out.println("all " + CollectionValue.class.getName() + " : " + (iteratorCounts+nonIteratorCounts) + "   " + lazyCounts + " : " + cachedCounts + " : " + reiteratedCounts);
			}

		};
		result.addTestSuite(MonikerTests.class);
		result.addTestSuite(PivotTests.class);
		result.addTestSuite(OCLstdlibTests.class);
		result.addTestSuite(PrecedenceTests.class);
		result.addTestSuite(EvaluateBooleanOperationsTest.class);
		result.addTestSuite(EvaluateClassifierOperationsTest.class);
		result.addTestSuite(EvaluateCollectionOperationsTest.class);
		result.addTestSuite(EvaluateConstructsTest.class);
		result.addTestSuite(EvaluateMapOperationsTest.class);
		result.addTestSuite(EvaluateModelOperationsTest.class);
		result.addTestSuite(EvaluateNameVisibilityTest.class);
		result.addTestSuite(EvaluateNumericOperationsTest.class);
		result.addTestSuite(EvaluateOclAnyOperationsTest.class);
		result.addTestSuite(EvaluateStringOperationsTest.class);
		result.addTestSuite(EvaluateTupleOperationsTest.class);
		result.addTestSuite(EvaluateUMLTest.class);
		result.addTestSuite(IteratorsTest.class);
		result.addTestSuite(FlowAnalysisTests.class);
		result.addTestSuite(DelegatesTest.class);
		result.addTestSuite(ErrorTests.class);
		result.addTestSuite(ImportTests.class);
		result.addTestSuite(LeakTests.class);
		result.addTestSuite(UMLValidateTest.class);
		ResourceSet resourceSet = new ResourceSetImpl();
		UMLResourcesUtil.init(resourceSet);
		LoadTests.getProjectMap().initializeResourceSet(resourceSet);
		if (resourceSet.getURIConverter().exists(URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/", true), null)) {
			result.addTestSuite(UML25LoadTests.class);
		}
		else {
			result.addTestSuite(LoadTests.class);
		}
		result.addTestSuite(PrettyPrinterTest.class);
		result.addTestSuite(ProjectMapTest.class);
		result.addTestSuite(RegistryTests.class);
		result.addTestSuite(SerializeTests.class);
		result.addTestSuite(RoundTripTests.class);
		result.addTestSuite(StereotypesTest.class);
		result.addTestSuite(EditTests.class);
		result.addTestSuite(InheritanceTests.class);
		result.addTestSuite(MarkupTests.class);
		result.addTestSuite(ValidateTests.class);
		result.addTestSuite(PivotDocumentationExamples.class);
		result.addTestSuite(OCLinEcoreTutorialExamples.class);
		result.addTestSuite(UsageTests.class);
		result.addTestSuite(StandaloneExecutionTests.class);
		result.addTestSuite(StandaloneParserTests.class);
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			result.addTestSuite(CompletionProposalTests.class);
			result.addTestSuite(ConsoleTests.class);
			result.addTestSuite(EcoreConsoleTests.class);
			result.addTestSuite(UMLConsoleTests.class);
			result.addTestSuite(EditorTests.class);
			result.addTestSuite(FileNewWizardTest.class);
			result.addTestSuite(PluginLabelTests.class);
			//			result.addTestSuite(DebuggerTests.class);
		}
		else {
			result.addTestSuite(StandaloneLabelTests.class);
			result.addTestSuite(GrammarTests.class);		// *.xtextbin fail to load in EClipse, but we don't need to test twice anyway.
		}
		return result;
	}

	public Object run(Object args)
			throws Exception {
		TestRunner.run(suite());
		PivotTestCase.closeTestLog();
		return Arrays
				.asList(new String[] {"Please see raw test suite output for details."});
	}
}
