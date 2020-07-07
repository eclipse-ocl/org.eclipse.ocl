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

import org.eclipse.jdt.annotation.NonNull;

public class SerializationBuilder
{
	private static final @NonNull Character SOFT_SPACE = new Character(Character.highSurrogate(' '));

	protected final @NonNull StringBuilder s;
	private final int startIndex;

	public SerializationBuilder(@NonNull StringBuilder s) {
		this.s = s;
		this.startIndex = s.length();
	}

	public void append(@NonNull String string) {
		s.append(string);
	}

	public void appendSoftSpace() {
		s.append(SOFT_SPACE);
	}

	public @NonNull SerializationBuilder createNestedSerializationBuilder() {
		return new SerializationBuilder(s);
	}

	public @NonNull String toRenderedString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < this.s.length(); i++) {
			char ch = this.s.charAt(i);
			int length = s.length();
			char prevCh = length <= 0 ? ' ' : s.charAt(length-1);
			switch (prevCh) {
			/*	case -1: {
					if (ch == SOFT_SPACE) {}
					else {
						s.append(ch);
					}
					break;
				} */
				case ' ': {
					if (ch == SOFT_SPACE) {}
					else {
						s.append(ch);
					}
					break;
				}
				case '\n': {
					if (ch == SOFT_SPACE) {}
					else {
						s.append(ch);
					}
					break;
				}
				default: {
					if (ch == SOFT_SPACE) {
						s.append(' ');
					}
					else {
						s.append(ch);
					}
					break;
				}
			}
		}
		return String.valueOf(s);
	}

	@Override
	public @NonNull String toString() {
		return String.valueOf(s.substring(startIndex));
	}

}