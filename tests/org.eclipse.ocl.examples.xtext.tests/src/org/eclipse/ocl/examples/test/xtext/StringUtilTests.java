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

import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * Tests.
 */
public class StringUtilTests extends XtextTestCase
{
	/**
	 * Checks that the local *.xtextbin is the same as the pre-compiled Java implementation.
	 *
	 * FIXME check the library/model version instead.
	 */
	public void testStringUtil_OCLconvert() throws Exception {
		doBadDecode("\\u123", "Malformed \\uxxxx encoding.");
		doEncodeDecode(null, null);
		doEncodeDecode("", "");
		doEncodeDecode("a", "a");
		doEncodeDecode("\'", "\\'");
		doEncodeDecode("\'\r\n\t\b\f\"", "\\'\\r\\n\\t\\b\\f\"");		// " is not encoded
		doEncodeDecode("\u1234", "\\u1234");
		//		doEncodeDecode("\u1234", "\\u1234");
	}

	private void doBadDecode(String encodedString, String expectedMessage) {
		try {
			String decodedString = StringUtil.convertFromOCLString(encodedString);
		}
		catch (IllegalArgumentException e) {
			assertEquals(expectedMessage, e.getMessage());
		}
		//		assertEquals(unencodedString, decodedString);
		//		if (unencodedString.equals(decodedString)) {
		//			//				assertSame(unencodedString, decodedString);
		//		}
		//	}
	}

	private void doEncodeDecode(String unencodedString, String expectedEncodedString) {
		String encodedString = StringUtil.convertToOCLString(unencodedString);
		assertEquals(expectedEncodedString, encodedString);
		if (expectedEncodedString != null) {
			if (expectedEncodedString.equals(encodedString)) {
				//			assertSame(expectedEncodedString, encodedString);
			}
			String decodedString = StringUtil.convertFromOCLString(expectedEncodedString);
			assertEquals(unencodedString, decodedString);
			if (unencodedString.equals(decodedString)) {
				//				assertSame(unencodedString, decodedString);
			}
		}
	}
}
