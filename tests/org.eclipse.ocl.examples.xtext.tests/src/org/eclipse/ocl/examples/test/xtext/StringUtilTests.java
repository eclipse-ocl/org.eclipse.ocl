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

import org.eclipse.jdt.annotation.NonNull;
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
		doEncodeDecode(null, null);
		doEncodeDecode("", "");
		doEncodeDecode("a", "a");
		doEncodeDecode("\'", "\\'");
		doEncodeDecode("\'\r\n\t\b\f\"", "\\'\\r\\n\\t\\b\\f\"");		// " is not encoded
		doEncodeDecode("\u1234", "\\u1234");
		doEncodeDecode("no change needed\noops", "no change needed\\noops");
		doBadDecode("\\u123", "Malformed Unicode escape: \\u123.");
		doBadDecode("ab\\u12fghi", "Malformed Unicode escape: \\u12.");
		doBadDecode("ab\\u'", "Malformed Unicode escape: \\u.");
	}

	private void doBadDecode(@NonNull String encodedString, String expectedMessage) {
		try {
			StringUtil.convertFromOCLString(encodedString);
		}
		catch (IllegalArgumentException e) {
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	private void doEncodeDecode(String unencodedString, String expectedEncodedString) {
		String encodedString = StringUtil.convertToOCLString(unencodedString);
		assertEquals(expectedEncodedString, encodedString);
		if (expectedEncodedString != null) {
			if (expectedEncodedString.equals(unencodedString)) {
				assertSame(unencodedString, encodedString);
			}
			String decodedString = StringUtil.convertFromOCLString(expectedEncodedString);
			assertEquals(unencodedString, decodedString);
			if (expectedEncodedString.equals(unencodedString)) {
				assertSame(expectedEncodedString, decodedString);
			}
		}
	}
}
