/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.ocl.examples.validity.test.AllValidityTests;

public class AllExamplesTests extends TestCase
{
	public static void buildSuite(TestSuite suite) {
		AllValidityTests.buildSuite(suite);
	}

	public static Test suite() {
    	String testSuiteName = System.getProperty("testSuiteName", "All Examples Tests");
		TestSuite suite = new TestSuite(testSuiteName);
		buildSuite(suite);
		return suite;
	}
}
