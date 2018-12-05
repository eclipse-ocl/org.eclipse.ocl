/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import junit.framework.TestCase;

public class MergeWriterTest extends TestCase
{	
	public void testNlRegex() {
		TestCase.assertEquals(MergeWriter.DEFAULT_COPYRIGHT, MergeWriter.getCopyright(null));
		TestCase.assertEquals("a\nb", MergeWriter.getCopyright("a\nb"));
		TestCase.assertEquals("a\nb", MergeWriter.getCopyright("a\n\rb"));
		TestCase.assertEquals("a\nb", MergeWriter.getCopyright("a\rb"));
		TestCase.assertEquals("a\nb", MergeWriter.getCopyright("a\r\nb"));
		TestCase.assertEquals("a\n\nb", MergeWriter.getCopyright("a\n\nb"));
		TestCase.assertEquals("a\n\nb", MergeWriter.getCopyright("a\n\r\r\nb"));
		TestCase.assertEquals("a\n\nb", MergeWriter.getCopyright("a\r\rb"));
		TestCase.assertEquals("a\n\nb", MergeWriter.getCopyright("a\r\n\nb"));
	}
}
