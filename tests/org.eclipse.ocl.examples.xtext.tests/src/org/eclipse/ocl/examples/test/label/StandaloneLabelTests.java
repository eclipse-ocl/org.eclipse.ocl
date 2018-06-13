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

import org.eclipse.emf.common.util.URI;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.labels.EcoreURILabelGenerator;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.labels.LabelGeneratorRegistry;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import junit.framework.TestCase;

public class StandaloneLabelTests extends TestCase
{
	@Override
	public String getName() {
		return TestUtil.getName(ClassUtil.nonNullState(super.getName()));
	}

	public void testEcoreURILabel() {
		String testURI = "http://xyzzy/jj";
		URI uri = URI.createURI(testURI);
		ILabelGenerator.Registry myRegistry = new LabelGeneratorRegistry(null);
		String actualLabel = myRegistry.labelFor(uri);
		String expectedLabel1 = "<unknown-URI " + testURI + ">";				// URI pre Kepler M6.
		String expectedLabel2 = "<unknown-Hierarchical " + testURI + ">";		// URI post Kepler M6.
		String expectedLabel = actualLabel.contains("URI") ? expectedLabel1 : expectedLabel2;
		assertEquals(expectedLabel, actualLabel);

		myRegistry = new LabelGeneratorRegistry(ILabelGenerator.Registry.INSTANCE);
		EcoreURILabelGenerator.initialize(myRegistry);
		actualLabel = myRegistry.labelFor(uri);
		expectedLabel = testURI;
		assertEquals(expectedLabel, actualLabel);

		EcoreURILabelGenerator.initialize(ILabelGenerator.Registry.INSTANCE);
		actualLabel = myRegistry.labelFor(uri);
		expectedLabel = testURI;
		assertEquals(expectedLabel, actualLabel);

		myRegistry.uninstall(URI.class);
		actualLabel = myRegistry.labelFor(uri);
		expectedLabel = testURI;
		assertEquals(expectedLabel, actualLabel);

		ILabelGenerator.Registry.INSTANCE.uninstall(URI.class);
		actualLabel = myRegistry.labelFor(uri);
		assertEquals(expectedLabel, actualLabel);
	}
}
