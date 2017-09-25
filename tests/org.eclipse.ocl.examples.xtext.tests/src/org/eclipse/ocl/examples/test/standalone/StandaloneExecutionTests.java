/*******************************************************************************
 * Copyright (c) 2014, 2016 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.standalone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.ocl.examples.standalone.StandaloneResponse;
import org.eclipse.ocl.examples.validity.locator.AbstractPivotConstraintLocator;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.resource.PivotEAnnotationValidator;
import org.junit.Test;

public class StandaloneExecutionTests extends StandaloneTestCase
{
	protected static int EXTRA_EAnnotationValidator_SUCCESSES = PivotEAnnotationValidator.hasEcoreEAnnotationValidators() ? 3 : 0;

	protected static void assertNoLogFile(@NonNull String logFileName) {
		File file = new File(logFileName);
		assertFalse(file.exists());
	}

	private void doFailingTest(@NonNull String @NonNull [] arguments) throws CoreException {
		StandaloneApplication validityApplication = new StandaloneApplication();
		StandaloneResponse applicationResponse = validityApplication.execute(arguments);
		assertEquals(StandaloneResponse.FAIL, applicationResponse);
		validityApplication.stop();
	}

	private void doOKTest(@NonNull String @NonNull [] arguments) throws CoreException {
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
	public void test_help() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help"};
		doOKTest(arguments);
	}

	@Test
	public void test_mandatoryArgumentsOnly() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName};
			doOKTest(arguments);
			assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_missingOutputArgument() throws CoreException, IOException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output"
		};
		doFailingTest(arguments);
		assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_missingExporterArgument() throws CoreException, IOException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-exporter"
		};
		doFailingTest(arguments);
		assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_missingUsingArgument() throws CoreException, IOException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-using"
		};
		doFailingTest(arguments);
		assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_textExportedFile() throws CoreException, IOException {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			checkLogFile(textLogFileName, 36+EXTRA_EAnnotationValidator_SUCCESSES, 1, 1, 1, 0);
	}

	@Test
	public void test_modelExportedFile() throws CoreException, IOException, InterruptedException {
		String modelLogFileName = getLogFileName(ModelExporter.INSTANCE);
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
			URI appendFileExtension = newFileURI.trimFileExtension().appendFileExtension(PivotEAnnotationValidator.hasEcoreEAnnotationValidators() ? "referenceWithEAnnotationValidators" : "reference");
			Resource refResource = resourceSet.getResource(appendFileExtension.appendFileExtension("validity"), true);
			refResource.setURI(newFileURI);
			TestUtil.assertSameModel(refResource, newResource);
	}


	@Test
	public void test_htmlExportedFile() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", getHTMLLogFileName(),
			"-exporter", HTMLExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			File file = new File(getHTMLLogFileName());
			assertTrue(file.exists());
	}

	@Test
	public void test_unknownExporter() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", getTextLogFileName(),
			"-exporter", "anotherExporterAttribute"};
			doFailingTest(arguments);
			assertNoLogFile(getTextLogFileName());
	}

	@Test
	public void test_nonExistentModelFile() throws CoreException {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(getProjectFileURI("models/nonExistentModel.ecore")),
			"-rules", inputOCLFileName,
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doFailingTest(arguments);
			assertNoLogFile(textLogFileName);
	}

	@Test
	public void test_nonExistentOclFile() throws CoreException, IOException {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", String.valueOf(getProjectFileURI("models/nonExistentFile.ocl")),
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);				// Missing file is ignored
			checkLogFile(textLogFileName, 30+EXTRA_EAnnotationValidator_SUCCESSES, 0, 0, 0, 0);
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
	public void test_nonExistentOutputFolder() throws CoreException {
		String nonExistentOutputFolderPath = "nonExistent" + "/" + "anotherName.txt"; //$NON-NLS-3$
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", nonExistentOutputFolderPath,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doFailingTest(arguments);
			assertNoLogFile(nonExistentOutputFolderPath);
	}

	@Test
	public void test_listOfOCLFiles() throws CoreException, IOException {
		String textLogFileName = getTextLogFileName();
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", textInputOCLFileName,
			"-output", textLogFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			checkLogFile(textLogFileName, 42+EXTRA_EAnnotationValidator_SUCCESSES, 2, 2, 2, 0);
	}

	@Test
	public void test_listOfOCLFilesToStdout() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", textInputOCLFileName,
			"-exporter", TextExporter.EXPORTER_TYPE};
			doOKTest(arguments);
			assertNoLogFile(getTextLogFileName());
	}
}
