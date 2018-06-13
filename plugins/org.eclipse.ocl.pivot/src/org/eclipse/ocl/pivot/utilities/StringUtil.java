/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.math.BigInteger;
import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.osgi.util.NLS;


public class StringUtil
{	
	private static final String maxIntValue = Integer.toString(Integer.MAX_VALUE);
	private static final int maxIntSize = maxIntValue.length();
	private static final String maxLongValue = Long.toString(Long.MAX_VALUE);
	private static final int maxLongSize = maxLongValue.length();

	/**
	 * Copied from {@link java.util.Properties}
	 */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Append a multiplicity string such as "[1..5]" to a StringBuilder.
	 * <br>
	 * Shortforms such as "[?]",  "[+]",  "[*]",  "[1]",  "[2..*]" are used if possible.
	 * <br>
	 * A -ve upper signals unlimited.
	 */
	public static void appendMultiplicity(@NonNull StringBuilder s, long lower, long upper, boolean isNullFree) {
		s.append("[");
		if (upper < 0) {
			if (lower == 1) {
				s.append("+");
			}
			else {
				if (lower != 0) {
					s.append(lower);
					s.append("..");
				}
				s.append("*");
			}
		}
		else if ((lower == 0) && (upper == 1)) {
			s.append("?");
		}
		else {
			s.append(lower);
			if (lower != upper) {
				s.append("..");
				s.append(upper);
			}
		}
		s.append("|");
		s.append(isNullFree ? "1" : "?");
		s.append("]");
	}
	/**
	 * @deprecated add isNullFree argument
	 */
	@Deprecated
	public static void appendMultiplicity(@NonNull StringBuilder s, long lower, long upper) {
		appendMultiplicity(s, lower, upper, false);
	}

	public static @NonNull String bind(String messageTemplate, Object... bindings) {
		@SuppressWarnings("null") @NonNull String result = NLS.bind(messageTemplate, bindings);
		return result;
	}

	/**
	 * Mostly copied from {@link java.util.Properties#loadConvert} via
	 * {@link org.eclipse.xtext.util.Strings#convertFromJavaString}
	 */
	public static@NonNull  String convertFromOCLString(@NonNull String javaString) {
		char[] in = javaString.toCharArray();
		int off = 0;
		int len = javaString.length();
		char[] convtBuf = new char[len];
		char aChar;
		char[] out = convtBuf;
		int outLen = 0;
		int end = off + len;
	
		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					if(off+4 > end)
						throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					else if (aChar == 'b')
						aChar = '\b';
					else if (aChar == '"')
						aChar = '\"';
					else if (aChar == '\'')
						aChar = '\'';
					else if (aChar == '\\')
						aChar = '\\';
					else
						throw new IllegalArgumentException("Illegal escape character \\" + aChar);
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	/**
	 * Mostly copied from {@link java.util.Properties#saveConvert} via
	 * {@link org.eclipse.xtext.util.Strings#convertToJavaString}
	 */
	public static String convertToOCLString(String theString) {
		if (theString == null) {
			return null;
		}
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuilder outBuffer = new StringBuilder(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
				case ' ':
					outBuffer.append(' ');
					break;
				case '\t':
					outBuffer.append('\\');
					outBuffer.append('t');
					break;
				case '\n':
					outBuffer.append('\\');
					outBuffer.append('n');
					break;
				case '\r':
					outBuffer.append('\\');
					outBuffer.append('r');
					break;
				case '\f':
					outBuffer.append('\\');
					outBuffer.append('f');
					break;
				case '\b':
					outBuffer.append('\\');
					outBuffer.append('b');
					break;
				case '\'':
					outBuffer.append('\\');
					outBuffer.append('\'');
					break;
				case '\\':
					outBuffer.append('\\');
					outBuffer.append('\\');
					break;
//				case '"':
//					outBuffer.append('\\');
//					outBuffer.append('"');
//					break;
				default:
					if (((aChar < 0x0020) || (aChar > 0x007e))) {
						outBuffer.append('\\');
						outBuffer.append('u');
						outBuffer.append(toHex((aChar >> 12) & 0xF));
						outBuffer.append(toHex((aChar >> 8) & 0xF));
						outBuffer.append(toHex((aChar >> 4) & 0xF));
						outBuffer.append(toHex(aChar & 0xF));
					} else {
						outBuffer.append(aChar);
					}
			}
		}
		return outBuffer.toString();
	}

	public static @NonNull Number createNumberFromString(@NonNull String aValue) throws NumberFormatException {
		if ("*".equals(aValue)) {
			return Unlimited.INSTANCE;
		}
		int len = aValue.length();
		if ((len < maxIntSize) || ((len == maxIntSize) && (maxIntValue.compareTo(aValue) >= 0))) {
			Integer result = Integer.valueOf(aValue);
			assert result != null;
			return result;
		}
		else if ((len < maxLongSize) || ((len == maxLongSize) && (maxLongValue.compareTo(aValue) >= 0))) {
			Long result = Long.valueOf(aValue);
			assert result != null;
			return result;
		}
		else {
			return new BigInteger(aValue);
		}
	}

	public static @NonNull String formatMultiplicity(@Nullable ETypedElement typedElement) {
		if (typedElement == null)
			return "";
		int lower = typedElement.getLowerBound();
		int upper = typedElement.getUpperBound();
		if (lower == upper)
			return "" + Integer.toString(lower);
		else if (lower == 0) {
			if (upper < 0)
				return "*";
			else if (upper == 1)
				return "?";
		}
		else if (lower == 1) {
			if (upper < 0)
				return "+";
		}
		return Integer.toString(lower) + ".." + (upper >= 0 ? Integer.toString(upper) : "*");
	}

	public static @NonNull String formatOrdered(@Nullable ETypedElement typedElement) {
		boolean isOrdered = typedElement != null ? (typedElement.isOrdered() && typedElement.isMany()) : false;
		return isOrdered ? "{ordered}" : "";
	}

	public static@NonNull  String formatString(@Nullable String name) {
		return name != null ? name : "<null>";
	}

	public static @NonNull String formatUnique(@Nullable ETypedElement typedElement) {
		boolean isOrdered = typedElement != null ? (typedElement.isUnique() && typedElement.isMany()) : false;
		return isOrdered ? "{unique}" : "";
	}

	public static String getIndentation(int depth, @NonNull String string) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			s.append(string);
		}
		return s.toString();
	}

	/**
	 * Return a composite string comprising each element od strings separated by separator.
	 * A null strings is returned as a null string. An empty strings as an empty string.
	 * 
	 * @param strings strings to be spliced
	 * @param separator between elements
	 * @return spliced string
	 */
	public static String splice(List<String> strings, String separator) {
		if (strings == null)
			return null;
		int iMax = strings.size();
		if (iMax <= 0)
			return "";
		if (iMax == 1)
			return strings.get(0);
		StringBuilder s = new StringBuilder();
		s.append(strings.get(0));
		for (int i = 1; i < iMax; i++) {
			s.append(separator);
			s.append(strings.get(i));
		}
		return s.toString();
	}

	/**
	 * Copied from {@link java.util.Properties}
	 */
	public static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}
}
