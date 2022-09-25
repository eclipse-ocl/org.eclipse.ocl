/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.standalone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Appender;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.export.HTMLExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.ModelExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.TextExporter;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCase;
import org.eclipse.ocl.examples.standalone.StandaloneApplication;
import org.eclipse.ocl.examples.standalone.StandaloneCommand;
import org.eclipse.ocl.examples.standalone.StandaloneResponse;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;
import org.eclipse.ocl.examples.validity.locator.AbstractPivotConstraintLocator;
import org.eclipse.ocl.examples.xtext.tests.TestCaseLogger;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestProject;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.validation.PivotEAnnotationValidator;
import org.junit.Test;

public class StandaloneExecutionTests extends StandaloneTestCase
{
	/**
	 * A StandaloneTestApplication can suppress console output to avoid cluttering the test log file.
	 */
	public static final class StandaloneTestApplication extends StandaloneApplication
	{
		@Override
		public boolean isTest() { return true; }
	}

	private static final class NullAppendable implements Appendable
	{
		public static final @NonNull Appendable INSTANCE = new NullAppendable();

		@Override
		public Appendable append(CharSequence csq) throws IOException {
			return null;
		}

		@Override
		public Appendable append(CharSequence csq, int start, int end) throws IOException {
			return null;
		}

		@Override
		public Appendable append(char c) throws IOException {
			return null;
		}
	}

	protected static int EXTRA_EAnnotationValidator_SUCCESSES = PivotEAnnotationValidator.getEAnnotationValidatorRegistry()  != null? 3 : 0;

	protected static void assertNoLogFile(@NonNull String logFileName) {
		File file = new File(logFileName);
		assertFalse(file.exists());
	}

	private @NonNull String checkExecuteResultFile(@NonNull String logFileName, @NonNull String @Nullable  [] inclusions, @NonNull String @Nullable [] exclusions) throws IOException {
		File file = new File(logFileName);
		assertTrue(file.exists());
		StringBuilder s = new StringBuilder();
		BufferedReader r = new BufferedReader(new FileReader(file));
		char buf[] = new char[4096];
		for (int len; (len = r.read(buf)) > 0; ) {
			s.append(buf, 0, len);
		}
		r.close();
		String contents = s.toString();
		checkExecuteResults(contents, inclusions, exclusions);
		return contents;
	}

	public String checkExecuteResults(@NonNull String contents, @NonNull String @Nullable [] inclusions, @NonNull String @Nullable [] exclusions) {
		if (inclusions != null) {
			for (String inclusion : inclusions) {
				assertTrue("Expected to find: " + inclusion, contents.contains(inclusion));
			}
		}
		if (exclusions != null) {
			for (String exclusion : exclusions) {
				assertFalse("Did not expect to find: " + exclusion, contents.contains(exclusion));
			}
		}
		return contents;
	}

