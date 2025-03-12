/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import java.io.IOException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ocl.examples.test.xtext.AbstractUMLLoadTests;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * These tests support development of analyses and optimizations for the OCL 2025 Collection / Iteration optimization paper.
 *
 * (Originals copied for org.eclipse.ocl.examples.test.xtext.RoundTripTests.testCompleteOCLRoundTrip_UML.)
 */
public class IterationAnalysisTests extends AbstractUMLLoadTests
{
	public void testAnalyze_UML_2_5_Final() throws IOException, InterruptedException, ParserException {
		//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("UML.xmi");
		//XXX	GlobalEnvironmentFactory.getInstance().setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		Model asModel = doLoadUML(null, modelURI, false, true, null, null);		// FIXME BUG 419132 eliminate last argument; always true
	}
}
