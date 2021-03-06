/*******************************************************************************
 * Copyright (c) 2018, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;

public class SerializationBuilderTests extends XtextTestCase
{
	private static final @NonNull String WE = SerializationBuilder.WRAP_END;
	private static final @NonNull String WH = SerializationBuilder.WRAP_HERE;
//	private static final @NonNull String SS = SerializationBuilder.SOFT_SPACE;
	private static final @NonNull String WBA = SerializationBuilder.WRAP_BEGIN_ALL;
//	private static final @NonNull String WA = SerializationBuilder.WRAP_ANCHOR;
	private static final @NonNull String POP = SerializationBuilder.POP;
	private static final @NonNull String NL = SerializationBuilder.NEW_LINE;
	private static final @NonNull String PUSH = SerializationBuilder.PUSH;

	public void appends(@NonNull SerializationBuilder s, @NonNull String @NonNull  ... strings) {
		for (@NonNull String string : strings) {
			s.append(string);
		}
	}

	public @NonNull String concat(@NonNull String @NonNull  ... strings) {
		StringBuilder s = new StringBuilder();
		for (@NonNull String string : strings) {
			s.append(string);
		}
		return s.toString();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SerializationBuilder.reset();
	}

	public void testSerializationBuilder_Indent() {
		String nl = "\n";
		String ht = "\t";
		String ht2 = ht + ht;
		String ht3 = ht2 + ht;
		String a1 = "a";
		String b2 = "bb";
		String c3 = "ccc";
		String x2 = "xx";
		String y2 = "yy";
		String z2 = "zz";
		//
		SerializationBuilder s = new SerializationBuilder(nl, ht);
		appends(s,              x2, PUSH, y2, NL,         z2, POP);
		String concat1 = concat(x2,       y2, nl, ht, z2);
		assertEquals(concat1 + nl, s.toString() + "\n");
		//
		appends(s, PUSH,        a1, NL, PUSH, b2, PUSH, NL,      c3, NL, POP, POP, POP);
		String concat2 = concat(a1, nl, ht2,  b2,       nl, ht3, c3);
		s.close();
		assertEquals(concat1 + concat2 + nl, s.toString());
	}

	public void testSerializationBuilder_NewLine() {
		String nl = "\r\n";
		//
		SerializationBuilder s = new SerializationBuilder(nl, "\t");
		assertEquals("", s.toString());
		//
		appends(s, NL);
		assertEquals(nl, s.toString());
		//
		appends(s, NL);
		assertEquals(nl + nl, s.toString());
		//
		appends(s, NL);
		assertEquals(nl + nl + nl, s.toString());
		s.close();
	}

	public void testSerializationBuilder_Wrap() {
		String a10 = "aaaaaaaaaa";
		String b12 = "bbbbbbbbbbbb";
		String c14 = "cccccccccccccc";
		String d16 = "dddddddddddddddd";
		String e18 = "eeeeeeeeeeeeeeeeee";
		String f20 = "ffffffffffffffffffff";
		String nl = "\n";
		String ht = "\t";
		SerializationBuilder s = new SerializationBuilder(nl, ht, 15, 8);
		appends(s, a10, WH, "(", WBA, b12, WH, "(", WBA, c14, WH, "(", WBA, d16, WH, "(", WBA, e18, WH, "(", WBA, f20, WH, ")", WE, ")", WE, ")", WE, ")", WE, ")", WE, NL);
		String concat = concat(a10, nl, spaces(0), "(", b12, nl, spaces(1), "(", c14, nl, spaces(1+1), "(", d16, nl, spaces(1+1+1), "(", e18, nl, spaces(1+1+1+1), "(", f20, nl, spaces(1+1+1+1+1), ")", ")", ")", ")", ")");
		assertEquals(concat + nl, s.toString());
/*		String a = "prelude";
		String b = "abcdefghijklmnopqrstuvwxyz";
		String c = "0123456789";
		String nl = "\n";
		String ht = "\t";
		SerializationBuilder s = new SerializationBuilder(nl, ht, 50, 8);
		appends(s, PUSH,           a, WBA, "(", WA, b, ",", SS, WH, / * "b,", WH,"c,", WH,"d,", "e,", WH,"f,","g,", WH, * / c, ")", WE, NL);
		String concat = concat(ht, a,      "(",     b, ",", nl, ht, "        ",                                           c, ")");
		assertEquals(concat + nl, s.toString()); */
		s.close();
	}

	private @NonNull String spaces(int count) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < count; i++) {
			s.append(' ');
		}
		return s.toString();
	}
}