	private @NonNull List<@NonNull String> checkValidateLogFile(@NonNull String logFileName, int oks, int infos, int warnings, int errors, int fails) throws IOException {
		File file = new File(logFileName);
		assertTrue(file.exists());
		List<@NonNull String> lines = new ArrayList<>();
		BufferedReader r = new BufferedReader(new FileReader(file));
		int metricsLine = 0;
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			if ("==== METRICS ====".equals(line)) {
				metricsLine = lines.size();
			}
			lines.add(line);
		}
		r.close();
		assertEquals("- Number of Success: " + oks, lines.get(metricsLine + 2));
		assertEquals("- Number of Infos: " + infos, lines.get(metricsLine + 3));
		assertEquals("- Number of Warnings: " + warnings, lines.get(metricsLine + 4));
		assertEquals("- Number of Errors: " + errors, lines.get(metricsLine + 5));
		assertEquals("- Number of Failures: " + fails, lines.get(metricsLine + 6));
		return lines;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AbstractPivotConstraintLocator.initialize();
	}

	@Test
	public void testStandaloneExecution_execute_model_echo_all() throws Exception {
		String logFileName = getXMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"2+3\"", "result=\"5\"", "integerSymbol=\"2\"", "integerSymbol=\"5\""}, new @NonNull String[] {"<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_echo_echoes() throws Exception {
		String logFileName = getXMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideResult",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"2+3\"", "integerSymbol=\"2\""}, new @NonNull String[] {"result=\"5\"", "integerSymbol=\"5\"", "<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_echo_none() throws Exception {
		String logFileName = getXMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideQuery",
			"-hideResult",
			"-hideASQuery",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, null, new @NonNull String[] {"query=\"2+3\"", "result=\"5\"", "integerSymbol=\"2\"", "integerSymbol=\"5\"", "<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_echo_models() throws Exception {
		String logFileName = getXMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideQuery",
			"-hideResult",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"integerSymbol=\"2\"", "integerSymbol=\"5\""}, new @NonNull String[] {"query=\"2+3\"", "result=\"5\"", "<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_echo_texts() throws Exception {
		String logFileName = getXMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideASQuery",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"2+3\"", "result=\"5\""}, new @NonNull String[] {"integerSymbol=\"2\"", "integerSymbol=\"5\"", "<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_semantic_error() throws Exception {
		String logFileName = getXMLLogFileName();
		String query = "Sequence{1,2}=>forAll(i:String | true)";
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", query,
			"-hideResult",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"" + query + "\"", "<errors>"}, new @NonNull String[] {"integerSymbol=\"2\"", "integerSymbol=\"5\""});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_syntax_error() throws Exception {
		String logFileName = getXMLLogFileName();
		String query = "2&3";
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", query,
			"-hideResult",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"2&amp;3\"", "<errors>"}, new @NonNull String[] {"integerSymbol=\"2\"", "integerSymbol=\"5\""});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_self_platform() throws Exception {
		String logFileName = getXMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "self",
			"-self", "platform:/resource/org.eclipse.ocl.pivot/model/Pivot.ecore#//Boolean", //"platform:/resource/org.eclipse.ocl.pivot/model/Pivot.ecore#T-pivot-Boolean",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"self\"", "referredElement=\"&_0;#T-pivot-Boolean\""}, new @NonNull String[] {"<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_self_file_absolute() throws Exception {
		try {
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			URI inputURI = URI.createPlatformPluginURI(PivotTestCase.PLUGIN_ID + "/models/standalone/EcoreTestFile.ecore", true);
			InputStream inputStream = standaloneApplication.getURIConverter().createInputStream(inputURI);
			assert inputStream != null;
			TestFile testFile = getTestProject().getOutputFile("EcoreTestFile.ecore", inputStream);
			String logFileName = getXMLLogFileName();
			String testPath = testFile.getFileString() + "#//BadClass";
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
				"-query", "self",
				"-self", testPath,
				"-output", logFileName,
				"-exporter", "model"};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.OK, applicationResponse);
			assertTrue(standaloneApplication.exists(logFileName));
			checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"self\"", "referredElement=\"&_0;#//BadClass\""}, new @NonNull String[] {"<errors>"});
			standaloneApplication.stop();
		}
		catch (Exception e) {				// As of Tycho 2.1.0 the stack trace is missing and the residue confusing.
			e.printStackTrace();
			throw e;
		}
	}

	@Test
