/*******************************************************************************
 * Copyright (c) 2018, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.compatibility.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CompatibilityPivotTests extends org.eclipse.ocl.xtext.tests.AllXtextTests
{
	public static Test suite() {
		TestSuite suite = (TestSuite) org.eclipse.ocl.xtext.tests.AllXtextTests.suite();
		suite.addTestSuite(StandaloneClassPathTests.class);
		return suite;
	}
}
