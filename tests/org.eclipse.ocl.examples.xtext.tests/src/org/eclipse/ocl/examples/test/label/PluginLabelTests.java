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
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.labels.LabelGeneratorRegistry;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import junit.framework.TestCase;

public class PluginLabelTests extends TestCase
{
	@Override
	public String getName() {
		return TestUtil.getName(ClassUtil.nonNullState(super.getName()));
	}

	public void testEcoreURIGlobalLabel() {
		String testURI = "http://xyzzy/jj";
		URI uri = URI.createURI(testURI);
		String actualLabel = ILabelGenerator.Registry.INSTANCE.labelFor(uri);
		String expectedLabel = testURI;
		assertEquals(expectedLabel, actualLabel);
	}

	public void testEcoreURILocalLabel() {
		String testURI = "http://xyzzy/jj";
		URI uri = URI.createURI(testURI);
		ILabelGenerator.Registry myRegistry = new LabelGeneratorRegistry(ILabelGenerator.Registry.INSTANCE);
		String actualLabel = myRegistry.labelFor(uri);
		String expectedLabel = testURI;
		assertEquals(expectedLabel, actualLabel);
	}
}
