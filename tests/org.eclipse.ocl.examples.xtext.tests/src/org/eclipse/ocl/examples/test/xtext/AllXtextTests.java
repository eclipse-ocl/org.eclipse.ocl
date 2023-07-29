/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.test.xtext;

import java.io.File;
import java.util.Arrays;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;

import junit.framework.Test;
import junit.framework.TestCase;
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
		TestSuite result = new TestSuite(testSuiteName);
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
		UML2AS.initializeUML(resourceSet);
		LoadTests.getProjectMap().initializeResourceSet(resourceSet);
		if (resourceSet.getURIConverter().exists(URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/", true), null)) {
			result.addTestSuite(UML25LoadTests.class);
		}
		else {
			result.addTestSuite(LoadTests.class);
		}
		result.addTestSuite(PrettyPrinterTest.class);
		result.addTestSuite(TestPrettyPrinter.class);
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
		result.addTestSuite(GrammarRuleVectorTests.class);
		result.addTestSuite(SerializationBuilderTests.class);
		result.addTestSuite(IdiomsLoadTests.class);
		result.addTestSuite(StringUtilTests.class);
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			result.addTestSuite(CompletionProposalTests.class);
			result.addTestSuite(ConsoleTests.class);
			result.addTestSuite(EcoreConsoleTests.class);
			result.addTestSuite(UMLConsoleTests.class);
			result.addTestSuite(EditorTests.class);
			result.addTestSuite(FileNewWizardTest.class);
			result.addTestSuite(PluginLabelTests.class);
			result.addTestSuite(DebuggerTests.class);
			result.addTestSuite(OCLBuilderTests.class);
		}
		else {
			result.addTestSuite(StandaloneLabelTests.class);
		}
		String targetRelease = System.getProperty("targetRelease");
		if (targetRelease == null) { // See Bug 527458 - GrammarTests are expected to fail when Xtext.ecore changes
			result.addTestSuite(GrammarTests.class);
		}
		// if (EMFPlugin.IS_ECLIPSE_RUNNING) {
		// 	result.addTestSuite(FinalTests.class);
		// }
		return result;
	}

	public Object run(Object args) throws Exception {
		TestRunner.run(suite());
		//		System.out.println("End of test");
		BaseUIActivator.cancelMultiValidationJob();
		//		System.out.println("MultiValidationJob cancelled");
		TestUIUtil.wait(1000);
		//		System.out.println("Closing test log");
		PivotTestCase.closeTestLog();
		return Arrays.asList(new String[] {"Please see raw test suite output for details."});
	}
}
