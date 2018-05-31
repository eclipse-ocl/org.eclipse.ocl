/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.standalone.ecore.tests;

import org.eclipse.ocl.ecore.tests.AbstractTestSuite;
import org.eclipse.ocl.tests.GenericTestSuite.CheckedTestSuite;

import junit.framework.Test;

public class StandaloneEcoreBacktrackingTests extends org.eclipse.ocl.ecore.tests.AllTestsBacktracking
{
	public static Test suite() {
		String testSuiteName = System.getProperty("testSuiteName", "OCL Tests for Ecore Metamodel");
		CheckedTestSuite result = new CheckedTestSuite(testSuiteName);
		result.createTestSuite(StandaloneClassPathTests.class, "ClassPath Tests");
		AbstractTestSuite.suite(result);
		return result;
	}
}
