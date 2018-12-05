/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *   E.D.Willink (Obeo) - 425799 Validity View Integration
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test;


import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.export.HTMLExporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

/**
 * Class testing the HTMLExport class.
 */
public class HTMLExportOCLValidationResultTests extends AbstractExportOCLValidationResultTests
{
	private static final String WARNING_NUMBER_XPATH_LOCATION = "//table[2]/tr[4]/td[2]"; //$NON-NLS-1$
	private static final String INFO_NUMBER_XPATH_LOCATION = "//table[2]/tr[3]/td[2]"; //$NON-NLS-1$
	private static final String ERROR_NUMBER_XPATH_LOCATION = "//table[2]/tr[5]/td[2]"; //$NON-NLS-1$
	private static final String FAILURE_NUMBER_XPATH_LOCATION = "//table[2]/tr[6]/td[2]"; //$NON-NLS-1$
	private static final String SUCCESS_NUMBER_XPATH_LOCATION = "//table[2]/tr[2]/td[2]"; //$NON-NLS-1$

	private String exportedFileName = null;
	private XPath xPathEngine = null;

	protected void assertXPathTrue( @NonNull String contents, String expression) throws XPathExpressionException, CoreException, IOException {
		InputSource stream = new InputSource(new StringReader(contents));
		xPathEngine.compile(expression);
		Object xPathResult = xPathEngine.evaluate(expression, stream, XPathConstants.BOOLEAN);
		assertTrue("Expected \"" + expression + "\" to be true", (Boolean) xPathResult);
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
		exportedFileName = getProjectFileName(getTestName() + ".html");
		initExporter(HTMLExporter.EXPORTER_TYPE);
		XPathFactory factory = XPathFactory.newInstance();
		xPathEngine = factory.newXPath();
		TEST_PROGRESS.println("xPathEngine = " + xPathEngine);
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
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
	public void testHTMLExport_LoggingMetricsWithNoSeverity()
			throws XPathExpressionException, CoreException, IOException {
		// initiate the test case
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);

		// launch the exporter
		String exported = doTest();

		// test the exported content
		assertXPathTrue(exported, SUCCESS_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, INFO_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
		assertXPathTrue(exported, WARNING_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
		assertXPathTrue(exported, ERROR_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
		assertXPathTrue(exported, FAILURE_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
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
	public void testHTMLExport_LoggingMetricsWithInformationSeverity()
			throws IOException, XPathExpressionException, CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.OK);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);

		// launch the exporter
		String exported = doTest();

		assertXPathTrue(exported, SUCCESS_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, INFO_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, WARNING_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
		assertXPathTrue(exported, ERROR_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
		assertXPathTrue(exported, FAILURE_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
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
	public void testHTMLExport_LoggingMetricsWithWarningSeverity()
			throws IOException, XPathExpressionException, CoreException {
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

		assertXPathTrue(exported, SUCCESS_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, INFO_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, WARNING_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, ERROR_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
		assertXPathTrue(exported, FAILURE_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
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
	public void testHTMLExport_LoggingMetricsWithErrorSeverity()
			throws IOException, XPathExpressionException, CoreException {
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

		assertXPathTrue(exported, SUCCESS_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, INFO_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, WARNING_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, ERROR_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, FAILURE_NUMBER_XPATH_LOCATION + "=0"); //$NON-NLS-1$
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
	public void testHTMLExport_LoggingMetricsWithFailureSeverity()
			throws IOException, XPathExpressionException, CoreException {
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

		assertXPathTrue(exported, SUCCESS_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, INFO_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, WARNING_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, ERROR_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
		assertXPathTrue(exported, FAILURE_NUMBER_XPATH_LOCATION + "=1"); //$NON-NLS-1$
	}

	/**
	 * Tests that the exported contents contains the expected diagnostics for a
	 * constraint.
	 *
	 * @throws XPathExpressionException
	 * @throws CoreException
	 * @throws IOException
	 */
	@Test
	public void testHTMLExport_LogNullDiagnosticMessage()
			throws XPathExpressionException, CoreException, IOException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);

		// launch the exporter
		String exported = doTest();

		assertXPathTrue(exported, "//table[3]/tr[2]/td[5]='null diagnostic message'"); //$NON-NLS-1$
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
	public void testHTMLExport_LogInfoDiagnosticMessage()
			throws XPathExpressionException, CoreException, IOException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.INFO);
		String diagnostic = "Diag INFO"; //$NON-NLS-1$
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setDiagnostic(diagnostic);

		// launch the exporter
		String exported = doTest();

		assertXPathTrue(exported, "//table[3]/tr[2]/td[5]='" + diagnostic + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void testHTMLExport_ProducesOneLogHeading() throws IOException,
	XPathExpressionException, CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS_CONSTRAINT, VALIDATABLE_E_CLASS3_ECLASS5)
		.setSeverity(Severity.FATAL);

		// launch the exporter
		String exported = doTest();

		// test heading
		assertXPathTrue(exported, "//body/h2[2]='4.1. Failures'");//$NON-NLS-1$
	}

	@Test
	public void testHTMLExport_ProducesAllLogHeadings() throws IOException,
	XPathExpressionException, CoreException {
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_ECLASS_CONSTRAINT, VALIDATABLE_E_CLASS3_ECLASS5)
		.setSeverity(Severity.FATAL);
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

		// test headings
		assertXPathTrue(exported, "//body/h2[2]='4.1. Infos'");
		assertXPathTrue(exported, "//body/h2[3]='4.2. Warnings'");
		assertXPathTrue(exported, "//body/h2[4]='4.3. Errors'");
		assertXPathTrue(exported, "//body/h2[5]='4.4. Failures'");
	}

	@Test
	public void testHTMLExport_LogContent() throws IOException,
	XPathExpressionException, CoreException {
		// Initialize test case
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

		// test the expected exported content
		// information
		String expression = "count(//table[3]/tr[" //$NON-NLS-1$
				+ "td[1]=' Resource: ecoreTest.ocl' " //$NON-NLS-1$
				+ " and td[2]='eclass2_constraint'" //$NON-NLS-1$
				+ " and contains(td[3],'eclass2_constraint')" //$NON-NLS-1$
				+ " and td[4]='INFO'" //$NON-NLS-1$
				+ "])=1"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		expression = "count(//table[3]/tr[td[1]=' Resource: ecore.ocl' " //$NON-NLS-1$
				+ " and td[2]='eattribute_constraint'" //$NON-NLS-1$
				+ " and contains(td[3],'eattribute_constraint')" //$NON-NLS-1$
				+ " and td[4]='INFO'])"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		assertXPathTrue(exported, "count(//table[3]/tr)=3");

		// information
		expression = "//table[3]/tr[3]/td[1]=' Resource: ecore.ocl' " //$NON-NLS-1$
				+ " and //table[3]/tr[3]/td[2]='eattribute_constraint'" //$NON-NLS-1$
				+ " and contains(//table[3]/tr[3]/td[3],'eattribute_constraint')" //$NON-NLS-1$
				+ " and //table[3]/tr[3]/td[4]='INFO'"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		assertXPathTrue(exported, "count(//table[3]/tr)=3");

		// error
		expression = "count(//table[5]/tr[td[1]=' Resource: ecoreTest.ocl'" //$NON-NLS-1$
				+ " and td[2]='eclass1_constraint'" //$NON-NLS-1$
				+ " and contains(td[3],'eclass1_constraint')" //$NON-NLS-1$
				+ " and td[4]='ERROR'])=1"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		expression = "count(//table[5]/tr[td[1]=' Resource: ecore.ocl'" //$NON-NLS-1$
				+ " and td[2]='epackage_constraint_2'" //$NON-NLS-1$
				+ " and contains(td[3],'epackage_constraint_2')" //$NON-NLS-1$
				+ " and td[4]='ERROR'])=1"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		assertXPathTrue(exported, "count(//table[5]/tr)=3");

		// fatal
		expression = "count(//table[6]/tr[td[1]=' Resource: ecore.ocl'" //$NON-NLS-1$
				+ " and td[2]='epackage_constraint'" //$NON-NLS-1$
				+ " and contains(td[3],'epackage_constraint')" //$NON-NLS-1$
				+ " and td[4]='FATAL'])=1"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		expression = "count(//table[6]/tr[td[1]=' Resource: ecore.ocl'" //$NON-NLS-1$
				+ " and td[2]='eclass_constraint'" //$NON-NLS-1$
				+ " and contains(td[3],'eclass_constraint')" //$NON-NLS-1$
				+ " and td[4]='FATAL'])=1"; //$NON-NLS-1$
		assertXPathTrue(exported, expression);

		assertXPathTrue(exported, "count(//table[6]/tr)=3");
	}

	@Test
	public void testHTMLExport_Statistics() throws IOException,
	XPathExpressionException, CoreException {
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
				CONSTRAINABLE_ECLASS2_CONSTRAINT, VALIDATABLE_E_CLASS2).setSeverity(
						Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE5_E_STRING)
		.setSeverity(Severity.INFO);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE1_E_STRING)
		.setSeverity(Severity.WARNING);
		getResultOfValidatableNodeFromLabel(results,
				CONSTRAINABLE_EATTRIBUTE_CONSTRAINT, VALIDATABLE_E_ATTRIBUTE3_E_SHORT)
		.setSeverity(Severity.WARNING);

		// launch the exporter
		String exported = doTest();

		// test output file name
		assertXPathTrue(exported, "//table[1]/tr/td[2]='" + exportedFileName + "'");

		// test resource validated
		//		assertXPathTrue(exported, "//ul[1]/li[1]='" + TEST_PROJECT_LOCATION + "/" + OCL_CONSTRAINTS_MODEL + "'");
		assertXPathTrue(exported, "//ul[1]/li[1]='" + TEST_PROJECT_LOCATION + "/" + ECORE_MODEL_NAME + "'");
		//		assertXPathTrue(exported, "//ul[1]/li[3]='" + TEST_PROJECT_LOCATION + "/" + OCL_CONSTRAINTS_MODEL2 + "'");
		assertXPathTrue(exported, "//ul[1]/li[2]='" + TEST_PROJECT_LOCATION + "/" + ECORE_MODEL_NAME3 + "'");
		assertXPathTrue(exported, "//ul[1]/li[3]='" + TEST_PROJECT_LOCATION + "/" + ECORE_MODEL_NAME2 + "'");

		// test author
		assertXPathTrue(exported, "//table[1]/tr[2]/td[2]!=''");

		// tests validation results
		// Total number
		assertXPathTrue(exported, "//body/table[2]/tr/td[2]=" + EXPECTED_RESULTS);

		// Success
		assertXPathTrue(exported, SUCCESS_NUMBER_XPATH_LOCATION + "=" + EXPECTED_SUCCESSES);

		// Infos
		assertXPathTrue(exported, INFO_NUMBER_XPATH_LOCATION + "=" + EXPECTED_INFOS);

		// Warning
		assertXPathTrue(exported, WARNING_NUMBER_XPATH_LOCATION + "=" + EXPECTED_WARNINGS);

		// Errors
		assertXPathTrue(exported, ERROR_NUMBER_XPATH_LOCATION + "=" + EXPECTED_ERRORS);

		// Failures
		assertXPathTrue(exported, FAILURE_NUMBER_XPATH_LOCATION + "=" + EXPECTED_FAILURES);
	}

	@Test
	public void testHTMLExport_ModelsValidatedSuccessfully() throws IOException, XPathExpressionException, CoreException {
		// launch the exporter
		String exported = doTest();

		// test output file name
		assertXPathTrue(exported, "//table[1]/tr/td[2]='" + exportedFileName + "'");

		// test resource validated
		//		assertXPathTrue(exported, "//ul[1]/li[1]='" + TEST_PROJECT_LOCATION + "/" + OCL_CONSTRAINTS_MODEL + "'");
		assertXPathTrue(exported, "//ul[1]/li[1]='" + TEST_PROJECT_LOCATION + "/" + ECORE_MODEL_NAME + "'");
		//		assertXPathTrue(exported, "//ul[1]/li[3]='" + TEST_PROJECT_LOCATION + "/" + OCL_CONSTRAINTS_MODEL2 + "'");
		assertXPathTrue(exported, "//ul[1]/li[2]='" + TEST_PROJECT_LOCATION + "/" + ECORE_MODEL_NAME3 + "'");
		assertXPathTrue(exported, "//ul[1]/li[3]='" + TEST_PROJECT_LOCATION + "/" + ECORE_MODEL_NAME2 + "'");

		// test author
		assertXPathTrue(exported, "//table[1]/tr[2]/td[2]!=''");

		// tests validation results
		assertXPathTrue(exported, "//body/table[2]/tr/td[2]=0");
		assertXPathTrue(exported, "//table[2]/tr[2]/td[2]=0");
		assertXPathTrue(exported, "//table[2]/tr[3]/td[2]=0");
		assertXPathTrue(exported, "//table[2]/tr[4]/td[2]=0");
		assertXPathTrue(exported, "//table[2]/tr[5]/td[2]=0");
		assertXPathTrue(exported, "//table[2]/tr[6]/td[2]=0");

		// test logs results
		assertXPathTrue(exported, "//p[1]='No log to display: models has been successfully validated.'");
	}
}
