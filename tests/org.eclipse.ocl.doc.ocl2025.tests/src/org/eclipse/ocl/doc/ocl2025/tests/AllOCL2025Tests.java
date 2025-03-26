/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.doc.ocl2025.tests;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Tests for the OCL 2025 example.
 */
public class AllOCL2025Tests extends TestCase
{
	public AllOCL2025Tests() {
		super("");
	}

	public static Test suite() {
		TestSuite result = new TestSuite("All OCL 2025 Tests");
		//		result.addTestSuite(OCL2025_Set_Tests.class);		// NB this is very slow since we are -ea
		//		result.addTestSuite(EXE2016_QVTr_AutomatedTests.class);		// NB this is very slow since we are -ea
		return result;
	}

	public Object run(Object args)
			throws Exception {

		TestRunner.run(suite());
		return Arrays
				.asList(new String[] {"Please see raw test suite output for details."});
	}
}
