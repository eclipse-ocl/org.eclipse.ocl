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
package org.eclipse.ocl.standalone.uml.tests;

import org.eclipse.ocl.tests.GenericTestSuite.CheckedTestSuite;
import org.eclipse.ocl.uml.tests.AbstractTestSuite;

import junit.framework.Test;

public class StandaloneUMLTests extends org.eclipse.ocl.uml.tests.AllTests
{
	public static Test suite() {
		String testSuiteName = System.getProperty("testSuiteName", "OCL Tests for UML Metamodel");
		CheckedTestSuite result = new CheckedTestSuite(testSuiteName);
		result.createTestSuite(StandaloneClassPathTests.class, "ClassPath Tests");
		AbstractTestSuite.suite(result);
		return result;
	}
}
