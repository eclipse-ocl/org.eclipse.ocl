/*******************************************************************************
 * Copyright (c) 2006, 2020 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.util;

/**
 * A proxy interface to isolate dependencies on the ICU4J library for
 * internationalization/UNICODE support.  This enables deployments that delegate
 * to standard Java APIs for text manipulation instead of the large ICU library.
 *
 * 2020, with ICU4J usage commented as unreferenceable since 2018 and Bug 562582 terminating
 * platform usage in 2020, it is time to totally delete the ICU4J references.
 *
 * @author Christian W. Damus (cdamus)
 */
@Deprecated /* @deprecated use Character/String directly. */
public abstract class UnicodeSupport {
	//	private static final UnicodeSupport INSTANCE = new UnicodeSupport.Default();

	/**
	 * This class is not instantiable nor subclassable by clients.
	 */
	UnicodeSupport() {
		super();
	}

	/**
	 * Obtains the code point at the specified 16-bit character <tt>offset</tt>
	 * of a string buffer.
	 *
	 * @param buffer a string buffer
	 * @param offset a 16-bit offset into the string buffer.  Must be between
	 *     0 (inclusive) and the buffer's length (exclusive)
	 *
	 * @return the UNICODE code point at the specified offset
	 */
	public static int codePointAt(StringBuffer buffer, int offset) {
		return buffer.codePointAt(offset);
	}

	/**
	 * Sets the code point at the specified 16-bit character <tt>offset</tt>
	 * of a string buffer.  This will lengthen the buffer, in the case that the
	 * supplied code point is wider than the one it is replacing.
	 *
	 * @param buffer a string buffer
	 * @param offset a 16-bit offset into the string buffer.  Must be between
	 *     0 (inclusive) and the buffer's length (exclusive)
	 * @param codePoint the new UNICODE code point value to substitute at the
	 *     specified offset
	 */
	public static void setCodePointAt(StringBuffer buffer, int offset, int codePoint) {
		//		INSTANCE.setCodePointAt0(buffer, offset, codePoint);
		int existingCodePoint = buffer.codePointAt(offset);
		int width = Character.charCount(existingCodePoint);

		buffer.replace(
			offset,
			offset + width,
			String.valueOf(Character.toChars(codePoint)));
	}

	/**
	 * Obtains the location of the code point <tt>shift</tt> positions from the
	 * specified 16-bit character <tt>offset</tt> of a string buffer.
	 *
	 * @param text a string of UNICODE text
	 * @param offset a 16-bit offset into the string buffer.  Must be between
	 *     0 (inclusive) and the buffer's length (exclusive)
	 * @param shift a positive or negative index, as a count of code points,
	 *     relative to the code point at the 16-bit offset
	 *
	 * @return the 16-bit index of the UNICODE code point at the specified
	 *     location relative to the offset
	 */
	public static int shiftCodePointOffsetBy(String text, int offset, int shift) {
		return text.offsetByCodePoints(offset, shift);
	}

	/**
	 * Converts a UNICODE code point to lower case.
	 *
	 * @param codePoint a UNICODE code point
	 *
	 * @return the lower case equivalent
	 */
	public static int toLowerCase(int codePoint) {
		return Character.toLowerCase(codePoint);
	}

	/**
	 * Converts a UNICODE code point to upper case.
	 *
	 * @param codePoint a UNICODE code point
	 *
	 * @return the upper case equivalent
	 */
	public static int toUpperCase(int codePoint) {
		return Character.toUpperCase(codePoint);
	}

	/**
	 * Converts a UNICODE string to lower case.
	 *
	 * @param text a UNICODE string
	 *
	 * @return the lower case equivalent
	 */
	public static String toLowerCase(String text) {
		return text.toLowerCase();
	}

	/**
	 * Converts a UNICODE string to upper case.
	 *
	 * @param text a UNICODE string
	 *
	 * @return the upper case equivalent
	 */
	public static String toUpperCase(String text) {
		return text.toUpperCase();
	}


	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract int codePointAt0(StringBuffer buffer, int offset);

	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract void setCodePointAt0(StringBuffer buffer, int offset, int codePoint);

	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract int shiftCodePointOffsetBy0(String text, int offset, int shift);

	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract int toLowerCase0(int codePoint);

	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract int toUpperCase0(int codePoint);

	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract String toLowerCase0(String text);

	/**
	 * ICU- or Java-specific implementation of the corresponding static API.
	 */
	abstract String toUpperCase0(String text);

	/**
	 * Default implementation of the ICU stub interface, for deployments without
	 * ICU4J.  This implementation just delegates to the relevant Java text APIs.
	 *
	 * @author Christian W. Damus (cdamus)
	 *
	static final class Default extends UnicodeSupport {

		@Override
		int codePointAt0(StringBuffer buffer, int offset) {
			return buffer.codePointAt(offset);
		}

		@Override
		int shiftCodePointOffsetBy0(String text, int offset, int shift) {
			return text.offsetByCodePoints(offset, shift);
		}

		@Override
		void setCodePointAt0(StringBuffer buffer, int offset, int codePoint) {
			int existingCodePoint = buffer.codePointAt(offset);
			int width = Character.charCount(existingCodePoint);

			buffer.replace(
				offset,
				offset + width,
				String.valueOf(Character.toChars(codePoint)));
		}

		@Override
		int toLowerCase0(int codePoint) {
			return Character.toLowerCase(codePoint);
		}

		@Override
		String toLowerCase0(String text) {
			return text.toLowerCase();
		}

		@Override
		int toUpperCase0(int codePoint) {
			return Character.toUpperCase(codePoint);
		}

		@Override
		String toUpperCase0(String text) {
			return text.toUpperCase();
		}
	} */
}
