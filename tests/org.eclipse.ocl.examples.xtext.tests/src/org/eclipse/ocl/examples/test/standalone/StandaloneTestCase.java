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

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.export.IValidityExporter;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;

public class StandaloneTestCase extends PivotTestCaseWithAutoTearDown
{
	protected final @NonNull String inputModelName = getProjectFileName("models/EcoreTestFile.ecore"); //$NON-NLS-1$
	protected final @NonNull String inputOCLFileName = getProjectFileName("models/eclipse_ocl_rule.ocl"); //$NON-NLS-1$
	protected final @NonNull String inputOCLFileName2 = getProjectFileName("models/eclipse_ocl_rule_2.ocl"); //$NON-NLS-1$
	protected final @NonNull String textInputOCLFileName = getProjectFileName("models/ocl_rules.txt");//$NON-NLS-1$

	protected @NonNull String getProjectFileName(String referenceName) {
		@SuppressWarnings("null")@NonNull String projectName = getClass().getPackage().getName().replace('.', '/');
		URL projectURL = getTestResource(projectName);
		assertNotNull(projectURL);
		URI uri = URI.createURI(projectURL.toString());
		String projectPath = uri.isFile() ? uri.toFileString() : uri.toString();
		assert projectPath != null;
		projectPath = projectPath.replace("\\", "/");
		if (!projectPath.endsWith("/")) {
			projectPath = projectPath + "/";
		}
		return projectPath + referenceName;
	}

	protected @NonNull String getHTMLLogFileName() {
		return getProjectFileName("models/log_" + getTestName() + ".html");
	}

	protected @NonNull String getTextLogFileName() {
		return getProjectFileName("models/log_" + getTestName() + ".txt");
	}

	protected @NonNull String getLogFileName(@NonNull IValidityExporter exporter) {
		return getProjectFileName("models/log_" + getTestName() + "." + exporter.getPreferredExtension());
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
