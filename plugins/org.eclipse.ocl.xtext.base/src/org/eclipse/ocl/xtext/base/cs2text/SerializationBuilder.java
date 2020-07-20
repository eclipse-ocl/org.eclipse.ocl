/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

/**
 * SerializationBuilder builds the intermediate serialization as an interleaving of concrete strings and virtual
 * characters such as soft-space. The append merthods do the building.
 *
 * Finally the toRenderedString returns a simple string with the virtual characters converted to concrete equivalents where
 * appropriate.
 */
public class SerializationBuilder
{
	public static final @NonNull String SOFT_SPACE = new String("« »");
	public static final @NonNull String PUSH = new String("«+»");
	public static final @NonNull String POP = new String("«-»");

	protected final @NonNull List<@NonNull String> strings = new ArrayList<>(1000);
	private int indentDepth = 0;

	public SerializationBuilder() {}

	public void append(@NonNull String string) {
//		if ((string == null) || string.contains("ordered")) {
//			getClass(); // XXX debugging
//		}
		strings.add(string);
	}

	public @NonNull String toRenderedString() {
		StringBuilder s = new StringBuilder();
		char prevCh = ' ';
		for (@NonNull String nextString : strings) {
		//	int nextLength = nextString.length();
		//	char nextCh = nextLength <= 0 ? ' ' : nextString.charAt(0);
			if (nextString == PUSH) {
				indentDepth++;
			}
			else if (nextString == POP) {
				indentDepth--;
			}
			else {
				switch (prevCh) {
				/*	case -1: {
						if (ch == SOFT_SPACE) {}
						else {
							s.append(ch);
						}
						break;
					} */
					case ' ': {
						if (nextString == SOFT_SPACE) {}
						else {
							s.append(nextString);
						}
						break;
					}
					case '\n': {
						if (nextString == SOFT_SPACE) {}
						else {
							for (int i = 0; i < indentDepth; i++) {
								s.append("    ");
							}
							s.append(nextString);
						}
						break;
					}
					default: {
						if (nextString == SOFT_SPACE) {
							s.append(' ');
						}
						else {
							s.append(nextString);
						}
						break;
					}
				}
			}
			int length = s.length();
			if (length > 0) {
				prevCh = s.charAt(length-1);
			}
		}
		return String.valueOf(s);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		for (@NonNull String string : strings) {
			s.append(string);
		}
		return String.valueOf(s);
	}

}