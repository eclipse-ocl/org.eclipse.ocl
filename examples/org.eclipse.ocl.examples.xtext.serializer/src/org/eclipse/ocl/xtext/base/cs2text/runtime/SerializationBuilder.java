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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.ToDebugString.ToDebugStringable;

/**
 * SerializationBuilder builds the intermediate serialization as an interleaving of concrete strings and virtual
 * characters such as soft-space. The append merthods do the building.
 *
 * Finally the toString returns a simple string with the virtual characters converted to concrete equivalents where
 * appropriate.
 */
public class SerializationBuilder implements ToDebugStringable
{
	/**
	 * The virtual character/string to start a new line, but pairs of half new lines generate one
	 * rather than two new lines.
	 */
	public static final @NonNull String HALF_NEW_LINE = new String("«½\\n»");

	/**
	 * The virtual character/string to start a new line.
	 */
	public static final @NonNull String NEW_LINE = new String("«\\n»");

	/**
	 * The virtual character/string to suppress soft space separation.
	 */
	public static final @NonNull String NO_SPACE = new String("«! »");

	/**
	 * The virtual character/string to start a new line,  but avoid multiple new lines.
	 */
	public static final @NonNull String SOFT_NEW_LINE = new String("«?\\n»");

	/**
	 * The virtual character/string to ensure space separation but avoid redundant spacing.
	 */
	public static final @NonNull String SOFT_SPACE = new String("«? »");

	/**
	 * The virtual character/string to push a standard indentation on the stack.
	 */
	public static final @NonNull String PUSH = new String("«+»");

	/**
	 * The virtual character/string to push the next string as indentation on the stack.
	 */
	public static final @NonNull String PUSH_NEXT = new String("«+?»");

	/**
	 * The virtual character/string to pop the most recent indenttatio push.
	 */
	public static final @NonNull String POP = new String("«-»");

	/**
	 * The string for a new-line; typically \n unless \r\n or \n\r really wanted.
	 */
	protected final @NonNull String newLineString;

	/**
	 * The string for an additional level of indentation; typically four spaces.
	 */
	protected final @NonNull String indentString;

	/**
	 * The strings to be rendered as output after resolving virtual character strings such as SOFT_SPACE.
	 */
	protected final @NonNull List<@NonNull String> strings = new ArrayList<>(1000);

	/**
	 * The strings to be concatenated as indentation following a new line.
	 */
	private @NonNull Stack<@NonNull String> indents = new Stack<>();

	/**
	 * True if a new-line has been output, and that indentation must preceded further non-white output.
	 */
	private boolean indentsPending = false;

	/**
	 * Errors embedded in the output and also here.
	 */
	private @Nullable List<@NonNull String> errors = null;

	@SuppressWarnings("unused")		// Used to obtain a raw debug representation
	private @NonNull ToDebugString toDebugString = new ToDebugString(this);

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
		assert indentsPending;
		for (int i = 0; i < indents.size(); i++) {
			s.append(indents.get(i));
		}
		indentsPending = false;
	}

	protected String appendNewLine(@NonNull StringBuilder s) {
		s.append(newLineString);
		indentsPending = true;
		return NO_SPACE;
	}

	protected String appendString(@NonNull StringBuilder s, @NonNull String string) {
		if (indentsPending) {
			appendIndents(s);
		}
		s.append(string);
		return null;
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
		@Nullable String pendingString = NO_SPACE;
		final int indexMax = strings.size();
		for (int index = 0; index < indexMax; ) {
			@NonNull String nextString = strings.get(index++);
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
			else if (nextString == NEW_LINE) {
				pendingString = appendNewLine(s);
			}
			else if (nextString == HALF_NEW_LINE) {
				if ((pendingString == null) || (pendingString == NO_SPACE)) {
					pendingString = HALF_NEW_LINE;
				}
				else if ((pendingString == SOFT_NEW_LINE) || (pendingString == HALF_NEW_LINE)) {
					appendNewLine(s);
					pendingString = NO_SPACE;
				}
			}
			else if (nextString == SOFT_NEW_LINE) {
				if ((pendingString == null) || (pendingString == NO_SPACE) || (pendingString == SOFT_SPACE)) {
					pendingString = SOFT_NEW_LINE;
				}
			}
			else if (nextString == SOFT_SPACE) {
				if (pendingString == null) {
					pendingString = SOFT_SPACE;
				}
				else if (pendingString == SOFT_NEW_LINE) {
					pendingString = appendNewLine(s);
				}
			}
			else if (nextString == NO_SPACE) {
				if ((pendingString == null) || (pendingString == SOFT_SPACE)) {
					pendingString = NO_SPACE;
				}
				else  if (pendingString == SOFT_NEW_LINE) {
					pendingString = appendNewLine(s);
				}
			}
			else {	// nextString - hard text
				if (pendingString == SOFT_NEW_LINE) {
					pendingString = appendNewLine(s);
				}
				else if (pendingString == SOFT_SPACE) {
					appendString(s, " ");
				}
				pendingString = appendString(s, nextString);
			}
		}
		if (!indentsPending) {
			appendNewLine(s);
		}
		return String.valueOf(s);
	}
}