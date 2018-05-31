/*******************************************************************************
 * Copyright (c) 2014, 2017 Obeo and others.
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
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.export.HTMLExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.ModelExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.TextExporter;
import org.eclipse.ocl.examples.standalone.StandaloneApplication;
import org.eclipse.ocl.examples.standalone.StandaloneCommand;
import org.eclipse.ocl.examples.standalone.StandaloneResponse;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;
import org.eclipse.ocl.examples.validity.locator.AbstractPivotConstraintLocator;
import org.eclipse.ocl.examples.xtext.tests.TestCaseLogger;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.validation.PivotEAnnotationValidator;
import org.junit.Test;

public class StandaloneExecutionTests extends StandaloneTestCase
{
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

	private void doFailingTest(@NonNull String @NonNull [] arguments) throws Exception {
		StandaloneApplication validityApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = validityApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		validityApplication.stop();
	}

	private void doOKTest(@NonNull String @NonNull [] arguments) throws Exception {
		StandaloneApplication validityApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = validityApplication.execute(arguments);
		assertEquals(StandaloneResponse.OK, applicationResponse);
		validityApplication.stop();
	}

	private @NonNull List<String> checkLogFile(@NonNull String logFileName, int oks, int infos, int warnings, int errors, int fails) throws IOException {
		File file = new File(logFileName);
		assertTrue(file.exists());
		List<String> lines = new ArrayList<String>();
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
	public void test_help() throws Exception {
		StringWriter outputStream = new StringWriter();
		Appendable savedDefaultOutputStream = StandaloneCommand.setDefaultOutputStream(outputStream);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help"};
		doOKTest(arguments);
		String s = outputStream.toString();
		assert s.contains(StandaloneMessages.Standalone_Help);
		assert s.contains(StandaloneMessages.HelpCommand_Help);
		assert s.contains(StandaloneMessages.ValidateCommand_Help);
		StandaloneCommand.setDefaultOutputStream(savedDefaultOutputStream);
	}

	@Test
	public void test_mandatoryArgumentsOnly() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI)};
			doOKTest(arguments);
			assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_missingOutputArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output"
			};
			doFailingTest(arguments);
			assertNoLogFile(getTextLogFileName());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-output"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_missingExporterArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-exporter"
			};
			doFailingTest(arguments);
			assertNoLogFile(getTextLogFileName());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-exporter"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_missingUsingArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-using"
			};
			doFailingTest(arguments);
			assertNoLogFile(getTextLogFileName());
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("No argument for"));
			assertTrue(logMessage.contains("-using"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_textExportedFile() throws Exception {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			checkLogFile(textLogFileName, 36+EXTRA_EAnnotationValidator_SUCCESSES, 1, 1, 1, 0);
	}

	@Test
	public void test_modelExportedFile() throws Exception {
		String modelLogFileName = getLogFileName(ModelExporter.INSTANCE);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", modelLogFileName,
			//			"-using", "ocl",
			"-exporter", ModelExporter.EXPORTER_TYPE};
			doOKTest(arguments);
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
	}


	@Test
	public void test_htmlExportedFile() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getHTMLLogFileName(),
			"-exporter", HTMLExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			assertTrue(StandaloneCommand.getURIConverter().exists(URI.createFileURI(getHTMLLogFileName()), null));
	}

	@Test
	public void test_unknownExporter() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", "anotherExporterAttribute"};
			doFailingTest(arguments);
			assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_nonExistentModelFile() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			String textLogFileName = getTextLogFileName();
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(getTestModelURI("models/nonExistentModel.ecore")),
				"-rules", String.valueOf(inputOCLURI),
				"-output", textLogFileName,
				"-exporter", TextExporter.EXPORTER_TYPE};
				doFailingTest(arguments);
				assertNoLogFile(textLogFileName);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_nonExistentOclFile() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			String textLogFileName = getTextLogFileName();
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(getTestModelURI("models/nonExistentFile.ocl")),
				"-output", textLogFileName,
				"-exporter", TextExporter.EXPORTER_TYPE};
				doOKTest(arguments);				// Missing file is ignored
				checkLogFile(textLogFileName, 30+EXTRA_EAnnotationValidator_SUCCESSES, 0, 0, 0, 0);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
				assertTrue(logMessage.contains("ignored"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	/*	@Test
	public void test_unexistingOutputFileTest() throws CoreException, IOException {
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
	public void test_nonExistentOutputFolder() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			String nonExistentOutputFolderPath = "nonExistent" + "/" + "anotherName.txt"; //$NON-NLS-3$
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output", nonExistentOutputFolderPath,
				"-exporter", TextExporter.EXPORTER_TYPE};
				doFailingTest(arguments);
				assertNoLogFile(nonExistentOutputFolderPath);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_listOfOCLFiles() throws Exception {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(textInputOCLURI),
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			checkLogFile(textLogFileName, 42+EXTRA_EAnnotationValidator_SUCCESSES, 2, 2, 2, 0);
	}

	@Test
	public void test_listOfOCLFilesToStdout() throws Exception {
		Appendable savedDefaultOutputStream = StandaloneCommand.setDefaultOutputStream(NullAppendable.INSTANCE);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(textInputOCLURI),
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			assertNoLogFile(getTextLogFileName());
			StandaloneCommand.setDefaultOutputStream(savedDefaultOutputStream);
	}
}
