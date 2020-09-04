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
package org.eclipse.ocl.examples.xtext.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString.ToDebugStringable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

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
	 * The virtual character/string to pop the most recent indenttatio push.
	 */
	public static final @NonNull String POP = new String("«-»");

	/**
	 * The virtual character/string to push a standard indentation on the stack.
	 */
	public static final @NonNull String PUSH = new String("«+»");

	/**
	 * The virtual character/string to push the next string as indentation on the stack.
	 */
	public static final @NonNull String PUSH_NEXT = new String("«+?»");

	/**
	 * The virtual character/string to start a new line,  but avoid multiple new lines.
	 */
	public static final @NonNull String SOFT_NEW_LINE = new String("«?\\n»");

	/**
	 * The virtual character/string to ensure space separation but avoid redundant spacing.
	 */
	public static final @NonNull String SOFT_SPACE = new String("«? »");

	/**
	 * The virtual character/string to mark the beginning of a wrap region within which either
	 * all or no wrap-here preferred wraappimg points are exploited,
	 */
	public static final @NonNull String WRAP_BEGIN_ALL = new String("«+w*»");

	/**
	 * The virtual character/string to mark the beginning of a wrap region within which any
	 * all or no wrap-here preferred wraapping points are exploited,
	 */
	public static final @NonNull String WRAP_BEGIN_SOME = new String("«+w?»");

	/**
	 * The virtual character/string to mark the end of a wrap region.
	 */
	public static final @NonNull String WRAP_END = new String("«-w»");

	/**
	 * The virtual character/string to mark a preferred wrapping pont.
	 */
	public static final @NonNull String WRAP_HERE = new String("«w»");

	/**
	 * The virtual character/string to mark the indentation reference for subsequent wraps.
	 */
	public static final @NonNull String WRAP_INDENT = new String("«wi»");

	protected static class WrappingContext
	{
		private final int startLine;
		private final int startColumn;
		private final int startOffset;
		private int lines = 0;
		private int columns = 0;
		private int offsets = 0;
		protected final boolean isAllOrNone;
		private final @Nullable WrappingContext parentContext;
		private @Nullable List<@NonNull WrappingContext> childContexts = null;
		private final Map<@NonNull Integer, @NonNull Integer> hereColumn2count = new HashMap<>();
		private final Map<@NonNull Integer, @NonNull Integer> indentsColumn2count = new HashMap<>();

		public WrappingContext() {
			this(null, 0, 0, 0, false);
		}

		protected WrappingContext(@Nullable WrappingContext parentContext, int startLine, int startColumn, int startOffset, boolean isAllOrNone) {
			this.startLine = startLine;
			this.startColumn = startColumn;
			this.startOffset = startOffset;
			this.isAllOrNone = isAllOrNone;
			this.parentContext = parentContext;
		}

		public void addHere(int nextLine, int nextColumn, int length) {
			// TODO Auto-generated method stub

		}

		public void addIndent(int nextLine, int nextColumn, int length) {
			// TODO Auto-generated method stub

		}

		public @NonNull WrappingContext pop(int endLine, int endColumn, int endOffset) {
			this.lines = endLine - startLine;
			this.columns = endColumn - startColumn;
			this.offsets = endOffset - startOffset;
			return ClassUtil.nonNullState(parentContext);
		}

		public @NonNull WrappingContext push(int startLine, int startColumn, int startOffset, boolean isAllOrNone) {
			List<@NonNull WrappingContext> childContexts2 = childContexts;
			if (childContexts2 == null) {
				childContexts = childContexts2 = new ArrayList<>();
			}
			WrappingContext childContext = new WrappingContext(this, startLine, startColumn, startOffset, isAllOrNone);
			childContexts2.add(childContext);
			return childContext;
		}
	}

	protected static class WrappingStringBuilder
	{
		private final @NonNull StringBuilder s;

		/**
		 * The maximum number of characters (excluding new line characters) per line. Less than 1 for no limit.
		 */
		protected final int lineLength;

		/**
		 * The number of spaces between tab columns; typically 8..
		 */
		protected final int tabWidth;

		/**
		 * The line number for the next character - line 0 is first.
		 */
		private int nextLine = 0;

		/**
		 * The column number for the next character - column 0 is first.
		 */
		private int nextColumn = 0;

		private @NonNull WrappingContext rootContext = new WrappingContext();
		private @NonNull WrappingContext currentContext = rootContext;

		public WrappingStringBuilder(@NonNull StringBuilder s, int lineLength, int tabWidth) {
			this.s = s;
			this.lineLength = lineLength;
			this.tabWidth = tabWidth;
		}

		public void append(@Nullable String string) {
			if (string != null) {
				if (WRAP_BEGIN_ALL.equals(string)) {
					currentContext = currentContext.push(nextLine, nextColumn, s.length(), true);
				}
				else if (WRAP_BEGIN_SOME.equals(string)) {
					currentContext = currentContext.push(nextLine, nextColumn, s.length(), false);
				}
				else if (WRAP_END.equals(string)) {
					currentContext = currentContext.pop(nextLine, nextColumn, s.length());
				}
				else if (WRAP_HERE.equals(string)) {
					currentContext.addHere(nextLine, nextColumn, s.length());
				}
				else if (WRAP_INDENT.equals(string)) {
					currentContext.addIndent(nextLine, nextColumn, s.length());
				}
				else {
					for (int i = 0; i < string.length(); i++) {
						char c = string.charAt(i);
						s.append(c);
						if (c == '\n') {
							nextColumn = 0;
							nextLine++;
						}
						else if (c == '\r') {
							// ignore
						}
						else if (c == '\t') {
							nextColumn = ((nextColumn % tabWidth) + 1) * tabWidth;
						}
						else {
							nextColumn++;
						}
					}
				}
			}
		}

		public void close() {

		}

		@Override
		public @NonNull String toString() {
			@SuppressWarnings("null")
			@NonNull String castString = (@NonNull String)String.valueOf(s);
			return castString;
		}
	}

	protected static class IndentingStringBuilder
	{

		/**
		 * The string for a new-line; typically \n unless \r\n or \n\r really wanted.
		 */
		protected final @NonNull String newLineString;

		/**
		 * The string for an additional level of indentation; typically four spaces.
		 */
		protected final @NonNull String indentString;

		/**
		 * The strings to be concatenated as indentation following a new line.
		 */
		private @NonNull Stack<@NonNull String> indents = new Stack<>();

		/**
		 * True if a new-line has been output, and that indentation must preceded further non-white output.
		 */
		private boolean indentsPending = false;

		private final @NonNull WrappingStringBuilder s;

		public IndentingStringBuilder(@NonNull WrappingStringBuilder s, @NonNull String newLineString, @NonNull String indentString) {
			this.s = s;
			this.newLineString = newLineString;
			this.indentString = indentString;
		}

		public void append(@NonNull String string) {
			s.append(string);
		}

		protected void appendIndents() {
			assert indentsPending;
			for (int i = 0; i < indents.size(); i++) {
				append(indents.get(i));
			}
			indentsPending = false;
		}

		protected String appendNewLine() {
			append(newLineString);
			indentsPending = true;
			return NO_SPACE;
		}

		protected String appendString(@NonNull String string) {
			if (indentsPending) {
				appendIndents();
			}
			append(string);
			return null;
		}

		public void close() {
			if (!indentsPending) {
				appendNewLine();
			}
			s.close();
		}

		public void pop() {
			indents.pop();
		}

		public void push(@NonNull String indentString) {
			indents.push(indentString);
		}

		@Override
		public @NonNull String toString() {
			@SuppressWarnings("null")
			@NonNull String castString = (@NonNull String)String.valueOf(s);
			return castString;
		}
	}

	/**
	 * The string for a new-line; typically \n unless \r\n or \n\r really wanted.
	 */
	protected final @NonNull String newLineString;

	/**
	 * The string for an additional level of indentation; typically four spaces.
	 */
	protected final @NonNull String indentString;

	/**
	 * The maximum number of characters (excluding new line characters) per line. Less than 1 for no limit.
	 */
	protected final int lineLength;

	/**
	 * The number of spaces between tab columns; typically 8..
	 */
	protected final int tabWidth;

	/**
	 * The strings to be rendered as output after resolving virtual character strings such as SOFT_SPACE.
	 */
	protected final @NonNull List<@NonNull String> strings = new ArrayList<>(1000);

	/**
	 * Errors embedded in the output and also here.
	 */
	private @Nullable List<@NonNull String> errors = null;

	@SuppressWarnings("unused")		// Used to obtain a raw debug representation
	private @NonNull ToDebugString toDebugString = new ToDebugString(this);

	public SerializationBuilder() {
		this("\n", "    ");
	}

	public SerializationBuilder(@NonNull String newLineString, @NonNull String indentString) {
		this(newLineString, indentString, 80, 8);
	}

	public SerializationBuilder(@NonNull String newLineString, @NonNull String indentString, int lineLength, int tabWidth) {
		this.newLineString = newLineString;
		this.indentString = indentString;
		this.lineLength = lineLength;
		this.tabWidth = tabWidth;
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

	public @Nullable Iterable<@NonNull String> getErrors() {
		return errors;
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
		WrappingStringBuilder sw = new WrappingStringBuilder(new StringBuilder(), lineLength, tabWidth);
		IndentingStringBuilder s = new IndentingStringBuilder(sw, newLineString, indentString);
		@Nullable String pendingString = NO_SPACE;
		final int indexMax = strings.size();
		for (int index = 0; index < indexMax; ) {
			@NonNull String nextString = strings.get(index++);
			if (nextString == PUSH) {
				s.push(indentString);
			}
			else if (nextString == PUSH_NEXT) {
				if (index < indexMax) {
					s.push(strings.get(index++));
				}
			}
			else if (nextString == POP) {
				s.pop();
			}
			else if (nextString == NEW_LINE) {
				pendingString = s.appendNewLine();
			}
			else if (nextString == HALF_NEW_LINE) {
				if ((pendingString == null) || (pendingString == NO_SPACE)) {
					pendingString = HALF_NEW_LINE;
				}
				else if ((pendingString == SOFT_NEW_LINE) || (pendingString == HALF_NEW_LINE)) {
					s.appendNewLine();
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
					pendingString = s.appendNewLine();
				}
			}
			else if (nextString == NO_SPACE) {
				if ((pendingString == null) || (pendingString == SOFT_SPACE)) {
					pendingString = NO_SPACE;
				}
				else  if (pendingString == SOFT_NEW_LINE) {
					pendingString = s.appendNewLine();
				}
			}
			else {	// nextString - hard text
				if (pendingString == SOFT_NEW_LINE) {
					pendingString = s.appendNewLine();
				}
				else if (pendingString == SOFT_SPACE) {
					s.appendString(" ");
				}
				pendingString = s.appendString(nextString);
			}
		}
		s.close();
		return s.toString();
	}
}