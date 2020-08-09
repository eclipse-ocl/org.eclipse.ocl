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
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString.ToDebugStringable;

/**
 * SerializationBuilder builds the intermediate serialization as an interleaving of concrete strings and virtual
 * characters such as soft-space. The append merthods do the building.
 *
 * Finally the toRenderedString returns a simple string with the virtual characters converted to concrete equivalents where
 * appropriate.
 */
public class SerializationBuilder implements ToDebugStringable
{
	public static final @NonNull String HALF_NEW_LINE = new String("«½\\n»");
	public static final @NonNull String NEW_LINE = new String("«\\n»");
	public static final @NonNull String NO_SPACE = new String("«! »");
	public static final @NonNull String SOFT_NEW_LINE = new String("«?\\n»");
	public static final @NonNull String SOFT_SPACE = new String("«? »");
	public static final @NonNull String PUSH = new String("«+»");
	public static final @NonNull String PUSH_NEXT = new String("«+?»");
	public static final @NonNull String POP = new String("«-»");

	private static final int HALF_NEW_LINE_PREVCH = -1;
	private static final int FULL_NEW_LINE_PREVCH = -2;
	private static final int NO_SPACE_PREVCH = -3;

	protected final @NonNull String newLineString;
	protected final @NonNull String indentString;
	protected final @NonNull List<@NonNull String> strings = new ArrayList<>(1000);
	private @NonNull Stack<@NonNull String> indents = new Stack<>();
	private @Nullable List<@NonNull String> errors = null;
	@SuppressWarnings("unused")		// Used to obtain a raw debug representation
	private @NonNull ToDebugString toDebugSring = new ToDebugString(this);

	public SerializationBuilder() {
		this.newLineString = "\n";
		this.indentString = "    ";
	}

	public SerializationBuilder(@NonNull String newLineString, @NonNull String indentString) {
		this.newLineString = newLineString;
		this.indentString = indentString;
	}

	public void append(@Nullable String string) {
		if (string != null) {
			strings.add(string);
		}
	}

	public void appendError(@NonNull String string) {
		List<@NonNull String> errors2 = errors;
		if (errors2 == null) {
			errors = errors2 = new ArrayList<>();
		}
		errors2.add(string);
		append(string);
	}

	protected void appendIndents(StringBuilder s) {
		for (int i = 0; i < indents.size(); i++) {
			s.append(indents.get(i));
		}
	}

	protected void appendNewLine(@NonNull StringBuilder s) {
		s.append(newLineString);
	}

	protected int appendString(@NonNull StringBuilder s, @NonNull String string) {
		s.append(string);
		return s.charAt(s.length()-1);
	}

	public boolean hasErrors() {
		return errors != null;
	}

	public void throwErrors() {
		List<@NonNull String> errors2 = errors;
		if (errors2 != null) {
			StringBuilder s = new StringBuilder();
			for (@NonNull String error : errors2) {
				if (s.length() > 0) {
					s.append("\n");
				}
				s.append(error);
			}
			throw new IllegalStateException(s.toString());		// FIXME A more consistent exception
		}
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		for (@NonNull String string : strings) {
			s.append(string);
			if ((string == NEW_LINE) || (string == HALF_NEW_LINE) || (string == SOFT_NEW_LINE)) {
				s.append("\n");
			}
		}
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		int prevCh = FULL_NEW_LINE_PREVCH;
		final int indexMax = strings.size();
		for (int index = 0; index < indexMax; ) {
			@NonNull String nextString = strings.get(index++);
			@Nullable String nextNextString = index < indexMax ? strings.get(index) : null;
			if (nextString == PUSH) {
				indents.push(indentString);
			}
			else if (nextString == PUSH_NEXT) {
				if (index < indexMax) {
					indents.push(strings.get(index++));
				}
			}
			else if (nextString == POP) {
				indents.pop();
			}
			else {
				switch (prevCh) {
					case ' ': {
						if (nextString == NO_SPACE) {
							prevCh = NO_SPACE_PREVCH;
						}
						else if (nextString == SOFT_SPACE) {}
						else if (nextString == SOFT_NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else if (nextString == HALF_NEW_LINE) {
							prevCh = HALF_NEW_LINE_PREVCH;
						}
						else if (nextString == NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else {
							prevCh = appendString(s, nextString);
						}
						break;
					}
					case NO_SPACE_PREVCH: {
						if (nextString == NO_SPACE) {}
						else if (nextString == SOFT_SPACE) {}
						else if (nextString == SOFT_NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else if (nextString == HALF_NEW_LINE) {
							prevCh = HALF_NEW_LINE_PREVCH;
						}
						else if (nextString == NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else {
							prevCh = appendString(s, nextString);
						}
						break;
					}
					case FULL_NEW_LINE_PREVCH: {	// FIXME system new line chars
						if (nextString == NO_SPACE) {}
						else if (nextString == SOFT_SPACE) {}
						else if (nextString == SOFT_NEW_LINE) {}
						else if (nextString == HALF_NEW_LINE) {
						//	appendNewLine(s);
							prevCh = HALF_NEW_LINE_PREVCH;
						}
						else if (nextString == NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else {
							appendIndents(s);
							prevCh = appendString(s, nextString);
						}
						break;
					}
					case HALF_NEW_LINE_PREVCH: {	// FIXME system new line chars
						if (nextString == NO_SPACE) {}
						else if (nextString == SOFT_SPACE) {}
						else if (nextString == SOFT_NEW_LINE) {}
						else if (nextString == HALF_NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else if (nextString == NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else {
							appendIndents(s);
							prevCh = appendString(s, nextString);
						}
						break;
					}
					default: {
						if (nextString == NO_SPACE) {
							prevCh = NO_SPACE_PREVCH;
						}
						else if (nextString == SOFT_SPACE) {
							if (nextNextString != NO_SPACE) {
								prevCh = appendString(s, " ");
							}
						}
						else if (nextString == HALF_NEW_LINE) {
							prevCh = HALF_NEW_LINE_PREVCH;
						}
						else if (nextString == SOFT_NEW_LINE) {}
						else if (nextString == NEW_LINE) {
							appendNewLine(s);
							prevCh = FULL_NEW_LINE_PREVCH;
						}
						else {
							prevCh = appendString(s, nextString);
						}
						break;
					}
				}
			}
		}
	//	if (prevCh != FULL_NEW_LINE_PREVCH) {
	//		appendNewLine(s);
	//	}
		return String.valueOf(s);
	}
}