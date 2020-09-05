/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;

public class SerializationBuilderTests extends XtextTestCase
{
	public void testSerializationBuilder_Indent() {
		SerializationBuilder s = new SerializationBuilder("\n", "\t");
		s.append("xx");
		s.append(SerializationBuilder.PUSH);
		s.append("yy");
		s.append(SerializationBuilder.NEW_LINE);
		s.append("zz");
		s.append(SerializationBuilder.POP);
		assertEquals("xxyy\n\tzz\n", s.toString());
		s.append(SerializationBuilder.PUSH);
		s.append("a");
		s.append(SerializationBuilder.NEW_LINE);
		s.append(SerializationBuilder.PUSH);
		s.append("bb");
		s.append(SerializationBuilder.PUSH);
		s.append(SerializationBuilder.NEW_LINE);
		s.append("ccc");
		s.append(SerializationBuilder.NEW_LINE);
		s.append(SerializationBuilder.POP);
		s.append(SerializationBuilder.POP);
		s.append(SerializationBuilder.POP);
		assertEquals("xxyy\n\tzza\n\t\tbb\n\t\t\tccc\n", s.toString());
	}

	public void testSerializationBuilder_NewLine() {
		SerializationBuilder s = new SerializationBuilder("\r\n", "\t");
		assertEquals("", s.toString());
		s.append(SerializationBuilder.NEW_LINE);
		assertEquals("\r\n", s.toString());
		s.append(SerializationBuilder.NEW_LINE);
		assertEquals("\r\n\r\n", s.toString());
		s.append(SerializationBuilder.NEW_LINE);
		assertEquals("\r\n\r\n\r\n", s.toString());
	}
}
