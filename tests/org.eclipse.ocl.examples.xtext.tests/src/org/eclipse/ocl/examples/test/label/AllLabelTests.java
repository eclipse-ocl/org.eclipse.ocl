/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.label;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class AllLabelTests extends TestCase
{
	public static void buildSuite(TestSuite suite) {
		suite.addTestSuite(LabelTests.class);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			suite.addTestSuite(PluginLabelTests.class);
		}
		else {
			suite.addTestSuite(StandaloneLabelTests.class);
		}
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("All Label Tests");
		buildSuite(suite);
		return suite;
	}
}
