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

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.export.IValidityExporter;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class StandaloneTestCase extends PivotTestCaseWithAutoTearDown
{
	protected final @NonNull URI inputModelURI = getTestModelURI("models/standalone/EcoreTestFile.ecore"); //$NON-NLS-1$
	protected final @NonNull URI inputOCLURI = getTestModelURI("models/standalone/eclipse_ocl_rule.ocl"); //$NON-NLS-1$
	protected final @NonNull URI inputOCLURI2 = getTestModelURI("models/standalone/eclipse_ocl_rule_2.ocl"); //$NON-NLS-1$
	protected final @NonNull URI textInputOCLURI = getTestModelURI("models/standalone/ocl_rules.txt");//$NON-NLS-1$

	protected @NonNull String getHTMLLogFileName() {
		String logFileName = "log_" + getTestName() + ".html";
		return ClassUtil.nonNullState(getTestFileURI(logFileName).toFileString()).replace("\\",  "/");
	}

	protected @NonNull String getTextLogFileName() {
		String logFileName = "log_" + getTestName() + ".txt";
		return ClassUtil.nonNullState(getTestFileURI(logFileName).toFileString()).replace("\\",  "/");
	}

	protected @NonNull String getLogFileName(@NonNull IValidityExporter exporter) {
		String logFileName = "log_" + getTestName() + "." + exporter.getPreferredExtension();
		return ClassUtil.nonNullState(getTestFileURI(logFileName).toFileString()).replace("\\",  "/");
	}

	protected @NonNull String getXMLLogFileName() {
		String logFileName = "log_" + getTestName() + ".xml";
		return ClassUtil.nonNullState(getTestFileURI(logFileName).toFileString()).replace("\\",  "/");
	}

	@Override
	protected void setUp() throws Exception {
		//		StandaloneProjectMap.PROJECT_MAP_ADD_EPACKAGE.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_ADD_GEN_MODEL.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_ADD_GENERATED_PACKAGE.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_ADD_URI_MAP.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_CONFIGURE.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_GET.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_INSTALL.setState(true);
		//		StandaloneProjectMap.PROJECT_MAP_RESOLVE.setState(true);
		super.setUp();
		TestCaseAppender.INSTANCE.uninstall();
	}
}
