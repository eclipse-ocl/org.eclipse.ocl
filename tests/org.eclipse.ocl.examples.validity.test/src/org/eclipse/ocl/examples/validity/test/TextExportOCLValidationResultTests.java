/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *   E.D.Willink (CEA LIST) - 425799 Validity View Integration
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import javax.xml.xpath.XPathExpressionException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.export.TextExporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class testing the TextExport class.
 */
public class TextExportOCLValidationResultTests extends AbstractExportOCLValidationResultTests
{
	private static final int CONSTRAINING_FILES = 3;
	private static final int TOTAL_NUMBER_XPATH_LOCATION = CONSTRAINING_FILES+11;
	private static final int SUCCESS_NUMBER_XPATH_LOCATION = TOTAL_NUMBER_XPATH_LOCATION+1;
	private static final int INFO_NUMBER_XPATH_LOCATION = TOTAL_NUMBER_XPATH_LOCATION+2;
	private static final int WARNING_NUMBER_XPATH_LOCATION = TOTAL_NUMBER_XPATH_LOCATION+3;
	private static final int ERROR_NUMBER_XPATH_LOCATION = TOTAL_NUMBER_XPATH_LOCATION+4;
	private static final int FAILURE_NUMBER_XPATH_LOCATION = TOTAL_NUMBER_XPATH_LOCATION+5;
	private static final int DIAG_INFO_LOCATION = TOTAL_NUMBER_XPATH_LOCATION+14;

	private String exportedFileName = null;

	protected void assertLineContains(@NonNull String contents, int lineNumber, String expression) throws CoreException, IOException {
		Scanner sc = new Scanner(new StringReader(contents));
		String line = null;
		int i = 1;
		while (i <= lineNumber) {
			line = sc.nextLine();
			i++;
		}
		if (line != null) {
			assertTrue("Expected \"" + line + "\" to contain \"" + expression + "\"", line.contains(expression));
		}
		sc.close();
	}

	protected @NonNull String doTest() throws IOException {
		String exported = exporter.export(rootNode, exportedFileName);
		FileWriter writer = new FileWriter(exportedFileName);
		writer.append(exported);
		writer.close();
		TEST_PROGRESS.println("exported " + ecoreResource.getURI());
		return exported;
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		exportedFileName = getProjectFileName(getTestName() + ".txt");
		initExporter(TextExporter.EXPORTER_TYPE);
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 *
	 * Tests that the exported file contains the expected log for metrics
	 * section.
	 *
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws CoreException
	 */
	@Test
	public void testTEXTExport_LoggingMetricsWithNoSeverity()
			throws IOException, XPathExpressionException, CoreException {
		// initiate the test case
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);

		// launch the exporter
		String exported = doTest();

		// test the exporteFile content
		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported file contains the expected log for metrics
	 * section.
	 *
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws CoreException
	 */
	@Test
	public void testTEXTExport_LoggingMetricsWithInformationSeverity()
			throws IOException, XPathExpressionException, CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);