public void testStandaloneExecution_execute_model_self_relative() throws Exception {
		try {
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			URI inputURI = URI.createPlatformPluginURI(PivotTestCase.PLUGIN_ID + "/models/standalone/EcoreTestFile.ecore", true);
			InputStream inputStream = standaloneApplication.getURIConverter().createInputStream(inputURI);
			assert inputStream != null;
			TestProject testProject = getTestProject();
			testProject.getOutputFile("EcoreTestFile.ecore", inputStream);
			String logFileName = getXMLLogFileName();
			String testPath;
			if (CGUtil.isMavenSurefire()) {
				testPath = "../../../" + PivotTestCase.PLUGIN_ID + "/models/standalone/EcoreTestFile.ecore#//BadClass";
			}
			else if (CGUtil.isTychoSurefire()) {
				testPath = "../" + PivotTestCase.PLUGIN_ID + "/models/standalone/EcoreTestFile.ecore#//BadClass";
			}
			else {
				testPath = testProject.getName() + "/EcoreTestFile.ecore#//BadClass";
			}
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
				"-query", "self",
				"-self", testPath,
				"-output", logFileName,
				"-exporter", "model"};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.OK, applicationResponse);
			assertTrue(standaloneApplication.exists(logFileName));
			checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"self\"", "referredElement=\"&_0;#//BadClass\""}, new @NonNull String[] {"<errors>"});
			standaloneApplication.stop();
		}
		catch (Exception e) {				// As of Tycho 2.1.0 the stack trace is missing and the residue confusing.
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testStandaloneExecution_execute_model_self_closure() throws Exception {
		String logFileName = getXMLLogFileName();
		String query = "self->closure(superClasses)";
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", query,
			"-self", "platform:/resource/org.eclipse.ocl.pivot/model-gen/Pivot.oclas#/0/pivot/Class",			// pivot::Class
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"query=\"" + query + "\"",
			"referredType=\"pivot:Class &_0;#zfDWR\"",
			"referredType=\"pivot:AnyType &_0;#jbMkR\"",
			"referredType=\"pivot:Class &_2;#t5rpa\"",
			"referredType=\"pivot:Class &_2;#rrwsa\"",
			"referredType=\"pivot:Class &_2;#MzHoc\"",
			"referredType=\"pivot:Class &_2;#5r5y9\"",
			"referredType=\"pivot:Class &_2;#pDcbe\"",
			"referredType=\"pivot:Class &_2;#0kVqz\""},
			new @NonNull String[] {"<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_self_ecore_closure() throws Exception {
		String logFileName = getXMLLogFileName();
		String query = "self->closure(eSuperTypes)";
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", query,
			"-self", "platform:/resource/org.eclipse.ocl.pivot/model/Pivot.ecore#T-pivot-Class",
			"-hideASTypes",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName,
			new @NonNull String[] {"query=\""+ query + "\"",
				"referredElement=\"&_0;#T-pivot-Class\"",
				"referredElement=\"&_0;#T-pivot-Element\"",
				"referredElement=\"&_0;#T-pivot-NamedElement\"",
				"referredElement=\"&_0;#T-pivot-Namespace\"",
				"referredElement=\"&_0;#T-pivot-TemplateableElement\"",
				"referredElement=\"&_0;#T-pivot-Type\""},
			new @NonNull String[] {"<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_model_self_oclType_closure() throws Exception {
		String logFileName = getXMLLogFileName();
		String query = "self.oclType()->closure(superClasses)";
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", query,
			"-self", "platform:/resource/org.eclipse.ocl.pivot/model/Pivot.ecore#T-pivot-Class",
			"-hideASTypes",
			"-output", logFileName,
			"-exporter", "model"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName,
			new @NonNull String[] {"query=\""+ query + "\"",
				"xmi:id=\"GzvN6\"",
				"xmi:id=\"HzvN6\"",
				"xmi:id=\"IzvN6\"",
				"xmi:id=\"JzvN6\"",
				"xmi:id=\"KzvN6\"",
				"xmi:id=\"LzvN6\""},
			new @NonNull String[] {"<errors>"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_text() throws Exception {
		String logFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideQuery",
			"-output", logFileName,
			"-exporter", "text"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"5"}, new @NonNull String[] {"Query", "Result", "Error"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_text_syntax_error() throws Exception {
		String logFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2&3",
			"-output", logFileName,
			"-exporter", "text"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"Query : 2&3", "Result:", "Error :"}, null);
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_text_semantic_error() throws Exception {
		String logFileName = getTextLogFileName();
		String query = "Set{1,2}->forAll(i:String | true)";
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", query,
			"-output", logFileName,
			"-exporter", "text"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"Query : " + query, "Result:", "Error :"}, null);
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_text_echo_texts() throws Exception {
		String logFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideASQuery",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "text"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"Query : 2+3", "Result: 5"}, new @NonNull String[] {"Error"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_none_echo_texts() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideASQuery",
			"-hideASResult"};
		StandaloneApplication standaloneApplication = new StandaloneTestApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		String consoleText = standaloneApplication.getConsoleText();
		assert consoleText != null;
		checkExecuteResults(consoleText, new @NonNull String[] {"5"}, new @NonNull String[] {"Query", "Result", "Error"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_text_echo_text() throws Exception {
		String logFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideResult",
			"-hideASQuery",
			"-hideASResult",
			"-output", logFileName,
			"-exporter", "text"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"Query : 2+3"}, new @NonNull String[] {"Result", "Error"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_execute_text_show_text() throws Exception {
		String logFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"execute",
			"-query", "2+3",
			"-hideQuery",
			"-output", logFileName,
			"-exporter", "text"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		checkExecuteResultFile(logFileName, new @NonNull String[] {"5"}, new @NonNull String[] {"Query", "Result", "Error"});
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_help() throws Exception {
		StringWriter outputStream = new StringWriter();
		Appendable savedDefaultOutputStream = StandaloneCommand.setDefaultOutputStream(outputStream);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		String s = outputStream.toString();
		assert s.contains(StandaloneMessages.Standalone_Help);
		assert s.contains(StandaloneMessages.HelpCommand_Help);
		assert s.contains(StandaloneMessages.ValidateCommand_Help);
		StandaloneCommand.setDefaultOutputStream(savedDefaultOutputStream);
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_mandatoryArgumentsOnly() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI)};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertNoLogFile(getTextLogFileName());
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_missingOutputArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output"
			};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.FAIL, applicationResponse);
			assertNoLogFile(getTextLogFileName());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-output"));
		} finally {
			standaloneApplication.stop();
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneExecution_validate_missingExporterArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-exporter"
			};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.FAIL, applicationResponse);
			assertNoLogFile(getTextLogFileName());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-exporter"));
		} finally {
			standaloneApplication.stop();
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneExecution_validate_missingUsingArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-using"
			};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.FAIL, applicationResponse);
			assertNoLogFile(getTextLogFileName());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-using"));
		} finally {
			standaloneApplication.stop();
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneExecution_validate_textExportedFile() throws Exception {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		checkValidateLogFile(textLogFileName, 36+EXTRA_EAnnotationValidator_SUCCESSES, 1, 1, 1, 0);
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_modelExportedFile() throws Exception {
		String modelLogFileName = getLogFileName(ModelExporter.INSTANCE);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", modelLogFileName,
			//			"-using", "ocl",
			"-exporter", ModelExporter.EXPORTER_TYPE};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		URI newFileURI = URI.createFileURI(modelLogFileName);
		Resource newResource = resourceSet.getResource(newFileURI, true);
		EObject eObject = newResource.getContents().get(0);
		assertTrue(eObject instanceof RootNode);
		String referenceName = newFileURI.trimFileExtension().appendFileExtension(PivotEAnnotationValidator.getEAnnotationValidatorRegistry() != null ? "referenceWithEAnnotationValidators" : "reference").appendFileExtension("validity").lastSegment();
		Resource refResource = resourceSet.getResource(getTestModelURI("models/standalone/" + referenceName), true);
		refResource.setURI(newFileURI);
		TestUtil.assertSameModel(refResource, newResource);
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_htmlExportedFile() throws Exception {
		String logFileName = getHTMLLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", logFileName,
			"-exporter", HTMLExporter.EXPORTER_TYPE};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertTrue(standaloneApplication.exists(logFileName));
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_unknownExporter() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", "anotherExporterAttribute"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		assertNoLogFile(getTextLogFileName());
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_nonExistentModelFile() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		try {
			String textLogFileName = getTextLogFileName();
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(getTestModelURI("models/nonExistentModel.ecore")),
				"-rules", String.valueOf(inputOCLURI),
				"-output", textLogFileName,
				"-exporter", TextExporter.EXPORTER_TYPE};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.FAIL, applicationResponse);
			assertNoLogFile(textLogFileName);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("does not exist"));
		} finally {
			standaloneApplication.stop();
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneExecution_validate_nonExistentOclFile() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		try {
			String textLogFileName = getTextLogFileName();
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(getTestModelURI("models/nonExistentFile.ocl")),
				"-output", textLogFileName,
				"-exporter", TextExporter.EXPORTER_TYPE};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.OK, applicationResponse);
			checkValidateLogFile(textLogFileName, 30+EXTRA_EAnnotationValidator_SUCCESSES, 0, 0, 0, 0);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("does not exist"));
			assertTrue(logMessage.contains("ignored"));
		} finally {
			standaloneApplication.stop();
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	/*	@Test
	public void testStandaloneExecution_validate_unexistingOutputFileTest() throws CoreException, IOException {
		@NonNull String @NonNull [] arguments = {"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", "unexistingFile",
			"-exporter", textExporterAttribute};
		doFailingTest(arguments);

		File file = new File(getTextLogFileName());

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		reader.close();
		assertEquals(null, line);
	} */

	@Test
	public void testStandaloneExecution_validate_nonExistentOutputFolder() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		try {
			String nonExistentOutputFolderPath = "nonExistent" + "/" + "anotherName.txt"; //$NON-NLS-3$
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output", nonExistentOutputFolderPath,
				"-exporter", TextExporter.EXPORTER_TYPE};
			StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
			assertEquals(StandaloneResponse.FAIL, applicationResponse);
			assertNoLogFile(nonExistentOutputFolderPath);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("does not exist"));
		} finally {
			standaloneApplication.stop();
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneExecution_validate_listOfOCLFiles() throws Exception {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(textInputOCLURI),
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		checkValidateLogFile(textLogFileName, 42+EXTRA_EAnnotationValidator_SUCCESSES, 2, 2, 2, 0);
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneExecution_validate_listOfOCLFilesToStdout() throws Exception {
		Appendable savedDefaultOutputStream = StandaloneCommand.setDefaultOutputStream(NullAppendable.INSTANCE);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(textInputOCLURI),
			"-exporter", TextExporter.EXPORTER_TYPE};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = standaloneApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		assertNoLogFile(getTextLogFileName());
		StandaloneCommand.setDefaultOutputStream(savedDefaultOutputStream);
		standaloneApplication.stop();
	}
}