		// launch the exporter
		String exported = doTest();

		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported file contains the expected log for metrics
	 * section.
	 *
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws CoreException
	 */
	@Test
	public void testTEXTExport_LoggingMetricsWithWarningSeverity()
			throws IOException, InterruptedException, XPathExpressionException,
			CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE1_E_STRING)
		.setSeverity(Severity.WARNING);

		// launch the exporter
		String exported = doTest();

		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported file contains the expected log for metrics
	 * section.
	 *
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws CoreException
	 */
	@Test
	public void testTEXTExport_LoggingMetricsWithErrorSeverity()
			throws IOException, InterruptedException, XPathExpressionException,
			CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE1_E_STRING)
		.setSeverity(Severity.WARNING);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE2_E_STRING)
		.setSeverity(Severity.ERROR);

		// launch the exporter
		String exported = doTest();

		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported file contains the expected log for metrics
	 * section.
	 *
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws CoreException
	 */
	@Test
	public void testTEXTExport_LoggingMetricsWithFailureSeverity()
			throws IOException, InterruptedException, XPathExpressionException,
			CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE1_E_STRING)
		.setSeverity(Severity.WARNING);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE2_E_STRING)
		.setSeverity(Severity.ERROR);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE4_E_STRING)
		.setSeverity(Severity.FATAL);

		// launch the exporter
		String exported = doTest();

		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, "1"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported content contains the expected diagnostics for a
	 * constraint.
	 *
	 * @throws XPathExpressionException
	 * @throws CoreException
	 * @throws IOException
	 */
	public void testTEXTExport_LogNullDiagnosticMessage()
			throws XPathExpressionException, CoreException, IOException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);

		// launch the exporter
		String exported = doTest();

		assertLineContains(exported, DIAG_INFO_LOCATION, "null diagnostic message"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported content contains the expected diagnostics for a
	 * constraint.
	 *
	 * @throws XPathExpressionException
	 * @throws CoreException
	 * @throws IOException
	 */
	@Test
	public void testTEXTExport_LogInfoDiagnosticMessage() throws IOException,
	InterruptedException, XPathExpressionException, CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);
		//		exporter.export(TestTool.getIResource(ecoreResource), rootNode, GENERATED_FILE_PATH);
		//		assertLineContains(exported, 26, "null diagnostic message"); //$NON-NLS-1$

		//		clearGeneratedReport();
		String diagnostic = "Diag INFO"; //$NON-NLS-1$
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setDiagnostic(diagnostic);

		// launch the exporter
		String exported = doTest();

		assertLineContains(exported, DIAG_INFO_LOCATION, diagnostic);
	}

	@Test
	public void testTEXTExport_ProducesAllLogHeadings() throws IOException,
	CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS1_CONSTRAINT, VALIDATABLE_ECLASS1_E1_ATT1).setSeverity(
						Severity.ERROR);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EPACKAGE_CONSTRAINT, VALIDATABLE_ECORE_TEST).setSeverity(
						Severity.FATAL);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.WARNING);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS2_CONSTRAINT, VALIDATABLE_E_CLASS2).setSeverity(
						Severity.INFO);

		// launch the exporter
		String exported = doTest();

		// test tables headings
		int TEST_TABLE_1_LOCATION = FAILURE_NUMBER_XPATH_LOCATION+5;
		assertLineContains(exported, TEST_TABLE_1_LOCATION, "ecoreTest.ocl"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_1_LOCATION+1, "eclass2_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_1_LOCATION+2, "eclass2_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_1_LOCATION+3, "INFO"); //$NON-NLS-1$

		int TEST_TABLE_2_LOCATION = TEST_TABLE_1_LOCATION+7;
		assertLineContains(exported, TEST_TABLE_2_LOCATION, "ecore.ocl"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_2_LOCATION+1, "eattribute_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_2_LOCATION+2, "eattribute_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_2_LOCATION+3, "WARNING"); //$NON-NLS-1$

		int TEST_TABLE_3_LOCATION = TEST_TABLE_1_LOCATION+2*7;
		assertLineContains(exported, TEST_TABLE_3_LOCATION, "ecoreTest.ocl"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_3_LOCATION+1, "eclass1_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_3_LOCATION+2, "eclass1_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_3_LOCATION+3, "ERROR"); //$NON-NLS-1$

		int TEST_TABLE_4_LOCATION = TEST_TABLE_1_LOCATION+3*7;
		assertLineContains(exported, TEST_TABLE_4_LOCATION, "ecore.ocl"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_4_LOCATION+1, "epackage_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_4_LOCATION+2, "epackage_constraint"); //$NON-NLS-1$
		assertLineContains(exported, TEST_TABLE_4_LOCATION+3, "FATAL"); //$NON-NLS-1$
	}

	@Test
	public void testTEXTExport_Statistics() throws IOException, CoreException {
		for (Result result : results) {
			result.setSeverity(Severity.OK);
		}
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS1_CONSTRAINT, VALIDATABLE_ECLASS1_E1_ATT1).setSeverity(
						Severity.ERROR);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EPACKAGE_CONSTRAINT_2, VALIDATABLE_ECORE_TEST).setSeverity(
						Severity.ERROR);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EPACKAGE_CONSTRAINT, VALIDATABLE_ECORE_TEST).setSeverity(
						Severity.FATAL);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS_CONSTRAINT, VALIDATABLE_E_CLASS3_ECLASS5)
		.setSeverity(Severity.FATAL);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.WARNING);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS2_CONSTRAINT, VALIDATABLE_E_CLASS2).setSeverity(
						Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE1_E_STRING)
		.setSeverity(Severity.WARNING);

		// launch the exporter
		String exported = doTest();

		// tests validation results
		assertLineContains(exported, TOTAL_NUMBER_XPATH_LOCATION, EXPECTED_RESULTS.toString());
		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, EXPECTED_SUCCESSES.toString());
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, EXPECTED_INFOS.toString());
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, EXPECTED_WARNINGS.toString());
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, EXPECTED_ERRORS.toString());
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, EXPECTED_FAILURES.toString());
	}

	@Test
	public void testTEXTExport_ModelsValidatedSuccessfully() throws IOException, CoreException {
		// launch the exporter
		String exported = doTest();

		// test output file name
		assertLineContains(exported, 2, exportedFileName);

		// test resource validated
		//		assertLineContains(exported, 8, OCL_CONSTRAINTS_MODEL); //$NON-NLS-1$
		assertLineContains(exported, 8, ECORE_MODEL_NAME);
		//		assertLineContains(exported, 10, OCL_CONSTRAINTS_MODEL2); //$NON-NLS-1$
		assertLineContains(exported, 9, ECORE_MODEL_NAME3);
		assertLineContains(exported, 10, ECORE_MODEL_NAME2);

		// tests validation results
		assertLineContains(exported, TOTAL_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, SUCCESS_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, INFO_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, WARNING_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, ERROR_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION, "0"); //$NON-NLS-1$
		assertLineContains(exported, FAILURE_NUMBER_XPATH_LOCATION+4, "No log to display: models has been successfully validated."); //$NON-NLS-1$
	}

}
